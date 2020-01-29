<%--===========================================================================
출고부품 Popup
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ page import="common.mafinder.mamstr.action.LovPtIssListAction"%>
<%@ page import="common.bean.User"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html>
<head>
<!-- 출고부품 -->
<title><bean:message key="LABEL.lovPtIssAcList"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<!-- ########## page 상단 공통 : 모든 페이지 적용 ########## --> 

<script language="javascript">
<!--//

//그리드명
var myGrid;

/** 자동완성 변수 */
var equipDescAc;
var workDescAc;
var deptAc;
var mainMngAc;
var subMngAc;
var empAc;
var eqLocDescAc;
var eqCtgTypeAc;
var vendorDescAc;
var pmTypeAc;

var isPmTypeLoad;
function loadPage() 
{

	lovPtIssListForm.elements['lovPtIssListDTO.filterRecByDesc'].value = loginUser.empName;
	lovPtIssListForm.elements['lovPtIssListDTO.filterRecBy'].value = loginUser.empId;
	
	//공장명
    if(loginUser.plant!='null'){
    	lovPtIssListForm.elements['lovPtIssListDTO.filterPlant'].value  = loginUser.plant;
    	lovPtIssListForm.elements['lovPtIssListDTO.filterPlantDesc'].value  = loginUser.plantDesc;
	}
	//창고
    if(loginUser.filterWcodeId!='null'){
    	lovPtIssListForm.elements['lovPtIssListDTO.filterWcodeId'].value  = loginUser.filterWcodeId;
    	lovPtIssListForm.elements['lovPtIssListDTO.filterWname'].value  = loginUser.filterWcodeDesc;
	}
	
	initGrid();
	
	
 	// 공장코드
	plantAc = new autoC({"lovPtIssListDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "lovPtIssListDTO.filterPlant":"plant"
    });
    plantAc.init();

    // 수령자
    empAc = new autoC({"lovPtIssListDTO.filterRecByDesc":"emp_name"});
    empAc.setTable("TAEMP");
    empAc.setAcResultMap({
        "lovPtIssListDTO.filterRecBy":"emp_id"
    });
    empAc.init();
    
    // 부서
    deptAc = new autoC({"lovPtIssListDTO.filterIssueDeptDesc":"description"});
    deptAc.setTable("TADEPT");
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    });
    deptAc.setAcResultMap({
        "lovPtIssListDTO.filterIssueDept":"dept_id"
    });
    deptAc.init();
    
    //창고 자동완성
    whAc = new autoC({"lovPtIssListDTO.filterWname":"wname"});
    whAc.setTable("TAWAREHOUSE");
    whAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    	,"wh_categ":"PART"
  	   });
    whAc.setAcResultMap({
        "lovPtIssListDTO.filterWcodeId":"wcode_id"
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
		goConfirm();
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	return sortColumn("lovPtIssAcList", this, lovPtIssListForm, "ptisslistId", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	setHeader(myGrid, "gridbox", "goSearch", "lovPtIssAcList"); // grid, grid id
}


/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
	var form = document.lovPtIssListForm;	
	form.strutsAction.value = '<%=LovPtIssListAction.LOV_PT_ISS_AC_FIND%>';
	var url = contextPath + "/lovPtIssAcList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(form), "ptisslistId", "Y");
}

function goSelect(){
	goConfirm();
}

/**
 * 확인
 */
function goConfirm()
{	
	setAcValue(myGrid, "ptisslistId");
}

/**
 * 검색
 */
function goSearch()
{
	findGridList("Search");
}

function goClose()
{
	closeLayerPopup(this);
}

//-->
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<!-- searchbox 박스 Line -->
	<html:form action="/lovPtIssAcList" >

<html:hidden property="currentPageId"/>
<html:hidden property="keyCode" /><html:hidden property="chName" />
<html:hidden property="resultCol" /><html:hidden property="multiSelect"/>
<html:hidden property="codeType" />
<html:hidden property="param" />
<html:hidden property="label" />
<html:hidden property="title" />

		<html:hidden property="lovPtIssListDTO.extCode1" />
		<html:hidden property="lovPtIssListDTO.extCode2" />
		<html:hidden property="lovPtIssListDTO.filterIssueDept"/>
		<html:hidden property="lovPtIssListDTO.filterRecBy"/>
		<html:hidden property="lovPtIssListDTO.filterPlant"/>
		<html:hidden property="lovPtIssListDTO.filterWcodeId"/>
		<html:hidden property="strutsAction" />
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
							<html:text property="lovPtIssListDTO.filterIssueDeptDesc" tabindex="10"/>
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					
	                <!-- 출고일자 -->
					<div class="field">
						<label><bean:message key="LABEL.issDate"/></label>
						<div class="calendar_wrap">
							<div class="input_box input_carendar">
								<html:text property="lovPtIssListDTO.filterIssueDateFrom" tabindex="20" />
								<p class="open_calendar"><span>날짜</span></p>
							</div>
							<div class="input_box input_carendar">
								<html:text property="lovPtIssListDTO.filterIssueDateTo" tabindex="30" />
								<p class="open_calendar"><span>날짜</span></p>
							</div>
						</div>
					</div>
					<!-- 품명/규격 -->
					<div class="field">
						<label><bean:message key="LABEL.partNameSize"/></label>
						<div class="input_box">
							<html:text property="lovPtIssListDTO.filterPtNameSize" tabindex="40"/>
						</div>
					</div>
					<!-- 수령자 -->
					<div class="field">
						<label><bean:message key="LABEL.recBy"/></label>
						<div class="input_box">
							<html:text property="lovPtIssListDTO.filterRecByDesc" tabindex="50"/>
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<!-- 출고번호 -->
					<div class="field">
                       <label><bean:message key="LABEL.ptIssListId"/></label>
                       <div class="input_box">
                            <html:text property="lovPtIssListDTO.filterPtIssListId" tabindex="80" />
                       </div>
                    </div>
					<!-- 창고 -->
					<div class="field">
						<label><bean:message key="LABEL.wname"/></label>
						<div class="input_box">
							<html:text property="lovPtIssListDTO.filterWname" tabindex="70" />
							<p class="open_spop">
								<a>
									<span>조회</span>
								</a>
							</p>
						</div>
					</div>
					<!-- 공장명 -->
					<div class="field">
                       <label><bean:message key="LABEL.plant"/></label>
                       <div class="input_box">
                            <html:text property="lovPtIssListDTO.filterPlantDesc" tabindex="90" />
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
					<div class="b_line"></div> 
					<div class="fb_group1">
						 
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