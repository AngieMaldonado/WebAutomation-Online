package com.choucair.formacion.utilities;

import java.nio.charset.StandardCharsets;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class EncryptCredentials {

	private static final String KEY = "Bar12345Bar12345";
	private static final String INIT_VECTOR = "RandomInitVector";
	private static final String INSTANCE_CIPHER = "AES/CBC/PKCS5PADDING";
	private static final String KEYSPEC = "AES";
	private static final Logger LoggerParm = LogManager.getLogger("LoggerParent");

	private EncryptCredentials() {
	}

	public static String encrypt(String value) {
		byte[] encrypted = null;
		try {
			IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes(StandardCharsets.UTF_8));
			SecretKeySpec skeySpec = new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8), KEYSPEC);
			Cipher cipher = Cipher.getInstance(INSTANCE_CIPHER);
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			encrypted = cipher.doFinal(value.getBytes());
		} catch (Exception e) {
			LoggerParm.error(e);
		}
		return Base64.encodeBase64String(encrypted);
	}

	public static String decrypt(String encrypted) {
		byte[] original = null;
		try {
			IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes(StandardCharsets.UTF_8));
			SecretKeySpec skeySpec = new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8), KEYSPEC);
			Cipher cipher = Cipher.getInstance(INSTANCE_CIPHER);
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			original = cipher.doFinal(Base64.decodeBase64(encrypted));
		} catch (Exception e) {
			LoggerParm.error(e);
		}
		return new String(original);
	}
}
