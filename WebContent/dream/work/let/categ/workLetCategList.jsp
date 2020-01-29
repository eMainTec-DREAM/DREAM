 <%--===========================================================================
안전작업유형 list page
author  euna0207
version $Id: workLetCategList.jsp,v 1.1 2015/12/03 01:45:27 euna0207 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.let.categ.action.WorkLetCategListAction" %>
<%@ page import="dream.work.let.categ.action.WorkLetCategDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 안전작업유형 -->
<title><bean:message key='MENU.woLetCtg'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

//그리드명
var myGrid;

//페이지 로드
function loadPage() 
{
    initGrid();

    //안전작업유형 필드 자동완성
    acSysDesc("workLetCategCommonDTO.filterWoLetCtgTypeDesc","workLetCategCommonDTO.filterWoLetCtgTypeId","WOLETCTG_TYPE");
    
}

//리스트에 적용할 그리드 
function initGrid()
{ 

	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect", function(rowId, columnId){
		goOpen();
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	workLetCategListForm.elements['workLetCategCommonDTO.filterWoLetCtgTypeId'].value = "";
    	return sortColumn("workLetCategList", this, workLetCategListForm, "woLetCtgId", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
	myGrid.init();
	setHeader(myGrid, "gridbox"); // grid, grid id
}


function goSearch()
{
	workLetCategListForm.elements['workLetCategCommonDTO.woLetCtgId'].value = "";
	findGridList('Search');
}

function findGridList(sheetAction)
{
    var url = contextPath + "/workLetCategList.do";
    workLetCategListForm.elements['strutsAction'].value = '<%=WorkLetCategListAction.LIST_FIND%>';

    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workLetCategListForm), "woLetCtgId","Y");

}


//기본적 list.jsp 페이지 셋팅 end



/** 
 * 수정된 그리드 1건을 다시 조회한다. 얘는 어디서 event로 동작? detail의 afterSave에서
 */
function findGridRow(_woLetCtgId)
{
	workLetCategListForm.elements['workLetCategCommonDTO.woLetCtgId'].value = _woLetCtgId;
	findGridList('ReloadRow');
	workLetCategListForm.elements['workLetCategCommonDTO.woLetCtgId'].value = "";
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('workLetCategDetail');
}

/* goOpenAction() */

/**
 * Tab 이동시 호출
 */
function goTabPage(pageId)
{	
	
	tabValidationCheck(myGrid, pageId, "goTabPageAction");  
}

function goTabPageAction(pageId, selectedId)
{																				//select된 row의 hidden되어있는 key값
	workLetCategListForm.elements['workLetCategCommonDTO.woLetCtgId'].value =  getValueById(myGrid, selectedId,'woLetCtgId'); 
	
	goCommonTabPage(workLetCategListForm, <%= WorkLetCategDetailAction.DETAIL_INIT %>, pageId);
}



/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "workLetCategDetail", "goCreateAction");
}

function goCreateAction(pageId)
{
	workLetCategListForm.elements['workLetCategCommonDTO.woLetCtgId'].value = "";
	
    goCommonTabPage(workLetCategListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{										
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'woLetCtgId'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
   
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
  
    workLetCategListForm.strutsAction.value = '<%=WorkLetCategListAction.LIST_DELETE%>';

    var url = contextPath + "/workLetCategList.do";
    
 
    $.post(url,FormQueryString(workLetCategListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
	
    goClose('workLetCategDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	workLetCategListForm.elements['workLetCategCommonDTO.woLetCtgId'].value = "";
	excelServerAction("workLetCategList", workLetCategListForm );  
}

//-->
</script>


</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workLetCategList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/> <!-- 파일명과 같다 -->
<html:hidden property="workLetCategCommonDTO.woLetCtgId"/><!-- Key -->
<html:hidden property="workLetCategCommonDTO.filterWoLetCtgTypeId"/>

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
				<!-- 제목 -->
				<div class="field">
					<label><bean:message key="LABEL.docDesc"/></label>
					<div class="input_box">
						<html:text property="workLetCategCommonDTO.filterDes" tabindex="10"/>
					</div>
				</div>
				<!-- 안전작업유형 -->
				<div class="field">
					<label><bean:message key="LABEL.woLetCtgType"/></label>
					<div class="input_box">
						<html:text property="workLetCategCommonDTO.filterWoLetCtgTypeDesc" tabindex="20"/>
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