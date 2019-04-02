package db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBCon {
	private static final String URL;
	private static final String USER;
	private static final String PWD;
	private static final String CLASS_NAME;
	private static Connection con;
	static {
		Properties prop = new Properties();
		InputStream is = DBCon.class.getResourceAsStream("/config/config.properties");
		try {
			prop.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		URL=prop.getProperty("url");
		USER=prop.getProperty("user");
		PWD=prop.getProperty("pwd");
		CLASS_NAME=prop.getProperty("className");
	}
	
	public static Connection getCon() {
		if(con==null) {
			try {
				Class.forName(CLASS_NAME);
				con = DriverManager.getConnection(URL, USER,PWD);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return con;
	}
	
	public static void close() {
		if(con!=null) {
			try {
				if(!con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		con = null;
	}
}
