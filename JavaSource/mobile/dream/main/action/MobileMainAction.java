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
         * Main���� �Ѿ���� ���
         * ���޴����� Home�� �������, Login������ ����̴�.
         * ������� Login �Ǿ� �ִ� �����̰� User ������ ������ �ǰ�, main.jsp �θ� ����.
         */
        MobileMainService mainService = (MobileMainService) getBean("mobileMainService");
        
        HttpSession session = request.getSession();
        String jsonString = null;
        // base action���� User ��ü�� ���ٸ� login.jsp�� ������ �ȴ�.
        User loginUser = getUser(request);   
        
        // User�� ORG , ID
        String userGroup = loginUser.getUsrgrpId();
        String fName = "";
        
        //================================================================
        // ���� ����� Menu�� session�� �����Ѵ�.
        if (session.getAttribute("USERMENU") == null)
        {
            List userMenuList = mainService.findUserMenuList(loginUser);
            session.setAttribute("USERMENU", userMenuList);
        }
        //================================================================
        
        //================================================================
        // ���� ����� �׷��� ���ѿ� �ش��ϴ� Menu�� session�� �����Ѵ�.
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
//            setMenuLang(request, menuList);  // MENU�̸��� �ٱ��� ó���Ѵ�.
            session.setAttribute("MENU", menuList);
        }
        //================================================================

        //=================================================================
        // ���� ����� �׷��� ���ѿ� �ش��ϴ� ADMIN Menu�� session�� �����Ѵ�.
        if (session.getAttribute("ADMINMENU") == null)
        {
            String [][] adminMenu = mainService.findAdminMenu(userGroup,loginUser);
            setUserMenuLang(request, adminMenu);  // MENU�̸��� �ٱ��� ó���Ѵ�.
            session.setAttribute("ADMINMENU", adminMenu);
        }
        //==================================================================

        //================================================================
        // ���� ����� �׷��� ������ ���� Menu�� session�� �����Ѵ�.
        if (session.getAttribute("NO_MENU") == null)
        {
            List noMenuList = mainService.findNoMenuList(userGroup);
            session.setAttribute("NO_MENU", noMenuList);
        }
        //================================================================
        
        //================================================================
        // ���� ����� Linked Menu�� session�� �����Ѵ�.
        if (session.getAttribute("LINKEDMENU") == null)
        {
            String [][] linkedMenu = mainService.findLinkedMenu(loginUser);
            session.setAttribute("LINKEDMENU", linkedMenu);
        }
        
        
        //================================================================
        // ���� ����� Field ������ session�� �����Ѵ�.
        if (session.getAttribute("PAGEFIELD") == null)
        {
            Hashtable pageFields = mainService.findPageFields(loginUser);
            session.setAttribute("PAGEFIELD", pageFields);
        }

        if(session.getAttribute("MAINLIST") == null)
        {
        	List mainList = mainService.findMainList(loginUser);
        	Map result = CommonUtil.makeResultJson(mainList); //�ƴϸ� �׳� 
            
            Gson gson = new Gson();

            String jStr = gson.toJson(result);
            jStr = jStr.replaceAll("\"ID\"", "id");

            session.setAttribute("MAINLIST", jStr);
        }
        
        //Locale ����
        DataBaseMessageResources dataBaseMessageResources = (DataBaseMessageResources)getResources(request);
        dataBaseMessageResources.loadLocale(getUser(request).getLangId());
            
        //================================================================
        
        //================================
        // ���ʿ��� session�� invalid ��Ų��.
        SessionTable.arrangeSesstion();
        //================================ 

        MobileMainForm mainForm = (MobileMainForm) form;
        
        //���ҽ� �缼��
        getCommonMessages(request, true);
        
        //Forwarding Page ���� Ȯ��
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
     * userMenu�� menu_id �� �ش� �ϴ� menu �̸��� �����Ѵ�.
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