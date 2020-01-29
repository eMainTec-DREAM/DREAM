<%--===========================================================================
부품창고 보관위치 Detail
author  cjscjs9
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.ptwh.action.MgrPtWhBinDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html:html>
<head>
<!-- 부품창고 보관위치 -->
<title><bean:message key='LABEL.binNo' /></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

/** 자동완성 변수 */


function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	
    setTitle("mgrPtWhBinDetailDTO.binNo");
   
    //For Save
    setForUpdate();

    //사용여부 자동완성
    acSysDesc("mgrPtWhBinDetailDTO.isUse","mgrPtWhBinDetailDTO.isUse","IS_USE", true);
}

/**
 * 입력
 */
function goInput()
{
	// 시퀀스를 조회한다.
    sequenceNextVal('SQAWHUSER_ID');    
    mgrPtWhBinDetailForm.elements['mgrPtWhBinDetailDTO.wcodeId'].value = mgrPtWhBinDetailForm.elements['mgrPtWhCommonDTO.wcodeId'].value;
    mgrPtWhBinDetailForm.elements['mgrPtWhBinDetailDTO.isUse'].value = "Y";
}

function setSequenceVal(sequenceVal)
{
    mgrPtWhBinDetailForm.elements['mgrPtWhBinDetailDTO.ptBinListId'].value = sequenceVal;
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

    // 생성, 수정 시 중복 담당자 체크
	/* validEmp(); */
	/* if(isValid!='0') return; */
    
    //strutsAction 셋팅.
    if(ckCreate(currentPageId)) mgrPtWhBinDetailForm.strutsAction.value = "<%=MgrPtWhBinDetailAction.DETAIL_INPUT%>";
    else mgrPtWhBinDetailForm.strutsAction.value = "<%=MgrPtWhBinDetailAction.DETAIL_UPDATE%>";

	var actionUrl = contextPath + "/mgrPtWhBinDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(mgrPtWhBinDetailForm), 'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc) 
{
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc))return;
	//=====================================
	if (parent.findGridRow)parent.findGridRow(mgrPtWhBinDetailForm.elements['mgrPtWhBinDetailDTO.ptBinListId'].value);

	mgrPtWhBinDetailForm.elements['mgrPtWhBinListDTO.ptBinListId'].value = mgrPtWhBinDetailForm.elements['mgrPtWhBinDetailDTO.ptBinListId'].value;
	getTopPage().afterSaveAll(currentPageId);
	 setTitle("mgrPtWhBinDetailDTO.binNo"); 

}


function sumBinNo()
{
	 var binLoc = mgrPtWhBinDetailForm.elements['mgrPtWhBinDetailDTO.loc'].value;
	 var binCol = mgrPtWhBinDetailForm.elements['mgrPtWhBinDetailDTO.col'].value;
	 var binRo = mgrPtWhBinDetailForm.elements['mgrPtWhBinDetailDTO.ro'].value;
	 var result = "";
	 
	 if (binLoc != null && binLoc != '') {
		result += binLoc;
	}
	 if (binCol != null && binCol != '') {
		result = result + "-" + binCol;
	}
	 if (binRo != null && binRo != '') {
		result = result + "-" + binRo;
	}

	 mgrPtWhBinDetailForm.elements['mgrPtWhBinDetailDTO.binNo'].value = result;
	 
}

/**
 * 중복 담당자 체크
 */
<%-- function validEmp(){
	var actionUrl = contextPath + "/mgrPtWhBinDetail.do";
	var param =  "&strutsAction=" + '<%= MgrPtWhBinDetailAction.DETAIL_CHECK %>'
	          +  "&" + FormQueryString(mgrPtWhBinDetailForm);
	XMLHttpPostVal(actionUrl, param, 'setValidEmpNo');
}
/**
 * valEmpNo()실행 후 호출
 */
var isValid;
function setValidEmpNo(ajaxXmlDoc)
{
	isValid = '0';
    isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
    
    if(isValid != '0')
    {
    	closeModal();
    	mgrPtWhBinDetailForm.elements['mgrPtWhBinDetailDTO.binNo'].value = '';
    	mgrPtWhBinDetailForm.elements['mgrPtWhBinDetailDTO.binNo'].focus();
        
        // 중복되었습니다.
        alertMessage1("<bean:message key='MESSAGE.MSG0009' />");
    }
} --%>
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = mgrPtWhBinDetailForm.elements['mgrPtWhBinDetailDTO.ptBinListId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/mgrPtWhBinDetail.do">
		<html:hidden property="strutsAction" />
		<html:hidden property="currentPageId"/>
		<html:hidden property="mgrPtWhCommonDTO.wcodeId" />
		<html:hidden property="mgrPtWhBinListDTO.wcodeId" />
		<html:hidden property="mgrPtWhBinListDTO.ptBinListId" />
		<html:hidden property="mgrPtWhBinDetailDTO.wcodeId" />
		<html:hidden property="mgrPtWhBinDetailDTO.ptBinListId" />


		<div class="article_box">
			<div class="form_box">
				<!-- 구역 -->
	            <div class="field">
					<label class="check"><bean:message key="LABEL.loc"/></label>
					<div class="input_box">
						<html:text property="mgrPtWhBinDetailDTO.loc" tabindex="10" onblur="javascript:sumBinNo();"/>
					</div>
				</div>
				<!-- 열  -->
				<div class="field">
					<label><bean:message key="LABEL.col"/></label>
					<div class="input_box">
			            <html:text property="mgrPtWhBinDetailDTO.col" tabindex="20" onblur="javascript:sumBinNo();"/>			           
					</div>
				</div>
				<!-- 행  -->
				<div class="field">
					<label><bean:message key="LABEL.ro"/></label>
					<div class="input_box">
			            <html:text property="mgrPtWhBinDetailDTO.ro" tabindex="30" onblur="javascript:sumBinNo();"/>			           
					</div>
				</div>
				<!-- 보관위치  -->
				<div class="field">
					<label><bean:message key="LABEL.binNo"/></label>
					<div class="input_read">
			            <html:text property="mgrPtWhBinDetailDTO.binNo" tabindex="40" readonly="true" />			           
					</div>
				</div>
				<!-- 사용여부  -->
				<div class="field">
					<label><bean:message key="LABEL.isUse"/></label>
					<div class="input_read">
			            <html:text property="mgrPtWhBinDetailDTO.isUse" tabindex="50" />
			            	<p class="open_spop"><a><span>조회</span></a></p>			           
					</div>
				</div>
				<!-- 비고 -->
				<div class="field_long">
					<label><bean:message key="LABEL.remark" /></label>
					<div class="input_box">
						<html:textarea styleClass="ta50" property="mgrPtWhBinDetailDTO.remark"
							tabindex="60" />
					</div>
				</div>
			</div>
			<!-- End of Form_box -->
		</div>
		<!-- End of Article_box -->
	</html:form>
</body>
</html:html>
