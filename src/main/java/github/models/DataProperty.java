package github.models;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.Enumerated;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class DataProperty {

    /**
     */
    private String name;

    /**
     */
    private String value;

    /**
     */
    @Enumerated
    private DataPropertyType dataPropertyType;
}
