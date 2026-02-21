package jpabasic.reserve.domain.others.entity;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Immutable;

import com.google.gson.Gson;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jpabasic.reserve.domain.others.value.BooleanToYnConverter;

@Immutable
@Entity
@Table(name = "notice")
public class NoticeReadonly {
	
	@Id
    @Column(name = "notice_id")
    private String noticeId;
    
    private String title;
    
    private String content;
    
    @Convert(converter = BooleanToYnConverter.class)
    @Column(name = "open_yn")
    private boolean opened;
    
    // 테스트용.
    @Column(name = "category_code")
    private String categoryCode;
    
    @Formula("(select c.value from category c where c.code = category_code)") 
    private String categoryValue;

    @Column(columnDefinition = "datetime default current_timestamp") // 테스트용.
    private String created;
    
    protected NoticeReadonly() {}
    
    // 테스트용.
    public NoticeReadonly(String noticeId, String title, String content, boolean opened, String categoryCode) {
        this.noticeId = noticeId;
        this.title = title;
        this.content = content;
        this.opened = opened;
        this.categoryCode = categoryCode;
    }

    public String getNoticeId() {
        return noticeId;
    }
    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }
    public boolean isOpened() {
        return opened;
    }
    public String getCategoryValue() {
    	return categoryValue;
    }
    public String getCreated() {
    	return created;
    }
    
    @Deprecated // 수정안되는 entity. // 테스트용.
    public void open() {
        this.opened = true;
    }
    
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}