package github.models;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.Enumerated;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Data {

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<DataProperty> fields = new HashSet<DataProperty>();

    /**
     */
    @Enumerated
    private DataType dataType;
    
    public DataProperty getPropertyByName (String propertyName) throws IllegalArgumentException {
        Object[] dataProperties = fields.toArray();
        for(Object dataProperty : dataProperties) {
         if(((DataProperty)dataProperty).getName().equals(propertyName)) {
          return (DataProperty)dataProperty;
         }
        }
        
        throw new IllegalArgumentException("Property with given property name does not exist in Data");
    }
}
