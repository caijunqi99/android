package com.example.green.bean.shopping;

/**
 * @ProjectName: Green
 * @Package: com.example.green.bean.shopping
 * @ClassName: AddCartInfobean
 * @Author: hjl
 * @email: 51721997@163.com
 * @Description: 商品加入购物车
 * @CreateDate: 2019/12/31 10:48
 */
public class AddCartInfobean {

    /**
     * code : 200
     * result : {"state":"true","num":4,"amount":"18792.00"}
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
         * state : true
         * num : 4
         * amount : 18792.00
         */

        private String state;
        private int num;
        private String amount;

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
    }
}
