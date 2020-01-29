<%--===========================================================================
문서분류체계 - 상세
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.doc.ctg.action.DocCtgDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<html:html> 
<head>
<!-- 문서분류체계 -->
<title><bean:message key='LABEL.wcode' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">// 저장 시 수행되는 action
<!--//

var docctgAc, plantAc;


function loadPage() 
{
    if(ckCreate(currentPageId)) goInput();
    else 
    {
    	setPtWhTitle();
    	goUpdate();
    }
    
    
    setForUpdate();

    
    acSysDesc("docCtgDetailDTO.isUse","docCtgDetailDTO.isUse","IS_USE", true);
    
    docctgAc = new autoC({"docCtgDetailDTO.pdocctgDesc":"description"});
    docctgAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
	});
    docctgAc.setAcDConditionMap({
        "docctg_id":"docCtgDetailDTO.docctgId"
    });
    docctgAc.setTable("TADOCCTG");
    docctgAc.setAcResultMap({
        "docCtgDetailDTO.pdocctgId":"docctg_id"
    });
    docctgAc.init();
    
    plantAc = new autoC({"docCtgDetailDTO.plantDesc":"description"});
    plantAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    plantAc.setTable("TAPLANT");
    plantAc.setKeyName("docCtgDetailDTO.plant");
    plantAc.setAcResultMap({
        "docCtgDetailDTO.plant":"plant"
    });
    plantAc.init(); 
}

function setPtWhTitle()
{
    setTitle("docCtgDetailDTO.docctgNo", "docCtgDetailDTO.description");
}

/**
 * 입력
 */
function goInput()
{
	sequenceNextVal('SQADOCCTG_ID');
	
	docCtgDetailForm.elements['docCtgDetailDTO.isUse'].value = 'Y';
    valSysDir('docCtgDetailDTO.isUse', 'docCtgDetailDTO.isUse', 'IS_USE', true);

}

function setSequenceVal(sequenceVal)
{
	docCtgDetailForm.elements['docCtgDetailDTO.docctgId'].value = sequenceVal;
	docCtgDetailForm.elements['docCtgCommonDTO.docctgId'].value = sequenceVal;
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
    if(ckCreate(currentPageId)) docCtgDetailForm.strutsAction.value = "<%=DocCtgDetailAction.DOCCTG_DETAIL_INPUT%>";
    else docCtgDetailForm.strutsAction.value = '<%=DocCtgDetailAction.DOCCTG_DETAIL_UPDATE%>';
    
    var actionUrl = contextPath + "/docCtgDetail.do";
    XMLHttpPost(actionUrl, FormQueryString(docCtgDetailForm), 'afterSave');
}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
   //=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    docCtgDetailForm.elements['docCtgCommonDTO.docctgId'].value = docCtgDetailForm.elements['docCtgDetailDTO.docctgId'].value;

    if (parent.findGridRow)	parent.findGridRow(docCtgDetailForm.elements['docCtgCommonDTO.docctgId'].value);
    getTopPage().afterSaveAll(currentPageId);
    
    goUpdate();
    setPtWhTitle();
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = docCtgDetailForm.elements['docCtgDetailDTO.docctgId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

	<html:form action="/docCtgDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="docCtgCommonDTO.compNo" />
	<html:hidden property="docCtgCommonDTO.docctgId" />
    <html:hidden property="docCtgDetailDTO.docctgId" />
    <html:hidden property="docCtgDetailDTO.pdocctgId" />
    <html:hidden property="docCtgDetailDTO.plant" />
    <html:hidden property="docCtgDetailDTO.fullDesc" />
    
    <div class="article_box" id="detailBox">
        <div class="form_box">
        	<!-- 상위분류 -->
			<div class="field">
				<label><bean:message key="LABEL.pcdUsrdDesc"/></label>
				<div class="input_box">
					<html:text property="docCtgDetailDTO.pdocctgDesc" tabindex="20"/>
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- Plant -->
			<div class="field">
				<label><bean:message key="LABEL.plant"/></label>
				<div class="input_box">
					<html:text property="docCtgDetailDTO.plantDesc" tabindex="20"/>
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 분류명 -->
			<div class="field">
				<label><bean:message key="LABEL.categDesc"/></label>
				<div class="input_box">
					<html:text property="docCtgDetailDTO.description" tabindex="20"/>
				</div>
			</div>
			<!-- 분류코드 -->
			<div class="field">
				<label><bean:message key="LABEL.categCode"/></label>
				<div class="input_box">
					<html:text property="docCtgDetailDTO.docctgNo" tabindex="20"/>
				</div>
			</div>
			<!-- 사용여부 -->
			<div class="field">
				<label><bean:message key="LABEL.isUse"/></label>
				<div class="input_box">
					<html:text property="docCtgDetailDTO.isUse" tabindex="20"/>
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 정렬값 -->
			<div class="field">
				<label><bean:message key="LABEL.ordNo"/></label>
				<div class="input_box">
					<html:text property="docCtgDetailDTO.ordNo" tabindex="20" styleClass="num"/>
				</div>
			</div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="docCtgDetailDTO.remark" styleClass="ta50" tabindex="250" />
				</div>
			</div>
			
        </div> <!-- End of Form_box -->
    </div> <!-- End of Article_box -->
</html:form>
            
</body>
</html:html>