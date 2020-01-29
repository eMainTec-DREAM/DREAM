<%--===========================================================================
목록
author  jung7126
version $Id: invtList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.invt.list.action.InvtListAction" %>
<%@ page import="dream.invt.list.action.InvtDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/functions.tld" prefix="udf"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 제목-->
<title><bean:message key="MENU.EQUIPINVT"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//
var isNew = false;
var myGrid, rcmNameAc, deptAc, empAc, eqCtgAc, eqLocDescAc, equipDescAc, plantAc;

function loadPage() 
{
	setInitVal("invtCommonDTO.empId",loginUser.filterEmpId);
    setInitVal("invtCommonDTO.empDesc",loginUser.filterEmpDesc);
    
    setInitVal("invtCommonDTO.deptId",loginUser.filterDeptId);
    setInitVal("invtCommonDTO.deptDesc",loginUser.filterDeptDesc);
  	
    setInitVal("invtCommonDTO.filterPlantId",loginUser.filterPlant);
    setInitVal("invtCommonDTO.filterPlantDesc",loginUser.filterPlantDesc);
	
    initGrid();
    
    setFunction();
}

function setFunction()
{
	acSysDesc("invtCommonDTO.invtlistStatusDesc","invtCommonDTO.invtlistStatus","INVTLIST_STATUS");
	acSysDesc("invtCommonDTO.invtCategDesc","invtCommonDTO.invtCateg","INVT_CATEG");
	//acSysDesc("invtCommonDTO.invtTypeDesc","invtCommonDTO.invtTypeId","INVT_TYPE");
	acSysDesc("invtCommonDTO.filterInvtKindDesc","invtCommonDTO.filterInvtKindId","INVT_KIND");
	
	//------------------------------------------------------------------//
	
	var woTypeAc = new autoC({"invtCommonDTO.invtTypeDesc":"description"});
    woTypeAc.setAcConditionMap({
        	"list_type":"INVT_TYPE",
        	"is_use":"Y"
  	   });
    woTypeAc.setAcDConditionMap({
    	"param1":"invtCommonDTO.invtCateg"
	   });
    woTypeAc.setTable("TACDSYSD");
    woTypeAc.setAcResultMap({
        "invtCommonDTO.invtTypeId":"cdsysd_no"
    });
    woTypeAc.init();
    
	//-------------------------------------------------------------------//
	equipDescAc = new autoC({"invtCommonDTO.equipDesc":"description"});
    equipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcResultMap({
        "invtCommonDTO.equipId":"equip_id"
    });
    equipDescAc.setAcDConditionMap({
    	"eqloc_id" : "invtCommonDTO.eqlocId",
    	"eqctg_id" : "invtCommonDTO.eqctgId",
    	"plant"    : "invtCommonDTO.filterPlantId"
    });
    equipDescAc.setCustomLov("lovEquip('maWoResultMstrCommonDTO.filterEquipId','maWoResultMstrCommonDTO.filterEquipDesc')");
    equipDescAc.init();
   //-------------------------------------------------------------------//
	eqLocDescAc = new autoC({"invtCommonDTO.eqlocDesc":"full_desc"});
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setAcConditionMap({
  	   "comp_no":loginUser.compNo
  	   });
    eqLocDescAc.setAcResultMap({
        "invtCommonDTO.eqlocId":"eqloc_id"
    });
    eqLocDescAc.setAcDConditionMap({
    	"plant" : "invtCommonDTO.filterPlantId"
    });
    eqLocDescAc.init();
   //-------------------------------------------------------------------//
	eqCtgAc = new autoC({"invtCommonDTO.eqctgDesc":"description"});
	eqCtgAc.setTable("TAEQCTG");
	eqCtgAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    }); 
	eqCtgAc.setAcResultMap({
        "invtCommonDTO.eqctgId":"eqctg_id"
    });
	eqCtgAc.init();
	//-------------------------------------------------------------------//
	deptAc = new autoC({"invtCommonDTO.deptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "invtCommonDTO.deptId":"dept_id"
    });
    deptAc.setAcDConditionMap({
    	"plant" : "invtCommonDTO.filterPlantId"
    });
    deptAc.init();
	//-------------------------------------------------------------------//
    empAc = new autoC({"invtCommonDTO.empDesc":"emp_name"});
    empAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    empAc.setTable("TAEMP");
    empAc.setAcResultMap({
        "invtCommonDTO.empId":"emp_id"
    });
    empAc.setAcDConditionMap({
    	"plant"	  : "invtCommonDTO.filterPlantId"
    });
    empAc.setCustomLov("lovTable('invtCommonDTO.empId', 'invtCommonDTO.empDesc','TAEMPNAME')");
    empAc.init();
    
    //----------------------------------------------------------------//
    
    // 공장명
    plantAc = new autoC({"invtCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "invtCommonDTO.filterPlantId":"plant"
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
		invtListForm.elements['invtCommonDTO.invtlistId'].value = "";
   		return sortColumn("invtList", this, invtListForm, "invtlistId", ind, direction);
	});
	
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	
	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/invtList.do";
    invtListForm.elements['strutsAction'].value = '<%=InvtListAction.INVT_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(invtListForm), "invtlistId", "Y");

}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	clearKey();
	
    findGridList('Search');   
}

function clearKey()
{
	invtListForm.elements['invtCommonDTO.invtlistId'].value = "";
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
	if(!isNew)
	{
		invtListForm.elements['invtCommonDTO.invtlistId'].value = getValueById(myGrid, selectedId,'invtlistId');
	}

	goCommonTabPage(invtListForm, <%=InvtDetailAction.INVT_DETAIL_INIT %>, pageId);
}


/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(invtListId)
{
	invtListForm.elements['invtCommonDTO.invtlistId'].value = invtListId;

	findGridList('ReloadRow');
	
	clearKey();

}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('invtDetail');	
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    if(!isNew)
	{
    	invtListForm.elements['invtCommonDTO.invtlistId'].value = getValueById(myGrid, selectedId,'invtlistId');
	}
    invtListForm.elements['strutsAction'].value = '<%=InvtDetailAction.INVT_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(invtListForm), 'invtDetail'); 
} 

/**
 * Excel Export
 */
function goExcel()
{
    //excelAction(myGrid);
	clearKey();
	excelServerAction("invtList", invtListForm);
}

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "invtDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	clearKey();
    goCommonTabPage(invtListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'invtlistId'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
    
    invtListForm.strutsAction.value = '<%=InvtListAction.INVT_LIST_DELETE%>';
    var url = contextPath + "/invtList.do";

    $.post(url,FormQueryString(invtListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('invtDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/*
 * 엑셀 업로드
 */
function goExluploadLink()
{
	var actionUrl = contextPath + "/invtList.do";
    var param =  "&strutsAction=" + '<%= InvtListAction.GET_DATA %>'
              +  "&" + FormQueryString(invtListForm);
    XMLHttpPostVal(actionUrl, param, 'afterGoExlupload');
}

var dataArr;
function afterGoExlupload(ajaxXmlDoc)
{
	dataArr = '0';
	var data = parseXmlDoc(ajaxXmlDoc, 'DESC');
	var uploadTypeId = "";
	var uploadType = "";
	var tableName = "";
	
	data = data.toString();

	if(data != '0')
    {
		dataArr = data.split(',');
		
		uploadTypeId = dataArr[0];
		uploadType = dataArr[1];
		tableName = dataArr[2];
		
    }
		goExlupload(uploadTypeId, uploadType, tableName);
}

function setKeyAftercopy(_newId)
{
	findGridRow(_newId);
	
	//상세 창 열기
	isNew = true;
	invtListForm.elements['invtCommonDTO.invtlistId'].value = _newId;
	goOpen();
	isNew = false;
}

function afterCreate(_equipId, pageId)
{
	this.frames["tabFrameTAB.invtDetail"].frames["tabFrameTAB.invtEquipList"].afterCreate(_equipId, pageId);	
}

/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = invtListForm.elements['invtCommonDTO.invtlistId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/invtList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="invtCommonDTO.deptId"/>
<html:hidden property="invtCommonDTO.empId"/>
<html:hidden property="invtCommonDTO.invtlistStatus"/>
<html:hidden property="invtCommonDTO.invtCateg"/>
<html:hidden property="invtCommonDTO.eqlocId"/>
<html:hidden property="invtCommonDTO.eqctgId"/>
<html:hidden property="invtCommonDTO.equipId"/>
<html:hidden property="invtCommonDTO.invtlistId"/>
<html:hidden property="invtCommonDTO.invtTypeId"/>
<html:hidden property="invtCommonDTO.filterPlantId"/>
<html:hidden property="invtCommonDTO.filterInvtKindId"/>
<html:hidden property="invtCommonDTO.filterInvtComFromDate"/>
<html:hidden property="invtCommonDTO.filterInvtComToDate"/>
<html:hidden property="invtCommonDTO.filterUsageDeptId"/>
<html:hidden property="invtCommonDTO.filterUsageDeptDesc"/>

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
		<div class="article_box" >
			<div class="form_box">
				<!-- 투자시기 -->
				<div class="field">
					<label><bean:message key="LABEL.invtSDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="invtCommonDTO.filterStartDate" tabindex="3" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="invtCommonDTO.filterEndDate" tabindex="6" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 투자명 -->
				<div class="field">
					<label><bean:message key="LABEL.eqInvtDesc"/></label>
					<div class="input_box">
						<html:text property="invtCommonDTO.description" tabindex="10"/>
					</div>
				</div>
				<!-- 상태 -->
				<div class="field">
					<label><bean:message key="LABEL.invtStatus"/></label>
					<div class="input_box">
						<html:text property="invtCommonDTO.invtlistStatusDesc" tabindex="20"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 투자부서 -->
				<div class="field">
					<label><bean:message key="LABEL.invtDept"/></label>
					<div class="input_box">
						<html:text property="invtCommonDTO.deptDesc" tabindex="30"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 담당자 -->
				<div class="field">
					<label><bean:message key="LABEL.manager"/></label>
					<div class="input_box">
						<html:text property="invtCommonDTO.empDesc" tabindex="40"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 투자구분 -->
				<div class="field">
					<label><bean:message key="LABEL.invtCategDesc"/></label>
					<div class="input_box">
						<html:text property="invtCommonDTO.invtCategDesc" tabindex="50"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 분류 -->
                <div class="field">
                    <label><bean:message key="LABEL.category"/></label>
                    <div class="input_box">
                        <html:text property="invtCommonDTO.invtTypeDesc" tabindex="55"/>
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
						<html:text property="invtCommonDTO.eqlocDesc" tabindex="60"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 종류 -->
				<div class="field">
					<label><bean:message key="LABEL.eqCtg"/></label>
					<div class="input_box">
						<html:text property="invtCommonDTO.eqctgDesc" tabindex="70"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 투자계획 -->
				<div class="field">
					<label><bean:message key="LABEL.invtlistNo"/></label>
					<div class="input_box">
						<html:text property="invtCommonDTO.invtlistNo" tabindex="80"/>
					</div>
				</div>
				<!-- 설비 -->
				<div class="field">
					<label><bean:message key="LABEL.equipDesc"/></label>
					<div class="input_box">
						<html:text property="invtCommonDTO.equipDesc" tabindex="90"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 구매절차 -->
				<div class="field">
					<label><bean:message key="LABEL.invtDesc"/></label>
					<div class="input_box">
						<html:text property="invtCommonDTO.invtprctpDesc" tabindex="100"/>
					</div>
				</div>
				<!-- 공장명  -->
                <div class="field">
                    <label><bean:message key="LABEL.plantDesc"/></label>
                    <div class="input_box">
							<html:text property="invtCommonDTO.filterPlantDesc" tabindex="180" />
							<p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
				<!-- 투자종류  -->
                <div class="field">
                    <label><bean:message key="LABEL.invtKind"/></label>
                    <div class="input_box">
							<html:text property="invtCommonDTO.filterInvtKindDesc" tabindex="190" />
							<p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <c:set var="filePath" value="enhance/${compName}/invt/list/invtList_${compNo}.jsp" />
				<c:if test="${udf:isExist(filePath)}">
					<c:import charEncoding="UTF-8" url="/enhance/${compName}/invt/list/invtList_${compNo}.jsp"></c:import>
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
		<div class="article_box" >
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>		
			</div>			
		</div>
	</div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>

