<%--===========================================================================
화변별 필드 목록
author  kim21017
version $Id: persPrivUsrFieldList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.pers.priv.pgm.action.PersPrivUsrFieldListAction" %>
<%@ page import="dream.pers.priv.pgm.action.PersPrivUsrFieldDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 화면 별 필드 -->
<title><bean:message key='LABEL.persPrivUsrFieldDetail'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	initGrid();
	
	$('.stitle_tx').append(" [ "+getIframeContent().currentPageId+" ]");
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goTabPage("persPrivUsrFieldDetail");
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		persPrivUsrFieldListForm.elements['persPrivUsrFieldCommonDTO.pgFieldId'].value = "";
    	return sortColumn("persPrivUsrFieldList", this, persPrivUsrFieldListForm, "PGFIELDID", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox", "goSearch"); // grid, grid id, callBack
}

var pPageInfo = new function(e) {
    this.inputArray = getParentInputArray();
    this.create = function(){
    	getParentInput();
    };
    this.update = function(){
    	updateParentInput();
    };
}

function updateParentInput()
{
	var arrayObj = pPageInfo.inputArray;
	var jsonArray = new Array();
	var flag;
	for(var i in arrayObj)
	{
		flag = true;
		for(var j = 0; myGrid.getRowsNum() > j; j++)
	 	{
			if(arrayObj[i].name == getValueById(myGrid, myGrid.getRowId(j), "fieldId")) flag = false;
		}
		
		if(flag) jsonArray.push(arrayObj[i]);
		
  	}
	
	var finalJsonData = JSON.stringify(jsonArray);
	
	persPrivUsrFieldListForm.elements['persPrivUsrFieldCommonDTO.fieldData'].value = finalJsonData;
	persPrivUsrFieldListForm.strutsAction.value = '<%=PersPrivUsrFieldListAction.PG_FIELD_CREATE%>';
	var url = contextPath + "/persPrivUsrFieldList.do";
	
	$.post(url,FormQueryString(persPrivUsrFieldListForm) , function(_data){
    	goSearch();
    });
}

		
//function afterSearch()
//{
	/* if(	myGrid.getRowsNum() == 0 )
	{
		//pPageInfo.create();
	}
	else  */
	//alert('pPageInfo.inputArray.length:' + pPageInfo.inputArray.length);
	//alert('myGrid.getRowsNum():' + myGrid.getRowsNum());
	//if(isSearched){
	//	isSearched = false;
	//	pPageInfo.update();	
	//}
	
	//if(pPageInfo.inputArray.length >  myGrid.getRowsNum())
	//{
		//pPageInfo.update();	
	//}
//}

function getParentInputArray()
{
	var jsonArray = new Array();
	var test = getIframeContent().$(".field,.field_long,.field_img").map(function(idx){
		
		var ty = $(this).is("[class*='ty']");
//		console.log(ty+"   "+$(this).find('label').text()+"   "+$(this).prop('class'));
		var fieldObj = $(this);
		var groupObj = $(this).parents('.field_group_wide,.field_group');
		var groupLabelObj = groupObj.find('legend');
		var inputObj = $(this).find('input,textarea,.img_box').eq(0);
		var labelObj = fieldObj.find('label');
		var divObj   = fieldObj.find('div[class^="input_"]').eq(0);

		if(typeof inputObj.attr("name") == "undefined") return false;

		var jsonObj = new Object();
		jsonObj.label = labelObj.text();
		jsonObj.name = inputObj.attr("name");
		jsonObj.check = labelObj.is(".check");
		jsonObj.ordNo =  (idx*5)+100;
	//	jsonObj.hiddenYn =  fieldObj.is(".hidden")=="false"?"Y":"N";
		jsonObj.hiddenYn =  fieldObj.is(".hidden")=="false"?"N":"Y";
		jsonObj.option = "";
		jsonObj.readonly = typeof divObj.prop("class")=="undefined"?"":divObj.prop("class");
		jsonObj.groupOption = typeof groupObj.prop("class")=="undefined"?"":groupObj.prop("class");
		jsonObj.groupLabel = groupLabelObj.text();

		if($(this).next().is('.field_long_blank,.field_blank,.field_divide') == true)
		{
			jsonObj.option = $(this).next().prop("class");
		}
		
		jsonArray.push(jsonObj);
		//return $(this).attr("name");
	});//.get().join();
	
	return jsonArray;
}

function getParentInput()
{
	var jsonArray = pPageInfo.inputArray;
	
	var finalJsonData = JSON.stringify(jsonArray);

	persPrivUsrFieldListForm.elements['persPrivUsrFieldCommonDTO.fieldData'].value = finalJsonData;
	persPrivUsrFieldListForm.strutsAction.value = '<%=PersPrivUsrFieldListAction.PG_FIELD_CREATE%>';
	var url = contextPath + "/persPrivUsrFieldList.do";
	
	$.post(url,FormQueryString(persPrivUsrFieldListForm) , function(_data){
    	goSearch();
    });
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	persPrivUsrFieldListForm.elements['persPrivUsrFieldCommonDTO.pgFieldId'].value = "";
	
    findGridList('Search');   
}

function findGridList(sheetAction)
{
	//페이지id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	if (persPrivUsrFieldListForm.elements['persPrivUsrFieldCommonDTO.pageId'].value == '') return;
	
	//persPrivUsrFieldListForm.elements['persPrivUsrFieldCommonDTO.pgFieldId'].value = "";
	
	var form = document.persPrivUsrFieldListForm;	
	form.strutsAction.value = '<%=PersPrivUsrFieldListAction.PG_FIELD_LIST_FIND %>';
	
	var url = contextPath + "/persPrivUsrFieldList.do";
	
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(persPrivUsrFieldListForm), "PGFIELDID", "Y");

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
	persPrivUsrFieldListForm.elements['persPrivUsrFieldCommonDTO.pgFieldId'].value = getValueById(myGrid, selectedId,'PGFIELDID');
	
	goCommonTabPage(persPrivUsrFieldListForm, <%= PersPrivUsrFieldDetailAction.PG_FIELD_DETAIL_INIT %>, pageId); 
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_pgFieldId)
{
	persPrivUsrFieldListForm.elements['persPrivUsrFieldCommonDTO.pgFieldId'].value = _pgFieldId;
	findGridList('ReloadRow');
	persPrivUsrFieldListForm.elements['persPrivUsrFieldCommonDTO.pgFieldId'].value = "";
}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('persPrivUsrFieldDetail');
}

 /**
  * 생성
  */
function goCreate()
{
 	createValidationCheck(myGrid, "persPrivUsrFieldDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
 	persPrivUsrFieldListForm.elements['persPrivUsrFieldCommonDTO.pgFieldId'].value = "";
 	goCommonTabPage(persPrivUsrFieldListForm, '', pageId);
}
 
/**
 * Excel Export
 */
function goExcel()
{
    excelAction(myGrid);
}

/**
 * Hide Fields
 */
function goConfirmiss()
{
	var selectedArray = getSelectedRows(myGrid, 'ISDELCHECK', 'PGFIELDID');
	if(typeof selectedArray == "undefined"){
		return;
	}

	persPrivUsrFieldListForm.strutsAction.value = '<%=PersPrivUsrFieldListAction.PG_FIELD_HIDE %>';
	var url = contextPath + "/persPrivUsrFieldList.do";
	
	alertMessage1('<bean:message key="MESSAGE.MSG0083"/>');   // 기다려주세요..
	
	$.post(url,FormQueryString(persPrivUsrFieldListForm)+selectedArray , function(_data){
    	afterHide("hide",myGrid.getCheckedRows(getIndexById(myGrid, "ISDELCHECK")).split(","));
    });
}//CONFIRMRTN

/**
 * Show Fields
 */
function goConfirmrtn()
{
	var selectedArray = getSelectedRows(myGrid, 'ISDELCHECK', 'PGFIELDID');
	if(typeof selectedArray == "undefined"){
		return;
	}
	
	persPrivUsrFieldListForm.strutsAction.value = '<%=PersPrivUsrFieldListAction.PG_FIELD_SHOW %>';
	var url = contextPath + "/persPrivUsrFieldList.do";
	
	alertMessage1('<bean:message key="MESSAGE.MSG0083"/>');   // 기다려주세요..
	
	$.post(url,FormQueryString(persPrivUsrFieldListForm)+selectedArray , function(_data){
    	afterHide("show",myGrid.getCheckedRows(getIndexById(myGrid, "ISDELCHECK")).split(","));
    });
}

function afterHide(actType, selectedArray)
{
	goClose('persPrivUsrFieldDetail');
	var ifmCnt = getIframeContent();

	for(var i = 0; selectedArray.length > i; i++)
	{
		var fieldName = getValueById(myGrid, selectedArray[i], "fieldId");
		var displayYN = getValueById(myGrid, selectedArray[i], "displayyn");
		var checkYN = getValueById(myGrid, selectedArray[i], "checkyn");
		
		if(actType == "hide" && displayYN == "Y" && checkYN == "N")
			ifmCnt.hideField(fieldName);
		else //if(displayYN == "N")
			ifmCnt.showField(fieldName);
	}
	
	alertMessage1('<bean:message key="MESSAGE.MSG034"/>');  //완료되었습니다.

	goSearch();
}

  /**
   * 삭제
   */
function goDelete()
{
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'PGFIELDID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	persPrivUsrFieldListForm.strutsAction.value = '<%=PersPrivUsrFieldListAction.PG_FIELD_LIST_DELETE%>';
	var url = contextPath + "/persPrivUsrFieldList.do";
	
	$.post(url,FormQueryString(persPrivUsrFieldListForm)+delArray , function(_data){
    	afterDelete();
    });
}
  
function afterDelete()
{
	goClose('persPrivUsrFieldDetail');
	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

function goImp_field()
{
    var jsonArray = pPageInfo.inputArray;
    
    var finalJsonData = JSON.stringify(jsonArray);

    persPrivUsrFieldListForm.elements['persPrivUsrFieldCommonDTO.fieldData'].value = finalJsonData;
	
    setModal('<bean:message key="MESSAGE.MSG0083"/>');
    // 기다려주세요...
    
	persPrivUsrFieldListForm.strutsAction.value = '<%=PersPrivUsrFieldListAction.PG_FIELD_CREATE%>';
	var url = contextPath + "/persPrivUsrFieldList.do";
	$.post(url,FormQueryString(persPrivUsrFieldListForm) , function(_data){
        closeModal();
        afterImp_field();
    });
}

function afterImp_field()
{
	alertMessage1('<bean:message key="MESSAGE.MSG0116"/>');
    goSearch();	
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/persPrivUsrFieldList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="persPrivUsrFieldCommonDTO.pageId"/><!-- Key -->
<html:hidden property="persPrivUsrFieldCommonDTO.pgFieldId"/><!-- Key -->
<html:hidden property="persPrivUsrFieldCommonDTO.fieldData"/>
    <!-- searchbox 박스 Line -->
<div class="section_wrap">
	<div class="sheader_box">
		<div class="sheader_wrap"><a></a></div>
		<div class="stitle_wrap">
			<div class="stitle_icon"><a></a></div>
			<div class="stitle_tx"><bean:message key="LABEL.List"/></div>
		</div>
		<!--<div class="stitle_box"><bean:message key="LABEL.Filter"/></div>  -->
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
           	<div id="gridbox" style="width:100%; height:250px; background-color:white;"></div>
           </div>
 	</div>
</div>
</html:form> 
</body>
</html>