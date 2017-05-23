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
					  <td class="font2" style="font-size: 14px;font-weight: bold;" align="center">&nbsp;教师团队信息详情</td>
					</tr>
				  </table>
				</td>
			  </tr>
			</table>
		  </td>
		</tr>
		<tr>
		  <td>
<form method="post" name="myform" id="myform" onSubmit="return check()" action="${ctx}/user!update.action" enctype="multipart/form-data">
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
	 <tr bgColor=#ffffff>
      <TD class="font1" width="45%" align=right height="26">性别：</td>
      <TD class="font1" align=left>
	      <input type="radio" name="sex" value="1" <c:if test="${user.sex=='1'}">checked="checked"</c:if>>男
	      <input type="radio" name="sex" value="0" <c:if test="${user.sex=='0'}">checked="checked"</c:if>>女
     </TD>
    </tr> 
	  <tr bgColor=#ffffff>
      <TD class="font1" width="45%" align=right height="26">年龄：</td>
      <TD class="font1" align=left><input name="age" id="age" type="text" size="20" value="${user.age}" readonly="readonly"></TD>
    </tr> 
     <tr bgColor=#ffffff>
      <TD class="font1" width="45%" align=right height="26">手机号码：</td>
      <TD class="font1" align=left><input name="mobile" id="mobile" type="text" size="20" value="${user.mobile}" readonly="readonly"></TD>
    </tr> 
     <tr bgColor=#ffffff>
      <TD class="font1" width="45%" align=right height="26">办公电话：</td>
      <TD class="font1" align=left><input name="officePhone" id="officePhone" type="text" size="20" value="${user.officePhone}" readonly="readonly"></TD>
    </tr> 
    <tr bgColor=#ffffff>
      <TD class="font1" width="45%" align=right height="26">E-mail：</td>
      <TD class="font1" align=left><input name="emial" id="emial" type="text" size="20" value="${user.emial}" readonly="readonly"></TD>
    </tr> 
    <tr bgColor=#ffffff>
      <TD class="font1" width="45%" align=right height="26">名师级别：</td>
      <TD class="font1" align=left>
		<select name="rank">			
			<option value="1" <c:if test="${user.rank=='1' }">selected="selected"</c:if>>国家级</option>
			<option value="2" <c:if test="${user.rank=='2' }">selected="selected"</c:if>>省级</option>
			<option value="3" <c:if test="${user.rank=='3' }">selected="selected"</c:if>>校级</option>
			<option value="4" <c:if test="${user.rank=='4' }">selected="selected"</c:if>>院级</option>
			<option value="5" <c:if test="${user.rank=='5' }">selected="selected"</c:if>>无级别</option>
		</select>
       </TD>
    </tr> 
    <tr bgColor=#ffffff>
      <TD class="font1" width="45%" align=right height="26">课程组名称：</td>
      <TD class="font1" align=left><input name="courseGroup" id="courseGroup" type="text" size="20" value="${user.courseGroup}" readonly="readonly"></TD>
    </tr> 
     <tr bgColor=#ffffff>
      <TD class="font1" width="45%" align=right height="26">职称/职务：</td>
      <TD class="font1" align=left>
      <select name="position">			
			<option value="1" <c:if test="${user.position=='1' }">selected="selected"</c:if>>教授</option>
			<option value="2" <c:if test="${user.position=='2' }">selected="selected"</c:if>>副教授</option>
			<option value="3" <c:if test="${user.position=='3' }">selected="selected"</c:if>>讲师</option>
			<option value="4" <c:if test="${user.position=='4' }">selected="selected"</c:if>>助教</option>
			<option value="5" <c:if test="${user.position=='5' }">selected="selected"</c:if>>无职称</option>
		</select>
    </tr>
     <tr bgColor=#ffffff>
      <TD class="font1" width="45%" align=right height="26">专业：</td>
      <TD class="font1" align=left><input name="specialty" id="specialty" type="text" size="20" value="${user.specialty}" readonly="readonly"></TD>
    </tr>
     <tr bgColor=#ffffff>
      <TD class="font1" width="45%" align=right height="26">主课程：</td>
      <TD class="font1" align=left><input name="mainCourse" id="mainCourse" type="text" size="20" value="${user.mainCourse}" readonly="readonly"></TD>
    </tr>
     <tr bgColor=#ffffff>
      <TD class="font1" width="45%" align=right height="26"> 办公地点：</td>
      <TD class="font1" align=left><input name="address" id="address" type="text" size="20" value="${user.address}" readonly="readonly"></TD>
    </tr>
     <tr bgColor=#ffffff>
      <TD class="font1" width="45%" align=right height="26">获得成就：</td>
      <TD class="font1" align=left><input name="accomplishment" id="accomplishment" type="text" size="20" value="${user.accomplishment}" readonly="readonly" ></TD>
    </tr>
     <tr bgColor=#ffffff>
      <TD class="font1" width="45%" align=right height="26">个人简介：</td>
      <TD class="font1" align=left>
      	<textarea rows="5" cols="35" name="brief" readonly="readonly">${user.brief }</textarea>
       </TD>
    </tr>
     <tr bgColor=#ffffff>
      <TD class="font1" width="45%" align=right height="26">头像：</td>
      <TD class="font1" align=left>
      	<img src="${ctx}/upload/${user.photo}" width="200" height="150"/>
      </TD>
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
