<%--===========================================================================
컨텐츠 상세보기
author  nhkim8548
version $Id: persPrivDbSetContDetail.jsp,v 1.1 2018/08/06 11:01:40 nhkim8548 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.pers.priv.db.set.action.PersPrivDbSetContDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html:html>
<head>
<!-- 대시보드 -->
<title><bean:message key='MENU.DASHBOARD'/></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

/** 자동완성 변수 */
//var cntDescAc;

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	
    setTitle("persPrivDbSetContDetailDTO.usrDbCntDesc");
    //For Save
    setForUpdate();
    
    if(ckCreate(currentPageId)) goInput();
    
    // 컨텐츠 명 자동완성 
    cntAc = new autoC({"persPrivDbSetContDetailDTO.usrDbCntDesc":"usrDbCntDesc"});
    cntAc.setTable("TADBCONTENTS");
    cntAc.setAcConditionMap({
         "is_use"  : "Y"
    })
    cntAc.setAcResultMap({
        "persPrivDbSetContDetailDTO.usrDbCntId":"usrDbCntId"
    });
    cntAc.init();
}

/**
 * 입력
 */
function goInput()
{
 sequenceNextVal('SQAUSRDBMENUCNTS_ID');
}
function setSequenceVal(sequenceVal)
{
	persPrivDbSetContDetailForm.elements['persPrivDbSetContDetailDTO.usrDbMenuCntId'].value = sequenceVal;
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
 function goSave() {
     //================================
 	// 필수 항목 체크한다.
 	//================================
 	if(checkValidation()) return;
     
     //strutsAction 셋팅.
     if(ckCreate(currentPageId)) persPrivDbSetContDetailForm.strutsAction.value = "<%=PersPrivDbSetContDetailAction.DETAIL_INPUT%>";
     else persPrivDbSetContDetailForm.strutsAction.value = "<%=PersPrivDbSetContDetailAction.DETAIL_UPDATE%>";

 	var actionUrl = contextPath + "/persPrivDbSetContDetail.do";
 	XMLHttpPost(actionUrl, FormQueryString(persPrivDbSetContDetailForm), 'afterSave');
 }

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc) {
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================
	if (parent.findGridRow) parent.findGridRow(persPrivDbSetContDetailForm.elements['persPrivDbSetContDetailDTO.usrDbMenuCntId'].value);
	
	getTopPage().afterSaveAll(currentPageId);
	
	goClose('persPrivDbSetContDetail');
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = persPrivDbSetContDetailForm.elements['persPrivDbSetContDetailDTO.usrDbMenuCntId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/persPrivDbSetContDetail.do" >
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="persPrivDbSetCommonDTO.usrDbId"/>
<html:hidden property="persPrivDbSetContDetailDTO.usrDbMenuCntId"/>
<html:hidden property="persPrivDbSetContDetailDTO.usrDbCntId"/>
	<div class="article_box">
		<div class="form_box">
			<!-- 컨텐츠 명 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.contentsName"/></label>
				<div class="input_box">
					<html:text property="persPrivDbSetContDetailDTO.usrDbCntDesc" tabindex="30"/>
					<p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
				</div>
			</div>
			<!-- 순서 -->
			<div class="field">
				<label><bean:message key="LABEL.order"/></label>
				<div class="input_box">
					<html:text property="persPrivDbSetContDetailDTO.usrDbMenuOder" tabindex="40"/>
				</div>
			</div>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>
