<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/include/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD><TITLE>后台操作区</TITLE>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css"> 
<script type="text/javascript" src="${ctx}/js/jquery-1.7.2.min.js"></script>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	font-family: "宋体";
	font-size: 12px;
	color: #333333;
	background-color: #2286C2;
}
-->
</style>
</head>
<SCRIPT language=javascript>
//检验表单的合法性
function check() {
	var password = $('#password').val();
	var repassword = $('#repassword').val();	
	if(password==null || password ==''){
		alert("密码不能为空！");
		return false;
	}
	if(repassword==null || repassword ==''){
		alert("重复密码不能为空！");
		return false;
	}
	if(repassword!=password){
		alert("两次输入的密码不一致不能为空！");
		return false;
	 }
	
	$('#myform').submit();	 
}
</SCRIPT>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
	<td height="2">&nbsp;</td>
  </tr>
  <tr>
	<td background="${ctx}/images/index1_45.gif"></td>
	<td bgcolor="#FFFFFF" style="height:490px; vertical-align:top;">
	  <table width="100%" border="0" cellspacing="10" cellpadding="0">
		<tr>
		  <td>
			<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#C4E7FB">
			  <tr>
				<td>
				  <table width="100%" border="0" cellpadding="0" cellspacing="5" bgcolor="#FFFFFF">
					<tr>
					  <td class="font2" style="font-size: 14px;font-weight: bold;">&nbsp;重置密码</td>
					</tr>
				  </table>
				</td>
			  </tr>
			</table>
		  </td>
		</tr>
		<tr>
		  <td>
<form method="post" name="myform" id="myform" onSubmit="return check()" action="${ctx}/user!savaPwd.action">
<input type="hidden" name="userId" value="${user.userId}"> 
<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#BBD3EB">
  <tbody> 
  
    <tr bgColor=#ffffff>
      <TD class="font1" width="45%" align=right height="26">账号：</td>
      <TD class="font1" align=left><input name="account" id="account" type="text" size="20" value="${user.account}" readonly="readonly"></TD>
    </tr>  
     <tr bgColor=#ffffff>
      <TD class="font1" width="45%" align=right height="26">用户名称：</td>
      <TD class="font1" align=left><input name="name" id="name" type="text" size="20" value="${user.name}" readonly="readonly"></TD>
    </tr>    
	  <tr  bgColor=#ffffff>
		<td class="font1" width="45%" align=right height="26">密码:</td>
		<td class="font1" align=left><input type="password" name="password" id="password"  size="20"></td>
	  </tr>
	   <tr  bgColor=#ffffff>
		<td class="font1" width="45%" align=right height="26">确认密码:</td>
		<td class="font1" align=left><input type="password" name="repassword" id="repassword"  size="20"></td>
	  </tr>
    <TR bgColor=#ffffff>
      <td class="font1" colspan="2" align=center height="26">
      <input type="submit" value="确定"></td>
    </tr> 
  </tbody>
</table>
</form>
</td>
</tr> 
	  </table>
	</td>
	<td background="images/index1_47.gif"></td>
  </tr>
  <tr>
	<td width="8" height="8"><img src="images/index1_91.gif" width="8" height="8" /></td>
	<td background="images/index1_92.gif"></td>
	<td width="8" height="8"><img src="images/index1_93.gif" width="8" height="8" /></td>
  </tr>
</table>
</body>
