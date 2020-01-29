package intf.dream.bee.pmi.patrol.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import common.util.CommonUtil;
import intf.dream.bee.pmi.patrol.dto.BeePmiPatrolCommonDTO;
import intf.dream.bee.pmi.patrol.form.BeePmiPatrolListForm;
import intf.dream.bee.pmi.patrol.service.BeePmiPatrolListService;

/**
 * 온라인버전 순회점검
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/beePmiPatrolList" name="beePmiPatrolListForm"
 *                input="/bee/pmi/patrol/beePmiPatrolList.jsp" scope="request"
 *                validate="false"
 */
public class BeePmiPatrolListAction extends IfOnlineAuthAction
{
    //PmiPatrol List
    public static final String PMI_LIST 					= "PMI_LIST";
    //PmiPatrol List Count
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
        BeePmiPatrolListForm beePmiPatrolListForm = (BeePmiPatrolListForm) form;
        
        switch (beePmiPatrolListForm.getServiceName())
        {
            case BeePmiPatrolListAction.PMI_LIST:
            	findPmiPatrolList(request, response, beePmiPatrolListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeePmiPatrolListAction.PMI_COUNT:
            	findPmiPatrolCount(request, response, beePmiPatrolListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeePmiPatrolListAction.POINT_LIST:
            	findPointList(request, response, beePmiPatrolListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeePmiPatrolListAction.POINT_HIST_LIST:
            	findPointHistList(request, response, beePmiPatrolListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeePmiPatrolListAction.POINT_COUNT:
            	findWoPointCount(request, response, beePmiPatrolListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeePmiPatrolListAction.POINT_INSERT:
            	insertPoint(request, response, beePmiPatrolListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeePmiPatrolListAction.POINT_UPDATE:
            	updatePoint(request, response, beePmiPatrolListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeePmiPatrolListAction.CHECK_STATUS:
            	findStatus(request, response, beePmiPatrolListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findPmiPatrolList(HttpServletRequest request, HttpServletResponse response, BeePmiPatrolListForm beePmiPatrolListForm) throws Exception
    {
    	BeePmiPatrolListService beePmiPatrolListService = (BeePmiPatrolListService) getBean("beePmiPatrolListService");
    	BeePmiPatrolCommonDTO beePmiPatrolCommonDTO = beePmiPatrolListForm.getBeePmiPatrolCommonDTO();
    	Map map = getMapData(request);
    	
    	beePmiPatrolCommonDTO.setIsLoadMaxCount(true);
    	if("".equals(CommonUtil.convertString(String.valueOf(map.get("nextStartRow"))))){
    		beePmiPatrolCommonDTO.setIsLoadMaxCount(false);
    	}
    	beePmiPatrolCommonDTO.setFirstRow(CommonUtil.convertString(String.valueOf(map.get("nextStartRow"))));

        //리스트를 조회한다.
        List resultList = beePmiPatrolListService.findPmiPatrolList(beePmiPatrolCommonDTO, map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
    private void findPmiPatrolCount(HttpServletRequest request, HttpServletResponse response, BeePmiPatrolListForm beePmiPatrolListForm) throws Exception
    {
    	BeePmiPatrolListService beePmiPatrolListService = (BeePmiPatrolListService) getBean("beePmiPatrolListService");
    	BeePmiPatrolCommonDTO beePmiPatrolCommonDTO = beePmiPatrolListForm.getBeePmiPatrolCommonDTO();
    	Map map = getMapData(request);
    	
        //리스트를 조회한다.
        List resultList = beePmiPatrolListService.findPmiPatrolCount(beePmiPatrolCommonDTO, map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
    private void findPointList(HttpServletRequest request, HttpServletResponse response, BeePmiPatrolListForm beePmiPatrolListForm) throws Exception
    {
    	BeePmiPatrolListService beePmiPatrolListService = (BeePmiPatrolListService) getBean("beePmiPatrolListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beePmiPatrolListService.findPointList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
    private void findPointHistList(HttpServletRequest request, HttpServletResponse response, BeePmiPatrolListForm beePmiPatrolListForm) throws Exception
    {
    	BeePmiPatrolListService beePmiPatrolListService = (BeePmiPatrolListService) getBean("beePmiPatrolListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beePmiPatrolListService.findPointHistList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void findWoPointCount(HttpServletRequest request, HttpServletResponse response, BeePmiPatrolListForm beePmiPatrolListForm) throws Exception
    {
    	BeePmiPatrolListService beePmiPatrolListService = (BeePmiPatrolListService) getBean("beePmiPatrolListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beePmiPatrolListService.findWoPointCount(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void findStatus(HttpServletRequest request, HttpServletResponse response, BeePmiPatrolListForm beePmiPatrolListForm) throws Exception
    {
    	BeePmiPatrolListService beePmiPatrolListService = (BeePmiPatrolListService) getBean("beePmiPatrolListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beePmiPatrolListService.findStatus(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void insertPoint(HttpServletRequest request, HttpServletResponse response, BeePmiPatrolListForm beePmiPatrolListForm) throws Exception
    {
    	BeePmiPatrolListService beePmiPatrolListService = (BeePmiPatrolListService) getBean("beePmiPatrolListService");
    	List list = getListData(request);
    	int qty = beePmiPatrolListService.insertPoint(list);
    	setMessage(response, "","OK");
    }
    private void updatePoint(HttpServletRequest request, HttpServletResponse response, BeePmiPatrolListForm beePmiPatrolListForm) throws Exception
    {
    	BeePmiPatrolListService beePmiPatrolListService = (BeePmiPatrolListService) getBean("beePmiPatrolListService");
    	List list = getListData(request);
    	int qty = beePmiPatrolListService.updatePoint(list);
    	setMessage(response, "","OK");
    }
}
