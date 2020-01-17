package com.example.green.bean.homepage;

/**
 * @ProjectName: Green
 * @Package: com.example.green.bean.homepage
 * @ClassName: Versionbean
 * @Author: hjl
 * @email: 51721997@163.com
 * @Description: 版本号bean类
 * @CreateDate: 2019/12/20 14:00
 */
public class Versionbean {

    /**
     * code : 200
     * result : {"type":1,"content":"优化了某些功能","mode":1,"version_num":"1.1.3","url":"http://www.baidu.com/","channel":"sss","package_name":"sss","time":1535646546,"is_update":-1}
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
        /**
         * type : 1
         * content : 优化了某些功能
         * mode : 1
         * version_num : 1.1.3
         * url : http://www.baidu.com/
         * channel : sss
         * package_name : sss
         * time : 1535646546
         * is_update : -1
         */

        private int type;
        private String content;
        private int mode;
        private String version_num;
        private String url;
        private String channel;
        private String package_name;
        private int time;
        private int is_update;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getMode() {
            return mode;
        }

        public void setMode(int mode) {
            this.mode = mode;
        }

        public String getVersion_num() {
            return version_num;
        }

        public void setVersion_num(String version_num) {
            this.version_num = version_num;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public String getPackage_name() {
            return package_name;
        }

        public void setPackage_name(String package_name) {
            this.package_name = package_name;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public int getIs_update() {
            return is_update;
        }

        public void setIs_update(int is_update) {
            this.is_update = is_update;
        }
    }
}
