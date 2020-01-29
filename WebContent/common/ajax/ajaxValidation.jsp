<%--===========================================================================
page 가장 상단에 include하여 사용
ex.) c taglib 이용 하여 include 
     <c:import charEncoding="UTF-8"  url="/common/commonInclude.jsp"></c:import>
author  javaworker
version $Id: ajaxValidation.jsp,v 1.1 2013/08/30 09:10:43 javaworker Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@page import="common.finder.valid.action.ValidationAction"%>
<%@page import="dream.pers.appreq.action.AppReqDetailAction"%>
<%@page import="dream.comm.action.MaFinderAcAction"%>
<%@page import="dream.comm.action.DateAction"%>
<script language="javascript">
/*
 * ajax validation시 strutsAction을 지정할 필요가 있으면 
 * 이곳에 구현하여 ajaxValidation.js 에서 사용한다.
 */
var strutsActionFindUserVal  = '<%//common.finder.action.FindDeptLoginAction.FIND_USER_VAL%>';
var strutsActionSeqNextVal = '<%=ValidationAction.VAL_NEXTVAL%>';		// sequence nextval
var strutsActionNoNextVal = '<%=ValidationAction.VAL_NO_NEXTVAL%>';		// no nextval(다음 No가져오기)

var strutsActionFindUsrDirDesc = '<%=ValidationAction.VAL_USR_DIR_DESC%>';	// TACDUSR테이블
var strutsActionFindSysDirDesc = '<%=ValidationAction.VAL_SYS_DIR_DESC%>';	// TACDSYS테이블
var strutsActionFindSysDirCode = '<%=ValidationAction.VAL_SYS_DIR_CODE%>';	// TACDSYS테이블
var strutsActionFindUsrDirCode = '<%=ValidationAction.VAL_USR_DIR_CODE%>';	// TACDUSR테이블 
var strutsActionFindSysDirId = '<%=ValidationAction.VAL_SYS_DIR_ID%>';	// TACDSYS테이블(ID TO DESC)
var strutsActionFindUsrDirId = '<%=ValidationAction.VAL_USR_DIR_ID%>';	// TACDUSR테이블 (ID TO DESC)
var strutsActionFindTableDesc = '<%=ValidationAction.VAL_TABLE_DESC%>';	// 기타 테이블 검색(description으로 code검색)
var strutsActionFileAttach = '<%=ValidationAction.VAL_FILE_ATTACH%>';	// 첨부파일조회
var strutsActionApproval = '<%=AppReqDetailAction.APP_REQ_INIT%>';	// APPROVAL

var strutsActionAutoComplete = '<%=MaFinderAcAction.AC_DESC%>';	// AutoComplete
var strutsActionVal = '<%=MaFinderAcAction.VA_CHECK%>';	// Validation Common
var strutsActionValCustom = '<%=MaFinderAcAction.AC_CUSTOM%>';	// Validation Custom

var strutsActionGetTimeStamp = '<%=DateAction.GET_TIME_STAMP%>';	// TimeStamp
var strutsActionGetTimeGap = '<%=DateAction.GET_TIME_GAP%>';	// Time Gap
</script>
<script src="<c:url value="/common/ajax/ajaxCommon.js?ver=${jsVer}" />" ></script>
<script src="<c:url value="/common/ajax/ajaxValidation.js?ver=${jsVer}" />" ></script>