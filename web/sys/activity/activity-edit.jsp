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
					  <td class="font2" style="font-size: 14px;font-weight: bold;">&nbsp;修改教学活动基本信息</td>
					</tr>
				  </table>
				</td>
			  </tr>
			</table>
		  </td>
		</tr>
		<tr>
		  <td>
<form method="post" name="myform" id="myform" onSubmit="return check()" action="${ctx}/activity!update.action" enctype="multipart/form-data">
<input type="hidden" name="activityId"  value="${activity.activityId }"/>
<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#BBD3EB">
  <tbody>   
    <tr bgColor=#ffffff>
      <TD class="font1" width="45%" align=right height="26">名称：</td>
      <TD class="font1" align=left><input name="name" id="name" type="text" size="20" value="${activity.name }"></TD>
    </tr> 
   
    <tr bgColor=#ffffff>
      <TD class="font1" width="45%" align=right height="26">类别：</td>
      <TD class="font1" align=left>
			<select name="type">
			    <option value="1" <c:if test="${activity.type=='1'}">selected="selected"</c:if>>学科竞赛</option>
			    <option value="2" <c:if test="${activity.type=='2'}">selected="selected"</c:if>>授课竞赛</option>
			    <option value="3" <c:if test="${activity.type=='3'}">selected="selected"</c:if>>师生座谈</option>
			    <option value="4" <c:if test="${activity.type=='4'}">selected="selected"</c:if>>教指委</option>
			    <option value="5" <c:if test="${activity.type=='5'}">selected="selected"</c:if>>认知实习</option>
			    <option value="6" <c:if test="${activity.type=='6'}">selected="selected"</c:if>>工作实习</option>
			    <option value="7" <c:if test="${activity.type=='7'}">selected="selected"</c:if>>教学评价</option>
			</select>
		</TD>
    </tr> 
      <tr bgColor=#ffffff>
      <TD class="font1" width="45%" align=right height="26">内容简介：</td>
      <TD class="font1" align=left>
      <textarea rows="10" cols="38" name="brief">${activity.brief }</textarea>
       </TD>
    </tr> 
    <tr bgColor=#ffffff>
      <TD class="font1" width="45%" align=right height="26">获得成果：</td>
      <TD class="font1" align=left>
      <input name="result" id="result" type="text" size="20" value="${activity.result }">
	      </TD>
    </tr> 
    <tr bgColor=#ffffff>
      <TD class="font1" width="45%" align=right height="26">地点：</td>
      <TD class="font1" align=left><input name="address" id="address" type="text" size="20" value="${activity.address }"></TD>
    </tr>      
      <tr bgColor=#ffffff>
      <TD class="font1" width="45%" align=right height="26">备注：</td>
      <TD class="font1" align=left><textarea rows="10" cols="38" name="mark">${activity.mark }</textarea></TD>
    </tr> 
    <tr bgColor=#ffffff>
      <TD class="font1" width="45%" align=right height="26">已上传资料：</td>
      <TD class="font1" align=left>
          <a href="${ctx}/activity!downLoad?id=${activity.data.enclosureId}">${activity.data.fileName }</a>
       </TD>
    </tr>
    <tr bgColor=#ffffff>
      <TD class="font1" width="45%" align=right height="26">上传资料：</td>
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
