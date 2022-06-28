package kr.youhost.utils.crypt;


public class Sha256 {
	/**
	 * sha256 encrypt
	 * convert plainText to sha256 and change to hex string
	 * @param planText
	 * @return
	 */
	public static String encrypt(String plainText) {
		return HashEngine.hashBySha256(plainText);
//		String res = null;
//        try {
//            MessageDigest md = MessageDigest.getInstance("SHA-256");
//            md.update(planText.getBytes());
//            byte byteData[] = md.digest();
//
//            StringBuffer sb = new StringBuffer();
//            for (int i = 0; i < byteData.length; i++) {
//                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
//            }
//
//            StringBuffer hexString = new StringBuffer();
//            for (int i=0;i<byteData.length;i++) {
//                String hex=Integer.toHexString(0xff & byteData[i]);
//                if(hex.length()==1){
//                    hexString.append('0');
//                }
//                hexString.append(hex);
//            }
//
//            res = hexString.toString();
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException();
//        }
//        return res;
    }
	/**
	 * getChecksum
	 * 
	 * @param plainText
	 * @return
	 */
	public static String getChecksum(String plainText) {
		return encrypt(plainText);
	}
}
