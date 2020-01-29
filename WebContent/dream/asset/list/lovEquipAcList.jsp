<%--===========================================================================
설비 Popup
author  ssong
version $Id: lovEquipPopup.jsp,v 1.1 2016/02/18 09:12:01 ssong Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/functions.tld" prefix="udf"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ page import="common.bean.User"%>
<%@ page import="dream.asset.list.action.LovEquipListAction"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html>
<head>
<!-- 설비 -->
<title><bean:message key="LABEL.equipment"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<!-- ########## page 상단 공통 : 모든 페이지 적용 ########## --> 

<script language="javascript">
<!--//

//그리드명
var myGrid;
/** 자동완성 변수 */
var equipDescAc;
var equipNoAc;
var eqLocDescAc;
var eqCtgAc;
var mainMngAc;
var subMngName;
var deptAc;
var eqStatusAc;
var plantAc;

var conditionFilterMap = {
		"dept_id":"lovEquipListDTO.deptId"
		,"deptDesc":"lovEquipListDTO.deptDesc"
		,"plant":"lovEquipListDTO.plantId"
		,"plantDesc":"lovEquipListDTO.plantDesc"
	};
	
function loadPage() 
{
	
	convertCondition();
	
	if(loginUser != null)
	{
		setInitVal('lovEquipListDTO.plantId', loginUser.filterPlant);
		setInitVal('lovEquipListDTO.plantDesc', loginUser.filterPlantDesc);
		
		setInitVal('lovEquipListDTO.deptId', loginUser.filterDeptId);
		setInitVal('lovEquipListDTO.deptDesc', loginUser.filterDeptDesc);
	}
	initGrid();
	
	//--------------------------------------------------------------------------------//
	equipDescAc = new autoC({"lovEquipListDTO.equipDesc":"description"});
    equipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcDConditionMap({
    	"eqloc_id" : "lovEquipListDTO.eqLocId",
    	"eqctg_id" : "lovEquipListDTO.eqCtgId",
    	"dept_id" : "lovEquipListDTO.deptId",
    	"plant" : "lovEquipListDTO.plantId"
    });
    equipDescAc.init();
  //--------------------------------------------------------------------------------//
  
    eqLocDescAc = new autoC({"lovEquipListDTO.eqLocDesc":"full_desc"});
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqLocDescAc.setAcResultMap({
        "lovEquipListDTO.eqLocId":"eqloc_id"
    });
    eqLocDescAc.setAcDConditionMap({
    	"plant" : "lovEquipListDTO.plantId"
    });
    eqLocDescAc.init();
  //--------------------------------------------------------------------------------//
  
    eqCtgAc = new autoC({"lovEquipListDTO.eqCtgDesc":"full_desc"});
    eqCtgAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqCtgAc.setTable("TAEQCTG");
    eqCtgAc.setAcResultMap({
        "lovEquipListDTO.eqCtgId":"eqctg_id"
    });
    eqCtgAc.init();
  //--------------------------------------------------------------------------------//
  
    mainMngAc = new autoC({"lovEquipListDTO.mainMngName":"emp_name"});
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcResultMap({
        "lovEquipListDTO.mainMngId":"emp_id"
    });
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "lovEquipListDTO.deptId",
    	"plant" : "lovEquipListDTO.plantId"
    });
    mainMngAc.init();
  //--------------------------------------------------------------------------------//
  
    subMngName = new autoC({"lovEquipListDTO.subMngName":"emp_name"});
    subMngName.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    subMngName.setTable("TAEMP");
    subMngName.setAcResultMap({
        "lovEquipListDTO.subMngId":"emp_id"
    });
    subMngName.setAcDConditionMap({
    	"dept_id" : "lovEquipListDTO.deptId",
    	"plant" : "lovEquipListDTO.plantId"
    });
    subMngName.init();
  //--------------------------------------------------------------------------------//
  
    deptAc = new autoC({"lovEquipListDTO.deptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "lovEquipListDTO.deptId":"dept_id"
    });
    deptAc.setAcDConditionMap({
    	"plant" : "lovEquipListDTO.plantId"
    });
    deptAc.init();
  //--------------------------------------------------------------------------------//
  
  // 공장코드
	plantAc = new autoC({"lovEquipListDTO.plantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "lovEquipListDTO.plantId":"plant"
    });
    plantAc.init();
  
    acSysDesc("lovEquipListDTO.eqStatusDesc","lovEquipListDTO.eqStatusId","EQ_STATUS");
    acSysDesc("lovEquipListDTO.eqCtgTypeDesc","lovEquipListDTO.eqCtgTypeId","EQCTG_TYPE");
    acSysDesc("lovEquipListDTO.isLawEq","lovEquipListDTO.isLawEq","IS_USE",true);
    acSysDesc("lovEquipListDTO.filterIsLastVersionDesc","lovEquipListDTO.filterIsLastVersionId","IS_USE");
    
}

/**
 * 그리드 초기화
 */
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableMultiselect(chkFilter());
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowDblClicked",function(rowId, columnId){
		goConfirm();
	});
// 	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
//         lovEquipListForm.elements['lovEquipListDTO.itemNo'].value = "";
//         return sortColumn("lovEquipAcList", this, lovEquipListForm, "ITEM_NO", ind, direction);
//     });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	setHeader(myGrid, "gridbox", "goSearch"); // grid, grid id //???
}


/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
	var form = document.lovEquipListForm;	
	form.strutsAction.value = '<%=LovEquipListAction.LOV_EQUIP_AC_FIND%>';
	var url = contextPath + "/lovEquipList.do";
	
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(form), "ITEM_NO", "Y");
    
}

function goSelect(){
	goConfirm();
}

/**
 * 확인
 */
function goConfirm()
{	
	setAcValue(myGrid, "equip_id");  // (gridObj, Id Column If exist)
}

/**
 * 검색
 */
function goSearch()
{
	findGridList("Search");
}

function goClose()
{
	closeLayerPopup(this);
}

function convertCondition()
{
	var paramObj = JSON.parse(M$('param').value);
	for(var key in paramObj){
		if(typeof conditionFilterMap[key] != "undefined" && typeof M$(conditionFilterMap[key]) == "object")
		{
			M$(conditionFilterMap[key]).value = paramObj[key];
			paramObj[key] = '';
			M$('param').value = JSON.stringify(paramObj);
		}
	}
}

//-->
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<!-- searchbox 박스 Line -->
	<html:form action="/lovEquipList" >
<html:hidden property="currentPageId"/>
<html:hidden property="keyCode" /><html:hidden property="chName" />
<html:hidden property="resultCol" /><html:hidden property="multiSelect"/>
<html:hidden property="codeType" />
<html:hidden property="param" />
<html:hidden property="label" />
<html:hidden property="title" />

		<html:hidden property="lovEquipListDTO.extCode1" />
		<html:hidden property="lovEquipListDTO.extCode2" />
		<html:hidden property="lovEquipListDTO.codeType" />
		<html:hidden property="lovEquipListDTO.eqLocId" />
		<html:hidden property="lovEquipListDTO.eqCtgId" />
		<html:hidden property="lovEquipListDTO.plfTypeId" />
		<html:hidden property="lovEquipListDTO.plfTypeDesc" />
		<html:hidden property="lovEquipListDTO.mainMngId" />
		<html:hidden property="lovEquipListDTO.subMngId" />
		<html:hidden property="lovEquipListDTO.deptId" />
		<html:hidden property="lovEquipListDTO.eqStatusId" />
		<html:hidden property="lovEquipListDTO.eqCtgTypeId" />
		<html:hidden property="lovEquipListDTO.multiSelect" />
		<html:hidden property="lovEquipListDTO.plantId"/>
		
		<html:hidden property="lovEquipListDTO.filterIsLastVersionId" />
		<html:hidden property="strutsAction" />
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
					<div class="field">
						<!-- 설비번호 -->
						<label><bean:message key="LABEL.equipNo"/></label>
						<div class="input_box">
							<html:text property="lovEquipListDTO.itemNo" tabindex="10"/>
						</div>
					</div>
					<div class="field">
						<!-- 설비명 -->
						<label><bean:message key="LABEL.equipName"/></label>
						<div class="input_box">
							<html:text property="lovEquipListDTO.equipDesc" tabindex="20"/>
						</div>
					</div>
					<!-- 위치 -->
					<div class="field">
						<label><bean:message key="LABEL.location"/></label>
						<div class="input_box">
							<html:text property="lovEquipListDTO.eqLocDesc" tabindex="30" />
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
							<html:text property="lovEquipListDTO.eqCtgDesc" tabindex="40" />
							<p class="open_spop">
								<a>
									<span>조회</span>
								</a>
							</p>
						</div>
					</div>
					<!-- 법정설비여부 -->
					<div class="field">
						<label><bean:message key="LABEL.isLawEq"/></label>
						<div class="input_box">
							<html:text property="lovEquipListDTO.isLawEq" tabindex="50" />
							<p class="open_spop">
								<a>
									<span>조회</span>
								</a>
							</p>
						</div>
					</div>
					<!-- 관리자(정) -->
					<div class="field">
						<label><bean:message key="LABEL.mainManager"/></label>
						<div class="input_box">
							<html:text property="lovEquipListDTO.mainMngName" tabindex="70" />
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<!-- 관리자(부) -->
					<div class="field">
						<label><bean:message key="LABEL.subManager"/></label>
						<div class="input_box">
							<html:text property="lovEquipListDTO.subMngName" tabindex="80" />
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<!-- 부서 -->
					<div class="field">
						<label><bean:message key="LABEL.dept"/></label>
						<div class="input_box">
							<html:text property="lovEquipListDTO.deptDesc" tabindex="70" />
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<!-- 공장명  -->
	                <div class="field">
	                    <label><bean:message key="LABEL.plantDesc"/></label>
	                    <div class="input_box">
							<html:text property="lovEquipListDTO.plantDesc"
								tabindex="80" />
							<p class="open_spop">
	                            <a>
	                                <span>조회</span>
	                            </a>
	                        </p>
	                    </div>
	                </div>
					<!-- 상태 -->
					<div class="field">
						<label><bean:message key="LABEL.equipStatus"/></label>
						<div class="input_box">
							<html:text property="lovEquipListDTO.eqStatusDesc" tabindex="35" />
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<!-- 설비유형 -->
					<div class="field hidden">
						<label><bean:message key="LABEL.eqCtgType"/></label>
						<div class="input_box">
							<html:text property="lovEquipListDTO.eqCtgTypeDesc" tabindex="90" />
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<!-- Old Eq# -->
					<div class="field">
						<label><bean:message key="LABEL.OldEqNo"/></label>
						<div class="input_box">
							<html:text property="lovEquipListDTO.oldEqNo" tabindex="100"/>
						</div>
					</div>
					<!-- 최신version여부  -->
	                <div class="field">
	                    <label><bean:message key="LABEL.isLastVersion"/></label>
	                    <div class="input_box">
	                        <html:text property="lovEquipListDTO.filterIsLastVersionDesc" tabindex="140"/>
	                        <p class="open_spop">
	                            <a>
	                                <span>조회</span>
	                            </a>
	                        </p>
	                    </div>
	                </div>
					<!-- TAG 번호 -->
					<div class="field">
						<label>TAG 번호</label>
						<div class="input_box">
							<html:text property="lovEquipListDTO.tagNo" tabindex="100"/>
						</div>
					</div>
					<c:set var="filePath" value="enhance/${compName}/asset/list/lovEquipAcList_${compNo}.jsp" />
					<c:if test="${udf:isExist(filePath)}">
						<c:import charEncoding="UTF-8" url="/enhance/${compName}/asset/list/lovEquipAcList_${compNo}.jsp"></c:import>
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