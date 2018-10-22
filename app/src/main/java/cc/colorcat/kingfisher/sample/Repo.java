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
public class Repo {

    /**
     * id : 135659546
     * node_id : MDEwOlJlcG9zaXRvcnkxMzU2NTk1NDY=
     * name : Adapter
     * full_name : ccolorcat/Adapter
     * owner : {"login":"ccolorcat","id":39815818,"node_id":"MDQ6VXNlcjM5ODE1ODE4","avatar_url":"https://avatars1.githubusercontent.com/u/39815818?v=4","gravatar_id":"","url":"https://api.github.com/users/ccolorcat","html_url":"https://github.com/ccolorcat","followers_url":"https://api.github.com/users/ccolorcat/followers","following_url":"https://api.github.com/users/ccolorcat/following{/other_user}","gists_url":"https://api.github.com/users/ccolorcat/gists{/gist_id}","starred_url":"https://api.github.com/users/ccolorcat/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/ccolorcat/subscriptions","organizations_url":"https://api.github.com/users/ccolorcat/orgs","repos_url":"https://api.github.com/users/ccolorcat/repos","events_url":"https://api.github.com/users/ccolorcat/events{/privacy}","received_events_url":"https://api.github.com/users/ccolorcat/received_events","type":"User","site_admin":false}
     * private : false
     * html_url : https://github.com/ccolorcat/Adapter
     * description : ListView 和 RecyclerView 的通用 Adapter
     * fork : false
     * url : https://api.github.com/repos/ccolorcat/Adapter
     * forks_url : https://api.github.com/repos/ccolorcat/Adapter/forks
     * keys_url : https://api.github.com/repos/ccolorcat/Adapter/keys{/key_id}
     * collaborators_url : https://api.github.com/repos/ccolorcat/Adapter/collaborators{/collaborator}
     * teams_url : https://api.github.com/repos/ccolorcat/Adapter/teams
     * hooks_url : https://api.github.com/repos/ccolorcat/Adapter/hooks
     * issue_events_url : https://api.github.com/repos/ccolorcat/Adapter/issues/events{/number}
     * events_url : https://api.github.com/repos/ccolorcat/Adapter/events
     * assignees_url : https://api.github.com/repos/ccolorcat/Adapter/assignees{/user}
     * branches_url : https://api.github.com/repos/ccolorcat/Adapter/branches{/branch}
     * tags_url : https://api.github.com/repos/ccolorcat/Adapter/tags
     * blobs_url : https://api.github.com/repos/ccolorcat/Adapter/git/blobs{/sha}
     * git_tags_url : https://api.github.com/repos/ccolorcat/Adapter/git/tags{/sha}
     * git_refs_url : https://api.github.com/repos/ccolorcat/Adapter/git/refs{/sha}
     * trees_url : https://api.github.com/repos/ccolorcat/Adapter/git/trees{/sha}
     * statuses_url : https://api.github.com/repos/ccolorcat/Adapter/statuses/{sha}
     * languages_url : https://api.github.com/repos/ccolorcat/Adapter/languages
     * stargazers_url : https://api.github.com/repos/ccolorcat/Adapter/stargazers
     * contributors_url : https://api.github.com/repos/ccolorcat/Adapter/contributors
     * subscribers_url : https://api.github.com/repos/ccolorcat/Adapter/subscribers
     * subscription_url : https://api.github.com/repos/ccolorcat/Adapter/subscription
     * commits_url : https://api.github.com/repos/ccolorcat/Adapter/commits{/sha}
     * git_commits_url : https://api.github.com/repos/ccolorcat/Adapter/git/commits{/sha}
     * comments_url : https://api.github.com/repos/ccolorcat/Adapter/comments{/number}
     * issue_comment_url : https://api.github.com/repos/ccolorcat/Adapter/issues/comments{/number}
     * contents_url : https://api.github.com/repos/ccolorcat/Adapter/contents/{+path}
     * compare_url : https://api.github.com/repos/ccolorcat/Adapter/compare/{base}...{head}
     * merges_url : https://api.github.com/repos/ccolorcat/Adapter/merges
     * archive_url : https://api.github.com/repos/ccolorcat/Adapter/{archive_format}{/ref}
     * downloads_url : https://api.github.com/repos/ccolorcat/Adapter/downloads
     * issues_url : https://api.github.com/repos/ccolorcat/Adapter/issues{/number}
     * pulls_url : https://api.github.com/repos/ccolorcat/Adapter/pulls{/number}
     * milestones_url : https://api.github.com/repos/ccolorcat/Adapter/milestones{/number}
     * notifications_url : https://api.github.com/repos/ccolorcat/Adapter/notifications{?since,all,participating}
     * labels_url : https://api.github.com/repos/ccolorcat/Adapter/labels{/name}
     * releases_url : https://api.github.com/repos/ccolorcat/Adapter/releases{/id}
     * deployments_url : https://api.github.com/repos/ccolorcat/Adapter/deployments
     * created_at : 2018-06-01T02:48:12Z
     * updated_at : 2018-08-16T15:10:38Z
     * pushed_at : 2018-08-16T15:10:36Z
     * git_url : git://github.com/ccolorcat/Adapter.git
     * ssh_url : git@github.com:ccolorcat/Adapter.git
     * clone_url : https://github.com/ccolorcat/Adapter.git
     * svn_url : https://github.com/ccolorcat/Adapter
     * homepage : null
     * size : 363
     * stargazers_count : 0
     * watchers_count : 0
     * language : Java
     * has_issues : true
     * has_projects : true
     * has_downloads : true
     * has_wiki : true
     * has_pages : false
     * forks_count : 0
     * mirror_url : null
     * archived : false
     * open_issues_count : 0
     * license : {"key":"apache-2.0","name":"Apache License 2.0","spdx_id":"Apache-2.0","url":"https://api.github.com/licenses/apache-2.0","node_id":"MDc6TGljZW5zZTI="}
     * forks : 0
     * open_issues : 0
     * watchers : 0
     * default_branch : master
     */

    @JSONField(name = "id")
    @JsonProperty("id")
    @SerializedName("id")
    private int id;
    @JSONField(name = "node_id")
    @JsonProperty("node_id")
    @SerializedName("node_id")
    private String nodeId;
    @JSONField(name = "name")
    @JsonProperty("name")
    @SerializedName("name")
    private String name;
    @JSONField(name = "full_name")
    @JsonProperty("full_name")
    @SerializedName("full_name")
    private String fullName;
    @JSONField(name = "owner")
    @JsonProperty("owner")
    @SerializedName("owner")
    private Owner owner;
    @JSONField(name = "private")
    @JsonProperty("private")
    @SerializedName("private")
    private boolean privateX;
    @JSONField(name = "html_url")
    @JsonProperty("html_url")
    @SerializedName("html_url")
    private String htmlUrl;
    @JSONField(name = "description")
    @JsonProperty("description")
    @SerializedName("description")
    private String description;
    @JSONField(name = "fork")
    @JsonProperty("fork")
    @SerializedName("fork")
    private boolean fork;
    @JSONField(name = "url")
    @JsonProperty("url")
    @SerializedName("url")
    private String url;
    @JSONField(name = "forks_url")
    @JsonProperty("forks_url")
    @SerializedName("forks_url")
    private String forksUrl;
    @JSONField(name = "keys_url")
    @JsonProperty("keys_url")
    @SerializedName("keys_url")
    private String keysUrl;
    @JSONField(name = "collaborators_url")
    @JsonProperty("collaborators_url")
    @SerializedName("collaborators_url")
    private String collaboratorsUrl;
    @JSONField(name = "teams_url")
    @JsonProperty("teams_url")
    @SerializedName("teams_url")
    private String teamsUrl;
    @JSONField(name = "hooks_url")
    @JsonProperty("hooks_url")
    @SerializedName("hooks_url")
    private String hooksUrl;
    @JSONField(name = "issue_events_url")
    @JsonProperty("issue_events_url")
    @SerializedName("issue_events_url")
    private String issueEventsUrl;
    @JSONField(name = "events_url")
    @JsonProperty("events_url")
    @SerializedName("events_url")
    private String eventsUrl;
    @JSONField(name = "assignees_url")
    @JsonProperty("assignees_url")
    @SerializedName("assignees_url")
    private String assigneesUrl;
    @JSONField(name = "branches_url")
    @JsonProperty("branches_url")
    @SerializedName("branches_url")
    private String branchesUrl;
    @JSONField(name = "tags_url")
    @JsonProperty("tags_url")
    @SerializedName("tags_url")
    private String tagsUrl;
    @JSONField(name = "blobs_url")
    @JsonProperty("blobs_url")
    @SerializedName("blobs_url")
    private String blobsUrl;
    @JSONField(name = "git_tags_url")
    @JsonProperty("git_tags_url")
    @SerializedName("git_tags_url")
    private String gitTagsUrl;
    @JSONField(name = "git_refs_url")
    @JsonProperty("git_refs_url")
    @SerializedName("git_refs_url")
    private String gitRefsUrl;
    @JSONField(name = "trees_url")
    @JsonProperty("trees_url")
    @SerializedName("trees_url")
    private String treesUrl;
    @JSONField(name = "statuses_url")
    @JsonProperty("statuses_url")
    @SerializedName("statuses_url")
    private String statusesUrl;
    @JSONField(name = "languages_url")
    @JsonProperty("languages_url")
    @SerializedName("languages_url")
    private String languagesUrl;
    @JSONField(name = "stargazers_url")
    @JsonProperty("stargazers_url")
    @SerializedName("stargazers_url")
    private String stargazersUrl;
    @JSONField(name = "contributors_url")
    @JsonProperty("contributors_url")
    @SerializedName("contributors_url")
    private String contributorsUrl;
    @JSONField(name = "subscribers_url")
    @JsonProperty("subscribers_url")
    @SerializedName("subscribers_url")
    private String subscribersUrl;
    @JSONField(name = "subscription_url")
    @JsonProperty("subscription_url")
    @SerializedName("subscription_url")
    private String subscriptionUrl;
    @JSONField(name = "commits_url")
    @JsonProperty("commits_url")
    @SerializedName("commits_url")
    private String commitsUrl;
    @JSONField(name = "git_commits_url")
    @JsonProperty("git_commits_url")
    @SerializedName("git_commits_url")
    private String gitCommitsUrl;
    @JSONField(name = "comments_url")
    @JsonProperty("comments_url")
    @SerializedName("comments_url")
    private String commentsUrl;
    @JSONField(name = "issue_comment_url")
    @JsonProperty("issue_comment_url")
    @SerializedName("issue_comment_url")
    private String issueCommentUrl;
    @JSONField(name = "contents_url")
    @JsonProperty("contents_url")
    @SerializedName("contents_url")
    private String contentsUrl;
    @JSONField(name = "compare_url")
    @JsonProperty("compare_url")
    @SerializedName("compare_url")
    private String compareUrl;
    @JSONField(name = "merges_url")
    @JsonProperty("merges_url")
    @SerializedName("merges_url")
    private String mergesUrl;
    @JSONField(name = "archive_url")
    @JsonProperty("archive_url")
    @SerializedName("archive_url")
    private String archiveUrl;
    @JSONField(name = "downloads_url")
    @JsonProperty("downloads_url")
    @SerializedName("downloads_url")
    private String downloadsUrl;
    @JSONField(name = "issues_url")
    @JsonProperty("issues_url")
    @SerializedName("issues_url")
    private String issuesUrl;
    @JSONField(name = "pulls_url")
    @JsonProperty("pulls_url")
    @SerializedName("pulls_url")
    private String pullsUrl;
    @JSONField(name = "milestones_url")
    @JsonProperty("milestones_url")
    @SerializedName("milestones_url")
    private String milestonesUrl;
    @JSONField(name = "notifications_url")
    @JsonProperty("notifications_url")
    @SerializedName("notifications_url")
    private String notificationsUrl;
    @JSONField(name = "labels_url")
    @JsonProperty("labels_url")
    @SerializedName("labels_url")
    private String labelsUrl;
    @JSONField(name = "releases_url")
    @JsonProperty("releases_url")
    @SerializedName("releases_url")
    private String releasesUrl;
    @JSONField(name = "deployments_url")
    @JsonProperty("deployments_url")
    @SerializedName("deployments_url")
    private String deploymentsUrl;
    @JSONField(name = "created_at")
    @JsonProperty("created_at")
    @SerializedName("created_at")
    private String createdAt;
    @JSONField(name = "updated_at")
    @JsonProperty("updated_at")
    @SerializedName("updated_at")
    private String updatedAt;
    @JSONField(name = "pushed_at")
    @JsonProperty("pushed_at")
    @SerializedName("pushed_at")
    private String pushedAt;
    @JSONField(name = "git_url")
    @JsonProperty("git_url")
    @SerializedName("git_url")
    private String gitUrl;
    @JSONField(name = "ssh_url")
    @JsonProperty("ssh_url")
    @SerializedName("ssh_url")
    private String sshUrl;
    @JSONField(name = "clone_url")
    @JsonProperty("clone_url")
    @SerializedName("clone_url")
    private String cloneUrl;
    @JSONField(name = "svn_url")
    @JsonProperty("svn_url")
    @SerializedName("svn_url")
    private String svnUrl;
    @JSONField(name = "homepage")
    @JsonProperty("homepage")
    @SerializedName("homepage")
    private String homepage;
    @JSONField(name = "size")
    @JsonProperty("size")
    @SerializedName("size")
    private int size;
    @JSONField(name = "stargazers_count")
    @JsonProperty("stargazers_count")
    @SerializedName("stargazers_count")
    private int stargazersCount;
    @JSONField(name = "watchers_count")
    @JsonProperty("watchers_count")
    @SerializedName("watchers_count")
    private int watchersCount;
    @JSONField(name = "language")
    @JsonProperty("language")
    @SerializedName("language")
    private String language;
    @JSONField(name = "has_issues")
    @JsonProperty("has_issues")
    @SerializedName("has_issues")
    private boolean hasIssues;
    @JSONField(name = "has_projects")
    @JsonProperty("has_projects")
    @SerializedName("has_projects")
    private boolean hasProjects;
    @JSONField(name = "has_downloads")
    @JsonProperty("has_downloads")
    @SerializedName("has_downloads")
    private boolean hasDownloads;
    @JSONField(name = "has_wiki")
    @JsonProperty("has_wiki")
    @SerializedName("has_wiki")
    private boolean hasWiki;
    @JSONField(name = "has_pages")
    @JsonProperty("has_pages")
    @SerializedName("has_pages")
    private boolean hasPages;
    @JSONField(name = "forks_count")
    @JsonProperty("forks_count")
    @SerializedName("forks_count")
    private int forksCount;
    @JSONField(name = "mirror_url")
    @JsonProperty("mirror_url")
    @SerializedName("mirror_url")
    private String mirrorUrl;
    @JSONField(name = "archived")
    @JsonProperty("archived")
    @SerializedName("archived")
    private boolean archived;
    @JSONField(name = "open_issues_count")
    @JsonProperty("open_issues_count")
    @SerializedName("open_issues_count")
    private int openIssuesCount;
    @JSONField(name = "license")
    @JsonProperty("license")
    @SerializedName("license")
    private License license;
    @JSONField(name = "forks")
    @JsonProperty("forks")
    @SerializedName("forks")
    private int forks;
    @JSONField(name = "open_issues")
    @JsonProperty("open_issues")
    @SerializedName("open_issues")
    private int openIssues;
    @JSONField(name = "watchers")
    @JsonProperty("watchers")
    @SerializedName("watchers")
    private int watchers;
    @JSONField(name = "default_branch")
    @JsonProperty("default_branch")
    @SerializedName("default_branch")
    private String defaultBranch;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public boolean isPrivateX() {
        return privateX;
    }

    public void setPrivateX(boolean privateX) {
        this.privateX = privateX;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFork() {
        return fork;
    }

    public void setFork(boolean fork) {
        this.fork = fork;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getForksUrl() {
        return forksUrl;
    }

    public void setForksUrl(String forksUrl) {
        this.forksUrl = forksUrl;
    }

    public String getKeysUrl() {
        return keysUrl;
    }

    public void setKeysUrl(String keysUrl) {
        this.keysUrl = keysUrl;
    }

    public String getCollaboratorsUrl() {
        return collaboratorsUrl;
    }

    public void setCollaboratorsUrl(String collaboratorsUrl) {
        this.collaboratorsUrl = collaboratorsUrl;
    }

    public String getTeamsUrl() {
        return teamsUrl;
    }

    public void setTeamsUrl(String teamsUrl) {
        this.teamsUrl = teamsUrl;
    }

    public String getHooksUrl() {
        return hooksUrl;
    }

    public void setHooksUrl(String hooksUrl) {
        this.hooksUrl = hooksUrl;
    }

    public String getIssueEventsUrl() {
        return issueEventsUrl;
    }

    public void setIssueEventsUrl(String issueEventsUrl) {
        this.issueEventsUrl = issueEventsUrl;
    }

    public String getEventsUrl() {
        return eventsUrl;
    }

    public void setEventsUrl(String eventsUrl) {
        this.eventsUrl = eventsUrl;
    }

    public String getAssigneesUrl() {
        return assigneesUrl;
    }

    public void setAssigneesUrl(String assigneesUrl) {
        this.assigneesUrl = assigneesUrl;
    }

    public String getBranchesUrl() {
        return branchesUrl;
    }

    public void setBranchesUrl(String branchesUrl) {
        this.branchesUrl = branchesUrl;
    }

    public String getTagsUrl() {
        return tagsUrl;
    }

    public void setTagsUrl(String tagsUrl) {
        this.tagsUrl = tagsUrl;
    }

    public String getBlobsUrl() {
        return blobsUrl;
    }

    public void setBlobsUrl(String blobsUrl) {
        this.blobsUrl = blobsUrl;
    }

    public String getGitTagsUrl() {
        return gitTagsUrl;
    }

    public void setGitTagsUrl(String gitTagsUrl) {
        this.gitTagsUrl = gitTagsUrl;
    }

    public String getGitRefsUrl() {
        return gitRefsUrl;
    }

    public void setGitRefsUrl(String gitRefsUrl) {
        this.gitRefsUrl = gitRefsUrl;
    }

    public String getTreesUrl() {
        return treesUrl;
    }

    public void setTreesUrl(String treesUrl) {
        this.treesUrl = treesUrl;
    }

    public String getStatusesUrl() {
        return statusesUrl;
    }

    public void setStatusesUrl(String statusesUrl) {
        this.statusesUrl = statusesUrl;
    }

    public String getLanguagesUrl() {
        return languagesUrl;
    }

    public void setLanguagesUrl(String languagesUrl) {
        this.languagesUrl = languagesUrl;
    }

    public String getStargazersUrl() {
        return stargazersUrl;
    }

    public void setStargazersUrl(String stargazersUrl) {
        this.stargazersUrl = stargazersUrl;
    }

    public String getContributorsUrl() {
        return contributorsUrl;
    }

    public void setContributorsUrl(String contributorsUrl) {
        this.contributorsUrl = contributorsUrl;
    }

    public String getSubscribersUrl() {
        return subscribersUrl;
    }

    public void setSubscribersUrl(String subscribersUrl) {
        this.subscribersUrl = subscribersUrl;
    }

    public String getSubscriptionUrl() {
        return subscriptionUrl;
    }

    public void setSubscriptionUrl(String subscriptionUrl) {
        this.subscriptionUrl = subscriptionUrl;
    }

    public String getCommitsUrl() {
        return commitsUrl;
    }

    public void setCommitsUrl(String commitsUrl) {
        this.commitsUrl = commitsUrl;
    }

    public String getGitCommitsUrl() {
        return gitCommitsUrl;
    }

    public void setGitCommitsUrl(String gitCommitsUrl) {
        this.gitCommitsUrl = gitCommitsUrl;
    }

    public String getCommentsUrl() {
        return commentsUrl;
    }

    public void setCommentsUrl(String commentsUrl) {
        this.commentsUrl = commentsUrl;
    }

    public String getIssueCommentUrl() {
        return issueCommentUrl;
    }

    public void setIssueCommentUrl(String issueCommentUrl) {
        this.issueCommentUrl = issueCommentUrl;
    }

    public String getContentsUrl() {
        return contentsUrl;
    }

    public void setContentsUrl(String contentsUrl) {
        this.contentsUrl = contentsUrl;
    }

    public String getCompareUrl() {
        return compareUrl;
    }

    public void setCompareUrl(String compareUrl) {
        this.compareUrl = compareUrl;
    }

    public String getMergesUrl() {
        return mergesUrl;
    }

    public void setMergesUrl(String mergesUrl) {
        this.mergesUrl = mergesUrl;
    }

    public String getArchiveUrl() {
        return archiveUrl;
    }

    public void setArchiveUrl(String archiveUrl) {
        this.archiveUrl = archiveUrl;
    }

    public String getDownloadsUrl() {
        return downloadsUrl;
    }

    public void setDownloadsUrl(String downloadsUrl) {
        this.downloadsUrl = downloadsUrl;
    }

    public String getIssuesUrl() {
        return issuesUrl;
    }

    public void setIssuesUrl(String issuesUrl) {
        this.issuesUrl = issuesUrl;
    }

    public String getPullsUrl() {
        return pullsUrl;
    }

    public void setPullsUrl(String pullsUrl) {
        this.pullsUrl = pullsUrl;
    }

    public String getMilestonesUrl() {
        return milestonesUrl;
    }

    public void setMilestonesUrl(String milestonesUrl) {
        this.milestonesUrl = milestonesUrl;
    }

    public String getNotificationsUrl() {
        return notificationsUrl;
    }

    public void setNotificationsUrl(String notificationsUrl) {
        this.notificationsUrl = notificationsUrl;
    }

    public String getLabelsUrl() {
        return labelsUrl;
    }

    public void setLabelsUrl(String labelsUrl) {
        this.labelsUrl = labelsUrl;
    }

    public String getReleasesUrl() {
        return releasesUrl;
    }

    public void setReleasesUrl(String releasesUrl) {
        this.releasesUrl = releasesUrl;
    }

    public String getDeploymentsUrl() {
        return deploymentsUrl;
    }

    public void setDeploymentsUrl(String deploymentsUrl) {
        this.deploymentsUrl = deploymentsUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getPushedAt() {
        return pushedAt;
    }

    public void setPushedAt(String pushedAt) {
        this.pushedAt = pushedAt;
    }

    public String getGitUrl() {
        return gitUrl;
    }

    public void setGitUrl(String gitUrl) {
        this.gitUrl = gitUrl;
    }

    public String getSshUrl() {
        return sshUrl;
    }

    public void setSshUrl(String sshUrl) {
        this.sshUrl = sshUrl;
    }

    public String getCloneUrl() {
        return cloneUrl;
    }

    public void setCloneUrl(String cloneUrl) {
        this.cloneUrl = cloneUrl;
    }

    public String getSvnUrl() {
        return svnUrl;
    }

    public void setSvnUrl(String svnUrl) {
        this.svnUrl = svnUrl;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStargazersCount() {
        return stargazersCount;
    }

    public void setStargazersCount(int stargazersCount) {
        this.stargazersCount = stargazersCount;
    }

    public int getWatchersCount() {
        return watchersCount;
    }

    public void setWatchersCount(int watchersCount) {
        this.watchersCount = watchersCount;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isHasIssues() {
        return hasIssues;
    }

    public void setHasIssues(boolean hasIssues) {
        this.hasIssues = hasIssues;
    }

    public boolean isHasProjects() {
        return hasProjects;
    }

    public void setHasProjects(boolean hasProjects) {
        this.hasProjects = hasProjects;
    }

    public boolean isHasDownloads() {
        return hasDownloads;
    }

    public void setHasDownloads(boolean hasDownloads) {
        this.hasDownloads = hasDownloads;
    }

    public boolean isHasWiki() {
        return hasWiki;
    }

    public void setHasWiki(boolean hasWiki) {
        this.hasWiki = hasWiki;
    }

    public boolean isHasPages() {
        return hasPages;
    }

    public void setHasPages(boolean hasPages) {
        this.hasPages = hasPages;
    }

    public int getForksCount() {
        return forksCount;
    }

    public void setForksCount(int forksCount) {
        this.forksCount = forksCount;
    }

    public String getMirrorUrl() {
        return mirrorUrl;
    }

    public void setMirrorUrl(String mirrorUrl) {
        this.mirrorUrl = mirrorUrl;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public int getOpenIssuesCount() {
        return openIssuesCount;
    }

    public void setOpenIssuesCount(int openIssuesCount) {
        this.openIssuesCount = openIssuesCount;
    }

    public License getLicense() {
        return license;
    }

    public void setLicense(License license) {
        this.license = license;
    }

    public int getForks() {
        return forks;
    }

    public void setForks(int forks) {
        this.forks = forks;
    }

    public int getOpenIssues() {
        return openIssues;
    }

    public void setOpenIssues(int openIssues) {
        this.openIssues = openIssues;
    }

    public int getWatchers() {
        return watchers;
    }

    public void setWatchers(int watchers) {
        this.watchers = watchers;
    }

    public String getDefaultBranch() {
        return defaultBranch;
    }

    public void setDefaultBranch(String defaultBranch) {
        this.defaultBranch = defaultBranch;
    }

    @Override
    public String toString() {
        return "Repo{" +
                "id=" + id +
                ", nodeId='" + nodeId + '\'' +
                ", name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", owner=" + owner +
                ", privateX=" + privateX +
                ", htmlUrl='" + htmlUrl + '\'' +
                ", description='" + description + '\'' +
                ", fork=" + fork +
                ", url='" + url + '\'' +
                ", forksUrl='" + forksUrl + '\'' +
                ", keysUrl='" + keysUrl + '\'' +
                ", collaboratorsUrl='" + collaboratorsUrl + '\'' +
                ", teamsUrl='" + teamsUrl + '\'' +
                ", hooksUrl='" + hooksUrl + '\'' +
                ", issueEventsUrl='" + issueEventsUrl + '\'' +
                ", eventsUrl='" + eventsUrl + '\'' +
                ", assigneesUrl='" + assigneesUrl + '\'' +
                ", branchesUrl='" + branchesUrl + '\'' +
                ", tagsUrl='" + tagsUrl + '\'' +
                ", blobsUrl='" + blobsUrl + '\'' +
                ", gitTagsUrl='" + gitTagsUrl + '\'' +
                ", gitRefsUrl='" + gitRefsUrl + '\'' +
                ", treesUrl='" + treesUrl + '\'' +
                ", statusesUrl='" + statusesUrl + '\'' +
                ", languagesUrl='" + languagesUrl + '\'' +
                ", stargazersUrl='" + stargazersUrl + '\'' +
                ", contributorsUrl='" + contributorsUrl + '\'' +
                ", subscribersUrl='" + subscribersUrl + '\'' +
                ", subscriptionUrl='" + subscriptionUrl + '\'' +
                ", commitsUrl='" + commitsUrl + '\'' +
                ", gitCommitsUrl='" + gitCommitsUrl + '\'' +
                ", commentsUrl='" + commentsUrl + '\'' +
                ", issueCommentUrl='" + issueCommentUrl + '\'' +
                ", contentsUrl='" + contentsUrl + '\'' +
                ", compareUrl='" + compareUrl + '\'' +
                ", mergesUrl='" + mergesUrl + '\'' +
                ", archiveUrl='" + archiveUrl + '\'' +
                ", downloadsUrl='" + downloadsUrl + '\'' +
                ", issuesUrl='" + issuesUrl + '\'' +
                ", pullsUrl='" + pullsUrl + '\'' +
                ", milestonesUrl='" + milestonesUrl + '\'' +
                ", notificationsUrl='" + notificationsUrl + '\'' +
                ", labelsUrl='" + labelsUrl + '\'' +
                ", releasesUrl='" + releasesUrl + '\'' +
                ", deploymentsUrl='" + deploymentsUrl + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", pushedAt='" + pushedAt + '\'' +
                ", gitUrl='" + gitUrl + '\'' +
                ", sshUrl='" + sshUrl + '\'' +
                ", cloneUrl='" + cloneUrl + '\'' +
                ", svnUrl='" + svnUrl + '\'' +
                ", homepage=" + homepage +
                ", size=" + size +
                ", stargazersCount=" + stargazersCount +
                ", watchersCount=" + watchersCount +
                ", language='" + language + '\'' +
                ", hasIssues=" + hasIssues +
                ", hasProjects=" + hasProjects +
                ", hasDownloads=" + hasDownloads +
                ", hasWiki=" + hasWiki +
                ", hasPages=" + hasPages +
                ", forksCount=" + forksCount +
                ", mirrorUrl='" + mirrorUrl + '\'' +
                ", archived=" + archived +
                ", openIssuesCount=" + openIssuesCount +
                ", license=" + license +
                ", forks=" + forks +
                ", openIssues=" + openIssues +
                ", watchers=" + watchers +
                ", defaultBranch='" + defaultBranch + '\'' +
                '}';
    }
}
