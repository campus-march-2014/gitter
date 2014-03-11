package github.models;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class DataCollection {

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Data> data = new ArrayList<Data>();

    /**
     */
    private String chanelName;
}
