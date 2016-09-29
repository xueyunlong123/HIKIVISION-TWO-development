package clientDemonew;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;
 
public class proIni {
	static Properties pro;
    public proIni(String str) throws FileNotFoundException, IOException {
    	pro = new Properties();
    	InputStream is = new FileInputStream(str);
        pro.load(is);
        is.close();
    }
     
    public static HashMap showPro()
    {
    	HashMap hashmap = new HashMap();
        Set<Entry<Object, Object>> entrys = pro.entrySet();
        for (Entry<Object, Object> entry : entrys) {
        	hashmap.put(entry.getKey(),entry.getValue());
        }
        return hashmap;
    }
    
    public static HashMap setMap(String path) {
		HashMap temp = new HashMap();
		try {
			proIni pi = new proIni(path);
			temp = pi.showPro();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return temp;
	}
}
