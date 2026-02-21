package jpabasic.reserve.domain.others.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.google.gson.Gson;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jpabasic.reserve.domain.others.value.BooleanToYnConverter;

@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "notice")
public class Notice {
	
	// @DynamicInsert 에선 null이면 필드로 추가되지 않아
	// @GeneratedValue 어노테이션이 안됨.
    @Id
    @Column(name = "notice_id")
    private String noticeId;
    
    private String title;
    
    private String content;
    
    @Convert(converter = BooleanToYnConverter.class)
    @Column(name = "open_yn")
    private boolean opened;
    
    @Column(name = "category_code")
    private String categoryCode;
    
    @Column(columnDefinition = "datetime default current_timestamp")
    private String created;

    protected Notice() {}
    public Notice(String noticeId, String title, String content, boolean opened, String categoryCode) {
        this.noticeId = noticeId;
        this.title = title;
        this.content = content;
        this.opened = opened;
        this.categoryCode = categoryCode;
    }

    public void updateContent(String content) {
    	this.content = content;
    }
    public void open() {
        this.opened = true;
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
    public String getCategoryCode() {
    	return categoryCode;
    }
    public String getCreated() {
    	return created;
    }

    @Override
    public String toString() {
//    	Map<String, Object> map = new HashMap<>();
//    	map.put("notice_id", id);
//    	map.put("title", title);
//    	map.put("content", content);
//    	map.put("opened", opened);
//    	map.put("created", created);
        return new Gson().toJson(this);
    }
}