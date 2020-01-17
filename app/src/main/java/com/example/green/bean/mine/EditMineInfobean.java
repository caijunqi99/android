package com.example.green.bean.mine;

/**
 * @ProjectName: Green
 * @Package: com.example.green.bean.mine
 * @ClassName: EditMineInfobean
 * @Author: hjl
 * @email: 51721997@163.com
 * @Description: 个人资料编辑bean类
 * @CreateDate: 2019/12/25 11:06
 */
public class EditMineInfobean {

    /**
     * code : 200
     * result : {"member_msg":"修改信息"}
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
         * member_msg : 修改信息
         */

        private String member_msg;

        public String getMember_msg() {
            return member_msg;
        }

        public void setMember_msg(String member_msg) {
            this.member_msg = member_msg;
        }
    }
}
