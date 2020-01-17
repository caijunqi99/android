package com.example.green.bean.pay;

/**
 * @ProjectName: Green
 * @Package: com.example.green.bean.pay
 * @ClassName: AcquirePayCodebean
 * @Author: hjl
 * @email: 51721997@163.com
 * @Description: java类作用描述
 * @CreateDate: 2019/12/29 10:53
 */
public class AcquirePayCodebean {

    /**
     * code : 10000
     * message :
     * result : {"content":"alipay_sdk=alipay-sdk-php-20161101&app_id=2019121069859014&biz_content=%7B%22body%22%3A%22pd_order%22%2C%22subject%22%3A%22%E9%A2%84%E5%AD%98%E6%AC%BE%E5%85%85%E5%80%BC_19121220492215477000%22%2C%22out_trade_no%22%3A%22pd_order-19121220492215477000%22%2C%22total_amount%22%3A%2210.00%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fw.gw.com%2Fhome%2Fpayment%2Falipay_app_notify.html&sign_type=RSA2&timestamp=2019-12-12+20%3A49%3A23&version=1.0&sign=YSRxl9MGS54NHehh%2FSneuy7gw4fcA3z3cGyVqevJYYLhlz09qxzVuFYH%2BHV0Ec1SCicdcN9RzZ1p7fRdFaXe5Jc9%2BhHo8E3lytzRllByd6hJipN2LUvsETGFqZFisApvquKHK9WKrw3EbdJdwkNA3UpGw9AVrrOPL5tdPMaOgkoTkHS%2FdVwT6KMqaRV3XSbjjgSCWTzDK8b6oSlNkOn7AYW7DfOGFKfAzx4TFhx85SZISvqXd36ZZPcLpF52ScjCMg%2FiKGWgpy%2Btst0lA0PbH3DhnktkIbJSBMB244Qsczz9hDxR9pEZBGoy5uSBfEUUwgRv5Y63YVYAajlH84G%2F1w%3D%3D"}
     * requestMethod :
     */

    private int code;
    private String message;
    private ResultBean result;
    private String requestMethod;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public static class ResultBean {
        /**
         * content : alipay_sdk=alipay-sdk-php-20161101&app_id=2019121069859014&biz_content=%7B%22body%22%3A%22pd_order%22%2C%22subject%22%3A%22%E9%A2%84%E5%AD%98%E6%AC%BE%E5%85%85%E5%80%BC_19121220492215477000%22%2C%22out_trade_no%22%3A%22pd_order-19121220492215477000%22%2C%22total_amount%22%3A%2210.00%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fw.gw.com%2Fhome%2Fpayment%2Falipay_app_notify.html&sign_type=RSA2&timestamp=2019-12-12+20%3A49%3A23&version=1.0&sign=YSRxl9MGS54NHehh%2FSneuy7gw4fcA3z3cGyVqevJYYLhlz09qxzVuFYH%2BHV0Ec1SCicdcN9RzZ1p7fRdFaXe5Jc9%2BhHo8E3lytzRllByd6hJipN2LUvsETGFqZFisApvquKHK9WKrw3EbdJdwkNA3UpGw9AVrrOPL5tdPMaOgkoTkHS%2FdVwT6KMqaRV3XSbjjgSCWTzDK8b6oSlNkOn7AYW7DfOGFKfAzx4TFhx85SZISvqXd36ZZPcLpF52ScjCMg%2FiKGWgpy%2Btst0lA0PbH3DhnktkIbJSBMB244Qsczz9hDxR9pEZBGoy5uSBfEUUwgRv5Y63YVYAajlH84G%2F1w%3D%3D
         */

        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
