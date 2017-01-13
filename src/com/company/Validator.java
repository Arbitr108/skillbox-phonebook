package com.company;

import java.util.regex.Pattern;

public class Validator {

    private static Pattern namePattern =
            Pattern.compile("^\\s*([А-ЯЁA-Z][а-яёa-z]+(?:-[А-ЯЁA-Z][а-яёa-z]+)?)(\\s+[А-ЯЁA-Z][а-яёa-z\\.]+)?(\\s?[А-ЯЁA-Z][а-яёa-z\\.]+)?\\s*$");
    private static Pattern phonePattern =
            Pattern.compile("^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{2})[-. ]*(\\d{2})\\s*$");

    public static boolean isName(String data) {
        return namePattern.matcher(data).matches();
    }

    public static boolean isNumber(String data) {
        return phonePattern.matcher(data).matches();
    }
}
