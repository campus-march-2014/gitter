package github.controllers;


import github.models.DataType;
import github.models.RepoInfo;
import github.services.RepositoryCollector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/gitter/**")
@Controller
public class GitterController {

	@RequestMapping
	@ResponseBody
	public Map<String, String> index() {
		Map<String, String> test = new HashMap<>();
		test.put("key", "value");
		return test;
	}
	
	@RequestMapping("/fetchAll")
	@ResponseBody
	public Map<String, Object> fetchAll(){
		RepositoryCollector repositoryCollector = new RepositoryCollector();
		repositoryCollector.fetchRepositiries();
		RepoInfo r = new RepoInfo();
		
		Map<String, Object> uiModel = new HashMap<>();
		uiModel.put("repoInfo", RepoInfo.findAllRepoInfoes());
		uiModel.put("dataType", DataType.values());
		   
		return uiModel;  
	}
	
	@RequestMapping("/fetchFromUI")
	@ResponseBody
	public void fetchFromUI(String proba){
		String[] data = proba.split(",");
		HashMap<String, Set<DataType>> repoInfos = new HashMap<>();
		for(int i= 0; i<data.length;i++)
		{
			Set<DataType> dataTypes = new HashSet<DataType>();
			String[] parts = data[i].split(";");
			String baseURL = parts[0];
			String dataType = parts[1];
			
			if(repoInfos.containsKey(baseURL)){
				Set<DataType> tempSet = repoInfos.get(baseURL);
				tempSet.add(DataType.valueOf(dataType));
				repoInfos.put(baseURL, tempSet);
				continue;
			}
			dataTypes.add(DataType.valueOf(dataType));
			repoInfos.put(baseURL, dataTypes);
			
		}
		ArrayList<RepoInfo> repoInfoList = new ArrayList<>();
		Iterator it = repoInfos.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it.next();
	        RepoInfo repo = new RepoInfo();
			repo.setBaseURL(pairs.getKey().toString());
			repo.setDateTypes((Set<DataType>) pairs.getValue());
			repoInfoList.add(repo);
	        it.remove(); // avoids a ConcurrentModificationException
	    }
		
		System.out.println(repoInfoList.toString());
		
	}
}
