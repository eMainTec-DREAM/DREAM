<%--===========================================================================
설비위치 - 목록
author  kim21017
version $Id: maEqLocList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.loc.list.action.MaEqLocListAction" %>
<%@ page import="dream.asset.loc.list.action.MaEqLocDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 설비위치 -->
<title><bean:message key='MENU.EQLOC'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;
var selectedEqId, selectedEqInd;

var levelTypeAc;
var eqLocAc;
var locDescAc;
function loadPage() 
{
    initGrid();

    acSysDesc("maEqLocCommonDTO.filterLevelTypeDesc","maEqLocCommonDTO.filterLevelType","EQLOC_LVL_TYPE");
    
    eqLocAc = new autoC({"maEqLocCommonDTO.filterPEqLocDesc":"full_desc"});
    eqLocAc.setTable("TAEQLOC");
    eqLocAc.setAcConditionMap({
  	   "comp_no":loginUser.compNo
  	   });
    eqLocAc.setAcResultMap({
        "maEqLocCommonDTO.filterPEqLocId":"eqloc_id"
    });
    eqLocAc.init();
    
    locDescAc = new autoC({"maEqLocCommonDTO.filterEqLocDesc":"description"});
    locDescAc.setTable("TAEQLOC");
    locDescAc.setAcConditionMap({
   	   "comp_no":loginUser.compNo
   	   });
    locDescAc.init();
    
}
/**
 * 그리드 초기화
 */
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	myGrid.enableTreeGridLines();
	myGrid.setImageSize(1,1);
	myGrid.attachEvent("onXLE",function(grdObj,count){setCounter(grdObj,"gridbox")});
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		selectedEqId = rowId;
		selectedEqInd = columnId;
		goOpen();
	});
	 myGrid.init();
	setHeader(myGrid, "gridbox"); // grid, grid id

}

function afterSearch()
{
	myGrid.expandAll(); //펼치기
 	setTimeout("myGrid.collapseAll();//접기", 100);
}
/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maEqLocList.do";
    maEqLocListForm.elements['strutsAction'].value = '<%=MaEqLocListAction.EQ_LOC_LIST_FIND%>';

    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maEqLocListForm), "eqLocId", "", "pEqLocId");
    /* myGrid.clearAll(); setLoading("gridbox");
    $.post(url,FormQueryString(maEqLocListForm), function(_data){
    	myGrid.parse(_data,"js");

    	//var jsonObj = JSON.parse(_data);
    	//console.log(jsonObj.total_count);
    	
     	myGrid.expandAll(); //펼치기
    	setCounter(myGrid,"gridbox"); //TreeGrid는 펼쳐진 Rows만 카운트 함.
     	setTimeout("myGrid.collapseAll();//접기", 200);
    }); */
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_eqLocId)
{
	maEqLocListForm.elements['maEqLocCommonDTO.eqLocId'].value = _eqLocId;
	findGridList('ReloadTreeRow');
	maEqLocListForm.elements['maEqLocCommonDTO.eqLocId'].value = "";
}

/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	maEqLocListForm.elements['maEqLocCommonDTO.eqLocId'].value = "";	// 검색시 Tab 이동Key Clear
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
	var form = document.maEqLocListForm;
	maEqLocListForm.elements['maEqLocCommonDTO.eqLocId'].value =  getValueById(myGrid, selectedId,'EQLOCID');
	maEqLocListForm.elements['maEqLocCommonDTO.detailPEqLocId'].value =  getValueById(myGrid, selectedId,'EQLOCID');
	maEqLocListForm.elements['maEqLocCommonDTO.detailPEqLocDesc'].value =  getValueById(myGrid, selectedId,'EQLOCDESC');
    
	goCommonTabPage(form, <%= MaEqLocDetailAction.EQ_LOC_DETAIL_INIT %>, pageId);
    
}

/**
 * 상세 열기
 */
 function goOpen(){
	 goTabPage('maEqLocDetail');
}

 function goOpenAction()
 {
     var selectedId=myGrid.getSelectedRowId();
     if(selectedId == null) return;
     
     maEqLocListForm.elements['maEqLocCommonDTO.eqLocId'].value =  getValueById(myGrid, selectedId,'EQLOCID');
 	maEqLocListForm.elements['maEqLocCommonDTO.detailPEqLocId'].value =  getValueById(myGrid, selectedId,'EQLOCID');
 	maEqLocListForm.elements['maEqLocCommonDTO.detailPEqLocDesc'].value =  getValueById(myGrid, selectedId,'EQLOCDESC');
     maEqLocListForm.elements['strutsAction'].value = '<%=MaEqLocDetailAction.EQ_LOC_DETAIL_INIT%>';
     openQuickTabPage(FormQueryString(maEqLocListForm), 'maEqLocDetail'); 
 } 

/**
 * 엑셀 다운.
 */
 function goExcel()
 {
	/* myGrid.expandAll(); //펼치기
	setTimeout("excelAction(myGrid);", 100);
 	setTimeout("myGrid.collapseAll();", 200); */
 	
 	maEqLocListForm.elements['maEqLocCommonDTO.eqLocId'].value = "";	// 검색시 Tab 이동Key Clear
	excelServerAction("maEqLocList", maEqLocListForm );  
 }

 /**
  * 생성
  */
 function goCreate()
 {
 	createValidationCheck(myGrid, "maEqLocDetail" , "goCreateAction");
 }
 
 function goCreateAction(pageId)
 {
	 maEqLocListForm.elements['maEqLocCommonDTO.eqLocId'].value = "";
 	goCommonTabPage(maEqLocListForm, '', "maEqLocDetail");
 }

/**
 * 삭제
 */
 function goDelete(){
	//myGrid에 1(2번째)번에 체크가 되어있으면 2번 데이터 가져오기
	 var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'EQLOCID'); //Grid, check box column seq, pk column seq
	//체크된게 없으면 return
	 if(typeof delArray == "undefined"){
			alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
			return;
		}
	
  	maEqLocListForm.strutsAction.value = '<%=MaEqLocListAction.EQ_LOC_LIST_DELETE%>';
  	var url = contextPath + "/maEqLocList.do";
  	
  	$.post(url,FormQueryString(maEqLocListForm)+delArray , function(_data){
    	afterDelete();
    });
  }

function afterDelete(){
	goClose('maEqLocDetail');
	//goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
  }

/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = maEqLocListForm.elements['maEqLocCommonDTO.invtlistId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maEqLocList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maEqLocCommonDTO.eqLocId"/><!-- Key -->
<html:hidden property="maEqLocCommonDTO.detailPEqLocId"/>
<html:hidden property="maEqLocCommonDTO.detailPEqLocDesc"/>
<html:hidden property="maEqLocCommonDTO.filterLevelType"/><!-- 구분 -->
<html:hidden property="maEqLocCommonDTO.filterPEqLocId"/><!-- 상위위치 -->
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
					<label><bean:message key="LABEL.eqLocName"/></label>
					<div class="input_box">
						<html:text property="maEqLocCommonDTO.filterEqLocDesc" tabindex="10"/>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.eqLocNo"/></label>
					<div class="input_box">
						<html:text property="maEqLocCommonDTO.filterEqLocNo" tabindex="11"/>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.separation"/></label>
					<div class="input_box">
						<html:text property="maEqLocCommonDTO.filterLevelTypeDesc" tabindex="12"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.pEqLoc"/></label>
					<div class="input_box">
						<html:text property="maEqLocCommonDTO.filterPEqLocDesc" tabindex="13" />
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
	    <div class="article_box">
            <div class="grid_area">
            	<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
            </div>
	 	</div>
	 </div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>