<%--===========================================================================
부품수리 - 목록
author  ssong
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.part.pur.req.action.MaPtPurReqListAction" %>
<%@ page import="dream.part.pur.req.action.MaPtPurReqDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 현장구매청구 -->
<title><bean:message key='MENU.SCPURCLAIM'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var myGrid;

var deptAc;
var ptStatusAc;
var mainMngAc;
var partNameAc;
var proGrid;
var plantAc;

function loadPage() 
{
	initGrid();
	
	// Button hide
    hideBtn("SAVE");
    hideBtn("EDITCNCL");
    
	maPtPurReqListForm.elements['maPtReqCommonDTO.filterRegStartDate'].value = getMinusMonth2(new Date(), -2); 
	maPtPurReqListForm.elements['maPtReqCommonDTO.filterRegEndDate'].value   = getToday();
	
	maPtPurReqListForm.elements['maPtReqCommonDTO.filterPlant'].value = loginUser.filterPlant;
	maPtPurReqListForm.elements['maPtReqCommonDTO.filterPlantDesc'].value = loginUser.filterPlantDesc;
	
    deptAc = new autoC({"maPtReqCommonDTO.filterDeptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "maPtReqCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.init();
    
    ptStatusAc = new autoC({"maPtReqCommonDTO.inputStatusDesc":"description"});
    ptStatusAc.setAcConditionMap({
        	"list_type":"PTPNLIST_STATUS",
        	"is_use":"Y"
  	   });
    ptStatusAc.setTable("TACDSYSD");
    ptStatusAc.setAcResultMap({
        "maPtReqCommonDTO.inputStatus":"cdsysd_no"
    });
    ptStatusAc.init();
    
    mainMngAc = new autoC({"maPtReqCommonDTO.filterEnterName":"emp_name"});
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcResultMap({
        "maPtReqCommonDTO.filterEnterId":"emp_id"
    });
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "maPtReqCommonDTO.filterDeptId"
    });
    mainMngAc.init();
    
    partNameAc = new autoC({"maPtReqCommonDTO.filterPartNameSize":"full_desc"});
    partNameAc.setAcConditionMap({
	   "part_categ":"SPPT",
	   "comp_no":loginUser.compNo
	   });
    partNameAc.setTable("TAPARTS");
    partNameAc.init();
    
    plantAc = new autoC({"maPtReqCommonDTO.filterPlantDesc":"description"});
    plantAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	});
    plantAc.setTable("TAPLANT");
    plantAc.setAcResultMap({
        "maPtReqCommonDTO.filterPlant":"plant"
    });
    plantAc.init();
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
    	maPtPurReqListForm.elements['maPtReqCommonDTO.reqId'].value = "";
       return sortColumn("maPtPurReqList", this, maPtPurReqListForm, "reqId", ind, direction);
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{	
    var url = contextPath + "/maPtPurReqList.do";

    maPtPurReqListForm.elements['strutsAction'].value = '<%=MaPtPurReqListAction.PTREQ_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maPtPurReqListForm), "reqId", "Y");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_reqId)
{
	maPtPurReqListForm.elements['maPtReqCommonDTO.reqId'].value = _reqId;
	findGridList('ReloadRow');
	maPtPurReqListForm.elements['maPtReqCommonDTO.reqId'].value = "";
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	maPtPurReqListForm.elements['maPtReqCommonDTO.reqId'].value = "";	// 검색시 Tab 이동Key Clear
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
	var form = document.maPtPurReqListForm;
	 
	form.elements['maPtReqCommonDTO.reqId'].value = getValueById(myGrid, selectedId, 'reqId');
	goCommonTabPage(form, <%= MaPtPurReqDetailAction.PTREQ_DETAIL_INIT %>, pageId);
}

/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('maPtPurReqDetail');	
}

 function goOpenAction()
 {
     var selectedId=myGrid.getSelectedRowId();
     if(selectedId == null) return;
     
     maPtPurReqListForm.elements['maPtReqCommonDTO.reqId'].value = getValueById(myGrid, selectedId, 'reqId');
     maPtPurReqListForm.elements['strutsAction'].value = '<%=MaPtPurReqDetailAction.PTREQ_DETAIL_INIT%>';
     openQuickTabPage(FormQueryString(maPtPurReqListForm), 'maPtPurReqDetail'); 
 } 
 
/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "maPtPurReqDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maPtPurReqListForm.elements['maPtReqCommonDTO.reqId'].value = "";
	goCommonTabPage(maPtPurReqListForm, '', pageId);
}

/**
  * 삭제
  */
function goDelete()
{
	var delArray = getDeletRows(myGrid, 'isDelCheck', 'reqId'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	
	maPtPurReqListForm.strutsAction.value = '<%=MaPtPurReqListAction.PTREQ_LIST_DELETE%>';
	var url = contextPath + "/maPtPurReqList.do";
	
	$.post(url,FormQueryString(maPtPurReqListForm)+delArray , function(_data){
    	afterDelete();
    });
}

function afterDelete()
{
	goClose('maPtPurReqDetail');
	goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG240"/>');
}

function goRequest() {
	var upArray = getSelectedRows(myGrid, 'isDelCheck', 'reqId'); //Grid, check box column seq, pk column seq
	if(typeof upArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    maPtPurReqListForm.strutsAction.value = '<%=MaPtPurReqListAction.PART_REQUEST%>';
    var url = contextPath + "/maPtPurReqList.do";
    
    $.post(url,FormQueryString(maPtPurReqListForm)+upArray , function(_data){
    	alertMessage1('<bean:message key="MESSAGE.MSG230"/>');
    	goSearch();
    	closeModal();
    });	
}

function afterAutoCmpt(codeName, rtnJsonArry)
{
	if(typeof exAfterAutoCmpt == "function") exAfterAutoCmpt(codeName, rtnJsonArry);
}

function goEdit()
{
	// Button Hide
	hideBtn("OPEN");
	hideBtn("DELETE");
	hideBtn("EXCEL");
	hideBtn("SETTING");
	hideBtn("CREATE");
	hideBtn("REQUEST");
	hideBtn("EDIT");
	// Button show
	showBtn("SAVE");
	showBtn("EDITCNCL");
	
	editRow(myGrid);	

    //Close Detail Page if it is open
    goClose("maPtPurReqDetail", this);

    //set for update mark.
    setForUpdate();
    
    //Set Grid as updatable
	var url = contextPath + "/maPtPurReqList.do";
	var stAct = "<%=MaPtPurReqListAction.PTREQ_LIST_SAVE %>";

	//URL, StrutsAction, Grid Obj, Call Back Function, Validation Rule (not_empty)
	proGrid = setGridUpdate(url, stAct, myGrid, "afterSave");

	//change column type (acedp (Auto complete, LOV), aced(Auto complete only), ednum(Auto complete and number only))
	setColumnType(myGrid, "qty", "ednum"); // EDIT 수량
	setColumnType(myGrid, "usedEquipDesc", "acedp"); //AC,EDIT,POPUP 사용설비
	setColumnType(myGrid, "usage", "ed"); // AC,EDIT,POPUP 사용용도 
	setColumnType(myGrid, "recDeptDesc", "acedp"); // AC,EDIT,POPUP 접수부서
	setColumnType(myGrid, "recByDesc", "acedp"); // AC,EDIT,POPUP 접수자
	setColumnType(myGrid, "remark", "ed"); // EDIT 비고
	
	myGrid.attachEvent("onEditCell",function(stage,rId,cInd,nValue,oValue){
		if(stage == 0) {
	    	var ips = getValueById(myGrid, rId, "purStatusId");
	    	var chkId = getValueById(myGrid, rId, "enterById");
	    	
	    	
	    	if(ips == "W" && loginUser.empId == chkId){
	    		return true;
	    	}
	        else{
	         	return false;
	        }
	    } else if (stage == 2) {
	    	if(0 >= getValueById(myGrid, rId, "qty")){
	    		alertMessage1("<bean:message key='LABEL.qty'/>"+"<bean:message key='MESSAGE.MSG0013' />");
	    	}
	    	return true;
	    }
		else return true;
	});
}
function setGridAc(_gridObj, _cellObj)
{
	// 사용설비 AC
    var usedEquipAc = new autoC({"USEDEQUIPDESC":"description"});
    usedEquipAc.setCol(_cellObj);
    usedEquipAc.setGrid(_gridObj);
    usedEquipAc.setTable("TAEQUIPMENT");
    usedEquipAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    });
    usedEquipAc.setAcResultMap({
        "USEDEQUIP":"equip_id"
    });
    
    usedEquipAc.setKeyName("USEDEQUIP");
    usedEquipAc.init();
    
 	// 접수부서 자동완성
    var reqDeptAc = new autoC({"RECDEPTDESC":"description"});
    reqDeptAc.setCol(_cellObj);
    reqDeptAc.setGrid(_gridObj);
 	reqDeptAc.setTable("TADEPT");
    reqDeptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    });
    reqDeptAc.setAcResultMap({
        "RECDEPT":"dept_id"
    });
    reqDeptAc.setKeyName("RECDEPT");
    reqDeptAc.init();
 	
    // 접수자 자동완성
    var reqByAc = new autoC({"RECBYDESC":"emp_name"});
    reqByAc.setCol(_cellObj);
    reqByAc.setGrid(_gridObj);
    reqByAc.setTable("TAEMP");
    reqByAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    });
    reqByAc.setAcResultMap({
        "RECBY":"emp_id"
    });
    reqByAc.setKeyName("RECBY");
    reqByAc.init();
}
function goSave()
{
	//================================
 	if(checkValidation()) return; // 필수 항목 체크 
 	//================================
 	
	var wArray = getRowIdsByValue(myGrid, "purStatusId", "W");
	
	for (var i = 0; i < wArray.length; i++) {
		if(0 >= getValueById(myGrid, wArray[i], "qty")){
			alertMessage1("<bean:message key='LABEL.qty'/>"+"<bean:message key='MESSAGE.MSG0013' />");
			closeModal();
			return false;
			
		}
	}
	
	//Send All Data ONce
	proGrid.sendData();
}

function goEditcncl()
{
	editcnclRow(myGrid);
	
	afterSave();
}

function afterSave()
{
	// Button Hide
	hideBtn("SAVE");
	hideBtn("EDITCNCL");
	// Button Show
	showBtn("OPEN");
	showBtn("DELETE");
	showBtn("EXCEL");
	showBtn("SETTING");
	showBtn("CREATE");
	showBtn("REQUEST");
	showBtn("EDIT");
	/* 
	findGridRow(maPtPurReqListForm.elements['maPtReqCommonDTO.reqId'].value);
	 */
	afterEditRow(myGrid);
	
	//Clear Key Value
	maPtPurReqListForm.elements['maPtReqCommonDTO.reqId'].value = "";
	
	//Search
	goSearch();
	
	//Clear Update Mark for this page 
	clearUpdate(currentPageId);
	
	//Make Page as Normal
	setForNormal();
}

/**
 * Excel Export
 */
function goExcel()
{
	maPtPurReqListForm.elements['maPtReqCommonDTO.reqId'].value = "";
    excelServerAction("maPtPurReqList", maPtPurReqListForm );  
}  
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maPtPurReqList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maPtReqCommonDTO.reqId"/><!-- Key -->
<html:hidden property="maPtReqCommonDTO.inputStatus"/>
<html:hidden property="maPtReqCommonDTO.filterDeptId"/>
<html:hidden property="maPtReqCommonDTO.filterEnterId"/>
<html:hidden property="maPtReqCommonDTO.filterPlant"/>
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
				<div class="b_line"></div> 
				<div class="fb_group1">
					 
				</div>
			</div>
        </div><!--sheader_box end-->
		<div class="article_box">
			<div class="form_box">
                <!-- 신청부서 -->
                <div class="field">
                    <label><bean:message key="LABEL.entDept"/></label>
                    <div class="input_box">
                        <html:text property="maPtReqCommonDTO.filterDeptDesc" tabindex="10" />
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
			 	<!-- 신청일자 -->
				<div class="field">
					<label><bean:message key="LABEL.reDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
	                        <html:text property="maPtReqCommonDTO.filterRegStartDate" tabindex="20" />
	                        <p class="open_calendar"><span>날짜</span></p>
	                    </div>
	                    <div class="input_box input_carendar">
	                        <html:text property="maPtReqCommonDTO.filterRegEndDate" tabindex="30" />
	                        <p class="open_calendar"><span>날짜</span></p>
	                    </div>
	                </div>
				</div>
                <!-- 부품명 -->
				<div class="field">
					<label><bean:message key="LABEL.partNameSize"/></label>
					<div class="input_box">
						<html:text property="maPtReqCommonDTO.filterPartNameSize" tabindex="20"/>
					</div>
				</div>
                <!-- 품번 -->
                 <div class="field">
                       <label><bean:message key="LABEL.partNo"/></label>
                       <div class="input_box">
                            <html:text property="maPtReqCommonDTO.filterPartNo" tabindex="50"/>
                       </div>
                   </div>
				<!-- 작성상태  -->
				<div class="field">
					<label><bean:message key="LABEL.stWrkStatus"/></label>
					<div class="input_box">
						<html:text property="maPtReqCommonDTO.inputStatusDesc" tabindex="60" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				 <!-- 요청번호 -->
                 <div class="field">
                       <label><bean:message key="LABEL.reqNo"/></label>
                       <div class="input_box">
                            <html:text property="maPtReqCommonDTO.filterReqNo" tabindex="20"/>
                       </div>
                   </div>
				<!-- 작성자 -->
				<div class="field">
					<label><bean:message key="LABEL.updBy"/></label>
					<div class="input_box">
						<html:text property="maPtReqCommonDTO.filterEnterName" tabindex="100" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 접수일자 -->
				<div class="field">
					<label><bean:message key="LABEL.recpDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="maPtReqCommonDTO.filterStartRecDate" tabindex="100" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="maPtReqCommonDTO.filterEndRecDate" tabindex="110" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 공장 -->
                <div class="field">
                    <label><bean:message key="LABEL.plant"/></label>
                    <div class="input_box">
                        <html:text property="maPtReqCommonDTO.filterPlantDesc" tabindex="120" />
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
				<div class="b_line"></div> 
				<div class="fb_group1">
					 
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