<%--===========================================================================
점검작업 - 목록
author  kim21017
version $Id: workPmiList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.pmi.list.action.WorkPmiListAction" %>
<%@ page import="dream.work.pmi.list.action.WorkPmiDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="common.bean.User"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 

%>
<html>
<head>
<!-- 작업결과 -->
<title><bean:message key='MENU.maPmInsList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var myGrid;
var beforePageId = '';
/** 자동완성 변수 */
var equipDescAc;
var deptAc;
var mainMngAc;
var subMngAc;
var eqLocDescAc;
var eqCtgTypeAc;
var pmTypeAc;
var wkCtrDescAc;
var empAc;
var plantAc;
var usageDeptAc;

function loadPage() 
{
	//작업시작일자, 종료일자 넣기.
	if(workPmiListForm.elements['workPmiCommonDTO.filterStartDate'].value == "")
	{
		workPmiListForm.elements['workPmiCommonDTO.filterStartDate'].value   = getMinusDay(7);
		workPmiListForm.elements['workPmiCommonDTO.filterEndDate'].value   = getToday();
	}
	//부서
	workPmiListForm.elements['workPmiCommonDTO.filterDeptId'].value    = loginUser.filterDeptId;
	workPmiListForm.elements['workPmiCommonDTO.filterDeptDesc'].value  = loginUser.filterDeptDesc;
       if(loginUser.eqLocId!='null'){
		workPmiListForm.elements['workPmiCommonDTO.filterEqLocId'].value = loginUser.eqLocId;
		workPmiListForm.elements['workPmiCommonDTO.filterEqLocDesc'].value = loginUser.eqLocDesc;
	}
       if(loginUser.eqCtgTypeId!='null'){
		workPmiListForm.elements['workPmiCommonDTO.filterEqCtgTypeId'].value = loginUser.eqCtgTypeId;
		workPmiListForm.elements['workPmiCommonDTO.filterEqCtgTypeDesc'].value = loginUser.eqCtgTypeDesc;
	}
    /* if(loginUser.wkctrId!='null'){
       	workPmiListForm.elements['workPmiCommonDTO.filterWkCtrId'].value = loginUser.wkctrId;
       	workPmiListForm.elements['workPmiCommonDTO.filterWkCtrDesc'].value = loginUser.wkctrDesc;
	} */
    if(loginUser.filterWkCtrId!='null'){
       	workPmiListForm.elements['workPmiCommonDTO.filterWkCtrId'].value = loginUser.filterWkCtrId;
       	workPmiListForm.elements['workPmiCommonDTO.filterWkCtrDesc'].value = loginUser.filterWkCtrDesc;
	}
    //공장명
       if(loginUser.filterPlant!='null'){
      	workPmiListForm.elements['workPmiCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
      	workPmiListForm.elements['workPmiCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
    }
    initGrid();

    equipDescAc = new autoC({"workPmiCommonDTO.filterEquipDesc":"description"});
    equipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    equipDescAc.setAcResultMap({
        "maWoResultMstrCommonDTO.filterEquipId":"equip_id"
    });
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcDConditionMap({
    	"eqloc_id" : "workPmiCommonDTO.filterEqLocId",
    	"eqctg_id" : "workPmiCommonDTO.filterEqCtgId",
    	"dept_id" : "workPmiCommonDTO.filterDeptId",
    	"plant" : "workPmiCommonDTO.filterPlantId"
    });
    equipDescAc.init();
    
    deptAc = new autoC({"workPmiCommonDTO.filterDeptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "workPmiCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.setAcDConditionMap({
    	"plant" : "workPmiCommonDTO.filterPlantId"
    });
    deptAc.init();
    
    mainMngAc = new autoC({"workPmiCommonDTO.filterMainMngName":"emp_name"});
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcResultMap({
        "workPmiCommonDTO.filterMainMngId":"emp_id"
    });
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "workPmiCommonDTO.filterDeptId",
    	"plant" : "workPmiCommonDTO.filterPlantId"
    });
    mainMngAc.init();
    
    subMngAc = new autoC({"workPmiCommonDTO.filterSubMngName":"emp_name"});
    subMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    subMngAc.setTable("TAEMP");
    subMngAc.setAcResultMap({
        "workPmiCommonDTO.filterSubMngId":"emp_id"
    });
    subMngAc.setAcDConditionMap({
    	"dept_id" : "workPmiCommonDTO.filterDeptId",
    	"plant" : "workPmiCommonDTO.filterPlantId"
    });
    subMngAc.init();
    
    eqLocDescAc = new autoC({"workPmiCommonDTO.filterEqLocDesc":"full_desc"});
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqLocDescAc.setAcResultMap({
        "workPmiCommonDTO.filterEqLocId":"eqloc_id"
    });
    eqLocDescAc.setAcDConditionMap({
    	"plant" : "workPmiCommonDTO.filterPlantId"
    });
    eqLocDescAc.init();
    
    eqCtgTypeAc = new autoC({"workPmiCommonDTO.filterEqCtgDesc":"full_desc"});
    eqCtgTypeAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqCtgTypeAc.setTable("TAEQCTG");
    eqCtgTypeAc.setAcResultMap({
        "workPmiCommonDTO.filterEqCtgId":"eqctg_id"
    });
    eqCtgTypeAc.init();
    
    wkCtrDescAc = new autoC({"workPmiCommonDTO.filterWkCtrDesc":"description"});
    wkCtrDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    wkCtrDescAc.setTable("TAWKCTR");
    wkCtrDescAc.setAcResultMap({
        "workPmiCommonDTO.filterWkCtrId":"wkctr_id"
    });
    wkCtrDescAc.init();
    
    acSysDesc("workPmiCommonDTO.filterIsLawEq","workPmiCommonDTO.filterIsLawEq","IS_USE");
    acSysDesc("workPmiCommonDTO.filterWoTypeDesc","workPmiCommonDTO.filterWoTypeId","WO_TYPE");
    acSysDesc("workPmiCommonDTO.filterEqCtgTypeDesc","workPmiCommonDTO.filterEqCtgTypeId","EQCTG_TYPE");
    acSysDesc("workPmiCommonDTO.filterPmSchedStatusDesc","workPmiCommonDTO.filterPmSchedStatusId","PMSCHED_STATUS");
    
    // 담당자
    empAc = new autoC({"workPmiCommonDTO.filterManagerDesc":"emp_name"});
    empAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    empAc.setTable("TAEMP");
    empAc.setAcResultMap({
        "workPmiCommonDTO.filterManagerId":"emp_id"
    });
    empAc.setAcDConditionMap({
    	"dept_id" : "workPmiCommonDTO.filterDeptId",
    	"plant" : "workPmiCommonDTO.filterPlantId"
    });
    empAc.init();
    
    plantAc = new autoC({"workPmiCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "workPmiCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
    
    /* 사용부서 AC*/
	//------------------------------------------------------------------------------------
    usageDeptAc = new autoC({"workPmiCommonDTO.filterUsageDeptDesc":"description"});
    usageDeptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    usageDeptAc.setTable("TADEPT");
    usageDeptAc.setAcResultMap({
        "workPmiCommonDTO.filterUsageDeptId":"dept_id"
    });
    usageDeptAc.setAcDConditionMap({
    	"plant" : "workPmiCommonDTO.filterPlantId"
    });
    usageDeptAc.init();
    //-------------------------------------------------------------------------------------

}

/*작업종류 선택후 실행*/
function afterAutoCmpt(code)
{
	if(code=="workPmiCommonDTO.filterWoTypeDesc")
	{
	 	var listType2 = workPmiListForm.elements['workPmiCommonDTO.filterWoTypeId'].value+"_TYPE";
	    acSysDesc("workPmiCommonDTO.filterPmTypeDesc","workPmiCommonDTO.filterPmTypeId",listType2);
	}
}


/*작업종류 선택후 실행*/
function afterSetValue(lovType)
{
	if(lovType=="WO_TYPE")
	{
	 	var listType2 = workPmiListForm.elements['workPmiCommonDTO.filterWoTypeId'].value+"_TYPE";
	    acSysDesc("workPmiCommonDTO.filterPmTypeDesc","workPmiCommonDTO.filterPmTypeId",listType2);
	}
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
    	workPmiListForm.elements['workPmiCommonDTO.pminslistId'].value = "";
    	return sortColumn("workPmiList", this, workPmiListForm, "PMINSLISTID", ind, direction);
	}); 

	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/workPmiList.do";

    workPmiListForm.elements['strutsAction'].value = '<%=WorkPmiListAction.WORK_PMI_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workPmiListForm), "PMINSLISTID", "Y");
}

/**
 * 엑셀 다운.
 */
 function goExcel()
 {
	workPmiListForm.elements['workPmiCommonDTO.pminslistId'].value = "";
 	excelServerAction("workPmiList", workPmiListForm );  
 }

 
/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	workPmiListForm.elements['workPmiCommonDTO.pminslistId'].value = "";	// 검색시 Tab 이동Key Clear
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
	var form = document.workPmiListForm;
	
	form.elements['workPmiCommonDTO.pminslistId'].value = getValueById(myGrid, selectedId,'PMINSLISTID');
	
	goCommonTabPage(form, <%= WorkPmiDetailAction.WORK_PMI_DETAIL_INIT %>, pageId,beforePageId);

	beforePageId = pageId;
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_pminslistId)
{
	workPmiListForm.elements['workPmiCommonDTO.pminslistId'].value = _pminslistId;
	findGridList('ReloadRow');
	workPmiListForm.elements['workPmiCommonDTO.pminslistId'].value = "";
}

/**
 * 상세열기
 */
 function goOpen()
 {
	goTabPage("workPmiDetail");
}

 function goOpenAction()
 {
     var selectedId=myGrid.getSelectedRowId();
     if(selectedId == null) return;
     
     workPmiListForm.elements['workPmiCommonDTO.pminslistId'].value = getValueById(myGrid, selectedId,'PMINSLISTID');
     workPmiListForm.elements['strutsAction'].value = '<%=WorkPmiDetailAction.WORK_PMI_DETAIL_INIT%>';
     openQuickTabPage(FormQueryString(workPmiListForm), 'workPmiDetail'); 
 } 

/**
  * 삭제
  */
function goDelete()
{
	var chkedRowsId = getCheckedRows(myGrid, 'ISDELCHECK');
	for(var i = 0 ;i < chkedRowsId.length; i++)
	{
		if(getValueById(myGrid, chkedRowsId[i], "PMSCHEDSTATUS") == "C")
		{
			alertMessage1('<bean:message key="LABEL.delCompPmi"/>'); //완료된 점검 삭제여부
			return;
		}
	}

	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'PMINSLISTID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	
	workPmiListForm.strutsAction.value = '<%=WorkPmiListAction.WORK_PMI_LIST_DELETE%>';
	var url = contextPath + "/workPmiList.do";
	$.post(url,FormQueryString(workPmiListForm)+delArray , function(_data){
    	afterDelete();
    }); 
}

function afterDelete()
{
	goClose(beforePageId);
  	alertMessage1('<bean:message key="MESSAGE.MSG0032"/>');
}

/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = workPmiListForm.elements['workPmiCommonDTO.pminslistId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }


//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workPmiList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="workPmiCommonDTO.pminslistId"/><!-- Key -->
<html:hidden property="workPmiCommonDTO.filterDeptId"/>
<html:hidden property="workPmiCommonDTO.filterEquipId"/>
<html:hidden property="workPmiCommonDTO.filterEqLocId"/>
<html:hidden property="workPmiCommonDTO.filterEqCtgId"/>
<html:hidden property="workPmiCommonDTO.filterMainMngId"/>
<html:hidden property="workPmiCommonDTO.filterSubMngId"/>
<html:hidden property="workPmiCommonDTO.filterPmTypeId"/>
<html:hidden property="workPmiCommonDTO.filterWoTypeId"/>
<html:hidden property="workPmiCommonDTO.filterEqCtgTypeId"/>
<html:hidden property="workPmiCommonDTO.filterWkCtrId"/>
<html:hidden property="workPmiCommonDTO.selectedWkorId"/>
<html:hidden property="workPmiCommonDTO.selectedPmType"/>
<html:hidden property="workPmiCommonDTO.selectedWoType"/>
<html:hidden property="workPmiCommonDTO.filterManagerId"/>
<html:hidden property="workPmiCommonDTO.notPmschedStatusId"/>
<html:hidden property="workPmiCommonDTO.filterPlantId"/>
<html:hidden property="workPmiCommonDTO.filterPmSchedStatusId"/>
<html:hidden property="workPmiCommonDTO.filterUsageDeptId"/>

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
				<!-- 일자 -->
				<div class="field">
					<label><bean:message key="LABEL.workDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="workPmiCommonDTO.filterStartDate" tabindex="10" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="workPmiCommonDTO.filterEndDate" tabindex="20" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 예방작업번호 -->
				<div class="field">
					<label><bean:message key="LABEL.pmNo"/></label>
					<div class="input_box">
						<html:text property="workPmiCommonDTO.filterPmNo" tabindex="30"/>
					</div>
				</div> 
				<!-- 관리부서 -->
				<div class="field">
					<label><bean:message key="LABEL.dept"/></label>
					<div class="input_box">
						<html:text property="workPmiCommonDTO.filterDeptDesc" tabindex="40"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 담당자 -->
				<div class="field">
					<label><bean:message key="LABEL.manager"/></label>
					<div class="input_box">
						<html:text property="workPmiCommonDTO.filterManagerDesc" tabindex="45"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 설비 -->
				<div class="field">
					<label><bean:message key="LABEL.equipDesc"/></label>
					<div class="input_box">
						<html:text property="workPmiCommonDTO.filterEquipDesc" tabindex="50" />
						<p class="open_spop">
							<a><span>조회</span></a>
						</p>
					</div>
				</div>
				<!-- 설비위치 -->
				<div class="field">
					<label><bean:message key="LABEL.location"/></label>
					<div class="input_box">
						<html:text property="workPmiCommonDTO.filterEqLocDesc" tabindex="60"/>
						<p class="open_spop">
							<a><span>조회</span></a>
						</p>
					</div>
				</div>
				<!-- 설비종류 -->
				<div class="field">
					<label><bean:message key="LABEL.type"/></label>
					<div class="input_box">
						<html:text property="workPmiCommonDTO.filterEqCtgDesc" tabindex="70" />
						<p class="open_spop">
							<a><span>조회</span></a>
						</p>
					</div>
				</div>
				<!-- 법정설비여부 -->
				<div class="field">
					<label><bean:message key="LABEL.isLawEq"/></label>
					<div class="input_box">
						<html:text property="workPmiCommonDTO.filterIsLawEq" tabindex="80" />
						<p class="open_spop">
							<a><span>조회</span></a>
						</p>
					</div>
				</div>
				<!-- 관리자(정) -->
				<div class="field">
					<label><bean:message key="LABEL.mainManager"/></label>
					<div class="input_box">
						<html:text property="workPmiCommonDTO.filterMainMngName" tabindex="90" />
						<p class="open_spop">
							<a><span>조회</span></a>
						</p>
					</div>
				</div>
				<!-- 관리자(부) -->
				<div class="field">
					<label><bean:message key="LABEL.subManager"/></label>
					<div class="input_box">
						<html:text property="workPmiCommonDTO.filterSubMngName" tabindex="100" />
						<p class="open_spop">
							<a><span>조회</span></a>
						</p>
					</div>
				</div>
				<!-- 작업종류  -->
				<div class="field">
					<label><bean:message key="LABEL.woType"/></label>
					<div class="input_box">
						<html:text property="workPmiCommonDTO.filterWoTypeDesc" tabindex="110" />
						<p class="open_spop">
							<a><span>조회</span></a>
						</p>
					</div>
				</div>
				<!-- 작업형태  -->
				<div class="field">
					<label><bean:message key="LABEL.pmType"/></label>
					<div class="input_box">
						<html:text property="workPmiCommonDTO.filterPmTypeDesc" tabindex="120" />
						<p class="open_spop">
							<a><span>조회</span></a>
						</p>
					</div>
				</div>
				<!-- 작업그룹  -->
				<div class="field">
                     <label><bean:message key="LABEL.wkCtr"/></label>
                     <div class="input_box">
                        <html:text property="workPmiCommonDTO.filterWkCtrDesc" tabindex="170" />
                         <p class="open_spop">
                             <a><span>조회</span></a>
                         </p>
                   </div>
                </div>
				<!-- 설비유형  -->
				<div class="field">
					<label><bean:message key="LABEL.eqCtgType"/></label>
					<div class="input_box">
						<html:text property="workPmiCommonDTO.filterEqCtgTypeDesc" tabindex="130" />
						<p class="open_spop">
							<a><span>조회</span></a>
						</p>
					</div>
				</div>
				<!-- 공장명  -->
                <div class="field">
                    <label><bean:message key="LABEL.plantDesc"/></label>
                    <div class="input_box">
							<html:text property="workPmiCommonDTO.filterPlantDesc" tabindex="140" />
							<p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
				<!-- 점검작업상태  -->
                <div class="field">
                    <label><bean:message key="LABEL.pmiSchedStatus"/></label>
                    <div class="input_box">
							<html:text property="workPmiCommonDTO.filterPmSchedStatusDesc" tabindex="150" />
							<p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 사용부서 -->
				<div class="field">
					<label><bean:message key="LABEL.usedDept"/></label>
					<div class="input_box">
						<html:text property="workPmiCommonDTO.filterUsageDeptDesc" tabindex="20" />
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