<%--===========================================================================

author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.loc.goal.action.MaLnGoalListAction" %>
<%@ page import="dream.asset.loc.goal.action.MaLnGoalDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>

<html>
<head>
<!-- 라인별투자목표금액 -->
<title><bean:message key="MENU.LNGOAL"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//

var myGrid;

/** 자동완성 변수 */
var plantTypeAc;
var eqLocDescAc;
var goalItemAc;

function loadPage() 
{
	maLnGoalListForm.elements['maLnGoalCommonDTO.startYyyymm'].value = dateToData(getToday()).substr(0, 4)+"-"+dateToData(getToday()).substr(4, 2);
	maLnGoalListForm.elements['maLnGoalCommonDTO.endYyyymm'].value = dateToData(getToday()).substr(0, 4)+"-"+dateToData(getToday()).substr(4, 2);

	if(loginUser.eqLocId!='null'){
		maLnGoalListForm.elements['maLnGoalCommonDTO.eqlocId'].value = loginUser.eqLocId;
		maLnGoalListForm.elements['maLnGoalCommonDTO.eqlocIdDesc'].value = loginUser.eqLocDesc;
	}
	if(loginUser.filterPlant!='null'&&loginUser.filterPlant!=''){
		maLnGoalListForm.elements['maLnGoalCommonDTO.plant'].value = loginUser.filterPlant;
		maLnGoalListForm.elements['maLnGoalCommonDTO.plantDesc'].value = loginUser.filterPlantDesc;
	}
	initGrid();
	
	plantTypeAc = new autoC({"maLnGoalCommonDTO.plantDesc":"description"});
	plantTypeAc.setTable("TAPLANT");
	plantTypeAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
	plantTypeAc.setAcResultMap({
        "maLnGoalCommonDTO.plant":"plant"
    });
	plantTypeAc.init();
	
	eqLocDescAc = new autoC({"maLnGoalCommonDTO.eqlocIdDesc":"full_desc"});
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqLocDescAc.setAcResultMap({
        "maLnGoalCommonDTO.eqlocId":"eqloc_id"
    });
    eqLocDescAc.init();
    
    // 목표항목
    goalItemAc = new autoC({"maLnGoalCommonDTO.goalItemDesc":"description"});
    goalItemAc.setAcDisplay("DESCRIPTION");
    goalItemAc.setAcConditionMap({
        	"list_type":"MTLNPOINT",
        	"is_use":"Y"
  	   });
    goalItemAc.setTable("TACDSYSD");
    goalItemAc.setAcResultMap({
        "maLnGoalCommonDTO.goalItem":"cdsysd_no"
    });
    goalItemAc.init();
}

function initGrid()
{
    myGrid = new dhtmlXGridObject('gridbox');
    myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
    
    myGrid.attachEvent("onRowSelect",function(rowId, columnId){
        goOpen();
    });
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox");setRowSpan(myGrid,"PLANT");setRowSpan(myGrid,"YYYYMM");setRowSpan(myGrid,"EQLOCID");}); myGrid.init();

    setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maLnGoalList.do";

    maLnGoalListForm.elements['strutsAction'].value = '<%=MaLnGoalListAction.MTGOAL_LIST_FIND%>';

    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maLnGoalListForm), "mtLnPointId");
    myGrid.setNumberFormat("0,000.00",getCoumnIdx(myGrid,"pvalue"),".",",");
    
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(mtLnPointId)
{
	maLnGoalListForm.elements['maLnGoalCommonDTO.mtLnPointId'].value = mtLnPointId;
	findGridList('ReloadRow');
	maLnGoalListForm.elements['maLnGoalCommonDTO.mtLnPointId'].value = "";
}

/**
 * Filter에서 Sheet 검색을 하는 경우이다.
 */
function goSearch()
{
	maLnGoalListForm.elements['maLnGoalCommonDTO.mtLnPointId'].value = "";    // 검색시 Tab 이동Key Clear
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
    maLnGoalListForm.elements['maLnGoalCommonDTO.mtLnPointId'].value = getValueById(myGrid, selectedId, 'mtLnPointId');

    goCommonTabPage(maLnGoalListForm, <%=MaLnGoalDetailAction.MTGOAL_DETAIL_INIT%>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
	goTabPage('maLnGoalDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    maLnGoalListForm.elements['maLnGoalCommonDTO.mtLnPointId'].value = getValueById(myGrid, selectedId, 'mtLnPointId');
    maLnGoalListForm.elements['strutsAction'].value = '<%=MaLnGoalDetailAction.MTGOAL_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(maLnGoalListForm), 'maLnGoalDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "maLnGoalDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maLnGoalListForm.elements['maLnGoalCommonDTO.mtLnPointId'].value = "";
    goCommonTabPage(maLnGoalListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'mtLnPointId'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
    
    maLnGoalListForm.strutsAction.value = '<%=MaLnGoalListAction.MTGOAL_LIST_DELETE%>';
    var url = contextPath + "/maLnGoalList.do";

    $.post(url,FormQueryString(maLnGoalListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('maLnGoalDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
    excelAction(myGrid);
}
//-->
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/maLnGoalList">
<html:hidden property="strutsAction"/>
<html:hidden property="maLnGoalCommonDTO.compNo"/><!-- Key -->
<html:hidden property="maLnGoalCommonDTO.mtLnPointId"/><!-- Key -->
<html:hidden property="maLnGoalCommonDTO.plant"/>
<html:hidden property="maLnGoalCommonDTO.eqlocId"/>
<html:hidden property="maLnGoalCommonDTO.goalItem"/>
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
					<label class="check"><bean:message key="LABEL.yyyymm"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="maLnGoalCommonDTO.startYyyymm" tabindex="20" />
							<p class="open_mon_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="maLnGoalCommonDTO.endYyyymm" tabindex="30" />
							<p class="open_mon_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
                   <div class="field">
                       <label><bean:message key="LABEL.plant"/></label>
                       <div class="input_box">
                           <html:text property="maLnGoalCommonDTO.plantDesc" tabindex="50" />
                            <p class="open_spop">
                                <a>
                                    <span>조회</span>
                                </a>
                            </p>
                      </div>
                   </div>
                   <div class="field">
						<label><bean:message key="LABEL.eqlocId"/></label>
						<div class="input_box">
							<html:text property="maLnGoalCommonDTO.eqlocIdDesc" tabindex="60" />
							<p class="open_spop">
								<a>
									<span>조회</span>
								</a>
							</p>
						</div>
					</div>
					<div class="field">
						<label><bean:message key="LABEL.goalItem"/></label>
						<div class="input_box">
						<html:text property="maLnGoalCommonDTO.goalItemDesc" tabindex="120"/>
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