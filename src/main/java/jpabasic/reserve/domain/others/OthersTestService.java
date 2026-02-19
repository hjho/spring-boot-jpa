package jpabasic.reserve.domain.others;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jpabasic.reserve.domain.TestService;
import jpabasic.reserve.domain.others.entity.Notice;
import jpabasic.reserve.domain.others.entity.NoticeReadonly;
import jpabasic.reserve.domain.others.entity.NoticeSubselect;

public class OthersTestService extends TestService {

	public static void main(String[] args) {
		
		OthersTestService service = new OthersTestService();
		
		test(PERSIST, service);
		test(FIND   , service);
		test(CHANGE, service);
		test(FIND   , service);
		test(REMOVE , service);
		
	}
	
	@Override
	public void persist(EntityManager manager, EntityTransaction transaction) {
		NoticeReadonly readonly1 = new NoticeReadonly("N01", "제목1", "내용1", false, "13");
		Notice notice2 = new Notice("N02", "제목2", "내용2", false, "16");
		Notice notice3 = new Notice("N03", "제목3", "내용3", false, "17");
		
		manager.persist(readonly1);
		manager.persist(notice2);
		manager.persist(notice3);
		// insert into notice (category_code, content, created, open_yn, title, notice_id) values (?, ?, ?, ?, ?, ?)
		// insert into notice (category_code, content, open_yn, title, notice_id)  values (?, ?, ?, ?, ?)
		// [main] TRACE org.hibernate.orm.jdbc.bind - binding parameter [1] as [VARCHAR] - [16]
		// [main] TRACE org.hibernate.orm.jdbc.bind - binding parameter [2] as [VARCHAR] - [내용2]
		// [main] TRACE org.hibernate.orm.jdbc.bind - binding parameter [3] as [VARCHAR] - [N]
		// [main] TRACE org.hibernate.orm.jdbc.bind - binding parameter [4] as [VARCHAR] - [제목2]
		// [main] TRACE org.hibernate.orm.jdbc.bind - binding parameter [5] as [VARCHAR] - [N02]
		
		// @Immutable 속성은 persist, remove 는 동작한다. 수정만 안됨.
		// 의도적으로 생성자, 수정함수는 작성하지 말아야 한다.
	}
	
	@Override
	public void find(EntityManager manager, EntityTransaction transaction) {
		NoticeReadonly readonly1 = manager.find(NoticeReadonly.class, "N01");
		Notice notice2 = manager.find(Notice.class, "N02");
		NoticeSubselect subselect3 = manager.find(NoticeSubselect.class, "N03");
		
		System.out.println("1. " + readonly1);
		System.out.println("2. " + notice2);
		System.out.println("3. " + subselect3);
		// 수정 전.
		// 1. {"noticeId":"N01","title":"제목1","content":"내용1","opened":false,"categoryCode":"13","categoryValue":"Team"}
		// 2. {"noticeId":"N02","title":"제목2","content":"내용2","opened":false,"categoryCode":"16","created":"2026-02-19 22:59:06"}
		// 3. {"noticeId":"N03","title":"제목3","content":"내용3","openYn":"N","categoryValue":"AssignPlayer"}
		
		// 수정 후.
		// 1. {"noticeId":"N01","title":"제목1","content":"내용1","opened":false,"categoryCode":"13","categoryValue":"Team"}
		// 2. {"noticeId":"N02","title":"제목2","content":"수정된 내용2","opened":true,"categoryCode":"16","created":"2026-02-19 22:59:06"}
		// 3. {"noticeId":"N03","title":"제목3","content":"내용3","openYn":"N","categoryValue":"AssignPlayer"}
		
		/* 
		// 쿼리: readonly1
		select n1_0.notice_id,
		       (select value from category c where c.code = n1_0.category_code),
		       n1_0.content, n1_0.created, n1_0.open_yn, n1_0.title 
		  from notice n1_0 
		 where n1_0.notice_id=?
		 
		// 쿼리: notice2
		select n1_0.notice_id, n1_0.category_code, n1_0.content, n1_0.created, n1_0.open_yn, n1_0.title
		  from notice n1_0 
         where n1_0.notice_id=?
		 
		// 쿼리: subselect3
		select n1_0.notice_id, n1_0.value, n1_0.content, n1_0.open_yn, n1_0.title 
          from (select n.notice_id, n.title, n.content, n.open_yn, c.value 
                  from notice n
                  left join category c       
                    on n.category_code = c.code
             ) n1_0 
         where n1_0.notice_id=?
		 */
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void change(EntityManager manager, EntityTransaction transaction) {
		NoticeReadonly readonly1 = manager.find(NoticeReadonly.class, "N01");
		Notice notice2 = manager.find(Notice.class, "N02");
		
		// 변경안됨.
		readonly1.open();
		
		// 변경됨.
		notice2.open();
		notice2.updateContent("수정된 내용2");
		
		// update notice set content=?, open_yn=? where notice_id=?
		// [main] TRACE org.hibernate.orm.jdbc.bind - binding parameter [1] as [VARCHAR] - [수정된 내용2]
		// [main] TRACE org.hibernate.orm.jdbc.bind - binding parameter [2] as [VARCHAR] - [Y]
		// [main] TRACE org.hibernate.orm.jdbc.bind - binding parameter [3] as [VARCHAR] - [N02]
		// 수정사항이 없을 경우엔 수정 안함.
	}
	
	@Override
	public void remove(EntityManager manager, EntityTransaction transaction) {
		NoticeReadonly readonly1 = manager.find(NoticeReadonly.class, "N01");
		Notice notice2 = manager.find(Notice.class, "N02");
		Notice subselect3 = manager.find(Notice.class, "N03");
		// NoticeSubselect subselect3 = manager.find(NoticeSubselect.class, "N03");
		
		manager.remove(readonly1);
		manager.remove(notice2);
		manager.remove(subselect3);
		
		// delete from notice where notice_id=?
		// [main] TRACE org.hibernate.orm.jdbc.bind - binding parameter [1] as [VARCHAR] - [N01]
		// delete from notice where notice_id=?
		// [main] TRACE org.hibernate.orm.jdbc.bind - binding parameter [1] as [VARCHAR] - [N02]
		
		// subselect3
		// delete from (select n.notice_id, n.title, n.content, n.open_yn, c.value from notice n left join category c on n.category_code = c.code  ) where notice_id=?
		// 쿼리가 돌기는 함.
		// You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near
		// 문법이 맞으면 돌아갈듯,,?
	}
	
}
