<%--===========================================================================
통보문서 - 목록
author  javaworker
version $Id: appNotList.jsp,v 1.7 2014/07/02 04:14:14 pochul2423 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="common.tree.action.TreeAction" %>
<%@ page import="dream.app.not.action.AppNotListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 통보문서 -->
<title><bean:message key='MENU.APPNOT'/></title>
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
        {name : 'isCheck'  		},
        {name : 'appDocNo'      },
        {name : 'title'        	},
        {name : 'appStatus'     },
		{name : 'wfType'		},
		{name : 'objectNo'  	},
        {name : 'appDate'  , type:'dateTime' 	},
		{name : 'enterDate', type:'date'    	},
		{name : 'appFlowNo'  	}
    ],
    recordType : 'array'
};

var colsOption = [
    {id: 'row_edit',    hidden: true   },
    {id: 'isCheck' ,    header: '<bean:message key="appNotList.checked"/>' ,    width :40,  noMark:true, resizable : false,  isCheckColumn : true, align :'center', headercheck : false },  // 확인
    {id: 'appDocNo',    header: '<bean:message key="appNotList.appDocNo"/>' ,   width :90,    align:'center' }, // 결재번호
	{id: 'title',       header: '<bean:message key="appNotList.title"/>' , 	  	width :290}, // 제목
	{id: 'appStatus',   header: '<bean:message key="appNotList.appStatus"/>' ,  width :90, 		align:'center'}, // 결재상태
	{id: 'wfType',   	header: '<bean:message key="appNotList.wfType"/>' ,	 	width :130,    	align :'center'}, // 결재종류
	{id: 'objectNo',   	header: '<bean:message key="appNotList.objectNo"/>' , 	width :90,    align :'center' 	},  // 문서번호
    {id: 'appDate',  	header: '<bean:message key="appNotList.checkDate"/>' ,  width :100,    align:'center'	 }, // 확인일자
	{id: 'enterDate',   header: '<bean:message key="appNotList.enterDate"/>',   width :100,    align:'center'  },	// 기안일자
    {id: 'appFlowNo',   hidden: true   }
];

var gridOption={
    id : 'appNotList',
    width: "961",
    height: "270",
    container : 'gridbox', 
    replaceContainer : true,
    dataset : dsOption ,
    columns : colsOption,
    toolbarPosition : 'bottom',
   
    onLoadFinish:function()
    {
        setGridEditable(appNotListGrid, true);
        
        // 목록에서 조회되어 보여줄때
        if (appNotListForm.elements['strutsAction'].value == '<%=AppNotListAction.BASE_QUICK_LIST%>')
        {
        	findGridList('Search');
        }
    },
    
    onDblClickCell:function(value, record , cell, row, colNO, rowNO,columnObj,grid)
    {
        goTabPage('appNotDetail');
    },
    
    beforeEdit:function(_value, _record, _col, _grid)
    {
   		var isCheck = _record[_grid.getColumn('isCheck').fieldIndex];
    	// 수정된 record 가 아니고, isCheck에 체크 되어있다면 수정못하게 함
    	if (!this.dataset.isUpdatedRecord(_record) && isCheck == 'Y') return false;
    	
    	return true;
    },
    
    toolbarContent : setToolBarContent("readOnly"),
    autoLoad : true
};

var appNotListGrid;

function initGrid()
{
    appNotListGrid=new Sigma.Grid( gridOption );
    appNotListGrid.render();
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(_Action)
{
    var url = contextPath + "/appNotList.do";
    appNotListForm.elements['strutsAction'].value = '<%=AppNotListAction.APP_NOT_FIND%>';
    doGridAction(_Action, appNotListGrid, url, FormQueryString(appNotListForm));
}

/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	appNotListForm.elements['appNotCommonDTO.appFlowNo'].value = "";
	findGridList('Search');
}

/**
 * Tab 이동시 호출
 */
function goTabPage(pageId)
{
    var totalCount = appNotListGrid.dataset.getSize();
    
    // 상세로 넘어가는 경우를 제외하고, 나머지 탭은 목록에서 조회를 하지 않았다면 이동시키지 않는다.
    if (pageId != "appNotDetail")
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
    	var appFlowNo = appNotListGrid.activeRecord[appNotListGrid.getColumn('appFlowNo').fieldIndex];
    	var appDate = appNotListGrid.activeRecord[appNotListGrid.getColumn('appDate').fieldIndex];
    	
    	// 확인일자가 없다면 확인 안된건
    	var isCheck="N";
    	if(""==appDate) isCheck="Y";
    	
        appNotListForm.elements['appReqCommonDTO.appFlowNo'].value = appFlowNo;
        appNotListForm.elements['appReqCommonDTO.isCheck'].value = isCheck;
    }

    goCommonTabPage(appNotListForm, '<%=AppNotListAction.BASE_DEFAULT_INIT%>', pageId);
}

/**
 * 확인
 */
function goConfirm()
{
    var form = document.appNotListForm;

	form.strutsAction.value = "<%=AppNotListAction.APP_NOT_CONFIRM%>";
    var actionUrl = contextPath + "/appNotList.do";
    var param = FormQueryString(form);
    
    doGridAction('SaveArray', appNotListGrid, actionUrl, param);  
}

/**
 * 저장후 호출
 */
 function onGridSaveEnd(_grid, ajaxXmlDoc)
 {
   	//===============================================================
    // 시스템 관리자에게 문의 바랍니다.
    var valMessage = '<bean:message key="COMMON.CMSG031"/>';
    if (!valCheckHttpXml(ajaxXmlDoc, valMessage)) return;
    //===============================================================

    // 처리 되었습니다.
    alertMessage1('<bean:message key="MESSAGE.MSG013"/>');    
    goSearch();
}
/**
 * server 에서 조회 excel download시 호출
 */
function exportExcel(_grid)
{
    doExportExcel(_grid, appNotListForm);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_appFlowNo)
{
	appNotListForm.elements['appNotCommonDTO.appFlowNo'].value = _appFlowNo;
	findGridList('ReloadRow');
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
    <!-- searchbox 박스 Line -->
    <div>
		<table cellspacing="0" cellpadding="0" border="0" style="width: 100%;" class="border_search" >
			<html:form action="/appNotList" styleClass="treeTabForm">
				<html:hidden property="strutsAction"/>
				<input type="hidden" name="appNotCommonDTO.appFlowNo" /><!-- 1row 재조회시 사용 -->
				<input type="hidden" name="appReqCommonDTO.appFlowNo" /><!-- 상세Key -->
				<input type="hidden" name="appReqCommonDTO.isCheck" /><!-- 상세 조회시 통보문서 확인여부 -->
				<tbody>
					<tr>
						<!-- 결재번호 -->
						<th class="td_title" width="100"><bean:message key="appNotList.appDocNo"/></th>
	                    <td class="td_input" width="240">
	                        <html:text property="appNotCommonDTO.filterAppDocNo" size='12' styleClass="no_bg" tabindex="10"/>
	                    </td>
	                    <!-- 제목 -->
	                    <th class="td_title" width="80"><bean:message key="appNotList.title"/></th>
	                    <td class="td_input" width="240">
	                    	  <html:text property="appNotCommonDTO.title" styleClass="no_bg" size='37' maxlength="100" tabindex="20" /> 
	                    </td>
		                <!-- 결재상태 -->
				        <th scope="col" class="td_title"><bean:message key="appNotList.appStatus"/></th>
				        <td class="td_input"> 
				        	<html:select property="appNotCommonDTO.appStatus" styleClass="select" style="width:50" tabindex="30" >
				           	<option value="">--------------------------------</option>
				               <html:optionsCollection property="appStatusOptions"/>
				           </html:select>	                           
				        </td>
					</tr>
					<tr>
		                <!-- 확인일자 --> 
		                <th class="td_title"><bean:message key="appNotList.checkDate"/></th>
		                <td class="td_input">
			 				<html:text property="appNotCommonDTO.appDateFrom" styleClass="no_line" size='7' maxlength="10" tabindex="40"
			 					onkeydown="javascript:onlyNumberInput(event);" onkeyup="javascript:dateFormat(this, event);"
			 				/><input name='popupImg' type="button" onfocus="this.blur();" class="btn_lov1" onmouseover="this.style.cursor='pointer'"
					                   onclick="openCalendar('appNotCommonDTO.appDateFrom', event);"/>
					          ~
			 				<html:text property="appNotCommonDTO.appDateTo" styleClass="no_line" size='7' maxlength="10" tabindex="50"
			 					onkeydown="javascript:onlyNumberInput(event);" onkeyup="javascript:dateFormat(this, event);"
			 				/><input name='popupImg' type="button" onfocus="this.blur();" class="btn_lov1" onmouseover="this.style.cursor='pointer'"
					                   onclick="openCalendar('appNotCommonDTO.appDateTo', event);"/>
						</td>
		                
		                <!-- 기안일자 --> 
		                <th class="td_title"><bean:message key="appNotList.enterDate"/></th>
		                <td class="td_input">
		 					<html:text property="appNotCommonDTO.enterDateFrom" styleClass="no_line" size='7' maxlength="10" tabindex="60"
		 						onkeydown="javascript:onlyNumberInput(event);" onkeyup="javascript:dateFormat(this, event);"
		 					/><input name='popupImg' type="button" onfocus="this.blur();" class="btn_lov1" onmouseover="this.style.cursor='pointer'"
				                     onclick="openCalendar('appNotCommonDTO.enterDateFrom', event);"/>
				            ~
		 					<html:text property="appNotCommonDTO.enterDateTo" styleClass="no_line" size='7' maxlength="10" tabindex="70"
		 						onkeydown="javascript:onlyNumberInput(event);" onkeyup="javascript:dateFormat(this, event);"
		 					/><input name='popupImg' type="button" onfocus="this.blur();" class="btn_lov1" onmouseover="this.style.cursor='pointer'"
				                     onclick="openCalendar('appNotCommonDTO.enterDateTo', event);"/>
						</td>
		                <!-- 결재종류 -->
				        <th scope="col" class="td_title"><bean:message key="appNotList.wfType"/></th>
				        <td class="td_input"> 
							<html:select property="appNotCommonDTO.wfType" styleClass="select" style="width:50" tabindex="80" >
								<option value="">--------------------------------</option>
								<html:optionsCollection property="wfTypeOptions"/>
							</html:select>	                           
				        </td>
					</tr>
					<tr>
						<th class="td_title"></th>
						<td class="td_title left">
						<!-- 결재처리상태 -->
							<html:radio property="appNotCommonDTO.wfAction" value="P" styleClass="input_noborder" onfocus='this.blur();' /><bean:message key="appNotList.uncheck"/><!-- 미확인 -->
							<html:radio property="appNotCommonDTO.wfAction" value="C" styleClass="input_noborder" onfocus='this.blur();' /><bean:message key="appNotList.checked"/><!-- 확인 -->
						</td>
						<!-- 문서번호 -->
						<th class="td_title"><bean:message key="appNotList.objectNo"/></th>
						<td class="td_input">
							<html:text property="appNotCommonDTO.objectNo" styleClass="no_bg" size='11' maxlength="20" tabindex="90" /> 
						</td>
						<th class="td_title" colspan="2">
							<mware:search />
						</th>
					</tr>
				</tbody>
			</html:form> 
		</table>
    </div>
    <div class="border_body">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		    <tr><td class="grid_top"></td></tr>
	        <tr>
	            <td>
	                <mware:grid gridId="gridbox" />
	            </td>
	        </tr>
	    </table>
	    <div class="clear"></div>
    </div>
<c:import charEncoding="UTF-8"  url="/common/jsp/buttonInclude.jsp"></c:import>
</body>
</html>