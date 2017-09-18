package longlong_util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import javax.swing.text.html.HTMLDocument.Iterator;

public class fio {

	//@考虑传入正则后规格化�?下数据再传入文件
	/**
	 * @author:dql
	 * @param s
	 * @param regx
	 * @return Arraylist
	 */
	public static ArrayList fio(String s) throws Exception {
		// TODO Auto-generated constructor stub
	    ArrayList<String> l = new ArrayList<String>();
		BufferedReader input = new BufferedReader(new InputStreamReader(
				new FileInputStream(new File(s))));// 打开文件
		String sc ="";
		while((sc = input.readLine())!=null){
			l.add(sc);
		}
		
		return l;
	}
	/**
	 * author:dql
	 * @param s
	 * @param ArrayList
	 * @return null
	 */
	public static void  fio(String s,ArrayList<String> l) throws Exception {
		// TODO Auto-generated constructor stub
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(s, true)));//打开要输出的文件
		String sc ="";
		 for(String tmp:l){
			 out.write(tmp);
         }
		 out.close();
	}
//	  @author:dql
//	  @param s
//	  @param regx
	public static void  fio(String File,String s) throws Exception {
		// TODO Auto-generated constructor stub
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(File, true)));//打开要输出的文件
		String sc ="";
		out.write(s);
		out.close();
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
      fio s = new fio();
      s.fio("./选项.txt");
      ArrayList<String> l = new ArrayList<String>();
      l.add("love");
      s.fio("E:\\22.txt",l);
      
	}

}
