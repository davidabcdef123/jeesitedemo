package com.superman.modules.cms.entity;

import com.superman.common.persistence.DataEntity;
import com.superman.common.utils.IdGen;
import com.superman.modules.sys.entity.User;

import java.util.Date;

/**
 * Define Super.Sun.
 * <p>Created with IntelliJ IDEA on 2016/6/15.</p>
 * 留言Entity
 * @author Super.Sun
 * @version 1.0
 */
public class Guestbook  extends DataEntity<Guestbook> {

    private static final long serialVersionUID = 1L;
    private String type; 	// 留言分类（咨询、建议、投诉、其它）
    private String content; // 留言内容
    private String name; 	// 姓名
    private String email; 	// 邮箱
    private String phone; 	// 电话
    private String workunit;// 单位
    private String ip; 		// 留言IP
    private Date createDate;// 留言时间
    private User reUser; 		// 回复人
    private Date reDate;	// 回复时间
    private String reContent;// 回复内容
    private String delFlag;	// 删除标记删除标记（0：正常；1：删除；2：审核）

    public Guestbook() {
        this.delFlag = DEL_FLAG_AUDIT;
    }

    public Guestbook(String id){
        this();
        this.id = id;
    }

    public void prePersist(){
        this.id = IdGen.uuid();
        this.createDate = new Date();
    }
}
