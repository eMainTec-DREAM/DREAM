<%--===========================================================================
부품사용설비
author  ssong
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.part.list.action.MaPtMstrEqPartDetailAction"%>
<html>
<head>
<!-- 부품사용설비 -->
<title><bean:message key="LABEL.equipment"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
/*자동완성 */
var equipDescAc, eqAsmbAc;
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
	
	setTitle("maPtMstrEqPartDetailDTO.itemNo", "maPtMstrEqPartDetailDTO.equipDesc");
	setForUpdate();
	
	equipDescAc = new autoC({"maPtMstrEqPartDetailDTO.equipDesc":"description"});
    equipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo,
 	   "eq_status":"R+S"
 	   });
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcResultMap({
        "maPtMstrEqPartDetailDTO.equipId":"equip_id"
        ,"maPtMstrEqPartDetailDTO.eqLocId":"eqloc_id"
        ,"maPtMstrEqPartDetailDTO.eqLocDesc":"eqLocDesc"
        ,"maPtMstrEqPartDetailDTO.eqCtgDesc":"eqCtgDesc"
    });
    equipDescAc.setKeyName("maPtMstrEqPartDetailDTO.equipId"); 
    equipDescAc.init();
    
    eqAsmbAc = new autoC({"maPtMstrEqPartDetailDTO.eqAsmbDesc":"full_desc"});
    eqAsmbAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqAsmbAc.setAcDConditionMap({
  	   "equip_id":"maPtMstrEqPartDetailDTO.equipId"
  	   });
    eqAsmbAc.setTable("TAEQASMB");
    eqAsmbAc.setAcResultMap({
        "maPtMstrEqPartDetailDTO.eqAsmbId":"eqasmb_id"
    });
    eqAsmbAc.setKeyName("maPtMstrEqPartDetailDTO.eqAsmbId"); 
    eqAsmbAc.init();
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAEQPART_ID');
	maPtMstrEqPartDetailForm.elements['maPtMstrEqPartDetailDTO.partId'].value = maPtMstrEqPartDetailForm.elements['maPtMstrCommonDTO.partId'].value;
}

/**
 * 수정
 */
function goUpdate()
{


}

function setSequenceVal(sequenceVal)
{
	maPtMstrEqPartDetailForm.elements['maPtMstrEqPartDetailDTO.eqPartId'].value = sequenceVal;
}

function goSave()
{
	if(checkValidation()) return;
	
	if(ckCreate(currentPageId)) maPtMstrEqPartDetailForm.strutsAction.value = '<%=MaPtMstrEqPartDetailAction.PTMSTR_EQPART_DETAIL_INPUT%>';
	else maPtMstrEqPartDetailForm.strutsAction.value = '<%= MaPtMstrEqPartDetailAction.PTMSTR_EQPART_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/maPtMstrEqPartDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maPtMstrEqPartDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(maPtMstrEqPartDetailForm.elements['maPtMstrEqPartDetailDTO.eqPartId'].value);
	
    getTopPage().afterSaveAll(currentPageId);
    
    setTitle("maPtMstrEqPartDetailDTO.itemNo", "maPtMstrEqPartDetailDTO.equipDesc");
}


/* audit Trail */
function goAudtrailLink()
{
	var objectId = maPtMstrEqPartDetailForm.elements['maPtMstrEqPartDetailDTO.eqPartId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/maPtMstrEqPartDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maPtMstrCommonDTO.partId"/>
	<html:hidden property="maPtMstrEqPartDetailDTO.eqPartId"/><!-- Key -->
	<html:hidden property="maPtMstrEqPartDetailDTO.equipId"/>
	<html:hidden property="maPtMstrEqPartDetailDTO.partId"/>
	<html:hidden property="maPtMstrEqPartDetailDTO.itemNo"/>
	<html:hidden property="maPtMstrEqPartDetailDTO.eqAsmbId"/>
	<html:hidden property="maPtMstrEqPartDetailDTO.eqLocId"/>
	<html:hidden property="maPtMstrEqPartDetailDTO.eqCtgId"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
            <!-- 설비 -->
            <div class="field">
                <label class="check"><bean:message key="LABEL.equipment"/></label>
                <div class="input_box">
                    <html:text property="maPtMstrEqPartDetailDTO.equipDesc" tabindex="10"/>
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>
            <!-- 설비부위 -->
            <div class="field">
                <label><bean:message key="LABEL.eqAsmb"/></label>
                <div class="input_box">
                    <html:text property="maPtMstrEqPartDetailDTO.eqAsmbDesc" tabindex="20"/>
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>
            <!-- 위치 -->
            <div class="field">
                <label><bean:message key="LABEL.location"/></label>
                <div class="input_read">
                    <html:text property="maPtMstrEqPartDetailDTO.eqLocDesc" tabindex="30" readonly="true"/>
                </div>
            </div>
            <!-- 종류 -->
            <div class="field">
                <label><bean:message key="LABEL.type"/></label>
                <div class="input_read">
                    <html:text property="maPtMstrEqPartDetailDTO.eqCtgDesc" tabindex="40" readonly="true"/>
                </div>
            </div>
		</div><!--article_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>