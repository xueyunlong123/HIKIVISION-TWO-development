package clientDemonew;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import ClientDemo.JFramePTZControl;
import javax.swing.JWindow;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.examples.win32.W32API.HWND;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.NativeLongByReference;


public class DeviceItem {

	int i = 0 ;
	NativeLong lPreviewHandle2 ;
	JFramePTZControl framePTZControl;//云台控制窗口
	public boolean[] bRealPlay=new boolean[16];// 是否在预览.
	public int[] iChannelNumInPanel=new int[16];//记录通道号所在的面板
	NativeLong lUserID;// 用户句柄
	HCNetSDK.NET_DVR_DEVICEINFO_V30 m_strDeviceInfo;// 设备信息
	HCNetSDK.NET_DVR_CLIENTINFO m_strClientInfo;// 用户参数
	static HCNetSDK hCNetSDK = HCNetSDK.INSTANCE;
	NativeLongByReference m_lPort;// 回调预览时播放库端口指针

	DefaultMutableTreeNode m_DeviceRoot;// 通道树子根节点

	int m_iTreeNodeNum;// 通道树节点数目
	String m_sDeviceIP;// 已登录设备的IP地址

	public DeviceItem() {
		lUserID = new NativeLong(-1);
		m_lPort = new NativeLongByReference(new NativeLong(-1));

	}
	public HCNetSDK.NET_DVR_DEVICEINFO_V30 getM_strDeviceInfo() {
		return m_strDeviceInfo;
	}

	public void setM_strDeviceInfo(HCNetSDK.NET_DVR_DEVICEINFO_V30 m_strDeviceInfo) {
		this.m_strDeviceInfo = m_strDeviceInfo;
	}

	void registDevice(String device,String ip) {
		if (lUserID.longValue() > -1) {
			// 先注销
			hCNetSDK.NET_DVR_Logout_V30(lUserID);
			lUserID = new NativeLong(-1);
			m_iTreeNodeNum = 0;
			m_DeviceRoot.removeAllChildren();
		}
		m_DeviceRoot = new DefaultMutableTreeNode(device);
		DeviceMap.root.add(m_DeviceRoot);
		m_sDeviceIP = ip;
		m_strDeviceInfo = new HCNetSDK.NET_DVR_DEVICEINFO_V30();
		int iPort = Integer.parseInt("8000");
		lUserID = hCNetSDK.NET_DVR_Login_V30(m_sDeviceIP, (short) iPort,
				"admin", new String("12345"), m_strDeviceInfo);
		long userID = lUserID.longValue();
		if (userID == -1) {
			m_sDeviceIP = "";// 登录未成功,IP置为空
			// JOptionPane.showMessageDialog(null, "注册失败!!!");
		} else {
			CreateDeviceTree();
		}
	}// GEN-LAST:event_jButtonLoginActionPerformed

	

	/*************************************************
	 * 函数: CreateDeviceTree 函数描述:建立设备通道树
	 *************************************************/
	private void CreateDeviceTree() {
		IntByReference ibrBytesReturned = new IntByReference(0);// 获取IP接入配置参数
		boolean bRet = false;
		HCNetSDK.NET_DVR_IPPARACFG m_strIpparaCfg = new HCNetSDK.NET_DVR_IPPARACFG();// IP参数
		m_strIpparaCfg.write();
		Pointer lpIpParaConfig = m_strIpparaCfg.getPointer();
		bRet = hCNetSDK.NET_DVR_GetDVRConfig(lUserID,
				HCNetSDK.NET_DVR_GET_IPPARACFG, new NativeLong(0),
				lpIpParaConfig, m_strIpparaCfg.size(), ibrBytesReturned);
		m_strIpparaCfg.read();
		DefaultTreeModel TreeModel = ((DefaultTreeModel) DeviceMap.jTreeDevice
				.getModel());// 获取树模型
		if (!bRet) {
			// 设备不支持,则表示没有IP通道
			for (int iChannum = 0; iChannum < m_strDeviceInfo.byChanNum; iChannum++) {
				DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(
						"Camera" + lUserID
								+ (iChannum + m_strDeviceInfo.byStartChan));
				TreeModel.insertNodeInto(newNode, m_DeviceRoot, iChannum);
			}
		} else {
			// 设备支持IP通道
			for (int iChannum = 0; iChannum < m_strDeviceInfo.byChanNum; iChannum++) {
				if (m_strIpparaCfg.byAnalogChanEnable[iChannum] == 1) {
					DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(
							"Camera" + lUserID
									+ (iChannum + m_strDeviceInfo.byStartChan));
					TreeModel.insertNodeInto(newNode, m_DeviceRoot,
							m_iTreeNodeNum);
					m_iTreeNodeNum++;
				}
			}
			for (int iChannum = 0; iChannum < HCNetSDK.MAX_IP_CHANNEL; iChannum++)
				if (m_strIpparaCfg.struIPChanInfo[iChannum].byEnable == 1) {
					DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(
							"IPCamera" + lUserID
									+ (iChannum + m_strDeviceInfo.byStartChan));
					TreeModel.insertNodeInto(newNode, m_DeviceRoot,
							m_iTreeNodeNum);
				}
		}
		TreeModel.reload();// 将添加的节点显示到界面
		// DeviceMap.jTreeDevice.setSelectionInterval(0, 2);// 选中第一个节点
	}

	/*************************************************
	 * 函数: "预览" 双击叶子即可预览次叶子关联的摄像头 
	 * 函数描述: 获取通道号,打开播放窗口,开始此通道的预览
	 *************************************************/
	public void showRealPlay(final int iChannelNum,final int index) {
			
			NativeLong lPreviewHandle1 = DeviceMap.list.get(index);
			HWND hwnd = new HWND(
					Native.getComponentPointer(DeviceMap.listp.get(index)));
			m_strClientInfo = new HCNetSDK.NET_DVR_CLIENTINFO();
			m_strClientInfo.lChannel = new NativeLong(iChannelNum);
			System.out.println(m_strClientInfo.lChannel);

			m_strClientInfo.hPlayWnd = hwnd;
			lPreviewHandle1 = hCNetSDK.NET_DVR_RealPlay_V30(lUserID,
					m_strClientInfo, null, null, true);
			DeviceMap.list.set(index, lPreviewHandle1);
			DeviceMap.listu.set(index,lUserID);
			bRealPlay[iChannelNum-1] = true;
			DeviceMap.bPanelUse[index] = true;//设置为该面板正在使用
			iChannelNumInPanel[iChannelNum-1] = index;
			DeviceMap.listp.get(index).addMouseListener(new java.awt.event.MouseAdapter() {
	            public void mousePressed(java.awt.event.MouseEvent evt) {
	            	System.out.println(index);
	                panelRealplayMousePressed(evt);
	            }

				private void panelRealplayMousePressed(MouseEvent evt) {
					// TODO Auto-generated method stub
					if( i==0){
					   	 if(evt.getClickCount() == 1)
					     {
					   		
					   		framePTZControl = new JFramePTZControl(DeviceMap.list.get(iChannelNum));
					        framePTZControl.setBounds(1100, 20, 200, 700);
					        framePTZControl.setVisible(true);
					        i++;
					     }
						}
					    	//鼠标单击事件为双击
					        if(evt.getClickCount() == 2)
					        {
					            //新建JWindow 全屏预览
					            final  JWindow wnd = new JWindow();
					            //获取屏幕尺寸
					            Dimension   screenSize   =   Toolkit.getDefaultToolkit().getScreenSize();
					            wnd.setSize(screenSize);
					            wnd.setVisible(true);
					            HWND hwnd = new HWND(Native.getComponentPointer(wnd));
								m_strClientInfo.hPlayWnd = hwnd;//画面显示容器设置为hwnd
								m_strClientInfo.lChannel = new NativeLong(iChannelNum);
								lPreviewHandle2 = hCNetSDK.NET_DVR_RealPlay_V30(DeviceMap.listu.get(iChannelNum-1),
										m_strClientInfo, null, null, true);//注意sdk传入的参数
					      
					            //JWindow增加双击响应函数,双击时停止预览,退出全屏
					            wnd.addMouseListener(new java.awt.event.MouseAdapter() {
					            public void mousePressed(java.awt.event.MouseEvent evt) {
					                if(evt.getClickCount() == 2)
					                {
					                    //停止预览
					                   //hCNetSDK.NET_DVR_StopRealPlay(lRealHandle);
					                   wnd.dispose();
					                }
					            }
					        
					    });

					    }
				}
			});
	}
	
	//重复双击某一正在预览的摄像头对应的叶子，即可关闭此摄像头
	public void closeRealPlay(int iChannelNum) {
		int index=iChannelNumInPanel[iChannelNum-1];
		if (DeviceMap.list.get(index).longValue() > -1)
			hCNetSDK.NET_DVR_StopRealPlay(DeviceMap.list.get(index));
		bRealPlay[iChannelNum-1] = false;
		if (m_lPort.getValue().intValue() != -1) {
			DeviceMap.playControl.PlayM4_Stop(m_lPort.getValue());
			m_lPort.setValue(new NativeLong(-1));
		}
		DeviceMap.bPanelUse[index] = false;//设置为该面板没有使用
		DeviceMap.listp.get(index).repaint();
	}
	
	 public  void  getXY(HashMap hm,String message){
		//将从服务器接收到的原始数据中的#断开，得到每一个单元数据"",""
	    	String[] tempStr = message.split("#");
	    	/*for(int i=0;i<tempStr.length;i++){
	    		System.out.println(tempStr[i]);5014,134
	    	}*/
	    	for(int i=0;i<tempStr.length;i++)
	    	{
	    		if(hm.get(tempStr[i].toString())!=null)
	    		{//从preset.ini中获得数据
	    			String[] wrp = ((String) hm.get(tempStr[i].toString())).split("#");//此处用#断开是断开（列如C3,1#C2,1）中的数据
	    			for(int j=0;j<wrp.length;j++)
	    			{	
	    				String[] trc = wrp[j].split(",");
	    				Action(trc[0],trc[1]);
	    				System.out.println(trc[0]+trc[1]);
	    			}
	    		}
	    	} 
	    }
	    private  void Action(String C,String P)
	    {
	    	int cameraNum = -1;
	    	
	    	//C2和C3分别代表摄像头的编号，类似于摄像头管道号
	    	//C2和C3是人为设定的，不受摄像头约束
	    	if(C.equals("C2"))
	    	{
	    		cameraNum=1;
	    	}
	    	if(C.equals("C3"))
	    	{
	    		cameraNum=0;
	    	}
	    	NativeLong camera = new NativeLong(cameraNum);
	    	//hCNetSDK.NET_DVR_PTZPreset(camera, HCNetSDK.GOTO_PRESET, Integer.parseInt(P));
	    	hCNetSDK.NET_DVR_PTZPreset_Other(lUserID, camera, HCNetSDK.GOTO_PRESET, Integer.parseInt(P));
	    	System.out.println("lUserID:"+lUserID+"   camera"+camera);
	    }
}
