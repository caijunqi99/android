package com.example.green.bean.store;

import java.util.List;

public class StoreListbean {

    /**
     * code : 200
     * result : [{"store_id":1,"store_name":"自营店铺","is_platform_store":1,"seller_name":"seller","store_sort":1,"store_addtime":1572950123,"store_logo":"https://shop.bayi-shop.com/uploads/home/store/1/1_2019121216053246268.png","store_avatar":"https://shop.bayi-shop.com/uploads/home/store/1/1_2019121216013778963.png","num_sales_jq":3,"goods_count":41,"store_credit_percent":100,"store_credit":{"store_desccredit":{"text":"描述相符","credit":5,"percent":"----","percent_class":"equal","percent_text":"持平"},"store_servicecredit":{"text":"服务态度","credit":5,"percent":"----","percent_class":"equal","percent_text":"持平"},"store_deliverycredit":{"text":"发货速度","credit":5,"percent":"----","percent_class":"equal","percent_text":"持平"}},"search_list_goods":[{"goods_id":136,"store_id":1,"goods_name":"红薯红薯红薯红薯红薯","goods_image":"https://oss.bayi-shop.com/home/store/goods/1/alioss_1_2019121117450292048.jpg","goods_price":"10.00","goods_salenum":0},{"goods_id":138,"store_id":1,"goods_name":"桃树苗桃树苗","goods_image":"https://oss.bayi-shop.com/home/store/goods/1/alioss_1_2019121118424369048.jpg","goods_price":"5.00","goods_salenum":0},{"goods_id":139,"store_id":1,"goods_name":"土鸡土鸡土鸡","goods_image":"https://oss.bayi-shop.com/home/store/goods/1/alioss_1_2019121212590222769.jpg","goods_price":"20.00","goods_salenum":0},{"goods_id":140,"store_id":1,"goods_name":"草鱼草鱼草鱼","goods_image":"https://oss.bayi-shop.com/home/store/goods/1/alioss_1_2019121213002151769.jpg","goods_price":"10.00","goods_salenum":0},{"goods_id":141,"store_id":1,"goods_name":"人参人参人参","goods_image":"https://oss.bayi-shop.com/home/store/goods/1/alioss_1_2019121213255634668.jpg","goods_price":"100.00","goods_salenum":0},{"goods_id":142,"store_id":1,"goods_name":"苹果苹果苹果苹果 富士康 4kg","goods_image":"https://oss.bayi-shop.com/home/store/goods/1/alioss_1_2019121117365572753.jpg","goods_price":"20.00","goods_salenum":0},{"goods_id":143,"store_id":1,"goods_name":"苹果苹果苹果苹果 富士康 8kg","goods_image":"https://oss.bayi-shop.com/home/store/goods/1/alioss_1_2019121117365572753.jpg","goods_price":"20.00","goods_salenum":0},{"goods_id":144,"store_id":1,"goods_name":"梨子鸭梨梨子新鲜10斤砀山梨皇冠梨香梨翠冠梨当季水果新鲜雪梨 中果","goods_image":"https://oss.bayi-shop.com/home/store/goods/1/alioss_1_2019121301265027009.jpg","goods_price":"19.90","goods_salenum":0}]}]
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
         * store_id : 1
         * store_name : 自营店铺
         * is_platform_store : 1
         * seller_name : seller
         * store_sort : 1
         * store_addtime : 1572950123
         * store_logo : https://shop.bayi-shop.com/uploads/home/store/1/1_2019121216053246268.png
         * store_avatar : https://shop.bayi-shop.com/uploads/home/store/1/1_2019121216013778963.png
         * num_sales_jq : 3
         * goods_count : 41
         * store_credit_percent : 100
         * store_credit : {"store_desccredit":{"text":"描述相符","credit":5,"percent":"----","percent_class":"equal","percent_text":"持平"},"store_servicecredit":{"text":"服务态度","credit":5,"percent":"----","percent_class":"equal","percent_text":"持平"},"store_deliverycredit":{"text":"发货速度","credit":5,"percent":"----","percent_class":"equal","percent_text":"持平"}}
         * search_list_goods : [{"goods_id":136,"store_id":1,"goods_name":"红薯红薯红薯红薯红薯","goods_image":"https://oss.bayi-shop.com/home/store/goods/1/alioss_1_2019121117450292048.jpg","goods_price":"10.00","goods_salenum":0},{"goods_id":138,"store_id":1,"goods_name":"桃树苗桃树苗","goods_image":"https://oss.bayi-shop.com/home/store/goods/1/alioss_1_2019121118424369048.jpg","goods_price":"5.00","goods_salenum":0},{"goods_id":139,"store_id":1,"goods_name":"土鸡土鸡土鸡","goods_image":"https://oss.bayi-shop.com/home/store/goods/1/alioss_1_2019121212590222769.jpg","goods_price":"20.00","goods_salenum":0},{"goods_id":140,"store_id":1,"goods_name":"草鱼草鱼草鱼","goods_image":"https://oss.bayi-shop.com/home/store/goods/1/alioss_1_2019121213002151769.jpg","goods_price":"10.00","goods_salenum":0},{"goods_id":141,"store_id":1,"goods_name":"人参人参人参","goods_image":"https://oss.bayi-shop.com/home/store/goods/1/alioss_1_2019121213255634668.jpg","goods_price":"100.00","goods_salenum":0},{"goods_id":142,"store_id":1,"goods_name":"苹果苹果苹果苹果 富士康 4kg","goods_image":"https://oss.bayi-shop.com/home/store/goods/1/alioss_1_2019121117365572753.jpg","goods_price":"20.00","goods_salenum":0},{"goods_id":143,"store_id":1,"goods_name":"苹果苹果苹果苹果 富士康 8kg","goods_image":"https://oss.bayi-shop.com/home/store/goods/1/alioss_1_2019121117365572753.jpg","goods_price":"20.00","goods_salenum":0},{"goods_id":144,"store_id":1,"goods_name":"梨子鸭梨梨子新鲜10斤砀山梨皇冠梨香梨翠冠梨当季水果新鲜雪梨 中果","goods_image":"https://oss.bayi-shop.com/home/store/goods/1/alioss_1_2019121301265027009.jpg","goods_price":"19.90","goods_salenum":0}]
         */

        private int store_id;
        private String store_name;
        private int is_platform_store;
        private String seller_name;
        private int store_sort;
        private int store_addtime;
        private String store_logo;
        private String store_avatar;
        private int num_sales_jq;
        private int goods_count;
        private int store_credit_percent;
        private StoreCreditBean store_credit;
        private List<SearchListGoodsBean> search_list_goods;

        public int getStore_id() {
            return store_id;
        }

        public void setStore_id(int store_id) {
            this.store_id = store_id;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public int getIs_platform_store() {
            return is_platform_store;
        }

        public void setIs_platform_store(int is_platform_store) {
            this.is_platform_store = is_platform_store;
        }

        public String getSeller_name() {
            return seller_name;
        }

        public void setSeller_name(String seller_name) {
            this.seller_name = seller_name;
        }

        public int getStore_sort() {
            return store_sort;
        }

        public void setStore_sort(int store_sort) {
            this.store_sort = store_sort;
        }

        public int getStore_addtime() {
            return store_addtime;
        }

        public void setStore_addtime(int store_addtime) {
            this.store_addtime = store_addtime;
        }

        public String getStore_logo() {
            return store_logo;
        }

        public void setStore_logo(String store_logo) {
            this.store_logo = store_logo;
        }

        public String getStore_avatar() {
            return store_avatar;
        }

        public void setStore_avatar(String store_avatar) {
            this.store_avatar = store_avatar;
        }

        public int getNum_sales_jq() {
            return num_sales_jq;
        }

        public void setNum_sales_jq(int num_sales_jq) {
            this.num_sales_jq = num_sales_jq;
        }

        public int getGoods_count() {
            return goods_count;
        }

        public void setGoods_count(int goods_count) {
            this.goods_count = goods_count;
        }

        public int getStore_credit_percent() {
            return store_credit_percent;
        }

        public void setStore_credit_percent(int store_credit_percent) {
            this.store_credit_percent = store_credit_percent;
        }

        public StoreCreditBean getStore_credit() {
            return store_credit;
        }

        public void setStore_credit(StoreCreditBean store_credit) {
            this.store_credit = store_credit;
        }

        public List<SearchListGoodsBean> getSearch_list_goods() {
            return search_list_goods;
        }

        public void setSearch_list_goods(List<SearchListGoodsBean> search_list_goods) {
            this.search_list_goods = search_list_goods;
        }

        public static class StoreCreditBean {
            /**
             * store_desccredit : {"text":"描述相符","credit":5,"percent":"----","percent_class":"equal","percent_text":"持平"}
             * store_servicecredit : {"text":"服务态度","credit":5,"percent":"----","percent_class":"equal","percent_text":"持平"}
             * store_deliverycredit : {"text":"发货速度","credit":5,"percent":"----","percent_class":"equal","percent_text":"持平"}
             */

            private StoreDesccreditBean store_desccredit;
            private StoreServicecreditBean store_servicecredit;
            private StoreDeliverycreditBean store_deliverycredit;

            public StoreDesccreditBean getStore_desccredit() {
                return store_desccredit;
            }

            public void setStore_desccredit(StoreDesccreditBean store_desccredit) {
                this.store_desccredit = store_desccredit;
            }

            public StoreServicecreditBean getStore_servicecredit() {
                return store_servicecredit;
            }

            public void setStore_servicecredit(StoreServicecreditBean store_servicecredit) {
                this.store_servicecredit = store_servicecredit;
            }

            public StoreDeliverycreditBean getStore_deliverycredit() {
                return store_deliverycredit;
            }

            public void setStore_deliverycredit(StoreDeliverycreditBean store_deliverycredit) {
                this.store_deliverycredit = store_deliverycredit;
            }

            public static class StoreDesccreditBean {
                /**
                 * text : 描述相符
                 * credit : 5
                 * percent : ----
                 * percent_class : equal
                 * percent_text : 持平
                 */

                private String text;
                private int credit;
                private String percent;
                private String percent_class;
                private String percent_text;

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }

                public int getCredit() {
                    return credit;
                }

                public void setCredit(int credit) {
                    this.credit = credit;
                }

                public String getPercent() {
                    return percent;
                }

                public void setPercent(String percent) {
                    this.percent = percent;
                }

                public String getPercent_class() {
                    return percent_class;
                }

                public void setPercent_class(String percent_class) {
                    this.percent_class = percent_class;
                }

                public String getPercent_text() {
                    return percent_text;
                }

                public void setPercent_text(String percent_text) {
                    this.percent_text = percent_text;
                }
            }

            public static class StoreServicecreditBean {
                /**
                 * text : 服务态度
                 * credit : 5
                 * percent : ----
                 * percent_class : equal
                 * percent_text : 持平
                 */

                private String text;
                private int credit;
                private String percent;
                private String percent_class;
                private String percent_text;

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }

                public int getCredit() {
                    return credit;
                }

                public void setCredit(int credit) {
                    this.credit = credit;
                }

                public String getPercent() {
                    return percent;
                }

                public void setPercent(String percent) {
                    this.percent = percent;
                }

                public String getPercent_class() {
                    return percent_class;
                }

                public void setPercent_class(String percent_class) {
                    this.percent_class = percent_class;
                }

                public String getPercent_text() {
                    return percent_text;
                }

                public void setPercent_text(String percent_text) {
                    this.percent_text = percent_text;
                }
            }

            public static class StoreDeliverycreditBean {
                /**
                 * text : 发货速度
                 * credit : 5
                 * percent : ----
                 * percent_class : equal
                 * percent_text : 持平
                 */

                private String text;
                private int credit;
                private String percent;
                private String percent_class;
                private String percent_text;

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }

                public int getCredit() {
                    return credit;
                }

                public void setCredit(int credit) {
                    this.credit = credit;
                }

                public String getPercent() {
                    return percent;
                }

                public void setPercent(String percent) {
                    this.percent = percent;
                }

                public String getPercent_class() {
                    return percent_class;
                }

                public void setPercent_class(String percent_class) {
                    this.percent_class = percent_class;
                }

                public String getPercent_text() {
                    return percent_text;
                }

                public void setPercent_text(String percent_text) {
                    this.percent_text = percent_text;
                }
            }
        }

        public static class SearchListGoodsBean {
            /**
             * goods_id : 136
             * store_id : 1
             * goods_name : 红薯红薯红薯红薯红薯
             * goods_image : https://oss.bayi-shop.com/home/store/goods/1/alioss_1_2019121117450292048.jpg
             * goods_price : 10.00
             * goods_salenum : 0
             */

            private int goods_id;
            private int store_id;
            private String goods_name;
            private String goods_image;
            private String goods_price;
            private int goods_salenum;

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public int getStore_id() {
                return store_id;
            }

            public void setStore_id(int store_id) {
                this.store_id = store_id;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getGoods_image() {
                return goods_image;
            }

            public void setGoods_image(String goods_image) {
                this.goods_image = goods_image;
            }

            public String getGoods_price() {
                return goods_price;
            }

            public void setGoods_price(String goods_price) {
                this.goods_price = goods_price;
            }

            public int getGoods_salenum() {
                return goods_salenum;
            }

            public void setGoods_salenum(int goods_salenum) {
                this.goods_salenum = goods_salenum;
            }
        }
    }
}
