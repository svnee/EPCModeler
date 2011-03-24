package lu.sven.epcmodeler.mouse;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class NodeMenu extends JPopupMenu {
	private static final long serialVersionUID = 8020436658882767609L;

	public NodeMenu() {
        super("Node Menu");
        this.add(new NodeMenuItems.IDDisplay());
        
        this.addSeparator();
        
        JMenuItem title = new JMenuItem("Please select the type:");
        title.setEnabled(false);
        this.add(title);
        
        ButtonGroup grp = new ButtonGroup();
        
        AbstractButton setFunction = new NodeMenuItems.setFunctionType();
        grp.add(setFunction);
        this.add(setFunction);
        
        AbstractButton setEvent = new NodeMenuItems.setEventType();
        grp.add(setEvent);
        this.add(setEvent);
        
        AbstractButton setAnd = new NodeMenuItems.setOpAndType();
        grp.add(setAnd);
        this.add(setAnd);
        
        AbstractButton setOr = new NodeMenuItems.setOpOrType();
        grp.add(setOr);
        this.add(setOr);
        
        AbstractButton setXor = new NodeMenuItems.setOpXorType();
        grp.add(setXor);
        this.add(setXor);
        
        this.addSeparator();
        
        JMenuItem titleVisibility = new JMenuItem("Visibility of the node:");
        titleVisibility.setEnabled(false);
        this.add(titleVisibility);
        
        ButtonGroup grp2 = new ButtonGroup();
        
        AbstractButton setVisible = new NodeMenuItems.setVisible();
        grp2.add(setVisible);
        this.add(setVisible);
        
        AbstractButton setInvisible = new NodeMenuItems.setInvisible();
        grp2.add(setInvisible);
        this.add(setInvisible);
        
        this.addSeparator();
        
        AbstractButton deleteNode = new NodeMenuItems.deleteNode();
        this.add(deleteNode);
    }
}
