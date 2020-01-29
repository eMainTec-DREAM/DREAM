<%--===========================================================================
page 가장 상단에 include하여 사용
ex.) c taglib 이용 하여 include 
     <c:import charEncoding="UTF-8"  url="/common/commonInclude.jsp"></c:import>
author  jung7126
version $Id: linkedMenu.jsp,v 1.1 2013/08/30 09:10:43 jung7126 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@page import="dream.asset.list.action.MaEqMstrDetailAction"%>
<%@ page import="dream.asset.list.action.MaEqMstrAsmbDetailAction" %>
<%@ page import="dream.part.list.action.MaPtMstrDetailAction" %>
<%@ page import="dream.org.vendor.action.MaVendorDetailAction" %>
<%@ page import="dream.work.list.action.MaWoResultMstrDetailAction" %>
<%@ page import="dream.req.work.action.MaWoReqDetailAction"%>
<%@ page import="dream.work.pmi.list.action.WorkPmiDetailAction"%>


<script language="javascript">
/*
 * ajax validation시 strutsAction을 지정할 필요가 있으면 
 * 이곳에 구현하여 ajaxValidation.js 에서 사용한다.
 */
var strutsEquipDetail  = '<%= MaEqMstrDetailAction.EQ_MSTR_DETAIL_INIT %>';
var strutsEqMstrAsmbDetail  = '<%=MaEqMstrAsmbDetailAction.EQ_MSTR_ASMB_DETAIL_INIT %>';
var strutsPartDetail  = '<%= MaPtMstrDetailAction.PTMSTR_DETAIL_INIT %>';
var strutsVendorDetail  = '<%= MaVendorDetailAction.VENDOR_DETAIL_INIT %>';
var strutsWoRsltDetail  = '<%= MaWoResultMstrDetailAction.WO_RESULT_DETAIL_INIT %>';
var strutsWoReqDetail  = '<%= MaWoReqDetailAction.DETAIL_INIT %>';
var strutsWoPmiDetail  = '<%= WorkPmiDetailAction.WORK_PMI_DETAIL_INIT %>';

</script>
<script src="<c:url value="/common/ajax/linkedMenu.js?ver=${jsVer}" />" ></script>