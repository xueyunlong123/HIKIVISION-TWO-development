package org.apache.mina.example.chat.client;

 

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

 

/**

 * <b>function:</b> 锟斤拷锟叫客伙拷锟剿筹拷锟斤拷

 */

public class LinkServerAndOutServer extends JFrame{
	
	private MinaClient client = new MinaClient();
	private JFrame jFrame = new JFrame();
	private JButton okjButton = new JButton("杩炴帴鏈嶅姟鍣�");
	private JButton cancelljButton = new JButton("鍙栨秷");
	JLabel jLabel = new JLabel("鏈嶅姟鍣ㄥ湴鍧�");
	JTextField jTextField = new JTextField();
	JPanel jbuttonpanel = new JPanel();
	
	private String serverAddress;
    public LinkServerAndOutServer(){
    	
    }

    public void LinkServer() {
    	
    	serverAddress = "192.168.1.104:8888";
    	jTextField.setText(serverAddress);
    	jLabel.setFont(new Font("瀹嬩綋",Font.BOLD, 16));
    	
    	jFrame.setVisible(true);
    	jFrame.setBounds(300, 300, 300, 120);
    
    	jbuttonpanel.add(okjButton);
    	jbuttonpanel.add(cancelljButton);
    	jFrame.setLayout(new BorderLayout());
    	jFrame.add(jLabel,BorderLayout.NORTH);
    	jFrame.add(jTextField,BorderLayout.CENTER);
    	jFrame.add(jbuttonpanel,BorderLayout.SOUTH);
    	
    	//娣诲姞浜嬩欢椹卞姩
    	okjButton.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

		       
				SocketAddress address = parseSocketAddress(jTextField.getText());
		        if (client.connect(address)){
		            JOptionPane.showMessageDialog(jFrame, "杩炴帴鎴愬姛");           
		            jFrame.dispose();
		        }
		        else{
		        	JOptionPane.showMessageDialog(jFrame, "杩炴帴澶辫触");
		        	
		        }
		        
			}

			 private SocketAddress parseSocketAddress(String s) {
			        s = s.trim();
			        int colonIndex = s.indexOf(":");
			        if (colonIndex > 0) {
			            String host = s.substring(0, colonIndex);
			            int port = parsePort(s.substring(colonIndex + 1));
			            return new InetSocketAddress(host, port);
			        } else {
			            int port = parsePort(s.substring(colonIndex + 1));
			            return new InetSocketAddress(port);
			        }
			    }

			    private int parsePort(String s) {
			        try {
			            return Integer.parseInt(s);
			        } catch (NumberFormatException nfe) {
			            throw new IllegalArgumentException("Illegal port number: " + s);
			        }
			    }

			
		});
		        
    	
    	cancelljButton.addActionListener(new ActionListener() {
			
		
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jFrame.dispose();
			}
		});
    }
    public void OutServer(){
    	if(client.close()){
    		 JOptionPane.showMessageDialog(jFrame, "杩炴帴宸插叧闂�"); 
    	}else{
    		JOptionPane.showMessageDialog(jFrame, "杩炴帴鍏抽棴澶辫触");
    			 }
    	}

}