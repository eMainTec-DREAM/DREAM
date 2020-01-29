<%--===========================================================================
Excel Data Upload
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.exldata.action.MgrExldataListAction" %>
<%@ page import="dream.mgr.exldata.action.MgrExldataDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- Excel Data Upload -->
<title><bean:message key='MENU.EXCELUPLOAD'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->

<script language="javascript">
<!--//
//그리드명
var myGrid;

/** 자동완성 변수 */
var excelTypeAc;

function loadPage() 
{
	//Excel유형은 투자목록으로 고정하고 수정할 수 없음.
	if(window.name =="OPEN_EXL")
	{
	}
	
    initGrid();
    //성공여부 자동완성
    acSysDesc("mgrExldataCommonDTO.filterIsSuccess","mgrExldataCommonDTO.filterIsSuccess","IS_USE");
    
    //Excel Type 자동완성
    excelTypeAc = new autoC({"mgrExldataCommonDTO.filterExcelTabDesc":"description"});
    excelTypeAc.setTable("TAEXCELTAB");
    excelTypeAc.setKeyName("mgrExldataCommonDTO.filterExcelTabId");
    excelTypeAc.setAcConditionMap({
        "excellist_status":"C"
        ,"is_use":"Y"
        ,"is_use_config":"N"
    });
    excelTypeAc.setAcResultMap({
        "mgrExldataCommonDTO.filterExcelTabId":"exceltab_id"
        ,"mgrExldataCommonDTO.filterTableName":"table_name"
        ,"mgrExldataCommonDTO.filterUploadPgmName":"upload_pgm_name"
        ,"mgrExldataCommonDTO.filterSheetName":"sheet_name"
        ,"mgrExldataCommonDTO.filterExcelUploadType":"excel_upload_type"
    });
    excelTypeAc.init();
    
    //탭페이지 frame이 로딩 후에 contents height가 바뀔경우 잘리는 문제를 해결하기 위함
    //자식 페이지에서 이 페이지를 호출 후 trigger를 걸면 그 자신의 높이가 재조정됨
    $('iframe').on("setHeight", function(e) {
        var iframeHeight=this.contentWindow.document.body.scrollHeight;
        $(this).css({height:iframeHeight});
      });
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
    	mgrExldataListForm.elements['mgrExldataCommonDTO.skeyId'].value = "";
    	return sortColumn("mgrExldataList", this, mgrExldataListForm, "SKEY_ID", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	
}

function setDummyHeader(_grid, _gridId, _callBack)
{
	var url = contextPath + "/"+currentPageId+".do";
	var _newParam = "strutsAction="+<%= MgrExldataListAction.SET_DUMMY_HEADER %>+'&listId='+_gridId+"&currentPageId="+currentPageId
							+"&mgrExldataCommonDTO.filterExcelTabId="+mgrExldataListForm.elements['mgrExldataCommonDTO.filterExcelTabId'].value;

	$.post(url,_newParam, function(_data){

		var jsonObj = JSON.parse(_data);  
		//console.log(jsonObj.header);
		
		isHeaderLoaded[currentPageId+"."+_gridId] = "Y";

		//Height 설정
		if(jsonObj.height != "0" && typeof _noHeight == "undefined")
		{
			$('#'+_gridId).css("height",jsonObj.height);
			_grid.setSizes();
		}	
		else 
		{
			$('#'+_gridId).css("height",_noHeight);
			_grid.setSizes();
		}
		
// 		if(typeof resizeTabFrame == "function") resizeTabFrame();
		
		try{_grid.parse(jsonObj.header,"js");}catch(e){}finally{
			
			if(!(typeof _callBack == "undefined" || _callBack =="")) 
			{
				$.globalEval(_callBack+"();");
			}
			
// 			if(typeof checkActionCall == "function")  checkActionCall();
				
		}	
    });
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/mgrExldataList.do";
    mgrExldataListForm.elements['strutsAction'].value = '<%=MgrExldataListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(mgrExldataListForm), "SKEY_ID","Y");

}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_skeyId)
{
	mgrExldataListForm.elements['mgrExldataCommonDTO.skeyId'].value = _skeyId;
	findGridList('ReloadRow');
	mgrExldataListForm.elements['mgrExldataCommonDTO.skeyId'].value = "";
}

function goSearch()
{
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	
	setDummyHeader(myGrid, "gridbox", "findListData"); // grid, grid id
}

function searchAfterUpload(_excelTabId, _excelTabDesc, _tableName, _uploadPgmName, _sheetName)
{
	mgrExldataListForm.elements['mgrExldataCommonDTO.filterExcelTabId'].value = _excelTabId;
    mgrExldataListForm.elements['mgrExldataCommonDTO.filterExcelTabDesc'].value = _excelTabDesc;
    mgrExldataListForm.elements['mgrExldataCommonDTO.filterTableName'].value = _tableName;
	mgrExldataListForm.elements['mgrExldataCommonDTO.filterUploadPgmName'].value = _uploadPgmName;
	mgrExldataListForm.elements['mgrExldataCommonDTO.filterSheetName'].value = _sheetName;
	
	goSearch();
	
	alertMessage1('<bean:message key="MESSAGE.MSG034"/>');
}

function findListData()
{
	mgrExldataListForm.elements['mgrExldataCommonDTO.skeyId'].value = "";	// 검색시 Tab 이동Key Clear
	
	mgrExldataListForm.elements['mgrExldataCommonDTO.excelTabId'].value = mgrExldataListForm.elements['mgrExldataCommonDTO.filterExcelTabId'].value;
	mgrExldataListForm.elements['mgrExldataCommonDTO.tableName'].value = mgrExldataListForm.elements['mgrExldataCommonDTO.filterTableName'].value;
	mgrExldataListForm.elements['mgrExldataCommonDTO.uploadPgmName'].value = mgrExldataListForm.elements['mgrExldataCommonDTO.filterUploadPgmName'].value;
	mgrExldataListForm.elements['mgrExldataCommonDTO.excelUploadType'].value = mgrExldataListForm.elements['mgrExldataCommonDTO.filterExcelUploadType'].value;
	
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
	mgrExldataListForm.elements['mgrExldataCommonDTO.skeyId'].value =  getValueById(myGrid, selectedId,'SKEY_ID');  
	goCommonTabPage(mgrExldataListForm, '<%=MgrExldataDetailAction.DETAIL_FAKE_ACTION%>', pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('mgrExldataDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    mgrExldataListForm.elements['mgrExldataCommonDTO.skeyId'].value =  getValueById(myGrid, selectedId,'SKEY_ID');  
    mgrExldataListForm.elements['strutsAction'].value = '<%=MgrExldataDetailAction.DETAIL_FAKE_ACTION%>';
    openQuickTabPage(FormQueryString(mgrExldataListForm), 'mgrExldataDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "mgrExldataDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	mgrExldataListForm.elements['mgrExldataCommonDTO.skeyId'].value = "";
    goCommonTabPage(mgrExldataListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'SKEY_ID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    mgrExldataListForm.strutsAction.value = '<%=MgrExldataListAction.LIST_DELETE%>';
    var url = contextPath + "/mgrExldataList.do";
    
    $.post(url,FormQueryString(mgrExldataListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('mgrExldataDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
// 	mgrExldataListForm.elements['mgrExldataCommonDTO.skeyId'].value = "";
// 	excelServerAction("mgrExldataList", mgrExldataListForm );  
	excelAction(myGrid);
}

function goSelectfile()
{
	var _pageId = currentPageId;
	var _compNo = loginUser.compNo;
	
	var url   = contextPath + "/mgrExldataUploadDetail.do";
    var popWidth = 1010;
    var popHeight = 640;
    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
	
	var param = "&strutsAction="+
                "&mgrExldataUploadDetailDTO.filterCompNo="+_compNo+
                "&mgrExldataUploadDetailDTO.filterFileName="+_pageId+
                "&mgrExldataUploadDetailDTO.excelTabId="+mgrExldataListForm.elements['mgrExldataCommonDTO.filterExcelTabId'].value+
                "&mgrExldataUploadDetailDTO.excelTabDesc="+mgrExldataListForm.elements['mgrExldataCommonDTO.filterExcelTabDesc'].value+
                "&mgrExldataUploadDetailDTO.tableName="+mgrExldataListForm.elements['mgrExldataCommonDTO.filterTableName'].value+
				"&mgrExldataUploadDetailDTO.uploadPgmName="+mgrExldataListForm.elements['mgrExldataCommonDTO.filterUploadPgmName'].value+
				"&mgrExldataUploadDetailDTO.sheetName="+mgrExldataListForm.elements['mgrExldataCommonDTO.filterSheetName'].value;
	//post 전송
	openWindowWithPost(url, "UPLOAD_EXCEL_DETAIL_POPUP", param, pos);
}

function goTemplate()
{
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	
	alertMessage1(COMMON_CMSG061);
	fileName = "ExcelTemplate";
	var param = FormQueryStringPost(mgrExldataListForm)+
				"&currentPageId="+ currentPageId+
				"&isDownloadPage=true" +
				"&strutsAction="+'<%=MgrExldataListAction.TEMPLATE_EXPORT%>' +
			    "&fileName="+fileName;
	submitPost("mgrExldataList", param , "bottomIframe");
}

/**
 * 데이터 반영
 */
 function goDataupload(){
	 if(checkValidation()) return;
	 
	goSearch();
	
	getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0235'/>", function(result){
        if(result){
		        	mgrExldataListForm.strutsAction.value = '<%=MgrExldataListAction.UPLOAD_DATA%>';
		            var url = contextPath + "/mgrExldataList.do";
		            XMLHttpPost(url, FormQueryString(mgrExldataListForm), 'afterDataUpload');
		        	setModal('<bean:message key="MESSAGE.MSG0149"/>');
               }
       });
}

/**
 * 데이터 반영 후 함수
 */
 function afterDataUpload(ajaxXmlDoc){
	closeModal();
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc))
		return;
	
	goSearch();

	//데이터 적용후 오픈한 페이지의 List 재로딩
	if(opener && typeof opener.goSearch == "function") opener.goSearch();
	
	alertMessage1('<bean:message key="MESSAGE.MSG034"/>');
}

 /**
  *  Audit trail
  */
  function goAudtrailLink()
  {
     var objectId = mgrExldataListForm.elements['mgrExldataCommonDTO.skeyId'].value;
     var fileName = currentPageId;

     if(typeof objectId=="undefined") return;

     goAudTrailList(objectId, fileName);
  }

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/mgrExldataList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="mgrExldataCommonDTO.skeyId"/><!-- Key -->
<html:hidden property="mgrExldataCommonDTO.keyColName"/>
<html:hidden property="mgrExldataCommonDTO.filterExcelTabId"/>
<html:hidden property="mgrExldataCommonDTO.filterTableName"/>
<html:hidden property="mgrExldataCommonDTO.filterUploadPgmName"/>
<html:hidden property="mgrExldataCommonDTO.filterSheetName"/>
<html:hidden property="mgrExldataCommonDTO.filterExcelUploadType"/>
<html:hidden property="mgrExldataCommonDTO.excelTabId"/>
<html:hidden property="mgrExldataCommonDTO.tableName"/>
<html:hidden property="mgrExldataCommonDTO.uploadPgmName"/>
<html:hidden property="mgrExldataCommonDTO.excelUploadType"/>
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
				<!-- Excel유형 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.excelType"/></label>
					<div class="input_box">
						<html:text property="mgrExldataCommonDTO.filterExcelTabDesc" tabindex="20"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 업로드일자 -->
				<div class="field">
					<label><bean:message key="LABEL.uploadDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="mgrExldataCommonDTO.filterStartDate" tabindex="20" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="mgrExldataCommonDTO.filterEndDate" tabindex="30" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 성공여부 -->
				<div class="field">
					<label><bean:message key="LABEL.isSuccess"/></label>
					<div class="input_box">
						<html:text property="mgrExldataCommonDTO.filterIsSuccess" tabindex="110" />
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