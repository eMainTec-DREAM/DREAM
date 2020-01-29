<%--===========================================================================
자재재고 - 상세
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.part.stk.action.PartStkCurrentAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="common.bean.User"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html:html> 
<head>
<!-- 자재재고 -->
<title><bean:message key='LABEL.partNo' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">// 저장 시 수행되는 action
var partNoAc, whAc;
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else goUpdate(); 
	
	setTitle("partStkCurrentDTO.wname", "partStkCurrentDTO.partNo");
	
    setForUpdate();
}

/**
 * 입력
 */
function goInput()
{
   partNoAc = new autoC({"partStkCurrentDTO.partNo":"part_no"}); // 조건식 자재명과 자재번호를 동시에 검색할때. 
   partNoAc.setAcResultMap({
	   "partStkCurrentDTO.partNameSize":"partNameSize", 
	   "partStkCurrentDTO.partDesc":"description", 
	   "partStkCurrentDTO.ptSize":"pt_size", 
	   "partStkCurrentDTO.model":"model", 
	   "partStkCurrentDTO.partId":"part_id",
	   "partStkCurrentDTO.partId":"part_id"
	   });   
   partNoAc.setAcConditionMap({
	   "part_categ":"SPPT",
	   "comp_no":loginUser.compNo
	   });
   partNoAc.setTable("TAPARTS");
   partNoAc.setKeyName("partStkCurrentDTO.partId"); 
   partNoAc.init();
   
    
   whAc = new autoC({"partStkCurrentDTO.wname":"wname"});
   whAc.setAcResultMap({
	     "partStkCurrentDTO.wcodeId":"wcode_id"
	    ,"partStkCurrentDTO.whType":"wh_type"
	   }); 
   whAc.setTable("TAWAREHOUSE");
   whAc.setAcConditionMap({
	   "wh_categ":"PART",
	   "comp_no":loginUser.compNo
	   });
   whAc.setKeyName("partStkCurrentDTO.wcodeId"); 
   whAc.init();
   
}

/**
 * 수정
 */
function goUpdate()
{
	setDisableAll();
}

/**
* Tab 이동시 호출
*/
function goTabPage(pageId)
{
	var form = document.partStkCurrentForm;
	goCommonTabPage(form, '' , pageId);
}


/* audit Trail */
function goAudtrailLink()
{
	var objectId = partStkCurrentForm.elements['partStkCurrentDTO.partId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}

</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

	<html:form action="/partStkCurrentDetail.do" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="partStkCurrentDTO.compNo" />
	<html:hidden property="partStkCurrentDTO.wcodeId" />
	<html:hidden property="partStkCurrentDTO.partId" />
    <html:hidden property="partStkCurrentDTO.wcodeId" />
    <html:hidden property="partStkCurrentDTO.partId" />
    <html:hidden property="partStkCurrentDTO.whType" />
    <html:hidden property="partStkCurrentDTO.partGrade" />
    <html:hidden property="partStkCurrentDTO.isSerial" />
	<html:hidden property="maPtMstrDetailDTO.partId" />
	<html:hidden property="maPtMstrDetailDTO.partNo" />
	<html:hidden property="maPtMstrDetailDTO.description" />
	<html:hidden property="maPtMstrDetailDTO.ptSize" />
	<html:hidden property="maPtMstrDetailDTO.model" />
	
    <div class="article_box">
        <div class="form_box">
        	 <div class="field">
        	 	<label><bean:message key="LABEL.wname"/></label>
        	 	<div class="input_box">
        	 		<html:text property="partStkCurrentDTO.wname" tabindex="20"/>
        	 	</div>
        	 </div>
        	 <div class="field">
        	 	<label><bean:message key="LABEL.partNo"/></label>
                <div class="input_box">
                    <html:text property="partStkCurrentDTO.partNo" tabindex="30"/>
                </div>
        	 </div>
        	 <div class="field">
        	 	<label><bean:message key="LABEL.partDesc"/></label>
        	 	<div class="input_read">
        	 		<html:text property="partStkCurrentDTO.partDesc" readonly="true" tabindex="40"/>
        	 	</div>
        	 </div>
        	 <div class="field">
        	 	<label><bean:message key="LABEL.ptSize"/></label>
        	 	<div class="input_read">
        	 		<html:text property="partStkCurrentDTO.ptSize" readonly="true" tabindex="40"/>
        	 	</div>
        	 </div>
        	 <div class="field">
        	 	<label><bean:message key="LABEL.model"/></label>
        	 	<div class="input_read">
        	 		<html:text property="partStkCurrentDTO.model" readonly="true" tabindex="40"/>
        	 	</div>
        	 </div>
        	 <div class="field_long">
        	 	<label><bean:message key="LABEL.partNameSize"/></label>
        	 	<div class="input_read">
        	 		<html:text property="partStkCurrentDTO.partNameSize" readonly="true" tabindex="40"/>
        	 	</div>
        	 </div>
        	 <div class="field">
        	 	<label><bean:message key="LABEL.aStockQty"/></label>
        	 	<div class="input_box">
        	 		<html:text property="partStkCurrentDTO.astockQty" tabindex="60" styleClass="num"/>
        	 	</div>
        	 </div>
        	 <div class="field">
        	 	<label><bean:message key="LABEL.bStockQty"/></label>
        	 	<div class="input_box">
        	 		<html:text property="partStkCurrentDTO.bstockQty" tabindex="70" styleClass="num"/>
        	 	</div>
        	 </div>
        	 <div class="field">
        	 	<label><bean:message key="LABEL.aBinNo"/></label>
        	 	<div class="input_box">
        	 		<html:text property="partStkCurrentDTO.abinNo" tabindex="80"/>
        	 	</div>
        	 </div>
        	 <div class="field">
        	 	<label><bean:message key="LABEL.bBinNo"/></label>
        	 	<div class="input_box">
        	 		<html:text property="partStkCurrentDTO.bbinNo" tabindex="90"/>
        	 	</div>
        	 </div>
        	 <div class="field">
        	 	<label><bean:message key="LABEL.minSaftyQty"/></label>
        	 	<div class="input_box">
        	 		<html:text property="partStkCurrentDTO.minSaftyQty" tabindex="100" styleClass="num"/>
        	 	</div>
        	 </div>
        	 <div class="field">
        	 	<label><bean:message key="LABEL.maxSaftyQty"/></label>
        	 	<div class="input_box">
        	 		<html:text property="partStkCurrentDTO.maxSaftyQty" tabindex="110" styleClass="num"/>
        	 	</div>
        	 </div>
        </div> <!-- End of Form_box -->
    </div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>
