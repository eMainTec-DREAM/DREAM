<%--===========================================================================
교정주기 상세
author  kim21017
version $Id: assetListTcycleDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="dream.asset.list.action.AssetListTcycleDetailAction"%>
<%@ page import="common.bean.User"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId());
%>
<html>
<head>
<!-- 설비제원-->
<title><bean:message key="TAB.assetListTcycleList"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var wkCtrDescAc;
var wrkCalAc;
var plantAc;

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	
	setTitle("assetListTcycleDetailDTO.cycle","assetListTcycleDetailDTO.periodTypeDesc");
	setForUpdate();
	
	wkCtrDescAc = new autoC({"assetListTcycleDetailDTO.wkCtrDesc":"description"});
    wkCtrDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    wkCtrDescAc.setTable("TAWKCTR");
    //wkCtrDescAc.setCustomLov("lovWkCtr('assetListTcycleDetailDTO.wkCtrId', 'assetListTcycleDetailDTO.wkCtrDesc')");
    wkCtrDescAc.setAcResultMap({
        "assetListTcycleDetailDTO.wkCtrId":"wkctr_id"
    });
    wkCtrDescAc.init();
    
    wrkCalAc = new autoC({"assetListTcycleDetailDTO.wrkcalListDesc":"description"});
	wrkCalAc.setAcConditionMap({
		"comp_no":loginUser.compNo
	  });
	wrkCalAc.setTable("TAWRKCALLIST");
	wrkCalAc.setKeyName("assetListTcycleDetailDTO.wrkcalListId");
	wrkCalAc.setAcResultMap({
	    "assetListTcycleDetailDTO.wrkcalListId":"wrkcallist_id"
	});
	wrkCalAc.init();
	 
	//공장 
    plantAc = new autoC({"assetListTcycleDetailDTO.plantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcDConditionMap({
    	"comp_no": loginUser.compNo
	});
    plantAc.setAcResultMap({
        "assetListTcycleDetailDTO.plant":"plant"
    });
    plantAc.init();
    
	acSysDesc("assetListTcycleDetailDTO.isActive","assetListTcycleDetailDTO.isActive","IS_USE");
	acSysDesc("assetListTcycleDetailDTO.periodTypeDesc","assetListTcycleDetailDTO.periodType","PERIOD_TYPE");
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAEQPMCYCLE_ID');
	
	assetListTcycleDetailForm.elements['assetListTcycleDetailDTO.woResBef'].value = "14";
	assetListTcycleDetailForm.elements['assetListTcycleDetailDTO.isActive'].value = "Y";
	assetListTcycleDetailForm.elements['assetListTcycleDetailDTO.revisionStatusId'].value = "W";
	assetListTcycleDetailForm.elements['assetListTcycleDetailDTO.isLastVersion'].value = "N";
	
	if("null" != "<%=user.getWkctrId()%>" && "undefined" != "<%=user.getWkctrId()%>")
	{
		assetListTcycleDetailForm.elements['assetListTcycleDetailDTO.wkCtrId'].value = "<%=user.getWkctrId()%>";
		assetListTcycleDetailForm.elements['assetListTcycleDetailDTO.wkCtrDesc'].value = "<%=user.getWkctrDesc()%>";	
	}
	
	assetListTcycleDetailForm.elements['assetListTcycleDetailDTO.plant'].value = $(searchPage("maEqToolMstrDetail").document).find("[name='maEqMstrDetailDTO.plant']").val();
	assetListTcycleDetailForm.elements['assetListTcycleDetailDTO.plantDesc'].value = $(searchPage("maEqToolMstrDetail").document).find("[name='maEqMstrDetailDTO.plantDesc']").val();

	if(assetListTcycleDetailForm.elements['assetListTcycleDetailDTO.plant'].value == "")
	{
		assetListTcycleDetailForm.elements['assetListTcycleDetailDTO.plant'].value = loginUser.plant;
		assetListTcycleDetailForm.elements['assetListTcycleDetailDTO.plantDesc'].value = loginUser.plantDesc;
	}
}

function setSequenceVal(sequenceVal)
{
	assetListTcycleDetailForm.elements['assetListTcycleDetailDTO.eqPmCycleId'].value = sequenceVal;
}

function goSave()
{
	if(checkValidation()) return;
	
	assetListTcycleDetailForm.elements['assetListTcycleDetailDTO.deptId'].value = parent.parent.maEqMstrDetailForm.elements['maEqMstrDetailDTO.deptId'].value;
	assetListTcycleDetailForm.elements['assetListTcycleDetailDTO.equipDesc'].value = parent.parent.maEqMstrDetailForm.elements['maEqMstrDetailDTO.equipDesc'].value;
	assetListTcycleDetailForm.elements['assetListTcycleDetailDTO.pmcTypeId'].value = parent.parent.maEqMstrDetailForm.elements['maEqToolMstrDetailDTO.pmcTypeId'].value;
	
	if(ckCreate(currentPageId)) assetListTcycleDetailForm.strutsAction.value = '<%=AssetListTcycleDetailAction.ASSET_LIST_TCYCLE_DETAIL_INPUT%>';
	else assetListTcycleDetailForm.strutsAction.value = '<%= AssetListTcycleDetailAction.ASSET_LIST_TCYCLE_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/assetListTcycleDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(assetListTcycleDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(assetListTcycleDetailForm.elements['assetListTcycleDetailDTO.eqPmCycleId'].value);
    getTopPage().afterSaveAll(currentPageId);
        
    setRtnValue(ajaxXmlDoc);
}

function setRtnValue(ajaxXmlDoc)
{
	var data = '0';
	var pmId = "";
	var pmequipId = "";
	
	data = parseXmlDoc(ajaxXmlDoc, 'DESC');
	data = data.toString();

	if(data != '0')
    {
		dataArr = data.split(',');
		
		pmId = dataArr[0];
		pmequipId = dataArr[1];
    }
	
    if(""==assetListTcycleDetailForm.elements['assetListTcycleDetailDTO.pmId'].value)
    	assetListTcycleDetailForm.elements['assetListTcycleDetailDTO.pmId'].value = pmId;
    
    if(""==assetListTcycleDetailForm.elements['assetListTcycleDetailDTO.pmEquipId'].value)
    	assetListTcycleDetailForm.elements['assetListTcycleDetailDTO.pmEquipId'].value = pmequipId;
    
}

function goAudtrailLink()
{
	var objectId = assetListTcycleDetailForm.elements['assetListTcycleDetailDTO.eqPmCycleId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}



//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/assetListTcycleDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="maEqMstrCommonDTO.equipId"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="assetListTcycleDetailDTO.equipDesc"/>
	<html:hidden property="assetListTcycleDetailDTO.deptId" />
	<html:hidden property="assetListTcycleDetailDTO.eqPmCycleId"/>
	<html:hidden property="assetListTcycleDetailDTO.pmId"/>
	<html:hidden property="assetListTcycleDetailDTO.wkCtrId"/>
	<html:hidden property="assetListTcycleDetailDTO.periodType"/>
	<html:hidden property="assetListTcycleDetailDTO.pmcTypeId"/>
	<html:hidden property="assetListTcycleDetailDTO.wrkcalListId" />
	<html:hidden property="assetListTcycleDetailDTO.pmEquipId" />
	<html:hidden property="assetListTcycleDetailDTO.revisionStatusId" />
	<html:hidden property="assetListTcycleDetailDTO.isLastVersion" />
	<html:hidden property="assetListTcycleDetailDTO.plant" />
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 주기 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.cycleDesc"/></label>
				<div class="datetime_wrap">
					<div class="input_box">
						<html:text property="assetListTcycleDetailDTO.cycle" tabindex="5"/>
					</div>
					<div class="input_box">
						<html:text property="assetListTcycleDetailDTO.periodTypeDesc" tabindex="10" />
						<p class="open_spop">
							<a><span>조회</span></a>
						</p>
					</div>
				</div>
			</div>
			<!-- 최초작업예정일 -->
			<div class="field">
				<label><bean:message key="LABEL.initWrkDate"/></label>
				<div class="input_box">
					<html:text property="assetListTcycleDetailDTO.initWrkDate" tabindex="15"/>
					<p class="open_calendar"><span>날짜</span></p>
				</div>
			</div>
			<!-- 몇일전 작업알림 -->
			<div class="field">
				<label><bean:message key="LABEL.woResBef"/></label>
				<div class="input_box">
					<html:text property="assetListTcycleDetailDTO.woResBef" tabindex="20"/>
				</div>
			</div>
			<!-- 작업그룹 -->
			<div class="field">
				<label><bean:message key="LABEL.wkCtr"/></label>
				<div class="input_box">
					<html:text property="assetListTcycleDetailDTO.wkCtrDesc" tabindex="25"/>
					<p class="open_spop">
						<a><span>조회</span></a>
					</p>
				</div>
			</div>
			<!-- 시행여부 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.isActive"/></label>
				<div class="input_box">
					<html:text property="assetListTcycleDetailDTO.isActive" tabindex="30"/>
                    <p class="open_spop">
                        <a><span>조회</span></a>
                    </p>
				</div>
			</div>
			<!-- 교정일자 -->
			<div class="field">
				<label><bean:message key="LABEL.planCalDate"/></label>
				<div class="input_read">
					<html:text property="assetListTcycleDetailDTO.calDate" tabindex="40" readonly="true" />
				</div>
			</div>
			<!-- 차기 교정일 -->
			<div class="field">
				<label><bean:message key="LABEL.nextPlanCalDate"/></label>
				<div class="input_read">
					<html:text property="assetListTcycleDetailDTO.nextCalDate" tabindex="50" readonly="true" />
				</div>
			</div>
			<!--근무달력 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.workCal"/></label>
				<div class="input_box">
					<html:text property="assetListTcycleDetailDTO.wrkcalListDesc" tabindex="60" />
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 공장 -->
			<div class="field">
				<label><bean:message key="LABEL.plant"/></label>
				<div class="input_box">
					<html:text property="assetListTcycleDetailDTO.plantDesc" tabindex="172"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>	
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="assetListTcycleDetailDTO.remark" styleClass="ta50" tabindex="70"/>
				</div>
			</div>
		</div><!--article_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>