<%--===========================================================================
단가계약설정 - 목록
author  youngjoo38
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.contract.action.MgrContractAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%> 
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 단가계약설정 -->
<title><bean:message key='PAGE.mgrContractList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;
var serviceAc;

function loadPage() 
{
    initGrid();
    
    //서비스명
    serviceAc = new autoC({"mgrContractDTO.filterServiceDesc":"serviceName"});
    serviceAc.setTable("TASERVICE");
    serviceAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    	,"is_use":"Y"
    });
    serviceAc.setAcResultMap({
        "mgrContractDTO.filterServiceId":"serviceId"
    });
    serviceAc.init();    
    
    //사용여부
    acSysDesc("mgrContractDTO.filterIsUse","mgrContractDTO.filterIsUse","IS_USE");
}
/**
 * 그리드 초기화
 */
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	myGrid.attachEvent("onXLE",function(grdObj,count){setCounter(grdObj,"gridbox")});
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen();
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		mgrContractForm.elements['mgrContractDTO.contractId'].value = "";
    	return sortColumn("mgrContractList", this, mgrContractForm, "CONTRACTID", ind, direction);
	});
	myGrid.init();
	setHeader(myGrid, "gridbox"); // grid, grid id

}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/mgrContractList.do";
    mgrContractForm.elements['strutsAction'].value = '<%=MgrContractAction.LIST_FIND%>';

    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(mgrContractForm), "CONTRACTID", "Y");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_contractId)
{
	mgrContractForm.elements['mgrContractDTO.contractId'].value = _contractId;
	findGridList('ReloadRow');
	mgrContractForm.elements['mgrContractDTO.contractId'].value = "";
}

/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	mgrContractForm.elements['mgrContractDTO.contractId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList();   
}

/**
 * Tab 이동시 호출
 */
function goTabPage(pageId)
{
	var selectedId=myGrid.getSelectedRowId();
    if(selectedId==null) return;
    
	tabValidationCheck(myGrid, pageId, "goTabPageAction");
}

function goTabPageAction(pageId, selectedId)
{
	var form = document.mgrContractForm;
	
	mgrContractForm.elements['mgrContractDTO.contractId'].value =  getValueById(myGrid, selectedId,'CONTRACTID');
    
	goCommonTabPage(form, <%= MgrContractAction.DETAIL_INIT %>, pageId);
    
}

/**
 * 상세 열기
 */
 function goOpen(){
	 goTabPage('mgrContractDetail');
}

 function goOpenAction()
 {
 	var selectedId=myGrid.getSelectedRowId();
     
     if(selectedId == null) return;
     
     mgrContractForm.elements['mgrContractDTO.contractId'].value =  getValueById(myGrid, selectedId,'CONTRACTID');
     mgrContractForm.elements['strutsAction'].value = '<%=MgrContractAction.DETAIL_INIT%>';
     openQuickTabPage(FormQueryString(mgrContractForm), 'mgrContractDetail'); 
 } 

 /**
  * 생성
  */
 function goCreate()
 {
 	createValidationCheck(myGrid, "mgrContractDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	 mgrContractForm.elements['mgrContractDTO.contractId'].value = "";
 	goCommonTabPage(mgrContractForm, '', pageId);	
 }
 
 /**
  * 삭제
  */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'CONTRACTID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	mgrContractForm.strutsAction.value = '<%=MgrContractAction.LIST_DELETE%>';
	var url = contextPath + "/mgrContractList.do";
	
	$.post(url,FormQueryString(mgrContractForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete(){
	goClose('mgrContractDetail',this);
   	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
   }
 
/**
 * 엑셀 다운.
 */
 function goExcel()
 {
	mgrContractForm.elements['mgrContractDTO.contractId'].value = "";
	excelServerAction("mgrContractList", mgrContractForm);
 }

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/mgrContractList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="mgrContractDTO.contractId"/><!-- Key -->
<html:hidden property="mgrContractDTO.filterVendorId"/>
<html:hidden property="mgrContractDTO.filterServiceId"/>

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
				<!-- 계약 # -->
				<div class="field">
					<label>계약 #</label>
					<div class="input_box">
						<html:text property="mgrContractDTO.filterContractNo" tabindex="10"/>
					</div>
				</div>
				<!-- 계약명 -->
				<div class="field">
					<label>계약명</label>
					<div class="input_box">
						<html:text property="mgrContractDTO.filterContractDesc" tabindex="20"/>
					</div>
				</div>
				<!-- 업체명 -->
				<div class="field">
					<label>업체명</label>
					<div class="input_box">
						<html:text property="mgrContractDTO.filterVendorDesc" tabindex="30"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 서비스명 -->
				<div class="field">
					<label>서비스명</label>
					<div class="input_box">
						<html:text property="mgrContractDTO.filterServiceDesc" tabindex="40"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
                <!-- 계약일자 -->
                <div class="field">
                    <label>계약일자</label>
                    <div class="calendar_wrap">
                        <div class="input_box input_carendar">
                            <html:text property="mgrContractDTO.filterContractStartDate" tabindex="50" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
                        <div class="input_box input_carendar">
                            <html:text property="mgrContractDTO.filterContractEndDate" tabindex="60" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
                    </div>
                </div>
                <!-- 계약기간시작일자 -->
                <div class="field">
                    <label>계약기간시작일자</label>
                    <div class="calendar_wrap">
                        <div class="input_box input_carendar">
                            <html:text property="mgrContractDTO.filterContractFromStartDate" tabindex="70" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
                        <div class="input_box input_carendar">
                            <html:text property="mgrContractDTO.filterContractFromEndDate" tabindex="80" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
                    </div>
                </div>
                <!-- 계약기간종료일자 -->
                <div class="field">
                    <label>계약기간종료일자</label>
                    <div class="calendar_wrap">
                        <div class="input_box input_carendar">
                            <html:text property="mgrContractDTO.filterContractToStartDate" tabindex="90" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
                        <div class="input_box input_carendar">
                            <html:text property="mgrContractDTO.filterContractToEndDate" tabindex="100" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
                    </div>
                </div>
				<!-- 사용여부 -->
				<div class="field">
					<label>사용여부</label>
					<div class="input_box">
						<html:text property="mgrContractDTO.filterIsUse" tabindex="110"/>
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