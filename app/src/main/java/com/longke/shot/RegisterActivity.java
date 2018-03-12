package com.longke.shot;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.JsonResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.OkHttpClient;

import static android.text.TextUtils.isEmpty;

public class RegisterActivity extends Activity {

    @InjectView(R.id.addrss_name_tv)
    TextView mAddrssNameTv;
    @InjectView(R.id.title_bar)
    RelativeLayout mTitleBar;
    @InjectView(R.id.sn_text)
    TextView mSnText;
    @InjectView(R.id.pad_name)
    EditText mPadName;
    @InjectView(R.id.bt_get_check_code)
    Button mBtGetCheckCode;
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(10000L, TimeUnit.MILLISECONDS)
            .readTimeout(10000L, TimeUnit.MILLISECONDS)
            //其他配置
            .build();
    private MyOkHttp mMyOkhttp;
    private String deviceId;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_view);
        ButterKnife.inject(this);
        mMyOkhttp = new MyOkHttp(okHttpClient);

       /* String sn = (String) SharedPreferencesUtil.get(RegisterActivity.this, "SN", "");
        if (!isEmpty(sn)) {
            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            finish();
            return;
        }*/
        deviceId = UUIDS.getUUID();
        mSnText.setText("pad序列号:" + deviceId);


    }

    public static String getDeviceId(Context context) {
        StringBuilder deviceId = new StringBuilder();
        // 渠道标志
        deviceId.append("a");
        try {
            //wifi mac地址
            WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wifi.getConnectionInfo();
            String wifiMac = info.getMacAddress();
            if (!isEmpty(wifiMac)) {
                deviceId.append("wifi");
                deviceId.append(wifiMac);

                return deviceId.toString();
            }
            //IMEI（imei）
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String imei = tm.getDeviceId();
            if (!isEmpty(imei)) {
                deviceId.append("imei");
                deviceId.append(imei);
                return deviceId.toString();
            }
            //序列号（sn）
            String sn = tm.getSimSerialNumber();
            if (!isEmpty(sn)) {
                deviceId.append("sn");
                deviceId.append(sn);
                return deviceId.toString();
            }
            //如果上面都没有， 则生成一个id：随机码
            String uuid = getUUID(context);
            if (!isEmpty(uuid)) {
                deviceId.append("id");
                deviceId.append(uuid);
                return deviceId.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            deviceId.append("id").append(getUUID(context));
        }
        return deviceId.toString();
    }

    /**
     * 得到全局唯一UUID
     */
    public static String getUUID(Context context) {

        String uuid = UUID.randomUUID().toString();

        return uuid;
    }

    /**
     * 获取imei
     *
     * @return
     */
    private String getImei() {
        TelephonyManager TelephonyMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        String szImei = TelephonyMgr.getDeviceId();
        return szImei;
    }

    @OnClick(R.id.bt_get_check_code)
    public void onClick() {
        if (isEmpty(mPadName.getText().toString().trim())) {
            Toast.makeText(RegisterActivity.this, "请输入pad的名字", Toast.LENGTH_SHORT);
            return;
        }
        dialog = new ProgressDialog(RegisterActivity.this);
        dialog.setMessage("注册中,请稍候...");

        dialog.show();
        register();
    }

    /**
     * 获取数据
     */
    private void register() {
        mMyOkhttp.post().url(Urls.BASE_URL+Urls.RegistPad)
                .addParam("SN", deviceId)
                .addParam("Name", mPadName.getText().toString())
                .tag(this)
                .enqueue(new JsonResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, JSONObject response) {
                        try {
                            Boolean success = response.getBoolean("Success");
                            dialog.dismiss();
                            if (success) {
                                SharedPreferencesUtil.put(RegisterActivity.this, "SN", deviceId);
                                SharedPreferencesUtil.put(RegisterActivity.this, "Name", mPadName.getText().toString());
                                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                                finish();
                            } else {
                                Toast.makeText(RegisterActivity.this, response.getString("Message"), Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onSuccess(int statusCode, JSONArray response) {
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        dialog.dismiss();
                        Toast.makeText(RegisterActivity.this, error_msg, Toast.LENGTH_SHORT).show();
                        // ToastUtil.showShort(BaseApplication.context,error_msg);
                    }
                });
    }


    @OnClick(R.id.peizhi)
    public void onViewClicked() {
        startActivity(new Intent(RegisterActivity.this,ConfigureActivity.class));
    }
}
