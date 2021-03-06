package beans;
public class StringUtil {
	public static boolean isNull(String str) {
		return str==null;
	}
	public static boolean isEmpty(String str) {
		return str.equals("");
	}
}