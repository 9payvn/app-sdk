package vn.npay.demo_sdk;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.GeneratedPluginRegistrant;
import vn.ewallet.sdk.NPayLibrary;

public class NPayActivity extends FlutterActivity {

    @Nullable
    @Override
    public FlutterEngine provideFlutterEngine(@NonNull Context context) {
        return FlutterEngineCache
                .getInstance()
                .get("9pay-Module");
    }

    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        MethodChannel channel = new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), "9pay/channel");
        String data = getIntent().getStringExtra("data");
        channel.setMethodCallHandler(
                (call, result) -> {
                    switch (call.method) {
                        case "open_9pay":
                            NPayLibrary.getInstance().open9payApp();
                            result.success("");
                            break;
                        case "finish":
                            finish();
                            result.success("");
                            break;
                        default:
                            result.notImplemented();
                    }
                });
        GeneratedPluginRegistrant.registerWith(flutterEngine);
        super.configureFlutterEngine(flutterEngine);
        channel.invokeMethod("data_npay", data);

    }
}
