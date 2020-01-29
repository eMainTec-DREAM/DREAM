<%--===========================================================================
평가결과복사 LOV Popup
author  js.lee
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ page import="dream.ass.asset.action.AssAssetScoreCopyLovAction"%>
<%@ page import="common.bean.User"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html>
<head>
<!-- 평가결과복사 -->
<title><bean:message key="LABEL.scoreCopy"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<!-- ########## page 상단 공통 : 모든 페이지 적용 ########## --> 

<script language="javascript">
<!--//

//그리드명
var mainGrid;
var subGrid;

/** 자동완성 변수 */
var equipDescAc;
var plantAc;

function loadPage() 
{
  	//공장명
    if(loginUser.filterPlant!='null' && loginUser.filterPlant!='' && loginUser.filterPlant!= null){
    	assAssetScoreCopyLovForm.elements['assAssetScoreCopyLovDTO.filterPlantId'].value  = loginUser.filterPlant;
    	assAssetScoreCopyLovForm.elements['assAssetScoreCopyLovDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
	}
    $('#sub_grid_area').hide();
	
	initGrid('MAIN');
	initGrid('SUB');
	
    //설비
    equipDescAc = new autoC({"assAssetScoreCopyLovDTO.filterEquipDesc":"description"});
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcDConditionMap({
    	"eqloc_id" : "assAssetScoreCopyLovDTO.filterEqLocId",
    	"eqctg_id" : "assAssetScoreCopyLovDTO.filterEqCtgId",
    	"dept_id" : "assAssetScoreCopyLovDTO.filterDeptId",
    	"plant" : "assAssetScoreCopyLovDTO.filterPlantId"
    });
    equipDescAc.init();
	
    //공장명
    plantAc = new autoC({"assAssetScoreCopyLovDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "assAssetScoreCopyLovDTO.filterPlantId":"plant"
    });
    plantAc.init();
    
    // 평가구분 자동완성
    acSysDesc("assAssetScoreCopyLovDTO.filterAssTypeDesc","assAssetScoreCopyLovDTO.filterAssTypeId","EQASSLIST_TYPE");
	
}


/**
 * 그리드 초기화
 */
function initGrid(gridType)
{
	if (typeof gridType == "undefined") return;
	
	switch(gridType)
	{
		case "MAIN" :
			mainGrid = new dhtmlXGridObject("main_gridbox");
			mainGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
			
			mainGrid.enableSmartRendering(true,500);
		    mainGrid.attachEvent("onRowSelect",function(rowId, columnId){
		    	goScoreOpen();
		    });
			mainGrid.attachEvent("onRowDblClicked",function(rowId, columnId){
				goConfirm();
			});
			mainGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"main_gridbox")});
			mainGrid.init();
			setHeader(mainGrid, "main_gridbox", "goSearch", "assAssetScoreCopyLov"); // grid, grid id
			break;
		case "SUB" :
			subGrid = new dhtmlXGridObject("sub_gridbox");
			subGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
			subGrid.enableSmartRendering(true,500);
			subGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"sub_gridbox")});
			subGrid.init();
			setHeader(subGrid, "sub_gridbox"); // grid, grid id 
			break;
	}
}


/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList()
{
	var form = document.assAssetScoreCopyLovForm;	
	form.strutsAction.value = '<%=AssAssetScoreCopyLovAction.LOV_ASS_FIND%>';
	var url = contextPath + "/assAssetScoreCopyLov.do";
	mainGrid.clearAll(); setLoading("main_gridbox");
    $.post(url,FormQueryString(form), function(_data){
    	mainGrid.parse(_data,"js");
    });
    $('#sub_grid_area').hide();
}

function goSelect(){
	goConfirm();
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

function goSelect(){
	goConfirm();
}

/**
 * 확인
 */
function goConfirm()
{	
	setAcValue(mainGrid, "eqasslistId");  // (gridObj, Id Column If exist)
}


/**
 * 평가점수 열기
 */
function goScoreOpen()
{
	$('#sub_grid_area').show();
	var form = document.assAssetScoreCopyLovForm;	
	form.strutsAction.value = '<%=AssAssetScoreCopyLovAction.LOV_ASS_SCORE_FIND%>';
	assAssetScoreCopyLovForm.elements['assAssetScoreCopyLovDTO.eqasslistId'].value =  getValueById(mainGrid, mainGrid.getSelectedRowId(),'EQASSLISTID');  
	var url = contextPath + "/assAssetScoreCopyLov.do";
	subGrid.clearAll(); setLoading("sub_gridbox");
    $.post(url,FormQueryString(form), function(_data){
    	subGrid.parse(_data,"js");
    });
}


//-->
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/assAssetScoreCopyLov.do">

<html:hidden property="currentPageId"/>
<html:hidden property="keyCode" /><html:hidden property="chName" />
<html:hidden property="resultCol" /><html:hidden property="multiSelect"/>
<html:hidden property="codeType" />
<html:hidden property="param" />
<html:hidden property="label" />
<html:hidden property="title" />

<html:hidden property="strutsAction"/>
<html:hidden property="assAssetScoreCopyLovDTO.filterEquipId"/>
<html:hidden property="assAssetScoreCopyLovDTO.filterPlantId"/>
<html:hidden property="assAssetScoreCopyLovDTO.eqasslistId"/>
<html:hidden property="assAssetScoreCopyLovDTO.filterAssTypeId"/>


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
				<!-- 공장명  -->
                <div class="field">
                    <label><bean:message key="LABEL.plantDesc"/></label>
                    <div class="input_box">
							<html:text property="assAssetScoreCopyLovDTO.filterPlantDesc" tabindex="180" />
							<p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 평가구분 -->
                <div class="field">
                    <label><bean:message key="LABEL.assType"/></label>
                    <div class="input_box">
                        <html:text property="assAssetScoreCopyLovDTO.filterAssTypeDesc" tabindex="100"/>
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 설비번호 -->
                <div class="field">
                    <label><bean:message key="LABEL.equipNo"/></label>
                    <div class="input_box">
                        <html:text property="assAssetScoreCopyLovDTO.filterEquipNo" tabindex="5"/>
                    </div>
                </div>
				<!-- 설비명 -->
				<div class="field">
					<label><bean:message key="LABEL.equipDesc"/></label>
					<div class="input_box">
						<html:text property="assAssetScoreCopyLovDTO.filterEquipDesc" tabindex="90"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
                <!-- 평가일자 -->
                <div class="field">
                    <label><bean:message key="LABEL.assDate"/></label>
                    <div class="calendar_wrap">
                        <div class="input_box input_carendar">
                            <html:text property="assAssetScoreCopyLovDTO.filterAssStartDate" tabindex="10" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
                        <div class="input_box input_carendar">
                            <html:text property="assAssetScoreCopyLovDTO.filterAssEndDate" tabindex="20" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
                    </div>
                </div>

			</div>
		</div><!--article_box end-->
	</div> <!--  end section_wrap -->
	<div class="section_wrap" name ="list"  id="main_grid_area" >
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
					<a href="javascript:goSelect();" class="b_select"><span><bean:message key="BUTTON.SELECT"/></span></a>
					<a href="javascript:goSetting('assAssetScoreCopyLov','main_gridbox');" class="b_setting"><span><bean:message key="BUTTON.SETTING"/></span></a>
				</div>
			</div>
		</div><!--sheader_box end-->
		<div class="article_box" >
			<div class="grid_area">
				<div id="main_gridbox" style="width:100%; height:270px; background-color:white;"></div>		
			</div>			
		</div>
	</div> <!--  End of section_wrap -->
	<div class="section_wrap" name ="list"  id="sub_grid_area" >
		<div class="sheader_box">
			<div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx"><bean:message key="LABEL.assScore"/></div>
			</div>
			<div class="function_box list">
				<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
				</div>
				<div class="fb_group2">
					<a href="javascript:goSetting('assAssetScoreCopyLov','sub_gridbox');" class="b_setting"><span><bean:message key="BUTTON.SETTING"/></span></a>
				</div>
			</div>
		</div><!--sheader_box end-->
		<div class="article_box" >
			<div class="grid_area">
				<div id="sub_gridbox" style="width:100%; height:270px; background-color:white;"></div>		
			</div>			
		</div>
	</div> <!--  End of section_wrap -->
	
</html:form> 
</body>
</html>