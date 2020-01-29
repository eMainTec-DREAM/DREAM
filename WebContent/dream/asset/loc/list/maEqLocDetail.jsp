<%--===========================================================================
설비위치 - 상세
author  kim21017
version $Id: maEqLocDetail.jsp,v 1.5 2014/07/02 04:13:54 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.asset.loc.list.action.MaEqLocDetailAction"%>
<html:html>
<head>
<!--설비위치 -->
<title><bean:message key='LABEL.eqLocNo' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">

var plantTypeAc;
var eqLocDescAc;
var mesLineAc;
var ctCtrAc;
var wkCtrAc;
var isOperationAc;
var isKpiAc;
var lnWrkDescAc;
function loadPage() 
{
	setTitle("maEqLocDetailDTO.eqLocNo","maEqLocDetailDTO.eqLocDesc");
	//For Save
	setForUpdate();
	
	plantTypeAc = new autoC({"maEqLocDetailDTO.plantDesc":"description"});
	plantTypeAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
   		, "is_use":"Y"
  	   });
	plantTypeAc.setTable("TAPLANT");
	plantTypeAc.setKeyName("maEqLocDetailDTO.plant");
	plantTypeAc.setAcResultMap({
        "maEqLocDetailDTO.plant":"plant"
    });
	plantTypeAc.init();
	
	eqLocDescAc = new autoC({"maEqLocDetailDTO.peqLocDesc":"full_desc"});
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqLocDescAc.setAcDConditionMap({
    	"plant" : "maEqLocDetailDTO.plant"
    });
    eqLocDescAc.setKeyName("maEqLocDetailDTO.peqLocId");
    eqLocDescAc.setAcResultMap({
        "maEqLocDetailDTO.peqLocId":"eqloc_id"
    });
    eqLocDescAc.init();

    
    mesLineAc = new autoC({"maEqLocDetailDTO.mesLineDesc":"mes_line_name"});
    mesLineAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    mesLineAc.setTable("TAMESLINE");
    mesLineAc.setKeyName("maEqLocDetailDTO.mesLineId");
    mesLineAc.setAcResultMap({
        "maEqLocDetailDTO.mesLineId":"mes_line_id"
    });
    mesLineAc.init();
    
    ctCtrAc = new autoC({"maEqLocDetailDTO.ctCtrDesc":"description"});
    ctCtrAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    ctCtrAc.setTable("TACTCTR");
    ctCtrAc.setKeyName("maEqLocDetailDTO.ctCtrId");
    ctCtrAc.setAcResultMap({
        "maEqLocDetailDTO.ctCtrId":"ctctr_id"
    });
    ctCtrAc.init();
    
    wkCtrAc = new autoC({"maEqLocDetailDTO.wkCtrDesc":"description"});
    wkCtrAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   ,"is_use":"Y"
 	   });
    wkCtrAc.setTable("TAWKCTR");
    wkCtrAc.setKeyName("maEqLocDetailDTO.wkCtrId");
    wkCtrAc.setAcResultMap({
        "maEqLocDetailDTO.wkCtrId":"wkctr_id"
    });
    wkCtrAc.init();
    
	lnWrkDescAc = new autoC({"maEqLocDetailDTO.lnWrkListDesc":"description"});
	lnWrkDescAc.setAcConditionMap({
		"comp_no": loginUser.compNo
  	   });
	lnWrkDescAc.setTable("TALNWRKLIST");
	lnWrkDescAc.setKeyName("maEqLocDetailDTO.lnWrkListId");
	lnWrkDescAc.setAcResultMap({
        "maEqLocDetailDTO.lnWrkListId":"lnWrkListId"
    });
	lnWrkDescAc.init();

    acSysDesc("maEqLocDetailDTO.levelTypeDesc","maEqLocDetailDTO.levelType","EQLOC_LVL_TYPE",true);
    acSysDesc("maEqLocDetailDTO.isUse","maEqLocDetailDTO.isUse","IS_USE",true);
    acSysDesc("maEqLocDetailDTO.isBdLoc","maEqLocDetailDTO.isBdLoc","IS_USE",true);
    acSysDesc("maEqLocDetailDTO.isKpi","maEqLocDetailDTO.isKpi","IS_USE",true);
    acSysDesc("maEqLocDetailDTO.isOperation","maEqLocDetailDTO.isOperation","IS_USE",true);
    
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
}
/* 
 * 설비보기
 */
function goMachequipmentLink(_pageId)
{
	var eqLocId = maEqLocDetailForm.elements['maEqLocDetailDTO.eqLocId'].value;
	var eqLocDesc = maEqLocDetailForm.elements['maEqLocDetailDTO.eqLocDesc'].value;
	
	if(typeof eqLocId=="undefined" &&typeof eqLocDesc=="undefined") return;
	
	goEquipList(eqLocId, eqLocDesc, '', '');
}


function goUpdate(){
	setDisable(document.getElementById("isBdLoc"));
}

function goInput()
{
	sequenceNextVal('SQAEQLOC_ID');
	
	maEqLocDetailForm.elements['maEqLocDetailDTO.isUse'].value = 'Y';
	maEqLocDetailForm.elements['maEqLocDetailDTO.isBdLoc'].value = 'N';
	maEqLocDetailForm.elements['maEqLocDetailDTO.peqLocId'].value = maEqLocDetailForm.elements['maEqLocCommonDTO.detailPEqLocId'].value;
	maEqLocDetailForm.elements['maEqLocDetailDTO.peqLocDesc'].value = maEqLocDetailForm.elements['maEqLocCommonDTO.detailPEqLocDesc'].value;
	
	plantTypeAc.openLov();
}
function setSequenceVal(sequenceVal)
{
	maEqLocDetailForm.elements['maEqLocDetailDTO.eqLocId'].value = sequenceVal;
	maEqLocDetailForm.elements['maEqLocDetailDTO.eqLocNo'].value = sequenceVal;
	maEqLocDetailForm.elements['maEqLocCommonDTO.eqLocId'].value = sequenceVal;
}

/**
 * 저장
 */ 
function goSave()
{
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	//strutsAction 셋팅.
	if(ckCreate(currentPageId)) maEqLocDetailForm.strutsAction.value = "<%=MaEqLocDetailAction.EQ_LOC_DETAIL_INPUT%>";
	else maEqLocDetailForm.strutsAction.value = "<%=MaEqLocDetailAction.EQ_LOC_DETAIL_UPDATE%>";
	
	var actionUrl = contextPath + "/maEqLocDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maEqLocDetailForm), 'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    maEqLocDetailForm.elements['maEqLocDetailDTO.eqLocId'].value = maEqLocDetailForm.elements['maEqLocCommonDTO.eqLocId'].value;
    //parent.goSearch();
    if (parent.findGridRow)	parent.findGridRow(maEqLocDetailForm.elements['maEqLocDetailDTO.eqLocId'].value);
    getTopPage().afterSaveAll(currentPageId);
}

function goAudtrailLink()
{
	var objectId = maEqLocDetailForm.elements['maEqLocDetailDTO.eqLocId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}


//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/maEqLocDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maEqLocCommonDTO.eqLocId" />
	<html:hidden property="maEqLocCommonDTO.detailPEqLocId"/>
	<html:hidden property="maEqLocCommonDTO.detailPEqLocDesc"/>
	<html:hidden property="maEqLocDetailDTO.eqLocId" />
	<html:hidden property="maEqLocDetailDTO.peqLocId" />
	<html:hidden property="maEqLocDetailDTO.ctCtrId" />
	<html:hidden property="maEqLocDetailDTO.wkCtrId" />
	<html:hidden property="maEqLocDetailDTO.mesLineId" />
	<html:hidden property="maEqLocDetailDTO.levelType" />
	<html:hidden property="maEqLocDetailDTO.plant" />
	<html:hidden property="maEqLocDetailDTO.bdEquipId" />
	<html:hidden property="maEqLocDetailDTO.lnWrkListId" />		<!-- 가동달력 ID -->
	<div class="article_box" id="detailBox">
		<div class="form_box">
		    
		    <div class="field">
				<label class="check"><bean:message key="LABEL.plant"/></label>
				<div class="input_box">
					<html:text property="maEqLocDetailDTO.plantDesc" tabindex="10"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
		    <div class="field">
				<label class="check"><bean:message key="LABEL.separation"/></label>
				<div class="input_box">
					<html:text property="maEqLocDetailDTO.levelTypeDesc" tabindex="20"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<div class="field">
				<label><bean:message key="LABEL.pEqLocName"/></label>
				<div class="input_box">
					<html:text property="maEqLocDetailDTO.peqLocDesc" tabindex="30"/>
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<div class="field">
				<label><bean:message key="LABEL.isUse"/></label>
				<div class="input_box">
					<html:text property="maEqLocDetailDTO.isUse" tabindex="40"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			
			<div class="field">
				<label class="check"><bean:message key="LABEL.eqLocName"/></label>
				<div class="input_box">
					<html:text property="maEqLocDetailDTO.eqLocDesc" tabindex="50"/>
				</div>
			</div>
			
			<div class="field" id="isBdLoc" >
				<label><bean:message key="LABEL.isBdLoc"/></label>
				<div class="input_box">
					<html:text property="maEqLocDetailDTO.isBdLoc" tabindex="55"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			
			
			<div class="field">
				<label><bean:message key="LABEL.eqLocNo"/></label>
				<div class="input_box">	
					<html:text property="maEqLocDetailDTO.eqLocNo" tabindex="56"/>
				</div>
			</div>
			
			<div class="field">
				<label><bean:message key="LABEL.wkCtrDesc"/></label>
				<div class="input_box">
					<html:text property="maEqLocDetailDTO.wkCtrDesc" tabindex="57"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			
			<div class="field">
				<label><bean:message key="LABEL.mesLine"/></label>
				<div class="input_box">
					<html:text property="maEqLocDetailDTO.mesLineDesc" tabindex="60"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			
			<div class="field">
				<label><bean:message key="LABEL.cpDesc"/></label>
				<div class="input_box">
					<html:text property="maEqLocDetailDTO.ctCtrDesc" tabindex="70"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			
			<div class="field">
				<label><bean:message key="LABEL.isKpi"/></label>
				<div class="input_box">
					<html:text property="maEqLocDetailDTO.isKpi" tabindex="80"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			
			
			
			<div class="field">
				<label><bean:message key="LABEL.ordNo"/></label>
				<div class="input_box">
					<html:text property="maEqLocDetailDTO.ordNo" tabindex="90" />
				</div>
			</div>
			
			<div class="field">
                <label><bean:message key="LABEL.mngCd"/></label>
                <div class="input_box">
                    <html:text property="maEqLocDetailDTO.mngCd" tabindex="95" />
                </div>
            </div>
			
			<div class="field">
				<label><bean:message key="LABEL.isOperation"/></label>
				<div class="input_box">
					<html:text property="maEqLocDetailDTO.isOperation" tabindex="100"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 낮 가동시간 -->
			<div class="field">
				<label><bean:message key="LABEL.dayRunTime"/></label>
				<div class="input_box">
					<html:text property="maEqLocDetailDTO.dayRunTime" tabindex="110" styleClass="num"/>
				</div>
			</div>
			<!-- 저녁 가동시간 -->
			<div class="field">
				<label><bean:message key="LABEL.nightRunTime"/></label>
				<div class="input_box">
					<html:text property="maEqLocDetailDTO.nightRunTime" tabindex="120" styleClass="num"/>
				</div>
			</div>
			<!-- 새벽 가동시간 -->
			<div class="field">
				<label><bean:message key="LABEL.extraRunTime"/></label>
				<div class="input_box">
					<html:text property="maEqLocDetailDTO.extraRunTime" tabindex="130" styleClass="num"/>
				</div>
			</div>
			<!-- 가동달력명 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.lnWrkListDesc"/></label>
				<div class="input_box">
					<html:text property="maEqLocDetailDTO.lnWrkListDesc" tabindex="10"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 설비보유갯수 -->
			<div class="field">
				<label><bean:message key="LABEL.EQCNT"/></label>
				<div class="input_read">
					<html:text property="maEqLocDetailDTO.eqCnt" readonly="true"/>
				</div>
			</div>
			
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="maEqLocDetailDTO.remark" styleClass="ta50" tabindex="140" />
				</div>
			</div>
			
			
			
			
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>
