<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/include/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<LINK href="${ctx }/css/admin.css" type="text/css" rel="stylesheet">
</HEAD>

<BODY>
<TABLE cellSpacing=0 cellPadding=0 width="100%" background="${ctx}/images/header_bg.jpg" border=0>
  <TR height=56>
    <TD width=500 style="font-size: 25px;font-weight: bold;color:#FFFFFF;letter-spacing:10px;">&nbsp;&nbsp;教务管理系统</TD>
    <TD style="FONT-WEIGHT: bold; COLOR: #fff; PADDING-TOP: 20px" align="right">当前用户：${session_user_key.name  } &nbsp;&nbsp;
    </TD>
    <TD align=right width=268><IMG height=56 src="${ctx}/images/header_right.jpg" width=268></TD></TR></TABLE>
<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0><TR bgColor=#1c5db6 height=4><TD></TD></TR></TABLE>
</BODY>
</HTML>