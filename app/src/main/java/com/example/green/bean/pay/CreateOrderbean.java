package com.example.green.bean.pay;

/**
 * @ProjectName: Green
 * @Package: com.example.green.bean.shopping
 * @ClassName: CreateOrderbean
 * @Author: hjl
 * @email: 51721997@163.com
 * @Description: java类作用描述
 * @CreateDate: 2019/12/27 17:50
 */
public class CreateOrderbean {

    /**
     * code : 200
     * result : {"pay_sn":"19120318435002710008","payment_code":"online"}
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
         * pay_sn : 19120318435002710008
         * payment_code : online
         */

        private String pay_sn;
        private String payment_code;

        public String getPay_sn() {
            return pay_sn;
        }

        public void setPay_sn(String pay_sn) {
            this.pay_sn = pay_sn;
        }

        public String getPayment_code() {
            return payment_code;
        }

        public void setPayment_code(String payment_code) {
            this.payment_code = payment_code;
        }
    }
}
