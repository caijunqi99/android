package com.example.green.bean.homepage;

import java.util.List;

public class SearchListbean {

    /**
     * code : 200
     * result : {"goods_list":[{"goods_id":147,"store_id":1,"goods_name":"台湾牛奶枣贵妃枣大青枣5斤包邮冬枣现摘青枣脆甜新鲜当季水果","goods_advword":"台湾牛奶枣贵妃枣大青枣5斤包邮冬枣现摘青枣脆甜新鲜当季水果","goods_price":"29.80","goods_promotion_price":"29.80","goods_promotion_type":0,"goods_marketprice":"119.80","goods_image":"alioss_1_2019121217102927726.jpg","goods_salenum":0,"evaluation_good_star":5,"evaluation_count":0,"is_virtual":0,"is_presell":0,"is_goodsfcode":0,"is_have_gift":0,"store_name":"自营店铺","is_platform_store":1,"group_flag":false,"xianshi_flag":false,"goods_image_url":"https://oss.bayi-shop.com/home/store/goods/1/alioss_1_2019121217102927726.jpg"},{"goods_id":146,"store_id":1,"goods_name":"现摘水蜜桃子水果新鲜雪桃10斤整箱包邮冬桃甜当季时令孕妇毛桃蟠","goods_advword":"现摘水蜜桃子水果新鲜雪桃10斤整箱包邮冬桃甜当季时令孕妇毛桃蟠","goods_price":"35.80","goods_promotion_price":"35.80","goods_promotion_type":0,"goods_marketprice":"69.80","goods_image":"alioss_1_2019121217014724405.jpg","goods_salenum":1,"evaluation_good_star":5,"evaluation_count":0,"is_virtual":0,"is_presell":0,"is_goodsfcode":0,"is_have_gift":0,"store_name":"自营店铺","is_platform_store":1,"group_flag":false,"xianshi_flag":false,"goods_image_url":"https://oss.bayi-shop.com/home/store/goods/1/alioss_1_2019121217014724405.jpg"},{"goods_id":144,"store_id":1,"goods_name":"梨子鸭梨梨子新鲜10斤砀山梨皇冠梨香梨翠冠梨当季水果新鲜雪梨 中果","goods_advword":"","goods_price":"19.90","goods_promotion_price":"19.90","goods_promotion_type":0,"goods_marketprice":"39.90","goods_image":"alioss_1_2019121216501934568.jpg","goods_salenum":0,"evaluation_good_star":5,"evaluation_count":0,"is_virtual":0,"is_presell":0,"is_goodsfcode":0,"is_have_gift":0,"store_name":"自营店铺","is_platform_store":1,"group_flag":false,"xianshi_flag":false,"goods_image_url":"https://oss.bayi-shop.com/home/store/goods/1/alioss_1_2019121216501934568.jpg"},{"goods_id":142,"store_id":1,"goods_name":"苹果苹果苹果苹果 富士康 4kg","goods_advword":"苹果苹果苹果苹果苹果","goods_price":"20.00","goods_promotion_price":"20.00","goods_promotion_type":0,"goods_marketprice":"30.00","goods_image":"alioss_1_2019121117365572753.jpg","goods_salenum":0,"evaluation_good_star":5,"evaluation_count":0,"is_virtual":0,"is_presell":0,"is_goodsfcode":0,"is_have_gift":0,"store_name":"自营店铺","is_platform_store":1,"group_flag":false,"xianshi_flag":false,"goods_image_url":"https://oss.bayi-shop.com/home/store/goods/1/alioss_1_2019121117365572753.jpg"}]}
     * message : ok
     * page_total : 1
     * hasmore : false
     */

    private String code;
    private ResultBean result;
    private String message;
    private int page_total;
    private boolean hasmore;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getPage_total() {
        return page_total;
    }

    public void setPage_total(int page_total) {
        this.page_total = page_total;
    }

    public boolean isHasmore() {
        return hasmore;
    }

    public void setHasmore(boolean hasmore) {
        this.hasmore = hasmore;
    }

    public static class ResultBean {
        private List<GoodsListBean> goods_list;

        public List<GoodsListBean> getGoods_list() {
            return goods_list;
        }

        public void setGoods_list(List<GoodsListBean> goods_list) {
            this.goods_list = goods_list;
        }

        public static class GoodsListBean {
            /**
             * goods_id : 147
             * store_id : 1
             * goods_name : 台湾牛奶枣贵妃枣大青枣5斤包邮冬枣现摘青枣脆甜新鲜当季水果
             * goods_advword : 台湾牛奶枣贵妃枣大青枣5斤包邮冬枣现摘青枣脆甜新鲜当季水果
             * goods_price : 29.80
             * goods_promotion_price : 29.80
             * goods_promotion_type : 0
             * goods_marketprice : 119.80
             * goods_image : alioss_1_2019121217102927726.jpg
             * goods_salenum : 0
             * evaluation_good_star : 5
             * evaluation_count : 0
             * is_virtual : 0
             * is_presell : 0
             * is_goodsfcode : 0
             * is_have_gift : 0
             * store_name : 自营店铺
             * is_platform_store : 1
             * group_flag : false
             * xianshi_flag : false
             * goods_image_url : https://oss.bayi-shop.com/home/store/goods/1/alioss_1_2019121217102927726.jpg
             */

            private int goods_id;
            private int store_id;
            private String goods_name;
            private String goods_advword;
            private String goods_price;
            private String goods_promotion_price;
            private int goods_promotion_type;
            private String goods_marketprice;
            private String goods_image;
            private int goods_salenum;
            private int evaluation_good_star;
            private int evaluation_count;
            private int is_virtual;
            private int is_presell;
            private int is_goodsfcode;
            private int is_have_gift;
            private String store_name;
            private int is_platform_store;
            private boolean group_flag;
            private boolean xianshi_flag;
            private String goods_image_url;

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public int getStore_id() {
                return store_id;
            }

            public void setStore_id(int store_id) {
                this.store_id = store_id;
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

            public String getGoods_price() {
                return goods_price;
            }

            public void setGoods_price(String goods_price) {
                this.goods_price = goods_price;
            }

            public String getGoods_promotion_price() {
                return goods_promotion_price;
            }

            public void setGoods_promotion_price(String goods_promotion_price) {
                this.goods_promotion_price = goods_promotion_price;
            }

            public int getGoods_promotion_type() {
                return goods_promotion_type;
            }

            public void setGoods_promotion_type(int goods_promotion_type) {
                this.goods_promotion_type = goods_promotion_type;
            }

            public String getGoods_marketprice() {
                return goods_marketprice;
            }

            public void setGoods_marketprice(String goods_marketprice) {
                this.goods_marketprice = goods_marketprice;
            }

            public String getGoods_image() {
                return goods_image;
            }

            public void setGoods_image(String goods_image) {
                this.goods_image = goods_image;
            }

            public int getGoods_salenum() {
                return goods_salenum;
            }

            public void setGoods_salenum(int goods_salenum) {
                this.goods_salenum = goods_salenum;
            }

            public int getEvaluation_good_star() {
                return evaluation_good_star;
            }

            public void setEvaluation_good_star(int evaluation_good_star) {
                this.evaluation_good_star = evaluation_good_star;
            }

            public int getEvaluation_count() {
                return evaluation_count;
            }

            public void setEvaluation_count(int evaluation_count) {
                this.evaluation_count = evaluation_count;
            }

            public int getIs_virtual() {
                return is_virtual;
            }

            public void setIs_virtual(int is_virtual) {
                this.is_virtual = is_virtual;
            }

            public int getIs_presell() {
                return is_presell;
            }

            public void setIs_presell(int is_presell) {
                this.is_presell = is_presell;
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

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public int getIs_platform_store() {
                return is_platform_store;
            }

            public void setIs_platform_store(int is_platform_store) {
                this.is_platform_store = is_platform_store;
            }

            public boolean isGroup_flag() {
                return group_flag;
            }

            public void setGroup_flag(boolean group_flag) {
                this.group_flag = group_flag;
            }

            public boolean isXianshi_flag() {
                return xianshi_flag;
            }

            public void setXianshi_flag(boolean xianshi_flag) {
                this.xianshi_flag = xianshi_flag;
            }

            public String getGoods_image_url() {
                return goods_image_url;
            }

            public void setGoods_image_url(String goods_image_url) {
                this.goods_image_url = goods_image_url;
            }
        }
    }
}
