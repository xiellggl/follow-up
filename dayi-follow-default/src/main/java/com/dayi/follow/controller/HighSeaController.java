package com.dayi.follow.controller;

import com.dayi.common.util.BizResult;
import com.dayi.follow.base.BaseController;
import com.dayi.follow.component.UserComponent;
import com.dayi.follow.enums.AgentCusTypeEnum;
import com.dayi.follow.model.follow.Agent;
import com.dayi.follow.model.follow.Config;
import com.dayi.follow.service.HighSeaService;
import com.dayi.follow.vo.LoginVo;
import com.dayi.follow.vo.SearchVo;
import com.dayi.follow.vo.highsea.HSConfigQo;
import com.dayi.follow.vo.highsea.HSConfigVo;
import com.dayi.mybatis.support.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/highsea")
public class HighSeaController extends BaseController {

    @Resource
    private UserComponent userComponent;
    @Resource
    private HighSeaService highSeaService;

    //@RequestMapping("/updateBatch")
    public void updateBatch(HttpServletRequest request) {
        LoginVo currVo = userComponent.getCurrUser(request);
        highSeaService.updateBatch();
    }

    //公海列表-先以羊毛党作为基础
    @RequestMapping("list")
    public String list(Model model, SearchVo searchVo, Page page) {
        page = highSeaService.findPage(page, searchVo);
        model.addAttribute("cusType", AgentCusTypeEnum.values());
        model.addAttribute("page", page);
        return "highsea/high_sea_list";
    }

    //踢入公海
    @RequestMapping("kick")
    @ResponseBody
    public BizResult kick(HttpServletRequest request, Integer agentId) {
        LoginVo currUser = userComponent.getCurrUser(request);
        return highSeaService.kick(currUser.getId(), agentId);
    }

    //从公海捞
    @RequestMapping("drag")
    @ResponseBody
    public BizResult drag(HttpServletRequest request, Integer agentId) {
        LoginVo currUser = userComponent.getCurrUser(request);
        return highSeaService.drag(currUser.getId(), agentId);
    }

    //公海设置
    @RequestMapping("getconfig")
    public String getconfig(HttpServletRequest request, Model model) {
        List<HSConfigVo> list = highSeaService.findConfig();
        model.addAttribute("list", list);
        return "highsea/high_sea_setting";
    }

    //公海设置
    @RequestMapping("setconfig")
    @ResponseBody
    public BizResult setconfig(HttpServletRequest request, @Valid HSConfigQo vo, BindingResult result) {
        BizResult bizResult = checkErrors(result);
        if (!bizResult.isSucc()) return bizResult;//参数传入错误
        return highSeaService.set(vo);
    }
}