<%--===========================================================================
보전부서 - 목록
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<!-- action 맵핑 -->
<%@ page import="dream.consult.comp.dept.action.ConsultCompDeptListAction" %>
<%@ page import="dream.consult.comp.dept.action.ConsultCompDeptDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 부서 -->
<title><bean:message key='MENU.DEPT'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="consultPage">
<!-- 공통메뉴 -->

<script language="javascript">
<!--//

var myGrid;
var selectedInd;

/** 자동완성 변수 */
var compAc;
function loadPage() 
{
    initGrid();
    // 사용여부 - 기본 선택.
    consultCompDeptListForm.elements['consultCompDeptCommonDTO.filterIsUse'].value = "Y";
    consultCompDeptListForm.elements['consultCompDeptCommonDTO.filterIsUseDesc'].value = "Y";
    //목록에서 검색하는 부분.
    /*
    ===========pop up 부분
    1.먼저 deptAC라는 지역변수 선언하고 autoC로 초기화 시켜준다 대신에 어떤 변수에 어떤 값을 넣을 건지.
    2.setAcDisplay 화면에 보여주는 역할을 하는 것 같음. 어떤 칼럼인지 
    3. setAcConditionMap 해석하면 조건 맵인데 어떤 조건이냐 하면 comp_no인데 회원번호를 넘겨 주는 것 같음
    4. setTable 어떤 테이블에 가서 조회를 할건지 
    5. 위에 1~4번을 셋팅을 해주고 그후에 init를 통해서 전체의 틀을 생성.
     */
     
    compAc = new autoC({"consultCompDeptCommonDTO.filterCompDesc":"description"});
    compAc.setTable("TACOMP");
    compAc.setAcDisplay("description")
    compAc.setKeyName("consultCompDeptCommonDTO.filterCompNo");
    compAc.setAcResultMap({
        "consultCompDeptCommonDTO.filterCompNo":"COMP_NO"
    });
    compAc.init();
   
    
    acSysDesc("consultCompDeptCommonDTO.filterIsUseDesc","consultCompDeptCommonDTO.filterIsUse","IS_USE",true);
    
    acSysDesc("consultCompDeptCommonDTO.filterIsMaintDesc","consultCompDeptCommonDTO.filterIsMaint","IS_USE");
    
}

function initGrid()
{
    myGrid = new dhtmlXGridObject('gridbox');
    myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
    
    myGrid.attachEvent("onRowSelect",function(rowId, columnId){
        goOpen();
        selectedId = rowId;
		selectedInd = columnId;
    });
    myGrid.enableTreeGridLines();
	myGrid.setImageSize(1,1);
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
    myGrid.init();
    setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/consultCompDeptList.do";
    consultCompDeptListForm.elements['strutsAction'].value = '<%=ConsultCompDeptListAction.DEPT_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(consultCompDeptListForm),"DEPTID",false,"pdeptId");
    
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_deptId)
{
	//findGridList에서 변수로 filterCompNo, deptId를 받으므로 Row에서도 변수의 수를 맵핑.
    consultCompDeptListForm.elements['consultCompDeptCommonDTO.deptId'].value = _deptId;
    //consultCompDeptListForm.elements['consultCompDeptCommonDTO.filterCompNo'].value = _filterCompNo;
    findGridList('ReloadTreeRow');
    consultCompDeptListForm.elements['consultCompDeptCommonDTO.deptId'].value = "";
    //consultCompDeptListForm.elements['consultCompDeptCommonDTO.filterCompNo'].value = "";
    
} 


/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	//필수사항 입력 안하면 검색 불가능
	if(checkValidation()) return;
	consultCompDeptListForm.elements['consultCompDeptCommonDTO.deptId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('SearchTree');   
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
	//이 값들이 detailAction에 값으로 넘어간다.
   	consultCompDeptListForm.elements['consultCompDeptCommonDTO.filterCompNo'].value = getValueById(myGrid, selectedId, 'COMPNO');
   	consultCompDeptListForm.elements['consultCompDeptCommonDTO.deptId'].value = getValueById(myGrid, selectedId, 'DEPTID');
    
   	goCommonTabPage(consultCompDeptListForm, <%=ConsultCompDeptDetailAction.DEPT_DETAIL_INIT %>, pageId);
}


/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('consultCompDeptDetail');
}
/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "consultCompDeptDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	goCommonTabPage(consultCompDeptListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck','compNo','deptId'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    consultCompDeptListForm.strutsAction.value = '<%=ConsultCompDeptListAction.DEPT_LIST_DELETE%>';
    var url = contextPath + "/consultCompDeptList.do";
    
    $.post(url,FormQueryString(consultCompDeptListForm)+delArray , function(_data){
        afterDelete();
    });
}


function afterDelete()
{
    goClose('consultCompDeptDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	if(checkValidation()) return;
	consultCompDeptListForm.elements['consultCompDeptCommonDTO.deptId'].value = "";
    excelServerAction("consultCompDeptList",consultCompDeptListForm);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/consultCompDeptList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="consultCompDeptCommonDTO.filterCompNo"/><!-- Key -->
<html:hidden property="consultCompDeptCommonDTO.deptId"/><!-- Key -->
<html:hidden property="consultCompDeptCommonDTO.filterIsUse"/>
<html:hidden property="consultCompDeptCommonDTO.filterIsMaint"/>

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
	           <div class="field">
                       <label class="check"><bean:message key="LABEL.compDesc"/></label>
                       <!--label class 에 check를 넣으면 회사명* << * 이 붙음 . -->
                       <div class="input_box">
                        <html:text property="consultCompDeptCommonDTO.filterCompDesc" tabindex="10"/>
                            <p class="open_spop">
                                <a><span>조회</span></a>
                            </p>
                       </div>
                   </div>
	           <div class="field">
		               <label><bean:message key="LABEL.deptDesc"/></label>
		               <div class="input_box">
		               		<html:text property="consultCompDeptCommonDTO.filterDescription" tabindex="20"/>
		               </div>
	               </div>
	               <div class="field">
		               <label><bean:message key="LABEL.deptNo"/></label>
                       <div class="input_box">
                            <html:text property="consultCompDeptCommonDTO.filterDeptNo" tabindex="30"/>
                       </div>
               	   </div>
               	   <div class="field">
                        <label><bean:message key="LABEL.isUse"/></label>
                        <div class="input_box">
                           <html:text property="consultCompDeptCommonDTO.filterIsUseDesc" tabindex="40"/>
                            <p class="open_spop">
                                <a>
                                    <span>조회</span>
                                </a>
                            </p>
                      </div>
                   </div> 
                   <div class="field">
                        <label><bean:message key="LABEL.maintDept"/></label>
                        <div class="input_box">
                           <html:text property="consultCompDeptCommonDTO.filterIsMaintDesc" tabindex="50"/>
                            <p class="open_spop">
                                <a>
                                    <span>조회</span>
                                </a>
                            </p>
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