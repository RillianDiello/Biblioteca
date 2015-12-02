package br.ufms.factory;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionFactory {

	public static Properties getProp() throws IOException {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream(
				"./src/conf/app.properties");
		props.load(file);
		return props;
	}

	public Connection getConnection() {
		try {
			Properties prop = getProp();
			return DriverManager.getConnection(prop.getProperty("url"),
					prop.getProperty("user"),
					prop.getProperty("password"));
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}
