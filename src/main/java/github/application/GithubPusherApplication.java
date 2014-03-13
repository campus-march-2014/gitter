package github.application;
import github.models.Data;
import github.models.DataCollection;
import github.models.DataProperty;
import github.models.DataPropertyType;
import github.models.DataType;
import github.models.RepoInfo;
import github.services.DataCollector;
import github.services.DataProvider;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class GithubPusherApplication {
	HashMap<String, DataCollection> datesOfLastSentCommits = new HashMap<String, DataCollection>();
	
    @Autowired
    private DataProvider dataProvider;
    
    @Autowired
    private DataCollector dataCollector;

    @Scheduled(fixedRate=120000)
    public void run(){
        //List<RepoInfo> repoInfos = RepoInfo.findAllRepoInfoes();
    	//for(RepoInfo ri : repoInfos) {
    	RepoInfo ri = new RepoInfo();
    	ri.setBaseURL("https://api.github.com/repos/campus-march-2014/gitter");
    	Set<DataType> dataTypes = new HashSet<>();
    	dataTypes.add(DataType.COMMIT);
    	ri.setDateTypes(dataTypes);
    		DataCollection commitDataCollection = dataCollector.collectData(ri);
    		String channelName = commitDataCollection.getChanelName();
    		DataCollection lastCommitDate = datesOfLastSentCommits.get(channelName);
    		try {
    			if(lastCommitDate != null) {
    				commitDataCollection = filterAlreadySentCommitsFromNewCommits(commitDataCollection, lastCommitDate);
    			} else {
    				DataProperty zeroDateDataProperty = new DataProperty();
    				zeroDateDataProperty.setName("CommitDate");
    				zeroDateDataProperty.setValue("0000-00-00T00:00:00Z");
    				zeroDateDataProperty.setDataPropertyType(DataPropertyType.STRING);
    				Data zeroDateData = new Data();
    				Set<DataProperty> zeroDateDataProperties = new HashSet<DataProperty>();
    				zeroDateDataProperties.add(zeroDateDataProperty);
    				zeroDateData.setDataType(DataType.COMMIT);
    				zeroDateData.setFields(zeroDateDataProperties);
    				List<Data> lastCommitDateList = new ArrayList<Data>();
    				lastCommitDateList.add(zeroDateData);
    				lastCommitDate = new DataCollection();
    				lastCommitDate.setChanelName(channelName);
    				lastCommitDate.setData(lastCommitDateList);
    			}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
    		
    		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    		try {
    			Data oldLastCommitData = lastCommitDate.getData().get(0);
    			Date oldLastCommitDate = df.parse(lastCommitDate.getData().get(0).getPropertyByName("CommitDate").getValue());
        		for(Data d : commitDataCollection.getData()) {
        			String newLastCommitDateString = d.getPropertyByName("CommitDate").getValue();
    					Date newLastCommitDate = df.parse(newLastCommitDateString);
    					if(newLastCommitDate.after(oldLastCommitDate)) {
    						oldLastCommitDate = newLastCommitDate;
    						oldLastCommitData = d;
    					}
        		}
        		List<Data> lista1 = new ArrayList<Data>();
        		DataProperty newDateDataProperty = new DataProperty();
        		newDateDataProperty.setName("CommitDate");
        		newDateDataProperty.setValue(oldLastCommitData.getPropertyByName("CommitDate").getValue());
        		newDateDataProperty.setDataPropertyType(DataPropertyType.STRING);
        		Data newDateData = new Data();
        		Set<DataProperty> newDatePropertySet = new HashSet<DataProperty>();
        		newDatePropertySet.add(newDateDataProperty);
        		newDateData.setFields(newDatePropertySet);
        		newDateData.setDataType(DataType.COMMIT);
        		lista1.add(newDateData);
        		lastCommitDate.setData(lista1);
    		} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		if(commitDataCollection.getData().size() != 0) {
    			dataProvider.sendData(commitDataCollection);
    		}
    	//}
    }
    
	private DataCollection filterAlreadySentCommitsFromNewCommits(
			DataCollection commitDataCollection,
			DataCollection lastCommitDateCollection) throws ParseException,
			IllegalArgumentException {
		String dateString = lastCommitDateCollection.getData().get(0)
				.getPropertyByName("CommitDate").getValue();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		Date lastCommitDate = df.parse(dateString);
		DataCollection filteredDataCollection = new DataCollection();
		List<Data> newCommits = new ArrayList<Data>();
		for (Data d : commitDataCollection.getData()) {
			Date date = df.parse(d.getPropertyByName("CommitDate").getValue());
			if (lastCommitDate.before(date)) {
				newCommits.add(d);
			}
		}
		filteredDataCollection.setData(newCommits);
		return filteredDataCollection;
	}
}
