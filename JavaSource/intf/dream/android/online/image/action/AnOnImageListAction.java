package intf.dream.android.online.image.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import common.struts.IfOnlineAuthAction;
import intf.dream.android.online.image.form.AnOnImageListForm;
import intf.dream.android.online.image.service.AnOnImageListService;

/**
 * count 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/anOnImageList" name="anOnImageListForm"
 *                input="/android/online/image/anOnImageList.jsp" scope="request"
 *                validate="false"
 */
public class AnOnImageListAction extends IfOnlineAuthAction
{
    public static final String IMAGE_LIST 					= "IMAGE_LIST";
    public static final String IMAGE_INSERT 				= "IMAGE_INSERT";
    public static final String IMAGE_UPDATE 				= "IMAGE_UPDATE";
    public static final String IMAGE_DELETE 				= "IMAGE_DELETE";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AnOnImageListForm anOnImageListForm = (AnOnImageListForm) form;
        
        switch (anOnImageListForm.getServiceName())
        {
            case AnOnImageListAction.IMAGE_LIST:
            	findImageList(request, response, anOnImageListForm, false);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnImageListAction.IMAGE_INSERT:
            	insertImage(request, response, anOnImageListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnImageListAction.IMAGE_UPDATE:
            	updateImage(request, response, anOnImageListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnImageListAction.IMAGE_DELETE:
            	deleteImage(request, response, anOnImageListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findImageList(HttpServletRequest request, HttpServletResponse response, AnOnImageListForm anOnImageListForm, boolean excelExport) throws Exception
    {
    	AnOnImageListService anOnImageListService = (AnOnImageListService) getBean("anOnImageListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnImageListService.findImageList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
    private void insertImage(HttpServletRequest request, HttpServletResponse response, AnOnImageListForm anOnImageListForm) throws Exception
    {
    	AnOnImageListService anOnImageListService = (AnOnImageListService) getBean("anOnImageListService");
    	List list = getListData(request);
    	
    	FormFile[] fileList = anOnImageListForm.getFileList();
        
    	anOnImageListService.insertImage(list, fileList);
    	setMessage(response, "","OK");
    }
    private void updateImage(HttpServletRequest request, HttpServletResponse response, AnOnImageListForm anOnImageListForm) throws Exception
    {
    	AnOnImageListService anOnImageListService = (AnOnImageListService) getBean("anOnImageListService");
    	List list = getListData(request);
    	FormFile[] fileList = anOnImageListForm.getFileList();
    	int qty = anOnImageListService.updateImage(list,fileList);
    	setMessage(response, "","OK");
    }
    private void deleteImage(HttpServletRequest request, HttpServletResponse response, AnOnImageListForm anOnImageListForm) throws Exception
    {
    	AnOnImageListService anOnImageListService = (AnOnImageListService) getBean("anOnImageListService");
    	List list = getListData(request);
    	int qty = anOnImageListService.deleteImage(list);
    	setMessage(response, "","OK");
    }
}
