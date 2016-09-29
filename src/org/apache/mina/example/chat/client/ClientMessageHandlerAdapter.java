package org.apache.mina.example.chat.client;

 

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import clientDemonew.JFrame主预览;
import clientDemonew.Jframe主预览1;
import clientDemonew.proIni;

 

/**

 * <b>function:</b> 锟酵伙拷锟斤拷锟斤拷息锟斤拷锟斤拷锟斤拷


 */

public class ClientMessageHandlerAdapter extends IoHandlerAdapter {

 

    private final static Logger log = LoggerFactory.getLogger(ClientMessageHandlerAdapter.class);
    public void messageReceived(IoSession session, Object message) throws Exception {

        String content = message.toString();

        log.info("\nclient receive a message is : \n" + content);
        Jframe主预览1.receivedMessageFromChat(content);
      
        


    }

    

    public void messageSent(IoSession session , Object message) throws Exception{

        log.info("messageSent 锟酵伙拷锟剿凤拷锟斤拷锟斤拷息锟斤拷" + message);

    }

    

    @Override

    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {

        log.info("锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟届常锟斤拷 {}", cause.getMessage());

    }
    /*
     * 宸ュ叿绫�,瀹炵幇鍦ㄦ枃浠堕噷闈㈠啓鍏ヨ建杩圭偣
     
    class MyUtil{
    	HashMap  hashmap = new HashMap();
    	private HashMap setMap(String path)
    	 {
    	    HashMap  temp = new HashMap();
    	    try {
    				proIni pi=new proIni(path);
    				temp = pi.showPro();
    			} catch (FileNotFoundException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			} 
    	    	return temp;
    	    }
    	public  void receivedMessageFromChatRoute(String message)throws Exception
    	{
    		String lastline="",temp="";
    		if(message!=null||message.length()>0)
    		{
    			hashmap=setMap(new File("file/setPointXY.ini").getPath());
    			message= message.replace(":", ",");
    			String xy = (String) hashmap.get(message.toString());
    			BufferedReader br = new BufferedReader(new FileReader(new File("file/xy.txt").getPath()),1024);
    			while((temp=br.readLine())!=null){
    				lastline=temp;
    			}
    			if(lastline.equals(xy)==false&&xy!=null)
    			{	
    				BufferedWriter bw=new BufferedWriter(new FileWriter(new File("file/xy.txt").getPath(),true));
    				bw.append(xy);
    				bw.newLine();
    				try{
    					bw.close();
    				}catch(Exception ex){
    					System.out.println(ex);
    				}
    			}
    		}
    	}
    }*/

}
