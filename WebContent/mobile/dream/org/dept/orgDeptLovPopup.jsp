<%--===========================================================================
List Of Value Popup
code, description 형태로 조회되는 T4LISTS, T4DIR_DTL 을 제외한 테이블은 
이곳에서 사용한다.
author  javaworker
version $Id: lovTablePopup.jsp,v 1.1 2013/08/30 09:12:01 javaworker Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ page import=" mobile.dream.org.dept.action.OrgDeptLovListAction"%>
<html>
<head>
<!-- 부서 -->
<title><bean:message key="MENU.DEPT"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<!-- ########## page 상단 공통 : 모든 페이지 적용 ########## --> 

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<!-- searchbox 박스 Line -->
	<html:form action="/orgDeptLovList" >
	<c:import charEncoding="UTF-8" url="/mobile/dream/jsp/baseDTOInclude.jsp"></c:import>
		<html:hidden property="orgDeptLovListDTO.codeType" />
		<html:hidden property="orgDeptLovListDTO.extCode1" />

		<div class="psrch_box">
			<html:text property="orgDeptLovListDTO.searchText"></html:text>
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
	var _codeType = $('[name="orgDeptLovListDTO.codeType"]').val();

	pageId = "orgDeptLovList";
	stAction = '<%=OrgDeptLovListAction.LOV_DEPT_FIND%>'; 	
	if(typeof obj != "undefined") rtnValue = "<li><a>"+obj.DEPTDESC+"<p>"+obj.DEPTNO+"</p>"+"</a></li>";

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

	returnArray[0] = dataObj.DEPTDESC;
	returnArray[1] = dataObj.DEPTID;
	
	var dirType = orgDeptLovListForm.elements['orgDeptLovListDTO.codeType'].value;

    getIframeContent().setLovValue(returnArray, dirType);

    closeLayerPopup();
}

function findList(sheetAction)
{
	orgDeptLovListForm.elements['strutsAction'].value = stAction;

	doListAction("Search", myList, pageId, FormQueryString(orgDeptLovListForm));
    
}

</script>
</body>
</html>
