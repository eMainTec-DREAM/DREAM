<%--===========================================================================
설비 Popup
author  ssong
version $Id: lovEquipPopup.jsp,v 1.1 2016/02/18 09:12:01 ssong Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ page import="common.bean.User"%>
<%@ page import="mobile.dream.asset.list.action.AssetListLovListAction"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html>
<head>
<!-- 설비 -->
<title><bean:message key="LABEL.equipment"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="mobilePopupPage">
<!-- ########## page 상단 공통 : 모든 페이지 적용 ########## --> 

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<!-- searchbox 박스 Line -->
	<html:form action="/assetListLovList" >
		<c:import charEncoding="UTF-8" url="/mobile/dream/jsp/baseDTOInclude.jsp"></c:import>
		<html:hidden property="assetListLovListDTO.extCode1" />
		<html:hidden property="assetListLovListDTO.extCode2" />
		<html:hidden property="assetListLovListDTO.codeType" />
		<html:hidden property="assetListLovListDTO.eqLocId" />
		<html:hidden property="assetListLovListDTO.eqCtgId" />
		<html:hidden property="assetListLovListDTO.plfTypeId" />
		<html:hidden property="assetListLovListDTO.plfTypeDesc" />
		<html:hidden property="assetListLovListDTO.mainMngId" />
		<html:hidden property="assetListLovListDTO.subMngId" />
		<html:hidden property="assetListLovListDTO.deptId" />
		<html:hidden property="assetListLovListDTO.eqStatusId" />
		<div class="psrch_box">
			<html:text property="assetListLovListDTO.searchText"></html:text>
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
	//var _codeType = $('[name="assetListLovListDTO.codeType"]').val();

	title = '<bean:message key="LABEL.EQUIPSRCH"/>';  //타이틀 설정
	pageId = "assetListLovList"; // LOV List 조회 pageid
	_id = "ITEMNO"; // id 칼럼이름
	_desc = "EQUIPDESC";  //desc 칼럼이름
	stAction = '<%=AssetListLovListAction.LOV_ASSET_FIND%>'; // LOV List 조회 Action
	if(typeof obj != "undefined") rtnValue = "<div class='t_title'><span>"+obj.EQUIPDESC+"</span>  "+obj.ITEMNO+"</div>"+"<div class='t_etc'>"+obj.EQLOCDESC+"</div></a></li>";

	$('.pop_title').text(title);

		
}

function initList()
{
	myList = new dhtmlXList({
		container:"data_container"
		,type:{
			template:function(obj){return "<div class='t_title'><span>"+obj.EQUIPDESC+"</span>  "+obj.ITEMNO+"</div>"+"<div class='t_etc'>"+obj.EQLOCDESC+"</div></a></li>"},
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

	returnArray[0] = dataObj.EQUIPDESC;
	returnArray[1] = dataObj.ITEMNO;

	var dirType = assetListLovListForm.elements['assetListLovListDTO.codeType'].value;

    getIframeContent().setLovValue(returnArray, dirType);

    closeLayerPopup();
}

function findList(sheetAction)
{
	assetListLovListForm.elements['strutsAction'].value = stAction;

	doListAction("Search", myList, pageId, FormQueryString(assetListLovListForm));
    
}

</script>
</body>
</html>