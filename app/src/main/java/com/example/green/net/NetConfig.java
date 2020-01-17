package com.example.green.net;

public class NetConfig {
    //http://front.api.movienow.xtech.fun/ movie/movieList
    public static String Url_TEST = "https://shop.bayi-shop.com/mobile/"; //测试
    public static String Url_SHOPPING = "https://shop.bayi-shop.com/index.php/mobile/"; // 订单流程
    public static String Url_Upload = "https://shop.bayi-shop.com/mobile/member/upload/"; // 上传图片

    public static String HTTPS_SPORTURL = "https://sport-data.dqdgame.com/";
    public static String HTTPS_API = "https://api.dongqiudi.com/";
    public static String INSURL = "https://bkbapi.dqdgame.com/app/tabs/";

    public static String HTTPS_URL;
    public static String HTTP_SPORTURL;
    public static int TYPE = 1;
    public static int SPORT_TYPE = 1;

    static {
        if (TYPE == 1) {
            HTTPS_URL = "https://bkbapi.dqdgame.com/app/tabs/android/";
        } else if (TYPE == 2) {
            HTTPS_URL = "";
        }

        if (SPORT_TYPE == 1) {
            HTTP_SPORTURL = "http://sport-data.dqdgame.com/";
        } else if (SPORT_TYPE == 2) {
            HTTP_SPORTURL = "";
        }
    }
}
