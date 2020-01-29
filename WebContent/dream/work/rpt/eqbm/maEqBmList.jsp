<%--===========================================================================
설비고장내역
author  kim21017
version $Id: maEqBmList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.maeqbm.action.MaEqBmListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ page import="common.bean.User"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html>
<head>
<title><bean:message key="MENU.EQBMLIST"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var myGrid, selectedId;

var deptAc;
var equipDescAc;
var eqLocDescAc;
var eqCtgTypeAc;
var mainMngAc;
var subMngAc;
var caCdDescAc;
var reCdDescAc;
var plantAc;

function loadPage() 
{
	if(window.name=="EQ_BM_LIST"){
	}else{
		//일자 세팅
		maEqBmListForm.elements['maEqBmListDTO.filterStartDate'].value = getMinusDay(7);
		maEqBmListForm.elements['maEqBmListDTO.filterEndDate'].value   = getToday();

		//부서정보 세팅
		maEqBmListForm.elements['maEqBmListDTO.filterDeptId'].value    = loginUser.filterDeptId;
		maEqBmListForm.elements['maEqBmListDTO.filterDeptDesc'].value  = loginUser.filterDeptDesc;
		if(loginUser.eqLocId!='null'){
			maEqBmListForm.elements['maEqBmListDTO.filterEqLocId'].value = loginUser.eqLocId;
			maEqBmListForm.elements['maEqBmListDTO.filterEqLocDesc'].value = loginUser.eqLocDesc;
		}
	}
	
	//공장명
    if(loginUser.filterPlant!='null'){
    	maEqBmListForm.elements['maEqBmListDTO.filterPlantId'].value  = loginUser.filterPlant;
    	maEqBmListForm.elements['maEqBmListDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
	}
	
    initGrid();
    
    caCdDescAc = new autoC({"maEqBmListDTO.filterCaCdDesc":"failureDesc"});
    caCdDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"fail_type":"CA",
 		"is_use":"Y"
  	   });
    caCdDescAc.setTable("TAFAILURE");
    caCdDescAc.setKeyName("maEqBmListDTO.filterCaCd");
    caCdDescAc.setAcResultMap({
        "maEqBmListDTO.filterCaCd":"failure_id"
    });
    caCdDescAc.init();

    reCdDescAc = new autoC({"maEqBmListDTO.filterReCdDesc":"failureDesc"});
    reCdDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"fail_type":"RE",
 		"is_use":"Y"
  	   });
    reCdDescAc.setTable("TAFAILURE");
    reCdDescAc.setKeyName("maEqBmListDTO.filterReCd");
    reCdDescAc.setAcResultMap({
        "maEqBmListDTO.filterReCd":"failure_id"
    });
    reCdDescAc.init();
    
    deptAc = new autoC({"maEqBmListDTO.filterDeptDesc":"description"});
    deptAc.setAcDisplay("DESCRIPTION");
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "maEqBmListDTO.filterDeptId":"dept_id"
    });
    deptAc.setAcDConditionMap({
    	"plant" : "maEqBmListDTO.filterPlantId"
    });
    deptAc.init();
    
    equipDescAc = new autoC({"maEqBmListDTO.filterEquipDesc":"description"});
    equipDescAc.setAcDisplay("DESCRIPTION");
    equipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcResultMap({
        "maEqBmListDTO.filterEquipId":"equip_id"
    });
    equipDescAc.setAcDConditionMap({
    	"eqloc_id" : "maEqBmListDTO.filterEqLocId",
    	"eqctg_id" : "maEqBmListDTO.filterEqCtgId",
    	"dept_id" : "maEqBmListDTO.filterDeptId",
    	"plant" : "maEqBmListDTO.filterPlantId"
    });
    equipDescAc.init();
    
    eqLocDescAc = new autoC({"maEqBmListDTO.filterEqLocDesc":"full_desc"});
    eqLocDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setAcResultMap({
        "maEqBmListDTO.filterEqLocId":"eqloc_id"
    });
    eqLocDescAc.setAcDConditionMap({
    	"plant" : "maEqBmListDTO.filterPlantId"
    });
    eqLocDescAc.init();
    
    eqCtgTypeAc = new autoC({"maEqBmListDTO.filterEqCtgDesc":"full_desc"});
    eqCtgTypeAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqCtgTypeAc.setTable("TAEQCTG");
    eqCtgTypeAc.setAcResultMap({
        "maEqBmListDTO.filterEqCtgId":"eqctg_id"
    });
    eqCtgTypeAc.init();
    
    mainMngAc = new autoC({"maEqBmListDTO.filterMainMngName":"emp_name"});
    mainMngAc.setAcDisplay("EMP_NAME");
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcResultMap({
        "maEqBmListDTO.filterMainMngId":"emp_id"
    });
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "maEqBmListDTO.filterDeptId",
    	"plant" : "maEqBmListDTO.filterPlantId"
    });
    mainMngAc.init();
    
    subMngAc = new autoC({"maEqBmListDTO.filterSubMngName":"emp_name"});
    subMngAc.setAcDisplay("EMP_NAME");
    subMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    subMngAc.setTable("TAEMP");
    subMngAc.setAcResultMap({
        "maEqBmListDTO.filterSubMngId":"emp_id"
    });
    subMngAc.setAcDConditionMap({
    	"dept_id" : "maEqBmListDTO.filterDeptId",
    	"plant" : "maEqBmListDTO.filterPlantId"
    });
    subMngAc.init();
    
 	// 공장코드
	plantAc = new autoC({"maEqBmListDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "maEqBmListDTO.filterPlantId":"plant"
    });
    plantAc.init();

	//법정설비유형  AC
    acSysDesc("maEqBmListDTO.filterIsLawEq","maEqBmListDTO.filterIsLawEq","IS_USE",true);
    //고장작업 종류 AC
    acSysDesc("maEqBmListDTO.filterPmTypeDesc","maEqBmListDTO.filterPmTypeId","BM_TYPE");
	//고장시간  AC
    acSysDesc("maEqBmListDTO.filterFailTimeDesc","maEqBmListDTO.filterFailTime","TIME_INTERVAL",true);
    // 내/외자
    acSysDesc("maEqBmListDTO.filterPlfTypeDesc","maEqBmListDTO.filterPlfTypeId","PLF_TYPE");
}

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	myGrid.enableSmartRendering(true,500); 
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		selectedId = rowId;
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		return sortColumn("maEqBmList", this, maEqBmListForm, "", ind, direction);
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox") });
	myGrid.init();
	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maEqBmList.do";

    maEqBmListForm.elements['strutsAction'].value = '<%=MaEqBmListAction.BM_LIST_FIND%>';

    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maEqBmListForm), "", "Y");
    
/*     myGrid.clearAll(); setLoading("gridbox");
    $.post(url,FormQueryString(maEqBmListForm), function(_data){
    	myGrid.parse(_data,"js");
    }); */
}
/**
 * 엑셀 다운.
 */
 function goExcel()
 {
	 excelServerAction("maEqBmList", maEqBmListForm);
 }

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	
	//validation
	if(checkRequireValue("maEqBmListDTO.filterStartDate","<bean:message key='LABEL.date'/>")) return;
	if(checkRequireValue("maEqBmListDTO.filterEndDate","<bean:message key='LABEL.date'/>")) return;
	if(checkTwoDate(maEqBmListForm.elements['maEqBmListDTO.filterStartDate'],
			maEqBmListForm.elements['maEqBmListDTO.filterEndDate'])) return;
	
	if(window.name!="EQ_BM_LIST"){
		//검색 기간 제한 
		var days = getDayInterval(maEqBmListForm.elements['maEqBmListDTO.filterStartDate'].value.replace(/\-/gi, ""),
				maEqBmListForm.elements['maEqBmListDTO.filterEndDate'].value.replace(/\-/gi, ""));
		//if(days>367){
		//	alertMessage1("<bean:message key='MESSAGE.MSG0014'/>");
		//	return;
		//}
	}
	
    findGridList('Search', '<%=MaEqBmListAction.BM_LIST_FIND%>');   
}

/**
 * 상세W/O 보기
 */
 function goWo(){
	//if(typeof selectedId !="number") return;
	var pmType = getValueById(myGrid, selectedId, "PMTYPE");
	pmType = pmType.substring(0,1)+pmType.substring(1,pmType.length).toLowerCase();
	
	if("Etc" == pmType)
	{
		pmType = 'Rpr';
	}
	else if("Esp" == pmType || "Psp" == pmType)
	{
	    pmType = 'Rpl';		
	}
	
	var url   = contextPath + "/maWoResultBm"+pmType+"MstrDetail.do";
	
	var popWidth = 1010;
	var popHeight = 640;

    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
    
    var param = "strutsAction=8001&maWoResultMstrCommonDTO.wkOrId="+ getValueById(myGrid, selectedId, "WKORID")
    			+"&maWoResultMstrCommonDTO.selectedWoType=BM";
				+"&maWoResultMstrCommonDTO.selectedPmType="+pmType.toUpperCase();
  
    openWindowWithPost(url, "WO_DETAIL", param, pos);
}
 /**
  * 설비고장검색하기
  */
  function goBreak(){
	//if(typeof selectedId !="number") return;
	
	var url   = contextPath + "/maEqBmList.do";

	var popWidth = 1010;
	var popHeight = 640;

    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";

    var param = "isDecoratorName=popupPage&strutsAction=&maEqBmListDTO.filterEquipId="+ getValueById(myGrid, selectedId, "EQID")
								+"&maEqBmListDTO.filterEquipDesc="+ getValueById(myGrid, selectedId, "EQDESC")
								+"&maEqBmListDTO.filterStartDate="+ getMinusDay(365).replace(/\-/gi, "")
								+"&maEqBmListDTO.filterEndDate="+ getToday().replace(/\-/gi, "");
    openWindowWithPost(url, "EQ_BM_LIST", param, pos);
 }
 /**
  * 부품교체검색하기
  */
  function goChpart(){
	//if(typeof selectedId !="number") return;
	
	var url   = contextPath + "/maWoPtHistList.do";
	
	var popWidth = 1010;
	var popHeight = 640;
    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
	
	var bottomForm = document.bottomForm;
	var param = "isDecoratorName=popupPage"+
				"&maWoPtHistCommonDTO.strutsAction="+
				"&maWoPtHistCommonDTO.filterStartDate="+getMinusDay(365).replace(/\-/gi, "")+
				"&maWoPtHistCommonDTO.filterEndDate="+getToday().replace(/\-/gi, "")+
				"&maWoPtHistCommonDTO.filterEquipId="+getValueById(myGrid, selectedId, "EQID")+
				"&maWoPtHistCommonDTO.filterEquipDesc="+getValueById(myGrid, selectedId, "EQDESC");
	//post 전송
	openWindowWithPost(url, "USE_PT_LIST_POPUP", param, pos);
 }
 /**
  * 예방수리검색하기
  */
  function goRepair(){
	//if(typeof selectedId !="number") return;
	var url   = contextPath + "/maPmRepList.do";

	var popWidth = 1010;
	var popHeight = 640;

    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";

    var param = "isDecoratorName=popupPage"+"&strutsAction=&maPmRepListDTO.filterEquipId="+ getValueById(myGrid, selectedId, "EQID")
								+"&maPmRepListDTO.filterEquipDesc="+ getValueById(myGrid, selectedId, "EQDESC")
								+"&maPmRepListDTO.filterStartDate="+ getMinusDay(365).replace(/\-/gi, "")
								+"&maPmRepListDTO.filterEndDate="+ getToday().replace(/\-/gi, "");
  
    openWindowWithPost(url, "PM_REP_LIST", param, pos);
 }
 /**
  * 예방점검검색
  */
  function goInspection(){
	//if(typeof selectedId !="number") return;
	var url   = contextPath + "/maPmPointList.do";

	var popWidth = 1010;
	var popHeight = 640;

    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";

    var param = "isDecoratorName=popupPage"+"&strutsAction=&maPmPointListDTO.filterEquipId="+ getValueById(myGrid, selectedId, "EQID")
								+"&maPmPointListDTO.filterEquipDesc="+ getValueById(myGrid, selectedId, "EQDESC")
								+"&maPmPointListDTO.filterStartDate="+ getMinusDay(365).replace(/\-/gi, "")
								+"&maPmPointListDTO.filterEndDate="+ getToday().replace(/\-/gi, "");
  
    openWindowWithPost(url, "PM_POINT_LIST", param, pos);
 }
 /**
  * 예방기준확인
  */
  function goStandard(){
	//if(typeof selectedId !="number") return;
	var url   = contextPath + "/maPmMstrList.do";
	var popWidth = 1010;
	var popHeight = 640;
    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
	
	var bottomForm = document.bottomForm;
	var param = "isDecoratorName=popupPage"+
				"&maPmMstrCommonDTO.strutsAction="+
				"&maPmMstrCommonDTO.equipId="+getValueById(myGrid, selectedId, "EQID")+
				"&maPmMstrCommonDTO.equipDesc="+getValueById(myGrid, selectedId, "EQDESC");
	//post 전송
	openWindowWithPost(url, "PM_LIST", param, pos);
 }

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maEqBmList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maEqBmListDTO.filterDeptId"/>
<html:hidden property="maEqBmListDTO.filterMainMngId"/>
<html:hidden property="maEqBmListDTO.filterSubMngId"/>
<html:hidden property="maEqBmListDTO.filterEqLocId"/>
<html:hidden property="maEqBmListDTO.filterEqCtgId"/>
<html:hidden property="maEqBmListDTO.filterEquipId"/>
<html:hidden property="maEqBmListDTO.filterPlfTypeId"/>
<html:hidden property="maEqBmListDTO.filterFailTime"/>
<html:hidden property="maEqBmListDTO.filterPmTypeId"/>
<html:hidden property="maEqBmListDTO.filterCaCd"/>
<html:hidden property="maEqBmListDTO.filterReCd"/>
<html:hidden property="maEqBmListDTO.filterPlantId"/>
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
				<!-- 작업일자 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.date"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="maEqBmListDTO.filterStartDate" tabindex="10" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="maEqBmListDTO.filterEndDate" tabindex="20" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 부서 -->
				<div class="field">
					<label><bean:message key="LABEL.dept"/></label>
					<div class="input_box">
						<html:text property="maEqBmListDTO.filterDeptDesc" tabindex="30" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 설비-->
				<div class="field">
					<label><bean:message key="LABEL.equipDesc"/></label>
					<div class="input_box">
						<html:text property="maEqBmListDTO.filterEquipDesc" tabindex="40" />
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
						<html:text property="maEqBmListDTO.filterEqLocDesc" tabindex="50"/>
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
						<html:text property="maEqBmListDTO.filterEqCtgDesc" tabindex="60" />
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 내/외자 -->
				<div class="field">
					<label><bean:message key="LABEL.plfType"/></label>
					<div class="input_box">
						<html:text property="maEqBmListDTO.filterPlfTypeDesc" tabindex="70" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 법정설비여부 -->
				<div class="field">
					<label><bean:message key="LABEL.isLawEq"/></label>
					<div class="input_box">
						<html:text property="maEqBmListDTO.filterIsLawEq" tabindex="80" />
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
						<html:text property="maEqBmListDTO.filterMainMngName" tabindex="90" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 관리자(부) -->
				<div class="field">
					<label><bean:message key="LABEL.subManager"/></label>
					<div class="input_box">
						<html:text property="maEqBmListDTO.filterSubMngName" tabindex="100" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 작업형태  -->
				<div class="field">
					<label><bean:message key="LABEL.pmType"/></label>
					<div class="input_box">
						<html:text property="maEqBmListDTO.filterPmTypeDesc" tabindex="120" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 고장원인 -->
				<div class="field">
					<label><bean:message key="LABEL.caCd"/></label>
					<div class="input_box">
						<html:text property="maEqBmListDTO.filterCaCdDesc" tabindex="120" />
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 고장조치 -->
				<div class="field">
					<label><bean:message key="LABEL.reCd"/></label>
					<div class="input_box">
						<html:text property="maEqBmListDTO.filterReCdDesc" tabindex="130" />
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 고장시간  -->
				<div class="field">
					<label><bean:message key="LABEL.bmTime"/></label>
					<div class="input_box">
						<html:text property="maEqBmListDTO.filterFailTimeDesc" tabindex="140" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 공장명  -->
                <div class="field">
                    <label><bean:message key="LABEL.plantDesc"/></label>
                    <div class="input_box">
                    	<html:text property="maEqBmListDTO.filterPlantDesc" tabindex="90" />
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
		<div class="article_box">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:410px; background-color:white;"></div>
			</div>
		</div>
	</div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>