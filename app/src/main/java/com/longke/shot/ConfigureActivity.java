package com.longke.shot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.tsy.sdk.myokhttp.MyOkHttp;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static com.longke.shot.SharedPreferencesUtil.IS_RADIO;
import static com.longke.shot.SharedPreferencesUtil.IS_VISITOR;
import static com.longke.shot.SharedPreferencesUtil.SHOW_OPTION;

public class ConfigureActivity extends AppCompatActivity {

    @InjectView(R.id.addrss_name_tv)
    TextView addrssNameTv;
    @InjectView(R.id.url_name)
    EditText urlName;
    @InjectView(R.id.isRed)
    CheckBox isRed;
    @InjectView(R.id.bt_queding)
    Button btQueding;
    @InjectView(R.id.bt_quxiao)
    Button btQuxiao;
    @InjectView(R.id.isRadio)
    CheckBox isRadio;
    @InjectView(R.id.isVisitor)
    CheckBox mIsVisitor;
    @InjectView(R.id.show_group)
    RadioGroup showGroup;
    private boolean isFromMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure);
        ButterKnife.inject(this);
        isFromMain = getIntent().getBooleanExtra("isFromMain", false);

        String isViSitor = (String) SharedPreferencesUtil.get(ConfigureActivity.this, IS_VISITOR, "2");
        String baseUrl = (String)  SharedPreferencesUtil.get(ConfigureActivity.this, SharedPreferencesUtil.BASE_URL, "");
        urlName.setText(baseUrl);
        boolean IS_RADIO = (boolean) SharedPreferencesUtil.get(ConfigureActivity.this, SharedPreferencesUtil.IS_RADIO, true);
        boolean isShowRedOpen = (boolean) SharedPreferencesUtil.get(ConfigureActivity.this, SharedPreferencesUtil.IS_RED, true);
        boolean isShowOrder = (boolean) SharedPreferencesUtil.get(ConfigureActivity.this, SharedPreferencesUtil.SHOW_OPTION, true);
        isRadio.setChecked(IS_RADIO);
        isRed.setChecked(isShowRedOpen);
        if (isViSitor.equals("1")) {
            mIsVisitor.setChecked(true);
        }else{
            mIsVisitor.setChecked(false);
        }
        isRed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                for (int i = 0; i < showGroup.getChildCount(); i++)
                {
                    showGroup.getChildAt(i).setEnabled(isChecked);
                }
            }
        });
        if (isShowOrder) {
            showGroup.check(R.id.show_order);
        }else{
            showGroup.check(R.id.show_score);
        }
        for (int i = 0; i < showGroup.getChildCount(); i++)
        {
            showGroup.getChildAt(i).setEnabled(isShowRedOpen);
        }
        if (!isFromMain) {
            btQuxiao.setVisibility(View.VISIBLE);
        }
        else
        {
            btQuxiao.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.bt_queding)
    public void onViewClicked() {
        SharedPreferencesUtil.put(ConfigureActivity.this, SharedPreferencesUtil.BASE_URL, urlName.getText().toString());
        SharedPreferencesUtil.put(ConfigureActivity.this, SharedPreferencesUtil.IS_RED, isRed.isChecked());
        SharedPreferencesUtil.put(ConfigureActivity.this, IS_RADIO, isRadio.isChecked());
        SharedPreferencesUtil.put(ConfigureActivity.this, SharedPreferencesUtil.SHOW_OPTION, showGroup.getCheckedRadioButtonId() == R.id.show_order);

        if(mIsVisitor.isChecked()){
            SharedPreferencesUtil.put(ConfigureActivity.this, SharedPreferencesUtil.IS_VISITOR, "1");
        }else{
            SharedPreferencesUtil.put(ConfigureActivity.this, SharedPreferencesUtil.IS_VISITOR,"2");
        }

        if (isFromMain) {
            startActivity(new Intent(ConfigureActivity.this, MainActivity.class));
        }else{
            setResult(RESULT_OK);
        }

        finish();

    }
    @OnClick(R.id.bt_quxiao)
    public void onClickCancel() {
        setResult(RESULT_OK);
        finish();
    }
}
