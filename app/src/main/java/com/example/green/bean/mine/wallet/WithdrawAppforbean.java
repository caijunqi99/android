package com.example.green.bean.mine.wallet;

/**
 * @ProjectName: Green
 * @Package: com.example.green.bean.mine.wallet
 * @ClassName: WithdrawAppforbean
 * @Author: hjl
 * @email: 51721997@163.com
 * @Description: 提现申请bean类
 * @CreateDate: 2020/1/3 15:28
 */
public class WithdrawAppforbean {

    /**
     * code : 200
     * result : {"pdc_amount":"84","pdc_bank_name":"建设银行","pdc_bank_no":"15555555555","pdc_bank_user":"阿狸"}
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
         * pdc_amount : 84
         * pdc_bank_name : 建设银行
         * pdc_bank_no : 15555555555
         * pdc_bank_user : 阿狸
         */

        private String pdc_amount;
        private String pdc_bank_name;
        private String pdc_bank_no;
        private String pdc_bank_user;

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
    }
}
