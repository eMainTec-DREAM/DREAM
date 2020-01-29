
<%--===========================================================================
상세
author  kim21017
version $Id: maPttDisPartsDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
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
<%@ page import="dream.tool.adj.action.MaPttDisPartsDetailAction"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html>
<head>
<!--답변사항 -->
<title><bean:message key="LABEL.questionPoint"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var partNameAc;
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	
	setTitle("maPttDisPartsDetailDTO.partNo");
	setForUpdate();
	
	partNameAc = new autoC({"maPttDisPartsDetailDTO.partNo":"part_no"});
    partNameAc.setAcConditionMap({
	   "part_categ":"TOOL",
	   "comp_no":loginUser.compNo
	   });
    partNameAc.setTable("TAPARTS");
    partNameAc.setAcResultMap({
        "maPttDisPartsDetailDTO.partDesc":"partNameSize"
        ,"maPttDisPartsDetailDTO.partId":"part_id"
    })
    partNameAc.init();
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAPTDISUSEITEM_ID');
	
	<%-- maPttDisPartsDetailForm.elements['maPttDisPartsDetailDTO.regDate'].value = getToday();

	maPttDisPartsDetailForm.elements['maPttDisPartsDetailDTO.deptId'].value    = "<%=user.getDeptId()%>";
	maPttDisPartsDetailForm.elements['maPttDisPartsDetailDTO.deptDesc'].value  = "<%=user.getDeptDesc()%>";
	maPttDisPartsDetailForm.elements['maPttDisPartsDetailDTO.userId'].value     = "<%=user.getUserId()%>";
	maPttDisPartsDetailForm.elements['maPttDisPartsDetailDTO.userDesc'].value   = "<%=user.getUserName()%>";
	 --%>
}

function setSequenceVal(sequenceVal)
{
	maPttDisPartsDetailForm.elements['maPttDisPartsDetailDTO.ptdisuseitemId'].value = sequenceVal;
	maPttDisPartsDetailForm.elements['maPttDisPartsListDTO.ptdisuseitemId'].value = sequenceVal;
}

function goSave(){
	//================================
    // 필수 항목 체크한다.
    //================================
    if(checkValidation()) return;
    
	//strutsAction 셋팅.
	if(ckCreate(currentPageId)) maPttDisPartsDetailForm.strutsAction.value = '<%=MaPttDisPartsDetailAction.QNA_ANS_DETAIL_INPUT%>';
	else maPttDisPartsDetailForm.strutsAction.value = '<%=MaPttDisPartsDetailAction.QNA_ANS_DETAIL_UPDATE%>';
	
	var actionUrl = contextPath + "/maPttDisPartsDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maPttDisPartsDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(maPttDisPartsDetailForm.elements['maPttDisPartsDetailDTO.ptdisuseitemId'].value);

	setTitle("maPttDisPartsDetailDTO.partNo");
    getTopPage().afterSaveAll(currentPageId);
}

function goTabPage(pageId)
{
	var form = document.maPttDisPartsDetailForm;

	goCommonTabPage(form, '' , pageId);
}

/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = maPttDisPartsDetailForm.elements['maPttDisPartsDetailDTO.ptdisuseitemId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/maPttDisPartsDetail.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maPttDisCommonDTO.ptdisuselistId"/>
<html:hidden property="maPttDisPartsListDTO.ptdisuseitemId"/>
<html:hidden property="maPttDisPartsDetailDTO.ptdisuseitemId"/>
<html:hidden property="maPttDisPartsDetailDTO.partId"/>

    <!-- searchbox 박스 Line -->
	<div class="article_box">
			<div class="form_box">
				<div class="field">
                <label class="check"><bean:message key="LABEL.ptNo"/></label>
                <div id="partNoDiv" class="input_box">
                    <html:text property="maPttDisPartsDetailDTO.partNo" tabindex="40"
                        onblur="valToolNo('maPttDisPartsDetailDTO.partId','maPttDisPartsDetailDTO.partNo','maPttDisPartsDetailDTO.partDesc', true);"/>
                    <p class="open_spop">
                        <a href="javascript:lovTools('maPttDisPartsDetailDTO.partId','maPttDisPartsDetailDTO.partNo','maPttDisPartsDetailDTO.partDesc');">
                            <span>조회</span>
                        </a>
                    </p>
                </div>
             </div>
             <div class="field">
                <label class="check"><bean:message key="LABEL.disQty"/></label>
                <div class="input_box">
                    <html:text property="maPttDisPartsDetailDTO.disQty" tabindex="70"  
                         styleClass="num"/>
                </div>
             </div>
             <div class="field_long">
                <label><bean:message key="LABEL.ptNameSize"/></label>
                <div class="input_read">
                    <html:text property="maPttDisPartsDetailDTO.partDesc" tabindex="60" readonly="true"/>
                </div>
            </div>
             <!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="maPttDisPartsDetailDTO.remark" styleClass="ta50" tabindex="190" />
				</div>
			</div>
			</div>
	</div><!--article_box end-->
</html:form> 
</body>
</html>