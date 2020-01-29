<%--===========================================================================
자격증분류 - 목록
author  ssong
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.edu.list.action.EduListAction" %>
<%@ page import="dream.edu.list.action.EduDetailAction" %>
<%@ page import="common.util.CommonUtil"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="common.bean.User"%>
<%
    User loginUser = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html>
<head>
<!-- 자격증분류 -->
<title><bean:message key='MENU.CERTLIST'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var myGrid;

function loadPage() 
{

    
    initGrid();

    //acSysDesc("eduCommonDTO.filterEduTypeDesc","eduCommonDTO.filterEduType","COURSE_TYPE", false);
	
    var courseTypeAc = new autoC({"eduCommonDTO.filterEduTypeDesc":"description"});
    courseTypeAc.setTable("TACDUSRD");
    courseTypeAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    	,"is_use":"Y"
    	,"dir_type":"COURSE_TYPE"
  	   });
    courseTypeAc.setAcResultMap({
        "eduCommonDTO.filterEduType":"cdusrd_no"
    });
    courseTypeAc.init();
   
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
    	eduListForm.elements['eduCommonDTO.courseListId'].value = "";
    	return sortColumn("eduList", this, eduListForm, "COURSELISTID", ind, direction);
	});
    
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/eduList.do";

    eduListForm.elements['strutsAction'].value = '<%=EduListAction.EDU_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(eduListForm), "COURSELISTID","Y");
    
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_courseListId)
{
	eduListForm.elements['eduCommonDTO.courseListId'].value = _courseListId;
	findGridList('ReloadRow');
	eduListForm.elements['eduCommonDTO.courseListId'].value = "";
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	eduListForm.elements['eduCommonDTO.courseListId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('Search', '<%=EduListAction.EDU_LIST_FIND%>');   
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
	var form = document.eduListForm;
	 
	form.elements['eduCommonDTO.courseListId'].value = getValueById(myGrid, selectedId, 'courseListId');
	goCommonTabPage(form, <%= EduDetailAction.EDU_DETAIL_INIT %>, pageId);
}

/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('eduDetail');	
}
 
function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    eduListForm.elements['eduCommonDTO.courseListId'].value = getValueById(myGrid, selectedId, 'courseListId');
    eduListForm.elements['strutsAction'].value = '<%=EduDetailAction.EDU_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(eduListForm), 'eduDetail'); 
} 
 
/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "eduDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	eduListForm.elements['eduCommonDTO.courseListId'].value = "";
	goCommonTabPage(eduListForm, '', pageId);
}

/**
  * 삭제
  */
function goDelete()
{
	// 삭제 시작
	var delArray = getDeletRows(myGrid, 'isDelCheck', 'courseListId'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	
	eduListForm.strutsAction.value = '<%=EduListAction.EDU_LIST_DELETE%>';
	var url = contextPath + "/eduList.do";
	$.post(url,FormQueryString(eduListForm)+delArray , function(_data){
    	afterDelete();
    });
}

function afterDelete()
{
	goClose('eduDetail');
	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
    //excelAction(myGrid);
	eduListForm.elements['eduCommonDTO.courseListId'].value = "";
	excelServerAction("eduList", eduListForm ); 
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/eduList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="eduCommonDTO.courseListId"/><!-- Key -->
<html:hidden property="eduCommonDTO.filterEduType"/><!-- Key -->
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
					<label><bean:message key="LABEL.trainDesc"/></label>
					<div class="input_box">
						<html:text property="eduCommonDTO.filterEduName" tabindex="50"/>
					</div>
				</div>
				<!-- 교육과정분류  -->
				<div class="field">
					<label><bean:message key="LABEL.courseType"/></label>
					<div class="input_box">
						<html:text property="eduCommonDTO.filterEduTypeDesc" tabindex="70" />
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