package intf.dream.bee.stktake.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import common.util.CommonUtil;
import intf.dream.bee.stktake.dto.BeeStkTakeCommonDTO;
import intf.dream.bee.stktake.form.BeeStkTakeListForm;
import intf.dream.bee.stktake.service.BeeStkTakeListService;

/**
 * 온라인버전 부품실사 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/beeStkTakeList" name="beeStkTakeListForm"
 *                input="/bee/stktake/beeStkTakeList.jsp" scope="request"
 *                validate="false"
 */
public class BeeStkTakeListAction extends IfOnlineAuthAction
{
    //STKTAKE LIST
    public static final String STKTAKE_LIST 					= "STKTAKE_LIST";
    //STKTAKE COUNT
    public static final String STKTAKE_COUNT 					= "STKTAKE_COUNT";
    //STKTAKE DELETE
    public static final String STKTAKE_DELETE 					= "STKTAKE_DELETE";
    //STKTAKEITEM_LIST
    public static final String STKTAKEITEM_LIST 				= "STKTAKEITEM_LIST";
    //STKTAKEITEM_DELETE
    public static final String STKTAKEITEM_DELETE 				= "STKTAKEITEM_DELETE";
    //STKTAKEITEM_INSERT
    public static final String STKTAKEITEM_INSERT 				= "STKTAKEITEM_INSERT";
    //STKTAKEITEM_UPDATE
    public static final String STKTAKEITEM_UPDATE 				= "STKTAKEITEM_UPDATE";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        BeeStkTakeListForm beeStkTakeListForm = (BeeStkTakeListForm) form;
        
        switch (beeStkTakeListForm.getServiceName())
        {
            case BeeStkTakeListAction.STKTAKE_LIST:
            	findStkTakeList(request, response, beeStkTakeListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeStkTakeListAction.STKTAKE_COUNT:
            	findStkTakeCount(request, response, beeStkTakeListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeStkTakeListAction.STKTAKE_DELETE:
            	deleteStkTake(request, response, beeStkTakeListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeStkTakeListAction.STKTAKEITEM_LIST:
            	findStkTakeItemList(request, response, beeStkTakeListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeStkTakeListAction.STKTAKEITEM_DELETE:
            	deleteStkTakeItem(request, response, beeStkTakeListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeStkTakeListAction.STKTAKEITEM_INSERT:
            	insertStkTakeItem(request, response, beeStkTakeListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeStkTakeListAction.STKTAKEITEM_UPDATE:
            	updateStkTakeItem(request, response, beeStkTakeListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            	
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }

    private void findStkTakeList(HttpServletRequest request, HttpServletResponse response, BeeStkTakeListForm beeStkTakeListForm) throws Exception
    {
    	BeeStkTakeListService beeStkTakeListService = (BeeStkTakeListService) getBean("beeStkTakeListService");
    	BeeStkTakeCommonDTO beeStkTakeCommonDTO = beeStkTakeListForm.getBeeStkTakeCommonDTO();
    	Map map = getMapData(request);

    	beeStkTakeCommonDTO.setIsLoadMaxCount(true);
    	if("".equals(CommonUtil.convertString(String.valueOf(map.get("nextStartRow"))))){
    		beeStkTakeCommonDTO.setIsLoadMaxCount(false);
    	}
    	beeStkTakeCommonDTO.setFirstRow(CommonUtil.convertString(String.valueOf(map.get("nextStartRow"))));
    	
        //리스트를 조회한다.
        List resultList = beeStkTakeListService.findStkTakeList(beeStkTakeCommonDTO, map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }

    private void findStkTakeCount(HttpServletRequest request, HttpServletResponse response, BeeStkTakeListForm beeStkTakeListForm) throws Exception
    {
    	BeeStkTakeListService beeStkTakeListService = (BeeStkTakeListService) getBean("beeStkTakeListService");
    	BeeStkTakeCommonDTO beeStkTakeCommonDTO = beeStkTakeListForm.getBeeStkTakeCommonDTO();
    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beeStkTakeListService.findStkTakeCount(beeStkTakeCommonDTO, map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
    private void deleteStkTake(HttpServletRequest request, HttpServletResponse response, BeeStkTakeListForm beeStkTakeListForm) throws Exception
    {
    	BeeStkTakeListService beeStkTakeListService = (BeeStkTakeListService) getBean("beeStkTakeListService");
    	List list = getListData(request);
    	int qty = beeStkTakeListService.deleteStkTake(list);
    	setMessage(response, "","OK");
    }

    private void findStkTakeItemList(HttpServletRequest request, HttpServletResponse response, BeeStkTakeListForm beeStkTakeListForm) throws Exception
    {
    	BeeStkTakeListService beeStkTakeListService = (BeeStkTakeListService) getBean("beeStkTakeListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beeStkTakeListService.findStkTakeItemList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void deleteStkTakeItem(HttpServletRequest request, HttpServletResponse response, BeeStkTakeListForm beeStkTakeListForm) throws Exception
    {
    	BeeStkTakeListService beeStkTakeListService = (BeeStkTakeListService) getBean("beeStkTakeListService");
    	List list = getListData(request);
    	int qty = beeStkTakeListService.deleteStkTakeItem(list);
    	setMessage(response, "","OK");
    }
    private void insertStkTakeItem(HttpServletRequest request, HttpServletResponse response, BeeStkTakeListForm beeStkTakeListForm) throws Exception
    {
    	BeeStkTakeListService beeStkTakeListService = (BeeStkTakeListService) getBean("beeStkTakeListService");
    	List list = getListData(request);
    	int qty = beeStkTakeListService.insertStkTakeItem(list);
    	setMessage(response, "","OK");
    }
    private void updateStkTakeItem(HttpServletRequest request, HttpServletResponse response, BeeStkTakeListForm beeStkTakeListForm) throws Exception
    {
    	BeeStkTakeListService beeStkTakeListService = (BeeStkTakeListService) getBean("beeStkTakeListService");
    	List list = getListData(request);
    	int qty = beeStkTakeListService.updateStkTakeItem(list);
    	setMessage(response, "","OK");
    }
}
