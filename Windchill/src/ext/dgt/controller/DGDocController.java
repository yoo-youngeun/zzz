package ext.dgt.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ext.dgt.service.DGDocService;
import wt.doc.WTDocument;

@Controller
@RequestMapping("/doc")
public class DGDocController {
	@Autowired
	private DGDocService service;
	
	@RequestMapping("/searchDoc")
	public ModelAndView searchDoc() throws Exception {
		return new ModelAndView("/jsp/doc/docList");
	}
	
	@RequestMapping("/docList")
	public ModelAndView docList(@RequestParam Map<String, Object> param) throws Exception {
		ModelAndView mv = new ModelAndView();
		List<WTDocument> docList = service.searchDoc(param);
		System.out.println("---- -do cL is t-----");
		for (WTDocument doc : docList) {
			System.out.println(doc.getName() + ":d:::" + doc.getDescription());
		}
		mv.addObject("docList", docList);
		
		return mv;
	}
}
