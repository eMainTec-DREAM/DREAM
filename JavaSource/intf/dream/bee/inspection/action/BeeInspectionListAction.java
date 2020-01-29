package intf.dream.bee.inspection.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import common.util.CommonUtil;
import intf.dream.bee.inspection.dto.BeeInspectionCommonDTO;
import intf.dream.bee.inspection.form.BeeInspectionListForm;
import intf.dream.bee.inspection.service.BeeInspectionListService;

/**
 * 온라인버전 점검 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/beeInspectionList" name="beeInspectionListForm"
 *                input="/bee/inspection/beeInspectionList.jsp" scope="request"
 *                validate="false"
 */
public class BeeInspectionListAction extends IfOnlineAuthAction
{
    //Inspection List
    public static final String INSPECTION_LIST 				= "INSPECTION_LIST";
    //Inspection List Cunt
    public static final String INSPECTION_COUNT 			= "INSPECTION_COUNT";
    //Point List
    public static final String POINT_LIST					= "POINT_LIST";
    //Point hist List
    public static final String POINT_HIST_LIST				= "POINT_HIST_LIST";
    //Wopoint count
    public static final String WOPOINT_COUNT				= "WOPOINT_COUNT";
    //INSERT POINT
    public static final String POINT_INSERT					= "POINT_INSERT";
    //UPDATE POINT
    public static final String POINT_UPDATE					= "POINT_UPDATE";
    //CHECK STATUS
    public static final String CHECK_STATUS					= "CHECK_STATUS";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        BeeInspectionListForm beeInspectionListForm = (BeeInspectionListForm) form;
        
        switch (beeInspectionListForm.getServiceName())
        {
            case BeeInspectionListAction.INSPECTION_LIST:
            	findInspectionList(request, response, beeInspectionListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeeInspectionListAction.INSPECTION_COUNT:
            	findInspectionCount(request, response, beeInspectionListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeeInspectionListAction.POINT_LIST:
            	findPointList(request, response, beeInspectionListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeeInspectionListAction.POINT_HIST_LIST:
            	findPointHistList(request, response, beeInspectionListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeeInspectionListAction.WOPOINT_COUNT:
            	findWoPointCount(request, response, beeInspectionListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeeInspectionListAction.POINT_INSERT:
            	insertPoint(request, response, beeInspectionListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeeInspectionListAction.POINT_UPDATE:
            	updatePoint(request, response, beeInspectionListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeeInspectionListAction.CHECK_STATUS:
            	findStatus(request, response, beeInspectionListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findInspectionList(HttpServletRequest request, HttpServletResponse response, BeeInspectionListForm beeInspectionListForm) throws Exception
    {
    	BeeInspectionListService beeInspectionListService = (BeeInspectionListService) getBean("beeInspectionListService");
    	BeeInspectionCommonDTO beeInspectionCommonDTO = beeInspectionListForm.getBeeInspectionCommonDTO();
    	Map map = getMapData(request);

    	beeInspectionCommonDTO.setIsLoadMaxCount(true);
    	if("".equals(CommonUtil.convertString(String.valueOf(map.get("nextStartRow"))))){
    		beeInspectionCommonDTO.setIsLoadMaxCount(false);
    	}
    	beeInspectionCommonDTO.setFirstRow(CommonUtil.convertString(String.valueOf(map.get("nextStartRow"))));
    	
        //리스트를 조회한다.
        List resultList = beeInspectionListService.findInspectionList(beeInspectionCommonDTO, map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
    private void findInspectionCount(HttpServletRequest request, HttpServletResponse response, BeeInspectionListForm beeInspectionListForm) throws Exception
    {
    	BeeInspectionListService beeInspectionListService = (BeeInspectionListService) getBean("beeInspectionListService");
    	BeeInspectionCommonDTO beeInspectionCommonDTO = beeInspectionListForm.getBeeInspectionCommonDTO();
    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beeInspectionListService.findInspectionCount(beeInspectionCommonDTO, map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
    private void findPointList(HttpServletRequest request, HttpServletResponse response, BeeInspectionListForm beeInspectionListForm) throws Exception
    {
    	BeeInspectionListService beeInspectionListService = (BeeInspectionListService) getBean("beeInspectionListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beeInspectionListService.findPointList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
    private void findPointHistList(HttpServletRequest request, HttpServletResponse response, BeeInspectionListForm beeInspectionListForm) throws Exception
    {
    	BeeInspectionListService beeInspectionListService = (BeeInspectionListService) getBean("beeInspectionListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beeInspectionListService.findPointHistList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void findWoPointCount(HttpServletRequest request, HttpServletResponse response, BeeInspectionListForm beeInspectionListForm) throws Exception
    {
    	BeeInspectionListService beeInspectionListService = (BeeInspectionListService) getBean("beeInspectionListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beeInspectionListService.findWoPointCount(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void findStatus(HttpServletRequest request, HttpServletResponse response, BeeInspectionListForm beeInspectionListForm) throws Exception
    {
    	BeeInspectionListService beeInspectionListService = (BeeInspectionListService) getBean("beeInspectionListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beeInspectionListService.findStatus(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void insertPoint(HttpServletRequest request, HttpServletResponse response, BeeInspectionListForm beeInspectionListForm) throws Exception
    {
    	BeeInspectionListService beeInspectionListService = (BeeInspectionListService) getBean("beeInspectionListService");
    	List list = getListData(request);
    	int qty = beeInspectionListService.insertPoint(list);
    	setMessage(response, "","OK");
    }
    private void updatePoint(HttpServletRequest request, HttpServletResponse response, BeeInspectionListForm beeInspectionListForm) throws Exception
    {
    	BeeInspectionListService beeInspectionListService = (BeeInspectionListService) getBean("beeInspectionListService");
    	List list = getListData(request);
    	int qty = beeInspectionListService.updatePoint(list);
    	setMessage(response, "","OK");
    }
}
