package com.example.green.bean.mine;

import java.util.List;

/**
 * @ProjectName: Green
 * @Package: com.example.green.bean.mine
 * @ClassName: ShoppingAddressListbean
 * @Author: hjl
 * @email: 51721997@163.com
 * @Description: 收货地址bean类
 * @CreateDate: 2019/12/18 11:20
 */
public class ShoppingAddressListbean {

    /**
     * code : 200
     * result : {"address_list":[{"address_id":"4","member_id":"11","address_realname":"ccc","city_id":"85","area_id":"1309","area_info":"山西 大同市 城区","address_detail":"收拾收拾","address_tel_phone":"","address_mob_phone":"15236615594","address_is_default":"1","dlyp_id":"0","address_longitude":"116.40387397","address_latitude":"39.91488908"},{"address_id":"3","member_id":"11","address_realname":"蔡俊岐","city_id":"36","area_id":"37","area_info":"北京\t北京市\t东城区","address_detail":"111","address_tel_phone":"","address_mob_phone":"18511310658","address_is_default":"0","dlyp_id":"0","address_longitude":"116.340051","address_latitude":"39.978328"}]}
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
        private List<AddressListBean> address_list;

        public List<AddressListBean> getAddress_list() {
            return address_list;
        }

        public void setAddress_list(List<AddressListBean> address_list) {
            this.address_list = address_list;
        }

        public static class AddressListBean {
            /**
             * address_id : 4
             * member_id : 11
             * address_realname : ccc
             * city_id : 85
             * area_id : 1309
             * area_info : 山西 大同市 城区
             * address_detail : 收拾收拾
             * address_tel_phone :
             * address_mob_phone : 15236615594
             * address_is_default : 1
             * dlyp_id : 0
             * address_longitude : 116.40387397
             * address_latitude : 39.91488908
             */

            private String address_id;
            private String member_id;
            private String address_realname;
            private String city_id;
            private String area_id;
            private String area_info;
            private String address_detail;
            private String address_tel_phone;
            private String address_mob_phone;
            private String address_is_default;
            private String dlyp_id;
            private String address_longitude;
            private String address_latitude;

            public String getAddress_id() {
                return address_id;
            }

            public void setAddress_id(String address_id) {
                this.address_id = address_id;
            }

            public String getMember_id() {
                return member_id;
            }

            public void setMember_id(String member_id) {
                this.member_id = member_id;
            }

            public String getAddress_realname() {
                return address_realname;
            }

            public void setAddress_realname(String address_realname) {
                this.address_realname = address_realname;
            }

            public String getCity_id() {
                return city_id;
            }

            public void setCity_id(String city_id) {
                this.city_id = city_id;
            }

            public String getArea_id() {
                return area_id;
            }

            public void setArea_id(String area_id) {
                this.area_id = area_id;
            }

            public String getArea_info() {
                return area_info;
            }

            public void setArea_info(String area_info) {
                this.area_info = area_info;
            }

            public String getAddress_detail() {
                return address_detail;
            }

            public void setAddress_detail(String address_detail) {
                this.address_detail = address_detail;
            }

            public String getAddress_tel_phone() {
                return address_tel_phone;
            }

            public void setAddress_tel_phone(String address_tel_phone) {
                this.address_tel_phone = address_tel_phone;
            }

            public String getAddress_mob_phone() {
                return address_mob_phone;
            }

            public void setAddress_mob_phone(String address_mob_phone) {
                this.address_mob_phone = address_mob_phone;
            }

            public String getAddress_is_default() {
                return address_is_default;
            }

            public void setAddress_is_default(String address_is_default) {
                this.address_is_default = address_is_default;
            }

            public String getDlyp_id() {
                return dlyp_id;
            }

            public void setDlyp_id(String dlyp_id) {
                this.dlyp_id = dlyp_id;
            }

            public String getAddress_longitude() {
                return address_longitude;
            }

            public void setAddress_longitude(String address_longitude) {
                this.address_longitude = address_longitude;
            }

            public String getAddress_latitude() {
                return address_latitude;
            }

            public void setAddress_latitude(String address_latitude) {
                this.address_latitude = address_latitude;
            }
        }
    }
}
