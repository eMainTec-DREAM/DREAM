package intf.dream.ant.woreq.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfAuthAction;
import intf.dream.ant.woreq.form.AntWoReqListForm;
import intf.dream.ant.woreq.service.AntWoReqListService;

/**
 * wo req  
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/antWoReqList" name="antWoReqListForm"
 *                input="/ant/woreq/antWoReqList.jsp" scope="request"
 *                validate="false"
 */
public class AntWoReqListAction extends IfAuthAction
{
    //WOREQ_FIND
    public static final String WOREQ_FIND 				= "WOREQ_FIND";
    //WOREQ_FILE_FIND
    public static final String WOREQ_FILE_FIND 			= "WOREQ_FILE_FIND";
    //WOREQ_SAVE
    public static final String WOREQ_SAVE				= "WOREQ_SAVE";
    //WORES_SAVE
    public static final String WORES_SAVE				= "WORES_SAVE";
    
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AntWoReqListForm antWoReqListForm = (AntWoReqListForm) form;
        
        switch (antWoReqListForm.getServiceName())
        {
            case AntWoReqListAction.WOREQ_FIND:
            	findWoReqList(request, response, antWoReqListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AntWoReqListAction.WOREQ_FILE_FIND:
            	findWoReqFileList(request, response, antWoReqListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AntWoReqListAction.WOREQ_SAVE:
            	saveWoReqList(request, response, antWoReqListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AntWoReqListAction.WORES_SAVE:
            	saveWoResList(request, response, antWoReqListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findWoReqList(HttpServletRequest request, HttpServletResponse response, AntWoReqListForm antWoReqListForm) throws Exception
    {
    	AntWoReqListService antWoReqListService = (AntWoReqListService) getBean("antWoReqListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = antWoReqListService.findWoReqList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void findWoReqFileList(HttpServletRequest request, HttpServletResponse response, AntWoReqListForm antWoReqListForm) throws Exception
    {
    	AntWoReqListService antWoReqListService = (AntWoReqListService) getBean("antWoReqListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = antWoReqListService.findWoReqFileList(map);
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
    private void saveWoReqList(HttpServletRequest request, HttpServletResponse response, AntWoReqListForm antWoReqListForm)  throws Exception
    {
    	AntWoReqListService antWoReqListService = (AntWoReqListService) getBean("antWoReqListService");

    	List list = getListData(request);
    	int qty = antWoReqListService.saveWoReqList(list);
    	setMessage(response, "","OK");
    }
    private void saveWoResList(HttpServletRequest request, HttpServletResponse response, AntWoReqListForm antWoReqListForm)  throws Exception
    {
    	AntWoReqListService antWoReqListService = (AntWoReqListService) getBean("antWoReqListService");

    	List list = getListData(request);
    	int qty = antWoReqListService.saveWoResList(list);
    	setMessage(response, "","OK");
    }
}
