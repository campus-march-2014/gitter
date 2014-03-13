package github.application;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import github.models.DataType;
import github.models.RepoInfo;
import github.services.DataCollector;
import github.services.DataProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.stereotype.Service;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@Service
public class GithubPusherApplication implements Application {
    private static final int THREAD_COUNT = 1;
    private static final long DEFAULT_DELAY = 0L;
    private static final long DEFAULT_PERIOD = 10;
    private static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;

    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(THREAD_COUNT);

    @Autowired
    private DataProvider dataProvider;
    
    @Autowired
    private DataCollector dataCollector;

    @Override
    @PostConstruct
    public void run(){
    	System.out.println("POKRENUTA RUN METODA");
        this.scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
            	//List<RepoInfo> repoInfos = RepoInfo.findAllRepoInfoes();
            	//for(RepoInfo ri : repoInfos) {
            		RepoInfo ri = new RepoInfo();
            		Set<DataType> dataTypes = new HashSet<DataType>();
            		dataTypes.add(DataType.COMMIT);
            		dataProvider.sendData(dataCollector.collectData(ri));
            	//}
            }
        }, DEFAULT_DELAY, DEFAULT_PERIOD, DEFAULT_TIME_UNIT);
    }

    @Override
    public void stop() {
        this.scheduledExecutorService.shutdown();
    }

}
