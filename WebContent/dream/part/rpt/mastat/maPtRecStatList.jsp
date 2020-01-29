<%--===========================================================================
자재입고내역 - 목록
author  ssong
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.part.rpt.mastat.action.MaPtRecStatListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 자재입고내역 -->
<title><bean:message key='MENU.PTRECSTAT'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var myGrid;

var deptAc;
var plantAc;
function loadPage() 
{
    initGrid();
    if(window.name=="CHART_REC_LIST_POPUP" || window.name == "LINKED_POPUP"){
    	
    }else{
    	maPtRecStatListForm.elements['maPtRecStatCommonDTO.filterDeptId'].value   = loginUser.filterDeptId;
        maPtRecStatListForm.elements['maPtRecStatCommonDTO.filterDeptDesc'].value = loginUser.filterDeptDesc;
       
        maPtRecStatListForm.elements['maPtRecStatCommonDTO.filterRecStartDate'].value = getMinusMonth2(new Date(), -1); 
        maPtRecStatListForm.elements['maPtRecStatCommonDTO.filterRecEndDate'].value   = getToday();
        
      	//공장명
        if(loginUser.filterPlant!='null'){
        	maPtRecStatListForm.elements['maPtRecStatCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
        	maPtRecStatListForm.elements['maPtRecStatCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
		}
    }
    
    deptAc = new autoC({"maPtRecStatCommonDTO.filterDeptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "maPtRecStatCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.setAcDConditionMap({
    	"plant" : "maPtRecStatCommonDTO.filterPlantId"
    });
    deptAc.init();
    
 	// 공장코드
	plantAc = new autoC({"maPtRecStatCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "maPtRecStatCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
        
 	// 부품명
    partNameAc = new autoC({"maPtRecStatCommonDTO.filterPartDesc":"full_desc"});
    partNameAc.setAcConditionMap({
	   "part_categ":"SPPT",
	   "comp_no":loginUser.compNo
	   });
    partNameAc.setTable("TAPARTS");
    partNameAc.setAcDConditionMap({
    	"wcode_id" : "maPtStckCommonDTO.filterWcodeId"
    });
    partNameAc.setAcResultMap({
        "maPtRecStatCommonDTO.filterPartNo":"part_no"
    });
    partNameAc.init();
}

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		//goOpen();
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		maPtRecStatListForm.elements['maPtRecStatCommonDTO.filterPartId'].value = "";
	   	return sortColumn("maPtRecStatList", this, maPtRecStatListForm, "PARTID", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maPtRecStatList.do";

    maPtRecStatListForm.elements['strutsAction'].value = '<%=MaPtRecStatListAction.PTREC_STAT_LIST_FIND%>';
    
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maPtRecStatListForm), "","Y");
    
//     myGrid.clearAll(); setLoading("gridbox");
//     $.post(url,FormQueryString(maPtRecStatListForm), function(_data){
//     	myGrid.parse(_data,"js");
//     });
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
    findGridList('Search', '<%=MaPtRecStatListAction.PTREC_STAT_LIST_FIND%>');   
}
 
/**
 * Excel Export
 */
function goExcel()
{
	excelServerAction("maPtRecStatList", maPtRecStatListForm);
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maPtRecStatList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maPtRecStatCommonDTO.filterDeptId"/>
<html:hidden property="maPtRecStatCommonDTO.filterPlantId"/>
<html:hidden property="maPtRecStatCommonDTO.filterPartId"/>
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
                    <label><bean:message key="LABEL.deptOg"/></label>
                    <div class="input_box">
                        <html:text property="maPtRecStatCommonDTO.filterDeptDesc" tabindex="10"/>
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
				<div class="field">
					<label><bean:message key="LABEL.recDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
	                        <html:text property="maPtRecStatCommonDTO.filterRecStartDate" tabindex="20" />
	                        <p class="open_calendar"><span>날짜</span></p>
	                    </div>
	                    <div class="input_box input_carendar">
	                        <html:text property="maPtRecStatCommonDTO.filterRecEndDate" tabindex="30" />
	                        <p class="open_calendar"><span>날짜</span></p>
	                    </div>
	                 </div>
				</div>
				<!-- 공장명  -->
                <div class="field">
                    <label><bean:message key="LABEL.plantDesc"/></label>
                    <div class="input_box">
							<html:text property="maPtRecStatCommonDTO.filterPlantDesc"
								tabindex="40" />
							<p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
				<!-- 부품번호  -->
                <div class="field">
                    <label><bean:message key="LABEL.partNo"/></label>
                    <div class="input_box">
							<html:text property="maPtRecStatCommonDTO.filterPartNo" tabindex="45" />
                    </div>
                </div>
				<!-- 부품명  -->
                <div class="field">
                    <label><bean:message key="LABEL.partDesc"/></label>
                    <div class="input_box">
							<html:text property="maPtRecStatCommonDTO.filterPartDesc"
								tabindex="50" />
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