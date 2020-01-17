package com.example.green.bean.register;

public class AccquireSmsbean {

    /**
     * code : 200
     * result : {"sms_time":10}
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
         * sms_time : 10
         */

        private int sms_time;

        public int getSms_time() {
            return sms_time;
        }

        public void setSms_time(int sms_time) {
            this.sms_time = sms_time;
        }
    }
}
