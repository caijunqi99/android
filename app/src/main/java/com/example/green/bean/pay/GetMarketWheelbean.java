package com.example.green.bean.pay;

import java.util.List;

/**
 * @ProjectName: Green
 * @Package: com.example.green.bean.pay
 * @ClassName: GetMarketWheelbean
 * @Author: hjl
 * @email: 51721997@163.com
 * @Description: 转盘抽奖页面
 * @CreateDate: 2020/1/15 9:21
 */
public class GetMarketWheelbean {

    /**
     * code : 200
     * result : {"marketmanage_info":{"marketmanage_id":4,"marketmanage_name":"VIP会员大转盘","marketmanage_detail":"大转盘抽奖","marketmanage_begintime":1574752560,"marketmanage_endtime":1609398960,"marketmanage_jointype":2,"marketmanage_joincount":0,"marketmanage_totalcount":0,"marketmanage_totalwin":0,"marketmanage_point":0,"marketmanage_addtime":1574752666,"marketmanage_failed":"很遗憾您未中奖","marketmanage_type":2,"marketmanage_grade":4,"botton":"https://shop.bayi-shop.com/uploads/home/marketmanageaward/choujiang.png?t=1579058285"},"marketmanageaward_list":[{"marketmanageaward_id":29,"marketmanage_id":4,"marketmanageaward_level":5,"marketmanageaward_type":3,"marketmanageaward_count":0,"marketmanageaward_send":0,"marketmanageaward_probability":1,"marketmanageaward_point":0,"marketmanageaward_picture":"https://shop.bayi-shop.com/uploads/home/marketmanageaward/15789869245.png","bonus_id":"0","vouchertemplate_id":0,"sort":1},{"marketmanageaward_id":30,"marketmanage_id":4,"marketmanageaward_level":6,"marketmanageaward_type":4,"marketmanageaward_count":1,"marketmanageaward_send":0,"marketmanageaward_probability":2,"marketmanageaward_point":1,"marketmanageaward_picture":"https://shop.bayi-shop.com/uploads/home/marketmanageaward/15787651681.jpg","bonus_id":"宝马一台","vouchertemplate_id":0,"sort":2},{"marketmanageaward_id":31,"marketmanage_id":4,"marketmanageaward_level":7,"marketmanageaward_type":5,"marketmanageaward_count":2,"marketmanageaward_send":0,"marketmanageaward_probability":3,"marketmanageaward_point":2,"marketmanageaward_picture":"https://shop.bayi-shop.com/uploads/home/marketmanageaward/15789869246.png","bonus_id":"0","vouchertemplate_id":0,"sort":3},{"marketmanageaward_id":32,"marketmanage_id":4,"marketmanageaward_level":8,"marketmanageaward_type":6,"marketmanageaward_count":3,"marketmanageaward_send":0,"marketmanageaward_probability":4,"marketmanageaward_point":3,"marketmanageaward_picture":"https://shop.bayi-shop.com/uploads/home/marketmanageaward/15787651682.jpg","bonus_id":"彩电一台","vouchertemplate_id":0,"sort":4},{"marketmanageaward_id":33,"marketmanage_id":4,"marketmanageaward_level":9,"marketmanageaward_type":7,"marketmanageaward_count":4,"marketmanageaward_send":0,"marketmanageaward_probability":5,"marketmanageaward_point":4,"marketmanageaward_picture":"https://shop.bayi-shop.com/uploads/home/marketmanageaward/15789869247.png","bonus_id":"0","vouchertemplate_id":0,"sort":5},{"marketmanageaward_id":34,"marketmanage_id":4,"marketmanageaward_level":10,"marketmanageaward_type":8,"marketmanageaward_count":5,"marketmanageaward_send":0,"marketmanageaward_probability":6,"marketmanageaward_point":5,"marketmanageaward_picture":"https://shop.bayi-shop.com/uploads/home/marketmanageaward/15787651683.jpg","bonus_id":"冰箱一台","vouchertemplate_id":0,"sort":6},{"marketmanageaward_id":35,"marketmanage_id":4,"marketmanageaward_level":11,"marketmanageaward_type":9,"marketmanageaward_count":6,"marketmanageaward_send":0,"marketmanageaward_probability":7,"marketmanageaward_point":6,"marketmanageaward_picture":"https://shop.bayi-shop.com/uploads/home/marketmanageaward/15789869248.png","bonus_id":"0","vouchertemplate_id":0,"sort":7},{"marketmanageaward_id":36,"marketmanage_id":4,"marketmanageaward_level":12,"marketmanageaward_type":10,"marketmanageaward_count":7,"marketmanageaward_send":0,"marketmanageaward_probability":8,"marketmanageaward_point":7,"marketmanageaward_picture":"https://shop.bayi-shop.com/uploads/home/marketmanageaward/15787651684.jpg","bonus_id":"","vouchertemplate_id":0,"sort":8}]}
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
         * marketmanage_info : {"marketmanage_id":4,"marketmanage_name":"VIP会员大转盘","marketmanage_detail":"大转盘抽奖","marketmanage_begintime":1574752560,"marketmanage_endtime":1609398960,"marketmanage_jointype":2,"marketmanage_joincount":0,"marketmanage_totalcount":0,"marketmanage_totalwin":0,"marketmanage_point":0,"marketmanage_addtime":1574752666,"marketmanage_failed":"很遗憾您未中奖","marketmanage_type":2,"marketmanage_grade":4,"botton":"https://shop.bayi-shop.com/uploads/home/marketmanageaward/choujiang.png?t=1579058285"}
         * marketmanageaward_list : [{"marketmanageaward_id":29,"marketmanage_id":4,"marketmanageaward_level":5,"marketmanageaward_type":3,"marketmanageaward_count":0,"marketmanageaward_send":0,"marketmanageaward_probability":1,"marketmanageaward_point":0,"marketmanageaward_picture":"https://shop.bayi-shop.com/uploads/home/marketmanageaward/15789869245.png","bonus_id":"0","vouchertemplate_id":0,"sort":1},{"marketmanageaward_id":30,"marketmanage_id":4,"marketmanageaward_level":6,"marketmanageaward_type":4,"marketmanageaward_count":1,"marketmanageaward_send":0,"marketmanageaward_probability":2,"marketmanageaward_point":1,"marketmanageaward_picture":"https://shop.bayi-shop.com/uploads/home/marketmanageaward/15787651681.jpg","bonus_id":"宝马一台","vouchertemplate_id":0,"sort":2},{"marketmanageaward_id":31,"marketmanage_id":4,"marketmanageaward_level":7,"marketmanageaward_type":5,"marketmanageaward_count":2,"marketmanageaward_send":0,"marketmanageaward_probability":3,"marketmanageaward_point":2,"marketmanageaward_picture":"https://shop.bayi-shop.com/uploads/home/marketmanageaward/15789869246.png","bonus_id":"0","vouchertemplate_id":0,"sort":3},{"marketmanageaward_id":32,"marketmanage_id":4,"marketmanageaward_level":8,"marketmanageaward_type":6,"marketmanageaward_count":3,"marketmanageaward_send":0,"marketmanageaward_probability":4,"marketmanageaward_point":3,"marketmanageaward_picture":"https://shop.bayi-shop.com/uploads/home/marketmanageaward/15787651682.jpg","bonus_id":"彩电一台","vouchertemplate_id":0,"sort":4},{"marketmanageaward_id":33,"marketmanage_id":4,"marketmanageaward_level":9,"marketmanageaward_type":7,"marketmanageaward_count":4,"marketmanageaward_send":0,"marketmanageaward_probability":5,"marketmanageaward_point":4,"marketmanageaward_picture":"https://shop.bayi-shop.com/uploads/home/marketmanageaward/15789869247.png","bonus_id":"0","vouchertemplate_id":0,"sort":5},{"marketmanageaward_id":34,"marketmanage_id":4,"marketmanageaward_level":10,"marketmanageaward_type":8,"marketmanageaward_count":5,"marketmanageaward_send":0,"marketmanageaward_probability":6,"marketmanageaward_point":5,"marketmanageaward_picture":"https://shop.bayi-shop.com/uploads/home/marketmanageaward/15787651683.jpg","bonus_id":"冰箱一台","vouchertemplate_id":0,"sort":6},{"marketmanageaward_id":35,"marketmanage_id":4,"marketmanageaward_level":11,"marketmanageaward_type":9,"marketmanageaward_count":6,"marketmanageaward_send":0,"marketmanageaward_probability":7,"marketmanageaward_point":6,"marketmanageaward_picture":"https://shop.bayi-shop.com/uploads/home/marketmanageaward/15789869248.png","bonus_id":"0","vouchertemplate_id":0,"sort":7},{"marketmanageaward_id":36,"marketmanage_id":4,"marketmanageaward_level":12,"marketmanageaward_type":10,"marketmanageaward_count":7,"marketmanageaward_send":0,"marketmanageaward_probability":8,"marketmanageaward_point":7,"marketmanageaward_picture":"https://shop.bayi-shop.com/uploads/home/marketmanageaward/15787651684.jpg","bonus_id":"","vouchertemplate_id":0,"sort":8}]
         */

        private MarketmanageInfoBean marketmanage_info;
        private List<MarketmanageawardListBean> marketmanageaward_list;

        public MarketmanageInfoBean getMarketmanage_info() {
            return marketmanage_info;
        }

        public void setMarketmanage_info(MarketmanageInfoBean marketmanage_info) {
            this.marketmanage_info = marketmanage_info;
        }

        public List<MarketmanageawardListBean> getMarketmanageaward_list() {
            return marketmanageaward_list;
        }

        public void setMarketmanageaward_list(List<MarketmanageawardListBean> marketmanageaward_list) {
            this.marketmanageaward_list = marketmanageaward_list;
        }

        public static class MarketmanageInfoBean {
            /**
             * marketmanage_id : 4
             * marketmanage_name : VIP会员大转盘
             * marketmanage_detail : 大转盘抽奖
             * marketmanage_begintime : 1574752560
             * marketmanage_endtime : 1609398960
             * marketmanage_jointype : 2
             * marketmanage_joincount : 0
             * marketmanage_totalcount : 0
             * marketmanage_totalwin : 0
             * marketmanage_point : 0
             * marketmanage_addtime : 1574752666
             * marketmanage_failed : 很遗憾您未中奖
             * marketmanage_type : 2
             * marketmanage_grade : 4
             * botton : https://shop.bayi-shop.com/uploads/home/marketmanageaward/choujiang.png?t=1579058285
             */

            private int marketmanage_id;
            private String marketmanage_name;
            private String marketmanage_detail;
            private int marketmanage_begintime;
            private int marketmanage_endtime;
            private int marketmanage_jointype;
            private int marketmanage_joincount;
            private int marketmanage_totalcount;
            private int marketmanage_totalwin;
            private int marketmanage_point;
            private int marketmanage_addtime;
            private String marketmanage_failed;
            private int marketmanage_type;
            private int marketmanage_grade;
            private String botton;

            public int getMarketmanage_id() {
                return marketmanage_id;
            }

            public void setMarketmanage_id(int marketmanage_id) {
                this.marketmanage_id = marketmanage_id;
            }

            public String getMarketmanage_name() {
                return marketmanage_name;
            }

            public void setMarketmanage_name(String marketmanage_name) {
                this.marketmanage_name = marketmanage_name;
            }

            public String getMarketmanage_detail() {
                return marketmanage_detail;
            }

            public void setMarketmanage_detail(String marketmanage_detail) {
                this.marketmanage_detail = marketmanage_detail;
            }

            public int getMarketmanage_begintime() {
                return marketmanage_begintime;
            }

            public void setMarketmanage_begintime(int marketmanage_begintime) {
                this.marketmanage_begintime = marketmanage_begintime;
            }

            public int getMarketmanage_endtime() {
                return marketmanage_endtime;
            }

            public void setMarketmanage_endtime(int marketmanage_endtime) {
                this.marketmanage_endtime = marketmanage_endtime;
            }

            public int getMarketmanage_jointype() {
                return marketmanage_jointype;
            }

            public void setMarketmanage_jointype(int marketmanage_jointype) {
                this.marketmanage_jointype = marketmanage_jointype;
            }

            public int getMarketmanage_joincount() {
                return marketmanage_joincount;
            }

            public void setMarketmanage_joincount(int marketmanage_joincount) {
                this.marketmanage_joincount = marketmanage_joincount;
            }

            public int getMarketmanage_totalcount() {
                return marketmanage_totalcount;
            }

            public void setMarketmanage_totalcount(int marketmanage_totalcount) {
                this.marketmanage_totalcount = marketmanage_totalcount;
            }

            public int getMarketmanage_totalwin() {
                return marketmanage_totalwin;
            }

            public void setMarketmanage_totalwin(int marketmanage_totalwin) {
                this.marketmanage_totalwin = marketmanage_totalwin;
            }

            public int getMarketmanage_point() {
                return marketmanage_point;
            }

            public void setMarketmanage_point(int marketmanage_point) {
                this.marketmanage_point = marketmanage_point;
            }

            public int getMarketmanage_addtime() {
                return marketmanage_addtime;
            }

            public void setMarketmanage_addtime(int marketmanage_addtime) {
                this.marketmanage_addtime = marketmanage_addtime;
            }

            public String getMarketmanage_failed() {
                return marketmanage_failed;
            }

            public void setMarketmanage_failed(String marketmanage_failed) {
                this.marketmanage_failed = marketmanage_failed;
            }

            public int getMarketmanage_type() {
                return marketmanage_type;
            }

            public void setMarketmanage_type(int marketmanage_type) {
                this.marketmanage_type = marketmanage_type;
            }

            public int getMarketmanage_grade() {
                return marketmanage_grade;
            }

            public void setMarketmanage_grade(int marketmanage_grade) {
                this.marketmanage_grade = marketmanage_grade;
            }

            public String getBotton() {
                return botton;
            }

            public void setBotton(String botton) {
                this.botton = botton;
            }
        }

        public static class MarketmanageawardListBean {
            /**
             * marketmanageaward_id : 29
             * marketmanage_id : 4
             * marketmanageaward_level : 5
             * marketmanageaward_type : 3
             * marketmanageaward_count : 0
             * marketmanageaward_send : 0
             * marketmanageaward_probability : 1
             * marketmanageaward_point : 0
             * marketmanageaward_picture : https://shop.bayi-shop.com/uploads/home/marketmanageaward/15789869245.png
             * bonus_id : 0
             * vouchertemplate_id : 0
             * sort : 1
             */

            private int marketmanageaward_id;
            private int marketmanage_id;
            private int marketmanageaward_level;
            private int marketmanageaward_type;
            private int marketmanageaward_count;
            private int marketmanageaward_send;
            private int marketmanageaward_probability;
            private int marketmanageaward_point;
            private String marketmanageaward_picture;
            private String bonus_id;
            private int vouchertemplate_id;
            private int sort;

            public int getMarketmanageaward_id() {
                return marketmanageaward_id;
            }

            public void setMarketmanageaward_id(int marketmanageaward_id) {
                this.marketmanageaward_id = marketmanageaward_id;
            }

            public int getMarketmanage_id() {
                return marketmanage_id;
            }

            public void setMarketmanage_id(int marketmanage_id) {
                this.marketmanage_id = marketmanage_id;
            }

            public int getMarketmanageaward_level() {
                return marketmanageaward_level;
            }

            public void setMarketmanageaward_level(int marketmanageaward_level) {
                this.marketmanageaward_level = marketmanageaward_level;
            }

            public int getMarketmanageaward_type() {
                return marketmanageaward_type;
            }

            public void setMarketmanageaward_type(int marketmanageaward_type) {
                this.marketmanageaward_type = marketmanageaward_type;
            }

            public int getMarketmanageaward_count() {
                return marketmanageaward_count;
            }

            public void setMarketmanageaward_count(int marketmanageaward_count) {
                this.marketmanageaward_count = marketmanageaward_count;
            }

            public int getMarketmanageaward_send() {
                return marketmanageaward_send;
            }

            public void setMarketmanageaward_send(int marketmanageaward_send) {
                this.marketmanageaward_send = marketmanageaward_send;
            }

            public int getMarketmanageaward_probability() {
                return marketmanageaward_probability;
            }

            public void setMarketmanageaward_probability(int marketmanageaward_probability) {
                this.marketmanageaward_probability = marketmanageaward_probability;
            }

            public int getMarketmanageaward_point() {
                return marketmanageaward_point;
            }

            public void setMarketmanageaward_point(int marketmanageaward_point) {
                this.marketmanageaward_point = marketmanageaward_point;
            }

            public String getMarketmanageaward_picture() {
                return marketmanageaward_picture;
            }

            public void setMarketmanageaward_picture(String marketmanageaward_picture) {
                this.marketmanageaward_picture = marketmanageaward_picture;
            }

            public String getBonus_id() {
                return bonus_id;
            }

            public void setBonus_id(String bonus_id) {
                this.bonus_id = bonus_id;
            }

            public int getVouchertemplate_id() {
                return vouchertemplate_id;
            }

            public void setVouchertemplate_id(int vouchertemplate_id) {
                this.vouchertemplate_id = vouchertemplate_id;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }
        }
    }
}
