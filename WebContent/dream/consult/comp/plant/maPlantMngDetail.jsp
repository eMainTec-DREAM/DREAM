<%--===========================================================================
회사설정 - 상세
author  kim21017
version $Id: maPlantMngDetail.jsp,v 1.5 2014/07/02 04:13:54 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.consult.comp.plant.action.MaPlantMngDetailAction"%>
<html:html>
<head>
<!-- 회사설정 -->
<title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
var compAc;
var wrkCalDescAc;
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else 
    {
        goUpdate();
    }
	setTitle("maPlantMngDetailDTO.plantNo","maPlantMngDetailDTO.plantDesc");
	//For Save
	setForUpdate();
	
	compAc = new autoC({"maPlantMngDetailDTO.compDesc":"description"});
    compAc.setTable("TACOMP");
    compAc.setKeyName("maPlantMngDetailDTO.compNo");
    compAc.setAcResultMap({
        "maPlantMngDetailDTO.compNo":"comp_no"
    });
    compAc.init();
    
	wrkCalDescAc = new autoC({"maPlantMngDetailDTO.wrkCalListDesc":"description"});
	wrkCalDescAc.setAcDConditionMap({
    	"comp_no":"maPlantMngDetailDTO.compNo"
  	   });
	wrkCalDescAc.setTable("TAWRKCALLIST");
	wrkCalDescAc.setKeyName("maPlantMngDetailDTO.wrkCalListId");
	wrkCalDescAc.setAcResultMap({
        "maPlantMngDetailDTO.wrkCalListId":"wrkcallist_id"
    });
	wrkCalDescAc.init();
    
    
    acSysDesc("maPlantMngDetailDTO.isUse","maPlantMngDetailDTO.isUse","IS_USE");
}

function goInput()
{
	 sequenceNextVal('SQAPLANT_ID');	
}

function setSequenceVal(sequenceVal)
{	
	maPlantMngDetailForm.elements['maPlantMngDetailDTO.plantId'].value = sequenceVal;
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
    valPlantNo();
}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(maPlantMngDetailForm.elements['maPlantMngDetailDTO.compNo'].value, maPlantMngDetailForm.elements['maPlantMngDetailDTO.plantId'].value);
    
    goUpdate();
    
    getTopPage().afterSaveAll(currentPageId);
}

/** 
 * PlantNo 중복체크 
 */
function valPlantNo() 
{
	var actionUrl = contextPath + "/maPlantMngDetail.do";
	var param =  "&strutsAction=" + '<%= MaPlantMngDetailAction.PLANT_DETAIL_CHECK %>'
			  +  "&" + FormQueryString(maPlantMngDetailForm);
	XMLHttpPostVal(actionUrl, param, 'setValidPlantNo');
	
}

var plantCnt = 0;
function setValidPlantNo(ajaxXmlDoc) 
{
	plantCnt = parseXmlDoc(ajaxXmlDoc, 'DESC');
    if(plantCnt != '0')
    {
    	closeModal();
    	maPlantMngDetailForm.elements['maPlantMngDetailDTO.plantNo'].value = '';
    	maPlantMngDetailForm.elements['maPlantMngDetailDTO.plantNo'].focus();
        
        alertMessage1("<bean:message key='MESSAGE.CMSG015' />");
    } else {
    	if(ckCreate(currentPageId)) maPlantMngDetailForm.strutsAction.value = "<%=MaPlantMngDetailAction.PLANT_DETAIL_INPUT%>";
    	else maPlantMngDetailForm.strutsAction.value = "<%=MaPlantMngDetailAction.PLANT_DETAIL_UPDATE%>";
    	
    	var actionUrl = contextPath + "/maPlantMngDetail.do";
    	XMLHttpPost(actionUrl, FormQueryString(maPlantMngDetailForm), 'afterSave');
    }
}

function goUpdate() {
    setReadOnly("maPlantMngDetailDTO.plantNo");
}
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/maPlantMngDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="maPlantMngCommonDTO.compNo" />
	<html:hidden property="maPlantMngCommonDTO.plantId" />
	<html:hidden property="maPlantMngDetailDTO.compNo" />
	<html:hidden property="maPlantMngDetailDTO.compId" />
	<html:hidden property="maPlantMngDetailDTO.plantId" />
	<html:hidden property="maPlantMngDetailDTO.wrkCalListId" />
         <div class="article_box" id="detailBox">
             <div class="form_box">
             	 <div class="field">
                    <label  class="check"><bean:message key="LABEL.compNo"/></label>
                    <div id="compNoDiv" class="input_box">
                        <html:text property="maPlantMngDetailDTO.compDesc" tabindex="10"/>
                        <p class="open_spop">
                            <a><span>조회</span></a>
                        </p>
                    </div>
                 </div>
             	 <div class="field">
             	 	<label class="check"><bean:message key="LABEL.plantNo"/></label>
             	 	<div id="plantNoDiv" class="input_box">
             	 		<html:text property="maPlantMngDetailDTO.plantNo" tabindex="20"/>
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label class="check"><bean:message key="LABEL.plantDesc"/></label>
             	 	<div class="input_box">
             	 		<html:text property="maPlantMngDetailDTO.plantDesc" tabindex="30" />
             	 	</div>
             	 </div>
	             <!-- 근무달력명 
				<div class="field">
					<label><bean:message key="LABEL.workCal"/></label>
					<div class="input_box">
						<html:text property="maPlantMngDetailDTO.wrkCalListDesc" tabindex="10"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
	             -->
             	 <div class="field">
                    <label><bean:message key="LABEL.isUse"/></label>
                    <div class="input_box">
                        <html:text property="maPlantMngDetailDTO.isUse" tabindex="60"/>
                        <p class="open_spop">
                            <a><span>조회</span></a>
                        </p>
                    </div>
                </div>
                <div class="field_blank"></div>
                <div class="field_blank"></div>
         </div> <!-- End of Article_box -->
         </div>
	</html:form>
</body>
</html:html>
