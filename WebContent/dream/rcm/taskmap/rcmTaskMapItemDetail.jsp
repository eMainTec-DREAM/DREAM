
<%--===========================================================================
응답 상세
author  kim21017
version $Id: rcmTaskMapItemDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
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
<%@page import="dream.rcm.taskmap.action.RcmTaskMapItemDetailAction"%>
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
var yIdDescAc, nIdDescAc;
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	
	setTitle("rcmTaskMapItemDetailDTO.taskDesc");
	setForUpdate();

	yIdDescAc = new autoC({"rcmTaskMapItemDetailDTO.yidDesc":"pmtaskmap_no"});
	yIdDescAc.setTable("TAPMTASKMAP");
	yIdDescAc.setAcResultMap({
    	 "rcmTaskMapItemDetailDTO.yid":"pmtaskmap_id",
         "rcmTaskMapItemDetailDTO.yidDesc":"pmtaskmap_no"
    });
	yIdDescAc.setAcDConditionMap({
	 	   "pmtaskmaplist_id":"rcmTaskMapCommonDTO.pmTaskMapListId",
	 	   "pmtaskmap_id":"rcmTaskMapItemDetailDTO.pmTaskmapId",
	 	   });
	yIdDescAc.setKeyName("rcmTaskMapItemDetailDTO.yid");
    yIdDescAc.init();
    
	nIdDescAc = new autoC({"rcmTaskMapItemDetailDTO.nidDesc":"pmtaskmap_no"});
	nIdDescAc.setTable("TAPMTASKMAP");
	nIdDescAc.setAcResultMap({
    	 "rcmTaskMapItemDetailDTO.nid":"pmtaskmap_id",
         "rcmTaskMapItemDetailDTO.nidDesc":"pmtaskmap_no"
    });
	nIdDescAc.setAcDConditionMap({
	 	   "pmtaskmaplist_id":"rcmTaskMapCommonDTO.pmTaskMapListId",
	 	   "pmtaskmap_id":"rcmTaskMapItemDetailDTO.pmTaskmapId",
	 	   });
	nIdDescAc.setKeyName("rcmTaskMapItemDetailDTO.nid");
    nIdDescAc.init();
    
	acSysDesc("rcmTaskMapItemDetailDTO.ytypeDesc","rcmTaskMapItemDetailDTO.ytypeId","TASKMAP_RSLT_TYPE");
	acSysDesc("rcmTaskMapItemDetailDTO.ntypeDesc","rcmTaskMapItemDetailDTO.ntypeId","TASKMAP_RSLT_TYPE");
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAANSWER_ID');
	
    var actionUrl = contextPath + "/rcmTaskMapItemDetail.do";
    var param =  "&strutsAction=" + '<%= RcmTaskMapItemDetailAction.RCM_NEXT_NO %>'
              +  "&" + FormQueryString(rcmTaskMapItemDetailForm);
    XMLHttpPostVal(actionUrl, param, 'setTaskNo');

}

function setTaskNo(ajaxXmlDoc)
{	
    taskNo = parseXmlDoc(ajaxXmlDoc, 'DESC');
    rcmTaskMapItemDetailForm.elements['rcmTaskMapItemDetailDTO.taskMapNo'].value=taskNo;

}

function setSequenceVal(sequenceVal)
{
	rcmTaskMapItemDetailForm.elements['rcmTaskMapItemDetailDTO.pmTaskmapId'].value = sequenceVal;
	
}

function goSave(){
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	
	//질문일경우 질문항목 필수
	if(rcmTaskMapItemDetailForm.elements['rcmTaskMapItemDetailDTO.ytypeId'].value=='Q'&&rcmTaskMapItemDetailForm.elements['rcmTaskMapItemDetailDTO.yid'].value=='')
	{
		rcmTaskMapItemDetailForm.elements['rcmTaskMapItemDetailDTO.yidDesc'].focus();        
        // 중복되었습니다.
        alertMessage1('<bean:message key="LABEL.yId"/>' + " " + COMMON_CMSG047);
        closeModal();
        return;
	}
	
	if(rcmTaskMapItemDetailForm.elements['rcmTaskMapItemDetailDTO.ntypeId'].value=='Q'&&rcmTaskMapItemDetailForm.elements['rcmTaskMapItemDetailDTO.nid'].value=='')
	{
		rcmTaskMapItemDetailForm.elements['rcmTaskMapItemDetailDTO.nidDesc'].focus();        
        // 중복되었습니다.
        alertMessage1('<bean:message key="LABEL.nId"/>' + " " + COMMON_CMSG047);
        closeModal();
        return;
	}
	
	//업무선택일경우 질문항목 필수
	if(rcmTaskMapItemDetailForm.elements['rcmTaskMapItemDetailDTO.ytypeId'].value=='S'&&rcmTaskMapItemDetailForm.elements['rcmTaskMapItemDetailDTO.ytask'].value=='')
	{
		rcmTaskMapItemDetailForm.elements['rcmTaskMapItemDetailDTO.yTask'].focus();        
        // 중복되었습니다.
        alertMessage1('<bean:message key="LABEL.yId"/>' + " " + COMMON_CMSG047);
        closeModal();
        return;
	}
	
	if(rcmTaskMapItemDetailForm.elements['rcmTaskMapItemDetailDTO.ntypeId'].value=='S'&&rcmTaskMapItemDetailForm.elements['rcmTaskMapItemDetailDTO.ntask'].value=='')
	{
		rcmTaskMapItemDetailForm.elements['rcmTaskMapItemDetailDTO.ntask'].focus();        
        // 중복되었습니다.
        alertMessage1('<bean:message key="LABEL.nTask"/>' + " " + COMMON_CMSG047);
        closeModal();
        return;
	}
	
	if(ckCreate(currentPageId)) rcmTaskMapItemDetailForm.strutsAction.value = '<%=RcmTaskMapItemDetailAction.QNA_ANS_DETAIL_INPUT%>';
	else rcmTaskMapItemDetailForm.strutsAction.value = '<%= RcmTaskMapItemDetailAction.QNA_ANS_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/rcmTaskMapItemDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(rcmTaskMapItemDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(rcmTaskMapItemDetailForm.elements['rcmTaskMapItemDetailDTO.pmTaskmapId'].value);

	setTitle("rcmTaskMapItemDetailDTO.rcmTaskMapNo");
    getTopPage().afterSaveAll(currentPageId);
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = rcmTaskMapItemDetailForm.elements['rcmTaskMapItemDetailDTO.pmTaskmapId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>

</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/rcmTaskMapItemDetail.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="rcmTaskMapCommonDTO.pmTaskMapListId"/>
<html:hidden property="rcmTaskMapItemDetailDTO.pmTaskmapId"/>
<html:hidden property="rcmTaskMapItemDetailDTO.ytypeId"/>
<html:hidden property="rcmTaskMapItemDetailDTO.yid"/>
<html:hidden property="rcmTaskMapItemDetailDTO.ntypeId"/>
<html:hidden property="rcmTaskMapItemDetailDTO.nid"/>
    <!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<div class="field">
				<label class="check"><bean:message key="LABEL.taskMapNo"/></label>
				<div class="input_read">
					<html:text property="rcmTaskMapItemDetailDTO.taskMapNo" tabindex="50" readonly="true"/>
				</div>
			</div>
			<!-- 질의항목 -->
			<div class="field_long">
				<label><bean:message key="LABEL.taskDesc"/></label>
				<div class="input_box">
					<html:textarea property="rcmTaskMapItemDetailDTO.taskDesc" tabindex="60" />
				</div>
			</div>
			<!-- Yes일때 -->
			<div class="field">
                <label><bean:message key="LABEL.yType"/></label>
                <div class="input_box">
                    <html:text property="rcmTaskMapItemDetailDTO.ytypeDesc" tabindex="30"/>
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                </div>
            </div>
			<!-- Yes질문 -->
			<div class="field">
                <label><bean:message key="LABEL.yId"/></label>
                <div class="input_box">
                    <html:text property="rcmTaskMapItemDetailDTO.yidDesc" tabindex="30"/>
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                </div>
            </div>	
			<div class="field_long">
                <label><bean:message key="LABEL.yTask"/></label>
                <div class="input_box">
                    <html:text property="rcmTaskMapItemDetailDTO.ytask" tabindex="30"/>
                </div>
             </div>
             <!-- No일때 -->
			<div class="field">
                <label><bean:message key="LABEL.nType"/></label>
                <div class="input_box">
                    <html:text property="rcmTaskMapItemDetailDTO.ntypeDesc" tabindex="30"/>
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                </div>
            </div>
			<!-- Yes질문 -->
			<div class="field">
                <label><bean:message key="LABEL.nId"/></label>
                <div class="input_box">
                    <html:text property="rcmTaskMapItemDetailDTO.nidDesc" tabindex="30"/>
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                </div>
            </div>	
			<div class="field_long">
                <label><bean:message key="LABEL.nTask"/></label>
                <div class="input_box">
                    <html:text property="rcmTaskMapItemDetailDTO.ntask" tabindex="30"/>
                </div>
             </div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="rcmTaskMapItemDetailDTO.remark" styleClass="ta50" tabindex="60" />
				</div>
			</div>
		</div>
	</div><!--article_box end-->
</html:form> 
</body>
</html>