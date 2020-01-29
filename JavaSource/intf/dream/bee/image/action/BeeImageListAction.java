package intf.dream.bee.image.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import common.struts.IfOnlineAuthAction;
import intf.dream.bee.image.form.BeeImageListForm;
import intf.dream.bee.image.service.BeeImageListService;

/**
 * count 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/beeImageList" name="beeImageListForm"
 *                input="/bee/image/beeImageList.jsp" scope="request"
 *                validate="false"
 */
public class BeeImageListAction extends IfOnlineAuthAction
{
    public static final String IMAGE_LIST 					= "IMAGE_LIST";
    public static final String IMAGE_INSERT 				= "IMAGE_INSERT";
    public static final String IMAGE_UPDATE 				= "IMAGE_UPDATE";
    public static final String IMAGE_DELETE 				= "IMAGE_DELETE";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        BeeImageListForm beeImageListForm = (BeeImageListForm) form;
        
        switch (beeImageListForm.getServiceName())
        {
            case BeeImageListAction.IMAGE_LIST:
            	findImageList(request, response, beeImageListForm, false);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeeImageListAction.IMAGE_INSERT:
            	insertImage(request, response, beeImageListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeImageListAction.IMAGE_UPDATE:
            	updateImage(request, response, beeImageListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeImageListAction.IMAGE_DELETE:
            	deleteImage(request, response, beeImageListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findImageList(HttpServletRequest request, HttpServletResponse response, BeeImageListForm beeImageListForm, boolean excelExport) throws Exception
    {
    	BeeImageListService beeImageListService = (BeeImageListService) getBean("beeImageListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beeImageListService.findImageList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
    private void insertImage(HttpServletRequest request, HttpServletResponse response, BeeImageListForm beeImageListForm) throws Exception
    {
    	BeeImageListService beeImageListService = (BeeImageListService) getBean("beeImageListService");
    	List list = getListData(request);
    	
    	FormFile[] fileList = beeImageListForm.getFileList();
        
    	beeImageListService.insertImage(list, fileList);
    	setMessage(response, "","OK");
    }
    private void updateImage(HttpServletRequest request, HttpServletResponse response, BeeImageListForm beeImageListForm) throws Exception
    {
    	BeeImageListService beeImageListService = (BeeImageListService) getBean("beeImageListService");
    	List list = getListData(request);
    	FormFile[] fileList = beeImageListForm.getFileList();
    	int qty = beeImageListService.updateImage(list,fileList);
    	setMessage(response, "","OK");
    }
    private void deleteImage(HttpServletRequest request, HttpServletResponse response, BeeImageListForm beeImageListForm) throws Exception
    {
    	BeeImageListService beeImageListService = (BeeImageListService) getBean("beeImageListService");
    	List list = getListData(request);
    	int qty = beeImageListService.deleteImage(list);
    	setMessage(response, "","OK");
    }
}
