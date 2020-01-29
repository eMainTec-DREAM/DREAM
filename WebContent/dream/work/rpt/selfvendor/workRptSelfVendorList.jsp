<%--===========================================================================
사내, 외주 작업 현황 Report
author  js.lee
version $Id:$ 
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.selfvendor.action.WorkRptSelfVendorListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 사내,외주작업현황 -->
<title><bean:message key='MENU.SELFVENDOR'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;
var plantAc;
var deptAc;

function loadPage() 
{
	//공장
    if(loginUser.filterPlant!='null'){
    	workRptSelfVendorListForm.elements['workRptSelfVendorCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
    	workRptSelfVendorListForm.elements['workRptSelfVendorCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
	}
	//부서
    if(loginUser.filterDeptId!='null'){
    	workRptSelfVendorListForm.elements['workRptSelfVendorCommonDTO.filterDeptId'].value  = loginUser.filterDeptId;
    	workRptSelfVendorListForm.elements['workRptSelfVendorCommonDTO.filterDeptDesc'].value  = loginUser.filterDeptDesc;
	}
	
	//년도 
	workRptSelfVendorListForm.elements['workRptSelfVendorCommonDTO.filterStartDate'].value = getMinusMonth(-6);
    workRptSelfVendorListForm.elements['workRptSelfVendorCommonDTO.filterEndDate'].value = getMinusMonth(0);
    
    //공장
    plantAc = new autoC({"workRptSelfVendorCommonDTO.filterPlantDesc":"description"});
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    })
    plantAc.setTable("TAPLANT");
    plantAc.setAcResultMap({
        "workRptSelfVendorCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
    
	//부서
    deptAc = new autoC({"workRptSelfVendorCommonDTO.filterDeptDesc":"description"});
    deptAc.setTable("TADEPT");
    deptAc.setAcConditionMap({
  	   "comp_no":loginUser.compNo
  	   });
    deptAc.setAcResultMap({
        "workRptSelfVendorCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.setAcDConditionMap({
    	"plant" : "workRptSelfVendorCommonDTO.filterPlantId"
    });
    deptAc.init();
    
	initGrid();
	
	goSearch();
	
}
/**
 * 그리드 초기화
 */
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	myGrid.setHeader("<bean:message key='LABEL.seqNo'/>,<bean:message key='LABEL.month'/>"
					+",<bean:message key='LABEL.selfWo'/>,#cspan"
					+",<bean:message key='LABEL.vendorWo'/>,#cspan,#cspan"
					+",<bean:message key='LABEL.unitWo'/>,#cspan,#cspan"
					+",<bean:message key='LABEL.total'/>,#cspan");
	myGrid.attachHeader(["#rspan","#rspan"
	                     ,"<bean:message key='LABEL.cnt2'/>","<bean:message key='LABEL.rate'/>"
	                     ,"<bean:message key='LABEL.cnt2'/>","<bean:message key='LABEL.rate'/>","<bean:message key='LABEL.amt'/>"
	                     ,"<bean:message key='LABEL.cnt2'/>","<bean:message key='LABEL.rate'/>","<bean:message key='LABEL.amt'/>"
	                     ,"<bean:message key='LABEL.cnt2'/>","<bean:message key='LABEL.amt'/>"
			            ]);
	myGrid.setColumnIds("SEQNO,YYYYMM,SELFCNT,SELFRATE,VENDORCNT,VENDORRATE,VENDORAMT"
					  +",UNITCNT,UNITRATE,UNITAMT,TOTCNT,TOTAMT"
						);
	myGrid.setInitWidths("60,100,80,100,80,100,120,80,100,120,80,120");
	myGrid.setColAlign("center,center,right,center,right,center,right,right,center,right,right,right");
	myGrid.setColTypes("cntr,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro");
	myGrid.attachEvent("onXLE",function(grdObj,count){setCounter(grdObj,"gridbox")});
	myGrid.init();
	
	isHeaderLoaded[currentPageId+".gridbox"] = "Y";
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/workRptSelfVendorList.do";
    workRptSelfVendorListForm.elements['strutsAction'].value = '<%=WorkRptSelfVendorListAction.LIST_FIND%>';
    
    myGrid.clearAll(); 
    setLoading("gridbox");
    
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workRptSelfVendorListForm), "");
    
}

function goSearch()
{
	if(checkRequireValue("workRptSelfVendorCommonDTO.filterStartDate","<bean:message key='LABEL.month'/>")) return;
    if(checkRequireValue("workRptSelfVendorCommonDTO.filterEndDate","<bean:message key='LABEL.month'/>")) return;
	
    if(checkValidation()) return;
    
    findGridList('Search');
}


/**
 * Excel Export
 */
function goExcel()
{
	if(checkValidation()) return;
	
   excelServerAction("workRptSelfVendorList", workRptSelfVendorListForm);
}

//-->

</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workRptSelfVendorList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="workRptSelfVendorCommonDTO.filterPlantId"/>
<html:hidden property="workRptSelfVendorCommonDTO.filterDeptId"/>

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
                <!-- 월 -->
                <div class="field">
                    <label class="check"><bean:message key="LABEL.month"/></label>
                    <div class="calendar_wrap">
                        <div class="input_box input_carendar">
                            <html:text property="workRptSelfVendorCommonDTO.filterStartDate" tabindex="10" />
                            <p class="open_mon_calendar"><span>날짜</span></p>
                        </div>
                        <div class="input_box input_carendar">
                            <html:text property="workRptSelfVendorCommonDTO.filterEndDate" tabindex="20" />
                            <p class="open_mon_calendar"><span>날짜</span></p>
                        </div>
                    </div>
                </div>
                <!-- 공장 -->
                <div class="field">
                    <label><bean:message key="LABEL.plant"/></label>
                    <div class="input_box">
                        <html:text property="workRptSelfVendorCommonDTO.filterPlantDesc" tabindex="30" />
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 부서  -->
				<div class="field">
					<label><bean:message key="LABEL.dept"/></label>
					<div class="input_box">
						<html:text property="workRptSelfVendorCommonDTO.filterDeptDesc" tabindex="40"/>
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

