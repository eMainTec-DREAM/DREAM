<%--===========================================================================
질의 - 상세
author  kim21017
version $Id: rcmFmeaDetail.jsp,v 1.0 2015/12/04 04:13:54 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="common.bean.User"%>
<%@ page import="dream.rcm.fmea.action.RcmFmeaDetailAction"%>
<%@ page import="dream.doc.file.action.MaDocLibListAction"%>
<html:html>
<head>
<title><bean:message key="LABEL.qnaAns"/>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">

var failureMo,failureCa,failureRe;
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else 
	{
		goUpdate();
	}
	
	setTitle("rcmFmeaDetailDTO.failDesc");
	
	setForUpdate();

	/** 모드  */ 
	failureMo = new autoC({"rcmFmeaDetailDTO.mofailDesc":"failName"});
    failureMo.setTable("TAFAILURE");
    failureMo.setAcResultMap({
        "rcmFmeaDetailDTO.mofailId":"failure_id",
        "rcmFmeaDetailDTO.mofcd":"failure_no"
    });
    failureMo.setAcConditionMap({
  	   "fail_type":"MO"
  	   });
    failureMo.setKeyName("rcmFmeaDetailDTO.mofailId");
    failureMo.init();
    
	/** 원인 */
    failureCa = new autoC({"rcmFmeaDetailDTO.cafailDesc":"failureDesc"});
    failureCa.setTable("TAFAILURE");
    failureCa.setAcResultMap({
    	"rcmFmeaDetailDTO.cafailId":"failure_id",
        "rcmFmeaDetailDTO.cafcd":"failure_no"
    });
    failureCa.setAcConditionMap({
  	   "fail_type":"CA"
  	   });
    failureCa.setKeyName("rcmFmeaDetailDTO.cafailId");
    failureCa.init();
    
    
    /** 영향 */
    failureRe = new autoC({"rcmFmeaDetailDTO.effailDesc":"failName"});
    failureRe.setTable("TAFAILURE");
    failureRe.setAcResultMap({
    	"rcmFmeaDetailDTO.effailId":"failure_id",
        "rcmFmeaDetailDTO.effcd":"failure_no"
    });
    failureRe.setAcConditionMap({
  	   "fail_type":"EF"
  	   });
    failureRe.setKeyName("rcmFmeaDetailDTO.effailId");
    failureRe.init();
}

function goUpdate()
{

}

function goInput()
{
	sequenceNextVal('SQARCMFMEA_ID');
}

function setSequenceVal(sequenceVal)
{
	rcmFmeaDetailForm.elements['rcmFmeaDetailDTO.rcmfmeaId'].value = sequenceVal;
	rcmFmeaDetailForm.elements['rcmFmeaCommonDTO.rcmfmeaId'].value = sequenceVal;
}

function goOpen(pageId)
{
	goTabPage(pageId);
}

function goTabPage(pageId)
{
	var form = document.rcmFmeaDetailForm;

	if(pageId == "maDocLibList" || pageId == "rcmFmeaDocLibList")
	{	
		rcmFmeaDetailForm.elements['maDocLibCommonDTO.objectId'].value = rcmFmeaDetailForm.elements['rcmFmeaCommonDTO.rcmfmeaId'].value;
		rcmFmeaDetailForm.elements['maDocLibCommonDTO.objectType'].value = "FMEA";
		rcmFmeaDetailForm.elements['maDocLibCommonDTO.description'].value = rcmFmeaDetailForm.elements['rcmFmeaDetailDTO.failDesc'].value;
		goCommonTabPage(form, '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>' , pageId);
	}
	else
		goCommonTabPage(form, '' , pageId);
    
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
	
	if(ckCreate(currentPageId)) rcmFmeaDetailForm.strutsAction.value = '<%=RcmFmeaDetailAction.FMEA_DETAIL_INPUT%>';
	else rcmFmeaDetailForm.strutsAction.value = '<%=RcmFmeaDetailAction.FMEA_DETAIL_UPDATE%>';
	
	
	var actionUrl = contextPath + "/rcmFmeaDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(rcmFmeaDetailForm), 'afterSave');
	
}

 /**
  * 저장후 호출
  */
 function afterSave(ajaxXmlDoc)
 {
 	//=====================================
     if (!checkHttpXml(ajaxXmlDoc)) return;
     //=====================================

    var rcmlistId = rcmFmeaDetailForm.elements['rcmFmeaCommonDTO.rcmlistId'].value;
    var rcmfuncId = rcmFmeaDetailForm.elements['rcmFmeaCommonDTO.rcmfuncId'].value;
    var rcmffailId =  rcmFmeaDetailForm.elements['rcmFmeaCommonDTO.rcmffailId'].value;
    var rcmeqid = rcmFmeaDetailForm.elements['rcmFmeaCommonDTO.rcmeqId'].value;
    var rcmeqasmbId = rcmFmeaDetailForm.elements['rcmFmeaCommonDTO.rcmeqasmbId'].value;
    var rcmfmeaId = rcmFmeaDetailForm.elements['rcmFmeaCommonDTO.rcmfmeaId'].value;
	
   	 if (parent.findGridRow)	parent.findGridRow([rcmlistId,rcmfuncId,rcmffailId,rcmeqid,rcmeqasmbId,rcmfmeaId]);

     getTopPage().afterSaveAll(currentPageId);
 }
 /**
  *  Audit trail
  */
  function goAudtrailLink()
  {
     var objectId = rcmFmeaDetailForm.elements['rcmFmeaDetailDTO.rcmfmeaId'].value;
     var fileName = currentPageId;

     if(typeof objectId=="undefined") return;

     goAudTrailList(objectId, fileName);
  }

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/rcmFmeaDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="rcmFmeaCommonDTO.rcmlistId"/>
	<html:hidden property="rcmFmeaCommonDTO.rcmfuncId"/>
	<html:hidden property="rcmFmeaCommonDTO.rcmffailId"/>
	<html:hidden property="rcmFmeaCommonDTO.rcmeqId"/>
	<html:hidden property="rcmFmeaCommonDTO.rcmeqasmbId"/>
	<html:hidden property="rcmFmeaCommonDTO.rcmfmeaId"/>
	<html:hidden property="rcmFmeaCommonDTO.critylistId"/>

	<html:hidden property="rcmFmeaDetailDTO.rcmlistId"/>
	<html:hidden property="rcmFmeaDetailDTO.rcmfuncId"/>
	<html:hidden property="rcmFmeaDetailDTO.rcmffailId"/>
	<html:hidden property="rcmFmeaDetailDTO.rcmeqId"/>
	<html:hidden property="rcmFmeaDetailDTO.rcmeqasmbId"/>
	<html:hidden property="rcmFmeaDetailDTO.rcmfmeaId"/>
	
	<html:hidden property="rcmFmeaDetailDTO.mofcd" />
	<html:hidden property="rcmFmeaDetailDTO.mofailId" />
	<html:hidden property="rcmFmeaDetailDTO.cafcd" />
	<html:hidden property="rcmFmeaDetailDTO.cafailId" />
	<html:hidden property="rcmFmeaDetailDTO.effcd" />
	<html:hidden property="rcmFmeaDetailDTO.effailId" />
	<html:hidden property="rcmFmeaDetailDTO.rcmffeqId" />
	
 	<html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
 	<html:hidden property="maDocLibCommonDTO.objectType" />
 	<html:hidden property="maDocLibCommonDTO.description" />
	<div class="article_box">
		<div class="form_box">
				<!-- 설비 -->
				<div class="field_long">
					<label><bean:message key="LABEL.equipName"/></label>
					<div class="input_read">
						<html:text property="rcmFmeaDetailDTO.equipDesc" tabindex="10" readonly="true"/>
					</div>
				</div>
				<!-- 기능 -->
				<div class="field_long">
					<label><bean:message key="LABEL.funcName"/></label>
					<div class="input_read">
						<html:text property="rcmFmeaDetailDTO.funcDesc" tabindex="20"  readonly="true"/>
					</div>
				</div>
				<!-- 기능고장 -->
				<div class="field_long">
					<label><bean:message key="LABEL.rcmFfailName"/></label>
					<div class="input_read">
						<html:text property="rcmFmeaDetailDTO.failDesc" tabindex="30"  readonly="true"/>
					</div>
				</div>
				<!-- 부위 -->
				<div class="field_long">
					<label><bean:message key="LABEL.asmb"/></label>
					<div class="input_read">
						<html:text property="rcmFmeaDetailDTO.asmbDesc" tabindex="40"  readonly="true"/>
					</div>
				</div>
				<!-- 고장모드 -->
				<div class="field">
					<label><bean:message key="LABEL.mofailDesc"/></label>
					<div class="input_box">
						<html:text property="rcmFmeaDetailDTO.mofailDesc" tabindex="50" />
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 고장원인 -->
				<div class="field">
					<label><bean:message key="LABEL.caCd"/></label>
					<div class="input_box">
						<html:text property="rcmFmeaDetailDTO.cafailDesc" tabindex="50" />
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 고장영향 -->
				<div class="field">
					<label><bean:message key="LABEL.effailDesc"/></label>
					<div class="input_box">
						<html:text property="rcmFmeaDetailDTO.effailDesc" tabindex="50" />
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 비고 -->
				<div class="field_long">
					<label><bean:message key="LABEL.remark"/></label>
					<div class="input_box">
						<html:textarea property="rcmFmeaDetailDTO.remark" styleClass="ta50" tabindex="250" />
					</div>
				</div>
			</div> <!-- End of Form_box -->
		</div> <!-- End of Article_box -->
	</html:form>
</body>
</html:html>