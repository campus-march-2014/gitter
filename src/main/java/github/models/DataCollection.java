package github.models;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    
    @Override
    public String toString() {
    	StringBuilder stringBuilder = new StringBuilder("");
    	for(Data d : data) {
    		if(d.getDataType() == DataType.COMMIT) {
    			stringBuilder.append("Commiter: " + d.getPropertyByName("CommitterName") + "\n");
    			stringBuilder.append("CommitURL" + d.getPropertyByName("CommitURL") + "\n");
    			stringBuilder.append("Message" + d.getPropertyByName("message") + "\n");
    		}
    	}
    	
    	return stringBuilder.toString();
    }
}