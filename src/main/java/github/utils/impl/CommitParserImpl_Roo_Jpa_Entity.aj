// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package github.utils.impl;

import github.utils.impl.CommitParserImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect CommitParserImpl_Roo_Jpa_Entity {
    
    declare @type: CommitParserImpl: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long CommitParserImpl.id;
    
    @Version
    @Column(name = "version")
    private Integer CommitParserImpl.version;
    
    public Long CommitParserImpl.getId() {
        return this.id;
    }
    
    public void CommitParserImpl.setId(Long id) {
        this.id = id;
    }
    
    public Integer CommitParserImpl.getVersion() {
        return this.version;
    }
    
    public void CommitParserImpl.setVersion(Integer version) {
        this.version = version;
    }
    
}
