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
function loadPage() 
{
	//부서세팅
	lovEmpListForm.elements['lovEmpListDTO.deptId'].value = loginUser.filterDeptId;
	lovEmpListForm.elements['lovEmpListDTO.deptDesc'].value = loginUser.filterDeptDesc;
	
	initGrid();
	
	deptAc = new autoC({"lovEmpListDTO.deptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "lovEmpListDTO.deptId":"dept_id"
    });
    deptAc.init();
    
}

/**
 * 그리드 초기화
 */
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowDblClicked",function(rowId, columnId){
		goConfirm();
	});
	
	if(lovEmpListForm.elements['lovEmpListDTO.multiSelect'].value == "Y") myGrid.enableMultiselect(true);
	
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	setHeader(myGrid, "gridbox", "goSearch", "lovEmpList"); // grid, grid id
}


/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList()
{
	var form = document.lovEmpListForm;	
	form.strutsAction.value = '<%=LovEmpListAction.LOV_EMP_FIND%>';
	var url = contextPath + "/lovEmpList.do";
	myGrid.clearAll(); setLoading("gridbox");
    $.post(url,FormQueryString(form), function(_data){
    	myGrid.parse(_data,"js");
    });
}

function goSelect(){
	goConfirm();
}

/**
 * 확인
 */
function goConfirm()
{	
	var returnArray = new Array();
	
	var selectedId=myGrid.getSelectedRowId();
    if(selectedId==null) return;
    
    var spId = selectedId.split(",");
    if(spId.length != 1)
    {
    	var rtnOne= new Array(), rtnTwo="", rtnThree ="", rtnFour="", rtnFive=""; 
    	var cnt = 1;

    	for(var i in spId)
    	{
    		if(lovEmpListForm.elements['lovEmpListDTO.multiSelect'].value == "Y")
    		{
    			rtnOne.push(getValueById(myGrid, spId[i], 'empId'));
    		}
    		
    		rtnTwo = rtnTwo + getValueById(myGrid, spId[i], 'empNo');
    		if(spId.length != cnt) rtnTwo = rtnTwo + "+";
    		
    		rtnThree = rtnThree + getValueById(myGrid, spId[i], 'empName');
    		if(spId.length != cnt) rtnThree = rtnThree + "+";
    		
    		rtnFour = rtnFour + getValueById(myGrid, spId[i], 'deptId');
    		if(spId.length != cnt) rtnFour = rtnFour + "+";
    		
    		rtnFive = rtnFive + getValueById(myGrid, spId[i], 'deptDesc');
    		if(spId.length != cnt) rtnFive = rtnFive + "+";
    		
    		cnt ++;
    	}
    }
    else
    {
    	rtnOne = getValueById(myGrid, selectedId, 'empId');
    	rtnTwo = getValueById(myGrid, selectedId, 'empNo');
    	rtnThree = getValueById(myGrid, selectedId, 'empName');
    	rtnFour = getValueById(myGrid, selectedId, 'deptId');
    	rtnFive = getValueById(myGrid, selectedId, 'deptDesc');
    }

	returnArray[0] = rtnOne; // ID
    returnArray[1] = rtnTwo; // NO
    returnArray[2] = rtnThree; // Desc
    returnArray[3] = rtnFour; // deptId
    returnArray[4] = rtnFive; // deptDesc
	
    getIframeContent().setLovValue(returnArray, '');
    closeLayerPopup();
}

/**
 * 검색
 */
function goSearch()
{
	findGridList();
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
	<html:form action="/lovEmpList" >
		<html:hidden property="lovEmpListDTO.deptId"/>
		<html:hidden property="lovEmpListDTO.multiSelect"/>
		<html:hidden property="lovEmpListDTO.extCode1"/>
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
							<html:text property="lovEmpListDTO.deptDesc"
										onkeydown="validationKeyDown('lovEmpListDTO.deptDesc', 'lovEmpListDTO.deptId');"/>
							<p class="open_spop"><a href="javascript:lovDept('lovEmpListDTO.deptId', '', 'lovEmpListDTO.deptDesc');"><span>조회</span></a></p>
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