package github.services.parser;

import github.models.Data;
import github.models.DataCollection;
import github.models.DataProperty;
import github.models.DataPropertyType;
import github.models.DataType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Service;

@Service
public class CommitParserImpl implements Parser {

	@Override
	public DataCollection parseData(String jsonString) {

		JsonFactory factory = new JsonFactory();
		ObjectMapper mapper = new ObjectMapper(factory);
		TypeReference<ArrayList<Object>> typeRef = new TypeReference<ArrayList<Object>>() {};
		DataCollection dataCollection = new DataCollection();
		try {	
			ArrayList<Object> objects = mapper.readValue(jsonString, typeRef);
			String url="";
			List<Data> list = new ArrayList<Data>();

			for (int i = 0; i < objects.size(); i++) {
				
				HashMap<String, Object> hashMap = (HashMap<String, Object>) objects.get(i);
				url = (String) hashMap.get("url");
				DataProperty urlProperty = createUrlDataProperty(url);

				HashMap<String, Object> commit = (HashMap<String, Object>) hashMap.get("commit");
				HashMap<String, Object> committer = (HashMap<String, Object>) commit.get("committer");
				
				String committerName = (String) committer.get("name");
				DataProperty committerNameProperty = createDataNameProperty(committerName);
				
				String message = (String) commit.get("message");
				DataProperty committerMessageProperty = createCommitterMessageProperty(message);

				Data data = createData(urlProperty, committerNameProperty,committerMessageProperty);
				
				list.add(data);
			}
			getChanel(dataCollection, url);
			dataCollection.setData(list);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dataCollection;
	}

	private void getChanel(DataCollection dataCollection, String url) {
		String[] splittedUrl = url.split("/");
		String channel = splittedUrl[5];
		dataCollection.setChanelName(channel);
	}

	private DataProperty createCommitterMessageProperty(String message) {
		DataProperty committerMessageProperty = new DataProperty();
		committerMessageProperty.setName("CommitterMessage");
		committerMessageProperty.setValue(message);
		return committerMessageProperty;
	}

	private DataProperty createDataNameProperty(String committerName) {
		DataProperty committerNameProperty = new DataProperty();
		committerNameProperty.setName("CommitterName");
		committerNameProperty.setValue(committerName);
		committerNameProperty.setDataPropertyType(DataPropertyType.STRING);
		return committerNameProperty;
	}

	private DataProperty createUrlDataProperty(String url) {
		DataProperty urlProperty = new DataProperty();
		urlProperty.setName("CommitURL");
		urlProperty.setValue(url);
		return urlProperty;
	}

	private Data createData(DataProperty urlProperty,
			DataProperty committerNameProperty,
			DataProperty committerMessageProperty) {
		Set<DataProperty> commitPropertiesSet = new HashSet<DataProperty>();
		commitPropertiesSet.add(urlProperty);
		commitPropertiesSet.add(committerNameProperty);
		commitPropertiesSet.add(committerMessageProperty);
		Data data = new Data();
		data.setFields(commitPropertiesSet);
		data.setDataType(DataType.COMMIT);
		return data;
	}

}
