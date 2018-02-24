package com.dingtalk.api.model.corp;

import java.io.Serializable;
import java.util.List;

/**
 * @Name：用户详情列表
 * @Author：xuhaowende
 * @Date：2018-02-19
 */
public class CorpUserDetailListExt implements Serializable  {

    private Boolean hasMore;
    private List<CorpUserDetailExt> userlist;

    public CorpUserDetailListExt() {
    }

    public Boolean isHasMore() {
        return this.hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }

    public List<CorpUserDetailExt> getUserlist() {
        return this.userlist;
    }

    public void setUserlist(List<CorpUserDetailExt> userlist) {
        this.userlist = userlist;
    }

    public int hashCode() {
        boolean prime = true;
        byte result = 1;
        int result1 = 31 * result + (this.hasMore == null?0:this.hasMore.hashCode());
        result1 = 31 * result1 + (this.userlist == null?0:this.userlist.hashCode());
        return result1;
    }

    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        } else if(obj == null) {
            return false;
        } else if(this.getClass() != obj.getClass()) {
            return false;
        } else {
            CorpUserDetailListExt other = (CorpUserDetailListExt)obj;
            if(this.hasMore == null) {
                if(other.hasMore != null) {
                    return false;
                }
            } else if(!this.hasMore.equals(other.hasMore)) {
                return false;
            }

            if(this.userlist == null) {
                if(other.userlist != null) {
                    return false;
                }
            } else if(!this.userlist.equals(other.userlist)) {
                return false;
            }

            return true;
        }
    }
}
