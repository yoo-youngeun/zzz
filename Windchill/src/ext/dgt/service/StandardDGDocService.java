package ext.dgt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import ext.dgt.util.CommonUtil;
import wt.doc.WTDocument;
import wt.fc.PersistenceHelper;
import wt.fc.QueryResult;
import wt.query.QuerySpec;
import wt.query.SearchCondition;

@Service
public class StandardDGDocService implements DGDocService{

	@Override
	public List<WTDocument> searchDoc(Map<String, Object> param) throws Exception {
		
		List<WTDocument> docList = new ArrayList<>();
		QuerySpec query = new QuerySpec();
		int docNum = query.addClassList(WTDocument.class, true);
		
		String name = (String) param.get("name");
		if (CommonUtil.isNotNull(name)) {
			query.appendWhere(
				 new SearchCondition(WTDocument.class, "master>name", 
				 SearchCondition.EQUAL, name), new int[]{docNum}
			);
		}
		
		QueryResult qr = PersistenceHelper.manager.find(query);
		
		while (qr.hasMoreElements()) {
			Object[] obj = (Object[]) qr.nextElement();
			
			if (obj[0] instanceof WTDocument) {
				WTDocument temp = (WTDocument) obj[0];
				WTDocument doc = WTDocument.newWTDocument();
				
				doc.setName(temp.getName());
				doc.setNumber(temp.getNumber());
				doc.setDescription(temp.getDescription());
				
				docList.add(doc);
				
			}
		}
		
		return docList;
	}
	
}
