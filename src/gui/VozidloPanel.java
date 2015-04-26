/*
 *  Copyright (c) 2015 Michal Ďuračík
 */
package gui;

import entity.TypVozidlo;
import entity.Zastavka;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Unlink
 */
public class VozidloPanel extends javax.swing.JPanel {

	private DeleteCallback aCallback = (VozidloPanel paNel) -> {};
	
	/**
	 * Creates new form VozidloPanel
	 */
	public VozidloPanel(List<TypVozidlo> paVozidla, TypVozidlo paSelected, double paTime) {
		initComponents();
		jComboBox1.setModel(new DefaultComboBoxModel<TypVozidlo>(paVozidla.toArray(new TypVozidlo[paVozidla.size()])));
		if (paSelected != null) {
			jComboBox1.setSelectedItem(paSelected);
		}
		jTextField1.setText(String.format("%.2f", paTime).replace(",", "."));
	}

	public VozidloPanel(List<TypVozidlo> paVozidla) {
		this(paVozidla, null, 0d);
	}
	
	public void onDelete(DeleteCallback paCallback) {
		aCallback = paCallback;
	}

	public TypVozidlo getTyp() {
		return (TypVozidlo) jComboBox1.getSelectedItem();
	}

	public double getTime() {
		return Double.parseDouble(jTextField1.getText());
	}

	public void setTime(double paTime) {
		jTextField1.setText(String.format("%.2f", paTime).replace(",", "."));
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(32767, 45));

        jLabel1.setText("Typ:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Čas začiatku:");

        jTextField1.setText("0");

        jButton1.setBackground(new java.awt.Color(255, 51, 51));
        jButton1.setText("X");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addGap(4, 4, 4)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLabel2)
                .addGap(4, 4, 4)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jButton1))
            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1))
                .addGap(6, 6, 6)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        aCallback.onDelete(this);
    }//GEN-LAST:event_jButton1ActionPerformed

	public interface DeleteCallback {
		public void onDelete(VozidloPanel panel);
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
