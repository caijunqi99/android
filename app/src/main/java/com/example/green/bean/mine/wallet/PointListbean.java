package com.example.green.bean.mine.wallet;

import java.util.List;

/**
 * @ProjectName: Green
 * @Package: com.example.green.adapter.mine.wallet
 * @ClassName: PointListbean
 * @Author: hjl
 * @email: 51721997@163.com
 * @Description: 积分交易明细
 * @CreateDate: 2020/1/3 12:24
 */
public class PointListbean {

    /**
     * code : 200
     * result : {"log_list":[{"pl_id":192,"pl_memberid":17,"pl_membername":"精神小伙子","pl_adminid":null,"pl_adminname":null,"pl_points":"10.00","pl_pointsav":"0.00","pl_addtime":1577979284,"pl_desc":"冻结积分增加10.00积分","pl_stage":"order","addtimetext":"2020-01-02"},{"pl_id":184,"pl_memberid":17,"pl_membername":"精神小伙子","pl_adminid":null,"pl_adminname":null,"pl_points":"10.00","pl_pointsav":"0.00","pl_addtime":1577961522,"pl_desc":"冻结积分增加10.00积分","pl_stage":"order","addtimetext":"2020-01-02"}]}
     * message : ok
     * page_total : 1
     * hasmore : false
     */

    private String code;
    private ResultBean result;
    private String message;
    private int page_total;
    private boolean hasmore;

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

    public int getPage_total() {
        return page_total;
    }

    public void setPage_total(int page_total) {
        this.page_total = page_total;
    }

    public boolean isHasmore() {
        return hasmore;
    }

    public void setHasmore(boolean hasmore) {
        this.hasmore = hasmore;
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
             * pl_id : 192
             * pl_memberid : 17
             * pl_membername : 精神小伙子
             * pl_adminid : null
             * pl_adminname : null
             * pl_points : 10.00
             * pl_pointsav : 0.00
             * pl_addtime : 1577979284
             * pl_desc : 冻结积分增加10.00积分
             * pl_stage : order
             * addtimetext : 2020-01-02
             */

            private int pl_id;
            private int pl_memberid;
            private String pl_membername;
            private Object pl_adminid;
            private Object pl_adminname;
            private String pl_points;
            private String pl_pointsav;
            private int pl_addtime;
            private String pl_desc;
            private String pl_stage;
            private String addtimetext;

            public int getPl_id() {
                return pl_id;
            }

            public void setPl_id(int pl_id) {
                this.pl_id = pl_id;
            }

            public int getPl_memberid() {
                return pl_memberid;
            }

            public void setPl_memberid(int pl_memberid) {
                this.pl_memberid = pl_memberid;
            }

            public String getPl_membername() {
                return pl_membername;
            }

            public void setPl_membername(String pl_membername) {
                this.pl_membername = pl_membername;
            }

            public Object getPl_adminid() {
                return pl_adminid;
            }

            public void setPl_adminid(Object pl_adminid) {
                this.pl_adminid = pl_adminid;
            }

            public Object getPl_adminname() {
                return pl_adminname;
            }

            public void setPl_adminname(Object pl_adminname) {
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

            public int getPl_addtime() {
                return pl_addtime;
            }

            public void setPl_addtime(int pl_addtime) {
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
