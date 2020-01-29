<%--===========================================================================
작업요청서(투자요청)- 처리사항 (상세)
author  js.lee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.req.work.action.ReqInvWorkResDetailAction"%>
<%@ page import="common.bean.User"%>
<html:html>
<head>
<title><bean:message key='LABEL.woReqNo' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<script language="javascript">


function loadPage() 
{
	
}
	
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/reqInvWorkResDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="reqWorkResListDTO.invtlistId" />
	<html:hidden property="reqInvWorkResDetailDTO.invtlistId" />

	<div class="article_box">
		<div class="form_box">
			<!-- 투자계획# -->
			<div class="field">
				<label><bean:message key="LABEL.invtlistNo"/></label>
				<div class="input_read">
					<html:text property="reqInvWorkResDetailDTO.invtlistNo" tabindex="10" readonly="true"/>
				</div>
			</div>
			<!-- 상태 -->
			<div class="field">
				<label><bean:message key="LABEL.status"/></label>
				<div class="input_read">
					<html:text property="reqInvWorkResDetailDTO.invtlistStatusDesc" tabindex="20"  readonly="true"/>
				</div>
			</div>
			<!-- 투자명 -->
			<div class="field_long">
				<label><bean:message key="LABEL.eqInvtDesc"/></label>
				<div class="input_read">
					<html:text property="reqInvWorkResDetailDTO.description" tabindex="30"/>
				</div>
			</div>
			<!-- 투자구분 -->
            <div class="field">
                <label><bean:message key="LABEL.invtCategDesc"/></label>
                <div class="input_read">
                    <html:text property="reqInvWorkResDetailDTO.invtCategDesc" tabindex="40"/>
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                </div>
            </div>
			<!-- 구매절차 -->
            <div class="field">
                <label><bean:message key="LABEL.invtDesc"/></label>
                <div class="input_read">
                    <html:text property="reqInvWorkResDetailDTO.invtDesc" tabindex="50" />
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                </div>
            </div>
			<!-- 설비 -->
			<div id ='equipDiv' class="field">
				<label><bean:message key="LABEL.equipDesc"/></label>
				<div class="input_read">
					<html:text property="reqInvWorkResDetailDTO.equipDesc" tabindex="50" />
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 위치 -->
			<div id ='equipLocDiv' class="field">
				<label><bean:message key="LABEL.eqLocDesc"/></label>
				<div class="input_read">
					<html:text property="reqInvWorkResDetailDTO.eqLocDesc" tabindex="50" />
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 종류 -->
			<div id ='equipCtgDiv' class="field">
				<label><bean:message key="LABEL.eqCtg"/></label>
				<div class="input_read">
					<html:text property="reqInvWorkResDetailDTO.eqctgDesc" tabindex="50" />
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 투자부서 -->
			<div class="field">
				<label><bean:message key="LABEL.invtDept"/></label>
				<div class="input_read">
					<html:text property="reqInvWorkResDetailDTO.deptDesc" tabindex="50" />
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 담당자 -->
			<div class="field">
				<label><bean:message key="LABEL.manager"/></label>
				<div class="input_read">
					<html:text property="reqInvWorkResDetailDTO.empDesc" tabindex="50" />
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 투자시기 -->
			<div class="field">
				<label><bean:message key="LABEL.invtSDate"/></label>
					<div class="input_read input_carendar">
						<html:text property="reqInvWorkResDetailDTO.planSdate" tabindex="50" />
						<p class="open_calendar">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				
			</div>
			<!-- 투자금액 -->
			<div class="field">
				<label><bean:message key="LABEL.invtAmt"/></label>
				<div class="input_read">
					<html:text property="reqInvWorkResDetailDTO.invtAmt" tabindex="50" styleClass="num" readonly="true"/>
				</div>
			</div>
			<!-- 공장(Plant) -->
			<div class="field">
				<label><bean:message key="LABEL.plant"/></label>
				<div class="input_read">
					<html:text property="reqInvWorkResDetailDTO.plantDesc" tabindex="110"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_read">
					<html:textarea property="reqInvWorkResDetailDTO.remark" styleClass="ta50" tabindex="250" />
				</div>
			</div>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>