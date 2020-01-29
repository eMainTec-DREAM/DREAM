<%--===========================================================================
안전작업 - 목록
author  syyang
version $Id: workLetList.jsp,v 1.1 2015/12/03 01:45:27 syyang Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.let.action.WorkLetListAction" %>
<%@ page import="dream.work.let.action.WorkLetDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/functions.tld" prefix="udf"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="common.bean.User"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 

%>
<html>
<head>
<!-- 안전작업 -->
<title><bean:message key='MENU.WOLETLIST'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var myGrid;
var beforePageId = '';
/** 자동완성 변수 */
var reqDeptAc;
var reqByAc;
var letDeptAc;
var letByAc;
var plantAc;
var woNoAc;
 
function loadPage() 
{
	//작업기간 From
	if(workLetListForm.elements['workLetCommonDTO.filterFromStartDate'].value == "")
	{
		workLetListForm.elements['workLetCommonDTO.filterFromStartDate'].value = getMinusMonth2(new Date(), -1);
		workLetListForm.elements['workLetCommonDTO.filterFromEndDate'].value   = getToday();
	}
	else if(workLetListForm.elements['workLetCommonDTO.filterFromStartDate'].value == "-1")
	{
		workLetListForm.elements['workLetCommonDTO.filterFromStartDate'].value   = "";
	}
	//작업기간 To
	if(workLetListForm.elements['workLetCommonDTO.filterToStartDate'].value == "")
	{
		workLetListForm.elements['workLetCommonDTO.filterToStartDate'].value = getMinusMonth2(new Date(), -1);
		workLetListForm.elements['workLetCommonDTO.filterToEndDate'].value   = getToday();
	}
	else if(workLetListForm.elements['workLetCommonDTO.filterFromStartDate'].value == "-1")
	{
		workLetListForm.elements['workLetCommonDTO.filterToStartDate'].value   = "";
	}
		
    //공장명
    if(loginUser.filterPlant!='null' && loginUser.filterPlant!=''){
       	workLetListForm.elements['workLetCommonDTO.filterPlant'].value  = loginUser.filterPlant;
       	workLetListForm.elements['workLetCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
	}
	
    initGrid();

    //신청부서
    reqDeptAc = new autoC({"workLetCommonDTO.filterReqDeptDesc":"description"});
    reqDeptAc.setAcDisplay("DESCRIPTION");
    reqDeptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
	});
    reqDeptAc.setTable("TADEPT");
    reqDeptAc.setAcResultMap({
        "workLetCommonDTO.filterReqDeptId":"dept_id"
    });
    reqDeptAc.setAcDConditionMap({
    	"plant" : "workLetCommonDTO.filterPlant"
    });
    reqDeptAc.init();

    //신청자
    reqByAc = new autoC({"workLetCommonDTO.filterReqByDesc":"emp_name"});
    reqByAc.setAcDisplay("EMP_NAME");
    reqByAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    reqByAc.setTable("TAEMP");
    reqByAc.setAcResultMap({
        "workLetCommonDTO.filterReqById":"emp_id"
    });
    reqByAc.setAcDConditionMap({
    	"dept_id" : "workLetCommonDTO.filterReqDeptId",
    	"plant" : "workLetCommonDTO.filterPlant"
    });
    reqByAc.init();
    
    //허가부서
    letDeptAc = new autoC({"workLetCommonDTO.filterRecDeptDesc":"description"});
    letDeptAc.setAcDisplay("DESCRIPTION");
    letDeptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
	});
    letDeptAc.setTable("TADEPT");
    letDeptAc.setAcResultMap({
        "workLetCommonDTO.filterRecDeptId":"dept_id"
    });
    letDeptAc.setAcDConditionMap({
    	"plant" : "workLetCommonDTO.filterPlant"
    });
    letDeptAc.init();

    //허가자
    letByAc = new autoC({"workLetCommonDTO.filterRecByDesc":"emp_name"});
    letByAc.setAcDisplay("EMP_NAME");
    letByAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    letByAc.setTable("TAEMP");
    letByAc.setAcResultMap({
        "workLetCommonDTO.filterRecById":"emp_id"
    });
    letByAc.setAcDConditionMap({
    	"dept_id" : "workLetCommonDTO.filterRecDeptId",
    	"plant" : "workLetCommonDTO.filterPlant"
    });
    letByAc.init();
    
 	// 공장코드
	plantAc = new autoC({"workLetCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "workLetCommonDTO.filterPlant":"plant"
    });
    plantAc.init();
    
 	// W/O#
    woNoAc = new autoC({"workLetCommonDTO.filterWoNo":"woNo"});
    woNoAc.setTable("TAWORKORDER");
    woNoAc.setAcResultMap({
        "workLetCommonDTO.filterWkorId":"wkorId"
    }); 
    woNoAc.setKeyName("workLetCommonDTO.filterWkorId"); 
    woNoAc.init();
    
}

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen(rowId);
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	workLetListForm.elements['workLetCommonDTO.woLetId'].value = "";
    	return sortColumn("workLetList", this, workLetListForm, "WOLETID", ind, direction);
	});
	
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
	myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/workLetList.do";

    workLetListForm.elements['strutsAction'].value = '<%=WorkLetListAction.WO_LET_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workLetListForm), "WOLETID", "Y");
}

/**
 * 엑셀 다운.
 */
function goExcel()
{
	workLetListForm.elements['workLetCommonDTO.woLetId'].value = "";
	excelServerAction("workLetList", workLetListForm );
 	//excelAction(myGrid);
}
 
/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	workLetListForm.elements['workLetCommonDTO.woLetId'].value = "";	// 검색시 Tab 이동Key Clear
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
	var form = document.workLetListForm;
	 
	form.elements['workLetCommonDTO.woLetId'].value = getValueById(myGrid, selectedId,'WOLETID');
	goCommonTabPage(form, <%= WorkLetDetailAction.WO_LET_DETAIL_INIT %>, pageId,beforePageId);

	beforePageId = pageId;
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_woLetId)
{
	workLetListForm.elements['workLetCommonDTO.woLetId'].value = _woLetId;
	findGridList('ReloadRow');
	workLetListForm.elements['workLetCommonDTO.woLetId'].value = "";
}

/**
 * 상세열기
 */
function goOpen(rowId)
{
    goTabPage('workLetDetail');	
}

/**
 * 상세열기 버튼
 */
function goOpenAction()
{
	var selectedId=myGrid.getSelectedRowId();
	if(selectedId == null) return;
	
	workLetListForm.elements['workLetCommonDTO.woLetId'].value = getValueById(myGrid, selectedId,'WOLETID');
	workLetListForm.elements['strutsAction'].value = '<%=WorkLetDetailAction.WO_LET_DETAIL_INIT%>';
	openQuickTabPage(FormQueryString(workLetListForm), 'workLetDetail'); 
	
} 
 
/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "workLetDetail" , "goCreateAction");
}
 
function goCreateAction(pageId)
{
	workLetListForm.elements['workLetCommonDTO.woLetId'].value = "";
    goCommonTabPage(workLetListForm, '', pageId);

}
 
/**
 * 삭제
 */
function goDelete()
{
	var chkedRowsId = getCheckedRows(myGrid, 'ISDELCHECK');
	for(var i = 0 ;i < chkedRowsId.length; i++)
	{
		if(getValueById(myGrid, chkedRowsId[i], "WOLETSTATUS") == "COM")
		{
			alertMessage1('<bean:message key="LABEL.delCompWork"/>'); //delCompWork
			return;
		}
	}
	
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'WOLETID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	
	workLetListForm.strutsAction.value = '<%=WorkLetListAction.WO_LET_LIST_DELETE%>';
	var url = contextPath + "/workLetList.do";
	$.post(url,FormQueryString(workLetListForm)+delArray , function(_data){
    	afterDelete();
    }); 
}
function afterDelete()
{
	goClose('workLetDetail');
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Print 버튼 클릭
 */
function goPrint()
{
	
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workLetList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="workLetCommonDTO.woLetId"/><!-- Key -->
<html:hidden property="workLetCommonDTO.filterReqDeptId"/>
<html:hidden property="workLetCommonDTO.filterReqById"/>
<html:hidden property="workLetCommonDTO.filterRecDeptId"/>
<html:hidden property="workLetCommonDTO.filterRecById"/>
<html:hidden property="workLetCommonDTO.filterPlant"/>
<html:hidden property="workLetCommonDTO.filterWkorId"/>
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
				<!-- 안전작업 명 -->
				<div class="field">
					<label><bean:message key="LABEL.woLetName"/></label>
					<div class="input_box">
						<html:text property="workLetCommonDTO.filterWoLetDesc" tabindex="10"/>
					</div>
				</div>
				<!-- 안전작업 번호 -->
				<div class="field">
					<label><bean:message key="LABEL.woLetNo"/></label>
					<div class="input_box">
						<html:text property="workLetCommonDTO.filterWoLetNo" tabindex="20"/>
					</div>
				</div>
				<!-- 신청부서 -->
				<div class="field">
					<label><bean:message key="LABEL.reqDept"/></label>
					<div class="input_box">
						<html:text property="workLetCommonDTO.filterReqDeptDesc" tabindex="30"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 신청자 -->
				<div class="field">
					<label><bean:message key="LABEL.reqBy"/></label>
					<div class="input_box">
						<html:text property="workLetCommonDTO.filterReqByDesc" tabindex="40" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 허가부서 -->
				<div class="field">
					<label><bean:message key="LABEL.letDept"/></label>
					<div class="input_box">
						<html:text property="workLetCommonDTO.filterRecDeptDesc" tabindex="50"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 허가자 -->
				<div class="field">
					<label><bean:message key="LABEL.letBy"/></label>
					<div class="input_box">
						<html:text property="workLetCommonDTO.filterRecByDesc" tabindex="60" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 설비/기기 -->
				<div class="field">
					<label><bean:message key="LABEL.itemDesc"/></label>
					<div class="input_box">
	                    <html:text property="workLetCommonDTO.filterItemDesc" tabindex="70" />
	                </div>
				</div>
				<!-- 작업장소 -->
				<div class="field">
					<label><bean:message key="LABEL.woLocation"/></label>
					<div class="input_box">
	                    <html:text property="workLetCommonDTO.filterPlace" tabindex="80" />
	                </div>
				</div>
				<!-- 공장명  -->
                <div class="field">
                    <label><bean:message key="LABEL.plant"/></label>
                    <div class="input_box">
						<html:text property="workLetCommonDTO.filterPlantDesc" tabindex="90" />
						<p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
				<!-- 작업 번호 -->
				<div class="field">
					<label><bean:message key="LABEL.woNo"/></label>
					<div class="input_box">
						<html:text property="workLetCommonDTO.filterWoNo" tabindex="80"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>  
				<!-- 작업기간 From -->
				<div class="field">
					<label><bean:message key="LABEL.woPeriodFrom"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="workLetCommonDTO.filterFromStartDate" tabindex="100" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="workLetCommonDTO.filterFromEndDate" tabindex="110" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 작업기간 To -->
				<div class="field">
					<label><bean:message key="LABEL.woPeriodTo"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="workLetCommonDTO.filterToStartDate" tabindex="120" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="workLetCommonDTO.filterToEndDate" tabindex="130" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
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