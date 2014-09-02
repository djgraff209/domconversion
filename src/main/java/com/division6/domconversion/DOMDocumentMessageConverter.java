package com.division6.domconversion;

import java.io.IOException;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.xml.AbstractXmlHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

@Component("domDocumentMessageConverter")
public class DOMDocumentMessageConverter extends AbstractXmlHttpMessageConverter<Object> {

	@Override
	protected Object readFromSource(Class<? extends Object> clazz,
			HttpHeaders headers, Source source) throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	protected void writeToResult(Object t, HttpHeaders headers, Result result) throws IOException {
		final TransformerFactory tf= TransformerFactory.newInstance();
		Transformer transformer;
		try {
			transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			transformer.transform(new DOMSource((Document)t), result);
		} catch (TransformerConfigurationException e) {
			throw new IOException("Transformer Config Exception", e);
		} catch (TransformerException e) {
			throw new IOException("Transformer Exception", e);
		}
	}

	@Override
	protected boolean supports(Class<?> clazz) {
		return Document.class.isAssignableFrom(clazz);
	}
}
