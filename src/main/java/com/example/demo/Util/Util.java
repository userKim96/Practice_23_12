package com.example.demo.Util;

import java.security.MessageDigest;
import java.util.UUID;

public class Util {
	public static boolean empty(String str) {
		
		if (str == null) {
			return true;
		}
		
		return str.trim().length() == 0;
	}
	
	public static String f(String format, Object... args) {
		return String.format(format, args);
	}
	
	public static String jsHistoryBack(String msg) {
		
		if (msg == null) {
			msg = "";
		}
		
		return Util.f("""
					<script>
					
						const msg = '%s'.trim();
						
						if (msg.length > 0) {
							alert(msg);
						}
						
						history.back();
					</script>
				""", msg);
	}

	public static String jsReplace(String msg, String uri) {
		
		if (msg == null) {
			msg = "";
		}
		
		if (uri == null) {
			uri = "";
		}
		
		return Util.f("""
					<script>
					
						const msg = '%s'.trim();
						
						if (msg.length > 0) {
							alert(msg);
						}
						
						location.replace('%s');
					</script>
				""", msg, uri);
	}
	
	public static String sha256(String base) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(base.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();

			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}

			return hexString.toString();

		} catch (Exception ex) {
			return "";
		}
	}
	
	public static String createTempPassword() {
	    return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
	}
}
