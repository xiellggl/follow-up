package com.dayi.follow.service.impl;


import com.dayi.common.util.BizResult;
import com.dayi.follow.dao.follow.FollowUpMapper;
import com.dayi.follow.dao.follow.ReportMapper;
import com.dayi.follow.model.FollowUp;
import com.dayi.follow.service.CountService;
import com.dayi.follow.service.FollowUpService;
import com.dayi.follow.service.OrgService;
import com.dayi.follow.util.Md5Util;
import com.dayi.follow.vo.IndexVo;
import com.dayi.follow.vo.OrgVo;
import com.dayi.user.authorization.authc.AccountInfo;
import com.dayi.user.authorization.authc.AuthenticationInfo;
import com.dayi.user.authorization.authc.AuthenticationToken;
import com.dayi.user.authorization.authc.support.SimpleAuthenticationInfo;
import com.dayi.user.authorization.authc.support.UsernamePasswordToken;
import com.dayi.user.authorization.authz.AuthorizationInfo;
import com.dayi.user.authorization.realm.Realm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 跟进人 业务实现类
 */
@Service
public class CountServiceImpl implements CountService {
    @Resource
    private FollowUpMapper followUpMapper;
    @Resource
    private ReportMapper reportMapper;

    @Override
    public IndexVo countPreDayDaily(String followId, String deptIdStr) {
        return reportMapper.countPreDayDaily(followId,deptIdStr);
    }
}

