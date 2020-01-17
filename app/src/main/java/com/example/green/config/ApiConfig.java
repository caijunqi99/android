package com.example.green.config;

public class ApiConfig {
    public static final int ACQUIRE_VERSIONCODE = 62; // 获取版本号
    public static final int SEARCH_GOODS = 10; // 搜索商品
    /*首页*/
    public static final int URL_HOMEDATA = 0;  // 首页数据
    public static final int SYSTEM_MESSAGE = 22;  // 消息列表
    public static final int URL_GOODSDATA = 1;   // 商品推荐
    public static final int HOT_SEARCH_KEY = 2; // 搜索页面热门搜索词
    public static final int GOODS_DETAILS = 13; // 商品详情
    public static final int ADD_CART = 61; // 商品加入购物车

    /*分类*/
    public static final int URL_ALLCLASSIFY = 3;   // 分类首页
    public static final int URL_RIGHTCLASSIFY = 4;   // 右侧分类

    /*店铺*/
    public static final int RECOMMEND_STORE = 5; // 推荐店铺
    public static final int STORE_CLASSIFY = 6; // 店铺分类
    public static final int STORE_INFO = 7; // 店铺首页
    public static final int STORE_RECOMMEND = 8; // 店铺首页推荐商品
    public static final int ALL_STOREGOODS = 9; // 全部商品(获取店铺商品)


    /*商品购物*/
    public static final int SHOPPING_STEP_ONE = 28; // 购买商品--第一步，生成购物信息
    public static final int SHOPPING_STEP_TWO = 29; // 购买商品--第二步，生成订单
    public static final int SHOPPING_THIRD = 30; // 购买商品--第三步，获取订单信息
    public static final int SHOPPING_FOURTH = 31; // 购买商品--第四步，购买
    public static final int DRAW_AWARD = 63; // 转盘抽奖页面
    public static final int STAR_DRAW = 65; // 转盘抽奖页面

    public static final int RANDOM_GOODS = 34; // 支付成功后获取随机商品
    public static final int ORDER_DETAILS = 59; // 订单详情查看
    public static final int GET_LOGISTICS = 60; // 获取物流信息

    /*购物车*/
    public static final int SHOPPING_CART = 52; // 购物车列表
    public static final int DELETE_CART = 61; // 删除购物车商品
    public static final int CHANGE_GOODS_NUM = 62; // 修改购物车商品数量


    /*我的*/
    public static final int MINEINFO = 11; // 我的信息
    public static final int COLLEGE = 12; // 商学院
    public static final int COLLEGE_DETAILS = 37; // 商学院
    public static final int LOGOUT = 17; // 退出登录
    public static final int SHOPPING_ADDRESS = 18; // 收货地址列表
    public static final int ADDADDRESS = 19; // 添加收货地址
    public static final int EDITOR_ADDRESS = 20; // 编辑收货地址
    public static final int DELETE_ADDRESS = 21; // 删除收货地址
    public static final int URL_UPLOAD_PICTURE = 25; // 上传图片
    public static final int URL_EDIT_USER_INFO = 26; // 个人资料编辑
    public static final int USER_AUTONYM = 27; // 实名认证
    public static final int USER_AUTONYMTWICE = 36; // 二次提交实名认证
    public static final int AUTHINFO = 35; // 获取实名认证信息
    public static final int USER_TEAM = 50; // 团队
    public static final int WALLET = 51; // 钱包页面个人资产查询
    public static final int QUERY_PROPERT = 52; // 个人资产查询
    public static final int CHUZHI_DETAIL = 53; // 储值卡交易明细
    public static final int JIFEN_DETAIL = 54; // 积分交易明细
    public static final int GRAPH = 64; // 认筹股价格曲线图
    public static final int DEAL_DETAIL = 55; // 交易码明细
    public static final int WITHDRAW_APPLY = 56; // 提现申请
    public static final int WIRHDRAW_RECORD = 57; // 提现记录
    public static final int TRANSFORM = 58; // 可用积分转换为交易码 （互转）

    //订单
    public static final int GOODSORDER = 204; // 订单列表
    public static final int CHANGER_ORDER_STATE = 205; // 修改订单状态

    /*注册 登录*/
    public static final int REGISTER = 14; // 注册
    public static final int ACCQUIRE_CODE = 15; // 验证码发送
    public static final int LOGIN = 16; // 登录
    public static final int MODIFICATION_PSW = 23; // 登陆密码修改-修改登陆密码
    public static final int CHANGE_PAYPSW = 24; // 支付密码修改--修改支付密码


    // 支付
    public static final int GETPAYMENT = 40; // 生成充值订单并支付
    public static final int REMITTANCE = 66; // 线下汇款信息


}