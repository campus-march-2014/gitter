// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package github.models;

import github.models.Data;
import github.models.DataCollection;
import java.util.List;

privileged aspect DataCollection_Roo_JavaBean {
    
    public List<Data> DataCollection.getData() {
        return this.data;
    }
    
    public void DataCollection.setData(List<Data> data) {
        this.data = data;
    }
    
    public String DataCollection.getChanelName() {
        return this.chanelName;
    }
    
    public void DataCollection.setChanelName(String chanelName) {
        this.chanelName = chanelName;
    }
    
}
