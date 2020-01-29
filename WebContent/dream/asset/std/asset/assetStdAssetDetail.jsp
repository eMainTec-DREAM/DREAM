<%--===========================================================================
회계자산 - 상세
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.std.asset.action.AssetStdAssetDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<html:html>
<head>
<!-- 회계자산 -->
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
	
    setTitle("assetStdAssetDetailDTO.assetNo", "assetStdAssetDetailDTO.description");
    //For Save
    setForUpdate();
    
    /**사용여부 */
    acSysDesc("assetStdAssetDetailDTO.isUse","assetStdAssetDetailDTO.isUse","IS_USE",true);
    
}

/**
 * 자산코드(Asset No) 중복 체크
 */
function valAssetNo()
{
	var actionUrl = contextPath + "/assetStdAssetDetail.do";
	var param =  "&strutsAction=" + '<%= AssetStdAssetDetailAction.ASSET_DETAIL_CHECK %>'
			  +  "&" + FormQueryString(assetStdAssetDetailForm);
	XMLHttpPostVal(actionUrl, param, 'setValidAssetNo');
}

/**
 * valAssetNo()실행 후 호출
 */
var isValid;
function setValidAssetNo(ajaxXmlDoc)
{
	isValid = '0';
    isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
    
    if(isValid != '0')
    {
    	closeModal();
        assetStdAssetDetailForm.elements['assetStdAssetDetailDTO.assetNo'].value = '';
        assetStdAssetDetailForm.elements['assetStdAssetDetailDTO.assetNo'].focus();
        
        // 중복되었습니다.
        alertMessage1("<bean:message key='MESSAGE.MSG0009' />");
    }
    else
    {
    	//strutsAction 셋팅.
        if(ckCreate(currentPageId)) assetStdAssetDetailForm.strutsAction.value = "<%=AssetStdAssetDetailAction.ASSET_DETAIL_INPUT%>";
        else assetStdAssetDetailForm.strutsAction.value = "<%=AssetStdAssetDetailAction.ASSET_DETAIL_UPDATE%>";
        
    	var actionUrl = contextPath + "/assetStdAssetDetail.do";
    	XMLHttpPost(actionUrl, FormQueryString(assetStdAssetDetailForm), 'afterSave');
    }
}

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQAASSET_ID');
    
    assetStdAssetDetailForm.elements['assetStdAssetDetailDTO.isUse'].value = 'Y';
    
}

/**
 * 수정
 */
function goUpdate()
{
    setDisable(document.getElementById("assetNoDiv"));
}

/**
 * set Id 
 */
function setSequenceVal(sequenceVal)
{
    assetStdAssetDetailForm.elements['assetStdAssetDetailDTO.assetId'].value = sequenceVal;
    assetStdAssetDetailForm.elements['assetStdAssetDetailDTO.assetNo'].value = sequenceVal;
    assetStdAssetDetailForm.elements['assetStdAssetCommonDTO.assetId'].value = sequenceVal;
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
    
    valAssetNo();
}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
    //=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    assetStdAssetDetailForm.elements['assetStdAssetDetailDTO.assetId'].value = assetStdAssetDetailForm.elements['assetStdAssetCommonDTO.assetId'].value;
    if (parent.findGridRow) parent.findGridRow(assetStdAssetDetailForm.elements['assetStdAssetCommonDTO.assetId'].value);
    getTopPage().afterSaveAll(currentPageId);

    goUpdate();
    setTitle("assetStdAssetDetailDTO.assetNo", "assetStdAssetDetailDTO.description");
    
}

function goRefeqlist(){

	var id = assetStdAssetDetailForm.elements['assetStdAssetDetailDTO.assetId'].value;
	
	if(typeof id=="undefined") return ;
    
    var assetId = id
    var assetDesc = assetStdAssetDetailForm.elements['assetStdAssetDetailDTO.description'].value;
    var url   = contextPath + "/maEqMstrList.do";
    var popWidth = 1010;
    var popHeight = 640;
    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
    
    var param = "isDecoratorName=popupPage"+
                "&maEqMstrCommonDTO.strutsAction="+
                "&maEqMstrCommonDTO.filterAccAssetId="+assetId+
                "&maEqMstrCommonDTO.filterAccAssetDesc="+assetDesc
                +"&ACTION_FUNCTION=goSearch";

    //post 전송
    openWindowWithPost(url, "EQ_MSTR_LIST_FIND", param, pos);
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = assetStdAssetDetailForm.elements['assetStdAssetDetailDTO.assetId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

<html:form action="/assetStdAssetDetail" >
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="assetStdAssetCommonDTO.assetId" />
<html:hidden property="assetStdAssetDetailDTO.assetId" /><!-- key -->
	 
	 <div class="article_box">
            <div class="form_box">
            	 <!--회계자산코드  -->
            	 <div class="field" id="assetNoDiv">
            	 	<label class="check"><bean:message key="LABEL.accAssetCode"/></label>
            	 	<div class="input_box">
            	 		<html:text property="assetStdAssetDetailDTO.assetNo"  tabindex="10"/> 
            	 	</div>
            	 </div>
	             <!--사용여부  -->
                 <div class="field">
	                <label><bean:message key="LABEL.isUse"/></label>
	                <div class="input_box">
	                    <html:text property="assetStdAssetDetailDTO.isUse" tabindex="20"/>
	                    <p class="open_spop"><a><span>조회</span></a></p>
	                </div>
	             </div>
            	 <!--회계자산명  -->
            	 <div class="field_long">
            	 	<label class="check"><bean:message key="LABEL.accAssetName"/></label>
            	 	<div class="input_box">
            	 		<html:text property="assetStdAssetDetailDTO.description" tabindex="30"/>
            	 	</div>
            	 </div>
	             <!--취득일자  -->
            	 <div class="field">
            	 	<label><bean:message key="LABEL.acqDate"/></label>
            	 	<div class="input_box input_carendar">
            	 		<html:text property="assetStdAssetDetailDTO.acqDate" tabindex="40"/>
            	 		<p class="open_calendar"><span>날짜</span></p>
            	 	</div>
            	 </div>
            	 <!--취득가액  -->
            	 <div class="field">
            	 	<label><bean:message key="LABEL.buyerAmt"/></label>
            	 	<div class="input_box">
            	 		<html:text property="assetStdAssetDetailDTO.buyerAmt" styleClass="num" tabindex="50"/>
            	 	</div>
            	 </div>
            	 <!--감가상각누계액  -->
            	 <div class="field">
            	 	<label><bean:message key="LABEL.depAmt"/></label>
            	 	<div class="input_box">
            	 		<html:text property="assetStdAssetDetailDTO.depAmt" styleClass="num" tabindex="60"/>
            	 	</div>
            	 </div>
            	 <!--잔존가액  -->
            	 <div class="field">
            	 	<label><bean:message key="LABEL.resAmt"/></label>
            	 	<div class="input_box">
            	 		<html:text property="assetStdAssetDetailDTO.resAmt" styleClass="num" tabindex="70"/>
            	 	</div>
            	 </div>
            	 <!--비고  -->
            	 <div class="field_long">
            	 	<label><bean:message key="LABEL.remark"/></label>
            	 	<div class="input_box">
            	 		<html:textarea property="assetStdAssetDetailDTO.remark" styleClass="ta100" tabindex="80"/>
            	 	</div>
            	 </div>
            </div> <!-- End of Form_box -->
        </div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>
