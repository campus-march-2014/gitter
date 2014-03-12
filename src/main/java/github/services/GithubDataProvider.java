package github.services;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.bind.DatatypeConverter;

import github.models.DataCollection;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class GithubDataProvider implements DataProvider {
	private final String APPLICATION_ID = "b4de07150619";
	private final String SERVICE_CHANNELS_LIST_URL = "https://pushapi.infobip.com/1/application/" + APPLICATION_ID + "/channels";
	private final String USER_CREDENTIALS = "pushdemo:pushdemo";

	@Override
	public String sendData(DataCollection dataCollection) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String retrieveChannels() throws IOException {
		try {
			DefaultHttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet(SERVICE_CHANNELS_LIST_URL);

			String encoding = DatatypeConverter.printBase64Binary((USER_CREDENTIALS).getBytes("UTF-8"));
			request.addHeader("Authorization", "Basic " + encoding);
			request.addHeader("content-type", "application/json");
			HttpResponse response = client.execute(request);

			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));

			String responseText = new String();
			String line;
			while ((line = rd.readLine()) != null) {
				responseText += line;
			}

			return responseText;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
