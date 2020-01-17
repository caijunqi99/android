package com.example.green.bean.mine;

public class MineInfobean {
    /**
     * code : 200
     * result : {"member_info":{"user_name":"小新","avator":"https://shop.bayi-shop.com/uploads/home/avatar/avatar_17.jpg","level_name":"普通用户","favorites_store":0,"favorites_goods":0,"mobile":"159****6215","order_nopay_count":0,"order_noreceipt_count":0,"order_notakes_count":0,"order_noeval_count":0}}
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
         * member_info : {"user_name":"小新","avator":"https://shop.bayi-shop.com/uploads/home/avatar/avatar_17.jpg","level_name":"普通用户","favorites_store":0,"favorites_goods":0,"mobile":"159****6215","order_nopay_count":0,"order_noreceipt_count":0,"order_notakes_count":0,"order_noeval_count":0}
         */

        private MemberInfoBean member_info;

        public MemberInfoBean getMember_info() {
            return member_info;
        }

        public void setMember_info(MemberInfoBean member_info) {
            this.member_info = member_info;
        }

        public static class MemberInfoBean {
            /**
             * user_name : 小新
             * avator : https://shop.bayi-shop.com/uploads/home/avatar/avatar_17.jpg
             * level_name : 普通用户
             * favorites_store : 0
             * favorites_goods : 0
             * mobile : 159****6215
             * order_nopay_count : 0
             * order_noreceipt_count : 0
             * order_notakes_count : 0
             * order_noeval_count : 0
             */

            private String user_name;
            private String avator;
            private String level_name;
            private int favorites_store;
            private int favorites_goods;
            private String mobile;
            private int order_nopay_count;
            private int order_noreceipt_count;
            private int order_notakes_count;
            private int order_noeval_count;

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public String getAvator() {
                return avator;
            }

            public void setAvator(String avator) {
                this.avator = avator;
            }

            public String getLevel_name() {
                return level_name;
            }

            public void setLevel_name(String level_name) {
                this.level_name = level_name;
            }

            public int getFavorites_store() {
                return favorites_store;
            }

            public void setFavorites_store(int favorites_store) {
                this.favorites_store = favorites_store;
            }

            public int getFavorites_goods() {
                return favorites_goods;
            }

            public void setFavorites_goods(int favorites_goods) {
                this.favorites_goods = favorites_goods;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public int getOrder_nopay_count() {
                return order_nopay_count;
            }

            public void setOrder_nopay_count(int order_nopay_count) {
                this.order_nopay_count = order_nopay_count;
            }

            public int getOrder_noreceipt_count() {
                return order_noreceipt_count;
            }

            public void setOrder_noreceipt_count(int order_noreceipt_count) {
                this.order_noreceipt_count = order_noreceipt_count;
            }

            public int getOrder_notakes_count() {
                return order_notakes_count;
            }

            public void setOrder_notakes_count(int order_notakes_count) {
                this.order_notakes_count = order_notakes_count;
            }

            public int getOrder_noeval_count() {
                return order_noeval_count;
            }

            public void setOrder_noeval_count(int order_noeval_count) {
                this.order_noeval_count = order_noeval_count;
            }
        }
    }
}
