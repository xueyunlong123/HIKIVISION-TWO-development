package clientDemonew;



import org.apache.mina.example.chat.client.LinkServerAndOutServer;

public class sington {
	private static LinkServerAndOutServer linkServerAndOutServer = null;  
	  
    public static LinkServerAndOutServer getInstance() {  
        if (linkServerAndOutServer == null) {  
        	linkServerAndOutServer = new LinkServerAndOutServer();  
        }  
        return linkServerAndOutServer;  
    }  
}