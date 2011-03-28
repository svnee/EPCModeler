package lu.sven.epcmodeler.mouse.dialogs;

import java.awt.Frame;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import lu.sven.epcmodeler.EPCModeler;
import lu.sven.epcmodeler.graph.Node;

public class NodeLabelDialog extends javax.swing.JDialog {
	private static final long serialVersionUID = -7166671767827909370L;
	Node node;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
	protected Frame frame;
    
	public NodeLabelDialog(java.awt.Frame parent, Node _node) {
        super(parent, true);
        this.node = _node;
        this.frame = parent;
        setTitle("Label for " + node.toString());
        setSize(400, 120);
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        
        jButton1.addActionListener(new java.awt.event.ActionListener() {
        	java.awt.Frame _frame = frame;
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonHandler(evt, _frame);
            }
        });

        jTextField1.setText(node.getLabel());
        jLabel1.setText("The node has the following label: ");
        jButton1.setText("OK");
        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);    
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup().addComponent(jLabel1).addComponent(jTextField1).addComponent(jButton1));
        hGroup.addGroup(layout.createParallelGroup());
        layout.setHorizontalGroup(hGroup);
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(jLabel1));
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(jTextField1));
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(jButton1));
        layout.setVerticalGroup(vGroup);
    }
	private void okButtonHandler(java.awt.event.ActionEvent evt, java.awt.Frame frame) {
		node.setLabel(this.jTextField1.getText());
		EPCModeler.pushToPeers(node.toGML(), "/updateNode");
		frame.repaint();
		dispose();
	}
}
