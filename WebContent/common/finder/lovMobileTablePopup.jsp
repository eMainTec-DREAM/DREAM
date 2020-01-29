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
<%@ page import="common.finder.valid.action.ListOfValAction"%>
<%@ page import="dream.asset.list.action.LovEquipListAction"%>
<html>
<head>
<!-- SEARCH CODE -->
<title><bean:message key="MENU.SEARCHCODE"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<!-- ########## page 상단 공통 : 모든 페이지 적용 ########## --> 

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<!-- searchbox 박스 Line -->
	<html:form action="/listOfVal" >
		<html:hidden property="listOfValDTO.codeType" />
		<html:hidden property="listOfValDTO.extCode1" />
		<html:hidden property="listOfValDTO.codeKind" />
		<html:hidden property="strutsAction" />
		<div class="psrch_box">
			<html:text property="listOfValDTO.searchText"></html:text>
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
	var _codeType = $('[name="listOfValDTO.codeType"]').val();

	if(_codeType == "TAEQUIPMENT")
	{
		title = '<bean:message key="LABEL.EQUIPSRCH"/>';  //타이틀 설정
		pageId = "lovEquipList"; // LOV List 조회 pageid
		_id = "ITEMNO"; // id 칼럼이름
		_desc = "EQUIPDESC";  //desc 칼럼이름
		stAction = '<%=LovEquipListAction.LOV_EQUIP_FIND%>'; // LOV List 조회 Action
		if(typeof obj != "undefined") rtnValue = "<div class='t_title'><span>"+obj.EQUIPDESC+"</span>  "+obj.ITEMNO+"</div>"+"<div class='t_etc'>"+obj.EQLOCDESC+"</div></a></li>";
	
		$('.pop_title').text(title);
	}
	else
	{
		pageId = "listOfVal";
		stAction = '<%=ListOfValAction.LOV_TABLE_FIND%>'; 	
		if(typeof obj != "undefined") rtnValue = "<li><a>"+obj.CODE+"<p>"+obj.DESCRIPTION+"</p>"+"</a></li>";
	}
		
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

	returnArray[0] = dataObj[_id];
	returnArray[1] = dataObj[_desc];
	
	var dirType = listOfValForm.elements['listOfValDTO.codeType'].value;

    getIframeContent().setLovValue(returnArray, dirType);

    closeLayerPopup();
}

function findList(sheetAction)
{
	listOfValForm.elements['strutsAction'].value = stAction;

	doListAction("Search", myList, pageId, FormQueryString(listOfValForm));
    
}

</script>
</body>
</html>
