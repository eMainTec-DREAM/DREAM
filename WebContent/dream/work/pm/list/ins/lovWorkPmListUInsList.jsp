<%--===========================================================================
에너지 측정기준주기 Lov
author  sy.yang
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ page import="common.bean.User"%>
<%@ page import="dream.work.pm.list.action.LovWorkPmListUInsListAction"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html>
<head>
<!-- 점검항목 lov -->
<title><bean:message key="TAB.maEqMstrPmInsPointList"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<!-- ########## page 상단 공통 : 모든 페이지 적용 ########## --> 

<script language="javascript">
<!--//

//그리드명
var myGrid;

var workDescAc;
var deptAc;
var plantAc;

function loadPage() 
{
	//부서세팅
	lovWorkPmListUInsListForm.elements['lovWorkPmListUInsListDTO.deptId'].value = loginUser.filterDeptId;
	lovWorkPmListUInsListForm.elements['lovWorkPmListUInsListDTO.deptDesc'].value = loginUser.filterDeptDesc;
	
	//공장명
    if(loginUser.filterPlant!='null'){
    	lovWorkPmListUInsListForm.elements['lovWorkPmListUInsListDTO.plantId'].value  = loginUser.filterPlant;
    	lovWorkPmListUInsListForm.elements['lovWorkPmListUInsListDTO.plantDesc'].value  = loginUser.filterPlantDesc;
	}
	
    lovWorkPmListUInsListForm.elements['lovWorkPmListUInsListDTO.isActive'].value = "Y";
	valSysDir('lovWorkPmListUInsListDTO.isActive', 'lovWorkPmListUInsListDTO.isActive', 'IS_USE', true);
	
	initGrid();
	
	// 작업명	    
    workDescAc = new autoC({"lovWorkPmListUInsListDTO.pmiDesc":"description"});
    workDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    workDescAc.setTable("TAPMLST");
    workDescAc.setAcResultMap({
        "lovWorkPmListUInsListDTO.pmiDesc":"description"
    });
    workDescAc.init();

    // 설비
    equipDescAc = new autoC({"lovWorkPmListUInsListDTO.equipDesc":"description"});
    equipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    equipDescAc.setAcResultMap({
        "lovWorkPmListUInsListDTO.equipId":"equip_id"
    });
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcDConditionMap({
    	"dept_id" : "lovWorkPmListUInsListDTO.deptId",
    	"plant" : "lovWorkPmListUInsListDTO.plantId"
    });
    equipDescAc.init();
    
    // 부서
	deptAc = new autoC({"lovWorkPmListUInsListDTO.deptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "lovWorkPmListUInsListDTO.deptId":"dept_id"
    });
    deptAc.setAcDConditionMap({
    	"plant" : "lovWorkPmListUInsListDTO.plantId"
    });
    deptAc.init();
    
 	// 작업그룹
    wkCtrDescAc = new autoC({"lovWorkPmListUInsListDTO.wkCtrDesc":"description"});
    wkCtrDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    wkCtrDescAc.setTable("TAWKCTR");
    wkCtrDescAc.setAcResultMap({
        "lovWorkPmListUInsListDTO.wkCtrId":"wkctr_id"
    });
    wkCtrDescAc.init();
    
    // 담당자
    empAc = new autoC({"lovWorkPmListUInsListDTO.empDesc":"emp_name"});
    empAc.setAcDisplay("EMP_NAME");
    empAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    empAc.setTable("TAEMP");
    empAc.setAcResultMap({
        "lovWorkPmListUInsListDTO.empId":"emp_id"
    });
    empAc.setAcDConditionMap({
    	"dept_id" : "lovWorkPmListUInsListDTO.deptId",
    	"plant" : "lovWorkPmListUInsListDTO.plantId"
    });
    empAc.init();
    
 	// 공장코드
	plantAc = new autoC({"lovWorkPmListUInsListDTO.plantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "lovWorkPmListUInsListDTO.plantId":"plant"
    });
    plantAc.init();
    
    // 시행여부
	acSysDesc("lovWorkPmListUInsListDTO.isActive","lovWorkPmListUInsListDTO.isActive","IS_USE",true);
	// 작업형태 AC
    acSysDesc("lovWorkPmListUInsListDTO.pmTypeDesc","lovWorkPmListUInsListDTO.pmTypeId", "PMU_TYPE");

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
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	setHeader(myGrid, "gridbox", "goSearch", "lovWorkPmListUInsList"); // grid, grid id
}


/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
	var form = document.lovWorkPmListUInsListForm;	
	form.strutsAction.value = '<%=LovWorkPmListUInsListAction.LOV_PM_UINS_LIST_AC_FIND%>';
	var url = contextPath + "/lovWorkPmListUInsList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(form), "PMID", "Y");
}

function goSelect()
{
	goConfirm();
}

/**
 * 확인
 */
function goConfirm()
{	

	setAcValue(myGrid, "PMID");
// 	setAcValue(myGrid, "PMDESC");
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
//-->
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
<!-- searchbox 박스 Line -->
<html:form action="/lovWorkPmListUInsList.do" >
<html:hidden property="currentPageId"/>
<html:hidden property="multiSelect"/>
<html:hidden property="keyCode" />
<html:hidden property="chName" />
<html:hidden property="resultCol" />
<html:hidden property="codeType" />
<html:hidden property="param" />
<html:hidden property="label" />
<html:hidden property="title" />
		<html:hidden property="lovWorkPmListUInsListDTO.pmTypeId"/>
		<html:hidden property="lovWorkPmListUInsListDTO.equipId"/>
		<html:hidden property="lovWorkPmListUInsListDTO.deptId"/>
		<html:hidden property="lovWorkPmListUInsListDTO.wkCtrId"/>
		<html:hidden property="lovWorkPmListUInsListDTO.empId"/>
		<html:hidden property="lovWorkPmListUInsListDTO.plantId"/>
		<html:hidden property="lovWorkPmListUInsListDTO.multiSelect"/>
		<html:hidden property="strutsAction" />
		<div class="section_wrap">
			<div class="sheader_box">
				<div class="sheader_wrap">
					<a></a>
				</div>
				<div class="stitle_wrap">
					<div class="stitle_icon">
						<a></a>
					</div>
					<div class="stitle_tx">
						<bean:message key="LABEL.Filter" />
					</div>
				</div>
				<div class="function_box filter">
					<div class="fb_group3">
						<div class="sfb_wrap" style="display: none;"></div>
					</div>
					<div class="fb_group2"></div>
					<div class="b_line"></div>
					<div class="fb_group1"></div>
				</div>
			</div>
			<!--sheader_box end-->
			<div class="article_box">			
				<div class="form_box">
					<!-- 기준# -->
					<div class="field">
						<label><bean:message key="LABEL.PERIODNO"/></label>
						<div class="input_box">
							<html:text property="lovWorkPmListUInsListDTO.pmNo" />
						</div>
					</div>    
					<!-- 작업명 -->    
					<div class="field">
						<label><bean:message key="LABEL.woName"/></label>
						<div class="input_box">
							<html:text property="lovWorkPmListUInsListDTO.pmiDesc" />
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<!-- 작업형태  -->
					<div class="field">
						<label><bean:message key="LABEL.pmType"/></label>
						<div class="input_box">
							<html:text property="lovWorkPmListUInsListDTO.pmTypeDesc" />
							<p class="open_spop">
								<a><span>조회</span></a>
							</p>
						</div>
					</div>
					<!-- 설비 -->
					<div class="field">
						<label><bean:message key="LABEL.equipDesc"/></label>
						<div class="input_box">
							<html:text property="lovWorkPmListUInsListDTO.equipDesc" />
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<!-- 부서 -->
					<div class="field">
						<label><bean:message key="LABEL.dept"/></label>
						<div class="input_box">
							<html:text property="lovWorkPmListUInsListDTO.deptDesc"/>
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<!-- 작업그룹  -->
					<div class="field">
	                     <label><bean:message key="LABEL.wkCtr"/></label>
	                     <div class="input_box">
	                     	<html:text property="lovWorkPmListUInsListDTO.wkCtrDesc" />
	                    	<p class="open_spop"><a><span>조회</span></a></p>
	                   </div>
	                </div>
					<!-- 담당자 -->
					<div class="field">
						<label><bean:message key="LABEL.manager"/></label>
						<div class="input_box">
							<html:text property="lovWorkPmListUInsListDTO.empDesc" />
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<!-- 공장 -->
	                <div class="field">
	                    <label><bean:message key="LABEL.plant"/></label>
	                    <div class="input_box">
							<html:text property="lovWorkPmListUInsListDTO.plantDesc" />
							<p class="open_spop"><a><span>조회</span></a></p>
	                    </div>
	                </div>
					<!-- 시행여부 -->
					<div class="field">
		                <label><bean:message key="LABEL.isActive"/></label>
		                <div class="input_box">
		                    <html:text property="lovWorkPmListUInsListDTO.isActive" />
		                    <p class="open_spop"><a><span>조회</span></a></p>
		                </div>
		            </div>
					
				</div>				
			</div>
			<!--article_box end-->
		</div>
		<!--  end section_wrap -->
		<div class="section_wrap">
			<div class="sheader_box">
				<div class="sheader_wrap">
					<a></a>
				</div>
				<div class="stitle_wrap">
					<div class="stitle_icon">
						<a></a>
					</div>
					<div class="stitle_tx">
						<bean:message key="LABEL.List" />
					</div>
				</div>
				<div class="function_box list">
					<div class="fb_group3">
						<div class="sfb_wrap" style="display: none;"></div>
					</div>
					<div class="fb_group2"></div>
					<div class="b_line"></div>
					<div class="fb_group1"></div>
				</div>
			</div>
			<!--sheader_box end-->
			<div class="article_box" id="listBox">
				<div class="grid_area">
					<div id="gridbox" style="width: 100%; height: 270px; background-color: white;"></div>
				</div>
			</div>
		</div>
		<!--  End of section_wrap -->
	</html:form>
</body>
</html>