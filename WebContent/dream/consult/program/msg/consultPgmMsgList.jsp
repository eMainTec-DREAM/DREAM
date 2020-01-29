<%--===========================================================================
메시지 설정(메일,SMS) - 목록
author  youngjoo38
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.program.msg.action.ConsultPgmMsgListAction" %>
<%@ page import="dream.consult.program.msg.action.ConsultPgmMsgDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<title><bean:message key="MENU.PGMMSG"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="consultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

function loadPage() 
{
  	//메시지전송유형
	acSysDesc("consultPgmMsgCommonDTO.filterMsgObjTypeDesc","consultPgmMsgCommonDTO.filterMsgObjType","MESSAGE_OBJECT_TYPE");
	//사용여부
	acSysDesc("consultPgmMsgCommonDTO.filterIsUseDesc","consultPgmMsgCommonDTO.filterIsUse","IS_USE");
	//메일 사용여부
	acSysDesc("consultPgmMsgCommonDTO.filterIsMailUseDesc","consultPgmMsgCommonDTO.filterIsMailUse","IS_USE");
	//SMS 사용여부
	acSysDesc("consultPgmMsgCommonDTO.filterIsSmsUseDesc","consultPgmMsgCommonDTO.filterIsSmsUse","IS_USE");
    
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
     	consultPgmMsgListForm.elements['consultPgmMsgCommonDTO.msgCategId'].value = "";
         return sortColumn("consultPgmMsgList", this, consultPgmMsgListForm, "msgCategId", ind, direction);
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/consultPgmMsgList.do";
    consultPgmMsgListForm.elements['strutsAction'].value = '<%=ConsultPgmMsgListAction.FIND_LIST%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(consultPgmMsgListForm), "msgCategId", "Y");

}


/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_msgCategId)
{
	consultPgmMsgListForm.elements['consultPgmMsgCommonDTO.msgCategId'].value = _msgCategId;
	findGridList('ReloadRow');
	consultPgmMsgListForm.elements['consultPgmMsgCommonDTO.msgCategId'].value = "";
}

function goSearch()
{
	consultPgmMsgListForm.elements['consultPgmMsgCommonDTO.msgCategId'].value = "";	// 검색시 Tab 이동Key Clear
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
	consultPgmMsgListForm.elements['consultPgmMsgCommonDTO.msgCategId'].value =  getValueById(myGrid, selectedId,'msgCategId');  
	goCommonTabPage(consultPgmMsgListForm, <%= ConsultPgmMsgDetailAction.DETAIL_INIT %>, pageId);
}


/**
 * 상세 열기
 */
function goOpen()
{
	goTabPage('consultPgmMsgDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    consultPgmMsgListForm.elements['consultPgmMsgCommonDTO.msgCategId'].value =  getValueById(myGrid, selectedId,'msgCategId');  
    consultPgmMsgListForm.elements['strutsAction'].value = '<%=ConsultPgmMsgDetailAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(consultPgmMsgListForm), 'consultPgmMsgDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "consultPgmMsgDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	consultPgmMsgListForm.elements['consultPgmMsgCommonDTO.msgCategId'].value = "";
	goCommonTabPage(consultPgmMsgListForm, '', pageId);	
}

/**
 * Excel Export
 */
function goExcel()
{
	consultPgmMsgListForm.elements['consultPgmMsgCommonDTO.msgCategId'].value = "";
    excelServerAction("consultPgmMsgList",consultPgmMsgListForm);
}

/**
 * 삭제
 */
 function goDelete(){
	//myGrid에 1(2번째)번에 체크가 되어있으면 2번 데이터 가져오기
	 var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'msgCategId'); //Grid, check box column seq, pk column seq
	//체크된게 없으면 return
	 if(typeof delArray == "undefined"){
			alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
			return;
		}
	
  	consultPgmMsgListForm.strutsAction.value = '<%=ConsultPgmMsgListAction.DELETE_LIST%>';
  	var url = contextPath + "/consultPgmMsgList.do";
  	
  	$.post(url,FormQueryString(consultPgmMsgListForm)+delArray , function(_data){
    	afterDelete();
    });
  }

function afterDelete(){
	goClose('consultPgmMsgDetail');
	//goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
  }

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/consultPgmMsgList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="consultPgmMsgCommonDTO.msgCategId"/><!-- Key -->
<html:hidden property="consultPgmMsgCommonDTO.filterMsgObjType"/>
<html:hidden property="consultPgmMsgCommonDTO.filterIsMailUse"/>
<html:hidden property="consultPgmMsgCommonDTO.filterIsSmsUse"/>
<html:hidden property="consultPgmMsgCommonDTO.filterIsUse"/>
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
                    <label><bean:message key="LABEL.msgObjType"/></label>
                    <div class="input_box">
                        <html:text property="consultPgmMsgCommonDTO.filterMsgObjTypeDesc" tabindex="10" />
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>                
                <!-- 사용여부 -->
                <div class="field">
                    <label><bean:message key="LABEL.isUse"/></label>
                    <div class="input_box">
                        <html:text property="consultPgmMsgCommonDTO.filterIsUseDesc" tabindex="20" />
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>                
                <!-- 메일 사용여부 -->
                <div class="field">
                    <label><bean:message key="LABEL.isMailUse"/></label>
                    <div class="input_box">
                        <html:text property="consultPgmMsgCommonDTO.filterIsMailUseDesc" tabindex="30" />
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>                
                <!-- SMS 사용여부 -->
                <div class="field">
                    <label><bean:message key="LABEL.isSmsUse"/></label>
                    <div class="input_box">
                        <html:text property="consultPgmMsgCommonDTO.filterIsSmsUseDesc" tabindex="40" />
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
	    <div class="article_box">
            <div class="grid_area">
            	<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
            </div>
	 	</div>
	 </div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>