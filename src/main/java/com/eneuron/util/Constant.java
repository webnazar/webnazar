package com.eneuron.util;

import java.util.HashMap;

public class Constant {

	public static String TWITTER_OAUTH2_CLIENT_ID = "UDV2cmRoUnd0bF9CcjVwbTFfOWk6MTpjaQ";
	public static String TWITTER_OAUTH2_CLIENT_SECRET = "5xy-S7xX5RM5XosW_oecaOL79B9Z1xmk4JubkAIDN9xHTczay_";
	
	public static String TWITTER_BEARER_TOKEN = "AAAAAAAAAAAAAAAAAAAAAJrWaQEAAAAAF3eZNPw77C0FRJ4XQh46EYGIb%2BI%3DAAaCxZikYo15AgXZj8SuSiQGAc3WSo6d71ieZL3oexzJxN187A";
	
	public static String DOMAIN = "http://www.webnazar.com/";
	
	public static HashMap<String, String> languageMap;
	
	static  {
		
		languageMap = new HashMap<>();
		languageMap.put("en", "English (default)");
		languageMap.put("ar", "Arabic");
		languageMap.put("bn", "Bengali");
		languageMap.put("cs", "Czech");
		languageMap.put("da", "Danish");
		languageMap.put("de", "German");
		languageMap.put("el", "Greek");
		languageMap.put("es", "Spanish");
		languageMap.put("fa", "Persian");
		languageMap.put("fi", "Finnish");
		languageMap.put("fil", "Filipino");
		languageMap.put("fr", "French");
		languageMap.put("he", "Hebrew");
		languageMap.put("hi", "Hindi");
		languageMap.put("hu", "Hungarian");
		languageMap.put("id", "Indonesian");
		languageMap.put("it", "Italian");
		languageMap.put("ko", "Korean");
		languageMap.put("msa", "Malay");
		languageMap.put("nl", "Dutch");
		languageMap.put("no", "Norwegian");
		languageMap.put("pl", "Polish");
		languageMap.put("pt", "Portuguese");
		languageMap.put("ro", "Romanian");
		languageMap.put("ru", "Russian");
		languageMap.put("sv", "Swedish");
		languageMap.put("th", "Thai");
		languageMap.put("tr", "Turkish");
		languageMap.put("uk", "Ukrainian");
		languageMap.put("ur", "Urdu");
		languageMap.put("vi", "Vietnamese");
		languageMap.put("zh-cn", "Chinese (Simplified)");
		languageMap.put("zh-tw", "Chinese (Traditional)");
	}
	
	
}
