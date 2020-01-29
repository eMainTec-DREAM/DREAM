<%--===========================================================================
설비종류 Popup
author  ssong
version $Id: lovEqCtgPopup.jsp,v 1.1 2016/02/18 09:12:01 ssong Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ page import="mobile.dream.asset.loc.list.action.AssetLocListLovAction"%>
<html>
<head>
<!-- 설비위치 -->
<title><bean:message key="MENU.EQLOC"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<!-- ########## page 상단 공통 : 모든 페이지 적용 ########## --> 

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<!-- searchbox 박스 Line -->
	<html:form action="/assetLocListLov" >
		<c:import charEncoding="UTF-8" url="/mobile/dream/jsp/baseDTOInclude.jsp"></c:import>
		<html:hidden property="assetLocListLovDTO.codeType" />
		<html:hidden property="assetLocListLovDTO.extCode1" />

		<div class="psrch_box">
			<html:text property="assetLocListLovDTO.searchText"></html:text>
			<div class="b_psrch"><a><span>검색</span></a></div>
		</div>
		<div class="plist_box">
			<ul>
				<div id='data_container' style='width:100%;height:100px;'></div>
			</ul>
		</div>
	</html:form> 
	
<script type="text/javascript">

var rtnValue, stAction, pageId, title, _id, _desc;
function loadPage() 
{
	initList();
	
	setActionType();
	
	findList("Search");
}

function goSearch()
{
	findList("Search");
}

function setActionType(obj)
{
	var _codeType = $('[name="assetLocListLovDTO.codeType"]').val();

	pageId = "assetLocListLov";
	stAction = '<%=AssetLocListLovAction.LOV_EQLOC_FIND%>'; 	
	if(typeof obj != "undefined") rtnValue = "<li><a>"+obj.FULLDESC+"<p>"+obj.EQLOCNO+"</p>"+"</a></li>";

}

function getTemplate(obj)
{
	setActionType(obj);
	return rtnValue;
}

function initList()
{
	myList = new dhtmlXList({
		container:"data_container"
		,type:{
			template:function(obj){return getTemplate(obj)},
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

	returnArray[0] = dataObj.FULLDESC;
	returnArray[1] = dataObj.EQLOCID;
	
	var dirType = assetLocListLovForm.elements['assetLocListLovDTO.codeType'].value;

    getIframeContent().setLovValue(returnArray, dirType);

    closeLayerPopup();
}

function findList(sheetAction)
{
	assetLocListLovForm.elements['strutsAction'].value = stAction;

	doListAction("Search", myList, pageId, FormQueryString(assetLocListLovForm));
    
}

</script>
</body>
</html>