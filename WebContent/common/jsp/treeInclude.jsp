<%--===========================================================================
Tab 을 포함한 Tree
author  ssong
version $Id: treeInclude.jsp,v 1.11 2014/02/13 08:12:53 javaworker Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ page import="common.tree.action.TreeAction " %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<script src="<c:url value="/common/tree/tabTree.js" />" ></script>
<%
	String resizeBar = request.getParameter("RESIZE");
%>
<script language="javascript">
<!--//

var defaultTabInfoArray = new Array();

defaultTabInfoArray[0] = ['<%=TreeAction.LOCATTREE%>', '<bean:message key="TREE.LOCATION"/>']; // 기능위치
defaultTabInfoArray[1] = ['<%=TreeAction.EQTYPTREE%>', '<bean:message key="TREE.EQ_TYPE"/>']; // 설비종류
defaultTabInfoArray[2] = ['<%=TreeAction.LOC_CODE_TREE%>', '<bean:message key="TREE.LOC_CODE"/>']; // 위치
defaultTabInfoArray[3] = ['<%=TreeAction.DEPT_TREE%>', '<bean:message key="TREE.DEPART"/>']; // 부서

/**
 * Tree 의 Tab을 구성한다.
 */
function setTabInfo(_tabInfoArray, _init_tab_index)
{
    if (!_tabInfoArray) return;
    
    var tabLocDiv = M$('tabLocDiv');
    
    var tabUlObj = document.createElement("ul");
    
    var initTabFunc = "";
    
    for (var i=0; i<_tabInfoArray.length; i++)
    {
        var treeDesc = _tabInfoArray[i][1];

        var tabObj = document.createElement("li");
        var tabFunc = "callClickTreeTab(" + (i+1) + ");";
        var tabClass = i==0?"stab_on_txt":"stab_off_txt";
       
        tabObj.innerHTML = "<a href=\"javaScript:" + tabFunc + "\"><span class=\"" + tabClass + "\" id=\"branch\"><span class=\"text\">" +
                               treeDesc + "</span></span></a>";
        tabUlObj.appendChild(tabObj);
        if (!_init_tab_index) _init_tab_index = 1;
        if ((i+1)==_init_tab_index) initTabFunc = tabFunc;
    }

    tabLocDiv.appendChild(tabUlObj);
    defaultTabInfoArray = _tabInfoArray;
    if (_init_tab_index != 99) eval(initTabFunc);
}

function callClickTreeTab(_tabIndex)
{	
	if (typeof (beforeClickTreeTab)=="function") 
	{
		// Tab 클릭되기 전에 check
		if (beforeClickTreeTab(defaultTabInfoArray[_tabIndex-1][0]) == false) return;;
	}
	
    initTreeTab(_tabIndex);
    treeDoc =  document.getElementById('treeFrame').contentWindow;
    treeDoc.changeTree(defaultTabInfoArray[_tabIndex-1][0], defaultTabInfoArray[_tabIndex-1][1]);
    
    if (typeof (clickTreeTab)=="function") clickTreeTab(defaultTabInfoArray[_tabIndex-1][0]);
}

/**
 * Tree Popup
 */
function goPoptree()
{
	var url   = contextPath + "/common/tree/popupTree2.jsp";

    // pop up이 중앙에 위치하게 한다.
    var popHeight = 500;
    var popWidth  = 400;
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    window.open(url, "LOV_TREE", "toolbar=no,resizable=yes,scrollbars=no,width="+popWidth+",height="+popHeight+",top="+TopPosition+",left="+LeftPosition);
}

/*
 * Remove Popup Button
 */
function remPbtn()
{
	jQuery("img[alt='Popup']").css("display", "none");
}

//-->
</script>
<%	if("NONE".equals(resizeBar)){%>
<div id="resizeTree" style="float: left; width: 100%; height: 100%; margin:0px;" class="detailTree">
<%
	}else
	{%>
<div id="resizeTree" style="float: left; width: 100%; height: 100%; margin:0px;">
<%  } %>
    <table id="tree_table" style="margin:0px; padding:0px;  width: 100%; height: 100%;" cellpadding="0" cellspacing="0">
        <tr style="margin: 0px;">
            <td style="height:100%; margin: 0px; padding: 0px;" valign="top">
                <!-- Tree Box Header --> 
                <!-- Tree Box Contents -->
                <div id="tree_middle"  style="height:100%; margin: 0px; padding: 0px;">
                    <!-- tree 탭 -->
                    <div class="stab_m_area" id="tabLocDiv"><div id="tPopupButton" style="float: right; padding-top: 8px;" ><img src="<c:url value="/common/images/button/pop_b.gif"/>" alt="Popup" onclick="goPoptree();" style="cursor: pointer; display: block;" /></div></div>
                    <!-- Data 영역 -->
                    <div class="tree_cont" style="margin-top: 0px; margin-right: 0px;">
                        <div id="tree_contents" style="margin-right: 0px; width: 100%; overflow: auto; overflow-y: hidden;">
                          <c:import charEncoding="UTF-8" url="/common/tree/masterTree.jsp"></c:import>
                        </div>
                    </div>
                </div> 
                <!-- Tree Box Bottom -->
                <div id="tree_bottom" class="tree_box_b">
                  <div class="b_left"></div>
                  <div class="b_right"></div>
                </div>
            </td>
<%if(!"NONE".equals(resizeBar)){%>
            <td width="10px;"   style="background:url('<c:url value='/common/images/sub_b_type/bg_stabs.gif'/>') repeat-x left top;">
            </td>
            <td style="cursor: e-resize;" width="10px;" height="100%" onmousedown="treeMoveStart(event);">
                <!-- Resize Bar -->
                <table width="10" height="100%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td style="background:url(<c:url value="/common/images/sub_b_type/img_diveder.gif"/>); background-size:100% 100%;" ondblclick="treeDbClick()">&nbsp;</td>
                    </tr>
                </table>
            </td>
<%}%>
        </tr>
    </table>
</div>
<script>

setTimeout("treeResize();", 200);

function treeResize()
{
	/**
	 * Tree의 높이를 조정한다. 
	 * FireFox 의 경우 height=100% 줄 경우 문제 발생. 따라서 특정 Object에 px 로 높이를 지정해 준다. 
	 * ie8, firefox, crome, safari 테스트완료.
	 */
	var treeMiddleObj = document.getElementById("tree_middle"); 
	var treeBottomObj = document.getElementById("tree_bottom"); 
	var treeContentsObj = document.getElementById("tree_contents"); // iframe이 들어가는 div object

	/* 높이 계산시 Tree div의 Header부분(class=tree_box_t)의 높이(44px) 만큼 빼준다.
	 * firefox를 위해서...  
	 */
	var iframeHeight = parseInt(treeBottomObj.offsetTop) - parseInt(treeMiddleObj.offsetTop) -44;

	treeMiddleObj.style.height = iframeHeight + "px";
	treeContentsObj.style.height =  (iframeHeight -10) + "px"; // 마진값 10px로 한다. 
}

</script>