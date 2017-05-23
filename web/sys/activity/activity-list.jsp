<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/include/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css"> 
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
<%
String message = (String)session.getAttribute("msg");
System.out.println(message);
	if(message == null){
		message = "";
	}
	if (!message.trim().equals("")){
		out.println("<script language='javascript'>");
		out.println("alert('"+message+"');");
		out.println("</script>");
	}
	session.removeAttribute("msg");
%>

<body>
<form method="post" action="${ctx}/activity.action" name="queryForm">
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
					  <td class="font2" colspan="3" align="center" style="font-size: 14px;font-weight: bold;">&nbsp;教学活动信息管理</td>
					</tr>
					<tr style="font-size: 12px;">
						<td>活动名称:<input type="text" name="name" value="${name }"/></td>
						<td>
						&nbsp;&nbsp;<input type="button"  value="查询" onclick="javaScript:activity.queryForm.submit()">
        	            &nbsp;&nbsp;<input type="button"  value="新增" onclick="location.href='${ctx}/activity!input.action'">
						</td>
					</tr>
				  </table>
				</td>
			  </tr>
			</table>
		  </td>
		</tr>
		<tr>
		  <td>	
			<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#BBD3EB">
			  <tr>
				  <td class="font1" height="27" align="center" ><strong>序号</strong></td>
				  <td class="font1" align="center" ><strong>名称</strong></td>
				  <td class="font1" align="center" ><strong>类别</strong></td>
				  <td class="font1" align="center" ><strong>内容简介</strong></td>
				  <td class="font1" align="center" ><strong>获得成果</strong></td>
				  <td class="font1" align="center" ><strong>地点</strong></td>		
				  <td class="font1" align="center" ><strong>创建时间</strong></td>				 
			      <td class="font1" align="center" ><strong>操作</strong></td>
    </tr>
    <c:forEach items="${pageResult.list }" begin="0" var="activity" varStatus="status">
		<tr bgColor=#ffffff align=center>
		    <td class="font1" bgcolor="#FFFFFF" align=center>${status.index + 1}</td>
			<td class="font1" bgcolor="#FFFFFF" align=center>${activity.name}</td>
			<td class="font1" bgcolor="#FFFFFF" align=center>
				<c:if test="${activity.type=='1'}"> 学科竞赛</c:if>
				<c:if test="${activity.type=='2'}"> 授课竞赛</c:if>
				<c:if test="${activity.type=='3'}"> 师生座谈</c:if>
				<c:if test="${activity.type=='4'}"> 教指委</c:if>
				<c:if test="${activity.type=='5'}"> 认知实习</c:if>
				<c:if test="${activity.type=='6'}"> 工作实习</c:if>
			    <c:if test="${activity.type=='7'}"> 教学评价</c:if>
			</td>
			<td class="font1" bgcolor="#FFFFFF" align=center>${activity.brief}</td>
			<td class="font1" bgcolor="#FFFFFF" align=center>${activity.result}</td>
			<td class="font1" bgcolor="#FFFFFF" align=center>${activity.address}</td>
			<td class="font1" bgcolor="#FFFFFF" align=center>${activity.createTime}</td>
			<td class="font1" bgcolor="#FFFFFF" align=center>
			<a href="${ctx}/activity!edit?id=${activity.activityId}">修改</a>
			<a href="${ctx}/activity!delete?id=${activity.activityId}">删除</a>
			  <a href="${ctx}/activity!downLoad?id=${activity.data.enclosureId}">下载资料</a>
			</td>
		</tr>   
   </c:forEach>
        <tr bgcolor="#FFFFFF" style="font-size: 12px;">
				  	<td colspan="10" align="right">
				  		共${pageResult.page.totalCount}条纪录，当前第${pageResult.page.currentPage}/${pageResult.page.totalPage}页，每页${pageResult.page.everyPage}条纪录
				  	<c:if test="${pageResult.page.hasPrePage }">				  	
                		<a href="${ctx}/activity.action?currentPage=1">首页</a> | 
                		<a href="${ctx}/activity.action?currentPage=${pageResult.page.currentPage - 1}">上一页</a> | 
               		</c:if>
               		<c:if test="${!pageResult.page.hasPrePage }">
               		首页 | 上一页 | 
               		</c:if>
               		<c:if test="${pageResult.page.hasNextPage }">               		
                		<a href="${ctx}/activity.action?currentPage=${pageResult.page.currentPage + 1}">下一页</a> | 
                		<a href="${ctx}/activity.action?currentPage=${pageResult.page.totalPage}">尾页</a>
               		</c:if>
               		<c:if test="${!pageResult.page.hasNextPage }">
               		下一页 | 尾页
               		</c:if>
				  	</td>
				  </tr>	 
	  </table> 
		  </td>
		</tr> 
	  </table>
	</td>
	<td background="${ctx }/images/index1_47.gif"></td>
  </tr>
  <tr>
	<td width="8" height="8"><img src="images/index1_91.gif" width="8" height="8" /></td>
	<td background="images/index1_92.gif"></td>
	<td width="8" height="8"><img src="images/index1_93.gif" width="8" height="8" /></td>
  </tr>
</table>
</form>
</body>

