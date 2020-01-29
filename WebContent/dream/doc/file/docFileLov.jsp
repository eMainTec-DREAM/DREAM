<%--===========================================================================
author  jung7126
version $Id: maDocLibList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="common.tree.action.TreeAction" %>
<%@ page import="dream.doc.file.action.DocFileLovAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="common.bean.User"%>
<%
    User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html>
<head>

<title><bean:message key='MENU.DOCLIB'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
 
<script language="javascript">

var myGrid;
var docCategDescAc, docctgAc;
var deptAc;
var mainMngAc;
function loadPage() 
{
    initGrid();
    
    docctgAc = new autoC({"maDocLibCommonDTO.docctgDesc":"description"});
    docctgAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_use":"Y"
  	   });
    docctgAc.setTable("TADOCCTG");
    docctgAc.setAcResultMap({
        "maDocLibCommonDTO.docctgId":"docctg_id"
    });
    docctgAc.init();
    
    docCategDescAc = new autoC({"maDocLibCommonDTO.docCategDesc":"description"});
    docCategDescAc.setAcDisplay("DESCRIPTION");
    docCategDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo,
 	   "dir_type":"DOC_CATEG"
 	   });
    docCategDescAc.setTable("(select a.description, a.cdusrd_id , b.DIR_TYPE, a.comp_no  from TACDUSRD a, TACDUSRM b where A.CDUSRM_ID = B.CDUSRM_ID) ");
    docCategDescAc.setAcResultMap({
        "maDocLibCommonDTO.docCateg":"cdusrd_id"
    });
    docCategDescAc.init(); 
    
    deptAc = new autoC({"maDocLibCommonDTO.regDeptDesc":"description"});
    deptAc.setAcDisplay("DESCRIPTION");
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "maDocLibCommonDTO.regDeptId":"dept_id"
    });
    deptAc.init();
    
    mainMngAc = new autoC({"maDocLibCommonDTO.regDesc":"emp_name"});
    mainMngAc.setAcDisplay("EMP_NAME");
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcResultMap({
        "maDocLibCommonDTO.regId":"emp_id"
    });
    mainMngAc.init();
}

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	myGrid.setDateFormat("%Y-%m-%d");
	myGrid.enableMultiselect(chkFilter());
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowDblClicked",function(rowId, columnId){
		goSelect();
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();


	setHeader(myGrid, "gridbox", "goSearch", "docFileLov"); // grid, grid id

}

function findGridList(sheetAction)
{
    docFileLovForm.elements['strutsAction'].value = '<%=DocFileLovAction.DOCLIB_LIST_FIND%>';
	var url = contextPath + "/docFileLov.do";
    
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(docFileLovForm),"docId");
}

function goSearch()
{
	docFileLovForm.elements['maDocLibCommonDTO.docId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('Search');   
}


function goSelect(){
	goConfirm();
}

/**
 * 확인
 */
function goConfirm()
{	
	var returnArray = new Array();
	var selectedId=myGrid.getSelectedRowId();
    if(selectedId==null) return;

	returnArray[0] = getValueById(myGrid, selectedId,"docId"); // ID
	alert(returnArray[0]);
/*     returnArray[1] = getValueById(myGrid, selectedId,"woNo"); // wname
    returnArray[2] = getValueById(myGrid, selectedId,"woDesc"); // wname
    returnArray[3] = getValueById(myGrid, selectedId,"woStatusDesc"); // wname
    returnArray[4] = getValueById(myGrid, selectedId,"equipDesc"); // wname
    returnArray[5] = getValueById(myGrid, selectedId,"eqLocDesc"); // wname */

	var dirType = "DOC_LOV";
	
// 	opener.setLovValue(returnArray, dirType);
    getIframeContent().setLovValueDoc(returnArray, dirType);
// 	self.close();
    closeLayerPopup();
}

</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/docFileLov.do">
<html:hidden property="currentPageId"/>
<html:hidden property="keyCode" /><html:hidden property="chName" />
<html:hidden property="resultCol" /><html:hidden property="multiSelect"/>
<html:hidden property="codeType" />
<html:hidden property="param" />
<html:hidden property="label" />
<html:hidden property="title" />
<html:hidden property="strutsAction"/>

<html:hidden property="maDocLibCommonDTO.docId"/><!-- Key -->
<html:hidden property="maDocLibCommonDTO.docCateg"/>
<html:hidden property="maDocLibCommonDTO.regDeptId"/>
<html:hidden property="maDocLibCommonDTO.regId"/>

<html:hidden property="maDocLibCommonDTO.pubdocYn"/>

<html:hidden property="maDocLibCommonDTO.docctgId" />
<html:hidden property="maDocLibCommonDTO.objectId"/>
<html:hidden property="maDocLibCommonDTO.objectType"/>
<html:hidden property="maDocLibCommonDTO.securGrade"/>
<html:hidden property="maDocLibCommonDTO.docCateg" />
<html:hidden property="maDocLibCommonDTO.description" />
    <!-- searchbox 박스 Line -->
    <div class="section_wrap hideFilter">
    	<div class="sheader_box">
	       	<div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx"><bean:message key="LABEL.Filter"/></div>
			</div>
			<div class="function_box filter">
				<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
				</div>
				<div class="fb_group2">
				</div>
			</div>
		</div><!--sheader_box end-->
	       <div class="article_box">
	           <div class="form_box">
	               <div class="field">
		               <label><bean:message key="LABEL.docDesc"/></label>
	               	   <div class="input_box">
	               	   		<html:text property="maDocLibCommonDTO.docDesc" tabindex="10"/>
	               	   </div>
               	   </div>
               	   <!-- 문서분류 -->
	               <div class="field">
					   <label><bean:message key="LABEL.docCateg"/></label>
					   <div class="input_box">
							<html:text property="maDocLibCommonDTO.docctgDesc" tabindex="30"/>
							<p class="open_spop"><a><span>조회</span></a></p>
					   </div>
				   </div>
               	   <!-- 문서타입 -->
	               <div class="field">
					   <label><bean:message key="LABEL.docType"/></label>
					   <div class="input_box">
							<html:text property="maDocLibCommonDTO.docCategDesc" tabindex="30"/>
							<p class="open_spop"><a><span>조회</span></a></p>
					   </div>
				   </div>
				   <div class="field">
					   <label><bean:message key="LABEL.docDeptId"/></label>
					   <div class="input_box">
							<html:text property="maDocLibCommonDTO.regDeptDesc" tabindex="30"/>
							<p class="open_spop"><a><span>조회</span></a></p>
					   </div>
				   </div>
				   <div class="field">
					   <label><bean:message key="LABEL.regId"/></label>
					   <div class="input_box">
							<html:text property="maDocLibCommonDTO.regDesc" tabindex="30"/>
							<p class="open_spop"><a><span>조회</span></a></p>
					   </div>
				   </div>
			</div>
		</div><!--article_box end-->
	</div> <!--  end section_wrap -->
		
	 <div class="section_wrap">
	    <div class="sheader_box hideFilter">
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
			</div>
	    </div><!--sheader_box end-->
	    <div class="article_box" >
            <div class="grid_area">
            	<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
            </div>
	 	</div>
	 </div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>