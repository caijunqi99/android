package com.example.green.bean.mine.wallet;

import java.util.List;

/**
 * @ProjectName: Green
 * @Package: com.example.green.bean.mine.wallet
 * @ClassName: DealCodeListbean
 * @Author: hjl
 * @email: 51721997@163.com
 * @Description: 交易码交易明细
 * @CreateDate: 2020/1/3 13:40
 */
public class DealCodeListbean {

    /**
     * code : 200
     * result : {"list":[{"tl_id":"37","tl_memberid":"11","tl_membername":"caijunqi","tl_adminid":"1","tl_adminname":"admin","tl_transaction":"1.00","tl_addtime":"1575352619","tl_desc":"管理员调节交易码【增加】，充值单号: 19120313565910228011,备注：","tl_stage":"system","tl_add_time_text":"2019-12-03 13:56:59"}]}
     * message : ok
     * page_total : 1
     * hasmore : false
     */

    private String code;
    private ResultBean result;
    private String message;
    private String page_total;
    private String hasmore;

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

    public String getPage_total() {
        return page_total;
    }

    public void setPage_total(String page_total) {
        this.page_total = page_total;
    }

    public String getHasmore() {
        return hasmore;
    }

    public void setHasmore(String hasmore) {
        this.hasmore = hasmore;
    }

    public static class ResultBean {
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * tl_id : 37
             * tl_memberid : 11
             * tl_membername : caijunqi
             * tl_adminid : 1
             * tl_adminname : admin
             * tl_transaction : 1.00
             * tl_addtime : 1575352619
             * tl_desc : 管理员调节交易码【增加】，充值单号: 19120313565910228011,备注：
             * tl_stage : system
             * tl_add_time_text : 2019-12-03 13:56:59
             */

            private String tl_id;
            private String tl_memberid;
            private String tl_membername;
            private String tl_adminid;
            private String tl_adminname;
            private String tl_transaction;
            private String tl_addtime;
            private String tl_desc;
            private String tl_stage;
            private String tl_add_time_text;

            public String getTl_id() {
                return tl_id;
            }

            public void setTl_id(String tl_id) {
                this.tl_id = tl_id;
            }

            public String getTl_memberid() {
                return tl_memberid;
            }

            public void setTl_memberid(String tl_memberid) {
                this.tl_memberid = tl_memberid;
            }

            public String getTl_membername() {
                return tl_membername;
            }

            public void setTl_membername(String tl_membername) {
                this.tl_membername = tl_membername;
            }

            public String getTl_adminid() {
                return tl_adminid;
            }

            public void setTl_adminid(String tl_adminid) {
                this.tl_adminid = tl_adminid;
            }

            public String getTl_adminname() {
                return tl_adminname;
            }

            public void setTl_adminname(String tl_adminname) {
                this.tl_adminname = tl_adminname;
            }

            public String getTl_transaction() {
                return tl_transaction;
            }

            public void setTl_transaction(String tl_transaction) {
                this.tl_transaction = tl_transaction;
            }

            public String getTl_addtime() {
                return tl_addtime;
            }

            public void setTl_addtime(String tl_addtime) {
                this.tl_addtime = tl_addtime;
            }

            public String getTl_desc() {
                return tl_desc;
            }

            public void setTl_desc(String tl_desc) {
                this.tl_desc = tl_desc;
            }

            public String getTl_stage() {
                return tl_stage;
            }

            public void setTl_stage(String tl_stage) {
                this.tl_stage = tl_stage;
            }

            public String getTl_add_time_text() {
                return tl_add_time_text;
            }

            public void setTl_add_time_text(String tl_add_time_text) {
                this.tl_add_time_text = tl_add_time_text;
            }
        }
    }
}
