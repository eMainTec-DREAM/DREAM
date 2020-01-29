<%--===========================================================================
목록
author  kim21017
version $Id: maPttDisList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.tool.adj.action.MaPttDisListAction" %>
<%@ page import="dream.tool.adj.action.MaPttDisDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 제목-->
<title><bean:message key="MENU.PTTDIS"/></title>
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
var ptDisStatusAc;
function loadPage() 
{
    initGrid();
    
    deptAc = new autoC({"maPttDisCommonDTO.filterDeptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "maPttDisCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.init();
    
    mainMngAc = new autoC({"maPttDisCommonDTO.filterExeName":"emp_name"});
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcResultMap({
        "maPttDisCommonDTO.filterExeBy":"emp_id"
    });
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "maPttDisCommonDTO.filterDeptId"
    });
    mainMngAc.init();
    
    partNameAc = new autoC({"maPttDisCommonDTO.filterPartNameSize":"full_desc"});
    partNameAc.setAcConditionMap({
	   "part_categ":"TOOL",
	   "comp_no":loginUser.compNo
	   });
    partNameAc.setTable("TAPARTS");
    partNameAc.init();
    
    wareHouseAc = new autoC({"maPttDisCommonDTO.filterWname":"wname"});
    wareHouseAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"wh_categ":"TOOL"
  	   });
    wareHouseAc.setTable("TAWAREHOUSE");
    wareHouseAc.setAcResultMap({
        "maPttDisCommonDTO.filterWcodeId":"wcode_id"
    });
    wareHouseAc.init();
    
    acSysDesc("maPttDisCommonDTO.ptDisStatusDesc","maPttDisCommonDTO.ptDisStatus","PTRTN_STATUS");

}

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,20);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen();
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	maPttDisListForm.elements['maPttDisCommonDTO.ptdisuselistId'].value = "";
        return sortColumn("maPttDisList", this, maPttDisListForm, "ptdisuselistId", ind, direction);
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	
	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maPttDisList.do";
    maPttDisListForm.elements['strutsAction'].value = '<%=MaPttDisListAction.PTDIS_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maPttDisListForm), "ptdisuselistId", "Y");

}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	maPttDisListForm.elements['maPttDisCommonDTO.ptdisuselistId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('Search', '<%=MaPttDisListAction.PTDIS_LIST_FIND%>');   
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
	maPttDisListForm.elements['maPttDisCommonDTO.ptdisuselistId'].value = getValueById(myGrid, selectedId,'ptdisuselistId');
	goCommonTabPage(maPttDisListForm, <%= MaPttDisDetailAction.PTDIS_DETAIL_INIT %>, pageId);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_ptdisuselistId)
{
	maPttDisListForm.elements['maPttDisCommonDTO.ptdisuselistId'].value = _ptdisuselistId;
	findGridList('ReloadRow');
	maPttDisListForm.elements['maPttDisCommonDTO.ptdisuselistId'].value = "";
}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('maPttDisDetail');	
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    maPttDisListForm.elements['maPttDisCommonDTO.ptdisuselistId'].value = getValueById(myGrid, selectedId,'ptdisuselistId');
    maPttDisListForm.elements['strutsAction'].value = '<%=MaPttDisDetailAction.PTDIS_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(maPttDisListForm), 'maPttDisDetail'); 
} 

 /**
  * 생성
  */
function goCreate()
{
	createValidationCheck(myGrid, "maPttDisDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maPttDisListForm.elements['maPttDisCommonDTO.ptdisuselistId'].value = "";
	goCommonTabPage(maPttDisListForm, '', pageId);
}


/**
 * Excel Export
 */
function goExcel()
{
	maPttDisListForm.elements['maPttDisCommonDTO.ptdisuselistId'].value = "";
    excelServerAction("maPttDisList", maPttDisListForm ); 
}

/**
 * 삭제
 */
function goDelete(){
	
	// 폐기완료 여부 체크 
	var cnt = 0;
	var checkRows = myGrid.getCheckedRows(getIndexById(myGrid, 'isDelCheck')).split(",");
	for(var i=0; i < checkRows.length; i++)
	{
		 var disStatus = getValueById(myGrid, checkRows[i], 'disStatus');
		 if(disStatus == "C")
         {
			 cnt++;
			 myGrid.cells(checkRows[i], getIndexById(myGrid, 'isDelCheck')).setValue(0);
         }
	}
	
	if(cnt > 0)
	{
		//alertMessage1("입고완료된 데이터는 삭제되지 않습니다.");
	}
	
	// 삭제 시작
	
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'ptdisuselistId'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maPttDisListForm.strutsAction.value = '<%=MaPttDisListAction.PTDIS_LIST_DELETE%>';
	var url = contextPath + "/maPttDisList.do";
	
    $.post(url,FormQueryString(maPttDisListForm)+delArray , function(_data){
    	afterDelete();
    });
}

  
function afterDelete(){
	goClose('maPttDisDetail');
	//goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
 }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maPttDisList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maPttDisCommonDTO.ptdisuselistId"/>
<html:hidden property="maPttDisCommonDTO.filterDeptId"/>

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
		<div class="article_box" >
			<div class="form_box">
				<div class="field">
                    <label><bean:message key="LABEL.exeDept"/></label>
                    <div class="input_box">
                        <html:text property="maPttDisCommonDTO.filterDeptDesc" tabindex="10"/>
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
				<div class="field">
					<label><bean:message key="LABEL.disUseDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
	                        <html:text property="maPttDisCommonDTO.filterDisStartDate" tabindex="20" />
	                        <p class="open_calendar"><span>날짜</span></p>
	                    </div>
	                    <div class="input_box input_carendar">
	                        <html:text property="maPttDisCommonDTO.filterDisEndDate" tabindex="30" />
	                        <p class="open_calendar"><span>날짜</span></p>
	                    </div>
	                </div>
				</div>
                <!-- 처리자 -->
                <div class="field">
                    <label><bean:message key="LABEL.exeBy"/></label>
                    <div class="input_box">
                        <html:text property="maPttDisCommonDTO.filterExeName" tabindex="40"/>
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
				<div class="field">
					<label><bean:message key="LABEL.ptNameSize"/></label>
					<div class="input_box">
						<html:text property="maPttDisCommonDTO.filterPartNameSize" tabindex="50"/>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.wcode"/></label>
					<div class="input_box">
	                     <html:text property="maPttDisCommonDTO.filterWname" tabindex="10"/>
			             <p class="open_spop">
			                 <a>
			                     <span>조회</span>
			                 </a>
			             </p>
	                </div>
                </div>
				<!-- 폐기상태  -->
				<div class="field">
					<label><bean:message key="LABEL.disUseStatus"/></label>
					<div class="input_box">
						<html:text property="maPttDisCommonDTO.ptDisStatusDesc" tabindex="70" />
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
		<div class="article_box" >
			<div class="grid_area">
				<div id="gridbox" style="height:270px; background-color:white;"></div>		
			</div>			
		</div>
	</div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>

