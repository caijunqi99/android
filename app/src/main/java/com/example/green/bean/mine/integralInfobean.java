package com.example.green.bean.mine;

import java.util.List;

/**
 * @ProjectName: Green
 * @Package: com.example.green.bean.mine
 * @ClassName: integralInfobean
 * @Author: hjl
 * @email: 51721997@163.com
 * @Description: java类作用描述
 * @CreateDate: 2020/1/2 10:42
 */
public class integralInfobean {

    /**
     * code : 200
     * result : {"log_list":[{"pl_id":"127","pl_memberid":"11","pl_membername":"caijunqi","pl_adminid":"null","pl_adminname":"admin","pl_points":"5.00","pl_pointsav":"0.00","pl_addtime":"1575023712","pl_desc":"冻结积分增加5.00积分","pl_stage":"system","addtimetext":"2019-11-29"}]}
     * message : ok
     * hasmore : true
     * page_total : 3
     */

    private String code;
    private ResultBean result;
    private String message;
    private String hasmore;
    private String page_total;

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

    public String getHasmore() {
        return hasmore;
    }

    public void setHasmore(String hasmore) {
        this.hasmore = hasmore;
    }

    public String getPage_total() {
        return page_total;
    }

    public void setPage_total(String page_total) {
        this.page_total = page_total;
    }

    public static class ResultBean {
        private List<LogListBean> log_list;

        public List<LogListBean> getLog_list() {
            return log_list;
        }

        public void setLog_list(List<LogListBean> log_list) {
            this.log_list = log_list;
        }

        public static class LogListBean {
            /**
             * pl_id : 127
             * pl_memberid : 11
             * pl_membername : caijunqi
             * pl_adminid : null
             * pl_adminname : admin
             * pl_points : 5.00
             * pl_pointsav : 0.00
             * pl_addtime : 1575023712
             * pl_desc : 冻结积分增加5.00积分
             * pl_stage : system
             * addtimetext : 2019-11-29
             */

            private String pl_id;
            private String pl_memberid;
            private String pl_membername;
            private String pl_adminid;
            private String pl_adminname;
            private String pl_points;
            private String pl_pointsav;
            private String pl_addtime;
            private String pl_desc;
            private String pl_stage;
            private String addtimetext;

            public String getPl_id() {
                return pl_id;
            }

            public void setPl_id(String pl_id) {
                this.pl_id = pl_id;
            }

            public String getPl_memberid() {
                return pl_memberid;
            }

            public void setPl_memberid(String pl_memberid) {
                this.pl_memberid = pl_memberid;
            }

            public String getPl_membername() {
                return pl_membername;
            }

            public void setPl_membername(String pl_membername) {
                this.pl_membername = pl_membername;
            }

            public String getPl_adminid() {
                return pl_adminid;
            }

            public void setPl_adminid(String pl_adminid) {
                this.pl_adminid = pl_adminid;
            }

            public String getPl_adminname() {
                return pl_adminname;
            }

            public void setPl_adminname(String pl_adminname) {
                this.pl_adminname = pl_adminname;
            }

            public String getPl_points() {
                return pl_points;
            }

            public void setPl_points(String pl_points) {
                this.pl_points = pl_points;
            }

            public String getPl_pointsav() {
                return pl_pointsav;
            }

            public void setPl_pointsav(String pl_pointsav) {
                this.pl_pointsav = pl_pointsav;
            }

            public String getPl_addtime() {
                return pl_addtime;
            }

            public void setPl_addtime(String pl_addtime) {
                this.pl_addtime = pl_addtime;
            }

            public String getPl_desc() {
                return pl_desc;
            }

            public void setPl_desc(String pl_desc) {
                this.pl_desc = pl_desc;
            }

            public String getPl_stage() {
                return pl_stage;
            }

            public void setPl_stage(String pl_stage) {
                this.pl_stage = pl_stage;
            }

            public String getAddtimetext() {
                return addtimetext;
            }

            public void setAddtimetext(String addtimetext) {
                this.addtimetext = addtimetext;
            }
        }
    }
}
