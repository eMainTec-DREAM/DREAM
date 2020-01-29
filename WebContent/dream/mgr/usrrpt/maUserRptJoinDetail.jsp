<%--===========================================================================

author  ssong
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
<%@page import="dream.mgr.usrrpt.action.MaUserRptJoinDetailAction"%>
<html>
<head>
<!--  -->
<title><bean:message key="LABEL.equipment"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
/*자동완성 */
var taTable, rTable, lCol, rCol;
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
	
	setFunction();
	
    if($("[name='maUserRptJoinDetailDTO.ltableId']").val() == "")setReadOnly("maUserRptJoinDetailDTO.lcolumnName");
	if($("[name='maUserRptJoinDetailDTO.rtableId']").val() == "")setReadOnly("maUserRptJoinDetailDTO.rcolumnName");
	
	
	$("[name='maUserRptJoinDetailDTO.rcolumnName']").parents('.field').hide().find('.check').removeClass('check');
	$("[name='maUserRptJoinDetailDTO.rtableName']").parents('.field').hide().find('.check').removeClass('check');
	$("[name='maUserRptJoinDetailDTO.conValue']").parents('.field').hide().find('.check').removeClass('check');
	
	afterAutoCmpt("maUserRptJoinDetailDTO.colValueTypeDesc");
}

function setFunction()
{
	setTitle("maUserRptJoinDetailDTO.stepNum","maUserRptJoinDetailDTO.ltableName");
	setForUpdate();
	
	// 왼쪽 테이블명
	taTable = new autoC({"maUserRptJoinDetailDTO.ltableName":"table_name"});
	taTable.setTable("TAUSRRPTTAB");
	taTable.setAcResultMap({
        "maUserRptJoinDetailDTO.ltableId":"table_id"
        ,"maUserRptJoinDetailDTO.lusrrpttabId":"usrrpttab_id"
        ,"maUserRptJoinDetailDTO.ltableName":"table_name"
    });
	taTable.setKeyName("maUserRptJoinDetailDTO.ltableId"); 
	taTable.init();
	
	//-----------------------------------------------------------------------//
	// 오른쪽 테이블명
	rTable = new autoC({"maUserRptJoinDetailDTO.rtableName":"table_name"});
	rTable.setTable("TAUSRRPTTAB");
	rTable.setAcResultMap({
        "maUserRptJoinDetailDTO.rtableId":"table_id"
        ,"maUserRptJoinDetailDTO.rusrrpttabId":"usrrpttab_id"
        ,"maUserRptJoinDetailDTO.rtableName":"table_name"
    });
	rTable.setKeyName("maUserRptJoinDetailDTO.rtableId"); 
	rTable.init();

	//-----------------------------------------------------------------------//
	lCol = new autoC({"maUserRptJoinDetailDTO.lcolumnName":"column_name"});
	lCol.setTable("TATABCOL");
	lCol.setAcResultMap({
        "maUserRptJoinDetailDTO.ltabcolId":"tabcol_id"
    });
	lCol.setKeyName("maUserRptJoinDetailDTO.ltabcolId"); 
	lCol.init();
	
	//------------------------------------------------------------------------//
	rCol = new autoC({"maUserRptJoinDetailDTO.rcolumnName":"column_name"});
	rCol.setTable("TATABCOL");
	rCol.setAcResultMap({
        "maUserRptJoinDetailDTO.rtabcolId":"tabcol_id"
    });
	rCol.setKeyName("maUserRptJoinDetailDTO.rtabcolId"); 
	rCol.init();
	//-----------------------------------------------------------------------//
	
	acSysDesc("maUserRptJoinDetailDTO.colValueTypeDesc","maUserRptJoinDetailDTO.colValueType","COL_VALUE_TYPE", true);
	acSysDesc("maUserRptJoinDetailDTO.tabConOperatorDesc","maUserRptJoinDetailDTO.tabConOperator","TAB_CON_OPERATOR", true);
}

function afterSetValue(code)
{
	afterAutoCmpt(code=="COL_VALUE_TYPE"?"maUserRptJoinDetailDTO.colValueTypeDesc":code);
}

function afterAutoCmpt(code)
{
	if(code == "maUserRptJoinDetailDTO.ltableName")
	{
		if($("[name='maUserRptJoinDetailDTO.ltableId']").val() == "") return;
		
		setReadable("maUserRptJoinDetailDTO.lcolumnName");

		$("[name='maUserRptJoinDetailDTO.ltabcolId']").val("");
		$("[name='maUserRptJoinDetailDTO.lcolumnName']").val("");	
	}
	else if(code == "maUserRptJoinDetailDTO.rtableName")
	{
		if($("[name='maUserRptJoinDetailDTO.rtableId']").val() == "") return;
		
		setReadable("maUserRptJoinDetailDTO.rcolumnName");
		
		$("[name='maUserRptJoinDetailDTO.rtabcolId']").val("");
		$("[name='maUserRptJoinDetailDTO.rcolumnName']").val("");	
		
	}
	else if(code == "maUserRptJoinDetailDTO.colValueTypeDesc")
	{
		var colValType = $("[name='maUserRptJoinDetailDTO.colValueType']").val();

		if(colValType == "COLUMN")
		{
			$("[name='maUserRptJoinDetailDTO.rcolumnName']").parents('.field').show().find('label').addClass('check');
			$("[name='maUserRptJoinDetailDTO.rtableName']").parents('.field').show().find('label').addClass('check');
			$("[name='maUserRptJoinDetailDTO.conValue']").parents('.field').hide().find('.check').removeClass('check');
		}
		else if(colValType == "VALUE")
		{
			$("[name='maUserRptJoinDetailDTO.rcolumnName']").parents('.field').hide().find('.check').removeClass('check');
			$("[name='maUserRptJoinDetailDTO.rtableName']").parents('.field').hide().find('.check').removeClass('check');
			$("[name='maUserRptJoinDetailDTO.conValue']").parents('.field').show().find('label').addClass('check');
		}
		
		resizeTabFrame();
	}
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('sqausrrptjoin_id');
}

/**
 * 수정
 */
function goUpdate()
{

}

function setSequenceVal(sequenceVal)
{
	maUserRptJoinDetailForm.elements['maUserRptJoinDetailDTO.usrrptjoinId'].value = sequenceVal;
	maUserRptJoinDetailForm.elements['maUserRptCommonDTO.usrrptjoinId'].value = sequenceVal;
	maUserRptJoinDetailForm.elements['maUserRptJoinDetailDTO.usrrptlistId'].value = maUserRptJoinDetailForm.elements['maUserRptCommonDTO.usrrptlistId'].value;
	maUserRptJoinDetailForm.elements['maUserRptJoinDetailDTO.usrrpttabId'].value = maUserRptJoinDetailForm.elements['maUserRptCommonDTO.usrrpttabId'].value;

}

function goSave()
{	
	if(checkValidation()) return;
	
	if(ckCreate(currentPageId)) maUserRptJoinDetailForm.strutsAction.value = '<%=MaUserRptJoinDetailAction.USER_JOIN_DETAIL_INPUT%>';
	else maUserRptJoinDetailForm.strutsAction.value = '<%= MaUserRptJoinDetailAction.USER_JOIN_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/maUserRptJoinDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maUserRptJoinDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(maUserRptJoinDetailForm.elements['maUserRptJoinDetailDTO.usrrptjoinId'].value);
	
    getTopPage().afterSaveAll(currentPageId);
    
    //setTitle("maUserRptJoinDetailDTO.itemNo", "maUserRptJoinDetailDTO.equipDesc");
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/maUserRptJoinDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="maUserRptCommonDTO.usrrptlistId"/>
	<html:hidden property="maUserRptCommonDTO.usrrpttabId"/>
	<html:hidden property="maUserRptCommonDTO.usrrptjoinId"/>
	<html:hidden property="maUserRptJoinDetailDTO.usrrptlistId"/><!-- Key -->
	<html:hidden property="maUserRptJoinDetailDTO.usrrpttabId"/>
	<html:hidden property="maUserRptJoinDetailDTO.usrrptjoinId"/>
	<html:hidden property="maUserRptJoinDetailDTO.ltableId"/>
	<html:hidden property="maUserRptJoinDetailDTO.rtableId"/>
	<html:hidden property="maUserRptJoinDetailDTO.lusrrpttabId"/>
	<html:hidden property="maUserRptJoinDetailDTO.rusrrpttabId"/>
	<html:hidden property="maUserRptJoinDetailDTO.ltabcolId"/>
	<html:hidden property="maUserRptJoinDetailDTO.rtabcolId"/>
	<html:hidden property="maUserRptJoinDetailDTO.tabConOperator"/>
	<html:hidden property="maUserRptJoinDetailDTO.colValueType"/>

	<html:hidden property="maUserRptJoinDetailDTO.stepNum"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
            <!-- 왼쪽테이블명 -->
            <div class="field">
                <label class="check"><bean:message key="LABEL.ltableName"/></label>
                <div class="input_read">
                    <html:text property="maUserRptJoinDetailDTO.ltableName" tabindex="10" readonly="true"/>
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                </div>
            </div>
            <!-- 왼쪽컬럼 -->
            <div class="field">
                <label class="check"><bean:message key="LABEL.lcolumnName"/></label>
                <div class="input_box">
                    <html:text property="maUserRptJoinDetailDTO.lcolumnName" tabindex="20"/>
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                </div>
            </div>
            <!-- 조건연산자 -->
            <div class="field">
                <label class="check"><bean:message key="LABEL.tabConOperatorDesc"/></label>
                <div class="input_read">
                    <html:text property="maUserRptJoinDetailDTO.tabConOperatorDesc" tabindex="30" readonly="true"/>
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                </div>
            </div>
            <!-- 컬럼,값구분 -->
            <div class="field">
                <label class="check"><bean:message key="LABEL.colValueTypeDesc"/></label>
                <div class="input_read">
                    <html:text property="maUserRptJoinDetailDTO.colValueTypeDesc" tabindex="40" readonly="true"/>
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                </div>
            </div>
            <!-- 오른쪽테이블명 -->
            <div class="field">
                <label class="check"><bean:message key="LABEL.rtableName"/></label>
                <div class="input_read">
                    <html:text property="maUserRptJoinDetailDTO.rtableName" tabindex="50" readonly="true"/>
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                </div>
            </div>
            <!-- 오른쪽컬럼 -->
            <div class="field">
                <label class="check"><bean:message key="LABEL.rcolumnName"/></label>
                <div class="input_box">
                    <html:text property="maUserRptJoinDetailDTO.rcolumnName" tabindex="60"/>
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                </div>
            </div>
            <div class="field">
                <label class="check"><bean:message key="LABEL.conValue"/></label>
                <div class="input_box">
                    <html:text property="maUserRptJoinDetailDTO.conValue" tabindex="70" styleClass="num"/>
                </div>
            </div>
            
		</div><!--article_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>