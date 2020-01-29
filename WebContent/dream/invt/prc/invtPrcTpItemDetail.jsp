
<%--===========================================================================
구매절차 Item 상세
author  hyosung
version $Id: invtPrcTpItemDetail.jsp,v 1.0 2015/12/04 07:26:18 hyosung Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ page import="common.bean.User"%>
<%@ page import="dream.doc.file.action.MaDocLibListAction"%>
<%@page import="dream.invt.prc.action.InvtPrcTpItemDetailAction"%>

<html>
<head>
<!--답변사항 -->
<title><bean:message key="LABEL.questionPoint"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//


var acSysDescAC;
function loadPage() 
{
	
    setTitle("invtPrcTpItemDetailDTO.invt_LTypeDesc");
	setForUpdate();
	/** 대분류 자동완성 */
	//acSysDescAC = acSysDesc("invtPrcTpItemDetailDTO.invt_LTypeDesc","invtPrcTpItemDetailDTO.invt_LTypeNo","INVT_PROC_LTYPE",true);
	
    var lTypeAc = new autoC({"invtPrcTpItemDetailDTO.invt_LTypeDesc":"description"});
    lTypeAc.setTable("TACDUSRD");
    lTypeAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    	,"is_use":"Y"
    	,"dir_type":"INVT_PROC_LTYPE"
  	   });
    lTypeAc.setAcResultMap({
        "invtPrcTpItemDetailDTO.invt_LTypeNo":"cdusrd_no"
    });
    lTypeAc.init();
	
	/** 소분류 자동완성 */
	//acSysDesc("invtPrcTpItemDetailDTO.invt_STypeDesc","invtPrcTpItemDetailDTO.invt_STypeNo","INVT_PROC_STYPE",true);
	
    var sTypeAc = new autoC({"invtPrcTpItemDetailDTO.invt_STypeDesc":"description"});
    sTypeAc.setTable("TACDUSRD");
    sTypeAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    	,"is_use":"Y"
    	,"dir_type":"INVT_PROC_STYPE"
  	   });
    sTypeAc.setAcResultMap({
        "invtPrcTpItemDetailDTO.invt_STypeNo":"cdusrd_no"
    });
    sTypeAc.init();
	
    
	/** 사용여부 자동완성 */
	acSysDesc("invtPrcTpItemDetailDTO.isUseDesc","invtPrcTpItemDetailDTO.isUse","IS_USE",true);
	
	if(ckCreate(currentPageId)) goInput();
}

function goInput(){
	/** 시퀀스를 조회한다 */
	sequenceNextVal('SQAINVTPRCPH_ID');
	/** 생성버튼 눌렀을 때 사용여부는 기본 Y값을 부여 */
	invtPrcTpItemDetailForm.elements['invtPrcTpItemDetailDTO.isUse'].value = 'Y';
	invtPrcTpItemDetailForm.elements['invtPrcTpItemDetailDTO.isUseDesc'].value = 'Y';
	
	
}

function setSequenceVal(sequenceVal)
{
	invtPrcTpItemDetailForm.elements['invtPrcTpItemListDTO.invtPrcPhId'].value = sequenceVal;
}

function goSave(){
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	
	if(ckCreate(currentPageId)) invtPrcTpItemDetailForm.strutsAction.value = '<%=InvtPrcTpItemDetailAction.INVT_DETAIL_INPUT%>';
	else invtPrcTpItemDetailForm.strutsAction.value = '<%= InvtPrcTpItemDetailAction.INVT_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/invtPrcTpItemDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(invtPrcTpItemDetailForm), 'afterSave');
}
/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    
    if (parent.findGridRow)	parent.findGridRow(invtPrcTpItemDetailForm.elements['invtPrcTpItemListDTO.invtPrcPhId'].value);
    
    getTopPage().afterSaveAll(currentPageId);
    setTitle("invtPrcTpItemDetailDTO.invt_LTypeDesc");
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = invtPrcTpItemDetailForm.elements['invtPrcTpItemListDTO.invtPrcPhId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>

</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/invtPrcTpItemDetail.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="invtPrcTpCommonDTO.invtPrcTpId"/>
<html:hidden property="invtPrcTpItemListDTO.invtPrcPhId"/>
<html:hidden property="invtPrcTpItemDetailDTO.isUse"/>
<html:hidden property="invtPrcTpItemDetailDTO.invt_LTypeNo"/>
<html:hidden property="invtPrcTpItemDetailDTO.invt_STypeNo"/>
    <!-- searchbox 박스 Line -->
	<div class="article_box">
			<div class="form_box">
			<!-- 진행순서 -->
				<div class="field">
					<label><bean:message key="LABEL.ordNo"/></label>
					<div class="input_box">
						<html:text property="invtPrcTpItemDetailDTO.ordNo" tabindex="10" styleClass="num" />
					</div>
				</div>
			<!-- 사용여부 -->	
				<div class="field">
					<label><bean:message key="LABEL.isUse"/></label>
					<div class="input_box">
						<html:text property="invtPrcTpItemDetailDTO.isUseDesc" tabindex="20" />
						<p class="open_spop">
                            <a>
                               <span>조회</span>
                           </a>
                       </p>
					</div>
				</div>
			<!-- 대분류 -->	
				<div class="field">
                    <label><bean:message key="LABEL.lType"/></label>
                    <div class="input_box">
                        <html:text property="invtPrcTpItemDetailDTO.invt_LTypeDesc" tabindex="30" />
                        <p class="open_spop">
                                <a>
                                    <span>조회</span>
                                </a>
                            </p>
                    </div>
                </div>
            <!-- 소분류 -->   
                <div class="field">
                    <label><bean:message key="LABEL.sType"/></label>
                    <div class="input_box">
                        <html:text property="invtPrcTpItemDetailDTO.invt_STypeDesc" tabindex="40" />
                        <p class="open_spop">
                                <a>
                                    <span>조회</span>
                                </a>
                            </p>
                    </div>
                </div>
            <!-- 유관부서 -->    
                <div class="field">
                    <label><bean:message key="LABEL.refDept"/></label>
                    <div class="input_box">
                        <html:text property="invtPrcTpItemDetailDTO.invtRefDept" tabindex="50" />
                    </div>
                </div>
            <!-- 관련문서 -->    
                <div class="field">
                    <label><bean:message key="LABEL.refDoc"/></label>
                    <div class="input_box">
                        <html:text property="invtPrcTpItemDetailDTO.invtRefDoc" tabindex="60" />
                    </div>
                </div>
            <!-- 번호생성 Prefix -->
	            <div class="field">
					<label><bean:message key="LABEL.docPrefix"/></label>
					<div class="input_box">
						<html:text property="invtPrcTpItemDetailDTO.docPrefix" tabindex="65"/>
					</div>
				</div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="invtPrcTpItemDetailDTO.remark" styleClass="ta50" tabindex="70" />
				</div>
			</div>
			</div>
	</div><!--article_box end-->
</html:form> 
</body>
</html>