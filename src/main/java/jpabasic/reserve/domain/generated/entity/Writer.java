package jpabasic.reserve.domain.generated.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.SecondaryTables;
import jakarta.persistence.Table;
import jpabasic.reserve.domain.generated.value.Address;
import jpabasic.reserve.domain.generated.value.Intro;

@Entity
@Table(name = "writer")
@SecondaryTables({
        @SecondaryTable(name = "writer_address",
                pkJoinColumns = @PrimaryKeyJoinColumn(name = "writer_id", referencedColumnName = "id")
        ),
        @SecondaryTable(name = "writer_intro",
                pkJoinColumns = @PrimaryKeyJoinColumn(name = "writer_id", referencedColumnName = "id")
        )}
)
// DB TABLE: writer(id=1, name="홍길동")
// DB TABLE: writer_address(writer_id=1, addr1="address1", addr2="address2", zipcode="zipcode")
// DB TABLE: writer_intro(writer_id=1, content="내용", content_type="type")
// Writer{id=2, name='홍길동', address=Address{address1='address1', address2='address2', zipcode='zipcode'}, intro=Intro{contentType='type', content='내용'}}
public class Writer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "address1", column = @Column(table = "writer_address", name = "addr1")),
            @AttributeOverride(name = "address2", column = @Column(table = "writer_address", name = "addr2")),
            @AttributeOverride(name = "zipcode", column = @Column(table = "writer_address"))
    })
    private Address address;

    @Embedded
    private Intro intro;

    protected Writer() {}

    public Writer(String name, Address address, Intro intro) {
        this.name = name;
        this.address = address;
        this.intro = intro;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public Intro getIntro() {
        return intro;
    }

    @Override
    public String toString() {
        return "Writer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", intro=" + intro +
                '}';
    }
}