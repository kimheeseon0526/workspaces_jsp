package util;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class PasswordEncoder {

	public static String encode(String rawPassword) {
		return BCrypt.withDefaults().hashToString(BCrypt.MIN_COST, rawPassword.toCharArray());
	}

	public static boolean matches(String rawPassword, String encodedPassword) {
		BCrypt.Result result = BCrypt.verifyer().verify(rawPassword.toCharArray(), encodedPassword);
		return result.verified;

	}

	public static void main(String[] args) {
		String origin = "평문";
		String encoded = encode(origin);
		String encoded2 = encode(origin);
		String encoded3 = encode(origin);

		System.out.println(encoded); //$2a$04$CELbuejo2WIQI.Bzvxqz2.ZRmU6sRubWEGwCFgciEdwcNmrcq.yBK
		System.out.println(encoded2); //$2a$04$utnW9s0ajkMIR2C99ImWmuTAiYaAFDvnf58yhsWR/Oq5zvslQIQNe
		System.out.println(encoded3); //$2a$04$Rhgi8OagJR/5gPcooxU3IuYRywMsBqNJjPdRFbI9cPFQJYDv9wdlG
		
		String[] encodeds = {
				"$2a$04$CELbuejo2WIQI.Bzvxqz2.ZRmU6sRubWEGwCFgciEdwcNmrcq.yBK",
				"$2a$04$utnW9s0ajkMIR2C99ImWmuTAiYaAFDvnf58yhsWR/Oq5zvslQIQNe",
				"$2a$04$Rhgi8OagJR/5gPcooxU3IuYRywMsBqNJjPdRFbI9cPFQJYDv9wdlG"
		}; //db에 저장된 평문의 암호화 값
		
		System.out.println(matches("평문", encodeds[0])); 
		System.out.println(matches("평문2", encodeds[0])); 
		//matches로 맞는지 확인
		
		
	}
}
