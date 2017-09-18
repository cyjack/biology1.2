package longlong_util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.Segment;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;

import longlong_util.fio;

public class Connect2LTP {
	private String api_key = "L3s4h3L9ulMaHlDQFaWI1qpArljj1DjPXtqUaD6A";
	private String url_get_base = "http://ltpapi.voicecloud.cn/analysis/?";
	private String url_get_base1 = "http://api.ltp-cloud.com/analysis/?";
	private String format = "plain";
	public static String pattern = "ws";

	public  String test1(String text) {
		
	//	System.out.println(output);
		String line = "";
		String output = "";
		try {
			text = URLEncoder.encode(text, "utf-8");
			URL url = new URL(this.url_get_base + "api_key=" + this.api_key
					+ "&" + "text=" + text + "&" + "format=" + this.format
					+ "&" + "pattern=" + this.pattern);
			URLConnection conn = url.openConnection();
			conn.connect();

			BufferedReader innet = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "utf-8"));
			while ((line = innet.readLine()) != null) {
				output += line + "\r\n";
			}
			innet.close();
		} catch (UnsupportedEncodingException e){//| MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return output;
	}

	 public  static String replaceBlank(String str) {
	        String dest = "";
	        if (str!=null) {
	            Pattern p = Pattern.compile("\t|\r|\n| |[^\u4e00-\u9fa5]");
	            Matcher m = p.matcher(str);
	            dest = m.replaceAll("");
	        }
	        return dest;
	    }
	 public  static String replaceBlank_1(String str) {
	        String dest = "";
	        if (str!=null) {
	            Pattern p = Pattern.compile("\t|\r|\n| ");
	            Matcher m = p.matcher(str);
	            dest = m.replaceAll("");
	        }
	        return dest;
	    }
	 public  static String replaceBlank_2(String str) {
	        String dest = "";
	        if (str!=null) {
	            Pattern p = Pattern.compile("\t|\r|\n| ");
	            Matcher m = p.matcher(str);
	            dest = m.replaceAll("a");
	        }
	        return dest;
	    }
	 
		public static String test(String text) throws Exception {
			String s1 = "";
			ArrayList<String> diy = new ArrayList<String>();
			diy = fio.fio("./data/diydictionnary.txt");
			for(String s :diy){
				//CustomDictionary.add(s);
				CustomDictionary.insert(s, "nz 10240");
				//System.out.println(s);
			}
				 List<Term> output = HanLP.segment(text);
				
				// System.out.println(output);
				 for(Term s :output){
					// System.out.println(s);
					 String []s_c = s.toString().split("/");
					 s1 = s1+s_c[0]+" ";
				 }
					return s1;
				}		
		//判断是不是字母
		public  static  boolean isAll_alphabet(String s)   
		  {   
			boolean isWord=s.matches("[a-zA-Z0-9]+");
			//System.out.println(isWord);
			return isWord;
		  }
//@input : @
//@input : regex,str
//@output : matchers and split by " "
		public static String getMatcher(String regex, String source) {
		String result = "";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(source);
		while (matcher.find()) {
			result = result + matcher.group(0)+" ";
		}
		return result;
	}

	public static void main(String[] args) throws Exception {
				String str ="能量,ADP,H2O,Pi,co2 h";
//				Connect2LTP connect2ltp = new Connect2LTP();
//				connect2ltp.pattern="dp";
				str = Connect2LTP.test(str);
				System.out.println(str);
				String []s =str.split("\r\n");
//				System.out.println(getMatcher("天安门",str));
//				String ss = "共 100 页, 1 2 3 4...";
//				System.out.println(ss.split(" ")[1]);
//				System.out.println(ss.split("共页")[1]);
				String result = "你好&";
				String [] wa =result.split("&");
				System.out.println(wa[1]);
				
		}

}

