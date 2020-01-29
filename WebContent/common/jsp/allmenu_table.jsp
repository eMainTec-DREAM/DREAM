<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Hashtable"%>
<%@ page import="common.util.CommonUtil"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/c-rt.tld" prefix="c-rt"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<html>
<head>
<link rel="stylesheet" href="<c:url value="/common/css/common.css"/>" type="text/css"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script language="javascript">
function goMenuPageAll(pageId)
{
	parent.goMenuPage(pageId);
}
</script>
<style type="text/css">
<!--
body {
	z-index : 99;
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.table_ct {border-bottom: 1px solid #e2e2e2; padding-top:12px; padding-bottom: 12px; padding-left:20px;}

-->
</style></head>

<body>
<table width="945" border="0" cellspacing="0" cellpadding="0">
  <%
String [] menuPath = (String [])request.getAttribute("menuPath");
String pMenuId = "";
String menuId = "";
if(menuPath != null)
{
    pMenuId = menuPath[0];
    menuId = menuPath[1];
}

String scrollTopValue = request.getParameter("menuScrollTop");
%>
  <%
    Object object = session.getAttribute("MENU");

    // 저장되는 ArrayList이다.
    ArrayList menuList = null;

    if (object != null)
    {           
        menuList = (ArrayList)object;        
    }
%>
  <%
int currentMenuIndex = 0;
int currentSubMenuIndex = 0;
int mainMenuCount = 0;
// 넘겨진 Menu가 0 보다 큰경우
if (menuList != null && menuList.size() > 0)
{
    // 0 번째가 메인 메뉴이다.
    Hashtable mainHash = (Hashtable)menuList.get(0);
    
    // menu[0][0] : 보여지는 이름, menu[0][1] : Menu Id
    String [][] mainMenu = (String [][])mainHash.get("menu");

    String [][] tempSubMenu = null;
    int tempSubMenuCount = 0;
    
    // Main Menu의 갯수
    mainMenuCount = mainMenu.length;
    
    String depth2ClassName = "depth2";
    // Main Menu를 구성한다.
    for (int i=0; i < mainMenuCount; i++)
    {
        // 해당 메뉴의 sub Menu를 ArrayList에서 찾아서 셋팅한다.
        //tempSubMenu = CommonUtil.getSubMenu(menuList, mainMenu[i][2]);
         
        // 해당 서브 메뉴의 갯수를 계산한다.
        tempSubMenuCount = tempSubMenu.length;
        
        // 현재 페이지 MENU_ID 와 같다면
        if (mainMenu[i][2].equals(pMenuId))
        {
            currentMenuIndex = i+1;     
        }
%>
		<tr>
			<td width="192" align="left" valign="middle" bgcolor="#efeeef" class="txt_black12b"
				 style="border-bottom: 1px solid #e2e2e2; padding-left:12px;">
				 <img src="<c:url value="/common/images/sub_a_type/img_allmenu_arrow.gif"/>" width="4" height="7" align="absmiddle" style="margin-right: 5px;"/><c-rt:set value='<%=mainMenu[i][2]%>' var='mainMenuId'></c-rt:set><bean:message key='MENU.${mainMenuId}' />
			</td>
			<td width="751" class="table_ct" style="border-bottom: 1px solid #e2e2e2;">
				<table width="725" border="0" cellspacing="0" cellpadding="0">
                
	<%		// Sub Menu를 구성한다.
			for (int j=0; j<tempSubMenuCount; j++)
			{	
			    // 현재 페이지의 P_MENU_ID 경로를 표시한다.
			    if (tempSubMenu[j][2].equals(menuId))
			    {
			        depth2ClassName = "depth2_on";
			        currentSubMenuIndex = j;
			    }
			    else if(j+1 == tempSubMenuCount)
			    {
			        depth2ClassName = "depth2 b_line";
			    }
			    else
			    {
			        depth2ClassName = "depth2";
			    }
	%>
	<%	
		//하나의 tr당 3개씩 보여주기위해 사용
		int q = j+7;
		int w = j+1;
		
		//0이거나 4,7,9... 번째인경우 tr을 실행한다.
		if(j%3==0||j==0)
	{	
   	%>
   					<tr>
   	<% 
	}
    %>
						<td width="248" class="txt_black12">
						<c-rt:set value='<%=tempSubMenu[j][2]%>' var='subMenuId'></c-rt:set><a href="javascript:goMenuPageAll('<%=tempSubMenu[j][1]%>');" onfocus="this.blur();"><bean:message key='MENU.${subMenuId}' /></a>
						</td>
	<%	
	//tr 을 닫기위해 3의 배수인경우 /tr 을 실행한다.
	if(q%3==0)
	{
   	%>
   					</tr>
   	<% 
	}
    %>
    
    <%	
    //TR 이 1줄인경우 1개의 td 를 더 생성하여 줄을 맞춰준다.
    if(w==tempSubMenuCount&& w<3)
	{
   	%>
   					<td width="248" class="txt_black12"></td>
   					</tr>
   	<% 
	}
    %>
	<%
	         } // Sub Menu 구성 for
	%>		  	
				</table>
			</td>
		</tr>
<%
    } // Main Menu 구성 for
}
%>    
</table>

</body>
</html>
