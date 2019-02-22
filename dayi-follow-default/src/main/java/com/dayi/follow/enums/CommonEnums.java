package com.dayi.follow.enums;

/**
 * @author xiell
 * @date 2018/11/14
 */

/**
 * 会员状态枚举
 */
public class CommonEnums {
    /**
     * 用户锁定类型
     */
    public enum UserLockType {
        LOGIN("登陆密码错误5次", 1),
        PAY("支付密码错误5次", 2);

        private String name;
        private Integer value;

        public String getName() {
            return name;
        }

        public Integer getValue() {
            return value;
        }

        UserLockType(String name, Integer value) {
            this.name = name;
            this.value = value;
        }

        public UserLockType returnEnum(Integer persistedValue) {
            if (persistedValue == null) return UserLockType.LOGIN;
            for (UserLockType type : values()) {
                if (type.getValue().intValue() == persistedValue.intValue()) return type;
            }
            return UserLockType.LOGIN;
        }
    }
}

