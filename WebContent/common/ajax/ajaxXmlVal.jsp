<?xml version="1.0" encoding="UTF-8"?>
<%--===========================================================================
AJAX XML
WAS 에서 client[browser]로 값을 전송시 XML 형태로 전송한다.
author  javaworker
version $Id: ajaxXmlVal.jsp,v 1.1 2013/08/30 09:10:43 javaworker Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/xml; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%
String [] ajaxDesc = (String [])request.getAttribute("AJAX_DESC");

String status      = (String)request.getAttribute("AJAX_STATUS");
String [] ajaxId   = (String [])request.getAttribute("AJAX_ID");
String [] ajaxEtc  = (String [])request.getAttribute("AJAX_ETC");
String [][] ajaxData  = (String [][])request.getAttribute("AJAX_DATA");

// stauts 값이 전송 안된경우 DESC에 값이 있고 없고로 판단한다.
if (status == null || "".equals(status))
{
    if (ajaxDesc == null || ajaxDesc.length == 0)
    {
        status = "INVALID";
    }
    else
    {
        /**
         * description 에 값이 length로 있긴 있지만
         * 들어있는값이 null 이거나 String blank 인 경우 INVALID 처리한다.
         */
        if (ajaxDesc.length == 1 && (ajaxDesc[0] == null || "".equals(ajaxDesc[0])))
        {
            status = "INVALID";
        }
        else
        {
            status = "VALID";
        }
    }
}
    
%>
<DATA status='<%=status%>'>
<%

if (ajaxId != null)
{
    int ajaxIdLength = ajaxId.length;

	for (int i=0; i<ajaxIdLength; i++)
	{
%>
	<ID><![CDATA[<%=ajaxId[i]%>]]></ID>
<%
	}
}
if (ajaxData != null)
{
    int ajaxIdLength = ajaxData.length;

	for (int i=0; i<ajaxIdLength; i++)
	{
	    String fstr = "<ARR" + String.valueOf(i) + ">";
	    String estr = "</ARR" + String.valueOf(i) + ">";
	    
	    for (int j=0; j<ajaxData[i].length; j++)
	    {
%>
		<%=fstr%><![CDATA[<%=ajaxData[i][j]%>]]><%=estr%>
<%
	    }//end for j
	}//end for i
}
if (ajaxDesc != null)
{
    int ajaxDescLength = ajaxDesc.length;

	for (int i=0; i<ajaxDescLength; i++)
	{
%>
	<DESC><![CDATA[<%=ajaxDesc[i]%>]]></DESC>
<%
	}
}
if (ajaxEtc != null)
{
    int ajaxEtcLength = ajaxEtc.length;

	for (int i=0; i<ajaxEtcLength; i++)
	{
%>
	<ETC><![CDATA[<%=ajaxEtc[i]%>]]></ETC>
<%
	}
}
%>
</DATA>