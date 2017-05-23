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
	var account = $('#account').val();	
	var name = $('#name').val();
	var password = $('#password').val();
	var repassword = $('#repassword').val();
	var account = $('#account').val();  		
	if(account==null || account ==''){
		alert("账号不能为空！");
		return false;
	}
	if(name==null || name ==''){
		alert("用户名称不能为空！");
		return false;
	}
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
					  <td class="font2" style="font-size: 14px;font-weight: bold;" align="center">&nbsp;用户注册信息</td>
					</tr>
				  </table>
				</td>
			  </tr>
			</table>
		  </td>
		</tr>
		<tr>
		  <td>
<form method="post" name="myform" id="myform" onSubmit="return check()" action="${ctx}/user!add.action" enctype="multipart/form-data">
<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#BBD3EB">
  <tbody> 
  
    <tr bgColor=#ffffff>
      <TD class="font1" width="45%" align=right height="26">账号：</td>
      <TD class="font1" align=left><input name="account" id="account" type="text" size="20" ></TD>
    </tr>  
     <tr bgColor=#ffffff>
      <TD class="font1" width="45%" align=right height="26">用户名称：</td>
      <TD class="font1" align=left><input name="name" id="name" type="text" size="20" ></TD>
    </tr>  
     <tr  bgColor=#ffffff>
		<td class="font1" width="45%" align=right height="26">密码:</td>
		<td class="font1" align=left><input type="password" name="password" id="password"  size="20"></td>
	  </tr>
	   <tr  bgColor=#ffffff>
		<td class="font1" width="45%" align=right height="26">确认密码:</td>
		<td class="font1" align=left><input type="password" name="repassword" id="repassword"  size="20"></td>
	  </tr>
	 <tr bgColor=#ffffff>
      <TD class="font1" width="45%" align=right height="26">性别：</td>
      <TD class="font1" align=left>
	      <input type="radio" name="sex" value="1" checked="checked">男
	      <input type="radio" name="sex" value="0">女
     </TD>
    </tr> 
	  <tr bgColor=#ffffff>
      <TD class="font1" width="45%" align=right height="26">年龄：</td>
      <TD class="font1" align=left><input name="age" id="age" type="text" size="20" ></TD>
    </tr> 
     <tr bgColor=#ffffff>
      <TD class="font1" width="45%" align=right height="26">手机号码：</td>
      <TD class="font1" align=left><input name="mobile" id="mobile" type="text" size="20" value="" ></TD>
    </tr> 
     <tr bgColor=#ffffff>
      <TD class="font1" width="45%" align=right height="26">办公电话：</td>
      <TD class="font1" align=left><input name="officePhone" id="officePhone" type="text" size="20" value="" ></TD>
    </tr> 
    <tr bgColor=#ffffff>
      <TD class="font1" width="45%" align=right height="26">E-mail：</td>
      <TD class="font1" align=left><input name="emial" id="emial" type="text" size="20" value="" ></TD>
    </tr> 
    <tr bgColor=#ffffff>
      <TD class="font1" width="45%" align=right height="26">名师级别：</td>
      <TD class="font1" align=left>
		<select name="rank">			
			<option value="1">国家级</option>
			<option value="2">省级</option>
			<option value="3">校级</option>
			<option value="4">院级</option>
			<option value="5">无级别</option>
		</select>
       </TD>
    </tr> 
    <tr bgColor=#ffffff>
      <TD class="font1" width="45%" align=right height="26">课程组名称：</td>
      <TD class="font1" align=left><input name="courseGroup" id="courseGroup" type="text" size="20" value="" ></TD>
    </tr> 
     <tr bgColor=#ffffff>
      <TD class="font1" width="45%" align=right height="26">职称/职务：</td>
      <TD class="font1" align=left>
      <select name="position">			
			<option value="1">教授</option>
			<option value="2">副教授</option>
			<option value="3">讲师</option>
			<option value="4">助教</option>
			<option value="5">无职称</option>
		</select>
    </tr>
     <tr bgColor=#ffffff>
      <TD class="font1" width="45%" align=right height="26">专业：</td>
      <TD class="font1" align=left><input name="specialty" id="specialty" type="text" size="20" value="" ></TD>
    </tr>
     <tr bgColor=#ffffff>
      <TD class="font1" width="45%" align=right height="26">主课程：</td>
      <TD class="font1" align=left><input name="mainCourse" id="mainCourse" type="text" size="20" value="" ></TD>
    </tr>
     <tr bgColor=#ffffff>
      <TD class="font1" width="45%" align=right height="26"> 办公地点：</td>
      <TD class="font1" align=left><input name="address" id="address" type="text" size="20" value="" ></TD>
    </tr>
     <tr bgColor=#ffffff>
      <TD class="font1" width="45%" align=right height="26">获得成就：</td>
      <TD class="font1" align=left><input name="accomplishment" id="accomplishment" type="text" size="20" value="" ></TD>
    </tr>
     <tr bgColor=#ffffff>
      <TD class="font1" width="45%" align=right height="26">个人简介：</td>
      <TD class="font1" align=left>
      	<textarea rows="5" cols="35" name="brief"></textarea>
       </TD>
    </tr>
    <tr bgColor=#ffffff>
      <TD class="font1" width="45%" align=right height="26">上传头像：</td>
      <TD class="font1" align=left>
           <input type="file" name="myFile">
       </TD>
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
