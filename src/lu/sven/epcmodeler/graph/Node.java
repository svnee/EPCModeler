package lu.sven.epcmodeler.graph;

import java.awt.Dimension;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.io.StringReader;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class Node implements Comparable<Node>{
	String nodeLabel;
	String nodeId;
	NodeType nodeType;
	String accessType;
	String timestamp;
	NodeVisibility state;
	long tStamp;
	
	private int x;
	private int y;
	
	public Point2D nodePosition;
	public Dimension nodeDimension;
	
	private static Logger logger = Logger.getRootLogger();
	
	public Node(String label, String Id, NodeType type, Point2D position, Dimension dimension, String access, String timestamp, NodeVisibility state) {
		nodeLabel = label;
		nodeId = Id;
		nodeType = type;
		nodePosition = position;
		nodeDimension = dimension;
		accessType=access;
		this.timestamp=timestamp;
		this.state=state;
		this.tStamp= Long.valueOf(timestamp).longValue();
	}
	
	public void setPoint(Point2D p) {
		nodePosition = p;
	}
	
	public String getLabel() {
		return this.nodeLabel;
	}
		
	public void setLabel(String _label) {
		this.nodeLabel = _label;
	}
	
	public void setTimeStamp(String ts) {
		this.timestamp = ts;
	}

	public String getTimeStamp() {
		return this.timestamp;
	}
	
	public String getID() {
		return nodeId;
	}
	
	public void setID(String _id) {
		nodeId = _id;
	}
	
	public String getAccess() {
		return this.accessType;
	}
	
	public NodeVisibility getState() {
		return state;
	}
	
	public void setState(NodeVisibility _state) {
		this.state = _state;
	}
	
	public String toString() {
		switch(nodeType) {
		case OPERATOR_AND:
			return nodeLabel.toUpperCase();
		case OPERATOR_XOR:
			return nodeLabel.toUpperCase();
		case OPERATOR_OR:
			return nodeLabel.toUpperCase();
		default:
			return nodeLabel;
		}
	}
	
	public boolean equals(Object o) {
		if(o instanceof Node){
			Node node2 = (Node)o;
			return this.nodeId.equals(node2.nodeId);
		}
		return false;
	}


	public NodeType getNodeType() {
		return this.nodeType;
	}

	public Shape getShape() {
		switch(this.nodeType) {
		case FUNCTION:
			return new RoundRectangle2D.Float(-this.nodeDimension.width/2, -this.nodeDimension.height/2, this.nodeDimension.width, this.nodeDimension.height, 15, 15);
		case EVENT:
			int[] x = {-(this.nodeDimension.width/2), -((this.nodeDimension.width/2)+5), -(this.nodeDimension.width/2), ((this.nodeDimension.width/2)-5), (this.nodeDimension.width/2), ((this.nodeDimension.width/2)-5)};
			int[] y = {-this.nodeDimension.height/2, 0, this.nodeDimension.height/2, this.nodeDimension.height/2, 0, -this.nodeDimension.height/2};
			return new Polygon(x, y, 6);
		case OPERATOR_AND:
			return new Ellipse2D.Float(-this.nodeDimension.width/2, -this.nodeDimension.height/2, this.nodeDimension.width, this.nodeDimension.height);
		case OPERATOR_XOR:
			return new Ellipse2D.Float(-this.nodeDimension.width/2, -this.nodeDimension.height/2, this.nodeDimension.width, this.nodeDimension.height);
		case OPERATOR_OR:
			return new Ellipse2D.Float(-this.nodeDimension.width/2, -this.nodeDimension.height/2, this.nodeDimension.width, this.nodeDimension.height);
		default:
			return null;
		}
	}
	
	/* This compareTo compares the objects according to its timestamps 
	 * x.compareTo(y)==0 is not equal to x.equals(y)*/
	@Override
	public int compareTo(Node n) {
		logger.debug("Comparing: " + tStamp + " <-> " + n.tStamp);
		if(n instanceof Node){
			Long t1= new Long(tStamp);
			Long t2= new Long(n.tStamp);
			return t1.compareTo(t2);
		}
		return -1;
	}

	public void setNodeType(NodeType t) {
		this.nodeType = t;
	}

	public Node(String gml) {
		SAXBuilder parser = new SAXBuilder();
		try {
			Document doc = parser.build(new StringReader(gml));
			Element root = doc.getRootElement();
			this.nodeId = root.getAttributeValue("id");
			for (Object e : root.getChildren()) {
				Element f = (Element)e;
				if(f.getName().equals("data")) {
					String key = f.getAttribute("key").getValue();
					if(key.equals("timestamp")) {
						this.timestamp = f.getValue();
						this.tStamp= Long.valueOf(timestamp).longValue();
					} else if(key.equals("state")) {
						this.state = NodeVisibility.valueOf(f.getValue());
					} else if(key.equals("label")) {
						this.nodeLabel = f.getValue();
					} else if(key.equals("access")) {
						this.accessType = f.getValue();
					} else if(key.equals("type")) {
						this.nodeType = NodeType.valueOf(f.getValue());
					} else if(key.equals("x")) {
						this.x = new Double(f.getValue()).intValue();
					} else if(key.equals("y")) {
						this.y = new Double(f.getValue()).intValue();
					}
				}
			}
			this.nodePosition = new Point2D.Float(this.x, this.y);
			this.nodeDimension = new Dimension(90,30);
			
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String toGML() {
		String gml = "";
		gml += "<node id=\"" + this.nodeId + "\">\n";
		gml += "\t<data key=\"timestamp\">"+this.timestamp+"</data>\n";
		gml += "\t<data key=\"state\">"+this.state.toString()+"</data>\n";
		gml += "\t<data key=\"label\">"+this.nodeLabel+"</data>\n";
		gml += "\t<data key=\"access\">"+this.accessType+"</data>\n";
		gml += "\t<data key=\"type\">"+this.nodeType.toString()+"</data>\n";
		gml += "\t<data key=\"x\">"+this.nodePosition.getX()+"</data>\n";
		gml += "\t<data key=\"y\">"+this.nodePosition.getY()+"</data>\n";
		gml += "</node>";
		return gml;
	}

	public void setAccess(String _access) {
		this.accessType = _access;		
	}
	

}