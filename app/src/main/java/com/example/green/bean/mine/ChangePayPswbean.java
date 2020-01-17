package com.example.green.bean.mine;

/**
 * @ProjectName: Green
 * @Package: com.example.green.bean.mine
 * @ClassName: ChangePswbean
 * @Author: hjl
 * @email: 51721997@163.com
 * @Description: 修改支付密码bean
 * @CreateDate: 2019/12/20 16:55
 */
public class ChangePayPswbean {

    /**
     * code : 200
     * result : {"state":"false"}
     * message : 密码设置失败
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
         * state : false
         */

        private String state;

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }
}
