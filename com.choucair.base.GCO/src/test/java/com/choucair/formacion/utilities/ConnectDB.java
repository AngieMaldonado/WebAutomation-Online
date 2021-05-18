package com.choucair.formacion.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ConnectDB {

	public static ConnectDB instance = null;
	public static final Logger LOGGER = LoggerFactory.getLogger(ConnectDB.class);
	Connection connection = null;

	static {
		try {
			// Loading postgresql driver
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
		LOGGER.info(CHClassUtility.getMethodName() + e.getStackTrace());
		}
	}

	public static ConnectDB getInstance() {
		if (null == instance) {
			instance = new ConnectDB();
		}

		return instance;
	}

	public Connection getConnection() {
		try {
			// Database Connection Creation
			if (connection == null || connection.isClosed()) {
				connection = DriverManager.getConnection(SessionVariables.DB_CONECTION, SessionVariables.DB_USER,
						SessionVariables.DB_PASSWORD);
			}
		} catch (SQLException e) {
			LOGGER.info(CHClassUtility.getMethodName() + e.getStackTrace());
		}

		return connection;
	}

	public void closeConnection() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			LOGGER.info(CHClassUtility.getMethodName() + e.getStackTrace());
		}
	}

	public int update(String strQuery) {
		int intResult = 0;
		try (Statement st = ConnectDB.getInstance().getConnection().createStatement();) {
			intResult = st.executeUpdate(strQuery);
		} catch (SQLException e) {

		}
		return intResult;
	}
	
	public JSONArray select(String strQuery) {
		JSONArray jsonArray = new JSONArray();
		try (Statement st = ConnectDB.getInstance().getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY); ResultSet rs = st.executeQuery(strQuery);) {

			rs.last();
			int total_rows = rs.getRow();
			rs.beforeFirst();
			int total_columns = rs.getMetaData().getColumnCount();

			for (int i = 0; i < total_rows; i++) {
				if (rs.next()) {
					JSONObject obj = new JSONObject();
					for (int j = 0; j < total_columns; j++) {
						try {
							obj.put(rs.getMetaData().getColumnLabel(j + 1).toLowerCase(), rs.getObject(j + 1));
						} catch (JSONException e) {
							LOGGER.info(CHClassUtility.getMethodName() + e.getStackTrace());
						}
					}
					jsonArray.put(obj);
				}
			}
		} catch (SQLException e) {
			LOGGER.info(CHClassUtility.getMethodName() + e.getStackTrace());
		}
		return jsonArray;
	}
	
	public JSONArray selectControlNull(String strQuery) {
		JSONArray jsonArray = new JSONArray();
		try (Statement st = ConnectDB.getInstance().getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY); ResultSet rs = st.executeQuery(strQuery);) {

			rs.last();
			int total_rows = rs.getRow();
			rs.beforeFirst();
			int total_columns = rs.getMetaData().getColumnCount();

			for (int i = 0; i < total_rows; i++) {
				if (rs.next()) {
					JSONObject obj = new JSONObject();
					for (int j = 0; j < total_columns; j++) {
						try {
							obj.put(rs.getMetaData().getColumnLabel(j + 1).toLowerCase(), rs.getObject(j + 1)==null?JSONObject.NULL:rs.getObject(j + 1));
						} catch (JSONException e) {
							LOGGER.info(CHClassUtility.getMethodName() + e.getStackTrace());
						}
					}
					jsonArray.put(obj);
				}
			}
		} catch (SQLException e) {
			LOGGER.info(CHClassUtility.getMethodName() + e.getStackTrace());
		}
		return jsonArray;
	}

}
