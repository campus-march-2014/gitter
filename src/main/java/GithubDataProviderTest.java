import github.models.DataCollection;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.junit.Before;
import org.junit.Test;

public class GithubDataProviderTest {
	private final String USER_AGENT = "Mozilla/5.0";
	private final String APPLICATION_ID = "b4de07150619";
	private final String SERVICE_CHANNELS_LIST_URL = "https://pushapi.infobip.com/1/application/" + APPLICATION_ID + "/channels";
	private final String SERVICE_SEND_MESSAGE_URL = "https://pushapi.infobip.com/3/application/" + APPLICATION_ID + "/message";
	private final String USER_CREDENTIALS = "pushdemo:pushdemo";
	
	@Before
	public void setup() {
	}
	
	@Test
	public void getsListOfAvailableChannelsFromInfobipPush() throws IOException {
		retrieveChannels();
	}
	
	@Test
	public void pushesDataCorrectlyToTestChannel() {
		pushData1();
	}
	
	private void pushData(DataCollection dataCollection) {
		String channel = dataCollection.getChanelName();
	}
	
	private void pushData1() {
		try {
			 
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost(SERVICE_SEND_MESSAGE_URL);
			
			String encoding = DatatypeConverter.printBase64Binary((USER_CREDENTIALS).getBytes("UTF-8"));
			postRequest.addHeader("Authorization", "Basic " + encoding);
			postRequest.addHeader("content-type", "application/json; charset=utf-8");
			String params = "{" +
	                "\"notificationMessage\":\"poruka sa servisa\"," +
	                "\"androidData\":{" +
	                "\"title\":\"Android\"," +
	                "\"sound\":\"true\"," +
	                "\"vibrate\":\"true\"," +
	                "\"light\":\"true\"" +
	                "}," +
	                "\"channelNames\":[" +
	                "\"test\"" +
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
	 
			if (response.getStatusLine().getStatusCode() != 201) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatusLine().getStatusCode());
			}
	 
			BufferedReader br = new BufferedReader(
	                        new InputStreamReader((response.getEntity().getContent())));
	 
			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
	 
			httpClient.getConnectionManager().shutdown();
	 
		  } catch (MalformedURLException e) {
	 
			e.printStackTrace();
	 
		  } catch (IOException e) {
	 
			e.printStackTrace();
	 
		  }
	}
	
	private void pushData() {
		try {
			DefaultHttpClient client = new DefaultHttpClient();
			HttpPost request = new HttpPost(SERVICE_SEND_MESSAGE_URL);

			String encoding = DatatypeConverter.printBase64Binary((USER_CREDENTIALS).getBytes("UTF-8"));
			request.addHeader("Authorization", "Basic " + encoding);
			request.addHeader("content-type", "application/json; charset=utf-8");
			String params = "{" +
	                "\"notificationMessage\":\"notification\"," +
	                "\"androidData\":{" +
	                "\"title\":\"Android\"," +
	                "\"sound\":\"true\"," +
	                "\"vibrate\":\"true\"," +
	                "\"light\":\"true\"" +
	                "}," +
	                "\"channelNames\":[" +
	                "\"testniCh1\"" +
	                "]," +
	                "\"mimeType\":\"text/plain\"," +
	                "\"sentType\":\"channels\"," +
	                "\"OSTypes\":[" +
	                "\"Android\"" +
	                "]" +
	                "}";
			HttpParams httpParam = new BasicHttpParams();
			httpParam.setParameter("MessagesPost", params);
			request.setParams(httpParam);
			HttpResponse response = client.execute(request);

			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));

			String responseText = new String();
			String line;
			while ((line = rd.readLine()) != null) {
				responseText += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
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
