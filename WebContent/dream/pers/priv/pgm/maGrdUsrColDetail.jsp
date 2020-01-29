<%--===========================================================================
칼럼 상세 
author  jung7126
version $Id: maGrdUsrColDetail.jsp,v 1.0 2015/12/04 07:26:18 jung7126 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.pers.priv.pgm.action.MaGrdUsrColDetailAction"%>
<html>
<head>
<!--칼럼 -->
<title><bean:message key="LABEL.columnId"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//자동완성 AC
function loadPage() 
{
	setTitle("maGrdUsrColDetailDTO.columnId","maGrdUsrColDetailDTO.columnDesc");
	setForUpdate();
	//setReadOnly();
	
	//자동완성 
	//숨김여부
    acSysDesc("maGrdUsrColDetailDTO.displayYn","maGrdUsrColDetailDTO.displayYn","IS_USE",true);
	//정렬
    acSysDesc("maGrdUsrColDetailDTO.alignDesc","maGrdUsrColDetailDTO.align","ALIGN_TYPE",true);
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAUSRPGGRIDCOL_ID');
	//maGrdUsrColDetailForm.elements['maGrdUsrColDetailDTO.hidden'].value = 'N';
}

function setSequenceVal(sequenceVal)
{
	maGrdUsrColDetailForm.elements['maGrdUsrColDetailDTO.usrPgGridColId'].value = sequenceVal;
	maGrdUsrColDetailForm.elements['maGrdUsrCommonDTO.usrPgGridColId'].value = sequenceVal;
	
	goSaveAction();
}

function goSaveAction()
{
	maGrdUsrColDetailForm.strutsAction.value = '<%=MaGrdUsrColDetailAction.GRD_USR_COL_DETAIL_INPUT%>';
	var actionUrl = contextPath + "/maGrdUsrColDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maGrdUsrColDetailForm), 'afterSave');
}

function goSave()
{
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	
	if(maGrdUsrColDetailForm.elements['maGrdUsrColDetailDTO.usrPgGridColId'].value == "")
	{ 
		goInput();
	}
	else
	{
		maGrdUsrColDetailForm.strutsAction.value = '<%= MaGrdUsrColDetailAction.GRD_USR_COL_DETAIL_UPDATE %>';

		var actionUrl = contextPath + "/maGrdUsrColDetail.do";
		XMLHttpPost(actionUrl, FormQueryString(maGrdUsrColDetailForm), 'afterSave');
		
	}
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    parent.goSearch();
    
    getTopPage().afterSaveAll(currentPageId);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/maGrdUsrColDetail.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maGrdUsrCommonDTO.pageId"/><!--  -->
<html:hidden property="maGrdUsrCommonDTO.pgGridId"/><!--  -->
<html:hidden property="maGrdUsrCommonDTO.gridObjId"/>
<html:hidden property="maGrdUsrCommonDTO.usrPgGridColId"/>
<html:hidden property="maGrdUsrCommonDTO.pgGridColId"/>

<html:hidden property="maGrdUsrDetailDTO.usrPgGridId"/>
<html:hidden property="maGrdUsrColDetailDTO.pgGridColId"/>
<html:hidden property="maGrdUsrColDetailDTO.usrPgGridColId"/>

<html:hidden property="maGrdUsrDetailDTO.pgGridId" />
<html:hidden property="maGrdUsrDetailDTO.gridObjId" />

<html:hidden property="maGrdUsrColDetailDTO.type"/>
<html:hidden property="maGrdUsrColDetailDTO.align"/>
<html:hidden property="maGrdUsrColDetailDTO.keyType"/>
<html:hidden property="maGrdUsrColDetailDTO.keyNo"/>
    <!-- searchbox 박스 Line -->
	       <div class="article_box">
	           <div class="form_box">
				<!-- 칼럼 ID -->
	               <div class="field">
		               <label class="check"><bean:message key="LABEL.columnId"/></label>
	               	   <div class="input_read">
	               	   		<html:text property="maGrdUsrColDetailDTO.columnId" tabindex="10" readonly="true"/>
	               	   </div>
               	   </div>
				<!-- 칼럼명 -->
               	   <div class="field">
		               <label><bean:message key="LABEL.columnDesc"/></label>
	               	   <div class="input_read">
	               	   		<html:text property="maGrdUsrColDetailDTO.columnDesc" tabindex="20" readonly="true"/>
	               	   </div>
               	   </div>
				<!-- 칼럼넓이 -->
				<div class="field">
					<label><bean:message key="LABEL.columnWidth"/></label>
					<div class="input_box">
							<html:text property="maGrdUsrColDetailDTO.width" tabindex="30" styleClass="num"/>
					</div>
				</div>
				<!-- 칼럼순서 -->
				<div class="field">
	               <label><bean:message key="LABEL.ordNo"/></label>
               	   <div class="input_box">
               	   		<html:text property="maGrdUsrColDetailDTO.ordNo" tabindex="40" styleClass="num"/>
               	   </div>
				</div>
				<!-- 정렬 -->
				<div class="field">
	               <label><bean:message key="LABEL.align"/></label>
               	   <div class="input_box">
               	   		<html:text property="maGrdUsrColDetailDTO.alignDesc" tabindex="50"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
               	   </div>
				</div>
				<!-- 숨김여부 -->
				<div class="field">
	               <label><bean:message key="LABEL.displayYn"/></label>
               	   <div class="input_box">
               	   		<html:text property="maGrdUsrColDetailDTO.displayYn" tabindex="60"/>
	                    <p class="open_spop">
	                        <a>
	                            <span>조회</span>
	                        </a>
	                    </p>
               	   </div>
				</div>
			</div>
		</div><!--article_box end-->
</html:form> 
</body>
</html>