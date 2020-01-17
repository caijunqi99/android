package com.example.green.bean.pay;

/**
 * @ProjectName: Green
 * @Package: com.example.green.bean.pay
 * @ClassName: RemittanceInfobean
 * @Author: hjl
 * @email: 51721997@163.com
 * @Description: 线下汇款信息
 * @CreateDate: 2020/1/16 15:00
 */
public class RemittanceInfobean {

    /**
     * code : 200
     * result : {"name":"兵赢天下网络有限公司","bank":"中国建设银行股份有限公司松原江南支行","account":"22050167393800000760","code":"HQCKN"}
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
         * name : 兵赢天下网络有限公司
         * bank : 中国建设银行股份有限公司松原江南支行
         * account : 22050167393800000760
         * code : HQCKN
         */

        private String name;
        private String bank;
        private String account;
        private String code;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBank() {
            return bank;
        }

        public void setBank(String bank) {
            this.bank = bank;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
