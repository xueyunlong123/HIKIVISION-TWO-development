package org.apache.mina.example.chat.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.future.CloseFuture;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.example.chat.client.SwingChatClient;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.filter.logging.MdcInjectionFilter;
import org.apache.mina.transport.socket.SocketConnector;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class MinaClient {

	NioSocketConnector connector = new NioSocketConnector();

	ConnectFuture future;

	IoSession session;

	public boolean connect(SocketAddress address) {

		try {
			connector.getFilterChain().addLast("mdc", new MyMdcInjectionFilter());
			connector.getFilterChain().addLast("codec",new ProtocolCodecFilter(new TextLineCodecFactory()));
			//connector.getFilterChain().addLast("logger", new LoggingFilter());
			connector.setHandler(new ClientMessageHandlerAdapter());
			future = connector.connect(address);
			future.awaitUninterruptibly();
			if (future.isConnected()) {
				System.out.println(" connect");
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		/*
		 * 
		 * LoggingFilter loggingFilter = new LoggingFilter();
		 * 
		 * loggingFilter.setMessageReceivedLogLevel(LogLevel.INFO);
		 * 
		 * loggingFilter.setMessageSentLogLevel(LogLevel.INFO);
		 * 
		 * filterChain.addLast("loger", loggingFilter);
		 */

		return true;

	}

	public void setAttribute(Object key, Object value) {

		session.setAttribute(key, value);

	}

	public boolean close() {
		//!session.isClosing()|| connector.isActive()			
				try {
					connector.dispose();
					 if (session != null) {
				            if (session.isConnected()) {
				                session.write("QUIT");
				                // Wait until the chat ends.
				                session.getCloseFuture().awaitUninterruptibly();
				            }
				            session.close(true);
				        }
					future.cancel();
					return true;
				} catch (Exception e) {
					
					return false;
				}
		}

	public SocketConnector getConnector() {

		return connector;
	}
}

/*class MyProtocolCodecFilter extends ProtocolCodecFilter{

	public MyProtocolCodecFilter(Class<? extends ProtocolEncoder> arg0,
			Class<? extends ProtocolDecoder> arg1) {
		super(arg0, arg1);
	}

	public MyProtocolCodecFilter(TextLineCodecFactory textLineCodecFactory) {
		// TODO Auto-generated constructor stub
	}

}*/

class MyMdcInjectionFilter extends MdcInjectionFilter{
	
}