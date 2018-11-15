package com.dayi.follow.util;


import com.dayi.follow.vo.InviteCodeVo;

import java.util.HashMap;
import java.util.Map;

/**
 * WordLetterUtil
 * 字母与数字的解释
 * @author hongjunqi
 * @date 2015/12/10
 */
public class WordLetterUtil {

    public static int maxCode = 99;

    private static Map<Integer,String> wordMap = new HashMap<Integer,String>();
    static{
        wordMap.put(0,"");
        wordMap.put(1,"A");wordMap.put(2,"B");
        wordMap.put(3,"C");wordMap.put(4,"D");
        wordMap.put(5,"E");wordMap.put(6,"F");
        wordMap.put(7,"G");wordMap.put(8,"H");
        wordMap.put(9,"I");wordMap.put(10,"J");
        wordMap.put(11,"K");wordMap.put(12,"L");
        wordMap.put(13,"M");wordMap.put(14,"N");
        wordMap.put(15,"O");wordMap.put(16,"P");
        wordMap.put(17,"Q");wordMap.put(18,"R");
        wordMap.put(19,"S");wordMap.put(20,"T");
        wordMap.put(21,"U");wordMap.put(22,"V");
        wordMap.put(23,"W");wordMap.put(24,"X");
        wordMap.put(25,"Y");wordMap.put(26,"Z");


    }

    public static void main(String[] args){
        getInviteCode("B345");
    }

    public static InviteCodeVo getInviteCode(String inviteCode){
        String head = inviteCode.substring(0,1);
        String back = inviteCode.substring(1,inviteCode.length());
        if(!StringUtil.isInteger(back)){
            return null;
        }
        Integer code = Integer.parseInt(back);
        Integer word = null;
        for(Map.Entry<Integer,String> entry : wordMap.entrySet()) {
            if(entry.getValue().equals(head)){
                word = entry.getKey();
                break;
            }
        }
        InviteCodeVo vo = new InviteCodeVo(code,word);
        return vo;
    }

    /**
     * 返回组合完成的验证码
     * @param wordLetter
     * @param afterCode
     * @return
     */
    public static String getInviteCode(Integer wordLetter,Integer afterCode){
        String result = wordMap.get(wordLetter)+afterCode;
        return result;
    }

    /**
     * 计算出下一个字母对应的数字
     * @param wordLetter
     * @param afterCode
     * @return
     */
    public static Integer nextWordLetter(Integer wordLetter,Integer afterCode){
        if(afterCode>=maxCode) {
            wordLetter++;
            return wordLetter;
        }else {
            return wordLetter;
        }
    }

    /**
     * 计算出下一个码对应的数字
     * @param wordLetter
     * @param afterCode
     * @return
     */
    public static Integer nextCode(Integer wordLetter,Integer afterCode){
        if(afterCode>=maxCode) {
           return 1;
        }else {
           afterCode++;
            return afterCode;
        }
    }

}
