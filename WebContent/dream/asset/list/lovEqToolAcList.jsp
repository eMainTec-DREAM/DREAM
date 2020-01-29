<%--===========================================================================
 Popup
author  kim21017
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
<%@ page import="dream.asset.list.action.LovEqToolAcListAction"%>
<html>
<head>
<!-- 기준측정기 -->
<title><bean:message key="LABEL.eqTool"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<!-- ########## page 상단 공통 : 모든 페이지 적용 ########## --> 

<script language="javascript">
<!--//

//그리드명
var myGrid;
var plantAc, eqLocDescAc;

function loadPage() 
{
	//공장명
    if(loginUser.plant!='null'){
    	lovEqToolAcListForm.elements['lovEqToolAcListDTO.plant'].value  = loginUser.plant;
    	lovEqToolAcListForm.elements['lovEqToolAcListDTO.plantDesc'].value  = loginUser.plantDesc;
	}
	
	initGrid();
	
	//----------------------------------------------------------------//
    eqLocDescAc = new autoC({"lovEqToolAcListDTO.eqlocDesc":"full_desc"});
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setAcConditionMap({
  	   "comp_no":loginUser.compNo
  	   });
    eqLocDescAc.setAcDConditionMap({
    	"plant" : "lovEqToolAcListDTO.plant"
    });
    eqLocDescAc.setAcResultMap({
        "lovEqToolAcListDTO.eqlocId":"eqloc_id"
    });
    eqLocDescAc.init();
    
    //-----------------------------------------------------------------//
    
    //공장명
    plantAc = new autoC({"lovEqToolAcListDTO.plantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "lovEqToolAcListDTO.plant":"plant"
    });
    plantAc.init();
    
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
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		lovEqToolAcListForm.elements['lovEqToolAcListDTO.equipId'].value = "";
    	return sortColumn("lovEqToolAcList", this, lovEqToolAcListForm, "EQUIPID", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	setHeader(myGrid, "gridbox", "goSearch", "lovEqToolAcList"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
	lovEqToolAcListForm.elements['strutsAction'].value = '<%=LovEqToolAcListAction.LIST_FIND%>';
	var url = contextPath + "/lovEqToolAcList.do";

    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(lovEqToolAcListForm), "EQUIPID","Y");
}

function goSelect(){
	goConfirm();
}

/**
 * 확인
 */
function goConfirm()
{	
	setAcValue(myGrid, "EQUIPID");
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
	<html:form action="/lovEqToolAcList" >
		<html:hidden property="currentPageId"/>
		<html:hidden property="keyCode" /><html:hidden property="chName" />
		<html:hidden property="resultCol" /><html:hidden property="multiSelect"/>
		<html:hidden property="codeType" />
		<html:hidden property="param" />
		<html:hidden property="label" />
		<html:hidden property="title" />
		<html:hidden property="strutsAction" />
		
		<html:hidden property="lovEqToolAcListDTO.eqlocId" />
		<html:hidden property="lovEqToolAcListDTO.plant" />

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
					<!-- 설비번호 -->
					<div class="field">
						<label><bean:message key="LABEL.equipNo"/></label>
						<div class="input_box">
							<html:text property="lovEqToolAcListDTO.itemNo" tabindex="100" />
						</div>
					</div>
					<!-- 계측기명 -->
					<div class="field">
						<label><bean:message key="LABEL.toolName"/></label>
						<div class="input_box">
							<html:text property="lovEqToolAcListDTO.toolDesc" tabindex="110" />
						</div>
					</div>
					<!-- 설비명 -->
					<div class="field">
						<label><bean:message key="LABEL.equipName"/></label>
						<div class="input_box">
							<html:text property="lovEqToolAcListDTO.eqDesc" tabindex="120" />
						</div>
					</div>
					<!-- 설비위치  -->
					<div class="field">
						<label><bean:message key="LABEL.eqLocDesc"/></label>
						<div class="input_box">
							<html:text property="lovEqToolAcListDTO.eqlocDesc" tabindex="130" />
							<p class="open_spop">
								<a>
									<span>조회</span>
								</a>
							</p>
						</div>
					</div>
					<!-- 계측기번호  -->
					<div class="field">
						<label><bean:message key="LABEL.toolNo"/></label>
						<div class="input_box">
							<html:text property="lovEqToolAcListDTO.toolNo" tabindex="140" />
						</div>
					</div>
					<!-- 공장명  -->
					<div class="field">
						<label><bean:message key="LABEL.plantDesc"/></label>
						<div class="input_box">
							<html:text property="lovEqToolAcListDTO.plantDesc" tabindex="150" />
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