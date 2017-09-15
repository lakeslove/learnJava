package learnJava;

import java.util.HashMap;

import sun.applet.Main;
import learnIO.systemIn;


/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a>
 * <br/>Copyright (C), 2001-2016, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class Hello
{
	
	 private static final char[] HANKAKU_KATAKANA = { '｡', '｢', '｣', '､', '･',
	      'ｦ', 'ｧ', 'ｨ', 'ｩ', 'ｪ', 'ｫ', 'ｬ', 'ｭ', 'ｮ', 'ｯ', 'ｰ', 'ｱ', 'ｲ',
	      'ｳ', 'ｴ', 'ｵ', 'ｶ', 'ｷ', 'ｸ', 'ｹ', 'ｺ', 'ｻ', 'ｼ', 'ｽ', 'ｾ', 'ｿ',
	      'ﾀ', 'ﾁ', 'ﾂ', 'ﾃ', 'ﾄ', 'ﾅ', 'ﾆ', 'ﾇ', 'ﾈ', 'ﾉ', 'ﾊ', 'ﾋ', 'ﾌ',
	      'ﾍ', 'ﾎ', 'ﾏ', 'ﾐ', 'ﾑ', 'ﾒ', 'ﾓ', 'ﾔ', 'ﾕ', 'ﾖ', 'ﾗ', 'ﾘ', 'ﾙ',
	      'ﾚ', 'ﾛ', 'ﾜ', 'ﾝ', 'ﾞ', 'ﾟ' };
	 private static final char[] ZENKAKU_KATAKANA = { '。', '「', '」', '、', '・',
	      'ヲ', 'ァ', 'ィ', 'ゥ', 'ェ', 'ォ', 'ャ', 'ュ', 'ョ', 'ッ', 'ー', 'ア', 'イ',
	      'ウ', 'エ', 'オ', 'カ', 'キ', 'ク', 'ケ', 'コ', 'サ', 'シ', 'ス', 'セ', 'ソ',
	      'タ', 'チ', 'ツ', 'テ', 'ト', 'ナ', 'ニ', 'ヌ', 'ネ', 'ノ', 'ハ', 'ヒ', 'フ',
	      'ヘ', 'ホ', 'マ', 'ミ', 'ム', 'メ', 'モ', 'ヤ', 'ユ', 'ヨ', 'ラ', 'リ', 'ル',
	      'レ', 'ロ', 'ワ', 'ン', '゛', '゜' };
	 public final static boolean isKana(String text){
			if (text.matches("^[ァ-ロワヲンー 　|ｦ-ﾝﾞﾟ･-]*$")) {
				return true;
			}
			return false;
		}
	 public final static boolean isSBC(String fullAngle) {
			if (fullAngle.matches("^[^\\x00-\\xff|^\\uff61-\\uff9f]*$")) {
				return true;
			}
			return false;
		}
	 
	public static void main(String[] args){
		
		char myCharactor = 'ー';
		char StyleBookCharactor = '－';
		char CSVCharactor = 'ー';
		char myquanjiao='－';
		char myKANAjiao='ー';
		char MIjiao='−';
		
		char MIjiao_Shift='−';
		char MIjiao_SHIFT='−';
		
		
//		我敲的 ー
//		式样书 －
//		csv的  ー
//		我敲的【全角英数字】  －
//		我敲的かな的全角　　ー
		System.out.println("我敲的:"+(int)myCharactor);
		System.out.println("式样书的:"+(int)StyleBookCharactor);
		System.out.println("csv的:"+(int)CSVCharactor);
		System.out.println("我敲的【全角英数字】:"+(int)myquanjiao);
		System.out.println("我敲的かな的全角:"+(int)myKANAjiao);
		System.out.println("MI的全角:"+(int)MIjiao);
		
		System.out.println("MI的全角:"+(int)MIjiao_Shift);
		System.out.println("MI的全角:"+(int)MIjiao_SHIFT);
		
		
		
//		System.out.println(isKana("　"));
		
		
		
		
		
//		
//		for(char kk:HANKAKU_KATAKANA){
//			System.out.print((int)kk+"  ");
//		}
//		System.out.println(" ");
//		for(char kk:ZENKAKU_KATAKANA){
//			System.out.print((int)kk+"  ");
//		}
//		for(int i=0;i<HANKAKU_KATAKANA.length;i++){
//			System.out.println((int)HANKAKU_KATAKANA[i]-(int)ZENKAKU_KATAKANA[i]);
//		}
//		String test0 = "ｶｶｶｶｶｶｶｶｶｶｶｶ";
//		char bkana = 'ｶ';
//		System.out.println((int)bkana);
//		char kana = 'か';
//		System.out.println('ｶ'-'か');
//		System.out.println();
//		char charac = 'A';
//		System.out.println((int)charac);
//		char charaa = 'a';
//		System.out.println((int)charaa);
//		char charaat ='\177';
//		System.out.println((int)charaat);
//		String test1 = "12321323123";
//		System.out.println(ToSBC(test1));
//		System.out.println("かかかかかかかかかかか");
	}
	
	
	public static char ConvertToQJC(char c){
		if (c == 32)
			return (char) 12288;
		if (c < 127)
			return (char) (c + 65248);
		return c;
	}
	public static String ToSBC(String input) {
        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
          if (c[i] == ' ') {
            c[i] = '\u3000';
          } else if (c[i] < '\177') {
            c[i] = (char) (c[i] + 65248);

          }
        }
        return new String(c);
}
}
