<%--===========================================================================
공장설정 - 상세
author  euna0207
version $Id: mgrPlantMngDetail.jsp,v 1.5 2014/07/02 04:13:54 euna0207 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.mgr.plant.action.MgrPlantMngDetailAction"%>
<html:html>
<head>
<!-- 공장설정 -->
<title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
var wrkCalDescAc;
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else 
    {
        goUpdate(); 
    }
	setTitle("mgrPlantMngDetailDTO.plantNo","mgrPlantMngDetailDTO.plantDesc");
	//For Save
	setForUpdate();
   
    acSysDesc("mgrPlantMngDetailDTO.isUse","mgrPlantMngDetailDTO.isUse","IS_USE");
}

function goInput()
{
	 sequenceNextVal('SQAPLANT_ID');	
}

function setSequenceVal(sequenceVal)
{	
	mgrPlantMngDetailForm.elements['mgrPlantMngDetailDTO.plantId'].value = sequenceVal;
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
    if (parent.findGridRow)	parent.findGridRow(mgrPlantMngDetailForm.elements['mgrPlantMngDetailDTO.plantId'].value);

    goUpdate();
    
    getTopPage().afterSaveAll(currentPageId);
}

/** 
 * PlantNo 중복체크
 */
function valPlantNo() 
{
	var actionUrl = contextPath + "/mgrPlantMngDetail.do";
	var param =  "&strutsAction=" + '<%= MgrPlantMngDetailAction.PLANT_DETAIL_CHECK %>'
			  +  "&" + FormQueryString(mgrPlantMngDetailForm);
	XMLHttpPostVal(actionUrl, param, 'setValidPlantNo');
	
}

var plantCnt = 0;
function setValidPlantNo(ajaxXmlDoc) 
{
	plantCnt = parseXmlDoc(ajaxXmlDoc, 'DESC');
	
    if(plantCnt != '0')
    {
    	closeModal();
    	mgrPlantMngDetailForm.elements['mgrPlantMngDetailDTO.plantNo'].value = '';
    	mgrPlantMngDetailForm.elements['mgrPlantMngDetailDTO.plantNo'].focus();
        
        alertMessage1("<bean:message key='MESSAGE.MSG0009' />");
    } else {
    	if(ckCreate(currentPageId)) mgrPlantMngDetailForm.strutsAction.value = "<%=MgrPlantMngDetailAction.PLANT_DETAIL_INPUT%>";
    	else mgrPlantMngDetailForm.strutsAction.value = "<%=MgrPlantMngDetailAction.PLANT_DETAIL_UPDATE%>";
    	
    	var actionUrl = contextPath + "/mgrPlantMngDetail.do";
    	XMLHttpPost(actionUrl, FormQueryString(mgrPlantMngDetailForm), 'afterSave');
    }
}

function goUpdate() {
    setReadOnly("mgrPlantMngDetailDTO.plantNo");
}
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/mgrPlantMngDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="mgrPlantMngCommonDTO.plantId" />
	<html:hidden property="mgrPlantMngDetailDTO.compId" />
	<html:hidden property="mgrPlantMngDetailDTO.plantId" />
	<html:hidden property="mgrPlantMngDetailDTO.wrkCalListId" />
         <div class="article_box" id="detailBox">
             <div class="form_box">
             	 <div class="field">
             	 	<label class="check"><bean:message key="LABEL.plantNo"/></label>
             	 	<div id="plantNoDiv" class="input_box">
             	 		<html:text property="mgrPlantMngDetailDTO.plantNo" tabindex="20"/>
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label class="check"><bean:message key="LABEL.plantDesc"/></label>
             	 	<div class="input_box">
             	 		<html:text property="mgrPlantMngDetailDTO.plantDesc" tabindex="30" />
             	 	</div>
             	 </div>
             	 <div class="field">
                    <label><bean:message key="LABEL.isUse"/></label>
                    <div class="input_box">
                        <html:text property="mgrPlantMngDetailDTO.isUse" tabindex="60"/>
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
