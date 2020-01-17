package com.example.green.bean.mine;

/**
 * @ProjectName: Green
 * @Package: com.example.green.bean.mine
 * @ClassName: Addsitebean
 * @Author: hjl
 * @email: 51721997@163.com
 * @Description: java类作用描述
 * @CreateDate: 2019/12/19 14:36
 */
public class Addsitebean {

    /**
     * code : 200
     * result : {"address_id":"2"}
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
         * address_id : 2
         */

        private String address_id;

        public String getAddress_id() {
            return address_id;
        }

        public void setAddress_id(String address_id) {
            this.address_id = address_id;
        }
    }
}
