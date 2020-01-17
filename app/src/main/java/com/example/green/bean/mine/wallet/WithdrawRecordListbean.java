package com.example.green.bean.mine.wallet;

import java.util.List;

/**
 * @ProjectName: Green
 * @Package: com.example.green.bean.mine.wallet
 * @ClassName: WithdrawRecordListbean
 * @Author: hjl
 * @email: 51721997@163.com
 * @Description: 提现记录bean类
 * @CreateDate: 2020/1/3 16:07
 */
public class WithdrawRecordListbean {

    /**
     * code : 200
     * hasmore : false
     * message : ok
     * page_total : 1
     * result : {"list":[{"pdc_add_time_text":"2020-01-03 17:27:06","pdc_addtime":1578043626,"pdc_amount":"93.50","pdc_bank_name":"明铁沟","pdc_bank_no":"649487696","pdc_bank_user":"胡嘉乐","pdc_id":5,"pdc_member_id":17,"pdc_member_name":"精神小伙子","pdc_payment_state":"0","pdc_payment_state_text":"未支付","pdc_payment_time_text":"1970-01-01 08:00:00","pdc_sn":"20010317270698379000"},{"pdc_add_time_text":"2020-01-03 17:02:26","pdc_addtime":1578042146,"pdc_amount":"85.85","pdc_bank_name":"明铁沟","pdc_bank_no":"649487696","pdc_bank_user":"胡嘉乐","pdc_id":4,"pdc_member_id":17,"pdc_member_name":"精神小伙子","pdc_payment_state":"0","pdc_payment_state_text":"未支付","pdc_payment_time_text":"1970-01-01 08:00:00","pdc_sn":"20010317022627236000"}]}
     */

    private String code;
    private boolean hasmore;
    private String message;
    private int page_total;
    private ResultBean result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isHasmore() {
        return hasmore;
    }

    public void setHasmore(boolean hasmore) {
        this.hasmore = hasmore;
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

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * pdc_add_time_text : 2020-01-03 17:27:06
             * pdc_addtime : 1578043626
             * pdc_amount : 93.50
             * pdc_bank_name : 明铁沟
             * pdc_bank_no : 649487696
             * pdc_bank_user : 胡嘉乐
             * pdc_id : 5
             * pdc_member_id : 17
             * pdc_member_name : 精神小伙子
             * pdc_payment_state : 0
             * pdc_payment_state_text : 未支付
             * pdc_payment_time_text : 1970-01-01 08:00:00
             * pdc_sn : 20010317270698379000
             */

            private String pdc_add_time_text;
            private int pdc_addtime;
            private String pdc_amount;
            private String pdc_bank_name;
            private String pdc_bank_no;
            private String pdc_bank_user;
            private int pdc_id;
            private int pdc_member_id;
            private String pdc_member_name;
            private String pdc_payment_state;
            private String pdc_payment_state_text;
            private String pdc_payment_time_text;
            private String pdc_sn;

            public String getPdc_add_time_text() {
                return pdc_add_time_text;
            }

            public void setPdc_add_time_text(String pdc_add_time_text) {
                this.pdc_add_time_text = pdc_add_time_text;
            }

            public int getPdc_addtime() {
                return pdc_addtime;
            }

            public void setPdc_addtime(int pdc_addtime) {
                this.pdc_addtime = pdc_addtime;
            }

            public String getPdc_amount() {
                return pdc_amount;
            }

            public void setPdc_amount(String pdc_amount) {
                this.pdc_amount = pdc_amount;
            }

            public String getPdc_bank_name() {
                return pdc_bank_name;
            }

            public void setPdc_bank_name(String pdc_bank_name) {
                this.pdc_bank_name = pdc_bank_name;
            }

            public String getPdc_bank_no() {
                return pdc_bank_no;
            }

            public void setPdc_bank_no(String pdc_bank_no) {
                this.pdc_bank_no = pdc_bank_no;
            }

            public String getPdc_bank_user() {
                return pdc_bank_user;
            }

            public void setPdc_bank_user(String pdc_bank_user) {
                this.pdc_bank_user = pdc_bank_user;
            }

            public int getPdc_id() {
                return pdc_id;
            }

            public void setPdc_id(int pdc_id) {
                this.pdc_id = pdc_id;
            }

            public int getPdc_member_id() {
                return pdc_member_id;
            }

            public void setPdc_member_id(int pdc_member_id) {
                this.pdc_member_id = pdc_member_id;
            }

            public String getPdc_member_name() {
                return pdc_member_name;
            }

            public void setPdc_member_name(String pdc_member_name) {
                this.pdc_member_name = pdc_member_name;
            }

            public String getPdc_payment_state() {
                return pdc_payment_state;
            }

            public void setPdc_payment_state(String pdc_payment_state) {
                this.pdc_payment_state = pdc_payment_state;
            }

            public String getPdc_payment_state_text() {
                return pdc_payment_state_text;
            }

            public void setPdc_payment_state_text(String pdc_payment_state_text) {
                this.pdc_payment_state_text = pdc_payment_state_text;
            }

            public String getPdc_payment_time_text() {
                return pdc_payment_time_text;
            }

            public void setPdc_payment_time_text(String pdc_payment_time_text) {
                this.pdc_payment_time_text = pdc_payment_time_text;
            }

            public String getPdc_sn() {
                return pdc_sn;
            }

            public void setPdc_sn(String pdc_sn) {
                this.pdc_sn = pdc_sn;
            }
        }
    }
}
