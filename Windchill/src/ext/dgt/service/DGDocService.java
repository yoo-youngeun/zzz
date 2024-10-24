package ext.dgt.service;

import java.util.List;
import java.util.Map;

import wt.doc.WTDocument;
import wt.method.RemoteInterface;

@RemoteInterface
public interface DGDocService {
	public List<WTDocument> searchDoc(Map<String, Object> param) throws Exception;
}
