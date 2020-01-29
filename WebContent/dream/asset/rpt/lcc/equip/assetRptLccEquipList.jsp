<%--===========================================================================
고장TOP(설비)
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.rpt.lcc.equip.action.AssetRptLccEquipListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 고장TOP(설비) -->
<title><bean:message key='MENU.LCCByEqp'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var eqLocAc;
var deptAc;
var eqCtgAc,equipAc;
var plantAc,uDeptAc;
var mainMngAc,subMngAc;

var selectedCid, selectedRowId;

function loadPage() 
{
	if(window.name != "LINKED_POPUP")
	{
		var startDate = assetRptLccEquipListForm.elements['assetRptLccEquipCommonDTO.filterStartDate'].value;
		var endDate = assetRptLccEquipListForm.elements['assetRptLccEquipCommonDTO.filterEndDate'].value;
		if((startDate=="" || startDate==null)&&(endDate=="" || endDate==null)) {
			assetRptLccEquipListForm.elements['assetRptLccEquipCommonDTO.filterStartDate'].value = getYear()+"-01";
			assetRptLccEquipListForm.elements['assetRptLccEquipCommonDTO.filterEndDate'].value   = getYear()+"-"+(getMonth());
		}
		
		//공장명
	    if(loginUser.filterPlant!='null'){
	    	assetRptLccEquipListForm.elements['assetRptLccEquipCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
	    	assetRptLccEquipListForm.elements['assetRptLccEquipCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
		}
	}
	
	assetRptLccEquipListForm.elements['assetRptLccEquipCommonDTO.filterEqLocDesc'].value = assetRptLccEquipListForm.elements['assetRptLccEquipCommonDTO.filterEqLocDesc'].value.replace(/& gt;/gi,'>');
	
	initGrid();
	
	eqLocAc = new autoC({"assetRptLccEquipCommonDTO.filterEqLocDesc":"full_desc"});
    eqLocAc.setTable("TAEQLOC");
    eqLocAc.setAcConditionMap({
  	   "comp_no":loginUser.compNo
  	   });
    eqLocAc.setAcResultMap({
        "assetRptLccEquipCommonDTO.filterEqLocId":"eqloc_id"
    });
    eqLocAc.setAcDConditionMap({
    	"plant" : "assetRptLccEquipCommonDTO.filterPlantId"
    });
    eqLocAc.init();
    
    uDeptAc = new autoC({"assetRptLccEquipCommonDTO.filterUsageDeptDesc":"description"});
    uDeptAc.setTable("TADEPT");
    uDeptAc.setAcConditionMap({
  	   "comp_no":loginUser.compNo
  	   });
    uDeptAc.setAcResultMap({
        "assetRptLccEquipCommonDTO.filterUsageDept":"dept_id"
    });
    uDeptAc.setAcDConditionMap({
    	"plant" : "assetRptLccEquipCommonDTO.filterPlantId"
    });
    uDeptAc.init();
    
    
    deptAc = new autoC({"assetRptLccEquipCommonDTO.filterDeptDesc":"description"});
    deptAc.setTable("TADEPT");
    deptAc.setAcConditionMap({
  	   "comp_no":loginUser.compNo
  	   });
    deptAc.setAcResultMap({
        "assetRptLccEquipCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.setAcDConditionMap({
    	"plant" : "assetRptLccEquipCommonDTO.filterPlantId"
    });
    deptAc.init();
    
    eqCtgAc = new autoC({"assetRptLccEquipCommonDTO.filterEqCtgDesc":"description"});
    eqCtgAc.setTable("TAEQCTG");
    eqCtgAc.setAcConditionMap({
  	   "comp_no":loginUser.compNo
  	   });
    eqCtgAc.setAcResultMap({
    	"assetRptLccEquipCommonDTO.filterEqCtgId":"eqctg_id"
    });
    eqCtgAc.init();
    
 	// 공장코드
	plantAc = new autoC({"assetRptLccEquipCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "assetRptLccEquipCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
    
    //담당자(쩡)
    mainMngAc = new autoC({"assetRptLccEquipCommonDTO.filterMainMngName":"emp_name"});
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    mainMngAc.setAcDConditionMap({
    	"plant" : "assetRptLccEquipCommonDTO.filterPlantId"
    });
    mainMngAc.setAcResultMap({
        "assetRptLccEquipCommonDTO.filterMainMngId":"emp_id"
    });
    mainMngAc.init();
	
    //담당자(부)
    subMngAc = new autoC({"assetRptLccEquipCommonDTO.filterSubMngName":"emp_name"});
    subMngAc.setTable("TAEMP");
    subMngAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    subMngAc.setAcDConditionMap({
    	"plant" : "assetRptLccEquipCommonDTO.filterPlantId"
    });
    subMngAc.setAcResultMap({
        "assetRptLccEquipCommonDTO.filterSubMngId":"emp_id"
    });
    subMngAc.init();
    
    //설비명
    equipAc = new autoC({"assetRptLccEquipCommonDTO.filterEquipDesc":"description"});
	equipAc.setTable("TAEQUIPMENT");
	equipAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
	equipAc.setAcResultMap({
        "assetRptLccEquipCommonDTO.filterEquipId":"EQUIP_ID"
    });
	equipAc.setAcDConditionMap({
    	"eqloc_id" : "assetRptLccEquipCommonDTO.filterEqLocId",
    	"plant" : "assetRptLccEquipCommonDTO.filterPlantId"
    	
    });
	equipAc.init();
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		selectedCid = columnId;
		selectedRowId = rowId;
		goOpen();
	});

	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
    assetRptLccEquipListForm.elements['assetRptLccEquipDetailDTO.startDate'].value = assetRptLccEquipListForm.elements['assetRptLccEquipCommonDTO.filterStartDate'].value;
    assetRptLccEquipListForm.elements['assetRptLccEquipDetailDTO.endDate'].value = assetRptLccEquipListForm.elements['assetRptLccEquipCommonDTO.filterEndDate'].value;
    assetRptLccEquipListForm.elements['assetRptLccEquipDetailDTO.plantId'].value = assetRptLccEquipListForm.elements['assetRptLccEquipCommonDTO.filterPlantId'].value;
    assetRptLccEquipListForm.elements['assetRptLccEquipDetailDTO.plantDesc'].value = assetRptLccEquipListForm.elements['assetRptLccEquipCommonDTO.filterPlantDesc'].value;
    findGridList('Search');   
}

function findGridList(sheetAction)
{
	if(checkValidation()) return;
	
	var form = document.assetRptLccEquipListForm;	
	form.strutsAction.value = '<%=AssetRptLccEquipListAction.LCC_EQUIP_LIST_FIND %>';
	
	var url = contextPath + "/assetRptLccEquipList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(assetRptLccEquipListForm), "ITEMNO", "Y");
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
	var form = document.assetRptLccEquipListForm;
	
	form.elements['assetRptLccEquipDetailDTO.eqLocId'].value = getValueById(myGrid, selectedId,'EQLOCID');
	form.elements['assetRptLccEquipDetailDTO.deptId'].value = getValueById(myGrid, selectedId,'DEPTID');
	form.elements['assetRptLccEquipDetailDTO.eqCtgId'].value = getValueById(myGrid, selectedId,'EQCTGID');
	form.elements['assetRptLccEquipDetailDTO.itemNo'].value = getValueById(myGrid, selectedId,'ITEMNO');
	form.elements['assetRptLccEquipDetailDTO.itemDesc'].value = getValueById(myGrid, selectedId,'ITEMDESC');
	goCommonTabPage(form, '' , pageId);
}

/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('assetRptLccEquipDetailList');
	goTabPage('assetRptLccEquipDetailChart');
	goTabPage('assetRptLccEquipMaintAmtDetailChart');
	goTabPage('assetRptLccEquipWorkTimeDetailChart');
	goTabPage('assetRptLccEquipFailCodeDetailChart');
}

 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
	 excelServerAction("assetRptLccEquipList", assetRptLccEquipListForm );
  }
 
 

  /*
   * 고장이력 보기
   */
  function goEqbmLink()
  {
  	if(typeof selectedRowId == "undefined" || "" == selectedRowId)
  	{
  		alertMessage1('<bean:message key="MESSAGE.MSG212"/>');
  		return;
  	}

  	var equipId   = getValueById(myGrid, selectedRowId,'EQUIPID');
  	var equipDesc = getValueById(myGrid, selectedRowId,'ITEMDESC');
  	var fromDate = assetRptLccEquipListForm.elements['assetRptLccEquipCommonDTO.filterStartDate'].value.replace("-","")+"01";
  	var toDate = assetRptLccEquipListForm.elements['assetRptLccEquipCommonDTO.filterEndDate'].value.replace("-","")+"31";
  	var woStatus = "C";
  	
  	goEqbmList('', '', equipId, equipDesc, fromDate, toDate, woStatus);
  }
   
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/assetRptLccEquipList.do">
<html:hidden property="strutsAction"/>

<html:hidden property="assetRptLccEquipCommonDTO.filterEqLocId"/>
<html:hidden property="assetRptLccEquipCommonDTO.filterDeptId"/>
<html:hidden property="assetRptLccEquipCommonDTO.filterEqCtgId"/>
<html:hidden property="assetRptLccEquipCommonDTO.filterPlantId"/>
<html:hidden property="assetRptLccEquipCommonDTO.filterUsageDept"/>
<html:hidden property="assetRptLccEquipCommonDTO.filterMainMngId"/>
<html:hidden property="assetRptLccEquipCommonDTO.filterSubMngId"/>
<html:hidden property="assetRptLccEquipCommonDTO.filterEquipId"/>

<html:hidden property="assetRptLccEquipDetailDTO.eqLocId"/>
<html:hidden property="assetRptLccEquipDetailDTO.eqLocDesc"/>
<html:hidden property="assetRptLccEquipDetailDTO.deptId"/>
<html:hidden property="assetRptLccEquipDetailDTO.deptDesc"/>
<html:hidden property="assetRptLccEquipDetailDTO.eqCtgId"/>
<html:hidden property="assetRptLccEquipDetailDTO.eqCtgDesc"/>
<html:hidden property="assetRptLccEquipDetailDTO.itemNo"/>
<html:hidden property="assetRptLccEquipDetailDTO.itemDesc"/>
<html:hidden property="assetRptLccEquipDetailDTO.startDate"/>
<html:hidden property="assetRptLccEquipDetailDTO.endDate"/>
<html:hidden property="assetRptLccEquipDetailDTO.chartValue"/>
<html:hidden property="assetRptLccEquipDetailDTO.plantId"/>
<html:hidden property="assetRptLccEquipDetailDTO.plantDesc"/>

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
				<!-- 월 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.month"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="assetRptLccEquipCommonDTO.filterStartDate" tabindex="10" />
							<p class="open_mon_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="assetRptLccEquipCommonDTO.filterEndDate" tabindex="20" />
							<p class="open_mon_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 설비위치  -->
				<div class="field">
					<label><bean:message key="LABEL.location"/></label>
					<div class="input_box">
						<html:text property="assetRptLccEquipCommonDTO.filterEqLocDesc" tabindex="30"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 부서  -->
				<div class="field">
					<label><bean:message key="LABEL.dept"/></label>
					<div class="input_box">
						<html:text property="assetRptLccEquipCommonDTO.filterDeptDesc" tabindex="40"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 사용부서  -->
				<div class="field">
					<label><bean:message key="LABEL.usedDept"/></label>
					<div class="input_box">
						<html:text property="assetRptLccEquipCommonDTO.filterUsageDeptDesc" tabindex="40"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 설비종류  -->
				<div class="field">
					<label><bean:message key="LABEL.eqCtg"/></label>
					<div class="input_box">
						<html:text property="assetRptLccEquipCommonDTO.filterEqCtgDesc" tabindex="50"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 설비번호  -->
                <div class="field">
                    <label><bean:message key="LABEL.equipNo"/></label>
                    <div class="input_box">
							<html:text property="assetRptLccEquipCommonDTO.filterItemNo" tabindex="51" />
                    </div>
                </div>
				<!-- 설비명  -->
                <div class="field">
                    <label><bean:message key="LABEL.equipName"/></label>
                    <div class="input_box">
							<html:text property="assetRptLccEquipCommonDTO.filterEquipDesc" tabindex="52" />
							<p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
				<!-- 관리자(정)  -->
                <div class="field">
                    <label><bean:message key="LABEL.mainManager"/></label>
                    <div class="input_box">
							<html:text property="assetRptLccEquipCommonDTO.filterMainMngName" tabindex="53" />
							<p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
				<!-- 관리자(부)  -->
                <div class="field">
                    <label><bean:message key="LABEL.subManager"/></label>
                    <div class="input_box">
							<html:text property="assetRptLccEquipCommonDTO.filterSubMngName" tabindex="56" />
							<p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
				<!-- 공장명  -->
                <div class="field">
                    <label><bean:message key="LABEL.plantDesc"/></label>
                    <div class="input_box">
							<html:text property="assetRptLccEquipCommonDTO.filterPlantDesc"
								tabindex="60" />
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