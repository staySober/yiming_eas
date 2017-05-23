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
					  <td class="font2" style="font-size: 14px;font-weight: bold;">&nbsp;新增课程信息</td>
					</tr>
				  </table>
				</td>
			  </tr>
			</table>
		  </td>
		</tr>
		<tr>
		  <td>
<form method="post" name="myform" id="myform" onSubmit="return check()" action="${ctx}/course!add.action" enctype="multipart/form-data"> 
<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#BBD3EB">
  <tbody>   
    <tr bgColor=#ffffff>
      <TD class="font1" width="45%" align=right height="26">课程名称：</td>
      <TD class="font1" align=left><input name="name" id="name" type="text" size="20" ></TD>
    </tr> 
     <tr bgColor=#ffffff>
      <TD class="font1" width="45%" align=right height="26">课程编号：</td>
      <TD class="font1" align=left><input name="courseNo" id="courseNo" type="text" size="20" ></TD>
    </tr> 
    <tr bgColor=#ffffff>
      <TD class="font1" width="45%" align=right height="26">课程简介：</td>
      <TD class="font1" align=left>
			<textarea rows="10" cols="38" name="brief"></textarea>
		</TD>
    </tr> 
    <tr bgColor=#ffffff>
      <TD class="font1" width="45%" align=right height="26">课程类别：</td>
      <TD class="font1" align=left>
      	<select name="courseType">
      		<option value="1">专业必修课</option>
      		<option value="2">专业选修课</option>
      	</select>
      </TD>
    </tr> 
    <tr bgColor=#ffffff>
      <TD class="font1" width="45%" align=right height="26">所带班级：</td>
      <TD class="font1" align=left><input name="grades" id="grades" type="text" size="20" ></TD>
    </tr> 
    <tr bgColor=#ffffff>
      <TD class="font1" width="45%" align=right height="26">课程表：</td>
      <TD class="font1" align=left>
           <input type="file" name="myFile0">
       </TD>
    </tr>
    <tr bgColor=#ffffff>
      <TD class="font1" width="45%" align=right height="26">上传资料：</td>
      <TD class="font1" align=left>
           <input type="file" name="myFile1">
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
