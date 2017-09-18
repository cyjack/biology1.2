package photosynthesis_solution;

import java.util.ArrayList;

import longlong_util.Connect2LTP;
import longlong_util.fio;

public class photosynthesis_solution {
	private static final String String = null;
 
	
	// @用逗好分开的数据
	public static String getSynonym(String keyword) throws Throwable {
		ArrayList<String> List = new ArrayList<String>();
		ArrayList<ArrayList> List1 = new ArrayList<ArrayList>();
		System.out.println(keyword);
		String[] mao = keyword.split(":");
		String[] a_keywords_ci = mao[1].split(",");
		String keywords = "";
		int i = 0;
		boolean x = false;
		List = fio.fio("./data/同义词处理.txt");
		for (int j = 0; j < a_keywords_ci.length; j++) {// 设置一个变量把拓展后的结果进行保存
			for (String s : List) {// 同义词表中的行数
				String[] sy = s.split("=");
				for (String syy : sy) {
					if (a_keywords_ci[j].equals(syy)) {// 如果这行中含有这个答案，就把它加进去
						//System.out.println(++i);
						a_keywords_ci[j] = s;
					}
				}
			}
		}
		for (String s : a_keywords_ci) {
			keywords = keywords + s + ",";
		}
		//System.out.println(keywords);
		return keywords;// 输出同义拓展的结果
	}
	// 否定词的影响
	public static int score_zero(String q_answer) throws Throwable {
		int score = 0;
		ArrayList<String> List = new ArrayList<String>();
		List = fio.fio("./data/否定词.txt");
		// List = fio.fio("./data/限定词.txt");
		for (String ss : List) {
			ss = Connect2LTP.replaceBlank(ss);//去掉非英文字符
			if (q_answer.contains(ss)) {
				score = 1000;
			}
		}
		return score;
	}
	public static String single_solution(String q_body, String a_keywords, String q_answer) throws Throwable {
		int score = 0;
		String score_string = "";
		if (score_zero(q_answer) == 1000) {
			score = 1000;
		}
		if(facker_detection.limitWord_detection(q_body, a_keywords, q_answer)==1000){
			score = 1000; 
		}
		if(Connect2LTP.replaceBlank(q_answer).length()==0){
			score = 1000; 
			score_string = score_decision.score_re(score, 0,"");
			return score_string;
		}
		q_answer = facker_detection.duplication_detection(q_answer);//去重复的时候加入了，重复算对么？
		q_answer = facker_detection.spellerror_detection(q_answer);//合并分词中的一些错误如：co2hh这种
		q_answer = facker_detection.Stop_word(q_answer);//去掉停用词 
		String[] q_answer_c =q_answer.split(",");
//		for(String s :q_answer_c){
//			System.out.println(s+"ddd");
//		}
		String str_absence = facker_detection.absence_detection(q_answer_c,a_keywords);
		a_keywords = getSynonym(a_keywords);
		String[] score_word = a_keywords.split(",");// 分开每行数据
		for (String temp : score_word) {
			String[] temp_cut = temp.split("=");// 分开每行中的词去匹配
			for (String temp_c : temp_cut) {
//				String[] q_answer_c  = q_answer.split("[,.?、，。 ]"); //去掉分词
				for (String q_word : q_answer_c) {
					if (q_word.equals(temp_c)) {// 这个里面有字母怎么办
						score++;
						//System.out.println(temp_c + "   你是个什么东西啊，对，你是答案");
					} // 这个就是打分系统了
				}
			}
		}
		score_string = score_decision.score_re(score, score_word.length,str_absence);
		return score_string;
	}
	public static String double_solution(String q_body, String a_keywords, String q_answer) throws Throwable {
		// 原料为:H,CO2&产物为:CH2O
		int score = 0;
		int length = 0;
		int score_com = 0;
		String score_string = "";
		String keyword = "";
		String[] s_c = a_keywords.split("&");// 拆分输入和输出
		// 在这里插入要做的事情
		String absence_all = "";
		String sc = facker_detection.spilt(q_answer);
		if(sc.equals("facker")){
			score=0;
		}else{
			String [] sc_q = sc .split("&");//分割出两份答案
			for(int i = 0;i<sc_q.length;i++){
				sc_q[i] = Connect2LTP.test(sc_q[i]);
			   }//对每个分割好的答案进行分词
			for (int i = 0;i<s_c.length;i++) {
				String[] q_answer_c = sc_q[i].split(" ");// 答案分词
			
				//String str_absence = facker_detection.absence_detection(q_answer,s_c[i]);
				//absence_all = absence_all+str_absence;
				//System.out.println(str_absence+"我是傻缺");
				s_c[i] = getSynonym(s_c[i]);// 获取同义词
				//System.out.println("我要开始解题了啊~");
				// 要解决的问题是答案的先后序问题，以及如何评分的问题？
				String[] score_word = s_c[i].split(",");// 分开每行数据
				score_com = score_com + score_word.length;//记录整个选项的分值
				//System.out.println(score_word.length);
				for (String temp_b : score_word) {
					 //System.out.println(temp_b);
					String[] temp_cut = temp_b.split("=");// 分开每行中的词去匹配
					
					for (String temp_bc : temp_cut) {
						// System.out.println(temp_bc+"你是个什么东西啊");
						for (String q_word : q_answer_c) {// 加一个顺序匹配检测
							if (q_word.equals(temp_bc)) {// 这个里面有字母怎么办
								score++;
								//System.out.println(temp_bc + "你是个什么东西啊，对，你是答案");
							} // 这个就是打分系统了
						}
					}
				}//目前是实现的按关键词去处理
			}
				if (facker_detection.io_sequence(a_keywords, q_answer) == 1000) {// 判断是不是乱序的答案
					score = 999;
				}
				if(facker_detection.limitWord_detection(q_body, a_keywords, q_answer)==1000){
					score = 1000; 
				}
				System.out.println(q_answer);
				if (score_zero(q_answer) == 1000) {
					score = 1000;
				}
		}

		//System.out.println(absence_all);
		score_string = score_decision.score_re(score, score_com,absence_all);
		//自动文本生成
		return score_string;
	}
	
	// @writer : dql
	// @
	// @keywords
	public static String solution(String q_id, String q_class, String q_body, String a_keywords, String q_answer)
			throws Throwable {
		String score_string = "";
		ArrayList<String> List = new ArrayList<String>();
//		Connect2LTP connect2ltp = new Connect2LTP();
//		connect2ltp.pattern = "ws";	
		if (q_class.equals("input")) {
			score_string = single_solution(q_body, a_keywords, q_answer);
		}
		if (q_class.equals("output")) {
			score_string = single_solution(q_body, a_keywords, q_answer);
		}
		if (q_class.equals("location")) {
			score_string = single_solution(q_body, a_keywords, q_answer);
		}
		if (q_class.equals("relation")) {
			score_string = single_solution(q_body, a_keywords, q_answer);
		}
		if (q_class.equals("input&output")) {
			score_string = double_solution(q_body, a_keywords, q_answer);
		}
		String information = q_id+" "+q_answer+" "+score_string+"\r\n";
		fio.fio("./information.txt",information);//保存一下题目结果信息
		return q_id + " " + score_string;
	}
	//auto_io1_001 input 光合作用的发生，需要哪些原料？ 原料为:能量 Pi,ADP,H2O,CO2
	//auto_loc_007 location 光合作用发生的场所在哪里？ 场所为:叶绿体
	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub//auto_io1_006"+" "+"O2 H"//这道题的答案co2h //是否是一句话？//限定到标点符号//Pi,Pi,Pi重//
	String score_string = solution("auto_io1_001","output","̼光合作用的发生，需要哪些原料？"
			,"场所为:叶绿体"," 叶绿体外的光和作用是这样么？");// 同义拓展未登录的内容
		System.out.println(score_string);//@要不要加学生编号。
	}
}
//"能量,ADP,H2O,Pi,co2"//5 正常作答 
//"能量,不ADP,H2O,Pi,co2"//0 加入了否定词 。
//"能量,ADP,H2O,Pi,co2hh"//缺少co2在一个词后加入随意关键词 英文字母少的话忽视，多的话随意作答
//"能量,ADP,H2O,Pi,co2 hh"//5 分不进行判断。
//"能量,ADP,H2O,Pi,co2 hhhh"//0 这个就是随意作答
//"能量,ADP,Pi,co2"//4 缺失关键词  缺失
//"能量,ADP,Pi与,co2"//5 去掉停用词 
//"能量,ADP,Pi,co2,水与与与与与" 
//"hj"//0 答案有误
//"hjgflk"//0 随意作答
//"能量,ADP,Pi,co2,h2o等"//0 随意作答
//"能量,ADP,Pi,co2,h2o等"
//""或者"   "空答案// 0 随意作答
//""



//""

//不