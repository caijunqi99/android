package com.example.green.bean.pay;

/**
 * @ProjectName: Green
 * @Package: com.example.green.bean.shopping
 * @ClassName: PayOrderInfobean
 * @Author: hjl
 * @email: 51721997@163.com
 * @Description: java类作用描述
 * @CreateDate: 2019/12/27 18:16
 */
public class PayOrderInfobean {

    /**
     * code : 200
     * result : {"pay_id":28,"pay_sn":"19120212295597729008","buyer_id":8,"api_paystate":"0","subject":"实物订单_19120212295597729008","order_type":"real_order","api_pay_amount":"0.00","order_list":null,"pay_end":1,"draw":0}
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
         * pay_id : 28
         * pay_sn : 19120212295597729008
         * buyer_id : 8
         * api_paystate : 0
         * subject : 实物订单_19120212295597729008
         * order_type : real_order
         * api_pay_amount : 0.00
         * order_list : null
         * pay_end : 1
         * draw : 0
         */

        private int pay_id;
        private String pay_sn;
        private int buyer_id;
        private String api_paystate;
        private String subject;
        private String order_type;
        private String api_pay_amount;
        private Object order_list;
        private int pay_end;
        private int draw;

        public int getPay_id() {
            return pay_id;
        }

        public void setPay_id(int pay_id) {
            this.pay_id = pay_id;
        }

        public String getPay_sn() {
            return pay_sn;
        }

        public void setPay_sn(String pay_sn) {
            this.pay_sn = pay_sn;
        }

        public int getBuyer_id() {
            return buyer_id;
        }

        public void setBuyer_id(int buyer_id) {
            this.buyer_id = buyer_id;
        }

        public String getApi_paystate() {
            return api_paystate;
        }

        public void setApi_paystate(String api_paystate) {
            this.api_paystate = api_paystate;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getOrder_type() {
            return order_type;
        }

        public void setOrder_type(String order_type) {
            this.order_type = order_type;
        }

        public String getApi_pay_amount() {
            return api_pay_amount;
        }

        public void setApi_pay_amount(String api_pay_amount) {
            this.api_pay_amount = api_pay_amount;
        }

        public Object getOrder_list() {
            return order_list;
        }

        public void setOrder_list(Object order_list) {
            this.order_list = order_list;
        }

        public int getPay_end() {
            return pay_end;
        }

        public void setPay_end(int pay_end) {
            this.pay_end = pay_end;
        }

        public int getDraw() {
            return draw;
        }

        public void setDraw(int draw) {
            this.draw = draw;
        }
    }
}
