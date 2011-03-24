package lu.sven.epcmodeler.mouse;

import lu.sven.epcmodeler.graph.Node;
import edu.uci.ics.jung.visualization.VisualizationViewer;

public interface EdgeMenuListener<E> {
    /**
     * Used to set the edge and visualization component.
     * @param e 
     * @param visComp 
     */
     void setEdgeAndView(E e, VisualizationViewer<Node, E> vv); 
    
}