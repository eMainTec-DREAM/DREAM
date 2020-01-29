 <%--===========================================================================
설비운용기간사용예상수량상세
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.list.action.AssetListPartOpQtyAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html:html>
<head>
<!-- 설비운용기간사용예상수량상세 -->
<title><bean:message key='PAGE.assetListPartOpQtyDetail' /></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

var vendorAc;

function loadPage() 
{
	setTitle("assetListPartOpQtyDTO.opPeriod");
	
    //For Save
    setForUpdate();
    
    vendorAc = new autoC({"assetListPartOpQtyDTO.vendorNo":"vendor_no"});
    vendorAc.setTable("TAVENDOR");
    vendorAc.setKeyName("assetListPartOpQtyDTO.vendorId");
    vendorAc.setAcConditionMap({
    	"is_use":"Y"
    	,"comp_no":loginUser.compNo
	});
    vendorAc.setAcResultMap({
        "assetListPartOpQtyDTO.vendorId":"vendor_id"
        ,"assetListPartOpQtyDTO.vendorDesc":"description"
    });
    vendorAc.init();
    
    if(ckCreate(currentPageId)) goInput();
    else 
    {
    	goUpdate();
    }
}

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQAEQPARTOPQTY_ID');
}

function setSequenceVal(sequenceVal)
{
	assetListPartOpQtyForm.elements['assetListPartOpQtyDTO.eqPartOpQtyId'].value = sequenceVal;
}

/**
 * 수정
 */
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
    if(ckCreate(currentPageId)) assetListPartOpQtyForm.strutsAction.value = "<%=AssetListPartOpQtyAction.INPUT_DETAIL%>";
    else assetListPartOpQtyForm.strutsAction.value = "<%=AssetListPartOpQtyAction.UPDATE_DETAIL%>";
    
	var pPage = searchPage("maEqMstrPartDetail");
    assetListPartOpQtyForm.elements['assetListPartOpQtyDTO.eqPartId'].value = pPage.M$('maEqMstrPartDetailDTO.eqPartId').value;
    assetListPartOpQtyForm.elements['assetListPartOpQtyDTO.equipId'].value = pPage.M$('maEqMstrPartDetailDTO.equipId').value;
    assetListPartOpQtyForm.elements['assetListPartOpQtyDTO.partId'].value = pPage.M$('maEqMstrPartDetailDTO.partId').value;
    assetListPartOpQtyForm.elements['assetListPartOpQtyDTO.eqAsmbId'].value = pPage.M$('maEqMstrPartDetailDTO.eqAsmbId').value;
    
	var actionUrl = contextPath + "/assetListPartOpQtyDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(assetListPartOpQtyForm), 'afterSave');
}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc) {
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc))
		return;
	//=====================================
	if (parent.findGridRow)
		parent.findGridRow(assetListPartOpQtyForm.elements['assetListPartOpQtyDTO.eqPartOpQtyId'].value);

	getTopPage().afterSaveAll(currentPageId);
	
	setTitle("assetListPartOpQtyDTO.opPeriod");
}

function goTabPage(pageId)
{
    goCommonTabPage(assetListPartOpQtyForm, '' , pageId);
}
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

	<html:form action="/assetListPartOpQtyDetail">
		<html:hidden property="strutsAction" />
		<html:hidden property="assetListPartOpQtyDTO.eqPartOpQtyId" />
		<html:hidden property="assetListPartOpQtyDTO.eqPartId" />
		<html:hidden property="assetListPartOpQtyDTO.equipId" />
		<html:hidden property="assetListPartOpQtyDTO.eqAsmbId" />
		<html:hidden property="assetListPartOpQtyDTO.partId" />
		<html:hidden property="assetListPartOpQtyDTO.vendorId" />
		<div class="article_box">
			<div class="form_box">
				<!-- 운영기간[년] -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.operatingPeriod" /></label>
					<div class="input_box">
						<html:text property="assetListPartOpQtyDTO.opPeriod" tabindex="10" />
					</div>
				</div>
				<!-- 운영수량 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.useQty" /></label>
					<div class="input_box">
						<html:text property="assetListPartOpQtyDTO.opQty" tabindex="20" />
					</div>
				</div>
				<!-- 납품업체PO번호 -->
				<div class="field">
					<label><bean:message key="LABEL.poNumber" /></label>
					<div class="input_box">
						<html:text property="assetListPartOpQtyDTO.venPoNo" tabindex="30" />
					</div>
				</div>
				<!-- 거래처 -->
				<div class="field ty1-2">
					<label><bean:message key="LABEL.vendor" /></label>
					<div class="multi_wrap">
						<div class="input_box">
							<html:text property="assetListPartOpQtyDTO.vendorNo" tabindex="40" />
							<p class="open_spop"><a><span>조회</span></a>
						</div>
						<div class="input_read">
							<html:text property="assetListPartOpQtyDTO.vendorDesc" readonly="true" />
						</div>
					</div>
				</div>
				<!-- 정렬순서 -->
				<div class="field">
					<label><bean:message key="LABEL.ordNo" /></label>
					<div class="input_box">
						<html:text property="assetListPartOpQtyDTO.ordNo" tabindex="50" />
					</div>
				</div>
				<!-- 비고 -->
				<div class="field_long">
					<label><bean:message key="LABEL.remark"/></label>
					<div class="input_box">
						<html:textarea property="assetListPartOpQtyDTO.remark" tabindex="60" styleClass="ta50"/>
					</div>
				</div>
			</div>
			<!-- End of Form_box -->
		</div>
		<!-- End of Article_box -->
	</html:form>
</body>
</html:html>
