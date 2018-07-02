package org.shop.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达工具
 * @author VIC
 *
 */
public class RegxUtil {
	
	public static final String regex = "[a-zA-Z0-9_-]+@\\w+\\.[a-z]+(\\.[a-z]+)?";
	
	public static boolean email(String string){
       Pattern p = Pattern.compile(regex); 
       Matcher m = p.matcher(string); 
       return m.find();
	}

}
