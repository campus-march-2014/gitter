package github.services.parser;

import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

@Service
public class DefaultReader implements Reader{
	
	public String readResource(URL url) throws IOException{
        Resource resource = new UrlResource(url);
        return IOUtils.toString(resource.getInputStream());
    }
	
}
