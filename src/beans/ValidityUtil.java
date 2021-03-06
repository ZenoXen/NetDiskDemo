package beans;
public class ValidityUtil {
	public static boolean userEmpty(User user) {
		String uname=user.getUname();
		String upwd=user.getUpwd();
		boolean valid=false;
		if(StringUtil.isNull(uname)||StringUtil.isNull(upwd))
			valid=true;
		else if(StringUtil.isEmpty(uname)||StringUtil.isEmpty(upwd))
			valid=true;
		return valid;
	}
	public static boolean nameFormatFalse(String uname) {
		if(uname.length()<6||uname.length()>12)
			return true;
		return !uname.matches("[a-zA-Z][a-zA-Z0-9]{5,11}");
	}
	public static boolean pwdFormatFalse(String upwd) {
		if(upwd.length()<7||upwd.length()>14)
			return true;
		return !upwd.matches("[a-zA-Z0-9]{7,14}");
	}
}