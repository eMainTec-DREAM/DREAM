<%--===========================================================================
설비별고장분석
author  kim21017
version $Id: maBmEqList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.mabmeqlist.action.MaBmEqListAction" %>
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
<!-- 설비별고장분석 -->
<title><bean:message key='MENU.BMEQLIST'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var myGrid;
var selectedInd;

var deptAc;
var equipDescAc;
var eqLocDescAc;
var eqCtgTypeAc;
var mainMngAc;
var subMngAc;
var plantAc;

function loadPage() 
{
	//년도 세팅
	maBmEqListForm.elements['maBmEqListDTO.filterYyyy'].value = dateToData(getToday()).substr(0, 4);

	//부서정보 세팅
	maBmEqListForm.elements['maBmEqListDTO.filterDeptId'].value    = loginUser.filterDeptId;
	maBmEqListForm.elements['maBmEqListDTO.filterDeptDesc'].value  = loginUser.filterDeptDesc;
	if(loginUser.eqLocId!='null'){
		maBmEqListForm.elements['maBmEqListDTO.filterEqLocId'].value = loginUser.eqLocId;
		maBmEqListForm.elements['maBmEqListDTO.filterEqLocDesc'].value = loginUser.eqLocDesc;
	}
	
	//공장명
    if(loginUser.filterPlant!='null'){
    	maBmEqListForm.elements['maBmEqListDTO.filterPlantId'].value  = loginUser.filterPlant;
    	maBmEqListForm.elements['maBmEqListDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
	}
	
    initGrid();
    

	deptAc = new autoC({"maBmEqListDTO.filterDeptDesc":"description"});
    deptAc.setAcDisplay("DESCRIPTION");
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "maBmEqListDTO.filterDeptId":"dept_id"
    });
    deptAc.setAcDConditionMap({
    	"plant" : "maBmEqListDTO.filterPlantId"
    });
    deptAc.init();
    
    equipDescAc = new autoC({"maBmEqListDTO.filterEquipDesc":"description"});
    equipDescAc.setAcDisplay("DESCRIPTION");
    equipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcResultMap({
        "maBmEqListDTO.filterEquipId":"equip_id"
    });
    equipDescAc.setAcDConditionMap({
    	"eqloc_id" : "maBmEqListDTO.filterEqLocId",
    	"eqctg_id" : "maBmEqListDTO.filterEqCtgId",
    	"dept_id" : "maBmEqListDTO.filterDeptId",
    	"plant" : "maBmEqListDTO.filterPlantId"
    });
    equipDescAc.init();
    
    eqLocDescAc = new autoC({"maBmEqListDTO.filterEqLocDesc":"full_desc"});
    eqLocDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setAcResultMap({
        "maBmEqListDTO.filterEqLocId":"eqloc_id"
    });
    eqLocDescAc.setAcDConditionMap({
    	"plant" : "maBmEqListDTO.filterPlantId"
    });
    eqLocDescAc.init();
    
    eqCtgTypeAc = new autoC({"maBmEqListDTO.filterEqCtgDesc":"full_desc"});
    eqCtgTypeAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqCtgTypeAc.setTable("TAEQCTG");
    eqCtgTypeAc.setAcResultMap({
        "maBmEqListDTO.filterEqCtgId":"eqctg_id"
    });
    eqCtgTypeAc.init();

    //법정설비여부 AC
    acSysDesc("maBmEqListDTO.filterIsLawEq","maBmEqListDTO.filterIsLawEq","IS_USE",true);
    
    mainMngAc = new autoC({"maBmEqListDTO.filterMainMngName":"emp_name"});
    mainMngAc.setAcDisplay("EMP_NAME");
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcResultMap({
        "maBmEqListDTO.filterMainMngId":"emp_id"
    });
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "maBmEqListDTO.filterDeptId",
    	"plant" : "maBmEqListDTO.filterPlantId"
    });
    mainMngAc.init();
    
    subMngAc = new autoC({"maBmEqListDTO.filterSubMngName":"emp_name"});
    subMngAc.setAcDisplay("EMP_NAME");
    subMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    subMngAc.setTable("TAEMP");
    subMngAc.setAcResultMap({
        "maBmEqListDTO.filterSubMngId":"emp_id"
    });
    subMngAc.setAcDConditionMap({
    	"dept_id" : "maBmEqListDTO.filterDeptId",
    	"plant" : "maBmEqListDTO.filterPlantId"
    });
    subMngAc.init();
    
 	// 공장코드
	plantAc = new autoC({"maBmEqListDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "maBmEqListDTO.filterPlantId":"plant"
    });
    plantAc.init();
}

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.setHeader("<bean:message key='LABEL.dept'/>"
    			   +",<bean:message key='LABEL.location'/>"
    			   +",<bean:message key='LABEL.eqType'/>"
    			   +",<bean:message key='LABEL.equipNo'/>"
    			   +",<bean:message key='LABEL.equipName'/>"
    			   +",<bean:message key='LABEL.bmCtg'/>"
    			   +",<bean:message key='LABEL.bmCnt'/>"
    			   +",<bean:message key='LABEL.bmTime'/>"
    			   +",<bean:message key='LABEL.caCd'/>"
    			   +",<bean:message key='LABEL.reCdResult'/>"
    			   +",DEPTID,EQLOCID,EQCTGID,BMCTGID"
    			   );
	myGrid.setColumnIds("DEPTDESC,EQLOCDESC,EQCTGDESC,EQUIPNO,EQDESC,BMCTGDESC,BMCNT,BMTIME,CACD,RECDRESULT,DEPTID,EQLOCID,EQCTGID,BMCTGID");
	myGrid.setInitWidths("100,200,100,100,100,100,100,100,100,100,100,100,100,100");
	myGrid.setColAlign("left,left,left,center,left,left,right,right,center,center,left,left,left,left");
	myGrid.setColTypes("ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro");
	myGrid.setColumnHidden(10,true);
	myGrid.setColumnHidden(11,true);
	myGrid.setColumnHidden(12,true);
	myGrid.setColumnHidden(13,true);

	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox");setRowSpan(myGrid,'DEPTDESC'); });
	myGrid.attachEvent("onRowSelect", function(id,ind){
		selectedId = id;
		selectedInd = ind;
	});
	myGrid.attachEvent("onRowDblClicked", function(id,ind){
		goWoList(id,ind);
	});
	myGrid.sortRows(0,"str","asc");
	myGrid.init();

	goSearch();
}

function goWoList(id,ind){
	if(typeof id=="undefined"||typeof ind=="undefined") return ;
	
	var yyyy = maBmEqListForm.elements['maBmEqListDTO.filterYyyy'].value;
	var fromYyyyMmDd = yyyy+"0101";
	var toYyyyMmDd = yyyy+"1231";
	//공장
	var plant = maBmEqListForm.elements['maBmEqListDTO.filterPlantId'].value;
	var plantDesc = maBmEqListForm.elements['maBmEqListDTO.filterPlantDesc'].value;
	//부서정보
	releaseRowSpan(myGrid,'DEPTDESC');
	var deptId = myGrid.cells(id, myGrid.getColIndexById("DEPTID")).getValue();
	var deptDesc = '';
	if(deptId != '') deptDesc = myGrid.cells(id, myGrid.getColIndexById("DEPTDESC")).getValue();
	else {
		deptId = maBmEqListForm.elements['maBmEqListDTO.filterDeptId'].value;
		deptDesc = maBmEqListForm.elements['maBmEqListDTO.filterDeptDesc'].value;
	}
	setRowSpan(myGrid,'DEPTDESC');
	//라인
	var locId = myGrid.cells(id, myGrid.getColIndexById("EQLOCID")).getValue();
	var locDesc = myGrid.cells(id, myGrid.getColIndexById("EQLOCDESC")).getValue();
	//종류
	var ctgId = myGrid.cells(id, myGrid.getColIndexById("EQCTGID")).getValue();
	var ctgDesc = myGrid.cells(id, myGrid.getColIndexById("EQCTGDESC")).getValue();
	//설비명
	var equipDesc = myGrid.cells(id, myGrid.getColIndexById("EQDESC")).getValue();
	//부위
	var bmCtgId = myGrid.cells(id, myGrid.getColIndexById("BMCTGID")).getValue();
	var bmCtgDesc = myGrid.cells(id, myGrid.getColIndexById("BMCTGDESC")).getValue();
	//원인
	var caCd = myGrid.cells(id, myGrid.getColIndexById("CACD")).getValue();
	//원인 내용이 없는 것을 구분
	var notCaCd = "0";
	if(caCd==""){
		notCaCd = "1";
	}
	//조치
	var reCd = myGrid.cells(id, myGrid.getColIndexById("RECDRESULT")).getValue();
	//조치 내용이 없는 것을 구분
	var notReCd = "0";
	if(reCd==""){
		notReCd = "1";
	}
	if(equipDesc==""){
		notCaCd = "";
		notReCd = "";
	}
	var url   = contextPath + "/maWoResultMstrList.do";
	//팝업사이즈
	var popWidth = 1010;
	var popHeight = 640;
    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
	
	var param = "isDecoratorName=popupPage"+
				"&maWoResultMstrCommonDTO.strutsAction="+
				"&maWoResultMstrCommonDTO.filterStartDate="+fromYyyyMmDd+
				"&maWoResultMstrCommonDTO.filterEndDate="+toYyyyMmDd+
				"&maWoResultMstrCommonDTO.filterWoTypeId=BM"+
				"&maWoResultMstrCommonDTO.filterWoStatus=C"+
				"&maWoResultMstrCommonDTO.filterPlantId="+plant+
				"&maWoResultMstrCommonDTO.filterPlantDesc="+plantDesc+
				"&maWoResultMstrCommonDTO.filterDeptId="+deptId+
				"&maWoResultMstrCommonDTO.filterDeptDesc="+deptDesc+
				"&maWoResultMstrCommonDTO.filterEqLocId="+locId+
				"&maWoResultMstrCommonDTO.filterEqLocDesc="+locDesc+
				"&maWoResultMstrCommonDTO.filterEqCtgId="+ctgId+
				"&maWoResultMstrCommonDTO.filterEqCtgDesc="+ctgDesc+
				"&maWoResultMstrCommonDTO.filterEquipDesc="+equipDesc+
				"&maWoResultMstrCommonDTO.filterEqAsmbId="+bmCtgId+
				"&maWoResultMstrCommonDTO.filterEqAsmbDesc="+bmCtgDesc+
				"&maWoResultMstrCommonDTO.caDesc="+ caCd +
				"&maWoResultMstrCommonDTO.reDesc="+reCd+
				"&maWoResultMstrCommonDTO.notCaCd="+ notCaCd +
				"&maWoResultMstrCommonDTO.notReCd="+notReCd;
	//post 전송
	openWindowWithPost(url, "CHART_WO_LIST_POPUP", param, pos);
}
function goWo(){
	goWoList(selectedId,selectedInd);
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maBmEqList.do";

    maBmEqListForm.elements['strutsAction'].value = '<%=MaBmEqListAction.BM_LIST_FIND%>';
    
    myGrid.clearAll(); 
    setLoading("gridbox");
    $.post(url,FormQueryString(maBmEqListForm), function(_data){
    	myGrid.parse(_data,"js");
    });
}

/**
 * 엑셀 다운.
 */
 function goExcel()
 {
 	excelAction(myGrid);
 }

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
    findGridList('Search');   
}

function releaseRowSpan(_grid, columnId)
{
	var topValue = "";
	for(var i = 0; _grid.getRowsNum() > i; i++)
	{
		var cellValue = _grid.cellById(_grid.getRowId(i), getIndexById(_grid, columnId)).getValue();
		if(cellValue != "")
		{
			_grid.setRowspan(_grid.getRowId(i) ,getIndexById(_grid, columnId),1);
			topValue = cellValue;
		}
		else
		{
			_grid.cellById(_grid.getRowId(i), getIndexById(_grid, columnId)).setValue(topValue);
		}
	}
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maBmEqList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maBmEqListDTO.filterDeptId"/>
<html:hidden property="maBmEqListDTO.filterMainMngId"/>
<html:hidden property="maBmEqListDTO.filterSubMngId"/>
<html:hidden property="maBmEqListDTO.filterEqLocId"/>
<html:hidden property="maBmEqListDTO.filterEqCtgId"/>
<html:hidden property="maBmEqListDTO.filterEquipId"/>
<html:hidden property="maBmEqListDTO.filterPlantId"/>
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
				<!-- 년 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.year"/></label>
					<div class="input_read">
						<html:text property="maBmEqListDTO.filterYyyy" tabindex="10" readonly="true"/>
						<p class="open_year_calendar"><span>날짜</span></p>
					</div>
				</div>
				<!-- 부서 -->
				<div class="field">
					<label><bean:message key="LABEL.dept"/></label>
					<div class="input_box">
						<html:text property="maBmEqListDTO.filterDeptDesc" tabindex="20" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 위치 -->
				<div class="field">
					<label><bean:message key="LABEL.location"/></label>
					<div class="input_box">
						<html:text property="maBmEqListDTO.filterEqLocDesc" tabindex="30"/>
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
						<html:text property="maBmEqListDTO.filterEqCtgDesc" tabindex="40" />
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 설비-->
				<div class="field">
					<label><bean:message key="LABEL.equipDesc"/></label>
					<div class="input_box">
						<html:text property="maBmEqListDTO.filterEquipDesc" tabindex="50" />
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
						<html:text property="maBmEqListDTO.filterMainMngName" tabindex="60" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 관리자(부) -->
				<div class="field">
					<label><bean:message key="LABEL.subManager"/></label>
					<div class="input_box">
						<html:text property="maBmEqListDTO.filterSubMngName" tabindex="70" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 법정설비여부 -->
				<div class="field">
					<label><bean:message key="LABEL.isLawEq"/></label>
					<div class="input_box">
						<html:text property="maBmEqListDTO.filterIsLawEq" tabindex="80" />
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
							<html:text property="maBmEqListDTO.filterPlantDesc"
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
				<div id="gridbox" style="width:100%; height:410px; background-color:white;"></div>
			</div>
		</div>
	</div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>