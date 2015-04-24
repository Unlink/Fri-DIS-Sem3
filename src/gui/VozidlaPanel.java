/*
 *  Copyright (c) 2015 Michal Ďuračík
 */
package gui;

import entity.TypVozidlo;
import java.awt.Component;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Unlink
 */
public class VozidlaPanel extends javax.swing.JPanel {

	private final List<TypVozidlo> aVozidla;
	
	/**
	 * Creates new form VozidlaPanel
	 * @param paVozidla
	 */
	public VozidlaPanel(List<TypVozidlo> paVozidla) {
		this.aVozidla = paVozidla;
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();

        jButton1.setBackground(new java.awt.Color(0, 153, 0));
        jButton1.setText("Pridaj Vozidlo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(295, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        insertVozidlo(aVozidla.get(0), 0);
    }//GEN-LAST:event_jButton1ActionPerformed

	public void insertVozidlo(TypVozidlo paTyp, double paTime) {
		VozidloPanel panel = new VozidloPanel(aVozidla, paTyp, paTime);
		panel.onDelete((p) -> {
			jPanel1.remove(p);
			jPanel1.validate();
			jScrollPane1.validate();
		});
		jPanel1.add(panel);
		jPanel1.validate();
		jScrollPane1.validate();
	}
	
	public void forEach(Command l) {
		for (Component component : jPanel1.getComponents()) {
			VozidloPanel p = (VozidloPanel) component;
			l.process(p.getTyp(), p.getTime());
		}
	}
	
	public List<VozidloPanel> getVozidla() {
		return Arrays.asList(jPanel1.getComponents()).stream().map((Component x) -> (VozidloPanel) x).collect(Collectors.toList());
	}

	@Override
	public void removeAll() {
		jPanel1.removeAll();
		jPanel1.validate();
	}
	
	
	

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

	public interface Command {
		public void process(TypVozidlo paVozidlo, double paTime);
	}

}
