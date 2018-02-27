package cn.com.sharinglife.util;

import org.apache.commons.codec.binary.Base64;

public final class Digest {

	private Digest() {
		
	}
	
	/**
	 * BASE64加密
	 * 
	 * @param plainText
	 * @return
	 * @throws Exception
	 */
	public static String encryptBASE64(final String plainText) {
		byte[] plainByte = plainText.getBytes();
		final Base64 base64 = new Base64();
		plainByte = base64.encode(plainByte);
		return new String(plainByte);
	}

	/**
	 * BASE64解密
	 * 
	 * @param encodeStr
	 * @return
	 */
	public static String decryptBASE64(final String encodeStr) {
		byte[] encodeByte = encodeStr.getBytes();
		final Base64 base64 = new Base64();
		encodeByte = base64.decode(encodeByte);
		return new String(encodeByte);
	}
}
