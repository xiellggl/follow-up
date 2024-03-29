package com.dayi.follow.controller;

import com.dayi.common.util.BizResult;
import com.dayi.follow.base.BaseController;
import com.dayi.follow.component.UserComponent;
import com.dayi.follow.model.follow.Department;
import com.dayi.follow.model.follow.FollowUp;
import com.dayi.follow.service.DeptService;
import com.dayi.follow.service.FollowUpService;
import com.dayi.follow.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/dept")
public class DeptController extends BaseController {

    @Resource
    private UserComponent userComponent;

    @Resource
    private DeptService deptService;
    @Resource
    private FollowUpService followUpService;
    @Resource
    private UserService userService;

    /**
     * 查询
     */
    @RequestMapping("/list")
    public String list(HttpServletRequest request, Model model) throws Exception {
        List<Department> deptTree = deptService.getDeptTree(null);
        model.addAttribute("deptTree", deptTree);
        return "sys/department/list";
    }

    /**
     * 新增 -- 部门 -- 初始化页面
     */
    @RequestMapping("/add")
    public String add(HttpServletRequest request, Model model) {
        List<Department> deptTree = deptService.getDeptTree(null);
        model.addAttribute("deptList", deptTree);  // 上级部门下拉选择数据
        return "sys/department/edit";
    }

    @RequestMapping("/add/save")
    @ResponseBody
    public BizResult addSave(HttpServletRequest request, @Valid Department department, BindingResult result) {
        BizResult bizResult = checkErrors(result);
        if (!bizResult.isSucc()) return bizResult;//参数传入错误

        if (department.getCityServer() != null && department.getCityServer().equals(1)) {
            if (department.getCityInviteCode().isEmpty()) {
                return BizResult.fail("城市服务商邀请码不能为空！");
            } else {
                //检查邀请码是否重复
                boolean b = deptService.checkInviteCode(department.getCityInviteCode(), null);
                if (b) return BizResult.fail("邀请码已经存在！");
            }
        }
        return deptService.add(department);
    }

    /**
     * 获取部门信息
     */
    @RequestMapping("/getEditList")
    @ResponseBody
    public BizResult getDeptList(HttpServletRequest request, String userId) {
        if (StringUtils.isBlank(userId)) return BizResult.FAIL;
        FollowUp followUp = userService.get(userId);
        String deptId = followUp.getDeptId();
        Department department = deptService.getDept(deptId);

        List<Department> topList = deptService.getDeptTree(department);

        return BizResult.succ(topList, "操作成功！");
    }

    /**
     * 修改 -- 部门 -- 初始化页面
     */
    @RequestMapping("/update/{deptId}")
    public String update(HttpServletRequest request, Model model, @PathVariable String deptId) {
        Department department = deptService.getDept(deptId);

        List<Department> deptTree = deptService.getDeptTree(department);

        model.addAttribute("deptVo", department);

        model.addAttribute("deptList", deptTree);  // 上级部门下拉选择数据（排除本部门及下属部门）
        return "sys/department/edit";
    }

    @RequestMapping("/update/save")
    @ResponseBody
    public BizResult updateSave(HttpServletRequest request, @Valid Department department, BindingResult result) {
        BizResult bizResult = checkErrors(result);
        if (!bizResult.isSucc()) return bizResult;//参数传入错误

        if (StringUtils.isBlank(department.getId())) BizResult.fail("请选择操作部门！");

        if (department.getCityServer() != null && department.getCityServer().equals(1)) {
            if (department.getCityInviteCode().isEmpty()) {
                return BizResult.fail("城市服务商邀请码不能为空！");
            } else {
                //检查邀请码是否重复
                boolean b = deptService.checkInviteCode(department.getCityInviteCode(), department.getId());
                if (b) return BizResult.fail("邀请码已经存在！");
            }
        }

        return deptService.updateDept(department);
    }

    /**
     * 删除 -- 部门
     */
    @RequestMapping("/delete")
    @ResponseBody
    public BizResult delete(HttpServletRequest request, String deptId) {
        if (StringUtils.isBlank(deptId)) return BizResult.FAIL;

        Department department = deptService.getDept(deptId);
        if (department == null) return BizResult.FAIL;
        return deptService.delete(department);

    }
}
