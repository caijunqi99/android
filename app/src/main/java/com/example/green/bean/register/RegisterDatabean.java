package com.example.green.bean.register;

public class RegisterDatabean {

    /**
     * code : 200
     * result : {"username":"15236615591","userid":13,"key":"2c4ad4a8f77a5221474e90bb9e795c3c"}
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
         * username : 15236615591
         * userid : 13
         * key : 2c4ad4a8f77a5221474e90bb9e795c3c
         */

        private String username;
        private int userid;
        private String key;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }
}
