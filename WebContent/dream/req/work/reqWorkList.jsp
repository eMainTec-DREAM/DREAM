<%--===========================================================================
 - 목록
author  kim21017
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.req.work.action.ReqWorkListAction" %>
<%@ page import="dream.req.work.action.ReqWorkDetailAction" %>
<%@ page import="dream.req.work.action.MaWoReqTypeSelectAction" %>
<%@ page import="common.util.CommonUtil"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/functions.tld" prefix="udf"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="common.bean.User"%>
<%
    User loginUser = (User)request.getSession().getAttribute(request.getSession().getId());
%>
<html>
<head>
<!-- 작업요청 -->
<title><bean:message key='MENU.WOREQLIST'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->

<script language="javascript">
<!--//

var myGrid;
var selectedWoReqId='';
var beforePageId = '';

/** 자동완성 변수 */
var woStatusAc;
var equipDescAc;
var eqLocDescAc;
var mainMngAc;
var deptAc;
var plantAc;
var reqPlantAc;
var recDeptAc;
var recEmpAc;
var recWkCtrAc;

function loadPage()
{
	if(_isFromNotice != "Y" && window.name != "LINKED_POPUP")
	{
		//담당자 
		reqWorkListForm.elements['reqWorkCommonDTO.filterReqEmpId'].value = loginUser.filterEmpId;
		reqWorkListForm.elements['reqWorkCommonDTO.filterReqEmpDesc'].value = loginUser.filterEmpDesc;
		
		reqWorkListForm.elements['reqWorkCommonDTO.filterReqStartDate'].value = getMinusMonth2(new Date(), -1);
	    reqWorkListForm.elements['reqWorkCommonDTO.filterReqEndDate'].value   = getToday();
	    
	  	//요청부서
	    if(loginUser.filterDeptId!='null'){
	    	reqWorkListForm.elements['reqWorkCommonDTO.filterReqDeptId'].value  = loginUser.filterDeptId;
	    	reqWorkListForm.elements['reqWorkCommonDTO.filterReqDeptDesc'].value  = loginUser.filterDeptDesc;
		}
	  
	  	//요청자
	    if(loginUser.filterEmpId!='null'){
	    	reqWorkListForm.elements['reqWorkCommonDTO.filterReqEmpId'].value  = loginUser.filterEmpId;
	    	reqWorkListForm.elements['reqWorkCommonDTO.filterReqEmpDesc'].value  = loginUser.filterEmpDesc;
		}
	  
	  	
	  	//공장
	    if(loginUser.filterPlant!='null'){
	    	reqWorkListForm.elements['reqWorkCommonDTO.filterReqPlant'].value  = loginUser.filterPlant;
	    	reqWorkListForm.elements['reqWorkCommonDTO.filterReqPlantDesc'].value  = loginUser.filterPlantDesc;
		}
	}
	else {
		valSysDir('reqWorkCommonDTO.filterWoReqStatusId', 'reqWorkCommonDTO.filterWoReqStatusDesc', 'WOREQ_STATUS', true);
	}

    initGrid();

    /** 진행상태  */
    acSysDesc("reqWorkCommonDTO.filterWoReqStatusDesc","reqWorkCommonDTO.filterWoReqStatusId","WOREQ_STATUS");
    /** 우선순위  */
    acSysDesc("reqWorkCommonDTO.filterReqPriorityDesc","reqWorkCommonDTO.filterReqPriorityId","REQ_PRIORITY");
    
    /*설비  */
    equipDescAc = new autoC({"reqWorkCommonDTO.filterEquipDesc":"description"});
    equipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcDConditionMap({
    	"eqloc_id" : "reqWorkCommonDTO.filterEqLocId",
    	"dept_id" : "reqWorkCommonDTO.filterReqDeptId",
    	"plant" : "reqWorkCommonDTO.filterPlantId"
    });
    equipDescAc.init();

    /*설비위치 */
    eqLocDescAc = new autoC({"reqWorkCommonDTO.filterEqLocDesc":"full_desc"});
    eqLocDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setAcResultMap({
        "reqWorkCommonDTO.filterEqLocId":"eqloc_id"
    });
    eqLocDescAc.setAcDConditionMap({
    	"plant" : "reqWorkCommonDTO.filterPlantId"
    });
    eqLocDescAc.init();

    /** 요청자  */
    mainMngAc = new autoC({"reqWorkCommonDTO.filterReqEmpDesc":"emp_name"});
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcResultMap({
        "reqWorkCommonDTO.filterReqEmpId":"emp_id"
    });
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "reqWorkCommonDTO.filterReqDeptId",
    	"plant" : "reqWorkCommonDTO.filterPlantId"
    });
    mainMngAc.init();

    /** 요청부서  */
    deptAc = new autoC({"reqWorkCommonDTO.filterReqDeptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "reqWorkCommonDTO.filterReqDeptId":"dept_id"
    });
    deptAc.setAcDConditionMap({
    	"plant" : "reqWorkCommonDTO.filterPlantId"
    });
    deptAc.init();
    
    /** 접수공장  */
    plantAc = new autoC({"reqWorkCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "reqWorkCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();

    /** 처리 부서  */
    recDeptAc = new autoC({"reqWorkCommonDTO.filterRecDeptDesc":"description"});
    recDeptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    recDeptAc.setTable("TADEPT");
    recDeptAc.setAcResultMap({
        "reqWorkCommonDTO.filterRecDeptId":"dept_id"
    });
    recDeptAc.setAcDConditionMap({
    	"plant" : "reqWorkCommonDTO.filterPlantId"
    });
    recDeptAc.init();

    /** 처리자  */
    recEmpAc = new autoC({"reqWorkCommonDTO.filterRecEmpDesc":"emp_name"});
    recEmpAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    recEmpAc.setTable("TAEMP");
    recEmpAc.setAcResultMap({
        "reqWorkCommonDTO.filterRecEmpId":"emp_id"
    });
    recEmpAc.setAcDConditionMap({
    	"dept_id" : "reqWorkCommonDTO.filterRecDeptId",
    	"plant" : "reqWorkCommonDTO.filterPlantId"
    });
    recEmpAc.init();
    
    /** 처리작업그룹  */
    recWkCtrAc = new autoC({"reqWorkCommonDTO.filterRecWkCtrDesc":"description"});
    recWkCtrAc.setKeyName("reqWorkCommonDTO.filterRecWkCtrId");
    recWkCtrAc.setAcConditionMap({
        "comp_no":loginUser.compNo
       });
    recWkCtrAc.setTable("TAWKCTR");
    recWkCtrAc.setAcResultMap({
        "reqWorkCommonDTO.filterRecWkCtrId":"wkctr_id"
    });
    recWkCtrAc.init();
    /** 공장  */
    reqPlantAc = new autoC({"reqWorkCommonDTO.filterReqPlantDesc":"description"});
    reqPlantAc.setTable("TAPLANT");
    reqPlantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    reqPlantAc.setAcResultMap({
        "reqWorkCommonDTO.filterReqPlant":"plant"
    });
    reqPlantAc.init();
    
}

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');

	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		selectedWoReqId = rowId;
		goOpen(rowId);
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	reqWorkListForm.elements['reqWorkCommonDTO.woReqId'].value = "";
    	return sortColumn("reqWorkList", this, reqWorkListForm, "WOREQID", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/reqWorkList.do";

    reqWorkListForm.elements['strutsAction'].value = '<%=ReqWorkListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(reqWorkListForm), "woReqId", "Y");

}

/**
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_woReqId)
{
	reqWorkListForm.elements['reqWorkCommonDTO.woReqId'].value = _woReqId;
	findGridList('ReloadRow');
	reqWorkListForm.elements['reqWorkCommonDTO.woReqId'].value = "";
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	reqWorkListForm.elements['reqWorkCommonDTO.woReqId'].value = "";	// 검색시 Tab 이동Key Clear
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
	var form = document.reqWorkListForm;

	form.elements['reqWorkCommonDTO.woReqId'].value = getValueById(myGrid, selectedId, 'WOREQID');
	goCommonTabPage(form, <%= ReqWorkDetailAction.DETAIL_INIT %>, pageId, beforePageId);
	
	beforePageId = pageId;
}

/**
 * 상세열기
 */
function goOpen(rowId)
{
	var param  = getValueById(myGrid, rowId,'PARAM');
	reqWorkListForm.elements['reqWorkCommonDTO.selectedWoReqTypeId'].value = getValueById(myGrid, rowId,'WOREQTYPE');
	// 투자요청 만들어지기 전, 기존에 param값 안들어가 있는 데이터들은 수리요청
	if (param == null || param == '') {
		goTabPage('reqWorkDetail');
	} else {
		goTabPage(param);
	}
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    reqWorkListForm.elements['reqWorkCommonDTO.woReqId'].value = getValueById(myGrid, selectedId, 'WOREQID');
    reqWorkListForm.elements['strutsAction'].value = '<%=ReqWorkDetailAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(reqWorkListForm), 'reqWorkDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
	var woReqTypeArr = getSysCode("WOREQ_TYPE");
 	goClose(beforePageId);
	reqWorkListForm.elements['reqWorkCommonDTO.woReqId'].value = ""; 
	
	// WOREQ_TYPE의 is_use값이 Y인게 2 이상일 때만 선택창
	if (woReqTypeArr.length >= 2) {
		openSelectType();
	} else {
		createValidationCheck(myGrid, woReqTypeArr[0].PARAM1 , "goCreateAction");
	}
	
}

/**
 * 작업요청유형 선택창 열기
 */
function openSelectType(){

	width  = 550;
	height = 540;
	sleft = (screen.width - width) / 2;
	stop = (screen.height - height) / 2;
	features = "left=" + sleft + ",top=" + stop;
	
	var param = "strutsAction=" + "<%=MaWoReqTypeSelectAction.WOREQTYPE_SELECT_DEFAULT%>";

	openLayerPopup("maWoReqTypeSelect", param);

}


function goCreateAction(pageId)
{
	reqWorkListForm.elements['reqWorkCommonDTO.woReqId'].value = "";
	goCommonTabPage(reqWorkListForm, '', pageId);
}


function setAfterSelect(returnArray){
	var woReqType = returnArray[0];
	var param2  = returnArray[1];
	reqWorkListForm.elements['reqWorkCommonDTO.selectedWoReqTypeId'].value = woReqType;
	beforePageId = param2;
	goCommonTabPage(reqWorkListForm, '', param2);
}

/**
 * Excel Export
 */
function goExcel()
{
	reqWorkListForm.elements['reqWorkCommonDTO.woReqId'].value = "";
    excelServerAction(currentPageId, reqWorkListForm);
}

/**
 * 삭제
 */
function goDelete(){
	var url = contextPath + "/reqWorkList.do";
	reqWorkListForm.strutsAction.value = '<%=ReqWorkListAction.LIST_DELETE%>';
	goDeleteAction(myGrid, url, FormQueryString(reqWorkListForm), 'ISDELCHECK', 'WOREQID');
}

/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = reqWorkListForm.elements['reqWorkCommonDTO.woReqId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/reqWorkList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="reqWorkCommonDTO.woReqId"/><!-- Key -->
<html:hidden property="reqWorkCommonDTO.filterWoReqStatusId"/>
<html:hidden property="reqWorkCommonDTO.filterEquipId"/>
<html:hidden property="reqWorkCommonDTO.filterEqLocId"/>
<html:hidden property="reqWorkCommonDTO.filterReqEmpId"/>
<html:hidden property="reqWorkCommonDTO.filterReqPlant"/>
<html:hidden property="reqWorkCommonDTO.filterReqDeptId"/>
<html:hidden property="reqWorkCommonDTO.filterPlantId"/>
<html:hidden property="reqWorkCommonDTO.filterRecEmpId"/>
<html:hidden property="reqWorkCommonDTO.filterRecDeptId"/>
<html:hidden property="reqWorkCommonDTO.filterRecWkCtrId"/>
<html:hidden property="reqWorkCommonDTO.notWoReqStatusId"/>
<html:hidden property="reqWorkCommonDTO.selectedWoReqTypeId"/>
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
				<!-- 요청일자 -->
				<div class="field">
					<label><bean:message key="LABEL.appReqDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="reqWorkCommonDTO.filterReqStartDate" tabindex="10" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="reqWorkCommonDTO.filterReqEndDate" tabindex="20" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 진행상태  -->
				<div class="field">
					<label><bean:message key="LABEL.proStatus"/></label>
					<div class="input_box">
						<html:text property="reqWorkCommonDTO.filterWoReqStatusDesc" tabindex="30"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 제목 -->
				<div class="field">
					<label><bean:message key="LABEL.title"/></label>
					<div class="input_box">
						<html:text property="reqWorkCommonDTO.filterWoReqDesc" tabindex="40"/>
					</div>
				</div>
				<!-- 요청내용 -->
				<div class="field">
					<label><bean:message key="LABEL.request"/></label>
					<div class="input_box">
						<html:text property="reqWorkCommonDTO.filterRequest" tabindex="50"/>
					</div>
				</div>
				<!--설비 -->
				<div class="field">
					<label><bean:message key="LABEL.equipDesc"/></label>
					<div class="input_box">
						<html:text property="reqWorkCommonDTO.filterEquipDesc" tabindex="60"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 공장  -->
				<div class="field">
				    <label><bean:message key="LABEL.plant"/></label>
				    <div class="input_box">
				        <html:text property="reqWorkCommonDTO.filterReqPlantDesc" tabindex="65"/>
				        <p class="open_spop"><a><span>조회</span></a></p>
				    </div>
				</div>
				<!-- 위치 -->
				<div class="field">
					<label><bean:message key="LABEL.location"/></label>
					<div class="input_box">
						<html:text property="reqWorkCommonDTO.filterEqLocDesc" tabindex="70"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 요청부서 -->
				<div class="field">
					<label><bean:message key="LABEL.woReqDept"/></label>
					<div class="input_box">
						<html:text property="reqWorkCommonDTO.filterReqDeptDesc" tabindex="80"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 요청자 -->
				<div class="field">
					<label><bean:message key="LABEL.appReqBy"/></label>
					<div class="input_box">
						<html:text property="reqWorkCommonDTO.filterReqEmpDesc" tabindex="90"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 우선순위 -->
				<div class="field">
					<label><bean:message key="LABEL.reqPriority"/></label>
					<div class="input_box">
						<html:text property="reqWorkCommonDTO.filterReqPriorityDesc" tabindex="100"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 접수공장명  -->
				<div class="field">
				    <label><bean:message key="LABEL.recPlantDesc"/></label>
				    <div class="input_box">
				        <html:text property="reqWorkCommonDTO.filterPlantDesc" tabindex="110"/>
				        <p class="open_spop">
				            <a>
				                <span>조회</span>
				            </a>
				        </p>
				    </div>
				</div>
				<!-- 처리부서 -->
				<div class="field">
					<label><bean:message key="LABEL.recReqDept"/></label>
					<div class="input_box">
						<html:text property="reqWorkCommonDTO.filterRecDeptDesc" tabindex="120" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 처리자 -->
				<div class="field">
					<label><bean:message key="LABEL.recReqEmp"/></label>
					<div class="input_box">
						<html:text property="reqWorkCommonDTO.filterRecEmpDesc" tabindex="130"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 요청번호 -->
				<div class="field">
					<label><bean:message key="LABEL.reqNo"/></label>
					<div class="input_box">
						<html:text property="reqWorkCommonDTO.filterWoReqNo" tabindex="50"/>
					</div>
				</div>
				<!-- 처리작업그룹 -->
				<div class="field">
					<label><bean:message key="LABEL.recReqWkCtr"/></label>
					<div class="input_box">
						<html:text property="reqWorkCommonDTO.filterRecWkCtrDesc" tabindex="50"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<c:set var="filePath" value="enhance/${compName}/req/work/reqWorkList_${compNo}.jsp" />
				<c:if test="${udf:isExist(filePath)}">
					<c:import charEncoding="UTF-8" url="/enhance/${compName}/req/work/reqWorkList_${compNo}.jsp"></c:import>
				</c:if>
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