<%--===========================================================================
작업결과(예방작업 - 교체) 투입자재
author  kim21017
version $Id: maWoResultPmRplPartDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.work.list.action.MaWoResultPartDetailAction"%>
<%@ page import="common.bean.User"%>
<%@ page import="common.bean.MwareConfig"%>

<c:import charEncoding="UTF-8" url="/dream/work/list/bm/maWoResultBmRplPartDetail.jsp"></c:import>