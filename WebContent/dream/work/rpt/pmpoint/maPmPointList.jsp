<%--===========================================================================
예방점검내역
author  kim21017
version $Id: maPmPointList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.mapmpoint.action.MaPmPointListAction" %>
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
<title><bean:message key="MENU.PMPOINTLIST"/></title>
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
var empAc;
var plantAc;

function loadPage() 
{
	if(window.name=="PM_POINT_LIST"){
	}else{
		//일자 세팅
		maPmPointListForm.elements['maPmPointListDTO.filterStartDate'].value = getMinusDay(7);
		maPmPointListForm.elements['maPmPointListDTO.filterEndDate'].value   = getToday();

		//부서정보 세팅
		maPmPointListForm.elements['maPmPointListDTO.filterDeptId'].value    = loginUser.filterDeptId;
		maPmPointListForm.elements['maPmPointListDTO.filterDeptDesc'].value  = loginUser.filterDeptDesc;
		if(loginUser.eqLocId!='null'){
			maPmPointListForm.elements['maPmPointListDTO.filterEqLocId'].value = loginUser.eqLocId;
			maPmPointListForm.elements['maPmPointListDTO.filterEqLocDesc'].value = loginUser.eqLocDesc;
		}
	}
	
	//공장명
    if(loginUser.filterPlant!='null'){
    	maPmPointListForm.elements['maPmPointListDTO.filterPlantId'].value  = loginUser.filterPlant;
    	maPmPointListForm.elements['maPmPointListDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
	}
	
    initGrid();
    
    deptAc = new autoC({"maPmPointListDTO.filterDeptDesc":"description"});
    deptAc.setAcDisplay("DESCRIPTION");
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setAcDConditionMap({
    	"plant" : "maPmPointListDTO.filterPlantId"
    });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "maPmPointListDTO.filterDeptId":"dept_id"
    });
    deptAc.init();
    
    equipDescAc = new autoC({"maPmPointListDTO.filterEquipDesc":"description"});
    equipDescAc.setAcDisplay("DESCRIPTION");
    equipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    equipDescAc.setAcDConditionMap({
    	"eqloc_id" : "maPmPointListDTO.filterEqLocId",
    	"eqctg_id" : "maPmPointListDTO.filterEqCtgId",
    	"dept_id" : "maPmPointListDTO.filterDeptId",
    	"plant" : "maPmPointListDTO.filterPlantId"
    });
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcResultMap({
        "maPmPointListDTO.filterEquipId":"equip_id"
    });
    equipDescAc.init();
    
    eqLocDescAc = new autoC({"maPmPointListDTO.filterEqLocDesc":"full_desc"});
    eqLocDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqLocDescAc.setAcDConditionMap({
    	"plant" : "maPmPointListDTO.filterPlantId"
    });
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setAcResultMap({
        "maPmPointListDTO.filterEqLocId":"eqloc_id"
    });
    eqLocDescAc.init();
    
    eqCtgTypeAc = new autoC({"maPmPointListDTO.filterEqCtgDesc":"full_desc"});
    eqCtgTypeAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqCtgTypeAc.setTable("TAEQCTG");
    eqCtgTypeAc.setAcResultMap({
        "maPmPointListDTO.filterEqCtgId":"eqctg_id"
    });
    eqCtgTypeAc.init();

    //법정설비 여부 AC
    acSysDesc("maPmPointListDTO.filterIsLawEq","maPmPointListDTO.filterIsLawEq","IS_USE",true);
    
    mainMngAc = new autoC({"maPmPointListDTO.filterMainMngName":"emp_name"});
    mainMngAc.setAcDisplay("EMP_NAME");
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "maPmPointListDTO.filterDeptId",
    	"plant" : "maPmPointListDTO.filterPlantId"
    });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcResultMap({
        "maPmPointListDTO.filterMainMngId":"emp_id"
    });
    mainMngAc.init();
    
    subMngAc = new autoC({"maPmPointListDTO.filterSubMngName":"emp_name"});
    subMngAc.setAcDisplay("EMP_NAME");
    subMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    subMngAc.setAcDConditionMap({
    	"dept_id" : "maPmPointListDTO.filterDeptId",
    	"plant" : "maPmPointListDTO.filterPlantId"
    });
    subMngAc.setTable("TAEMP");
    subMngAc.setAcResultMap({
        "maPmPointListDTO.filterSubMngId":"emp_id"
    });
    subMngAc.init();
    
    empAc = new autoC({"maPmPointListDTO.filterEmpDesc":"emp_name"});
    empAc.setAcDisplay("EMP_NAME");
    empAc.setAcConditionMap({
        "comp_no":loginUser.compNo
       });
    empAc.setAcDConditionMap({
    	"dept_id" : "maPmPointListDTO.filterDeptId",
    	"plant" : "maPmPointListDTO.filterPlantId"
    });
    empAc.setTable("TAEMP");
    empAc.setAcResultMap({
        "maPmPointListDTO.filterEmpId":"emp_id"
    });
    empAc.init();
    
 	// 공장코드
	plantAc = new autoC({"maPmPointListDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "maPmPointListDTO.filterPlantId":"plant"
    });
    plantAc.init();
    
	// 내/외자
    acSysDesc("maPmPointListDTO.filterPlfTypeDesc","maPmPointListDTO.filterPlfTypeId","PLF_TYPE");

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
        return sortColumn("maPmPointList", this, maPmPointListForm, "", ind, direction);
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
    var url = contextPath + "/maPmPointList.do";

    maPmPointListForm.elements['strutsAction'].value = '<%=MaPmPointListAction.PM_LIST_FIND%>';
    
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maPmPointListForm), "", "Y");
    
/*    myGrid.clearAll(); setLoading("gridbox");
    $.post(url,FormQueryString(maPmPointListForm), function(_data){
    	myGrid.parse(_data,"js");
    }); */
}
/**
 * 엑셀 다운.
 */
 function goExcel()
 {
	 excelServerAction("maPmPointList", maPmPointListForm);
 }

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	
	//validation
	if(checkRequireValue("maPmPointListDTO.filterStartDate","<bean:message key='LABEL.date'/>")) return;
	if(checkRequireValue("maPmPointListDTO.filterEndDate","<bean:message key='LABEL.date'/>")) return;
	if(checkTwoDate(maPmPointListForm.elements['maPmPointListDTO.filterStartDate'],
			maPmPointListForm.elements['maPmPointListDTO.filterEndDate'])) return;
	
	//if(window.name!="PM_POINT_LIST"){
		//검색 기간 제한 
		//var days = getDayInterval(maPmPointListForm.elements['maPmPointListDTO.filterStartDate'].value.replace(/\-/gi, ""),
		//		maPmPointListForm.elements['maPmPointListDTO.filterEndDate'].value.replace(/\-/gi, ""));
		//if(days>367){
			//alertMessage1("<bean:message key='MESSAGE.MSG0222'/>");
			//return;
		//}
	//}
	
    findGridList('Search', '<%=MaPmPointListAction.PM_LIST_FIND%>');   
}

/**
 * 상세W/O 보기
 */
 function goWo(){
	if(typeof selectedId !="number") return;
	
	var url   = contextPath + "/maWoResultPmInsMstrDetail.do";
	
	var popWidth = 1010;
	var popHeight = 640;

    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
    
    var param = "strutsAction=8001&maWoResultMstrCommonDTO.wkOrId="+ getValueById(myGrid, selectedId, "WKORID");
  
    openWindowWithPost(url, "WO_DETAIL", param, pos);
}
 /**
  * 설비고장검색하기
  */
  function goBreak(){
	if(typeof selectedId !="number") return;
	
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
	if(typeof selectedId !="number") return;
	
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
	if(typeof selectedId !="number") return;
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
	if(typeof selectedId !="number") return;
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
	if(typeof selectedId !="number") return;
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
<html:form action="/maPmPointList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maPmPointListDTO.filterDeptId"/>
<html:hidden property="maPmPointListDTO.filterMainMngId"/>
<html:hidden property="maPmPointListDTO.filterSubMngId"/>
<html:hidden property="maPmPointListDTO.filterEqLocId"/>
<html:hidden property="maPmPointListDTO.filterEqCtgId"/>
<html:hidden property="maPmPointListDTO.filterEquipId"/>
<html:hidden property="maPmPointListDTO.filterPlfTypeId"/>

<html:hidden property="maPmPointListDTO.filterEmpId"/>
<html:hidden property="maPmPointListDTO.filterPlantId"/>
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
							<html:text property="maPmPointListDTO.filterStartDate" tabindex="10" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="maPmPointListDTO.filterEndDate" tabindex="20" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 부서 -->
				<div class="field">
					<label><bean:message key="LABEL.dept"/></label>
					<div class="input_box">
						<html:text property="maPmPointListDTO.filterDeptDesc" tabindex="30" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 담당자 -->
                <div class="field">
                    <label><bean:message key="LABEL.manager"/></label>
                    <div class="input_box">
                        <html:text property="maPmPointListDTO.filterEmpDesc" tabindex="35" />
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
				<!-- 설비-->
				<div class="field">
					<label><bean:message key="LABEL.equipDesc"/></label>
					<div class="input_box">
						<html:text property="maPmPointListDTO.filterEquipDesc" tabindex="40" />
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
						<html:text property="maPmPointListDTO.filterEqLocDesc" tabindex="50"/>
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
						<html:text property="maPmPointListDTO.filterEqCtgDesc" tabindex="60" />
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
						<html:text property="maPmPointListDTO.filterPlfTypeDesc" tabindex="70" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 법정설비여부 -->
				<div class="field">
					<label><bean:message key="LABEL.isLawEq"/></label>
					<div class="input_box">
						<html:text property="maPmPointListDTO.filterIsLawEq" tabindex="80" />
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
						<html:text property="maPmPointListDTO.filterMainMngName" tabindex="90" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 관리자(부) -->
				<div class="field">
					<label><bean:message key="LABEL.subManager"/></label>
					<div class="input_box">
						<html:text property="maPmPointListDTO.filterSubMngName" tabindex="100" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 공장명  -->
                <div class="field">
                    <label><bean:message key="LABEL.plantDesc"/></label>
                    <div class="input_box">
						<html:text property="maPmPointListDTO.filterPlantDesc" tabindex="90" />
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