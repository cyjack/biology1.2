package photosynthesis_solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class dql_client {

	public static void main(String[] args) throws Exception {
		 
	            // 1.建立客户端,输入服务器的地址以及端口
		String result = "";
		String q_id="1"; 
		String q_content="原料为:能量,Pi,ADP&产物为:ATP";
		result = requst(q_id,q_content);
        System.out.println(result);
	}
	public static String requst(String q_id,String q_content) throws IOException{
		String result = "";
        Socket socket = new Socket("59.64.36.52", 8989);
        // 获取输出流，向服务器打印信息
        OutputStream os = socket.getOutputStream();
        // 包装成打印流
        PrintWriter pw = new PrintWriter(os);
        pw.write(q_id+"&"+q_content);
        pw.flush();
        socket.shutdownOutput(); //关闭输出流
        //此时应该先关闭输出流然后在开启输入流
        // 获取客户端的输入流
        InputStream is = socket.getInputStream();
        // 转换成字符流
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader rd = new BufferedReader(isr); // 转换成缓冲流
        String data = null;
        while ((data = rd.readLine()) != null) {
            // 循环读取
        	result = data ;
            System.out.println( data);
        }
        // 关闭可关闭的资源
        rd.close();
        is.close();
        isr.close();
        pw.close();
        os.close();
        pw.close();
    	return result;
	}
}
