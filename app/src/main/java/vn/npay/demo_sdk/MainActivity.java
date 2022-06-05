package vn.npay.demo_sdk;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import vn.ewallet.sdk.NPayLibrary;
import vn.ewallet.sdk.model.SdkConfig;

public class MainActivity extends AppCompatActivity {
    private EditText edtMcCode, edtMcUid, edtOrderId;
    private Button btnOpenWallet, btnPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initSDK();

        initView();

        demo();


    }

    private void initSDK() {
        NPayLibrary.getInstance().init(this, new SdkConfig.Builder()
                .merchantCode("demo")
                .uid("1")
                .build()
        );
    }

    private void demo() {
        btnOpenWallet.setOnClickListener(view -> {
            String merchantCode = edtMcCode.getText().toString().isEmpty() ? "demo" : edtMcCode.getText().toString();
            String uid = edtMcUid.getText().toString().isEmpty() ? "1" : edtMcUid.getText().toString();

            NPayLibrary.getInstance().init(this, new SdkConfig.Builder()
                    .merchantCode(merchantCode)
                    .uid(uid)
                    .env(NPayLibrary.STAGING)
                    .build()
            );
            Intent intent = new Intent(MainActivity.this, NPayActivity.class);
            intent.putExtra("data", NPayLibrary.getInstance().walletData());
            startActivity(intent);
        });

        btnPayment.setOnClickListener(view -> {
            if (edtOrderId.getText().toString().isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập Order id", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(MainActivity.this, NPayActivity.class);
            intent.putExtra("data", NPayLibrary.getInstance().paymentData(edtOrderId.getText().toString()));
            startActivity(intent);
        });

    }

    private void initView() {
        edtMcCode = findViewById(R.id.edt_mc_code);
        edtMcUid = findViewById(R.id.ed_mc_uid);
        edtOrderId = findViewById(R.id.edt_order);

        btnOpenWallet = findViewById(R.id.btn_open_sdk);
        btnPayment = findViewById(R.id.btn_payment);
    }


}
