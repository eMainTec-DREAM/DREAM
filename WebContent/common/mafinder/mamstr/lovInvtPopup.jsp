<%--===========================================================================
투자목록 LOV Popup
author  js.lee
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ page import="common.mafinder.mamstr.action.LovInvtListAction"%>
<%@ page import="common.bean.User"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html>
<head>
<!-- 투자목록 -->
<title><bean:message key="LABEL.lovInvtPopup"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<!-- ########## page 상단 공통 : 모든 페이지 적용 ########## --> 

<script language="javascript">
<!--//

//그리드명
var myGrid;

/** 자동완성 변수 */
var deptAc;
var empAc;
var eqLocDescAc;
var eqCtgTypeAc;
var equipDescAc;
var plantAc;

function loadPage() 
{
	
	//담당자 
	lovInvtListForm.elements['lovInvtListDTO.filterEmpId'].value = loginUser.filterEmpId;
	lovInvtListForm.elements['lovInvtListDTO.filterEmpDesc'].value = loginUser.filterEmpDesc;
	//부서
	lovInvtListForm.elements['lovInvtListDTO.filterDeptId'].value    = loginUser.filterDeptId;
	lovInvtListForm.elements['lovInvtListDTO.filterDeptDesc'].value  = loginUser.filterDeptDesc;
	
  	//공장명
    if(loginUser.plant!='null'){
    	lovInvtListForm.elements['lovInvtListDTO.filterPlantId'].value  = loginUser.plant;
    	lovInvtListForm.elements['lovInvtListDTO.filterPlantDesc'].value  = loginUser.plantDesc;
	}
	
	initGrid();
	
	//진행단계
	acSysDesc("lovInvtListDTO.filterInvtStatusDesc","lovInvtListDTO.filterInvtStatus","INVTLIST_STATUS");
	//투자구분
	acSysDesc("lovInvtListDTO.filterInvtCategDesc","lovInvtListDTO.filterInvtCateg","INVT_CATEG");
	//분류
	acSysDesc("lovInvtListDTO.filterInvtTypeDesc","lovInvtListDTO.filterInvtType","INVT_TYPE");
	
	//투자부서
    deptAc = new autoC({"lovInvtListDTO.filterDeptDesc":"description"});
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "lovInvtListDTO.filterDeptId":"dept_id",
    });
    deptAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    }); 
    deptAc.setAcDConditionMap({
    	"plant" : "lovInvtListDTO.filterPlantId"
    });
    deptAc.init();
    
    //담당자
    empAc = new autoC({"lovInvtListDTO.filterEmpDesc":"emp_name"});
    empAc.setTable("TAEMP");
    empAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    empAc.setAcResultMap({
        "lovInvtListDTO.filterEmpId":"emp_id"
    });
    empAc.setAcDConditionMap({
    	"dept_id" : "lovInvtListDTO.filterDeptId"
    });
    empAc.init();
    
    //설비위치
    eqLocDescAc = new autoC({"lovInvtListDTO.filterEqlocDesc":"full_desc"});
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setAcConditionMap({
       "comp_no":loginUser.compNo
       });
    eqLocDescAc.setAcResultMap({
        "lovInvtListDTO.filterEqLocId":"eqloc_id"
    });
    eqLocDescAc.setAcDConditionMap({
    	"plant" : "lovInvtListDTO.filterPlantId"
    });
    eqLocDescAc.init();
    
    //설비종류
    eqCtgTypeAc = new autoC({"lovInvtListDTO.filterEqCtgDesc":"full_desc"});
    eqCtgTypeAc.setTable("TAEQCTG");
    eqCtgTypeAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    }); 
    eqCtgTypeAc.setAcResultMap({
        "lovInvtListDTO.filterEqCtgId":"eqctg_id"
    });
    eqCtgTypeAc.init();
    
    //설비
    equipDescAc = new autoC({"lovInvtListDTO.filterEquipDesc":"description"});
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcDConditionMap({
    	"eqloc_id" : "lovInvtListDTO.filterEqLocId",
    	"eqctg_id" : "lovInvtListDTO.filterEqCtgId",
    	"dept_id" : "lovInvtListDTO.filterDeptId",
    	"plant" : "lovInvtListDTO.filterPlantId"
    });
    equipDescAc.init();
	
    //공장명
    plantAc = new autoC({"lovInvtListDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "lovInvtListDTO.filterPlantId":"plant"
    });
    plantAc.init();
	
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
	setHeader(myGrid, "gridbox", "goSearch", "lovInvtList"); // grid, grid id
}


/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList()
{
	var form = document.lovInvtListForm;	
	form.strutsAction.value = '<%=LovInvtListAction.LOV_INVT_FIND%>';
	var url = contextPath + "/lovInvtList.do";
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
	//setAcValue(myGrid, "invtlistId");  // (gridObj, Id Column If exist)
	
	var returnArray = new Array();
	var selectedId=myGrid.getSelectedRowId();
    if(selectedId==null) return;

	returnArray[0] = getValueById(myGrid, selectedId,"invtlistId"); // ID

	var dirType = "";
	
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
<html:form action="/lovInvtList.do">
<html:hidden property="strutsAction"/>

<html:hidden property="currentPageId"/>
<html:hidden property="keyCode" /><html:hidden property="chName" />
<html:hidden property="resultCol" /><html:hidden property="multiSelect"/>
<html:hidden property="codeType" />
<html:hidden property="param" />
<html:hidden property="label" />
<html:hidden property="title" />

<html:hidden property="lovInvtListDTO.filterDeptId"/>
<html:hidden property="lovInvtListDTO.filterEmpId"/>
<html:hidden property="lovInvtListDTO.filterInvtStatus"/>
<html:hidden property="lovInvtListDTO.filterInvtCateg"/>
<html:hidden property="lovInvtListDTO.filterEqlocId"/>
<html:hidden property="lovInvtListDTO.filterEqctgId"/>
<html:hidden property="lovInvtListDTO.filterEquipId"/>

<html:hidden property="lovInvtListDTO.invtlistId"/>
<html:hidden property="lovInvtListDTO.filterInvtType"/>
<html:hidden property="lovInvtListDTO.filterPlantId"/>

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
		<div class="article_box" >
			<div class="form_box">
				<!-- 투자시기 -->
				<div class="field">
					<label><bean:message key="LABEL.invtSDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="lovInvtListDTO.filterStartDate" tabindex="3" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="lovInvtListDTO.filterEndDate" tabindex="6" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 투자명 -->
				<div class="field">
					<label><bean:message key="LABEL.eqInvtDesc"/></label>
					<div class="input_box">
						<html:text property="lovInvtListDTO.filterInvtDesc" tabindex="10"/>
					</div>
				</div>
				<!-- 진행단계 -->
				<div class="field">
					<label><bean:message key="LABEL.invtStatus"/></label>
					<div class="input_box">
						<html:text property="lovInvtListDTO.filterInvtStatusDesc" tabindex="20"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 투자부서 -->
				<div class="field">
					<label><bean:message key="LABEL.invtDept"/></label>
					<div class="input_box">
						<html:text property="lovInvtListDTO.filterDeptDesc" tabindex="30"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 담당자 -->
				<div class="field">
					<label><bean:message key="LABEL.manager"/></label>
					<div class="input_box">
						<html:text property="lovInvtListDTO.filterEmpDesc" tabindex="40"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 투자구분 -->
				<div class="field">
					<label><bean:message key="LABEL.invtCategDesc"/></label>
					<div class="input_box">
						<html:text property="lovInvtListDTO.filterInvtCategDesc" tabindex="50"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 분류 -->
                <div class="field">
                    <label><bean:message key="LABEL.category"/></label>
                    <div class="input_box">
                        <html:text property="lovInvtListDTO.filterInvtTypeDesc" tabindex="55"/>
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
				<!-- 설비위치 -->
				<div class="field">
					<label><bean:message key="LABEL.location"/></label>
					<div class="input_box">
						<html:text property="lovInvtListDTO.filterEqlocDesc" tabindex="60"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 설비종류 -->
				<div class="field">
					<label><bean:message key="LABEL.eqCtg"/></label>
					<div class="input_box">
						<html:text property="lovInvtListDTO.filterEqctgDesc" tabindex="70"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 투자계획 -->
				<div class="field">
					<label><bean:message key="LABEL.invtlistNo"/></label>
					<div class="input_box">
						<html:text property="lovInvtListDTO.filterInvtlistNo" tabindex="80"/>
					</div>
				</div>
				<!-- 설비 -->
				<div class="field">
					<label><bean:message key="LABEL.equipDesc"/></label>
					<div class="input_box">
						<html:text property="lovInvtListDTO.filterEquipDesc" tabindex="90"/>
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
							<html:text property="lovInvtListDTO.filterPlantDesc" tabindex="180" />
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
		<div class="article_box" >
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>		
			</div>			
		</div>
	</div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>