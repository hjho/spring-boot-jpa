package jpabasic.reserve.domain.collector.embed;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class GrantedPermission {
	
	@Column(name = "perms")
    private String permission;
    
    private String grantor;

    protected GrantedPermission() {}

    public GrantedPermission(String permission, String grantor) {
        this.permission = permission;
        this.grantor = grantor;
    }

    public String getPermission() {
        return permission;
    }

    public String getGrantor() {
        return grantor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrantedPermission that = (GrantedPermission) o;
        return Objects.equals(permission, that.permission) && Objects.equals(grantor, that.grantor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(permission, grantor);
    }

    @Override
    public String toString() {
        return "GrantedPermission(" +
                "permission='" + permission + '\'' +
                ", grantor='" + grantor + '\'' +
                ")";
    }
}