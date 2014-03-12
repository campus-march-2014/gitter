package github.controllers;


import github.models.DataType;
import github.models.RepoInfo;
import github.services.RepositoryCollector;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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
		for(int i= 0; i<data.length;i++)
		{
			System.out.println(data[i]);
		}
	}
}
