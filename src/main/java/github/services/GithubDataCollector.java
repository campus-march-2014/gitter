package github.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Set;

import javax.xml.bind.DatatypeConverter;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.stereotype.Service;

import github.models.DataCollection;
import github.models.DataType;
import github.models.RepoInfo;
import github.utils.Parser;
import github.utils.Reader;
import github.utils.impl.CommitParserImpl;

@Service
public class GithubDataCollector implements DataCollector {
	public static final String COMMITS_RESOURCE = "/commits";
	public static final String GITHUB_BASE_URL = "https://api.github.com/repos/campus-march-2014/gitter";

	@Override
	public DataCollection collectData(RepoInfo repoInfo) {
		try {
			String jsonCommits = getCommitJson(repoInfo);
			Parser commitParser = new CommitParserImpl();
			DataCollection commitDataCollection = commitParser.parseData(jsonCommits);
			return commitDataCollection;
		} catch (MalformedURLException e) {
			System.err.println(e);
			e.printStackTrace();
		} catch(IOException e) {
			System.err.println(e);
		}
		return null;
	}
	
	private URL makeCommitUrl(String baseUrl, Set<DataType> watchedData) throws MalformedURLException {
		URL commitURL = null;
		if(watchedData.contains(DataType.COMMIT)) {
			commitURL = new URL(baseUrl + COMMITS_RESOURCE);			
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
	
	private String getCommitJson(RepoInfo repoInfo) throws MalformedURLException, IOException {
		Reader reader = new Reader();
		String resultingJson = reader.readResource(makeCommitUrl(GITHUB_BASE_URL, repoInfo.getDateTypes()));
		return resultingJson;
	}
}
