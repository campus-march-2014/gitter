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
}
