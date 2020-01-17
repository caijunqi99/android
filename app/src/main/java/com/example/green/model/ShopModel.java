package com.example.green.model;

import com.example.green.base.ICommonModel;
import com.example.green.base.ICommonView;
import com.example.green.config.ApiConfig;
import com.example.green.net.NetManager;

public class ShopModel implements ICommonModel {
    private static final String TAG = "ShopModel";

    @Override
    public void getData(ICommonView view, int whichApi, Object[] t) {
        switch (whichApi) {
            /*购物车列表*/
            case ApiConfig.SHOPPING_CART:
                String KEy = (String) t[0];
                int mode = (int) t[1];
                NetManager.getNetManager().netMethod(NetManager.getNetManager()
                        .getHttpService()
                        .getShoppingCartList(KEy), view, whichApi, mode);
                break;
            /*删除购物车商品*/
            case ApiConfig.DELETE_CART:
                String dkey = (String) t[0];
                String dcartId = (String) t[1];
                NetManager.getNetManager().netMethod(NetManager.getNetManager()
                        .getHttpService()
                        .getDelete(dkey, dcartId), view, whichApi, 0);
                break;
            /*修改购物车商品数量*/
            case ApiConfig.CHANGE_GOODS_NUM:
                String ckey = (String) t[0];
                String quantity = (String) t[1];
                String ccartId = (String) t[2];
                NetManager.getNetManager().netMethod(NetManager.getNetManager()
                        .getHttpService()
                        .getChangeGoodsNumInfo(ckey, quantity, ccartId), view, whichApi, 0);
                break;
            /*购买商品--第一步，生成购物信息*/
            case ApiConfig.SHOPPING_STEP_ONE:
                String key = (String) t[0];
                String cart_id = (String) t[1]; // 商品类型
                String ifcart = (String) t[2]; // 默认为空，为直接购物，1为从购物车
                NetManager.getNetManager().netMethod(NetManager.getNetManager()
                        .getHttpShoppingService()
                        .getShoppingInfo(key, cart_id, ifcart), view, whichApi, 0);
                break;
            /*购买商品--第二步，生成订单*/
            case ApiConfig.SHOPPING_STEP_TWO:
                String token = (String) t[0];
                String if_cart = (String) t[1]; // 默认为空，为直接购物，1为从购物车
                String cartId = (String) t[2]; // 商品类型
                String address_id = (String) t[3];
                String vat_hash = (String) t[4];
                String offpay_hash = (String) t[5];
                String offpay_hash_batch = (String) t[6];
                String pay_name = (String) t[7];
                String invoice_id = (String) t[8];
                String voucher = (String) t[9];
                NetManager.getNetManager().netMethod(NetManager.getNetManager()
                        .getHttpShoppingService()
                        .getCreateOrderbean(token, if_cart, cartId, address_id, vat_hash,
                                offpay_hash, offpay_hash_batch, pay_name, invoice_id, voucher), view, whichApi, 0);
                break;
            /*购买商品--第三步，获取订单信息*/
            case ApiConfig.SHOPPING_THIRD:
                String Key = (String) t[0];
                String pay_sn = (String) t[1];
                NetManager.getNetManager().netMethod(NetManager.getNetManager()
                        .getHttpShoppingService()
                        .getOrderInfobean(Key, pay_sn), view, whichApi, 0);
                break;
            /*购买商品--第四步，购买*/
            case ApiConfig.SHOPPING_FOURTH:
                String KEY = (String) t[0];
                String paySn = (String) t[1];
                String psw = (String) t[2];
                String pd_pay = (String) t[3];
                String payment_code = (String) t[4];
                NetManager.getNetManager().netMethod(NetManager.getNetManager()
                        .getHttpService()
                        .getPayOrderInfobean(KEY, paySn, psw, pd_pay, payment_code), view, whichApi, 0);
                break;
            /*转盘抽奖页面*/
            case ApiConfig.DRAW_AWARD:
                String dKey = (String) t[0];
                String marketmanage_id = (String) t[1];
                String dpay_sn = (String) t[2];
                NetManager.getNetManager().netMethod(NetManager.getNetManager()
                        .getHttpShoppingService()
                        .getMarketWheelbean(dKey, marketmanage_id, dpay_sn), view, whichApi, 0);
                break;
            /*开始抽奖*/
            case ApiConfig.STAR_DRAW:
                String sKey = (String) t[0];
                String smarketmanage_id = (String) t[1];
                NetManager.getNetManager().netMethod(NetManager.getNetManager()
                        .getHttpShoppingService()
                        .getSurprise(sKey, smarketmanage_id), view, whichApi, 0);
                break;
            /*支付成功后获取随机商品*/
            case ApiConfig.RANDOM_GOODS:
                NetManager.getNetManager().netMethod(NetManager.getNetManager()
                        .getHttpService()
                        .getRandomList(), view, whichApi, 0);
                break;
        }
    }
}
