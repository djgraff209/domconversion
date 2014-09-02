package com.division6.domconversion;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

@Controller("homeController")
@RequestMapping("/")
public class HomeController {
	@RequestMapping
	public String home() {
		return "home";
	}
	
	@RequestMapping(headers={ "Accept=application/xml", "X-Requested-With=XMLHttpRequest" }, produces="application/xml")
	public @ResponseBody Document getDocument() throws Exception {
		final DocumentBuilderFactory dbf= DocumentBuilderFactory.newInstance();
		final DocumentBuilder db= dbf.newDocumentBuilder();
		
		final Document doc= db.newDocument();
		doc.setXmlStandalone(true);
		final Element foo= doc.createElement("foo");
		final Element bar= doc.createElement("bar");
		bar.setTextContent("This is bar text content");
		foo.appendChild(bar);
		doc.appendChild(foo);
		
		return doc;
	}
}
