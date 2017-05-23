<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/include/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<LINK href="${ctx}/css/admin.css" type="text/css" rel="stylesheet">
<SCRIPT language=javascript>
	function expand(el)
	{
		childObj = document.getElementById("child" + el);

		if (childObj.style.display == 'none')
		{
			childObj.style.display = 'block';
		}
		else
		{
			childObj.style.display = 'none';
		}
		return;
	}
</SCRIPT>
</HEAD>
<body>
<TABLE height="100%" cellSpacing=0 cellPadding=0 width=170 background="${ctx }/images/menu_bg.jpg" border=0>
  <TR>
    <TD vAlign=top align=middle>
      <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0><TR><TD height=10></TD></TR></TABLE> 
          <TABLE cellSpacing=0 cellPadding=0 width=150 border=0>
        <TR height=22>
          <TD style="PADDING-LEFT: 30px" background="${ctx}/images/menu_bt.jpg">
          <A class=menuParent onclick=expand(0) href="javascript:void(0);">教师团队管理</A></TD>
		</TR>
        <TR height=4><TD></TD></TR>
	  </TABLE>
          <TABLE id=child0 style="DISPLAY:" cellSpacing=0 cellPadding=0 width=150 border=0>  
        <TR height=20>
          <TD align=middle width=30><IMG height=9 src="${ctx}/images/menu_icon.gif" width=9></TD>
          <TD><A class=menuChild href="${ctx}/user!teamList.action" target="MainFrame">教师团队信息管理</A></TD></TR>                      
        <TR height=4>
          <TD colSpan=2></TD>
		</TR>
	  </TABLE> 
	  
	  
      <TABLE cellSpacing=0 cellPadding=0 width=150 border=0>
        <TR height=22>
          <TD style="PADDING-LEFT: 30px" background="${ctx}/images/menu_bt.jpg">
          <A class=menuParent onclick=expand(1) href="javascript:void(0);">教学文档管理</A></TD>
		</TR>
        <TR height=4><TD></TD></TR>
	  </TABLE>
      <TABLE id=child1 style="DISPLAY: none" cellSpacing=0 cellPadding=0 width=150 border=0>
        <TR height=20>
          <TD align=middle width=30><IMG height=9 src="${ctx}/images/menu_icon.gif" width=9></TD>
          <TD><A class=menuChild href="${ctx}/document.action" target="MainFrame">教学文档</A></TD>
          </TR>                
        <TR height=4>
          <TD colSpan=2></TD>
		</TR>
	  </TABLE>  	
	  
	  <TABLE cellSpacing=0 cellPadding=0 width=150 border=0>
        <TR height=22>
          <TD style="PADDING-LEFT: 30px" background="${ctx}/images/menu_bt.jpg">
          <A class=menuParent onclick=expand(2) href="javascript:void(0);">学生管理管理</A></TD>
		</TR>
        <TR height=4><TD></TD></TR>
	  </TABLE>
      <TABLE id=child2 style="DISPLAY: none" cellSpacing=0 cellPadding=0 width=150 border=0>
        <TR height=20>
          <TD align=middle width=30><IMG height=9 src="${ctx}/images/menu_icon.gif" width=9></TD>
          <TD><A class=menuChild href="${ctx}/student.action" target="MainFrame">学生信息管理</A></TD>
          </TR>                
        <TR height=4>
          <TD colSpan=2></TD>
		</TR>
	  </TABLE> 
		  
	  <TABLE cellSpacing=0 cellPadding=0 width=150 border=0>
        <TR height=22>
          <TD style="PADDING-LEFT: 30px" background="${ctx}/images/menu_bt.jpg">
          <A class=menuParent onclick=expand(3) href="javascript:void(0);">教学活动管理</A></TD>
		</TR>
        <TR height=4><TD></TD></TR>
	  </TABLE>
      <TABLE id=child3 style="DISPLAY: none" cellSpacing=0 cellPadding=0 width=150 border=0>
        <TR height=20>
          <TD align=middle width=30><IMG height=9 src="${ctx}/images/menu_icon.gif" width=9></TD>
          <TD><A class=menuChild href="${ctx}/activity.action" target="MainFrame">教学活动</A></TD>
          </TR>                
        <TR height=4>
          <TD colSpan=2></TD>
		</TR>
	  </TABLE> 
	
	 <TABLE cellSpacing=0 cellPadding=0 width=150 border=0>
        <TR height=22>
          <TD style="PADDING-LEFT: 30px" background="${ctx}/images/menu_bt.jpg">
          <A class=menuParent onclick=expand(4) href="javascript:void(0);">班级管理</A></TD>
		</TR>
        <TR height=4><TD></TD></TR>
	  </TABLE>
      <TABLE id=child4 style="DISPLAY: none" cellSpacing=0 cellPadding=0 width=150 border=0>
        <TR height=20>
          <TD align=middle width=30><IMG height=9 src="${ctx}/images/menu_icon.gif" width=9></TD>
          <TD><A class=menuChild href="${ctx}/grade.action" target="MainFrame">班级信息管理</A></TD>
          </TR>                
        <TR height=4>
          <TD colSpan=2></TD>
		</TR>
	  </TABLE> 
	   <TABLE cellSpacing=0 cellPadding=0 width=150 border=0>
        <TR height=22>
          <TD style="PADDING-LEFT: 30px" background="${ctx}/images/menu_bt.jpg">
          <A class=menuParent onclick=expand(5) href="javascript:void(0);">课程管理</A></TD>
		</TR>
        <TR height=4><TD></TD></TR>
	  </TABLE>
      <TABLE id=child5 style="DISPLAY: none" cellSpacing=0 cellPadding=0 width=150 border=0>
        <TR height=20>
          <TD align=middle width=30><IMG height=9 src="${ctx}/images/menu_icon.gif" width=9></TD>
          <TD><A class=menuChild href="${ctx}/course.action" target="MainFrame">课程信息管理</A></TD>
          </TR>                
        <TR height=4>
          <TD colSpan=2></TD>
		</TR>
	  </TABLE> 
	  
	    <TABLE cellSpacing=0 cellPadding=0 width=150 border=0>
        <TR height=22>
          <TD style="PADDING-LEFT: 30px" background="${ctx}/images/menu_bt.jpg">
          <A class=menuParent onclick=expand(6) href="javascript:void(0);">系统管理</A></TD>
		</TR>
        <TR height=4><TD></TD></TR>
	  </TABLE>	  
      <TABLE id=child6 style="DISPLAY:none" cellSpacing=0 cellPadding=0 width=150 border=0>
        <TR height=20>
          <TD align=middle width=30><IMG height=9 src="${ctx}/images/menu_icon.gif" width=9></TD>
          <TD><A class=menuChild href="${ctx}/user.action" target="MainFrame">个人信息管理</A></TD>
          </tr>    
              
        <TR height=4>
          <TD colSpan=2></TD>
		</TR>
	  </TABLE>  
       </TD>
     </TR> 
</TABLE>
</body>
</HTML>
