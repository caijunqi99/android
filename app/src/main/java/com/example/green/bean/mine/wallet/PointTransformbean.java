package com.example.green.bean.mine.wallet;

/**
 * @ProjectName: Green
 * @Package: com.example.green.bean.mine.wallet
 * @ClassName: PointTransformbean
 * @Author: hjl
 * @email: 51721997@163.com
 * @Description: 可用积分转换为交易码
 * @CreateDate: 2020/1/3 17:44
 */
public class PointTransformbean {

    /**
     * code : 200
     * result : {"state":true}
     * message : 本次申请互转成功！交易码【增加】 20 ,积分【减少】20，本次交易转换比率为 【1：1】
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

        private boolean state;

        public boolean isState() {
            return state;
        }

        public void setState(boolean state) {
            this.state = state;
        }
    }
}
