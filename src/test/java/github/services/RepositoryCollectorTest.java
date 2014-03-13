package github.services;

import java.util.ArrayList;
import java.util.List;

import github.models.RepoInfo;
import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
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

	@Before
	public void setup() {
		RepoInfo repoInfo1 = new RepoInfo();
		repoInfo1.setBaseURL("info1");
		repoInfo1.persist();
		RepoInfo repoInfo2 = new RepoInfo();
		repoInfo2.setBaseURL("info2");
		repoInfo2.persist();
	}
	@Test
	public void checksNumberOfRows() {
		Assert.assertEquals(2, RepoInfo.countRepoInfoes());
	}
	
	@Test
	public void checksRemoveFromBase() {
		List<RepoInfo> base = RepoInfo.findAllRepoInfoes();
		List<RepoInfo> lista = new ArrayList<RepoInfo>();
		RepoInfo repoInfo = new RepoInfo();
		repoInfo.setBaseURL("info1");
		lista.add(repoInfo);
		for (int i = 0; i < base.size(); i++) {
			RepoInfo infobase = base.get(i);
			boolean is = false;
			for (int j = 0; j < lista.size(); j++) {
				RepoInfo infolista = lista.get(j);
				if (infolista.getBaseURL().equals(infobase.getBaseURL())) {
					is = true;
					break;
				}
			}
			if (!is) {
				infobase.remove();
			}
		}
		List<RepoInfo> novi = RepoInfo.findAllRepoInfoes();
		Assert.assertEquals(1, novi.size());
	}

	@Test
	public void checksUpdate() {
		List<RepoInfo> repos = RepoInfo.findAllRepoInfoes();
		RepoInfo repoInfo = repos.get(0);
		repoInfo.setBaseURL("a");
		repoInfo.merge();
		Assert.assertEquals("a", RepoInfo.findAllRepoInfoes().get(0).getBaseURL());
		Assert.assertEquals(2, RepoInfo.countRepoInfoes());
	}
	
	@After
	public void destry(){
		List<RepoInfo> repos = RepoInfo.findAllRepoInfoes();
		for(RepoInfo ri : repos){
			ri.remove();
		}
	}
}
