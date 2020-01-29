<%--===========================================================================
부서별 작업진행현황
author  youngjoo38
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.deptworkprecon.action.WorkRptDeptWorkPreconListAction" %>
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
<!-- 부서별 작업진행현황 -->
<title><bean:message key="MENU.DEPTWORKPRECON"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//
//그리드명
var myGrid;			//부서별 작업진행현황 그리드
/** 자동완성 변수 */
var plantAc;

function loadPage() 
{
	// Grid 헤더 세팅
	setGridHeader();
	
	//일자 세팅
	workRptDeptWorkPreconListForm.elements['workRptDeptWorkPreconListDTO.filterStartDate'].value = getMinusMonth(0)+"-01";
	workRptDeptWorkPreconListForm.elements['workRptDeptWorkPreconListDTO.filterEndDate'].value   = getMinusDay(0);
	
	//공장명
    if(loginUser.filterPlant!='null' && loginUser.filterPlant!=''){
    	workRptDeptWorkPreconListForm.elements['workRptDeptWorkPreconListDTO.filterPlantId'].value  = loginUser.filterPlant;
    	workRptDeptWorkPreconListForm.elements['workRptDeptWorkPreconListDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
	}

	goSearch();
	
	// 공장코드
	plantAc = new autoC({"workRptDeptWorkPreconListDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "workRptDeptWorkPreconListDTO.filterPlantId":"plant"
    });
    plantAc.init();
	
}

function setGridHeader()
 {	
	// TACDSYSD에서 LIST_TYPE이 'WO_TYPE'인 것 중에 IS_USE가 'Y'인 것만 알아와서 세팅한다.
	var url = contextPath + "/"+currentPageId+".do";
	var param = "&strutsAction="+<%= WorkRptDeptWorkPreconListAction.SET_GRID_HEADER %>
	
	XMLHttpPostVal(url , param, 'beforeInitGrid');
}

function beforeInitGrid(ajaxXmlDoc)
{
	var woTypes = '0';
	woTypes = parseXmlDoc(ajaxXmlDoc, 'DESC');
	var woTypeArr = woTypes.toString().split(',');

	if(woTypes != '0')
	{
		workRptDeptWorkPreconListForm.elements['workRptDeptWorkPreconListDTO.woTypes'].value = woTypes;
		workRptDeptWorkPreconListForm.elements['workRptDeptWorkPreconListDTO.woTypeCnt'].value = woTypeArr.length-1;
		
		initGrid(workRptDeptWorkPreconListForm.elements['workRptDeptWorkPreconListDTO.woTypes'].value);
	}
}

/**
 * 부서별 작업진행현황 그리드 초기화
 */
function initGrid(woTypes)
{
	// TACDSYSD에서 LIST_TYPE이 'WO_TYPE'인 것 중에 IS_USE가 'Y'인 WO_TYPE의 갯수를 세팅한다. 
	var woTypeCnt = workRptDeptWorkPreconListForm.elements['workRptDeptWorkPreconListDTO.woTypeCnt'].value;

	myGrid = new dhtmlXGridObject('gridbox');
    myGrid.enableTreeGridLines();
    myGrid.setImageSize(1,1);
    myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	myGrid.setHeader("<bean:message key='LABEL.dept'/>,,,,," + getColumns()
			+ ", <bean:message key='LABEL.total'/>, <bean:message key='LABEL.completed'/>, <bean:message key='LABEL.Incompleted'/>"); // 합계, 완료, 미완료
	myGrid.setColumnIds("DEPTDESC,ID,LVL,MINLVL,DEPTID,PDEPTID"+getColumnsId()+",TOTCNT,COMCNT,INCOMCNT");
	myGrid.setInitWidths("350,100,100,100,100,100"+getWords(woTypeCnt,"100")+",100,100,100");
	myGrid.setColAlign("left,left,left,left,left,left"+getWords(woTypeCnt,"right")+",right,right,right");
	myGrid.setColTypes("tree,ro,ro,ro,ro,ro"+getWords(woTypeCnt,"ro")+",ro,ro,ro");
	myGrid.setColumnHidden(1,true);
	myGrid.setColumnHidden(2,true);
	myGrid.setColumnHidden(3,true);
	myGrid.setColumnHidden(4,true);
	myGrid.setColumnHidden(5,true);
	
	$('#gridbox').height(500);
	myGrid.setSizes();
	
	myGrid.attachEvent("onRowDblClicked", function(id,ind){
		goWoList(id,ind);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox"); });
	myGrid.init();
}

// Grid 헤더 이름 세팅
function getColumns()
{
	var str = "";
	var woTypeArr = workRptDeptWorkPreconListForm.elements['workRptDeptWorkPreconListDTO.woTypes'].value.split(',');
	
	for(var i=1; i<woTypeArr.length; i++)
	{
		switch (woTypeArr[i])
	    {
	    	case 'BMCNT' :		//고장작업
	    		str += ", <bean:message key='CODESET.WO_TYPE.BM'/>";
	    		break;
	    	case 'PMCNT' :		//예방작업
	    		str += ", <bean:message key='CODESET.WO_TYPE.PM'/>";
	    		break;
	    	case 'CMCNT' :		//개조개선
	    		str += ", <bean:message key='CODESET.WO_TYPE.CM'/>";
	    		break;
	    	case 'IVCNT' :		//투자작업
	    		str += ", <bean:message key='CODESET.WO_TYPE.IV'/>";
	    		break;
	    	case 'PMWCNT' :		//정기 수리/교체작업
	    		str += ", <bean:message key='CODESET.WO_TYPE.PMW'/>";
	    		break;
	    	case 'PMICNT' :		//예방 점검작업
	    		str += ", <bean:message key='CODESET.WO_TYPE.PMI'/>";
	    		break;
	    	case 'TICNT' :		//설치/테스트작업
	    		str += ", <bean:message key='CODESET.WO_TYPE.TI'/>";
	    		break;
	    	case 'PMCCNT' :		//교정작업
	    		str += ", <bean:message key='CODESET.WO_TYPE.PMC'/>";
	    		break;
	    	case 'TRCNT' :		//예방 교육
	    		str += ", <bean:message key='CODESET.WO_TYPE.TR'/>";
	    		break;
	    	case 'PMPCNT' :		//예방 순회점검
	    		str += ", <bean:message key='CODESET.WO_TYPE.PMP'/>";
	    		break;
	    	default :
	   			break;
		}
    }
	return str;
}
//Grid 헤더 ID 세팅
function getColumnsId()
{
	var str = "";
	str = workRptDeptWorkPreconListForm.elements['workRptDeptWorkPreconListDTO.woTypes'].value;
	return str;
}

function goWoList(id, ind)
{
	if(typeof id=="undefined"||typeof ind=="undefined") return ;

	var woTypeId = "";
	var woStatusId = "";
	var startDate = workRptDeptWorkPreconListForm.elements['workRptDeptWorkPreconListDTO.filterStartDate'].value;
	var endDate = workRptDeptWorkPreconListForm.elements['workRptDeptWorkPreconListDTO.filterEndDate'].value;
	var deptId = getValueById(myGrid, id, "DEPTID");
	var deptDesc = getValueById(myGrid, id, "DEPTDESC");
	
	// 선택한 cell의 Id
	var tempWoTypeId = myGrid.getColumnId(myGrid.getSelectedCellIndex());
		
	// 선택한 cell이 부서인 경우 return 한다.
	if("DEPTDESC"==tempWoTypeId) return;

	// 선택한 cell이   [ 합계 / 완료 / 미완료 ] 가 아닌 경우 woTypeId를 세팅한다.
	if("TOTCNT"!=tempWoTypeId&&"COMCNT"!=tempWoTypeId&&"INCOMCNT"!=tempWoTypeId)
		woTypeId = myGrid.getColumnId(myGrid.getSelectedCellIndex()).replace("CNT","");
	
	// 선택한 cell이 [ 완료 / 미완료 ] 인 경우 woStatusId를 세팅한다.
    switch (myGrid.getColumnId(myGrid.getSelectedCellIndex()))
    {
    	case 'COMCNT' :		//완료
    		woStatusId = "C";
    		break;
    	case 'INCOMCNT' :	//미완료
    		woStatusId = "!C";
    		break;
    	default :
   			break;
    }
    
    var url   = contextPath + "/maWoResultMstrList.do";
	var popWidth = 1010;
	var popHeight = 640;
    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
    
	var param = "isDecoratorName=popupPage"+
				"&maWoResultMstrCommonDTO.strutsAction="+
				"&maWoResultMstrCommonDTO.filterDeptId="+deptId+
				"&maWoResultMstrCommonDTO.filterDeptDesc="+deptDesc+
				"&maWoResultMstrCommonDTO.filterWoTypeId="+woTypeId+
				"&maWoResultMstrCommonDTO.filterWoStatus="+woStatusId+
				"&maWoResultMstrCommonDTO.filterStartDate="+startDate+
				"&maWoResultMstrCommonDTO.filterEndDate="+endDate
				+"&ACTION_FUNCTION=goSearch";
	//post 전송
	openWindowWithPost(url, "DEPT_WORK_POPUP", param, pos);
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 부서별 작업진행현황 그리드에 셋팅한다.
 */
function findGridList()
{
	if(checkValidation()) return;

    var url = contextPath + "/workRptDeptWorkPreconList.do";
    workRptDeptWorkPreconListForm.elements['strutsAction'].value = '<%=WorkRptDeptWorkPreconListAction.LIST_FIND%>';
    myGrid.clearAll();
    setLoading("gridbox");
    //전체부서별 작업진행현황 그리드 초기화
    //initGrid(workRptDeptWorkPreconListForm.elements['workRptDeptWorkPreconListDTO.woTypes'].value);

    setModal(); 
    
    $.post(url,FormQueryString(workRptDeptWorkPreconListForm), function(_data){
    	myGrid.parse(_data,"js");
    	myGrid.expandAll();
    	setCounter(myGrid,"gridbox");
    	closeModal();
    });
}

/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	//validation
	if(checkRequireValue("workRptDeptWorkPreconListDTO.filterStartDate","<bean:message key='LABEL.workDate'/>")) return;
	if(checkRequireValue("workRptDeptWorkPreconListDTO.filterEndDate","<bean:message key='LABEL.workDate'/>")) return;
	if(checkTwoDate(workRptDeptWorkPreconListForm.elements['workRptDeptWorkPreconListDTO.filterStartDate']+"-01",
			workRptDeptWorkPreconListForm.elements['workRptDeptWorkPreconListDTO.filterEndDate']+"-01")) return;
	//검색 기간 제한 
	var days = getDayInterval(workRptDeptWorkPreconListForm.elements['workRptDeptWorkPreconListDTO.filterStartDate'].value.replace(/\-/gi, ""),
			workRptDeptWorkPreconListForm.elements['workRptDeptWorkPreconListDTO.filterEndDate'].value.replace(/\-/gi, ""));
	
	if(days>365){
		//최대 1년 단위로 검색 가능합니다.
		alertMessage1("<bean:message key='MESSAGE.MSG0222'/>");
		return;
	}
		
    findGridList();
}
 
/**
 * 전체부서별 작업진행현황 엑셀 다운
 */
 function goExcel()
 {
 	excelAction(myGrid);
 	//workRptDeptWorkPreconListForm.elements['workRptDeptWorkPreconListDTO.deptId'].value = "";
	//excelServerAction("workRptDeptWorkPreconList", workRptDeptWorkPreconListForm );
 } 

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workRptDeptWorkPreconList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="workRptDeptWorkPreconListDTO.months"/>
<html:hidden property="workRptDeptWorkPreconListDTO.woTypeCnt"/>
<html:hidden property="workRptDeptWorkPreconListDTO.woTypes"/>
<html:hidden property="workRptDeptWorkPreconListDTO.deptId"/>
<html:hidden property="workRptDeptWorkPreconListDTO.filterPlantId"/>
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
				<!-- 일자 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.date"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="workRptDeptWorkPreconListDTO.filterStartDate" tabindex="10" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="workRptDeptWorkPreconListDTO.filterEndDate" tabindex="20" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 공장명 -->
				<div class="field">
					<label><bean:message key="LABEL.plantDesc"/></label>
						<div class="input_box">
						<html:text property="workRptDeptWorkPreconListDTO.filterPlantDesc" tabindex="30"/>
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
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
	</div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>