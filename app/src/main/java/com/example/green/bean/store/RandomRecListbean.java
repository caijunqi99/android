package com.example.green.bean.store;

import java.util.List;

/**
 * @ProjectName: Green
 * @Package: com.example.green.bean.store
 * @ClassName: RandomRecListbean
 * @Author: hjl
 * @email: 51721997@163.com
 * @Description: 获取随机商品
 * @CreateDate: 2019/12/26 18:19
 */
public class RandomRecListbean {

    /**
     * code : 200
     * result : [{"goods_id":148,"goods_name":"有机菠菜新鲜蔬菜小青菜火锅蔬菜宝宝辅食250g","goods_advword":"有机菠菜新鲜蔬菜小青菜火锅蔬菜宝宝辅食250g","goods_image":"alioss_1_2019121217163746310.jpg","store_id":1,"goods_promotion_price":"19.00","goods_price":"12.00","goods_commonid":128},{"goods_id":149,"goods_name":"桃树苗桃树苗","goods_advword":"桃树苗桃树苗桃树苗","goods_image":"alioss_1_2019121118424369048.jpg","store_id":1,"goods_promotion_price":"10.00","goods_price":"5.00","goods_commonid":129},{"goods_id":150,"goods_name":"土鸡土鸡土鸡","goods_advword":"土鸡土鸡","goods_image":"alioss_1_2019121212590222769.jpg","store_id":1,"goods_promotion_price":"25.00","goods_price":"20.00","goods_commonid":130},{"goods_id":151,"goods_name":"红薯红薯红薯红薯红薯","goods_advword":"红薯红薯红薯红薯红薯","goods_image":"alioss_1_2019121117450292048.jpg","store_id":1,"goods_promotion_price":"15.00","goods_price":"10.00","goods_commonid":131},{"goods_id":152,"goods_name":"雪花梨赵县新鲜梨包邮水果皇冠梨砀山梨黄金梨9斤河北雪梨整箱10 中果","goods_advword":"雪花梨赵县新鲜梨包邮水果皇冠梨砀山梨黄金梨9斤河北雪梨整箱10","goods_image":"alioss_1_2019121313415564065.jpg","store_id":1,"goods_promotion_price":"55.00","goods_price":"33.80","goods_commonid":132},{"goods_id":153,"goods_name":"人参人参人参","goods_advword":"人参人参","goods_image":"alioss_1_2019121213255634668.jpg","store_id":1,"goods_promotion_price":"150.00","goods_price":"100.00","goods_commonid":133}]
     * message : ok
     */

    private String code;
    private String message;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * goods_id : 148
         * goods_name : 有机菠菜新鲜蔬菜小青菜火锅蔬菜宝宝辅食250g
         * goods_advword : 有机菠菜新鲜蔬菜小青菜火锅蔬菜宝宝辅食250g
         * goods_image : alioss_1_2019121217163746310.jpg
         * store_id : 1
         * goods_promotion_price : 19.00
         * goods_price : 12.00
         * goods_commonid : 128
         */

        private int goods_id;
        private String goods_name;
        private String goods_advword;
        private String goods_image;
        private int store_id;
        private String goods_promotion_price;
        private String goods_price;
        private int goods_commonid;

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getGoods_advword() {
            return goods_advword;
        }

        public void setGoods_advword(String goods_advword) {
            this.goods_advword = goods_advword;
        }

        public String getGoods_image() {
            return goods_image;
        }

        public void setGoods_image(String goods_image) {
            this.goods_image = goods_image;
        }

        public int getStore_id() {
            return store_id;
        }

        public void setStore_id(int store_id) {
            this.store_id = store_id;
        }

        public String getGoods_promotion_price() {
            return goods_promotion_price;
        }

        public void setGoods_promotion_price(String goods_promotion_price) {
            this.goods_promotion_price = goods_promotion_price;
        }

        public String getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(String goods_price) {
            this.goods_price = goods_price;
        }

        public int getGoods_commonid() {
            return goods_commonid;
        }

        public void setGoods_commonid(int goods_commonid) {
            this.goods_commonid = goods_commonid;
        }
    }
}
