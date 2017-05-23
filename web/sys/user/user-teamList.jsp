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
<form method="post" action="${ctx}/user!teamList.action" name="queryForm">
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
					  <td class="font2" colspan="3" align="center" style="font-size: 14px;font-weight: bold;">&nbsp;教师团队信息管理</td>
					</tr>
					<tr style="font-size: 12px;">
						<td>用户姓名:<input type="text" name="name" value="${name }"/></td>
						<td>&nbsp;&nbsp;用户账号:<input type="text" name="account" value="${account }"/></td>
						<td>
						&nbsp;&nbsp;<input type="button"  value="查询" onclick="javaScript:document.queryForm.submit()">
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
				  <td class="font1" align="center" ><strong>账号</strong></td>
				  <td class="font1" align="center" ><strong>姓名</strong></td>
			      <td class="font1" align="center" ><strong>年龄</strong></td>
			      <td class="font1" align="center" ><strong>性别</strong></td>
			      <td class="font1" align="center" ><strong>手机号码</strong></td>
			      <td class="font1" align="center" ><strong>办公电话</strong></td>
			       <td class="font1" align="center" ><strong>E-mail</strong></td>
			       <td class="font1" align="center" ><strong>名师级别</strong></td>
			       <td class="font1" align="center" ><strong>课程组名称</strong></td>
			       <td class="font1" align="center" ><strong>职称/职务</strong></td>
			       <td class="font1" align="center" ><strong>专业</strong></td>
			       <td class="font1" align="center" ><strong>主课程</strong></td>
			       <td class="font1" align="center" ><strong>获得成就</strong></td>
			      <td class="font1" align="center" ><strong>创建时间</strong></td>
			      <td class="font1" align="center" ><strong>操作</strong></td>
    </tr>
    <c:forEach items="${pageResult.list }" begin="0" var="user" varStatus="status">
		<tr bgColor=#ffffff align=center>
		    <td class="font1" bgcolor="#FFFFFF" align=center>${status.index + 1}</td>
			<td class="font1" bgcolor="#FFFFFF" align=center>${user.account}</td>
			<td class="font1" bgcolor="#FFFFFF" align=center>${user.name}</td>
			<td class="font1" bgcolor="#FFFFFF" align=center>${user.age}</td>
			<td class="font1" bgcolor="#FFFFFF" align=center>${user.sex=='1'?'男':'女'}</td>
		    <td class="font1" bgcolor="#FFFFFF" align=center>${user.mobile}</td>
		    <td class="font1" bgcolor="#FFFFFF" align=center>${user.officePhone}</td>
		    <td class="font1" bgcolor="#FFFFFF" align=center>${user.emial}</td>
		    <td class="font1" bgcolor="#FFFFFF" align=center>
		    	<c:if test="${user.rank=='1'}">国家级</c:if>
		    	<c:if test="${user.rank=='2'}">省级</c:if>
		    	<c:if test="${user.rank=='3'}">校级</c:if>
		    	<c:if test="${user.rank=='4'}">院级</c:if>
		    	<c:if test="${user.rank=='5'}">无级别</c:if>
		    </td>
		    <td class="font1" bgcolor="#FFFFFF" align=center>${user.courseGroup}</td>
		    <td class="font1" bgcolor="#FFFFFF" align=center>
		    		<c:if test="${user.position=='1'}">教授</c:if>
		    		<c:if test="${user.position=='2'}">副教授</c:if>
		    		<c:if test="${user.position=='3'}">讲师</c:if>
		    		<c:if test="${user.position=='4'}">助教</c:if>
		    		<c:if test="${user.position=='5'}">无职称</c:if>
		    </td>
		    <td class="font1" bgcolor="#FFFFFF" align=center>${user.specialty}</td>
		    <td class="font1" bgcolor="#FFFFFF" align=center>${user.mainCourse}</td>
		    <td class="font1" bgcolor="#FFFFFF" align=center>${user.accomplishment}</td>					
			<td class="font1" bgcolor="#FFFFFF" align=center>${user.createTime}</td>
			<td class="font1" bgcolor="#FFFFFF" align=center>
			  <a href="${ctx}/user!view?id=${user.userId}">详情</a>			
			</td>
		</tr>   
   </c:forEach>
        <tr bgcolor="#FFFFFF" style="font-size: 12px;">
				  	<td colspan="20" align="right">
				  		共${pageResult.page.totalCount}条纪录，当前第${pageResult.page.currentPage}/${pageResult.page.totalPage}页，每页${pageResult.page.everyPage}条纪录
				  	<c:if test="${pageResult.page.hasPrePage }">				  	
                		<a href="${ctx}/user!teamList.action?currentPage=1">首页</a> | 
                		<a href="${ctx}/user!teamList.action?currentPage=${pageResult.page.currentPage - 1}">上一页</a> | 
               		</c:if>
               		<c:if test="${!pageResult.page.hasPrePage }">
               		首页 | 上一页 | 
               		</c:if>
               		<c:if test="${pageResult.page.hasNextPage }">               		
                		<a href="${ctx}/user!teamList.action?currentPage=${pageResult.page.currentPage + 1}">下一页</a> | 
                		<a href="${ctx}/user!teamList.action?currentPage=${pageResult.page.totalPage}">尾页</a>
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

