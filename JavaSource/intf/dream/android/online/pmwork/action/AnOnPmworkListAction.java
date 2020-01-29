package intf.dream.android.online.pmwork.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import common.struts.IfOnlineAuthAction;
import intf.dream.android.online.pmwork.form.AnOnPmworkListForm;
import intf.dream.android.online.pmwork.service.AnOnPmworkListService;

/**
 * 온라인버전 계획작업 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/anOnPmworkList" name="anOnPmworkListForm"
 *                input="/android/online/pmwork/anOnPmworkList.jsp" scope="request"
 *                validate="false"
 */
public class AnOnPmworkListAction extends IfOnlineAuthAction
{
    //PMWORK LIST
    public static final String PMWORK_LIST 					= "PMWORK_LIST";
    //PMWORK DELETE 
    public static final String PMWORK_DELETE 				= "PMWORK_DELETE";
    //PMWORK INSERT
    public static final String PMWORK_INSERT 				= "PMWORK_INSERT";
    //PMWORK UPDATE 
    public static final String PMWORK_UPDATE 				= "PMWORK_UPDATE";
    
    //WOCRAFT LIST
    public static final String WOCRAFT_LIST 				= "WOCRAFT_LIST";
    //WOCRAFT INSERT
    public static final String WOCRAFT_INSERT 				= "WOCRAFT_INSERT";
    //WOCRAFT UPDATE
    public static final String WOCRAFT_UPDATE 				= "WOCRAFT_UPDATE";
    //WOCRAFT DELETE
    public static final String WOCRAFT_DELETE 				= "WOCRAFT_DELETE";

    //WOPARTS LIST
    public static final String WOPARTS_LIST 				= "WOPARTS_LIST";
    //WOPARTS INSERT 
    public static final String WOPARTS_INSERT 				= "WOPARTS_INSERT";
    //WOPARTS UPDATE
    public static final String WOPARTS_UPDATE 				= "WOPARTS_UPDATE";
    //WOPARTS DELETE
    public static final String WOPARTS_DELETE 				= "WOPARTS_DELETE";
    //FIND STOCK QTY
    public static final String STOCK_QTY_FIND 				= "STOCK_QTY_FIND";

    //PHOTO LIST
    public static final String PHOTO_LIST 					= "PHOTO_LIST";
    //PHOTO INSERT 
    public static final String PHOTO_INSERT 				= "PHOTO_INSERT";
    //PHOTO UPDATE 
    public static final String PHOTO_UPDATE 				= "PHOTO_UPDATE";
    //DOCUMENT UPDATE 
    public static final String DOCUMENT_UPDATE 				= "DOCUMENT_UPDATE";
    //PHOTO DELETE
    public static final String PHOTO_DELETE 				= "PHOTO_DELETE";
    //TEMP PHOTO DELETE
    public static final String TEMP_PHOTO_DELETE 			= "TEMP_PHOTO_DELETE";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AnOnPmworkListForm anOnPmworkListForm = (AnOnPmworkListForm) form;
        
        switch (anOnPmworkListForm.getServiceName())
        {
            case AnOnPmworkListAction.PMWORK_LIST:
            	findPmworkList(request, response, anOnPmworkListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnPmworkListAction.PMWORK_DELETE:
            	deletePmwork(request, response, anOnPmworkListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnPmworkListAction.PMWORK_INSERT:
            	insertPmwork(request, response, anOnPmworkListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnPmworkListAction.PMWORK_UPDATE:
            	updatePmwork(request, response, anOnPmworkListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 

            case AnOnPmworkListAction.WOCRAFT_LIST:
            	findWoCraftList(request, response, anOnPmworkListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnPmworkListAction.WOCRAFT_INSERT:
            	insertWoCraft(request, response, anOnPmworkListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnPmworkListAction.WOCRAFT_UPDATE:
            	updateWoCraft(request, response, anOnPmworkListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnPmworkListAction.WOCRAFT_DELETE:
            	deleteWoCraft(request, response, anOnPmworkListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 

            case AnOnPmworkListAction.WOPARTS_LIST:
            	findWoPartsList(request, response, anOnPmworkListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnPmworkListAction.WOPARTS_INSERT:
            	insertWoParts(request, response, anOnPmworkListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnPmworkListAction.WOPARTS_UPDATE:
            	updateWoParts(request, response, anOnPmworkListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnPmworkListAction.WOPARTS_DELETE:
            	deleteWoParts(request, response, anOnPmworkListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnPmworkListAction.STOCK_QTY_FIND:
            	findStockQty(request, response, anOnPmworkListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;

            case AnOnPmworkListAction.PHOTO_LIST:
            	findPhotoList(request, response, anOnPmworkListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnPmworkListAction.PHOTO_INSERT:
            	insertPhoto(request, response, anOnPmworkListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnPmworkListAction.PHOTO_UPDATE:
            	updatePhoto(request, response, anOnPmworkListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnPmworkListAction.DOCUMENT_UPDATE:
            	updateDocument(request, response, anOnPmworkListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnPmworkListAction.PHOTO_DELETE:
            	deletePhoto(request, response, anOnPmworkListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnPmworkListAction.TEMP_PHOTO_DELETE:
            	deleteTempPhoto(request, response, anOnPmworkListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            	
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }

    private void findPmworkList(HttpServletRequest request, HttpServletResponse response, AnOnPmworkListForm anOnPmworkListForm) throws Exception
    {
    	AnOnPmworkListService anOnPmworkListService = (AnOnPmworkListService) getBean("anOnPmworkListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnPmworkListService.findPmworkList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void deletePmwork(HttpServletRequest request, HttpServletResponse response, AnOnPmworkListForm anOnPmworkListForm) throws Exception
    {
    	AnOnPmworkListService anOnPmworkListService = (AnOnPmworkListService) getBean("anOnPmworkListService");
    	List list = getListData(request);
    	int qty = anOnPmworkListService.deletePmwork(list);
    	setMessage(response, "","OK");
    }
    private void insertPmwork(HttpServletRequest request, HttpServletResponse response, AnOnPmworkListForm anOnPmworkListForm) throws Exception
    {
    	AnOnPmworkListService anOnPmworkListService = (AnOnPmworkListService) getBean("anOnPmworkListService");
    	List list = getListData(request);
    	int qty = anOnPmworkListService.insertPmwork(list);
    	setMessage(response, "","OK"); 
    }
    private void updatePmwork(HttpServletRequest request, HttpServletResponse response, AnOnPmworkListForm anOnPmworkListForm) throws Exception
    {
    	AnOnPmworkListService anOnPmworkListService = (AnOnPmworkListService) getBean("anOnPmworkListService");
    	List list = getListData(request);
    	int qty = anOnPmworkListService.updatePmwork(list);
    	setMessage(response, "","OK");
    }
    
    
    private void findWoCraftList(HttpServletRequest request, HttpServletResponse response, AnOnPmworkListForm anOnPmworkListForm) throws Exception
    {
    	AnOnPmworkListService anOnPmworkListService = (AnOnPmworkListService) getBean("anOnPmworkListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnPmworkListService.findWoCraftList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void insertWoCraft(HttpServletRequest request, HttpServletResponse response, AnOnPmworkListForm anOnPmworkListForm) throws Exception
    {
    	AnOnPmworkListService anOnPmworkListService = (AnOnPmworkListService) getBean("anOnPmworkListService");
    	List list = getListData(request);
    	int qty = anOnPmworkListService.insertWoCraft(list);
    	setMessage(response, "","OK");
    }
    private void updateWoCraft(HttpServletRequest request, HttpServletResponse response, AnOnPmworkListForm anOnPmworkListForm) throws Exception
    {
    	AnOnPmworkListService anOnPmworkListService = (AnOnPmworkListService) getBean("anOnPmworkListService");
    	List list = getListData(request);
    	int qty = anOnPmworkListService.updateWoCraft(list);
    	setMessage(response, "","OK");
    }
    private void deleteWoCraft(HttpServletRequest request, HttpServletResponse response, AnOnPmworkListForm anOnPmworkListForm) throws Exception
    {
    	AnOnPmworkListService anOnPmworkListService = (AnOnPmworkListService) getBean("anOnPmworkListService");
    	List list = getListData(request);
    	int qty = anOnPmworkListService.deleteWoCraft(list);
    	setMessage(response, "","OK");
    }

    private void findWoPartsList(HttpServletRequest request, HttpServletResponse response, AnOnPmworkListForm anOnPmworkListForm) throws Exception
    {
    	AnOnPmworkListService anOnPmworkListService = (AnOnPmworkListService) getBean("anOnPmworkListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnPmworkListService.findWoPartsList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void insertWoParts(HttpServletRequest request, HttpServletResponse response, AnOnPmworkListForm anOnPmworkListForm) throws Exception
    {
    	AnOnPmworkListService anOnPmworkListService = (AnOnPmworkListService) getBean("anOnPmworkListService");
    	List list = getListData(request);
    	int qty = anOnPmworkListService.insertWoParts(list);
    	setMessage(response, "","OK");
    }
    private void updateWoParts(HttpServletRequest request, HttpServletResponse response, AnOnPmworkListForm anOnPmworkListForm) throws Exception
    {
    	AnOnPmworkListService anOnPmworkListService = (AnOnPmworkListService) getBean("anOnPmworkListService");
    	List list = getListData(request);
    	int qty = anOnPmworkListService.updateWoParts(list);
    	setMessage(response, "","OK");
    }
    private void deleteWoParts(HttpServletRequest request, HttpServletResponse response, AnOnPmworkListForm anOnPmworkListForm) throws Exception
    {
    	AnOnPmworkListService anOnPmworkListService = (AnOnPmworkListService) getBean("anOnPmworkListService");
    	List list = getListData(request);
    	int qty = anOnPmworkListService.deleteWoParts(list);
    	setMessage(response, "","OK");
    }
    private void findStockQty(HttpServletRequest request, HttpServletResponse response, AnOnPmworkListForm anOnPmworkListForm) throws Exception
    {
    	AnOnPmworkListService anOnPmworkListService = (AnOnPmworkListService) getBean("anOnPmworkListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnPmworkListService.findStockQty(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }

    private void findPhotoList(HttpServletRequest request, HttpServletResponse response, AnOnPmworkListForm anOnPmworkListForm) throws Exception
    {
    	AnOnPmworkListService anOnPmworkListService = (AnOnPmworkListService) getBean("anOnPmworkListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnPmworkListService.findPhotoList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void insertPhoto(HttpServletRequest request, HttpServletResponse response, AnOnPmworkListForm anOnPmworkListForm) throws Exception
    {
    	AnOnPmworkListService anOnPmworkListService = (AnOnPmworkListService) getBean("anOnPmworkListService");
    	List list = getListData(request);
    	FormFile[] fileList = anOnPmworkListForm.getFileList();
        
    	anOnPmworkListService.insertPhoto(list, fileList);
    	setMessage(response, "","OK");
    }
    private void updatePhoto(HttpServletRequest request, HttpServletResponse response, AnOnPmworkListForm anOnPmworkListForm) throws Exception
    {
    	AnOnPmworkListService anOnPmworkListService = (AnOnPmworkListService) getBean("anOnPmworkListService");
    	List list = getListData(request);
    	FormFile[] fileList = anOnPmworkListForm.getFileList();
    	int qty = anOnPmworkListService.updatePhoto(list,fileList);
    	setMessage(response, "","OK");
    }
    private void updateDocument(HttpServletRequest request, HttpServletResponse response, AnOnPmworkListForm anOnPmworkListForm) throws Exception
    {
    	AnOnPmworkListService anOnPmworkListService = (AnOnPmworkListService) getBean("anOnPmworkListService");
    	List list = getListData(request);
    	FormFile[] fileList = anOnPmworkListForm.getFileList();
    	int qty = anOnPmworkListService.updateDocument(list,fileList);
    	setMessage(response, "","OK");
    }
    private void deletePhoto(HttpServletRequest request, HttpServletResponse response, AnOnPmworkListForm anOnPmworkListForm) throws Exception
    {
    	AnOnPmworkListService anOnPmworkListService = (AnOnPmworkListService) getBean("anOnPmworkListService");
    	List list = getListData(request);
    	int qty = anOnPmworkListService.deletePhoto(list);
    	setMessage(response, "","OK");
    }
    private void deleteTempPhoto(HttpServletRequest request, HttpServletResponse response, AnOnPmworkListForm anOnPmworkListForm) throws Exception
    {
    	AnOnPmworkListService anOnPmworkListService = (AnOnPmworkListService) getBean("anOnPmworkListService");
    	List list = getListData(request);
    	int qty = anOnPmworkListService.deleteTempPhoto(list);
    	setMessage(response, "","OK");
    }
}
