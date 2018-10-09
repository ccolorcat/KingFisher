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

public class License {
    /**
     * key : apache-2.0
     * name : Apache License 2.0
     * spdx_id : Apache-2.0
     * url : https://api.github.com/licenses/apache-2.0
     * node_id : MDc6TGljZW5zZTI=
     */

    @JSONField(name = "key")
    @JsonProperty("key")
    @SerializedName("key")
    private String key;
    @JSONField(name = "name")
    @JsonProperty("name")
    @SerializedName("name")
    private String name;
    @JSONField(name = "spdx_id")
    @JsonProperty("spdx_id")
    @SerializedName("spdx_id")
    private String spdxId;
    @JSONField(name = "url")
    @JsonProperty("url")
    @SerializedName("url")
    private String url;
    @JSONField(name = "node_id")
    @JsonProperty("node_id")
    @SerializedName("node_id")
    private String nodeId;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpdxId() {
        return spdxId;
    }

    public void setSpdxId(String spdxId) {
        this.spdxId = spdxId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    @Override
    public String toString() {
        return "License{" +
                "key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", spdxId='" + spdxId + '\'' +
                ", url='" + url + '\'' +
                ", nodeId='" + nodeId + '\'' +
                '}';
    }
}