<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>

<tr data-id="${item.id}" data-pid="${item.pid}">
    <td> <i class="fa fa-chevron-dcu fa-chevron-down" data-flag="1"></i></td>
    <td>&nbsp首页</td>
    <td>
        <a class="state-btn" data-state="1" href="javascript:;" data-id="30" title="" data-original-title="已启用">
            <span class="btn btn-minier btn-yellow">启用</span>
        </a>
    </td>
    <td>http://spotnewuc.fiidee.loc/#/admin/member/user </td>
    <td>管理首页信息</td>
    <td>
        <a href="#"  data-toggle="modal" data-target="#myModalEditFollowuper" data-act="edit" data-toggle="tooltip" title="修改">
            <i class="ace-icon fa fa-pencil bigger-130"></i></a>
        <a href="#" data-act="del" data-toggle="tooltip" title="删除">
            <i class="ace-icon fa fa-trash-o bigger-130 red"></i></a>
        <a href="#" data-act="del" data-toggle="tooltip" title="绑定功能">
            <i class="ace-icon fa fa-exchange bigger-130 red"></i></a>
    </td>
</tr>
<tr class="conceal-tr click1">
    <td><i class="fa fa-chevron-mcu fa-chevron-down" data-flag="1"></i></td>
    <td>&nbsp&nbsp二级模块</td>
    <td>
        <a class="state-btn" data-state="1" href="javascript:;" data-id="30" title="" data-original-title="已启用">
        <span class="btn btn-minier btn-yellow">启用</span>
        </a>
    </td>
    <td>http://spotnewuc.fiidee.loc/#/admin/member/user </td>
    <td>可查看预约联系时间为今天的代理商</td>
    <td>
        <a href="#"  data-toggle="modal" data-target="#myModalEditFollowuper" data-act="edit" data-toggle="tooltip" title="修改">
            <i class="ace-icon fa fa-pencil bigger-130"></i></a>
        <a href="#" data-act="del" data-toggle="tooltip" title="删除">
            <i class="ace-icon fa fa-trash-o bigger-130 red"></i></a>
        <a href="#" data-act="del" data-toggle="tooltip" title="绑定功能">
            <i class="ace-icon fa fa-exchange bigger-130 red"></i></a>
    </td>
</tr>
<tr class="conceal-tr link1">
    <td><i class="fa fa-chevron-dc" data-flag="1"></i></td>
    <td>&nbsp&nbsp&nbsp这是功能权限3</td>
    <td>&nbsp</td>
    <td>http://spotnewuc.fiidee.loc/#/admin/member/user </td>
    <td>可查看不同状态下的代理商数量（适用于业务人员）</td>
    <td>
        <a href="#"  data-toggle="modal" data-target="#myModalEditFollowuper" data-act="edit" data-toggle="tooltip" title="修改">
            <i class="ace-icon fa fa-pencil bigger-130"></i></a>
        <a href="#" data-act="del" data-toggle="tooltip" title="绑定功能">
            <i class="ace-icon fa fa-exchange bigger-130 red"></i></a>
    </td>
</tr>


