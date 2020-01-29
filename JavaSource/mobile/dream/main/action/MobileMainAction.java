package mobile.dream.main.action;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;

import common.bean.User;
import common.message.DataBaseMessageResources;
import common.session.SessionTable;
import common.struts.AuthAction;
import common.struts.MobileAuthAction;
import common.util.CommonUtil;
import mobile.dream.main.form.MobileMainForm;
import mobile.dream.main.service.MobileMainService;


/**
 * Main Page
 * @author  javaworker
 * @version $Id: MainAction.java,v 1.6 2014/02/17 06:06:56 javaworker Exp $
 * @since   1.0
 * 
 * @struts:action path="/mobileMain" name="mobileMainForm"
 *                input="/mobile/dream/main/mobileMain.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="success" path="/mobile/dream/main/mobileMain.jsp"
 *                        redirect="false"
 */
public class MobileMainAction extends MobileAuthAction
{
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        /*
         * Main으로 넘어오는 경우
         * 각메뉴에서 Home을 누른경우, Login성공한 경우이다.
         * 어느경우든 Login 되어 있는 상태이고 User 정보만 있으면 되고, main.jsp 로만 간다.
         */
        MobileMainService mainService = (MobileMainService) getBean("mobileMainService");
        
        HttpSession session = request.getSession();
        String jsonString = null;
        // base action에서 User 객체가 없다면 login.jsp로 보내게 된다.
        User loginUser = getUser(request);   
        
        // User의 ORG , ID
        String userGroup = loginUser.getUsrgrpId();
        String fName = "";
        
        //================================================================
        // 현재 사용자 Menu를 session에 셋팅한다.
        if (session.getAttribute("USERMENU") == null)
        {
            List userMenuList = mainService.findUserMenuList(loginUser);
            session.setAttribute("USERMENU", userMenuList);
        }
        //================================================================
        
        //================================================================
        // 현재 사용자 그룹의 권한에 해당하는 Menu를 session에 셋팅한다.
        if (session.getAttribute("MENU") == null)
        {
            ArrayList menuList = mainService.findMenuList(userGroup,loginUser);
            
            Map menuJsonMap = mainService.findMenuJsonList(userGroup,loginUser);
            
            ArrayList menuJsonList = (ArrayList)menuJsonMap.get("ITEM");
            
            List result = CommonUtil.makeTreeResultJson(menuJsonList);
            
            menuJsonMap.put("ITEM", result);
            
            Gson gson = new Gson();
            
            jsonString = gson.toJson(menuJsonMap);
            
            session.setAttribute("JROWMENU", jsonString);
            jsonString = jsonString.replaceAll("\"ID\"", "id");
            jsonString = jsonString.replaceAll("\"OPEN\"", "open");
            jsonString = jsonString.replaceAll("\"false\"", "false");
            jsonString = jsonString.replaceAll("\"true\"", "true");
            jsonString = jsonString.replaceAll("\"TEXT\"", "text");
            jsonString = jsonString.replaceAll("\"userdata\"", "userdata");

            session.setAttribute("JSONMENU", jsonString);
//            setMenuLang(request, menuList);  // MENU이름의 다국어 처리한다.
            session.setAttribute("MENU", menuList);
        }
        //================================================================

        //=================================================================
        // 현재 사용자 그룹의 권한에 해당하는 ADMIN Menu를 session에 셋팅한다.
        if (session.getAttribute("ADMINMENU") == null)
        {
            String [][] adminMenu = mainService.findAdminMenu(userGroup,loginUser);
            setUserMenuLang(request, adminMenu);  // MENU이름의 다국어 처리한다.
            session.setAttribute("ADMINMENU", adminMenu);
        }
        //==================================================================

        //================================================================
        // 현재 사용자 그룹의 권한이 없는 Menu를 session에 셋팅한다.
        if (session.getAttribute("NO_MENU") == null)
        {
            List noMenuList = mainService.findNoMenuList(userGroup);
            session.setAttribute("NO_MENU", noMenuList);
        }
        //================================================================
        
        //================================================================
        // 현재 사용자 Linked Menu를 session에 셋팅한다.
        if (session.getAttribute("LINKEDMENU") == null)
        {
            String [][] linkedMenu = mainService.findLinkedMenu(loginUser);
            session.setAttribute("LINKEDMENU", linkedMenu);
        }
        
        
        //================================================================
        // 현재 사용자 Field 정보를 session에 셋팅한다.
        if (session.getAttribute("PAGEFIELD") == null)
        {
            Hashtable pageFields = mainService.findPageFields(loginUser);
            session.setAttribute("PAGEFIELD", pageFields);
        }

        if(session.getAttribute("MAINLIST") == null)
        {
        	List mainList = mainService.findMainList(loginUser);
        	Map result = CommonUtil.makeResultJson(mainList); //아니면 그냥 
            
            Gson gson = new Gson();

            String jStr = gson.toJson(result);
            jStr = jStr.replaceAll("\"ID\"", "id");

            session.setAttribute("MAINLIST", jStr);
        }
        
        //Locale 설정
        DataBaseMessageResources dataBaseMessageResources = (DataBaseMessageResources)getResources(request);
        dataBaseMessageResources.loadLocale(getUser(request).getLangId());
            
        //================================================================
        
        //================================
        // 불필요한 session을 invalid 시킨다.
        SessionTable.arrangeSesstion();
        //================================ 

        MobileMainForm mainForm = (MobileMainForm) form;
        
        //리소스 재세팅
        getCommonMessages(request, true);
        
        //Forwarding Page 권한 확인
        String initFileName = getUser(request).getFileName();

        boolean isAuthed = false;
        String[][] adminMenuList = (String[][])session.getAttribute("ADMINMENU");
        for (int i = 0; i < adminMenuList.length; i++)
        {
            String[] menu = adminMenuList[i];
           // menu[1] : fileName, menu[2] :  menu_id, menu[3] :ord no , menu[4] : desc
            if(menu[1].equals(initFileName)){
            	isAuthed = true;
                break;
            }
        }
        
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObj = (JSONObject)jsonParser.parse((String)session.getAttribute("JROWMENU"));

        JSONArray jsonArray = (JSONArray) jsonObj.get("ITEM");
    	for(int n = 0; n < jsonArray.size(); n++)
        {
        	JSONObject object = (JSONObject)jsonArray.get(n);
        	
        	if(!object.containsValue(initFileName))isAuthed = findSubList((JSONArray)object.get("ITEM"), initFileName);
        	else isAuthed = true;
        }

    	if(!isAuthed || "null".equals(initFileName)) initFileName = "maMyInfo";

//        ActionForward forward = new ActionForward("/"+initFileName+".do");
        
        return mapping.findForward("success");

//        return forward;
    }
    
    public boolean findSubList(JSONArray jsonArray, String fileName)
    {
    	boolean isAuthed = false;
    	if(jsonArray == null) return isAuthed;
    	for(int n = 0; n < jsonArray.size(); n++)
        {
        	JSONObject object = (JSONObject)jsonArray.get(n);

        	if(!object.containsValue(fileName) && object.containsKey("ITEM")) return findSubList((JSONArray)object.get("item"), fileName);
        	else isAuthed = true;
        }

    	return isAuthed;
    }

    
    /**
     * userMenu의 menu_id 에 해당 하는 menu 이름을 셋팅한다.
     * @author  javaworker
     * @version $Id: MainAction.java,v 1.6 2014/02/17 06:06:56 javaworker Exp $
     * @since   1.0
     * 
     * @param request
     * @param userMenu[] : 0(name), 1(page id), 2(menu_id)
     * @throws Exception
     */
    private void setUserMenuLang(HttpServletRequest request, String [][] userMenu) throws Exception
    {
        
        // userMenu[] : 0(name), 1(page id), 2(menu_id)
        java.util.Locale locale = getUser(request).getLocale();
        org.apache.struts.util.MessageResources messages = getResources(request);
        
        for (int i=0, length=userMenu.length; i<length; i++)
        {
            userMenu[i][0] = userMenu[i][4];
        }
    }
}