package intf.dream.bee.pmi.routine.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import common.util.CommonUtil;
import intf.dream.bee.pmi.routine.dto.BeePmiRoutineCommonDTO;
import intf.dream.bee.pmi.routine.form.BeePmiRoutineListForm;
import intf.dream.bee.pmi.routine.service.BeePmiRoutineListService;

/**
 * 온라인버전 정기점검
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/beePmiRoutineList" name="beePmiRoutineListForm"
 *                input="/bee/pmi/routine/beePmiRoutineList.jsp" scope="request"
 *                validate="false"
 */
public class BeePmiRoutineListAction extends IfOnlineAuthAction
{
    //PmiRoutine List
    public static final String PMI_LIST 					= "PMI_LIST";
    //PmiRoutine List Count
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
        BeePmiRoutineListForm beePmiRoutineListForm = (BeePmiRoutineListForm) form;
        
        switch (beePmiRoutineListForm.getServiceName())
        {
            case BeePmiRoutineListAction.PMI_LIST:
            	findPmiRoutineList(request, response, beePmiRoutineListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeePmiRoutineListAction.PMI_COUNT:
            	findPmiRoutineCount(request, response, beePmiRoutineListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeePmiRoutineListAction.POINT_LIST:
            	findPointList(request, response, beePmiRoutineListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeePmiRoutineListAction.POINT_HIST_LIST:
            	findPointHistList(request, response, beePmiRoutineListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeePmiRoutineListAction.POINT_COUNT:
            	findWoPointCount(request, response, beePmiRoutineListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeePmiRoutineListAction.POINT_INSERT:
            	insertPoint(request, response, beePmiRoutineListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeePmiRoutineListAction.POINT_UPDATE:
            	updatePoint(request, response, beePmiRoutineListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeePmiRoutineListAction.CHECK_STATUS:
            	findStatus(request, response, beePmiRoutineListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findPmiRoutineList(HttpServletRequest request, HttpServletResponse response, BeePmiRoutineListForm beePmiRoutineListForm) throws Exception
    {
    	BeePmiRoutineListService beePmiRoutineListService = (BeePmiRoutineListService) getBean("beePmiRoutineListService");
    	BeePmiRoutineCommonDTO beePmiRoutineCommonDTO = beePmiRoutineListForm.getBeePmiRoutineCommonDTO();
    	Map map = getMapData(request);
    	
    	beePmiRoutineCommonDTO.setIsLoadMaxCount(true);
    	if("".equals(CommonUtil.convertString(String.valueOf(map.get("nextStartRow"))))){
    		beePmiRoutineCommonDTO.setIsLoadMaxCount(false);
    	}
    	beePmiRoutineCommonDTO.setFirstRow(CommonUtil.convertString(String.valueOf(map.get("nextStartRow"))));

        //리스트를 조회한다.
        List resultList = beePmiRoutineListService.findPmiRoutineList(beePmiRoutineCommonDTO, map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
    private void findPmiRoutineCount(HttpServletRequest request, HttpServletResponse response, BeePmiRoutineListForm beePmiRoutineListForm) throws Exception
    {
    	BeePmiRoutineListService beePmiRoutineListService = (BeePmiRoutineListService) getBean("beePmiRoutineListService");
    	BeePmiRoutineCommonDTO beePmiRoutineCommonDTO = beePmiRoutineListForm.getBeePmiRoutineCommonDTO();
    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beePmiRoutineListService.findPmiRoutineCount(beePmiRoutineCommonDTO, map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
    private void findPointList(HttpServletRequest request, HttpServletResponse response, BeePmiRoutineListForm beePmiRoutineListForm) throws Exception
    {
    	BeePmiRoutineListService beePmiRoutineListService = (BeePmiRoutineListService) getBean("beePmiRoutineListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beePmiRoutineListService.findPointList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
    private void findPointHistList(HttpServletRequest request, HttpServletResponse response, BeePmiRoutineListForm beePmiRoutineListForm) throws Exception
    {
    	BeePmiRoutineListService beePmiRoutineListService = (BeePmiRoutineListService) getBean("beePmiRoutineListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beePmiRoutineListService.findPointHistList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void findWoPointCount(HttpServletRequest request, HttpServletResponse response, BeePmiRoutineListForm beePmiRoutineListForm) throws Exception
    {
    	BeePmiRoutineListService beePmiRoutineListService = (BeePmiRoutineListService) getBean("beePmiRoutineListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beePmiRoutineListService.findWoPointCount(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void findStatus(HttpServletRequest request, HttpServletResponse response, BeePmiRoutineListForm beePmiRoutineListForm) throws Exception
    {
    	BeePmiRoutineListService beePmiRoutineListService = (BeePmiRoutineListService) getBean("beePmiRoutineListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beePmiRoutineListService.findStatus(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void insertPoint(HttpServletRequest request, HttpServletResponse response, BeePmiRoutineListForm beePmiRoutineListForm) throws Exception
    {
    	BeePmiRoutineListService beePmiRoutineListService = (BeePmiRoutineListService) getBean("beePmiRoutineListService");
    	List list = getListData(request);
    	int qty = beePmiRoutineListService.insertPoint(list);
    	setMessage(response, "","OK");
    }
    private void updatePoint(HttpServletRequest request, HttpServletResponse response, BeePmiRoutineListForm beePmiRoutineListForm) throws Exception
    {
    	BeePmiRoutineListService beePmiRoutineListService = (BeePmiRoutineListService) getBean("beePmiRoutineListService");
    	List list = getListData(request);
    	int qty = beePmiRoutineListService.updatePoint(list);
    	setMessage(response, "","OK");
    }
}
