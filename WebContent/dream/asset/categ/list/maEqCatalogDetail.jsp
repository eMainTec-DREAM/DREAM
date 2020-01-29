
<%--===========================================================================
설비종류 - 상세
author  kim21017
version $Id: maEqCatalogDetail.jsp,v 1.0 2015/12/04 04:13:54 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.asset.categ.list.action.MaEqCatalogDetailAction"%>
<html:html>
<head>
<!-- 종류코드 -->
<title><bean:message key='LABEL.ctgCode' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
var saveStrutsAction;

var eqCtgTypeAc;
var eqTypeAc;
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else 
	{
		goUpdate();
	}
	
	setTitle("maEqCatalogDetailDTO.eqCtgCode","maEqCatalogDetailDTO.eqCtgDesc");
	
	setForUpdate();
	
	eqCtgTypeAc = new autoC({"maEqCatalogDetailDTO.peqCtgDesc":"description"});
    eqCtgTypeAc.setAcDisplay("DESCRIPTION");
    eqCtgTypeAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqCtgTypeAc.setTable("TAEQCTG");
    eqCtgTypeAc.setKeyName("maEqCatalogDetailDTO.peqCtgId");
    eqCtgTypeAc.setAcResultMap({
        "maEqCatalogDetailDTO.peqCtgId":"eqctg_id"
    });
    eqCtgTypeAc.init();
    
	eqTypeAc = new autoC({"maEqCatalogDetailDTO.eqTypeDesc":"description"});
	eqTypeAc.setAcDisplay("DESCRIPTION");
	eqTypeAc.setAcConditionMap({
        	"list_type":"EQCTG_TYPE",
        	"is_use":"Y"
  	   });
	eqTypeAc.setTable("TACDSYSD");
	eqTypeAc.setKeyName("maEqCatalogDetailDTO.eqTypeId");
	eqTypeAc.setAcResultMap({
        "maEqCatalogDetailDTO.eqTypeId":"cdsysd_no"
    });
	eqTypeAc.init();
	
    
	acSysDesc("maEqCatalogDetailDTO.isUse","maEqCatalogDetailDTO.isUse","IS_USE", true);
}

/* 
 * 설비보기
 */
function goMachequipmentLink(_pageId)
{
	var eqCtgId = maEqCatalogDetailForm.elements['maEqCatalogDetailDTO.eqCtgId'].value;
	var eqCtgDesc = maEqCatalogDetailForm.elements['maEqCatalogDetailDTO.eqCtgDesc'].value;
	
	if(typeof eqCtgId=="undefined" &&typeof eqCtgDesc=="undefined") return;
	
	goEquipList('', '', eqCtgId, eqCtgDesc);
}


function goUpdate(){
}

function goInput()
{
	sequenceNextVal('SQAEQCTG_ID');
	maEqCatalogDetailForm.elements['maEqCatalogDetailDTO.isUse'].value = 'Y';
	maEqCatalogDetailForm.elements['maEqCatalogDetailDTO.peqCtgId'].value = maEqCatalogDetailForm.elements['maEqCatalogCommonDTO.detailPctgId'].value;
	maEqCatalogDetailForm.elements['maEqCatalogDetailDTO.peqCtgDesc'].value = maEqCatalogDetailForm.elements['maEqCatalogCommonDTO.detailPctgDesc'].value;
}

function setSequenceVal(sequenceVal)
{
	maEqCatalogDetailForm.elements['maEqCatalogDetailDTO.eqCtgId'].value = sequenceVal;
	maEqCatalogDetailForm.elements['maEqCatalogDetailDTO.eqCtgCode'].value = sequenceVal;
	maEqCatalogDetailForm.elements['maEqCatalogCommonDTO.eqCtgId'].value = sequenceVal;
	
// 	goTabPage("maEqCtgAsmbList");
// 	goTabPage("maEqCtgPartList");
}

function goOpen(pageId)
{
	goTabPage(pageId);
}

function goTabPage(pageId)
{
	var form = document.maEqCatalogDetailForm;

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
	//strutsAction 셋팅.
	if(ckCreate(currentPageId)) maEqCatalogDetailForm.strutsAction.value = '<%=MaEqCatalogDetailAction.EQ_CATALOG_DETAIL_INPUT%>';
	else maEqCatalogDetailForm.strutsAction.value = '<%=MaEqCatalogDetailAction.EQ_CATALOG_DETAIL_UPDATE%>';
	
	var actionUrl = contextPath + "/maEqCatalogDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maEqCatalogDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
 function afterSave(ajaxXmlDoc)
 {
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================
	maEqCatalogDetailForm.elements['maEqCatalogDetailDTO.eqCtgId'].value = maEqCatalogDetailForm.elements['maEqCatalogCommonDTO.eqCtgId'].value;
// 	parent.goSearch();
	if (parent.findGridRow)	parent.findGridRow(maEqCatalogDetailForm.elements['maEqCatalogDetailDTO.eqCtgId'].value);
    getTopPage().afterSaveAll(currentPageId);
 }
 
 function goAudtrailLink()
 {
 	var objectId = maEqCatalogDetailForm.elements['maEqCatalogDetailDTO.eqCtgId'].value;
 	var fileName = currentPageId;

 	if(typeof objectId=="undefined") return;

 	goAudTrailList(objectId, fileName);
 }
	 
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/maEqCatalogDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maEqCatalogCommonDTO.eqCtgId" />
	<html:hidden property="maEqCatalogCommonDTO.eqTypeId" />
	<html:hidden property="maEqCatalogCommonDTO.detailPctgId"/>
	<html:hidden property="maEqCatalogCommonDTO.detailPctgDesc"/>
	<html:hidden property="maEqCatalogDetailDTO.eqCtgId" />
	<html:hidden property="maEqCatalogDetailDTO.eqTypeId" />
	<html:hidden property="maEqCatalogDetailDTO.peqCtgId" />
	<html:hidden property="maEqCatalogDetailDTO.fullDesc" />
 
	<div class="article_box">
		<div class="form_box">
		
		    <div class="field">
				<label><bean:message key="LABEL.pCtg"/></label>
				<div class="input_box">
					<html:text property="maEqCatalogDetailDTO.peqCtgDesc" tabindex="10"/>
					<p class="open_spop">
						<a href="javascript:lovEqCtg('maEqCatalogDetailDTO.peqCtgId', 'maEqCatalogDetailDTO.peqCtgDesc');">
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<div class="field">
				<label><bean:message key="LABEL.eqType"/></label>
				<div class="input_box">
						<html:text property="maEqCatalogDetailDTO.eqTypeDesc" tabindex="20"
								onkeydown="validationKeyDown('maEqCatalogDetailDTO.eqTypeDesc', 'maEqCatalogDetailDTO.eqTypeId');"/>
						<p class="open_spop">
							<a href="javascript:lovSysDir('maEqCatalogDetailDTO.eqTypeId', 'maEqCatalogDetailDTO.eqTypeDesc','EQCTG_TYPE');">
								<span>조회</span>
							</a>
						</p>
					</div>
			</div>
			<div class="field">
				<label class="check"><bean:message key="LABEL.ctgName"/></label>
				<div class="input_box">
					<html:text property="maEqCatalogDetailDTO.eqCtgDesc" tabindex="20" />
				</div>
			</div>
			<div class="field">
				<label><bean:message key="LABEL.ctgCode"/></label>
				<div class="input_read">
					<html:text property="maEqCatalogDetailDTO.eqCtgCode" readonly="true" />
				</div>
			</div>
			
			<div class="field">
				<label><bean:message key="LABEL.isUse"/></label>
				<div class="input_box">
					<html:text property="maEqCatalogDetailDTO.isUse" tabindex="30"/>
					<p class="open_spop">
						<a href="javascript:lovTable('maEqCatalogDetailDTO.isUse', 'maEqCatalogDetailDTO.isUse','YN');">
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			
			
			<div class="field">
                <label><bean:message key="LABEL.mngCd"/></label>
                <div class="input_box">
                    <html:text property="maEqCatalogDetailDTO.mngCd" tabindex="35" />
                </div>
            </div>
            
			
			<div class="field">
				<label><bean:message key="LABEL.ordNo"/></label>
				<div class="input_box">
					<html:text property="maEqCatalogDetailDTO.ordNo" tabindex="40" />
				</div>
			</div>
			<!-- 설비보유갯수 -->
			<div class="field">
				<label><bean:message key="LABEL.EQCNT"/></label>
				<div class="input_read">
					<html:text property="maEqCatalogDetailDTO.eqCnt" readonly="true"/>
				</div>
			</div>
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="maEqCatalogDetailDTO.remark" styleClass="ta50" tabindex="50" />
				</div>
			</div>
			
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>
