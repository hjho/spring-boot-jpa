package jpabasic.reserve.domain.generated.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;

@Entity
@Table(name = "access_log")
// DB TABLE: access_log(id=1, path="/access/log/test", accessed="2026-02-11 22:48:11.225035")
public class AccessLog {
    @Id
    @TableGenerator(
            name = "access_log_id",
            table = "id_seq", // 키 관리 테이블명
            pkColumnName = "entity",  // 테이블에서 사용할 컬럼명1
            pkColumnValue = "accesslog", // 테이블에서 사용할 컬럼값1
            valueColumnName = "nextval", // 테이블에서 사용할 컬럼명2
            initialValue = 0, // 테이블에서 사용할 컬럼값2
            allocationSize = 1 // 한번에 1개씩 증가
    )
    @GeneratedValue(generator = "access_log_id", strategy = GenerationType.TABLE)
    // DB TABLE: id_seq(entity=accesslog, nextval=1)
    private Long id;
    
    private String path;
    
    private LocalDateTime accessed;

    protected AccessLog() {
    }

    public AccessLog(String path) {
        this.path = path;
        this.accessed = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public LocalDateTime getAccessed() {
        return accessed;
    }
}