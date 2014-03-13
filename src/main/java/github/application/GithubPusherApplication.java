package github.application;
import github.models.Data;
import github.models.DataType;
import github.models.RepoInfo;
import github.services.DataCollector;
import github.services.DataProvider;
import github.services.GithubDataCollector;
import github.services.GithubDataProvider;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class GithubPusherApplication {
    private static final int THREAD_COUNT = 1;
    private static final long DEFAULT_DELAY = 0L;
    private static final long DEFAULT_PERIOD = 10;
    private static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;

    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(THREAD_COUNT);

    @Autowired
    private DataProvider dataProvider;
    
    @Autowired
    private DataCollector dataCollector;

    @Scheduled(fixedRate=15000)
    public void run(){
        //List<RepoInfo> repoInfos = RepoInfo.findAllRepoInfoes();
    	//for(RepoInfo ri : repoInfos) {
    	RepoInfo ri = new RepoInfo();
    	ri.setBaseURL("https://api.github.com/repos/campus-march-2014/gitter");
    	Set<DataType> dataTypes = new HashSet<>();
    	dataTypes.add(DataType.COMMIT);
    	ri.setDateTypes(dataTypes);
    		dataProvider.sendData(dataCollector.collectData(ri));
    	//}
    }
}
