package com.example.poszyf.mefragment.setup;

import android.app.Dialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.poszyf.R;
import com.example.poszyf.base.BaseActivity;
import com.example.poszyf.net.HttpRequest;
import com.example.poszyf.net.OkHttpException;
import com.example.poszyf.net.RequestParams;
import com.example.poszyf.net.ResponseCallback;
import com.example.poszyf.views.MyDialog1;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者: qgl
 * 创建日期：2022/3/31
 * 描述:签约界面
 */
public class MeSigningActivity extends BaseActivity implements View.OnClickListener {
    private Button signing_btn;
    private LinearLayout iv_back;
    //    0 未签约 1签约失败 2 审核中 3 签约成功
    private String Signing = "0";
    private String ret_msg = "";
    private TextView prompt_tv;
    @Override
    protected int getLayoutId() {
        // 设置状态栏颜色
        statusBarConfig(R.color.new_theme_color,false).init();
        return R.layout.mesigning_layout;
    }

    @Override
    protected void initView() {
        signing_btn = findViewById(R.id.signing_btn);
        iv_back = findViewById(R.id.iv_back);
//        prompt_tv = findViewById(R.id.prompt_tv);
//        Signing = getIntent().getStringExtra("Signing");
//        ret_msg = getIntent().getStringExtra("ret_msg");
//        if (Signing.equals("0")){
//            signing_btn.setVisibility(View.VISIBLE);
//            prompt_tv.setVisibility(View.INVISIBLE);
//        }else if (Signing.equals("1")){
//            signing_btn.setVisibility(View.GONE);
//            prompt_tv.setVisibility(View.VISIBLE);
//            prompt_tv.setText(ret_msg);
//        }else if (Signing.equals("2")){
//            signing_btn.setVisibility(View.GONE);
//            prompt_tv.setVisibility(View.VISIBLE);
//            prompt_tv.setText(ret_msg);
//        }
    }

    @Override
    protected void initListener() {
        signing_btn.setOnClickListener(this);
        iv_back.setOnClickListener(this);

    }

    @Override
    protected void initData() {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.signing_btn:
                showDialog("您是否签约？");
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    private void posSigningData(){
        RequestParams params = new RequestParams();
        params.put("userId",getUserId());
        HttpRequest.getSigningTo(params, new ResponseCallback() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    JSONObject result = new JSONObject(responseObj.toString());
                    JSONObject data = result.getJSONObject("data");
                    Intent intent = new Intent(MeSigningActivity.this, SiginWebActivity.class);
                    intent.putExtra("url",data.getString("url"));
                    startActivity(intent);
                    finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(OkHttpException failuer) {
                Failuer(failuer.getEcode(), failuer.getEmsg());
            }
        });
    }


    //设置支付密码提示Dialog
    private void showDialog(String value) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_content, null);
        TextView textView = view.findViewById(R.id.dialog_tv1);
        TextView dialog_cancel = view.findViewById(R.id.dialog_cancel);
        TextView dialog_determine = view.findViewById(R.id.dialog_determine);
        textView.setText(value);
        Dialog dialog = new MyDialog1(MeSigningActivity.this, true, true, (float) 0.7).setNewView(view);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        dialog_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog_determine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                posSigningData();
            }
        });
    }
}