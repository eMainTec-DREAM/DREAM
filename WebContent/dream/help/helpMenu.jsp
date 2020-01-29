<%--===========================================================================
Menu 를 구성하기 위한 JSP 이다.
author  javaworker
version $Id: helpMenu.jsp,v 1.7 2014/04/22 08:51:02 pochul2423 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Hashtable"%>
<%@ page import="common.util.CommonUtil"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%
	Object mainMenuobject  = session.getAttribute("MENU");
	Object adminMenuObject = session.getAttribute("ADMINMENU");

	// 저장되는 ArrayList이다.
	ArrayList menuList      = null;
	String [][] adminMenuList = null;

   	if (mainMenuobject != null)
    {           
        menuList = (ArrayList)mainMenuobject;        
    }
   	
   	if (adminMenuObject != null)
   	{
		adminMenuList = (String [][])adminMenuObject;
   	}
%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=7" />
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
	<meta name="keywords" content="" />
	<meta name="description" content="" />
	<meta name="author" content="" />
	<link rel="stylesheet" href="<c:url value="/common/css/help.css" />" type="text/css">
<title>메뉴</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 공통 페이지 -->
<c:import charEncoding="UTF-8" url="/common/jsp/commonInclude.jsp"></c:import>
<SCRIPT language=Javascript >

/**
 * 처음 로딩시 호출된다.
 */
function init()
{
	var helpPmenuId = parent.helpPmenuId;
	var functionPageId = parent.functionPageId;
	
	if(helpPmenuId == "null" || helpPmenuId == "" || functionPageId=="null" || functionPageId=="")	
	{
		helpPmenuId = 'HOME';
		functionPageId = 'basic';
	}
	
	callViewAnsByID(helpPmenuId);
	goHelpPage(helpPmenuId, functionPageId);
}

/**
 * Menu를 클릭시 펼쳐지는 효과를 나타내게 한다.
 */
function viewAns(index)
{
	// 전체 Main Menu 수를 나타낸다.
    var totCnt = Number(document.all.TD_ANS.length);

    // 현재 클릭한 메뉴를 찾는다.
    index = index - 1;
    if(totCnt == 1 )
    {
       var obj =  document.all.TD_ANS;
    }
    else if ( totCnt > 1)
    {
        var obj =  document.all.TD_ANS[index];
    }

    if (totCnt == 1 )
    {
        document.all.TD_ANS.style.display = "" ;
    }
    else if ( totCnt > 1)
    {
        for (i =0 ; i < totCnt ; i++)
        {
            if ( index == i )
            {
            	// 현재 선택한 메뉴가 펼쳐져 있으면 닫고, 닫혀있으면 펼쳐지게 한다.
                if(obj.style.display == "none")
			    {
			       obj.style.display = "";
			    }
			    else
			    {
			        obj.style.display = "none";
			    }
            }
            else
            {
            	// 선택된 메뉴이외의 메뉴는 모두 닫는다.
               	document.all.TD_ANS[i].style.display = "none" ;
            }
        }
    }
}

/**
 * 해당 페이지의 메뉴얼을 연다.
 */
function goHelpPage(helpPmenuId, functionPageId)
{
	functionPageId = functionPageId + "_help"; 
	
	var helpLang = loginUser.userLang;
	if(helpLang != "ko") helpLang = "en";
	
	parent.helpDoc.location.href = contextPath + "/help/"+ helpLang + "/" + helpPmenuId + "/" + functionPageId + ".html";
	
	// 전체 Main Menu 수를 나타낸다.
    var totCnt = Number(document.all.TD_ANS.length);
	
    for (i =0 ; i < totCnt ; i++)
    {	
    	//Home 일경우에만 모든 메뉴를 닫는다.
    	if(helpPmenuId=='HOME')
    	{
    		// 선택된 메뉴이외의 메뉴는 모두 닫는다.
    	    document.all.TD_ANS[i].style.display = "none" ;
    	}
 
    }
}

function callViewAnsByID(helpPmenuId)
{
	var obj = document.getElementsByName("pMenuId");
	
	for(i=0; i<obj.length; i++)
	{
		if(helpPmenuId == obj[i].value)
		{
			viewAns(i+1);
		}
	}
}
</SCRIPT>
</head>
<body onload="init();" class="bg" style="overflow-x:hidden;">
	<!-- CONTAINER -->
	<div id="popup_container">
		<!-- LNB -->
		<div id="popup_lnb_area">
			<div class="popup_lnb">
				<a href="javascript:goHelpPage('HOME','basic');" onFocus='this.blur();'>
					<img src="<c:url value="/common/images/help/icon_home.gif" />" alt="HOME" />
				</a>
			<ul>
				<li class="depth1_close b_line">
					<a href="javascript:goHelpPage('HOME','basic');" onFocus='this.blur();'>
						<bean:message key='helpMenu.basic'/>
					</a>
				</li>
				<li class="depth1_close b_line">
					<a href="javascript:goHelpPage('HOME','process');" onFocus='this.blur();'>
						<bean:message key='helpMenu.process'/>
					</a>
				</li>
<%
		int i=0;

		// 넘겨진 Menu가 0 보다 큰경우
		if (menuList != null && menuList.size() > 0)
        {

			// 0 번째가 메인 메뉴이다.
			Hashtable mainHash = (Hashtable)menuList.get(0);

			// menu[0][0] : 보여지는 이름, menu[0][2] : Menu Id
			String [][] mainMenu = (String [][])mainHash.get("menu");
			
            String [][] tempSubMenu = null;
            int tempSubMenuCount = 0;

			// Main Menu의 갯수
            int mainMenuCount = mainMenu.length;
            
			// Main Menu를 구성한다.
			for (i=0; i < mainMenuCount; i++)
            {
%>            
                <li class="depth1_close b_line">
                        <a href="javaScript:viewAns(<%=i+1%>)"  onFocus="this.blur();"><%=mainMenu[i][0]%></a>
                    	<input type="hidden" name="pMenuId" value="<%=mainMenu[i][2]%>" />
                </li>
<%
				// 해당 메뉴의 sub Menu를 ArrayList에서 찾아서 셋팅한다.
               // tempSubMenu = CommonUtil.getSubMenu(menuList, mainMenu[i][2]);

				// 해당 서브 메뉴의 갯수를 계산한다.
				tempSubMenuCount = tempSubMenu.length;
%>
					<ul ID="TD_ANS" style="display:none">
<%	
						// Sub Menu를 구성한다.
					    for (int j=0; j<tempSubMenuCount; j++)
                        {
%>                        
                            <li class="depth2" >
                            	<a href="javascript:goHelpPage('<%=mainMenu[i][2]%>','<%=tempSubMenu[j][1]%>')" onFocus='this.blur();'><%=tempSubMenu[j][0]%></a>
                            </li>
<%
                        }
%>
                    </ul>
<%
            }	// end for

        }	// end if
%>
<!-- F
            <table width="167" border="0" cellspacing="0" cellpadding="2">
                <tr>
                    <td class="imenu">
                        <img src="<c:url value="/common/images/help/i_tri2.gif" />" width="15" height="10" align="absmiddle"><a href="javascript:goHelpPage('PDA','pda');"  onFocus="this.blur();">PDA</a>
                    </td>
                </tr>
                <tr>
                    <td height="1" bgcolor="#E3E3E3"></td>
                </tr>
            </table>
 -->            
<%
		// 넘겨진 Menu가 0 보다 큰경우
		if (adminMenuList != null && adminMenuList.length > 0)
        {
%>
            <li class="depth1_close b_line">
            	<a href="javaScript:viewAns(<%=i+1%>)" onFocus="this.blur();"><font color='blue'><bean:message key="helpMenu.admin"/></font></a>
                <input type="hidden" name="pMenuId" value="MANAGER" />
            </li>
            <ul ID="TD_ANS" style="display:none">
<%	
						// Sub Menu를 구성한다.
					    for (int j=0; j<adminMenuList.length; j++)
                        {
%>                      
                            <li class="depth2">
                            	<a href="javascript:goHelpPage('MANAGER','<%=adminMenuList[j][1]%>')" onFocus='this.blur();'><%=adminMenuList[j][0]%></a>
                            </li>
<%
                        }
%>
			</ul>
<%
        }	// end if
%>
<form name="bottomForm" method="post" action="" style="border:0;" ></form>
</body>
</html>
