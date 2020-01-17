package com.example.green.bean.mine;

/**
 * @ProjectName: Green
 * @Package: com.example.green.bean.mine
 * @ClassName: WalletInfobean
 * @Author: hjl
 * @email: 51721997@163.com
 * @Description: java类作用描述
 * @CreateDate: 2019/12/30 18:43
 */
public class WalletInfobean {

    /**
     * code : 200
     * result : {"member_info":{"user_name":"caijunqi","avator":"http://shop.com/uploads/home/common/default_user_portrait.gif","member_points":"5.00","member_points_available":"211.00","available_predeposit":"100.00","member_transaction":"0.00","level_name":"银牌用户","mobile":"185****0658","inviter_code":"HQCKN","company_level":"市级子公司"}}
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
         * member_info : {"user_name":"caijunqi","avator":"http://shop.com/uploads/home/common/default_user_portrait.gif","member_points":"5.00","member_points_available":"211.00","available_predeposit":"100.00","member_transaction":"0.00","level_name":"银牌用户","mobile":"185****0658","inviter_code":"HQCKN","company_level":"市级子公司"}
         */

        private MemberInfoBean member_info;

        public MemberInfoBean getMember_info() {
            return member_info;
        }

        public void setMember_info(MemberInfoBean member_info) {
            this.member_info = member_info;
        }

        public static class MemberInfoBean {
            /**
             * user_name : caijunqi
             * avator : http://shop.com/uploads/home/common/default_user_portrait.gif
             * member_points : 5.00
             * member_points_available : 211.00
             * available_predeposit : 100.00
             * member_transaction : 0.00
             * level_name : 银牌用户
             * mobile : 185****0658
             * inviter_code : HQCKN
             * company_level : 市级子公司
             */

            private String user_name;
            private String avator;
            private String member_points;
            private String member_points_available;
            private String available_predeposit;
            private String member_transaction;
            private String level_name;
            private String mobile;
            private String inviter_code;
            private String company_level;

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public String getAvator() {
                return avator;
            }

            public void setAvator(String avator) {
                this.avator = avator;
            }

            public String getMember_points() {
                return member_points;
            }

            public void setMember_points(String member_points) {
                this.member_points = member_points;
            }

            public String getMember_points_available() {
                return member_points_available;
            }

            public void setMember_points_available(String member_points_available) {
                this.member_points_available = member_points_available;
            }

            public String getAvailable_predeposit() {
                return available_predeposit;
            }

            public void setAvailable_predeposit(String available_predeposit) {
                this.available_predeposit = available_predeposit;
            }

            public String getMember_transaction() {
                return member_transaction;
            }

            public void setMember_transaction(String member_transaction) {
                this.member_transaction = member_transaction;
            }

            public String getLevel_name() {
                return level_name;
            }

            public void setLevel_name(String level_name) {
                this.level_name = level_name;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getInviter_code() {
                return inviter_code;
            }

            public void setInviter_code(String inviter_code) {
                this.inviter_code = inviter_code;
            }

            public String getCompany_level() {
                return company_level;
            }

            public void setCompany_level(String company_level) {
                this.company_level = company_level;
            }
        }
    }
}
