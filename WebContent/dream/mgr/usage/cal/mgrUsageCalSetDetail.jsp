<%--===========================================================================
사용달력설정- 상세
author  youngjoo38
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%> 
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.mgr.usage.cal.action.MgrUsageCalSetAction"%>
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
	
	setTitle("mgrUsageCalSetDTO.lnWrkListNo","mgrUsageCalSetDTO.lnWrkListDesc");
	//For Save
	setForUpdate();
	
	compDescAc = new autoC({"mgrUsageCalSetDTO.compDesc":"description"});
	compDescAc.setTable("TACOMP");
	compDescAc.setKeyName("mgrUsageCalSetDTO.compNo");
	compDescAc.setAcResultMap({
        "mgrUsageCalSetDTO.compNo":"comp_no"
        ,"mgrUsageCalSetDTO.compNo":"comp_no"
    });
	compDescAc.init();
    
	eqLocDescAc = new autoC({"mgrUsageCalSetDTO.eqLocDesc":"description"});
	eqLocDescAc.setAcDConditionMap({
    	"comp_no":"mgrUsageCalSetDTO.compNo",
    	"plant" : "mgrUsageCalSetDTO.plantId"
  	   });
	eqLocDescAc.setTable("TAEQLOC");
	eqLocDescAc.setKeyName("mgrUsageCalSetDTO.eqLocId");
	eqLocDescAc.setAcResultMap({
        "mgrUsageCalSetDTO.eqLocId":"eqloc_id"
    });
	eqLocDescAc.init();
    
	wrkCalDescAc = new autoC({"mgrUsageCalSetDTO.wrkCalListDesc":"description"});
	wrkCalDescAc.setAcDConditionMap({
    	"comp_no":"mgrUsageCalSetDTO.compNo"
  	   });
	wrkCalDescAc.setTable("TAWRKCALLIST");
	wrkCalDescAc.setKeyName("mgrUsageCalSetDTO.wrkCalListId");
	wrkCalDescAc.setAcResultMap({
        "mgrUsageCalSetDTO.wrkCalListId":"wrkcallist_id"
    });
	wrkCalDescAc.init();
	
	plantAc = new autoC({"mgrUsageCalSetDTO.plantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcDConditionMap({
        "comp_no":"mgrUsageCalSetDTO.compNo"
    });
   	plantAc.setKeyName("mgrUsageCalSetDTO.plantId");
    plantAc.setAcResultMap({
        "mgrUsageCalSetDTO.plantId":"plant"
    });
    plantAc.init();
    
    equipNameAc = new autoC({"mgrUsageCalSetDTO.equipNameDesc":"description"});
    equipNameAc.setTable("TAEQUIPMENT");
    equipNameAc.setAcDConditionMap({
        "comp_no":"mgrUsageCalSetDTO.compNo"
    });
    equipNameAc.setKeyName("mgrUsageCalSetDTO.equipNameId");
    equipNameAc.setAcResultMap({
        "mgrUsageCalSetDTO.equipNameId":"equip_id"
    });
    equipNameAc.init();
	
	// 사용여부
    acSysDesc("mgrUsageCalSetDTO.isUse","mgrUsageCalSetDTO.isUse","IS_USE", true);
	
	//가동시간 설정방법
    acSysDesc("mgrUsageCalSetDTO.runTimeSettingDesc","mgrUsageCalSetDTO.runTimeSettingId","LNWRK_CREATE_TYPE", true);
	
}

function goInput()
{
	sequenceNextVal('SQALNWRKLIST_ID');
	
	mgrUsageCalSetForm.elements['mgrUsageCalSetDTO.isUse'].value = 'Y';
}

function setSequenceVal(sequenceVal)
{
	mgrUsageCalSetForm.elements['mgrUsageCalSetDTO.lnWrkListId'].value = sequenceVal;
	mgrUsageCalSetForm.elements['mgrUsageCalSetDTO.lnWrkListNo'].value = sequenceVal;
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
	if(ckCreate(currentPageId)) mgrUsageCalSetForm.strutsAction.value = "<%=MgrUsageCalSetAction.LINE_DETAIL_INPUT%>";
	else mgrUsageCalSetForm.strutsAction.value = "<%=MgrUsageCalSetAction.LINE_DETAIL_UPDATE%>";
	
	var actionUrl = contextPath + "/mgrUsageCalSetDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(mgrUsageCalSetForm), 'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    mgrUsageCalSetForm.elements['mgrUsageCalSetDTO.compNo'].value = mgrUsageCalSetForm.elements['mgrUsageCalSetDTO.compNo'].value;
    
    if (parent.findGridRow)	parent.findGridRow(mgrUsageCalSetForm.elements['mgrUsageCalSetDTO.lnWrkListId'].value);
    getTopPage().afterSaveAll(currentPageId);
}

function goTabPage(pageId)
{
	var form = document.mgrUsageCalSetForm;

	goCommonTabPage(form, '' , pageId);
    
}

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/mgrUsageCalSetDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="mgrUsageCalSetDTO.lnWrkListId" />
	<html:hidden property="mgrUsageCalSetDTO.compNo" />
	<html:hidden property="mgrUsageCalSetDTO.eqLocId" />
	<html:hidden property="mgrUsageCalSetDTO.wrkCalListId" />
	<html:hidden property="mgrUsageCalSetDTO.plantId" />
	<html:hidden property="mgrUsageCalSetDTO.runTimeSettingId" />
	<html:hidden property="mgrUsageCalSetDTO.equipNameId" />
	
	<div class="article_box" id="detailBox">
		<div class="form_box">
			<!-- 사용달력# -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.lnWrkListNo"/></label>
				<div class="input_read">
					<html:text property="mgrUsageCalSetDTO.lnWrkListNo" readonly="true"/>
				</div>
			</div>
			<!-- 사용달력명 -->
			<div class="field_long">
				<label class="check"><bean:message key="LABEL.lnWrkListDesc"/></label>
				<div class="input_box">
					<html:text property="mgrUsageCalSetDTO.lnWrkListDesc"  tabindex="20"/>
				</div>
			</div>
			
			<!-- 근무달력명 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.workCal"/></label>
				<div class="input_box">
					<html:text property="mgrUsageCalSetDTO.wrkCalListDesc" tabindex="30"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 사용여부 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.isUse"/></label>
				<div class="input_box">
					<html:text property="mgrUsageCalSetDTO.isUse" tabindex="40"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 공장명  -->
            <div class="field">
                <label><bean:message key="LABEL.plantDesc"/></label>
                <div class="input_box">
			<html:text property="mgrUsageCalSetDTO.plantDesc" tabindex="50" />
			<p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>
			<!-- 위치명 -->
			<div class="field">
				<label><bean:message key="LABEL.eqLocName"/></label>
				<div class="input_box">
					<html:text property="mgrUsageCalSetDTO.eqLocDesc" tabindex="60"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 설비명 -->
			<div class="field">
				<label><bean:message key="LABEL.equipName"/></label>
				<div class="input_box">
					<html:text property="mgrUsageCalSetDTO.equipNameDesc" tabindex="60"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 가동시간 설정방법 --> 
			<div class="field">
				<label><bean:message key="LABEL.runTimeSetting"/></label>
				<div class="input_box">
					<html:text property="mgrUsageCalSetDTO.runTimeSettingDesc" tabindex="60"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>									
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="mgrUsageCalSetDTO.remark" styleClass="ta50" tabindex="900" />
				</div>
			</div>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>
