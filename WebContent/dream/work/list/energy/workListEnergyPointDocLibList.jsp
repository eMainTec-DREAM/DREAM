<%--===========================================================================
첨부문서 - 목록
author  sy.yang
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="common.tree.action.TreeAction" %>
<%@ page import="dream.doc.file.action.MaDocLibListAction" %>
<%@ page import="dream.doc.file.action.MaDocLibDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="common.bean.User"%>
<%
    User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html>
<head>
<!-- 첨부문서 -->
<title><bean:message key='TAB.workListEnergyPointDocLibList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
 
<script language="javascript">
<!--//

//그리드명
var myGrid;
var empAc, deptAc, docctgAc;

function loadPage() 
{
    initGrid();
    
    // 부서
    deptAc = new autoC({"maDocLibCommonDTO.regDeptDesc":"description"});
    deptAc.setTable("TADEPT");
    deptAc.setAcDConditionMap({
    	"comp_no":loginUser.compNo
    });
    deptAc.setAcResultMap({
        "maDocLibCommonDTO.regDeptId":"dept_id"
    });
    deptAc.init();
    
    // docCateg
    docctgAc = new autoC({"maDocLibCommonDTO.docCategDesc":"description"});
    docctgAc.setTable("TADOCCTG");
    docctgAc.setAcDConditionMap({
    	"comp_no":loginUser.compNo
    });
    docctgAc.setAcResultMap({
        "maDocLibCommonDTO.docCategId":"docctg_id"
    });
    docctgAc.init();

}

/**
 * 그리드 초기화
 */
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	myGrid.setDateFormat("%Y-%m-%d");

	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen();
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	maDocLibListForm.elements['maDocLibCommonDTO.docId'].value = "";
        return sortColumn("workPmiDocLibList", this, maDocLibListForm, "DOCID", ind, direction);
    });

	//Inner Page
	if(maDocLibListForm.elements['strutsAction'].value == '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>')
	{
		if($('.inner_section').length != "0") $('.hideFilter').hide();
		
		setHeader(myGrid, "gridbox"); // grid, grid id
	}
	else
	{
		setHeader(myGrid, "gridbox"); // grid, grid id
	}
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/workListEnergyPointDocLibList.do";
    maDocLibListForm.elements['strutsAction'].value = '<%=MaDocLibListAction.DOCLIB_LIST_FIND%>';
    
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maDocLibListForm), "DOCID", "Y");

}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_docId)
{
	maDocLibListForm.elements['maDocLibCommonDTO.docId'].value = _docId;
	findGridList('ReloadRow');
	maDocLibListForm.elements['maDocLibCommonDTO.docId'].value = "";
}
/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	maDocLibListForm.elements['maDocLibCommonDTO.docId'].value = "";	// 검색시 Tab 이동Key Clear
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
	maDocLibListForm.elements['maDocLibCommonDTO.docId'].value = getValueById(myGrid, selectedId,'docId');
	goCommonTabPage(maDocLibListForm, '<%=MaDocLibDetailAction.DOCLIB_DETAIL_INIT%>', pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
	goTabPage('workListEnergyPointDocLibDetail');
}
function goOpenAction()
{
	var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
	maDocLibListForm.elements['maDocLibCommonDTO.docId'].value = getValueById(myGrid, selectedId,'docId');
	maDocLibListForm.elements['strutsAction'].value = '<%=MaDocLibDetailAction.DOCLIB_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(maDocLibListForm),'workListEnergyPointDocLibDetail');
}

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "workListEnergyPointDocLibDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maDocLibListForm.elements['maDocLibCommonDTO.docId'].value = "";
	goCommonTabPage(maDocLibListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
	//myGrid에 1(2번째)번에 체크가 되어있으면 2번 데이터 가져오기
	 var delArray = getDeletRows(myGrid, 'isDelCheck', 'docId'); //Grid, check box column seq, pk column seq
	//체크된게 없으면 return
	 if(typeof delArray == "undefined"){
			alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
			return;
		}
	 
	maDocLibListForm.strutsAction.value = '<%=MaDocLibListAction.DOCLIB_LIST_DELETE%>';
	var url = contextPath + "/workListEnergyPointDocLibList.do";
	
	$.post(url,FormQueryString(maDocLibListForm)+delArray , function(_data){
    	afterDelete();
    });
}
  

function afterDelete()
{
	goClose('workListEnergyPointDocLibDetail', this);
	//goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	maDocLibListForm.elements['maDocLibCommonDTO.docId'].value = "";
	excelServerAction("workPmiDocLibList", maDocLibListForm);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workListEnergyPointDocLibList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maDocLibCommonDTO.docId"/><!-- Key -->
<html:hidden property="maDocLibCommonDTO.docCateg"/>
<html:hidden property="maDocLibCommonDTO.regDeptId"/>
<html:hidden property="maDocLibCommonDTO.regId"/>

<html:hidden property="maDocLibCommonDTO.addedDocId"/>

<html:hidden property="maDocLibCommonDTO.objectId"/>
<html:hidden property="maDocLibCommonDTO.objectType"/>
<html:hidden property="maDocLibCommonDTO.securGrade"/>
<html:hidden property="maDocLibCommonDTO.docCateg" />
<html:hidden property="maDocLibCommonDTO.description" />
    <!-- searchbox 박스 Line -->
    <div class="section_wrap hideFilter">
    	<div class="sheader_box">
	       	<div class="stitle_box"><bean:message key="LABEL.Filter"/></div>
	           <div class="function_box filter">
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
	       <div class="article_box" style="display:none;">
	           <div class="form_box">
	               <div class="field">
		               <label><bean:message key="LABEL.docDesc"/></label>
	               	   <div class="input_box">
	               	   		<html:text property="maDocLibCommonDTO.docDesc" tabindex="10"/>
	               	   </div>
               	   </div>
	               <div class="field">
					   <label><bean:message key="LABEL.docCateg"/></label>
					   <div class="input_box">
							<html:text property="maDocLibCommonDTO.docCategDesc" tabindex="30"/>
							<p class="open_spop"><a><span>조회</span></a></p>
					   </div>
				   </div>
				   <div class="field">
					   <label><bean:message key="LABEL.docDeptId"/></label>
					   <div class="input_box">
							<html:text property="maDocLibCommonDTO.regDeptDesc" tabindex="30"/>
							<p class="open_spop"><a><span>조회</span></a></p>
					   </div>
				   </div>
				   <div class="field">
					   <label><bean:message key="LABEL.regId"/></label>
					   <div class="input_box">
							<html:text property="maDocLibCommonDTO.regDesc" tabindex="30"/>
							<p class="open_spop"><a><span>조회</span></a></p>
					   </div>
				   </div>
			</div>
		</div><!--article_box end-->
	</div> <!--  end section_wrap -->
		
	 <div class="section_wrap">
	    <div class="sheader_box hideFilter">
	        <div class="stitle_box"><bean:message key="LABEL.List"/></div>
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
	    <div class="article_box" >
            <div class="grid_area">
            	<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
            </div>
	 	</div>
	 </div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>