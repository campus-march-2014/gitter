package github.services;
import github.models.DataCollection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import javax.xml.bind.DatatypeConverter;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.stereotype.Service;

@Service
public class GithubDataProvider implements DataProvider {
	private final String APPLICATION_ID = "b4de07150619";
	private final String SERVICE_CHANNELS_LIST_URL = "https://pushapi.infobip.com/1/application/" + APPLICATION_ID + "/channels";
	private final String SERVICE_SEND_MESSAGE_URL = "https://pushapi.infobip.com/3/application/" + APPLICATION_ID + "/message";
	private final String USER_CREDENTIALS = "pushdemo:pushdemo";

	@Override
	public String sendData(DataCollection dataCollection) {
		pushData(dataCollection);
		return dataCollection.toString();
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
	
	private void pushData(DataCollection dataCollection) {
		String pushString = dataCollection.toString();
		pushString = pushString.replaceAll("\\s", " ");
		String channelName = dataCollection.getChanelName();
		channelName = "test";
		pushMessageToChannel(pushString, channelName);
	}
	
	private void pushMessageToChannel(String message, String channelName) {
		try {
			 
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost(SERVICE_SEND_MESSAGE_URL);
			
			String encoding = DatatypeConverter.printBase64Binary((USER_CREDENTIALS).getBytes("UTF-8"));
			postRequest.addHeader("Authorization", "Basic " + encoding);
			postRequest.addHeader("content-type", "application/json; charset=utf-8");
			String params = "{" +
	                "\"notificationMessage\":\"" + message + "\"," +
	                "\"androidData\":{" +
	                "\"title\":\"Android\"," +
	                "\"sound\":\"true\"," +
	                "\"vibrate\":\"true\"," +
	                "\"light\":\"true\"" +
	                "}," +
	                "\"channelNames\":[" +
	                "\"" + channelName + "\"" +
	                "]," +
	                "\"mimeType\":\"text/plain\"," +
	                "\"sentType\":\"channels\"," +
	                "\"OSTypes\":[" +
	                "\"Android\"" +
	                "]" +
	                "}";
			StringEntity input = new StringEntity(params);
			input.setContentType("application/json");
			postRequest.setEntity(input);
	 
			HttpResponse response = httpClient.execute(postRequest);
	 
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatusLine().getStatusCode());
			}
	 
			httpClient.getConnectionManager().shutdown();
	 
		  } catch (MalformedURLException e) {
	 
			e.printStackTrace();
	 
		  } catch (IOException e) {
	 
			e.printStackTrace();
	 
		  }
	}
}
