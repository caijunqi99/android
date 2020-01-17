package com.example.green.customs;

/**
 * 重新格式化H5代码，使图片自适应屏幕
 */
public class HtmlUtil {
    public static String getHtmlData(String bodyHTML) {

        String head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no\"> " +
                "<style>mip-img{max-width: 100%; height:auto;}*{font-family: Noto Sans CJK SC!improtant;}*{font-size: 1rem!important;line-height:22px;}" +
                "</style>" +
                "</head>";
        return "<html style=\"background: #fbfbfb;\">" + head + "<body>" + bodyHTML + "</body></html>";
    }

    public static String getReplaceString(String bodyHTML) {
        //替换代码中所有字体大小格式为统一的像素
        bodyHTML = bodyHTML.replaceAll("(<span\\b[^>]+\\bstyle=\"font-size:)[^\"]*\"[^>]*(>[\\s\\S]*?</span>)", "$1 1rem;\"$2");
        //去除代码中所有颜色格式
        bodyHTML = bodyHTML.replaceAll("<span\\b[^>]+\\bstyle=\"color:[^\"]*\"[^>]*>([\\s\\S]*?)</span>", "$1");
        bodyHTML = bodyHTML.replaceAll("<font\\b[^>]+\\bcolor=[^\"]*\"[^>]*>([\\s\\S]*?)</font>", "$1");
        return bodyHTML;
    }

    public static String getHtmlDataArticle(String bodyHTML) {
        String head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no\"> " +
                "<style>img{max-width: 100%; width:auto; height:auto;}*{font-family: Noto Sans CJK SC!improtant;}*{font-size: 1.05rem!important;line-height:34px;}" +
                "</style>" +
                "</head>";
        return "<html style=\"background: #ffffff; color: #151515;\">" + head + "<body>" + bodyHTML + "</body></html>";
    }

    public static String getReplaceStringArticle(String bodyHTML) {
        //去除代码中所有视频
        bodyHTML = bodyHTML.replaceAll("<embed[^>]*\\></embed>", "");
        //替换代码中所有图片的固定宽高为宽高自适应
        bodyHTML = bodyHTML.replaceAll("(<img[^>]{0,}height=\")([^>]+)(\"+[^>]{0,}/>)", "$1 auto $3");
        bodyHTML = bodyHTML.replaceAll("(<img[^>]{0,}width=\")([^>]+)(\"+[^>]{0,}/>)", "$1 100% $3");
        bodyHTML = bodyHTML.replaceAll("(<img[^>]{0,}height:)([^>]+)([^>]{0,}/>)", "$1 auto;\" $3");
        bodyHTML = bodyHTML.replaceAll("(<img[^>]{0,}width:)([^>]+)([^>]{0,}/>)", "$1 100%;\" $3");
        //去除代码中所有背景色格式
        bodyHTML = bodyHTML.replaceAll("<span\\b[^>]+\\bstyle=\"background-color:[^\"]*\"[^>]*>([\\s\\S]*?)</span>", "$1");
        //去除代码中所有超链接格式
        bodyHTML = bodyHTML.replaceAll("<a\\b[^>]+\\bhref=\"[^\"]*\"[^>]*>([<>\\=\"\"'';:\\s\\w])*<span[^>]*>([\\s\\S]*?)</span>([<>/=\"\"'';:\\s\\w])*</a>", "$2");
        bodyHTML = bodyHTML.replaceAll("<a\\b[^>]+\\bhref=\"[^\"]*\"[^>]*>([\\s\\S]*?)</a>", "$1");
        bodyHTML = bodyHTML.replaceAll("了解项目或转载内容请加微信：tcsdjmw，违规转载法律必究。", "");
        //去除代码中所有下划线格式
//        bodyHTML = bodyHTML.replaceAll("<u[^>]*\\>([\\s\\S]*?)</u>", "$1");
        //去除下划线
        bodyHTML = bodyHTML.replaceAll("(<\\/?a.*?>)|(<\\/?span.*?>)", "");
//        bodyHTML = bodyHTML.replaceAll("<span\\b[^>]+\\bstyle=\"text-decoration:[^\"]*\"[^>]*>([\\s\\S]*?)</span>", "$1");
        //去除代码中所有颜色格式
//        bodyHTML = bodyHTML.replaceAll("<span\\b[^>]+\\bstyle=\"color:[^\"]*\"[^>]*>([\\s\\S]*?)</span>", "$1");
        //替换代码中所有字体大小格式为统一的像素
        bodyHTML = bodyHTML.replaceAll("(<span\\b[^>]+\\bstyle=\"font-size:)[^\"]*\"[^>]*(>[\\s\\S]*?</span>)", "$1 1rem;\"$1");
        //替换代码中所有字体为思源黑体
        bodyHTML = bodyHTML.replaceAll("(<span\\b[^>]+\\bstyle=\"font-family:)[^\"]*\"[^>]*(>[\\s\\S]*?</span>)", "$1 Noto Sans CJK SC;\"$2");
        return bodyHTML;
    }
}
