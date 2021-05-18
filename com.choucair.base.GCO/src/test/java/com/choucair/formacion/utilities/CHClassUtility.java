package com.choucair.formacion.utilities;

public class CHClassUtility {

	public static String getMethodName() {
		return Thread.currentThread().getStackTrace()[2].getMethodName() + ": ";
	}
	

}
