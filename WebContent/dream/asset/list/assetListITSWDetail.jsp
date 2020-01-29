<%--===========================================================================
설치 SW
author  youngjoo38
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.list.action.AssetListITSWDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html:html>
<head>
<!-- 설치 SW -->
<title><bean:message key='TAB.sw'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //
var sqCategAc;
function loadPage() 
{
	assetListITSWDetailForm.elements['assetListITSWDetailDTO.itEqId'].value = assetListITSWDetailForm.elements['assetListITSWListDTO.itEqId'].value;
	
    setTitle("assetListITSWDetailDTO.swName");
    
    sqCategAc = new autoC({"assetListITSWDetailDTO.swCategory":"description"});
    sqCategAc.setAcConditionMap({
    	"is_use":"Y"
    	,"dir_type":"SW_CATEGORY"
    	,"comp_no":loginUser.compNo
  	   });
    sqCategAc.setTable("TACDUSRD");
    sqCategAc.setKeyName("assetListITSWDetailDTO.swCategoryId");
    sqCategAc.setAcResultMap({
        "assetListITSWDetailDTO.swCategoryId":"cdusrd_no"
    });
    sqCategAc.init();

    //For Save
    setForUpdate();
    
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();

}

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQAEQITINSTSW_ID');
    
    sqCategAc.openLov();
}
function setSequenceVal(sequenceVal)
{
    assetListITSWDetailForm.elements['assetListITSWDetailDTO.eqItInstSwId'].value = sequenceVal;
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
    if(ckCreate(currentPageId)) assetListITSWDetailForm.strutsAction.value = "<%=AssetListITSWDetailAction.DETAIL_INPUT%>";
    else assetListITSWDetailForm.strutsAction.value = "<%=AssetListITSWDetailAction.DETAIL_UPDATE%>";
    
	var actionUrl = contextPath + "/assetListITSWDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(assetListITSWDetailForm), 'afterSave');
    
}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
    //=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(assetListITSWDetailForm.elements['assetListITSWDetailDTO.eqItInstSwId'].value);
    getTopPage().afterSaveAll(currentPageId);
    setTitle("assetListITSWDetailDTO.swName");
    
}

function goAudtrailLink()
{
	var objectId = assetListITSWDetailForm.elements['assetListITSWDetailDTO.eqItInstSwId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}


//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

<html:form action="/assetListITSWDetail.do" >
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="assetListITSWListDTO.itEqId"/><!-- Key -->
<html:hidden property="assetListITSWDetailDTO.eqItInstSwId"/><!-- Key -->
<html:hidden property="assetListITSWDetailDTO.itEqId"/>
<html:hidden property="assetListITSWDetailDTO.swCategoryId"/>
	<div class="article_box">
		<div class="form_box">
			<!-- S/W 종류 -->
			<div class="field">
				<label><bean:message key="LABEL.swType"/></label>
				<div class="input_box">
					<html:text property="assetListITSWDetailDTO.swCategory" tabindex="10"/>
					<p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
				</div>
			</div>
			<!-- S/W 명 -->
			<div class="field">
				<label><bean:message key="LABEL.swDesc"/></label>
				<div class="input_box">
					<html:text property="assetListITSWDetailDTO.swName" tabindex="20"/>
				</div>
			</div>
			<!-- S/W Version -->
			<div class="field">
				<label><bean:message key="LABEL.swVersion"/></label>
				<div class="input_box">
					<html:text property="assetListITSWDetailDTO.swVer" tabindex="30"/>
				</div>
			</div>
			<!-- 설치일자 -->
			<div class="field">
				<label><bean:message key="LABEL.setupDate"/></label>
				<div class="input_box">
					<html:text property="assetListITSWDetailDTO.installDate" tabindex="40"/>
					<p class="open_calendar">
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
					<html:textarea property="assetListITSWDetailDTO.remark" styleClass="ta50" tabindex="50" />
				</div>
			</div>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>
