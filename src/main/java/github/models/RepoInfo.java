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

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((baseURL == null) ? 0 : baseURL.hashCode());
		result = prime * result
				+ ((dateTypes == null) ? 0 : dateTypes.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RepoInfo other = (RepoInfo) obj;
		if (baseURL == null) {
			if (other.baseURL != null)
				return false;
		} else if (!baseURL.equals(other.baseURL))
			return false;
		if (dateTypes == null) {
			if (other.dateTypes != null)
				return false;
		} else if (!dateTypes.equals(other.dateTypes))
			return false;
		return true;
	}

	/**
     */
    private String baseURL;

    /**
     */
    @ElementCollection
    private Set<DataType> dateTypes = new HashSet<DataType>();
    
    
    
}
