package dev.dmytririsov.statemachine;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Dmytri on 25.03.2017.
 */

public class JsonLoader {

    private static final String STATES_JSON = "states.json";

    private static JSONObject mJsonObjectStates;


    public static void initJsonStates(Context context) {
        try {
            mJsonObjectStates = new JSONObject(loadJSONFromAsset(STATES_JSON, context));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static String loadJSONFromAsset(String jsonName, Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(jsonName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static JSONObject getStateDependencyByAction(String value) {
        try {
            return mJsonObjectStates.getJSONObject(value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
