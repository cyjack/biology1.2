package longlong_util;

import java.util.List;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;

public class hansegment {
	public void  hansegment(){
		
	}
	public String test(String text) {
		String s1 = "";
			 List<Term> output = HanLP.segment(text);
			 System.out.println(output);
			 for(Term s :output){
				 s1 = s1+s+" ";//.toString().replaceAll("[/a-z]", "")+" ";
			 }
			
				return s1;
			}		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		hansegment l = new hansegment();
		String str ="我们以后和人交往要委婉和谦逊，不要向扁鹊一样，我们要向邹忌一样，就算是对方很通情达理也要注意说话的方式和语气" ;
		List<String> keywordList = HanLP.extractKeyword(str, 10);
		for(String s:keywordList){
			System.out.println(s);
		}
		System.out.println(keywordList);
		System.out.println(HanLP.parseDependency("把市场经济奉行的等价交换原则引入党的生活和国家机关政务活动中"));
//		str = l.test(str);
//		System.out.println(str);

	}

}
