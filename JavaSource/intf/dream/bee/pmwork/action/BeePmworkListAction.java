package intf.dream.bee.pmwork.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import common.struts.IfOnlineAuthAction;
import common.util.CommonUtil;
import intf.dream.bee.pmwork.dto.BeePmworkCommonDTO;
import intf.dream.bee.pmwork.form.BeePmworkListForm;
import intf.dream.bee.pmwork.service.BeePmworkListService;

/**
 * 온라인버전 계획작업 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/beePmworkList" name="beePmworkListForm"
 *                input="/bee/pmwork/beePmworkList.jsp" scope="request"
 *                validate="false"
 */
public class BeePmworkListAction extends IfOnlineAuthAction
{
    //PMWORK LIST
    public static final String PMWORK_LIST 					= "PMWORK_LIST";
    //PMWORK LIST COUNT
    public static final String PMWORK_COUNT					= "PMWORK_COUNT";
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
        BeePmworkListForm beePmworkListForm = (BeePmworkListForm) form;
        
        switch (beePmworkListForm.getServiceName())
        {
            case BeePmworkListAction.PMWORK_LIST:
            	findPmworkList(request, response, beePmworkListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeePmworkListAction.PMWORK_COUNT:
            	findPmworkCount(request, response, beePmworkListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeePmworkListAction.PMWORK_DELETE:
            	deletePmwork(request, response, beePmworkListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeePmworkListAction.PMWORK_INSERT:
            	insertPmwork(request, response, beePmworkListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeePmworkListAction.PMWORK_UPDATE:
            	updatePmwork(request, response, beePmworkListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 

            case BeePmworkListAction.WOCRAFT_LIST:
            	findWoCraftList(request, response, beePmworkListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeePmworkListAction.WOCRAFT_INSERT:
            	insertWoCraft(request, response, beePmworkListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeePmworkListAction.WOCRAFT_UPDATE:
            	updateWoCraft(request, response, beePmworkListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeePmworkListAction.WOCRAFT_DELETE:
            	deleteWoCraft(request, response, beePmworkListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 

            case BeePmworkListAction.WOPARTS_LIST:
            	findWoPartsList(request, response, beePmworkListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeePmworkListAction.WOPARTS_INSERT:
            	insertWoParts(request, response, beePmworkListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeePmworkListAction.WOPARTS_UPDATE:
            	updateWoParts(request, response, beePmworkListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeePmworkListAction.WOPARTS_DELETE:
            	deleteWoParts(request, response, beePmworkListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeePmworkListAction.STOCK_QTY_FIND:
            	findStockQty(request, response, beePmworkListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;

            case BeePmworkListAction.PHOTO_LIST:
            	findPhotoList(request, response, beePmworkListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeePmworkListAction.PHOTO_INSERT:
            	insertPhoto(request, response, beePmworkListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeePmworkListAction.PHOTO_UPDATE:
            	updatePhoto(request, response, beePmworkListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeePmworkListAction.DOCUMENT_UPDATE:
            	updateDocument(request, response, beePmworkListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeePmworkListAction.PHOTO_DELETE:
            	deletePhoto(request, response, beePmworkListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeePmworkListAction.TEMP_PHOTO_DELETE:
            	deleteTempPhoto(request, response, beePmworkListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            	
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }

    private void findPmworkList(HttpServletRequest request, HttpServletResponse response, BeePmworkListForm beePmworkListForm) throws Exception
    {
    	BeePmworkListService beePmworkListService = (BeePmworkListService) getBean("beePmworkListService");
    	BeePmworkCommonDTO beePmworkCommonDTO = beePmworkListForm.getBeePmworkCommonDTO();
    	Map map = getMapData(request);

    	beePmworkCommonDTO.setIsLoadMaxCount(true);
    	if("".equals(CommonUtil.convertString(String.valueOf(map.get("nextStartRow"))))){
    		beePmworkCommonDTO.setIsLoadMaxCount(false);
    	}
    	beePmworkCommonDTO.setFirstRow(CommonUtil.convertString(String.valueOf(map.get("nextStartRow"))));
    	
        //리스트를 조회한다.
        List resultList = beePmworkListService.findPmworkList(beePmworkCommonDTO, map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }

    private void findPmworkCount(HttpServletRequest request, HttpServletResponse response, BeePmworkListForm beePmworkListForm) throws Exception
    {
    	BeePmworkListService beePmworkListService = (BeePmworkListService) getBean("beePmworkListService");
    	BeePmworkCommonDTO beePmworkCommonDTO = beePmworkListForm.getBeePmworkCommonDTO();
    	Map map = getMapData(request);

    	
        //리스트를 조회한다.
        List resultList = beePmworkListService.findPmworkCount(beePmworkCommonDTO, map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void deletePmwork(HttpServletRequest request, HttpServletResponse response, BeePmworkListForm beePmworkListForm) throws Exception
    {
    	BeePmworkListService beePmworkListService = (BeePmworkListService) getBean("beePmworkListService");
    	List list = getListData(request);
    	int qty = beePmworkListService.deletePmwork(list);
    	setMessage(response, "","OK");
    }
    private void insertPmwork(HttpServletRequest request, HttpServletResponse response, BeePmworkListForm beePmworkListForm) throws Exception
    {
    	BeePmworkListService beePmworkListService = (BeePmworkListService) getBean("beePmworkListService");
    	List list = getListData(request);
    	int qty = beePmworkListService.insertPmwork(list);
    	setMessage(response, "","OK"); 
    }
    private void updatePmwork(HttpServletRequest request, HttpServletResponse response, BeePmworkListForm beePmworkListForm) throws Exception
    {
    	BeePmworkListService beePmworkListService = (BeePmworkListService) getBean("beePmworkListService");
    	List list = getListData(request);
    	int qty = beePmworkListService.updatePmwork(list);
    	setMessage(response, "","OK");
    }
    
    
    private void findWoCraftList(HttpServletRequest request, HttpServletResponse response, BeePmworkListForm beePmworkListForm) throws Exception
    {
    	BeePmworkListService beePmworkListService = (BeePmworkListService) getBean("beePmworkListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beePmworkListService.findWoCraftList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void insertWoCraft(HttpServletRequest request, HttpServletResponse response, BeePmworkListForm beePmworkListForm) throws Exception
    {
    	BeePmworkListService beePmworkListService = (BeePmworkListService) getBean("beePmworkListService");
    	List list = getListData(request);
    	int qty = beePmworkListService.insertWoCraft(list);
    	setMessage(response, "","OK");
    }
    private void updateWoCraft(HttpServletRequest request, HttpServletResponse response, BeePmworkListForm beePmworkListForm) throws Exception
    {
    	BeePmworkListService beePmworkListService = (BeePmworkListService) getBean("beePmworkListService");
    	List list = getListData(request);
    	int qty = beePmworkListService.updateWoCraft(list);
    	setMessage(response, "","OK");
    }
    private void deleteWoCraft(HttpServletRequest request, HttpServletResponse response, BeePmworkListForm beePmworkListForm) throws Exception
    {
    	BeePmworkListService beePmworkListService = (BeePmworkListService) getBean("beePmworkListService");
    	List list = getListData(request);
    	int qty = beePmworkListService.deleteWoCraft(list);
    	setMessage(response, "","OK");
    }

    private void findWoPartsList(HttpServletRequest request, HttpServletResponse response, BeePmworkListForm beePmworkListForm) throws Exception
    {
    	BeePmworkListService beePmworkListService = (BeePmworkListService) getBean("beePmworkListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beePmworkListService.findWoPartsList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void insertWoParts(HttpServletRequest request, HttpServletResponse response, BeePmworkListForm beePmworkListForm) throws Exception
    {
    	BeePmworkListService beePmworkListService = (BeePmworkListService) getBean("beePmworkListService");
    	List list = getListData(request);
    	int qty = beePmworkListService.insertWoParts(list);
    	setMessage(response, "","OK");
    }
    private void updateWoParts(HttpServletRequest request, HttpServletResponse response, BeePmworkListForm beePmworkListForm) throws Exception
    {
    	BeePmworkListService beePmworkListService = (BeePmworkListService) getBean("beePmworkListService");
    	List list = getListData(request);
    	int qty = beePmworkListService.updateWoParts(list);
    	setMessage(response, "","OK");
    }
    private void deleteWoParts(HttpServletRequest request, HttpServletResponse response, BeePmworkListForm beePmworkListForm) throws Exception
    {
    	BeePmworkListService beePmworkListService = (BeePmworkListService) getBean("beePmworkListService");
    	List list = getListData(request);
    	int qty = beePmworkListService.deleteWoParts(list);
    	setMessage(response, "","OK");
    }
    private void findStockQty(HttpServletRequest request, HttpServletResponse response, BeePmworkListForm beePmworkListForm) throws Exception
    {
    	BeePmworkListService beePmworkListService = (BeePmworkListService) getBean("beePmworkListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beePmworkListService.findStockQty(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }

    private void findPhotoList(HttpServletRequest request, HttpServletResponse response, BeePmworkListForm beePmworkListForm) throws Exception
    {
    	BeePmworkListService beePmworkListService = (BeePmworkListService) getBean("beePmworkListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beePmworkListService.findPhotoList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void insertPhoto(HttpServletRequest request, HttpServletResponse response, BeePmworkListForm beePmworkListForm) throws Exception
    {
    	BeePmworkListService beePmworkListService = (BeePmworkListService) getBean("beePmworkListService");
    	List list = getListData(request);
    	FormFile[] fileList = beePmworkListForm.getFileList();
        
    	beePmworkListService.insertPhoto(list, fileList);
    	setMessage(response, "","OK");
    }
    private void updatePhoto(HttpServletRequest request, HttpServletResponse response, BeePmworkListForm beePmworkListForm) throws Exception
    {
    	BeePmworkListService beePmworkListService = (BeePmworkListService) getBean("beePmworkListService");
    	List list = getListData(request);
    	FormFile[] fileList = beePmworkListForm.getFileList();
    	int qty = beePmworkListService.updatePhoto(list,fileList);
    	setMessage(response, "","OK");
    }
    private void updateDocument(HttpServletRequest request, HttpServletResponse response, BeePmworkListForm beePmworkListForm) throws Exception
    {
    	BeePmworkListService beePmworkListService = (BeePmworkListService) getBean("beePmworkListService");
    	List list = getListData(request);
    	FormFile[] fileList = beePmworkListForm.getFileList();
    	int qty = beePmworkListService.updateDocument(list,fileList);
    	setMessage(response, "","OK");
    }
    private void deletePhoto(HttpServletRequest request, HttpServletResponse response, BeePmworkListForm beePmworkListForm) throws Exception
    {
    	BeePmworkListService beePmworkListService = (BeePmworkListService) getBean("beePmworkListService");
    	List list = getListData(request);
    	int qty = beePmworkListService.deletePhoto(list);
    	setMessage(response, "","OK");
    }
    private void deleteTempPhoto(HttpServletRequest request, HttpServletResponse response, BeePmworkListForm beePmworkListForm) throws Exception
    {
    	BeePmworkListService beePmworkListService = (BeePmworkListService) getBean("beePmworkListService");
    	List list = getListData(request);
    	int qty = beePmworkListService.deleteTempPhoto(list);
    	setMessage(response, "","OK");
    }
}
