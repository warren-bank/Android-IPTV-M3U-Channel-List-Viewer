package com.github.warren_bank.m3u_viewer.settings;

import com.github.warren_bank.m3u_viewer.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SettingsUtils {

  public static SharedPreferences getPrefs(Context context) {
    return PreferenceManager.getDefaultSharedPreferences(context);
  }

  // --------------------

  public static String getDefaultM3uUrlPreference(Context context) {
    return getDefaultM3uUrlPreference(context, getPrefs(context));
  }

  private static String getDefaultM3uUrlPreference(Context context, SharedPreferences prefs) {
    String pref_key    = context.getString(R.string.pref_default_m3u_url_key);
    String val_default = context.getString(R.string.pref_default_m3u_url_default);

    return prefs.getString(pref_key, val_default);
  }

  // --------------------

  public static String getChannelUrlTemplatePreference(Context context) {
    return getChannelUrlTemplatePreference(context, getPrefs(context));
  }

  private static String getChannelUrlTemplatePreference(Context context, SharedPreferences prefs) {
    String pref_key    = context.getString(R.string.pref_channel_url_template_key);
    String val_default = context.getString(R.string.pref_channel_url_template_default);

    return prefs.getString(pref_key, val_default);
  }

}
