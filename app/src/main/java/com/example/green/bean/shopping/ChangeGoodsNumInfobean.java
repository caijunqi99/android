package com.example.green.bean.shopping;

/**
 * @ProjectName: Green
 * @Package: com.example.green.bean.shopping
 * @ClassName: ChangeGoodsNumInfobean
 * @Author: hjl
 * @email: 51721997@163.comshang
 * @Description: 修改购物车商品数量
 * @CreateDate: 2020/1/10 12:00
 */
public class ChangeGoodsNumInfobean {

    /**
     * code : 200
     * result : {"quantity":3,"update":0}
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
         * quantity : 3
         * update : 0
         */

        private int quantity;
        private int update;

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getUpdate() {
            return update;
        }

        public void setUpdate(int update) {
            this.update = update;
        }
    }
}
