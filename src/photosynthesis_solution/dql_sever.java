package photosynthesis_solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import longlong_util.Connect2LTP;
import longlong_util.fio;

public class dql_sever {
	private static Socket accept;
	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		int i =0;
		String result = "";
		  while(true){
	            // 1.创建Server服务器端的连接,并指定监听端口号
	            ServerSocket server = new ServerSocket(8008);
	            // 2.建立accept（）连接
	            System.out.println("服务器即将连接，等待客户端");
	            Socket socket = server.accept();
	            // 获取客户端输入流
	            InputStream is = socket.getInputStream();
	            // 转换成字符流
	            InputStreamReader isr = new InputStreamReader(is);
	            // 添加缓冲流
	            BufferedReader br = new BufferedReader(isr);
	            String data = null;
	            	   OutputStream os = socket.getOutputStream();
	     	            PrintWriter pw = new PrintWriter(os); // 包装流
	            	  while ((data = br.readLine()) != null) {
	  	                // 循环读取
	  	                System.out.println("我是服务器端，客户端说:" + data);
	  	                String []data_spilt = data.split(" ");
	  	                 result = judgmentP(data_spilt[0],data_spilt[1]);
	  	                
                    //这时是获取客户端发过来的消息即题目
	  	            }
	  	              socket.shutdownInput();//关闭输入流
	  	            //注意，此时应先关闭输入流之后才能获取输出流
	  	            // 获取服务器端的输出流,响应客户端
	  	            pw.write("你好，客户端"+result);
	       
	            // 关闭可关闭的资源
	            pw.flush();
	            os.close();
	            pw.close();
	            is.close();
	            isr.close();
	            br.close();
	            server.close();
	            }
	 

	 
	}
	public static String judgmentP(String q_id, String q_content) throws Throwable
	{//我要在这里读一下题目数据。联系起来然后反馈产生的结果
		ArrayList<String> love_question = fio.fio("./data/love_question.txt");
		String request = "";
		for(String s :love_question){
			String []split = s.split(" ");
			System.out.println(split[0]);
			System.out.println(q_id);
			String id = Connect2LTP.replaceBlank(split[0]);
			if(split[0].contains(q_id)){
				request = s +" "+q_content;
				//System.out.println(request);
				//﻿1 input&output ATP的合成的所需要的原材料有哪些,产物有哪些 原料为:能量,Pi,ADP&产物为:ATP+题目内容
			}
		}
		
		String [] split = request.split(" ");
//		
		String result = "";
		result = photosynthesis_solution.solution(split[0], split[1], split[2], split[3], split[4]);
//	
		System.out.println(result);
        return result;
		
	}

}
