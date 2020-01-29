<%--===========================================================================
Program Guide List
author  kim21017
version $Id: consultPgmGuideList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.program.guide.action.ConsultPgmGuideListAction" %>
<%@ page import="dream.consult.program.guide.action.ConsultPgmGuideDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 프로그램 가이드 -->
<title><bean:message key='MENU.PGMGUIDE'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="consultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

/** 자동완성 변수 */
var isUseAc;
var eqStatusAc;
var deptAc;
var eqLocAc;
var eqCtgAc;
var equipAc;
var empAc;
var plantAc;
var partAc;

function loadPage() 
{
    initGrid();
    // 사용여부 - 기본 선택.
    consultPgmGuideListForm.elements['consultPgmGuideCommonDTO.filterIsUseId'].value = "Y";
    consultPgmGuideListForm.elements['consultPgmGuideCommonDTO.filterIsUseDesc'].value = "Y";
    //사용여부 자동완성
    acSysDesc("consultPgmGuideCommonDTO.filterIsUseDesc","consultPgmGuideCommonDTO.filterIsUseId","IS_USE");
    //설비상태 자동완성
    acSysDesc("consultPgmGuideCommonDTO.filterEqStatusDesc","consultPgmGuideCommonDTO.filterEqStatusId","EQ_STATUS");
    //부서 자동완성
    deptAc = new autoC({"consultPgmGuideCommonDTO.filterDeptDesc":"description"});
    deptAc.setTable("TADEPT");
    //filterCompNo 에 100으로 하드코딩되어있으니 유의.참고용
    deptAc.setAcDConditionMap({
    	"comp_no":"consultPgmGuideCommonDTO.filterCompNo"
  	   });
    deptAc.setAcResultMap({
        "consultPgmGuideCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.init();
    
    //설비위치 자동완성
    eqLocAc = new autoC({"consultPgmGuideCommonDTO.filterEqLocDesc":"full_desc"});
    eqLocAc.setTable("TAEQLOC");
    //filterCompNo 에 100으로 하드코딩되어있으니 유의.참고용
    eqLocAc.setAcDConditionMap({
    	"comp_no":"consultPgmGuideCommonDTO.filterCompNo"
  	   });
    eqLocAc.setAcResultMap({
        "consultPgmGuideCommonDTO.filterEqLocId":"eqloc_id"
    });
    eqLocAc.init();
    //설비종류 자동완성
    eqCtgAc = new autoC({"consultPgmGuideCommonDTO.filterEqCtgDesc":"full_desc"});
    eqCtgAc.setTable("TAEQCTG");
    eqCtgAc.setCustomLov("lovEqCtg('consultPgmGuideCommonDTO.filterEqCtgId', 'consultPgmGuideCommonDTO.filterEqCtgDesc')");
    eqCtgAc.setAcResultMap({
        "consultPgmGuideCommonDTO.filterEqCtgId":"eqctg_id"
    });
    eqCtgAc.init();
    //설비 자동완성
    equipAc = new autoC({"consultPgmGuideCommonDTO.filterEquipDesc":"description"});
    equipAc.setTable("TAEQUIPMENT");
    equipAc.setCustomLov("lovEquip('consultPgmGuideCommonDTO.filterEquipId', 'consultPgmGuideCommonDTO.filterEquipDesc')");
    equipAc.setAcResultMap({
        "consultPgmGuideCommonDTO.filterEquipId":"equip_id"
    });
    equipAc.init();
    //사원 자동완성
    empAc = new autoC({"consultPgmGuideCommonDTO.filterEmpDesc":"emp_name"});
    empAc.setTable("TAEMP");
    empAc.setCustomLov("lovEmp('consultPgmGuideCommonDTO.filterEmpId','', 'consultPgmGuideCommonDTO.filterEmpDesc')");
    empAc.setAcResultMap({
        "consultPgmGuideCommonDTO.filterEmpId":"emp_id"
    });
    empAc.init();
    
    //공장 자동완성
    plantAc = new autoC({"consultPgmGuideCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    //filterCompNo 에 100으로 하드코딩되어있으니 유의.참고용
    plantAc.setAcDConditionMap({
    	"comp_no":"consultPgmGuideCommonDTO.filterCompNo"
  	   });
    plantAc.setAcResultMap({
        "consultPgmGuideCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
    //사원 자동완성
    partAc = new autoC({"consultPgmGuideCommonDTO.filterPartDesc":"full_desc"});
    partAc.setTable("TAPARTS");
    partAc.setCustomLov("lovParts('consultPgmGuideCommonDTO.filterPartId','', 'consultPgmGuideCommonDTO.filterPartDesc')");
    partAc.setAcResultMap({
        "consultPgmGuideCommonDTO.filterPartId":"part_id"
    });
    partAc.init();
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
    	consultPgmGuideListForm.elements['consultPgmGuideCommonDTO.pgGuideId'].value = "";
    	return sortColumn("consultPgmGuideList", this, consultPgmGuideListForm, "PGGUIDEID", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox", "modifyHead"); // grid, grid id
}

function setAcLovValue(rtnValueArr, acInputName)
{
	for(var i in rtnValueArr)
	{
	     console.log("setAcLovValue:"+JSON.stringify(rtnValueArr[i]));
	}
}

function afterAutoCmpt(acInputName, rtnJArray)
{
	for(var i in rtnJArray)
	{
	     console.log("afterAutoCmpt:"+JSON.stringify(rtnJArray[i]));
	}
}

function modifyHead(gridId, headData)
{
	var jsonArray = JSON.parse(headData);
	var data = {"id":"DEPTID2","sort":"str","hidden":"false","align":"left","width":"50","value":"부서코드11111111","type":"ro"};

	var headArray = jsonArray.head;
	headArray.push(data);
	
	jsonArray.head = headArray;
	
	for(var t in jsonArray.head)
	{
		//console.log(t);
	}

	myGrid.parse(jsonArray,"js");
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/consultPgmGuideList.do";
    consultPgmGuideListForm.elements['strutsAction'].value = '<%=ConsultPgmGuideListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(consultPgmGuideListForm), "PGGUIDEID","Y");

}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_pgGuideId)
{
	consultPgmGuideListForm.elements['consultPgmGuideCommonDTO.pgGuideId'].value = _pgGuideId;
	findGridList('ReloadRow');
	consultPgmGuideListForm.elements['consultPgmGuideCommonDTO.pgGuideId'].value = "";
}

function goSearch()
{
	consultPgmGuideListForm.elements['consultPgmGuideCommonDTO.pgGuideId'].value = "";	// 검색시 Tab 이동Key Clear
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
	consultPgmGuideListForm.elements['consultPgmGuideCommonDTO.pgGuideId'].value =  getValueById(myGrid, selectedId,'PGGUIDEID');  
	goCommonTabPage(consultPgmGuideListForm, <%= ConsultPgmGuideDetailAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('consultPgmGuideDetail');
}

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "consultPgmGuideDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	consultPgmGuideListForm.elements['consultPgmGuideCommonDTO.pgGuideId'].value = "";
    goCommonTabPage(consultPgmGuideListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'PGGUIDEID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    consultPgmGuideListForm.strutsAction.value = '<%=ConsultPgmGuideListAction.LIST_DELETE%>';
    var url = contextPath + "/consultPgmGuideList.do";
    
    $.post(url,FormQueryString(consultPgmGuideListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('consultPgmGuideDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	consultPgmGuideListForm.elements['consultPgmGuideCommonDTO.pgGuideId'].value = "";
	excelServerAction("consultPgmGuideList", consultPgmGuideListForm );  
}

//-->
</script>


<!-- ########## tab 관련 ########## -->
<LINK href="common/css/ma/bootstrap.css" rel="stylesheet">
<!--  
<SCRIPT src="common/js/jquery.js"></SCRIPT>
-->
<SCRIPT src="common/js/bootstrap-dropdown.js"></SCRIPT>
<SCRIPT src="common/js/bootstrap-tab.js"></SCRIPT>
<SCRIPT src="common/js/bootstrap-tabdrop.js"></SCRIPT>
<SCRIPT>
	if (top.location != location) {
    top.location.href = document.location.href ;
  }
		$(function(){
			window.prettyPrint && prettyPrint();
      $('.nav-tabs:first').tabdrop();
      $('.nav-tabs:last').tabdrop({text: 'More options'});
      $('.nav-pills').tabdrop({text: 'With pills'});
		});
</SCRIPT>
<!-- ########## tab 관련 end ########## -->


</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/consultPgmGuideList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="consultPgmGuideCommonDTO.pgGuideId"/><!-- Key -->
<html:hidden property="consultPgmGuideCommonDTO.filterEqLocId"/>
<html:hidden property="consultPgmGuideCommonDTO.filterEqCtgId"/>
<html:hidden property="consultPgmGuideCommonDTO.filterEquipId"/>
<html:hidden property="consultPgmGuideCommonDTO.filterDeptId"/>
<html:hidden property="consultPgmGuideCommonDTO.filterEmpId"/>
<html:hidden property="consultPgmGuideCommonDTO.filterEqStatusId"/>
<html:hidden property="consultPgmGuideCommonDTO.filterPlantId"/>
<html:hidden property="consultPgmGuideCommonDTO.filterPartId"/>
<html:hidden property="consultPgmGuideCommonDTO.filterIsUseId"/>
<html:hidden property="consultPgmGuideCommonDTO.filterCompNo" value="100"/>


<DIV class="tabbable ">
  <UL class="nav nav-tabs">
    <LI class="active"><A href="#tab1" data-toggle="tab">1설비마스터</A></LI>
    <LI><A href="#tab2" data-toggle="tab">2부품마스터</A></LI>
    <LI><A href="#tab3" data-toggle="tab">3작업자</A></LI>
    <LI><A href="#tab4" data-toggle="tab">4작업카렌다</A></LI>
    <LI><A href="#tab5" data-toggle="tab">5설비마스터</A></LI>
    <LI><A href="#tab6" data-toggle="tab">6부품마스터</A></LI>
    <LI><A href="#tab7" data-toggle="tab">7작업자 작업현황</A></LI>
    <LI><A href="#tab8" data-toggle="tab">8작업카렌다</A></LI>
    <LI><A href="#tab9" data-toggle="tab">9설비마스터</A></LI>
    <LI><A href="#tab10" data-toggle="tab">10설비마스터</A></LI>
  </UL>
</DIV>
<!-- 
<DIV class="tab-content">
  <DIV class="tab-pane active" id="tab1">
    <P>I'm in Section 1.설비마스터</P>
  </DIV>
  <DIV class="tab-pane" id="tab2">
    <P>Howdy, I'm in Section 2.부품마스터</P>
  </DIV>
  <DIV class="tab-pane" id="tab3">
    <P>Howdy, I'm in Section 3.작업자 작업현황 </P>
  </DIV>
  <DIV class="tab-pane" id="tab4">
    <P>Howdy, I'm in Section 4.작업카렌</P>
  </DIV>
  <DIV class="tab-pane" id="tab5">
    <P>Howdy, I'm in Section 5.</P>
  </DIV>
  <DIV class="tab-pane" id="tab6">
    <P>Howdy, I'm in Section 6.</P>
  </DIV>
  <DIV class="tab-pane" id="tab7">
    <P>Howdy, I'm in Section 7.</P>
  </DIV>
  <DIV class="tab-pane" id="tab8">
    <P>Howdy, I'm in Section 8.</P>
  </DIV>
  <DIV class="tab-pane" id="tab9">
    <P>Howdy, I'm in Section 9.</P>
  </DIV>
  <DIV class="tab-pane" id="tab10">
    <P>Howdy, I'm in Section 10.</P>
  </DIV>
</DIV>
-->



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
				<!-- 프로그램 가이드명 -->
				<div class="field">
					<label><bean:message key="LABEL.pgmGuideName"/></label>
					<div class="input_box">
						<html:text property="consultPgmGuideCommonDTO.filterPgGuideDesc" tabindex="10"/>
					</div>
				</div>
				<!-- 위치 -->
				<div class="field">
					<label><bean:message key="LABEL.location"/></label>
					<div class="input_box">
						<html:text property="consultPgmGuideCommonDTO.filterEqLocDesc" tabindex="20"/>
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
						<html:text property="consultPgmGuideCommonDTO.filterEqCtgDesc" tabindex="30"/>
						<p class="tx_clear">
							<a>
							 <span>삭제</span>
							</a>
						</p>
						<p class="open_spop">
							<a>
							 <span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 설비 -->
				<div class="field">
					<label><bean:message key="LABEL.equipDesc"/></label>
					<div class="input_box">
						<html:text property="consultPgmGuideCommonDTO.filterEquipDesc" tabindex="40"/>
						<p class="open_spop">
							<a>
							 <span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 부서 -->
				<div class="field">
					<label><bean:message key="LABEL.dept"/></label>
					<div class="input_box">
						<html:text property="consultPgmGuideCommonDTO.filterDeptDesc" tabindex="50"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 사원 -->
				<div class="field">
					<label><bean:message key="LABEL.emp"/></label>
					<div class="input_box">
						<html:text property="consultPgmGuideCommonDTO.filterEmpDesc" tabindex="60"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 설비상태 -->
				<div class="field">
					<label><bean:message key="LABEL.equipStatus"/></label>
					<div class="input_box">
						<html:text property="consultPgmGuideCommonDTO.filterEqStatusDesc" tabindex="70" />
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 공장 -->
				<div class="field">
					<label><bean:message key="LABEL.plant"/></label>
					<div class="input_box">
						<html:text property="consultPgmGuideCommonDTO.filterPlantDesc" tabindex="80" />
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 자재 -->
				<div class="field">
					<label><bean:message key="LABEL.parts"/></label>
					<div class="input_box">
						<html:text property="consultPgmGuideCommonDTO.filterPartDesc" tabindex="90" />
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 비고 -->
				<div class="field">
					<label><bean:message key="LABEL.remark"/></label>
					<div class="input_box">
						<html:text property="consultPgmGuideCommonDTO.filterRemark" tabindex="100"/>
					</div>
				</div>
				<!-- 사용여부 -->
				<div class="field">
					<label><bean:message key="LABEL.isUse"/></label>
					<div class="input_box">
						<html:text property="consultPgmGuideCommonDTO.filterIsUseDesc" tabindex="110" />
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
		    <div class="grid_caption">처리할 요청 건 : <a href="#">30</a></div>
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
	</div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>