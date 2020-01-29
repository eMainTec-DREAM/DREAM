<%--===========================================================================
출고요청 List
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.part.iss.emg.req.action.PartIssEmgReqListAction" %>
<%@ page import="dream.part.iss.emg.req.action.PartIssEmgReqDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 출고요청 -->
<title><bean:message key='MENU.PTISSREQ'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

/** 자동완성 변수 */
var deptAc;
var empAc;
var plantAc;
var ctctrAc;
var equipDescAc;

function loadPage() 
{
	//공장명
    if(loginUser.filterPlant!='null'){
    	partIssEmgReqListForm.elements['partIssEmgReqCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
    	partIssEmgReqListForm.elements['partIssEmgReqCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
	}
	
    partIssEmgReqListForm.elements['partIssEmgReqCommonDTO.filterIssStatus'].value = 'WT';
    partIssEmgReqListForm.elements['partIssEmgReqCommonDTO.filterIssStatusDesc'].value = 'WT';
    valSysDir('partIssEmgReqCommonDTO.filterIssStatus',  'partIssEmgReqCommonDTO.filterIssStatusDesc', 'PTEMGISSREQ_STATUS', true);
	
    initGrid();
    
    //부서 자동완성
    deptAc = new autoC({"partIssEmgReqCommonDTO.filterIssDeptDesc":"description"});
    deptAc.setTable("TADEPT");
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setAcResultMap({
        "partIssEmgReqCommonDTO.filterIssDeptId":"dept_id"
    });
    deptAc.setAcDConditionMap({
    	"plant" : "partIssEmgReqCommonDTO.filterPlantId"
    });
    deptAc.init();
    
    //사원 자동완성
    empAc = new autoC({"partIssEmgReqCommonDTO.filterRecByDesc":"emp_name"});
    empAc.setTable("TAEMP");
    empAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
	   });
    empAc.setAcResultMap({
        "partIssEmgReqCommonDTO.filterRecById":"emp_id"
    });
    empAc.setAcDConditionMap({
    	"dept_id" : "partIssEmgReqCommonDTO.filterIssDeptId",
    	"plant" : "partIssEmgReqCommonDTO.filterPlantId"
    });
    empAc.init();
    
 	// 공장코드
	plantAc = new autoC({"partIssEmgReqCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "partIssEmgReqCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
    
 	// 코스트센터
	ctctrAc = new autoC({"partIssEmgReqCommonDTO.filterCtctrDesc":"description"});
	ctctrAc.setTable("TACTCTR");
	ctctrAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
	ctctrAc.setAcResultMap({
        "partIssEmgReqCommonDTO.filterCtctrId":"ctctr_id"
    });
	ctctrAc.init();
    
 	// 설비
	equipDescAc = new autoC({"partIssEmgReqCommonDTO.filterEquipDesc":"description"});
    equipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcResultMap({
        "partIssEmgReqCommonDTO.filterEquipId":"equip_id"
    });
    equipDescAc.setAcDConditionMap({
    	"dept_id" : "partIssEmgReqCommonDTO.filterIssDeptId",
    	"plant" : "partIssEmgReqCommonDTO.filterPlantId"
    });
    equipDescAc.init();
    
    acSysDesc("partIssEmgReqCommonDTO.filterIssStatusDesc","partIssEmgReqCommonDTO.filterIssStatus","PTEMGISSREQ_STATUS");
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
    	partIssEmgReqListForm.elements['partIssEmgReqCommonDTO.ptEmgIssReqId'].value = "";
    	return sortColumn("partIssEmgReqList", this, partIssEmgReqListForm, "PTEMGISSREQID", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/partIssEmgReqList.do";
    partIssEmgReqListForm.elements['strutsAction'].value = '<%=PartIssEmgReqListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(partIssEmgReqListForm), "PTEMGISSREQID","Y");

}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_ptEmgIssReqId)
{
	partIssEmgReqListForm.elements['partIssEmgReqCommonDTO.ptEmgIssReqId'].value = _ptEmgIssReqId;
	findGridList('ReloadRow');
	partIssEmgReqListForm.elements['partIssEmgReqCommonDTO.ptEmgIssReqId'].value = "";
}

function goSearch()
{
	partIssEmgReqListForm.elements['partIssEmgReqCommonDTO.ptEmgIssReqId'].value = "";	// 검색시 Tab 이동Key Clear
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
	partIssEmgReqListForm.elements['partIssEmgReqCommonDTO.ptEmgIssReqId'].value =  getValueById(myGrid, selectedId,'PTEMGISSREQID');  
	goCommonTabPage(partIssEmgReqListForm, <%= PartIssEmgReqDetailAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('partIssEmgReqDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    partIssEmgReqListForm.elements['partIssEmgReqCommonDTO.ptEmgIssReqId'].value =  getValueById(myGrid, selectedId,'PTEMGISSREQID'); 
    partIssEmgReqListForm.elements['strutsAction'].value = '<%=PartIssEmgReqDetailAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(partIssEmgReqListForm), 'partIssEmgReqDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "partIssEmgReqDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	partIssEmgReqListForm.elements['partIssEmgReqCommonDTO.ptEmgIssReqId'].value = "";
    goCommonTabPage(partIssEmgReqListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
	var chkedRowsId = getCheckedRows(myGrid, 'ISDELCHECK');
	for(var i = 0 ;i < chkedRowsId.length; i++)
	{
		if(getValueById(myGrid, chkedRowsId[i], "ptEmgIssReqStatus") != "WT" &&
		   getValueById(myGrid, chkedRowsId[i], "ptEmgIssReqStatus") != "DA" &&
		   getValueById(myGrid, chkedRowsId[i], "ptEmgIssReqStatus") != "X")
		{
			alertMessage1('<bean:message key="MESSAGE.MSG0153"/>');
			return;
		}
		if(getValueById(myGrid, chkedRowsId[i], "reqBy") != loginUser.empId)
		{
			alertMessage1('<bean:message key="MESSAGE.MSG0214"/>');
			return;
		}
	}
	
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'PTEMGISSREQID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    partIssEmgReqListForm.strutsAction.value = '<%=PartIssEmgReqListAction.LIST_DELETE%>';
    var url = contextPath + "/partIssEmgReqList.do";
    
    $.post(url,FormQueryString(partIssEmgReqListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('partIssEmgReqDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	partIssEmgReqListForm.elements['partIssEmgReqCommonDTO.ptEmgIssReqId'].value = "";
	excelServerAction("partIssEmgReqList", partIssEmgReqListForm );  
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/partIssEmgReqList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="partIssEmgReqCommonDTO.ptEmgIssReqId"/><!-- Key -->
<html:hidden property="partIssEmgReqCommonDTO.filterIssDeptId"/>
<html:hidden property="partIssEmgReqCommonDTO.filterRecById"/>
<html:hidden property="partIssEmgReqCommonDTO.filterPlantId"/>
<html:hidden property="partIssEmgReqCommonDTO.filterIssStatus"/>
<html:hidden property="partIssEmgReqCommonDTO.filterCtctrId"/>
<html:hidden property="partIssEmgReqCommonDTO.filterEquipId"/>
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
						<html:text property="partIssEmgReqCommonDTO.filterIssDeptDesc" tabindex="10"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 출고일자 -->
				<div class="field">
					<label><bean:message key="LABEL.issDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="partIssEmgReqCommonDTO.filterIssStartDate" tabindex="20" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="partIssEmgReqCommonDTO.filterIssEndDate" tabindex="30" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 부품명/규격 -->
				<div class="field">
					<label><bean:message key="LABEL.partNameSize"/></label>
					<div class="input_box">
						<html:text property="partIssEmgReqCommonDTO.filterPartNameSize" tabindex="40"/>
					</div>
				</div>
				<!-- 수령자 -->
				<div class="field">
					<label><bean:message key="LABEL.recBy"/></label>
					<div class="input_box">
						<html:text property="partIssEmgReqCommonDTO.filterRecByDesc" tabindex="50"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 상태 -->
				<div class="field">
                    <label><bean:message key="LABEL.status"/></label>
                    <div class="input_box">
						<html:text property="partIssEmgReqCommonDTO.filterIssStatusDesc" tabindex="60" />
						<p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
				<!-- 출고No -->
				<div class="field">
					<label><bean:message key="LABEL.issNo"/></label>
					<div class="input_box">
						<html:text property="partIssEmgReqCommonDTO.filterIssNo" tabindex="70"/>
					</div>
				</div>
				<!-- 공장명 -->
				<div class="field">
                    <label><bean:message key="LABEL.plantDesc"/></label>
                    <div class="input_box">
						<html:text property="partIssEmgReqCommonDTO.filterPlantDesc" tabindex="80" />
						<p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
				<!-- 코스트센터 -->
				<div class="field">
                    <label><bean:message key="LABEL.ctctrName"/></label>
                    <div class="input_box">
						<html:text property="partIssEmgReqCommonDTO.filterCtctrDesc" tabindex="90" />
						<p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
				<!-- 설비 -->
				<div class="field">
                    <label><bean:message key="LABEL.equipDesc"/></label>
                    <div class="input_box">
						<html:text property="partIssEmgReqCommonDTO.filterEquipDesc" tabindex="100" />
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