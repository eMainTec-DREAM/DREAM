<%--===========================================================================
설비 Popup
author  ssong
version $Id: lovEquipCtgPopup.jsp,v 1.1 2016/02/18 09:12:01 ssong Exp $
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
<%@ page import="dream.rcm.funceq.action.LovEquipCtgListAction"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html>
<head>
<!-- 설비 -->
<title><bean:message key="LABEL.equipment"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<!-- ########## page 상단 공통 : 모든 페이지 적용 ########## --> 

<script language="javascript">
<!--//

//그리드명
var myGrid;
/** 자동완성 변수 */

var equipDescAc;
var equipNoAc;
var eqLocDescAc;
var eqCtgTypeAc;
var isLawEqAc;
var mainMngAc;
var subMngName;
var deptAc;
var eqStatusAc;
function loadPage() 
{
	initGrid();
	
	equipDescAc = new autoC({"lovEquipCtgListDTO.equipDesc":"description"});
    equipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.init();
    
    equipNoAc = new autoC({"lovEquipCtgListDTO.itemNo":"item_no"});
    equipNoAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    equipNoAc.setTable("TAEQUIPMENT");
    equipNoAc.init();
  
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
	myGrid.enableMultiselect(true);
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	setHeader(myGrid, "gridbox", "goSearch", "lovEquipCtgList"); // grid, grid id
}


/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList()
{
	var form = document.lovEquipCtgListForm;	
	form.strutsAction.value = '<%=LovEquipCtgListAction.LOV_EQUIP_FIND%>';
	var url = contextPath + "/lovEquipCtgList.do";
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
    	var rtnOne= new Array();
    	var rtnTwo= new Array(); 
    	var cnt = 1;

    	for(var i in spId)
    	{

    			rtnOne.push(getValueById(myGrid, spId[i], 'EQUIPID'));
    			rtnTwo.push(getValueById(myGrid, spId[i], 'RCMEQASMBID'));
   		    		
    		cnt ++;
    	}
    }
    else
    {
    	rtnOne = getValueById(myGrid, selectedId,'EQUIPID');
    	rtnTwo = getValueById(myGrid, selectedId,'RCMEQASMBID');
    }
	returnArray[0] = rtnOne;
	returnArray[1] = rtnTwo;
	
	//var dirType = listOfValForm.elements['listOfValDTO.codeType'].value;
    var ifm = getIframeContent();
    //if(typeof ifm.setLovValue == "function") ifm.setLovValue(returnArray,'');
     getIframeContent().setLovValue(returnArray,'Test');

//     self.close();
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
	<html:form action="/lovEquipCtgList" >
		<html:hidden property="lovEquipCtgListDTO.extCode1" />
		<html:hidden property="lovEquipCtgListDTO.extCode2" />
		<html:hidden property="lovEquipCtgListDTO.codeType" />
		<html:hidden property="lovEquipCtgListDTO.eqLocId" />
		<html:hidden property="lovEquipCtgListDTO.eqCtgId" />
		<html:hidden property="lovEquipCtgListDTO.plfTypeId" />
		<html:hidden property="lovEquipCtgListDTO.plfTypeDesc" />
		<html:hidden property="lovEquipCtgListDTO.mainMngId" />
		<html:hidden property="lovEquipCtgListDTO.subMngId" />
		<html:hidden property="lovEquipCtgListDTO.deptId" />
		<html:hidden property="lovEquipCtgListDTO.eqStatusId" />
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
						<!-- 설비번호 -->
						<label><bean:message key="LABEL.equipNo"/></label>
						<div class="input_box">
							<html:text property="lovEquipCtgListDTO.itemNo" tabindex="10"/>
						</div>
					</div>
					<div class="field">
						<!-- 설비명 -->
						<label><bean:message key="LABEL.equipName"/></label>
						<div class="input_box">
							<html:text property="lovEquipCtgListDTO.equipDesc" tabindex="20"/>
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