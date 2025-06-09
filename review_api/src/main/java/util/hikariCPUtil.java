package util;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class hikariCPUtil {
	
	public static void main(String[] args) {
		System.out.println(dataSource);
	}
	
	private static HikariDataSource dataSource;
	static {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:mariadb://np.levelupseon.com:3306/sample");
		config.setUsername("sample");
		config.setPassword("1234");
		config.setDriverClassName("org.mariadb.jdbc.Driver");
		
		config.setMaximumPoolSize(10);
		config.setMinimumIdle(5);
		config.setIdleTimeout(30000);
		config.setConnectionTimeout(30000);
		config.setPoolName("MyHikariCP");
		
		dataSource = new HikariDataSource(config);
	}
	public static DataSource getDataSource() {
		return dataSource;
	}
}
