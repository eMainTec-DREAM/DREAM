<%--===========================================================================
보전사원 - 목록
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.comp.emp.action.ConsultCompEmpListAction" %>
<%@ page import="dream.consult.comp.emp.action.ConsultCompEmpDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 보전사원 -->
<title><bean:message key='LABEL.EMP'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="consultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var myGrid;

/** 자동완성 변수 */
var compAc;
var deptAc;
var wkCtrDescAc;
var empDescAc;
var isUseAc;
function loadPage() 
{
    initGrid();
    
    // 근무여부 - 기본 Y 선택.
    consultCompEmpListForm.elements['consultCompEmpCommonDTO.filterIsJoin'].value = 'Y';
    consultCompEmpListForm.elements['consultCompEmpCommonDTO.filterIsJoinDesc'].value = 'Y';

    compAc = new autoC({"consultCompEmpCommonDTO.filterCompDesc":"description"});
    compAc.setTable("TACOMP");
    compAc.setCustomLov("lovComp('consultCompEmpCommonDTO.filterCompNo', 'consultCompEmpCommonDTO.filterCompDesc')");
    compAc.setKeyName("consultCompEmpCommonDTO.filterCompNo");
    compAc.setAcResultMap({
        "consultCompEmpCommonDTO.filterCompNo":"comp_no"
    });
    compAc.init();

    deptAc = new autoC({"consultCompEmpCommonDTO.filterDeptDesc":"description"});
    deptAc.setAcDisplay("DESCRIPTION");
    deptAc.setAcDConditionMap({
    	"comp_no":"consultCompEmpCommonDTO.filterCompNo"
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "consultCompEmpCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.init();

    acSysDesc("consultCompEmpCommonDTO.filterIsJoinDesc","consultCompEmpCommonDTO.filterIsJoin","IS_USE");
}

function initGrid()
{
    myGrid = new dhtmlXGridObject('gridbox');
    myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
    
    myGrid.enableSmartRendering(true,500);
    myGrid.attachEvent("onRowSelect",function(rowId, columnId){
        goOpen();
    });
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	consultCompEmpListForm.elements['consultCompEmpCommonDTO.empId'].value = "";
        return sortColumn("consultCompEmpList", this, consultCompEmpListForm, "empId", ind, direction);
    });
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

    setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/consultCompEmpList.do";
    consultCompEmpListForm.elements['strutsAction'].value = '<%=ConsultCompEmpListAction.EMP_LIST_FIND%>';

    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(consultCompEmpListForm), "empId", "Y");
}

function findGridRow(_empId)
{
	// 검색 요청번호와 Tab이동시 요청번호가 다르면 상세에서 입력한것이므로 조회 하지 않는다.
	//if (consultEqMstrListForm.elements['consultEqMstrCommonDTO.equipId'].value != _equipId) return;
	consultCompEmpListForm.elements['consultCompEmpCommonDTO.empId'].value = _empId;
	findGridList('ReloadRow');
	consultCompEmpListForm.elements['consultCompEmpCommonDTO.empId'].value = "";
}


/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	//================================
    // 필수 항목 체크한다.
    //================================
    if(checkValidation()) return;
	
	consultCompEmpListForm.elements['consultCompEmpCommonDTO.empId'].value = "";	// 검색시 Tab 이동Key Clear
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
   	consultCompEmpListForm.elements['consultCompEmpCommonDTO.compNo'].value = getValueById(myGrid, selectedId, 'compNo');
   	consultCompEmpListForm.elements['consultCompEmpCommonDTO.empId'].value  = getValueById(myGrid, selectedId, 'empId');

	goCommonTabPage(consultCompEmpListForm, <%= ConsultCompEmpDetailAction.EMP_DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('consultCompEmpDetail');
}

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "consultCompEmpDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	consultCompEmpListForm.elements['consultCompEmpCommonDTO.empId'].value = "";
    goCommonTabPage(consultCompEmpListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'empId'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    consultCompEmpListForm.strutsAction.value = '<%=ConsultCompEmpListAction.EMP_LIST_DELETE%>';
    var url = contextPath + "/consultCompEmpList.do";
    
    $.post(url,FormQueryString(consultCompEmpListForm)+delArray , function(_data){
        afterDelete();
    }); 
}

function afterDelete()
{
    goClose('consultCompEmpDetail', this);
    // goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	if(checkValidation()) return;
	consultCompEmpListForm.elements['consultCompEmpCommonDTO.empId'].value = "";
    excelServerAction("consultCompEmpList", consultCompEmpListForm);
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/consultCompEmpList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="consultCompEmpCommonDTO.compNo"/><!-- Key -->
<html:hidden property="consultCompEmpCommonDTO.empId"/><!-- Key -->
<html:hidden property="consultCompEmpCommonDTO.filterCompNo"/>
<html:hidden property="consultCompEmpCommonDTO.filterDeptId"/>
<html:hidden property="consultCompEmpCommonDTO.filterIsJoin"/>
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
		               <label class="check"><bean:message key="LABEL.compDesc"/></label>
	               	   <div class="input_box"">
	                        <html:text property="consultCompEmpCommonDTO.filterCompDesc" tabindex="10"/>
	                        <p class="open_spop"><a><span>조회</span></a></p>
                       </div>
               	   </div>
	               <div class="field">
		               <label><bean:message key="LABEL.empName"/></label>
	               	   <div class="input_box">
	               	   		<html:text property="consultCompEmpCommonDTO.filterEmpName" tabindex="10"/>
	               	   </div>
               	   </div>
	               <div class="field">
		               <label><bean:message key="LABEL.dept"/></label>
	               	   <div class="input_box"">
	                        <html:text property="consultCompEmpCommonDTO.filterDeptDesc" tabindex="30"/>
	                        <p class="open_spop"><a><span>조회</span></a></p>
                       </div>
               	   </div>
                   <div class="field">
                        <label><bean:message key="LABEL.isJoin"/></label>
                        <div class="input_box">
                           <html:text property="consultCompEmpCommonDTO.filterIsJoinDesc" tabindex="30"/>
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