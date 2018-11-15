package com.dayi.follow.vo;


import com.dayi.follow.util.WordLetterUtil;

public class InviteCodeVo {

        Integer code;
        Integer word;

        @Override
        public String toString(){
            return WordLetterUtil.getInviteCode(word, code);
        }
        public InviteCodeVo(){}
        public InviteCodeVo(Integer code, Integer word){
            this.code = code;
            this.word = word;
        }
        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public Integer getWord() {
            return word;
        }

        public void setWord(Integer word) {
            this.word = word;
        }
    }