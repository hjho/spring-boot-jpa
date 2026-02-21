package jpabasic.reserve.domain.generated.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "activity_log")
// DB TABLE: activity_log(id=3, activity_type="test" user_id="홀길동", created="2026-02-11 22:42:07.746642")
public class ActivityLog {
	
    @Id
    @SequenceGenerator(
            name = "activity_log_id", // JPA에서 사용할 생성기 이름
            sequenceName = "activity_seq", // DB에 실제 생성되어 있는 시퀀스명
            initialValue = 10, // 시작 값
            allocationSize = 10 // 시퀀스 한 번 호출에 증가하는 수 (성능 최적화 시 50 이상 설정)
    )
    @GeneratedValue(generator = "activity_log_id", strategy = GenerationType.SEQUENCE)
    // DB TABLE: activity_seq(next_val=10)
    private Long id;
    
    @Column(name = "user_id")
    private String userId;
    
    @Column(name = "activity_type")
    private String activityType;
    
    private LocalDateTime created;

    protected ActivityLog() {}

    public ActivityLog(String userId, String activityType) {
        this.userId = userId;
        this.activityType = activityType;
        this.created = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getActivityType() {
        return activityType;
    }

    public LocalDateTime getCreated() {
        return created;
    }
}