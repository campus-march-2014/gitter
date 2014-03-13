package github.services.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import github.models.Data;
import github.models.DataCollection;
import github.models.DataProperty;
import github.models.DataPropertyType;
import github.models.DataType;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.stereotype.Service;

@Service
public class LastCommitDateParser implements Parser {

	@Override
	public DataCollection parseData(String stringResorces) {
		JsonFactory factory = new JsonFactory();
		ObjectMapper mapper = new ObjectMapper(factory);
		TypeReference<ArrayList<Object>> typeRef = new TypeReference<ArrayList<Object>>() {
		};
		DataCollection dataCollection = new DataCollection();
		try {
			ArrayList<Object> objects = mapper.readValue(stringResorces,
					typeRef);
			List<Data> listData = new ArrayList<Data>();

			String lastCommitDate = getLastCommitDate(objects);
			DataProperty commitDateProperty = createDataProperty(lastCommitDate);
			createData(dataCollection, listData, commitDateProperty);

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

	private String getLastCommitDate(ArrayList<Object> objects) {
		HashMap<String, Object> hashMap = (HashMap<String, Object>) objects
				.get(0);
		HashMap<String, Object> commit = (HashMap<String, Object>) hashMap
				.get("commit");
		HashMap<String, Object> committer = (HashMap<String, Object>) commit
				.get("committer");
		String lastCommitDate = (String) committer.get("date");
		return lastCommitDate;
	}

	private void createData(DataCollection dataCollection, List<Data> listData,
			DataProperty commitDateProperty) {
		Set<DataProperty> commitPropertiesSet = new HashSet<DataProperty>();
		commitPropertiesSet.add(commitDateProperty);
		Data data = new Data();
		data.setFields(commitPropertiesSet);
		data.setDataType(DataType.COMMIT);
		dataCollection.setData(listData);
		listData.add(data);
	}

	private DataProperty createDataProperty(String lastCommitDate) {
		DataProperty commitDateProperty = new DataProperty();
		commitDateProperty.setName("CommitDate");
		commitDateProperty.setValue(lastCommitDate.toString());
		commitDateProperty.setDataPropertyType(DataPropertyType.STRING);
		return commitDateProperty;
	}

}
