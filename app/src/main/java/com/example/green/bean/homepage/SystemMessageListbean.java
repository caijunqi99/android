package com.example.green.bean.homepage;

import java.util.List;

/**
 * @ProjectName: Green
 * @Package: com.example.green.bean.homepage
 * @ClassName: SystemMessageListbean
 * @Author: hjl
 * @email: 51721997@163.com
 * @Description: 系统消息bean类
 * @CreateDate: 2019/12/20 13:41
 */
public class SystemMessageListbean {

    /**
     * code : 200
     * result : [{"message_id":66,"message_parent_id":0,"from_member_id":0,"to_member_id":"8","message_title":null,"message_body":"你的账户于 2019-12-03 18:04:50 账户资金有变化，描述：下单，支付储值卡，订单号: 2000000000003201，可用金额变化 ：-998.00元。","message_time":"1575367489","message_update_time":"1575367489","message_open":"0","message_state":0,"message_type":1,"read_member_id":"","del_member_id":"","message_ismore":0,"from_member_name":"系统消息","to_member_name":"","times":"2020-01-03 17:27:41"},{"message_id":67,"message_parent_id":0,"from_member_id":0,"to_member_id":"8","message_title":null,"message_body":"你的账户于 2019-12-03 16:36:29 账户资金有变化，描述：下单，支付储值卡，订单号: 2000000000002801，可用金额变化 ：-998.00元。","message_time":"1575362189","message_update_time":"1575362189","message_open":"0","message_state":0,"message_type":1,"read_member_id":"","del_member_id":"","message_ismore":0,"from_member_name":"系统消息","to_member_name":"","times":"2020-01-03 17:27:41"},{"message_id":68,"message_parent_id":0,"from_member_id":0,"to_member_id":"8","message_title":null,"message_body":"您的订单已经出库。<a href=\"/index.php/home/memberorder/show_order/order_id/28.html\" target=\"_blank\">点击查看订单<\/a>","message_time":"1575361438","message_update_time":"1575361438","message_open":"0","message_state":0,"message_type":1,"read_member_id":"","del_member_id":"","message_ismore":0,"from_member_name":"系统消息","to_member_name":"","times":"2020-01-03 17:27:41"},{"message_id":69,"message_parent_id":0,"from_member_id":0,"to_member_id":"8","message_title":null,"message_body":"你的账户于 2019-12-03 16:10:22 账户资金有变化，描述：下单，支付储值卡，订单号: 2000000000002901，可用金额变化 ：-998.00元。","message_time":"1575360622","message_update_time":"1575360622","message_open":"0","message_state":0,"message_type":1,"read_member_id":"","del_member_id":"","message_ismore":0,"from_member_name":"系统消息","to_member_name":"","times":"2020-01-03 17:27:41"},{"message_id":70,"message_parent_id":0,"from_member_id":0,"to_member_id":"8","message_title":null,"message_body":"您的订单已经出库。<a href=\"/index.php/home/memberorder/show_order/order_id/29.html\" target=\"_blank\">点击查看订单<\/a>","message_time":"1575359778","message_update_time":"1575359778","message_open":"0","message_state":0,"message_type":1,"read_member_id":"","del_member_id":"","message_ismore":0,"from_member_name":"系统消息","to_member_name":"","times":"2020-01-03 17:27:41"},{"message_id":71,"message_parent_id":0,"from_member_id":0,"to_member_id":"8","message_title":null,"message_body":"你的账户于 2019-12-03 15:37:22 账户资金有变化，描述：下单，支付储值卡，订单号: 2000000000003001，可用金额变化 ：-998.00元。","message_time":"1575358642","message_update_time":"1575358642","message_open":"0","message_state":0,"message_type":1,"read_member_id":"","del_member_id":"","message_ismore":0,"from_member_name":"系统消息","to_member_name":"","times":"2020-01-03 17:27:41"},{"message_id":72,"message_parent_id":0,"from_member_id":0,"to_member_id":"8","message_title":null,"message_body":"你的账户于 2019-12-02 12:30:49 账户资金有变化，描述：管理员调节储值卡【增加】，充值单号: 19120212304995782008,备注：，可用金额变化 ：10000.00元。","message_time":"1575261049","message_update_time":"1575261049","message_open":"0","message_state":0,"message_type":1,"read_member_id":"","del_member_id":"","message_ismore":0,"from_member_name":"系统消息","to_member_name":"","times":"2020-01-03 17:27:41"}]
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
         * message_id : 66
         * message_parent_id : 0
         * from_member_id : 0
         * to_member_id : 8
         * message_title : null
         * message_body : 你的账户于 2019-12-03 18:04:50 账户资金有变化，描述：下单，支付储值卡，订单号: 2000000000003201，可用金额变化 ：-998.00元。
         * message_time : 1575367489
         * message_update_time : 1575367489
         * message_open : 0
         * message_state : 0
         * message_type : 1
         * read_member_id :
         * del_member_id :
         * message_ismore : 0
         * from_member_name : 系统消息
         * to_member_name :
         * times : 2020-01-03 17:27:41
         */

        private int message_id;
        private int message_parent_id;
        private int from_member_id;
        private String to_member_id;
        private Object message_title;
        private String message_body;
        private String message_time;
        private String message_update_time;
        private String message_open;
        private int message_state;
        private int message_type;
        private String read_member_id;
        private String del_member_id;
        private int message_ismore;
        private String from_member_name;
        private String to_member_name;
        private String times;

        public int getMessage_id() {
            return message_id;
        }

        public void setMessage_id(int message_id) {
            this.message_id = message_id;
        }

        public int getMessage_parent_id() {
            return message_parent_id;
        }

        public void setMessage_parent_id(int message_parent_id) {
            this.message_parent_id = message_parent_id;
        }

        public int getFrom_member_id() {
            return from_member_id;
        }

        public void setFrom_member_id(int from_member_id) {
            this.from_member_id = from_member_id;
        }

        public String getTo_member_id() {
            return to_member_id;
        }

        public void setTo_member_id(String to_member_id) {
            this.to_member_id = to_member_id;
        }

        public Object getMessage_title() {
            return message_title;
        }

        public void setMessage_title(Object message_title) {
            this.message_title = message_title;
        }

        public String getMessage_body() {
            return message_body;
        }

        public void setMessage_body(String message_body) {
            this.message_body = message_body;
        }

        public String getMessage_time() {
            return message_time;
        }

        public void setMessage_time(String message_time) {
            this.message_time = message_time;
        }

        public String getMessage_update_time() {
            return message_update_time;
        }

        public void setMessage_update_time(String message_update_time) {
            this.message_update_time = message_update_time;
        }

        public String getMessage_open() {
            return message_open;
        }

        public void setMessage_open(String message_open) {
            this.message_open = message_open;
        }

        public int getMessage_state() {
            return message_state;
        }

        public void setMessage_state(int message_state) {
            this.message_state = message_state;
        }

        public int getMessage_type() {
            return message_type;
        }

        public void setMessage_type(int message_type) {
            this.message_type = message_type;
        }

        public String getRead_member_id() {
            return read_member_id;
        }

        public void setRead_member_id(String read_member_id) {
            this.read_member_id = read_member_id;
        }

        public String getDel_member_id() {
            return del_member_id;
        }

        public void setDel_member_id(String del_member_id) {
            this.del_member_id = del_member_id;
        }

        public int getMessage_ismore() {
            return message_ismore;
        }

        public void setMessage_ismore(int message_ismore) {
            this.message_ismore = message_ismore;
        }

        public String getFrom_member_name() {
            return from_member_name;
        }

        public void setFrom_member_name(String from_member_name) {
            this.from_member_name = from_member_name;
        }

        public String getTo_member_name() {
            return to_member_name;
        }

        public void setTo_member_name(String to_member_name) {
            this.to_member_name = to_member_name;
        }

        public String getTimes() {
            return times;
        }

        public void setTimes(String times) {
            this.times = times;
        }
    }
}
