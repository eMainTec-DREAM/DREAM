<%--===========================================================================
칼럼 상세 
author  kim21017
version $Id: maPgMngGrdColDetail.jsp,v 1.0 2015/12/04 07:26:18 jung7126 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.consult.program.page.action.MaGrdMngColDetailAction"%>
<html>
<head>
<!--칼럼 -->
<title><bean:message key="LABEL.columnId"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var labelAc;

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	
	setTitle("maGrdMngColDetailDTO.columnId","maGrdMngColDetailDTO.columnDesc");
	setForUpdate();
	
	acSysDesc("maGrdMngColDetailDTO.typeDesc","maGrdMngColDetailDTO.type","COLUMN_TYPE","Y");
	acSysDesc("maGrdMngColDetailDTO.alignDesc","maGrdMngColDetailDTO.align","ALIGN_TYPE","Y");
	acSysDesc("maGrdMngColDetailDTO.hidden","maGrdMngColDetailDTO.hidden","IS_USE","Y");
	acSysDesc("maGrdMngColDetailDTO.systemCol","maGrdMngColDetailDTO.systemCol","IS_USE","Y");
	acSysDesc("maGrdMngColDetailDTO.displayYn","maGrdMngColDetailDTO.displayYn","IS_USE","Y");
	
	//maGrdMngColDetailDTO.columnDesc
	//maGrdMngColDetailDTO.KeyName
	
	labelAc = new autoC({"maGrdMngColDetailDTO.columnDesc":"key_name"});
	labelAc.setTable("TALANG");
	labelAc.setAcResultMap({
    	"maGrdMngColDetailDTO.keyType":"key_type",
    	"maGrdMngColDetailDTO.KeyName":"key_name",
    	"maGrdMngColDetailDTO.keyNo":"key_no"
    });
	labelAc.setAcConditionMap({
    	"key_type":"LABEL"
    });
	labelAc.setKeyName("maGrdMngColDetailDTO.columnDesc");
	labelAc.init();  
	
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAPGGRIDCOL_ID');
	//maGrdMngColDetailForm.elements['maGrdMngColDetailDTO.hidden'].value = 'N';
}

function setSequenceVal(sequenceVal)
{
	maGrdMngColDetailForm.elements['maGrdMngColDetailDTO.pgGridColId'].value = sequenceVal;
	maGrdMngColDetailForm.elements['maGrdMngCommonDTO.pgGridColId'].value = sequenceVal;
}

function goSave()
{
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	
	if(ckCreate(currentPageId)) maGrdMngColDetailForm.strutsAction.value = '<%=MaGrdMngColDetailAction.GRD_COL_DETAIL_INPUT%>';
	else maGrdMngColDetailForm.strutsAction.value = '<%= MaGrdMngColDetailAction.GRD_COL_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/maGrdMngColDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maGrdMngColDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(maGrdMngColDetailForm.elements['maGrdMngColDetailDTO.pgGridColId'].value);
	
    getTopPage().afterSaveAll(currentPageId);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/maPgMngGrdColDetail.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maGrdMngCommonDTO.pgGridId"/>
<html:hidden property="maGrdMngCommonDTO.pgGridColId"/>
<html:hidden property="maGrdMngColDetailDTO.pgGridColId"/>
<html:hidden property="maGrdMngColDetailDTO.type"/>
<html:hidden property="maGrdMngColDetailDTO.align"/>
<html:hidden property="maGrdMngColDetailDTO.keyType"/>
<html:hidden property="maGrdMngColDetailDTO.keyNo"/>
    <!-- searchbox 박스 Line -->
	       <div class="article_box">
	           <div class="form_box">
				<!-- 칼럼 ID -->
	               <div class="field">
		               <label class="check"><bean:message key="LABEL.columnId"/></label>
	               	   <div class="input_box">
	               	   		<html:text property="maGrdMngColDetailDTO.columnId" tabindex="10" />
	               	   </div>
               	   </div>
				<!-- 칼럼명 -->
               	   <div class="field">
		               <label><bean:message key="LABEL.columnDesc"/></label>
	               	   <div class="input_box">
	               	   		<html:text property="maGrdMngColDetailDTO.columnDesc" tabindex="20"/>
							<p class="open_spop">
								<a>
									<span>조회</span>
								</a>
							</p>
	               	   </div>
               	   </div>
				<!-- 칼럼종류 -->
               	   <div class="field">
		               <label><bean:message key="LABEL.columnType"/></label>
	               	   <div class="input_box">
               	   		<html:text property="maGrdMngColDetailDTO.typeDesc" tabindex="20" />
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
	               	   </div>
               	   </div>
				<!-- 칼럼넓이 -->
				<div class="field">
					<label><bean:message key="LABEL.columnWidth"/></label>
					<div class="input_box">
							<html:text property="maGrdMngColDetailDTO.width" tabindex="30" styleClass="num"/>
					</div>
				</div>
				<!-- 칼럼순서 -->
				<div class="field">
	               <label><bean:message key="LABEL.ordNo"/></label>
               	   <div class="input_box">
               	   		<html:text property="maGrdMngColDetailDTO.ordNo" tabindex="40"/>
               	   </div>
				</div>
				<!-- 정렬 -->
				<div class="field">
	               <label class="check"><bean:message key="LABEL.align"/></label>
               	   <div class="input_box">
               	   		<html:text property="maGrdMngColDetailDTO.alignDesc" tabindex="50" />
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
               	   </div>
				</div>
				<!-- 숨김여부 -->
				<div class="field">
	               <label><bean:message key="LABEL.hidden"/></label>
               	   <div class="input_box">
               	   		<html:text property="maGrdMngColDetailDTO.hidden" tabindex="60"/>
	                    <p class="open_spop">
	                        <a>
	                            <span>조회</span>
	                        </a>
	                    </p>
               	   </div>
				</div>
				<!-- 화면Display여부 -->
				<div class="field">
	               <label><bean:message key="LABEL.displayYn"/></label>
               	   <div class="input_box">
               	   		<html:text property="maGrdMngColDetailDTO.displayYn" tabindex="60"/>
	                    <p class="open_spop">
	                        <a>
	                            <span>조회</span>
	                        </a>
	                    </p>
               	   </div>
				</div>
				<!-- 시스템칼럼 -->
				<div class="field">
	               <label><bean:message key="LABEL.systemCol"/></label>
               	   <div class="input_box">
               	   		<html:text property="maGrdMngColDetailDTO.systemCol" tabindex="70"/>
	                    <p class="open_spop">
	                        <a>
	                            <span>조회</span>
	                        </a>
	                    </p>
               	   </div>
				</div>
				
				<div class="field_long">
                    <label><bean:message key="LABEL.remark"/></label>
                    <div class="input_box">
                        <html:textarea property="maGrdMngColDetailDTO.description" styleClass="ta50" tabindex="80" />
                    </div>
                 </div>
                 
                 
			</div>
		</div><!--article_box end-->
</html:form> 
</body>
</html>