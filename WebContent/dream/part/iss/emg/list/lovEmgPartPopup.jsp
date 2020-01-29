<%--===========================================================================
긴급출고 Popup
author  jung7126
version $Id: lovEmgPartPopup.jsp,v 1.1 2016/02/18 09:12:01 ssong Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ page import="dream.part.iss.emg.list.action.LovEmgPartListAction"%>
<html>
<head>
<!-- 긴급출고 -->
<title><bean:message key="MENU.PTEMG"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<script language="javascript">
<!--//

//그리드명
var myGrid;
var plantAc;
var empAc;
var deptAc;
var whAc;

function loadPage() 
{
	lovEmgPartListForm.elements['lovEmgPartListDTO.recByName'].value = loginUser.empName;
	lovEmgPartListForm.elements['lovEmgPartListDTO.recBy'].value = loginUser.empId;
	
	//공장명
    if(loginUser.plant!='null'){
    	lovEmgPartListForm.elements['lovEmgPartListDTO.filterPlantId'].value  = loginUser.plant;
    	lovEmgPartListForm.elements['lovEmgPartListDTO.filterPlantDesc'].value  = loginUser.plantDesc;
	}
	//창고
    if(loginUser.filterWcodeId!='null'){
    	lovEmgPartListForm.elements['lovEmgPartListDTO.filterWCodeId'].value  = loginUser.filterWcodeId;
    	lovEmgPartListForm.elements['lovEmgPartListDTO.filterWName'].value  = loginUser.filterWcodeDesc;
    	lovEmgPartListForm.elements['lovEmgPartListDTO.filterStockQty'].value  = "0.1";
	}
	
	initGrid();
	
	
 	// 공장코드
	plantAc = new autoC({"lovEmgPartListDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "lovEmgPartListDTO.filterPlantId":"plant"
    });
    plantAc.init();

    // 수령자
    empAc = new autoC({"lovEmgPartListDTO.recByName":"emp_name"});
    empAc.setTable("TAEMP");
    empAc.setAcResultMap({
        "lovEmgPartListDTO.recBy":"emp_id"
    });
    empAc.init();
    
    // 부서
    deptAc = new autoC({"lovEmgPartListDTO.issueDeptDesc":"description"});
    deptAc.setTable("TADEPT");
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    });
    deptAc.setAcResultMap({
        "lovEmgPartListDTO.issueDept":"dept_id"
    });
    deptAc.init();
    
    //창고 자동완성
    whAc = new autoC({"lovEmgPartListDTO.filterWName":"wname"});
    whAc.setTable("TAWAREHOUSE");
    whAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    	,"wh_categ":"PART"
  	   });
    whAc.setAcResultMap({
        "lovEmgPartListDTO.filterWCodeId":"wcode_id"
    });
    whAc.init();

}

/**
 * 그리드 초기화
 */
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableMultiselect(chkFilter());
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowDblClicked",function(rowId, columnId){
		//goConfirm();
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		lovEmgPartListForm.elements['lovEmgPartListDTO.ptemgisslistId'].value = "";
		return sortColumn("lovEmgPartList", this, lovEmgPartListForm, "ptemgisslistId", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox", "goSearch", "lovEmgPartList"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
	var form = document.lovEmgPartListForm;	
	form.strutsAction.value = '<%=LovEmgPartListAction.LOV_EMGPART_FIND%>';
	var url = contextPath + "/lovEmgPartList.do";
	/* myGrid.clearAll(); setLoading("gridbox");
    $.post(url,FormQueryString(form), function(_data){
    	myGrid.parse(_data,"js");
    }); */
    
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(form), "ptemgisslistId" , "Y");

}

function goSelect()
{
	goConfirm();
}

/**
 * 확인
 */
function goConfirm()
{	
	setAcValue(myGrid, "ptemgisslistId");  // (gridObj, Id Column If exist)
}

/**
 * 검색
 */
function goSearch()
{
	findGridList('Search');
}

function goClose()
{
	closeLayerPopup(this);
}

function goExcel()
{
    excelServerAction("lovEmgPartList", lovEmgPartListForm );  
}
//-->
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<!-- searchbox 박스 Line -->
	<html:form action="/lovEmgPartList" >
		<html:hidden property="strutsAction" />
		<html:hidden property="keyCode" /><html:hidden property="chName" />
		<html:hidden property="resultCol" /><html:hidden property="multiSelect"/>
		<html:hidden property="codeType" />
		<html:hidden property="param" />
		<html:hidden property="label" />
		<html:hidden property="title" />
		<html:hidden property="lovEmgPartListDTO.issueDept"/>
		<html:hidden property="lovEmgPartListDTO.ptemgTaskStatus"/>
		<html:hidden property="lovEmgPartListDTO.recBy"/>
		<html:hidden property="lovEmgPartListDTO.ptemgissStatus"/>
		<html:hidden property="lovEmgPartListDTO.ptemgisslistId"/>
		<html:hidden property="lovEmgPartListDTO.wkorId"/>
		<html:hidden property="lovEmgPartListDTO.filterPlantId"/>
		<html:hidden property="lovEmgPartListDTO.multiSelect"/>
		<html:hidden property="lovEmgPartListDTO.filterWCodeId"/>
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
					<div class="b_line"></div> 
					<div class="fb_group1">
						 
					</div>
				</div>
			</div><!--sheader_box end-->
			<div class="article_box">
				<div class="form_box">
					<!-- 출고부서 -->
					<div class="field">
						<label><bean:message key="LABEL.issDept"/></label>
						<div class="input_box">
							<html:text property="lovEmgPartListDTO.issueDeptDesc" tabindex="10"/>
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					
	                <!-- 출고일자 -->
					<div class="field">
						<label><bean:message key="LABEL.issDate"/></label>
						<div class="calendar_wrap">
							<div class="input_box input_carendar">
								<html:text property="lovEmgPartListDTO.issueDateFrom" tabindex="20" />
								<p class="open_calendar"><span>날짜</span></p>
							</div>
							<div class="input_box input_carendar">
								<html:text property="lovEmgPartListDTO.issueDateTo" tabindex="30" />
								<p class="open_calendar"><span>날짜</span></p>
							</div>
						</div>
					</div>
					<!-- 품명/규격 -->
					<div class="field">
						<label><bean:message key="LABEL.partNameSize"/></label>
						<div class="input_box">
							<html:text property="lovEmgPartListDTO.partDesc" tabindex="40"/>
						</div>
					</div>
					<!-- 수령자 -->
					<div class="field">
						<label><bean:message key="LABEL.recBy"/></label>
						<div class="input_box">
							<html:text property="lovEmgPartListDTO.recByName" tabindex="50"/>
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<!-- 출고번호 -->
					<div class="field">
                       <label><bean:message key="LABEL.ptIssListId"/></label>
                       <div class="input_box">
                            <html:text property="lovEmgPartListDTO.filterPtemgisslistId" tabindex="80" />
                       </div>
                    </div>
					<!-- 창고 -->
					<div class="field">
						<label><bean:message key="LABEL.wname"/></label>
						<div class="input_box">
							<html:text property="lovEmgPartListDTO.filterWName" tabindex="70" />
							<p class="open_spop">
								<a>
									<span>조회</span>
								</a>
							</p>
						</div>
					</div>
					<!-- 현재고 -->
					<div class="field">
                       <label><bean:message key="LABEL.stockQty"/></label>
                       <div class="input_box">
                            <html:text property="lovEmgPartListDTO.filterStockQty" tabindex="85" />
                       </div>
                    </div>
					<!-- 공장명 -->
					<div class="field">
                       <label><bean:message key="LABEL.plant"/></label>
                       <div class="input_box">
                            <html:text property="lovEmgPartListDTO.filterPlantDesc" tabindex="90" />
                            <p class="open_spop"><a><span>조회</span></a></p>
                       </div>
                    </div>
                    <!-- 비고 -->
					<div class="field">
                       <label><bean:message key="LABEL.remark"/></label>
                       <div class="input_box">
                            <html:text property="lovEmgPartListDTO.filterRemark" tabindex="95" />
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
			<div class="article_box" id="listBox">
				<div class="grid_area">
					<div id="gridbox" style="width:100%; height:150px; background-color:white;"></div>
				</div>
			</div>
		</div> <!--  End of section_wrap -->
	</html:form> 
</body>
</html>