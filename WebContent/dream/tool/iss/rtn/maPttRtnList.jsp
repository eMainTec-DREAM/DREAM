<%--===========================================================================
공기구반납 - 목록
author  ssong
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.tool.iss.rtn.action.MaPttRtnListAction" %>
<%@ page import="dream.tool.iss.rtn.action.MaPttRtnDetailAction" %>
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
<!-- 공기구반납 -->
<title><bean:message key='MENU.PTTRTN'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var myGrid;
var deptAc;
var mainMngAc;
var partNameAc;
var wareHouseAc;
var ptRtnStatusAc;

function loadPage() 
{
	//설비작업현황 - 부품입고 팝업 시
	if(window.name=="RTNREQHDR"){
		
	}else{
		maPttRtnListForm.elements['maPttRtnCommonDTO.filterDeptId'].value   = loginUser.filterDeptId;
		maPttRtnListForm.elements['maPttRtnCommonDTO.filterDeptDesc'].value = loginUser.filterDeptDesc;
		
		maPttRtnListForm.elements['maPttRtnCommonDTO.filterRtnStartDate'].value = getMinusMonth2(new Date(), -2); 
	    maPttRtnListForm.elements['maPttRtnCommonDTO.filterRtnEndDate'].value   = getToday();
	}
    
    initGrid();
    
    deptAc = new autoC({"maPttRtnCommonDTO.filterDeptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "maPttRtnCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.init();
    
    mainMngAc = new autoC({"maPttRtnCommonDTO.filterRecName":"emp_name"});
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcResultMap({
        "maPttRtnCommonDTO.filterRecBy":"emp_id"
    });
    mainMngAc.init();
    
    partNameAc = new autoC({"maPttRtnCommonDTO.filterPartNameSize":"full_desc"});
    partNameAc.setAcConditionMap({
	   "part_categ":"TOOL",
	   "comp_no":loginUser.compNo
	   });
    partNameAc.setTable("TAPARTS");
    partNameAc.init();
    
    wareHouseAc = new autoC({"maPttRtnCommonDTO.filterWname":"wname"});
    wareHouseAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"wh_categ":"TOOL"
  	   });
    wareHouseAc.setTable("TAWAREHOUSE");
    wareHouseAc.setAcResultMap({
        "maPttRtnCommonDTO.filterWcodeId":"wcode_id"
    });
    wareHouseAc.init();
    
    acSysDesc("maPttRtnCommonDTO.ptRtnStatusDesc","maPttRtnCommonDTO.ptRtnStatus","PTRTN_STATUS");

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
    	maPttRtnListForm.elements['maPttRtnCommonDTO.ptRtnListId'].value = "";
        return sortColumn("maPttRtnList", this, maPttRtnListForm, "PTRTNLISTID", ind, direction);
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maPttRtnList.do";

    maPttRtnListForm.elements['strutsAction'].value = '<%=MaPttRtnListAction.PTRTN_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maPttRtnListForm), "PTRTNLISTID","Y");
    
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_ptRtnListId)
{
	maPttRtnListForm.elements['maPttRtnCommonDTO.ptRtnListId'].value = _ptRtnListId;
	findGridList('ReloadRow');
	maPttRtnListForm.elements['maPttRtnCommonDTO.ptRtnListId'].value = "";
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	maPttRtnListForm.elements['maPttRtnCommonDTO.ptRtnListId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('Search', '<%=MaPttRtnListAction.PTRTN_LIST_FIND%>');   
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
	var form = document.maPttRtnListForm;
	form.elements['maPttRtnCommonDTO.ptRtnListId'].value = getValueById(myGrid, selectedId, 'ptRtnListId');
	goCommonTabPage(form, <%= MaPttRtnDetailAction.PTRTN_DETAIL_INIT %>, pageId);
}

/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('maPttRtnDetail');	
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    maPttRtnListForm.elements['maPttRtnCommonDTO.ptRtnListId'].value = getValueById(myGrid, selectedId, 'ptRtnListId');
    maPttRtnListForm.elements['strutsAction'].value = '<%=MaPttRtnDetailAction.PTRTN_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(maPttRtnListForm), 'maPttRtnDetail'); 
} 
 
/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "maPttRtnDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maPttRtnListForm.elements['maPttRtnCommonDTO.ptRtnListId'].value = "";
	goCommonTabPage(maPttRtnListForm, '', pageId);
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
		 var rtnStatus = getValueById(myGrid, checkRows[i], 'rtnStatus');
		 if(rtnStatus == "C")
         {
			 cnt++;
			 myGrid.cells(checkRows[i], getIndexById(myGrid, 'isDelCheck')).setValue(0);
         }
	}
	
	if(cnt > 0)
	{
		//alertMessage1("입고완료된 데이터는 삭제되지 않습니다.");
		alertMessage1("반납완료된 데이터는 삭제할 수 없습니다.");
		return;
	}
	
	// 삭제 시작
	var delArray = getDeletRows(myGrid, 'isDelCheck', 'ptRtnListId'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	
	maPttRtnListForm.strutsAction.value = '<%=MaPttRtnListAction.PTRTN_LIST_DELETE%>';
	var url = contextPath + "/maPttRtnList.do";
	$.post(url,FormQueryString(maPttRtnListForm)+delArray , function(_data){
    	afterDelete();
    });
}

function afterDelete()
{
	goClose('maPttRtnDetail');
	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	maPttRtnListForm.elements['maPttRtnCommonDTO.ptRtnListId'].value = "";
    excelServerAction("maPttRtnList", maPttRtnListForm ); 
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maPttRtnList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maPttRtnCommonDTO.ptRtnListId"/><!-- Key -->
<html:hidden property="maPttRtnCommonDTO.filterDeptId"/>
<html:hidden property="maPttRtnCommonDTO.filterRecBy"/>
<html:hidden property="maPttRtnCommonDTO.ptRtnStatus"/>
<html:hidden property="maPttRtnCommonDTO.filterWcodeId"/>
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
                        <html:text property="maPttRtnCommonDTO.filterDeptDesc" tabindex="10"/>
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
				<div class="field">
					<label><bean:message key="LABEL.rtnDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
	                        <html:text property="maPttRtnCommonDTO.filterRtnStartDate" tabindex="20" />
	                        <p class="open_calendar"><span>날짜</span></p>
	                    </div>
	                    <div class="input_box input_carendar">
	                        <html:text property="maPttRtnCommonDTO.filterRtnEndDate" tabindex="30" />
	                        <p class="open_calendar"><span>날짜</span></p>
	                    </div>
	                </div>
				</div>
                <!-- 수령자 -->
                <div class="field">
                    <label><bean:message key="LABEL.recBy"/></label>
                    <div class="input_box">
                        <html:text property="maPttRtnCommonDTO.filterRecName" tabindex="40"/>
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
				<div class="field">
					<label><bean:message key="LABEL.ptNameSize"/></label>
					<div class="input_box">
						<html:text property="maPttRtnCommonDTO.filterPartNameSize" tabindex="50"/>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.rtnWcode"/></label>
					<div class="input_box">
	                     <html:text property="maPttRtnCommonDTO.filterWname" tabindex="10" />
			             <p class="open_spop">
			                 <a>
			                     <span>조회</span>
			                 </a>
			             </p>
	                </div>
                </div>
				<!-- 출고상태  -->
				<div class="field">
					<label><bean:message key="LABEL.ptRtnStatus"/></label>
					<div class="input_box">
						<html:text property="maPttRtnCommonDTO.ptRtnStatusDesc" tabindex="70" />
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