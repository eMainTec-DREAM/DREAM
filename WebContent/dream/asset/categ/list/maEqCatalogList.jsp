<%--===========================================================================
설비종류 - 목록
author  kim21017
version $Id: maEqCatalogList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.categ.list.action.MaEqCatalogListAction" %>
<%@ page import="dream.asset.categ.list.action.MaEqCatalogDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 설비종류 -->
<title><bean:message key='MENU.EQCTG'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var myGrid;
var selectedEqId, selectedEqInd;
var eqCtgTypeAc;
var eqCtgAc;
var eqCtgDescAc;
function loadPage() 
{
    initGrid();
    
    eqCtgAc = new autoC({"maEqCatalogCommonDTO.filterPeqCtgDesc":"full_desc"});
    eqCtgAc.setTable("TAEQCTG");
    eqCtgAc.setAcResultMap({
        "maEqCatalogCommonDTO.filterPeqCtgId":"eqctg_id"
    });
    eqCtgAc.init();
    
    eqCtgDescAc = new autoC({"maEqCatalogCommonDTO.filterEqCtgDesc":"full_desc"});
    eqCtgDescAc.setTable("TAEQCTG");
    eqCtgDescAc.init();
    
    acSysDesc("maEqCatalogCommonDTO.eqTypeDesc","maEqCatalogCommonDTO.eqTypeId","EQCTG_TYPE");
}

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	myGrid.setImageSize(1,1);
	myGrid.enableTreeGridLines();
	myGrid.attachEvent("onXLE",function(grdObj,count){setCounter(grdObj,"gridbox")});
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		selectedEqId = rowId;
		selectedEqInd = columnId;
		goOpen();
	});
	myGrid.init();
	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 엑셀 다운.
 */
 function goExcel()
 {
	/* myGrid.expandAll(); //펼치기
	setTimeout("excelAction(myGrid);", 100);
 	//excelAction(myGrid);
 	setTimeout("myGrid.collapseAll();", 300);
 	//myGrid.collapseAll() */
 	
 	var form = document.maEqCatalogListForm;   
 	
 	form.elements['maEqCatalogCommonDTO.eqCtgId'].value = "";
	form.elements['maEqCatalogCommonDTO.detailPctgId'].value = "";
	form.elements['maEqCatalogCommonDTO.detailPctgDesc'].value = "";

	excelServerAction("maEqCatalogList", form );  
 }
 
/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maEqCatalogList.do";
    maEqCatalogListForm.elements['strutsAction'].value = '<%=MaEqCatalogListAction.EQ_CATALOG_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maEqCatalogListForm), "EQCTGID" ,"" , "PEQCTGID");
    
    //myGrid.setNumberFormat("0,000.00",getIndexById(myGrid, "pvalue"),".",",");
    //myGrid.setNumberFormat("0,000.00",getCoumnIdx(myGrid,"pvalue"),".",",");
    for(var i = 0; myGrid.getColumnsNum() > i; i++)
		{
			//console.log(i+"    "+grdObj.getColType(i));
			if(myGrid.getColType(i) == "ron")myGrid.setNumberFormat("0,000.00",i,".",",");
		}
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_eqCtgId)
{
	maEqCatalogListForm.elements['maEqCatalogCommonDTO.eqCtgId'].value = _eqCtgId;
	findGridList('ReloadTreeRow');
	maEqCatalogListForm.elements['maEqCatalogCommonDTO.eqCtgId'].value = "";
}

function afterSearch()
{

	myGrid.expandAll(); //펼치기
 	setTimeout("myGrid.collapseAll();//접기", 100);
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	maEqCatalogListForm.elements['maEqCatalogCommonDTO.eqCtgId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('SearchTree');   
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
	var form = document.maEqCatalogListForm;
	 
	form.elements['maEqCatalogCommonDTO.eqCtgId'].value = getValueById(myGrid, selectedId,'EQCTGID');
	form.elements['maEqCatalogCommonDTO.detailPctgId'].value = getValueById(myGrid, selectedId,'EQCTGID');
	form.elements['maEqCatalogCommonDTO.detailPctgDesc'].value = getValueById(myGrid, selectedId,'EQCTGDESC');
	goCommonTabPage(form, <%= MaEqCatalogDetailAction.EQ_CATALOG_DETAIL_INIT %>, pageId);
}

/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('maEqCatalogDetail');	
}

 function goOpenAction()
 {
     var selectedId=myGrid.getSelectedRowId();
     if(selectedId == null) return;
     
     var form = document.maEqCatalogListForm;
     
     form.elements['maEqCatalogCommonDTO.eqCtgId'].value = getValueById(myGrid, selectedId,'EQCTGID');
 	form.elements['maEqCatalogCommonDTO.detailPctgId'].value = getValueById(myGrid, selectedId,'EQCTGID');
 	form.elements['maEqCatalogCommonDTO.detailPctgDesc'].value = getValueById(myGrid, selectedId,'EQCTGDESC');
 	form.elements['strutsAction'].value = '<%=MaEqCatalogDetailAction.EQ_CATALOG_DETAIL_INIT%>';
     openQuickTabPage(FormQueryString(form), 'maEqCatalogDetail'); 
 } 
 
  /**
   * 생성
   */
 function goCreate()
 {
 	createValidationCheck(myGrid, "maEqCatalogDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	maEqCatalogListForm.elements['maEqCatalogCommonDTO.eqCtgId'].value = "";
	goCommonTabPage(maEqCatalogListForm, '', pageId);
 }

 /**
   * 삭제
   */
	function goDelete(){
		var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'EQCTGID'); //Grid, check box column seq, pk column seq
		if(typeof delArray == "undefined"){
			alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
			return;
		}
		
		maEqCatalogListForm.strutsAction.value = '<%=MaEqCatalogListAction.EQ_CATALOG_LIST_DELETE%>';
		var url = contextPath + "/maEqCatalogList.do";
		$.post(url,FormQueryString(maEqCatalogListForm)+delArray , function(_data){
	    	afterDelete();
	    });
	}
 
	function afterDelete(){
		goClose('maEqCatalogDetail');
    	//goSearch();
    	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
    }
  
	/**
	 *  Audit trail
	 */
	 function goAudtrailLink()
	 {
	    var objectId = maEqCatalogListForm.elements['maEqCatalogCommonDTO.eqCtgId'].value;
	    var fileName = currentPageId;

	    if(typeof objectId=="undefined") return;

	    goAudTrailList(objectId, fileName);
	 }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maEqCatalogList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maEqCatalogCommonDTO.eqCtgId"/><!-- Key -->
<html:hidden property="maEqCatalogCommonDTO.eqTypeId"/><!-- Key -->
<html:hidden property="maEqCatalogCommonDTO.detailPctgId"/>
<html:hidden property="maEqCatalogCommonDTO.detailPctgDesc"/>
<html:hidden property="maEqCatalogCommonDTO.filterPeqCtgId"/>
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
					<label><bean:message key="LABEL.ctgName"/></label>
					<div class="input_box">
						<html:text property="maEqCatalogCommonDTO.filterEqCtgDesc" tabindex="10"/>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.pCtg"/></label>
					<div class="input_box">
						<html:text property="maEqCatalogCommonDTO.filterPeqCtgDesc" tabindex="20"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<div class="field">
				<label><bean:message key="LABEL.eqType"/></label>
				<div class="input_box">
						<html:text property="maEqCatalogCommonDTO.eqTypeDesc" tabindex="20"/>
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