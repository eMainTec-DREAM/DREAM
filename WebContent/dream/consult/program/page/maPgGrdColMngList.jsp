<%--===========================================================================
목록
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.program.page.action.MaGrdMngColListAction" %>
<%@ page import="dream.consult.program.page.action.MaGrdMngColDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 칼럼 목록 -->
<title><bean:message key='MENU.PGGRIDCOL'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="consultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var fileNameAc;
function loadPage() 
{
	fileNameAc = new autoC({"maGrdMngCommonDTO.filterPageDesc":"description"});
	fileNameAc.setTable("TAPAGE");
	fileNameAc.setAcResultMap({
    	"maGrdMngCommonDTO.filterPageId":"page_id",
    	"maGrdMngCommonDTO.filterFileName":"file_name"
    });
	fileNameAc.init();  
    
	acSysDesc("maGrdMngCommonDTO.filterTypeDesc","maGrdMngCommonDTO.filterType","COLUMN_TYPE");
	acSysDesc("maGrdMngCommonDTO.filterAlignDesc","maGrdMngCommonDTO.filterAlign","ALIGN_TYPE");
	acSysDesc("maGrdMngCommonDTO.filterHidden","maGrdMngCommonDTO.filterHidden","IS_USE");
	acSysDesc("maGrdMngCommonDTO.filterSystemCol","maGrdMngCommonDTO.filterSystemCol","IS_USE");
	
	initGrid();
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen();
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		maGrdMngColListForm.elements['maGrdMngCommonDTO.pgGridColId'].value = "";
    	return sortColumn("maPgGrdColMngList", this, maGrdMngColListForm, "PGGRIDCOLID", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	if(checkValidation()) return;
	
	maGrdMngColListForm.elements['maGrdMngCommonDTO.pgGridColId'].value = "";
    findGridList('Search');   
}

function findGridList(sheetAction)
{
	var form = document.maGrdMngColListForm;	
	form.strutsAction.value = '<%=MaGrdMngColListAction.GRD_COL_LIST_FIND %>';
	
	var url = contextPath + "/maPgGrdColMngList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maGrdMngColListForm), "PGGRIDCOLID", "Y");

}

/**
 * Tab 이동시 호출
 */
function goTabPage(pageId)
{
	tabValidationCheck(myGrid, pageId, "goTabPageAction");
}

function goTabPageAction(pageId, selectedId)
{
	maGrdMngColListForm.elements['maGrdMngCommonDTO.pgGridColId'].value = getValueById(myGrid, selectedId,'pgGridColId');
	maGrdMngColListForm.elements['maGrdMngCommonDTO.fileName'].value = getValueById(myGrid, selectedId,'fileName');
	maGrdMngColListForm.elements['maGrdMngCommonDTO.gridObjId'].value = getValueById(myGrid, selectedId,'gridObjId');
	maGrdMngColListForm.elements['maGrdMngCommonDTO.columnId'].value = getValueById(myGrid, selectedId,'columnId');
	
	goCommonTabPage(maGrdMngColListForm, <%= MaGrdMngColDetailAction.GRD_COL_DETAIL_INIT %>, pageId);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_pgGridColId)
{
	maGrdMngColListForm.elements['maGrdMngCommonDTO.pgGridColId'].value = _pgGridColId;
	findGridList('ReloadRow');
	maGrdMngColListForm.elements['maGrdMngCommonDTO.pgGridColId'].value = "";
}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('maPgGrdColMngDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    maGrdMngColListForm.elements['maGrdMngCommonDTO.pgGridColId'].value = getValueById(myGrid, selectedId,'pgGridColId');
	maGrdMngColListForm.elements['maGrdMngCommonDTO.fileName'].value = getValueById(myGrid, selectedId,'fileName');
	maGrdMngColListForm.elements['maGrdMngCommonDTO.gridObjId'].value = getValueById(myGrid, selectedId,'gridObjId');
	maGrdMngColListForm.elements['maGrdMngCommonDTO.columnId'].value = getValueById(myGrid, selectedId,'columnId');
    maGrdMngColListForm.elements['strutsAction'].value = '<%=MaGrdMngColDetailAction.GRD_COL_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(maGrdMngColListForm), 'maPgGrdColMngDetail'); 
} 

 /**
  * 생성
  */
function goCreate()
{
 	createValidationCheck(myGrid, "maPgGrdColMngDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
 	maGrdMngColListForm.elements['maGrdMngCommonDTO.pgGridColId'].value = "";
 	goCommonTabPage(maGrdMngColListForm, '', pageId);
}
 
  /**
   * 삭제
   */
function goDelete()
{
	var delArray = getDeletRows(myGrid, 'isDelCheck', 'pgGridColId'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maGrdMngColListForm.strutsAction.value = '<%=MaGrdMngColListAction.GRD_COL_LIST_DELETE%>';
	var url = contextPath + "/maPgGrdColMngList.do";
	
	$.post(url,FormQueryString(maGrdMngColListForm)+delArray , function(_data){
    	afterDelete();
    });
}
  
function afterDelete()
{
	goClose('maPgGrdColMngDetail');
	//	goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

function goSetsysy() {
	
	getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.CMSG058'/>", function(result){
		 if(result){
			var selectedArray = getSelectedRows(myGrid, 'isDelCheck', 'pgGridColId'); //Grid, check box column seq, pk column seq
			if(typeof selectedArray == "undefined"){
				alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
				return;
			}
			maGrdMngColListForm.strutsAction.value = '<%=MaGrdMngColListAction.GRD_COL_LIST_SYSY%>';
			var url = contextPath + "/maPgGrdColMngList.do";
			
			$.post(url,FormQueryString(maGrdMngColListForm)+selectedArray , function(_data){
		    	afterSetsys();
		    });
		 }
	});
	
}

function goSetsysn() {
	
	getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.CMSG058'/>", function(result){
		 if(result){
			var selectedArray = getSelectedRows(myGrid, 'isDelCheck', 'pgGridColId'); //Grid, check box column seq, pk column seq
			if(typeof selectedArray == "undefined"){
				alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
				return;
			}
			maGrdMngColListForm.strutsAction.value = '<%=MaGrdMngColListAction.GRD_COL_LIST_SYSN%>';
			var url = contextPath + "/maPgGrdColMngList.do";
			
			$.post(url,FormQueryString(maGrdMngColListForm)+selectedArray , function(_data){
		    	afterSetsys();
		    });
		 }
	});
	
}

function afterSetsys()
{
	goClose('maPgGrdColMngDetail');
	goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG034"/>');
}

function goExcel() {
	excelAction(myGrid);
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maPgGrdColMngList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maGrdMngCommonDTO.pgGridId"/><!-- Key -->
<html:hidden property="maGrdMngCommonDTO.pgGridColId"/><!-- Key -->
<html:hidden property="maGrdMngCommonDTO.fileName"/>
<html:hidden property="maGrdMngCommonDTO.gridObjId"/>
<html:hidden property="maGrdMngCommonDTO.columnId"/>
<html:hidden property="maGrdMngCommonDTO.filterPageId"/>
<html:hidden property="maGrdMngCommonDTO.filterType"/>
<html:hidden property="maGrdMngCommonDTO.filterAlign"/>
<html:hidden property="maGrdMngCommonDTO.filterKeyType"/>
<html:hidden property="maGrdMngCommonDTO.filterKeyNo"/>

    <!-- searchbox 박스 Line -->
    <div class="section_wrap">
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
	              	   <label><bean:message key="LABEL.fileName"/></label>
	             	   <div class="input_box">
	             	   		<html:text property="maGrdMngCommonDTO.filterFileName" tabindex="10"/>
	             	   </div>
	             </div>
	             <div class="field">
		              <label><bean:message key="LABEL.pageDesc"/></label>
		              <div class="input_box">
		              		<html:text property="maGrdMngCommonDTO.filterPageDesc" tabindex="20"/>
		              		<p class="open_spop">
		                       <a>
		                           <span>조회</span>
		                       </a>
		                    </p>
		              </div>
	             </div>
	            <div class="field">
					<label><bean:message key="LABEL.gridObjId"/></label>
					<div class="input_box">
						<html:text property="maGrdMngCommonDTO.filterGridObjId" tabindex="10" />
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.gridDesc"/></label>
					<div class="input_box">
						<html:text property="maGrdMngCommonDTO.filterGridDesc" tabindex="20" />
					</div>
				</div>
			
				<!-- 칼럼 ID -->
	            <div class="field">
              	   <label><bean:message key="LABEL.columnId"/></label>
             	   <div class="input_box">
             	   		<html:text property="maGrdMngCommonDTO.filterColumnId" tabindex="10" />
             	   </div>
	            </div>
				<!-- 칼럼명 -->
	            <div class="field">
		             <label><bean:message key="LABEL.columnDesc"/></label>
	             	 <div class="input_box">
	             	   		<html:text property="maGrdMngCommonDTO.filterColumnDesc" tabindex="20"/>
	            	 </div>
	             </div>
				 <!-- 칼럼종류 -->
	             <div class="field">
	             	<label><bean:message key="LABEL.columnType"/></label>
	             	<div class="input_box">
	            		<html:text property="maGrdMngCommonDTO.filterTypeDesc" tabindex="20" />
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
	             	</div>
	             </div>
			   	 <!-- 정렬 -->
				 <div class="field">
	              	<label><bean:message key="LABEL.align"/></label>
	             	<div class="input_box">
	             	   		<html:text property="maGrdMngCommonDTO.filterAlignDesc" tabindex="50" />
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
	           	   		<html:text property="maGrdMngCommonDTO.filterHidden" tabindex="60"/>
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
		             	<html:text property="maGrdMngCommonDTO.filterSystemCol" tabindex="70"/>
	                    <p class="open_spop">
	                       <a>
	                           <span>조회</span>
	                       </a>
	                    </p>
	             	</div>
				</div>
            	   
	       	</div>
		</div><!--article_box end-->
    </div> <!--  end section_wrap -->
		
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
			</div>
	    </div><!--sheader_box end-->
	    <div class="article_box" id="listBox">
            <div class="grid_area">
            	<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
            </div>
	 	</div>
	 </div> <!--  End of section_wrap -->

</html:form> 
</body>
</html>