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
<form method="post" action="${ctx}/course.action" name="queryForm">
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
					  <td class="font2" colspan="3" align="center" style="font-size: 14px;font-weight: bold;">&nbsp;班级信息管理</td>
					</tr>
					<tr style="font-size: 12px;">
						<td>课程名称:<input type="text" name="name" value="${name }"/></td>
						<td>
						&nbsp;&nbsp;<input type="button"  value="查询" onclick="javaScript:document.queryForm.submit()">
        	            &nbsp;&nbsp;<input type="button"  value="新增" onclick="location.href='${ctx}/course!input.action'">
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
				  <td class="font1" align="center" ><strong>课程名称</strong></td>
				  <td class="font1" align="center" ><strong>课程编号</strong></td>
				  <td class="font1" align="center" ><strong>课程简介</strong></td>	
				  <td class="font1" align="center" ><strong>课程类别</strong></td>	
				  <td class="font1" align="center" ><strong>所带班级</strong></td>	
			      <td class="font1" align="center" ><strong>操作</strong></td>
    </tr>
    <c:forEach items="${pageResult.list }" begin="0" var="course" varStatus="status">
		<tr bgColor=#ffffff align=center>
		    <td class="font1" bgcolor="#FFFFFF" align=center>${status.index + 1}</td>
			<td class="font1" bgcolor="#FFFFFF" align=center>${course.name}</td>
			<td class="font1" bgcolor="#FFFFFF" align=center>${course.courseNo}</td>
			<td class="font1" bgcolor="#FFFFFF" align=center>
			<c:if test="${fn:length(course.brief) >= 30}">
			   ${fn:substring(course.brief, 0, 30)}......
			</c:if>
			<c:if test="${fn:length(course.brief) < 30}">
			   ${course.brief}
			</c:if>
			</td>
			<td class="font1" bgcolor="#FFFFFF" align=center>
				<c:if test="${course.courseType=='1'}">专业必修课</c:if>
				<c:if test="${course.courseType=='2'}">专业选修课</c:if>
			 </td>
			<td class="font1" bgcolor="#FFFFFF" align=center>${course.grades}</td>
			<td class="font1" bgcolor="#FFFFFF" align=center>
			<a href="${ctx}/course!edit?id=${course.courseId}">修改</a>
			<a href="${ctx}/course!delete?id=${course.courseId}">删除</a>
			  <a href="${ctx}/course!downLoad?id=${course.courseTable.enclosureId}">下载班级课表</a>
			  <a href="${ctx}/course!downLoad?id=${course.data.enclosureId}">下载资料</a>
			</td>
		</tr>   
   </c:forEach>
        <tr bgcolor="#FFFFFF" style="font-size: 12px;">
				  	<td colspan="10" align="right">
				  		共${pageResult.page.totalCount}条纪录，当前第${pageResult.page.currentPage}/${pageResult.page.totalPage}页，每页${pageResult.page.everyPage}条纪录
				  	<c:if test="${pageResult.page.hasPrePage }">				  	
                		<a href="${ctx}/course.action?currentPage=1">首页</a> | 
                		<a href="${ctx}/course.action?currentPage=${pageResult.page.currentPage - 1}">上一页</a> | 
               		</c:if>
               		<c:if test="${!pageResult.page.hasPrePage }">
               		首页 | 上一页 | 
               		</c:if>
               		<c:if test="${pageResult.page.hasNextPage }">               		
                		<a href="${ctx}/course.action?currentPage=${pageResult.page.currentPage + 1}">下一页</a> | 
                		<a href="${ctx}/course.action?currentPage=${pageResult.page.totalPage}">尾页</a>
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

