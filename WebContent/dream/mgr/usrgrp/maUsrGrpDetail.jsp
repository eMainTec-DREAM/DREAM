<%--===========================================================================
권한명 - 상세
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.mgr.usrgrp.action.MaUsrGrpDetailAction"%>
<html:html>
<head>
<!-- 권한명 -->
<title><bean:message key='LABEL.usrGrpNo' /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript"> // 저장 시 수행되는 action
<!--//
var myTree;
var saveStrutsAction;
function loadPage() 
{ 
	saveStrutsAction = maUsrGrpDetailForm.elements['strutsAction'].value;
	
    if(ckCreate(currentPageId)) goInput();
    else goUpdate();
    
    setTitle("maUsrGrpDetailDTO.usrGrpNo", "maUsrGrpDetailDTO.groupName");
    //For Save
    setForUpdate();
    
    $("input[name='maUsrGrpDetailDTO.usrGrpNo']").blur( function(){
    	valUsrGrpNo();
    });
    
   // intiTree(); 
    
    //goTabPage('maUsrGrpAuthList');
}

// Tree 구성 
function intiTree()
{
    // 메뉴 Tree
    myTree = new dhtmlXTreeObject("treeboxbox_tree","100%","100%",0);
	myTree.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/dhxtree_skyblue/" />');
	myTree.enableCheckBoxes(true);
	//myTree.enableThreeStateCheckboxes(true); 
	//myTree.parse({id:"0","item":[{id:56,text:"관리자",checked:"",open:"1","item":[{id:109,text:"시스템코드",checked:"-1","OPEN":"1"},{id:89,text:"버튼",checked:"1","OPEN":"1"},{id:87,text:"화면",checked:"1","OPEN":"1"},{id:88,text:"메뉴",checked:"1","OPEN":"1"},{id:91,text:"사용자코드",checked:"-1","OPEN":"1"},{id:98,text:"다국어",checked:"-1","OPEN":"1"},{id:99,text:"시스템 환경변수",checked:"-1","OPEN":"1"},{id:92,text:"작업부서",checked:"-1","OPEN":"1"},{id:93,text:"보전사원",checked:"-1","OPEN":"1"},{id:94,text:"권한명",checked:"-1","OPEN":"1"},{id:95,text:"사용자",checked:"-1","OPEN":"1"},{id:100,text:"실시간 접속자",checked:"-1","OPEN":"1"},{id:97,text:"내정보",checked:"-1","OPEN":"1"}]}]}, "json")
	//myTree.parse([[1,0,"1111","select:1"], [2,0,"2222"], [3,0,"3333"], [4,2,"child"]], "jsarray");

	myTree.attachEvent("onCheck", function(id, state){ // Tree 체크 여부 
		maUsrGrpDetailForm.elements['maUsrGrpDetailDTO.isUpdateMenu'].value = "Y";
		getTopPage().updateArray[currentPageId] = "TREE";
	});

	var url = contextPath + "/maUsrGrpDetail.do";
	maUsrGrpDetailForm.elements['strutsAction'].value = '<%=MaUsrGrpDetailAction.USRGRP_DETAIL_TREE%>';
    
    $.post(url,FormQueryString(maUsrGrpDetailForm), function(_data){
    	//console.log(_data);
    	eval('var z='+_data); 
    	myTree.parse(z,"json");
    });
    
    // strutsAction 초기화 
    maUsrGrpDetailForm.elements['strutsAction'].value = saveStrutsAction;
}

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQAUSRGRP_ID');
}

/**
 * 수정
 */
function goUpdate()
{
    //수정시 등록유형과 등록id readonly설정 
    maUsrGrpDetailForm.elements['maUsrGrpDetailDTO.usrGrpNo'].readOnly = true;
    document.getElementById("usrGrpNoDiv").className = "input_read";
}

/**
 * 
 */
function setSequenceVal(sequenceVal)
{
    maUsrGrpDetailForm.elements['maUsrGrpDetailDTO.usrGrpId'].value = sequenceVal;
    maUsrGrpDetailForm.elements['maUsrGrpDetailDTO.usrGrpNo'].value = sequenceVal;
    maUsrGrpDetailForm.elements['maUsrGrpCommonDTO.usrGrpId'].value = sequenceVal;
}

/**
 * 저장
 */ 
function goSave()
{
    //================================
    // 필수 항목 체크한다.
    //================================
    if(checkValidation()) return;

    // 선택된 메뉴id의 파라메터 
//    var chkArray = getCheckRows(myTree);
//    if(typeof chkArray == "undefined") chkArray="";
    
    //strutsAction 셋팅.
    if(ckCreate(currentPageId)) maUsrGrpDetailForm.strutsAction.value = "<%=MaUsrGrpDetailAction.USRGRP_DETAIL_INPUT%>";
    else maUsrGrpDetailForm.strutsAction.value = "<%=MaUsrGrpDetailAction.USRGRP_DETAIL_UPDATE%>";
    
    var actionUrl = contextPath + "/maUsrGrpDetail.do";
    XMLHttpPost(actionUrl, FormQueryString(maUsrGrpDetailForm), 'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
    //=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    maUsrGrpDetailForm.elements['maUsrGrpDetailDTO.usrGrpId'].value = maUsrGrpDetailForm.elements['maUsrGrpCommonDTO.usrGrpId'].value;
    maUsrGrpDetailForm.elements['maUsrGrpDetailDTO.isUpdateMenu'].value = "N";
    
    if (parent.findGridRow)	parent.findGridRow(maUsrGrpDetailForm.elements['maUsrGrpDetailDTO.usrGrpId'].value);

    setTitle("maUsrGrpDetailDTO.usrGrpNo", "maUsrGrpDetailDTO.groupName");
    goUpdate();
    
    getTopPage().afterSaveAll(currentPageId);
}

/**
 * usrGrpNo 중복 체크
 */
function valUsrGrpNo()
{
    isValid = 0;

    if(maUsrGrpDetailForm.strutsAction.value == '0')
    {
        var actionUrl = contextPath + "/maUsrGrpDetail.do";
        var param =  "&strutsAction=" + '<%= MaUsrGrpDetailAction.USRGRP_DETAIL_CHECK %>'
                  +  "&" + FormQueryString(maUsrGrpDetailForm);
        XMLHttpPostVal(actionUrl, param, 'setValidUsrGrpNo');
    }
}

/**
 * valUsrGrpNo()실행 후 호출
 */
var isValid = 0;
function setValidUsrGrpNo(ajaxXmlDoc)
{
    isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
    
    if(isValid != '0')
    {
    	maUsrGrpDetailForm.elements['maUsrGrpDetailDTO.usrGrpNo'].value = '';
    	maUsrGrpDetailForm.elements['maUsrGrpDetailDTO.usrGrpNo'].focus();
        
    	// 중복되었습니다.
        alertMessage1("<bean:message key='MESSAGE.MSG0009' />");
    }
}

function goTabPage(pageId)
{
	goCommonTabPage(maUsrGrpDetailForm, '' , pageId);
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = maUsrGrpDetailForm.elements['maUsrGrpDetailDTO.usrGrpId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/maUsrGrpDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maUsrGrpCommonDTO.compNo" />
	<html:hidden property="maUsrGrpCommonDTO.usrGrpId" />
	<html:hidden property="maUsrGrpCommonDTO.usrGrpNo" />
	<html:hidden property="maUsrGrpDetailDTO.usrGrpId" />
	<html:hidden property="maUsrGrpDetailDTO.usrGrpMenuChkList" />
	<html:hidden property="maUsrGrpDetailDTO.isUpdateMenu" value="N" />
         <div class="article_box" id="detailBox">
             <div class="form_box">
                 <div class="field">
             	 	<label class="check"><bean:message key="LABEL.usrGrpNo"/></label>
             	 	<div id="usrGrpNoDiv" class="input_box">
             	 		<html:text property="maUsrGrpDetailDTO.usrGrpNo" tabindex="1"/>
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label class="check"><bean:message key="LABEL.groupName"/></label>
             	 	<div class="input_box">
             	 		<html:text property="maUsrGrpDetailDTO.groupName" tabindex="10"/>
             	 	</div>
             	 </div>
             	 <div class="field_long">
             	 	<label><bean:message key="LABEL.usrGrpRemark"/></label>
             	 	<div class="input_box">
             	 		<html:textarea property="maUsrGrpDetailDTO.remark" tabindex="1"/>
                    </div>
                </div>
             	 <%-- <div class="field_long">
             	 	<label><bean:message key="LABEL.usrGrpMenu"/></label>
             	 	<div class="input_box">
			              <div class="tree_area" id="listBox">
								<div id="treeboxbox_tree" style="height:218px;background-color:#f8f8f8; overflow:auto;"></div>
						  </div>
                    </div>
                </div> --%>
             </div> <!-- End of Form_box -->
         </div> <!-- End of Article_box -->
    </html:form>              
</body>
</html:html>
