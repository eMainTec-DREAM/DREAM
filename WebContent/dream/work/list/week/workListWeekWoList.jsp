<%--===========================================================================
작업실행일정(주간) - 목록
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.list.action.MaWoResultMstrListAction" %>
<%@ page import="dream.work.list.action.MaWoResultMstrDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/functions.tld" prefix="udf"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="common.bean.User"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId());
%>
<html>
<head>
<!-- 작업실행일정(주간) -->
<title><bean:message key='PAGE.workListWeekWoList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<style>
	.tb_basic { border-collapse:collapse; border:none; margin:1px 2px; }
	.tb_basic th { background-color:#f2f2f2; border:1px solid #e6e6e6; border-top:none; padding:7px; font-weight:normal; }
	.tb_basic td { background-color:#ffffff; border:1px solid #f1f1f1; border-top:none;}
</style>
<script language="javascript">
<!--//
//그리드명
var selectedGrid;
var beforePageId = '';
const gridbox = [[], []];
var lang = loginUser.locale;

/** 자동완성 변수 */
var eqCtgTypeAc;
var plantAc;
function loadPage() 
{
	//오늘기준 이번주 일요일~토요일
// 	maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.filterStartDate'].value = getMinusDay2(monOfThisWeek(getToday()),1);
// 	maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.filterEndDate'].value = getMinusDay2(monOfThisWeek(getToday()),-5);
	maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.filterStartDate'].value = monOfThisWeek(getToday());
	
	maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.filterPlantId'].value = loginUser.filterPlant;
	maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.filterPlantDesc'].value = loginUser.filterPlantDesc;
	
    initGrid();
    
    eqCtgTypeAc = new autoC({"maWoResultMstrCommonDTO.filterEqCtgDesc":"full_desc"});
    eqCtgTypeAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqCtgTypeAc.setTable("TAEQCTG");
    eqCtgTypeAc.setAcResultMap({
        "maWoResultMstrCommonDTO.filterEqCtgId":"eqctg_id"
    });
    eqCtgTypeAc.init();
	
    plantAc = new autoC({"maWoResultMstrCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "maWoResultMstrCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
}

/**
 * 그리드 초기화
 */
function initGrid()
{
	for(var i=0; i<2; i++){
		for(var j=0; j<7; j++){
			gridbox[i][j] = new dhtmlXGridObject('gridbox'+i+j);
			gridbox[i][j].setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
			gridbox[i][j].enableAutoHeight(true);
			gridbox[i][j].attachEvent("onBeforeSelect",function(rowId, state){
				clearSelection();
				return true;
			});
			gridbox[i][j].attachEvent("onRowSelect",function(rowId, columnId){
				selectedGrid = this;
				goOpen(rowId);
			});
			gridbox[i][j].setNoHeader(true);
			gridbox[i][j].objBox.style.overflowX = "hidden";
			gridbox[i][j].setHeader("WKORID,WOTYPE,PMTYPE,PARAM,WKORDESC");
			gridbox[i][j].setColumnIds("WKORID,WOTYPE,PMTYPE,PARAM,WKORDESC");
			gridbox[i][j].setColTypes("ro,ro,ro,ro,ro");
			gridbox[i][j].setColumnHidden(0,true);
			gridbox[i][j].setColumnHidden(1,true);
			gridbox[i][j].setColumnHidden(2,true);
			gridbox[i][j].setColumnHidden(3,true);
			gridbox[i][j].setInitWidths("0,0,0,0");
			gridbox[i][j].init();
			
			isHeaderLoaded[currentPageId+"."+gridbox[i][j]] = "Y";
		}
	}
	goSearch();
}

function clearSelection()
{
	for(var i=0; i<2; i++){
		for(var j=0; j<7; j++){
			gridbox[i][j].clearSelection();
		}
	}
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList()
{
    var url = contextPath + "/workListWeekWoList.do";
    maWoResultMstrListForm.elements['strutsAction'].value = '<%=MaWoResultMstrListAction.WO_RESULT_LIST_FIND%>';
    
    for(var i=0; i<2; i++){
    	if(i==0){
	    	maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.filterWoStatus'].value = '!C';
    	}
    	else if(i==1){
	    	maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.filterWoStatus'].value = 'C';
    	}
		for(var j=0; j<7; j++){
			(function(i, j){
				if(checkValidation()) return;
				
				if(!(currentPageId+"."+gridbox[i][j] in isHeaderLoaded))
				{
					alertMessage1("Loading...");
					return;
				}
				
				var filterDate = $("#day"+j).text().substr(0,10);
				var param = FormQueryString(maWoResultMstrListForm);
				param = param.replace(/maWoResultMstrCommonDTO\.filterStartDate=(19|20)\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])/gi,"maWoResultMstrCommonDTO.filterStartDate="+filterDate);
				param = param.replace(/maWoResultMstrCommonDTO\.filterEndDate=(19|20)\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])/gi,"maWoResultMstrCommonDTO.filterEndDate="+filterDate);
				
				gridbox[i][j].clearAll();
				if(filterDate != ''){
					ajaxPost(url,param).done(function(_data){
						var res = _data.replace(/"ID"/gi, "id"); 
						var jsonObj = JSON.parse(_data);
						
						gridbox[i][j].parse(res,"js");
					});
				}
			})(i, j);
		}
    }
	closeModal();
}

function findGridRow()
{
	goSearch();
}

/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.filterEndDate'].value = getMinusDay2(maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.filterStartDate'].value,'-6');
	
	//validation
	if(checkRequireValue("maWoResultMstrCommonDTO.filterStartDate","<bean:message key='LABEL.date'/>")) return;
	if(checkRequireValue("maWoResultMstrCommonDTO.filterEndDate","<bean:message key='LABEL.date'/>")) return;
	if(checkTwoDate(maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.filterStartDate'], maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.filterEndDate'])) return;
	//검색 기간 제한 
	var days = getDayInterval(maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.filterStartDate'].value.replace(/\-/gi, ""),
			maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.filterEndDate'].value.replace(/\-/gi, ""));
	
	for(var i=0;i<=days;i++){
// 		$("#day"+i).text(getMinusDay2(maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.filterStartDate'].value,-1*i));
		$("#day"+i).text(getMinusDay2(maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.filterStartDate'].value,-1*i)
								+ '(' + setComDay(getMinusDay2(maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.filterStartDate'].value,-1*i), lang).substr(0,1) + ')'
				 			 );
// 		document.getElementById("day"+i).innerHTML(getMinusDay2(maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.filterStartDate'].value,-1*i)
// 								+ '(' + setComDay(getMinusDay2(maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.filterStartDate'].value,-1*i), lang).substr(0,1) + ')'
// 				 			 );
	}
	for(var i=days+1;i<=6;i++){
		$("#day"+i).text("");
	}
	
	maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.wkOrId'].value = "";	// 검색시 Tab 이동Key Clear
	findGridList();
}

/**
 * 상세 열기
 */
function goOpen(rowId)
{
	var woType = getValueById(selectedGrid, rowId,'WOTYPE');
	var pmType = getValueById(selectedGrid, rowId,'PMTYPE');
	var param  = getValueById(selectedGrid, rowId,'PARAM');
	
	maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.selectedWoType'].value = woType;
	maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.selectedPmType'].value = pmType;

	goTabPage(param);
}

/**
 * Tab 이동시 호출
 */
function goTabPage(pageId)
{
	tabValidationCheck(selectedGrid, pageId, "goTabPageAction");
}

function goTabPageAction(pageId, selectedId)
{
	var form = document.maWoResultMstrListForm;
	 
	form.elements['maWoResultMstrCommonDTO.wkOrId'].value = getValueById(selectedGrid, selectedId,'WKORID');
	goCommonTabPage(form, <%= MaWoResultMstrDetailAction.WO_RESULT_DETAIL_INIT %>, pageId,beforePageId);

	beforePageId = pageId;
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workListWeekWoList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maWoResultMstrCommonDTO.filterEndDate"/>
<html:hidden property="maWoResultMstrCommonDTO.filterEqCtgId"/>
<html:hidden property="maWoResultMstrCommonDTO.filterPlantId"/>
<html:hidden property="maWoResultMstrCommonDTO.filterWoStatus"/>
<html:hidden property="maWoResultMstrCommonDTO.wkOrId"/>
<html:hidden property="maWoResultMstrCommonDTO.selectedWoType"/>
<html:hidden property="maWoResultMstrCommonDTO.selectedPmType"/>
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
					<label class="check"><bean:message key="LABEL.date"/></label>
					<div class="input_box input_carendar">
						<html:text property="maWoResultMstrCommonDTO.filterStartDate" tabindex="10" />
						<p class="open_calendar"><span>날짜</span></p>
					</div>
				</div>
				<!-- 종류 -->
				<div class="field">
					<label><bean:message key="LABEL.type"/></label>
					<div class="input_box">
						<html:text property="maWoResultMstrCommonDTO.filterEqCtgDesc" tabindex="35" />
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 공장명  -->
                <div class="field">
                    <label><bean:message key="LABEL.plantDesc"/></label>
                    <div class="input_box">
							<html:text property="maWoResultMstrCommonDTO.filterPlantDesc" tabindex="160" />
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
		</div>
		<!--sheader_box end-->
		<div class="article_box">
			<div id="myGrid" class="tb_basic">
				<table width="100%" border="0" cellpadding="0" cellspacing="0;" >
					<tr>
						<th scope="col" style="width:4%;"><bean:message key="LABEL.seqNo"/></th>
						<th scope="col" style="width:5%;"><bean:message key="LABEL.separation"/></th>
						<th scope="col" style="width:13%;" id="day0"></th>
						<th scope="col" style="width:13%;" id="day1"></th>
						<th scope="col" style="width:13%;" id="day2"></th>
						<th scope="col" style="width:13%;" id="day3"></th>
						<th scope="col" style="width:13%;" id="day4"></th>
						<th scope="col" style="width:13%;" id="day5"></th>
						<th scope="col" style="width:13%;" id="day6"></th>
					</tr>
					
					<tr>
						<td style="text-align:center; min-height:200px;">1</div></td>
						<td style="text-align:center;"><bean:message key="LABEL.Incompleted"/></div></td>
						<td valign="top"><div id="gridbox00" style="width:100%; background-color:white;"></div></td>
						<td valign="top"><div id="gridbox01" style="width:100%; background-color:white;"></div></td>
						<td valign="top"><div id="gridbox02" style="width:100%; background-color:white;"></div></td>
						<td valign="top"><div id="gridbox03" style="width:100%; background-color:white;"></div></td>
						<td valign="top"><div id="gridbox04" style="width:100%; background-color:white;"></div></td>
						<td valign="top"><div id="gridbox05" style="width:100%; background-color:white;"></div></td>
						<td valign="top"><div id="gridbox06" style="width:100%; background-color:white;"></div></td>
					</tr>
					
					<tr>
						<td style="text-align:center; min-height:200px;">2</div></td>
						<td style="text-align:center;"><bean:message key="LABEL.completed"/></div></td>
						<td valign="top"><div id="gridbox10" style="width:100%; background-color:white;"></div></td>
						<td valign="top"><div id="gridbox11" style="width:100%; background-color:white;"></div></td>
						<td valign="top"><div id="gridbox12" style="width:100%; background-color:white;"></div></td>
						<td valign="top"><div id="gridbox13" style="width:100%; background-color:white;"></div></td>
						<td valign="top"><div id="gridbox14" style="width:100%; background-color:white;"></div></td>
						<td valign="top"><div id="gridbox15" style="width:100%; background-color:white;"></div></td>
						<td valign="top"><div id="gridbox16" style="width:100%; background-color:white;"></div></td>
					</tr>
				</table>
			</div>
		</div>
	<!--article_box-->
	</div><!--연간일정 end-->
</html:form> 
</body>
</html>