package longlong_util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class stop_word {

	//读文本
	public ArrayList fio(String s) throws Exception {
		// TODO Auto-generated constructor stub
	    ArrayList<String> l = new ArrayList<String>();
		BufferedReader input = new BufferedReader(new InputStreamReader(
				new FileInputStream(new File(s))));// 打开文件
		String sc ="";
		while((sc = input.readLine())!=null){
			l.add(sc);
			//System.out.println(sc);
		}
		input.close();
		return l;
	}
	public void  fio(String s,ArrayList<String> l) throws Exception {
		// TODO Auto-generated constructor stub
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(s, true)));//打开要输出的文件
		String sc ="";
		 for(String tmp:l){
			 out.write(tmp);
         }
		 out.close();
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
     stop_word s = new stop_word();
     ArrayList<String> l = new ArrayList<String>();
     ArrayList<String> stop_word = new ArrayList<String>();
     l = s.fio("./data/data_onehot_8 _label_delstop.csv");
     stop_word = s.fio("./data/中文停用词表(比较全面_有1208个停用词).txt");
      String str11="";
     String [] s_f = l.get(0).split(",");
     for(int i = 0 ;i<l.size();i++){
    	 for(String temp: stop_word){
    	    	if(s_f[i].equals(temp)){
    	    		str11 = str11+" "+i;
    	    		System.out.println(temp);
    	    		System.out.println(i);//匹配到的词和对应的位置
    	    	}
    	    }
     }
		String[] str22 = str11.split(" ");
		System.out.println(str11);
		for (int i = 0; i < l.size(); i++) {
			String[] s_f_1 = l.get(i).split(",");
			ArrayList<String> ll = new ArrayList<String>();
			for (int j = 0; j < s_f_1.length; j++) {
				int xx = 0;// 指示有么有
				for (String s2 : str22) {
					if (s2.equals(String.valueOf(j))) {
						xx = 1;
					}

				}
				if (xx == 0) {
					ll.add(s_f_1[j] + ",");
				} else {
					xx = 0;
				}

			}
			ll.add("\r\n");
			s.fio("d:\\xx.csv", ll);
		}
   
     
	

   }
	}
