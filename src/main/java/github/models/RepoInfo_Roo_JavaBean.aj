// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package github.models;

import github.models.DataType;
import github.models.RepoInfo;
import java.util.Set;

privileged aspect RepoInfo_Roo_JavaBean {
    
    public String RepoInfo.getBaseURL() {
        return this.baseURL;
    }
    
    public void RepoInfo.setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }
    
    public Set<DataType> RepoInfo.getDateTypes() {
        return this.dateTypes;
    }
    
    public void RepoInfo.setDateTypes(Set<DataType> dateTypes) {
        this.dateTypes = dateTypes;
    }
    
}
