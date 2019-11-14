package com.deloitte.asset.util;

public class CodgenUtil {
	
	public static String  packageToPath(String packageName) {
		return packageName.replace(".", "/");
	}
	
	
	public static String  getFileNamewithoutExtension(String fileName) {
		 int index = fileName.lastIndexOf('.');
		    if (index == -1) {
		        return fileName;
		    } else {
		        return fileName.substring(0, index);
		    }
	}

	
	 public static String toCamelCase(final String init) {
		    if (init == null)
		        return null;

		    final StringBuilder ret = new StringBuilder(init.length());

		    for (final String word : init.split(" ")) {
		        if (!word.isEmpty()) {
		            ret.append(Character.toUpperCase(word.charAt(0)));
		            ret.append(word.substring(1).toLowerCase());
		        }
		        if (!(ret.length() == init.length()))
		            ret.append(" ");
		    }

		    return ret.toString();
		}
}
