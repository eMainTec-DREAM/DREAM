<%--===========================================================================
긴급출고
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.part.iss.emg.list.action.MaPtIssEmgListAction" %> 
<%@ page import="dream.part.iss.emg.list.action.MaPtIssEmgDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="common.bean.User"%>
<%@ page import="common.bean.MwareConfig"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
	String partGrade = MwareConfig.getPartGrade();
%>
<html>
<head>
<!-- 긴급출고 -->
<title><bean:message key="MENU.PTEMG"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//

var myGrid;

/** 자동완성 변수 */
var deptAc;
var recByAc;
var partNameAc;
var ptEmgIssStatusAc;
var ptEmgTaskStatusAc;
var plantAc;
var ctCtrAc;
var reqDeptAc;
var reqByAc;
var multiPartAc;
function loadPage() 
{
	var deptId = loginUser.filterDeptId;
	var deptDesc = loginUser.filterDeptDesc;

 	/* maPtIssEmgListForm.elements['maPtIssEmgCommonDTO.deptId'].value = deptId;
	maPtIssEmgListForm.elements['maPtIssEmgCommonDTO.deptDesc'].value = deptDesc; 
	
	maPtIssEmgListForm.elements['maPtIssEmgCommonDTO.startDateFrom'].value = getMinusDay(9);
	maPtIssEmgListForm.elements['maPtIssEmgCommonDTO.startDateTo'].value   = getToday(); */
    

	maPtIssEmgListForm.elements['maPtIssEmgCommonDTO.recByName'].value = loginUser.filterEmpDesc;
	maPtIssEmgListForm.elements['maPtIssEmgCommonDTO.recBy'].value = loginUser.filterEmpId;
	
	//공장명
    if(loginUser.filterPlant!='null'){
    	maPtIssEmgListForm.elements['maPtIssEmgCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
    	maPtIssEmgListForm.elements['maPtIssEmgCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
	}
	
    maPtIssEmgListForm.elements['maPtIssEmgCommonDTO.ptemgissStatus'].value = 'W';
    maPtIssEmgListForm.elements['maPtIssEmgCommonDTO.ptemgissStatusDesc'].value = 'W';
    valSysDir('maPtIssEmgCommonDTO.ptemgissStatus',  'maPtIssEmgCommonDTO.ptemgissStatusDesc', 'PTEMGISS_STATUS', true);
    
	
	initGrid();	
	
	deptAc = new autoC({"maPtIssEmgCommonDTO.issueDeptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "maPtIssEmgCommonDTO.issueDept":"dept_id"
    });
    deptAc.setAcDConditionMap({
    	"plant" : "maPtIssEmgCommonDTO.filterPlantId"
    });
    deptAc.init();
    
  	//요청부서 자동완성
    reqDeptAc = new autoC({"maPtIssEmgCommonDTO.filterReqDeptDesc":"description"});
    reqDeptAc.setTable("TADEPT");
    reqDeptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    reqDeptAc.setAcResultMap({
        "maPtIssEmgCommonDTO.filterReqDeptId":"dept_id"
    });
    reqDeptAc.setAcDConditionMap({
    	"plant" : "maPtIssEmgCommonDTO.filterPlantId"
    });
    reqDeptAc.init();
    
    recByAc = new autoC({"maPtIssEmgCommonDTO.recByName":"emp_name"});
    recByAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    recByAc.setTable("TAEMP");
    recByAc.setAcResultMap({
        "maPtIssEmgCommonDTO.recBy":"emp_id"
    });
    recByAc.setAcDConditionMap({
    	"dept_id" : "maPtIssEmgCommonDTO.filterReqDeptId",
    	"plant" : "maPtIssEmgCommonDTO.filterPlantId"
    });
    recByAc.init();
    
    partNameAc = new autoC({"maPtIssEmgCommonDTO.partDesc":"full_desc"});
    partNameAc.setAcConditionMap({
 	   "part_categ":"SPPT",
 	   "comp_no":loginUser.compNo
 	   });
    partNameAc.setTable("TAPARTS");
    partNameAc.init();
    
 	// 공장코드
	plantAc = new autoC({"maPtIssEmgCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "maPtIssEmgCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
    
    ctCtrAc = new autoC({"maPtIssEmgCommonDTO.filterCtctrDesc":"description"});
    ctCtrAc.setTable("TACTCTR");
    ctCtrAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    ctCtrAc.setAcResultMap({
        "maPtIssEmgCommonDTO.filterCtctrId":"ctctr_id"
    });
    ctCtrAc.init();
    
    reqByAc = new autoC({"maPtIssEmgCommonDTO.filterReqByDesc":"emp_name"});
    reqByAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    reqByAc.setTable("TAEMP");
    reqByAc.setAcResultMap({
        "maPtIssEmgCommonDTO.filterReqById":"emp_id"
    });
    reqByAc.setAcDConditionMap({
    	"dept_id" : "maPtIssEmgCommonDTO.filterReqDeptId",
    	"plant" : "maPtIssEmgCommonDTO.filterPlantId"
    });
    reqByAc.init();
    
    multiPartAc = new autoC({"maPtIssEmgDetailDTO.multiDesc":"full_desc"});
    multiPartAc.setTable("TAPARTS");
    multiPartAc.setAcConditionMap({
	   "comp_no":loginUser.compNo
	   ,"part_categ":"SPPT"
	   ,"wcode_id":loginUser.fromWcodeId
	   });
    multiPartAc.setAcResultMap({
	    "maPtIssEmgDetailDTO.multiKey":"part_id"
	});
    multiPartAc.setMultiSelect(true);
    multiPartAc.init();
    
    acSysDesc("maPtIssEmgCommonDTO.ptemgissStatusDesc","maPtIssEmgCommonDTO.ptemgissStatus","PTEMGISS_STATUS");
    acSysDesc("maPtIssEmgCommonDTO.ptemgTaskStatusDesc","maPtIssEmgCommonDTO.ptemgTaskStatus","PTEMG_TASK_STATUS");

}

function initGrid()
{
    myGrid = new dhtmlXGridObject('gridbox');
    myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
    
    myGrid.enableSmartRendering(true,500);
    myGrid.attachEvent("onRowSelect",function(rowId, columnId){
        goOpen();
    });
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

    setHeader(myGrid, "gridbox"); // grid, grid id

    myGrid.setNumberFormat("0,000",getCoumnIdx(myGrid,"issueQty"),".",",");
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maPtIssEmgList.do";

    maPtIssEmgListForm.elements['strutsAction'].value = '<%=MaPtIssEmgListAction.PTISSEMG_LIST_FIND%>';

    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maPtIssEmgListForm), "ptemgisslistId", "Y");
    
}

/**
 * Filter에서 Sheet 검색을 하는 경우이다.
 */
function goSearch()
{
	maPtIssEmgListForm.elements['maPtIssEmgCommonDTO.ptemgisslistId'].value = "";     // 검색시 Tab 이동Key Clear
	findGridList('Search');
}
function findGridRow(ptemgisslistId)
{
	maPtIssEmgListForm.elements['maPtIssEmgCommonDTO.ptemgisslistId'].value 	= ptemgisslistId;
	findGridList('ReloadRow');
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
    maPtIssEmgListForm.elements['maPtIssEmgCommonDTO.ptemgisslistId'].value = getValueById(myGrid, selectedId, 'ptemgisslistId');

    goCommonTabPage(maPtIssEmgListForm, <%=MaPtIssEmgDetailAction.PTISSEMG_DETAIL_INIT%>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
	goTabPage('maPtIssEmgDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    maPtIssEmgListForm.elements['maPtIssEmgCommonDTO.ptemgisslistId'].value = getValueById(myGrid, selectedId, 'ptemgisslistId');
    maPtIssEmgListForm.elements['strutsAction'].value = '<%=MaPtIssEmgDetailAction.PTISSEMG_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(maPtIssEmgListForm), 'maPtIssEmgDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "maPtIssEmgDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maPtIssEmgListForm.elements['maPtIssEmgCommonDTO.ptemgisslistId'].value = "";
    goCommonTabPage(maPtIssEmgListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
	var chkedRowsId = getCheckedRows(myGrid, 'ISDELCHECK');
	var coFlag = false;
	for(var i = 0 ;i < chkedRowsId.length; i++)
	{
		if(getValueById(myGrid, chkedRowsId[i], "ptemgissStatus") == "C"&&getValueById(myGrid, chkedRowsId[i], "ptemgTaskStatus") == "C")
		{
			coFlag = true;
		}
	}
	
	if(coFlag)
	{
		alertMessage1('<bean:message key="LABEL.compPtNotDel"/>'); //
		return;
	}
	
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'ptemgisslistId'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
    
    maPtIssEmgListForm.strutsAction.value = '<%=MaPtIssEmgListAction.PTISSEMG_LIST_DELETE%>';
    var url = contextPath + "/maPtIssEmgList.do";

    $.post(url,FormQueryString(maPtIssEmgListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('maPtIssEmgDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	maPtIssEmgListForm.elements['maPtIssEmgCommonDTO.ptemgisslistId'].value = "";
    excelServerAction("maPtIssEmgList", maPtIssEmgListForm);
}

function goSave(){
	var url = contextPath + "/maPtIssEmgList.do";
	
    $.post(url,FormQueryString(maPtIssEmgListForm), function(_data){
    	afterSave(_data);
    });
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    getTopPage().afterSaveAll(currentPageId);
    
    goSearch();
    
    setForNormal();
}

/**
 * 일괄등록
 */
function goRegbatch()
{
	multiPartAc.openLov();
}

function beforeSetAcValue(code)
{
	if(code == 'PART_ID')
	{
		setForUpdate();
	}
}

function setAcLovValue(rtnArr, code)
{
	if(code == 'maPtIssEmgDetailDTO.multiDesc')
	{
		maPtIssEmgListForm.strutsAction.value = '<%=MaPtIssEmgListAction.PTISSEMG_LIST_INPUT%>';
		
		maPtIssEmgListForm.elements['maPtIssEmgDetailDTO.ptemgissStatus'].value = "W";
	    maPtIssEmgListForm.elements['maPtIssEmgDetailDTO.ptissType'].value = "WOISS";
	   	maPtIssEmgListForm.elements['maPtIssEmgDetailDTO.ptemgTaskStatus'].value = "W";
	    maPtIssEmgListForm.elements['maPtIssEmgDetailDTO.partGrade'].value = '<%=partGrade%>';
	    maPtIssEmgListForm.elements['maPtIssEmgDetailDTO.issueDept'].value = loginUser.deptId;
	    maPtIssEmgListForm.elements['maPtIssEmgDetailDTO.issueBy'].value = loginUser.empId;
	    maPtIssEmgListForm.elements['maPtIssEmgDetailDTO.wcodeId'].value = loginUser.fromWcodeId;
		maPtIssEmgListForm.elements['maPtIssEmgDetailDTO.toWcodeId'].value = loginUser.toWcodeId;
		maPtIssEmgListForm.elements['maPtIssEmgDetailDTO.issueQty'].value = 1;
		maPtIssEmgListForm.elements['maPtIssEmgDetailDTO.issueDate'].value = getToday();
		
		maPtIssEmgListForm.elements['maPtIssEmgDetailDTO.multiDesc'].value = JSON.stringify(rtnArr);
		
		goSaveAll();
	}
}
//-->
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/maPtIssEmgList">
<html:hidden property="strutsAction"/>
<html:hidden property="maPtIssEmgCommonDTO.issueDept"/><!-- Key -->
<html:hidden property="maPtIssEmgCommonDTO.ptemgTaskStatus"/><!-- Key -->
<html:hidden property="maPtIssEmgCommonDTO.recBy"/><!-- Key -->
<html:hidden property="maPtIssEmgCommonDTO.ptemgissStatus"/><!-- Key -->
<html:hidden property="maPtIssEmgCommonDTO.ptemgisslistId"/>
<html:hidden property="maPtIssEmgCommonDTO.filterReqDeptId"/>
<html:hidden property="maPtIssEmgCommonDTO.filterPlantId"/>
<html:hidden property="maPtIssEmgCommonDTO.filterCtctrId"/>
<html:hidden property="maPtIssEmgCommonDTO.filterReqById"/>
<html:hidden property="maPtIssEmgDetailDTO.ptemgissStatus"/>
<html:hidden property="maPtIssEmgDetailDTO.ptissType"/>
<html:hidden property="maPtIssEmgDetailDTO.ptemgTaskStatus"/>
<html:hidden property="maPtIssEmgDetailDTO.partGrade"/>
<html:hidden property="maPtIssEmgDetailDTO.issueDept"/>
<html:hidden property="maPtIssEmgDetailDTO.issueBy"/>
<html:hidden property="maPtIssEmgDetailDTO.wcodeId"/>
<html:hidden property="maPtIssEmgDetailDTO.toWcodeId"/>
<html:hidden property="maPtIssEmgDetailDTO.issueQty"/>
<html:hidden property="maPtIssEmgDetailDTO.issueDate"/>
<html:hidden property="maPtIssEmgDetailDTO.multiKey"/>
<html:hidden property="maPtIssEmgDetailDTO.multiDesc"/>
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
               		<!-- 출고부서 -->
					<div class="field">
						<label><bean:message key="LABEL.issDept"/></label>
						<div class="input_box">
							<html:text property="maPtIssEmgCommonDTO.issueDeptDesc" tabindex="10"/>
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					
	                <!-- 출고일자 -->
					<div class="field">
						<label><bean:message key="LABEL.issDate"/></label>
						<div class="calendar_wrap">
							<div class="input_box input_carendar">
								<html:text property="maPtIssEmgCommonDTO.issueDateFrom" tabindex="20" />
								<p class="open_calendar"><span>날짜</span></p>
							</div>
							<div class="input_box input_carendar">
								<html:text property="maPtIssEmgCommonDTO.issueDateTo" tabindex="30" />
								<p class="open_calendar"><span>날짜</span></p>
							</div>
						</div>
					</div>
					<!-- 품명/규격 -->
					<div class="field">
						<label><bean:message key="LABEL.partNameSize"/></label>
						<div class="input_box">
							<html:text property="maPtIssEmgCommonDTO.partDesc" tabindex="40"/>
						</div>
					</div>
					<!-- 수령자 -->
					<div class="field">
						<label><bean:message key="LABEL.recBy"/></label>
						<div class="input_box">
							<html:text property="maPtIssEmgCommonDTO.recByName" tabindex="50"/>
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<!-- 출고상태 -->
					<div class="field">
						<label><bean:message key="LABEL.ptIssListStatus"/></label>
						<div class="input_box">
							<html:text property="maPtIssEmgCommonDTO.ptemgissStatusDesc" tabindex="65" />
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<!-- 작업연결상태 -->
					<div class="field">
						<label><bean:message key="LABEL.ptemgTaskStatus"/></label>
						<div class="input_box">
							<html:text property="maPtIssEmgCommonDTO.ptemgTaskStatusDesc" tabindex="75" />
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<!-- 출고번호 -->
					<div class="field">
                       <label><bean:message key="LABEL.ptIssListId"/></label>
                       <div class="input_box">
                            <html:text property="maPtIssEmgCommonDTO.filterPtemgisslistId" tabindex="80" />
                       </div>
                    </div>
                    <!-- 공장명  -->
	                <div class="field">
	                    <label><bean:message key="LABEL.plantDesc"/></label>
	                    <div class="input_box">
							<html:text property="maPtIssEmgCommonDTO.filterPlantDesc"
								tabindex="90" />
							<p class="open_spop">
	                            <a>
	                                <span>조회</span>
	                            </a>
	                        </p>
	                    </div>
	                </div>
	                <!-- 코스트센터  -->
	                <div class="field">
	                    <label><bean:message key="LABEL.kostl"/></label>
	                    <div class="input_box">
							<html:text property="maPtIssEmgCommonDTO.filterCtctrDesc" tabindex="100" />
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
							<html:text property="maPtIssEmgCommonDTO.filterReqDeptDesc" tabindex="20"/>
							<p class="open_spop">
								<a>
									<span>조회</span>
								</a>
							</p>
						</div>
					</div>
					<!-- 요청일자 -->
					<div class="field">
						<label><bean:message key="LABEL.appReqDate"/></label>
						<div class="calendar_wrap">
							<div class="input_box input_carendar">
								<html:text property="maPtIssEmgCommonDTO.filterReqStartDate" tabindex="50" />
								<p class="open_calendar"><span>날짜</span></p>
							</div>
							<div class="input_box input_carendar">
								<html:text property="maPtIssEmgCommonDTO.filterReqEndDate" tabindex="60" />
								<p class="open_calendar"><span>날짜</span></p>
							</div>
						</div>
					</div>
					<!-- 요청자 -->
					<div class="field">
						<label><bean:message key="LABEL.appReqBy"/></label>
						<div class="input_box">
							<html:text property="maPtIssEmgCommonDTO.filterReqByDesc" tabindex="40"/>
							<p class="open_spop">
								<a>
									<span>조회</span>
								</a>
							</p>
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