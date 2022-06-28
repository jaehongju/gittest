package kr.youhost.utils.crypt;


public class PasswordUtil {

	public static String getChecksum(String plainPw) {
		return Sha256.encrypt(plainPw);
	}
	
	public static boolean isPasswordOk(String plainPw, String targetChecksum) {
		boolean res = false;
		
		if (plainPw!=null && targetChecksum!=null) {
			String checksum = getChecksum(plainPw);
			if ( checksum!=null 	) {
				if (checksum.equals(targetChecksum)) {
					res=true;
				}
			}
		}

		return res;
	}
}
