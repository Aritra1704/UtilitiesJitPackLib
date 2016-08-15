package com.arpaul.utilitieslib;

import org.json.JSONObject;

/**
 * Created by Aritra on 09-08-2016.
 */
public class JSONUtils {
    public static boolean hasJSONtag(JSONObject jsonObject, String tag){
        boolean hasTag = false;

        if(jsonObject != null && jsonObject.has(tag) && !jsonObject.isNull(tag))
            hasTag = true;

        return hasTag;
    }
}
