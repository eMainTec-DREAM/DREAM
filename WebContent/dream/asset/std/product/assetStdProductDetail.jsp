<%--===========================================================================
생산품목 - 상세
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.std.product.action.AssetStdProductDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<html:html>
<head>
<!-- 생산품목 -->
<title><bean:message key='LABEL.docCountrNo' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">// 저장 시 수행되는 action
<!-- //

function loadPage() 
{
	
	if(ckCreate(currentPageId)) goInput();
	else 
    {
        goUpdate();
    }
	
    setTitle("assetStdProductDetailDTO.productNo", "assetStdProductDetailDTO.description");
    //For Save
    setForUpdate();
    
    /**사용여부 */
    acSysDesc("assetStdProductDetailDTO.isUse","assetStdProductDetailDTO.isUse","IS_USE",true);
    
}

/**
 * 생산품목코드(Product No) 중복 체크
 */
function valProductNo()
{
	var actionUrl = contextPath + "/assetStdProductDetail.do";
	var param =  "&strutsAction=" + '<%= AssetStdProductDetailAction.PRODUCT_DETAIL_CHECK %>'
			  +  "&" + FormQueryString(assetStdProductDetailForm);
	XMLHttpPostVal(actionUrl, param, 'setValidProductNo');
}

/**
 * valProductNo()실행 후 호출
 */
var isValid;
function setValidProductNo(ajaxXmlDoc)
{
	isValid = '0';
    isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
    
    if(isValid != '0')
    {
    	closeModal();
        assetStdProductDetailForm.elements['assetStdProductDetailDTO.productNo'].value = '';
        assetStdProductDetailForm.elements['assetStdProductDetailDTO.productNo'].focus();
        
        // 중복되었습니다.
        alertMessage1("<bean:message key='MESSAGE.MSG0009' />");
    }
    else
    {
    	if(ckCreate(currentPageId)) assetStdProductDetailForm.strutsAction.value = "<%=AssetStdProductDetailAction.PRODUCT_DETAIL_INPUT%>";
    	else assetStdProductDetailForm.strutsAction.value = "<%=AssetStdProductDetailAction.PRODUCT_DETAIL_UPDATE%>";
    	
    	var actionUrl = contextPath + "/assetStdProductDetail.do";
		XMLHttpPost(actionUrl, FormQueryString(assetStdProductDetailForm), 'afterSave');
    }
}

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQAPRODUCT_ID');
    
    assetStdProductDetailForm.elements['assetStdProductDetailDTO.isUse'].value = 'Y';
    
}

/**
 * 수정
 */
function goUpdate()
{
    setDisable(document.getElementById("productNoDiv"));
}

/**
 * set Id 
 */
function setSequenceVal(sequenceVal)
{
    assetStdProductDetailForm.elements['assetStdProductDetailDTO.productId'].value = sequenceVal;
    assetStdProductDetailForm.elements['assetStdProductDetailDTO.productNo'].value = sequenceVal;
    assetStdProductDetailForm.elements['assetStdProductCommonDTO.productId'].value = sequenceVal;
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
    
    valProductNo();
}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
    //=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    assetStdProductDetailForm.elements['assetStdProductDetailDTO.productId'].value = assetStdProductDetailForm.elements['assetStdProductCommonDTO.productId'].value;
    if (parent.findGridRow) parent.findGridRow(assetStdProductDetailForm.elements['assetStdProductCommonDTO.productId'].value);
    getTopPage().afterSaveAll(currentPageId);

    goUpdate();
    setTitle("assetStdProductDetailDTO.productNo", "assetStdProductDetailDTO.description");
    
}


function goTabPage(pageId) 
{
    var form = document.assetStdProductDetailForm;
    goCommonTabPage(form, '' , pageId);
       
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = assetStdProductDetailForm.elements['assetStdProductDetailDTO.productId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

<html:form action="/assetStdProductDetail" >
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="assetStdProductCommonDTO.productId" />
<html:hidden property="assetStdProductDetailDTO.productId" /><!-- key -->
	 
	 <div class="article_box">
            <div class="form_box">
            	 <!--생산품목코드  -->
            	 <div class="field" id="productNoDiv">
            	 	<label class="check"><bean:message key="LABEL.productCode"/></label>
            	 	<div class="input_box">
            	 		<html:text property="assetStdProductDetailDTO.productNo"  tabindex="10"/> 
            	 	</div>
            	 </div>
	             <!--사용여부  -->
                 <div class="field">
	                <label><bean:message key="LABEL.isUse"/></label>
	                <div class="input_box">
	                    <html:text property="assetStdProductDetailDTO.isUse" tabindex="20"/>
	                    <p class="open_spop"><a><span>조회</span></a></p>
	                </div>
	             </div>
            	 <!--생산품목명  -->
            	 <div class="field_long">
            	 	<label class="check"><bean:message key="LABEL.productName"/></label>
            	 	<div class="input_box">
            	 		<html:text property="assetStdProductDetailDTO.description" tabindex="30"/>
            	 	</div>
            	 </div>
            	 <!--비고  -->
            	 <div class="field_long">
            	 	<label><bean:message key="LABEL.remark"/></label>
            	 	<div class="input_box">
            	 		<html:textarea property="assetStdProductDetailDTO.remark" styleClass="ta100" tabindex="80"/>
            	 	</div>
            	 </div>
            </div> <!-- End of Form_box -->
        </div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>
