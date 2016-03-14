package com.administrator.youyaoqi.Bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/5.
 */
public class ManhuaGengxinData {


    private int code;
    private String msg;


    private DataEntity data;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public DataEntity getData() {
        return data;
    }

    public static class DataEntity {
        private int stateCode;
        private String message;


        private List<ReturnDataEntity> returnData;

        public void setStateCode(int stateCode) {
            this.stateCode = stateCode;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public void setReturnData(List<ReturnDataEntity> returnData) {
            this.returnData = returnData;
        }

        public int getStateCode() {
            return stateCode;
        }

        public String getMessage() {
            return message;
        }

        public List<ReturnDataEntity> getReturnData() {
            return returnData;
        }

        public static class ReturnDataEntity {
            private int comic_id;
            private String name;
            private String cover;
            private int accredit;
            private int last_update_time;
            private String last_update_chapter_name;
            private String description;
            private int user_id;
            private String nickname;
            private String series_status;
            private String theme_ids;
            private String is_vip;
            private String chapter_num;
            private String last_update_chapter_id;
            private int is_dujia;
            private int is_free;
            private String extraValue;
            private String click_total;
            private List<String> tags;

            public void setComic_id(int comic_id) {
                this.comic_id = comic_id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public void setAccredit(int accredit) {
                this.accredit = accredit;
            }

            public void setLast_update_time(int last_update_time) {
                this.last_update_time = last_update_time;
            }

            public void setLast_update_chapter_name(String last_update_chapter_name) {
                this.last_update_chapter_name = last_update_chapter_name;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public void setSeries_status(String series_status) {
                this.series_status = series_status;
            }

            public void setTheme_ids(String theme_ids) {
                this.theme_ids = theme_ids;
            }

            public void setIs_vip(String is_vip) {
                this.is_vip = is_vip;
            }

            public void setChapter_num(String chapter_num) {
                this.chapter_num = chapter_num;
            }

            public void setLast_update_chapter_id(String last_update_chapter_id) {
                this.last_update_chapter_id = last_update_chapter_id;
            }

            public void setIs_dujia(int is_dujia) {
                this.is_dujia = is_dujia;
            }

            public void setIs_free(int is_free) {
                this.is_free = is_free;
            }

            public void setExtraValue(String extraValue) {
                this.extraValue = extraValue;
            }

            public void setClick_total(String click_total) {
                this.click_total = click_total;
            }

            public void setTags(List<String> tags) {
                this.tags = tags;
            }

            public int getComic_id() {
                return comic_id;
            }

            public String getName() {
                return name;
            }

            public String getCover() {
                return cover;
            }

            public int getAccredit() {
                return accredit;
            }

            public int getLast_update_time() {
                return last_update_time;
            }

            public String getLast_update_chapter_name() {
                return last_update_chapter_name;
            }

            public String getDescription() {
                return description;
            }

            public int getUser_id() {
                return user_id;
            }

            public String getNickname() {
                return nickname;
            }

            public String getSeries_status() {
                return series_status;
            }

            public String getTheme_ids() {
                return theme_ids;
            }

            public String getIs_vip() {
                return is_vip;
            }

            public String getChapter_num() {
                return chapter_num;
            }

            public String getLast_update_chapter_id() {
                return last_update_chapter_id;
            }

            public int getIs_dujia() {
                return is_dujia;
            }

            public int getIs_free() {
                return is_free;
            }

            public String getExtraValue() {
                return extraValue;
            }

            public String getClick_total() {
                return click_total;
            }

            public List<String> getTags() {
                return tags;
            }
        }
    }





}
