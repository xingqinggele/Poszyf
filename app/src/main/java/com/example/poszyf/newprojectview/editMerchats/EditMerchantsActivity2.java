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
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * 作者: qgl
 * 创建日期：2022/3/2
 * 描述:修改报件2
 */
public class EditMerchantsActivity2 extends BaseActivity implements View.OnClickListener {
    //返回键
    private LinearLayout iv_back;
    // 身份证正面
    private SimpleDraweeView id_card_is;
    // 身份证反面
    private SimpleDraweeView id_card_the;
    //手持身份证照片
    private SimpleDraweeView id_card_pay;
    //身份证正面照片地址
    private String IdCard1_Url = "";
    //标识
    private String IdUrl1isActive = "1";
    //身份证反面照片地址
    private String IdCard2_Url = "";
    //标识
    private String IdUrl2isActive = "1";
    //手持身份证照片地址
    private String Handheld_url = "";
    //标识
    private String IdUrl3isActive = "1";
    //有效期开始时间
    private String s = "";
    //有效期结束时间
    private String t = "";
    // 姓名
    private EditText name_ed;
    // 身份证号码
    private EditText card_number_ed;
    //开始时间
    private TextView home_quote_start_time;
    //结束时间
    private TextView home_quote_un_time;
    //下一步按钮
    private Button submit_bt;
    // 需要关闭
    public static EditMerchantsActivity2 instance = null;
    /*******第一页传递数据************/
    private String snCode = ""; //设备SN码
    private String uPhone = ""; //联系电话
    private String province = ""; //商户注册省份
    private String city = ""; //商户注册城市
    private String area = ""; //商户注册区县
    private String Hid = "";
    private String ID_url4;
    private String ID_url5;
    private String Name;
    private String IdNum;
    private String BankNum;
    private String merchantNo;
    /*******第一页传递数据************/


    @Override
    protected int getLayoutId() {
        //设置状态栏颜色
        statusBarConfig(R.color.new_theme_color, false).init();
        return R.layout.editmerchants_activity2;
    }

    @Override
    protected void initView() {
        instance = this;
        iv_back = findViewById(R.id.iv_back);
        id_card_is = findViewById(R.id.id_card_is);
        name_ed = findViewById(R.id.name_ed);
        card_number_ed = findViewById(R.id.card_number_ed);
        id_card_the = findViewById(R.id.id_card_the);
        id_card_pay = findViewById(R.id.id_card_pay);
        home_quote_start_time = findViewById(R.id.home_quote_start_time);
        home_quote_un_time = findViewById(R.id.home_quote_un_time);
        submit_bt = findViewById(R.id.submit_bt);
    }

    @Override
    protected void initListener() {
        iv_back.setOnClickListener(this);
        submit_bt.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        snCode = getIntent().getStringExtra("quote_sn_num");
        uPhone = getIntent().getStringExtra("quote_phone");
        province = getIntent().getStringExtra("province");
        city = getIntent().getStringExtra("city");
        area = getIntent().getStringExtra("area");
        Hid = getIntent().getStringExtra("Hid");
        IdCard1_Url = getIntent().getStringExtra("ID_url1");
        IdCard2_Url = getIntent().getStringExtra("ID_url2");
        Handheld_url = getIntent().getStringExtra("ID_url3");
        ID_url4 = getIntent().getStringExtra("ID_url4");
        ID_url5 = getIntent().getStringExtra("ID_url5");
        Name = getIntent().getStringExtra("Name");
        IdNum = getIntent().getStringExtra("IdNum");
        s = getIntent().getStringExtra("onYear");
        t = getIntent().getStringExtra("outYear");
        BankNum = getIntent().getStringExtra("BankNum");
        merchantNo = getIntent().getStringExtra("merchantNo");
        id_card_is.setImageURI(IdCard1_Url);
        id_card_the.setImageURI(IdCard2_Url);
        id_card_pay.setImageURI(Handheld_url);
        name_ed.setText(Name);
        card_number_ed.setText(IdNum);
        home_quote_start_time.setText(s);
        home_quote_un_time.setText(t);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.submit_bt:
                Intent intent = new Intent(this, EditMerchantsActivity3.class);
                if (TextUtils.isEmpty(IdCard1_Url)) {
                    showToast(3, "选择身份证正面照");
                    return;
                }
                if (TextUtils.isEmpty(IdCard2_Url)) {
                    showToast(3, "选择身份证背面照");
                    return;
                }
                if (TextUtils.isEmpty(Handheld_url)) {
                    showToast(3, "选择手持身份证照");
                    return;
                }
                if (TextUtils.isEmpty(name_ed.getText().toString().trim())) {
                    showToast(3, "法人姓名");
                    return;
                }
                if (TextUtils.isEmpty(card_number_ed.getText().toString().trim())) {
                    showToast(3, "法人身份证号码");
                    return;
                }
                if (TextUtils.isEmpty(s)) {
                    showToast(3, "有效期开始时间");
                    return;
                }
                if (TextUtils.isEmpty(t)) {
                    showToast(3, "有效期结束时间");
                    return;
                }
                intent.putExtra("snCode", snCode);
                intent.putExtra("uPhone", uPhone);
                intent.putExtra("province", province);
                intent.putExtra("city", city);
                intent.putExtra("area", area);
                intent.putExtra("IdUrl1", IdCard1_Url);
                intent.putExtra("IdUrl1isActive", IdUrl1isActive);
                intent.putExtra("IdUrl2", IdCard2_Url);
                intent.putExtra("IdUrl2isActive", IdUrl2isActive);
                intent.putExtra("IdUrl3", Handheld_url);
                intent.putExtra("IdUrl3isActive", IdUrl3isActive);
                intent.putExtra("fName", name_ed.getText().toString().trim());
                intent.putExtra("fNumber", card_number_ed.getText().toString().trim());
                intent.putExtra("startTime", s);
                intent.putExtra("endTime", t);
                intent.putExtra("Hid", Hid);
                intent.putExtra("ID_url4", ID_url4);
                intent.putExtra("ID_url5", ID_url5);
                intent.putExtra("BankNum", BankNum);
                intent.putExtra("merchantNo", merchantNo);
                startActivity(intent);
                break;
        }
    }
}