package intf.dream.ant.asset.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfAuthAction;
import intf.dream.ant.asset.form.AntAssetListForm;
import intf.dream.ant.asset.service.AntAssetListService;

/**
 * wo req  
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/antAssetList" name="antAssetListForm"
 *                input="/ant/woreq/antAssetList.jsp" scope="request"
 *                validate="false"
 */
public class AntAssetListAction extends IfAuthAction
{
    //ASSET_FIND
    public static final String ASSET_FIND 				= "ASSET_FIND";
    //ASSET_FILE_FIND
    public static final String ASSET_FILE_FIND 			= "ASSET_FILE_FIND";
    //ASSET_SAVE
    public static final String ASSET_SAVE				= "ASSET_SAVE";
    
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AntAssetListForm antAssetListForm = (AntAssetListForm) form;
        
        switch (antAssetListForm.getServiceName())
        {
            case AntAssetListAction.ASSET_FIND:
            	findAssetList(request, response, antAssetListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AntAssetListAction.ASSET_FILE_FIND:
            	findAssetFileList(request, response, antAssetListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AntAssetListAction.ASSET_SAVE:
            	saveAssetList(request, response, antAssetListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findAssetList(HttpServletRequest request, HttpServletResponse response, AntAssetListForm antAssetListForm) throws Exception
    {
    	AntAssetListService antAssetListService = (AntAssetListService) getBean("antAssetListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = antAssetListService.findAssetList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void findAssetFileList(HttpServletRequest request, HttpServletResponse response, AntAssetListForm antAssetListForm) throws Exception
    {
    	AntAssetListService antAssetListService = (AntAssetListService) getBean("antAssetListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = antAssetListService.findAssetFileList(map);
//        for(Object object : resultList)
//        {
//        	MultipartUtility multipartUtility = new MultipartUtility(request.getRequestURL().toString());
//            
//        	Map rMap = (Map)object;
//
//        	String compNo = String.valueOf(rMap.get("COMP_NO"));
//        	String fileId = String.valueOf(rMap.get("FILE_ID"));
//        	String objectType = String.valueOf(rMap.get("OBJECT_TYPE"));
//        	String objectId = String.valueOf(rMap.get("OBJECT_ID"));
//        	String filePath = String.valueOf(rMap.get("FILE_PATH"));
//        	String fileName = String.valueOf(rMap.get("FILE_NAME"));
//        	String extension = String.valueOf(rMap.get("EXTENSION"));
//        	
//        	String file = MwareConfig.getFileDir()+filePath+"/"+fileName+"."+extension;
//        	
//        	
//        	Map inputMap = new HashMap();
//        	inputMap.put("COMP_NO", compNo);
//        	inputMap.put("FILE_ID", fileId);
//        	inputMap.put("OBJECT_TYPE", objectType);
//        	inputMap.put("OBJECT_ID", objectId);
//        	inputMap.put("FILE_PATH", filePath);
//        	inputMap.put("FILE_NAME", fileName);
//        	inputMap.put("EXTENSION", extension);
//        	inputMap.put("FILE", new File(file));
//        	
//            Gson gson = new Gson();
//            String jsonString = gson.toJson(inputMap);
//        	
//        	
//        	String resultJson  = 
//        			multipartUtility.addParam("fileList",new File(file))
//        			.addParam("pwd","emaintec")
//        			.addParam("user","ADMIN")
//        			.addParam("data",jsonString)
//        			.submit();
//        	System.out.println(resultJson);
//        }
        
        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void saveAssetList(HttpServletRequest request, HttpServletResponse response, AntAssetListForm antAssetListForm)  throws Exception
    {
    	AntAssetListService antAssetListService = (AntAssetListService) getBean("antAssetListService");

    	List list = getListData(request);
    	int qty = antAssetListService.saveAssetList(list);
    	setMessage(response, "","OK");
    }

}
