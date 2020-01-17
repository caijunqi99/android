package com.example.green.bean.shopping;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @ProjectName: Green
 * @Package: com.example.green.bean.shopping
 * @ClassName: ShoppingtrolleyListbean
 * @Author: hjl
 * @email: 51721997@163.com
 * @Description: 购物车列表
 * @CreateDate: 2020/1/9 17:44
 */
public class ShoppingtrolleyListbean {

    /**
     * code : 200
     * message : ok
     * result : {"cart_count":2,"cart_list":[{"goods":[{"bl_id":0,"buyer_id":11,"cart_id":165,"gc_id":293,"gift_list":"","goods_commonid":143,"goods_freight":"0.00","goods_id":176,"goods_image":"alioss_2_2019121314291160951.jpg","goods_image_url":"https://oss.bayi-shop.com/home/store/goods/2/alioss_2_2019121314291160951.jpg","goods_name":"绿企 海南芒果青芒10斤一整箱新鲜水果金煌芒果凯特芒果批发包邮5","goods_num":1,"goods_price":"9.96","goods_storage":507,"goods_storage_alarm":0,"goods_sum":"9.96","goods_vat":0,"groupbuy_info":"","ifmgdiscount":true,"is_goodsfcode":0,"is_have_gift":0,"mgdiscount_desc":"会员享受2折","mgdiscount_info":{"1":{"level_discount":10,"level_name":"普通用户"},"2":{"level_discount":8,"level_name":"银牌用户"},"3":{"level_discount":5,"level_name":"金牌用户"},"4":{"level_discount":2,"level_name":"VIP用户"}},"state":true,"storage_state":true,"store_id":2,"store_name":"水果大全","transport_id":0,"xianshi_info":""}],"store_id":2,"store_name":"水果大全"},{"goods":[{"bl_id":0,"buyer_id":11,"cart_id":165,"gc_id":293,"gift_list":"","goods_commonid":143,"goods_freight":"0.00","goods_id":176,"goods_image":"alioss_2_2019121314291160951.jpg","goods_image_url":"https://oss.bayi-shop.com/home/store/goods/2/alioss_2_2019121314291160951.jpg","goods_name":"绿企 海南芒果青芒10斤一整箱新鲜水果金煌芒果凯特芒果批发包邮5","goods_num":1,"goods_price":"9.96","goods_storage":507,"goods_storage_alarm":0,"goods_sum":"9.96","goods_vat":0,"groupbuy_info":"","ifmgdiscount":true,"is_goodsfcode":0,"is_have_gift":0,"mgdiscount_desc":"会员享受2折","mgdiscount_info":{"1":{"level_discount":10,"level_name":"普通用户"},"2":{"level_discount":8,"level_name":"银牌用户"},"3":{"level_discount":5,"level_name":"金牌用户"},"4":{"level_discount":2,"level_name":"VIP用户"}},"state":true,"storage_state":true,"store_id":2,"store_name":"水果大全","transport_id":0,"xianshi_info":""}],"store_id":3,"store_name":"自营店铺"}],"sum":"10.96"}
     */

    private String code;
    private String message;
    private ResultBean result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * cart_count : 2
         * cart_list : [{"goods":[{"bl_id":0,"buyer_id":11,"cart_id":165,"gc_id":293,"gift_list":"","goods_commonid":143,"goods_freight":"0.00","goods_id":176,"goods_image":"alioss_2_2019121314291160951.jpg","goods_image_url":"https://oss.bayi-shop.com/home/store/goods/2/alioss_2_2019121314291160951.jpg","goods_name":"绿企 海南芒果青芒10斤一整箱新鲜水果金煌芒果凯特芒果批发包邮5","goods_num":1,"goods_price":"9.96","goods_storage":507,"goods_storage_alarm":0,"goods_sum":"9.96","goods_vat":0,"groupbuy_info":"","ifmgdiscount":true,"is_goodsfcode":0,"is_have_gift":0,"mgdiscount_desc":"会员享受2折","mgdiscount_info":{"1":{"level_discount":10,"level_name":"普通用户"},"2":{"level_discount":8,"level_name":"银牌用户"},"3":{"level_discount":5,"level_name":"金牌用户"},"4":{"level_discount":2,"level_name":"VIP用户"}},"state":true,"storage_state":true,"store_id":2,"store_name":"水果大全","transport_id":0,"xianshi_info":""}],"store_id":2,"store_name":"水果大全"},{"goods":[{"bl_id":0,"buyer_id":11,"cart_id":165,"gc_id":293,"gift_list":"","goods_commonid":143,"goods_freight":"0.00","goods_id":176,"goods_image":"alioss_2_2019121314291160951.jpg","goods_image_url":"https://oss.bayi-shop.com/home/store/goods/2/alioss_2_2019121314291160951.jpg","goods_name":"绿企 海南芒果青芒10斤一整箱新鲜水果金煌芒果凯特芒果批发包邮5","goods_num":1,"goods_price":"9.96","goods_storage":507,"goods_storage_alarm":0,"goods_sum":"9.96","goods_vat":0,"groupbuy_info":"","ifmgdiscount":true,"is_goodsfcode":0,"is_have_gift":0,"mgdiscount_desc":"会员享受2折","mgdiscount_info":{"1":{"level_discount":10,"level_name":"普通用户"},"2":{"level_discount":8,"level_name":"银牌用户"},"3":{"level_discount":5,"level_name":"金牌用户"},"4":{"level_discount":2,"level_name":"VIP用户"}},"state":true,"storage_state":true,"store_id":2,"store_name":"水果大全","transport_id":0,"xianshi_info":""}],"store_id":3,"store_name":"自营店铺"}]
         * sum : 10.96
         */

        private int cart_count;
        private String sum;
        private List<CartListBean> cart_list;

        public int getCart_count() {
            return cart_count;
        }

        public void setCart_count(int cart_count) {
            this.cart_count = cart_count;
        }

        public String getSum() {
            return sum;
        }

        public void setSum(String sum) {
            this.sum = sum;
        }

        public List<CartListBean> getCart_list() {
            return cart_list;
        }

        public void setCart_list(List<CartListBean> cart_list) {
            this.cart_list = cart_list;
        }

        public static class CartListBean implements Cloneable {
            /**
             * goods : [{"bl_id":0,"buyer_id":11,"cart_id":165,"gc_id":293,"gift_list":"","goods_commonid":143,"goods_freight":"0.00","goods_id":176,"goods_image":"alioss_2_2019121314291160951.jpg","goods_image_url":"https://oss.bayi-shop.com/home/store/goods/2/alioss_2_2019121314291160951.jpg","goods_name":"绿企 海南芒果青芒10斤一整箱新鲜水果金煌芒果凯特芒果批发包邮5","goods_num":1,"goods_price":"9.96","goods_storage":507,"goods_storage_alarm":0,"goods_sum":"9.96","goods_vat":0,"groupbuy_info":"","ifmgdiscount":true,"is_goodsfcode":0,"is_have_gift":0,"mgdiscount_desc":"会员享受2折","mgdiscount_info":{"1":{"level_discount":10,"level_name":"普通用户"},"2":{"level_discount":8,"level_name":"银牌用户"},"3":{"level_discount":5,"level_name":"金牌用户"},"4":{"level_discount":2,"level_name":"VIP用户"}},"state":true,"storage_state":true,"store_id":2,"store_name":"水果大全","transport_id":0,"xianshi_info":""}]
             * store_id : 2
             * store_name : 水果大全
             */
            public CartListBean clone() {
                CartListBean o = null;
                try {
                    o = (CartListBean) super.clone();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
                return o;
            }

            private int store_id;
            private String store_name;
            private List<GoodsBean> goods;
            private boolean isSelect_shop;      //店铺是否在购物车中被选中

            public boolean getIsSelect_shop() {
                return isSelect_shop;
            }

            public void setIsSelect_shop(boolean select_shop) {
                isSelect_shop = select_shop;
            }

            public int getStore_id() {
                return store_id;
            }

            public void setStore_id(int store_id) {
                this.store_id = store_id;
            }

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public List<GoodsBean> getGoods() {
                return goods;
            }

            public void setGoods(List<GoodsBean> goods) {
                this.goods = goods;
            }

            public static class GoodsBean {
                /**
                 * bl_id : 0
                 * buyer_id : 11
                 * cart_id : 165
                 * gc_id : 293
                 * gift_list :
                 * goods_commonid : 143
                 * goods_freight : 0.00
                 * goods_id : 176
                 * goods_image : alioss_2_2019121314291160951.jpg
                 * goods_image_url : https://oss.bayi-shop.com/home/store/goods/2/alioss_2_2019121314291160951.jpg
                 * goods_name : 绿企 海南芒果青芒10斤一整箱新鲜水果金煌芒果凯特芒果批发包邮5
                 * goods_num : 1
                 * goods_price : 9.96
                 * goods_storage : 507
                 * goods_storage_alarm : 0
                 * goods_sum : 9.96
                 * goods_vat : 0
                 * groupbuy_info :
                 * ifmgdiscount : true
                 * is_goodsfcode : 0
                 * is_have_gift : 0
                 * mgdiscount_desc : 会员享受2折
                 * mgdiscount_info : {"1":{"level_discount":10,"level_name":"普通用户"},"2":{"level_discount":8,"level_name":"银牌用户"},"3":{"level_discount":5,"level_name":"金牌用户"},"4":{"level_discount":2,"level_name":"VIP用户"}}
                 * state : true
                 * storage_state : true
                 * store_id : 2
                 * store_name : 水果大全
                 * transport_id : 0
                 * xianshi_info :
                 */

                private int bl_id;
                private int buyer_id;
                private int cart_id;
                private int gc_id;
                private String gift_list;
                private int goods_commonid;
                private String goods_freight;
                private int goods_id;
                private String goods_image;
                private String goods_image_url;
                private String goods_name;
                private int goods_num;
                private String goods_price;
                private int goods_storage;
                private int goods_storage_alarm;
                private String goods_sum;
                private int goods_vat;
                private String groupbuy_info;
                private boolean ifmgdiscount;
                private int is_goodsfcode;
                private int is_have_gift;
                private String mgdiscount_desc;
                private MgdiscountInfoBean mgdiscount_info;
                private boolean state;
                private boolean storage_state;
                private int store_id;
                private String store_name;
                private int transport_id;
                private String xianshi_info;
                private boolean isSelect;        //商品是否在购物车中被选中

                public boolean getIsSelect() {
                    return isSelect;
                }

                public void setIsSelect(boolean isSelect) {
                    this.isSelect = isSelect;
                }

                public int getBl_id() {
                    return bl_id;
                }

                public void setBl_id(int bl_id) {
                    this.bl_id = bl_id;
                }

                public int getBuyer_id() {
                    return buyer_id;
                }

                public void setBuyer_id(int buyer_id) {
                    this.buyer_id = buyer_id;
                }

                public int getCart_id() {
                    return cart_id;
                }

                public void setCart_id(int cart_id) {
                    this.cart_id = cart_id;
                }

                public int getGc_id() {
                    return gc_id;
                }

                public void setGc_id(int gc_id) {
                    this.gc_id = gc_id;
                }

                public String getGift_list() {
                    return gift_list;
                }

                public void setGift_list(String gift_list) {
                    this.gift_list = gift_list;
                }

                public int getGoods_commonid() {
                    return goods_commonid;
                }

                public void setGoods_commonid(int goods_commonid) {
                    this.goods_commonid = goods_commonid;
                }

                public String getGoods_freight() {
                    return goods_freight;
                }

                public void setGoods_freight(String goods_freight) {
                    this.goods_freight = goods_freight;
                }

                public int getGoods_id() {
                    return goods_id;
                }

                public void setGoods_id(int goods_id) {
                    this.goods_id = goods_id;
                }

                public String getGoods_image() {
                    return goods_image;
                }

                public void setGoods_image(String goods_image) {
                    this.goods_image = goods_image;
                }

                public String getGoods_image_url() {
                    return goods_image_url;
                }

                public void setGoods_image_url(String goods_image_url) {
                    this.goods_image_url = goods_image_url;
                }

                public String getGoods_name() {
                    return goods_name;
                }

                public void setGoods_name(String goods_name) {
                    this.goods_name = goods_name;
                }

                public int getGoods_num() {
                    return goods_num;
                }

                public void setGoods_num(int goods_num) {
                    this.goods_num = goods_num;
                }

                public String getGoods_price() {
                    return goods_price;
                }

                public void setGoods_price(String goods_price) {
                    this.goods_price = goods_price;
                }

                public int getGoods_storage() {
                    return goods_storage;
                }

                public void setGoods_storage(int goods_storage) {
                    this.goods_storage = goods_storage;
                }

                public int getGoods_storage_alarm() {
                    return goods_storage_alarm;
                }

                public void setGoods_storage_alarm(int goods_storage_alarm) {
                    this.goods_storage_alarm = goods_storage_alarm;
                }

                public String getGoods_sum() {
                    return goods_sum;
                }

                public void setGoods_sum(String goods_sum) {
                    this.goods_sum = goods_sum;
                }

                public int getGoods_vat() {
                    return goods_vat;
                }

                public void setGoods_vat(int goods_vat) {
                    this.goods_vat = goods_vat;
                }

                public String getGroupbuy_info() {
                    return groupbuy_info;
                }

                public void setGroupbuy_info(String groupbuy_info) {
                    this.groupbuy_info = groupbuy_info;
                }

                public boolean isIfmgdiscount() {
                    return ifmgdiscount;
                }

                public void setIfmgdiscount(boolean ifmgdiscount) {
                    this.ifmgdiscount = ifmgdiscount;
                }

                public int getIs_goodsfcode() {
                    return is_goodsfcode;
                }

                public void setIs_goodsfcode(int is_goodsfcode) {
                    this.is_goodsfcode = is_goodsfcode;
                }

                public int getIs_have_gift() {
                    return is_have_gift;
                }

                public void setIs_have_gift(int is_have_gift) {
                    this.is_have_gift = is_have_gift;
                }

                public String getMgdiscount_desc() {
                    return mgdiscount_desc;
                }

                public void setMgdiscount_desc(String mgdiscount_desc) {
                    this.mgdiscount_desc = mgdiscount_desc;
                }

                public MgdiscountInfoBean getMgdiscount_info() {
                    return mgdiscount_info;
                }

                public void setMgdiscount_info(MgdiscountInfoBean mgdiscount_info) {
                    this.mgdiscount_info = mgdiscount_info;
                }

                public boolean isState() {
                    return state;
                }

                public void setState(boolean state) {
                    this.state = state;
                }

                public boolean isStorage_state() {
                    return storage_state;
                }

                public void setStorage_state(boolean storage_state) {
                    this.storage_state = storage_state;
                }

                public int getStore_id() {
                    return store_id;
                }

                public void setStore_id(int store_id) {
                    this.store_id = store_id;
                }

                public String getStore_name() {
                    return store_name;
                }

                public void setStore_name(String store_name) {
                    this.store_name = store_name;
                }

                public int getTransport_id() {
                    return transport_id;
                }

                public void setTransport_id(int transport_id) {
                    this.transport_id = transport_id;
                }

                public String getXianshi_info() {
                    return xianshi_info;
                }

                public void setXianshi_info(String xianshi_info) {
                    this.xianshi_info = xianshi_info;
                }

                public static class MgdiscountInfoBean {
                    /**
                     * 1 : {"level_discount":10,"level_name":"普通用户"}
                     * 2 : {"level_discount":8,"level_name":"银牌用户"}
                     * 3 : {"level_discount":5,"level_name":"金牌用户"}
                     * 4 : {"level_discount":2,"level_name":"VIP用户"}
                     */

                    @SerializedName("1")
                    private _$1Bean _$1;
                    @SerializedName("2")
                    private _$2Bean _$2;
                    @SerializedName("3")
                    private _$3Bean _$3;
                    @SerializedName("4")
                    private _$4Bean _$4;

                    public _$1Bean get_$1() {
                        return _$1;
                    }

                    public void set_$1(_$1Bean _$1) {
                        this._$1 = _$1;
                    }

                    public _$2Bean get_$2() {
                        return _$2;
                    }

                    public void set_$2(_$2Bean _$2) {
                        this._$2 = _$2;
                    }

                    public _$3Bean get_$3() {
                        return _$3;
                    }

                    public void set_$3(_$3Bean _$3) {
                        this._$3 = _$3;
                    }

                    public _$4Bean get_$4() {
                        return _$4;
                    }

                    public void set_$4(_$4Bean _$4) {
                        this._$4 = _$4;
                    }

                    public static class _$1Bean {
                        /**
                         * level_discount : 10
                         * level_name : 普通用户
                         */

                        private int level_discount;
                        private String level_name;

                        public int getLevel_discount() {
                            return level_discount;
                        }

                        public void setLevel_discount(int level_discount) {
                            this.level_discount = level_discount;
                        }

                        public String getLevel_name() {
                            return level_name;
                        }

                        public void setLevel_name(String level_name) {
                            this.level_name = level_name;
                        }
                    }

                    public static class _$2Bean {
                        /**
                         * level_discount : 8
                         * level_name : 银牌用户
                         */

                        private int level_discount;
                        private String level_name;

                        public int getLevel_discount() {
                            return level_discount;
                        }

                        public void setLevel_discount(int level_discount) {
                            this.level_discount = level_discount;
                        }

                        public String getLevel_name() {
                            return level_name;
                        }

                        public void setLevel_name(String level_name) {
                            this.level_name = level_name;
                        }
                    }

                    public static class _$3Bean {
                        /**
                         * level_discount : 5
                         * level_name : 金牌用户
                         */

                        private int level_discount;
                        private String level_name;

                        public int getLevel_discount() {
                            return level_discount;
                        }

                        public void setLevel_discount(int level_discount) {
                            this.level_discount = level_discount;
                        }

                        public String getLevel_name() {
                            return level_name;
                        }

                        public void setLevel_name(String level_name) {
                            this.level_name = level_name;
                        }
                    }

                    public static class _$4Bean {
                        /**
                         * level_discount : 2
                         * level_name : VIP用户
                         */

                        private int level_discount;
                        private String level_name;

                        public int getLevel_discount() {
                            return level_discount;
                        }

                        public void setLevel_discount(int level_discount) {
                            this.level_discount = level_discount;
                        }

                        public String getLevel_name() {
                            return level_name;
                        }

                        public void setLevel_name(String level_name) {
                            this.level_name = level_name;
                        }
                    }
                }
            }
        }
    }
}
