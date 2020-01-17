package com.example.green.net;


import com.example.green.bean.MyOrderbean;
import com.example.green.bean.classify.AllClassifyListbean;
import com.example.green.bean.classify.RightClassifyListbean;
import com.example.green.bean.homepage.DetailsDatabean;
import com.example.green.bean.homepage.GoodsListbean;
import com.example.green.bean.homepage.HomePgaeList;
import com.example.green.bean.homepage.HotSearchKeyListbean;
import com.example.green.bean.homepage.SearchListbean;
import com.example.green.bean.homepage.SystemMessageListbean;
import com.example.green.bean.homepage.Versionbean;
import com.example.green.bean.mine.Addsitebean;
import com.example.green.bean.mine.AuthInfobean;
import com.example.green.bean.mine.AutonymBean;
import com.example.green.bean.mine.ChangeOrderStatebean;
import com.example.green.bean.mine.ChangePayPswbean;
import com.example.green.bean.mine.CollegeDetailsInfo;
import com.example.green.bean.mine.CollegeListbean;
import com.example.green.bean.mine.EditMineInfobean;
import com.example.green.bean.mine.LogisticsInfobean;
import com.example.green.bean.mine.Logoutbean;
import com.example.green.bean.mine.MineInfobean;
import com.example.green.bean.mine.OrderDetailsInfobean;
import com.example.green.bean.mine.PictureUploadBean;
import com.example.green.bean.mine.QueryPropertybean;
import com.example.green.bean.mine.ShoppingAddressListbean;
import com.example.green.bean.mine.TeamBean;
import com.example.green.bean.mine.WalletInfobean;
import com.example.green.bean.mine.wallet.DealCodeListbean;
import com.example.green.bean.mine.wallet.GraphListbean;
import com.example.green.bean.mine.wallet.PointListbean;
import com.example.green.bean.mine.wallet.PointTransformbean;
import com.example.green.bean.mine.wallet.StoredValueListbean;
import com.example.green.bean.mine.wallet.WithdrawAppforbean;
import com.example.green.bean.mine.wallet.WithdrawRecordListbean;
import com.example.green.bean.pay.AcquireOrderInfobean;
import com.example.green.bean.pay.AcquirePayCodebean;
import com.example.green.bean.pay.CreateOrderbean;
import com.example.green.bean.pay.GetMarketWheelbean;
import com.example.green.bean.pay.PayOrderInfobean;
import com.example.green.bean.pay.RemittanceInfobean;
import com.example.green.bean.pay.ShoppingInfobean;
import com.example.green.bean.pay.ToSurpriseInfo;
import com.example.green.bean.register.AccquireSmsbean;
import com.example.green.bean.register.ModificationPswbean;
import com.example.green.bean.register.RegisterDatabean;
import com.example.green.bean.shopping.AddCartInfobean;
import com.example.green.bean.shopping.ChangeGoodsNumInfobean;
import com.example.green.bean.shopping.ShoppingtrolleyListbean;
import com.example.green.bean.store.AllStoreListbean;
import com.example.green.bean.store.RandomRecListbean;
import com.example.green.bean.store.StoreClassListbean;
import com.example.green.bean.store.StoreInfoListbean;
import com.example.green.bean.store.StoreListbean;
import com.example.green.bean.store.StoreRecommendListbean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface INetService {

    /*
     * 获取版本号
     * https://shop.bayi-shop.com/mobile/Version/GetVersion
     * */
    @GET("Version/GetVersion")
    Observable<Versionbean> getVersionbean(@Query("version_num") String version_num,
                                           @Query("type") String type);

    /*
     * 商品搜索
     * https://shop.bayi-shop.com/mobile/goods/goods_list
     * */
    @GET("goods/goods_list")
    Observable<SearchListbean> getSearchList(@Query("keyword") String keyword,
                                             @Query("page") int page,
                                             @Query("key") int key,
                                             @Query("gc_id") String gcId);

    /*
     * 获取首页数据
     * https://shop.bayi-shop.com/mobile/Index/index
     * */
    @GET("Index/index")
    Observable<HomePgaeList> getHomeListbean();

    /*
     * 商品推荐
     * https://shop.bayi-shop.com/mobile/Index/getCommendGoods
     * */
    @GET("Index/getCommendGoods")
    Observable<GoodsListbean> getGoodsList(@Query("limit") int size,
                                           @Query("page") int index);

    /*
     * 搜索页面热门搜索词
     * https://shop.bayi-shop.com/mobile/index/search_key_list
     * */
    @GET("index/search_key_list")
    Observable<HotSearchKeyListbean> getHotSearchKeyList();

    /*
     * 商品详情
     * https://shop.bayi-shop.com/mobile/goods/goods_detail
     * */
    @GET("goods/goods_detail")
    Observable<DetailsDatabean> getGoodsDetailsList(@Query("goods_id") String goods_id);

    /*
     * 获取消息列表
     * https://shop.bayi-shop.com/index.php/mobile/Membermessage/systemmsg
     * */
    @POST("Membermessage/systemmsg")
    @FormUrlEncoded
    Observable<SystemMessageListbean> getSystemMessageList(@Field("key") String key,
                                                           @Field("page") int page);

    /*
     * 分类首页
     * https://shop.bayi-shop.com/mobile/goodsclass/index
     * */
    @GET("goodsclass/index")
    Observable<AllClassifyListbean> getClassifyList();

    /*
     * 分类右侧
     * https://shop.bayi-shop.com/mobile/goodsclass/get_child_all.html
     * */
    @GET("goodsclass/get_child_all.html")
    Observable<RightClassifyListbean> getRightClassifyList(@Query("gc_id") int gc_id);

    /*
     * 店铺列表
     * https://shop.bayi-shop.com/mobile/Storelist/index
     * */
    @GET("Storelist/index")
    Observable<StoreListbean> getStoreList(@Query("cate_id") String cate_id,
                                           @Query("page") int index);

    /*
     * 店铺首页
     * https://shop.bayi-shop.com/mobile/Store/store_info
     * */
    @GET("Store/store_info")
    Observable<StoreInfoListbean> getStoreInfoList(@Query("store_id") String store_id);

    /*
     * 店铺首页推荐商品
     * https://shop.bayi-shop.com/mobile/Store/GetStoreCommentGoods
     * */
    @GET("Store/GetStoreCommentGoods")
    Observable<StoreRecommendListbean> getStoreRecommendList(@Query("store_id") String store_id,
                                                             @Query("page") int page);

    /*
     * 店铺分类
     * https://shop.bayi-shop.com/mobile/storelist/getStoreClassList
     * */
    @GET("storelist/getStoreClassList")
    Observable<StoreClassListbean> getStoreClassList();

    /*
     * 获取店铺商品
     * https://shop.bayi-shop.com/mobile/Store/store_goods
     * */
    @GET("Store/store_goods")
    Observable<AllStoreListbean> getAllStoreList(@Query("store_id") String store_id,
                                                 @Query("page") int page);

    /*
     * 我的页面
     * https://shop.bayi-shop.com/mobile/member/index
     * */
    @GET("member/index")
    Observable<MineInfobean> getMineInfo(@Query("key") String key);

    /*
     * 收货地址列表
     * https://shop.bayi-shop.com/mobile/Memberaddress/address_list
     * */
    @GET("Memberaddress/address_list")
    Observable<ShoppingAddressListbean> getShoppingAddressList(@Query("key") String key);

    /*
     * 添加收货地址
     * https://shop.bayi-shop.com/mobile/Memberaddress/address_add
     * */
    @POST("Memberaddress/address_add")
    @FormUrlEncoded
    Observable<Addsitebean> getAddSite(@Field("true_name") String true_name,
                                       @Field("mob_phone") String mob_phone,
                                       @Field("city_id") String city_id,
                                       @Field("area_id") String area_id,
                                       @Field("address") String address,
                                       @Field("area_info") String area_info,
                                       @Field("is_default") String is_default,
                                       @Field("key") String token);


    /*
     * 编辑收货地址
     * https://shop.bayi-shop.com/mobile/Memberaddress/address_edit
     * */
    @POST("Memberaddress/address_edit")
    @FormUrlEncoded
    Observable<Logoutbean> getEditorAddressbean(@Field("key") String token,
                                                @Field("true_name") String true_name,
                                                @Field("mob_phone") String mob_phone,
                                                @Field("city_id") String city_id,
                                                @Field("area_id") String area_id,
                                                @Field("address") String address,
                                                @Field("area_info") String area_info,
                                                @Field("is_default") String is_default,
                                                @Field("address_id") String address_id);

    /*
     * 删除收货地址
     * https://shop.bayi-shop.com/mobile/Memberaddress/address_del
     * */
    @POST("Memberaddress/address_del")
    @FormUrlEncoded
    Observable<Logoutbean> getDeleteAddressbean(@Field("key") String key,
                                                @Field("address_id") String address_id);

    /*
     * 用户头像上传
     * https://shop.bayi-shop.com/mobile/member/upload
     * */
    @Multipart
    @POST(value = "file/images")
    Observable<PictureUploadBean> uploadPicture(@Part MultipartBody.Part body, @Part MultipartBody.Part token);

    /*
     * 个人资料编辑
     * https://shop.bayi-shop.com/mobile/member/my_edit
     * */
    @POST("member/my_edit")
    @FormUrlEncoded
    Observable<EditMineInfobean> getEditMineInfo(@Field("key") String key,
                                                 @Field("commit") int commit,
                                                 @Field("member_name") String member_name,
                                                 @Field("member_sex") String member_sex,
                                                 @Field("member_email") String member_email);

    /*
     * 实名认证
     * https://shop.bayi-shop.com/mobile/memberauth/auth
     * */
    @Multipart
    @POST("memberauth/auth")
    Observable<AutonymBean> getAutonym(@Part("member_id") RequestBody member_id,
                                       @Part("username") RequestBody username,
                                       @Part("idcard") RequestBody idcard,
                                       @Part("member_bankname") RequestBody member_bankname,
                                       @Part("member_bankcard") RequestBody member_bankcard,
                                       @Part MultipartBody.Part member_idcard_image1,// 手持身份证照片
                                       @Part MultipartBody.Part member_idcard_image2,// 身份证正面
                                       @Part MultipartBody.Part member_idcard_image3,// 身份证反面
                                       @Part("member_provinceid") RequestBody member_provinceid,
                                       @Part("member_cityid") RequestBody member_cityid,
                                       @Part("member_areaid") RequestBody member_areaid,
                                       @Part("member_areainfo") RequestBody member_areainfo,
                                       @Part("commit") RequestBody commit);

    /*
     * 实名认证(二次)
     * https://shop.bayi-shop.com/mobile/memberauth/auth
     * */
    @POST("memberauth/auth")
    @FormUrlEncoded
    Observable<AutonymBean> getAutonymtwice(@Field("member_id") int member_id,
                                            @Field("username") String username,
                                            @Field("idcard") String idcard,
                                            @Field("member_bankname") String member_bankname,
                                            @Field("member_bankcard") String member_bankcard,
                                            @Field("member_provinceid") String member_provinceid,
                                            @Field("member_cityid") String member_cityid,
                                            @Field("member_areaid") String member_areaid,
                                            @Field("member_areainfo") String member_areainfo,
                                            @Field("commit") int commit);

    /*
     * 获取实名认证信息
     * https://shop.bayi-shop.com/mobile/memberauth/auth
     * */
    @POST("memberauth/auth")
    @FormUrlEncoded
    Observable<AuthInfobean> getAuthInfo(@Field("member_id") String member_id);

    /*
     * 商学院
     * https://shop.bayi-shop.com/mobile/college/college
     * article_type: 0-文章 1-视频
     * */
    @GET("college/college")
    Observable<CollegeListbean> getCollegeList(@Query("article_type") int type);

    /*
     * 商学院详情页
     * https://shop.bayi-shop.com/mobile/college/detail
     * */
    @GET("college/detail")
    Observable<CollegeDetailsInfo> getCollegeDetails(@Query("article_id") String article_id);


    /*
     * 注册
     * https://shop.bayi-shop.com/mobile/login/register
     * */
    @POST("login/register")
    @FormUrlEncoded
    Observable<RegisterDatabean> getRegisterbean(@Field("username") String username,
                                                 @Field("password") String password,
                                                 @Field("password_confirm") String password_confirm,
                                                 @Field("client") String client,
                                                 @Field("inviter_code") String inviter_code,
                                                 @Field("sms_captcha") String sms_captcha,
                                                 @Field("log_type") int log_type);

    /*
     * 验证码发送
     * https://shop.bayi-shop.com/mobile/Connect/get_sms_captcha
     * */
    @POST("Connect/get_sms_captcha")
    @FormUrlEncoded
    Observable<AccquireSmsbean> getAccquirebean(@Field("member_mobile") String member_mobile,
                                                @Field("type") int type);

    /*
     * 登陆密码修改-修改登陆密码
     * https://shop.bayi-shop.com/mobile/Memberaccount/modify_password_step4
     * */
    @POST("Memberaccount/modify_password_step4")
    @FormUrlEncoded
    Observable<ModificationPswbean> getModificationbean(@Field("key") String key,
                                                        @Field("auth_code") String auth_code,
                                                        @Field("password") String password,
                                                        @Field("confirm_password") String confirm_password,
                                                        @Field("mobile_key") String mobile_key);

    /*
     * 支付密码修改--修改支付密码
     * https://shop.bayi-shop.com/mobile/Memberaccount/modify_paypwd_step4
     * */
    @POST("Memberaccount/modify_paypwd_step4")
    @FormUrlEncoded
    Observable<ChangePayPswbean> getChangePaypswbean(@Field("key") String key,
                                                     @Field("auth_code") String auth_code,
                                                     @Field("password") String password,
                                                     @Field("confirm_password") String confirm_password);

    /*
     * 登录
     * https://shop.bayi-shop.com/mobile/login/index
     * */
    @POST("login/index")
    @FormUrlEncoded
    Observable<RegisterDatabean> getLoginbean(@Field("username") String username,
                                              @Field("password") String password,
                                              @Field("client") String client);

    /*
     * 退出登录
     * https://shop.bayi-shop.com/mobile/logout/index
     * */
    @POST("logout/index")
    @FormUrlEncoded
    Observable<Logoutbean> getLogOutbean(@Field("username") String username,
                                         @Field("key") String key,
                                         @Field("client") String client);

    /*
     * 购买商品--第一步，生成购物信息
     * https://shop.bayi-shop.com/index.php/mobile/Memberbuy/buy_step1
     * */
    @POST("Memberbuy/buy_step1")
    @FormUrlEncoded
    Observable<ShoppingInfobean> getShoppingInfo(@Field("key") String key,
                                                 @Field("cart_id") String cart_id,
                                                 @Field("ifcart") String ifcart);

    /*
     * 购买商品--第二步，生成订单
     * https://shop.bayi-shop.com/index.php/mobile/Memberbuy/buy_step2
     * */
    @POST("Memberbuy/buy_step2")
    @FormUrlEncoded
    Observable<CreateOrderbean> getCreateOrderbean(@Field("key") String key,
                                                   @Field("ifcart") String ifcart,
                                                   @Field("cart_id") String cart_id,
                                                   @Field("address_id") String address_id,
                                                   @Field("vat_hash") String vat_hash,
                                                   @Field("offpay_hash") String offpay_hash,
                                                   @Field("offpay_hash_batch") String offpay_hash_batch,
                                                   @Field("pay_name") String pay_name,
                                                   @Field("invoice_id") String invoice_id,
                                                   @Field("voucher") String voucher);

    /*
     * 购买商品--第三步，获取订单信息
     * https://shop.bayi-shop.com/index.php/mobile/memberbuy/pay
     * */
    @POST("memberbuy/pay")
    @FormUrlEncoded
    Observable<AcquireOrderInfobean> getOrderInfobean(@Field("key") String key,
                                                      @Field("pay_sn") String pay_sn);

    /*
     * 购买商品--第四步，购买
     * https://shop.bayi-shop.com/mobile/Memberpayment/pay_new
     * */
    @POST("Memberpayment/pay_new")
    @FormUrlEncoded
    Observable<PayOrderInfobean> getPayOrderInfobean(@Field("key") String key,
                                                     @Field("pay_sn") String pay_sn,
                                                     @Field("password") String password,
                                                     @Field("pd_pay") String pd_pay,
                                                     @Field("payment_code") String payment_code);


    /*
     * 转盘抽奖页面
     * https://shop.bayi-shop.com/index.php/mobile/Membermarket/GetMarketWheel
     * */
    @POST("Membermarket/GetMarketWheel")
    @FormUrlEncoded
    Observable<GetMarketWheelbean> getMarketWheelbean(@Field("key") String key,
                                                      @Field("marketmanage_id") String marketmanage_id,
                                                      @Field("pay_sn") String pay_sn);

    /*
     * 开始抽奖
     * https://shop.bayi-shop.com/index.php/mobile/Membermarket/ToSurprise
     * */
    @POST("Membermarket/ToSurprise")
    @FormUrlEncoded
    Observable<ToSurpriseInfo> getSurprise(@Field("key") String key,
                                           @Field("marketmanage_id") String marketmanage_id);


    /*
     * 支付成功后获取随机商品
     * https://shop.bayi-shop.com/mobile/Goods/getRandGoods
     * */
    @GET("Goods/getRandGoods")
    Observable<RandomRecListbean> getRandomList();


    /**
     * 订单列表
     *
     * @param token
     * @param type
     * @param page
     * @param pagesize
     * @return
     */
    @POST("Memberorder/order_list")
    @FormUrlEncoded
    Observable<MyOrderbean> getGoodsOrder(@Field("key") String token,
                                          @Field("state_type") String type,
                                          @Field("page") int page,
                                          @Field("pagesize") int pagesize);

    /*
     * 修改订单状态
     * https://shop.bayi-shop.com/mobile/memberorder/change_state
     * */
    @POST("memberorder/change_state")
    @FormUrlEncoded
    Observable<ChangeOrderStatebean> getChangeOrderState(@Field("key") String key,
                                                         @Field("state_type") String state_type,
                                                         @Field("order_id") String order_id);

    /*
     * 订单详情查看
     * https://shop.bayi-shop.com/index.php/mobile/Memberorder/order_info
     * */
    @POST("Memberorder/order_info")
    @FormUrlEncoded
    Observable<OrderDetailsInfobean> getOrderDetailsInfo(@Field("key") String key,
                                                         @Field("order_id") String order_id);


    /*
     * 获取物流信息
     * https://shop.bayi-shop.com/mobile/Memberorder/get_express
     * */
    @POST("Memberorder/get_express")
    @FormUrlEncoded
    Observable<LogisticsInfobean> getLogisticsInfo(@Field("key") String key,
                                                   @Field("express_code") String express_code,
                                                   @Field("shipping_code") String shipping_code,
                                                   @Field("phone") String phone);


    /*
     * 生成充值订单并支付
     * https://shop.bayi-shop.com/mobile/Memberpayment/PdaddPay
     * */
    @POST("Memberpayment/PdaddPay")
    @FormUrlEncoded
    Observable<AcquirePayCodebean> getPayCodeInfo(@Field("key") String key,
                                                  @Field("payment_code") String payment_code,
                                                  @Field("pdr_amount") String pdr_amount);

    /*
     * 线下汇款信息
     * https://shop.bayi-shop.com/mobile/Payoffline/remittance
     * */
    @POST("Payoffline/remittance")
    @FormUrlEncoded
    Observable<RemittanceInfobean> getRemittanceInfo(@Field("key") String key);

    /*
     * 团队成员信息获取
     * https://shop.bayi-shop.com/mobile/member/inviter
     */
    @POST("member/inviter")
    @FormUrlEncoded
    Observable<TeamBean> getTeamList(@Field("key") String key);

    /*
     * 钱包页面个人资产查询
     * https://shop.bayi-shop.com/mobile/member/wallet
     * */
    @POST("member/wallet")
    @FormUrlEncoded
    Observable<WalletInfobean> getWalletInfo(@Field("key") String key);

    /*
     * 可用积分/冻结积分/储值卡/交易码 查询接口
     * https://shop.bayi-shop.com/mobile/member/my_asset
     * */
    @POST("member/my_asset")
    @FormUrlEncoded
    Observable<QueryPropertybean> getPropertybean(@Field("key") String key);

    /*
     * 储值卡交易明细
     * https://shop.bayi-shop.com/mobile/memberfund/predepositlog
     * */
    @POST("memberfund/predepositlog")
    @FormUrlEncoded
    Observable<StoredValueListbean> getStoredValueList(@Field("key") String key,
                                                       @Field("page") int page,
                                                       @Field("pagesize") int pagesize);

    /*
     * 积分交易明细
     * https://shop.bayi-shop.com/mobile/memberpoints/pointslog
     * */
    @POST("memberpoints/pointslog")
    @FormUrlEncoded
    Observable<PointListbean> getPointList(@Field("key") String key,
                                           @Field("page") int page,
                                           @Field("pagesize") int pagesize);

    /*
     * 认筹股价格曲线图
     * https://shop.bayi-shop.com/mobile/memberfund/tranprice
     * */
    @POST("memberfund/tranprice")
    @FormUrlEncoded
    Observable<GraphListbean> getGraphList(@Field("key") String key);

    /*
     * 认筹股明细
     * https://shop.bayi-shop.com/mobile/memberfund/transactionlog
     * */
    @POST("memberfund/transactionlog")
    @FormUrlEncoded
    Observable<DealCodeListbean> getDealCodeList(@Field("key") String key,
                                                 @Field("page") int page,
                                                 @Field("pagesize") int pagesize);

    /*
     * 提现申请
     * https://shop.bayi-shop.com/mobile/member/my_withdraw
     * */
    @POST("member/my_withdraw")
    @FormUrlEncoded
    Observable<WithdrawAppforbean> getWithdrawInfo(@Field("key") String key,
                                                   @Field("amount") String amount,
                                                   @Field("commission") String commission,
                                                   @Field("memberbank_name") String memberbank_name,
                                                   @Field("memberbank_no") String memberbank_no,
                                                   @Field("memberbank_truename") String memberbank_truename,
                                                   @Field("auth_code") String auth_code);

    /*
     * 提现记录
     * https://shop.bayi-shop.com/mobile/memberfund/pdcashlist
     * */
    @POST("memberfund/pdcashlist")
    @FormUrlEncoded
    Observable<WithdrawRecordListbean> getWithdrawList(@Field("key") String key,
                                                       @Field("page") int page,
                                                       @Field("pagesize") int pagesize);

    /*
     * 可用积分转换为交易码
     * https://shop.bayi-shop.com/mobile/Membertransform/PointTransform
     * */
    @POST("Membertransform/PointTransform")
    @FormUrlEncoded
    Observable<PointTransformbean> getPointTransformInfo(@Field("key") String key,
                                                         @Field("transtype") int transtype,
                                                         @Field("point") String point);

    /*
     * 商品加入购物车
     * https://shop.bayi-shop.com/mobile/membercart/cart_add
     * */
    @POST("membercart/cart_add")
    @FormUrlEncoded
    Observable<AddCartInfobean> getAddCartInfo(@Field("key") String key,
                                               @Field("goods_id") String goods_id,
                                               @Field("quantity") String quantity);

    /*
     * 删除购物车商品
     * https://shop.bayi-shop.com/mobile/Membercart/cart_del
     * */
    @POST("Membercart/cart_del")
    @FormUrlEncoded
    Observable<Logoutbean> getDelete(@Field("key") String key,
                                     @Field("cart_id") String cart_id);

    /*
     * 修改购物车商品数量
     * https://shop.bayi-shop.com/mobile/membercart/cart_edit_quantity
     * */
    @POST("membercart/cart_edit_quantity")
    @FormUrlEncoded
    Observable<ChangeGoodsNumInfobean> getChangeGoodsNumInfo(@Field("key") String key,
                                                             @Field("quantity") String quantity,
                                                             @Field("cart_id") String cart_id);

    /*
     * 购物车列表
     * https://shop.bayi-shop.com/mobile/membercart/cart_list
     * */
    @POST("membercart/cart_list")
    @FormUrlEncoded
    Observable<ShoppingtrolleyListbean> getShoppingCartList(@Field("key") String key);

}

