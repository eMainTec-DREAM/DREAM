<%--===========================================================================
생산설비
author  youngjoo38
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.std.product.action.AssetStdProductEquipDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html:html>
<head>
<!-- 설비 -->
<title><bean:message key='TAB.INVTEQUIP'/></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

/** 자동완성 변수 */
var equipAc, pEquipAc;

function loadPage() 
{
    setTitle("assetStdProductEquipDetailDTO.equipNo", "assetStdProductEquipDetailDTO.equipDesc");
    
    //For Save
    setForUpdate();

    // 설비
    equipAc = new autoC({"assetStdProductEquipDetailDTO.equipDesc":"description"});
    equipAc.setTable("TAEQUIPMENT");
    equipAc.setKeyName("assetStdProductEquipDetailDTO.equipId");
    equipAc.setAcResultMap({
        "assetStdProductEquipDetailDTO.eqLocDesc":"eqLocDesc",
        "assetStdProductEquipDetailDTO.equipId":"equip_id"
    });
    equipAc.init();
    
    // 이전생산설비
    pEquipAc = new autoC({"assetStdProductEquipDetailDTO.pprdEquipDesc":"description"});
    pEquipAc.setTable("TAEQUIPMENT");
    pEquipAc.setKeyName("assetStdProductEquipDetailDTO.pprdEquipId");
    pEquipAc.setAcResultMap({
        "assetStdProductEquipDetailDTO.pprdEquipId":"equip_id"
    });
    pEquipAc.init();
    
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
}

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQAPRDEQUIP_ID');
    
    equipAc.openLov();
}
function setSequenceVal(sequenceVal)
{
    assetStdProductEquipDetailForm.elements['assetStdProductEquipDetailDTO.assetStdProductEquipId'].value = sequenceVal;
    assetStdProductEquipDetailForm.elements['assetStdProductEquipListDTO.assetStdProductEquipId'].value = sequenceVal;
}
/**
 * 수정
 */
function goUpdate()
{
	assetStdProductEquipDetailForm.elements['assetStdProductEquipListDTO.assetStdProductEquipId'].value = 
		assetStdProductEquipDetailForm.elements['assetStdProductEquipDetailDTO.assetStdProductEquipId'].value;
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
    if(ckCreate(currentPageId)) assetStdProductEquipDetailForm.strutsAction.value = "<%=AssetStdProductEquipDetailAction.DETAIL_INPUT%>";
    else assetStdProductEquipDetailForm.strutsAction.value = "<%=AssetStdProductEquipDetailAction.DETAIL_UPDATE%>";
    
	var actionUrl = contextPath + "/assetStdProductEquipDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(assetStdProductEquipDetailForm), 'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
    //=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(assetStdProductEquipDetailForm.elements['assetStdProductEquipDetailDTO.assetStdProductEquipId'].value);
    getTopPage().afterSaveAll(currentPageId);
    
    setTitle("assetStdProductEquipDetailDTO.equipNo", "assetStdProductEquipDetailDTO.equipDesc");
    
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = assetStdProductEquipDetailForm.elements['assetStdProductEquipDetailDTO.assetStdProductEquipId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

<html:form action="/assetStdProductEquipDetail.do" >
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="assetStdProductCommonDTO.productId"/><!-- Key -->
<html:hidden property="assetStdProductEquipListDTO.assetStdProductEquipId"/><!-- Key -->
<html:hidden property="assetStdProductEquipDetailDTO.assetStdProductEquipId"/><!-- Key -->
<html:hidden property="assetStdProductEquipDetailDTO.productId"/>
<html:hidden property="assetStdProductEquipDetailDTO.equipId"/>
<html:hidden property="assetStdProductEquipDetailDTO.equipNo"/>
<html:hidden property="assetStdProductEquipDetailDTO.eqLocId"/>
<html:hidden property="assetStdProductEquipDetailDTO.pprdEquipId"/>
	 
	<div class="article_box">
		<div class="form_box">
			<!-- 진행순서 -->
			<div class="field">
				<label><bean:message key="LABEL.prodSeq"/></label>
				<div class="input_box">
					<html:text property="assetStdProductEquipDetailDTO.prodSeq" tabindex="10" styleClass="num"/>
				</div>
			</div>
			<!-- 설비 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.equipDesc"/></label>
				<div class="input_box">
					<html:text property="assetStdProductEquipDetailDTO.equipDesc" tabindex="20"/>
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 설비위치 -->
			<div class="field_long">
				<label><bean:message key="LABEL.eqLocDesc"/></label>
				<div class="input_read">
					<html:text property="assetStdProductEquipDetailDTO.eqLocDesc" tabindex="30" readonly="true"/>
				</div>
			</div>
			<!-- 이전생산설비명 -->
			<div class="field">
				<label><bean:message key="LABEL.pPrdEquipDesc"/></label>
				<div class="input_box">
					<html:text property="assetStdProductEquipDetailDTO.pprdEquipDesc" tabindex="40"/>
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- remark -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="assetStdProductEquipDetailDTO.remark" styleClass="ta50" tabindex="50" />
				</div>
			</div>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>
