package jpabasic.reserve.domain.others.entity;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import com.google.gson.Gson;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Immutable
@Entity
@Subselect("""
		select n.notice_id
		     , n.title
		     , n.content
		     , n.open_yn
		     , c.value
		  from notice n
		  left join category c
		    on n.category_code = c.code
""")
public class NoticeSubselect {
	
	@Id
    @Column(name = "notice_id")
    private String noticeId;
    
    private String title;
    
    private String content;
    
    @Column(name = "open_yn")
    private String openYn;
    
    @Column(name = "value")
    private String categoryValue;

    protected NoticeSubselect() {}

    public String getNoticeId() {
        return noticeId;
    }
    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }
    public String getOpenYn() {
        return openYn;
    }
    public String getCategoryValue() {
    	return categoryValue;
    }
    
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}