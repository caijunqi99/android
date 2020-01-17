package com.example.green.bean.mine;

import java.util.List;

/**
 * @ProjectName: Green
 * @Package: com.example.green.bean.mine
 * @ClassName: LogisticsInfobean
 * @Author: hjl
 * @email: 51721997@163.com
 * @Description: 商品物流信息
 * @CreateDate: 2020/1/6 9:48
 */
public class LogisticsInfobean {

    /**
     * code : 200
     * result : [{"AcceptStation":"客户签收人: 本人签收 已签收  感谢使用圆通速递，期待再次为您服务 如有疑问请联系：13521841248，投诉电话：15354067415","AcceptTime":"2019-12-07 18:10:05"},{"AcceptStation":"圆通合作点【熊猫快收】快件已到达星光公馆17号楼东侧小转门东50米驿站,联系电话18513662186","AcceptTime":"2019-12-06 10:18:53"},{"AcceptStation":"星光公馆17号楼东侧小转门东50米熊猫快收已发出自提通知，请上门自提，联系电话18513662186","AcceptTime":"2019-12-06 10:18:53"},{"AcceptStation":"【北京东燕郊开发区公司】 派件中  派件人: 杨春燕 电话 13521841248  如有疑问，请联系：15354067415","AcceptTime":"2019-12-06 07:51:29"},{"AcceptStation":"【北京东燕郊开发区】 已发出 下一站 【北京转运中心】","AcceptTime":"2019-12-06 07:05:05"},{"AcceptStation":"【北京东燕郊开发区公司】 已收入","AcceptTime":"2019-12-06 01:32:57"},{"AcceptStation":"【北京转运中心】 已发出 下一站 【北京东燕郊开发区】","AcceptTime":"2019-12-06 00:04:45"},{"AcceptStation":"【北京转运中心公司】 已收入","AcceptTime":"2019-12-05 23:51:20"},{"AcceptStation":"【义乌转运中心】 已发出 下一站 【北京转运中心】","AcceptTime":"2019-12-04 19:26:42"},{"AcceptStation":"【浙江省金华市义乌市万通公司】 已打包","AcceptTime":"2019-12-04 18:55:53"},{"AcceptStation":"【浙江省金华市义乌市万通公司】 已收件 取件人: 魏中远 (15382486687)","AcceptTime":"2019-12-04 18:26:37"}]
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
         * AcceptStation : 客户签收人: 本人签收 已签收  感谢使用圆通速递，期待再次为您服务 如有疑问请联系：13521841248，投诉电话：15354067415
         * AcceptTime : 2019-12-07 18:10:05
         */

        private String AcceptStation;
        private String AcceptTime;

        public String getAcceptStation() {
            return AcceptStation;
        }

        public void setAcceptStation(String AcceptStation) {
            this.AcceptStation = AcceptStation;
        }

        public String getAcceptTime() {
            return AcceptTime;
        }

        public void setAcceptTime(String AcceptTime) {
            this.AcceptTime = AcceptTime;
        }
    }
}
