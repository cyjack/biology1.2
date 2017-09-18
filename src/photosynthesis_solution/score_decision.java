package photosynthesis_solution;

public class score_decision {
	public static String score_re(int score, int x,String str_absence) throws Throwable {
		String score_string = "";
		if (score > x) {
			score_string ="0"+" "+"请不要随意作答，谢谢!";
		}
		if (score ==999) {
			score_string ="0"+" "+"您对概念理解有误，请参考正确答案!";
		}
		if (score == x) {
			score_string = Integer.toString(score)+" "+"恭喜你，你答对了！棒棒的!";
			//System.out.println(score);
		}
		if (score < x) {
			score_string = Integer.toString(score)+" "+"答案不完全正确，缺少"+str_absence+"完整答案请参考答案解析，还需要继续努力";
			//System.out.println(score);
		}
		if (score == 0) {
			score_string =Integer.toString(score)+" "+ "你的答案有误，请参考答案解析，还需要继续努力！";
			//System.out.println(score);
		}//答案评分，评价，开放性的问题。
		
		return score_string;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
