package com.example.poszyf.newprojectview.editMerchats;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.poszyf.R;
import com.example.poszyf.base.BaseActivity;
import com.example.poszyf.net.HttpRequest;
import com.example.poszyf.net.OkHttpException;
import com.example.poszyf.net.RequestParams;
import com.example.poszyf.net.ResponseCallback;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者: qgl
 * 创建日期：2022/3/1
 * 描述:修改报件1
 */
public class EditMerchantsActivity1 extends BaseActivity implements View.OnClickListener {
    //已经提交的数据ID
    private String Hid = "";
    // 需要关闭
    public static EditMerchantsActivity1 instance = null;
    //选择商户注册省市区、TextView
    private TextView province_tv;
    //设备SN码
    private EditText quote_sn_num;
    //申请人联系电话
    private EditText quote_phone;
    //提交按钮
    private Button submit_bt;
    //返回键
    private LinearLayout iv_back;
    private String snNum;
    private String Phone;
    private String AddressU;
    private String ID_url1;
    private String ID_url2;
    private String ID_url3;
    private String ID_url4;
    private String ID_url5;
    private String Name;
    private String IdNum;
    private String onYear;
    private String outYear;
    private String BankNum;
    private String merchantNo;
    private String province = ""; //省编码
    private String city = ""; //市编码
    private String area = ""; //区编码

    @Override
    protected int getLayoutId() {
        statusBarConfig(R.color.new_theme_color, false).init();
        return R.layout.edit_merchants_activity_01;
    }

    @Override
    protected void initView() {
        instance = this;
        province_tv = findViewById(R.id.province_tv);
        quote_sn_num = findViewById(R.id.quote_sn_num);
        quote_phone = findViewById(R.id.quote_phone);
        submit_bt = findViewById(R.id.submit_bt);
        iv_back = findViewById(R.id.iv_back);
    }

    @Override
    protected void initListener() {
        submit_bt.setOnClickListener(this);
        iv_back.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        Hid = getIntent().getStringExtra("id");
        posDetail(Hid);

    }

    //请求填写的数据
    private void posDetail(String id) {
        RequestParams params = new RequestParams();
        params.put("merchantNo", id);
        HttpRequest.getNewEntry(params,new ResponseCallback() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    JSONObject result = new JSONObject(responseObj.toString());
                    JSONObject data = result.getJSONObject("data");
                    snNum = data.getString("sn");
                    Phone = data.getString("phone");
                    AddressU = data.getJSONObject("provinceno").getString("cname") + "-" + data.getJSONObject("cityno").getString("cname") + "-" + data.getJSONObject("areano").getString("cname");
                    province = data.getJSONObject("provinceno").getString("dictValue");
                    city = data.getJSONObject("cityno").getString("dictValue");
                    area = data.getJSONObject("areano").getString("dictValue");
                    Name = data.getString("applicant");
                    IdNum = data.getString("certificateno");
                    onYear = data.getString("certificatestartdate");
                    outYear = data.getString("certificateenddate");
                    BankNum = data.getString("bankcardaccount");
                    merchantNo = data.getString("merchantNo");
                    ID_url1 = data.getString("idcardfront");
                    ID_url2 = data.getString("idcardback");
                    ID_url3 = data.getString("idcardhand");
                    ID_url4 = data.getString("bankcardfront");
                    ID_url5 = data.getString("bankcardback");
                    //赋值给控件
                    setVaule();
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.submit_bt:
                Intent intent = new Intent(this, EditMerchantsActivity2.class);
                if (TextUtils.isEmpty(quote_sn_num.getText().toString().trim())) {
                    showToast(3, "SN号码");
                    return;
                }

                if (TextUtils.isEmpty(quote_phone.getText().toString().trim())) {
                    showToast(3, "电话");
                    return;
                }

                if (TextUtils.isEmpty(province.trim())) {
                    showToast(3, "商户省市区");
                    return;
                }
                intent.putExtra("quote_sn_num", quote_sn_num.getText().toString().trim());
                intent.putExtra("quote_phone", quote_phone.getText().toString().trim());
                intent.putExtra("province", province);
                intent.putExtra("city", city);
                intent.putExtra("area", area);
                intent.putExtra("Hid", Hid);
                intent.putExtra("ID_url1", ID_url1);
                intent.putExtra("ID_url2", ID_url2);
                intent.putExtra("ID_url3", ID_url3);
                intent.putExtra("ID_url4", ID_url4);
                intent.putExtra("ID_url5", ID_url5);
                intent.putExtra("Name", Name);
                intent.putExtra("IdNum", IdNum);
                intent.putExtra("onYear", onYear);
                intent.putExtra("outYear", outYear);
                intent.putExtra("BankNum", BankNum);
                intent.putExtra("merchantNo", merchantNo);
                startActivity(intent);
                break;
        }
    }

    private void setVaule() {
        quote_sn_num.setText(snNum);
        quote_phone.setText(Phone);
        province_tv.setText(AddressU);
    }
}