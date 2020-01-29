<%--===========================================================================
통보문서 - 상세
author  javaworker
version $Id: appNotDetail.jsp,v 1.6 2014/08/05 01:14:18 pochul2423 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.pers.appreq.action.AppReqDetailAction"%>
<%@page import="dream.app.not.action.AppNotListAction"%>
<html>
<head>
<!-- 통보문서 -->
<title><bean:message key="MENU.APPNOT"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<script language="javascript">
<!--//
function loadPage() 
{
	initFlowUserGrid();
	
   	// 통보문서 확인처리
   	confirmNotDoc();
   	
   	tinyMCE.init({
		selector: "textarea",
		menubar:false,
		toolbar:false,
		statusbar : false,
		plugins: [
		          "noneditable preview"
		      ],
		      setup: function (ed) {
		          ed.on('PreInit', function (event) {
		              var ed = event.target, dom = ed.dom;
		              dom.setAttrib(ed.getBody(), 'contenteditable', false);
		          });
		      },
		readonly: true
	});
	
	// 문서번호 셋팅
	setAppObjectNo();
}

/**
 * 결재문서 셋팅
 */
function setAppObjectNo()
{
	var wfType = appReqDetailForm.elements['appReqDetailDTO.wfType'].value;
	if ("" == wfType || "GEN" == wfType) return;
	
	M$('objecTag').innerText = '<bean:message key="appReqDetail.objectNo"/>';
	
	var _objectNo = appReqDetailForm.elements['appReqDetailDTO.objectNo'].value;
	
	var objectArray = _objectNo.split(',');
	
	var objectHtml = "";
	for (var i=0; i<objectArray.length; i++)
	{
		if ("" != objectHtml) objectHtml = objectHtml + " , ";
		
		// 작업요청 작성중에는 link 필요없음
		var alinkObject = "<a href='javascript:goAppDetailview(\""+objectArray[i]+"\")'>" + objectArray[i] + "</a>";
		objectHtml = objectHtml + M$('objectNo').innerHTML + alinkObject;
	}
	M$('objectNo').innerHTML = objectHtml;
}

/**
 * 상세 보기
 */
function goAppDetailview(_objectNo)
{
	goDetailview(_objectNo, appReqDetailForm.elements['appReqDetailDTO.wfType'].value);
}

/**
 * 통보문서 확인처리
 */
function confirmNotDoc()
{
    var form = document.appReqDetailForm;
    
    var isCheck = form.elements['appReqCommonDTO.isCheck'].value;
    if ("Y" != isCheck) return;    

	form.strutsAction.value = "<%=AppNotListAction.APP_NOT_CONFIRM%>";
	var appFlowNo = form.elements['appReqDetailDTO.appFlowNo'].value;
	var param = "&" + "appNotList[0].isCheck=Y" +
	            "&" + "appNotList[0].dtStatus=U" +
	            "&" + "appNotList[0].appFlowNo="+appFlowNo;
	
    var actionUrl = contextPath + "/appNotList.do";

    XMLHttpPost(actionUrl, FormQueryString(form)+param, 'afterConfirm');
}
function afterConfirm(ajaxXmlDoc)
{
    //=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    	
 	// 목록의 수정된 Row를 재조회
    if (parent.findGridRow)	parent.findGridRow(appReqDetailForm.elements['appReqDetailDTO.appFlowNo'].value);
}

var flowUserDsOption= {

	fields :[
	    {name : 'row_edit' 	},
		{name : 'seqNo'   	},
		{name : 'wfStatus'  },
		{name : 'appDate', type:'dateTime'   },
		{name : 'appType'   },
		{name : 'userInfo' 	},
		{name : 'appDesc' 	},
		{name : 'userId' 	}
	],
	recordType : 'array'
};

var flowUserColsOption = [
    {id: 'row_edit'	,  hidden : true		},
    {id: 'seqNo'	,  header: '<bean:message key="appNotDetail.seqNo"/>', 	  align:'center',width :40, soltable:false	},	// 순번
    {id: 'wfStatus'	,  header: '<bean:message key="appNotDetail.wfStatus"/>', align:'center',width :70, soltable:false	},	// 처리상태
    {id: 'appDate'	,  header: '<bean:message key="appNotDetail.appDate"/>', align:'center',width :110, soltable:false	},	// 결재일자
    {id: 'appType'	,  header: '<bean:message key="appNotDetail.appType"/>', align:'center',width :70, soltable:false	},	// 승인구분
    {id: 'userInfo'	,  header: '<bean:message key="appNotDetail.userInfo"/>', width :200, soltable:false},	// 결재자정보
    {id: 'appDesc'	,  header: '<bean:message key="appNotDetail.appDesc"/>', width :440, soltable:false, toolTip:true, toolTipWidth:440},	// 결재의견
    {id: 'userId'	,  hidden : true	}	// 결재자
];

var flowUserGridOption={
	id : "flowDtlDTOList",
	width: "961",  
	height: "170",  
	container : 'flowUserGrid', 
	replaceContainer : true,
	dataset : flowUserDsOption,
	columns : flowUserColsOption,
	toolbarPosition : 'bottom',
	editable : true,
    onLoadFinish:function()
    {
    	setGridEditable(this, false);
    	
    	findFlowUserList();
	},

	afterSearch:function()
	{
	},
	
	onDelRow:function(_grid, _rowNo)
	{
		resetSeqNo(_grid, _rowNo);
		this.refresh(false, true);
		selectEquiVal(_grid, 'seqNo', _rowNo);
	},
	
	onClickCell:function(value, record, cell, row, colNO, rowNO, columnObj, grid)
	{
		// ToolTip 보이게...
		cellToolTip(grid, cell, value, columnObj);
	},

	toolbarContent : "nav | del | xls",
	autoUpdateSortState : false,
    autoLoad : true
};

var flowUserGrid;
function initFlowUserGrid()
{
	flowUserGrid = new Sigma.Grid( flowUserGridOption );
	flowUserGrid.render();
}

/**
 * 탭이동
 */
function goTabPage(pageId)
{
	//==========================
	if (checkIsUpdate()) return;
	//==========================
	
	goCommonTabPage(appReqDetailForm, '<%=common.struts.BaseAction.BASE_DEFAULT_INIT%>', pageId);
}

/*
 * 저장된 결재선 조회 
 */
function findFlowUserList()
{
	appReqDetailForm.strutsAction.value = '<%=AppReqDetailAction.FLOW_USER_LIST%>';
	
	var url = contextPath + "/appReqDetail.do";
	doGridAction('Search', flowUserGrid, url, FormQueryString(appReqDetailForm));
}

/**
 * 결재요청 취소
 */
function goCancelreq()
{
	
}

//-->
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
<c:import charEncoding="UTF-8" url="/common/jsp/topButtonInclude.jsp"></c:import>
<div>
  <div class="board_detail">
	<html:form action="/appReqDetail">
	<html:hidden property="strutsAction" />
	<html:hidden property="appReqCommonDTO.isCheck"/><!-- 확인여부(Y/N) -->
	<html:hidden property="appReqDetailDTO.appFlowNo"/><!-- 결재상세번호 -->
	<html:hidden property="appReqDetailDTO.objectNo"/><!-- 문서번호 -->
    <table cellspacing="0" cellpadding="0" border="0" summary="List" class="board_detail_list">
	    <tr>
	      	<!-- 결재번호 -->
	        <td class="td_title_d" width="130px"><bean:message key="appNotDetail.appDocNo"/></td>
	        <td class="td_input_d" width="250px">
	            <html:text property="appReqDetailDTO.appDocNo" style="width:100px;" styleClass="input_read" readonly="true" />
	        </td>
          	<!-- 결재상태 -->
            <td class="td_title_d" width="150px"><bean:message key="appNotDetail.appStatus"/></td>
            <td class="td_input_d">
	            <html:select property="appReqDetailDTO.appStatus" style="width:100px;" disabled="true" styleClass="select">
	           		<option value="">-----------</option>
	                <html:optionsCollection property="appStatusOptions" />
	            </html:select>
            </td>
	    </tr>
		<tr>
	      <!-- 제목 -->
	        <td class="td_title_d"><bean:message key="appNotDetail.title"/></td>
	        <td class="td_input_d" colspan="3">
	            <html:text property="appReqDetailDTO.title" styleClass="input_read" style="width:280px;" maxlength="50" readonly="true"/>
	        </td>
		</tr>
        <tr>
          <!-- 결재종류 -->
            <td class="td_title_d"><bean:message key="appNotDetail.wfType"/></td>
            <td class="td_input_d">
	            <html:select property="appReqDetailDTO.wfType" style="width:150px;" disabled="true" styleClass="select">
	           		<option value="">-------------------------------</option>
	                <html:optionsCollection property="wfTypeOptions" />
	            </html:select>
            </td>
          	<!-- 문서번호 -->
            <td class="td_title_d" id="objecTag"></td>
            <td class="td_input_d">
            	<font color="red" id="objectNo" size="3"></font>
            </td>
        </tr>
        <tr>
          <!-- 내용 -->
            <td class="td_title_d"><bean:message key="appNotDetail.remark"/></td>
            <td class="td_input_d" colspan="3">
                <html:textarea property="appReqDetailDTO.remark" rows="9" style="width:700px;" readonly="true"/>
            </td>
        </tr>
        </tbody>
    </table>
	</html:form>
  </div> 
  <div class="border_body">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
    	<tr><td class="grid_top"></td></tr>
        <tr>
        	<td>
	        	<mware:grid gridId="flowUserGrid" />
	        </td>
        </tr>
    </table>
  </div>
</div>
<c:import url="/common/jsp/buttonInclude.jsp"></c:import>
</body>
</html>