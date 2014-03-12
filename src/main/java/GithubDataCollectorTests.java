import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import org.apache.commons.codec.binary.Base64;

import github.models.DataType;
import github.models.RepoInfo;
import github.services.DataCollector;
import github.services.GithubDataCollector;
import github.utils.Reader;

import static org.junit.Assert.*;

public class GithubDataCollectorTests {
	public static final String COMMITS_RESOURCE = "/commits";
	public static final String GITHUB_BASE_URL = "https://api.github.com/repos/campus-march-2014/gitter";
	RepoInfo repoInfo;
	DataCollector subject;

	@Before
	public void setup() {
		subject = new GithubDataCollector();
		repoInfo = new RepoInfo();
		repoInfo.setBaseURL(GITHUB_BASE_URL);
		Set<DataType> watchedData = new HashSet<DataType>();
		watchedData.add(DataType.COMMIT);
		repoInfo.setDateTypes(watchedData);
	}
	
	@Test
	public void returnsCorrectCommitsUrlForGithubRepo() throws MalformedURLException {
		URL expectedUrl = new URL(GITHUB_BASE_URL+COMMITS_RESOURCE); 
		URL resultingUrl = makeCommitUrl(GITHUB_BASE_URL, repoInfo.getDateTypes());
		assertEquals(expectedUrl, resultingUrl);
	}
	
	@Test
	public void returnsCorrectlyGeneratedUrlsThatAreToBeObserved() throws MalformedURLException {
		ArrayList<URL> expectedUrls = new ArrayList<URL>();
		URL url = new URL(GITHUB_BASE_URL+COMMITS_RESOURCE);
		expectedUrls.add(url);
		assertArrayEquals(expectedUrls.toArray(), makeUrls(repoInfo).toArray());
	}

	@Test
	public void readerReadsDataCorrectly() throws MalformedURLException, IOException {
		Reader reader = new Reader();
		String expectedJsonResult = "";
		String resultingJson = reader.readResource(makeCommitUrl(GITHUB_BASE_URL, repoInfo.getDateTypes()));
		assertEquals(expectedJsonResult, resultingJson);
	}
	
	@Test
	public void returnsCommitJsonCorrectly() throws MalformedURLException, IOException {
		Reader reader = new Reader();
		String expectedJsonResult = "";
		String resultingJson = reader.readResource(makeCommitUrl(GITHUB_BASE_URL, repoInfo.getDateTypes()));
		assertEquals(expectedJsonResult, resultingJson);
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
	
	private URL makeCommitUrl(String baseUrl, Set<DataType> watchedData) throws MalformedURLException {
		URL commitURL = null;
		if(watchedData.contains(DataType.COMMIT)) {
			commitURL = new URL(baseUrl + COMMITS_RESOURCE);			
		}
		return commitURL;
	}

}
