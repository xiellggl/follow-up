package com.dayi.follow.controller;

import com.dayi.common.util.BizResult;
import com.dayi.follow.base.BaseController;
import com.dayi.follow.component.UserComponent;
import com.dayi.follow.model.follow.Department;
import com.dayi.follow.model.follow.FollowUp;
import com.dayi.follow.service.DeptService;
import com.dayi.follow.service.FollowUpService;
import com.dayi.follow.service.UserService;
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
     * 查询 -- 一级部门（前端需自行递归层级部门）
     */
    @RequestMapping("/list")
    public String list(HttpServletRequest request, Model model) throws Exception {
        List<Department> deptTree = deptService.getDeptTree(null);
        model.addAttribute("deptTree", deptTree);
        return "manage/dept_list";
    }

    /**
     * 新增 -- 部门 -- 初始化页面
     */
    @RequestMapping("/add")
    public String add(HttpServletRequest request, Model model) {
        List<Department> deptTree = deptService.getDeptTree(null);
        model.addAttribute("deptList", deptTree);  // 上级部门下拉选择数据
        return "manage/dept_edit";
    }

    @RequestMapping("/add/save")
    @ResponseBody
    public BizResult addSave(HttpServletRequest request, @Valid Department department, BindingResult result) {
        BizResult bizResult = checkErrors(result);
        if (!bizResult.isSucc()) return bizResult;//参数传入错误

        if (department.getCityServer().equals(1) && department.getCityInviteCode().isEmpty()) {
            return BizResult.fail("城市服务商邀请码不能为空！");
        } else if (department.getCityServer().equals(1) && !department.getCityInviteCode().isEmpty()) {
            //检查邀请码是否重复
            boolean b = deptService.checkInviteCode(department.getCityInviteCode());
            if (!b) return BizResult.fail("邀请码已经存在！");
        }

        return deptService.add(department);
    }

    /**
     * 获取部门信息
     */
    @RequestMapping("/getEditList/{userId}")
    @ResponseBody
    public BizResult getDeptList(HttpServletRequest request, @PathVariable String userId) {
        FollowUp followUp = userService.get(userId);
        String deptId = followUp.getDeptId();
        Department department = deptService.get(deptId);

        List<Department> topList = deptService.getDeptTree(department);

        return BizResult.succ(topList, "操作成功！");
    }

    /**
     * 修改 -- 部门 -- 初始化页面
     */
    @RequestMapping("/update/{deptId}")
    public String update(HttpServletRequest request, Model model, @PathVariable String deptId) {
        Department department = deptService.get(deptId);

        List<Department> deptTree = deptService.getDeptTree(department);

        model.addAttribute("deptVo", department);

        model.addAttribute("deptList", deptTree);  // 上级部门下拉选择数据（排除本部门及下属部门）
        return "manage/dept_edit";
    }

    @RequestMapping("/update/save")
    @ResponseBody
    public BizResult updateSave(HttpServletRequest request, @Valid Department department, BindingResult result) {
        BizResult bizResult = checkErrors(result);
        if (!bizResult.isSucc()) return bizResult;//参数传入错误

        if (department.getCityServer().equals(1) && department.getCityInviteCode().isEmpty()) {
            return BizResult.fail("城市服务商邀请码不能为空！");
        } else if (department.getCityServer().equals(1) && !department.getCityInviteCode().isEmpty()) {
            //检查邀请码是否重复
            boolean b = deptService.checkInviteCode(department.getCityInviteCode());
            if (!b) return BizResult.fail("邀请码已经存在！");
        }

        return deptService.updateDept(department);
    }

    /**
     * 删除 -- 部门
     */
    @RequestMapping("/delete/{deptId}")
    @ResponseBody
    public BizResult delete(HttpServletRequest request, @PathVariable String deptId) {

        Department department = deptService.get(deptId);
        if (department == null) return BizResult.FAIL;
        return deptService.delete(department);

    }
}
