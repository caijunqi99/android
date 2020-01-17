package com.example.green.bean.pay;

import java.util.List;

/**
 * @ProjectName: Green
 * @Package: com.example.green.bean.shopping
 * @ClassName: AcquireOrderInfobean
 * @Author: hjl
 * @email: 51721997@163.com
 * @Description: 购买商品--第三步，获取订单信息
 * @CreateDate: 2019/12/27 18:14
 */
public class AcquireOrderInfobean {

    /**
     * code : 200
     * result : {"pay_info":{"pay_amount":"2994.00","member_available_pd":"6008.00","member_available_rcb":"0.00","member_paypwd":true,"pay_sn":"19120318435002710008","payed_amount":"0.00","payment_list":[{"payment_code":"predeposit","payment_name":"储值卡支付","payment_config":null,"payment_platform":"app","payment_state":"1"}]}}
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
         * pay_info : {"pay_amount":"2994.00","member_available_pd":"6008.00","member_available_rcb":"0.00","member_paypwd":true,"pay_sn":"19120318435002710008","payed_amount":"0.00","payment_list":[{"payment_code":"predeposit","payment_name":"储值卡支付","payment_config":null,"payment_platform":"app","payment_state":"1"}]}
         */

        private PayInfoBean pay_info;

        public PayInfoBean getPay_info() {
            return pay_info;
        }

        public void setPay_info(PayInfoBean pay_info) {
            this.pay_info = pay_info;
        }

        public static class PayInfoBean {
            /**
             * pay_amount : 2994.00
             * member_available_pd : 6008.00
             * member_available_rcb : 0.00
             * member_paypwd : true
             * pay_sn : 19120318435002710008
             * payed_amount : 0.00
             * payment_list : [{"payment_code":"predeposit","payment_name":"储值卡支付","payment_config":null,"payment_platform":"app","payment_state":"1"}]
             */

            private String pay_amount;
            private String member_available_pd;
            private String member_available_rcb;
            private boolean member_paypwd;
            private String pay_sn;
            private String payed_amount;
            private List<PaymentListBean> payment_list;

            public String getPay_amount() {
                return pay_amount;
            }

            public void setPay_amount(String pay_amount) {
                this.pay_amount = pay_amount;
            }

            public String getMember_available_pd() {
                return member_available_pd;
            }

            public void setMember_available_pd(String member_available_pd) {
                this.member_available_pd = member_available_pd;
            }

            public String getMember_available_rcb() {
                return member_available_rcb;
            }

            public void setMember_available_rcb(String member_available_rcb) {
                this.member_available_rcb = member_available_rcb;
            }

            public boolean isMember_paypwd() {
                return member_paypwd;
            }

            public void setMember_paypwd(boolean member_paypwd) {
                this.member_paypwd = member_paypwd;
            }

            public String getPay_sn() {
                return pay_sn;
            }

            public void setPay_sn(String pay_sn) {
                this.pay_sn = pay_sn;
            }

            public String getPayed_amount() {
                return payed_amount;
            }

            public void setPayed_amount(String payed_amount) {
                this.payed_amount = payed_amount;
            }

            public List<PaymentListBean> getPayment_list() {
                return payment_list;
            }

            public void setPayment_list(List<PaymentListBean> payment_list) {
                this.payment_list = payment_list;
            }

            public static class PaymentListBean {
                /**
                 * payment_code : predeposit
                 * payment_name : 储值卡支付
                 * payment_config : null
                 * payment_platform : app
                 * payment_state : 1
                 */

                private String payment_code;
                private String payment_name;
                private Object payment_config;
                private String payment_platform;
                private String payment_state;

                public String getPayment_code() {
                    return payment_code;
                }

                public void setPayment_code(String payment_code) {
                    this.payment_code = payment_code;
                }

                public String getPayment_name() {
                    return payment_name;
                }

                public void setPayment_name(String payment_name) {
                    this.payment_name = payment_name;
                }

                public Object getPayment_config() {
                    return payment_config;
                }

                public void setPayment_config(Object payment_config) {
                    this.payment_config = payment_config;
                }

                public String getPayment_platform() {
                    return payment_platform;
                }

                public void setPayment_platform(String payment_platform) {
                    this.payment_platform = payment_platform;
                }

                public String getPayment_state() {
                    return payment_state;
                }

                public void setPayment_state(String payment_state) {
                    this.payment_state = payment_state;
                }
            }
        }
    }
}
