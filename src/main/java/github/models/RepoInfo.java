package github.models;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.ElementCollection;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class RepoInfo {

    /**
     */
    private String baseURL;

    /**
     */
    @ElementCollection
    private Set<DataType> dateTypes = new HashSet<DataType>();
}
