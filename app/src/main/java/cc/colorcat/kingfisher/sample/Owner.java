/*
 * Copyright 2018 cxx
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cc.colorcat.kingfisher.sample;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

/**
 * Author: cxx
 * Date: 2018-08-25
 * GitHub: https://github.com/ccolorcat
 */
public class Owner {
    /**
     * login : ccolorcat
     * id : 39815818
     * node_id : MDQ6VXNlcjM5ODE1ODE4
     * avatar_url : https://avatars1.githubusercontent.com/u/39815818?v=4
     * gravatar_id :
     * url : https://api.github.com/users/ccolorcat
     * html_url : https://github.com/ccolorcat
     * followers_url : https://api.github.com/users/ccolorcat/followers
     * following_url : https://api.github.com/users/ccolorcat/following{/other_user}
     * gists_url : https://api.github.com/users/ccolorcat/gists{/gist_id}
     * starred_url : https://api.github.com/users/ccolorcat/starred{/owner}{/repo}
     * subscriptions_url : https://api.github.com/users/ccolorcat/subscriptions
     * organizations_url : https://api.github.com/users/ccolorcat/orgs
     * repos_url : https://api.github.com/users/ccolorcat/repos
     * events_url : https://api.github.com/users/ccolorcat/events{/privacy}
     * received_events_url : https://api.github.com/users/ccolorcat/received_events
     * type : User
     * site_admin : false
     */

    @JsonProperty("login")
    @JSONField(name = "login")
    @SerializedName("login")
    private String login;
    @JsonProperty("id")
    @JSONField(name = "id")
    @SerializedName("id")
    private int id;
    @JsonProperty("node_id")
    @JSONField(name = "node_id")
    @SerializedName("node_id")
    private String nodeId;
    @JsonProperty("avatar_url")
    @JSONField(name = "avatar_url")
    @SerializedName("avatar_url")
    private String avatarUrl;
    @JsonProperty("gravatar_id")
    @JSONField(name = "gravatar_id")
    @SerializedName("gravatar_id")
    private String gravatarId;
    @JsonProperty("url")
    @JSONField(name = "url")
    @SerializedName("url")
    private String url;
    @JsonProperty("html_url")
    @JSONField(name = "html_url")
    @SerializedName("html_url")
    private String htmlUrl;
    @JsonProperty("followers_url")
    @JSONField(name = "followers_url")
    @SerializedName("followers_url")
    private String followersUrl;
    @JsonProperty("following_url")
    @JSONField(name = "following_url")
    @SerializedName("following_url")
    private String followingUrl;
    @JsonProperty("gists_url")
    @JSONField(name = "gists_url")
    @SerializedName("gists_url")
    private String gistsUrl;
    @JsonProperty("starred_url")
    @JSONField(name = "starred_url")
    @SerializedName("starred_url")
    private String starredUrl;
    @JsonProperty("subscriptions_url")
    @JSONField(name = "subscriptions_url")
    @SerializedName("subscriptions_url")
    private String subscriptionsUrl;
    @JsonProperty("organizations_url")
    @JSONField(name = "organizations_url")
    @SerializedName("organizations_url")
    private String organizationsUrl;
    @JsonProperty("repos_url")
    @JSONField(name = "repos_url")
    @SerializedName("repos_url")
    private String reposUrl;
    @JsonProperty("events_url")
    @JSONField(name = "events_url")
    @SerializedName("events_url")
    private String eventsUrl;
    @JsonProperty("received_events_url")
    @JSONField(name = "received_events_url")
    @SerializedName("received_events_url")
    private String receivedEventsUrl;
    @JsonProperty("type")
    @JSONField(name = "type")
    @SerializedName("type")
    private String type;
    @JsonProperty("site_admin")
    @JSONField(name = "site_admin")
    @SerializedName("site_admin")
    private boolean siteAdmin;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getGravatarId() {
        return gravatarId;
    }

    public void setGravatarId(String gravatarId) {
        this.gravatarId = gravatarId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getFollowersUrl() {
        return followersUrl;
    }

    public void setFollowersUrl(String followersUrl) {
        this.followersUrl = followersUrl;
    }

    public String getFollowingUrl() {
        return followingUrl;
    }

    public void setFollowingUrl(String followingUrl) {
        this.followingUrl = followingUrl;
    }

    public String getGistsUrl() {
        return gistsUrl;
    }

    public void setGistsUrl(String gistsUrl) {
        this.gistsUrl = gistsUrl;
    }

    public String getStarredUrl() {
        return starredUrl;
    }

    public void setStarredUrl(String starredUrl) {
        this.starredUrl = starredUrl;
    }

    public String getSubscriptionsUrl() {
        return subscriptionsUrl;
    }

    public void setSubscriptionsUrl(String subscriptionsUrl) {
        this.subscriptionsUrl = subscriptionsUrl;
    }

    public String getOrganizationsUrl() {
        return organizationsUrl;
    }

    public void setOrganizationsUrl(String organizationsUrl) {
        this.organizationsUrl = organizationsUrl;
    }

    public String getReposUrl() {
        return reposUrl;
    }

    public void setReposUrl(String reposUrl) {
        this.reposUrl = reposUrl;
    }

    public String getEventsUrl() {
        return eventsUrl;
    }

    public void setEventsUrl(String eventsUrl) {
        this.eventsUrl = eventsUrl;
    }

    public String getReceivedEventsUrl() {
        return receivedEventsUrl;
    }

    public void setReceivedEventsUrl(String receivedEventsUrl) {
        this.receivedEventsUrl = receivedEventsUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isSiteAdmin() {
        return siteAdmin;
    }

    public void setSiteAdmin(boolean siteAdmin) {
        this.siteAdmin = siteAdmin;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "login='" + login + '\'' +
                ", id=" + id +
                ", nodeId='" + nodeId + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", gravatarId='" + gravatarId + '\'' +
                ", url='" + url + '\'' +
                ", htmlUrl='" + htmlUrl + '\'' +
                ", followersUrl='" + followersUrl + '\'' +
                ", followingUrl='" + followingUrl + '\'' +
                ", gistsUrl='" + gistsUrl + '\'' +
                ", starredUrl='" + starredUrl + '\'' +
                ", subscriptionsUrl='" + subscriptionsUrl + '\'' +
                ", organizationsUrl='" + organizationsUrl + '\'' +
                ", reposUrl='" + reposUrl + '\'' +
                ", eventsUrl='" + eventsUrl + '\'' +
                ", receivedEventsUrl='" + receivedEventsUrl + '\'' +
                ", type='" + type + '\'' +
                ", siteAdmin=" + siteAdmin +
                '}';
    }
}
