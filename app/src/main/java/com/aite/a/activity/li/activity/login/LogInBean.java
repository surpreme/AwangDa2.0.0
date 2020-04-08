package com.aite.a.activity.li.activity.login;

import com.aite.a.activity.li.mvp.ErrorBean;

/**
 * @author:TQX
 * @Date: 2019/11/12
 * @description:
 */
public class LogInBean extends ErrorBean {

    /**
     * username : aiteshop
     * key : bd0019a4797ead1c8652edb15eaaf5ad
     * config : {"friend_valid":0,"member_id":"135"}
     */

    private String username;
    private String key;
    private ConfigBean config;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ConfigBean getConfig() {
        return config;
    }

    public void setConfig(ConfigBean config) {
        this.config = config;
    }

    public static class ConfigBean {
        /**
         * friend_valid : 0
         * member_id : 135
         */

        private int friend_valid;
        private String member_id;

        public int getFriend_valid() {
            return friend_valid;
        }

        public void setFriend_valid(int friend_valid) {
            this.friend_valid = friend_valid;
        }

        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
        }
    }
}
