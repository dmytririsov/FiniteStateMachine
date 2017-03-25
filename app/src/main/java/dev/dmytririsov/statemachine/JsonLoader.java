package dev.dmytririsov.statemachine;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Dmytri on 25.03.2017.
 */

public class JsonLoader {

    private static JSONObject sJsonObjectStates;

    public static void initJsonStates(Context context, String jsonAsset) {
        try {
            sJsonObjectStates = new JSONObject(loadJSONFromAsset(jsonAsset, context));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static String loadJSONFromAsset(String jsonName, Context context) {
        String json;
        try {
            InputStream inputStream = context.getAssets().open(jsonName);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static JSONObject getStateDependencyByAction(String value) {
        try {
            return sJsonObjectStates.getJSONObject(value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
