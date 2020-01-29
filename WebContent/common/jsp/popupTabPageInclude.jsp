<%--===========================================================================
popup Tab page
author  ssong
version $Id: popupTabPageInclude.jsp,v 1.1 2013/08/30 09:09:08 javaworker Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/c-rt.tld" prefix="c-rt"%>
<c:set var="currentPageId" value="TAB.${currentPageId}" />

            <div class="clear"></div>
            
            <c:if test="${not empty tabPage}"> 
            <c-rt:set var='pageLength' value='<%=((String [])request.getAttribute("tabPage")).length%>'></c-rt:set>
            <c:set var="lastSelect" value="false" /><!-- 바로전 tab이 선택되어졌는지 확인 -->
            <div class="tab_main_m_area">
                <div class="tab_main_l_area"></div>
                <ul>
                    <c:forEach items="${tabPage}" var="page" varStatus="i">
                        <c:choose>
                            <c:when test="${currentPageId == page}"><!-- 선택 tab background -->
                                <c-rt:set var="currentTabNum" value="${i.count}" scope="page"></c-rt:set>
                                <c:catch var="e1"><li><span class="tab_main_on_txt"><span class="text"><bean:message key="${page}" /></span></span></li></c:catch>
                                <c:if test="${e1!=null}"><c:out value="${e1}" /></c:if>                            
                            </c:when>
                            <c:when test="${currentTabNum + 1 == i.count}"><!-- 선택 tab background -->
                                <c:catch var="e1"><li><a href="javascript:callGoTabPage('<c:out value="${page}" />', '<c:out value="${i.count}" />');" onFocus='this.blur();'><span class="tab_main_off_txt"><span class="text"><bean:message key="${page}" /></span></span></a></li></c:catch>
                                <c:if test="${e1!=null}"><c:out value="${e1}" /></c:if>
                            </c:when>
                            <c:otherwise>
                                <c:catch var="e1"><li class="bar"><a href="javascript:callGoTabPage('<c:out value="${page}" />', '<c:out value="${i.count}" />');" onFocus='this.blur();'><span class="tab_main_off_txt_bar"><span class="text"><bean:message key="${page}" /></span></span></a></li></c:catch> 
                                <c:if test="${e1!=null}"><c:out value="${e1}" /></c:if>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </ul>
                <div class="tab_main_r_area"></div>
            </div>
            </c:if>
            <c:if test="${empty tabPage}">
            <div>
                <table width="100%" border="0" cellpadding="0" cellspacing="0" style="margin:0 auto;">
                    <tr>
                        <td width="7" height="7"><img src="<c:url value="/common/images/sub/tab_no01.gif"/>"></td>
                        <td height="7" style="background:url('<c:url value='/common/images/sub/tab_nobg.gif'/>') repeat-x left top; width:100%;"></td>
                        <td width="7" height="7"><img src="<c:url value="/common/images/sub/tab_no02.gif"/>"></td>
                    </tr>
                    <tr>
                        <td height="5"></td>
                    </tr>
                </table>
            </div>
            </c:if>

            <div class="clear"></div>