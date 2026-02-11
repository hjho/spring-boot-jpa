package jpabasic.reserve.domain.generated.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
create table jpabegin.review (
  review_id integer not null auto_increment,
  hotel_id varchar(50) not null,
  mark integer,
  writer_name varchar(20),
  `comment` text,
  created datetime
) engine innodb character set utf8mb4;
 */
@Entity
public class Review {
    @Id
    @Column(name = "review_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // persist 시점 식별자 생성
    // 이 후 식별자 사용 가능.
    private Long id;
    
    @Column(name = "hotel_id")
    private String hotelId;
    
    private int mark;
    
    @Column(name = "writer_name")
    private String writerName;
    
    private String comment;
    
    private LocalDateTime created;

    protected Review() {
    }

    public Review(int mark, String hotelId, String writerName, String comment) {
        this.mark = mark;
        this.hotelId = hotelId;
        this.writerName = writerName;
        this.comment = comment;
        this.created = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public int getMark() {
        return mark;
    }

    public String getWriterName() {
        return writerName;
    }

    public String getComment() {
        return comment;
    }

    public LocalDateTime getCreated() {
        return created;
    }
}