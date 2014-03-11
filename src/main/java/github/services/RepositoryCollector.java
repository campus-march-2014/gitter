package github.services;
import github.models.DataType;
import github.models.RepoInfo;
import github.utils.Reader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.stereotype.Service;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@Service
public class RepositoryCollector {
	public static final String url = "https://api.github.com/users/campus-march-2014/repos";
	
	public void fetchRepositiries(){
		Reader reader = new Reader();
		try {
			String resource = reader.readResource(new URL(url));
			getRepoInfos(resource);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void getRepoInfos(String resource) {
		JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);
        TypeReference<ArrayList<Object>> typeRef = new TypeReference<ArrayList<Object>>() {};
        try {
			ArrayList<Object> objects = mapper.readValue(resource, typeRef);
			for(int i=0;i<objects.size(); i++){
			HashMap<String,Object> map =(HashMap<String,Object>)objects.get(i);
			String html_url = (String) map.get("html_url");
			RepoInfo repoInfo = new RepoInfo();
			repoInfo.setBaseURL(html_url);
			repoInfo.getDateTypes().add(DataType.COMMIT);
			repoInfo.getDateTypes().add(DataType.ISSUE);
			repoInfo.getDateTypes().add(DataType.PULL_REQUEST);
			repoInfo.persist();
			}
			//repository.setDataPropertyType(DataPropertyType.);
			
			
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
