<%--===========================================================================
칼럼 상세 
author  ghlee
version $Id:$
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
<title><bean:message key="TAB.pgGridCol"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var fileNameAc;
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
	
	$('.stitle_tx').append(maGrdMngColDetailForm.elements['maGrdMngCommonDTO.fileName'].value+" - "+
			maGrdMngColDetailForm.elements['maGrdMngCommonDTO.gridObjId'].value+" - "+
			maGrdMngColDetailForm.elements['maGrdMngCommonDTO.columnId'].value);
	setForUpdate();
	
	acSysDesc("maGrdMngColDetailDTO.typeDesc","maGrdMngColDetailDTO.type","COLUMN_TYPE","Y");
	acSysDesc("maGrdMngColDetailDTO.alignDesc","maGrdMngColDetailDTO.align","ALIGN_TYPE","Y");
	acSysDesc("maGrdMngColDetailDTO.hidden","maGrdMngColDetailDTO.hidden","IS_USE","Y");
	acSysDesc("maGrdMngColDetailDTO.systemCol","maGrdMngColDetailDTO.systemCol","IS_USE","Y");
	acSysDesc("maGrdMngColDetailDTO.displayYn","maGrdMngColDetailDTO.displayYn","IS_USE","Y");
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAPGGRIDCOL_ID');
	//maGrdMngColDetailForm.elements['maGrdMngColDetailDTO.hidden'].value = 'N';
	
	maGrdMngColDetailForm.elements['maGrdMngColDetailDTO.gridObjId'].value = "gridbox";
	
	fileNameAc = new autoC({"maGrdMngColDetailDTO.pageDesc":"description"});
	fileNameAc.setTable("TAPAGE");
	fileNameAc.setAcResultMap({
    	"maGrdMngColDetailDTO.pageId":"page_id"
    });
	fileNameAc.setKeyName("maGrdMngColDetailDTO.pageId");
	fileNameAc.init();  
}

function goUpdate() {
	setDisable(document.getElementById("pageDescDiv"));
    setDisable(document.getElementById("gridObjIdDiv"));
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
	
	var actionUrl = contextPath + "/maPgGrdColMngDetail.do";
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
<html:form action="/maPgGrdColMngDetail.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maGrdMngCommonDTO.pgGridId"/>
<html:hidden property="maGrdMngCommonDTO.pgGridColId"/>
<html:hidden property="maGrdMngCommonDTO.fileName"/>
<html:hidden property="maGrdMngCommonDTO.gridObjId"/>
<html:hidden property="maGrdMngCommonDTO.columnId"/>

<html:hidden property="maGrdMngColDetailDTO.pageId"/>
<html:hidden property="maGrdMngColDetailDTO.pgGridId"/>
<html:hidden property="maGrdMngColDetailDTO.pgGridColId"/>
<html:hidden property="maGrdMngColDetailDTO.type"/>
<html:hidden property="maGrdMngColDetailDTO.align"/>
<html:hidden property="maGrdMngColDetailDTO.keyType"/>
<html:hidden property="maGrdMngColDetailDTO.keyNo"/>
    <!-- searchbox 박스 Line -->
       <div class="article_box">
           <div class="form_box">
           		<div class="field" id="pageDescDiv">
		              <label class="check"><bean:message key="LABEL.pageDesc"/></label>
		              <div class="input_box">
		              		<html:text property="maGrdMngColDetailDTO.pageDesc" tabindex="20"/>
		              		<p class="open_spop">
		                       <a>
		                           <span>조회</span>
		                       </a>
		                    </p>
		              </div>
	             </div>
	             <div class="field" id="gridObjIdDiv">
					<label><bean:message key="LABEL.gridObjId"/></label>
					<div class="input_box">
						<html:text property="maGrdMngColDetailDTO.gridObjId" tabindex="10" />
					</div>
				</div>
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
               	   		<html:text property="maGrdMngColDetailDTO.columnDesc" tabindex="20"
							onblur="valLang('maGrdMngColDetailDTO.keyType', 'maGrdMngColDetailDTO.keyNo','maGrdMngColDetailDTO.columnDesc', true,'LABEL');"/>
						<p class="open_spop">
							<a href="javascript:lovLang('maGrdMngColDetailDTO.keyType','maGrdMngColDetailDTO.keyNo','maGrdMngColDetailDTO.columnDesc','LABEL');">
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
				<!-- 숨김여부 -->
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