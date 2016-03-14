package com.administrator.youyaoqi.Bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/7.
 */
public class ManhuaPaihangData {

    /**
     * code : 1
     * data : {"stateCode":1,"message":"成功","returnData":{"version":2,"rankinglist":[{"rankingName":"月票排行榜","rankingDescription1":"最受欢迎作品排行","rankingDescription2":"死忠们票选出的不会错","cover":"http://static.u17i.com/app/v3image/rank/yuepiao.png","argCon":null,"argName":"sort","argValue":23,"explainUrl":""},{"rankingName":"点击排行榜","rankingDescription1":"最具人气作品排行","rankingDescription2":"看看最近流行什么","cover":"http://static.u17i.com/app/v3image/rank/renqi.png","argCon":null,"argName":"sort","argValue":9,"explainUrl":""},{"rankingName":"吐槽排行榜","rankingDescription1":"最多槽点作品排行","rankingDescription2":"完全不知道从哪开始吐槽","cover":"http://static.u17i.com/app/v3image/rank/tucao.png","argCon":null,"argName":"sort","argValue":20,"explainUrl":""},{"rankingName":"感动排行榜","rankingDescription1":"最动人作品排行","rankingDescription2":"前方催泪弹出没","cover":"http://static.u17i.com/app/v3image/rank/gandong.png","argCon":1,"argName":"sort","argValue":15,"explainUrl":""},{"rankingName":"恐怖排行榜","rankingDescription1":"最吓人作品排行","rankingDescription2":"避免噩梦谨慎浏览","cover":"http://static.u17i.com/app/v3image/rank/kongbu.png","argCon":2,"argName":"sort","argValue":17,"explainUrl":""},{"rankingName":"爆笑排行榜","rankingDescription1":"最搞笑作品排行","rankingDescription2":"哈哈哈哈哈哈哈哈哈哈","cover":"http://static.u17i.com/app/v3image/rank/baoxiao.png","argCon":3,"argName":"sort","argValue":18,"explainUrl":""},{"rankingName":"另类排行榜","rankingDescription1":"最独特作品排行","rankingDescription2":"有妖气最具新意的漫画","cover":"http://static.u17i.com/app/v3image/rank/linglei.png","argCon":1,"argName":"sort","argValue":19,"explainUrl":""},{"rankingName":"新作排行榜","rankingDescription1":"全新作品排行","rankingDescription2":"新百万级作品谁投了第一票","cover":"http://static.u17i.com/app/v3image/rank/xinzuo.png","argCon":2,"argName":"sort","argValue":2,"explainUrl":""}]}}
     */

    private int code;
    /**
     * stateCode : 1
     * message : 成功
     * returnData : {"version":2,"rankinglist":[{"rankingName":"月票排行榜","rankingDescription1":"最受欢迎作品排行","rankingDescription2":"死忠们票选出的不会错","cover":"http://static.u17i.com/app/v3image/rank/yuepiao.png","argCon":null,"argName":"sort","argValue":23,"explainUrl":""},{"rankingName":"点击排行榜","rankingDescription1":"最具人气作品排行","rankingDescription2":"看看最近流行什么","cover":"http://static.u17i.com/app/v3image/rank/renqi.png","argCon":null,"argName":"sort","argValue":9,"explainUrl":""},{"rankingName":"吐槽排行榜","rankingDescription1":"最多槽点作品排行","rankingDescription2":"完全不知道从哪开始吐槽","cover":"http://static.u17i.com/app/v3image/rank/tucao.png","argCon":null,"argName":"sort","argValue":20,"explainUrl":""},{"rankingName":"感动排行榜","rankingDescription1":"最动人作品排行","rankingDescription2":"前方催泪弹出没","cover":"http://static.u17i.com/app/v3image/rank/gandong.png","argCon":1,"argName":"sort","argValue":15,"explainUrl":""},{"rankingName":"恐怖排行榜","rankingDescription1":"最吓人作品排行","rankingDescription2":"避免噩梦谨慎浏览","cover":"http://static.u17i.com/app/v3image/rank/kongbu.png","argCon":2,"argName":"sort","argValue":17,"explainUrl":""},{"rankingName":"爆笑排行榜","rankingDescription1":"最搞笑作品排行","rankingDescription2":"哈哈哈哈哈哈哈哈哈哈","cover":"http://static.u17i.com/app/v3image/rank/baoxiao.png","argCon":3,"argName":"sort","argValue":18,"explainUrl":""},{"rankingName":"另类排行榜","rankingDescription1":"最独特作品排行","rankingDescription2":"有妖气最具新意的漫画","cover":"http://static.u17i.com/app/v3image/rank/linglei.png","argCon":1,"argName":"sort","argValue":19,"explainUrl":""},{"rankingName":"新作排行榜","rankingDescription1":"全新作品排行","rankingDescription2":"新百万级作品谁投了第一票","cover":"http://static.u17i.com/app/v3image/rank/xinzuo.png","argCon":2,"argName":"sort","argValue":2,"explainUrl":""}]}
     */

    private DataEntity data;

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public DataEntity getData() {
        return data;
    }

    public static class DataEntity {
        private int stateCode;
        private String message;
        /**
         * version : 2
         * rankinglist : [{"rankingName":"月票排行榜","rankingDescription1":"最受欢迎作品排行","rankingDescription2":"死忠们票选出的不会错","cover":"http://static.u17i.com/app/v3image/rank/yuepiao.png","argCon":null,"argName":"sort","argValue":23,"explainUrl":""},{"rankingName":"点击排行榜","rankingDescription1":"最具人气作品排行","rankingDescription2":"看看最近流行什么","cover":"http://static.u17i.com/app/v3image/rank/renqi.png","argCon":null,"argName":"sort","argValue":9,"explainUrl":""},{"rankingName":"吐槽排行榜","rankingDescription1":"最多槽点作品排行","rankingDescription2":"完全不知道从哪开始吐槽","cover":"http://static.u17i.com/app/v3image/rank/tucao.png","argCon":null,"argName":"sort","argValue":20,"explainUrl":""},{"rankingName":"感动排行榜","rankingDescription1":"最动人作品排行","rankingDescription2":"前方催泪弹出没","cover":"http://static.u17i.com/app/v3image/rank/gandong.png","argCon":1,"argName":"sort","argValue":15,"explainUrl":""},{"rankingName":"恐怖排行榜","rankingDescription1":"最吓人作品排行","rankingDescription2":"避免噩梦谨慎浏览","cover":"http://static.u17i.com/app/v3image/rank/kongbu.png","argCon":2,"argName":"sort","argValue":17,"explainUrl":""},{"rankingName":"爆笑排行榜","rankingDescription1":"最搞笑作品排行","rankingDescription2":"哈哈哈哈哈哈哈哈哈哈","cover":"http://static.u17i.com/app/v3image/rank/baoxiao.png","argCon":3,"argName":"sort","argValue":18,"explainUrl":""},{"rankingName":"另类排行榜","rankingDescription1":"最独特作品排行","rankingDescription2":"有妖气最具新意的漫画","cover":"http://static.u17i.com/app/v3image/rank/linglei.png","argCon":1,"argName":"sort","argValue":19,"explainUrl":""},{"rankingName":"新作排行榜","rankingDescription1":"全新作品排行","rankingDescription2":"新百万级作品谁投了第一票","cover":"http://static.u17i.com/app/v3image/rank/xinzuo.png","argCon":2,"argName":"sort","argValue":2,"explainUrl":""}]
         */

        private ReturnDataEntity returnData;

        public void setStateCode(int stateCode) {
            this.stateCode = stateCode;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public void setReturnData(ReturnDataEntity returnData) {
            this.returnData = returnData;
        }

        public int getStateCode() {
            return stateCode;
        }

        public String getMessage() {
            return message;
        }

        public ReturnDataEntity getReturnData() {
            return returnData;
        }

        public static class ReturnDataEntity {
            private int version;
            /**
             * rankingName : 月票排行榜
             * rankingDescription1 : 最受欢迎作品排行
             * rankingDescription2 : 死忠们票选出的不会错
             * cover : http://static.u17i.com/app/v3image/rank/yuepiao.png
             * argCon : null
             * argName : sort
             * argValue : 23
             * explainUrl :
             */

            private List<RankinglistEntity> rankinglist;

            public void setVersion(int version) {
                this.version = version;
            }

            public void setRankinglist(List<RankinglistEntity> rankinglist) {
                this.rankinglist = rankinglist;
            }

            public int getVersion() {
                return version;
            }

            public List<RankinglistEntity> getRankinglist() {
                return rankinglist;
            }

            public static class RankinglistEntity {
                private String rankingName;
                private String rankingDescription1;
                private String rankingDescription2;
                private String cover;
                private Object argCon;
                private String argName;
                private int argValue;
                private String explainUrl;

                public void setRankingName(String rankingName) {
                    this.rankingName = rankingName;
                }

                public void setRankingDescription1(String rankingDescription1) {
                    this.rankingDescription1 = rankingDescription1;
                }

                public void setRankingDescription2(String rankingDescription2) {
                    this.rankingDescription2 = rankingDescription2;
                }

                public void setCover(String cover) {
                    this.cover = cover;
                }

                public void setArgCon(Object argCon) {
                    this.argCon = argCon;
                }

                public void setArgName(String argName) {
                    this.argName = argName;
                }

                public void setArgValue(int argValue) {
                    this.argValue = argValue;
                }

                public void setExplainUrl(String explainUrl) {
                    this.explainUrl = explainUrl;
                }

                public String getRankingName() {
                    return rankingName;
                }

                public String getRankingDescription1() {
                    return rankingDescription1;
                }

                public String getRankingDescription2() {
                    return rankingDescription2;
                }

                public String getCover() {
                    return cover;
                }

                public Object getArgCon() {
                    return argCon;
                }

                public String getArgName() {
                    return argName;
                }

                public int getArgValue() {
                    return argValue;
                }

                public String getExplainUrl() {
                    return explainUrl;
                }
            }
        }
    }
}
