// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package github.utils.impl;

import github.utils.impl.RepositoryParser;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect RepositoryParser_Roo_Jpa_Entity {
    
    declare @type: RepositoryParser: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long RepositoryParser.id;
    
    @Version
    @Column(name = "version")
    private Integer RepositoryParser.version;
    
    public Long RepositoryParser.getId() {
        return this.id;
    }
    
    public void RepositoryParser.setId(Long id) {
        this.id = id;
    }
    
    public Integer RepositoryParser.getVersion() {
        return this.version;
    }
    
    public void RepositoryParser.setVersion(Integer version) {
        this.version = version;
    }
    
}
