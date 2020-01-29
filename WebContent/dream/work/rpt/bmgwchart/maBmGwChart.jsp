<%--===========================================================================
과별고장분석
author  kim21017
version $Id: maBmGwChart.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.mabmgwchart.action.MaBmGwChartAction" %>
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
<!-- 과별고장분석 -->
<title><bean:message key='MENU.BMGWCHART'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//
//그리드명
var bmGrid;			//과별고장분석 그리드
var selectedInd;
var deptAc;
var equipDescAc;
var eqLocDescAc;
var eqCtgTypeAc;
var mainMngAc;
var subMngAc;
var plantAc;

function loadPage() 
{
	//년도 세팅
	maBmGwChartForm.elements['maBmGwChartDTO.filterYyyy'].value = dateToData(getToday()).substr(0, 4);

	//부서정보 세팅
	maBmGwChartForm.elements['maBmGwChartDTO.filterDeptId'].value    = "<%=user.getDeptId()%>";
	maBmGwChartForm.elements['maBmGwChartDTO.filterDeptDesc'].value  = "<%=user.getDeptDesc()%>";
	if(loginUser.eqLocId!='null'){
		maBmGwChartForm.elements['maBmGwChartDTO.filterEqLocId'].value = loginUser.eqLocId;
		maBmGwChartForm.elements['maBmGwChartDTO.filterEqLocDesc'].value = loginUser.eqLocDesc;
	}	
	//공장명
    if(loginUser.plant!='null'){
    	maBmGwChartForm.elements['maBmGwChartDTO.filterPlantId'].value  = loginUser.plant;
    	maBmGwChartForm.elements['maBmGwChartDTO.filterPlantDesc'].value  = loginUser.plantDesc;
	}	
	//그리드 초기화
	initGrid();
	
	goSearch();
	

    deptAc = new autoC({"maBmGwChartDTO.filterDeptDesc":"description"});
    deptAc.setAcDisplay("DESCRIPTION");
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "maBmGwChartDTO.filterDeptId":"dept_id"
    });
    deptAc.setAcDConditionMap({
    	"plant" : "maBmGwChartDTO.filterPlantId"
    });
    deptAc.init();
    
    equipDescAc = new autoC({"maBmGwChartDTO.filterEquipDesc":"description"});
    equipDescAc.setAcDisplay("DESCRIPTION");
    equipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcResultMap({
        "maBmGwChartDTO.filterEquipId":"equip_id"
    });
    equipDescAc.setAcDConditionMap({
    	"eqloc_id" : "maBmGwChartDTO.filterEqLocId",
    	"eqctg_id" : "maBmGwChartDTO.filterEqCtgId",
    	"dept_id" : "maBmGwChartDTO.filterDeptId",
    	"plant" : "maBmGwChartDTO.filterPlantId"
    });
    equipDescAc.init();
    
    eqLocDescAc = new autoC({"maBmGwChartDTO.filterEqLocDesc":"full_desc"});
    eqLocDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setAcResultMap({
        "maBmGwChartDTO.filterEqLocId":"eqloc_id"
    });
    eqLocDescAc.setAcDConditionMap({
    	"plant" : "maBmGwChartDTO.filterPlantId"
    });
    eqLocDescAc.init();
    
    eqCtgTypeAc = new autoC({"maBmGwChartDTO.filterEqCtgDesc":"description"});
    eqCtgTypeAc.setAcDisplay("DESCRIPTION");
    eqCtgTypeAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqCtgTypeAc.setTable("TAEQCTG");
    eqCtgTypeAc.setAcResultMap({
        "maBmGwChartDTO.filterEqCtgId":"eqctg_id"
    });
    eqCtgTypeAc.init();

    //법정설비여부 AC
    acSysDesc("maBmGwChartDTO.filterIsLawEq","maBmGwChartDTO.filterIsLawEq","IS_USE",true);
    
    mainMngAc = new autoC({"maBmGwChartDTO.filterMainMngName":"emp_name"});
    mainMngAc.setAcDisplay("EMP_NAME");
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcResultMap({
        "maBmGwChartDTO.filterMainMngId":"emp_id"
    });
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "maBmGwChartDTO.filterDeptId",
    	"plant" : "maBmGwChartDTO.filterPlantId"
    });
    mainMngAc.init();
    
    subMngAc = new autoC({"maBmGwChartDTO.filterSubMngName":"emp_name"});
    subMngAc.setAcDisplay("EMP_NAME");
    subMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    subMngAc.setTable("TAEMP");
    subMngAc.setAcResultMap({
        "maBmGwChartDTO.filterSubMngId":"emp_id"
    });
    subMngAc.setAcDConditionMap({
    	"dept_id" : "maBmGwChartDTO.filterDeptId",
    	"plant" : "maBmGwChartDTO.filterPlantId"
    });
    subMngAc.init();
    
 	// 공장코드
	plantAc = new autoC({"maBmGwChartDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "maBmGwChartDTO.filterPlantId":"plant"
    });
    plantAc.init();
}
/**
 * 그리드 초기화
 */
function initGrid()
{
    bmGrid = new dhtmlXGridObject('gridbox');
    bmGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	bmGrid.setHeader("DEPTID,<bean:message key='LABEL.deptOg'/>,<bean:message key='LABEL.month1'/>,#cspan"
														+",<bean:message key='LABEL.month2'/>,#cspan"
														+",<bean:message key='LABEL.month3'/>,#cspan"
														+",<bean:message key='LABEL.month4'/>,#cspan"
														+",<bean:message key='LABEL.month5'/>,#cspan"
														+",<bean:message key='LABEL.month6'/>,#cspan"
														+",<bean:message key='LABEL.month7'/>,#cspan"
														+",<bean:message key='LABEL.month8'/>,#cspan"
														+",<bean:message key='LABEL.month9'/>,#cspan"
														+",<bean:message key='LABEL.month10'/>,#cspan"
														+",<bean:message key='LABEL.month11'/>,#cspan"
														+",<bean:message key='LABEL.month12'/>,#cspan"
														+",<bean:message key='LABEL.total2'/>,#cspan"
					);
	bmGrid.attachHeader(["DEPTID","#rspan","<bean:message key='LABEL.cnt2'/>","<bean:message key='LABEL.time'/>"
			                              ,"<bean:message key='LABEL.cnt2'/>","<bean:message key='LABEL.time'/>"
			                              ,"<bean:message key='LABEL.cnt2'/>","<bean:message key='LABEL.time'/>"
			                              ,"<bean:message key='LABEL.cnt2'/>","<bean:message key='LABEL.time'/>"
			                              ,"<bean:message key='LABEL.cnt2'/>","<bean:message key='LABEL.time'/>"
			                              ,"<bean:message key='LABEL.cnt2'/>","<bean:message key='LABEL.time'/>"
			                              ,"<bean:message key='LABEL.cnt2'/>","<bean:message key='LABEL.time'/>"
			                              ,"<bean:message key='LABEL.cnt2'/>","<bean:message key='LABEL.time'/>"
			                              ,"<bean:message key='LABEL.cnt2'/>","<bean:message key='LABEL.time'/>"
			                              ,"<bean:message key='LABEL.cnt2'/>","<bean:message key='LABEL.time'/>"
			                              ,"<bean:message key='LABEL.cnt2'/>","<bean:message key='LABEL.time'/>"
			                              ,"<bean:message key='LABEL.cnt2'/>","<bean:message key='LABEL.time'/>"
			                              ,"<bean:message key='LABEL.cnt2'/>","<bean:message key='LABEL.time'/>"
			            ]);
	bmGrid.setColumnIds("DEPTID,DEPTDESC,CNT01,TIME01"
									  +",CNT02,TIME02"
									  +",CNT03,TIME03"
									  +",CNT04,TIME04"
									  +",CNT05,TIME05"
									  +",CNT06,TIME06"
									  +",CNT07,TIME07"
									  +",CNT08,TIME08"
									  +",CNT09,TIME09"
									  +",CNT10,TIME10"
									  +",CNT11,TIME11"
									  +",CNT12,TIME12"
									  +",CNT,TIME"
						);
	bmGrid.setInitWidths("100,110"+getWords(26,"43"));
	bmGrid.setColAlign("left,left"+getWords(26,"center"));
	bmGrid.setColTypes("ro,ro"+getWords(26,"ro"));
	bmGrid.setColumnHidden(0,true);
	bmGrid.attachEvent("onXLE",function(grdObj,count){setCounter(grdObj,"gridbox")});
	bmGrid.attachEvent("onRowSelect", function(id,ind){
		selectedId = id;
		selectedInd = ind;
		goOpen();
	});
	bmGrid.attachEvent("onRowDblClicked", function(id,ind){
		goWoList(id,ind);
	});
	bmGrid.init();
}

/**
* Tab 이동시 호출
*/
function goTabPage(pageId)
{
   tabValidationCheck(bmGrid, pageId, "goTabPageAction");
}

function goTabPageAction(pageId, selectedId)
{
	var form = document.maBmGwChartForm;
	
	form.elements['maBmGwChartDTO.deptId'].value = bmGrid.cells(selectedId, bmGrid.getColIndexById("DEPTID")).getValue();
	form.elements['maBmGwChartDTO.deptDesc'].value = bmGrid.cells(selectedId, bmGrid.getColIndexById("DEPTDESC")).getValue();
	
	goCommonTabPage(form, '' , pageId);
}

/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('maBmGwChartDetail');
}

function goWo(){
	goWoList(selectedId, selectedInd);
}

function goWoList(id, ind){
	if(typeof id=="undefined"||typeof ind=="undefined") return ;
	
	//클릭한 컬럼의 일자.
	var yyyy = maBmGwChartForm.elements['maBmGwChartDTO.yyyy'].value;
	var mm = bmGrid.getColumnId(ind).substring(bmGrid.getColumnId(ind).length-2,bmGrid.getColumnId(ind).length);
	var fromYyyyMmDd = yyyy+mm+"01";
	var toYyyyMmDd = yyyy+mm+"31";
	//클릭한 row의 부서정보
	var deptId = bmGrid.cells(id, bmGrid.getColIndexById("DEPTID")).getValue();
	var deptDesc = bmGrid.cells(id, bmGrid.getColIndexById("DEPTDESC")).getValue();
	if(deptId == ""){
		deptId = maBmGwChartForm.elements['maBmGwChartDTO.filterDeptId'].value;;
		deptDesc = maBmGwChartForm.elements['maBmGwChartDTO.filterDeptDesc'].value;;
	}
	//25이상의 컬럼은 합계 컬럼.
	if(ind>25||ind<2){
		fromYyyyMmDd = yyyy+"0101";
		toYyyyMmDd = yyyy+"1231";
	}
	//팝업사이즈
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
				"&maWoResultMstrCommonDTO.filterStartDate="+fromYyyyMmDd+
				"&maWoResultMstrCommonDTO.filterEndDate="+toYyyyMmDd+
				"&maWoResultMstrCommonDTO.filterWoTypeId=BM"+
				"&maWoResultMstrCommonDTO.filterWoStatus=C"+
				"&maWoResultMstrCommonDTO.filterDeptId="+deptId+
				"&maWoResultMstrCommonDTO.filterDeptDesc="+deptDesc;
	//post 전송
	openWindowWithPost(url, "CHART_WO_LIST_POPUP", param, pos);
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 과별고장현황 그리드에 셋팅한다.
 */
function findBmGridList()
{
    var url = contextPath + "/maBmGwChart.do";
    maBmGwChartForm.elements['strutsAction'].value = '<%=MaBmGwChartAction.BM_LIST_FIND%>';

    bmGrid.clearAll();
    setLoading("gridbox");
    $.post(url,FormQueryString(maBmGwChartForm), function(_data){
    	bmGrid.parse(_data,"js");
    });
}

/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	//validation
	if(checkRequireValue("maBmGwChartDTO.filterYyyy","<bean:message key='LABEL.year'/>")) return;
// 	if(checkRequireValue("maBmGwChartDTO.filterDeptDesc","<bean:message key='LABEL.dept'/>")) return;
	
	maBmGwChartForm.elements['maBmGwChartDTO.yyyy'].value = maBmGwChartForm.elements['maBmGwChartDTO.filterYyyy'].value;
	maBmGwChartForm.elements['maBmGwChartDTO.deptId'].value = maBmGwChartForm.elements['maBmGwChartDTO.filterDeptId'].value;
	findBmGridList();
}

/**
 *  엑셀 다운
 */
 function goExcel()
 {
 	excelAction(bmGrid);
 } 

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maBmGwChart.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maBmGwChartDTO.filterDeptId"/>
<html:hidden property="maBmGwChartDTO.yyyy"/>
<html:hidden property="maBmGwChartDTO.deptId"/>
<html:hidden property="maBmGwChartDTO.deptDesc"/>
<html:hidden property="maBmGwChartDTO.filterMainMngId"/>
<html:hidden property="maBmGwChartDTO.filterSubMngId"/>
<html:hidden property="maBmGwChartDTO.filterEqLocId"/>
<html:hidden property="maBmGwChartDTO.filterEqCtgId"/>
<html:hidden property="maBmGwChartDTO.filterEquipId"/>
<html:hidden property="maBmGwChartDTO.filterPlantId"/>
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
				<!-- 년 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.year"/></label>
					<div class="input_read">
						<html:text property="maBmGwChartDTO.filterYyyy" tabindex="10" readonly="true"/>
						<p class="open_year_calendar"><span>날짜</span></p>
					</div>
				</div>
				<!-- 부서 -->
				<div class="field">
					<label><bean:message key="LABEL.dept"/></label>
					<div class="input_box">
						<html:text property="maBmGwChartDTO.filterDeptDesc" tabindex="20" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 위치 -->
				<div class="field">
					<label><bean:message key="LABEL.location"/></label>
					<div class="input_box">
						<html:text property="maBmGwChartDTO.filterEqLocDesc" tabindex="30"/>
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
						<html:text property="maBmGwChartDTO.filterEqCtgDesc" tabindex="40" />
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 설비-->
				<div class="field">
					<label><bean:message key="LABEL.equipDesc"/></label>
					<div class="input_box">
						<html:text property="maBmGwChartDTO.filterEquipDesc" tabindex="50" />
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
						<html:text property="maBmGwChartDTO.filterMainMngName" tabindex="60" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 관리자(부) -->
				<div class="field">
					<label><bean:message key="LABEL.subManager"/></label>
					<div class="input_box">
						<html:text property="maBmGwChartDTO.filterSubMngName" tabindex="70" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 법정설비여부 -->
				<div class="field">
					<label><bean:message key="LABEL.isLawEq"/></label>
					<div class="input_box">
						<html:text property="maBmGwChartDTO.filterIsLawEq" tabindex="80" />
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
							<html:text property="maBmGwChartDTO.filterPlantDesc"
								tabindex="90" />
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
				<div class="b_line"></div> 
				<div class="fb_group1">
					 
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