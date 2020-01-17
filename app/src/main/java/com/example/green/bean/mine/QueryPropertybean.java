package com.example.green.bean.mine;

/**
 * @ProjectName: Green
 * @Package: com.example.green.bean.mine
 * @ClassName: QueryChuzhibean
 * @Author: hjl
 * @email: 51721997@163.com
 * @Description: 查询个人资产
 * @CreateDate: 2020/1/3 11:10
 */
public class QueryPropertybean {

    /**
     * code : 200
     * result : {"withdraw":"100","point":"20.00","available":"0.00","awable":0,"commission":"5","predepoit":"98952.60","transaction":"0.00","bankinfo":{"memberbank_name":"明铁沟","memberbank_no":"649487696","memberbank_truename":"胡嘉乐","member_mobile":"15993586215"}}
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
         * withdraw : 100
         * point : 20.00
         * available : 0.00
         * awable : 0
         * commission : 5
         * predepoit : 98952.60
         * transaction : 0.00
         * bankinfo : {"memberbank_name":"明铁沟","memberbank_no":"649487696","memberbank_truename":"胡嘉乐","member_mobile":"15993586215"}
         */

        private String withdraw;
        private String point;
        private String available;
        private double awable;
        private String commission;
        private String predepoit;
        private String transaction;
        private BankinfoBean bankinfo;

        public String getWithdraw() {
            return withdraw;
        }

        public void setWithdraw(String withdraw) {
            this.withdraw = withdraw;
        }

        public String getPoint() {
            return point;
        }

        public void setPoint(String point) {
            this.point = point;
        }

        public String getAvailable() {
            return available;
        }

        public void setAvailable(String available) {
            this.available = available;
        }

        public double getAwable() {
            return awable;
        }

        public void setAwable(int awable) {
            this.awable = awable;
        }

        public String getCommission() {
            return commission;
        }

        public void setCommission(String commission) {
            this.commission = commission;
        }

        public String getPredepoit() {
            return predepoit;
        }

        public void setPredepoit(String predepoit) {
            this.predepoit = predepoit;
        }

        public String getTransaction() {
            return transaction;
        }

        public void setTransaction(String transaction) {
            this.transaction = transaction;
        }

        public BankinfoBean getBankinfo() {
            return bankinfo;
        }

        public void setBankinfo(BankinfoBean bankinfo) {
            this.bankinfo = bankinfo;
        }

        public static class BankinfoBean {
            /**
             * memberbank_name : 明铁沟
             * memberbank_no : 649487696
             * memberbank_truename : 胡嘉乐
             * member_mobile : 15993586215
             */

            private String memberbank_name;
            private String memberbank_no;
            private String memberbank_truename;
            private String member_mobile;

            public String getMemberbank_name() {
                return memberbank_name;
            }

            public void setMemberbank_name(String memberbank_name) {
                this.memberbank_name = memberbank_name;
            }

            public String getMemberbank_no() {
                return memberbank_no;
            }

            public void setMemberbank_no(String memberbank_no) {
                this.memberbank_no = memberbank_no;
            }

            public String getMemberbank_truename() {
                return memberbank_truename;
            }

            public void setMemberbank_truename(String memberbank_truename) {
                this.memberbank_truename = memberbank_truename;
            }

            public String getMember_mobile() {
                return member_mobile;
            }

            public void setMember_mobile(String member_mobile) {
                this.member_mobile = member_mobile;
            }
        }
    }
}
