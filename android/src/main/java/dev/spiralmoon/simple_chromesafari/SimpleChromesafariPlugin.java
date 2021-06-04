package dev.spiralmoon.simple_chromesafari;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/**
 * SimpleChromesafariPlugin
 */
public class SimpleChromesafariPlugin implements FlutterPlugin, MethodCallHandler {
    /**
     * Plugin registration.
     */

    private MethodChannel methodChannel;
    private PendingIntent pendingIntent;
    private Context context;

    @Override
    public void onMethodCall(MethodCall call, Result result) {

        // open chrome custom tabs.
        if (call.method.equals("openBrowser")) {
            Uri uri = Uri.parse(call.argument("url").toString());
            Intent intent = new Intent(context, CustomTabsReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

            this.pendingIntent = pendingIntent;

            CustomTabsIntent.Builder customTabsBuilder = new CustomTabsIntent.Builder();
            CustomTabsIntent customTabsIntent = customTabsBuilder.build();
            customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            customTabsIntent.launchUrl(context, uri);

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

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        methodChannel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "simple_chromesafari");
        methodChannel.setMethodCallHandler(this);
        this.context = flutterPluginBinding.getApplicationContext();
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        methodChannel.setMethodCallHandler(null);
    }
}
