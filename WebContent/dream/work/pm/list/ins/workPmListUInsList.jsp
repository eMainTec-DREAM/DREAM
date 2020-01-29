<%--===========================================================================
에너지측정주기 - 목록
author  js.lee
version $Id: Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.pm.list.action.MaPmMstrListAction" %>
<%@ page import="dream.work.pm.list.action.MaPmMstrDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/functions.tld" prefix="udf"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="common.bean.User"%>
<%@ page import="common.bean.MwareConfig"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
	String isUsePmRevision = MwareConfig.getIsUsePmRevision();
%>
<html>
<head>
<!-- 에너지측정주기설정 -->
<title><bean:message key='PAGE.workPmListUInsList'/></title>
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
var wkCtrDescAc;
var empDescAc;
var plantAc;
 
var woType = "PMU";

var beforePageId = 'maPmMstrDetail';
function loadPage() 
{
	maPmMstrListForm.elements['maPmMstrCommonDTO.woType'].value = woType;
	
	if(window.name=="PM_LIST"){
	}else{
		// 부서
		if(loginUser.filterDeptId != '' && null != loginUser.filterDeptId){
			maPmMstrListForm.elements['maPmMstrCommonDTO.deptId'].value    = loginUser.filterDeptId;
			maPmMstrListForm.elements['maPmMstrCommonDTO.deptDesc'].value  = loginUser.filterDeptDesc;
		}
		// 공장
		if(loginUser.filterPlant != '' && null != loginUser.filterPlant){
	    	maPmMstrListForm.elements['maPmMstrCommonDTO.filterPlantId'].value  = loginUser.plant;
	    	maPmMstrListForm.elements['maPmMstrCommonDTO.filterPlantDesc'].value  = loginUser.plantDesc;
		}
		// 담당자
		if(loginUser.filterEmpId != '' && null != loginUser.filterEmpId){
			maPmMstrListForm.elements['maPmMstrCommonDTO.empId'].value    = loginUser.filterEmpId;
			maPmMstrListForm.elements['maPmMstrCommonDTO.empName'].value  = loginUser.filterEmpDesc;
		}
		// 작업그룹
		if(loginUser.filterWkCtrId != '' && null != loginUser.filterWkCtrId){
	    	maPmMstrListForm.elements['maPmMstrCommonDTO.wkCtrId'].value = loginUser.filterWkCtrId;
	    	maPmMstrListForm.elements['maPmMstrCommonDTO.wkCtrDesc'].value = loginUser.filterWkCtrDesc;
		}
	}
	
	maPmMstrListForm.elements['maPmMstrCommonDTO.isLastVersion'].value = "Y";
	valSysDir('maPmMstrCommonDTO.isLastVersion', 'maPmMstrCommonDTO.isLastVersion', 'IS_USE', true);
	
    initGrid();
    
    // 설비
    equipDescAc = new autoC({"maPmMstrCommonDTO.equipDesc":"description"});
    equipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcResultMap({
        "maPmMstrCommonDTO.equipId":"equip_id"
    });
    equipDescAc.setAcDConditionMap({
    	"dept_id" : "maPmMstrCommonDTO.deptId",
    	"plant" : "maPmMstrCommonDTO.filterPlantId"
    });
    equipDescAc.init();
    
    // 부서
    deptAc = new autoC({"maPmMstrCommonDTO.deptDesc":"description"});
    deptAc.setAcDisplay("DESCRIPTION");
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "maPmMstrCommonDTO.deptId":"dept_id"
    });
    deptAc.setAcDConditionMap({
    	"plant" : "maPmMstrCommonDTO.filterPlantId"
    });
    deptAc.init();
    
    // 작업그룹
    wkCtrDescAc = new autoC({"maPmMstrCommonDTO.wkCtrDesc":"description"});
    wkCtrDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    wkCtrDescAc.setTable("TAWKCTR");
    wkCtrDescAc.setAcResultMap({
        "maPmMstrCommonDTO.wkCtrId":"wkctr_id"
    });
    wkCtrDescAc.init();
    
    // 담당자
    mgrDescAc = new autoC({"maPmMstrCommonDTO.empName":"emp_name"});
    mgrDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    mgrDescAc.setTable("TAEMP");
    mgrDescAc.setAcResultMap({
        "maPmMstrCommonDTO.empId":"emp_id"
    });
    mgrDescAc.setAcDConditionMap({
    	"dept_id" : "maPmMstrCommonDTO.deptId",
    	"plant" : "maPmMstrCommonDTO.filterPlantId"
    });
    mgrDescAc.init();
    
    // 공장명
    plantAc = new autoC({"maPmMstrCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "maPmMstrCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
    
    // 작업형태
    acSysDesc("maPmMstrCommonDTO.pmTypeDesc","maPmMstrCommonDTO.pmType", woType+"_TYPE");
    // 최신Version여부
    acSysDesc("maPmMstrCommonDTO.isLastVersion","maPmMstrCommonDTO.isLastVersion","IS_USE");
    // Revision 진행상태
    acSysDesc("maPmMstrCommonDTO.revisionStatusDesc","maPmMstrCommonDTO.revisionStatus","REVISION_STATUS");

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
    	maPmMstrListForm.elements['maPmMstrCommonDTO.pmId'].value = "";
    	return sortColumn("workPmListUInsList", this, maPmMstrListForm, "PMID", ind, direction);
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
    var url = contextPath + "/workPmListUInsList.do";

    maPmMstrListForm.elements['strutsAction'].value = '<%=MaPmMstrListAction.PM_MSTR_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maPmMstrListForm), "PMID", "Y");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_pmId)
{
	maPmMstrListForm.elements['maPmMstrCommonDTO.pmId'].value = _pmId;
	findGridList('ReloadRow');
	maPmMstrListForm.elements['maPmMstrCommonDTO.pmId'].value = "";
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	maPmMstrListForm.elements['maPmMstrCommonDTO.pmId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('Search');   
}

/**
* Tab 이동시 호출
*/
function goTabPage(pageId)
{
   //goClose(beforePageId);
   tabValidationCheck(myGrid, pageId, "goTabPageAction");
}

function goTabPageAction(pageId, selectedId)
{
	var form = document.maPmMstrListForm;

	form.elements['maPmMstrCommonDTO.pmId'].value = getValueById(myGrid, selectedId,'PMID');
	goCommonTabPage(maPmMstrListForm, '<%=MaPmMstrDetailAction.PM_MSTR_DETAIL_INIT%>', pageId, beforePageId);
	
	beforePageId = pageId;
}

/**
 * 상세열기
 */
 function goOpen(rowId){
	var pmType = getValueById(myGrid, rowId,'PMTYPE');
	var param = getValueById(myGrid, rowId,'PARAM');
	maPmMstrListForm.elements['maPmMstrCommonDTO.selectedPmType'].value = pmType;
	goTabPage(param);	
}

/**
 * Open 버튼 클릭
 */
function goOpenAction()
{
	var selectedId=myGrid.getSelectedRowId();
	if(selectedId == null) return;
	
	var pageId = getValueById(myGrid, selectedId,'PARAM');
	maPmMstrListForm.elements['maPmMstrCommonDTO.pmId'].value = getValueById(myGrid, selectedId,'PMID');
	maPmMstrListForm.elements['strutsAction'].value = '<%=MaPmMstrDetailAction.PM_MSTR_DETAIL_INIT%>';
	
	openQuickTabPage(FormQueryString(maPmMstrListForm), pageId); 
}

/**
 * 생성
 */
function goCreate()
{
	goClose(beforePageId);
	openSelectType();
	maPmMstrListForm.elements['maPmMstrCommonDTO.pmId'].value = "";
}
  
function afterCreate(_pmId, pageId)
{
	findGridRow(_pmId);
	 
	var form = document.maPmMstrListForm;

	form.elements['maPmMstrCommonDTO.pmId'].value = _pmId;
	goCommonTabPage(maPmMstrListForm, '<%=MaPmMstrDetailAction.PM_MSTR_DETAIL_INIT%>', pageId, beforePageId);
}

function goCreateAction(pageId)
{
}

/**
 * 삭제
 */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'isDelCheck', 'pmId'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	
	maPmMstrListForm.strutsAction.value = '<%=MaPmMstrListAction.PM_MSTR_LIST_DELETE%>';
	var url = contextPath + "/workPmListUInsList.do";
	$.post(url,FormQueryString(maPmMstrListForm)+delArray , function(_data){
   	afterDelete();
   });
}

function afterDelete(){
	
	goClose('workPmListUInsDetail');
   //	goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * 엑셀 다운.
 */
function goExcel()
{
	maPmMstrListForm.elements['maPmMstrCommonDTO.pmId'].value = "";
	excelServerAction("workPmListCalList", maPmMstrListForm);
}

function setAfterSelect(returnArray){
	var pmType = returnArray[0];
	var param2 = returnArray[1];
	
	maPmMstrListForm.elements['maPmMstrCommonDTO.selectedWoType'].value = woType;
	maPmMstrListForm.elements['maPmMstrCommonDTO.selectedPmType'].value = pmType;

	beforePageId = param2;
	//제개정 사용여부
	if("<%=isUsePmRevision%>"=="N"){
		goCommonTabPage(maPmMstrListForm, '', param2);
	}else{
		var param = "strutsAction=1001";
		//PM의 경우 PM마스터 추가시 작업종류(woType)와 작업형태(pmType)값이 필요하여 파라미터 전달
		//생성시 선택하는 팝업이 없으면 param에 제정 후 열릴 상세 PageId만 파리미터로 전달하면 됨
		param += "&" + "popupWidth=800";
		param += "&" + "commRevCommonDTO.selectedWoType="+woType;
		param += "&" + "commRevCommonDTO.selectedPmType="+pmType;
		param += "&" + "commRevCommonDTO.revisionObjType="+"PMSTD";
		param += "&" + "commRevCommonDTO.param="+param2;
		
		openLayerPopup("commRevRegislate", param);
	}
}


/**
 * 작업형태 선택창 열기
 */
function openSelectType(){
	var param = "strutsAction=1001";
	param += "&"+"maPmMstrSelectDTO.selectedPmType="+woType+"_TYPE";
	
	var url =  contextPath + "/workPmListSelectPm.do";
	
	openLayerPopup("workPmListSelectPm", param);
}



/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = maPmMstrListForm.elements['maPmMstrCommonDTO.pmId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workPmListUInsList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maPmMstrCommonDTO.pmId"/><!-- Key -->
<html:hidden property="maPmMstrCommonDTO.pmType"/>
<html:hidden property="maPmMstrCommonDTO.woType"/>
<html:hidden property="maPmMstrCommonDTO.equipId"/>
<html:hidden property="maPmMstrCommonDTO.deptId"/>
<html:hidden property="maPmMstrCommonDTO.wkCtrId"/>
<html:hidden property="maPmMstrCommonDTO.empId"/>
<html:hidden property="maPmMstrCommonDTO.filterPlantId"/>
<html:hidden property="maPmMstrCommonDTO.revisionStatus"/>
<html:hidden property="maPmMstrCommonDTO.selectedPmType"/>
<html:hidden property="maPmMstrCommonDTO.selectedWoType"/>
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
				<!-- 주기# -->
				<div class="field">
					<label>주기#</label>
					<div class="input_box">
						<html:text property="maPmMstrCommonDTO.pmNo" tabindex="10"/>
					</div>
				</div>
				<!-- 작업명 -->
				<div class="field">
					<label>작업명</label>
					<div class="input_box">
						<html:text property="maPmMstrCommonDTO.pmDesc" tabindex="20"/>
					</div>
				</div>
				<!-- 작업형태  -->
				<div class="field">
					<label>작업형태</label>
					<div class="input_box">
						<html:text property="maPmMstrCommonDTO.pmTypeDesc" tabindex="30"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 설비  -->
				<div class="field">
					<label>설비</label>
					<div class="input_box">
						<html:text property="maPmMstrCommonDTO.equipDesc" tabindex="40"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 부서명  -->
				<div class="field">
					<label>부서명</label>
					<div class="input_box">
						<html:text property="maPmMstrCommonDTO.deptDesc" tabindex="50"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 작업그룹 -->
                <div class="field">
                     <label>작업그룹</label>
                     <div class="input_box">
                        <html:text property="maPmMstrCommonDTO.wkCtrDesc" tabindex="60"/>
                         <p class="open_spop">
                             <a>
                                 <span>조회</span>
                             </a>
                         </p>
                   </div>
                </div>
				<!-- 담당자 -->
				<div class="field">
					<label>담당자</label>
					<div class="input_box">
						<html:text property="maPmMstrCommonDTO.empName" tabindex="70"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 공장명  -->
                <div class="field">
                    <label>공장명</label>
                    <div class="input_box">
							<html:text property="maPmMstrCommonDTO.filterPlantDesc" tabindex="80" />
							<p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
				<!-- 최신 Version 여부  -->
				<div class="field">
					<label>최신 Version 여부</label>
					<div class="input_box">
						<html:text property="maPmMstrCommonDTO.isLastVersion" tabindex="90"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!--  Revision 진행상태  -->
				<div class="field">
					<label>Revision 진행상태</label>
					<div class="input_box">
						<html:text property="maPmMstrCommonDTO.revisionStatusDesc" tabindex="100"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- Revision 번호  -->
				<div class="field">
					<label>Revision 번호</label>
					<div class="input_box">
						<html:text property="maPmMstrCommonDTO.revNo" tabindex="110"/>
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