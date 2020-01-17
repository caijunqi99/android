package com.example.green.bean.mine.wallet;

import java.util.List;

/**
 * @ProjectName: Green
 * @Package: com.example.green.bean.mine.wallet
 * @ClassName: StoredValueListbean
 * @Author: hjl
 * @email: 51721997@163.com
 * @Description: 储值卡交易明细
 * @CreateDate: 2020/1/3 11:39
 */
public class StoredValueListbean {

    /**
     * code : 200
     * result : {"list":[{"lg_id":222,"lg_member_id":17,"lg_member_name":"精神小伙子","lg_admin_name":null,"lg_type":"order_pay","lg_av_amount":"-10.00","lg_freeze_amount":"0.00","lg_addtime":1577979284,"lg_desc":"储值卡减少-10.00元","lg_add_time_text":"2020-01-02"},{"lg_id":213,"lg_member_id":17,"lg_member_name":"精神小伙子","lg_admin_name":null,"lg_type":"order_pay","lg_av_amount":"-10.00","lg_freeze_amount":"0.00","lg_addtime":1577961522,"lg_desc":"储值卡减少-10.00元","lg_add_time_text":"2020-01-02"}]}
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
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * lg_id : 222
             * lg_member_id : 17
             * lg_member_name : 精神小伙子
             * lg_admin_name : null
             * lg_type : order_pay
             * lg_av_amount : -10.00
             * lg_freeze_amount : 0.00
             * lg_addtime : 1577979284
             * lg_desc : 储值卡减少-10.00元
             * lg_add_time_text : 2020-01-02
             */

            private int lg_id;
            private int lg_member_id;
            private String lg_member_name;
            private Object lg_admin_name;
            private String lg_type;
            private String lg_av_amount;
            private String lg_freeze_amount;
            private int lg_addtime;
            private String lg_desc;
            private String lg_add_time_text;

            public int getLg_id() {
                return lg_id;
            }

            public void setLg_id(int lg_id) {
                this.lg_id = lg_id;
            }

            public int getLg_member_id() {
                return lg_member_id;
            }

            public void setLg_member_id(int lg_member_id) {
                this.lg_member_id = lg_member_id;
            }

            public String getLg_member_name() {
                return lg_member_name;
            }

            public void setLg_member_name(String lg_member_name) {
                this.lg_member_name = lg_member_name;
            }

            public Object getLg_admin_name() {
                return lg_admin_name;
            }

            public void setLg_admin_name(Object lg_admin_name) {
                this.lg_admin_name = lg_admin_name;
            }

            public String getLg_type() {
                return lg_type;
            }

            public void setLg_type(String lg_type) {
                this.lg_type = lg_type;
            }

            public String getLg_av_amount() {
                return lg_av_amount;
            }

            public void setLg_av_amount(String lg_av_amount) {
                this.lg_av_amount = lg_av_amount;
            }

            public String getLg_freeze_amount() {
                return lg_freeze_amount;
            }

            public void setLg_freeze_amount(String lg_freeze_amount) {
                this.lg_freeze_amount = lg_freeze_amount;
            }

            public int getLg_addtime() {
                return lg_addtime;
            }

            public void setLg_addtime(int lg_addtime) {
                this.lg_addtime = lg_addtime;
            }

            public String getLg_desc() {
                return lg_desc;
            }

            public void setLg_desc(String lg_desc) {
                this.lg_desc = lg_desc;
            }

            public String getLg_add_time_text() {
                return lg_add_time_text;
            }

            public void setLg_add_time_text(String lg_add_time_text) {
                this.lg_add_time_text = lg_add_time_text;
            }
        }
    }
}
