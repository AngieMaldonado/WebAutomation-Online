package com.choucair.formacion.utilities;

public enum SessionVariables {
	
	USER("usuario.name"),
	PASSWORD("usuario.password");
	
	public static final String DB_CONECTION = "jdbc:postgresql://10.66.166.5:5432/siconline_pru";	
	public static final String DB_USER = "postgres";
	public static final String DB_PASSWORD = "";
	
	private String value;

	 SessionVariables(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}


}
