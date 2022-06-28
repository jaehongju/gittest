package kr.youhost.utils.crypt;

import java.security.MessageDigest;

public class HashEngine {
	public final static String HASH_BY_MD5 = "MD5";
	public final static String HASH_BY_SHA256 = "SHA-256";
	
	public static String hashBySha256(String plainText) {
		return encrypt(plainText, HASH_BY_SHA256);
	}
	public static String hashByMD5(String plainText) {
		return encrypt(plainText, HASH_BY_MD5);
	}
	public static String encrypt(String plainText, String hashBy) {
		if( plainText==null ) {
			return null;
		}
		if( hashBy==null ) {
			hashBy = HASH_BY_SHA256;
		}
		String res = null;
        try {
            MessageDigest md = MessageDigest.getInstance(hashBy);
            md.update(plainText.getBytes());
            byte byteData[] = md.digest();

            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }

            StringBuffer hexString = new StringBuffer();
            for (int i=0;i<byteData.length;i++) {
                String hex=Integer.toHexString(0xff & byteData[i]);
                if(hex.length()==1){
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            res = hexString.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return res;
	}
}
