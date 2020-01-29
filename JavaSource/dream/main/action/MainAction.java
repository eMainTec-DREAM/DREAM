package dream.main.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import common.bean.User;
import common.session.SessionTable;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.main.form.MainForm;
import dream.main.service.MainService;

/**
 * Main Page
 * @author  javaworker
 * @version $Id: MainAction.java,v 1.6 2014/02/17 06:06:56 javaworker Exp $
 * @since   1.0
 * 
 * @struts:action path="/main" name="mainForm"
 *                input="/dream/index.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="success" path="/dream/main/main.jsp"
 *                        redirect="false"
 * @struts.action-forward name="findUser"
 *                        path="/mainAll.do" redirect="false"
 */
public class MainAction extends AuthAction
{
	private static final Logger logger = Logger.getLogger(AuthAction.class);
//	public static final String storageConnectionString =
//	        "DefaultEndpointsProtocol=https;"
//	                + "AccountName=dreamdiag;"
//	                + "AccountKey=DefaultEndpointsProtocol=https;AccountName=dreamdiag;AccountKey=K+IzkSMaAqFpPLSyLyVbLbOj+uOEaKLF2fZcSV7B0hLrQgAG0enzonhc+DY2xrj4FV3WmvtoTwsYt3xOTy6YhQ==;EndpointSuffix=core.windows.net";

    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {       
        
//        try {
//            CloudStorageAccount account = CloudStorageAccount.parse(storageConnectionString);
//            CloudBlobClient serviceClient = account.createCloudBlobClient();
//
//            // Container name must be lower case.
//            CloudBlobContainer container = serviceClient.getContainerReference("myimages");
//            container.createIfNotExists();
//
//            // Upload an image file.
//            CloudBlockBlob blob = container.getBlockBlobReference("image1.jpg");
//            File sourceFile = new File("c:\\image1.jpg");
//            blob.upload(new FileInputStream(sourceFile), sourceFile.length());
//
//
//            // Download the image file.
//            File destinationFile = new File(sourceFile.getParentFile(), "image1Download.tmp");
//            
//            System.out.println("@@@@@@"+destinationFile.getAbsolutePath());
//            blob.downloadToFile(destinationFile.getAbsolutePath());
//        }
//        catch (FileNotFoundException fileNotFoundException) {
//            System.out.print("FileNotFoundException encountered: ");
//            System.out.println(fileNotFoundException.getMessage());
//            System.exit(-1);
//        }
//        catch (StorageException storageException) {
//            System.out.print("StorageException encountered: ");
//            System.out.println(storageException.getMessage());
//            System.exit(-1);
//        }
//        catch (Exception e) {
//            System.out.print("Exception encountered: ");
//            System.out.println(e.getMessage());
//            System.exit(-1);
//        }

        
        /*fr
         * Main���� �Ѿ���� ���
         * ���޴����� Home�� �������, Login������ ����̴�.
         * ������� Login �Ǿ� �ִ� �����̰� User ������ ������ �ǰ�, main.jsp �θ� ����.
         */
        MainService mainService = (MainService) getBean("mainService");
        
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

        //================================================================
        // ���� ������� Filter ���������� ������ session�� �����Ѵ�.
        if (session.getAttribute("FILTERVALUE") == null)
        {
            Hashtable filterValue = mainService.findFilterValue(loginUser);
            session.setAttribute("FILTERVALUE", filterValue);
        }

        //Locale ����
        //DataBaseMessageResources dataBaseMessageResources = (DataBaseMessageResources)getResources(request);
        //dataBaseMessageResources.loadLocale(getUser(request).getLangId());
            
        //================================================================
        
        //================================
        // ���ʿ��� session�� invalid ��Ų��.
        SessionTable.arrangeSesstion();
        //================================ 

        MainForm mainForm = (MainForm) form;
        
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
        
        ArrayList<Map> authedMenuList = (ArrayList<Map>)session.getAttribute("MENU");
        for(Map menu : authedMenuList)
        {
        	if(isAuthed) break;
        	
        	if(initFileName.equals(menu.get("PAGEID")))isAuthed = true;
        }
        
        
        /*JSONParser jsonParser = new JSONParser();
        JSONObject jsonObj = (JSONObject)jsonParser.parse((String)session.getAttribute("JROWMENU"));

        JSONArray jsonArray = (JSONArray) jsonObj.get("ITEM");
    	for(int n = 0; n < jsonArray.size(); n++)
        {
        	JSONObject object = (JSONObject)jsonArray.get(n);
        	if(isAuthed) break;
        	
        	if(!initFileName.equals(object.get("PAGEID")))isAuthed = findSubList((JSONArray)object.get("ITEM"), initFileName);
        	else isAuthed = true;
        }*/

        String rdParam = "";
        if(!CommonUtil.isNullCheck(session.getAttribute("redirectPage")))
        {
            initFileName = String.valueOf(session.getAttribute("redirectPage"));
            if(!CommonUtil.isNullCheck(session.getAttribute("redirectParam")))
            {
                String redParam = String.valueOf(session.getAttribute("redirectParam"));
                JsonParser parser = new JsonParser();
                
                StringBuffer sb = new StringBuffer();
                JsonElement jel= (JsonObject) parser.parse(redParam);
                JsonObject jObj = jel.getAsJsonObject();

                List<Map.Entry> entryList = new ArrayList<Map.Entry>(jObj.entrySet());
                
                sb.append("?");
                for(int i = 0; i < entryList.size(); i++) {
                    Map.Entry entry = entryList.get(i);
                    sb.append(String.valueOf(entry.getKey())+"="+jObj.get(String.valueOf(entry.getKey())).getAsString());
                    
                    request.setAttribute(String.valueOf(entry.getKey()), jObj.get(String.valueOf(entry.getKey())).getAsString());
                    if(i != entryList.size()-1) sb.append("&"); 
                }
                
                rdParam = sb.toString();
            }
        }

    	if(!isAuthed || "null".equals(initFileName)) initFileName = "/noAuthPageList.do?noAuthPageId="+initFileName;
    	else initFileName = "/"+initFileName+".do";
    	
    	logger.debug("initFileName:"+initFileName+"   Auth:"+isAuthed);
        ActionForward forward = new ActionForward(initFileName+rdParam);
        
        return forward;
    }
    
    public boolean findSubList(JSONArray jsonArray, String fileName)
    {
    	boolean isAuthed = false;
    	
    	if(jsonArray == null) return isAuthed;
    	for(int n = 0; n < jsonArray.size(); n++)
        {
    		if(isAuthed) break;
    		
        	JSONObject object = (JSONObject)jsonArray.get(n);

//        	if(!object.containsValue(fileName) && object.containsKey("ITEM")) return findSubList((JSONArray)object.get("item"), fileName);
        	if(!fileName.equals(object.get("PAGEID")) && object.containsKey("ITEM")) return findSubList((JSONArray)object.get("item"), fileName);
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
    
    /**
     * ArrayList �ȿ� �ִ� Hashtable:�޴� ���� 
     * menu �̸��� struts message�� ���� �����Ѵ�. 
     * @author  javaworker
     * @version $Id: MainAction.java,v 1.6 2014/02/17 06:06:56 javaworker Exp $
     * @since   1.0
     * 
     * @param menuList 
     * @throws Exception
     */
    private void setMenuLang(HttpServletRequest request, ArrayList menuList) throws Exception
    {
        java.util.Locale locale = getUser(request).getLocale();
        org.apache.struts.util.MessageResources messages = getResources(request);

        Hashtable tempMenuHash = null;
        String [][] tempMenu = null;
        
        int menuListSize = menuList.size();
        
        // �� �޴�
        for (int i=0; i<menuListSize; i++)
        {
            tempMenuHash = (Hashtable)menuList.get(i);
            tempMenu = (String [][])tempMenuHash.get("menu");
            
            // ���� �޴�
            for (int j=0; j<tempMenu.length; j++)
            {
                // tempMenu : 0(name), 1(page id), 2(menu_id)
            	tempMenu[j][0] = tempMenu[j][4];
            }
        }
    }
}