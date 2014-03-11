package github.controllers;

import github.models.DataType;
import github.models.RepoInfo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/examplejson/**")
@Controller
public class ExampleJsonController {

	@RequestMapping
	@ResponseBody
	public Map<String, String> index() {
		Map<String, String> test = new HashMap<>();
		test.put("key", "value");
		return test;
	}
	
	@RequestMapping("/loadRandom")
	public void loadRandom(){
		RepoInfo repoInfo = new RepoInfo();
		repoInfo.setBaseURL("http://test/" + UUID.randomUUID().toString());
		repoInfo.getDateTypes().add(DataType.COMMIT);
		repoInfo.persist();
	}
	
	@RequestMapping("/fetchAll")
	@ResponseBody
	public Collection<RepoInfo> fetchAll(){
		return RepoInfo.findAllRepoInfoes();
	}

}
