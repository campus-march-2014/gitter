package github.services;

import github.models.DataType;
import github.models.RepoInfo;
import github.utils.Reader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
public class RepositoryCollector implements Collector {
	public static final String url = "https://api.github.com/users/campus-march-2014/repos";

	public void fetchRepositiries() {
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
		List<RepoInfo> repoInfos = RepoInfo.findAllRepoInfoes();
		List<RepoInfo> repoInfosFromUrl = new ArrayList<RepoInfo>();
		JsonFactory factory = new JsonFactory();
		ObjectMapper mapper = new ObjectMapper(factory);
		TypeReference<ArrayList<Object>> typeRef = new TypeReference<ArrayList<Object>>() {};
		try {
			ArrayList<Object> objects = mapper.readValue(resource, typeRef);
			for (int i = 0; i < objects.size(); i++) {
				HashMap<String, Object> map = (HashMap<String, Object>) objects.get(i);
				String html_url = (String) map.get("html_url");
				RepoInfo repoInfo = new RepoInfo();
				repoInfo.setBaseURL(html_url);
				repoInfosFromUrl.add(repoInfo);
				if (!repoInfoAlreadyInDB(repoInfos, repoInfo)) {
					repoInfo.persist();
					
				}
			}
			repoInfoNotInDB(repoInfosFromUrl, repoInfos);
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

	private void repoInfoNotInDB(List<RepoInfo> repoInfosFromUrl,
			List<RepoInfo> repoInfos) {
		for (int i = 0; i < repoInfos.size(); i++) {
			RepoInfo infobase = repoInfos.get(i);
			boolean is = false;
			for (int j = 0; j < repoInfosFromUrl.size(); j++) {
				RepoInfo infolista = repoInfosFromUrl.get(j);
				if (equalsRipoInfo(infobase, infolista)) {
					is = true;
					break;
				}
			}
			if (!is) {
				infobase.remove();
			}
		}
	}

	private boolean repoInfoAlreadyInDB(List<RepoInfo> repoInfos,
			RepoInfo repoInfo) {
		for (RepoInfo ri : repoInfos) {
			if (equalsRipoInfo(ri, repoInfo)) {
				return true;
			}
		}
		return false;
	}

	private boolean equalsRipoInfo(RepoInfo ri, RepoInfo repoInfo) {
		if (ri.getBaseURL().equals(repoInfo.getBaseURL())
				&& ri.getDateTypes().containsAll(repoInfo.getDateTypes())) {
			return true;
		}
		return false;
	}
}
