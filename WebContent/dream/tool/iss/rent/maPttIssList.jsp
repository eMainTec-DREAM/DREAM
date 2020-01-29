<%--===========================================================================
구매입고 - 목록
author  ssong
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.tool.iss.rent.action.MaPttIssListAction" %>
<%@ page import="dream.tool.iss.rent.action.MaPttIssDetailAction" %>
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
<title><bean:message key='MENU.PTTISS'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var myGrid;
var deptAc;
var partNameAc;
var wareHouseAc;
var ptIssStatusAc;
function loadPage() 
{
	//설비작업현황 - 부품입고 팝업 시
	if(window.name=="CHART_PT_LIST_POPUP"){
		if(M$('maPttIssCommonDTO.prRecStatus').value!='')
		valSysDirCode('maPttIssCommonDTO.prRecStatus', 'maPttIssCommonDTO.prRecStatusDesc', 'PRRECLIST_STATUS','', true);
	}else{
		maPttIssListForm.elements['maPttIssCommonDTO.filterDeptId'].value   = loginUser.filterDeptId;
		maPttIssListForm.elements['maPttIssCommonDTO.filterDeptDesc'].value = loginUser.filterDeptDesc;
		
		maPttIssListForm.elements['maPttIssCommonDTO.filterIssStartDate'].value = getMinusMonth2(new Date(), -2); 
	    maPttIssListForm.elements['maPttIssCommonDTO.filterIssEndDate'].value   = getToday();
	}
    
    initGrid();
    
    deptAc = new autoC({"maPttIssCommonDTO.filterDeptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "maPttIssCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.init();
    
    mainMngAc = new autoC({"maPttIssCommonDTO.filterRecName":"emp_name"});
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcResultMap({
        "maPttIssCommonDTO.filterRecBy":"emp_id"
    });
    mainMngAc.init();
    
    partNameAc = new autoC({"maPttIssCommonDTO.filterPartNameSize":"full_desc"});
    partNameAc.setAcConditionMap({
	   "part_categ":"TOOL",
	   "comp_no":loginUser.compNo
	   });
    partNameAc.setTable("TAPARTS");
    partNameAc.init();
    
    wareHouseAc = new autoC({"maPttIssCommonDTO.filterWname":"wname"});
    wareHouseAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"wh_categ":"TOOL"
  	   });
    wareHouseAc.setTable("TAWAREHOUSE");
    wareHouseAc.setAcResultMap({
        "maPttIssCommonDTO.filterWcodeId":"wcode_id"
    });
    wareHouseAc.init();
    
    acSysDesc("maPttIssCommonDTO.ptIssStatusDesc","maPttIssCommonDTO.ptIssStatus","PTISS_STATUS");
    
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
    	maPttIssListForm.elements['maPttIssCommonDTO.ptIssListId'].value = "";
        return sortColumn("maPttIssList", this, maPttIssListForm, "PTISSLISTID", ind, direction);
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maPttIssList.do";

    maPttIssListForm.elements['strutsAction'].value = '<%=MaPttIssListAction.PTISS_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maPttIssListForm), "PTISSLISTID", "Y");
    
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_ptIssListId)
{
	maPttIssListForm.elements['maPttIssCommonDTO.ptIssListId'].value = _ptIssListId;
	findGridList('ReloadRow');
	maPttIssListForm.elements['maPttIssCommonDTO.ptIssListId'].value = "";
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	maPttIssListForm.elements['maPttIssCommonDTO.ptIssListId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('Search', '<%=MaPttIssListAction.PTISS_LIST_FIND%>');   
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
	var form = document.maPttIssListForm;
	form.elements['maPttIssCommonDTO.ptIssListId'].value = getValueById(myGrid, selectedId, 'ptIssListId');
	goCommonTabPage(form, <%= MaPttIssDetailAction.PTISS_DETAIL_INIT %>, pageId);
}

/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('maPttIssDetail');	
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    maPttIssListForm.elements['maPttIssCommonDTO.ptIssListId'].value = getValueById(myGrid, selectedId, 'ptIssListId');
    maPttIssListForm.elements['strutsAction'].value = '<%=MaPttIssDetailAction.PTISS_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(maPttIssListForm), 'maPttIssDetail'); 
} 
 
/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "maPttIssDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maPttIssListForm.elements['maPttIssCommonDTO.ptIssListId'].value = "";
	goCommonTabPage(maPttIssListForm, '', pageId);
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
		 var issStatus = getValueById(myGrid, checkRows[i], 'issStatus');
		 if(issStatus == "C")
         {
			 cnt++;
			 myGrid.cells(checkRows[i], getIndexById(myGrid, 'isDelCheck')).setValue(0);
         }
	}
	
	if(cnt > 0)
	{
		//alertMessage1("입고완료된 데이터는 삭제되지 않습니다.");
		alertMessage1("출고완료된 데이터는 삭제할 수 없습니다.");
		return;
	}
	
	// 삭제 시작
	var delArray = getDeletRows(myGrid, 'isDelCheck', 'ptIssListId'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	
	maPttIssListForm.strutsAction.value = '<%=MaPttIssListAction.PTISS_LIST_DELETE%>';
	var url = contextPath + "/maPttIssList.do";
	$.post(url,FormQueryString(maPttIssListForm)+delArray , function(_data){
    	afterDelete();
    });
}

function afterDelete()
{
	goClose('maPttIssDetail');
	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	maPttIssListForm.elements['maPttIssCommonDTO.ptIssListId'].value = "";
    excelServerAction("maPttIssList", maPttIssListForm ); 
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maPttIssList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maPttIssCommonDTO.ptIssListId"/><!-- Key -->
<html:hidden property="maPttIssCommonDTO.filterDeptId"/>
<html:hidden property="maPttIssCommonDTO.filterRecBy"/>
<html:hidden property="maPttIssCommonDTO.ptIssStatus"/>
<html:hidden property="maPttIssCommonDTO.filterWcodeId"/>
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
                        <html:text property="maPttIssCommonDTO.filterDeptDesc" tabindex="10"/>
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
				<div class="field">
					<label><bean:message key="LABEL.issDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
	                        <html:text property="maPttIssCommonDTO.filterIssStartDate" tabindex="20" />
	                        <p class="open_calendar"><span>날짜</span></p>
	                    </div>
	                    <div class="input_box input_carendar">
	                        <html:text property="maPttIssCommonDTO.filterIssEndDate" tabindex="30" />
	                        <p class="open_calendar"><span>날짜</span></p>
	                    </div>
	                </div>
				</div>
                <!-- 수령자 -->
                <div class="field">
                    <label><bean:message key="LABEL.recBy"/></label>
                    <div class="input_box">
                        <html:text property="maPttIssCommonDTO.filterRecName" tabindex="40"/>
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
				<div class="field">
					<label><bean:message key="LABEL.ptNameSize"/></label>
					<div class="input_box">
						<html:text property="maPttIssCommonDTO.filterPartNameSize" tabindex="50"/>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.issWcode"/></label>
					<div class="input_box">
	                     <html:text property="maPttIssCommonDTO.filterWname" tabindex="10" />
			             <p class="open_spop">
			                 <a>
			                     <span>조회</span>
			                 </a>
			             </p>
	                </div>
                </div>
				<!-- 출고상태  -->
				<div class="field">
					<label><bean:message key="LABEL.ptIssListStatus"/></label>
					<div class="input_box">
						<html:text property="maPttIssCommonDTO.ptIssStatusDesc" tabindex="70" />
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