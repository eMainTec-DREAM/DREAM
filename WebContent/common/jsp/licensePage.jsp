<%--===========================================================================
author  pksup
version $Id: error500.jsp,v 1.1 2013/08/30 09:09:09 javaworker Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="true"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>

<%

Object namedObj = session.getAttribute("ALERT");

Object bottomAlertObject = request.getAttribute("ALERT");
String bottomAlert = "";

if (bottomAlertObject != null && bottomAlertObject !="null")
{
    bottomAlert = (String)bottomAlertObject;
}
else
{
    bottomAlert = (String)namedObj;
}
%>

<script>

var bottomAlert = '<%=bottomAlert%>';
if (bottomAlert != null && bottomAlert != '')
{
	var alertJson = JSON.parse(bottomAlert);

	if(alertJson != null)
	if(alertJson.code=="WRONGIDPW"){
		var pwFailLimitCnt = alertJson.pwFailLimitCnt;
		var pwFailCnt = alertJson.pwFailCnt;
		var _text = "";
		_text += "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">                                                                                                                                    ";
		_text += "<html xmlns=\"http://www.w3.org/1999/xhtml\">                                                                                                                                                                                                 ";
		_text += "<head>                                                                                                                                                                                                                                        ";
		_text += "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />                                                                                                                                                                     ";
		_text += "<title>Untitled Document</title>                                                                                                                                                                                                              ";
		_text += "<style>                                                                                                                                                                                                                                       ";
		_text += "	p {                                                                                                                                                                                                                                         ";
		_text += "		margin-block-start: 1em;                                                                                                                                                                                                                ";
		_text += "	    margin-block-end: 1em;                                                                                                                                                                                                                  ";
		_text += "	    margin-inline-start: 0px;                                                                                                                                                                                                               ";
		_text += "	    margin-inline-end: 0px;                                                                                                                                                                                                                 ";
		_text += "    }                                                                                                                                                                                                                                         ";
		_text += "</style>                                                                                                                                                                                                                                      ";
		_text += "</head>                                                                                                                                                                                                                                       ";
		_text += "<body style=\"font-family:'맑은 고딕','Malgun Gothic','돋움',Dotum,Arial,Helvetica,sans-serif\">                                                                                                                                                    ";
		_text += "	<div style=\"padding:45px; text-align:center;\"> <img src=\"common/images/ma/i_alert_info.png\" />                                                                                                                                          ";
		_text += "	  <p style=\"font-size:18px; font-weight:bold; color:#333333; letter-spacing:-2px;\"><bean:message key='MESSAGE.MSG0286'/><br />                                                                                                            ";
		_text += "	    <bean:message key='MESSAGE.MSG0287'/></p>                                                                                                                                                                                               ";
		_text += "	  <p style=\"font-size:14px; color:#333333; text-align:left; color:#666666; \"><bean:message key='MESSAGE.MSG0288'/>                                                                                                                        ";
		if(pwFailLimitCnt!='0'){
			_text += "	  <br />"+pwFailLimitCnt+"<bean:message key='MESSAGE.MSG0253'/></p>                                                                                                                                                                     ";
			_text += "	  <p style=\"font-size:16px; color:#333333; color:#666666; text-decoration:underline;\"><bean:message key='MESSAGE.MSG0254'/> <span style=\"color:#000; font-weight:bold;\">"+pwFailCnt+"</span><bean:message key='MESSAGE.MSG0255'/>   ";
		}
		_text += "	  </p>                                                                                                                                                                                                                                      ";
		_text += "	</div>                                                                                                                                                                                                                                      ";
		_text += "</body>                                                                                                                                                                                                                                       ";
		_text += "</html>																																																			                            ";
		
		var topPage = getTopPage();
		topPage.dhtmlx.message({
			id : "loading"
			,text: _text
			,type :"alert"
			,ok:"<bean:message key='LABEL.ok'/>"
			,"width":"450px"
		});
	}
	else if(alertJson.code=="OVERUSER"){
		var pwFailLimitCnt = alertJson.pwFailLimitCnt;
		var pwFailCnt = alertJson.pwFailCnt;
		var _text = "";
		_text += "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">                                                                                                                                    ";
		_text += "<html xmlns=\"http://www.w3.org/1999/xhtml\">                                                                                                                                                                                                 ";
		_text += "<head>                                                                                                                                                                                                                                        ";
		_text += "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />                                                                                                                                                                     ";
		_text += "<title>Untitled Document</title>                                                                                                                                                                                                              ";
		_text += "<style>                                                                                                                                                                                                                                       ";
		_text += "	p {                                                                                                                                                                                                                                         ";
		_text += "		margin-block-start: 1em;                                                                                                                                                                                                                ";
		_text += "	    margin-block-end: 1em;                                                                                                                                                                                                                  ";
		_text += "	    margin-inline-start: 0px;                                                                                                                                                                                                               ";
		_text += "	    margin-inline-end: 0px;                                                                                                                                                                                                                 ";
		_text += "    }                                                                                                                                                                                                                                         ";
		_text += "</style>                                                                                                                                                                                                                                      ";
		_text += "</head>                                                                                                                                                                                                                                       ";
		_text += "<body style=\"font-family:'맑은 고딕','Malgun Gothic','돋움',Dotum,Arial,Helvetica,sans-serif\">                                                                                                                                                    ";
		_text += "	<div style=\"padding:45px; text-align:center;\"> <img src=\"common/images/ma/i_alert_info.png\" />                                                                                                                                          ";
		_text += "	  <p style=\"font-size:18px; font-weight:bold; color:#333333; letter-spacing:-2px;\"><bean:message key='MESSAGE.MSG0291'/><br />                                                                                                            ";
		_text += "	    <bean:message key='MESSAGE.MSG0292'/></p>                                                                                                                                                                                               ";
		_text += "	  <p style=\"font-size:14px; color:#333333; text-align:left; color:#666666; \"><bean:message key='MESSAGE.MSG0293'/>                                                                                                                        ";
		if(pwFailLimitCnt!='0'){
			_text += "	  <br />"+pwFailLimitCnt+"<bean:message key='MESSAGE.MSG0253'/></p>                                                                                                                                                                     ";
			_text += "	  <p style=\"font-size:16px; color:#333333; color:#666666; text-decoration:underline;\"><bean:message key='MESSAGE.MSG0254'/> <span style=\"color:#000; font-weight:bold;\">"+pwFailCnt+"</span><bean:message key='MESSAGE.MSG0255'/>   ";
		}
		_text += "	  </p>                                                                                                                                                                                                                                      ";
		_text += "	</div>                                                                                                                                                                                                                                      ";
		_text += "</body>                                                                                                                                                                                                                                       ";
		_text += "</html>																																																			                            ";
		
		var topPage = getTopPage();
		topPage.dhtmlx.message({
			id : "loading"
			,text: _text
			,type :"alert"
			,ok:"<bean:message key='LABEL.ok'/>"
			,"width":"450px"
		});
	}
	else if(alertJson.code=="NAMEDOVER"){

		var _text = "";
		_text += "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">                                                                                                                                    ";
		_text += "<html xmlns=\"http://www.w3.org/1999/xhtml\">                                                                                                                                                                                                 ";
		_text += "<head>                                                                                                                                                                                                                                        ";
		_text += "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />                                                                                                                                                                     ";
		_text += "<title>Untitled Document</title>                                                                                                                                                                                                              ";
		_text += "<style>                                                                                                                                                                                                                                       ";
		_text += "	p {                                                                                                                                                                                                                                         ";
		_text += "		margin-block-start: 1em;                                                                                                                                                                                                                ";
		_text += "	    margin-block-end: 1em;                                                                                                                                                                                                                  ";
		_text += "	    margin-inline-start: 0px;                                                                                                                                                                                                               ";
		_text += "	    margin-inline-end: 0px;                                                                                                                                                                                                                 ";
		_text += "    }                                                                                                                                                                                                                                         ";
		_text += "</style>                                                                                                                                                                                                                                      ";
		_text += "</head>                                                                                                                                                                                                                                       ";
		_text += "<body style=\"font-family:'맑은 고딕','Malgun Gothic','돋움',Dotum,Arial,Helvetica,sans-serif\">                                                                                                                                                    ";
		_text += "	<div style=\"padding:45px; text-align:center;\"> <img src=\"common/images/ma/i_alert_info.png\" />                                                                                                                                          ";
		_text += "	  <p style=\"font-size:18px; font-weight:bold; color:#333333; letter-spacing:-2px;\"><bean:message key='MESSAGE.MSG0291'/><br /></p>                                                                                                        ";
		_text += "	  <p style=\"font-size:14px; color:#333333; text-align:left; color:#666666; \"><bean:message key='MESSAGE.MSG0293'/>                                                                                                                        ";
		
		_text += "	  </p>                                                                                                                                                                                                                                      ";
		_text += "	</div>                                                                                                                                                                                                                                      ";
		_text += "</body>                                                                                                                                                                                                                                       ";
		_text += "</html>																																																			                            ";
		
		var topPage = getTopPage();
		topPage.dhtmlx.message({
			id : "loading"
			,text: _text
			,type :"alert"
			,ok:"<bean:message key='LABEL.ok'/>"
			,"width":"450px"
		});
	}
	else if(alertJson.code=="LOCKUSER"){
		var _text = "";
		_text += "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">                                                                                                                                    ";
		_text += "<html xmlns=\"http://www.w3.org/1999/xhtml\">                                                                                                                                                                                                 ";
		_text += "<head>                                                                                                                                                                                                                                        ";
		_text += "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />                                                                                                                                                                     ";
		_text += "<title>Untitled Document</title>                                                                                                                                                                                                              ";
		_text += "<style>                                                                                                                                                                                                                                       ";
		_text += "	p {                                                                                                                                                                                                                                         ";
		_text += "		margin-block-start: 1em;                                                                                                                                                                                                                ";
		_text += "	    margin-block-end: 1em;                                                                                                                                                                                                                  ";
		_text += "	    margin-inline-start: 0px;                                                                                                                                                                                                               ";
		_text += "	    margin-inline-end: 0px;                                                                                                                                                                                                                 ";
		_text += "    }                                                                                                                                                                                                                                         ";
		_text += "</style>                                                                                                                                                                                                                                      ";
		_text += "</head>                                                                                                                                                                                                                                       ";
		_text += "<body style=\"font-family:'맑은 고딕','Malgun Gothic','돋움',Dotum,Arial,Helvetica,sans-serif\">                                                                                                                                                    ";
		_text += "	<div style=\"padding:45px; text-align:center;\"> <img src=\"common/images/ma/i_alert_info.png\" />                                                                                                                                          ";
		_text += "	  <p style=\"font-size:18px; font-weight:bold; color:#333333; letter-spacing:-2px;\"><bean:message key='MESSAGE.MSG0289'/><br />                                                                                                            ";
		_text += "	    </p>                                                                                                                                                                                                                                    ";
		_text += "	  <p style=\"font-size:14px; color:#333333; text-align:left; color:#666666; \"><bean:message key='MESSAGE.MSG0290'/>                                                                                                                        ";
		_text += "	  </p>                                                                                                                                                                                                                                      ";
		_text += "	</div>                                                                                                                                                                                                                                      ";
		_text += "</body>                                                                                                                                                                                                                                       ";
		_text += "</html>																																																			                            ";
		
		var topPage = getTopPage();
		topPage.dhtmlx.message({
			id : "loading"
			,text: _text
			,type :"alert"
			,ok:"<bean:message key='LABEL.ok'/>"
			,"width":"450px"
		});
	}
}

</script>