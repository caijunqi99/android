package com.example.green.bean.homepage;

import java.util.List;

public class GoodsListbean {

    /**
     * code : 200
     * result : [{"goods_id":2,"goods_name":"荣耀V9 play 标配版 3+32G 全网通4G手机 铂光金","goods_advword":"北欧极简设计，EMUI5.1","goods_image":"https://shop.bayi-shop.com/uploads/home/store/goods/1/1_2017092120144795113.jpg","store_id":1,"goods_promotion_price":"998.00","goods_price":"998.00"},{"goods_id":3,"goods_name":"Apple/苹果 iPhone 7plus 128GB 玫瑰金色 移动联通电信4G手机","goods_advword":"购机即送精美防尘塞+便捷支架+鱼骨绕线器+店铺延保一年","goods_image":"https://shop.bayi-shop.com/uploads/home/store/goods/1/1_2017092120244885492.jpg","store_id":1,"goods_promotion_price":"6999.00","goods_price":"6999.00"},{"goods_id":4,"goods_name":"nubia/努比亚Z17 6G+64G 全网通4G手机 烈焰红 无边框","goods_advword":"烈焰红 无边框","goods_image":"https://shop.bayi-shop.com/uploads/home/store/goods/1/1_2017092120312320763.jpg","store_id":1,"goods_promotion_price":"2699.00","goods_price":"2699.00"},{"goods_id":5,"goods_name":"智能电动代步车","goods_advword":"长续航蓝牙遥控 轻小便携","goods_image":"https://shop.bayi-shop.com/uploads/home/store/goods/1/1_2017092120345719915.jpg","store_id":1,"goods_promotion_price":"1990.00","goods_price":"1990.00"},{"goods_id":6,"goods_name":"灵动Lite版 NV3012 视频通话智能机器人","goods_advword":"人工智能 视频聊天儿童早教益智娱乐 智能家居","goods_image":"https://shop.bayi-shop.com/uploads/home/store/goods/1/1_2017092120365848023.jpg","store_id":1,"goods_promotion_price":"149.00","goods_price":"149.00"},{"goods_id":7,"goods_name":"HOST好帅智能云教育机器人二蛋Q6 ","goods_advword":"早教机英语学习机国学儿童陪护 玩具礼物百科全书正版音乐播放机京剧粤剧儿歌国学","goods_image":"https://shop.bayi-shop.com/uploads/home/store/goods/1/1_2017092120382376796.jpg","store_id":1,"goods_promotion_price":"1699.00","goods_price":"1699.00"}]
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
         * goods_id : 2
         * goods_name : 荣耀V9 play 标配版 3+32G 全网通4G手机 铂光金
         * goods_advword : 北欧极简设计，EMUI5.1
         * goods_image : https://shop.bayi-shop.com/uploads/home/store/goods/1/1_2017092120144795113.jpg
         * store_id : 1
         * goods_promotion_price : 998.00
         * goods_price : 998.00
         */

        private int goods_id;
        private String goods_name;
        private String goods_advword;
        private String goods_image;
        private int store_id;
        private String goods_promotion_price;
        private String goods_price;

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
    }
}
