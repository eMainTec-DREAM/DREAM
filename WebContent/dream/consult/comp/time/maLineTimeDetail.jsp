<%--===========================================================================
가동시간설정- 상세
author  kim21017
version $Id: maLineTimeDetail.jsp,v 1.5 2014/07/02 04:13:54 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%> 
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.consult.comp.time.action.MaLineTimeDetailAction"%>
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
	
	setTitle("maLineTimeDetailDTO.lnWrkListNo","maLineTimeDetailDTO.lnWrkListDesc");
	//For Save
	setForUpdate();
	
	compDescAc = new autoC({"maLineTimeDetailDTO.compDesc":"description"});
	compDescAc.setTable("TACOMP");
	compDescAc.setKeyName("maLineTimeDetailDTO.compNo");
	compDescAc.setAcResultMap({
        "maLineTimeDetailDTO.compNo":"comp_no"
        ,"maLineTimeCommonDTO.compNo":"comp_no"
    });
	compDescAc.init();
    
	eqLocDescAc = new autoC({"maLineTimeDetailDTO.eqLocDesc":"description"});
	eqLocDescAc.setAcDConditionMap({
    	"comp_no":"maLineTimeDetailDTO.compNo",
    	"plant" : "maLineTimeDetailDTO.plantId"
  	   });
	eqLocDescAc.setTable("TAEQLOC");
	eqLocDescAc.setKeyName("maLineTimeDetailDTO.eqLocId");
	eqLocDescAc.setAcResultMap({
        "maLineTimeDetailDTO.eqLocId":"eqloc_id"
    });
	eqLocDescAc.init();
    
	wrkCalDescAc = new autoC({"maLineTimeDetailDTO.wrkCalListDesc":"description"});
	wrkCalDescAc.setAcDConditionMap({
    	"comp_no":"maLineTimeDetailDTO.compNo"
  	   });
	wrkCalDescAc.setTable("TAWRKCALLIST");
	wrkCalDescAc.setKeyName("maLineTimeDetailDTO.wrkCalListId");
	wrkCalDescAc.setAcResultMap({
        "maLineTimeDetailDTO.wrkCalListId":"wrkcallist_id"
    });
	wrkCalDescAc.init();
	
	plantAc = new autoC({"maLineTimeDetailDTO.plantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcDConditionMap({
        "comp_no":"maLineTimeDetailDTO.compNo"
    });
   	plantAc.setKeyName("maLineTimeDetailDTO.plantId");
    plantAc.setAcResultMap({
        "maLineTimeDetailDTO.plantId":"plant"
    });
    plantAc.init();
    
    equipNameAc = new autoC({"maLineTimeDetailDTO.equipNameDesc":"description"});
    equipNameAc.setTable("TAEQUIPMENT");
    equipNameAc.setAcDConditionMap({
        "comp_no":"maLineTimeDetailDTO.compNo"
    });
    equipNameAc.setKeyName("maLineTimeDetailDTO.equipNameId");
    equipNameAc.setAcResultMap({
        "maLineTimeDetailDTO.equipNameId":"equip_id"
    });
    equipNameAc.init();
	
	// 사용여부
    acSysDesc("maLineTimeDetailDTO.isUse","maLineTimeDetailDTO.isUse","IS_USE", true);
	
	//가동시간 설정방법
    acSysDesc("maLineTimeDetailDTO.runTimeSettingDesc","maLineTimeDetailDTO.runTimeSettingId","LNWRK_CREATE_TYPE", true);
	
}

function goInput()
{
	sequenceNextVal('SQALNWRKLIST_ID');
	
	maLineTimeDetailForm.elements['maLineTimeDetailDTO.isUse'].value = 'Y';
}

function setSequenceVal(sequenceVal)
{
	maLineTimeDetailForm.elements['maLineTimeCommonDTO.lnWrkListId'].value = sequenceVal;
	maLineTimeDetailForm.elements['maLineTimeDetailDTO.lnWrkListId'].value = sequenceVal;
	maLineTimeDetailForm.elements['maLineTimeDetailDTO.lnWrkListNo'].value = sequenceVal;
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
	if(ckCreate(currentPageId)) maLineTimeDetailForm.strutsAction.value = "<%=MaLineTimeDetailAction.LINE_DETAIL_INPUT%>";
	else maLineTimeDetailForm.strutsAction.value = "<%=MaLineTimeDetailAction.LINE_DETAIL_UPDATE%>";
	
	var actionUrl = contextPath + "/maLineTimeDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maLineTimeDetailForm), 'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    maLineTimeDetailForm.elements['maLineTimeDetailDTO.lnWrkListId'].value = maLineTimeDetailForm.elements['maLineTimeCommonDTO.lnWrkListId'].value;
    maLineTimeDetailForm.elements['maLineTimeDetailDTO.compNo'].value = maLineTimeDetailForm.elements['maLineTimeCommonDTO.compNo'].value;
    
    if (parent.findGridRow)	parent.findGridRow(maLineTimeDetailForm.elements['maLineTimeDetailDTO.compNo'].value, maLineTimeDetailForm.elements['maLineTimeDetailDTO.lnWrkListId'].value);
    getTopPage().afterSaveAll(currentPageId);
}

function goTabPage(pageId)
{
	var form = document.maLineTimeDetailForm;

	goCommonTabPage(form, '' , pageId);
    
}

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/maLineTimeDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="maLineTimeCommonDTO.lnWrkListId" />
	<html:hidden property="maLineTimeCommonDTO.compNo" />
	<html:hidden property="maLineTimeDetailDTO.lnWrkListId" />
	<html:hidden property="maLineTimeDetailDTO.compNo" />
	<html:hidden property="maLineTimeDetailDTO.eqLocId" />
	<html:hidden property="maLineTimeDetailDTO.wrkCalListId" />
	<html:hidden property="maLineTimeDetailDTO.plantId" />
	<html:hidden property="maLineTimeDetailDTO.runTimeSettingId" />
	<html:hidden property="maLineTimeDetailDTO.equipNameId" />
	
	<div class="article_box" id="detailBox">
		<div class="form_box">
			<!-- 회사명 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.compDesc"/></label>
				<div class="input_box">
					<html:text property="maLineTimeDetailDTO.compDesc" tabindex="10"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 가동달력# -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.lnWrkListNo"/></label>
				<div class="input_read">
					<html:text property="maLineTimeDetailDTO.lnWrkListNo" readonly="true"/>
				</div>
			</div>
			<!-- 가동달력명 -->
			<div class="field_long">
				<label class="check"><bean:message key="LABEL.lnWrkListDesc"/></label>
				<div class="input_box">
					<html:text property="maLineTimeDetailDTO.lnWrkListDesc"  tabindex="20"/>
				</div>
			</div>
			
			<!-- 근무달력명 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.workCal"/></label>
				<div class="input_box">
					<html:text property="maLineTimeDetailDTO.wrkCalListDesc" tabindex="30"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 사용여부 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.isUse"/></label>
				<div class="input_box">
					<html:text property="maLineTimeDetailDTO.isUse" tabindex="40"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 공장명  
            <div class="field">
                <label><bean:message key="LABEL.plantDesc"/></label>
                <div class="input_box">
			<html:text property="maLineTimeDetailDTO.plantDesc" tabindex="50" />
			<p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>
			-->
			<!-- 위치명 
			<div class="field">
				<label><bean:message key="LABEL.eqLocName"/></label>
				<div class="input_box">
					<html:text property="maLineTimeDetailDTO.eqLocDesc" tabindex="60"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			-->
			<!-- 설비명 
			<div class="field">
				<label><bean:message key="LABEL.equipName"/></label>
				<div class="input_box">
					<html:text property="maLineTimeDetailDTO.equipNameDesc" tabindex="60"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			-->
			<!-- 가동시간 설정방법 --> 
			<div class="field">
				<label><bean:message key="LABEL.runTimeSetting"/></label>
				<div class="input_box">
					<html:text property="maLineTimeDetailDTO.runTimeSettingDesc" tabindex="60"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>									
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="maLineTimeDetailDTO.remark" styleClass="ta50" tabindex="900" />
				</div>
			</div>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>
