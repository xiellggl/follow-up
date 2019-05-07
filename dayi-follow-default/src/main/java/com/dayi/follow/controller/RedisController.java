package com.dayi.follow.controller;

import com.dayi.common.util.BizResult;
import com.dayi.follow.component.UserComponent;
import com.dayi.follow.enums.AgentCusTypeEnum;
import com.dayi.follow.enums.BankTypeEnum;
import com.dayi.follow.model.follow.FollowUp;
import com.dayi.follow.model.follow.RedisTest;
import com.dayi.follow.service.*;
import com.dayi.follow.util.PageUtil;
import com.dayi.follow.util.StringUtil;
import com.dayi.follow.vo.LoginVo;
import com.dayi.follow.vo.SearchVo;
import com.dayi.mybatis.support.Page;
import com.dayi.user.model.User;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiell
 * @date 2018/11/13
 */
@Controller
@RequestMapping("/redis")
public class RedisController {
    @Resource
    FRedisService FRedisService;
    @Resource
    AgentService agentService;
    @Resource
    UserComponent userComponent;
    @Resource
    RedisTemplate redisTemplate;

    @RequestMapping("/set1")
    public void set() {
        FollowUp followUp = new FollowUp();
        followUp.setName("谢淋璃");


        FRedisService.set("set1", followUp, redisTemplate);
    }

    @RequestMapping("/get1")
    public void get(String name) {
//        String s1 = (String) FRedisService.get1(name);
//        System.out.println(s1);
//        String s2 = FRedisService.get2(name);
//        System.out.println(s2);
        FollowUp followUp = FRedisService.get2("set1", redisTemplate);
        System.out.println(followUp.getName());
    }


    @RequestMapping("/set2")
    public void set2(HttpServletRequest request, SearchVo searchVo, Page page, String key) {
        LoginVo currVo = userComponent.getCurrUser(request);
        String followId = currVo.getId();
        Page agentPage = agentService.findAgentPage(page, searchVo, followId);

        FRedisService.set(followId, agentPage, redisTemplate);
    }

    @RequestMapping("/get2")
    public void get2(HttpServletRequest request, Model model, String name) {
        LoginVo currVo = userComponent.getCurrUser(request);
        String followId = currVo.getId();

        Object obj = FRedisService.get1(followId, redisTemplate);

        Page page = (Page) obj;

        model.addAttribute("page", page);

        //处理银行枚举
        BankTypeEnum[] bankTypes = ArrayUtils.removeElements(BankTypeEnum.values(), BankTypeEnum.Ping_An, BankTypeEnum.Ping_An_Card);

        String queryStr = request.getQueryString();//返回地址
        String returnUrl = StringUtil.urlEncode(queryStr);
        model.addAttribute("returnUrl", returnUrl);

        String pageUrl = PageUtil.getPageUrl(request.getRequestURI(), request.getQueryString());  // 构建分页查询请求

        String[] bankTypesArr = request.getParameterValues("bankType");   //结算银行搜索参数

        model.addAttribute("bankTypes", bankTypes);//银行类型
        model.addAttribute("bankTypesArr", bankTypesArr);
        model.addAttribute("customerTypes", AgentCusTypeEnum.values());
    }


    @RequestMapping("/set3")
    public void set3(HttpServletRequest request, SearchVo searchVo, Page page, String key) {
        RedisTest xll1 = new RedisTest("xll1");
        RedisTest xll2 = new RedisTest("xll2");

        List list = new ArrayList();
        list.add(xll1);
        list.add(xll2);

        page.setTotalRecord(2);
        page.setResults(list);

        FRedisService.set("set3", list, redisTemplate);
    }

    @RequestMapping("/get3")
    public void get3(HttpServletRequest request, Model model, String name) {
        Object set3 = FRedisService.get1("set3", redisTemplate);
    }

    @RequestMapping("/set4")
    public void set4(HttpServletRequest request, SearchVo searchVo, Page page, String key) {
        RedisTest xll1 = new RedisTest("xll1");
        RedisTest xll2 = new RedisTest("xll2");

        List list = new ArrayList();
        list.add(xll1);
        list.add(xll2);

        page.setTotalRecord(2);
        page.setResults(list);
        FRedisService.setList("set4", list, redisTemplate);
    }

    @RequestMapping("/get4")
    public void get4(HttpServletRequest request, Model model, String name) {

        Object set3 = FRedisService.get1("set3", redisTemplate);
    }

    @RequestMapping("/set5")
    public void set5() {
        //redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        FRedisService.set("set5", "谢淋璃", redisTemplate);
        FRedisService.setList("set5list", "谢淋璃", redisTemplate);

        RedisTest xll1 = new RedisTest("xll1");
        RedisTest xll2 = new RedisTest("xll2");

        List list = new ArrayList();
        list.add(xll1);
        list.add(xll2);


        FRedisService.setList("set5list", list, redisTemplate);
    }

    @RequestMapping("/get5")
    @ResponseBody
    public BizResult get5() {
        String s = (String) FRedisService.get1("set5", redisTemplate);

        List set5list = FRedisService.getList("set5list", 1, redisTemplate);

        return BizResult.succ(set5list);
    }

}
