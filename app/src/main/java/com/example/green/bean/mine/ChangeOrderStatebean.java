package com.example.green.bean.mine;

/**
 * @ProjectName: Green
 * @Package: com.example.green.bean.mine
 * @ClassName: ChangeOrderStatebean
 * @Author: hjl
 * @email: 51721997@163.com
 * @Description: 修改订单状态
 * @CreateDate: 2019/12/31 14:38
 */
public class ChangeOrderStatebean {

    /**
     * code : 200
     * result : {"state":"true"}
     * message : 操作成功
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
         * state : true
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
