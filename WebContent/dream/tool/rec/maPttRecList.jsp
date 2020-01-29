<%--===========================================================================
구매입고 - 목록
author  ssong
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.tool.rec.action.MaPttRecListAction" %>
<%@ page import="dream.tool.rec.action.MaPttRecDetailAction" %>
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
<!-- 구매입고 -->
<title><bean:message key='MENU.PTTREC'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var myGrid;
var deptAc;

/** 자동완성 변수 */
var mainMngAc;
var deptAc;
var partNameAc;
var vendorDescAc;
var prRecStatusAc;
function loadPage() 
{
	//설비작업현황 - 부품입고 팝업 시
	if(window.name=="CHART_PT_LIST_POPUP"){
		if(M$('maPttRecCommonDTO.prRecStatus').value!='')
		valSysDirCode('maPttRecCommonDTO.prRecStatus', 'maPttRecCommonDTO.prRecStatusDesc', 'PRRECLIST_STATUS','', true);
	}else{
		maPttRecListForm.elements['maPttRecCommonDTO.filterDeptId'].value   = loginUser.filterDeptId;
		maPttRecListForm.elements['maPttRecCommonDTO.filterDeptDesc'].value = loginUser.filterDeptDesc;
		
		maPttRecListForm.elements['maPttRecCommonDTO.filterRecStartDate'].value = getMinusMonth2(new Date(), -2); 
	    maPttRecListForm.elements['maPttRecCommonDTO.filterRecEndDate'].value   = getToday();
	}
    
    initGrid();
    
    deptAc = new autoC({"maPttRecCommonDTO.filterDeptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "maPttRecCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.init();
    
    mainMngAc = new autoC({"maPttRecCommonDTO.filterInspectorName":"emp_name"});
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcResultMap({
        "maPttRecCommonDTO.filterInspector":"emp_id"
    });
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "maPttRecCommonDTO.filterDeptId"
    });
    mainMngAc.init();
    
    partNameAc = new autoC({"maPttRecCommonDTO.filterPartNameSize":"partNameSize"});
    partNameAc.setAcConditionMap({
	   "part_categ":"TOOL",
	   "comp_no":loginUser.compNo
	   });
    partNameAc.setTable("TAPARTS");
    partNameAc.init();
    
    vendorDescAc = new autoC({"maPttRecCommonDTO.filterVendorDesc":"description"});
    vendorDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_use":"Y"
  	   });
    vendorDescAc.setTable("TAVENDOR");
    vendorDescAc.setAcResultMap({
        "maPttRecCommonDTO.filterVendorId":"vendor_id"
    });
    vendorDescAc.init();
    
    acSysDesc("maPttRecCommonDTO.prRecStatusDesc","maPttRecCommonDTO.prRecStatus","PRRECLIST_STATUS");
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
    	maPttRecListForm.elements['maPttRecCommonDTO.prRecListId'].value = "";
        return sortColumn("maPttRecList", this, maPttRecListForm, "PRRECLISTID", ind, direction);
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maPttRecList.do";

    maPttRecListForm.elements['strutsAction'].value = '<%=MaPttRecListAction.PTREC_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maPttRecListForm), "PRRECLISTID", "Y");
    
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_prRecListId)
{
	maPttRecListForm.elements['maPttRecCommonDTO.prRecListId'].value = _prRecListId;
	findGridList('ReloadRow');
	maPttRecListForm.elements['maPttRecCommonDTO.prRecListId'].value = "";
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	maPttRecListForm.elements['maPttRecCommonDTO.prRecListId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('Search', '<%=MaPttRecListAction.PTREC_LIST_FIND%>');   
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
	var form = document.maPttRecListForm;
	 
	form.elements['maPttRecCommonDTO.prRecListId'].value = getValueById(myGrid, selectedId, 'prRecListId');
	goCommonTabPage(form, <%= MaPttRecDetailAction.PTREC_DETAIL_INIT %>, pageId);
}

/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('maPttRecDetail');	
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    maPttRecListForm.elements['maPttRecCommonDTO.prRecListId'].value = getValueById(myGrid, selectedId, 'prRecListId');
    maPttRecListForm.elements['strutsAction'].value = '<%=MaPttRecDetailAction.PTREC_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(maPttRecListForm), 'maPttRecDetail'); 
} 
 
/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "maPttRecDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maPttRecListForm.elements['maPttRecCommonDTO.prRecListId'].value = "";
	goCommonTabPage(maPttRecListForm, '', pageId);
}

/**
  * 삭제
  */
function goDelete()
{
	// 입고완료 여부 체크 
	var cnt = 0;
	var checkRows = myGrid.getCheckedRows(getIndexById(myGrid, 'isDelCheck')).split(",");
	for(var i=0; i < checkRows.length; i++)
	{
		 var prRecListStatus = getValueById(myGrid, checkRows[i], 'prRecListStatus');
		 if(prRecListStatus == "C")
         {
			 cnt++;
			 myGrid.cells(checkRows[i], getIndexById(myGrid, 'isDelCheck')).setValue(0);
         }
	}
	
	if(cnt > 0)
	{
		//alertMessage1("입고완료된 데이터는 삭제되지 않습니다.");
		alertMessage1('<bean:message key="MESSAGE.MSG0153"/>');
		return;
	}
	
	// 삭제 시작
	var delArray = getDeletRows(myGrid, 'isDelCheck', 'prRecListId'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	
	maPttRecListForm.strutsAction.value = '<%=MaPttRecListAction.PTREC_LIST_DELETE%>';
	var url = contextPath + "/maPttRecList.do";
	$.post(url,FormQueryString(maPttRecListForm)+delArray , function(_data){
    	afterDelete();
    });
}

function afterDelete()
{
	goClose('maPttRecDetail');
	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	maPttRecListForm.elements['maPttRecCommonDTO.prRecListId'].value = "";
    excelServerAction("maPttRecList", maPttRecListForm ); 
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maPttRecList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maPttRecCommonDTO.prRecListId"/><!-- Key -->
<html:hidden property="maPttRecCommonDTO.filterDeptId"/>
<html:hidden property="maPttRecCommonDTO.filterInspector"/>
<html:hidden property="maPttRecCommonDTO.filterVendorId"/>
<html:hidden property="maPttRecCommonDTO.prRecStatus"/>
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
                    <label><bean:message key="LABEL.manageDept"/></label>
                    <div class="input_box">
                        <html:text property="maPttRecCommonDTO.filterDeptDesc" tabindex="10"/>
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
				<div class="field">
					<label><bean:message key="LABEL.recDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
	                        <html:text property="maPttRecCommonDTO.filterRecStartDate" tabindex="20" />
	                        <p class="open_calendar"><span>날짜</span></p>
	                    </div>
	                    <div class="input_box input_carendar">
	                        <html:text property="maPttRecCommonDTO.filterRecEndDate" tabindex="30" />
	                        <p class="open_calendar"><span>날짜</span></p>
	                    </div>
	                </div>
				</div>
                <!-- 관리자(정) -->
                <div class="field">
                    <label><bean:message key="LABEL.inspector"/></label>
                    <div class="input_box">
                        <html:text property="maPttRecCommonDTO.filterInspectorName" tabindex="40"/>
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
				<div class="field">
					<label><bean:message key="LABEL.ptNameSize"/></label>
					<div class="input_box">
						<html:text property="maPttRecCommonDTO.filterPartNameSize" tabindex="50"/>
					</div>
				</div>
	            <div class="field">
	                <label><bean:message key="LABEL.recVendor"/></label>
	                <div class="input_box">
	                    <html:text property="maPttRecCommonDTO.filterVendorDesc" tabindex="60" />
	                    <p class="open_spop">
	                        <a>
	                            <span>조회</span>
	                        </a>
	                    </p>
	                </div>
	            </div>
				<!-- 입고상태  -->
				<div class="field">
					<label><bean:message key="LABEL.ptRecListStatus"/></label>
					<div class="input_box">
						<html:text property="maPttRecCommonDTO.prRecStatusDesc" tabindex="70" />
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