
package com.example;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.example.constant.Result;
import com.example.response.GeocodeResBean;
import com.example.util.AccessApiUtil;
import com.open.jsonhttp.AsyncTask;
import com.open.jsonhttp.OnRequestHttpListener;
import com.open.jsonhttp.ResponseBean;

public class MainActivity extends Activity implements OnClickListener,
        OnRequestHttpListener {

    private TextView tvHelloWorld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvHelloWorld = (TextView) findViewById(R.id.tvHelloWorld);
        findViewById(R.id.btnObtain).setOnClickListener(this);
    }

    AsyncTask mTaskGenCode;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnObtain:
                mTaskGenCode = AccessApiUtil.gencode("HK", this);
                break;

            default:
                break;
        }
    }

    @Override
    public void onSuccess(int responseKey, ResponseBean responseBean) {
        switch (responseKey) {
            case AccessApiUtil.INDEX_GEOCODE:
                if (responseBean.getStatus().equals(Result.OK)) {
                    GeocodeResBean resBean = (GeocodeResBean) responseBean;
                    tvHelloWorld.setText(resBean.toString());
                }
                break;

            default:
                break;
        }
    }

    @Override
    public void onFail(int responseKey, int code) {
        switch (responseKey) {
            case AccessApiUtil.INDEX_GEOCODE:

                break;

            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        if (mTaskGenCode != null) {
            mTaskGenCode.interrupt();
        }
        super.onDestroy();
    }
}
