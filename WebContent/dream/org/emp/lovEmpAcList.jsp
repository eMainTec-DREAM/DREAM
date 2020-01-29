<%--===========================================================================
사원 Popup
author  ssong
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
<%@ page import="common.bean.User"%>
<%@ page import="dream.org.emp.action.LovEmpListAction"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html>
<head>
<!-- 사원 -->
<title><bean:message key="LABEL.emp"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<!-- ########## page 상단 공통 : 모든 페이지 적용 ########## --> 

<script language="javascript">
<!--//

//그리드명
var myGrid;

var deptAc;
var plantAc;
var conditionFilterMap = {
	"plant":"lovEmpListDTO.plantId"
	,"plantDesc":"lovEmpListDTO.plantDesc"
	,"dept_id":"lovEmpListDTO.deptId"
	,"deptDesc":"lovEmpListDTO.deptDesc"
	,"is_join":"lovEmpListDTO.isJoinDesc"
	};
function loadPage() 
{
	
	convertCondition();
	
	//공장명
	setInitVal('lovEmpListDTO.plantId', loginUser.filterPlant);
	setInitVal('lovEmpListDTO.plantDesc', loginUser.filterPlantDesc);
	
	//부서명 (로그인사용자의 자주보는 부서)
	setInitVal('lovEmpListDTO.deptId', loginUser.filterDeptId);
	setInitVal('lovEmpListDTO.deptDesc', loginUser.filterDeptDesc);
	
	//부서명 (로그인 사용자의 부서)
	setInitVal('lovEmpListDTO.deptId', loginUser.deptId);
	setInitVal('lovEmpListDTO.deptDesc', loginUser.deptDesc);
	
	setInitVal('lovEmpListDTO.isJoinId', 'Y');
	setInitVal('lovEmpListDTO.isJoinDesc', 'Y');
	
	initGrid();
	
	deptAc = new autoC({"lovEmpListDTO.deptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "lovEmpListDTO.deptId":"dept_id"
    });
    deptAc.setAcDConditionMap({
    	"plant" : "lovEmpListDTO.plantId"
    });
    deptAc.init();
    
 	// 공장코드
	plantAc = new autoC({"lovEmpListDTO.plantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "lovEmpListDTO.plantId":"plant"
    });
    plantAc.init();
    
    acSysDesc("lovEmpListDTO.isJoinDesc","lovEmpListDTO.isJoinId","IS_USE",true); 
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
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	setHeader(myGrid, "gridbox", "goSearch"); // grid, grid id
}


/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
	var form = document.lovEmpListForm;	
	form.strutsAction.value = '<%=LovEmpListAction.LOV_EMP_AC_FIND%>';
	var url = contextPath + "/lovEmpList.do";
	
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(form),"EMP_ID","Y");
}

function goSelect(){
	goConfirm();
}

/**
 * 확인
 */
function goConfirm()
{	
	setAcValue(myGrid, "emp_id");  // (gridObj, Id Column If exist)
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


function convertCondition()
{
	var paramObj = JSON.parse(M$('param').value);
	for(var key in paramObj){
		if(typeof conditionFilterMap[key] != "undefined" && typeof M$(conditionFilterMap[key]) == "object")
		{
			M$(conditionFilterMap[key]).value = paramObj[key];
			paramObj[key] = '';
			M$('param').value = JSON.stringify(paramObj);
		}
	}
}
//-->
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<!-- searchbox 박스 Line -->
	<html:form action="/lovEmpList" >
	
<html:hidden property="currentPageId"/>
<html:hidden property="keyCode" /><html:hidden property="chName" />
<html:hidden property="resultCol" /><html:hidden property="multiSelect"/>
<html:hidden property="codeType" />
<html:hidden property="param" />
<html:hidden property="label" />
<html:hidden property="title" />

		<html:hidden property="lovEmpListDTO.deptId"/>
		<html:hidden property="lovEmpListDTO.plantId"/>
		<html:hidden property="lovEmpListDTO.isJoinId"/>
		<html:hidden property="lovEmpListDTO.multiSelect"/>
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
				</div>
			</div><!--sheader_box end-->
			<div class="article_box">
				<div class="form_box">
					<div class="field">
						<!-- 사원명 -->
						<label><bean:message key="LABEL.empName"/></label>
						<div class="input_box">
							<html:text property="lovEmpListDTO.empName" />
						</div>
					</div>
					<div class="field">
						<!-- 사원코드 -->
						<label><bean:message key="LABEL.empNo"/></label>
						<div class="input_box">
							<html:text property="lovEmpListDTO.empNo" />
						</div>
					</div>
					<!-- 부서 -->
					<div class="field">
						<label><bean:message key="LABEL.dept"/></label>
						<div class="input_box">
							<html:text property="lovEmpListDTO.deptDesc"/>
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<!-- 공장명  -->
	                <div class="field">
	                    <label><bean:message key="LABEL.plantDesc"/></label>
	                    <div class="input_box">
							<html:text property="lovEmpListDTO.plantDesc" />
							<p class="open_spop"><a><span>조회</span></a></p>
	                    </div>
	                </div>
                   <!-- 근무여부 -->
                   <div class="field">
                   <label><bean:message key="LABEL.isJoin"/></label>
                        <div class="input_box">
                           <html:text property="lovEmpListDTO.isJoinDesc" />
                            <p class="open_spop">
                                <a>
                                    <span>조회</span>
                                </a>
                            </p>
                      </div>
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