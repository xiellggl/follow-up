package com.dayi.follow.base;

import com.dayi.common.util.BizResult;
import com.dayi.follow.util.JodaDateEditor;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class BaseController {

    @SuppressWarnings("unused")
    @InitBinder
    private void dateBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new JodaDateEditor());
    }

    /**
     * 对BindingResult 进行基本的封装以方便检查前端输入的格式的错误信息
     *
     * @param result
     * @return
     */
    protected List<String> getErrorMessageListFromBindingResult(BindingResult result) {
        List<String> errorMsgList = new ArrayList<String>();
        for (ObjectError error : result.getAllErrors()) {
            errorMsgList.add(error.getDefaultMessage());
        }
        return errorMsgList;
    }

    /**
     * 验证表单错误并包装成返回值对象
     *
     * @param results
     */
    protected BizResult checkErrors(BindingResult results) {
        List<String> errorMsgList = getErrorMessageListFromBindingResult(results);
        if (CollectionUtils.isNotEmpty(errorMsgList)) {
            return BizResult.fail(StringUtils.join(errorMsgList, "\n\r"));
        } else {
            return BizResult.SUCCESS;
        }
    }


}
