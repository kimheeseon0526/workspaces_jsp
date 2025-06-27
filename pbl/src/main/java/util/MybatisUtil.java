package util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

public class MybatisUtil {

	private static SqlSessionFactory sqlSessionFactory;
	
	static {
		InputStream is;
		try {
			is = Resources.getResourceAsStream("mybatis-config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
			TransactionFactory factory = new JdbcTransactionFactory();
			Environment environment = new Environment("mybatis-conf", factory, hikariCPUtil.getDataSource());
			sqlSessionFactory.getConfiguration().setEnvironment(environment);
		} catch (IOException e) {e.printStackTrace();}
	}

	public static SqlSession getSqlSession() {
		return getSqlSession(true);
	}
	
	public static SqlSession getSqlSession(boolean autoCommit) {
		return sqlSessionFactory.openSession(autoCommit);
	}
	
	
	public static void main(String[] args) {
		System.out.println(sqlSessionFactory.getConfiguration().getEnvironment().getDataSource());
		// 여기서부터 세로운 내용
		System.out.println(getSqlSession());
	}
	
}
