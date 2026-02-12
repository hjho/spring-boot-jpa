package jpabasic.reserve.domain.generated.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jpabasic.reserve.domain.generated.embed.Address;

@Entity
// DB TABLE: employee(id="20260211", addr1="home1", addr2="home2", zipcode="homecode", waddr1="work1", waddr2="work1", wzipcode="workcode")
// Employee(id=20260211, homeAddress=Address{address1='home1', address2='home2', zipcode='homecode'}, workAddress=Address{address1='work1', address2='work2', zipcode='workcode'})
public class Employee {
    @Id
    private String id;

    @Embedded
    private Address homeAddress;

    // insert into Employee (addr1, addr2, zipcode, waddr1, waddr2, wzipcode ,,,)
    @AttributeOverrides({
            @AttributeOverride(name = "address1", column = @Column(name = "waddr1")),
            @AttributeOverride(name = "address2", column = @Column(name = "waddr2")),
            @AttributeOverride(name = "zipcode", column = @Column(name = "wzipcode"))
    })
    @Embedded
    private Address workAddress;

    protected Employee() {}

    public Employee(String id, Address homeAddress, Address workAddress) {
        this.id = id;
        this.homeAddress = homeAddress;
        this.workAddress = workAddress;
    }

    public String getId() {
        return id;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public Address getWorkAddress() {
        return workAddress;
    }
    
    
    public String toString() {
    	return "Employee(id=" + this.id
    			+ ", homeAddress=" + this.homeAddress.toString()
    			+ ", workAddress=" + this.workAddress.toString()
    			+ ")";
    }
}