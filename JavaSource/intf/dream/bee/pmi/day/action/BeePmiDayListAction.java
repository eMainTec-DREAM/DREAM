package intf.dream.bee.pmi.day.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import common.util.CommonUtil;
import intf.dream.bee.pmi.day.dto.BeePmiDayCommonDTO;
import intf.dream.bee.pmi.day.form.BeePmiDayListForm;
import intf.dream.bee.pmi.day.service.BeePmiDayListService;

/**
 * 온라인버전 일상점검
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/beePmiDayList" name="beePmiDayListForm"
 *                input="/bee/pmi/day/beePmiDayList.jsp" scope="request"
 *                validate="false"
 */
public class BeePmiDayListAction extends IfOnlineAuthAction
{
    //PmiDay List
    public static final String PMI_LIST 					= "PMI_LIST";
    //PmiDay List Count
    public static final String PMI_COUNT 					= "PMI_COUNT";
    //Point List
    public static final String POINT_LIST					= "POINT_LIST";
    //Point Hist List
    public static final String POINT_HIST_LIST				= "POINT_HIST_LIST";
    //Wopoint count
    public static final String POINT_COUNT					= "POINT_COUNT";
    //INSERT POINT
    public static final String POINT_INSERT					= "POINT_INSERT";
    //UPDATE POINT
    public static final String POINT_UPDATE					= "POINT_UPDATE";
    //CHECK STATUS
    public static final String CHECK_STATUS					= "CHECK_STATUS";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        BeePmiDayListForm beePmiDayListForm = (BeePmiDayListForm) form;
        
        switch (beePmiDayListForm.getServiceName())
        {
            case BeePmiDayListAction.PMI_LIST:
            	findPmiDayList(request, response, beePmiDayListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeePmiDayListAction.PMI_COUNT:
            	findPmiDayCount(request, response, beePmiDayListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeePmiDayListAction.POINT_HIST_LIST:
            	findPointHistList(request, response, beePmiDayListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeePmiDayListAction.POINT_LIST:
            	findPointList(request, response, beePmiDayListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeePmiDayListAction.POINT_COUNT:
            	findWoPointCount(request, response, beePmiDayListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeePmiDayListAction.POINT_INSERT:
            	insertPoint(request, response, beePmiDayListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeePmiDayListAction.POINT_UPDATE:
            	updatePoint(request, response, beePmiDayListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeePmiDayListAction.CHECK_STATUS:
            	findStatus(request, response, beePmiDayListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findPmiDayList(HttpServletRequest request, HttpServletResponse response, BeePmiDayListForm beePmiDayListForm) throws Exception
    {
    	BeePmiDayListService beePmiDayListService = (BeePmiDayListService) getBean("beePmiDayListService");
    	BeePmiDayCommonDTO beePmiDayCommonDTO = beePmiDayListForm.getBeePmiDayCommonDTO();
    	Map map = getMapData(request);

    	beePmiDayCommonDTO.setIsLoadMaxCount(true);
    	if("".equals(CommonUtil.convertString(String.valueOf(map.get("nextStartRow"))))){
    		beePmiDayCommonDTO.setIsLoadMaxCount(false);
    	}
    	beePmiDayCommonDTO.setFirstRow(CommonUtil.convertString(String.valueOf(map.get("nextStartRow"))));
    	
        //리스트를 조회한다.
        List resultList = beePmiDayListService.findPmiDayList(beePmiDayCommonDTO, map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
    private void findPmiDayCount(HttpServletRequest request, HttpServletResponse response, BeePmiDayListForm beePmiDayListForm) throws Exception
    {
    	BeePmiDayListService beePmiDayListService = (BeePmiDayListService) getBean("beePmiDayListService");
    	BeePmiDayCommonDTO beePmiDayCommonDTO = beePmiDayListForm.getBeePmiDayCommonDTO();
    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beePmiDayListService.findPmiDayCount(beePmiDayCommonDTO, map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
    private void findPointList(HttpServletRequest request, HttpServletResponse response, BeePmiDayListForm beePmiDayListForm) throws Exception
    {
    	BeePmiDayListService beePmiDayListService = (BeePmiDayListService) getBean("beePmiDayListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beePmiDayListService.findPointList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
    private void findPointHistList(HttpServletRequest request, HttpServletResponse response, BeePmiDayListForm beePmiDayListForm) throws Exception
    {
    	BeePmiDayListService beePmiDayListService = (BeePmiDayListService) getBean("beePmiDayListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beePmiDayListService.findPointHistList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void findWoPointCount(HttpServletRequest request, HttpServletResponse response, BeePmiDayListForm beePmiDayListForm) throws Exception
    {
    	BeePmiDayListService beePmiDayListService = (BeePmiDayListService) getBean("beePmiDayListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beePmiDayListService.findWoPointCount(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void findStatus(HttpServletRequest request, HttpServletResponse response, BeePmiDayListForm beePmiDayListForm) throws Exception
    {
    	BeePmiDayListService beePmiDayListService = (BeePmiDayListService) getBean("beePmiDayListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beePmiDayListService.findStatus(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void insertPoint(HttpServletRequest request, HttpServletResponse response, BeePmiDayListForm beePmiDayListForm) throws Exception
    {
    	BeePmiDayListService beePmiDayListService = (BeePmiDayListService) getBean("beePmiDayListService");
    	List list = getListData(request);
    	int qty = beePmiDayListService.insertPoint(list);
    	setMessage(response, "","OK");
    }
    private void updatePoint(HttpServletRequest request, HttpServletResponse response, BeePmiDayListForm beePmiDayListForm) throws Exception
    {
    	BeePmiDayListService beePmiDayListService = (BeePmiDayListService) getBean("beePmiDayListService");
    	List list = getListData(request);
    	int qty = beePmiDayListService.updatePoint(list);
    	setMessage(response, "","OK");
    }
}
