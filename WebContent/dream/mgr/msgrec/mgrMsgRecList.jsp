<%--===========================================================================
메시지 수신설정
author  youngjoo38 
version $Id:$ 
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.msgrec.action.MgrMsgRecListAction" %>
<%@ page import="dream.mgr.msgrec.action.MgrMsgRecDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 메시지 수신설정 -->
<title><bean:message key='MENU.MSGREC'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;
var msgCategAc;

function loadPage() 
{
	//메시지전송유형
    msgCategAc = new autoC({"mgrMsgRecCommonDTO.filterMsgObjTypeDesc":"msgObjTypeDesc"});
    msgCategAc.setTable("TAMESSAGECATEG");
    msgCategAc.setAcResultMap({
        "mgrMsgRecCommonDTO.filterMsgObjType":"msgObjType"
    });
    msgCategAc.init();	
	
	//사용여부
	acSysDesc("mgrMsgRecCommonDTO.filterIsUseDesc","mgrMsgRecCommonDTO.filterIsUse","IS_USE");
	//메일 사용여부
	acSysDesc("mgrMsgRecCommonDTO.filterIsMailUseDesc","mgrMsgRecCommonDTO.filterIsMailUse","IS_USE");
	//SMS 사용여부
	acSysDesc("mgrMsgRecCommonDTO.filterIsSmsUseDesc","mgrMsgRecCommonDTO.filterIsSmsUse","IS_USE");

	initGrid();
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
     myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
     	mgrMsgRecListForm.elements['mgrMsgRecCommonDTO.msgCompSetId'].value = "";
         return sortColumn("mgrMsgRecList", this, mgrMsgRecListForm, "msgCompSetId", ind, direction);
    });
 	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox");}); 
 	myGrid.init();

 	setHeader(myGrid, "gridbox", "goSearch"); // grid, grid id
 }

function goSearch()
{
    if(checkValidation()) return;
    mgrMsgRecListForm.elements['mgrMsgRecCommonDTO.msgCompSetId'].value = "";  // 검색시 Tab 이동Key Clear
    findGridList('Search');
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/mgrMsgRecList.do";

    mgrMsgRecListForm.elements['strutsAction'].value = '<%=MgrMsgRecListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(mgrMsgRecListForm), "MSGCOMPSETId", "Y");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_msgCompSetId)
{
    mgrMsgRecListForm.elements['mgrMsgRecCommonDTO.msgCompSetId'].value = _msgCompSetId;
    findGridList('ReloadRow');
    mgrMsgRecListForm.elements['mgrMsgRecCommonDTO.msgCompSetId'].value = "";
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('mgrMsgRecDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    mgrMsgRecListForm.elements['mgrMsgRecCommonDTO.msgCompSetId'].value =  getValueById(myGrid, selectedId,'MSGCOMPSETID');  
    mgrMsgRecListForm.elements['strutsAction'].value = '<%=MgrMsgRecDetailAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(mgrMsgRecListForm), 'mgrMsgRecDetail'); 
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
    mgrMsgRecListForm.elements['mgrMsgRecCommonDTO.msgCompSetId'].value =  getValueById(myGrid, selectedId,'MSGCOMPSETId');  
    
    goCommonTabPage(mgrMsgRecListForm, <%= MgrMsgRecDetailAction.DETAIL_INIT %>, pageId);
}

/**
 * Excel Export
 */
function goExcel()
{
	mgrMsgRecListForm.elements['mgrMsgRecCommonDTO.msgCompSetId'].value = "";	
	excelServerAction("mgrMsgRecList", mgrMsgRecListForm );
}

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "mgrMsgRecDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	mgrMsgRecListForm.elements['mgrMsgRecCommonDTO.msgCompSetId'].value = "";
	goCommonTabPage(mgrMsgRecListForm, '', pageId);	
}

/**
 * 삭제
 */
 function goDelete(){
	//myGrid에 1(2번째)번에 체크가 되어있으면 2번 데이터 가져오기
	 var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'msgCompSetId'); //Grid, check box column seq, pk column seq
	//체크된게 없으면 return
	 if(typeof delArray == "undefined"){
			alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
			return;
		}
	
  	mgrMsgRecListForm.strutsAction.value = '<%=MgrMsgRecListAction.LIST_DELETE%>';
  	var url = contextPath + "/mgrMsgRecList.do";
  	
  	$.post(url,FormQueryString(mgrMsgRecListForm)+delArray , function(_data){
    	afterDelete();
    });
}

function afterDelete(){
	goClose('mgrMsgRecDetail');
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}


//-->

</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/mgrMsgRecList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="mgrMsgRecCommonDTO.msgCompSetId"/><!-- Key -->
<html:hidden property="mgrMsgRecCommonDTO.filterIsMailUse"/>
<html:hidden property="mgrMsgRecCommonDTO.filterIsSmsUse"/>
<html:hidden property="mgrMsgRecCommonDTO.filterIsUse"/>
<html:hidden property="mgrMsgRecCommonDTO.filterMsgObjType"/>

    <!-- searchbox 박스 Line -->
    <div class="section_wrap">
        <div class="sheader_box">
            <div class="sheader_wrap"><a></a></div>
            <div class="stitle_wrap">
                <div class="stitle_icon"><a></a></div>
                <div class="stitle_tx"><bean:message key="LABEL.Filter"/></div>
            </div>
            <div class="function_box filter">
                <div class="fb_group3">
                    <div class="sfb_wrap" style="display:none;">
                    </div>
                </div>  
                <div class="fb_group2">
                </div>
            </div>
        </div><!--sheader_box end-->
        <div class="article_box">
            <div class="form_box">
                <!-- 메시지 전송유형 -->
                <div class="field">
                    <label><bean:message key="LABEL.msgObjTypeDesc"/></label>
                    <div class="input_box">
                        <html:text property="mgrMsgRecCommonDTO.filterMsgObjTypeDesc" tabindex="10" />
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>                
                <!-- 사용여부 -->
                <div class="field">
                    <label><bean:message key="LABEL.isUse"/></label>
                    <div class="input_box">
                        <html:text property="mgrMsgRecCommonDTO.filterIsUseDesc" tabindex="20" />
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>                
                <!-- 메일 사용여부 -->
                <div class="field">
                    <label><bean:message key="LABEL.isMailUse"/></label>
                    <div class="input_box">
                        <html:text property="mgrMsgRecCommonDTO.filterIsMailUseDesc" tabindex="30" />
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>                
                <!-- SMS 사용여부 -->
                <div class="field">
                    <label><bean:message key="LABEL.isSmsUse"/></label>
                    <div class="input_box">
                        <html:text property="mgrMsgRecCommonDTO.filterIsSmsUseDesc" tabindex="40" />
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>                
            </div>
        </div><!--article_box end-->
    </div> <!--  end section_wrap -->
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
            </div>
        </div><!--sheader_box end-->
        <div class="article_box" id="listBox">
            <div class="grid_area">
                <div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
            </div>
        </div>
    </div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>

