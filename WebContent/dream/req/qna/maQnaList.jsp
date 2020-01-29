
<%--===========================================================================
질의 - 목록
author  kim21017
version $Id: maQnaList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.req.qna.action.MaQnaListAction" %>
<%@ page import="dream.req.qna.action.MaQnaDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 제목-->
<title><bean:message key="LABEL.qnaAns"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//

var myGrid;

var deptAc;
var mainMngAc;
function loadPage() 
{
    initGrid();
    
    deptAc = new autoC({"maQnaCommonDTO.filterDeptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "maQnaCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.init();
    
    mainMngAc = new autoC({"maQnaCommonDTO.filterUserDesc":"emp_name"});
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcResultMap({
        "maQnaCommonDTO.filterUserId":"emp_id"
    });
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "maQnaCommonDTO.filterDeptId"
    });
    mainMngAc.init();
}

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,20);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen();
	});

	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	
	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maQnaList.do";
    maQnaListForm.elements['strutsAction'].value = '<%=MaQnaListAction.QNA_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maQnaListForm), "QUESTIONID", "Y");

}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	maQnaListForm.elements['maQnaCommonDTO.questionId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('Search', '<%=MaQnaListAction.QNA_LIST_FIND%>');   
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
	maQnaListForm.elements['maQnaCommonDTO.questionId'].value = getValueById(myGrid, selectedId,'QUESTIONID');
	
	goCommonTabPage(maQnaListForm, <%= MaQnaDetailAction.QNA_DETAIL_INIT %>, pageId);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_questionId)
{
	maQnaListForm.elements['maQnaCommonDTO.questionId'].value = _questionId;
	findGridList('ReloadRow');
	maQnaListForm.elements['maQnaCommonDTO.questionId'].value = "";
}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('maQnaDetail');	
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    maQnaListForm.elements['maQnaCommonDTO.questionId'].value = getValueById(myGrid, selectedId,'QUESTIONID');
    maQnaListForm.elements['strutsAction'].value = '<%=MaQnaDetailAction.QNA_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(maQnaListForm), 'maQnaDetail'); 
} 

 /**
  * 생성
  */
function goCreate()
{
	createValidationCheck(myGrid, "maQnaDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maQnaListForm.elements['maQnaCommonDTO.questionId'].value = "";
	goCommonTabPage(maQnaListForm, '', pageId);
}


/**
 * Excel Export
 */
function goExcel()
{
	maQnaListForm.elements['maQnaCommonDTO.questionId'].value = "";
	excelServerAction("maQnaList", maQnaListForm );
}

/**
 * 삭제
 */
function goDelete(){
	
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'QUESTIONID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maQnaListForm.strutsAction.value = '<%=MaQnaListAction.QNA_LIST_DELETE%>';
	var url = contextPath + "/maQnaList.do";
	
    $.post(url,FormQueryString(maQnaListForm)+delArray , function(_data){
    	afterDelete();
    });
}

  
function afterDelete(){
	goClose('maQnaDetail');
	//goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
 }
 
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = maQnaListForm.elements['maQnaCommonDTO.questionId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maQnaList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maQnaCommonDTO.questionId"/>
<html:hidden property="maQnaCommonDTO.filterUserId"/>
<html:hidden property="maQnaCommonDTO.filterDeptId"/>
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
		<div class="article_box" >
			<div class="form_box">
				<div class="field">
					<label><bean:message key="LABEL.docCntrNo"/></label>
					<div class="input_box">
						<html:text property="maQnaCommonDTO.filterQuestionNo" tabindex="10"/>
					</div>
				</div>
				<!-- 작성일자 -->
				<div class="field">
					<label><bean:message key="LABEL.repRegDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="maQnaCommonDTO.filterStartRegDate" tabindex="20" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="maQnaCommonDTO.filterEndRegDate" tabindex="30" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.title"/></label>
					<div class="input_box">
						<html:text property="maQnaCommonDTO.filterQuestionTitle" tabindex="40"/>
					</div>
				</div>
				<!-- 작성자 -->
				<div class="field">
					<label><bean:message key="LABEL.updBy"/></label>
					<div class="input_box">
						<html:text property="maQnaCommonDTO.filterUserDesc" tabindex="50"
									onkeydown="validationKeyDown('maQnaCommonDTO.filterUserDesc', 'maQnaCommonDTO.filterUserId');"/>
						<p class="open_spop"><a href="javascript:lovUser('maQnaCommonDTO.filterUserId', '', 'maQnaCommonDTO.filterUserDesc');"><span>조회</span></a></p>
					</div>
				</div>
				<!-- 부서 -->
				<div class="field">
					<label><bean:message key="LABEL.dept"/></label>
					<div class="input_box">
						<html:text property="maQnaCommonDTO.filterDeptDesc" tabindex="60"
									onkeydown="validationKeyDown('maQnaCommonDTO.filterDeptDesc', 'maQnaCommonDTO.filterDeptId');"/>
						<p class="open_spop"><a href="javascript:lovDept('maQnaCommonDTO.filterDeptId', '', 'maQnaCommonDTO.filterDeptDesc');"><span>조회</span></a></p>
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
		<div class="article_box" >
			<div class="grid_area">
				<div id="gridbox" style="height:270px; background-color:white;"></div>		
			</div>			
		</div>
	</div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>

