package github.utils.impl;
import github.models.DataCollection;
import github.utils.Parser;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class CommitParserImpl implements Parser {

	@Override
	public DataCollection parseData(String stringResorces) {
		// TODO Auto-generated method stub
		
		return null;
	}
}
