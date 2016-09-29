//写在程序最前边，每个看到这个程序的人都要记住，一定要写注释，就算是你自己写的，你也会忘
//注：薛云龙 2015//11/9
package clientDemonew;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Panel;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.watermark.SubstanceBubblesWatermark;
import com.sun.jna.NativeLong;
import ClientDemo.HCNetSDK;

public class MainFrame {

	private JFrame frame;
	static HCNetSDK hCNetSDK = HCNetSDK.INSTANCE;// 初始化海康威视设备

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {  
	                UIManager.setLookAndFeel(new SubstanceLookAndFeel()); 
	               
	            } catch (UnsupportedLookAndFeelException ex) {  
	               System.out.println(ex.getMessage());  
	            }  
	            SubstanceLookAndFeel.setCurrentWatermark(new SubstanceBubblesWatermark());
	            //SubstanceLookAndFeel.setSkin(new ChallengerDeepSkin());//��ɫ�������ѡ��
				try {
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
					boolean initSuc = hCNetSDK.NET_DVR_Init();//初始化sdk
	                if (initSuc != true)
	                {
	                    JOptionPane.showMessageDialog(null, "初始化失败");
	                }
	                for(int i = 0;i<4;i++){
	        			Panel p = new Panel();
	        			DeviceMap.listp.add(p);
	        			NativeLong lPreviewHandle = new NativeLong(-1);//预览句柄
	        			NativeLong lUserID = new NativeLong(1);
	        			DeviceMap.list.add(lPreviewHandle);
	        			DeviceMap.listu.add(lUserID);
	        		}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	HCNetSDK.NET_DVR_DEVICEINFO_V30 m_strDeviceInfo;//设备信息
    HCNetSDK.NET_DVR_CLIENTINFO m_strClientInfo;//

    boolean bRealPlay;//�Ƿ���Ԥ��.
    String m_sDeviceIP;//�ѵ�¼�豸��IP��ַ

    NativeLong lUserID;//�û����
    NativeLong lPreviewHandle1;//Ԥ�����
    NativeLong lPreviewHandle2;//Ԥ�����
    

    int m_iTreeNodeNum;//ͨ�����ڵ���Ŀ
	/**  
	 * Create the application.
	 */
	public MainFrame() {
		
		initialize();
		lPreviewHandle1 = new NativeLong(-1);
        lPreviewHandle2 = new NativeLong(-1);
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		
		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.LIGHT_GRAY);
		
		final JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel1, GroupLayout.DEFAULT_SIZE, 841, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 821, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel.setLayout(null);
		JButton btnNewButton = new JButton("\u4E3B\u9884\u89C8");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBackground(Color.GRAY);
		btnNewButton.setToolTipText("\u663E\u793A\u76D1\u63A7\u70B9\u7684\u9884\u89C8\u6216\u8005\u56DE\u8BBF\u753B\u9762\uFF0C\u4EE5\u53CA\u76D1\u63A7\u70B9\u7684\u64CD\u4F5C\u529F\u80FD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jframe主预览1 jFrame主预览= new Jframe主预览1();
				jFrame主预览.setVisible(true);
			}
		});
		btnNewButton.setBounds(23, 35, 181, 47);
		panel.add(btnNewButton);
		
		JButton button = new JButton("\u8FDC\u7A0B\u56DE\u653E");
		button.setBackground(Color.GRAY);
		button.setBounds(286, 35, 181, 47);
		panel.add(button);
		
		JButton button_1 = new JButton("\u7535\u5B50\u5730\u56FE");
		button_1.setBackground(Color.GRAY);
		button_1.setBounds(555, 35, 179, 47);
		panel.add(button_1);
		
		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.GRAY);
		panel2.setBounds(0, 0, 821, 26);
		panel.add(panel2);
		
		JLabel lblNewLabel = new JLabel("操作与控制");
		lblNewLabel.setFont(new Font("BLACK", Font.BOLD, 14));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBackground(Color.BLACK);
		GroupLayout gl_panel2 = new GroupLayout(panel2);
		gl_panel2.setHorizontalGroup(
			gl_panel2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel2.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(683, Short.MAX_VALUE))
		);
		gl_panel2.setVerticalGroup(
			gl_panel2.createParallelGroup(Alignment.LEADING)
				.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
		);
		panel2.setLayout(gl_panel2);
		
		JPanel panel3 = new JPanel();
		panel3.setBackground(Color.GRAY);
		panel3.setBounds(0, 92, 821, 26);
		panel.add(panel3);
		
		JLabel label = new JLabel("维护与管理");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("BLACK", Font.BOLD, 14));
		label.setBackground(Color.BLACK);
		GroupLayout gl_panel3 = new GroupLayout(panel3);
		gl_panel3.setHorizontalGroup(
			gl_panel3.createParallelGroup(Alignment.LEADING)
				.addGap(0, 821, Short.MAX_VALUE)
				.addGroup(gl_panel3.createSequentialGroup()
					.addContainerGap()
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(683, Short.MAX_VALUE))
		);
		gl_panel3.setVerticalGroup(
			gl_panel3.createParallelGroup(Alignment.LEADING)
				.addGap(0, 26, Short.MAX_VALUE)
				.addComponent(label, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
		);
		panel3.setLayout(gl_panel3);
		
		JButton button_2 = new JButton("\u8BBE\u5907\u7BA1\u7406");
		button_2.setToolTipText("\u663E\u793A\u76D1\u63A7\u70B9\u7684\u9884\u89C8\u6216\u8005\u56DE\u8BBF\u753B\u9762\uFF0C\u4EE5\u53CA\u76D1\u63A7\u70B9\u7684\u64CD\u4F5C\u529F\u80FD");
		button_2.setBounds(23, 134, 181, 47);
		panel.add(button_2);
		
		JButton button_3 = new JButton("\u4E8B\u4EF6\u7BA1\u7406");
		button_3.setToolTipText("\u663E\u793A\u76D1\u63A7\u70B9\u7684\u9884\u89C8\u6216\u8005\u56DE\u8BBF\u753B\u9762\uFF0C\u4EE5\u53CA\u76D1\u63A7\u70B9\u7684\u64CD\u4F5C\u529F\u80FD");
		button_3.setBounds(286, 134, 181, 47);
		panel.add(button_3);
		
		JButton button_4 = new JButton("\u5B58\u50A8\u8BA1\u5212");
		button_4.setToolTipText("\u663E\u793A\u76D1\u63A7\u70B9\u7684\u9884\u89C8\u6216\u8005\u56DE\u8BBF\u753B\u9762\uFF0C\u4EE5\u53CA\u76D1\u63A7\u70B9\u7684\u64CD\u4F5C\u529F\u80FD");
		button_4.setBounds(553, 134, 181, 47);
		panel.add(button_4);
		
		JButton button_5 = new JButton("\u7528\u6237\u7BA1\u7406");
		button_5.setToolTipText("\u663E\u793A\u76D1\u63A7\u70B9\u7684\u9884\u89C8\u6216\u8005\u56DE\u8BBF\u753B\u9762\uFF0C\u4EE5\u53CA\u76D1\u63A7\u70B9\u7684\u64CD\u4F5C\u529F\u80FD");
		button_5.setBounds(23, 216, 181, 47);
		panel.add(button_5);
		
		JButton button_6 = new JButton("\u65E5\u5FD7\u641C\u7D22");
		button_6.setToolTipText("\u663E\u793A\u76D1\u63A7\u70B9\u7684\u9884\u89C8\u6216\u8005\u56DE\u8BBF\u753B\u9762\uFF0C\u4EE5\u53CA\u76D1\u63A7\u70B9\u7684\u64CD\u4F5C\u529F\u80FD");
		button_6.setBounds(286, 216, 181, 47);
		panel.add(button_6);
		
		JButton button_7 = new JButton("\u7CFB\u7EDF\u914D\u7F6E");
		button_7.setToolTipText("\u663E\u793A\u76D1\u63A7\u70B9\u7684\u9884\u89C8\u6216\u8005\u56DE\u8BBF\u753B\u9762\uFF0C\u4EE5\u53CA\u76D1\u63A7\u70B9\u7684\u64CD\u4F5C\u529F\u80FD");
		button_7.setBounds(553, 216, 181, 47);
		panel.add(button_7);
		
		JPanel panel4 = new JPanel();
		panel4.setBackground(Color.GRAY);
		panel4.setBounds(0, 273, 821, 26);
		panel.add(panel4);
		
		JLabel label_1 = new JLabel("数据与统计");
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("BLACK", Font.BOLD, 14));
		label_1.setBackground(Color.BLACK);
		GroupLayout gl_panel4 = new GroupLayout(panel4);
		gl_panel4.setHorizontalGroup(
			gl_panel4.createParallelGroup(Alignment.LEADING)
				.addGap(0, 821, Short.MAX_VALUE)
				.addGap(0, 821, Short.MAX_VALUE)
				.addGroup(gl_panel4.createSequentialGroup()
					.addContainerGap()
					.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(683, Short.MAX_VALUE))
		);
		gl_panel4.setVerticalGroup(
			gl_panel4.createParallelGroup(Alignment.LEADING)
				.addGap(0, 26, Short.MAX_VALUE)
				.addGap(0, 26, Short.MAX_VALUE)
				.addComponent(label_1, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
		);
		panel4.setLayout(gl_panel4);
		
		JButton Button1 = new JButton("\u63A7\u5236\u9762\u677F");
		Button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panel.setVisible(true);
				
			}
		});
		Button1.setBounds(10, 0, 93, 40);
		panel1.setLayout(null);
		panel1.add(Button1);
		frame.getContentPane().setLayout(groupLayout);
		frame.setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 857, 563);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.GRAY);
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("文件");
		mnNewMenu.setBackground(Color.GRAY);
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("图片");
		mntmNewMenuItem.setBackground(Color.GRAY);
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("视频");
		mntmNewMenuItem_1.setBackground(Color.GRAY);
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("退出");
		mntmNewMenuItem_2.setBackground(Color.GRAY);
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenu mnNewMenu_1 = new JMenu("系统");
		mnNewMenu_1.setBackground(Color.GRAY);
		menuBar.add(mnNewMenu_1);
		
		JMenu menu_1 = new JMenu("视图");
		menu_1.setBackground(Color.GRAY);
		menuBar.add(menu_1);
		
		JMenu menu = new JMenu("工具");
		menu.setBackground(Color.GRAY);
		menuBar.add(menu);
		
		JMenu menu_2 = new JMenu("帮助");
		menu_2.setBackground(Color.GRAY);
		menuBar.add(menu_2);
		
		
	}
}
