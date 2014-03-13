package github.services.parser;

import java.io.IOException;
import java.net.URL;

public interface Reader {
	
	String readResource(URL url) throws IOException;
	
}
