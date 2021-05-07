package com.forum.dataindexer.datarepository.entitymappers.utils;

import com.yahoo.text.Text;

import org.json.JSONObject;

public class Utils {
    public static JSONObject vespaAssign(Object val) {
        return new JSONObject().put("assign", val);
    }

    public static String vespaStripInvalidCharacters(String string) {
        if (string == null) {
            return null;
        }
        return Text.stripInvalidCharacters(string);
    }

    private Utils() {}
}
