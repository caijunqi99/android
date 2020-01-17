package com.example.green.bean.mine;

/**
 * @ProjectName: Green
 * @Package: com.example.green.bean.mine
 * @ClassName: PictureUploadBean
 * @Author: hjl
 * @email: 51721997@163.com
 * @Description: 用户上传头像bean类
 * @CreateDate: 2019/12/24 16:53
 */
public class PictureUploadBean {

    /**
     * code : 200
     * result : {"res":"1","avatar":"http://shop.com/uploads/home/avatar/avatar_11.jpg"}
     */

    private String code;
    private ResultBean result;

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

    public static class ResultBean {
        /**
         * res : 1
         * avatar : http://shop.com/uploads/home/avatar/avatar_11.jpg
         */

        private String res;
        private String avatar;

        public String getRes() {
            return res;
        }

        public void setRes(String res) {
            this.res = res;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }
}
