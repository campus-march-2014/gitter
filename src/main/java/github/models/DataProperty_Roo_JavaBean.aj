// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package github.models;

import github.models.DataProperty;
import github.models.DataPropertyType;

privileged aspect DataProperty_Roo_JavaBean {
    
    public String DataProperty.getName() {
        return this.name;
    }
    
    public void DataProperty.setName(String name) {
        this.name = name;
    }
    
    public String DataProperty.getValue() {
        return this.value;
    }
    
    public void DataProperty.setValue(String value) {
        this.value = value;
    }
    
    public DataPropertyType DataProperty.getDataPropertyType() {
        return this.dataPropertyType;
    }
    
    public void DataProperty.setDataPropertyType(DataPropertyType dataPropertyType) {
        this.dataPropertyType = dataPropertyType;
    }
    
}
