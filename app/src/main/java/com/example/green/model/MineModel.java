package com.example.green.model;

import com.example.green.base.ICommonModel;
import com.example.green.base.ICommonView;
import com.example.green.config.ApiConfig;
import com.example.green.net.NetManager;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MineModel implements ICommonModel {
    NetManager mNetManager = NetManager.getNetManager();

    @Override
    public void getData(ICommonView view, int whichApi, Object[] t) {
        switch (whichApi) {
            /*个人页面*/
            case ApiConfig.MINEINFO:
                String key = (String) t[0];
                mNetManager.getNetManager().netMethod(mNetManager
                        .getHttpService()
                        .getMineInfo(key), view, whichApi, 0);
                break;
            /*钱包页面个人资产查询*/
            case ApiConfig.WALLET:
                String mkey = (String) t[0];
                int mode = (int) t[1];
                mNetManager.getNetManager().netMethod(mNetManager
                        .getHttpService()
                        .getWalletInfo(mkey), view, whichApi, mode);
                break;
            /*商学院*/
            case ApiConfig.COLLEGE:
                int type = (int) t[0];
                int loadMode = (int) t[1];
                mNetManager.getNetManager().netMethod(mNetManager
                        .getHttpService()
                        .getCollegeList(type), view, whichApi, loadMode);
                break;
            /*商学院详情*/
            case ApiConfig.COLLEGE_DETAILS:
                String article_id = (String) t[0];
                mNetManager.getNetManager().netMethod(mNetManager
                        .getHttpService()
                        .getCollegeDetails(article_id), view, whichApi, 0);
                break;
            /*收货地址列表*/
            case ApiConfig.SHOPPING_ADDRESS:
                String key_site = (String) t[0];
                mNetManager.getNetManager().netMethod(mNetManager
                        .getHttpService()
                        .getShoppingAddressList(key_site), view, whichApi, 0);
                break;
            /*添加收货地址*/
            case ApiConfig.ADDADDRESS:
                String true_name = (String) t[0]; // 姓名
                String mob_phone = (String) t[1]; // 手机号
                String city_id = (String) t[2]; // 城市ID
                String area_id = (String) t[3]; // 县/区ID
                String address = (String) t[4]; // 详细地址
                String area_info = (String) t[5]; // 省 市 县   空格拼接
                String is_default = (String) t[6]; // 0不默认，1默认地址
                String user_token = (String) t[7]; // 用户token
                mNetManager.getNetManager().netMethod(mNetManager
                        .getHttpService()
                        .getAddSite(true_name, mob_phone, city_id, area_id, address, area_info, is_default, user_token), view, whichApi, 0);
                break;
            /*编辑收货地址*/
            case ApiConfig.EDITOR_ADDRESS:
                String userToken = (String) t[0]; // 用户token
                String name = (String) t[1]; // 姓名
                String phone = (String) t[2]; // 手机号
                String cityId = (String) t[3]; // 城市ID
                String areaId = (String) t[4]; // 县/区ID
                String details_site = (String) t[5]; // 详细地址
                String areaInfo = (String) t[6]; // 省 市 县   空格拼接
                String isDefault = (String) t[7]; // 0不默认，1默认地址
                String address_id = (String) t[8]; // 收货地址ID

                mNetManager.getNetManager().netMethod(mNetManager
                        .getHttpService()
                        .getEditorAddressbean(userToken, name, phone, cityId, areaId, details_site, areaInfo, isDefault, address_id), view, whichApi, 0);
                break;
            /*删除收货地址*/
            case ApiConfig.DELETE_ADDRESS:
                String Key = (String) t[0];
                String AddressId = (String) t[1];

                mNetManager.getNetManager().netMethod(mNetManager
                        .getHttpService()
                        .getDeleteAddressbean(Key, AddressId), view, whichApi, 0);
                break;
            /*用户上传头像*/
            case ApiConfig.URL_UPLOAD_PICTURE://get
                String path = (String) t[0];
                String mtoken = (String) t[1];

                //获取路径对应的文件
                File file = new File(path);
                //得到请求体
                RequestBody fileRQ = RequestBody.create(MediaType.parse("image/jpeg"), file);
                //创建MultipartBody.Part对象
                //注意:这个file是后台定义的参数名
                MultipartBody.Part part = MultipartBody.Part.createFormData("pic", file.getName(), fileRQ);
                MultipartBody.Part part1 = MultipartBody.Part.createFormData("key", mtoken);
                mNetManager.netMethod(mNetManager
                        .getHttpUploadService()
                        .uploadPicture(part, part1), view, whichApi, 0);
                break;
            /*个人资料编辑*/
            case ApiConfig.URL_EDIT_USER_INFO:
                String userkey = (String) t[0];
                int commit = (int) t[1];
                String Name = (String) t[2];
                String Sex = (String) t[3];
                String Email = (String) t[4];
                NetManager.getNetManager().netMethod(NetManager.getNetManager()
                        .getHttpService()
                        .getEditMineInfo(userkey, commit, Name, Sex, Email), view, whichApi, 0);
                break;
            /*实名认证*/
            case ApiConfig.USER_AUTONYM:// post 上传实名身份信息
                int member_id = (int) t[0];
                String user_name = (String) t[1];
                String idcard = (String) t[2];
                String member_bankname = (String) t[3];
                String member_bankcard = (String) t[4];
                String member_idcard_image1 = (String) t[5];
                String member_idcard_image2 = (String) t[6];
                String member_idcard_image3 = (String) t[7];
                String member_provinceid = (String) t[8];
                String member_cityid = (String) t[9];
                String member_areaid = (String) t[10];
                String member_areainfo = (String) t[11];
                int Commit = (int) t[12];

                RequestBody id = RequestBody.create(null, String.valueOf(member_id));
                RequestBody xingming = RequestBody.create(null, user_name);
                RequestBody IdNumber = RequestBody.create(null, idcard);
                RequestBody bankName = RequestBody.create(null, member_bankname);
                RequestBody bankCard = RequestBody.create(null, member_bankcard);
                RequestBody provinceid = RequestBody.create(null, member_provinceid);
                RequestBody cityid = RequestBody.create(null, member_cityid);
                RequestBody areaid = RequestBody.create(null, member_areaid);
                RequestBody areainfo = RequestBody.create(null, member_areainfo);
                RequestBody commit_type = RequestBody.create(null, String.valueOf(Commit));

                File file_img1 = new File(member_idcard_image1);
                RequestBody fileRQ_1 = RequestBody.create(MediaType.parse("image/jpeg"), file_img1);
                MultipartBody.Part img_1 = MultipartBody.Part.createFormData("member_idcard_image1", file_img1.getName(), fileRQ_1);

                //获取路径对应的文件
                File file_img2 = new File(member_idcard_image2);
                //得到请求体
                RequestBody fileRQ_2 = RequestBody.create(MediaType.parse("image/jpeg"), file_img2);
                //创建MultipartBody.Part对象
                //注意:这个file是后台定义的参数名
                MultipartBody.Part img_2 = MultipartBody.Part.createFormData("member_idcard_image2", file_img2.getName(), fileRQ_2);

                File file_img3 = new File(member_idcard_image3);
                RequestBody fileRQ_3 = RequestBody.create(MediaType.parse("image/jpeg"), file_img3);
                MultipartBody.Part img_3 = MultipartBody.Part.createFormData("member_idcard_image3", file_img3.getName(), fileRQ_3);

                mNetManager.netMethod(mNetManager
                        .getHttpService()
                        .getAutonym(id, xingming, IdNumber, bankName, bankCard,
                                img_1, img_2, img_3, provinceid, cityid, areaid, areainfo, commit_type), view, whichApi, 0);
                break;
            /*二次提交实名认证信息*/
            case ApiConfig.USER_AUTONYMTWICE:// post 上传实名身份信息
                int member_Id = (int) t[0];
                String user_Name = (String) t[1];
                String idCard = (String) t[2];
                String member_Bankname = (String) t[3];
                String member_Bankcard = (String) t[4];
                String member_Provinceid = (String) t[5];
                String member_Cityid = (String) t[6];
                String member_Areaid = (String) t[7];
                String member_Areainfo = (String) t[8];
                int COmmit = (int) t[9];

                mNetManager.netMethod(mNetManager
                        .getHttpService()
                        .getAutonymtwice(member_Id, user_Name, idCard, member_Bankname, member_Bankcard,
                                member_Provinceid, member_Cityid, member_Areaid, member_Areainfo, COmmit), view, whichApi, 0);
                break;
            /*获取实名认证信息*/
            case ApiConfig.AUTHINFO:
                String userId = (String) t[0];

                NetManager.getNetManager().netMethod(NetManager.getNetManager()
                        .getHttpService()
                        .getAuthInfo(userId), view, whichApi, 0);
                break;
            /*订单列表*/
            case ApiConfig.GOODSORDER:
                String tokenq = (String) t[0];
                String typeq = (String) t[1];
                int opage = (int) t[2];
                int opagesize = (int) t[3];
                int loadModea = (int) t[4];

                NetManager.getNetManager().netMethod(NetManager.getNetManager()
                        .getHttpService()
                        .getGoodsOrder(tokenq, typeq, opage, opagesize), view, whichApi, loadModea);

                break;
            /*订单详情查看*/
            case ApiConfig.ORDER_DETAILS:
                String okey = (String) t[0];
                String order_Id = (String) t[1]; // 订单id
                NetManager.getNetManager().netMethod(NetManager.getNetManager()
                        .getHttpShoppingService()
                        .getOrderDetailsInfo(okey, order_Id), view, whichApi, 0);
                break;
            /*获取物流信息*/
            case ApiConfig.GET_LOGISTICS:
                String lkey = (String) t[0];
                String express_code = (String) t[1]; // 物流信息代码
                String shipping_code = (String) t[2]; // 物流号
                String lphone = (String) t[3]; // 收货人手机号
                int lloadMode = (int) t[4]; // 加载模式
                NetManager.getNetManager().netMethod(NetManager.getNetManager()
                        .getHttpService()
                        .getLogisticsInfo(lkey, express_code, shipping_code, lphone), view, whichApi, lloadMode);
                break;
            /*修改订单状态*/
            case ApiConfig.CHANGER_ORDER_STATE:
                String tokeno = (String) t[0];
                String state_type = (String) t[1];
                String order_id = (String) t[2];

                NetManager.getNetManager().netMethod(NetManager.getNetManager()
                        .getHttpService()
                        .getChangeOrderState(tokeno, state_type, order_id), view, whichApi, 0);
                break;
            /*购买商品--第三步，获取订单信息  订单去付款*/
            case ApiConfig.SHOPPING_THIRD:
                String sKey = (String) t[0];
                String pay_sn = (String) t[1];
                NetManager.getNetManager().netMethod(NetManager.getNetManager()
                        .getHttpShoppingService()
                        .getOrderInfobean(sKey, pay_sn), view, whichApi, 0);
                break;
            /*生成充值订单并支付*/
            case ApiConfig.GETPAYMENT:
                String Token = (String) t[0];
                String payment_code = (String) t[1];
                String pdr_amount = (String) t[2];
                NetManager.getNetManager().netMethod(NetManager.getNetManager()
                        .getHttpService()
                        .getPayCodeInfo(Token, payment_code, pdr_amount), view, whichApi, 0);
                break;
            /*线下汇款*/
            case ApiConfig.REMITTANCE:
                String rtoken = (String) t[0];
                NetManager.getNetManager().netMethod(NetManager.getNetManager()
                        .getHttpService()
                        .getRemittanceInfo(rtoken), view, whichApi, 0);
                break;
            /*可用积分/冻结积分/储值卡/交易码 查询接口*/
            case ApiConfig.QUERY_PROPERT:
                String ktoken = (String) t[0];
                NetManager.getNetManager().netMethod(NetManager.getNetManager()
                        .getHttpService()
                        .getPropertybean(ktoken), view, whichApi, 0);
                break;
            /*获取验证码*/
            case ApiConfig.ACCQUIRE_CODE:
                String member_mobile = (String) t[0];
                int Type = (int) t[1]; // 验证码类型（1登录2注册4绑定手机5绑定邮箱6安全验证）

                NetManager.getNetManager().netMethod(NetManager.getNetManager()
                        .getHttpService()
                        .getAccquirebean(member_mobile, Type), view, whichApi, 0);
                break;
            /*提现申请*/
            case ApiConfig.WITHDRAW_APPLY:
                String wtoken = (String) t[0];
                String amount = (String) t[1];
                String commission = (String) t[2];
                String memberbank_name = (String) t[3];
                String memberbank_no = (String) t[4];
                String memberbank_truename = (String) t[5];
                String auth_code = (String) t[6];
                NetManager.getNetManager().netMethod(NetManager.getNetManager()
                        .getHttpService()
                        .getWithdrawInfo(wtoken, amount, commission, memberbank_name,
                                memberbank_no, memberbank_truename, auth_code), view, whichApi, 0);
                break;
            /*提现记录*/
            case ApiConfig.WIRHDRAW_RECORD:
                String Wtoken = (String) t[0];
                int Wpage = (int) t[1];
                int WpageSize = (int) t[2];
                int Wload = (int) t[3];
                NetManager.getNetManager().netMethod(NetManager.getNetManager()
                        .getHttpService()
                        .getWithdrawList(Wtoken, Wpage, WpageSize), view, whichApi, Wload);
                break;
            /*可用积分转换为交易码*/
            case ApiConfig.TRANSFORM:
                String Ttoken = (String) t[0];
                int transtype = (int) t[1];
                String point = (String) t[2];
                NetManager.getNetManager().netMethod(NetManager.getNetManager()
                        .getHttpService()
                        .getPointTransformInfo(Ttoken, transtype, point), view, whichApi, 0);
                break;
            /*储值卡交易明细*/
            case ApiConfig.CHUZHI_DETAIL:
                String ctoken = (String) t[0];
                int page = (int) t[1];
                int pageSize = (int) t[2];
                int load = (int) t[3];
                NetManager.getNetManager().netMethod(NetManager.getNetManager()
                        .getHttpService()
                        .getStoredValueList(ctoken, page, pageSize), view, whichApi, load);
                break;
            /*积分交易明细*/
            case ApiConfig.JIFEN_DETAIL:
                String jtoken = (String) t[0];
                int jpage = (int) t[1];
                int jpageSize = (int) t[2];
                int jload = (int) t[3];
                NetManager.getNetManager().netMethod(NetManager.getNetManager()
                        .getHttpService()
                        .getPointList(jtoken, jpage, jpageSize), view, whichApi, jload);
                break;
            /*认筹股价格曲线图*/
            case ApiConfig.GRAPH:
                String gtoken = (String) t[0];
                NetManager.getNetManager().netMethod(NetManager.getNetManager()
                        .getHttpService()
                        .getGraphList(gtoken), view, whichApi, 0);
                break;
            /*认筹股明细*/
            case ApiConfig.DEAL_DETAIL:
                String dtoken = (String) t[0];
                int dpage = (int) t[1];
                int dpageSize = (int) t[2];
                int dload = (int) t[3];
                NetManager.getNetManager().netMethod(NetManager.getNetManager()
                        .getHttpService()
                        .getDealCodeList(dtoken, dpage, dpageSize), view, whichApi, dload);
                break;
            /*团队*/
            case ApiConfig.USER_TEAM:
                String mToken = (String) t[0];
                NetManager.getNetManager().netMethod(NetManager.getNetManager()
                        .getHttpService()
                        .getTeamList(mToken), view, whichApi, 0);
                break;
            /*退出登录*/
            case ApiConfig.LOGOUT:
                String username = (String) t[0];
                String token = (String) t[1];
                String client = (String) t[2];
                NetManager.getNetManager().netMethod(NetManager.getNetManager()
                        .getHttpService()
                        .getLogOutbean(username, token, client), view, whichApi, 0);
                break;
        }
    }
}
