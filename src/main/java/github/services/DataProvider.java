package github.services;

import github.models.DataCollection;

public interface DataProvider {
	String sendData(DataCollection dataCollection);
}
