package clientDemonew;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;

import org.apache.commons.lang.time.FastDateFormat;
import org.apache.mina.example.chat.client.MinaClient;

import clientDemonew.sington;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.awt.Color;

public class JDialogLinkServer extends JDialog {

	private MinaClient client = new MinaClient();
	private JFrame jFrame = new JFrame();
	
	private String serverAddress;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

	

	/**
	 * Create the dialog.
	 */
	public JDialogLinkServer() {
		setBackground(Color.LIGHT_GRAY);
		setBounds(100, 100, 375, 204);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			serverAddress = "192.168.0.22:9999";
			textField = new JTextField();
			textField.setText(serverAddress);
			textField.setBounds(137, 10, 113, 21);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		
		JLabel lblServerAddress = new JLabel("Server Address");
		lblServerAddress.setBounds(10, 10, 155, 21);
		contentPanel.add(lblServerAddress);
		
		JLabel lblNewLabel = new JLabel("please  confirm  your Server Address is right");
		lblNewLabel.setBounds(10, 41, 281, 27);
		contentPanel.add(lblNewLabel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.LIGHT_GRAY);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setBackground(Color.GRAY);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						SocketAddress address = parseSocketAddress(textField.getText());
				        if (client.connect(address)){
				            JOptionPane.showMessageDialog(jFrame, "连接成功");           
				            setVisible(false);
				        }
				        else{
				        	JOptionPane.showMessageDialog(jFrame, "连接失败");
				        	setVisible(false);
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
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setBackground(Color.GRAY);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			
		}
		
	
	  
    

		        
    	
		
	}
}
