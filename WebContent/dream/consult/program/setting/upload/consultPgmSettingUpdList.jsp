<%--===========================================================================
Excel Data Upload
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.program.setting.upload.action.ConsultPgmSettingUpdAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- Excel Data Upload -->
<title><bean:message key='PAGE.consultPgmSettingUpdList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="consultPage">
<!-- 공통메뉴 -->

<script language="javascript">
<!--//
//그리드명
var myGrid;

/** 자동완성 변수 */
var excelTypeAc;

function loadPage() 
{
    initGrid();
    //성공여부 자동완성
    acSysDesc("consultPgmSettingUpdDTO.filterIsSuccess","consultPgmSettingUpdDTO.filterIsSuccess","IS_USE");
    
    //Excel Type 자동완성
    excelTypeAc = new autoC({"consultPgmSettingUpdDTO.filterExcelTabDesc":"description"});
    excelTypeAc.setTable("TAEXCELTAB");
    excelTypeAc.setKeyName("consultPgmSettingUpdDTO.filterExcelTabId");
    excelTypeAc.setAcConditionMap({
        "excellist_status":"C"
        ,"is_use":"Y"
        ,"is_use_config":"Y"
    });
    excelTypeAc.setAcResultMap({
        "consultPgmSettingUpdDTO.filterExcelTabId":"exceltab_id"
        ,"consultPgmSettingUpdDTO.filterTableName":"table_name"
        ,"consultPgmSettingUpdDTO.filterUploadPgmName":"upload_pgm_name"
        ,"consultPgmSettingUpdDTO.filterSheetName":"sheet_name"
        ,"consultPgmSettingUpdDTO.filterExcelUploadType":"excel_upload_type"
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
	   	consultPgmSettingUpdForm.elements['consultPgmSettingUpdDTO.skeyId'].value = "";
	   	return sortColumn("consultPgmSettingUpdList", this, consultPgmSettingUpdForm, "SKEY_ID", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	
}

function setDummyHeader(_grid, _gridId, _callBack)
{
	var url = contextPath + "/"+currentPageId+".do";
	var _newParam = "strutsAction="+<%= ConsultPgmSettingUpdAction.SET_DUMMY_HEADER %>+'&listId='+_gridId+"&currentPageId="+currentPageId
							+"&consultPgmSettingUpdDTO.filterExcelTabId="+consultPgmSettingUpdForm.elements['consultPgmSettingUpdDTO.filterExcelTabId'].value;

	$.post(url,_newParam, function(_data){
		
		var jsonObj = JSON.parse(_data);
		
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
    var url = contextPath + "/consultPgmSettingUpdList.do";
    consultPgmSettingUpdForm.elements['strutsAction'].value = '<%=ConsultPgmSettingUpdAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(consultPgmSettingUpdForm), "SKEY_ID","Y");

}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_skeyId)
{
	consultPgmSettingUpdForm.elements['consultPgmSettingUpdDTO.skeyId'].value = _skeyId;
	findGridList('ReloadRow');
	consultPgmSettingUpdForm.elements['consultPgmSettingUpdDTO.skeyId'].value = "";
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
	consultPgmSettingUpdForm.elements['consultPgmSettingUpdDTO.filterExcelTabId'].value = _excelTabId;
    consultPgmSettingUpdForm.elements['consultPgmSettingUpdDTO.filterExcelTabDesc'].value = _excelTabDesc;
    consultPgmSettingUpdForm.elements['consultPgmSettingUpdDTO.filterTableName'].value = _tableName;
	consultPgmSettingUpdForm.elements['consultPgmSettingUpdDTO.filterUploadPgmName'].value = _uploadPgmName;
	consultPgmSettingUpdForm.elements['consultPgmSettingUpdDTO.filterSheetName'].value = _sheetName;
	
	goSearch();
	
	alertMessage1('<bean:message key="MESSAGE.MSG034"/>');
}

function findListData()
{
	consultPgmSettingUpdForm.elements['consultPgmSettingUpdDTO.skeyId'].value = "";	// 검색시 Tab 이동Key Clear
	
	consultPgmSettingUpdForm.elements['consultPgmSettingUpdDTO.excelTabId'].value = consultPgmSettingUpdForm.elements['consultPgmSettingUpdDTO.filterExcelTabId'].value;
    consultPgmSettingUpdForm.elements['consultPgmSettingUpdDTO.tableName'].value = consultPgmSettingUpdForm.elements['consultPgmSettingUpdDTO.filterTableName'].value;
	consultPgmSettingUpdForm.elements['consultPgmSettingUpdDTO.uploadPgmName'].value = consultPgmSettingUpdForm.elements['consultPgmSettingUpdDTO.filterUploadPgmName'].value;
	consultPgmSettingUpdForm.elements['consultPgmSettingUpdDTO.excelUploadType'].value = consultPgmSettingUpdForm.elements['consultPgmSettingUpdDTO.filterExcelUploadType'].value;
	
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
	consultPgmSettingUpdForm.elements['consultPgmSettingUpdDTO.skeyId'].value =  getValueById(myGrid, selectedId,'SKEY_ID');  
	goCommonTabPage(consultPgmSettingUpdForm, '<%=ConsultPgmSettingUpdAction.DETAIL_FAKE_ACTION%>', pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('consultPgmSettingUpdDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    consultPgmSettingUpdForm.elements['consultPgmSettingUpdDTO.skeyId'].value =  getValueById(myGrid, selectedId,'SKEY_ID');  
    consultPgmSettingUpdForm.elements['strutsAction'].value = '<%=ConsultPgmSettingUpdAction.DETAIL_FAKE_ACTION%>';
    openQuickTabPage(FormQueryString(consultPgmSettingUpdForm), 'consultPgmSettingUpdDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "consultPgmSettingUpdDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	consultPgmSettingUpdForm.elements['consultPgmSettingUpdDTO.skeyId'].value = "";
    goCommonTabPage(consultPgmSettingUpdForm, '', pageId);
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

    consultPgmSettingUpdForm.strutsAction.value = '<%=ConsultPgmSettingUpdAction.LIST_DELETE%>';
    var url = contextPath + "/consultPgmSettingUpdList.do";
    
    $.post(url,FormQueryString(consultPgmSettingUpdForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('consultPgmSettingUpdDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	consultPgmSettingUpdForm.elements['consultPgmSettingUpdDTO.skeyId'].value = "";
	excelServerAction("consultPgmSettingUpdList", consultPgmSettingUpdForm );
// 	excelAction(myGrid);
}

function goSelectfile()
{
	var _pageId = currentPageId;
	var _compNo = loginUser.compNo;
	
	var url   = contextPath + "/consultPgmSettingUpdFileDetail.do";
    var popWidth = 1010;
    var popHeight = 640;
    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
	
	var param = "&strutsAction="+
                "&consultPgmSettingUpdFileDTO.filterCompNo="+_compNo+
                "&consultPgmSettingUpdFileDTO.filterFileName="+_pageId+
                "&consultPgmSettingUpdFileDTO.excelTabId="+consultPgmSettingUpdForm.elements['consultPgmSettingUpdDTO.filterExcelTabId'].value+
                "&consultPgmSettingUpdFileDTO.excelTabDesc="+consultPgmSettingUpdForm.elements['consultPgmSettingUpdDTO.filterExcelTabDesc'].value+
                "&consultPgmSettingUpdFileDTO.tableName="+consultPgmSettingUpdForm.elements['consultPgmSettingUpdDTO.filterTableName'].value+
				"&consultPgmSettingUpdFileDTO.uploadPgmName="+consultPgmSettingUpdForm.elements['consultPgmSettingUpdDTO.filterUploadPgmName'].value+
				"&consultPgmSettingUpdFileDTO.sheetName="+consultPgmSettingUpdForm.elements['consultPgmSettingUpdDTO.filterSheetName'].value;
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
	var param = FormQueryStringPost(consultPgmSettingUpdForm)+
				"&currentPageId="+ currentPageId+
				"&isDownloadPage=true" +
				"&strutsAction="+'<%=ConsultPgmSettingUpdAction.TEMPLATE_EXPORT%>' +
			    "&fileName="+fileName;
	submitPost("consultPgmSettingUpdList", param , "bottomIframe");
}

/**
 * 데이터 반영
 */
function goDataupload(){
	if(checkValidation()) return;
	
	goSearch();
	
	getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0235'/>", function(result){
        if(result){
        	consultPgmSettingUpdForm.strutsAction.value = '<%=ConsultPgmSettingUpdAction.UPLOAD_DATA%>';
            var url = contextPath + "/consultPgmSettingUpdList.do";
            XMLHttpPost(url, FormQueryString(consultPgmSettingUpdForm), 'afterDataUpload');
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

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/consultPgmSettingUpdList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="consultPgmSettingUpdDTO.skeyId"/><!-- Key -->
<html:hidden property="consultPgmSettingUpdDTO.keyColName"/>
<html:hidden property="consultPgmSettingUpdDTO.filterExcelTabId"/>
<html:hidden property="consultPgmSettingUpdDTO.filterTableName"/>
<html:hidden property="consultPgmSettingUpdDTO.filterUploadPgmName"/>
<html:hidden property="consultPgmSettingUpdDTO.filterSheetName"/>
<html:hidden property="consultPgmSettingUpdDTO.filterExcelUploadType"/>

<html:hidden property="consultPgmSettingUpdDTO.excelTabId"/>
<html:hidden property="consultPgmSettingUpdDTO.tableName" />
<html:hidden property="consultPgmSettingUpdDTO.uploadPgmName" />
<html:hidden property="consultPgmSettingUpdDTO.sheetName" />
<html:hidden property="consultPgmSettingUpdDTO.excelUploadType" />
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
						<html:text property="consultPgmSettingUpdDTO.filterExcelTabDesc" tabindex="20"/>
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
							<html:text property="consultPgmSettingUpdDTO.filterStartDate" tabindex="20" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="consultPgmSettingUpdDTO.filterEndDate" tabindex="30" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 성공여부 -->
				<div class="field">
					<label><bean:message key="LABEL.isSuccess"/></label>
					<div class="input_box">
						<html:text property="consultPgmSettingUpdDTO.filterIsSuccess" tabindex="110" />
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