<%--===========================================================================
 - 목록
author  kim21017
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.req.work.action.MaWoReqListAction" %>
<%@ page import="dream.req.work.action.MaWoReqDetailAction" %>
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
<title><bean:message key='MENU.WOREQRECLIST'/></title>
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
var reqEmpAc;
var reqDeptAc;
var recEmpAc;
var recDeptAc;
var plantAc;
var moDescAc;
var recWkCtrAc;

function loadPage() 
{
	/*
	if(window.name=="WORESLIST_POPUP" || window.name=="OPEN_LINK" || window.name == "WOREQ_RATE_LIST_POPUP"){
		
	}else{
		//담당자 
		maWoReqListForm.elements['maWoReqCommonDTO.filterRecEmpId'].value = loginUser.filterEmpId;
		maWoReqListForm.elements['maWoReqCommonDTO.filterRecEmpDesc'].value = loginUser.filterEmpDesc;
		
		if(maWoReqListForm.elements['maWoReqCommonDTO.filterReqStartDate'].value == '') {
			maWoReqListForm.elements['maWoReqCommonDTO.filterReqStartDate'].value = getMinusMonth2(new Date(), -1); 
		}
		
	    if(maWoReqListForm.elements['maWoReqCommonDTO.filterReqEndDate'].value == '') {
			maWoReqListForm.elements['maWoReqCommonDTO.filterReqEndDate'].value   = getToday();
	    }
	    
	  	//처리부서
	    if(loginUser.filterDeptId!='null'){
	    	maWoReqListForm.elements['maWoReqCommonDTO.filterRecDeptId'].value  = loginUser.filterDeptId;
	    	maWoReqListForm.elements['maWoReqCommonDTO.filterRecDeptDesc'].value  = loginUser.filterDeptDesc;
		}
	  	
	  	//공장명
	    if(loginUser.filterPlant!='null'){
	    	maWoReqListForm.elements['maWoReqCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
	    	maWoReqListForm.elements['maWoReqCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
	    }
	}
	*/

	setInitVal('maWoReqCommonDTO.filterRecEmpId', loginUser.filterEmpId);
	setInitVal('maWoReqCommonDTO.filterRecEmpDesc', loginUser.filterEmpDesc);
	setInitVal('maWoReqCommonDTO.filterReqStartDate', getMinusMonth2(new Date(), -1));
	setInitVal('maWoReqCommonDTO.filterReqEndDate', getToday());
	setInitVal('maWoReqCommonDTO.filterPlantId', loginUser.filterPlant);
	setInitVal('maWoReqCommonDTO.filterPlantDesc', loginUser.filterPlantDesc);
  	
    initGrid();
    
    
    /**진행상태  */
    acSysDesc("maWoReqCommonDTO.filterWoReqStatusDesc","maWoReqCommonDTO.filterWoReqStatusId","WOREQ_STATUS");
    
    
    /**설비  */
    equipDescAc = new autoC({"maWoReqCommonDTO.filterEquipDesc":"description"});
    equipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcResultMap({
        "maWoReqCommonDTO.filterEquipId":"equip_id"
    });
    equipDescAc.setAcDConditionMap({
    	"eqloc_id" : "maWoReqCommonDTO.filterEqLocId",
    	"dept_id" : "maWoReqCommonDTO.filterReqDeptId",
    	"plant" : "maWoReqCommonDTO.filterPlantId"
    });
    equipDescAc.init();
    
    /**설비위치  */
    eqLocDescAc = new autoC({"maWoReqCommonDTO.filterEqLocDesc":"full_desc"});
    eqLocDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setAcResultMap({
        "maWoReqCommonDTO.filterEqLocId":"eqloc_id"
    });
    eqLocDescAc.setAcDConditionMap({
    	"plant" : "maWoReqCommonDTO.filterPlantId"
    });
    eqLocDescAc.init();
    
    /**요청자  */
    reqEmpAc = new autoC({"maWoReqCommonDTO.filterReqEmpDesc":"emp_name"});
    reqEmpAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    reqEmpAc.setTable("TAEMP");
    reqEmpAc.setAcResultMap({
        "maWoReqCommonDTO.filterReqEmpId":"emp_id"
    });
    reqEmpAc.setAcDConditionMap({
    	"dept_id" : "maWoReqCommonDTO.filterReqDeptId",
    	"plant" : "maWoReqCommonDTO.filterPlantId"
    });
    reqEmpAc.init();
    
    /** 요청부서 */
    reqDeptAc = new autoC({"maWoReqCommonDTO.filterReqDeptDesc":"description"});
    reqDeptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    reqDeptAc.setTable("TADEPT");
    reqDeptAc.setAcResultMap({
        "maWoReqCommonDTO.filterReqDeptId":"dept_id"
    });
    reqDeptAc.setAcDConditionMap({
    	"plant" : "maWoReqCommonDTO.filterPlantId"
    });
    reqDeptAc.init();
    /**처리자  */
    reqEmpAc = new autoC({"maWoReqCommonDTO.filterRecEmpDesc":"emp_name"});
    reqEmpAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    reqEmpAc.setTable("TAEMP");
    reqEmpAc.setAcResultMap({
        "maWoReqCommonDTO.filterRecEmpId":"emp_id"
    });
    reqEmpAc.setAcDConditionMap({
    	"dept_id" : "maWoReqCommonDTO.filterRecDeptId",
    	"plant" : "maWoReqCommonDTO.filterPlantId"
    });
    reqEmpAc.init();
    
    /** 처리부서 */
    reqDeptAc = new autoC({"maWoReqCommonDTO.filterRecDeptDesc":"description"});
    reqDeptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    reqDeptAc.setTable("TADEPT");
    reqDeptAc.setAcResultMap({
        "maWoReqCommonDTO.filterRecDeptId":"dept_id"
    });
    reqDeptAc.setAcDConditionMap({
    	"plant" : "maWoReqCommonDTO.filterPlantId"
    });
    reqDeptAc.init();
    
    /** 공장명 */
    plantAc = new autoC({"maWoReqCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "maWoReqCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
    
    /** 고장현상 */
    moDescAc = new autoC({"maWoReqCommonDTO.filterMoCdDesc":"failureDesc"});
    moDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"fail_type":"MO",
 		"is_use":"Y"
  	   });
    moDescAc.setTable("TAFAILURE");
    moDescAc.setAcResultMap({
        "maWoReqCommonDTO.filterMoCd":"failure_id"
    });
    moDescAc.init();
    
    /** 처리작업그룹  */
    recWkCtrAc = new autoC({"maWoReqCommonDTO.filterRecWkCtrDesc":"description"});
    recWkCtrAc.setKeyName("maWoReqCommonDTO.filterRecWkCtrId");
    recWkCtrAc.setAcConditionMap({
        "comp_no":loginUser.compNo
       });
    recWkCtrAc.setTable("TAWKCTR");
    recWkCtrAc.setAcResultMap({
        "maWoReqCommonDTO.filterRecWkCtrId":"wkctr_id"
    });
    recWkCtrAc.init();
    
    acSysDesc("maWoReqCommonDTO.filterWoReqTypeDesc","maWoReqCommonDTO.filterWoReqType","WOREQ_TYPE");
    
    /**우선순위  */
    acSysDesc("maWoReqCommonDTO.filterReqPriorityDesc","maWoReqCommonDTO.filterReqPriorityId","REQ_PRIORITY");
    
    /** 작업요청발생구분 */
    acSysDesc("maWoReqCommonDTO.filterReqChannelDesc","maWoReqCommonDTO.filterReqChannelId","WOREQ_CHANNEL");
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
    	maWoReqListForm.elements['maWoReqCommonDTO.woReqId'].value = "";
    	return sortColumn("maWoReqList", this, maWoReqListForm, "WOREQID", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox","goSearch"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maWoReqList.do";

    maWoReqListForm.elements['strutsAction'].value = '<%=MaWoReqListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maWoReqListForm), "woReqId", "Y");
    
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_woReqId)
{
	maWoReqListForm.elements['maWoReqCommonDTO.woReqId'].value = _woReqId;
	findGridList('ReloadRow');
	maWoReqListForm.elements['maWoReqCommonDTO.woReqId'].value = "";
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	maWoReqListForm.elements['maWoReqCommonDTO.woReqId'].value = "";	// 검색시 Tab 이동Key Clear
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
	var form = document.maWoReqListForm;
	form.elements['maWoReqCommonDTO.woReqId'].value = getValueById(myGrid, selectedId, 'WOREQID');
	goCommonTabPage(form, <%= MaWoReqDetailAction.DETAIL_INIT %>, pageId, beforePageId);
	
	beforePageId = pageId;
}

/**
 * 상세열기
 */
function goOpen(rowId)
{
	var param  = getValueById(myGrid, rowId,'PARAM');
	// maWoReqListForm.elements['maWoReqCommonDTO.selectedWoReqTypeId'].value = getValueById(myGrid, rowId,'WOREQTYPE');
	if (param == null || param == '') {
		goTabPage('maWoReqDetail');
	} else {
		goTabPage(param);
	}
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    maWoReqListForm.elements['maWoReqCommonDTO.woReqId'].value = getValueById(myGrid, selectedId, 'WOREQID');
    maWoReqListForm.elements['strutsAction'].value = '<%=MaWoReqDetailAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(maWoReqListForm), 'maWoReqDetail'); 
} 
 
/**
 * 생성
 */
function goCreate()
{
	goClose(beforePageId);
	createValidationCheck(myGrid, "maWoReqDetail" , "goCreateAction");
}


function goCreateAction(pageId)
{
	maWoReqListForm.elements['maWoReqCommonDTO.woReqId'].value = "";
	goCommonTabPage(maWoReqListForm, '', pageId);
	beforePageId = pageId;
}

/**
 * Excel Export
 */
function goExcel()
{
    //excelAction(myGrid);
    maWoReqListForm.elements['maWoReqCommonDTO.woReqId'].value = "";
	excelServerAction("maWoReqList", maWoReqListForm ); 
}

/**
 * 삭제
 */
function goDelete(){
	var url = contextPath + "/maWoReqList.do";
	maWoReqListForm.strutsAction.value = '<%=MaWoReqListAction.LIST_DELETE%>';
	goDeleteAction(myGrid, url, FormQueryString(maWoReqListForm), 'ISDELCHECK', 'WOREQID');
}
 
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = maWoReqListForm.elements['maWoReqCommonDTO.woReqId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maWoReqList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maWoReqCommonDTO.woReqId"/><!-- Key -->
<html:hidden property="maWoReqCommonDTO.filterWoReqStatusId"/>
<html:hidden property="maWoReqCommonDTO.filterEquipId"/>
<html:hidden property="maWoReqCommonDTO.filterEqLocId"/>
<html:hidden property="maWoReqCommonDTO.filterReqEmpId"/>
<html:hidden property="maWoReqCommonDTO.filterReqDeptId"/>
<html:hidden property="maWoReqCommonDTO.filterRecEmpId"/>
<html:hidden property="maWoReqCommonDTO.filterRecDeptId"/>
<html:hidden property="maWoReqCommonDTO.filterReqPriorityId"/>
<html:hidden property="maWoReqCommonDTO.filterPlantId"/>
<html:hidden property="maWoReqCommonDTO.filterWoReqType"/>
<html:hidden property="maWoReqCommonDTO.filterMoCd"/>
<html:hidden property="maWoReqCommonDTO.filterReqChannelId"/>
<html:hidden property="maWoReqCommonDTO.filterRecWkCtrId"/>
<html:hidden property="maWoReqCommonDTO.invtlistId"/>
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
							<html:text property="maWoReqCommonDTO.filterReqStartDate" tabindex="10" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="maWoReqCommonDTO.filterReqEndDate" tabindex="20" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 진행상태  -->
				<div class="field">
					<label><bean:message key="LABEL.proStatus"/></label>
					<div class="input_box">
						<html:text property="maWoReqCommonDTO.filterWoReqStatusDesc" tabindex="30"/>
						  <p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 제목 -->
				<div class="field">
					<label><bean:message key="LABEL.title"/></label>
					<div class="input_box">
						<html:text property="maWoReqCommonDTO.filterWoReqDesc" tabindex="40"/>
					</div>
				</div>
				<!-- 요청내용 -->
				<div class="field">
					<label><bean:message key="LABEL.request"/></label>
					<div class="input_box">
						<html:text property="maWoReqCommonDTO.filterRequest" tabindex="50"/>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.equipDesc"/></label>
					<div class="input_box">
						<html:text property="maWoReqCommonDTO.filterEquipDesc" tabindex="60"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 위치 -->
				<div class="field">
					<label><bean:message key="LABEL.location"/></label>
					<div class="input_box">
						<html:text property="maWoReqCommonDTO.filterEqLocDesc" tabindex="70"/>
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
						<html:text property="maWoReqCommonDTO.filterReqDeptDesc" tabindex="80" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 요청자 -->
				<div class="field">
					<label><bean:message key="LABEL.appReqBy"/></label>
					<div class="input_box">
						<html:text property="maWoReqCommonDTO.filterReqEmpDesc" tabindex="90"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 처리부서 -->
				<div class="field">
					<label><bean:message key="LABEL.recReqDept"/></label>
					<div class="input_box">
						<html:text property="maWoReqCommonDTO.filterRecDeptDesc" tabindex="100" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 처리자 -->
				<div class="field">
					<label><bean:message key="LABEL.recReqEmp"/></label>
					<div class="input_box">
						<html:text property="maWoReqCommonDTO.filterRecEmpDesc" tabindex="110"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 우선순위 -->
				<div class="field">
					<label><bean:message key="LABEL.reqPriority"/></label>
					<div class="input_box">
						<html:text property="maWoReqCommonDTO.filterReqPriorityDesc" tabindex="120"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 공장명  -->
                <div class="field">
                    <label><bean:message key="LABEL.plantDesc"/></label>
                    <div class="input_box">
							<html:text property="maWoReqCommonDTO.filterPlantDesc" tabindex="130" />
							<p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 요청번호 -->
				<div class="field">
					<label><bean:message key="LABEL.reqNo"/></label>
					<div class="input_box">
						<html:text property="maWoReqCommonDTO.filterWoReqNo" tabindex="140"/>
					</div>
				</div>
				<!-- 요청구분 -->
				<div class="field">
					<label><bean:message key="LABEL.reqNo"/></label>
					<div class="input_box">
						<html:text property="maWoReqCommonDTO.filterWoReqTypeDesc" tabindex="150"/>
						<p class="open_spop">
							<a><span>조회</span></a>
						</p>
					</div>
				</div>
				<!-- 고장현상 -->
				<div class="field">
					<label><bean:message key="LABEL.moCd"/></label>
					<div class="input_box">
						<html:text property="maWoReqCommonDTO.filterMoCdDesc" tabindex="160"/>
						<p class="open_spop">
							<a><span>조회</span></a>
						</p>
					</div>
				</div>
				<!-- 고장현상설명 -->
				<div class="field">
					<label><bean:message key="LABEL.moCdRemark"/></label>
					<div class="input_box">
						<html:text property="maWoReqCommonDTO.filterMoDesc" tabindex="170"/>
					</div>
				</div>
				<!-- W/O # -->
				<div class="field">
					<label><bean:message key="LABEL.woNo"/></label>
					<div class="input_box">
						<html:text property="maWoReqCommonDTO.filterWoNo" tabindex="180"/>
					</div>
				</div>
				<!-- 작업요청발생구분 -->
				<div class="field">
					<label>작업요청발생구분</label>
					<div class="input_box">
						<html:text property="maWoReqCommonDTO.filterReqChannelDesc" tabindex="280"/>
						<p class="open_spop">
							<a><span>조회</span></a>
						</p>
					</div>
				</div>
				<!-- 처리작업그룹 -->
				<div class="field">
					<label><bean:message key="LABEL.recReqWkCtr"/></label>
					<div class="input_box">
						<html:text property="maWoReqCommonDTO.filterRecWkCtrDesc" tabindex="290"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<c:set var="filePath" value="enhance/${compName}/req/work/maWoReqList_${compNo}.jsp" />
				<c:if test="${udf:isExist(filePath)}">
					<c:import charEncoding="UTF-8" url="/enhance/${compName}/req/work/maWoReqList_${compNo}.jsp"></c:import>
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