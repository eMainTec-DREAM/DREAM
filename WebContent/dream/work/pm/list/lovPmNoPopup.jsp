<%--===========================================================================
예방점검 Popup
author  kim21017
version $Id: lovPmNoPopup.jsp,v 1.1 2016/02/18 09:12:01 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ page import="dream.work.pm.list.action.LovPmNoListAction"%>
<html>
<head>
<!-- 부품검색 -->
<title><bean:message key="LABEL.partSearch"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<!-- ########## page 상단 공통 : 모든 페이지 적용 ########## --> 

<script language="javascript">
<!--//

//그리드명
var myGrid;

/** 자동완성 변수  */
var deptAc;
var equipDescAc;

function loadPage() 
{
	initGrid();
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
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	setHeader(myGrid, "gridbox", "goSearch","lovPmNoList"); // grid, grid id
	
	equipDescAc = new autoC({"lovPmNoListDTO.equipDesc":"description"});
    equipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcResultMap({
        "lovPmNoListDTO.equipId":"equip_id"
    });
    equipDescAc.init();

    deptAc = new autoC({"lovPmNoListDTO.deptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "lovPmNoListDTO.deptId":"dept_id"
    });
    deptAc.init();
}

function goSelect(){
	goConfirm();
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList()
{
	var form = document.lovPmNoListForm;	
	form.strutsAction.value = '<%=LovPmNoListAction.LOV_PM_FIND%>';
	var url = contextPath + "/lovPmNoList.do";
	myGrid.clearAll(); setLoading("gridbox");
    $.post(url,FormQueryString(form), function(_data){
    	myGrid.parse(_data,"js");
    });
}

/**
 * 확인
 */
function goConfirm()
{	
	var returnArray = new Array();
	var selectedId=myGrid.getSelectedRowId();
    if(selectedId==null) return;

	var pmId		 = getValueById(myGrid, selectedId,'PMNO');
	var pmNo		 = getValueById(myGrid, selectedId,'PMNO');
	var pmDesc	     = getValueById(myGrid, selectedId,'DESCRIPTION').replace(/&gt;/gi, ">");
	var equipDesc 	 = getValueById(myGrid, selectedId,'EQUIPDESC');
	var scheduleType = getValueById(myGrid, selectedId,'SCHEDULETYPE');
	var deptDesc	 = getValueById(myGrid, selectedId,'DEPTDESC');
	var cycle	     = getValueById(myGrid, selectedId,'CYCLEDESC');
	var usage	     = getValueById(myGrid, selectedId,'USAGE');
	var periodType	 = getValueById(myGrid, selectedId,'PERIODTYPE');
	var pmType	     = getValueById(myGrid, selectedId,'PMTYPEDESC');
	var remark	     = getValueById(myGrid, selectedId,'REMARK');
	

	returnArray[0] = pmId; 
	returnArray[1] = pmNo;
	returnArray[2] = pmDesc;
	returnArray[3] = equipDesc;
	returnArray[4] = scheduleType;
	returnArray[5] = deptDesc;
	returnArray[6] = cycle;
	//작업결과-투입예방점검 -부품 선택시 사용수량 없으면 1넣기.
	returnArray[7] = usage;
	returnArray[8] = periodType;
	returnArray[9] = pmType;
	returnArray[10] = remark;


	var dirType = lovPmNoListForm.elements['lovPmNoListDTO.codeType'].value;
	dirType="LOV_PM";
// 	opener.setLovValue(returnArray, dirType);
    getIframeContent().setLovValue(returnArray, dirType);

// 	self.close();
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
	<html:form action="/lovPmNoList" >
		<html:hidden property="lovPmNoListDTO.extCode1" />
		<html:hidden property="lovPmNoListDTO.extCode2" />
		<html:hidden property="lovPmNoListDTO.codeType" />
		<html:hidden property="lovPmNoListDTO.deptId" />
		<html:hidden property="lovPmNoListDTO.pmId" />
		<html:hidden property="lovPmNoListDTO.equipId"/>
		<html:hidden property="strutsAction" />
		<div class="section_wrap">
			<div class="sheader_box">
				<div class="sheader_wrap"><a></a></div>
				<div class="stitle_wrap">
					<div class="stitle_icon"><a></a></div>
					<div class="stitle_tx"><bean:message key="LABEL.Filter"/></div>
				</div>
				<div class="function_box filter not_fold">
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
					<label><bean:message key="LABEL.pmNo"/></label>
					<div class="input_box">
						<html:text property="lovPmNoListDTO.pmNo" tabindex="10"/>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.pmDesc"/></label>
					<div class="input_box">
						<html:text property="lovPmNoListDTO.pmDesc" tabindex="20"/>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.equipDesc"/></label>
					<div class="input_box">
						<html:text property="lovPmNoListDTO.equipDesc" tabindex="40" />
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.deptDesc"/></label>
					<div class="input_box">
						<html:text property="lovPmNoListDTO.deptDesc" tabindex="50" />
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
			<div class="article_box" id="listBox">
				<div class="grid_area">
					<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
				</div>
			</div>
		</div> <!--  End of section_wrap -->
	</html:form> 
</body>
</html>