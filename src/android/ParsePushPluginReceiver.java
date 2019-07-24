package github.taivo.parsepushplugin;

import com.parse.ParsePushBroadcastReceiver;

import android.content.Context;
import android.content.Intent;

import android.util.Log;


public class ParsePushPluginReceiver extends ParsePushBroadcastReceiver {
  public static final String LOGTAG = "ParsePushPluginReceiver";

  @Override
  protected void onPushReceive(Context context, Intent intent) {
    if (ParsePushPlugin.isInForeground()) {
      //
      // relay the push notification data to the javascript
      ParsePushPlugin.jsCallback(getPushData(intent));
    } else {
      // only create entry for notification tray if plugin/application is
      // not running in foreground.

      super.onPushReceive(context, intent);
    }

  }

  @Override
  protected void onPushOpen(Context context, Intent intent) {

    Log.d(LOGTAG, "onPushOpen");

    super.onPushOpen(context, intent);

    // relay the push notification data to the javascript in case the
    // app is already running when this push is open.
    ParsePushPlugin.jsCallback(getPushData(intent), "OPEN");

  }

}
