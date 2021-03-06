/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.table.DefaultTableModel;
import model.Match;
import model.StatMatch;
import model.StatPlayer;
import model.Tournament;
import model.User;

/**
 *
 * @author Vinh
 */
public class StatDetailPlayerFrm extends javax.swing.JFrame {

    /**
     * Creates new form StatDetailPlayerFrm
     */
    private User user;
    private Tournament tournament;
    private StatPlayer statPlayer;
    public StatDetailPlayerFrm(User user, Tournament tournament, StatPlayer statPlayer) {
        this.user=user;
        this.tournament=tournament;
        this.statPlayer=statPlayer;
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

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel1.setText("Detail Stat Player" + this.statPlayer.getChessPlayer().getName());

        String[] columnNames = {"", "Id", "Name Player 2", "Elo Change"};
        String[][] value = new String[statPlayer.getListStatMatch().size()][4];
        for(int i=0; i<statPlayer.getListStatMatch().size(); i++){
            value[i][0]="Round "+(i+1);
            value[i][1]=statPlayer.getChessPlayer().getId()+"";
            StatMatch temp=statPlayer.getListStatMatch().get(i);
            if(statPlayer.getId()==temp.getPlayer1().getId()){
                value[i][2]=temp.getPlayer2().getChessPlayer().getName();
                value[i][3]=temp.getEloChange1()+"";
            }
            else{
                value[i][2]=temp.getPlayer1().getChessPlayer().getName();
                value[i][3]=temp.getEloChange2()+"";
            }
        }
        DefaultTableModel tableModel = new DefaultTableModel(value, columnNames);
        jTable1.setModel(tableModel);
        jScrollPane1.setViewportView(jTable1);

        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(jButton2)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        (new StatEloFrm(user, this.tournament)).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
