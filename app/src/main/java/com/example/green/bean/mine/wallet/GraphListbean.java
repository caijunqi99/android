package com.example.green.bean.mine.wallet;

import java.util.List;

/**
 * @ProjectName: Green
 * @Package: com.example.green.bean.mine.wallet
 * @ClassName: GraphListbean
 * @Author: hjl
 * @email: 51721997@163.com
 * @Description: 认筹股价格曲线图
 * @CreateDate: 2020/1/15 10:14
 */
public class GraphListbean {

    /**
     * code : 200
     * result : {"list":[{"t_id":"9","t_price":"1.00","t_addtime":"01-14"}]}
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
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * t_id : 9
             * t_price : 1.00
             * t_addtime : 01-14
             */

            private String t_id;
            private String t_price;
            private String t_addtime;

            public String getT_id() {
                return t_id;
            }

            public void setT_id(String t_id) {
                this.t_id = t_id;
            }

            public String getT_price() {
                return t_price;
            }

            public void setT_price(String t_price) {
                this.t_price = t_price;
            }

            public String getT_addtime() {
                return t_addtime;
            }

            public void setT_addtime(String t_addtime) {
                this.t_addtime = t_addtime;
            }
        }
    }
}
