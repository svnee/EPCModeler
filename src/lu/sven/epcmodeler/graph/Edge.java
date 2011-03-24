package lu.sven.epcmodeler.graph;

import java.io.IOException;
import java.io.StringReader;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class Edge {
	
	private String id;
	private String timestamp;
	public String source;
	public String dest;
	
	public Edge(String id, String ts){
		this.id=id;
		this.setTimestamp(ts);
	}

	public String toString() {
		return "";
	}
	
	public boolean equals(Object o) {
		if(o instanceof Edge){
			Edge e = (Edge)o;
			return e.source.equals(source) && e.dest.equals(dest);
		}
		return false;
		
	}
	
	public String getID(){
		return id;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getTimestamp() {
		return timestamp;
	}
	
	public Edge(String gml) {
		SAXBuilder parser = new SAXBuilder();
		try {
			Document doc = parser.build(new StringReader(gml));
			Element root = doc.getRootElement();
			this.source = root.getAttributeValue("source");
			this.dest = root.getAttributeValue("target");
			this.id = source + "->" + dest;
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

