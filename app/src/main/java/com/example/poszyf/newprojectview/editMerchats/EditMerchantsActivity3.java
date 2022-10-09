package com.example.poszyf.newprojectview.editMerchats;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.poszyf.R;
import com.example.poszyf.base.BaseActivity;
import com.example.poszyf.bean.BankCardInfo;
import com.example.poszyf.cos.CosServiceFactory;
import com.example.poszyf.homefragment.homemerchants.homenewmerchants.merchantstype.bean.SmallinformationBean;
import com.example.poszyf.net.HttpRequest;
import com.example.poszyf.net.OkHttpException;
import com.example.poszyf.net.RequestParams;
import com.example.poszyf.net.ResponseCallback;
import com.example.poszyf.utils.CustomConfigUtil;
import com.example.poszyf.utils.ImageConvertUtil;
import com.example.poszyf.utils.TimeUtils;
import com.example.poszyf.utils.Utility;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.tencent.cos.xml.CosXmlService;
import com.tencent.cos.xml.exception.CosXmlClientException;
import com.tencent.cos.xml.exception.CosXmlServiceException;
import com.tencent.cos.xml.listener.CosXmlProgressListener;
import com.tencent.cos.xml.listener.CosXmlResultListener;
import com.tencent.cos.xml.model.CosXmlRequest;
import com.tencent.cos.xml.model.CosXmlResult;
import com.tencent.cos.xml.transfer.COSXMLUploadTask;
import com.tencent.cos.xml.transfer.TransferConfig;
import com.tencent.cos.xml.transfer.TransferManager;
import com.tencent.cos.xml.transfer.TransferState;
import com.tencent.cos.xml.transfer.TransferStateListener;
import com.tencent.ocr.sdk.common.ISDKKitResultListener;
import com.tencent.ocr.sdk.common.OcrModeType;
import com.tencent.ocr.sdk.common.OcrSDKConfig;
import com.tencent.ocr.sdk.common.OcrSDKKit;
import com.tencent.ocr.sdk.common.OcrType;
import com.wildma.pictureselector.PictureBean;
import com.wildma.pictureselector.PictureSelector;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * 作者: qgl
 * 创建日期：2022/3/2
 * 描述:修改报件3
 */
public class EditMerchantsActivity3 extends BaseActivity implements View.OnClickListener {
    //银行卡正面
    private SimpleDraweeView id_card_is;
    //银行卡正面图片
    private Bitmap retBitmap1;
    //银行卡正面图片地址
    private String bUrl1 = "";
    //银行卡反面
    private SimpleDraweeView id_card_the;
    //银行卡反面图片地址
    private String bUrl2 = "";
    //银行卡号
    private EditText bNumber;
    /*******第一页传递数据************/
    private String snCode = ""; //设备SN码
    private String uName = ""; //姓名
    private String uPhone = ""; //联系电话
    private String province = ""; //商户注册省份
    private String city = ""; //商户注册城市
    private String area = ""; //商户注册区县
    /*******第一页传递数据************/
    /*******第二页传递数据************/
    private String IdUrl1 = ""; //身份证正面照片
    private String IdUrl2 = ""; //身份证反面照片
    private String IdUrl3 = ""; //手持身份证照片
    private String fName = ""; //法人姓名
    private String fNumber = ""; //法人身份证号码
    private String startTime = ""; //身份证有效期开始
    private String endTime = ""; //身份证有效期结束
    /*******第二页传递数据************/
    //提交数据按钮
    private Button submit_bt;
    private String region = "ap-beijing"; // 存储桶地区
    private String folderName = "";
    private CosXmlService cosXmlService;
    private TransferManager transferManager;
    private COSXMLUploadTask cosxmlTask;
    private String author = "Android";
    /****************存储图片地址**************/
    private LinearLayout iv_back;
    //银行卡号
    private String BankNum;
    //修改的报件ID
    private String merchantNo;
    //图片标识
    private String IdUrl1isActive = "1";
    private String IdUrl2isActive = "1";
    private String IdUrl3isActive = "1";
    private String IdUrl4isActive = "1";
    private String IdUrl5isActive = "1";
    //已经提交的数据ID
    private String Hid = "";

    @Override
    protected int getLayoutId() {
        return R.layout.editmerchants_activity3;
    }

    @Override
    protected void initView() {
        // 初始化腾讯存储桶
        cosXmlService = CosServiceFactory.getCosXmlService(this, region, getSecretId(), getSecretKey(), true);
        TransferConfig transferConfig = new TransferConfig.Builder().build();
        transferManager = new TransferManager(cosXmlService, transferConfig);
        id_card_is = findViewById(R.id.id_card_is);
        bNumber = findViewById(R.id.b_number);
        id_card_the = findViewById(R.id.id_card_the);
        submit_bt = findViewById(R.id.submit_bt);
        iv_back = findViewById(R.id.iv_back);
    }

    @Override
    protected void initListener() {
        id_card_is.setOnClickListener(this);
        id_card_the.setOnClickListener(this);
        submit_bt.setOnClickListener(this);
        iv_back.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        snCode = getIntent().getStringExtra("snCode");
        uName = getIntent().getStringExtra("fName");
        uPhone = getIntent().getStringExtra("uPhone");
        province = getIntent().getStringExtra("province");
        city = getIntent().getStringExtra("city");
        area = getIntent().getStringExtra("area");
        IdUrl1 = getIntent().getStringExtra("IdUrl1");
        IdUrl2 = getIntent().getStringExtra("IdUrl2");
        IdUrl3 = getIntent().getStringExtra("IdUrl3");
        fName = getIntent().getStringExtra("fName");
        fNumber = getIntent().getStringExtra("fNumber");
        startTime = getIntent().getStringExtra("startTime");
        endTime = getIntent().getStringExtra("endTime");
        IdUrl1isActive = getIntent().getStringExtra("IdUrl1isActive");
        IdUrl2isActive = getIntent().getStringExtra("IdUrl2isActive");
        IdUrl3isActive = getIntent().getStringExtra("IdUrl3isActive");
        Hid = getIntent().getStringExtra("Hid");
        bUrl1 = getIntent().getStringExtra("ID_url4");
        bUrl2 = getIntent().getStringExtra("ID_url5");
        BankNum = getIntent().getStringExtra("BankNum");
        merchantNo = getIntent().getStringExtra("merchantNo");
        id_card_is.setImageURI(bUrl1);
        id_card_the.setImageURI(bUrl2);
        bNumber.setText(BankNum);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_card_is:
                initSdk(getSecretId(), getSecretKey());
                OcrSDKKit.getInstance().startProcessOcr(this, OcrType.BankCardOCR,
                        CustomConfigUtil.getInstance().getCustomConfigUi(), new ISDKKitResultListener() {
                            @Override
                            public void onProcessSucceed(String response, String srcBase64Image, String requestId) {
                                //回显银行卡信息
                                getBank_information(response, srcBase64Image);
                            }

                            @Override
                            public void onProcessFailed(String errorCode, String message, String requestId) {
                                popTip(errorCode, message);
                                Log.e("requestId", requestId);
                            }
                        });
                break;
            case R.id.id_card_the:
                PictureSelector
                        .create(this, PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture(false);
                break;
            case R.id.submit_bt:
                if (TextUtils.isEmpty(bUrl1)) {
                    showToast(3, "银行卡正面照片");
                    return;
                }
                if (TextUtils.isEmpty(bUrl2)) {
                    showToast(3, "银行卡反面照片");
                    return;
                }
                if (TextUtils.isEmpty(bNumber.getText().toString().trim())) {
                    showToast(3, "银行卡号");
                    return;
                }
                loadDialog.show();
                folderName = "baojian" + "/" + author + "/" + fNumber + "/" + TimeUtils.getNowTime("day");
                List<SmallinformationBean> beans = new ArrayList<>();
                // 需要存储的
                if (IdUrl1isActive.equals("2")) {
                    SmallinformationBean bean1 = new SmallinformationBean();
                    bean1.setName("1");
                    bean1.setUrl(IdUrl1);
                    bean1.setAcitvie(IdUrl1isActive);
                    beans.add(bean1);
                }
                if (IdUrl2isActive.equals("2")) {
                    SmallinformationBean bean2 = new SmallinformationBean();
                    bean2.setName("2");
                    bean2.setUrl(IdUrl2);
                    bean2.setAcitvie(IdUrl2isActive);
                    beans.add(bean2);
                }
                if (IdUrl3isActive.equals("2")) {
                    SmallinformationBean bean3 = new SmallinformationBean();
                    bean3.setName("3");
                    bean3.setUrl(IdUrl3);
                    bean3.setAcitvie(IdUrl3isActive);
                    beans.add(bean3);
                }
                if (IdUrl4isActive.equals("2")) {
                    SmallinformationBean bean4 = new SmallinformationBean();
                    bean4.setName("4");
                    bean4.setUrl(bUrl1);
                    bean4.setAcitvie(IdUrl4isActive);
                    beans.add(bean4);
                }
                if (IdUrl5isActive.equals("2")) {
                    SmallinformationBean bean5 = new SmallinformationBean();
                    bean5.setName("5");
                    bean5.setUrl(bUrl2);
                    bean5.setAcitvie(IdUrl5isActive);
                    beans.add(bean5);
                }
                if (beans.size() < 1) {
                    EditData();

                } else {
                    Log.e("------》", "需要上传图片");
                    for (int i = 0; i < beans.size(); i++) {
                        upload(beans, folderName);
                    }
                }
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    /**
     * 腾讯银行卡识别初始化
     */
    private void initSdk(String secretId, String secretKey) {
        // 启动参数配置
        OcrType ocrType = OcrType.BankCardOCR; // 设置默认的业务识别，银行卡
        OcrSDKConfig configBuilder = OcrSDKConfig.newBuilder(secretId, secretKey, null)
                .OcrType(ocrType)
                .ModeType(OcrModeType.OCR_DETECT_MANUAL)
                .build();
        // 初始化SDK
        OcrSDKKit.getInstance().initWithConfig(this.getApplicationContext(), configBuilder);
    }

    /**
     * 获取银行卡信息
     *
     * @param response
     * @param srcBase64Image
     */
    public void getBank_information(String response, String srcBase64Image) {
        try {
            if (!srcBase64Image.isEmpty()) {
                retBitmap1 = ImageConvertUtil.base64ToBitmap(srcBase64Image);
            }
            if (retBitmap1 != null) {
                id_card_is.setImageBitmap(retBitmap1);
                bUrl1 = ImageConvertUtil.getFile(retBitmap1).getCanonicalPath();
                IdUrl4isActive = "2";
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!response.isEmpty()) {
            final BankCardInfo bankCardInfo = new Gson().fromJson(response, BankCardInfo.class);
            bNumber.setText(bankCardInfo.getCardNo());
        }
    }


    /**
     * 上传图片,对象存储
     */
    private void upload(List<SmallinformationBean> list, String newfolderName) {
        int i = 0;
        if (TextUtils.isEmpty(list.get(i).getUrl()) || list.get(i).getAcitvie().equals("1")) {
            list.remove(i);
            return;
        }
        if (cosxmlTask == null) {
            File file = new File(list.get(i).getUrl());
            String cosPath;
            if (TextUtils.isEmpty(newfolderName)) {
                cosPath = file.getName();
            } else {
                cosPath = newfolderName + File.separator + file.getName();
            }
            cosxmlTask = transferManager.upload(getBucketName(), cosPath, list.get(i).getUrl(), null);
            Log.e("参数-------》", getBucketName() + "----" + cosPath + "---" + list.get(i).getUrl());

            cosxmlTask.setTransferStateListener(new TransferStateListener() {
                @Override
                public void onStateChanged(final TransferState state) {
                    // refreshUploadState(state);
                }
            });
            cosxmlTask.setCosXmlProgressListener(new CosXmlProgressListener() {
                @Override
                public void onProgress(final long complete, final long target) {
                    // refreshUploadProgress(complete, target);
                }
            });
            cosxmlTask.setCosXmlResultListener(new CosXmlResultListener() {
                @Override
                public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                    COSXMLUploadTask.COSXMLUploadTaskResult cOSXMLUploadTaskResult = (COSXMLUploadTask.COSXMLUploadTaskResult) result;
                    cosxmlTask = null;
                    Log.e("1111", "成功");
                    if (list.get(i).getName().equals("1")) {
                        IdUrl1 = cOSXMLUploadTaskResult.accessUrl;
                        Log.e("身份证正面", cOSXMLUploadTaskResult.accessUrl);
                    } else if (list.get(i).getName().equals("2")) {
                        IdUrl2 = cOSXMLUploadTaskResult.accessUrl;
                        Log.e("身份证反面", cOSXMLUploadTaskResult.accessUrl);
                    } else if (list.get(i).getName().equals("3")) {
                        IdUrl3 = cOSXMLUploadTaskResult.accessUrl;
                        Log.e("手持身份证", cOSXMLUploadTaskResult.accessUrl);
                    } else if (list.get(i).getName().equals("4")) {
                        bUrl1 = cOSXMLUploadTaskResult.accessUrl;
                        Log.e("银行卡正面", cOSXMLUploadTaskResult.accessUrl);
                    } else {
                        Log.e("银行卡反面", cOSXMLUploadTaskResult.accessUrl);
                        bUrl2 = cOSXMLUploadTaskResult.accessUrl;
                    }
                    setResult(RESULT_OK);
                    list.remove(i);
                    if (list.size() < 1) {
                        Log.e("----------》", "没有了去提交后台吧");
                        EditData();
                    } else {
                        upload(list, newfolderName);
                    }
                }

                @Override
                public void onFail(CosXmlRequest request, CosXmlClientException exception, CosXmlServiceException serviceException) {
                    if (cosxmlTask.getTaskState() != TransferState.PAUSED) {
                        cosxmlTask = null;
                        Log.e("1111", "上传失败");
                        loadDialog.dismiss();
                        showToast(2, "图片上传失败！请重新选择图片");
                    }
                    exception.printStackTrace();
                    serviceException.printStackTrace();
                }
            });

        }
    }

    /**
     * 修改
     */
    private void EditData() {
        RequestParams params = new RequestParams();
        params.put("merchantNo", merchantNo);
        params.put("sn", snCode);
        params.put("accountId", getUserName());
        params.put("provinceno", province);
        params.put("cityno", city);
        params.put("areano", area);
        params.put("certificateno", fNumber);
        params.put("certificatestartdate", startTime);
        params.put("certificateenddate", endTime);
        params.put("bankcardaccount", bNumber.getText().toString().trim());
        params.put("idcardhand", IdUrl3);
        params.put("idcardfront", IdUrl1);
        params.put("idcardback", IdUrl2);
        params.put("bankcardfront", bUrl1);
        params.put("bankcardback", bUrl2);
        HttpRequest.getNewUpdate(params,new ResponseCallback() {
            @Override
            public void onSuccess(Object responseObj) {
                loadDialog.dismiss();
                try {
                    JSONObject result = new JSONObject(responseObj.toString());
                    if (result.getString("data").equals("true")) {
                        //成功
                        EditMerchantsActivity2.instance.finish();
                        EditMerchantsActivity1.instance.finish();
                        finish();
                    } else {
                        showToast(3, result.getString("msg"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(OkHttpException failuer) {
                loadDialog.dismiss();
                Failuer(failuer.getEcode(), failuer.getEmsg());
            }

        });
    }
    /************************************** 选取照片开始 ***********************************************************************/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PictureSelector.SELECT_REQUEST_CODE) {
            if (data != null) {
                PictureBean pictureBean = data.getParcelableExtra(PictureSelector.PICTURE_RESULT);
                IdUrl5isActive = "2";
                Luban.with(this)
                        .load(pictureBean.getPath())
                        .ignoreBy(100)
                        .setTargetDir(Utility.getPath())
                        .filter(new CompressionPredicate() {
                            @Override
                            public boolean apply(String path) {
                                return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
                            }
                        })
                        .setCompressListener(new OnCompressListener() {
                            @Override
                            public void onStart() {

                            }

                            @Override
                            public void onSuccess(File file) {
                                bUrl2 = file.getPath();
                                id_card_the.setImageBitmap(BitmapFactory.decodeFile(bUrl2));
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }).launch();
            }
        }
    }

    /************************************** 选取照片结束 ***********************************************************************/
}