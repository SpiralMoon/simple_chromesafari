package dev.spiralmoon.simple_chromesafari;

import android.app.Activity;
import android.content.Intent;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/** SimpleChromesafariPlugin */
public class SimpleChromesafariPlugin implements MethodCallHandler {
  /** Plugin registration. */

  private final MethodChannel methodChannel;
  public static Activity activity;
  private Intent intent;

  private SimpleChromesafariPlugin (Activity activity, MethodChannel methodChannel) {
    System.out.println(activity.getLocalClassName());
    this.activity = activity;
    this.methodChannel = methodChannel;
  }

  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "simple_chromesafari");
    channel.setMethodCallHandler(new SimpleChromesafariPlugin(registrar.activity(), channel));
  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {

    // open chrome custom tabs.
    if (call.method.equals("openBrowser")) {

      this.intent = new Intent(activity, BrowserActivity.class);
      this.intent.putExtra("url", call.argument("url").toString());
      //this.intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

      activity.startActivity(this.intent);
    }
    // close chrome custom tabs.
    else if (call.method.equals("closeBrowser")) {
      if (BrowserActivity.activity != null) {
        BrowserActivity.activity.close();
      }
    } else {
      result.notImplemented();
    }
  }
}
