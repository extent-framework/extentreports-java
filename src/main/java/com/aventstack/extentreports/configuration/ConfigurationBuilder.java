package com.aventstack.extentreports.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ConfigurationBuilder {

	private static final Logger logger = Logger.getLogger(ConfigurationBuilder.class.getName());

	private ConfigurationStore store = new ConfigurationStore();
	private InputStream stream;

	public ConfigurationBuilder(URL url) {
		try {
			stream = url.openStream();
		} catch (IOException e) {
			logger.log(Level.SEVERE, url.toString(), e);
		}
	}

	public ConfigurationBuilder(File file, Boolean silent) {
		try {
			if (silent && !file.exists())
				return;
			stream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			logger.log(Level.SEVERE, file.getPath(), e);
		}
	}

	public ConfigurationStore getConfigurationStore() {
		if (stream == null)
			return null;

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder;
		String value;

		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();

			Document doc = documentBuilder.parse(stream);
			doc.getDocumentElement().normalize();

			NodeList nodeList = doc.getElementsByTagName("configuration").item(0).getChildNodes();

			for (int ix = 0; ix < nodeList.getLength(); ix++) {
				Node node = nodeList.item(ix);

				Element el = node.getNodeType() == Node.ELEMENT_NODE ? (Element) node : null;

				if (el != null) {
					value = el.getTextContent();

					value = el instanceof CharacterData ? ((CharacterData) el).getData() : value;
					store.storeConfig(el.getNodeName(), value);
				}
			}

			return store;
		} catch (IOException | SAXException | ParserConfigurationException e) {
			logger.log(Level.SEVERE, "Failed to load external configuration", e);
		}

		return null;
	}
}