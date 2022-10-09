package com.example.poszyf.newprojectview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.poszyf.R;
import com.example.poszyf.base.BaseActivity;
import com.example.poszyf.bean.BankCardInfo;
import com.example.poszyf.cos.CosServiceFactory;

import com.example.poszyf.homefragment.homemerchants.homenewmerchants.merchantstype.bean.SmallinformationBean;

import com.example.poszyf.net.HttpRequest;
import com.example.poszyf.net.OkHttpException;
import com.example.poszyf.net.RequestParams;
import com.example.poszyf.net.ResponseCallback;
import com.example.poszyf.newprojectview.adapter.BankNameAdapter;
import com.example.poszyf.newprojectview.adapter.ZhiBankNameAdapter;
import com.example.poszyf.newprojectview.bean.BankNameBean;
import com.example.poszyf.newprojectview.bean.NewCityBean;
import com.example.poszyf.newprojectview.bean.NewProvinceBean;
import com.example.poszyf.newprojectview.model.ProvinceView;
import com.example.poszyf.utils.CustomConfigUtil;
import com.example.poszyf.utils.ImageConvertUtil;
import com.example.poszyf.utils.TimeUtils;
import com.example.poszyf.utils.Utility;
import com.example.poszyf.views.MyListView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
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
 * 创建日期：2021/8/17
 * 描述: 商户报件3
 */
public class AddMerchantsActivity3 extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private LinearLayout iv_back;
    // 需要关闭
    public static AddMerchantsActivity3 instance = null;
    //银行卡正面
    private SimpleDraweeView id_card_is;
    //银行卡正面图片
    private Bitmap retBitmap1;
    //银行卡正面图片地址
    private String bUrl1 = "";
    private String url4 = "";
    //银行卡号
    private EditText bNumber;
    //提交数据按钮
    private Button submit_bt;
    //开户名
    private EditText quote_xy_card_name;
    //银行预留手机号
    private EditText quote_xy_phone;
    //银行编码
    private String bankCode = "";
    //支行名称
    private String bankSite = "";
    //开户支行联行号
    private String bankSiteCode = "";
    //开户省
    private String BankProvince = "";
    //开户市
    private String BankCity = "";
    //开户网点
    private EditText bankSite_tv;
    /************* 存储桶 ****************/

    //存储桶地区
    private String region = "ap-beijing";
    private String folderName = "";
    private String author = "Android";
    private CosXmlService cosXmlService;
    private TransferManager transferManager;
    private COSXMLUploadTask cosxmlTask;
    private int BankCardIn = 1006;

    /************* 存储桶 ****************/

    /************* 弹框  ****************/

    private PopupWindow popWindow;
    private View popView;
    private RelativeLayout province_relative;
    private ProvinceView provinceView;
    private TextView province_tv;
    private EditText bank_code_ed;
    private MyListView addListView;
    private MyListView zhListView;
    private List<BankNameBean> bankNameBeanList = new ArrayList<>();
    private List<BankNameBean> zhiBankList = new ArrayList<>();
    private BankNameAdapter bankNameAdapter;
    private ZhiBankNameAdapter zhiBankNameAdapter;

    /************* 弹框  ****************/

    /**************  地图定位  ****************/

    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    private String Longitude = ""; //经
    private String Latitude = "";//纬度

    /**************  地图定位  ****************/

    /**************  接受数据  ****************/
    private String applicant;
    private String contactPhoneNo;
    private String clientServicePhoneNo;
    private String merchantShortHand;
    private String rateId;
    private String PosCode;
    private String provinceNo;
    private String cityNo;
    private String areaNo;
    private String address;
    private String certificateEndDate;
    private String certificateStartDate;
    private String certificateNo;
    private String certificatesBackPic;
    private String url1 ;
    private String url2 ;
    private String url3 ;
    private String certificatesFrontPic;
    private String idcardHandPic;
    private String legalPersonName;

    /**************  接受数据  ****************/
    private boolean isBank = true;
    private boolean isBankSite = true;

    @Override
    protected int getLayoutId() {
        //设置状态栏颜色
        statusBarConfig(R.color.new_theme_color, false).init();
        return R.layout.addmerchants_activity_03;
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void initView() {
        instance = this;
        // 初始化腾讯存储桶
        cosXmlService = CosServiceFactory.getCosXmlService(this, region, getSecretId(), getSecretKey(), true);
        TransferConfig transferConfig = new TransferConfig.Builder().build();
        transferManager = new TransferManager(cosXmlService, transferConfig);
        //初始化选择省市区
        provinceView = new ProvinceView(this, AddMerchantsActivity3.this);
        //初始化定位
        initLocation();
        //开启定位
        startLocation();
        id_card_is = findViewById(R.id.id_card_is);
        bNumber = findViewById(R.id.b_number);
        submit_bt = findViewById(R.id.submit_bt);
        iv_back = findViewById(R.id.iv_back);
        province_relative = findViewById(R.id.province_relative);
        province_tv = findViewById(R.id.province_tv);
        bank_code_ed = findViewById(R.id.bank_code_ed);
        addListView = findViewById(R.id.addListView);
        zhListView = findViewById(R.id.zhListView);
        bankSite_tv = findViewById(R.id.bankSite_tv);
        quote_xy_card_name = findViewById(R.id.quote_xy_card_name);
        quote_xy_phone = findViewById(R.id.quote_xy_phone);

    }

    //初始化定位
    private void initLocation() {
        AMapLocationClient.updatePrivacyShow(this, true, true);
        AMapLocationClient.updatePrivacyAgree(this, true);
        //初始化client
        try {
            locationClient = new AMapLocationClient(this.getApplicationContext());
            locationOption = getDefaultOption();
            //设置定位参数
            locationClient.setLocationOption(locationOption);
            // 设置定位监听
            locationClient.setLocationListener(locationListener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 定位监听
     */
    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation location) {
            if (null != location) {
                StringBuffer sb = new StringBuffer();
                //errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
                if (location.getErrorCode() == 0) {
                    Longitude = location.getLongitude() + "";
                    Latitude = location.getLatitude() + "";
                    sb.append("定位成功" + "\n");
                    sb.append("定位类型: " + location.getLocationType() + "\n");
                    sb.append("经    度    : " + location.getLongitude() + "\n");
                    sb.append("纬    度    : " + location.getLatitude() + "\n");
                    sb.append("精    度    : " + location.getAccuracy() + "米" + "\n");
                    sb.append("提供者    : " + location.getProvider() + "\n");

                    sb.append("速    度    : " + location.getSpeed() + "米/秒" + "\n");
                    sb.append("角    度    : " + location.getBearing() + "\n");
                    // 获取当前提供定位服务的卫星个数
                    sb.append("星    数    : " + location.getSatellites() + "\n");
                    sb.append("国    家    : " + location.getCountry() + "\n");
                    sb.append("省            : " + location.getProvince() + "\n");
                    sb.append("市            : " + location.getCity() + "\n");
                    sb.append("城市编码 : " + location.getCityCode() + "\n");
                    sb.append("区            : " + location.getDistrict() + "\n");
                    sb.append("区域 码   : " + location.getAdCode() + "\n");
                    sb.append("地    址    : " + location.getAddress() + "\n");
                    sb.append("兴趣点    : " + location.getPoiName() + "\n");
                    //成功之后停止定位
                    stopLocation();
                    shouLog("地址信息1：", sb.toString());
                } else {
                    //定位失败
                    sb.append("定位失败" + "\n");
                    sb.append("错误码:" + location.getErrorCode() + "\n");
                    sb.append("错误信息:" + location.getErrorInfo() + "\n");
                    sb.append("错误描述:" + location.getLocationDetail() + "\n");
                   // startLocation();
                }

                //解析定位结果，
                String result = sb.toString();
                shouLog("地址信息2：", sb.toString());
            } else {

            }
        }
    };

    /**
     * 开始定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void startLocation() {
        try {
            // 设置定位参数
            locationClient.setLocationOption(locationOption);
            // 启动定位
            locationClient.startLocation();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 停止定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void stopLocation() {
        try {
            // 停止定位
            locationClient.stopLocation();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 销毁定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void destroyLocation() {
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }
    }


    /**
     * 默认的定位参数
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(true);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(true);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        mOption.setGeoLanguage(AMapLocationClientOption.GeoLanguage.DEFAULT);//可选，设置逆地理信息的语言，默认值为默认语言（根据所在地区选择语言）
        return mOption;
    }

    @Override
    protected void initListener() {
        id_card_is.setOnClickListener(this);
        submit_bt.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        province_relative.setOnClickListener(this);
        bank_code_ed.addTextChangedListener(textWatcher);
        bankSite_tv.addTextChangedListener(zhiTextWatcher);
        addListView.setOnItemClickListener(this);
        zhListView.setOnItemClickListener(this);

    }

    @Override
    protected void initData() {
        applicant = getIntent().getStringExtra("quote_contact_name");
        merchantShortHand = getIntent().getStringExtra("quote_shop_jname");
        contactPhoneNo = getIntent().getStringExtra("quote_service_phone");
        rateId = getIntent().getStringExtra("rateId");
        PosCode = getIntent().getStringExtra("PosCode");
        address = getIntent().getStringExtra("quote_address");
        clientServicePhoneNo = getIntent().getStringExtra("quote_phone");
        provinceNo = getIntent().getStringExtra("province");
        cityNo = getIntent().getStringExtra("city");
        areaNo = getIntent().getStringExtra("area");
        url1 = getIntent().getStringExtra("IdUrl1");
        url2 = getIntent().getStringExtra("IdUrl2");
        url3 = getIntent().getStringExtra("IdUrl3");
        legalPersonName = getIntent().getStringExtra("fName");
        certificateNo = getIntent().getStringExtra("fNumber");
        certificateStartDate = getIntent().getStringExtra("startTime");
        certificateEndDate = getIntent().getStringExtra("endTime");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_card_is:
                showEditPhotoWindow(BankCardIn);

                break;
            case R.id.id_card_the:
                PictureSelector
                        .create(AddMerchantsActivity3.this, PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture(false);
                break;
            case R.id.submit_bt:
                if (TextUtils.isEmpty(url4)) {
                    showToast(3, "银行卡正面照片");
                    return;
                }
                if (TextUtils.isEmpty(bNumber.getText().toString().trim())) {
                    showToast(3, "银行卡号");
                    return;
                }
                if (TextUtils.isEmpty(quote_xy_card_name.getText().toString().trim())) {
                    showToast(3, "开户名");
                    return;
                }
                if (TextUtils.isEmpty(quote_xy_phone.getText().toString().trim())) {
                    showToast(3, "开户手机号");
                    return;
                }
                if (TextUtils.isEmpty(BankProvince)) {
                    showToast(3, "请选择省");
                    return;
                }
                if (TextUtils.isEmpty(BankCity)) {
                    showToast(3, "请选择市");
                    return;
                }
                if (TextUtils.isEmpty(bankCode)) {
                    showToast(3, "请选择正确银行编码");
                    return;
                }

                if (TextUtils.isEmpty(bankSite)) {
                    showToast(3, "请选择正确开户支行网点");
                    return;
                }

                loadDialog.show();
                folderName = "baojian" + "/" + author + "/" + certificateNo + "/" + TimeUtils.getNowTime("day");
                List<SmallinformationBean> beans = new ArrayList<>();
                // 需要存储的
                SmallinformationBean bean1 = new SmallinformationBean();
                bean1.setName("1");
                bean1.setUrl(url1);
                beans.add(bean1);
                SmallinformationBean bean2 = new SmallinformationBean();
                bean2.setName("2");
                bean2.setUrl(url2);
                beans.add(bean2);
                SmallinformationBean bean3 = new SmallinformationBean();
                bean3.setName("3");
                bean3.setUrl(url3);
                beans.add(bean3);
                SmallinformationBean bean4 = new SmallinformationBean();
                bean4.setName("4");
                bean4.setUrl(url4);
                beans.add(bean4);
                if (beans.size() < 1) {
                    posData();
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
            case R.id.province_relative:
                provinceView.showCityPicker();
                break;

        }
    }


    /**
     * 上传图片,对象存储
     */
    private void upload(List<SmallinformationBean> list, String newfolderName) {
        int i = 0;
        if (TextUtils.isEmpty(list.get(i).getUrl())) {
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
                        certificatesBackPic = cOSXMLUploadTaskResult.accessUrl;
                        Log.e("身份证正面", cOSXMLUploadTaskResult.accessUrl);
                    } else if (list.get(i).getName().equals("2")) {
                        certificatesFrontPic = cOSXMLUploadTaskResult.accessUrl;
                        Log.e("身份证反面", cOSXMLUploadTaskResult.accessUrl);
                    } else if (list.get(i).getName().equals("3")) {
                        idcardHandPic = cOSXMLUploadTaskResult.accessUrl;
                        Log.e("手持身份证", cOSXMLUploadTaskResult.accessUrl);
                    } else if (list.get(i).getName().equals("4")) {
                        bUrl1 = cOSXMLUploadTaskResult.accessUrl;
                        Log.e("银行卡正面", cOSXMLUploadTaskResult.accessUrl);
                    }
                    setResult(RESULT_OK);
                    list.remove(i);
                    if (list.size() < 1) {
                        Log.e("----------》", "没有了去提交后台吧");
                        posData();
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
     * 新增
     */
    private void posData() {
        RequestParams params = new RequestParams();
        //联系人
        params.put("applicant", applicant);
        //联系电话
        params.put("contactPhoneNo", contactPhoneNo);
        //客服电话
        params.put("clientServicePhoneNo", clientServicePhoneNo);
        //费率ID
        params.put("feeId", rateId);

        params.put("posCode", PosCode);
        //商户简写
        params.put("merchantShortHand", merchantShortHand);
        //省份代码
        params.put("provinceNo", provinceNo);
        //城市编号
        params.put("cityNo", cityNo);
        //区县代码
        params.put("areaNo", areaNo);
        //详细地址
        params.put("address", address);
        //证件有效期截止日期（例：1949-10-01）;
        params.put("certificateEndDate", certificateEndDate);
        //法人证件号码
        params.put("certificateNo", certificateNo);
        //证件有效期开始日期（例：1949-10-01）
        params.put("certificateStartDate", certificateStartDate);
        //证件国徽面
        params.put("certificatesBackPic", certificatesFrontPic);
        //证件人脸面
        params.put("certificatesFrontPic",certificatesBackPic);
        //手持身份证照片
        params.put("idcardHandPic", idcardHandPic);
        //法人姓名
        params.put("legalPersonName", legalPersonName);
        //银行账号
        params.put("bankCardAccount", bNumber.getText().toString().trim());
        //开户名
        params.put("bankCardHolder", quote_xy_card_name.getText().toString().trim());
        //银行卡预留手机号
        params.put("bankCardPhone", quote_xy_phone.getText().toString().trim());
        //银行卡照片
        params.put("bankCardPic", bUrl1);
        //开户银行省份
        params.put("bankProvince", BankProvince);
        //开户银行城市
        params.put("bankCity", BankCity);
        //银行编码
        params.put("bankCode", bankCode);
        //开户行网点
        params.put("bankSite", bankSite);
        //开户支行联行号
        params.put("unionpayCode", bankSiteCode);
        //手机号
        params.put("phone", getUserName());
        //商户活体检测地址
        params.put("activeAddress", address);
        //商户活体检测地址纬度
        params.put("activeLatitude", Longitude);
        //商户活体检测地址经度
        params.put("activeLongitude", Latitude);
        shouLog("提交的值-------------->", params.toString());
        HttpRequest.getPosPOperation(params,  new ResponseCallback() {
            @Override
            public void onSuccess(Object responseObj) {
                loadDialog.dismiss();
                try {
                    JSONObject result = new JSONObject(responseObj.toString());
                     showToast(2, result.getString("msg") + "");
                     AddMerchantsActivity1.instance.finish();
                     AddMerchantsActivity2.instance.finish();
                     finish();
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
        if (requestCode == BankCardIn) {
            PictureBean pictureBean = data.getParcelableExtra(PictureSelector.PICTURE_RESULT);
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
                            url4 = file.getPath();
                            id_card_is.setImageBitmap(BitmapFactory.decodeFile(url4));
                        }

                        @Override
                        public void onError(Throwable e) {

                        }
                    }).launch();
        }
    }

    /************************************** 选取照片结束 ***********************************************************************/

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
                url4 = ImageConvertUtil.getFile(retBitmap1).getCanonicalPath();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!response.isEmpty()) {
            final BankCardInfo bankCardInfo = new Gson().fromJson(response, BankCardInfo.class);
            bNumber.setText(bankCardInfo.getCardNo());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OcrSDKKit.getInstance().release();
        destroyLocation();

    }


    /**
     * 弹出框
     *
     * @param value
     */
    private void showEditPhotoWindow(int value) {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        popView = layoutInflater.inflate(R.layout.popwindow_main, (ViewGroup) null);
        popWindow = new PopupWindow(popView, -1, -2);
        popWindow.setAnimationStyle(R.style.AnimBottom);
        popWindow.setBackgroundDrawable(new ColorDrawable());
        popWindow.showAtLocation(popView, 80, 0, 0);
        popWindow.setTouchable(true);
        popWindow.setOutsideTouchable(false);
        popWindow.setFocusable(true);
        TextView tv_select_pic = popView.findViewById(R.id.tv_photo);
        TextView tv_pai_pic = popView.findViewById(R.id.tv_photograph);
        TextView tv_cancl = popView.findViewById(R.id.tv_cancle);
        LinearLayout layout = popView.findViewById(R.id.dialog_ll);
        tv_select_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popWindow.isShowing()) {
                    popWindow.dismiss();
                }
                initSdk(getSecretId(), getSecretKey());
                OcrSDKKit.getInstance().startProcessOcr(AddMerchantsActivity3.this, OcrType.BankCardOCR,
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
            }
        });
        tv_pai_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popWindow.isShowing()) {
                    popWindow.dismiss();
                }
                PictureSelector
                        .create(AddMerchantsActivity3.this, value)
                        .selectPicture(false);
            }
        });
        tv_cancl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popWindow.isShowing()) {
                    popWindow.dismiss();
                }
            }
        });
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popWindow.isShowing()) {
                    popWindow.dismiss();
                }
            }
        });

    }


    /**
     * 请求省市区
     *
     * @param areaLevel  级别 1、2、3
     * @param parentCode 父类的code
     * @param type       请求的界别 1、2、3
     */
    public void postProvince(String areaLevel, String parentCode, int type) {
        RequestParams params = new RequestParams();
        params.put("areaLevel", areaLevel);
        params.put("parentCode", parentCode);
        HttpRequest.getArea(params,getToken(), new ResponseCallback() {
            @Override
            public void onSuccess(Object responseObj) {
                //需要转化为实体对象
                Gson gson = new GsonBuilder().serializeNulls().create();
                try {
                    JSONObject result = new JSONObject(responseObj.toString());
                    if (type == 0) {
                        List<NewProvinceBean> titleList = gson.fromJson(result.getJSONArray("data").toString(),
                                new TypeToken<List<NewProvinceBean>>() {
                                }.getType());
                        provinceView.setTitleData(titleList);
                    } else if (type == 1) {
                        List<NewCityBean> titleList = gson.fromJson(result.getJSONArray("data").toString(),
                                new TypeToken<List<NewCityBean>>() {
                                }.getType());
                        provinceView.setTwoLabelData(titleList);
                    }
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

    /**
     * 省市区回调接口
     *
     * @param provinceTv   选择的省市区text
     * @param provinceCode
     * @param cityCode
     */
    public void setSelectAdrCallback(String provinceTv, String provinceCode, String cityCode) {
        province_tv.setText(provinceTv);
        BankProvince = provinceCode;
        BankCity = cityCode;
    }

    private void getBanks(String bankName) {
        if (TextUtils.isEmpty(bankName)) {
            return;
        }
        RequestParams params = new RequestParams();
        params.put("page", "1");
        params.put("pageSize", "100");
        params.put("bankName", bankName);
        HttpRequest.getBanks(params,getToken(), new ResponseCallback() {
            @Override
            public void onSuccess(Object responseObj) {
                //需要转化为实体对象
                Gson gson = new GsonBuilder().serializeNulls().create();
                try {
                    JSONObject result = new JSONObject(responseObj.toString());
                    JSONObject data = result.getJSONObject("data");

                    int totalCount = result.getJSONObject("data").getInt("totalCount");
                    if (totalCount > 0) {
                        bankNameBeanList = gson.fromJson(data.getJSONArray("list").toString(),
                                new TypeToken<List<BankNameBean>>() {
                                }.getType());
                    }

                    bankNameAdapter = new BankNameAdapter(AddMerchantsActivity3.this, bankNameBeanList);
                    bankNameAdapter.notifyDataSetChanged();
                    addListView.setAdapter(bankNameAdapter);

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

    //银行编码监听
    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (!TextUtils.isEmpty(bank_code_ed.getText().toString().trim()) && isBank)
            getBanks(bank_code_ed.getText().toString().trim());
        }
    };

    //银行支行
    TextWatcher zhiTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (!TextUtils.isEmpty(bankSite_tv.getText().toString().trim()) && isBankSite)
            getBranchs(bankSite_tv.getText().toString().trim());
        }
    };

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()) {
            case R.id.addListView:
                isBank = false;
                bank_code_ed.setText(bankNameBeanList.get(i).getName());
                bankCode = bankNameBeanList.get(i).getCode();
                bankNameAdapter.clear();
                isBank = true;
                break;
            case R.id.zhListView:
                isBankSite = false;
                bankSite_tv.setText(zhiBankList.get(i).getName());
                bankSite = zhiBankList.get(i).getName();
                bankSiteCode = zhiBankList.get(i).getCode();
                zhiBankNameAdapter.clear();
                isBankSite = true;
                break;
        }
    }

    private void getBranchs(String bankSiteName) {
        RequestParams params = new RequestParams();
        params.put("page", "1");
        params.put("pageSize", "100");
        params.put("provinceCode", BankProvince);
        params.put("cityCode", BankCity);
        params.put("bankCode", bankCode);
        params.put("bankSiteName", bankSiteName);
        HttpRequest.getBranchs(params, getToken(),new ResponseCallback() {
            @Override
            public void onSuccess(Object responseObj) {
                //需要转化为实体对象
                Gson gson = new GsonBuilder().serializeNulls().create();
                try {
                    JSONObject result = new JSONObject(responseObj.toString());
                    JSONObject data = result.getJSONObject("data");
                    int totalCount = result.getJSONObject("data").getInt("totalCount");
                    if (totalCount > 0) {
                        zhiBankList = gson.fromJson(data.getJSONArray("list").toString(),
                                new TypeToken<List<BankNameBean>>() {
                                }.getType());
                    }
                    zhiBankNameAdapter = new ZhiBankNameAdapter(AddMerchantsActivity3.this, zhiBankList);
                    zhiBankNameAdapter.notifyDataSetChanged();
                    zhListView.setAdapter(zhiBankNameAdapter);

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


}
