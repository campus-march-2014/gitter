import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import github.models.Data;
import github.models.DataCollection;
import github.models.DataType;
import github.models.RepoInfo;
import github.services.parser.CommitParserImpl;
import github.services.parser.DefaultReader;
import github.services.parser.LastCommitDateParser;
import github.services.parser.Parser;

import org.junit.Before;
import org.junit.Test;


public class Tests {
	@Before
	public void setup() {
		
	}
	
	@Test
	public void returnsOnlyNewCommits() throws MalformedURLException, IOException, ParseException {
		RepoInfo ri = new RepoInfo();
		ri.setBaseURL("https://api.github.com/repos/campus-march-2014/gitter");
		Set<DataType> dataTypes = new HashSet<>();
    	dataTypes.add(DataType.COMMIT);
    	ri.setDateTypes(dataTypes);
		String jsonCommits = getCommitJson(ri);
		Parser commitParser = new CommitParserImpl();
		DataCollection commitDataCollection = commitParser.parseData(jsonCommits);
		LastCommitDateParser lastCommitDateParser = new LastCommitDateParser();
		DataCollection lastCommitDateCollection = lastCommitDateParser.parseData(jsonCommits);
		/*
		 * uporedi datum za svaki Data element u commitDataCollection sa datumom u lastCommitDateParser
		 * 	ukoliko je datum elementa u commitDataCollection veci od datuma u lastCommitDateParser
		 * 		postavi ovaj element u rezultujuci filteredDataCollection
		 * posalji filteredDataCollection na PUSH.
		 */
		String dateString = lastCommitDateCollection.getData().get(0).getPropertyByName("CommitDate").getValue();
	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	    Date lastCommitDate =  df.parse(dateString); 
		DataCollection filteredDataCollection = new DataCollection();
		List<Data> newCommits = new ArrayList<Data>();
		for(Data d : commitDataCollection.getData()) {
			Date date = df.parse(d.getPropertyByName("CommitDate").getValue());
			if(lastCommitDate.before(date)) {
				newCommits.add(d);
			}
		}
		filteredDataCollection.setData(newCommits);
	}
	
	private String getCommitJson(RepoInfo repoInfo) throws MalformedURLException, IOException {
		DefaultReader reader = new DefaultReader();
		String resultingJson = reader.readResource(makeCommitUrl(repoInfo.getBaseURL(), repoInfo.getDateTypes()));
		return resultingJson;
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
