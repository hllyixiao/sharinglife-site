package cn.com.sharinglife.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @author hjp
 * 
 * @since 1.0.0
 *
 */
public final class MD5Utils {

	private final static Logger LOGGER = LoggerFactory.getLogger(MD5Utils.class);
	
	private MD5Utils() {
		super();
	}

	public static String md5(final String parameter) {
		String returnVal = "";
		final char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			final byte[] strTemp = parameter.getBytes();
			final MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			final byte[] result = mdTemp.digest();
			final int resultLength = result.length;
			char[] str = new char[resultLength * 2];
			int key = 0;
			for (int i = 0; i < resultLength; i++) {
				final byte byte0 = result[i];
				str[key++] = hexDigits[byte0 >>> 4 & 0xf];
				str[key++] = hexDigits[byte0 & 0xf];
			}
			returnVal = new String(str);
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error(e.getLocalizedMessage());
		}
		return returnVal;
	}
	
}
