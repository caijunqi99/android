package com.example.green.bean.store;

import java.util.List;

public class StoreClassListbean {

    /**
     * code : 200
     * result : [{"storeclass_id":1,"storeclass_name":"农副/副食","storeclass_bail":0,"storeclass_sort":8},{"storeclass_id":2,"storeclass_name":"水果","storeclass_bail":0,"storeclass_sort":1},{"storeclass_id":3,"storeclass_name":"蔬菜","storeclass_bail":0,"storeclass_sort":2},{"storeclass_id":4,"storeclass_name":"种苗","storeclass_bail":0,"storeclass_sort":3},{"storeclass_id":5,"storeclass_name":"禽畜牧蛋肉","storeclass_bail":0,"storeclass_sort":4},{"storeclass_id":6,"storeclass_name":"水产","storeclass_bail":0,"storeclass_sort":5},{"storeclass_id":7,"storeclass_name":"中药材","storeclass_bail":0,"storeclass_sort":6},{"storeclass_id":8,"storeclass_name":"坚果干果","storeclass_bail":0,"storeclass_sort":7},{"storeclass_id":9,"storeclass_name":"粮油作物","storeclass_bail":0,"storeclass_sort":9},{"storeclass_id":10,"storeclass_name":"种子","storeclass_bail":0,"storeclass_sort":10},{"storeclass_id":11,"storeclass_name":"食用菌","storeclass_bail":0,"storeclass_sort":11}]
     * message : ok
     */

    private String code;
    private String message;
    private List<ResultBean> result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * storeclass_id : 1
         * storeclass_name : 农副/副食
         * storeclass_bail : 0
         * storeclass_sort : 8
         */

        private int storeclass_id;
        private String storeclass_name;
        private int storeclass_bail;
        private int storeclass_sort;

        public int getStoreclass_id() {
            return storeclass_id;
        }

        public void setStoreclass_id(int storeclass_id) {
            this.storeclass_id = storeclass_id;
        }

        public String getStoreclass_name() {
            return storeclass_name;
        }

        public void setStoreclass_name(String storeclass_name) {
            this.storeclass_name = storeclass_name;
        }

        public int getStoreclass_bail() {
            return storeclass_bail;
        }

        public void setStoreclass_bail(int storeclass_bail) {
            this.storeclass_bail = storeclass_bail;
        }

        public int getStoreclass_sort() {
            return storeclass_sort;
        }

        public void setStoreclass_sort(int storeclass_sort) {
            this.storeclass_sort = storeclass_sort;
        }
    }
}
