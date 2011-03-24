package lu.sven.epcmodeler.util;

import lu.sven.epcmodeler.graph.*;

import java.awt.Color;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.geom.Point2D;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.AbstractLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Context;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;

public class Transformers {
	public static AbstractLayout<Node, Edge> _l;
	
	// Transformer for the position
    public static Transformer<Node, Point2D> locationTransformer = new Transformer<Node, Point2D>() {
        @Override
        public Point2D transform(Node n) {
            double x = n.nodePosition.getX() - n.nodeDimension.width / 2.0;
            double y = n.nodePosition.getY() - n.nodeDimension.height / 2.0;
            return new Point2D.Double(x, y);
        }
    };
    
    // Transform the Edge Shape to a straight line
    public static Transformer<Context<Graph<Node, Edge>, Edge>, Shape> edgeShape =
    	new Transformer<Context<Graph<Node, Edge>, Edge>, Shape>() {
			@Override
			public Shape transform(Context<Graph<Node, Edge>, Edge> arg0) {
				return new EdgeShape.Line<Node, Edge>().transform(arg0);
			}
    };
	
	// Transformer to color the vertices
	public static Transformer<Node,Paint> vertexPaint = new Transformer<Node,Paint>() {
		public Paint transform(Node n) { 
			switch(n.getNodeType()) {
			case FUNCTION:
				return Color.GREEN;
			case EVENT:
				return Color.RED;
			case OPERATOR_AND:
				return Color.LIGHT_GRAY;
			case OPERATOR_OR:
				return Color.LIGHT_GRAY;
			case OPERATOR_XOR:
				return Color.LIGHT_GRAY;
			default:
				return Color.BLUE;
			}
		}
	};
	
	// Transformer to shape the vertices
	public static Transformer<Node,Shape> vertexForm = new Transformer<Node,Shape>() {
		public Shape transform(Node n) {
			return n.getShape();
		}
	};
	
	
	// All Transformers to write the graph metadata
	public static Transformer<Node,String> setVertexId = new Transformer<Node, String>() {
		@Override
		public String transform(Node n) {
			return n.getID();
		}
	};
	
	public static Transformer<Node,String> setXValue = new Transformer<Node, String>() {
        public String transform(Node n) {
            return Double.toString(_l.getX(n));
        }
    };
    
    public static Transformer<Node,String> setYValue = new Transformer<Node, String>() {
        public String transform(Node n) {
            return Double.toString(_l.getY(n));
        }
    };
	
    public static Transformer<Node, String> setLabel = new Transformer<Node, String>() {
        public String transform(Node n) {
            return n.getLabel();
        }
    };
    
    public static Transformer<Node, String> setNodeType = new Transformer<Node, String>() {
        public String transform(Node n) {
            return n.getNodeType().toString();
        }
    };
    
    public static Transformer<Node, String> setTimestamp = new Transformer<Node, String>() {
        public String transform(Node n) {
            return n.getTimeStamp();
        }
    };
    
    public static Transformer<Node, String> setAccess = new Transformer<Node, String>() {
        public String transform(Node n) {
            return n.getAccess();
        }
    };
    
    public static Transformer<Node, String> setState = new Transformer<Node, String>() {
        public String transform(Node n) {
            return n.getState().toString();
        }
    };

	
}
