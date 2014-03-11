package github.services;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Set;

import org.springframework.stereotype.Service;

import github.models.DataCollection;
import github.models.DataType;
import github.models.RepoInfo;

@Service
public class GithubDataCollector implements DataCollector {
	public static final String COMMITS_RESOURCE = "/commits";
	public static final String GITHUB_BASE_URL = "https://api.github.com/repos/campus-march-2014/gitter";

	@Override
	public DataCollection collectData(RepoInfo repoInfo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private URL makeCommitUrl(String baseUrl, Set<DataType> watchedData) throws MalformedURLException {
		URL commitURL = null;
		if(watchedData.contains(DataType.COMMIT)) {
			commitURL = new URL(baseUrl + "/commits");			
		}
		return commitURL;
	}
	
	private ArrayList<URL> makeUrls(RepoInfo repoInfo) throws MalformedURLException {
		String baseURL = repoInfo.getBaseURL();
		Set<DataType> observedData = repoInfo.getDateTypes();
		
		ArrayList<URL> urls = new ArrayList<URL>();
		if(shouldWatchCommits(observedData)) {
			urls.add(makeCommitUrl(repoInfo.getBaseURL(), observedData));
		}
		
		return urls;
	}
	
	private boolean shouldWatchCommits(Set<DataType> watchData) {
		return watchData.contains(DataType.COMMIT);
	}
}
