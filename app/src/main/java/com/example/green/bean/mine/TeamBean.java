package com.example.green.bean.mine;

import java.util.List;

public class TeamBean {
    /**
     * code : 200
     * result : {"datainfo":[{"member_id":11,"member_mobile":"18511310658","level":"一代","member_exppoints":"1000","member_addtime":"2019-11-26 17:45:17"}],"countOne":3,"countAll":5,"inviterlink":"NZFHO"}
     * message : ok
     */

    private int code;
    private ResultBean result;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
         * datainfo : [{"member_id":11,"member_mobile":"18511310658","level":"一代","member_exppoints":"1000","member_addtime":"2019-11-26 17:45:17"}]
         * countOne : 3
         * countAll : 5
         * inviterlink : NZFHO
         */

        private int countOne;
        private int countAll;
        private String inviterlink;
        private List<DatainfoBean> datainfo;

        public int getCountOne() {
            return countOne;
        }

        public void setCountOne(int countOne) {
            this.countOne = countOne;
        }

        public int getCountAll() {
            return countAll;
        }

        public void setCountAll(int countAll) {
            this.countAll = countAll;
        }

        public String getInviterlink() {
            return inviterlink;
        }

        public void setInviterlink(String inviterlink) {
            this.inviterlink = inviterlink;
        }

        public List<DatainfoBean> getDatainfo() {
            return datainfo;
        }

        public void setDatainfo(List<DatainfoBean> datainfo) {
            this.datainfo = datainfo;
        }

        public static class DatainfoBean {
            /**
             * member_id : 11
             * member_mobile : 18511310658
             * level : 一代
             * member_exppoints : 1000
             * member_addtime : 2019-11-26 17:45:17
             */

            private int member_id;
            private String member_mobile;
            private String level;
            private String member_exppoints;
            private String member_addtime;

            public int getMember_id() {
                return member_id;
            }

            public void setMember_id(int member_id) {
                this.member_id = member_id;
            }

            public String getMember_mobile() {
                return member_mobile;
            }

            public void setMember_mobile(String member_mobile) {
                this.member_mobile = member_mobile;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public String getMember_exppoints() {
                return member_exppoints;
            }

            public void setMember_exppoints(String member_exppoints) {
                this.member_exppoints = member_exppoints;
            }

            public String getMember_addtime() {
                return member_addtime;
            }

            public void setMember_addtime(String member_addtime) {
                this.member_addtime = member_addtime;
            }
        }
    }
}
