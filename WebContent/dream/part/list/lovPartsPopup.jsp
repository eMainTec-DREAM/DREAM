<%--===========================================================================
자재 Popup
author  kim21017
version $Id: lovPartsPopup.jsp,v 1.1 2016/02/18 09:12:01 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ page import="dream.part.list.action.LovPartsListAction"%>
<html>
<head>
<!-- 부품검색 -->
<title><bean:message key="LABEL.partSearch"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<!-- ########## page 상단 공통 : 모든 페이지 적용 ########## --> 

<script language="javascript">
<!--//

//그리드명
var myGrid;

/** 자동완성 변수 */
var partNameAc;
var deptAc;
var partGroupAc;
function loadPage() 
{
	initGrid();
}

/**
 * 그리드 초기화
 */
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowDblClicked",function(rowId, columnId){
		goConfirm();
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	setHeader(myGrid, "gridbox", "goSearch","lovPartsList"); // grid, grid id
	
	partNameAc = new autoC({"lovPartsListDTO.partDesc":"full_desc"});
	partNameAc.setAcConditionMap({
	   "part_categ":"SPPT",
	   "comp_no":loginUser.compNo
	   });
	partNameAc.setTable("TAPARTS");
	partNameAc.init();
	
	deptAc = new autoC({"lovPartsListDTO.deptDesc":"description"});
	deptAc.setAcDisplay("DESCRIPTION");
	deptAc.setAcConditionMap({
		"comp_no":loginUser.compNo
	   });
	deptAc.setTable("TADEPT");
	deptAc.setAcResultMap({
	    "lovPartsListDTO.deptId":"dept_id"
	});
	deptAc.init();
	
	partGroupAc = new autoC({"lovPartsListDTO.filterPartGroupDesc":"description"});
	partGroupAc.setAcDisplay("DESCRIPTION");
	partGroupAc.setAcConditionMap({
	    	"dir_type":"PART_GROUP",
	    	"is_use":"Y",
	    	"comp_no":loginUser.compNo
		   });
	partGroupAc.setTable("TACDUSRD");
	partGroupAc.setAcResultMap({
	    "lovPartsListDTO.filterPartGroup":"cdusrd_no"
	});
	partGroupAc.init();
}

function goSelect(){
	goConfirm();
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList()
{
	var form = document.lovPartsListForm;	
	form.strutsAction.value = '<%=LovPartsListAction.LOV_PARTS_FIND%>';
	var url = contextPath + "/lovPartsList.do";
	myGrid.clearAll(); setLoading("gridbox");
    $.post(url,FormQueryString(form), function(_data){
    	myGrid.parse(_data,"js");
    });
}

/**
 * 확인
 */
function goConfirm()
{	
	var returnArray = new Array();
	var selectedId=myGrid.getSelectedRowId();
    if(selectedId==null) return;

	var partId		 = getValueById(myGrid, selectedId,0);
	var partNo		 = getValueById(myGrid, selectedId,1);
	var partDesc	 = getValueById(myGrid, selectedId,2).replace(/&gt;/gi, ">");
	var maker 		 = getValueById(myGrid, selectedId,3);
	var model		 = getValueById(myGrid, selectedId,4);
	var plfTypeId	 = getValueById(myGrid, selectedId,5);
	var plfTypeDesc	 = getValueById(myGrid, selectedId,6);
	
	var lastPrice	 = getValueById(myGrid, selectedId,7);
	
	returnArray[0] = partId; 
	returnArray[1] = partNo;
	returnArray[2] = partDesc;
	returnArray[3] = maker;
	returnArray[4] = model;
	returnArray[5] = plfTypeId;
	returnArray[6] = plfTypeDesc;
	//작업결과-투입자재 -부품 선택시 사용수량 없으면 1넣기.
	returnArray[7] = '1';
	returnArray[8] = lastPrice;
	

	var dirType = lovPartsListForm.elements['lovPartsListDTO.codeType'].value;
	dirType="LOV_PARTS";
// 	opener.setLovValue(returnArray, dirType);
    getIframeContent().setLovValue(returnArray, dirType);

// 	self.close();
    closeLayerPopup();
}

/**
 * 검색
 */
function goSearch()
{
	findGridList();
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
	<html:form action="/lovPartsList" >
		<html:hidden property="lovPartsListDTO.extCode1" />
		<html:hidden property="lovPartsListDTO.extCode2" />
		<html:hidden property="lovPartsListDTO.codeType" />
		<html:hidden property="lovPartsListDTO.deptId" />
		<html:hidden property="lovPartsListDTO.filterPartGroup" />
		<html:hidden property="strutsAction" />
		<div class="section_wrap">
			<div class="sheader_box">
				<div class="sheader_wrap"><a></a></div>
				<div class="stitle_wrap">
					<div class="stitle_icon"><a></a></div>
					<div class="stitle_tx"><bean:message key="LABEL.Filter"/></div>
				</div>
				<div class="function_box filter not_fold">
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
						<!-- 부품명/규격 -->
						<label><bean:message key="LABEL.partNameSize"/></label>
						<div class="input_box">
							<input type='text' name="lovPartsListDTO.partDesc" tabindex="10"/>
						</div>
					</div>
					<div class="field">
						<!-- 부품코드 -->
						<label><bean:message key="LABEL.partCode"/></label>
						<div class="input_box">
							<input type='text' name="lovPartsListDTO.partNo" tabindex="20"/>
						</div>
					</div>
					<!-- 부서 -->
					<div class="field">
						<label><bean:message key="LABEL.dept"/></label>
						<div class="input_box">
							<html:text property="lovPartsListDTO.deptDesc" tabindex="30"
										onkeydown="validationKeyDown('lovPartsListDTO.deptDesc', 'lovPartsListDTO.deptId');"/>
							<p class="open_spop"><a href="javascript:lovDept('lovPartsListDTO.deptId', '', 'lovPartsListDTO.deptDesc');"><span>조회</span></a></p>
						</div>
					</div>
					<div class="field">
                       <label><bean:message key="LABEL.model"/></label>
                       <div class="input_box">
                            <html:text property="lovPartsListDTO.filterModel" tabindex="40"/>
                       </div>
                   </div>
                   <div class="field">
                       <label><bean:message key="LABEL.maker"/></label>
                       <div class="input_box">
                            <html:text property="lovPartsListDTO.filterMaker" tabindex="50"/>
                       </div>
                   </div>
                    <div class="field">
                        <label><bean:message key="LABEL.partGroup"/></label>
                        <div class="input_box">
                           <html:text property="lovPartsListDTO.filterPartGroupDesc" tabindex="60" />
                            <p class="open_spop">
                                <a>
                                    <span>조회</span>
                                </a>
                            </p>
                      </div>
                   </div>
                   <div class="field">
                       <label><bean:message key="LABEL.vendorPtCode"/></label>
                       <div class="input_box">
                            <html:text property="lovPartsListDTO.filterVendorPtCode" tabindex="70"/>
                       </div>
                   </div>
                   <div class="field">
						<label><bean:message key="LABEL.remark"/></label>
						<div class="input_box">
							<input type='text' name="lovPartsListDTO.filterRemark" tabindex="80"/>
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