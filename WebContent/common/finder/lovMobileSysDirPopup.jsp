<%--===========================================================================
List Of Value Popup
code, description 형태로 조회되는 시스템 코드 테이블 
author  javaworker
version $Id: lovSysDirPopup.jsp,v 1.3 2014/01/28 07:49:27 pochul2423 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ page import="common.finder.valid.action.ListOfValAction"%>
<html>
<head>
<!-- SEARCH CODE -->
<title><bean:message key="MENU.SEARCHCODE"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="mobilePopupPage">
<!-- ########## page 상단 공통 : 모든 페이지 적용 ########## --> 
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<!-- searchbox 박스 Line -->
	<html:form action="/listOfVal" >
		<html:hidden property="listOfValDTO.codeType" />
		<html:hidden property="listOfValDTO.codeKind" />
		<html:hidden property="listOfValDTO.extCode1" />
		<html:hidden property="strutsAction" />

		    <div class="plist_box">
		      <ul>
		        <div id='data_container' style='width:100%;height:100px;'></div>
		      </ul>
		    </div>

	</html:form> 
<script type="text/javascript">
var myList;
function loadPage() 
{
	initList();
	
	findList("Search");
}

function initList()
{
	myList = new dhtmlXList({
		container:"data_container"
		,type:{
			template:"<li><a>#CODE#"+
   		 			 "<p>#DESCRIPTION#</p>"+
   		 			 "</a></li>",
			height:"50"
		}
	});
	
	myList.attachEvent("onItemClick", function (id, ev, html){
		var dataObj = myList.get(id);
		goConfirm(dataObj);
		return true;
	});
}

function goConfirm(dataObj)
{
	var returnArray = new Array();

	returnArray[0] = dataObj.CODE;
	returnArray[1] = dataObj.DESCRIPTION;
	
	var dirType = listOfValForm.elements['listOfValDTO.codeType'].value;

    getIframeContent().setLovValue(returnArray, dirType);

    closeLayerPopup();
}

function findList(sheetAction)
{
	listOfValForm.elements['strutsAction'].value = '<%=ListOfValAction.LOV_SYS_DIR_FIND%>'; 

	doListAction("Search", myList, "listOfVal", FormQueryString(listOfValForm));
    
}
</script>
</body>
</html>
