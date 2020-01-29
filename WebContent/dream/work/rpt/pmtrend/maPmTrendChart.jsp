<%--===========================================================================
예방점검경향분석
author  kim21017
version $Id: maPmTrendChart.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.mapmtrend.action.MaPmTrendChartAction" %>
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
<!-- 예방점검경향분석 -->
<title><bean:message key='MENU.PMTREND'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//
//그리드명
var pmGrid;         //예방점검경향분석 그리드
var pmChart;        //예방점검경향분석 차트
var isChartLoading = false;

/** 자동완성 변수 */
var eqLocDescAc;
var eqCtgTypeAc;
var deptAc;
var mainMngAc;
var equipDescAc;
var equipAc;
var subMngAc;
var empAc;
var plantAc;

var selected = false;

function loadPage() 
{
    //차트 일자 세팅
    maPmTrendChartForm.elements['maPmTrendChartDTO.filterStartDate'].value = getMinusDay(30);
    maPmTrendChartForm.elements['maPmTrendChartDTO.filterEndDate'].value = getToday();
    
    //부서정보 세팅
    maPmTrendChartForm.elements['maPmTrendChartDTO.filterDeptId'].value    = "<%=user.getDeptId()%>";
    maPmTrendChartForm.elements['maPmTrendChartDTO.filterDeptDesc'].value  = "<%=user.getDeptDesc()%>";
    if(loginUser.eqLocId!='null'){
        maPmTrendChartForm.elements['maPmTrendChartDTO.filterEqLocId'].value = loginUser.eqLocId;
        maPmTrendChartForm.elements['maPmTrendChartDTO.filterEqLocDesc'].value = loginUser.eqLocDesc;
    }
	
	//공장명
    if(loginUser.plant!='null'){
    	maPmTrendChartForm.elements['maPmTrendChartDTO.filterPlantId'].value  = loginUser.plant;
    	maPmTrendChartForm.elements['maPmTrendChartDTO.filterPlantDesc'].value  = loginUser.plantDesc;
	}
    
    //그리드 초기화
    initGrid();
    //차트 초기화
    initChart();
    
    eqLocDescAc = new autoC({"maPmTrendChartDTO.filterEqLocDesc":"full_desc"});
    eqLocDescAc.setAcDisplay("DESCRIPTION");
    eqLocDescAc.setAcConditionMap({
       "comp_no":loginUser.compNo
       });
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setAcResultMap({
        "maPmTrendChartDTO.filterEqLocId":"eqloc_id"
    });
    eqLocDescAc.setAcDConditionMap({
    	"plant" : "maPmTrendChartDTO.filterPlantId"
    });
    eqLocDescAc.init();
    
    eqCtgTypeAc = new autoC({"maPmTrendChartDTO.filterEqCtgDesc":"full_desc"});
    eqCtgTypeAc.setAcConditionMap({
       "comp_no":loginUser.compNo
       });
    eqCtgTypeAc.setTable("TAEQCTG");
    eqCtgTypeAc.setAcResultMap({
        "maPmTrendChartDTO.filterEqCtgId":"eqctg_id"
    });
    eqCtgTypeAc.init();
    
    deptAc = new autoC({"maPmTrendChartDTO.filterDeptDesc":"description"});
    deptAc.setAcDisplay("DESCRIPTION");
    deptAc.setAcConditionMap({
        "comp_no":loginUser.compNo
       });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "maPmTrendChartDTO.filterDeptId":"dept_id"
    });
    deptAc.setAcDConditionMap({
    	"plant" : "maPmTrendChartDTO.filterPlantId"
    });
    deptAc.init();

    //법정설비 여부 AC
    acSysDesc("maPmTrendChartDTO.filterIsLawEq","maPmTrendChartDTO.filterIsLawEq","IS_USE",true);
    
    mainMngAc = new autoC({"maPmTrendChartDTO.filterMainMngName":"emp_name"});
    mainMngAc.setAcDisplay("EMP_NAME");
    mainMngAc.setAcConditionMap({
        "comp_no":loginUser.compNo
       });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcResultMap({
        "maPmTrendChartDTO.filterMainMngId":"emp_id"
    });
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "maPmTrendChartDTO.filterDeptId",
    	"plant" : "maPmTrendChartDTO.filterPlantId"
    });
    mainMngAc.init();
    
    subMngAc = new autoC({"maPmTrendChartDTO.filterSubMngName":"emp_name"});
    subMngAc.setAcDisplay("EMP_NAME");
    subMngAc.setAcConditionMap({
        "comp_no":loginUser.compNo
       });
    subMngAc.setTable("TAEMP");
    subMngAc.setAcResultMap({
        "maPmTrendChartDTO.filterSubMngId":"emp_id"
    });
    subMngAc.setAcDConditionMap({
    	"dept_id" : "maPmTrendChartDTO.filterDeptId",
    	"plant" : "maPmTrendChartDTO.filterPlantId"
    });
    subMngAc.init();
    
    // 설비 자동완성 
    equipAc = new autoC({"maPmTrendChartDTO.equipDesc":"description"});
    equipAc.setTable("TAEQUIPMENT");
    equipAc.setKeyName("maPmTrendChartDTO.equipId");
    equipAc.setAcResultMap({
        "maPmTrendChartDTO.eqLocDesc":"eqLocDesc",
        "maPmTrendChartDTO.equipNo":"item_no",
        "maPmTrendChartDTO.equipId":"equip_id"
    });
    equipAc.init();
    
    equipDescAc = new autoC({"maPmTrendChartDTO.filterEquipDesc":"description"});
    equipDescAc.setAcConditionMap({
       "comp_no":loginUser.compNo
       });
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcDConditionMap({
    	"eqloc_id" : "maPmTrendChartDTO.filterEqLocId",
    	"eqctg_id" : "maPmTrendChartDTO.filterEqCtgId",
    	"dept_id" : "maPmTrendChartDTO.filterDeptId",
    	"plant" : "maPmTrendChartDTO.filterPlantId"
    });
    equipDescAc.init();
    
    // 설비코드
    
    
    // 담당자
    empAc = new autoC({"maPmTrendChartDTO.filterEmpDesc":"emp_name"});
    empAc.setAcDisplay("EMP_NAME");
    empAc.setAcConditionMap({
        "comp_no":loginUser.compNo
       });
    empAc.setTable("TAEMP");
    empAc.setAcResultMap({
        "maPmTrendChartDTO.filterEmpId":"emp_id"
    });
    empAc.setAcDConditionMap({
    	"dept_id" : "maPmTrendChartDTO.filterDeptId",
    	"plant" : "maPmTrendChartDTO.filterPlantId"
    });
    empAc.init();
    
 	// 공장코드
	plantAc = new autoC({"maPmTrendChartDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "maPmTrendChartDTO.filterPlantId":"plant"
    });
    plantAc.init();
    
	// 내/외자
    acSysDesc("maPmTrendChartDTO.filterPlfTypeDesc","maPmTrendChartDTO.filterPlfTypeId","PLF_TYPE");

}
/**
 * 그리드 초기화
 */
function initGrid()
{
    pmGrid = new dhtmlXGridObject('gridbox');
    pmGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
    
    pmGrid.enableSmartRendering(true,500); 
    pmGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	maPmTrendChartForm.elements['maPmTrendChartDTO.pmPointId'].value = "";
        return sortColumn("maPmTrendChart", this, maPmTrendChartForm, "pmPointId", ind, direction);
    });
    pmGrid.attachEvent("onRowSelect",function(id, ind){
        selectedId=id;
        if(!isChartLoading){
            isChartLoading = true;
            drawChart(id,ind);
        }
    });
    pmGrid.attachEvent("onXLE",function(grdObj,count){setCounter(grdObj,"gridbox")}); pmGrid.init();
    setHeader(pmGrid, "gridbox"); // grid, grid id
}

function initChart(){
    pmChart =  new dhtmlXChart({
        view:"line",
        container:"chartbox",
        value:"#CNT#",
        tooltip:{
            template:"#CNT#"
        },
        item:{
            borderColor: "#FF6633",
            color: "#ffffff"
        },
        line:{
            color:"#FF6633",
            width:2
        },
        xAxis:{
            template:"#DAYS#"
        },
        offset:0,
        yAxis:{
            start:0,
            step:1
        },
        origin:0,
        legend:{
            layout:"x",
            width: 55,
            align:"right",
            toggle:false,
            valign:"bottom",
            values:[
                {text:"<bean:message key='LABEL.resultVal'/>",color:"#FF7171"},
                {text:"<bean:message key='LABEL.checkMin'/>",color:"#99ccff"},
                {text:"<bean:message key='LABEL.checkMax'/>",color:"#99ccff"}
            ],
            margin: 5
        }
    });
    pmChart.addSeries({
        value:"#MINVAL#",
        tooltip:{
            template:"#MINVAL#"
        },item:{
            borderColor: "#99ccff",
            color: "#ffffff"
        },
        line:{
            color:"#99ccff",
            width:1
        }
    });
    pmChart.addSeries({
        value:"#MAXVAL#",
        tooltip:{
            template:"#MAXVAL#"
        },item:{
            borderColor: "#99ccff",
            color: "#ffffff"
        },
        line:{
            color:"#99ccff",
            width:1
        }
    });
}

function drawChart(id, ind){
    //차트 title설정
    document.getElementById("chartDiv").innerText = 
        "<bean:message key='LABEL.checkPoint'/>"+": "+pmGrid.cells(id, pmGrid.getColIndexById("EQUIPDESC")).getValue()
        +", "+pmGrid.cells(id, pmGrid.getColIndexById("ASMBPOINT")).getValue()
        +" "+ "<bean:message key='LABEL.period'/>"+": "
        +maPmTrendChartForm.elements['maPmTrendChartDTO.filterStartDate'].value+" ~ "
        +maPmTrendChartForm.elements['maPmTrendChartDTO.filterEndDate'].value;
    
        maPmTrendChartForm.elements['maPmTrendChartDTO.pmPointId'].value = pmGrid.cells(id, pmGrid.getColIndexById("PMPOINTID")).getValue();
        maPmTrendChartForm.elements['maPmTrendChartDTO.equipId'].value = pmGrid.cells(id, pmGrid.getColIndexById("EQUIPID")).getValue();
    
    findBmChart();
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 예방점검경향분석 그리드에 셋팅한다.
 */
function findBmGridList(sheetAction)
{
	if(checkValidation()) return;
	
	var url = contextPath + "/maPmTrendChart.do";
    maPmTrendChartForm.elements['strutsAction'].value = '<%=MaPmTrendChartAction.PMTREND_LIST_FIND%>';
    
	doGridAction(sheetAction, pmGrid, "gridbox", url, FormQueryString(maPmTrendChartForm), "pmPoinId", "Y");
}

$(window).resize(function(){
    pmChart.resize();
});

/**
 * 현재 셋팅된 조건으로 값을 조회하여 예방점검경향분석 차트에 셋팅한다.
 */
function findBmChart()
{
    var url = contextPath + "/maPmTrendChart.do";
    
    var pmType = getValueById(pmGrid, selectedId, "pmType");
    
    maPmTrendChartForm.elements['maPmTrendChartDTO.pmType'].value = pmType;
    
    maPmTrendChartForm.elements['strutsAction'].value = '<%=MaPmTrendChartAction.PMTREND_CHART_FIND%>';

    pmChart.clearAll(); 
    
    $.post(url,FormQueryString(maPmTrendChartForm), function(_data){
        pmChart.parse(_data,"json");
        isChartLoading = false;
    });
}

/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
    //차트 title 변경
    document.getElementById("chartDiv").innerText = "Chart";
    if(pmChart!=null)
        pmChart.clearAll(); 
    
    findBmGridList();
}

/**
 *  엑셀 다운
 */
 function goExcel()
 {
	 excelServerAction("maPmTrendChart", maPmTrendChartForm);
 } 


 /**
  *   W/O 열기
  */
/*  function goWo(){
     
     var id = "";
     
     id = maPmTrendChartForm.elements['maPmTrendChartDTO.wkorId'].value;
     
     alert(id);
     
//     if(selectedId == "undefined" || selectedId == "") return;
     var wkorId = id;
     var pmType = maPmTrendChartForm.elements['maPmTrendChartDTO.pmType'].value;
     var woType = maPmTrendChartForm.elements['maPmTrendChartDTO.woType'].value;
     var woparam = maPmTrendChartForm.elements['maPmTrendChartDTO.woParam'].value;
     
     alert(wkorId + ', ' +pmType + ', ' + woType + ', ' + woparam);

//     var woparam = "maWoResultPmRprMstrDetail";

     if(wkOrId == "undefined" || wkOrId == ""
         || pmType == "undefined" || pmType == ""
         || woparam == "undefined" || woparam == "")
     {
         alertMessage1('<bean:message key="MESSAGE.MSG0024"/>');
         return;
     }

     pmType = pmType.substring(0,1)+pmType.substring(1,pmType.length).toLowerCase();
     
     var url   = contextPath + "/"+woparam+".do";

     var popWidth = 1010;
     var popHeight = 640;

     // pop up이 중앙에 위치하게 한다.
     var TopPosition  = (screen.height/2 - popHeight/2);
     var LeftPosition = (screen.width/2 - popWidth/2);

     var pos = "width=" + popWidth + ",height=" + popHeight + "" +
               ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
     pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
     
     var param = "strutsAction=1001&maWoResultMstrCommonDTO.wkOrId="+ wkorId+"&maWoResultMstrDetailDTO.equipId="+equipId;
   
     openWindowWithPost(url, "WO_DETAIL", param, pos);
    
 } */

 function goSelect(){
        var param = "strutsAction=1001";
        var url =  contextPath + "/maWoResultSelect.do";
            
        openLayerPopup("maWoResultSelect", param);
    }

    function setAfterSelect(returnArray){

        var woType = returnArray[0];
        var pmType = returnArray[1];
        var _pageId  = returnArray[2];

        var ifm = getIframeContent();
        
        maPmTrendChartForm.elements['maPmTrendChartDTO.selectedWoType'].value = woType;
        maPmTrendChartForm.elements['maPmTrendChartDTO.selectedPmType'].value = pmType;
        
        selected = true;

        goWoex();
    }
 
 
 /**
  * 조치 WO 발행
  */
  function goWoex(){
      
      if (selected == false)
     {
         // 선택
         goSelect();
     }
     else
     {
     
     getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0028'/>", function(result){
          if(result){
              
               maPmTrendChartForm.strutsAction.value = '<%=MaPmTrendChartAction.WO_CREATE%>';
              var url = contextPath + "/maPmTrendChart.do";
              $.post(url,FormQueryString(maPmTrendChartForm), function(_data){
                  afterWoex();
              });
          }
      });
     } 
 }

  function afterWoex(){
      goClose('maBdPointDetail');
      alertMessage1('<bean:message key="MESSAGE.MSG0027"/>');
      goSearch();
      selected=false;
  }
  
  
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maPmTrendChart.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maPmTrendChartDTO.pmPointId"/>
<html:hidden property="maPmTrendChartDTO.filterEqLocId"/>
<html:hidden property="maPmTrendChartDTO.filterEqCtgId"/>
<html:hidden property="maPmTrendChartDTO.filterPlfTypeId"/>
<html:hidden property="maPmTrendChartDTO.filterDeptId"/>
<html:hidden property="maPmTrendChartDTO.filterMainMngId"/>
<html:hidden property="maPmTrendChartDTO.filterSubMngId"/>
<html:hidden property="maPmTrendChartDTO.filterEmpId"/>
<html:hidden property="maPmTrendChartDTO.filterPlantId"/>

<html:hidden property="maPmTrendChartDTO.pmType"/>
<html:hidden property="maPmTrendChartDTO.equipId"/>
<html:hidden property="maPmTrendChartDTO.selectedWoType"/>
<html:hidden property="maPmTrendChartDTO.selectedPmType"/>
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
                <!-- 설비명 -->
                <div class="field">
                    <label><bean:message key="LABEL.equipName"/></label>
                    <div class="input_box">
                        <html:text property="maPmTrendChartDTO.filterEquipDesc" tabindex="10"/>
                    </div>
                </div>
                <!-- 설비번호 -->
                <div class="field">
                    <label><bean:message key="LABEL.equipNo"/></label>
                    <div class="input_box">
                        <html:text property="maPmTrendChartDTO.filterEquipNo" tabindex="45"/>
                    </div>
                </div>                
                <!-- 위치 -->
                <div class="field">
                    <label><bean:message key="LABEL.location"/></label>
                    <div class="input_box">
                        <html:text property="maPmTrendChartDTO.filterEqLocDesc" tabindex="20" />
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
                        <html:text property="maPmTrendChartDTO.filterEqCtgDesc" tabindex="30" />
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
						<html:text property="maPmTrendChartDTO.filterPlfTypeDesc" tabindex="40" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
                <!-- 관리부서 -->
                <div class="field">
                    <label><bean:message key="LABEL.mngDept"/></label>
                    <div class="input_box">
                        <html:text property="maPmTrendChartDTO.filterDeptDesc" tabindex="50" />
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
                <!-- 담당자 -->
                <div class="field">
                    <label><bean:message key="LABEL.manager"/></label>
                    <div class="input_box">
                        <html:text property="maPmTrendChartDTO.filterEmpDesc" tabindex="55" />
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
                
                <!-- 법정설비여부 -->
                <div class="field">
                    <label><bean:message key="LABEL.isLawEq"/></label>
                    <div class="input_box">
                        <html:text property="maPmTrendChartDTO.filterIsLawEq" tabindex="60" />
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
                        <html:text property="maPmTrendChartDTO.filterMainMngName" tabindex="70" />
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
                <!-- 제작사 -->
                <div class="field">
                    <label><bean:message key="LABEL.maker"/></label>
                    <div class="input_box">
                        <html:text property="maPmTrendChartDTO.filterMaker" tabindex="80"/>
                    </div>
                </div>
                <!-- 관리자(부) -->
                <div class="field">
                    <label><bean:message key="LABEL.subManager"/></label>
                    <div class="input_box">
                        <html:text property="maPmTrendChartDTO.filterSubMngName" tabindex="90" />
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
                <!-- 모델 -->
                <div class="field">
                    <label><bean:message key="LABEL.model"/></label>
                    <div class="input_box">
                        <html:text property="maPmTrendChartDTO.filterModelNo" tabindex="100"/>
                    </div>
                </div>
                <!-- 점검일자 -->
                <div class="field">
                    <label><bean:message key="LABEL.inspectDate"/></label>
                    <div class="calendar_wrap">
                        <div class="input_box input_carendar">
                            <html:text property="maPmTrendChartDTO.filterStartDate" tabindex="110" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
                        <div class="input_box input_carendar">
                            <html:text property="maPmTrendChartDTO.filterEndDate" tabindex="120" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
                    </div>
                </div>
                <!-- 공장명  -->
                <div class="field">
                    <label><bean:message key="LABEL.plantDesc"/></label>
                    <div class="input_box">
							<html:text property="maPmTrendChartDTO.filterPlantDesc"
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
            </div>
        </div><!--sheader_box end-->
        <div class="article_box">
            <div class="grid_area">
                <div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
            </div>
        </div>
    </div> <!--  End of section_wrap -->
    <div class="section_wrap">
        <div class="sheader_box">
            <div class="sheader_wrap"><a></a></div>
            <div class="stitle_wrap">
                <div class="stitle_icon"><a></a></div>
                <div class="stitle_tx" id="chartDiv"><bean:message key="LABEL.chart"/></div>
            </div>
            <div class="function_box">
                <div class="fb_group3">
                    <div class="sfb_wrap" style="display:none;">
                    </div>
                </div>

                <div class="fb_group2">
                    <a href="javascript:goWoex();" class="b_woex"><span><bean:message key="BUTTON.BDWO"/></span></a>
                </div>
            </div>
        </div><!--sheader_box end-->
        <div class="article_box">
            <div class="grid_area">
                <div id="chartbox" style="width:100%;height:270px;background-color:white;"></div>
            </div>
        </div>
    </div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>