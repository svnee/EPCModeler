package lu.sven.epcmodeler.mouse;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import lu.sven.epcmodeler.mouse.actions.NodeChangeAccess;
import lu.sven.epcmodeler.mouse.actions.NodeChangeLabel;

public class NodeMenu extends JPopupMenu {
	private static final long serialVersionUID = 8020436658882767609L;

	public NodeMenu(final JFrame frame) {
        super("Node Menu");
        this.add(new NodeMenuItems.IDDisplay());
        
        this.addSeparator();
        
        JMenuItem label = new NodeMenuItems.LabelDisplay();
        label.setEnabled(false);
        this.add(label);
        
        AbstractButton setLabel = new NodeChangeLabel(frame);
        this.add(setLabel);
        
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
        
        JMenuItem titleVisibility = new JMenuItem("Privacy of the node:");
        titleVisibility.setEnabled(false);
        this.add(titleVisibility);
        
        ButtonGroup grp2 = new ButtonGroup();
        
        AbstractButton setPublic = new NodeMenuItems.setPublic();
        grp2.add(setPublic);
        this.add(setPublic);
        
        AbstractButton setPrivate = new NodeMenuItems.setPrivate();
        grp2.add(setPrivate);
        this.add(setPrivate);
        
        AbstractButton setGroup = new NodeChangeAccess(frame);
        grp2.add(setGroup);
        this.add(setGroup);
        
        this.addSeparator();
        
        AbstractButton deleteNode = new NodeMenuItems.deleteNode();
        this.add(deleteNode);
    }
}
