/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JDialogCruise.java
 *
 * Created on 2009-11-2, 19:36:07
 */
/**
 *
 * @author Administrator
 */

package clientDemonew;

import com.sun.jna.NativeLong;
import javax.swing.JOptionPane;

/*****************************************************************************
 *�? ：JDialogPTZCruise
 *类描�? ：设置巡航路�?
 ****************************************************************************/
public class JDialogPTZCruise extends javax.swing.JDialog
{

    static HCNetSDK hCNetSDK = HCNetSDK.INSTANCE;
    public static final int MAX_CRUISE_SEQ = 32;
    public static final int MAX_CRUISE_POINT = 32;
    public static final int MAX_CRUISE_PRESET = 128;
    public static final int MAX_CRUISE_TIME = 255;
    public static final int MAX_CRUISE_SPEED = 15;
    private int m_iCruiseNum;
    private int m_iSeqPoint;
    private int m_iPresetNum;
    private int m_iSeqDwell;//time interval
    private int m_iSeqSpeed;
    private int m_iChanNum;//通道号，不是通道索引
    private NativeLong m_lRealHandle;//预览句柄

    /*************************************************
    函数:      JDialogPTZCruise
    函数描述:	构�?�函�?   Creates new form JDialogPTZCruise
     *************************************************/
    public JDialogPTZCruise(java.awt.Frame parent, boolean modal, NativeLong lRealHandle)
    {
        super(parent, modal);
        initComponents();
        initDialog();
        m_lRealHandle = lRealHandle;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxCruiseRoute = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBoxCruisePoint = new javax.swing.JComboBox();
        jComboBoxTime = new javax.swing.JComboBox();
        jComboBoxSpeed = new javax.swing.JComboBox();
        jComboBoxPresetPoint = new javax.swing.JComboBox();
        jButtonAdd = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButtonExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("巡航路径");
        getContentPane().setLayout(null);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setLayout(null);

        jLabel1.setText("巡航路径");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(20, 20, 60, 15);

        jPanel1.add(jComboBoxCruiseRoute);
        jComboBoxCruiseRoute.setBounds(100, 20, 110, 21);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("巡航�?")));
        jPanel2.setLayout(null);

        jLabel2.setText("巡航�?");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(10, 20, 40, 15);

        jLabel3.setText("预置�?");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(140, 20, 50, 15);

        jLabel4.setText("时间");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(10, 60, 30, 15);

        jLabel5.setText("速度");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(140, 60, 30, 15);

        jPanel2.add(jComboBoxCruisePoint);
        jComboBoxCruisePoint.setBounds(60, 20, 60, 21);

        jPanel2.add(jComboBoxTime);
        jComboBoxTime.setBounds(60, 60, 60, 21);

        jPanel2.add(jComboBoxSpeed);
        jComboBoxSpeed.setBounds(190, 60, 70, 21);

        jPanel2.add(jComboBoxPresetPoint);
        jComboBoxPresetPoint.setBounds(190, 20, 70, 21);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(10, 50, 300, 90);

        jButtonAdd.setText("添加巡航�?");
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonAdd);
        jButtonAdd.setBounds(30, 150, 100, 23);

        jButtonDelete.setText("删除巡航�?");
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonDelete);
        jButtonDelete.setBounds(170, 150, 100, 23);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(10, 10, 320, 190);

        jButtonExit.setText("�?�?");
        jButtonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExitActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonExit);
        jButtonExit.setBounds(250, 210, 70, 23);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /*************************************************
    函数:      "添加巡航�?" 按钮单击响应函数
    函数描述:	添加巡航�?
     *************************************************/
    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        boolean bRet = false;
        m_iCruiseNum = jComboBoxCruiseRoute.getSelectedIndex() + 1;
        m_iSeqPoint = jComboBoxCruisePoint.getSelectedIndex() + 1;
        m_iPresetNum = jComboBoxPresetPoint.getSelectedIndex() + 1;
        m_iSeqDwell = jComboBoxTime.getSelectedIndex() + 1;
        m_iSeqSpeed = jComboBoxSpeed.getSelectedIndex() + 1;

        bRet = Set_NET_DVR_PTZCruise(m_iChanNum, HCNetSDK.FILL_PRE_SEQ, (byte) m_iCruiseNum, (byte) m_iSeqPoint, (short) m_iPresetNum);
        if (bRet)
        {
            bRet = Set_NET_DVR_PTZCruise(m_iChanNum, HCNetSDK.SET_SEQ_DWELL, (byte) m_iCruiseNum, (byte) m_iSeqPoint, (short) m_iSeqDwell);
            if (bRet)
            {
                bRet = Set_NET_DVR_PTZCruise(m_iChanNum, HCNetSDK.SET_SEQ_SPEED, (byte) m_iCruiseNum, (byte) m_iSeqPoint, (short) m_iSeqSpeed);
            }
        }
    }//GEN-LAST:event_jButtonAddActionPerformed

    /*************************************************
    函数:      "删除巡航�?" 按钮单击响应函数
    函数描述:	删除巡航�?
     *************************************************/
    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        m_iCruiseNum = jComboBoxCruiseRoute.getSelectedIndex() + 1;
        m_iSeqPoint = jComboBoxCruisePoint.getSelectedIndex() + 1;
        m_iPresetNum = jComboBoxPresetPoint.getSelectedIndex() + 1;
        if (!hCNetSDK.NET_DVR_PTZCruise(m_lRealHandle, HCNetSDK.CLE_PRE_SEQ, (byte) m_iCruiseNum, (byte) m_iSeqPoint, (short) m_iPresetNum))
        {
            JOptionPane.showInternalMessageDialog(this, "删除失败");
            return;
        }
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    /*************************************************
    函数:      "�?�?" 按钮单击响应函数
    函数描述:	添加�?毁窗口巡航点
     *************************************************/
    private void jButtonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonExitActionPerformed

    /*************************************************
    函数:      initDialog
    函数描述:	初始化窗�?
     *************************************************/
    private void initDialog()
    {

        int i;
        for (i = 0; i < MAX_CRUISE_SEQ; i++)
        {
            jComboBoxCruiseRoute.addItem("巡航路径" + (i + 1));
        }

        for (i = 0; i < MAX_CRUISE_POINT; i++)
        {
            jComboBoxCruisePoint.addItem(i + 1);
        }

        for (i = 0; i < MAX_CRUISE_PRESET; i++)
        {
            jComboBoxPresetPoint.addItem(i + 1);
        }

        for (i = 0; i < MAX_CRUISE_TIME; i++)
        {
            jComboBoxTime.addItem(i + 1);
        }
        jComboBoxTime.setSelectedIndex(3);

        for (i = 0; i < MAX_CRUISE_SPEED; i++)
        {
            jComboBoxSpeed.addItem(i + 1);
        }
        jComboBoxSpeed.setSelectedIndex(9);
    }

    private boolean Set_NET_DVR_PTZCruise(int iChanNum, int dwPTZCruiseCmd, byte byCruiseRoute, byte byCruisePoint, short wInput)
    {
        if (!hCNetSDK.NET_DVR_PTZCruise(m_lRealHandle, dwPTZCruiseCmd, byCruiseRoute, byCruisePoint, wInput))
        {
            JOptionPane.showInternalMessageDialog(this, "设置失败");
            return false;
        }
        return true;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonExit;
    private javax.swing.JComboBox jComboBoxCruisePoint;
    private javax.swing.JComboBox jComboBoxCruiseRoute;
    private javax.swing.JComboBox jComboBoxPresetPoint;
    private javax.swing.JComboBox jComboBoxSpeed;
    private javax.swing.JComboBox jComboBoxTime;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
