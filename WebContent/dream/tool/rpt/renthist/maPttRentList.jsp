<%--===========================================================================
공기구대여내역 - 목록
author  ssong
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.tool.rpt.renthist.action.MaPttRentListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 공기구대여내역 -->
<title><bean:message key='MENU.PTTRENT'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var myGrid;
var wareHouseAc;
var deptAc;
var partNameAc;
var mainMngAc;
function loadPage() 
{
    initGrid();
    if(window.name=="CHART_REC_LIST_POPUP"){
    	
    }else{
    	maPttRentListForm.elements['maPttRentCommonDTO.filterDeptId'].value   = loginUser.filterDeptId;
        maPttRentListForm.elements['maPttRentCommonDTO.filterDeptDesc'].value = loginUser.filterDeptDesc;
    }
    
    wareHouseAc = new autoC({"maPttRentCommonDTO.filterWname":"wname"});
    wareHouseAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"wh_categ":"TOOL"
  	   });
    wareHouseAc.setTable("TAWAREHOUSE");
    wareHouseAc.setAcResultMap({
        "maPttRentCommonDTO.filterWcodeId":"wcode_id"
    });
    wareHouseAc.init();
    
    partNameAc = new autoC({"maPttRentCommonDTO.filterPartNameSize":"partNameSize"});
    partNameAc.setAcConditionMap({
	   "part_categ":"TOOL",
	   "comp_no":loginUser.compNo
	   });
    partNameAc.setTable("TAPARTS");
    partNameAc.init();
    
    deptAc = new autoC({"maPttRentCommonDTO.filterDeptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "maPttRentCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.init();
    
    mainMngAc = new autoC({"maPttRentCommonDTO.filterRecName":"emp_name"});
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcResultMap({
        "maPttRentCommonDTO.filterRecBy":"emp_id"
    });
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "maPttRentCommonDTO.filterDeptId"
    });
    mainMngAc.init();
}

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		//goOpen();
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maPttRentList.do";

    maPttRentListForm.elements['strutsAction'].value = '<%=MaPttRentListAction.PTRENT_STAT_LIST_FIND%>';
    
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maPttRentListForm));
    
/*     myGrid.clearAll(); setLoading("gridbox");
    $.post(url,FormQueryString(maPttRentListForm), function(_data){
    	myGrid.parse(_data,"js");
    }); */
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
    findGridList('Search');   
}
 
/**
 * Excel Export
 */
function goExcel()
{
    excelAction(myGrid);
}

/**
 * 폐기신청
 */
 var selectArray;
function goCheckreturn()
{
	var selArray = getSelectRows(myGrid, 'ISDELCHECK', 'PTRENTLISTID');
	if(typeof selArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	
	getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0091'/>", function(result){
		 if(result){
			 selectArray = selArray;
			sequenceNextVal('SQAPTRTNLIST_ID');
		 }
		});
}

function setSequenceVal(sequenceVal)
{
	maPttRentListForm.elements['maPttRentCommonDTO.reqIdx'].value = sequenceVal;
	maPttRentListForm.strutsAction.value = '<%=MaPttRentListAction.PTRENT_STAT_LIST_RETURN%>';
	var url = contextPath + "/maPttRentList.do"; 
   $.post(url,FormQueryString(maPttRentListForm)+selectArray , function(_data){
   	afterReturnreq();
   });
}
/**
 * after반납신청
 */
 function afterReturnreq()
 {
 	//구매신청 상세 팝업 띄우기
	var url   = contextPath + "/maPttRtnList.do";
	var popWidth = 1010;
	var popHeight = 640;

    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
    
    var param = "isDecoratorName=popupPage&strutsAction=&maPttRtnCommonDTO.filterRtnStartDate="+ getToday()+"&maPttRtnCommonDTO.filterRtnEndDate="+ getToday();
  
    openWindowWithPost(url, "RTNREQHDR", param, pos);
    
    goSearch();
 }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maPttRentList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maPttRentCommonDTO.filterDeptId"/>
<html:hidden property="maPttRentCommonDTO.reqIdx"/>
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
                <div class="field">
					<label><bean:message key="LABEL.wcode"/></label>
					<div class="input_box">
	                     <html:text property="maPttRentCommonDTO.filterWname" tabindex="10" />
			             <p class="open_spop">
			                 <a>
			                     <span>조회</span>
			                 </a>
			             </p>
	                </div>
                </div>
                <div class="field">
					<label><bean:message key="LABEL.ptNameSize"/></label>
					<div class="input_box">
						<html:text property="maPttRentCommonDTO.filterPartNameSize" tabindex="50"/>
					</div>
				</div>
				<div class="field">
                    <label><bean:message key="LABEL.recDept"/></label>
                    <div class="input_box">
                        <html:text property="maPttRentCommonDTO.filterDeptDesc" tabindex="10"/>
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
                <!-- 수령자 -->
                <div class="field">
                    <label><bean:message key="LABEL.recBy"/></label>
                    <div class="input_box">
                        <html:text property="maPttRentCommonDTO.filterRecName" tabindex="40"/>
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