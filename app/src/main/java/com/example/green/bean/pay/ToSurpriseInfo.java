package com.example.green.bean.pay;

/**
 * @ProjectName: Green
 * @Package: com.example.green.bean.pay
 * @ClassName: ToSurpriseInfo
 * @Author: hjl
 * @email: 51721997@163.com
 * @Description: 开始抽奖
 * @CreateDate: 2020/1/15 11:05
 */
public class ToSurpriseInfo {

    /**
     * code : 200
     * result : {"count_left":0,"draw_result":true,"draw_info":{"marketmanageaward_id":2,"marketmanage_id":1,"marketmanageaward_level":2,"marketmanageaward_type":2,"marketmanageaward_count":2,"marketmanageaward_send":3,"marketmanageaward_probability":2,"marketmanageaward_point":0,"bonus_id":"彩电一台","vouchertemplate_id":0,"sort":""},"awardMsg":"恭喜手机号码为【15210356014】的用户【yang】,抽中二等奖，奖励 彩电一台!"}
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
         * count_left : 0
         * draw_result : true
         * draw_info : {"marketmanageaward_id":2,"marketmanage_id":1,"marketmanageaward_level":2,"marketmanageaward_type":2,"marketmanageaward_count":2,"marketmanageaward_send":3,"marketmanageaward_probability":2,"marketmanageaward_point":0,"bonus_id":"彩电一台","vouchertemplate_id":0,"sort":""}
         * awardMsg : 恭喜手机号码为【15210356014】的用户【yang】,抽中二等奖，奖励 彩电一台!
         */

        private int count_left;
        private boolean draw_result;
        private DrawInfoBean draw_info;
        private String awardMsg;

        public int getCount_left() {
            return count_left;
        }

        public void setCount_left(int count_left) {
            this.count_left = count_left;
        }

        public boolean isDraw_result() {
            return draw_result;
        }

        public void setDraw_result(boolean draw_result) {
            this.draw_result = draw_result;
        }

        public DrawInfoBean getDraw_info() {
            return draw_info;
        }

        public void setDraw_info(DrawInfoBean draw_info) {
            this.draw_info = draw_info;
        }

        public String getAwardMsg() {
            return awardMsg;
        }

        public void setAwardMsg(String awardMsg) {
            this.awardMsg = awardMsg;
        }

        public static class DrawInfoBean {
            /**
             * marketmanageaward_id : 2
             * marketmanage_id : 1
             * marketmanageaward_level : 2
             * marketmanageaward_type : 2
             * marketmanageaward_count : 2
             * marketmanageaward_send : 3
             * marketmanageaward_probability : 2
             * marketmanageaward_point : 0
             * bonus_id : 彩电一台
             * vouchertemplate_id : 0
             * sort :
             */

            private int marketmanageaward_id;
            private int marketmanage_id;
            private int marketmanageaward_level;
            private int marketmanageaward_type;
            private int marketmanageaward_count;
            private int marketmanageaward_send;
            private int marketmanageaward_probability;
            private int marketmanageaward_point;
            private String bonus_id;
            private int vouchertemplate_id;
            private String sort;

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

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }
        }
    }
}
