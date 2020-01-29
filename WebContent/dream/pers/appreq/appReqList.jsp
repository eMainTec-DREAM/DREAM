<%--===========================================================================
요청문서 - 목록
author  javaworker
version $Id: appReqList.jsp,v 1.6 2014/07/02 04:14:11 pochul2423 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="common.tree.action.TreeAction" %>
<%@ page import="dream.pers.appreq.action.AppReqListAction" %>
<%@ page import="dream.pers.appreq.action.AppReqDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 요청문서 -->
<title><bean:message key='MENU.APPREQDOC'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//

function loadPage() 
{	
    initGrid();
    goSearch();
}

var dsOption= {

    fields :[
        {name : 'row_edit'      },
        {name : 'appDate'  , type:'dateTime' 	},
        {name : 'appDocNo'      },
        {name : 'title'        	},
        {name : 'appStatus'     },
		{name : 'wfType'		},
		{name : 'objectNo'  	},
		{name : 'appFlowNo'  	}
    ],
    recordType : 'array'
};

var colsOption = [
    {id: 'row_edit',    hidden: true   },
    {id: 'appDate',  	header: '<bean:message key="appReqList.enterDate"/>' ,  width :125,    	align:'center'	 }, // 기안일자
    {id: 'appDocNo',    header: '<bean:message key="appReqList.appDocNo"/>' ,  	width :80,    	align:'center' }, // 결재번호
	{id: 'title',       header: '<bean:message key="appReqList.title"/>' , 	  	width :300}, // 제목
	{id: 'appStatus',   header: '<bean:message key="appReqList.appStatus"/>' , 	width :90, 		align:'center'}, // 결재상태
	{id: 'wfType',   	header: '<bean:message key="appReqList.wfType"/>' ,		width :110,    	align :'center'}, // 결재종류
	{id: 'objectNo',   	header: '<bean:message key="appReqList.objectNo"/>' ,  	width :230 	},  // 문서번호
	{id: 'appFlowNo',   hidden: true   }
];

var gridOption={
    id : 'appReqListGrid',
    width: "961",
    height: "310",
    container : 'gridbox', 
    replaceContainer : true,
    dataset : dsOption ,
    columns : colsOption,
    toolbarPosition : 'bottom',
   
    onLoadFinish:function()
    {
        setGridEditable(appReqListGrid, false);

        // 목록에서 조회되어 보여줄때
        if (appReqListForm.elements['strutsAction'].value == '<%=AppReqListAction.BASE_QUICK_LIST%>')
        {
        	findGridList('Search');
        }
        else
        {
        	appReqListForm.elements['appReqCommonDTO.enterDateFrom'].value = getMinusDay(30);
        	appReqListForm.elements['appReqCommonDTO.enterDateTo'].value = getToday();        	
        }
    },
    
    onDblClickCell:function(value, record , cell, row, colNO, rowNO,columnObj,grid)
    {
        goTabPage('appReqDetail');
    },
    
    toolbarContent : setToolBarContent("readOnly"),
    autoLoad : true
};

var appReqListGrid;

function initGrid()
{
    appReqListGrid=new Sigma.Grid( gridOption );
    appReqListGrid.render();
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(_action)
{
    var url = contextPath + "/appReqList.do";
    appReqListForm.elements['strutsAction'].value = '<%=AppReqListAction.APP_REQ_FIND%>';
    doGridAction(_action, appReqListGrid, url, FormQueryString(appReqListForm));
}

/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	appReqListForm.elements['appReqCommonDTO.appFlowNo'].value="";
	findGridList('Search', '<%=AppReqListAction.APP_REQ_FIND%>');
}

/**
 * Tab 이동시 호출
 */
function goTabPage(pageId)
{
    var totalCount = appReqListGrid.dataset.getSize();
    
    // 상세로 넘어가는 경우를 제외하고, 나머지 탭은 목록에서 조회를 하지 않았다면 이동시키지 않는다.
    if (pageId != "appReqDetail")
    {
        if (totalCount == 0)
        {
            alertMessage1('<bean:message key="MESSAGE.MSG005" />'); //조회, 컬럼 선택후 이동 바랍니다.
            return;
        }
    }
    //결과 값이 있는경우 점검번호를 가지고 상세페이지로 가져간다.
    if(totalCount > 0)
    {
    	appFlowNo = appReqListGrid.activeRecord[appReqListGrid.getColumn('appFlowNo').fieldIndex];
        appReqListForm.elements['appReqCommonDTO.appFlowNo'].value = appFlowNo;
    }

    goCommonTabPage(appReqListForm, '<%=AppReqDetailAction.BASE_DEFAULT_INIT%>', pageId);
}

/**
 * server 에서 조회 excel download시 호출
 */
function exportExcel(_grid)
{
    doExportExcel(_grid, appReqListForm);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_appFlowNo)
{
	findGridList('ReloadRow');
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/appReqList">
<html:hidden property="strutsAction"/>
<html:hidden property="appReqCommonDTO.appFlowNo"/><!-- Key -->
	<!-- searchbox 박스 Line -->
	<div>
		<table cellspacing="0" cellpadding="0" border="0" style="width: 100%;" class="border_search" >
			<tbody>
		      <tr>
		          <!-- 결재번호 -->
		          <th class="td_title" width="120"><bean:message key="appReqList.appDocNo"/></th>
		          <td class="td_input" width="200">
		              <html:text property="appReqCommonDTO.filterAppDocNo" size='12' styleClass="no_bg" tabindex="10"/>
		          </td>
		    	  <!-- 제목 -->
		          <th class="td_title" width="90"><bean:message key="appReqList.title"/></th>
		          <td class="td_input" width="240">
		          	  <html:text property="appReqCommonDTO.title" styleClass="no_bg" size='37' maxlength="100" tabindex="20" /> 
		          </td>
		          <!-- 결재상태 -->
		          <th scope="col" class="td_title" width="90"><bean:message key="appReqList.appStatus"/></th>
		          <td class="td_input"> 
		          	<html:select property="appReqCommonDTO.appStatus" styleClass="select" style="width:50" tabindex="30" >
		             	<option value="">--------------------------------</option>
		                 <html:optionsCollection property="appStatusOptions"/>
		             </html:select>	                           
		          </td>
		      </tr>
		      <tr>
		          <!-- 기안일자 --> 
		          <th class="td_title"><bean:message key="appReqList.enterDate"/></th>
		          <td class="td_input">
					<html:text property="appReqCommonDTO.enterDateFrom" styleClass="no_line" size='7' maxlength="10" tabindex="40"
						onkeydown="javascript:onlyNumberInput(event);" onkeyup="javascript:dateFormat(this, event);"
					/><input name='popupImg' type="button" onfocus="this.blur();" class="btn_lov1" onmouseover="this.style.cursor='pointer'"
		                     onclick="openCalendar('appReqCommonDTO.enterDateFrom', event);"/>
		            ~
					<html:text property="appReqCommonDTO.enterDateTo" styleClass="no_line" size='7' maxlength="10" tabindex="50"
						onkeydown="javascript:onlyNumberInput(event);" onkeyup="javascript:dateFormat(this, event);"
					/><input name='popupImg' type="button" onfocus="this.blur();" class="btn_lov1" onmouseover="this.style.cursor='pointer'"
		                     onclick="openCalendar('appReqCommonDTO.enterDateTo', event);"/>
		          </td>
		  	 	  <!-- 문서번호 -->
		          <th class="td_title"><bean:message key="appReqList.objectNo"/></th>
		          <td class="td_input">
		          	  <html:text property="appReqCommonDTO.objectNo" styleClass="no_bg" size='11' maxlength="20" tabindex="60" /> 
		          </td>
		          <!-- 결재종류 -->
		          <th scope="col" class="td_title"><bean:message key="appReqList.wfType"/></th>
		          <td class="td_input"> 
		          	<html:select property="appReqCommonDTO.wfType" styleClass="select" style="width:50" tabindex="70" >
		             	<option value="">--------------------------------</option>
		                 <html:optionsCollection property="wfTypeOptions"/>
		             </html:select>	                           
		          </td>
		      </tr>
		      <tr>
		          <th class="td_title"></th>
		          <td class="td_input">
					<!-- 결재완료여부 -->
			  		<input type='radio' name="appReqCommonDTO.isComp" value="" class="input_noborder" onfocus='this.blur();' /><bean:message key="appReqList.all"/><!-- 전체 -->
			  		<input type='radio' name="appReqCommonDTO.isComp" value="Y" class="input_noborder" onfocus='this.blur();' /><bean:message key="appReqList.comp"/><!-- 결재완료 -->
			  		<input type='radio' name="appReqCommonDTO.isComp" value="N" class="input_noborder" onfocus='this.blur();' checked="checked" /><bean:message key="appReqList.notComp"/><!-- 진행중 --> 
		          </td>
		          <th class="td_title" colspan="4">
		              <mware:search />
		          </th>
		      </tr>
		    </tbody>
		</table>
    </div>
	<div class="border_body">
	    <table width="100%" border="0" cellpadding="0" cellspacing="0">
	    	<tr><td class="grid_top"></td></tr>
            <tr>
                <td valign="top">
					<mware:grid gridId="gridbox" />
                </td>
            </tr>
        </table>
        <div class="clear"></div>
    </div>
</html:form> 
<c:import charEncoding="UTF-8"  url="/common/jsp/buttonInclude.jsp"></c:import>
</body>
</html>