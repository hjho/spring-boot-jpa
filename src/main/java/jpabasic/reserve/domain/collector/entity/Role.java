package jpabasic.reserve.domain.collector.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jpabasic.reserve.domain.collector.embed.GrantedPermission;

@Entity
@Table(name = "role")
// DB TABLE: role(id='1', name='TEST')
// DB TABLE: role_perms_1([(role_id='1', perms='A'), (role_id='1', perms='B'), (role_id='1', perms='C')])
// DB TABLE: role_perms_2([(role_id='1', grantor='grant_1', perms='permis_1'), (role_id='1', grantor='grant_2', perms='permis_2')])
// Role(id='1', name='TEST', permissions=[A, B, C], 
// grantedPermissions=[GrantedPermission(permission='permis_1', grantor='grant_1'), GrantedPermission(permission='permis_2', grantor='grant_2')])
public class Role {
    
	@Id
    private String id;
    
    private String name;

    @ElementCollection
    @CollectionTable(name = "role_perms_1", joinColumns = @JoinColumn(name = "role_id"))
    @Column(name = "perms")
    private Set<String> permissions = new HashSet<>();
    
    @ElementCollection
    @CollectionTable(name = "role_perms_2", joinColumns = @JoinColumn(name = "role_id"))
    private Set<GrantedPermission> grantedPermissions = new HashSet<>();

    protected Role() {}

    public Role(String id, String name, Set<String> permissions, Set<GrantedPermission> grantedPermissions) {
    	this.id = id;
    	this.name = name;
    	this.permissions = permissions;
    	this.grantedPermissions = grantedPermissions;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void revokeAll() {
//        this.permissions.clear(); // SELECT 후 delete 실행.
//        this.permissions = new HashSet<>(); // delete 실행.
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "Role(" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", permissions=" + permissions +
                ", grantedPermissions=" + grantedPermissions +
                ")";
    }
}