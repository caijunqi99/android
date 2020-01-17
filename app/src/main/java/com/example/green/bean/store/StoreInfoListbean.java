package com.example.green.bean.store;

import java.io.Serializable;
import java.util.List;

public class StoreInfoListbean implements Serializable {

    /**
     * code : 200
     * result : {"store_info":{"store_id":1,"store_name":"自营店铺","member_id":1,"store_avatar":"https://shop.bayi-shop.com/uploads/home/store/1/1_2019120310233068889.png","goods_count":105,"store_collect":0,"is_favorate":false,"is_platform_store":true,"store_credit_text":"官方店铺","mb_title_img":"https://shop.bayi-shop.com/uploads/home/store/1_2019120218342817979.png","mb_sliders":[{"img":"1_1.png","type":1,"link":"","imgUrl":"https://shop.bayi-shop.com/uploads/home/store/mobileslide/1_1.png"},{"img":"1_2.jpg","type":1,"link":"","imgUrl":"https://shop.bayi-shop.com/uploads/home/store/mobileslide/1_2.jpg"}]}}
     * message : ok
     */

    private String code;
    private ResultBean result;
    private String message;

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

    public static class ResultBean {
        /**
         * store_info : {"store_id":1,"store_name":"自营店铺","member_id":1,"store_avatar":"https://shop.bayi-shop.com/uploads/home/store/1/1_2019120310233068889.png","goods_count":105,"store_collect":0,"is_favorate":false,"is_platform_store":true,"store_credit_text":"官方店铺","mb_title_img":"https://shop.bayi-shop.com/uploads/home/store/1_2019120218342817979.png","mb_sliders":[{"img":"1_1.png","type":1,"link":"","imgUrl":"https://shop.bayi-shop.com/uploads/home/store/mobileslide/1_1.png"},{"img":"1_2.jpg","type":1,"link":"","imgUrl":"https://shop.bayi-shop.com/uploads/home/store/mobileslide/1_2.jpg"}]}
         */

        private StoreInfoBean store_info;

        public StoreInfoBean getStore_info() {
            return store_info;
        }

        public void setStore_info(StoreInfoBean store_info) {
            this.store_info = store_info;
        }

        public static class StoreInfoBean implements Serializable {
            /**
             * store_id : 1
             * store_name : 自营店铺
             * member_id : 1
             * store_avatar : https://shop.bayi-shop.com/uploads/home/store/1/1_2019120310233068889.png
             * goods_count : 105
             * store_collect : 0
             * is_favorate : false
             * is_platform_store : true
             * store_credit_text : 官方店铺
             * mb_title_img : https://shop.bayi-shop.com/uploads/home/store/1_2019120218342817979.png
             * mb_sliders : [{"img":"1_1.png","type":1,"link":"","imgUrl":"https://shop.bayi-shop.com/uploads/home/store/mobileslide/1_1.png"},{"img":"1_2.jpg","type":1,"link":"","imgUrl":"https://shop.bayi-shop.com/uploads/home/store/mobileslide/1_2.jpg"}]
             */

            private int store_id;
            private String store_name;
            private int member_id;
            private String store_avatar;
            private int goods_count;
            private int store_collect;
            private boolean is_favorate;
            private boolean is_platform_store;
            private String store_credit_text;
            private String mb_title_img;
            private List<MbSlidersBean> mb_sliders;

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

            public int getMember_id() {
                return member_id;
            }

            public void setMember_id(int member_id) {
                this.member_id = member_id;
            }

            public String getStore_avatar() {
                return store_avatar;
            }

            public void setStore_avatar(String store_avatar) {
                this.store_avatar = store_avatar;
            }

            public int getGoods_count() {
                return goods_count;
            }

            public void setGoods_count(int goods_count) {
                this.goods_count = goods_count;
            }

            public int getStore_collect() {
                return store_collect;
            }

            public void setStore_collect(int store_collect) {
                this.store_collect = store_collect;
            }

            public boolean isIs_favorate() {
                return is_favorate;
            }

            public void setIs_favorate(boolean is_favorate) {
                this.is_favorate = is_favorate;
            }

            public boolean isIs_platform_store() {
                return is_platform_store;
            }

            public void setIs_platform_store(boolean is_platform_store) {
                this.is_platform_store = is_platform_store;
            }

            public String getStore_credit_text() {
                return store_credit_text;
            }

            public void setStore_credit_text(String store_credit_text) {
                this.store_credit_text = store_credit_text;
            }

            public String getMb_title_img() {
                return mb_title_img;
            }

            public void setMb_title_img(String mb_title_img) {
                this.mb_title_img = mb_title_img;
            }

            public List<MbSlidersBean> getMb_sliders() {
                return mb_sliders;
            }

            public void setMb_sliders(List<MbSlidersBean> mb_sliders) {
                this.mb_sliders = mb_sliders;
            }

            public static class MbSlidersBean implements Serializable {
                /**
                 * img : 1_1.png
                 * type : 1
                 * link :
                 * imgUrl : https://shop.bayi-shop.com/uploads/home/store/mobileslide/1_1.png
                 */

                private String img;
                private int type;
                private String link;
                private String imgUrl;

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public String getLink() {
                    return link;
                }

                public void setLink(String link) {
                    this.link = link;
                }

                public String getImgUrl() {
                    return imgUrl;
                }

                public void setImgUrl(String imgUrl) {
                    this.imgUrl = imgUrl;
                }
            }
        }
    }
}
