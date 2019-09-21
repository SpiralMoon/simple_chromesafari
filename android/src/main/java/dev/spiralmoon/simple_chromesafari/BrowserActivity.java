package dev.spiralmoon.simple_chromesafari;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import io.flutter.app.FlutterActivity;
import androidx.browser.customtabs.CustomTabsIntent;

public class BrowserActivity extends FlutterActivity {

    public static BrowserActivity activity;

    private byte count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        activity = this;

        // get uri
        Uri uri = Uri.parse(getIntent().getStringExtra("url"));

        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        // ... setup

        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, uri);
    }

    public void close() {
        Intent myIntent = new Intent(this, SimpleChromesafariPlugin.activity.getClass());
        myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(myIntent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();

        count++;
        if (count == 2) {
            finish();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}
