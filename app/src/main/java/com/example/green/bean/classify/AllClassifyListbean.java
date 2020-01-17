package com.example.green.bean.classify;

import java.io.Serializable;
import java.util.List;

public class AllClassifyListbean implements Serializable {

    /**
     * code : 200
     * result : {"class_list":[{"gc_id":1,"gc_name":"水果","type_id":1,"type_name":"联动","gc_parent_id":0,"commis_rate":0,"gc_sort":255,"gc_virtual":0,"gc_title":null,"gc_keywords":"","gc_description":"","gc_show":1,"image":"https://shop.bayi-shop.com/uploads/home/common/category-pic-1.jpg","text":"核果仁果类/柑橘类/热带水果/浆果类/瓜果类/野果"},{"gc_id":4,"gc_name":"蔬菜","type_id":1,"type_name":"联动","gc_parent_id":0,"commis_rate":0,"gc_sort":255,"gc_virtual":0,"gc_title":null,"gc_keywords":"","gc_description":"","gc_show":1,"image":"https://shop.bayi-shop.com/uploads/home/common/category-pic-4.jpg","text":"薯芋类/叶菜类/葱蒜类/根茎类/瓜类/野菜特菜"},{"gc_id":8,"gc_name":"种苗","type_id":1,"type_name":"联动","gc_parent_id":0,"commis_rate":0,"gc_sort":255,"gc_virtual":0,"gc_title":null,"gc_keywords":"","gc_description":"","gc_show":1,"image":"https://shop.bayi-shop.com/uploads/home/common/category-pic-8.jpg","text":"水果苗/苗木种苗/禽畜种苗/药材种苗/蔬菜苗/水产种苗/花卉种苗/特种养殖种苗"},{"gc_id":9,"gc_name":"禽畜牧蛋肉","type_id":1,"type_name":"联动","gc_parent_id":0,"commis_rate":0,"gc_sort":255,"gc_virtual":0,"gc_title":null,"gc_keywords":"","gc_description":"","gc_show":1,"image":"https://shop.bayi-shop.com/uploads/home/common/category-pic-9.jpg","text":"活禽/活畜/蛋类/禽畜初加工品/鲜肉类/禽畜精加工品/肉类冻品/乳类"},{"gc_id":10,"gc_name":"水产","type_id":1,"type_name":"联动","gc_parent_id":0,"commis_rate":0,"gc_sort":255,"gc_virtual":0,"gc_title":null,"gc_keywords":"","gc_description":"","gc_show":1,"image":"https://shop.bayi-shop.com/uploads/home/common/category-pic-10.jpg","text":"淡水鱼类/虾类/贝类/海水鱼类/软体类/水产干货/水产加工品/两栖爬行类"},{"gc_id":11,"gc_name":"中药材","type_id":1,"type_name":"联动","gc_parent_id":0,"commis_rate":0,"gc_sort":255,"gc_virtual":0,"gc_title":null,"gc_keywords":"","gc_description":"","gc_show":1,"image":"https://shop.bayi-shop.com/uploads/home/common/category-pic-11.jpg","text":"中药根茎类/中药果实籽仁类/中药全草类/中药动物类/中药树皮类"},{"gc_id":12,"gc_name":"坚果干果","type_id":1,"type_name":"联动","gc_parent_id":0,"commis_rate":0,"gc_sort":255,"gc_virtual":0,"gc_title":null,"gc_keywords":"","gc_description":"","gc_show":1,"image":"https://shop.bayi-shop.com/uploads/home/common/category-pic-12.jpg","text":"坚果/干果"},{"gc_id":15,"gc_name":"农副/副食","type_id":1,"type_name":"联动","gc_parent_id":0,"commis_rate":0,"gc_sort":255,"gc_virtual":0,"gc_title":null,"gc_keywords":"","gc_description":"","gc_show":1,"image":"https://shop.bayi-shop.com/uploads/home/common/category-pic-15.jpg","text":"加工食品/粮油产品/调味品/干货/酒水饮品/粮油副产品"},{"gc_id":16,"gc_name":"粮油作物","type_id":1,"type_name":"联动","gc_parent_id":0,"commis_rate":0,"gc_sort":255,"gc_virtual":0,"gc_title":null,"gc_keywords":"","gc_description":"","gc_show":1,"image":"https://shop.bayi-shop.com/uploads/home/common/category-pic-16.jpg","text":"谷类作物/油类作物/豆类作物"},{"gc_id":468,"gc_name":"种子","type_id":1,"type_name":"联动","gc_parent_id":0,"commis_rate":0,"gc_sort":255,"gc_virtual":0,"gc_title":null,"gc_keywords":"","gc_description":"","gc_show":1,"image":"https://shop.bayi-shop.com/uploads/home/common/category-pic-468.jpg","text":"蔬菜种子/中药材种子/林木种子/水果种子/花卉种子/粮油种子/坚果种子"},{"gc_id":764,"gc_name":"食用菌","type_id":1,"type_name":"联动","gc_parent_id":0,"commis_rate":0,"gc_sort":255,"gc_virtual":0,"gc_title":null,"gc_keywords":"","gc_description":"","gc_show":1,"image":"https://shop.bayi-shop.com/uploads/home/common/category-pic-764.jpg","text":"食用菌鲜货/食用菌干货/食用菌菌棒"}]}
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

    public static class ResultBean implements Serializable {
        private List<ClassListBean> class_list;

        public List<ClassListBean> getClass_list() {
            return class_list;
        }

        public void setClass_list(List<ClassListBean> class_list) {
            this.class_list = class_list;
        }

        public static class ClassListBean implements Serializable {
            /**
             * gc_id : 1
             * gc_name : 水果
             * type_id : 1
             * type_name : 联动
             * gc_parent_id : 0
             * commis_rate : 0
             * gc_sort : 255
             * gc_virtual : 0
             * gc_title : null
             * gc_keywords :
             * gc_description :
             * gc_show : 1
             * image : https://shop.bayi-shop.com/uploads/home/common/category-pic-1.jpg
             * text : 核果仁果类/柑橘类/热带水果/浆果类/瓜果类/野果
             */

            private int gc_id;
            private String gc_name;
            private int type_id;
            private String type_name;
            private int gc_parent_id;
            private int commis_rate;
            private int gc_sort;
            private int gc_virtual;
            private Object gc_title;
            private String gc_keywords;
            private String gc_description;
            private int gc_show;
            private String image;
            private String text;
            private boolean chick;   //标识

            public boolean isChick() {
                return chick;
            }

            public void setChick(boolean chick) {
                this.chick = chick;
            }

            public int getGc_id() {
                return gc_id;
            }

            public void setGc_id(int gc_id) {
                this.gc_id = gc_id;
            }

            public String getGc_name() {
                return gc_name;
            }

            public void setGc_name(String gc_name) {
                this.gc_name = gc_name;
            }

            public int getType_id() {
                return type_id;
            }

            public void setType_id(int type_id) {
                this.type_id = type_id;
            }

            public String getType_name() {
                return type_name;
            }

            public void setType_name(String type_name) {
                this.type_name = type_name;
            }

            public int getGc_parent_id() {
                return gc_parent_id;
            }

            public void setGc_parent_id(int gc_parent_id) {
                this.gc_parent_id = gc_parent_id;
            }

            public int getCommis_rate() {
                return commis_rate;
            }

            public void setCommis_rate(int commis_rate) {
                this.commis_rate = commis_rate;
            }

            public int getGc_sort() {
                return gc_sort;
            }

            public void setGc_sort(int gc_sort) {
                this.gc_sort = gc_sort;
            }

            public int getGc_virtual() {
                return gc_virtual;
            }

            public void setGc_virtual(int gc_virtual) {
                this.gc_virtual = gc_virtual;
            }

            public Object getGc_title() {
                return gc_title;
            }

            public void setGc_title(Object gc_title) {
                this.gc_title = gc_title;
            }

            public String getGc_keywords() {
                return gc_keywords;
            }

            public void setGc_keywords(String gc_keywords) {
                this.gc_keywords = gc_keywords;
            }

            public String getGc_description() {
                return gc_description;
            }

            public void setGc_description(String gc_description) {
                this.gc_description = gc_description;
            }

            public int getGc_show() {
                return gc_show;
            }

            public void setGc_show(int gc_show) {
                this.gc_show = gc_show;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }
        }
    }
}
