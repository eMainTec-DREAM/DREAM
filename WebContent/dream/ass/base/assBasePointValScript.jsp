<%--===========================================================================
메뉴 - 목록
author  youngjoo38
version $Id: assBasePointValList.jsp,v 1.1 2017/11/06 16:00:27 youngjoo38 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.ass.base.action.AssBasePointValScriptAction" %>
<%@ page import="dream.ass.base.action.AssBasePointValDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 메뉴 -->
<title><bean:message key='LABEL.script'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="consultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

//그리드명
var myGrid,creByAc;

function loadPage() 
{
	
	if(ckCreate(currentPageId)) goInput();
    else goUpdate();
	
	
    //initGrid();
	if(typeof resizeTabFrame == "function") setTimeout("resizeTabFrame()",500);
}

/**
 * 그리드 초기화
 */
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen();
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

function goSave()
{
	if(checkValidation()) return;
	
	//strutsAction 셋팅.
    assBasePointValScriptForm.strutsAction.value = '<%=AssBasePointValScriptAction.DATA_SCRIPT_UPDATE%>';
    
    var actionUrl = contextPath + "/assBasePointValScript.do";
    XMLHttpPost(actionUrl, FormQueryString(assBasePointValScriptForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    getTopPage().afterSaveAll(currentPageId);
    
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/assBasePointValScript.do">
<html:hidden property="strutsAction"/>
<html:hidden property="assBasePointValDetailDTO.assBasePointValId"/><!-- Key -->
<html:hidden property="assBasePointValDetailDTO.assBasePointId"/>
<html:hidden property="assBasePointValDetailDTO.assBaseListId"/>
    <!-- searchbox 박스 Line -->

		
	 <div class="section_wrap">
	    <div class="sheader_box">
	        <div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx"><bean:message key="LABEL.List"/></div>
			</div>
			<div class="function_box list">
				<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
				</div>	
				<div class="fb_group2">
				</div>
				<div class="b_line"></div> 
				<div class="fb_group1">
					 
				</div>
			</div>
	    </div><!--sheader_box end-->
	    <div class="article_box" style="margin-top: 0px;">
            <div class="form_box" style="margin-top: 0px;">

             	 <div class="field_long">
					<label><bean:message key="LABEL.script"/></label>
					<div class="input_box">
						<html:textarea property="assBasePointValDetailDTO.script" styleClass="ta50" tabindex="20" />
					</div>
				</div>
				 
             </div> <!-- End of Form_box -->
	 	</div>
	 </div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>