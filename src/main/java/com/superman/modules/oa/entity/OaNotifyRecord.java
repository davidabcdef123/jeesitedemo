package com.superman.modules.oa.entity;

import com.superman.common.persistence.DataEntity;
import com.superman.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * Define Super.Sun.
 * <p>Created with IntelliJ IDEA on 2016/7/7.</p>
 * 通知通告记录Entity
 * @author Super.Sun
 * @version 1.0
 */
public class OaNotifyRecord  extends DataEntity<OaNotifyRecord> {

    private static final long serialVersionUID = 1L;
    private OaNotify oaNotify;		// 通知通告ID
    private User user;		// 接受人
    private String readFlag;		// 阅读标记（0：未读；1：已读）
    private Date readDate;		// 阅读时间


    public OaNotifyRecord() {
        super();
    }

    public OaNotifyRecord(String id){
        super(id);
    }

    public OaNotifyRecord(OaNotify oaNotify){
        this.oaNotify = oaNotify;
    }

    public OaNotify getOaNotify() {
        return oaNotify;
    }

    public void setOaNotify(OaNotify oaNotify) {
        this.oaNotify = oaNotify;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Length(min=0, max=1, message="阅读标记（0：未读；1：已读）长度必须介于 0 和 1 之间")
    public String getReadFlag() {
        return readFlag;
    }

    public void setReadFlag(String readFlag) {
        this.readFlag = readFlag;
    }

    public Date getReadDate() {
        return readDate;
    }

    public void setReadDate(Date readDate) {
        this.readDate = readDate;
    }
}
