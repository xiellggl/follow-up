package com.dayi.follow.controller;

import com.dayi.common.util.BizResult;
import com.dayi.follow.FollowApplication;
import com.dayi.follow.model.FollowUp;
import com.dayi.follow.service.FollowUpService;
import com.dayi.user.authorization.AuthorizationManager;
import com.dayi.user.authorization.authc.AccountInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.session.Session;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.CookieResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

/**
 * @author xiell
 * @date 2018/11/14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FollowApplication.class)
@WebAppConfiguration
public class FollowupControllerTest {
    @Resource
    FollowUpService followUpService;
    @Resource
    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    MockHttpSession session;


    @Before
    public void before() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        //登录
//        String userName = "liucheng";
//        String passWord = "123456";

        String userName = "admin";
        String passWord = "123456";

        String uri = "/followup/login/post?username=" + userName + "&password=" + passWord;
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        Assert.assertTrue("错误，正确的返回值为200", status == 200);


        session = (MockHttpSession) mvcResult.getRequest().getSession();

    }

    @After
    public void after() throws Exception {
    }


    /**
     * Method: add(HttpServletRequest request, @Valid FollowUp followUp)
     */
    @Test
    @Rollback(value = false)
    public void testAdd() throws Exception {


        String userName = "xiellno";
        String passWord = "123456";
        String inviteCode="9527";


        String uri = "/followup/add/save?userName=" + userName + "&passWord=" + passWord+ "&inviteCode=" + inviteCode;
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON).session(session)).
                andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);

    }


} 
