<%--===========================================================================
설비 Popup
author  ssong
version $Id: lovEquipPopup.jsp,v 1.1 2016/02/18 09:12:01 ssong Exp $
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
<%@ page import="dream.asset.list.action.LovEquipListAction"%>
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
	//부서세팅
	lovEquipListForm.elements['lovEquipListDTO.deptId'].value = loginUser.filterDeptId;
	lovEquipListForm.elements['lovEquipListDTO.deptDesc'].value = loginUser.filterDeptDesc;
	
	if(loginUser.eqLocId!='null'){
		lovEquipListForm.elements['lovEquipListDTO.eqLocId'].value = loginUser.eqLocId;
		lovEquipListForm.elements['lovEquipListDTO.eqLocDesc'].value = loginUser.eqLocDesc;
	}
	
    //lovEquipListForm.elements['lovEquipListDTO.eqStatusId'].value = 'R';
    lovEquipListForm.elements['lovEquipListDTO.eqStatusDesc'].value = 'R';
    valSysDir('lovEquipListDTO.eqStatusId',  'lovEquipListDTO.eqStatusDesc', 'EQ_STATUS', true);

	initGrid();
	
	equipDescAc = new autoC({"lovEquipListDTO.equipDesc":"description"});
    equipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.init();
    
    equipNoAc = new autoC({"lovEquipListDTO.itemNo":"item_no"});
    equipNoAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    equipNoAc.setTable("TAEQUIPMENT");
    equipNoAc.init();
    
    eqLocDescAc = new autoC({"lovEquipListDTO.eqLocDesc":"full_desc"});
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqLocDescAc.setAcResultMap({
        "lovEquipListDTO.eqLocId":"eqloc_id"
    });
    eqLocDescAc.init();
    
    eqCtgTypeAc = new autoC({"lovEquipListDTO.eqCtgDesc":"description"});
    eqCtgTypeAc.setAcDisplay("DESCRIPTION");
    eqCtgTypeAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqCtgTypeAc.setTable("TAEQCTG");
    eqCtgTypeAc.setAcResultMap({
        "lovEquipListDTO.eqCtgId":"eqctg_id"
    });
    eqCtgTypeAc.init();
    
    isLawEqAc = new autoC({"lovEquipListDTO.isLawEq":"description"});
    isLawEqAc.setAcDisplay("DESCRIPTION");
    isLawEqAc.setAcConditionMap({
        	"list_type":"IS_USE"
  	   });
    isLawEqAc.setTable("TACDSYSD");
    isLawEqAc.init();
    
    mainMngAc = new autoC({"lovEquipListDTO.mainMngName":"emp_name"});
    mainMngAc.setAcDisplay("EMP_NAME");
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcResultMap({
        "lovEquipListDTO.mainMngId":"emp_id"
    });
    mainMngAc.init();
    
    subMngName = new autoC({"lovEquipListDTO.subMngName":"emp_name"});
    subMngName.setAcDisplay("EMP_NAME");
    subMngName.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    subMngName.setTable("TAEMP");
    subMngName.setAcResultMap({
        "lovEquipListDTO.subMngId":"emp_id"
    });
    subMngName.init();
    
    deptAc = new autoC({"lovEquipListDTO.deptDesc":"description"});
    deptAc.setAcDisplay("DESCRIPTION");
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "lovEquipListDTO.deptId":"dept_id"
    });
    deptAc.init();
    
    eqStatusAc = new autoC({"lovEquipListDTO.eqStatusDesc":"description"});
    eqStatusAc.setAcDisplay("DESCRIPTION");
    eqStatusAc.setAcConditionMap({
        	"list_type":"EQ_STATUS",
        	"is_use":"Y"
  	   });
    eqStatusAc.setTable("TACDSYSD");
    eqStatusAc.setAcResultMap({
        "lovEquipListDTO.eqStatusId":"cdsysd_no"
    });
    eqStatusAc.init();
    
    
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
	
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
        lovEquipListForm.elements['lovEquipListDTO.itemNo'].value = "";
        return sortColumn("lovEquipPopup", this, lovEquipListForm, "ITEM_NO", ind, direction);
    });
	
	if(lovEquipListForm.elements['lovEquipListDTO.multiSelect'].value == "Y") myGrid.enableMultiselect(true);
	
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	setHeader(myGrid, "gridbox", "goSearch", "lovEquipList"); // grid, grid id
}


/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList()
{
	var form = document.lovEquipListForm;	
	form.strutsAction.value = '<%=LovEquipListAction.LOV_EQUIP_FIND%>';
	var url = contextPath + "/lovEquipList.do";
	
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(form), "ITEM_NO", "Y");

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
    
    if(lovEquipListForm.elements['lovEquipListDTO.multiSelect'].value == "Y")
	{
        if(spId.length != 1)
        {
        	var rtnOne= new Array(), rtnTwo=""; 
        	var cnt = 1;

        	for(var i in spId)
        	{
        		if(lovEquipListForm.elements['lovEquipListDTO.multiSelect'].value == "Y")
        		{
        			rtnOne.push(getValueById(myGrid, spId[i], "equipId"));
        		}
        		
        		rtnTwo = rtnTwo + getValueById(myGrid, spId[i], "description");
        		if(spId.length != cnt) rtnTwo = rtnTwo + "+";
        		
        		cnt ++;
        	}
        }
        else
        {
        	rtnOne = getValueById(myGrid, selectedId,"equipId");
        	rtnTwo = htmlEnDeCode.htmlDecode(getValueById(myGrid, selectedId,"description"));
        }

    	returnArray[0] = rtnOne;
    	returnArray[1] = rtnTwo;
	} 
    else 
    {
    	returnArray[0] = getValueById(myGrid, selectedId,'equipId'); //equipId
        returnArray[1] = getValueById(myGrid, selectedId,'equipDesc'); //equipDesc
        returnArray[2] = getValueById(myGrid, selectedId,'eqLocDesc').replace(/&gt;/gi, ">"); //eqLocDesc
        returnArray[3] = getValueById(myGrid, selectedId,'eqCtgDesc').replace(/&gt;/gi, ">"); //eqCtgDesc
        returnArray[4] = getValueById(myGrid, selectedId,'eqLocId'); //eqLocId
        returnArray[5] = getValueById(myGrid, selectedId,'eqCtgId'); //eqCtgId
        returnArray[6] = getValueById(myGrid, selectedId,'eqStatusDesc'); //eqStatusDesc
        returnArray[7] = getValueById(myGrid, selectedId,'eqStatusId'); //eqStatusId
	}

	var dirType = lovEquipListForm.elements['lovEquipListDTO.codeType'].value;
	
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
	<html:form action="/lovEquipList" >
		<html:hidden property="lovEquipListDTO.extCode1" />
		<html:hidden property="lovEquipListDTO.extCode2" />
		<html:hidden property="lovEquipListDTO.codeType" />
		<html:hidden property="lovEquipListDTO.eqLocId" />
		<html:hidden property="lovEquipListDTO.eqCtgId" />
		<html:hidden property="lovEquipListDTO.plfTypeId" />
		<html:hidden property="lovEquipListDTO.plfTypeDesc" />
		<html:hidden property="lovEquipListDTO.mainMngId" />
		<html:hidden property="lovEquipListDTO.subMngId" />
		<html:hidden property="lovEquipListDTO.deptId" />
		<html:hidden property="lovEquipListDTO.eqStatusId" />
		<html:hidden property="lovEquipListDTO.multiSelect" />
		<html:hidden property="lovEquipListDTO.eqCtgTypeId" />
		<html:hidden property="lovEquipListDTO.eqCtgTypeDesc" />
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
							<html:text property="lovEquipListDTO.itemNo" tabindex="10"/>
						</div>
					</div>
					<div class="field">
						<!-- 설비명 -->
						<label><bean:message key="LABEL.equipName"/></label>
						<div class="input_box">
							<html:text property="lovEquipListDTO.equipDesc" tabindex="20"/>
						</div>
					</div>
					<!-- 위치 -->
					<div class="field">
						<label><bean:message key="LABEL.location"/></label>
						<div class="input_box">
							<html:text property="lovEquipListDTO.eqLocDesc" tabindex="30" />
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
							<html:text property="lovEquipListDTO.eqCtgDesc" tabindex="40"
										onkeydown="validationKeyDown('lovEquipListDTO.eqCtgDesc', 'lovEquipListDTO.eqCtgId');"/>
							<p class="open_spop">
								<a href="javascript:lovEqCtg('lovEquipListDTO.eqCtgId', 'lovEquipListDTO.eqCtgDesc');">
									<span>조회</span>
								</a>
							</p>
						</div>
					</div>
					<!-- 법정설비여부 -->
					<div class="field">
						<label><bean:message key="LABEL.isLawEq"/></label>
						<div class="input_box">
							<html:text property="lovEquipListDTO.isLawEq" tabindex="50" />
							<p class="open_spop">
								<a href="javascript:lovTable('lovEquipListDTO.isLawEq', 'lovEquipListDTO.isLawEq','YN');">
									<span>조회</span>
								</a>
							</p>
						</div>
					</div>
					<!-- 관리자(정) -->
					<div class="field">
						<label><bean:message key="LABEL.mainManager"/></label>
						<div class="input_box">
							<html:text property="lovEquipListDTO.mainMngName" tabindex="70"
										onkeydown="validationKeyDown('lovEquipListDTO.mainMngName', 'lovEquipListDTO.mainMngId');"/>
							<p class="open_spop"><a href="javascript:lovEmp('lovEquipListDTO.mainMngId', '', 'lovEquipListDTO.mainMngName');"><span>조회</span></a></p>
						</div>
					</div>
					<!-- 관리자(부) -->
					<div class="field">
						<label><bean:message key="LABEL.subManager"/></label>
						<div class="input_box">
							<html:text property="lovEquipListDTO.subMngName" tabindex="80"
										onkeydown="validationKeyDown('lovEquipListDTO.subMngName', 'lovEquipListDTO.subMngId');"/>
							<p class="open_spop"><a href="javascript:lovEmp('lovEquipListDTO.subMngId', '', 'lovEquipListDTO.subMngName');"><span>조회</span></a></p>
						</div>
					</div>
					<!-- 부서 -->
					<div class="field">
						<label><bean:message key="LABEL.dept"/></label>
						<div class="input_box">
							<html:text property="lovEquipListDTO.deptDesc" tabindex="70"
								onkeydown="validationKeyDown('lovEquipListDTO.deptDesc', 'lovEquipListDTO.deptId');"/>
							<p class="open_spop"><a href="javascript:lovDept('lovEquipListDTO.deptId', '', 'lovEquipListDTO.deptDesc');"><span>조회</span></a></p>
						</div>
					</div>
					<!-- 상태 -->
					<div class="field">
						<label><bean:message key="LABEL.equipStatus"/></label>
						<div class="input_box">
							<html:text property="lovEquipListDTO.eqStatusDesc" tabindex="35" 
								onkeydown="validationKeyDown('lovEquipListDTO.eqStatusDesc', 'lovEquipListDTO.eqStatusId');"/>
							<p class="open_spop"><a href="javascript:lovSysDir('lovEquipListDTO.eqStatusId', 'lovEquipListDTO.eqStatusDesc','EQ_STATUS');"><span>조회</span></a></p>
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