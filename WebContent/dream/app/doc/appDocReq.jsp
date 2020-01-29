<%--===========================================================================
결재요청
author  javaworker
version $Id: appDocReq.jsp,v 1.12 2014/05/20 07:26:16 pochul2423 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.app.doc.action.AppDocReqAction"%>
<html>
<head>
<!-- 결재요청 -->
<title><bean:message key="MENU.APPDOCREQ"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<script language="javascript">
<!--//
function loadPage() 
{
	setPageButton('init');
	initFlowUserGrid();
	
	//editorInit(false);
}

function editorInit(flag, flag2)
{
	var flag3;
	if(flag2)
	{
		flag3= "undo redo | styleselect | bold italic | link image";
	}
	else
	{
		flag3=false;
	}
	tinyMCE.init({
		selector: "textarea",
		menubar:flag2,
		toolbar:flag3,
		statusbar : false,
		plugins: [
		          "noneditable preview"
		      ],
		      setup: function (ed) {
		          ed.on('PreInit', function (event) {
		              var ed = event.target, dom = ed.dom;
		              dom.setAttrib(ed.getBody(), 'contenteditable', flag2);
		          });
		      },
		readonly: flag
	});
}
var flowUserDsOption= {

	fields :[
	    {name : 'row_edit' 	},
		{name : 'seqNo'   	},
		{name : 'appType'   },
		{name : 'userInfo' 	},
		{name : 'userId' 	},
		{name : 'appFlowNo'	}
	],
	recordType : 'array'
};

var flowUserColsOption = [
    {id: 'row_edit'	,  hidden : true		},
    {id: 'seqNo'	,  header: '<bean:message key="appDocReq.seqNo"/>', align:'center',width :35, soltable:false	},	// 순번
    {id: 'appType'	,  header: '<bean:message key="appDocReq.appType"/>', align:'center', width :60, soltable:false,
		editor: { type :"select", validRule:['R'], 	options : {<c:out value="${appDocReqForm.appTypeCombo}" escapeXml="false" />} ,defaultText : '', validRule:['R'] } }, //승인종류
    {id: 'userInfo'	,  header: '<bean:message key="appDocReq.userInfo"/>', width :210, soltable:false},	// 결재자정보
    {id: 'userId'	,  hidden : true	},	// 결재자
    {id: 'appFlowNo',  hidden : true	}
];

var flowUserGridOption={
	id : "flowDtlDTOList",
	width: "330",  
	height: "130",  
	container : 'flowUserGrid', 
	replaceContainer : true,
	dataset : flowUserDsOption,
	columns : flowUserColsOption,
	toolbarPosition : 'bottom',
	editable : true,
    onLoadFinish:function()
    {
    	setGridEditable(this, true);
    	
    	// 입력 상태로 한다.
    	if (appDocReqForm.strutsAction.value == '<%=AppDocReqAction.BASE_DEFAULT_INIT%>')
    	{
    		goInput();
    		setAppObjectNo();
    	}
	},

	onDelRow:function(_grid, _rowNo)
	{
		resetSeqNo(_grid, _rowNo);
		this.refresh(false, true);
		selectEquiVal(_grid, 'seqNo', _rowNo);
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
 * 결재문서 셋팅
 */
function setAppObjectNo()
{
	M$('objecTag').innerText = '<bean:message key="appDocReq.objectNo"/>';
	
	var _objectNo = appDocReqForm.elements['appDocReqDTO.objectNo'].value;
	
	var objectArray = _objectNo.split(',');
	
	var objectHtml = "";
	for (var i=0; i<objectArray.length; i++)
	{
		if ("" != objectHtml) objectHtml = objectHtml + " , ";
		
		var alinkObject = objectArray[i];
		objectHtml = objectHtml + M$('objectNo').innerHTML + alinkObject;
	}
	M$('objectNo').innerHTML = objectHtml;
}

/**
 * Page Button 제어
 */
function setPageButton(_status)
{
    if ("init" == _status)
    {
        disabledButton("APPSUBMIT", false); // 결재요청
        disabledButton("REQCOM", true);     // 작성완료
        disabledButton("FILEATTACH", true); // 파일첨부
        disabledButton("APPLINE", true); 	// 결재선
        disabledButton("APPUSER", true); 	// 결재자
    }
    else if ("input" == _status)
    {
        disabledButton("APPSUBMIT", true);  // 결재요청
        disabledButton("REQCOM", false);     // 작성완료
        disabledButton("FILEATTACH", false); // 파일첨부
        disabledButton("APPLINE", false); 	// 결재선
        disabledButton("APPUSER", false); 	// 결재자
    }
    else
    {
        disabledButton("APPSUBMIT", true);  // 결재요청
        disabledButton("REQCOM", true);     // 작성완료
        disabledButton("FILEATTACH", true); // 파일첨부
        disabledButton("APPLINE", true); 	// 결재선
        disabledButton("APPUSER", true); 	// 결재자
    }
}

/**
 * 결재자 셋팅
 */
function goAppuser()
{
    var url   = contextPath + "/lovUser.do";

    var popWidth = 650;
    var popHeight = 600;

    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=no, status=no";
    
    var param = "?" + "strutsAction=" + lovBaseDefaultAction +
                "&" + "lovType=" + "appDoc";
    
    window.open(url + param, "LOV_USER", pos);
}

/**
 * lovUser.jsp 에서 호출
 */
function setAppUser(_userId, _userName, _deptName)
{
    addAppUser("", _userId, _userName, _deptName);
}

/**
 * 결재선 검색
 */
function goAppline()
{
    var url   = contextPath + "/lovAppLine.do";

    var popWidth = 450;
    var popHeight = 580;

    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=no, status=no";
    
    var param = "?" + "strutsAction=" + lovBaseDefaultAction;
    
    window.open(url + param, "LOV_APP_LINE", pos);
}

/**
 * lovAppLine(결재선지정) 에서 호출된다.
 */
function setAppLine(_userData)
{
    for (var i=0; i<_userData.length; i++)
    {
        addAppUser(_userData[i][2], _userData[i][3], _userData[i][4], _userData[i][5]);
    }
}

/**
 * user gridList에서 선택된 유저 넣기
 */
function addAppUser(_appType, _userId, _userName, _deptName)
{
	if (_appType==null || _appType=="") _appType = "AP";
	
    var insertRow = flowUserGrid.addRow();

    flowUserGrid.setColumnValue('seqNo', insertRow, flowUserGrid.getPageInfo().rowNum);
    flowUserGrid.setColumnValue('appType', insertRow, _appType);
    flowUserGrid.setColumnValue('userInfo', insertRow, _userId+' / '+_userName+' / '+_deptName);
    flowUserGrid.setColumnValue('userId', insertRow, _userId);
}

/**
 * 선택된 결재자를 위로 이동시킴
 */
function goUp()
{
    moveAppUser('UP');
}

/**
 * 선택된 결재자를 아래로 이동시킴
 */
function goDown()
{
    moveAppUser('DOWN');
}

/**
 * 현재 선택된 결재자의 위치를 이동
 */
function moveAppUser(_moveType)
{
	var _grid = flowUserGrid;
	var _record = _grid.activeRecord;
	if (_record == null) return;
	var seqNo = _record[_grid.getColumn('seqNo').fieldIndex];
	
	var moveSeqNo = 0;
    if (_moveType == 'UP')
    {
    	if (seqNo == 1) return;
    	moveSeqNo = parseInt(seqNo)-1;
    }
    else if (_moveType == 'DOWN')
    {
    	if (seqNo == _grid.rowNum) return;
    	moveSeqNo = parseInt(seqNo)+1;
    }
	selectEquiVal(_grid, 'seqNo', moveSeqNo);
	
	_record[_grid.getColumn('seqNo').fieldIndex] = moveSeqNo;
	
	var _moveRecord = _grid.activeRecord;
	_moveRecord[_grid.getColumn('seqNo').fieldIndex] = seqNo;
	
	//======================================
	// 재정렬
    E=_grid.Dy8(_grid.getColumn('seqNo'));
    E.sortOrder="asc";
    _grid.addSortInfo(E);
    _grid.refresh(false, true);
  	//======================================
	
  	selectEquiVal(_grid, 'seqNo', moveSeqNo);
}

/**
 * 정렬 seqNo를 다시 셋팅
 * (삭제시 seqNo 정렬)
 */
function resetSeqNo(_grid, _delRowNo)
{
	// 삭제 seqNo 보다 큰 seqNo 부터 reset(삭제후 호출되므로 같은 rowNo부터 처리하면됨) 
	var viewIndex = _delRowNo;
	for (var i=viewIndex; i<_grid.getPageInfo().rowNum; i++)
	{
		var _record = _grid.getRecord(i);
		_record[_grid.getColumn('seqNo').fieldIndex] = _record[_grid.getColumn('seqNo').fieldIndex]-1;
	}
}

/**
 * 초기 입력상태로 나타나게 한다.
 */
function goInput()
{
	// 결재요청시 결재번호 시퀀스를 조회
	sequenceNextVal('SQ2APP_DOC_NO');
	
	isUpdating = true;
	
	var _form = appDocReqForm;
	_form.elements['appDocReqDTO.enterDate'].value = getToday();		// loginUser User_Id
	_form.elements['appDocReqDTO.enterBy'].value = loginUser.userID;	// loginUser User_Id
	_form.elements['appDocReqDTO.enterByName'].value = loginUser.userName;	// loginUser User_Id
	
	_form.elements['appDocReqDTO.appStatus'].value = "APP01";	// 결재상태 : 요청
	
	//==============================
	// 해당 폼의 모든 Object를 enable 
	enableFormObject(_form, true);
	//==============================
	
	setPageButton('input');
	
    // 입력 가능 합니다.
    alertMessage1("<bean:message key='MESSAGE.MSG001'/>"); 
	editorInit(false, true);
}

/**
 * sequence value setting
 */ 
function setSequenceVal(sequenceVal)
{
	appDocReqForm.elements['appDocReqDTO.appDocNo'].value = sequenceVal;
}

/**
 * 결재요청
 */
function goAppsubmit()
{
	// 결재요청 버튼을 누른경우 [일반] 결재로 처리한다.
	appDocReqForm.elements['appDocReqDTO.wfType'].value = "GEN";
	
	goInput();
}

/**
 * 작성완료
 */
function goReqcom()
{
	// 	제목
    if (checkRequireValue('appDocReqDTO.title', '<bean:message key="appDocReq.title"/>')) return; //제목
    // 결재자가 1명도 없다면 오류
    if (flowUserGrid.getAllRowNum() == 0)
    {
        //결재자를 지정하시기 바랍니다.
        alertMessage1('<bean:message key="appDocReq.MSG001"/>');
        return;
    }
   
    // 중복 check
    var dupRowPm = flowUserGrid.colValueDup("userId");
    if(dupRowPm > -1)
    { 
        // 결재자가 중복되었습니다.
        alertMessage1('<bean:message key="appDocReq.MSG004"/>');
        flowUserGrid.selectRowByIndex(dupRowPm);
        return false; 
    }

	// textarea의 값을 dto에 덮어쓴다.
	tinyMCE.triggerSave();
	
    // 결재요청 시에 결재요청 버튼을 disable 한다.
    disabledButton("APPSUBMIT", true);
    
    appDocReqForm.strutsAction.value = '<%=AppDocReqAction.APP_DOC_INPUT%>';
    var actionUrl = contextPath + "/appDocReq.do";

    XMLHttpPost(actionUrl, FormQueryString(appDocReqForm)+'&' +getGridParam(flowUserGrid), 'afterAppsubmit');	
}

/**
 * 상신후 호출
 */
function afterAppsubmit(ajaxXmlDoc)
{
    //====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //====================================
    
    var appStatus = parseXmlDoc(ajaxXmlDoc, 'DESC');
    if (appStatus == "ERROR")
    {
        // 현재문서로 결재요청된 결재내역이 있습니다.
        alertMessage1('<bean:message key="appDocReq.MSG003"/>');
    	return;
    }
    
    try
    {
    	// 결재요청 되었습니다.
        opener.afterApprove('<bean:message key="appDocReq.MSG002"/>');
    }
    catch(ex){}
    
    // 결재요청 되었습니다.
    alertMessage1('<bean:message key="appDocReq.MSG002"/>');
    
    appDocReqForm.elements['appDocReqDTO.appStatus'].value = appStatus;
    
    //======================================
    // 해당 폼의 모든 Object를 enable 상태로 한다.
    readOnlyFormObject(appDocReqForm);
    setGridEditable(flowUserGrid, false);
    //======================================
    
    tinyMCE.get('appDocReqDTO.remark').remove();
	editorInit(true,false);
	
	setPageButton('afterSave');
    
    findFlowUserList();
    
    isUpdating = false;
    
    if (DECORATOR_NAME=='popupPage') self.close();
}

/*
 * 저장된 결재선 조회 
 */
function findFlowUserList()
{
	appDocReqForm.strutsAction.value = '<%=AppDocReqAction.FLOW_USER_LIST%>';
	
	var url = contextPath + "/appDocReq.do";
	doGridAction('Search', flowUserGrid, url, FormQueryString(appDocReqForm));
}

/**
 * 첨부파일 
 */
function goFileattach()
{
    var appDocNo = appDocReqForm.elements['appDocReqDTO.appDocNo'].value;
    
    // 첨부 파일
    lovAttachFile('APP', appDocNo);
}

//-->
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
<div>
  <div class="board_detail">
    <table cellspacing="0" cellpadding="0" border="0" summary="List" class="board_detail_list">
	  <html:form action="/appDocReq">
      <html:hidden property="strutsAction" />
      <html:hidden property="appDocReqDTO.objectNo" />
      <tbody>
	    <tr>
	      	<!-- 결재번호 -->
	        <td class="td_title_d" width="110px;"><mware:key/><bean:message key="appDocReq.appDocNo"/></td>
	        <td class="td_input_d" width="200px;">
	            <html:text property="appDocReqDTO.appDocNo" style="width:100px;" styleClass="input_read" />
	        </td>
          	<!-- 결재상태 -->
            <td class="td_title_d" width="100px;"><bean:message key="appDocReq.appStatus"/></td>
            <td class="td_input_d" width="150px;">
	            <html:select property="appDocReqDTO.appStatus" style="width:100px;" disabled="true" styleClass="select_read">
	           		<option value="">-----------</option>
	                <html:optionsCollection property="appStatusOptions" />
	            </html:select>
            </td>
			<td class="td_input_d" rowspan="5"><!-- 결재선, 결재자 -->
				<!-- 결재선 그리드 -->
				<table border="0" height="140" width="350" cellpadding="0" cellspacing="0" align="center">
					<tr>
						<td align="right">
			    			<mware:button buttonId="APPLINE" buttonType="midC"/>&nbsp;&nbsp;<mware:button buttonId="APPUSER" buttonType="midC"/>
						</td>
						<td></td>
					</tr>
            		<TR>
                		<TD>
                    		<mware:grid gridId="flowUserGrid" />
                		</TD>
		            	<td class="td_input" width="50" valign="middle">
		                	<mware:button buttonId="UP" buttonName="&#8593;" buttonType="midD"/><br><br>
		                	<mware:button buttonId="DOWN" buttonName="&#8595;" buttonType="midD"/>
		            	</td>
            		</TR>						
				</table>
			    
			</td>
	    </tr>
		<tr>
	      <!-- 제목 -->
	        <td class="td_title_d" width="100px;"><mware:key/><bean:message key="appDocReq.title"/></td>
	        <td class="td_input_d" colspan="3">
	            <html:text property="appDocReqDTO.title" styleClass="no_bg" style="width:400px;" maxlength="200" readonly="true" tabindex="10" />
	        </td>
		</tr>
		<tr>
	      	<!-- 기안일자 -->
	        <td class="td_title_d"><bean:message key="appDocReq.enterDate"/></td>
	        <td class="td_input_d">
	            <html:text property="appDocReqDTO.enterDate" styleClass="input_read center" readonly="true" style="width:100px;"/>
	        </td>
	      	<!-- 기안자 -->
	        <td class="td_title_d"><bean:message key="appDocReq.enterBy"/></td>
	        <td class="td_input_d">
	            <html:text property="appDocReqDTO.enterByName" styleClass="input_read" readonly="true" style="width:100px;" />
	            <html:hidden property="appDocReqDTO.enterBy" />
	        </td>
		</tr>
        <tr>
          <!-- 결재종류 -->
            <td class="td_title_d"><bean:message key="appDocReq.wfType"/></td>
            <td class="td_input_d" colspan="3">
	            <html:select property="appDocReqDTO.wfType" style="width:150px;" disabled="true" styleClass="select_read">
	           		<option value="">-------------------------------</option>
	                <html:optionsCollection property="wfTypeOptions" />
	            </html:select>
            </td>
        </tr>
        <tr>
          <!-- 문서번호 -->
            <td class="td_title_d" id="objecTag"></td>
            <td class="td_input_d" colspan="3" width="330px">
                <font color="red" id="objectNo" size="3"></font>
            </td>
        </tr>
        <tr>
          <!-- 내용 -->
            <td class="td_title_d"><bean:message key="appDocReq.remark"/></td>
            <td class="td_input_d" colspan="4">
                <html:textarea property="appDocReqDTO.remark" style="width:800px;" rows="17" readonly="true" />
            </td>
        </tr>
        </tbody>
      </html:form>
    </table>
  </div>
</div>
<c:import charEncoding="UTF-8" url="/common/jsp/buttonInclude.jsp" ></c:import>
</body>
</html>