<%--===========================================================================
수리기안
author  ssong
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.part.rep.action.MaPtRepAppDetailAction"%>
<html>
<head>
<!-- 수리기안-->
<title><bean:message key="TAB.maPtRepAppList"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var ptAppIdAc;
function loadPage() 
{
    if(ckCreate(currentPageId)) goInput();
    
    setTitle("maPtRepAppDetailDTO.ptAppId");
    setForUpdate();
    
    ptAppIdAc = new autoC({"maPtRepAppDetailDTO.ptAppId":"ptapp_id"});
    ptAppIdAc.setAcDisplay("ptapp_id");
    ptAppIdAc.setAcConditionMap({
		"comp_no":loginUser.compNo
	   });
    ptAppIdAc.setTable("TEPTAPPLIST");
    //oracle : DECODE(rec_date,'','',SUBSTR(rec_date, 0, 4)||'-'||SUBSTR(rec_date, 5, 2)||'-'||SUBSTR(rec_date, 7, 2))
    //mssql : CASE rec_date WHEN '' THEN '' ELSE SUBSTRING(rec_date, 1, 4)+'-'+SUBSTRING(rec_date, 5, 2)+'-'+SUBSTRING(rec_date, 7, 2) END
    ptAppIdAc.setAcResultMap({
	    "maPtRepAppDetailDTO.ptAppId":"ptapp_id",
	    "maPtRepAppDetailDTO.title":"title",
	    "maPtRepAppDetailDTO.recDate":" DECODE(rec_date,'','',SUBSTR(rec_date, 0, 4)||'-'||SUBSTR(rec_date, 5, 2)||'-'||SUBSTR(rec_date, 7, 2)) ",
	    "maPtRepAppDetailDTO.eqDesc":"eq_desc",
	    "maPtRepAppDetailDTO.totAmt":"tot_amt",
	    "maPtRepAppDetailDTO.contents":"contents"
	    
	});
    ptAppIdAc.setKeyName("maPtRepAppDetailDTO.ptAppId");
    ptAppIdAc.init();

}

function goInput()
{
    // 시퀀스를 조회한다.
    sequenceNextVal('SQAPTRPRAPPLIST_ID');
    maPtRepAppDetailForm.elements['maPtRepAppDetailDTO.ptRepairListId'].value = maPtRepAppDetailForm.elements['maPtRepCommonDTO.ptRepairListId'].value;
}

function setSequenceVal(sequenceVal)
{
    maPtRepAppDetailForm.elements['maPtRepAppDetailDTO.ptRprAppListId'].value = sequenceVal;
}

function goSave()
{
    if(ckCreate(currentPageId)) maPtRepAppDetailForm.strutsAction.value = '<%=MaPtRepAppDetailAction.PTREPAPP_DETAIL_INPUT%>';
    else maPtRepAppDetailForm.strutsAction.value = '<%= MaPtRepAppDetailAction.PTREPAPP_DETAIL_UPDATE %>';
    
    var actionUrl = contextPath + "/maPtRepAppDetail.do";
    XMLHttpPost(actionUrl, FormQueryString(maPtRepAppDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
    //=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(maPtRepAppDetailForm.elements['maPtRepAppDetailDTO.ptRprAppListId'].value);
	
    getTopPage().afterSaveAll(currentPageId);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/maPtRepAppDetail.do">
    <html:hidden property="strutsAction"/>
    <html:hidden property="maPtRepCommonDTO.ptRepairListId"/>
    <html:hidden property="maPtRepAppDetailDTO.ptRepairListId"/>
    <html:hidden property="maPtRepAppDetailDTO.ptRprAppListId"/>
    <!-- searchbox 박스 Line -->
    <div class="article_box">
        <div class="form_box"> 
            <!-- 품의번호 -->
            <div class="field">
                <label><bean:message key="LABEL.ptAppId"/></label>
                <div class="input_box">
                    <html:text property="maPtRepAppDetailDTO.ptAppId" tabindex="10"/>
                    <p class="open_spop">
                        <a href="javascript:lovPtApp('maPtRepAppDetailDTO.ptAppId'
	                        ,'maPtRepAppDetailDTO.title'
	                        ,'maPtRepAppDetailDTO.recDate','maPtRepAppDetailDTO.eqDesc'
	                        ,'maPtRepAppDetailDTO.totAmt','maPtRepAppDetailDTO.contents');">
                            <span>조회</span>
                        </a>
                    </p>
                </div> 
            </div>
            <!-- 접수일자 -->
            <div class="field">
                <label><bean:message key="LABEL.recpDate"/></label>
                <div class="input_read">
                    <html:text property="maPtRepAppDetailDTO.recDate" readonly="true"/>
                </div>
            </div>
            <!-- 설비명 -->
            <div class="field">
                <label><bean:message key="LABEL.equipName"/></label>
                <div class="input_read">
                    <html:text property="maPtRepAppDetailDTO.eqDesc" readonly="true" />
                </div>
            </div>
            <!-- 금액 -->
            <div class="field">
                <label><bean:message key="LABEL.amt"/></label>
                <div class="input_read">
                    <html:text property="maPtRepAppDetailDTO.totAmt" readonly="true" />
                </div>
            </div>
            <!-- 제목 -->
            <div class="field_long">
                <label><bean:message key="LABEL.title"/></label>
                <div class="input_read">
                    <html:text property="maPtRepAppDetailDTO.title" readonly="true" />
                </div>
            </div>
            <!-- 내용 -->
            <div class="field_long">
                <label><bean:message key="LABEL.contents"/></label>
                <div class="input_read">
                    <html:textarea property="maPtRepAppDetailDTO.contents" styleClass="ta50" readonly="true" />
                </div>
            </div>
        </div><!--article_box end-->
    </div> <!-- End of Article_box -->
</html:form> 
</body>
</html>