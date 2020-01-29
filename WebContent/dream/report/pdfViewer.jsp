<%--===========================================================================
PDF Report Viewer
author  javaworker
version $Id: pdfViewer.jsp,v 1.1 2015/09/03 07:55:48 brpark Exp $
since   1.0
===========================================================================--%>
<%@page import="java.util.*"%>
<%@page import="java.io.File"%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@page import="common.bean.User"%>
<%@page import="common.report.ReportPDF"%>
<%@page import="common.struts.BaseAction"%>
<%
Object reportObj = request.getAttribute(BaseAction.REPORT_MAP_ATTRIBUTE);
String reportName = (String)request.getAttribute(BaseAction.REPORT_NAME_ATTRIBUTE);
/* 
User user = (User)session.getAttribute(session.getId());
String pdfName = reportName + "_" + user.getUserId();

ReportPDF report = new ReportPDF(reportObj, getServletContext().getRealPath("/"), reportName, pdfName); 
report.generatePDF();

// delete old fo, pdf file generated
report.deleteTempPdfFiles(); */
%>
<div>
   	<iframe id="PDF_VIEWER" src="" width="100%" height="100%" ></iframe>
</div>
<script>
<%-- document.getElementById("PDF_VIEWER").src = "<%=request.getContextPath()%>"+"/dream/report/temp/<%=pdfName%>.pdf"; --%>
document.getElementById("PDF_VIEWER").src = "<%=request.getContextPath()%>"+"/common/jasper/<%=reportName%>";
window.focus();
</script>