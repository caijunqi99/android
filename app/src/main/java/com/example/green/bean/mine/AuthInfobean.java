package com.example.green.bean.mine;

/**
 * @ProjectName: Green
 * @Package: com.example.green.bean.mine
 * @ClassName: AuthInfobean
 * @Author: hjl
 * @email: 51721997@163.com
 * @Description: java类作用描述
 * @CreateDate: 2020/1/2 10:51
 */
public class AuthInfobean {

    /**
     * code : 200
     * result : {"member_id":14,"member_auth_state":1,"idcard":"141033695695986656","member_provinceid":45067,"member_cityid":4566,"member_areaid":2717,"member_townid":0,"member_villageid":0,"member_areainfo":"河南 新乡市 原阳县 太平镇 后白寨村","member_idcard_image2":"http://shop.com/uploads/home/idcard_image/14_idcard_z.jpeg","member_idcard_image3":"http://shop.com/uploads/home/idcard_image/14_idcard_f.jpg","username":"阿狸","member_bankname":"建设银行","member_bankcard":"62170000365696568","member_idcard_image1":"http://shop.com/uploads/home/idcard_image/14_idcard_z.jpeg\n"}
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
         * member_id : 14
         * member_auth_state : 1
         * idcard : 141033695695986656
         * member_provinceid : 45067
         * member_cityid : 4566
         * member_areaid : 2717
         * member_townid : 0
         * member_villageid : 0
         * member_areainfo : 河南 新乡市 原阳县 太平镇 后白寨村
         * member_idcard_image2 : http://shop.com/uploads/home/idcard_image/14_idcard_z.jpeg
         * member_idcard_image3 : http://shop.com/uploads/home/idcard_image/14_idcard_f.jpg
         * username : 阿狸
         * member_bankname : 建设银行
         * member_bankcard : 62170000365696568
         * member_idcard_image1 : http://shop.com/uploads/home/idcard_image/14_idcard_z.jpeg
         */

        private int member_id;
        private int member_auth_state;
        private String idcard;
        private int member_provinceid;
        private int member_cityid;
        private int member_areaid;
        private int member_townid;
        private int member_villageid;
        private String member_areainfo;
        private String member_idcard_image2;
        private String member_idcard_image3;
        private String username;
        private String member_bankname;
        private String member_bankcard;
        private String member_idcard_image1;

        public int getMember_id() {
            return member_id;
        }

        public void setMember_id(int member_id) {
            this.member_id = member_id;
        }

        public int getMember_auth_state() {
            return member_auth_state;
        }

        public void setMember_auth_state(int member_auth_state) {
            this.member_auth_state = member_auth_state;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public int getMember_provinceid() {
            return member_provinceid;
        }

        public void setMember_provinceid(int member_provinceid) {
            this.member_provinceid = member_provinceid;
        }

        public int getMember_cityid() {
            return member_cityid;
        }

        public void setMember_cityid(int member_cityid) {
            this.member_cityid = member_cityid;
        }

        public int getMember_areaid() {
            return member_areaid;
        }

        public void setMember_areaid(int member_areaid) {
            this.member_areaid = member_areaid;
        }

        public int getMember_townid() {
            return member_townid;
        }

        public void setMember_townid(int member_townid) {
            this.member_townid = member_townid;
        }

        public int getMember_villageid() {
            return member_villageid;
        }

        public void setMember_villageid(int member_villageid) {
            this.member_villageid = member_villageid;
        }

        public String getMember_areainfo() {
            return member_areainfo;
        }

        public void setMember_areainfo(String member_areainfo) {
            this.member_areainfo = member_areainfo;
        }

        public String getMember_idcard_image2() {
            return member_idcard_image2;
        }

        public void setMember_idcard_image2(String member_idcard_image2) {
            this.member_idcard_image2 = member_idcard_image2;
        }

        public String getMember_idcard_image3() {
            return member_idcard_image3;
        }

        public void setMember_idcard_image3(String member_idcard_image3) {
            this.member_idcard_image3 = member_idcard_image3;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getMember_bankname() {
            return member_bankname;
        }

        public void setMember_bankname(String member_bankname) {
            this.member_bankname = member_bankname;
        }

        public String getMember_bankcard() {
            return member_bankcard;
        }

        public void setMember_bankcard(String member_bankcard) {
            this.member_bankcard = member_bankcard;
        }

        public String getMember_idcard_image1() {
            return member_idcard_image1;
        }

        public void setMember_idcard_image1(String member_idcard_image1) {
            this.member_idcard_image1 = member_idcard_image1;
        }
    }
}
