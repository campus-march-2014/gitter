// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package github.services;

import github.services.RepositoryCollector;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect RepositoryCollector_Roo_Jpa_Entity {
    
    declare @type: RepositoryCollector: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long RepositoryCollector.id;
    
    @Version
    @Column(name = "version")
    private Integer RepositoryCollector.version;
    
    public Long RepositoryCollector.getId() {
        return this.id;
    }
    
    public void RepositoryCollector.setId(Long id) {
        this.id = id;
    }
    
    public Integer RepositoryCollector.getVersion() {
        return this.version;
    }
    
    public void RepositoryCollector.setVersion(Integer version) {
        this.version = version;
    }
    
}
