// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package github.services;

import github.services.GithubDataProvider;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect GithubDataProvider_Roo_Jpa_Entity {
    
    declare @type: GithubDataProvider: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long GithubDataProvider.id;
    
    @Version
    @Column(name = "version")
    private Integer GithubDataProvider.version;
    
    public Long GithubDataProvider.getId() {
        return this.id;
    }
    
    public void GithubDataProvider.setId(Long id) {
        this.id = id;
    }
    
    public Integer GithubDataProvider.getVersion() {
        return this.version;
    }
    
    public void GithubDataProvider.setVersion(Integer version) {
        this.version = version;
    }
    
}