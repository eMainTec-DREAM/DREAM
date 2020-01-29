<%--===========================================================================
가동달력설정- 상세
author  euna0207
version $Id: mgrCalLineTimeSetDetail.jsp,v 1.5 2014/07/02 04:13:54 euna0207 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%> 
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.mgr.cal.action.MgrCalLineTimeSetAction"%>
<html:html>
<head>
<!--설비위치 -->
<title><bean:message key='LABEL.lnWrkListNo' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">

var compDescAc, eqLocDescAc, wrkCalDescAc, plantAc, equipNameAc;
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else 
	{
		goUpdate();
	}
	
	setTitle("mgrCalLineTimeSetDTO.lnWrkListNo","mgrCalLineTimeSetDTO.lnWrkListDesc");
	//For Save
	setForUpdate();
	
	compDescAc = new autoC({"mgrCalLineTimeSetDTO.compDesc":"description"});
	compDescAc.setTable("TACOMP");
	compDescAc.setKeyName("mgrCalLineTimeSetDTO.compNo");
	compDescAc.setAcResultMap({
        "mgrCalLineTimeSetDTO.compNo":"comp_no"
        ,"mgrCalLineTimeSetDTO.compNo":"comp_no"
    });
	compDescAc.init();
    
	eqLocDescAc = new autoC({"mgrCalLineTimeSetDTO.eqLocDesc":"description"});
	eqLocDescAc.setAcDConditionMap({
    	"comp_no":"mgrCalLineTimeSetDTO.compNo",
    	"plant" : "mgrCalLineTimeSetDTO.plantId"
  	   });
	eqLocDescAc.setTable("TAEQLOC");
	eqLocDescAc.setKeyName("mgrCalLineTimeSetDTO.eqLocId");
	eqLocDescAc.setAcResultMap({
        "mgrCalLineTimeSetDTO.eqLocId":"eqloc_id"
    });
	eqLocDescAc.init();
    
	wrkCalDescAc = new autoC({"mgrCalLineTimeSetDTO.wrkCalListDesc":"description"});
	wrkCalDescAc.setAcDConditionMap({
    	"comp_no":"mgrCalLineTimeSetDTO.compNo"
  	   });
	wrkCalDescAc.setTable("TAWRKCALLIST");
	wrkCalDescAc.setKeyName("mgrCalLineTimeSetDTO.wrkCalListId");
	wrkCalDescAc.setAcResultMap({
        "mgrCalLineTimeSetDTO.wrkCalListId":"wrkcallist_id"
    });
	wrkCalDescAc.init();
	
	plantAc = new autoC({"mgrCalLineTimeSetDTO.plantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcDConditionMap({
        "comp_no":"mgrCalLineTimeSetDTO.compNo"
    });
   	plantAc.setKeyName("mgrCalLineTimeSetDTO.plantId");
    plantAc.setAcResultMap({
        "mgrCalLineTimeSetDTO.plantId":"plant"
    });
    plantAc.init();
    
    equipNameAc = new autoC({"mgrCalLineTimeSetDTO.equipNameDesc":"description"});
    equipNameAc.setTable("TAEQUIPMENT");
    equipNameAc.setAcDConditionMap({
        "comp_no":"mgrCalLineTimeSetDTO.compNo"
    });
    equipNameAc.setKeyName("mgrCalLineTimeSetDTO.equipNameId");
    equipNameAc.setAcResultMap({
        "mgrCalLineTimeSetDTO.equipNameId":"equip_id"
    });
    equipNameAc.init();
	
	// 사용여부
    acSysDesc("mgrCalLineTimeSetDTO.isUse","mgrCalLineTimeSetDTO.isUse","IS_USE", true);
	
	//가동시간 설정방법
    acSysDesc("mgrCalLineTimeSetDTO.runTimeSettingDesc","mgrCalLineTimeSetDTO.runTimeSettingId","LNWRK_CREATE_TYPE", true);
	
}

function goInput()
{
	sequenceNextVal('SQALNWRKLIST_ID');
	
	mgrCalLineTimeSetForm.elements['mgrCalLineTimeSetDTO.isUse'].value = 'Y';
}

function setSequenceVal(sequenceVal)
{
	mgrCalLineTimeSetForm.elements['mgrCalLineTimeSetDTO.lnWrkListId'].value = sequenceVal;
	mgrCalLineTimeSetForm.elements['mgrCalLineTimeSetDTO.lnWrkListNo'].value = sequenceVal;
}

function goUpdate()
{
	
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
	if(ckCreate(currentPageId)) mgrCalLineTimeSetForm.strutsAction.value = "<%=MgrCalLineTimeSetAction.LINE_DETAIL_INPUT%>";
	else mgrCalLineTimeSetForm.strutsAction.value = "<%=MgrCalLineTimeSetAction.LINE_DETAIL_UPDATE%>";
	
	var actionUrl = contextPath + "/mgrCalLineTimeSetDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(mgrCalLineTimeSetForm), 'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    mgrCalLineTimeSetForm.elements['mgrCalLineTimeSetDTO.compNo'].value = mgrCalLineTimeSetForm.elements['mgrCalLineTimeSetDTO.compNo'].value;
    
    if (parent.findGridRow)	parent.findGridRow(mgrCalLineTimeSetForm.elements['mgrCalLineTimeSetDTO.lnWrkListId'].value);
    getTopPage().afterSaveAll(currentPageId);
}

function goTabPage(pageId)
{
	var form = document.mgrCalLineTimeSetForm;

	goCommonTabPage(form, '' , pageId);
    
}

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/mgrCalLineTimeSetDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="mgrCalLineTimeSetDTO.lnWrkListId" />
	<html:hidden property="mgrCalLineTimeSetDTO.compNo" />
	<html:hidden property="mgrCalLineTimeSetDTO.eqLocId" />
	<html:hidden property="mgrCalLineTimeSetDTO.wrkCalListId" />
	<html:hidden property="mgrCalLineTimeSetDTO.plantId" />
	<html:hidden property="mgrCalLineTimeSetDTO.runTimeSettingId" />
	<html:hidden property="mgrCalLineTimeSetDTO.equipNameId" />
	
	<div class="article_box" id="detailBox">
		<div class="form_box">
			<!-- 가동달력# -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.lnWrkListNo"/></label>
				<div class="input_read">
					<html:text property="mgrCalLineTimeSetDTO.lnWrkListNo" readonly="true"/>
				</div>
			</div>
			<!-- 가동달력명 -->
			<div class="field_long">
				<label class="check"><bean:message key="LABEL.lnWrkListDesc"/></label>
				<div class="input_box">
					<html:text property="mgrCalLineTimeSetDTO.lnWrkListDesc"  tabindex="20"/>
				</div>
			</div>
			
			<!-- 근무달력명 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.workCal"/></label>
				<div class="input_box">
					<html:text property="mgrCalLineTimeSetDTO.wrkCalListDesc" tabindex="30"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 사용여부 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.isUse"/></label>
				<div class="input_box">
					<html:text property="mgrCalLineTimeSetDTO.isUse" tabindex="40"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 공장명  -->
            <div class="field">
                <label><bean:message key="LABEL.plantDesc"/></label>
                <div class="input_box">
			<html:text property="mgrCalLineTimeSetDTO.plantDesc" tabindex="50" />
			<p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>
			<!-- 위치명 -->
			<div class="field">
				<label><bean:message key="LABEL.eqLocName"/></label>
				<div class="input_box">
					<html:text property="mgrCalLineTimeSetDTO.eqLocDesc" tabindex="60"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 설비명 -->
			<div class="field">
				<label><bean:message key="LABEL.equipName"/></label>
				<div class="input_box">
					<html:text property="mgrCalLineTimeSetDTO.equipNameDesc" tabindex="60"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 가동시간 설정방법 --> 
			<div class="field">
				<label><bean:message key="LABEL.runTimeSetting"/></label>
				<div class="input_box">
					<html:text property="mgrCalLineTimeSetDTO.runTimeSettingDesc" tabindex="60"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>									
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="mgrCalLineTimeSetDTO.remark" styleClass="ta50" tabindex="900" />
				</div>
			</div>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>
