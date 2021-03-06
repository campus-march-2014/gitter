// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package github.models;

import github.models.RepoInfo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect RepoInfo_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager RepoInfo.entityManager;
    
    public static final EntityManager RepoInfo.entityManager() {
        EntityManager em = new RepoInfo().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long RepoInfo.countRepoInfoes() {
        return entityManager().createQuery("SELECT COUNT(o) FROM RepoInfo o", Long.class).getSingleResult();
    }
    
    public static List<RepoInfo> RepoInfo.findAllRepoInfoes() {
        return entityManager().createQuery("SELECT o FROM RepoInfo o", RepoInfo.class).getResultList();
    }
    
    public static RepoInfo RepoInfo.findRepoInfo(Long id) {
        if (id == null) return null;
        return entityManager().find(RepoInfo.class, id);
    }
    
    public static List<RepoInfo> RepoInfo.findRepoInfoEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM RepoInfo o", RepoInfo.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void RepoInfo.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void RepoInfo.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            RepoInfo attached = RepoInfo.findRepoInfo(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void RepoInfo.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void RepoInfo.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public RepoInfo RepoInfo.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        RepoInfo merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
