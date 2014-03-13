package github.services.parser;

import github.models.DataCollection;

public interface Parser {
	
	DataCollection parseData(String stringResorces);
	
}
