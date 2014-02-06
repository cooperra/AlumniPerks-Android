package edu.rosehulman.roseperks;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class CredentialManager {
	static final String PREF_SESSION_KEY = "session_key";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setSessionKey(Context ctx, String key) {
        Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_SESSION_KEY, key);
        editor.commit();
    }

    public static String getSessionKey(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_SESSION_KEY, "");
    }
    
    public static boolean removeSessionKey(Context ctx) {
    	Editor editor = getSharedPreferences(ctx).edit();
    	editor.remove(PREF_SESSION_KEY);
    	return editor.commit();
    }
}
