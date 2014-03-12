package github.services;

import java.util.List;

import github.models.RepoInfo;
import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/META-INF/spring/applicationContext.xml")
public class RepositoryCollectorTest {
	@Autowired
	private Collector collector;
	
	
	@Test
	public void testLoadTitle() throws Exception {
		RepoInfo repoInfo = new RepoInfo();
		repoInfo.setBaseURL("info1");
		repoInfo.persist();
		RepoInfo repoInfo2 = new RepoInfo();
		repoInfo.setBaseURL("info2");
		repoInfo.persist();
		List<RepoInfo> list = RepoInfo.findAllRepoInfoes();
		RepoInfo rez = list.get(1);
		Assert.assertEquals("info1",rez.getBaseURL());
	
	}
	
}
