<%--===========================================================================
상세코드 Popup
author  youngjoo38
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
<%@ page import="dream.work.list.action.LovPmEquipAcListAction"%>
<html>
<head>
<!-- 생산제품 검색 -->
<title><bean:message key="LABEL.prodGoods"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<!-- ########## page 상단 공통 : 모든 페이지 적용 ########## --> 

<script language="javascript">
<!--//

//그리드명
var myGrid;

var equipAc, empAc, deptAc;

function loadPage() 
{
	initGrid();
	
	// 설비 
    equipAc = new autoC({"lovPmEquipAcListDTO.equipDesc":"description"});
    equipAc.setTable("TAEQUIPMENT");
    equipAc.setAcResultMap({
        "lovPmEquipAcListDTO.equipId":"EQUIP_ID"
    });
    equipAc.init();
	
    // 담당자
    empAc = new autoC({"lovPmEquipAcListDTO.empDesc":"emp_name"});
    empAc.setTable("TAEMP");
    empAc.setAcResultMap({
        "lovPmEquipAcListDTO.empId":"emp_id"
    });
    empAc.init();
    
    // 부서 
    deptAc = new autoC({"lovPmEquipAcListDTO.deptDesc":"description"});
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "lovPmEquipAcListDTO.deptId":"dept_id"
    });
    deptAc.init();
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
	setHeader(myGrid, "gridbox", "goSearch", "lovPmEquipAcList"); // grid, grid id
}


/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
	var form = document.lovPmEquipAcListForm;	
	form.strutsAction.value = '<%=LovPmEquipAcListAction.LOV_AC_FIND%>';
	var url = contextPath + "/lovPmEquipAcList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(lovPmEquipAcListForm), "PRODUCTID");
}

function goSelect(){
	goConfirm();
}

/**
 * 확인
 */
function goConfirm()
{	
	setAcValue(myGrid, "PRODUCTID");
}
/**
 * 검색
 */
function goSearch()
{
    findGridList('Search');
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
	<html:form action="/lovPmEquipAcList" >
	
<html:hidden property="currentPageId"/>
<html:hidden property="keyCode" /><html:hidden property="chName" />
<html:hidden property="resultCol" /><html:hidden property="multiSelect"/>
<html:hidden property="codeType" />
<html:hidden property="param" />
<html:hidden property="label" />
<html:hidden property="title" />

<html:hidden property="lovPmEquipAcListDTO.equipId"/> <!-- 설비ID -->
<html:hidden property="lovPmEquipAcListDTO.deptId"/>  <!-- 부서ID -->
<html:hidden property="lovPmEquipAcListDTO.empId"/>   <!-- 담당자ID -->

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
                    <!-- 생산제품명 -->
					<div class="field">
						<label><bean:message key="LABEL.prodGoodsName"/></label>
						    <div class="input_box">
							<html:text property="lovPmEquipAcListDTO.productDesc" />
						</div>
					</div>
					<!-- 생산제품코드 -->
					<div class="field">
						<label><bean:message key="LABEL.prodGoodsCode"/></label>
                        <div class="input_box">
    						<html:text property="lovPmEquipAcListDTO.productNo" />
   						</div>
					</div>
					<!-- 설비 -->
					<div class="field">
						<label><bean:message key="LABEL.equipDesc"/></label>
                        <div class="input_box">
    						<html:text property="lovPmEquipAcListDTO.equipDesc" />
    						<p class="open_spop"><a><span>조회</span></a></p>
   						</div>
					</div>
					<!-- 부서 -->
					<div class="field">
						<label><bean:message key="LABEL.dept"/></label>
                        <div class="input_box">
    						<html:text property="lovPmEquipAcListDTO.deptDesc" />
    						<p class="open_spop"><a><span>조회</span></a></p>
   						</div>
					</div>
					<!-- 담당자 -->
					<div class="field">
						<label><bean:message key="LABEL.manager"/></label>
                        <div class="input_box">
    						<html:text property="lovPmEquipAcListDTO.empDesc" />
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