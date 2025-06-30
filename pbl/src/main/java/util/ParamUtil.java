package util;

import java.lang.reflect.Field;

import javax.servlet.http.HttpServletRequest;

import domain.Board;
import domain.Reply;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ParamUtil {
	public static <T> T get(HttpServletRequest req, Class<T> clazz) {
		try {
			T t = clazz.getDeclaredConstructor().newInstance();
//			Method[] methods = clazz.getDeclaredMethods();	//내가 선언한 메서드들만 출력
//			for(Method m : methods) {
//				log.info(m.getName());
//				if(m.getName().equals("setBno")) {
//					m.invoke(t, 5L); //bno가 100인 객체도 같이 출력됨
//				}
//			}
			Field[] fields = clazz.getDeclaredFields();
			for(Field f : fields) {
				log.info("{},{}", f.getName(), f.getType());
				String param = req.getParameter(f.getName());
				//이름 자체에 접근하는게 아니라 getName이 존재하면(null이 아니면 그 값을 가져옴)
				if(param != null) {
					f.setAccessible(true);	//생성자가 private일 경우 직접 접근 불가능해서 true 로 변경
					Object o = convert(param, f.getType());
					f.set(t, o);
					
				}

			}
			return t;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	@SuppressWarnings("unchecked")
	private static Object convert(String param, Class<?> type) {
		//int
		if(type == int.class || type == Integer.class) return Integer.parseInt(param);
		//long
		if(type == long.class || type == Long.class) return Long.parseLong(param);
		//boolean
		if(type == boolean.class || type == Boolean.class) return Boolean.parseBoolean(param);
		//enum
		if(type.isEnum()) return Enum.valueOf(type.asSubclass(Enum.class),param.toUpperCase());
		
		
		//String
		return param;
	}

}
