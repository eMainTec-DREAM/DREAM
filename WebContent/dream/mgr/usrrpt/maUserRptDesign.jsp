<%--===========================================================================
메뉴 - 목록
author  kim21017
version $Id: maUserRptList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.usrrpt.action.MaUserRptDesignAction" %>
<%@ page import="dream.mgr.usrrpt.action.MaUserRptDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 메뉴 -->
<title><bean:message key='MENU.maUserRptList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="consultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

//그리드명
var myGrid,creByAc, deptAc;

function loadPage() 
{
	setFunction();
	
	setForUpdate();

    //initGrid();
	if(typeof resizeTabFrame == "function") setTimeout("resizeTabFrame()",500);
}

/**
 * 그리드 초기화
 */
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen();
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

function setFunction()
{
	/* acSysDesc("maUserRptCommonDTO.usrdataTypeDesc","maUserRptCommonDTO.usrdataType","USRDATA_TYPE", true);
	 
	creByAc = new autoC("maUserRptCommonDTO.creByDesc");
    creByAc.setTable("TAUSER");
    creByAc.setAcResultMap({
    	"maUserRptCommonDTO.creBy":"user_id"
    }); 
    creByAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    creByAc.setCustomLov("lovUser('maUserRptCommonDTO.creBy', '', 'maUserRptCommonDTO.creByDesc')");
    creByAc.setKeyName("maUserRptCommonDTO.creBy");
    creByAc.init();   */

}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maUserRptList.do";
    <%-- maUserRptListForm.elements['strutsAction'].value = '<%=MaUserRptListAction.DATA_LIST_FIND%>'; --%>
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maUserRptListForm), "usrrptlistId");

}

/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	maUserRptListForm.elements['maUserRptCommonDTO.usrrptlistId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('Search');   
}

/**
 * Tab 이동시 호출
 */
function goTabPage(pageId)
{
	tabValidationCheck(myGrid, pageId, "goTabPageAction");
}

function goTabPageAction(pageId, selectedId)
{
	maUserRptListForm.elements['maUserRptCommonDTO.usrrptlistId'].value = getValueById(myGrid, selectedId,'usrrptlistId');
	goCommonTabPage(maUserRptListForm, <%= MaUserRptDetailAction.USER_RPT_DETAIL_INIT %>, pageId);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_usrrptlistId)
{
	maUserRptListForm.elements['maUserRptCommonDTO.usrrptlistId'].value = _usrrptlistId;
	findGridList('ReloadRow');
	maUserRptListForm.elements['maUserRptCommonDTO.usrrptlistId'].value = "";
}
/**
 * 상세 열기
 */
function goOpen()
{
	goTabPage('maUserRptDetail');
}

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "maUserRptDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maUserRptListForm.elements['maUserRptCommonDTO.usrrptlistId'].value = "";
	goCommonTabPage(maUserRptListForm, '', "maUserRptDetail");
}

/**
 * Excel Export
 */
function goExcel()
{
    excelAction(myGrid);
}

/**
 * 삭제
 */
function goDelete(){
	//myGrid에 1(2번째)번에 체크가 되어있으면 2번 데이터 가져오기
	 var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'usrrptlistId'); //Grid, check box column seq, pk column seq
	//체크된게 없으면 return
	 if(typeof delArray == "undefined"){
			alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
			return;
		}
	<%-- maUserRptListForm.strutsAction.value = '<%=MaUserRptListAction.DATA_LIST_DELETE%>'; --%>
	var url = contextPath + "/maUserRptList.do";
	
	$.post(url,FormQueryString(maUserRptListForm)+delArray , function(_data){
    	afterDelete();
    });
}
  

function afterDelete(){
	goClose('maUserRptDetail');
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

function goSave()
{
	if(checkValidation()) return;
	
	//strutsAction 셋팅.
    maUserRptDesignForm.strutsAction.value = '<%=MaUserRptDesignAction.USER_RPT_SCRIPT_UPDATE%>';
    
    var actionUrl = contextPath + "/maUserRptDesign.do";
    XMLHttpPost(actionUrl, FormQueryString(maUserRptDesignForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    getTopPage().afterSaveAll(currentPageId);
    
    goUpdate();
}

function goPlay()
{
	//openLayerPopup("maUserRptPopup", FormQueryString(maUserRptDesignForm));
				
	var url   = contextPath + "/maUserRptPopup.do";
	var popWidth = 1010;
	var popHeight = 600;
    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
	//post 전송
	openWindowWithPost(url, "SCRIPT_POPUP", decodeURIComponent(FormQueryString(maUserRptDesignForm)), pos);
}


//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maUserRptDesign.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maUserRptCommonDTO.usrrptlistId"/><!-- Key -->
<html:hidden property="maUserRptCommonDTO.compNo"/>
<html:hidden property="maUserRptCommonDTO.title"/>
    <!-- searchbox 박스 Line -->

		
	 <div class="section_wrap">
	    <div class="sheader_box">
	        <div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx"><bean:message key="LABEL.List"/></div>
			</div>
			<div class="function_box list">
				<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
				</div>	
				<div class="fb_group2">
				</div>
				<div class="b_line"></div> 
				<div class="fb_group1">
					 
				</div>
			</div>
	    </div><!--sheader_box end-->
	    <div class="article_box" style="margin-top: 0px;">
            <div class="form_box" style="margin-top: 0px;">

             	
				 
             </div> <!-- End of Form_box -->
	 	</div>
	 </div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>