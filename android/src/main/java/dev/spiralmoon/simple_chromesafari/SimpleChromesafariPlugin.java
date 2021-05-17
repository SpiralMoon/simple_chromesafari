package dev.spiralmoon.simple_chromesafari;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;

import androidx.browser.customtabs.CustomTabsIntent;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/**
 * SimpleChromesafariPlugin
 */
public class SimpleChromesafariPlugin implements MethodCallHandler {
    /**
     * Plugin registration.
     */

    private final MethodChannel methodChannel;
    public static Activity activity;
    public static PendingIntent pendingIntent;

    private SimpleChromesafariPlugin(Activity activity, MethodChannel methodChannel) {
        System.out.println(activity.getLocalClassName());
        SimpleChromesafariPlugin.activity = activity;
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
            Uri uri = Uri.parse(call.argument("url").toString());
            Intent intent = new Intent(activity, CustomTabsReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(activity, 0, intent, 0);

            SimpleChromesafariPlugin.pendingIntent = pendingIntent;

            CustomTabsIntent.Builder customTabsBuilder = new CustomTabsIntent.Builder();
            CustomTabsIntent customTabsIntent = customTabsBuilder.build();
            customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            customTabsIntent.launchUrl(activity, uri);

            result.success(null);
        }
        // close chrome custom tabs.
        else if (call.method.equals("closeBrowser")) {
            if (pendingIntent != null) {
                try {
                    pendingIntent.send();
                    pendingIntent = null;
                    result.success(null);
                } catch (Exception e) {
                    result.error("Close failed.", e.getMessage(), e.getStackTrace());
                }
            } else {
                result.success(null);
            }
        } else {
            result.notImplemented();
        }
    }
}
