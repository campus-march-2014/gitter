package github.services;

import github.models.DataCollection;
import github.models.RepoInfo;

public interface DataCollector {

	DataCollection collectData(RepoInfo repoInfo);
	
}
