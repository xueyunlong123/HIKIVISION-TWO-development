package clientDemonew;

import java.awt.List;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import com.sun.jna.NativeLong;

public class DeviceMap {
	public static Map dmap = new HashMap<String, DeviceItem>();
	
	public static JTree jTreeDevice = new JTree();
	public static DefaultMutableTreeNode root;//通道树根节点
	
	public static ArrayList<Panel> listp = new ArrayList<Panel>();//摄像头面�?
	public static ArrayList<NativeLong> list = new ArrayList<NativeLong>();//预览句柄
	public static ArrayList<NativeLong> listu = new ArrayList<NativeLong>();//UserID
	public static PlayCtrl playControl = PlayCtrl.INSTANCE;
	
	//false代表没有用，true代表正在使用
	public static boolean[] bPanelUse=new boolean[32];//面板位置的句柄是否在预览
}
