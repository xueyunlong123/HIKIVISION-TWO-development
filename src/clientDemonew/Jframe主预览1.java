package clientDemonew;


import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.watermark.SubstanceBubblesWatermark;

import com.sun.jna.NativeLong;

import clientDemonew.HCNetSDK.NET_DVR_DEVICEINFO_V30;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.awt.event.ActionEvent;
import javax.swing.JTree;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.DefaultMutableTreeNode;

public class Jframe主预览1 extends JFrame {

	private JPanel contentPane;
	private boolean  a = true,b = true;
	private int j =0;
	protected Frame parent;
	public static NativeLong g_lVoiceHandle;//全局的语音对讲句柄

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new SubstanceLookAndFeel());
					Jframe主预览1 frame = new Jframe主预览1();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}  
				/*SubstanceLookAndFeel.setCurrentWatermark(new SubstanceBubblesWatermark());*/
			    
		}
		});
	}

	/**
	 * Create the frame.
	 */
	public Jframe主预览1() {
		//对全局的有语音对讲句柄进行初始化
		g_lVoiceHandle = new NativeLong(-1);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		Dimension screensize =  Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)screensize.getWidth();
		int height = (int)screensize.getHeight();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//显示面板，通过该面板来显示摄像头图像
		final JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.darkGray);
        panel_2.setBounds(291, 106, 810, 639);
		contentPane.add(panel_2);
		JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                ImageIcon icon = new ImageIcon("E:/123.png");
                java.awt.Image img = icon.getImage();
                g.drawImage(img, 0, 0, icon.getIconWidth(),
                        icon.getIconHeight(), icon.getImageObserver());
            }
        };
		panel.setBackground(Color.GRAY);
        JLabel label = new JLabel("粮食库内物流可视化追踪系统");
		label.setBounds(458, 28, 573, 55);
		label.setFont(new Font("华文行楷", Font.PLAIN, 37));
		panel.setBounds(309, 0, 114,100);
		contentPane.add(panel);
		contentPane.add(label);
		
		JPanel panel_1 = new JPanel();     
		panel_1.setBackground(Color.GRAY);
		panel_1.setForeground(Color.GRAY);
		panel_1.setBounds(0, 128, 136, 560);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("linkserver");
		btnNewButton.setFont(new Font("华文隶书", Font.PLAIN, 20));
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialogLinkServer jDialogLinkServer = new JDialogLinkServer();
				jDialogLinkServer.setVisible(true);
			}
		});
		btnNewButton.setBounds(8, 23, 118, 41);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("outserver");
		btnNewButton_1.setFont(new Font("华文隶书", Font.PLAIN, 22));
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setBounds(8, 97, 118, 41);
		panel_1.add(btnNewButton_1);
		
		JButton btnScreenshot = new JButton("screenshot");
		btnScreenshot.setFont(new Font("华文隶书", Font.PLAIN, 20));
		btnScreenshot.setBackground(Color.WHITE);
		btnScreenshot.setForeground(Color.BLACK);
		btnScreenshot.setBounds(8, 178, 118, 41);
		panel_1.add(btnScreenshot);
		
		JButton btnRecording = new JButton("recording");
		btnRecording.setFont(new Font("华文隶书", Font.PLAIN, 21));
		btnRecording.setForeground(Color.BLACK);
		btnRecording.setBackground(Color.WHITE);
		btnRecording.setBounds(8, 256, 118, 41);
		panel_1.add(btnRecording);
		
		
		final JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.DARK_GRAY);
		panel_3.setBounds(146, 128, 114, 250);
		
		panel_3.setLayout(null);
		JLabel label_2 = new JLabel("监控点");
		label_2.setForeground(Color.BLACK);
		label_2.setBounds(21, 5, 72, 27);
		label_2.setFont(new Font("华文行楷", Font.PLAIN, 24));
		panel_3.add(label_2);
	
		/* * * * * * * * * * * * */
		/* * 初始化设备树，tree* */
		/* * * * * * * * * * * * */
		JTree tree = new JTree();
		tree.setModel(this.initialTreeModel("摄像头选择区域"));
		startRegist();//开始配置摄像头选择区域
		//为设备树添加监听
		tree.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
		             JTree tree = (JTree) e.getSource();
		                 int selRow = tree.getRowForLocation(e.getX(), e.getY());
		                TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
		                if (selRow != -1)
		                {
		                	TreeNode node = (TreeNode) selPath.getLastPathComponent();
		                	//如果是一个节点
		                	 if(node.isLeaf()){
		                		//双击
			                    if (e.getClickCount() == 2)
			                    {
			                    	TreeNode t=node.getParent();//得到父节点
			                    	DeviceItem item = (DeviceItem) DeviceMap.dmap.get(t.toString());
			                    	int iChannelNum = Integer.valueOf(node.toString().substring(7));
			                    	int flag = 0;
			                    	if (item.bRealPlay[iChannelNum-1] == false){
			                    		for(int b=0;b<DeviceMap.listp.size();b++)
				                    	{
				                    		if(DeviceMap.bPanelUse[b]== false)
				                    		{//面板有空闲
				                    			item.showRealPlay(iChannelNum,b);
				                    			flag= 1;
				                    			break;
				                    			
				                    		}
				                    	}
				                    	if(flag == 0)
				                    	{
				                    		System.out.println("面板已经用完");
				                    	}
			                    	}
			                    	else{
			                    		item.closeRealPlay(iChannelNum);
			                    	}
			                    }
		                	 }
		                }
		    }
		        });
		tree.setBackground(Color.GRAY);
		tree.setBounds(0, 42, 114, 250);
		panel_3.add(tree);
		panel_3.setVisible(b);
		contentPane.add(panel_3);
		JButton button = new JButton("监控点");
		button.setFont(new Font("华文行楷", Font.PLAIN, 21));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b=!b;
				panel_3.setVisible(b);
				
			}
		});
		button.setBounds(8, 336, 118, 41);
		panel_1.add(button);
		
		//添加“语音对讲按钮”，通过该按钮弹出新窗口，实现语音对讲的一些功能
				JButton voicebutton = new JButton("语音对讲");
				voicebutton.setFont(new Font("华文行楷", Font.PLAIN, 20));
				voicebutton.setBounds(8, 486, 118, 41);
				voicebutton.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						NET_DVR_DEVICEINFO_V30 strDeviceInfo = null;
						// TODO Auto-generated method stub
					 	new JDialogVoiceTalk(parent, true, DeviceMap.listu.get(0),  ((DeviceItem) DeviceMap.dmap.get("Camera01")).getM_strDeviceInfo());
					}
				});
				panel_1.add(voicebutton);
		
		
		
		final JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.DARK_GRAY);
		panel_4.setBounds(146, 400, 114, 310);
		contentPane.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel label_3 = new JLabel("视图");
		label_3.setBounds(32, 5, 72, 27);
		label_3.setFont(new Font("华文行楷", Font.PLAIN, 24));
		panel_4.add(label_3);
		/* * * * * * * * * * * * */
		/* * 初始化树tree_1，视图管理* */
		/* * * * * * * * * * * * */
		JTree tree_1 = new JTree();
		tree_1.setBackground(Color.GRAY);
		tree_1.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("视图管理") {
				{
					DefaultMutableTreeNode node_1;
					DefaultMutableTreeNode node_2 = new DefaultMutableTreeNode("1-画面");
					DefaultMutableTreeNode node_3 = new DefaultMutableTreeNode("4-画面");
					DefaultMutableTreeNode node_4 = new DefaultMutableTreeNode("9-画面");
					DefaultMutableTreeNode node_5 = new DefaultMutableTreeNode("16-画面");
					node_1 = new DefaultMutableTreeNode("默认视图");
						node_1.add(node_2);
						node_1.add(node_3);
						node_1.add(node_4);
						node_1.add(node_5);
					add(node_1);
					add(new DefaultMutableTreeNode("sports"));
				}
			}
		));
		/* * * * * * * * * * * * */
		/* * 树tree_1：视图管理添加事件监听* */
		/* * * * * * * * * * * * */
		tree_1.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
            JTree tree = (JTree) e.getSource();
            int selRow = tree.getRowForLocation(e.getX(), e.getY());
            TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
            if (selRow != -1)
            {
            	TreeNode node = (TreeNode) selPath.getLastPathComponent();
            	 if(node.isLeaf()){
            		//双击
                    if (e.getClickCount() == 2)
                    {
                    	
                    	DeviceMap.listp.clear();
                    	String str2 = "";
                    	//得到字符串中的数字
                    	for(int i=0;i<node.toString().length();i++){
                    		if(node.toString().charAt(i)>=48 && node.toString().charAt(i)<=57){
                    			str2+=node.toString().charAt(i);
                    		}
                    		}
                    	final int iChannelNum = Integer.valueOf(str2);
                    	
                    	panel_2.removeAll();
                    	panel_2.updateUI();
                    	
                    	Panel []Panel;
                    	Panel = new Panel[17];
                    	panel_2.setLayout(new GridLayout((int)Math.sqrt(iChannelNum), (int) Math.sqrt(iChannelNum),2,2));
  
                    	for(int i = 1;i<=iChannelNum;i++){    		
                    		Panel[i] = new Panel()/*{
                    			public void paintComponent(Graphics g) {
                    				super.paintComponent(g);
                    				ImageIcon img = new ImageIcon("E:/1.png");
                    				g.drawImage(img.getImage(), 0, 0, null);
                    				}
                    		}*/;
                    		
                    		Panel[i].setBackground(Color.darkGray);
                    		DeviceMap.listp.add(Panel[i]);
                    		panel_2.add(Panel[i]);
                    	}
                    	j=1;
                    	}
                    		
                    	}
                    	
                    }
            	 
            }
}
    );
		tree_1.setBounds(0, 42, 114, 268);
		panel_4.add(tree_1);
		panel_4.setVisible(a);
		
		JButton button_1 = new JButton("视图");
		button_1.setFont(new Font("华文行楷", Font.PLAIN, 20));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				a=!a;
				panel_4.setVisible(a);
			}
		});
		button_1.setBounds(8, 413, 118, 41);
		panel_1.add(button_1);
		
		
		
		
		JLabel label_1 = new JLabel("功能键");
		label_1.setFont(new Font("华文行楷", Font.PLAIN, 24));
		label_1.setBounds(28, 106, 75, 21);
		contentPane.add(label_1);
		
	}

	private TreeModel initialTreeModel(String string) {
		// TODO Auto-generated method stub
		DeviceMap.root = new DefaultMutableTreeNode(string);
        DefaultTreeModel myDefaultTreeModel = new DefaultTreeModel(DeviceMap.root);//
        return myDefaultTreeModel;
	}

	@SuppressWarnings("unchecked")
	private void startRegist() {
		// TODO Auto-generated method stub
		HashMap<String, String>  hdev = new HashMap<String, String>();
		  hdev = proIni.setMap(new File("file/yplxj.ini").getPath());
		  SortedMap<String, String> sdev = new TreeMap<String, String>(hdev);
		  Iterator<String> it = sdev.keySet().iterator();
		  while(it.hasNext()){
			  String key = (String) it.next();
			  String value= (String) hdev.get(key);
			  DeviceItem item = new DeviceItem();
			  DeviceMap.dmap.put(key, item);
			  item.registDevice(key,value);	
		  }
		  
	}
	//保存perSet.ini中的数据
	static HashMap  hashmap = proIni.setMap(new File("file/preSet.ini").getPath());
   //保存pointToYPLXJ.ini中的数据
    static HashMap  pointToYPLXJ = proIni.setMap(new File("file/pointToYPLXJ.ini").getPath());
    public static void receivedMessageFromChat(String message)
	{
    	message=message.replace(":",",");
    	String tdev=(String) pointToYPLXJ.get(message);
    	DeviceItem i=(DeviceItem) DeviceMap.dmap.get(tdev);
    	i.getXY(hashmap,message);
    	/*//console输出从服务器接收的数据
		System.out.println(message);*/
		
	}
}
