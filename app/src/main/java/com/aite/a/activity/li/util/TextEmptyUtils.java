package com.aite.a.activity.li.util;

public class TextEmptyUtils {

    public static String getText(String text) {
        return text == null ? "" : text;
    }

    public static String getNumber(String number) {
        return number == null || number.isEmpty() || number.equals("null") ? "0" : number;
    }

    public static String getSymbol(String symbol) {
        if (symbol != null && symbol.contains("/"))
            symbol = symbol.substring(symbol.lastIndexOf("/") + 1);
        return symbol == null ? "￥" : symbol;
    }

    public static String getChaniese(String unicodeStr) {
        if (unicodeStr == null) {
            return null;
        }
        StringBuffer retBuf = new StringBuffer();
        int maxLoop = unicodeStr.length();
        for (int i = 0; i < maxLoop; i++) {
            if (unicodeStr.charAt(i) == '\\') {
                if ((i < maxLoop - 5) && ((unicodeStr.charAt(i + 1) == 'u') || (unicodeStr.charAt(i + 1) == 'U')))
                    try {
                        retBuf.append((char) Integer.parseInt(unicodeStr.substring(i + 2, i + 6), 16));
                        i += 5;
                    } catch (NumberFormatException localNumberFormatException) {
                        retBuf.append(unicodeStr.charAt(i));
                    }
                else
                    retBuf.append(unicodeStr.charAt(i));
            } else {
                retBuf.append(unicodeStr.charAt(i));
            }
        }
        return retBuf.toString();
    }

    public static String getImageUrl(String url, String pre){
        if (url.startsWith("http")){
            return url;
        }else {
            return pre+url;
        }
    }

}
