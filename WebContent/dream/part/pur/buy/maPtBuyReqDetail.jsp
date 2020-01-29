
<%--===========================================================================
구매신청 item 상세
author  kim21017
version $Id: maPtBuyReqDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/functions.tld" prefix="udf"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ page import="common.bean.User"%>
<%@page import="dream.part.pur.buy.action.MaPtBuyReqDetailAction"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html>
<head>
<!--답변사항 -->
<title><bean:message key="LABEL.partDesc"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var partNoAc, empAc, accntAc, ctCtrAc, purGroupAc;
function loadPage() 
{
	setTitle("maPtBuyReqDetailDTO.partDescSize");
	setForUpdate();
	
	// 현장신청부품으로 생성한 품목일시에는 부품수정 불가능
	if(""!=maPtBuyReqDetailForm.elements['maPtBuyReqDetailDTO.ptPnListId'].value)
	{
		$('#partNo').removeClass('input_box').addClass('input_read').attr("readonly",true);
		$('#partNo').find('a').hide();
		$('#partDesc').removeClass('input_box').addClass('input_read').attr("readonly",true);
	}
	
	//화폐 자동완성
	
	if(""==maPtBuyReqDetailForm.elements['maPtBuyReqDetailDTO.currDesc'].value)
	{
		maPtBuyReqDetailForm.elements['maPtBuyReqDetailDTO.curr'].value = "WON";
		maPtBuyReqDetailForm.elements['maPtBuyReqDetailDTO.currDesc'].value = "WON";
	}
    acSysDesc("maPtBuyReqDetailDTO.currDesc","maPtBuyReqDetailDTO.curr","CURRENCY",true);

    //자재등급
    acSysDesc("maPtBuyReqDetailDTO.partGradeDesc","maPtBuyReqDetailDTO.partGrade","PART_GRADE",true);
	
	partNoAc = new autoC({"maPtBuyReqDetailDTO.partNo":"part_no"});
    partNoAc.setTable("TAPARTS");
    partNoAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo,
 	   "is_contract":"Y",
 	   "part_categ":"SPPT"
 	   });
    partNoAc.setAcResultMap({
    	"maPtBuyReqDetailDTO.partDesc":"description",
        "maPtBuyReqDetailDTO.partId":"part_id",
		"maPtBuyReqDetailDTO.erpPartNo":"erp_part_no",
		"maPtBuyReqDetailDTO.ptSize":"pt_size",
		"maPtBuyReqDetailDTO.model":"model",
		"maPtBuyReqDetailDTO.unitPrice":"last_price",
		"maPtBuyReqDetailDTO.uomDesc":"uom_desc"
    });
    partNoAc.setKeyName("maPtBuyReqDetailDTO.partId");
    partNoAc.init();
    
    // 요청자
    empAc = new autoC({"maPtBuyReqDetailDTO.appReqByDesc":"emp_name"});
    empAc.setTable("TAEMP");
    empAc.setAcResultMap({
        "maPtBuyReqDetailDTO.appReqById":"emp_id"
    });
    empAc.setKeyName("maPtBuyReqDetailDTO.appReqById");
    empAc.init();
    
    // 예산계정
    accntAc = new autoC({"maPtBuyReqDetailDTO.accntDesc":"description"});
    accntAc.setTable("TAACCOUNT");
    accntAc.setAcConditionMap({
  	   "is_use":"Y"
  	});
    accntAc.setAcResultMap({
        "maPtBuyReqDetailDTO.accntId":"accnt_id"
    });
    accntAc.setKeyName("maPtBuyReqDetailDTO.accntId");
    accntAc.init();
    
 	// 코스트센터
    ctCtrAc = new autoC({"maPtBuyReqDetailDTO.ctCtrDesc":"description"});
    ctCtrAc.setTable("TACTCTR");
    ctCtrAc.setAcConditionMap({
  	   "comp_no":loginUser.compNo
  	   ,"is_use":"Y"
  	});
    ctCtrAc.setAcResultMap({
        "maPtBuyReqDetailDTO.ctCtrId":"ctctr_id"
    });
    ctCtrAc.setKeyName("maPtBuyReqDetailDTO.ctCtrId");
    ctCtrAc.init();
    
    /** 구매그룹 */
    purGroupAc = new autoC({"maPtBuyReqDetailDTO.purGroupDesc":"description"});
    purGroupAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_use":"Y",
    	"dir_type":"PUR_GROUP"
  	   });
    purGroupAc.setTable("TACDUSRD");
    purGroupAc.setKeyName("maPtBuyReqDetailDTO.purGroup");
    purGroupAc.setAcResultMap({
        "maPtBuyReqDetailDTO.purGroup":"cdusrd_no"
    });
    purGroupAc.init();
    
    /** G/L계정범주 */
    acSysDesc("maPtBuyReqDetailDTO.accntTypeDesc","maPtBuyReqDetailDTO.accntType","ACCNT_TYPE",true);
    
	if(ckCreate(currentPageId)) goInput();

}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAPTPRITEM_ID');

	valSysDirCode('maPtBuyReqDetailDTO.partGrade', 'maPtBuyReqDetailDTO.partGradeDesc', 'PART_GRADE', true);
	
	// 요청자
	if(maPtBuyReqDetailForm.elements['maPtBuyReqDetailDTO.appReqById'].value==""){
		maPtBuyReqDetailForm.elements['maPtBuyReqDetailDTO.appReqById'].value     = "<%=user.getEmpId()%>";
		maPtBuyReqDetailForm.elements['maPtBuyReqDetailDTO.appReqByDesc'].value   = "<%=user.getEmpName()%>";
	}
	
	partNoAc.openLov();
	
	if(typeof exGoInput == "function") exGoInput();
}

function setSequenceVal(sequenceVal)
{
	maPtBuyReqDetailForm.elements['maPtBuyReqDetailDTO.ptPrItemId'].value = sequenceVal;
}

function goSave(){
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	if(parseInt(maPtBuyReqDetailForm.elements['maPtBuyReqDetailDTO.recQty'].value) <= 0 )
    {
    	alertMessage1("<bean:message key='LABEL.useQty'/>"+"<bean:message key='MESSAGE.MSG0013' />");
    	return;
    }
	
	if(ckCreate(currentPageId)) maPtBuyReqDetailForm.strutsAction.value = '<%=MaPtBuyReqDetailAction.BUY_ITEM_DETAIL_INPUT%>';
	else maPtBuyReqDetailForm.strutsAction.value = '<%= MaPtBuyReqDetailAction.BUY_ITEM_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/maPtBuyReqDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maPtBuyReqDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(maPtBuyReqDetailForm.elements['maPtBuyReqDetailDTO.ptPrItemId'].value);

    setTitle("maPtBuyReqDetailDTO.partDescSize");
    getTopPage().afterSaveAll(currentPageId);
}

function goTabPage(pageId)
{
	var form = document.maPtBuyReqDetailForm;

	goCommonTabPage(form, '' , pageId);
}


function sumTotPrice()
{
	var form = document.maPtBuyReqDetailForm;
	
	 var unitPrice = form.elements['maPtBuyReqDetailDTO.unitPrice'].value;
	 var recQty = form.elements['maPtBuyReqDetailDTO.recQty'].value;
	
	 var result = intToData(recQty) * intToData(unitPrice);
	 form.elements['maPtBuyReqDetailDTO.totalPrice'].value = result;
	 setMoneyFormat(form.elements['maPtBuyReqDetailDTO.totalPrice'], "3");
}

/* audit Trail */
function goAudtrailLink()
{
	var objectId = maPtBuyReqDetailForm.elements['maPtBuyReqDetailDTO.ptPrItemId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}


//-->
</script>

</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/maPtBuyReqDetail.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maPtBuyReqHdrCommonDTO.ptPrListId"/>
<html:hidden property="maPtBuyReqDetailDTO.ptPrItemId"/>
<html:hidden property="maPtBuyReqDetailDTO.partId"/>
<html:hidden property="maPtBuyReqDetailDTO.partGrade" />
<html:hidden property="maPtBuyReqDetailDTO.curr"/>
<html:hidden property="maPtBuyReqDetailDTO.partDescSize"/>
<html:hidden property="maPtBuyReqDetailDTO.appReqById"/>
<html:hidden property="maPtBuyReqDetailDTO.ptPnListId"/>
<html:hidden property="maPtBuyReqDetailDTO.accntId"/>
<html:hidden property="maPtBuyReqDetailDTO.ctCtrId"/>
<html:hidden property="maPtBuyReqDetailDTO.purGroup" />
<html:hidden property="maPtBuyReqDetailDTO.accntType" />
<html:hidden property="maPtBuyReqDetailDTO.poOnQty" />
<html:hidden property="maPtBuyReqDetailDTO.poQty" />
<html:hidden property="maPtBuyReqDetailDTO.grOnQty" />
<html:hidden property="maPtBuyReqDetailDTO.grQty" />
    <!-- searchbox 박스 Line -->
	<div class="article_box">
			<div class="form_box">
				<!-- 부품번호 -->
				<div class="field">
					<label><bean:message key="LABEL.partNo"/></label>
					<div class="input_box" id="partNo">
						<html:text property="maPtBuyReqDetailDTO.partNo" tabindex="10"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- ERP 코드 -->
				<div class="field">
					<label><bean:message key="LABEL.erpPartNo"/></label>
					<div class="input_read">
						<html:text property="maPtBuyReqDetailDTO.erpPartNo" tabindex="15" readonly="true" />
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.ptDesc"/></label>
					<div class="input_box" id="partDesc">
						<html:text property="maPtBuyReqDetailDTO.partDesc" tabindex="20"/>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.ptSize"/></label>
					<div class="input_box">
						<html:text property="maPtBuyReqDetailDTO.ptSize" tabindex="25"/>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.model"/></label>
					<div class="input_read">
						<html:text property="maPtBuyReqDetailDTO.model" tabindex="27" readonly="true"/>
					</div>
				</div>
				<div class="field">
					<label class="check"><bean:message key="LABEL.qty"/></label>
					<div class="input_box">
						<html:text property="maPtBuyReqDetailDTO.recQty" tabindex="30" 
                        onblur="javascript:sumTotPrice();"   styleClass="num"/>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.uom"/></label>
					<div class="input_box">
						<html:text property="maPtBuyReqDetailDTO.uomDesc" tabindex="40"/>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.unitPrice"/></label>
					<div class="input_box">
						<html:text property="maPtBuyReqDetailDTO.unitPrice" tabindex="50" 
                        onblur="javascript:sumTotPrice();" styleClass="num"/>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.price"/></label>
					<div class="input_read">
						<html:text property="maPtBuyReqDetailDTO.totalPrice" tabindex="55" styleClass="num" readonly="true"/>
					</div>
				</div>
				<div class="field">
					<label class="check"><bean:message key="LABEL.currency"/></label>
					<div class="input_box">
						<html:text property="maPtBuyReqDetailDTO.currDesc" tabindex="60"/>
						<p class="open_spop">
							<a>
							<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 요청자 -->
				<div class="field">
					<label><bean:message key="LABEL.appReqBy"/></label>
					<div class="input_box">
						<html:text property="maPtBuyReqDetailDTO.appReqByDesc" tabindex="65"/>
						<p class="open_spop">
							<a>
							<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<div class="field_long">
					<label><bean:message key="LABEL.remark"/></label>
					<div class="input_box">
						<html:textarea property="maPtBuyReqDetailDTO.remark" styleClass="ta350" tabindex="70" />
					</div>
				</div>
				<!-- 자재등급 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.partGrade"/></label>
					<div class="input_box">
						<html:text property="maPtBuyReqDetailDTO.partGradeDesc" tabindex="70" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- G/L계정 -->
				<div class="field">
					<label><bean:message key="LABEL.saknr"/></label>
					<div class="input_box">
						<html:text property="maPtBuyReqDetailDTO.accntDesc" tabindex="75" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 코스트센터 -->
				<div class="field">
					<label><bean:message key="LABEL.kostl"/></label>
					<div class="input_box">
						<html:text property="maPtBuyReqDetailDTO.ctCtrDesc" tabindex="80" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 구매그룹 -->
				<div class="field">
					<label><bean:message key="LABEL.buyGubun"/></label>
					<div class="input_box">
						<html:text property="maPtBuyReqDetailDTO.purGroupDesc" tabindex="90"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>								
				<!-- G/L계정범주 -->
				<div class="field">
					<label><bean:message key="LABEL.accntType"/></label>
					<div class="input_box">
						<html:text property="maPtBuyReqDetailDTO.accntTypeDesc" tabindex="100"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>	
				<!-- ERP PR번호 -->
				<div class="field">
					<label><bean:message key="LABEL.erpPrNo"/></label>
					<div class="input_read">
						<html:text property="maPtBuyReqDetailDTO.erpPrNo" tabindex="110" readonly="true" />
					</div>
				</div>
				<!-- ERP PR순번 -->
				<div class="field">
					<label><bean:message key="LABEL.erpPrSeq"/></label>
					<div class="input_read">
						<html:text property="maPtBuyReqDetailDTO.erpPrSeq" tabindex="120" readonly="true" />
					</div>
				</div>
				<!-- 요청일자 -->
				<div class="field">
	                <label><bean:message key="LABEL.appReqDate"/></label>
	                <div class="input_box">
	                    <html:text property="maPtBuyReqDetailDTO.reqComDate" tabindex="100" />
	                    <p class="open_calendar"><span>날짜</span></p>
	                </div>
            	</div>	
				<c:set var="filePath" value="enhance/${compName}/part/pur/buy/maPtBuyReqDetail_${compNo}.jsp" />
				<c:if test="${udf:isExist(filePath)}">
					<c:import charEncoding="UTF-8" url="/enhance/${compName}/part/pur/buy/maPtBuyReqDetail_${compNo}.jsp"></c:import>
				</c:if>
			</div>
	</div><!--article_box end-->
</html:form> 
</body>
</html>