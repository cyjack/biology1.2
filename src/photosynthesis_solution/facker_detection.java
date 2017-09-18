package photosynthesis_solution;

import java.util.ArrayList;

import longlong_util.Connect2LTP;
import longlong_util.fio;

public class facker_detection {
//去出字符串中的重复数据
	public static String duplication_detection(String answer) throws Throwable {
        String dup="";
        String[] answers = answer.split("[,.?、，。 ]");//@@@还要去除一些为空的字符串
        ArrayList<String> L = new ArrayList<String>(); 
        for(String s :answers){
        	//System.out.println(s);
        	int i=0;
        	for(String x :L){
        		if(x.equals(s)){
        			i=1;
        			break;
        		}
        	}
        	if(i!=1){
        		dup =dup+s+",";
        		L.add(s);
        	}	
        }        
        dup = dup.substring(0, dup.length()-1);
        //System.out.println(dup);
		return dup;
	}
	//去掉分词中的错误
	public static String spellerror_detection(String q_answer) throws Throwable {
		//System.out.println(q_answer);
		q_answer = Connect2LTP.test(q_answer);
		String[] q_answer_c = q_answer.split(" ");
		String dup = "";
		for(int i =0 ;i<q_answer_c.length;i++){
			//拼接成字符串然后判断是否全部为中文。
			if((i!=0)&&(i!=q_answer_c.length-1)){
				String temp = q_answer_c[i]+q_answer_c[i+1];
				
				if(Connect2LTP.isAll_alphabet(temp)){
					dup = dup+temp+",";
					i++;
					//sSystem.out.println(temp);
				}else{
					if(!q_answer_c[i].equals(",")){
						dup = dup+q_answer_c[i]+",";//上一步的数据可能含有,号
					}
					//System.out.println(q_answer_c[i]);
				}
				//System.out.println(q_answer_c[i]);
			}else{
				    dup = dup+q_answer_c[i]+",";
				    //System.out.println(q_answer_c[i]);
			}
        }
		String []q_answer_c_1 = dup.substring(0, dup.length()-1).split(",");
		
		//System.out.println(dup+"xxx");
		//answer;
		return dup;
	}
	//判断原料和产物的顺序
	public static int io_sequence(String a_keywords, String answer) throws Throwable {
		ArrayList<Integer> L = new ArrayList<Integer>();	
		String[] s_c = a_keywords.split("&");// 拆分输入和输出这个是关键词
		answer = Connect2LTP.test(answer);
		String[] q_answer_c = answer.split(" ");// 答案分词
		for (String temp : s_c) {
			String[] temp_c = temp.split(":");
			String[] score_word = temp_c[1].split(",");// 分开每行数据
			for (int i = 0; i < q_answer_c.length; i++) {//q_answer的分词结果与s进行匹配
				for (String s : score_word) {
					if (q_answer_c[i].equals(s)) {
						L.add(i);
						//System.out.println(i);//看是否出现逆序
					}
				}
			}//前一部分先得到一个位置。后一部分再得到一部分位置。
		}
		int temp = L.get(0);
		for (Integer i : L) {
			System.out.println(i);
			if (temp > i) {
				temp = 1000;
				break;
			}
			temp = i;
		}
		//System.out.println(temp);//看是否出现逆序
		return temp;// input&output答案的前后顺序

	}
	//可能性报告 做一下。缺失关键词的检查
	public static String absence_detection(String[] q_answer_c,String a_keywords) throws Throwable {
		String score="";
		a_keywords = photosynthesis_solution.getSynonym(a_keywords);// 对同义词拓展 H,O2。拓展后用,好分割
		String[] score_word = a_keywords.split(",");// 分开每行数据
		for (int j = 0 ;j<score_word.length;j++) {// 这里面是每个关键词所拓展的co2 =CO2
			String[] temp_cut = score_word[j].split("=");// 分开每行中的词去匹配
			for (String temp_c : temp_cut) {//在这个
				for (String q_word : q_answer_c) {
					//System.out.println(temp_c+"1");
					//System.out.println(q_word);
					if(q_word.equals(temp_c)){
							score_word[j]="";
							//System.out.println(temp_c);
					}
				}
			}
		}
		String absence="";
		for(String s:score_word){	
			if(!s.equals("")){
				String[] ss = s.split("=");
				absence = absence+ss[0]+"，";//有些不含有二氧化碳=co2=CO2=cO2=Co2二氧化碳=co2=CO2=cO2=Co2
				}
			}
			System.out.println(absence+"1111111");
		return absence;
	}
	public static int limitWord_detection(String question,String a_keywords, String q_answer) throws Throwable {
		double i = 0;//看看不包含的百分比。
		q_answer = Connect2LTP.replaceBlank_1(q_answer);
		q_answer = Connect2LTP.test(q_answer);
		String[] q_answer_c = q_answer.split(" ");
		//System.out.print(q_answer_c.length);
		String bigger = question+a_keywords;
		for(String q:q_answer_c){
			if(Connect2LTP.isAll_alphabet(q)&&(q.length()>3)){
				return 1000;
			}
			if(!bigger.contains(q)){
				i++;
			}
		}
		if(i>6){//限定词表的扩充
			return 1000;
		}else{
			return 0;
		}
		
	}
	public static String Stop_word(String q_answer) throws Throwable {
	     ArrayList<String> stop_word = new ArrayList<String>();
	     stop_word = fio.fio("./data/停用词.txt");
	     String[] q_answer_c =q_answer.split(",");
	     String dup ="";
	     for(String c:q_answer_c){
	    	 int i =0;//标识找到了停用词没有
	    	   for(String s:stop_word){
	    		   //System.out.println(s);
	   	    	if(s.equals(c)){
	   	    		//System.out.println(c);
	   	    		i=1;
	   	    	}
	  	     }
	    	 if(i==1){
	    		 i=0;
	    	 }else{
	    		 dup = dup+c+",";
	    	 }
	     }
	     q_answer = dup.substring(0, dup.length()-1);
	     return q_answer;
		}
public static String spilt(String q_answer) throws Throwable {
	String s_spilt = "";
		if(q_answer.contains("原")&& q_answer.contains("产")){
			int a = q_answer.indexOf("原");
			int b = q_answer.indexOf("产");
			if(a>b){
				String[ ] s_sp = q_answer.split("原");
				s_spilt = s_sp[1]+"&"+s_sp[0];
			}else{
				String[ ] s_sp = q_answer.split("产");
				s_spilt = s_sp[0]+"&"+s_sp[1];
			}
			System.out.println(s_spilt);
			return s_spilt;
			
		}else{
			return "facker";
		}
	}
	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		String b = "能量,ADP,H2O,Pi与,co2hhh";
		String [] x;
		b =facker_detection.duplication_detection(b);
		b = facker_detection.spellerror_detection(b);
		b = facker_detection.Stop_word(b);
		
		//String q_answer = Connect2LTP.test(b);
		//System.out.println(b);
//		String z = "";
//		z = Connect2LTP.replaceBlank_1(z);
//		System.out.println(z.length());
//		if(z.length()==0){
//			System.out.println("我可是空字符串");
//		}//null指向的是空对象地址null。空字符串不指向任何空间
		
		
	}

}
