<%--===========================================================================
부품거래처
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
<%@page import="dream.part.list.action.MaPtMstrVendorDetailAction"%>
<html>
<head>
<!-- 거래처 -->
<title><bean:message key="LABEL.vendorNo"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
	
	setTitle("maPtMstrVendorDetailDTO.vendorNo", "maPtMstrVendorDetailDTO.vendorDesc");
	setForUpdate();
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAPTVENDOR_ID');
}

/**
 * 수정
 */
function goUpdate()
{
    //수정시 등록유형과 등록id readonly설정 
    maPtMstrVendorDetailForm.elements['maPtMstrVendorDetailDTO.vendorDesc'].readOnly = true;
    document.getElementById("vendorDescDiv").className = "input_read"; 
    document.getElementById("vendorDescPopup").style.display = "none";

}

function setSequenceVal(sequenceVal)
{
	maPtMstrVendorDetailForm.elements['maPtMstrVendorDetailDTO.ptVendorId'].value = sequenceVal;
}

function goSave()
{
	if(checkValidation()) return;
	
	if(ckCreate(currentPageId)) maPtMstrVendorDetailForm.strutsAction.value = '<%=MaPtMstrVendorDetailAction.PTMSTR_VENDOR_DETAIL_INPUT%>';
	else maPtMstrVendorDetailForm.strutsAction.value = '<%= MaPtMstrVendorDetailAction.PTMSTR_VENDOR_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/maPtMstrVendorDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maPtMstrVendorDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(maPtMstrVendorDetailForm.elements['maPtMstrVendorDetailDTO.ptVendorId'].value);
	
    getTopPage().afterSaveAll(currentPageId);
    
	setTitle("maPtMstrVendorDetailDTO.vendorNo", "maPtMstrVendorDetailDTO.vendorDesc");
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/maPtMstrVendorDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="maPtMstrCommonDTO.partId"/>
	<html:hidden property="maPtMstrVendorDetailDTO.vendorId"/>
	<html:hidden property="maPtMstrVendorDetailDTO.vendorNo"/>
	<html:hidden property="maPtMstrVendorDetailDTO.ptVendorId"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 부위명 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.vendorDesc"/></label>
				<div id="vendorDescDiv" class="input_box">
					<html:text property="maPtMstrVendorDetailDTO.vendorDesc" tabindex="10"
                            onblur="valVendorDesc('maPtMstrVendorDetailDTO.vendorId'
                                                , 'maPtMstrVendorDetailDTO.vendorNo'
                                                , 'maPtMstrVendorDetailDTO.vendorDesc'
                                                , 'maPtMstrVendorDetailDTO.addrNrepName'
                                                , 'maPtMstrVendorDetailDTO.person'
                                                , 'maPtMstrVendorDetailDTO.office'
                                                , 'maPtMstrVendorDetailDTO.mobile'
                                                , 'maPtMstrVendorDetailDTO.email'
                                                , true);"/>
                    <p id="vendorDescPopup" class="open_spop">
                        <a href="javascript:lovVendor('maPtMstrVendorDetailDTO.vendorId'
                                                    , 'maPtMstrVendorDetailDTO.vendorNo'
                                                    , 'maPtMstrVendorDetailDTO.vendorDesc'
                                                    , 'maPtMstrVendorDetailDTO.addrNrepName'
                                                    , 'maPtMstrVendorDetailDTO.person'
                                                    , 'maPtMstrVendorDetailDTO.office'
                                                    , 'maPtMstrVendorDetailDTO.mobile'
                                                    , 'maPtMstrVendorDetailDTO.email'
                                                    , '','');">
                            <span>조회</span>
                        </a>
                    </p>
                </div>
			</div>
			<div class="field">
				<label><bean:message key="LABEL.addrNrepName"/></label>
				<div class="input_read">
					<html:text property="maPtMstrVendorDetailDTO.addrNrepName" tabindex="20" readonly="true"/>
				</div>
			</div>
			<div class="field">
				<label><bean:message key="LABEL.person"/></label>
				<div class="input_box">
					<html:text property="maPtMstrVendorDetailDTO.person" tabindex="30" />
				</div>
			</div>
            <div class="field">
                <label><bean:message key="LABEL.office"/></label>
                <div class="input_box">
                    <html:text property="maPtMstrVendorDetailDTO.office" tabindex="40" />
                </div>
            </div>
            <div class="field">
                <label><bean:message key="LABEL.mobile"/></label>
                <div class="input_box">
                    <html:text property="maPtMstrVendorDetailDTO.mobile" tabindex="50" />
                </div>
            </div>
            <div class="field">
                <label><bean:message key="LABEL.email"/></label>
                <div class="input_box">
                    <html:text property="maPtMstrVendorDetailDTO.email" tabindex="60" />
                </div>
            </div>
		</div><!--article_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>