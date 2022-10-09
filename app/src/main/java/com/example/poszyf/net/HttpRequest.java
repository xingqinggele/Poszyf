package com.example.poszyf.net;

import com.example.poszyf.bean.OrderBean;

import java.util.List;

/**
 * 作者：zb.
 * <p>
 * 时间：On 2019-05-05.
 * <p>
 * 描述：所有的请求接口
 */
public class HttpRequest {

    /**
     * 登录接口
     *
     * @param params
     * @param callback
     */
    public static void getLogin(RequestParams params,  ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "login", params, callback, null);
    }

    /**
     * 首页数据接口
     *
     * @param params
     * @param callback
     */
    public static void getHomeDate(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/homepage/data", params, callback, null);
    }

    /**
     * 获取用户终端统计数据
     *
     * @param params
     * @param callback
     */
    public static void getEquipment(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/terminal/counts",params,callback, null);
    }

    /**
     * 获取用户终端列表
     *
     * @param params
     * @param callback
     */
    public static void getEquipmentList(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/terminal/list", params,callback, null);
    }

    /**
     * 获取用户划拨任务
     *
     * @param params
     * @param callback
     */
    public static void getTypeList(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/terminal/pos/type/list", params,callback, null);
    }
    /**
     * 终端查询筛选条件
     *
     * @param params
     * @param callback
     */
    public static void getConditions(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/terminal/filter/conditions", params,callback, null);
    }

    /**
     * 获取直接子商户字典
     *
     * @param params
     * @param callback
     */
    public static void getMerchantsList(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/merchant/direct/child/dict", params,callback, null);
    }

    /**
     * 终端划拔回调操作
     *
     * @param params
     * @param callback
     */
    public static void updPosListFrom(RequestParams params, int[] data, ResponseCallback callback) {
        RequestMode.postRequest2(Urls.commUrls + "pos/api/v2/terminal/operations", params,data, callback, null);
    }

    /**
     * 区间查询用户终端列表
     *
     * @param params
     * @param callback
     */
    public static void updPosintervalList(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/terminal/interval/list", params,callback, null);
    }

    /**
     * 获取我的伙伴列表
     *
     * @param params
     * @param callback
     */
    public static void updMypartnerList(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/merchant/mypartner/list", params,callback, null);
    }

    /**
     * 获取我的伙伴详情
     *
     * @param params
     * @param callback
     */
    public static void updMypartnerDetail(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/merchant/mypartner/detail", params,callback, null);
    }

    /**
     * 终端划拔回调简要记录
     *
     * @param params
     * @param callback
     */
    public static void getRecords(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/terminal/operations/brief/records", params,callback, null);
    }

    /**
     * 获取省市区
     *
     * @param params
     * @param callback
     */
    public static void getCity(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/common/dictdata/list", params,callback, null);
    }

    /**
     * 报件获取省市区
     *
     * @param params
     * @param callback
     */
    public static void getCityB(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "noauth/posv1/code", params,callback, null);
    }

    /**
     * 商户入驻，实名认证
     *
     * @param params
     * @param callback
     */
    public static void getIn(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/merchant/register", params,callback, null);
    }

    /**
     * 获取验证码接口
     *
     * @param params
     * @param callback
     */
    public static void getRegister_Code(RequestParams params, ResponseCallback callback) {
        RequestMode.getRequest(Urls.commUrls + "pos/api/v2/common/verifyCode/sender", params,callback, null);
    }

    /**
     * 获取验证码接口
     *
     * @param params
     * @param callback
     */
    public static void getRegister_Code1(RequestParams params, ResponseCallback callback) {
        RequestMode.getRequest(Urls.commUrls + "noauth/verifyCode/sender", params,callback, null);
    }

    /**
     * 获取商户入驻信息
     *
     * @param params
     * @param callback
     */
    public static void getCurrent(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/merchant/current", params,callback, null);
    }

    /**
     * 获取我的银行卡信息
     *
     * @param params
     * @param callback
     */
    public static void getBankInfo(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/bankcard/homepage", params,callback, null);
    }

    /**
     * 银行卡变更
     *
     * @param params
     * @param callback
     */
    public static void getBankChange(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/bankcard/alter", params,callback, null);
    }

    /**
     * 修改密码
     *
     * @param params
     * @param callback
     */
    public static void getPass(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/merchant/password/reset", params,callback, null);
    }

    /**
     * 忘记 密码
     *
     * @param params
     * @param callback
     */
    public static void getPass1(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "noauth/password/reset", params,callback, null);
    }

    /**
     * 邀请伙伴
     *
     * @param params
     * @param callback
     */
    public static void getInvitationPartner(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/merchant/mypartner/invite", params,callback, null);
    }

    /**
     * 支付密码，验证是否身份证后六位相同
     *
     * @param params
     * @param callback
     */
    public static void getPay_password1(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/merchant/payment/validate", params,callback, null);
    }

    /**
     * 支付密码，设置
     *
     * @param params
     * @param callback
     */
    public static void getPay_password2(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/merchant/payment/updatePassword", params,callback, null);
    }

    /**
     * 支付密码，修改，比对原密码是否正确
     *
     * @param params
     * @param callback
     */
    public static void getPay_ModifyPassword(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/merchant/payment/validatePassUpdate", params,callback, null);
    }

    /**
     * 更换头像
     *
     * @param params
     * @param callback
     */
    public static void getUpdatePortrait(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/merchant/updatePortrait", params,callback, null);
    }

    /**
     * 获取数据页，数据
     *
     * @param params
     * @param callback
     */
    public static void getTransAmount(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/homepage/transAmountStatistics", params,callback, null);
    }

    /**
     * 钱包余额
     *
     * @param params
     * @param callback
     */
    public static void getBalanceOf(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/wallet/homepage", params,callback, null);
    }

    /**
     * 查询是否签约接口
     *
     * @param params
     * @param callback
     */
    public static void getSigning(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "xinLong/signed/withdrawal/echoReceiver", params,callback, null);
    }

    /**
     * 签约接口
     *
     * @param params
     * @param callback
     */
    public static void getSigningTo(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "xinLong/signed/withdrawal/submitCreateUser", params,callback, null);
    }

    /**
     * 账单列表
     *
     * @param params
     * @param callback
     */
    public static void getBillList(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/wallet/bill/list", params,callback, null);
    }

    /**
     * 账单列表详情
     *
     * @param params
     * @param callback
     */
    public static void getBillDetails(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/message/billDetails", params,callback, null);
    }


    /**
     * 账单类型
     *
     * @param params
     * @param callback
     */
    public static void getBillType(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/wallet/bill/type", params,callback, null);
    }

    /**
     * 消息列表
     *
     * @param params
     * @param callback
     */
    public static void getMessageList(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/message/list", params,callback, null);
    }

    /**
     * 消息列表详情
     *
     * @param params
     * @param callback
     */
    public static void getMessageDetail(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/message/msgTypeDetail", params,callback, null);
    }


    /**
     * 我的积分首页数据
     *
     * @param params
     * @param callback
     */
    public static void getTotal_score(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/integral/homepage", params,callback, null);
    }


    /**
     * 我的积分详细列表
     *
     * @param params
     * @param callback
     */
    public static void getTotal_scoreList(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/integral/list", params,callback, null);
    }

    /**
     * 提交兑换积分订单
     *
     * @param params
     * @param callback
     */
    public static void getSubmit_orders(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/integral/order", params,callback, null);
    }
    /**
     * 提交兑换积分订单
     *
     * @param params
     * @param callback
     */
    public static void getSubmit_orderList(RequestParams params, List<OrderBean> list,ResponseCallback callback) {
        RequestMode.postRequest3(Urls.commUrls + "pos/api/v2/integral/cart/order", params,list, callback, null);
    }




    /**
     * 获取订单列表
     *
     * @param params
     * @param callback
     */
    public static void getOrderList(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/order/list/type", params,callback, null);
    }


    /**
     * 获取订单列表详情
     *
     * @param params
     * @param callback
     */
    public static void getOrderList_detail(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/order/detail/type", params,callback, null);
    }

    /**
     * 判断身份证号是否唯一
     *
     * @param params
     * @param callback
     */
    public static void getIsIdNumber(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/merchant/idCordIsExist", params,callback, null);
    }


    /**
     * 获取我的商户列表
     *
     * @param params
     * @param callback
     */
    public static void getMeMerchants_list(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v1/merchant/my/merch", params,callback, null);
    }



    /**
     * 获取我的商户列表详情 -- 设备
     *
     * @param params
     * @param callback
     */
    public static void getMeMerchants_detailEquipment(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/merchant/queryEquipmentInfo", params,callback, null);
    }


    /**
     * 获取我的商户列表详情 -- 交易
     *
     * @param params
     * @param callback
     */
    public static void getMeMerchants_detailTrading(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/merchant/queryMerchantDealInfo", params,callback, null);
    }


    /**
     * 获取提现界面数据
     *
     * @param params
     * @param callback
     */
    public static void getPayData(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/payment/toCashOut", params,callback, null);
    }

    /**
     * 核对密码接口
     *
     * @param params
     * @param callback
     */
    public static void getPayPassWord(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/payment/confirmPassword", params,callback, null);
    }

    /**
     * 提交提现接口
     *
     * @param params
     * @param callback
     */
    public static void getNewPayWithdrawal(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "xinLong/signed/withdrawal/submitGrantDetail", params,callback, null);
    }




    /**
     * 提交提现接口
     *
     * @param params
     * @param callback
     */
    public static void getPayWithdrawal(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/payment/doCashOut", params,callback, null);
    }


    /**
     * 个人业绩 ----- 日交易量、月交易量
     *
     * @param params
     * @param callback
     */
    public static void getDayAmount(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/merchant/getMerchantTransInfo", params,callback, null);
    }

    /**
     * 团队业绩 -----日交易量、月交易量
     *
     * @param params
     * @param callback
     */
    public static void getManthAmount(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/merchant/getTeamTransInfo", params,callback, null);
    }

      /**
     * 总业绩 -----日交易量、月交易量
     *
     * @param params
     * @param callback
     */
    public static void getTotalAmount(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/merchant/getAllTransInfo", params,callback, null);
    }



    /**
     * 我的收益
     *
     * @param params
     * @param callback
     */
    public static void getEarnings(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/message/earnings", params,callback, null);
    }


    /**
     * 请求可转移的伙伴
     *
     * @param params
     * @param callback
     */
    public static void getTransferList(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/transfer/list", params,callback, null);
    }

    /**
     * 转移商户
     *
     * @param params
     * @param callback
     */
    public static void getTransferMerchants(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/transfer/updateTransfer", params,callback, null);
    }

    /**
     * 请求交易线形图数据
     *
     * @param params
     * @param callback
     */
    public static void getTradingDataList(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/merchant/mypartner/amount/statistical", params,callback, null);
    }


    /**
     * 请求设备线形图数据
     *
     * @param params
     * @param callback
     */
    public static void getEquipmentDataList(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/merchant/mypartner/machines/statistical", params,callback, null);
    }


    /**
     * 请求设备线形图数据
     *
     * @param params
     * @param callback
     */
    public static void getObtainSuperior(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/homepage/my/referrer", params,callback, null);
    }


    /**
     * 请求设备线形图数据
     *
     * @param params
     * @param callback
     */
    public static void getTeamPersonList(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/integral/teamList", params,callback, null);
    }

    /**
     * 新增收货地址
     *
     * @param params
     * @param callback
     */
    public static void getSaveAddress(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/address/save", params,callback, null);
    }

    /**
     * 收货地址列表
     *
     * @param params
     * @param callback
     */
    public static void getAddressList(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/address/list", params,callback, null);
    }

    /**
     * 收货地址修改默认
     *
     * @param params
     * @param callback
     */
    public static void getAddressType(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/address/getType", params,callback, null);
    }

    /**
     * 删除收货地址
     *
     * @param params
     * @param callback
     */
    public static void DeleteAddress(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/address/del", params,callback, null);
    }

    /**
     * 编辑收货地址
     *
     * @param params
     * @param callback
     */
    public static void EditorAddress(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/address/edit", params,callback, null);
    }



    /**
     * 获取一条收货地址
     *
     * @param params
     * @param callback
     */
    public static void orderAddress(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/integral/orderAddress", params,callback, null);
    }


    /**
     * 广告位
     *
     * @param params
     * @param callback
     */
    public static void getAdvertising(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/advertising/getAdvertising", params,callback, null);
    }

    /**
     * 排行榜
     *
     * @param params
     * @param callback
     */
    public static void getRanking(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/homepage/ranking", params,callback, null);
    }

    /**
     * 我的
     *
     * @param params
     * @param callback
     */
    public static void getMeData(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/homepage/dataInfo", params,callback, null);
    }


    /**
     * 请求回调设备列表
     *
     * @param params
     * @param callback
     */
    public static void getPosList(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/terminal/posList", params,callback, null);
    }

    /**
     * 积分设备列表
     *
     * @param params
     * @param callback
     */
    public static void getMostList(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/integral/get/posBrandType", params,callback, null);
    }

  /**
     * 获取设备类型
     *
     * @param params
     * @param callback
     */
    public static void getPosType(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/integral/get/posType", params,callback, null);
    }
    /**
     * 终端查询设备类型
     *
     * @param params
     * @param callback
     */
    public static void getPosBrandTypeAll(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/integral/get/posBrandTypeAll", params,callback, null);
    }


 /**
     * 获订单类型
     *
     * @param params
     * @param callback
     */
    public static void getOrderType(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/order/status", params,callback, null);
    }



 /**
     *
     *
     * @param params
     * @param callback
     */
    public static void getTransfer(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/terminal/pos/transfer", params,callback, null);
    }
    /**
     * 加入购物车
     *
     * @param params
     * @param callback
     */
    public static void getTrolley(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/shop/save/trolley", params,callback, null);
    }

 /**
     * 购物车列表
     *
     * @param params
     * @param callback
     */
    public static void getCarList(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/shop/cart/list", params,callback, null);
    }

    /**
     * 删除购物车商品
     *
     * @param params
     * @param callback
     */
    public static void getDeleteList(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/shop/del/comm", params,callback, null);
    }


    /**
     * 商户报件类型选择
     *
     * @param params
     * @param callback
     */
    public static void getAddMerchantMessageType(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "noauth/posv1/AddMerchantMessageType", params,callback, null);
    }
 /**
     * 商户报件查询
     *
     * @param params
     * @param callback
     */
    public static void getEntry(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v1/merchant/query/entry", params,callback, null);
    }

/**
     * 商户报件上传
     *
     * @param params
     * @param callback
     */
    public static void getOperation(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "noauth/posv1/Operation", params,callback, null);
    }


    /**
     * 新的商户报件上传
     *
     * @param params
     * @param callback
     */
    public static void getNewOperation(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "noauth/posv1/insertMerchantEntry", params, callback, null);
    }
    /**
     * 商户报件修改
     *
     * @param params
     * @param callback
     */
    public static void getUpdate(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v1/merchant/update", params,callback, null);
    }
    /**
     * 获取二维码生成的选项接口
     *
     * @param params
     * @param callback
     */
    public static void getBizTerminalRateList(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "noauth/posv1terminallist/getBizTerminalRateList", params,callback, null);
    }

    /**
     * 新增二维码生成接口
     *
     * @param params
     * @param callback
     */
    public static void postOpenAccount(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "noauth/posv1terminallist/postOpenAccount", params,callback, null);
    }
    /**
     * 查看二维码生成接口
     *
     * @param params
     * @param callback
     */
    public static void getBizTerminalRateLists(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "noauth/posv1terminallist/getBizTerminalRateLists", params,callback, null);
    }

    /**
     * 修改二维码生成接口
     *
     * @param params
     * @param callback
     */
    public static void putOpenAccount(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "noauth/posv1terminallist/putOpenAccount", params,callback, null);
    }


    /**
     * 修改伙伴费率生成接口
     *
     * @param params
     * @param callback
     */
    public static void putModifyRate(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/merchant/modifyRate", params,callback, null);
    }


 /**
     * 交易记录列表
     *
     * @param params
     * @param callback
     */
    public static void putRecordList(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "merchant/transactions/record", params,callback, null);
    }

 /**
     * 交易记录列表
     *
     * @param params
     * @param callback
     */
    public static void getv1terminallist(RequestParams params, ResponseCallback callback) {
        RequestMode.getRequest(Urls.commUrls + "noauth/posv1terminallist", params,callback, null);
    }


    /**
     * 获取类目
     *
     * @param params
     * @param callback
     */
    public static void getEchoServer(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "noauth/echoServer", params, callback, null);
    }

    /**
     * 提醒后台商户报件
     *
     * @param params
     * @param callback
     */
    public static void getNewOutOperation(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "noauth/posv1/new/Operation", params, callback, null);
    }

    /**
     *伙伴费率 回显
     *
     * @param params
     * @param callback
     */
    public static void getParntEchoServer(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v2/merchant/echoRate", params, callback, null);
    }

    /**
     *商戶详情
     *
     * @param params
     * @param callback
     */
    public static void getQueryMyCommercialTenant(RequestParams params, ResponseCallback callback) {
        RequestMode.postRequest(Urls.commUrls + "pos/api/v1/merchant/queryMyCommercialTenant", params, callback, null);
    }

}
