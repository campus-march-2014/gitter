// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package github.application;

import github.application.GithubPusherApplication;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect GithubPusherApplication_Roo_Jpa_Entity {
    
    declare @type: GithubPusherApplication: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long GithubPusherApplication.id;
    
    @Version
    @Column(name = "version")
    private Integer GithubPusherApplication.version;
    
    public Long GithubPusherApplication.getId() {
        return this.id;
    }
    
    public void GithubPusherApplication.setId(Long id) {
        this.id = id;
    }
    
    public Integer GithubPusherApplication.getVersion() {
        return this.version;
    }
    
    public void GithubPusherApplication.setVersion(Integer version) {
        this.version = version;
    }
    
}
