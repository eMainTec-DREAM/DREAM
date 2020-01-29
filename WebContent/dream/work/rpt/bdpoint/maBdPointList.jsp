<%--===========================================================================
이상점검조치 - 목록
author  kim21017
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.mabdpoint.action.MaBdPointListAction" %>
<%@ page import="dream.work.rpt.mabdpoint.action.MaBdPointDetailAction" %>
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
<!-- 이상점검조치 -->
<title><bean:message key='MENU.BDPOINT'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var myGrid;
/** 자동완성 변수 */
var deptAc;
var empNameAc;
var eqLocDescAc;
var EqTypeDescAc;
var wkCtrDescAc;
var plantAc;
var usageDeptAc;

function loadPage() 
{
	setInitVal("maBdPointCommonDTO.filterDeptId", loginUser.filterDeptId);
	setInitVal("maBdPointCommonDTO.filterDeptDesc", loginUser.filterDeptDesc);
		
	setInitVal("maBdPointCommonDTO.filterStartDate", getMinusMonth2(new Date(), -1));
	setInitVal("maBdPointCommonDTO.filterEndDate", getToday());

	setInitVal("maBdPointCommonDTO.filterRepStatus", 'BD');
	setInitVal("maBdPointCommonDTO.filterRepStatusDesc", getSysCodeDesc("BD","PM_POINT_REP_STATUS"));
	
	setInitVal("maBdPointCommonDTO.filterEqLocId", loginUser.eqLocId);
	setInitVal("maBdPointCommonDTO.filterEqLocDesc", loginUser.eqLocDesc);

	setInitVal("maBdPointCommonDTO.filterEqCtgTypeId", loginUser.eqCtgTypeId);
	setInitVal("maBdPointCommonDTO.filterEqCtgTypeDesc", loginUser.eqCtgTypeDesc);

	setInitVal("maBdPointCommonDTO.filterWkCtrId", loginUser.filterWkCtrId);
	setInitVal("maBdPointCommonDTO.filterWkCtrDesc", loginUser.filterWkCtrDesc);
	//공장명
	setInitVal("maBdPointCommonDTO.filterPlantId", loginUser.filterPlant);
	setInitVal("maBdPointCommonDTO.filterPlantDesc", loginUser.filterPlantDesc);

	setInitVal("maBdPointCommonDTO.reloadRow", "N");

	//Set AC 
 	setFunction();
 
    initGrid();
}

function setFunction()
{
   //조치결과 AC
    acSysDesc("maBdPointCommonDTO.filterRepStatusDesc","maBdPointCommonDTO.filterRepStatus","PM_POINT_REP_STATUS");
    
    deptAc = new autoC({"maBdPointCommonDTO.filterDeptDesc":"description"});
    deptAc.setAcDisplay("DESCRIPTION");
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "maBdPointCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.setAcDConditionMap({
    	"plant" : "maBdPointCommonDTO.filterPlantId"
    });
    deptAc.init();
    
    empNameAc = new autoC({"maBdPointCommonDTO.filterEmpName":"emp_name"});
    empNameAc.setAcDisplay("EMP_NAME");
    empNameAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    empNameAc.setTable("TAEMP");
    empNameAc.setAcResultMap({
        "maBdPointCommonDTO.filterEmpId":"emp_id"
    });
    empNameAc.setAcDConditionMap({
    	"dept_id" : "maBdPointCommonDTO.filterDeptId",
    	"plant" : "maBdPointCommonDTO.filterPlantId"
    });
    empNameAc.init();
    
    eqLocDescAc = new autoC({"maBdPointCommonDTO.filterEqLocDesc":"full_desc"});
    eqLocDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setAcResultMap({
        "maBdPointCommonDTO.filterEqLocId":"eqloc_id"
    });
    eqLocDescAc.setAcDConditionMap({
    	"plant" : "maBdPointCommonDTO.filterPlantId"
    });
    eqLocDescAc.init();
    
    eqTypeAc = new autoC({"maBdPointCommonDTO.filterEqCtgDesc":"full_desc"});
    eqTypeAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqTypeAc.setTable("TAEQCTG");
    eqTypeAc.setAcResultMap({
        "maBdPointCommonDTO.filterEqCtgId":"eqctg_id"
    });
    eqTypeAc.init();

    //설비유형 AC
    acSysDesc("maBdPointCommonDTO.filterEqCtgTypeDesc","maBdPointCommonDTO.filterEqCtgTypeId","EQCTG_TYPE");
    
    wkCtrDescAc = new autoC({"maBdPointCommonDTO.filterWkCtrDesc":"description"});
    wkCtrDescAc.setAcDisplay("DESCRIPTION");
    wkCtrDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    wkCtrDescAc.setTable("TAWKCTR");
    wkCtrDescAc.setAcResultMap({
        "maBdPointCommonDTO.filterWkCtrId":"wkctr_id"
    });
    wkCtrDescAc.init();
    
    plantAc = new autoC({"maBdPointCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "maBdPointCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
    
    /* 사용부서 AC */
	//------------------------------------------------------------------------------------
    usageDeptAc = new autoC({"maBdPointCommonDTO.filterUsageDeptDesc":"description"});
    usageDeptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    usageDeptAc.setTable("TADEPT");
    usageDeptAc.setAcResultMap({
        "maBdPointCommonDTO.filterUsageDeptId":"dept_id"
    });
    usageDeptAc.setAcDConditionMap({
    	"plant" : "maBdPointCommonDTO.filterPlantId"
    });
    usageDeptAc.init();
    //-------------------------------------------------------------------------------------
    
    //작업형태  AC
    acSysDesc("maBdPointCommonDTO.filterPmTypeDesc","maBdPointCommonDTO.filterPmTypeId","PMI_TYPE");
    
}

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		var selectedId=myGrid.getSelectedRowId();
		selectedId = rowId;
		goOpen();
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	maBdPointListForm.elements['maBdPointCommonDTO.woNgPointId'].value = "";
        return sortColumn("maBdPointList", this, maBdPointListForm, "WONGPOINTID", ind, direction);
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
    var url = contextPath + "/maBdPointList.do";

    maBdPointListForm.elements['strutsAction'].value = '<%=MaBdPointListAction.BD_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maBdPointListForm), "WONGPOINTID","Y");
    
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_woNgPointId)
{
	maBdPointListForm.elements['maBdPointCommonDTO.woNgPointId'].value = _woNgPointId;
	maBdPointListForm.elements['maBdPointCommonDTO.reloadRow'].value = "Y";
	findGridList('ReloadRow');
	maBdPointListForm.elements['maBdPointCommonDTO.reloadRow'].value = "N";
	maBdPointListForm.elements['maBdPointCommonDTO.woNgPointId'].value = "N";
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	if(checkValidation()) return;
	maBdPointListForm.elements['maBdPointCommonDTO.woNgPointId'].value = "";	// 검색시 Tab 이동Key Clear
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
	var form = document.maBdPointListForm;
	var selectedId=myGrid.getSelectedRowId();
	
	form.elements['maBdPointCommonDTO.woNgPointId'].value = getValueById(myGrid, selectedId, 'WONGPOINTID');
	goCommonTabPage(form, <%= MaBdPointDetailAction.BD_DETAIL_INIT %>, pageId);
}

/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('maBdPointDetail');	
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    maBdPointListForm.elements['maBdPointCommonDTO.woNgPointId'].value = getValueById(myGrid, selectedId, 'WONGPOINTID');
    maBdPointListForm.elements['strutsAction'].value = '<%=MaBdPointDetailAction.BD_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(maBdPointListForm), 'maBdPointDetail'); 
} 
 
/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "maBdPointDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maBdPointListForm.elements['maBdPointCommonDTO.woNgPointId'].value = "";
	goCommonTabPage(maBdPointListForm, '', pageId);
}

/**
 * Excel Export
 */
function goExcel()
{
	maBdPointListForm.elements['maBdPointCommonDTO.woNgPointId'].value = "";
	excelServerAction("maBdPointList", maBdPointListForm );  
}

function goSelect(){
	var param = "strutsAction=1001";
    var url =  contextPath + "/maWoResultSelect.do";
        
    openLayerPopup("maWoResultSelect", param);
}

function setAfterSelect(returnArray){

    var woType = returnArray[0];
    var pmType = returnArray[1];
    var _pageId  = returnArray[2];

    var ifm = getIframeContent();
    
    maBdPointListForm.elements['maBdPointCommonDTO.woType'].value = woType;
    maBdPointListForm.elements['maBdPointCommonDTO.pmType'].value = pmType;
    
}

 
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maBdPointList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maBdPointCommonDTO.woNgPointId"/><!-- Key -->
<html:hidden property="maBdPointCommonDTO.filterDeptId"/>
<html:hidden property="maBdPointCommonDTO.filterEmpId"/>
<html:hidden property="maBdPointCommonDTO.filterEqLocId"/>
<html:hidden property="maBdPointCommonDTO.filterEqCtgId"/>
<html:hidden property="maBdPointCommonDTO.filterWkCtrId"/>
<html:hidden property="maBdPointCommonDTO.filterEqCtgTypeId"/>
<html:hidden property="maBdPointCommonDTO.filterRepStatus"/>
<html:hidden property="maBdPointCommonDTO.pmiType"/>
<html:hidden property="maBdPointCommonDTO.pmType"/>
<html:hidden property="maBdPointCommonDTO.woType"/>
<html:hidden property="maBdPointCommonDTO.filterUsageDeptId"/>
<html:hidden property="maBdPointCommonDTO.filterPmTypeId"/>
<html:hidden property="maBdPointCommonDTO.filterPlantId"/>
<html:hidden property="maBdPointCommonDTO.reloadRow"/>
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
				<!-- 점검일자 -->
				<div class="field">
					<label><bean:message key="LABEL.inspectDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="maBdPointCommonDTO.filterStartDate" tabindex="10" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="maBdPointCommonDTO.filterEndDate" tabindex="20" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 조치결과  -->
				<div class="field">
					<label><bean:message key="LABEL.reCdResult"/></label>
					<div class="input_box">
						<html:text property="maBdPointCommonDTO.filterRepStatusDesc" tabindex="30" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
                <!-- 부서 -->
				<div class="field">
					<label><bean:message key="LABEL.dept"/></label>
					<div class="input_box">
						<html:text property="maBdPointCommonDTO.filterDeptDesc" tabindex="40" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				
                <!-- 작업자 -->
                <div class="field">
                    <label><bean:message key="LABEL.woCraft"/></label>
                    <div class="input_box">
                        <html:text property="maBdPointCommonDTO.filterEmpName" tabindex="50" />
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
				<!-- 설비번호 -->
				<div class="field">
					<label><bean:message key="LABEL.equipNo"/></label>
					<div class="input_box">
						<html:text property="maBdPointCommonDTO.filterItemNo" tabindex="60"/>
					</div>
				</div>                
				<!-- 위치 -->
				<div class="field">
					<label><bean:message key="LABEL.location"/></label>
					<div class="input_box">
						<html:text property="maBdPointCommonDTO.filterEqLocDesc" tabindex="60"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 종류 -->
				<div class="field">
					<label><bean:message key="LABEL.type"/></label>
					<div class="input_box">
						<html:text property="maBdPointCommonDTO.filterEqCtgDesc" tabindex="70" />
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 설비유형  -->
				<div class="field">
					<label><bean:message key="LABEL.eqCtgType"/></label>
					<div class="input_box">
						<html:text property="maBdPointCommonDTO.filterEqCtgTypeDesc" tabindex="80" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
                <div class="field">
                     <label><bean:message key="LABEL.wkCtr"/></label>
                     <div class="input_box">
                        <html:text property="maBdPointCommonDTO.filterWkCtrDesc" tabindex="90"/>
                         <p class="open_spop">
                             <a>
                                 <span>조회</span>
                             </a>
                         </p>
                   </div>
                </div>
                
                <!-- 작업형태 -->
                <div class="field">
                     <label><bean:message key="LABEL.pmTypeDesc"/></label>
                     <div class="input_box">
                        <html:text property="maBdPointCommonDTO.filterPmTypeDesc" tabindex="100"/>
                         <p class="open_spop">
                             <a>
                                 <span>조회</span>
                             </a>
                         </p>
                   </div>
                </div>
                <!-- 공장명  -->
                <div class="field">
                    <label><bean:message key="LABEL.plantDesc"/></label>
                    <div class="input_box">
							<html:text property="maBdPointCommonDTO.filterPlantDesc" tabindex="110" />
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
						<html:text property="maBdPointCommonDTO.filterUsageDeptDesc" tabindex="20" />
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