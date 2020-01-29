package intf.dream.bee.unplan.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import common.util.CommonUtil;
import intf.dream.bee.unplan.dto.BeeUnPlanCommonDTO;
import intf.dream.bee.unplan.form.BeeUnPlanListForm;
import intf.dream.bee.unplan.service.BeeUnPlanListService;

/**
 * 온라인버전 돌발작업 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/beeUnPlanList" name="beeUnPlanListForm"
 *                input="/bee/unplan/beeUnPlanList.jsp" scope="request"
 *                validate="false"
 */
public class BeeUnPlanListAction extends IfOnlineAuthAction
{
    //UNPLAN LIST
    public static final String UNPLAN_LIST 			= "UNPLAN_LIST";
    //UNPLAN LIST COUNT
    public static final String UNPLAN_COUNT			= "UNPLAN_COUNT";
    //UNPLAN DELETE
    public static final String UNPLAN_DELETE 		= "UNPLAN_DELETE";
    //UNPLAN INSERT
    public static final String UNPLAN_INSERT 		= "UNPLAN_INSERT";
    //UNPLAN UPDATE
    public static final String UNPLAN_UPDATE 		= "UNPLAN_UPDATE";

    //WO FAIL LIST
    public static final String WO_FAIL_LIST 		= "WO_FAIL_LIST";
    //WO FAIL DELETE
    public static final String WO_FAIL_DELETE 		= "WO_FAIL_DELETE";
    //WO FAIL INSERT
    public static final String WO_FAIL_INSERT 		= "WO_FAIL_INSERT";
    //WO FAIL UPDATE
    public static final String WO_FAIL_UPDATE 		= "WO_FAIL_UPDATE";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        BeeUnPlanListForm beeUnPlanListForm = (BeeUnPlanListForm) form;
        
        switch (beeUnPlanListForm.getServiceName())
        {
            case BeeUnPlanListAction.UNPLAN_LIST:
            	findUnPlanList(request, response, beeUnPlanListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeUnPlanListAction.UNPLAN_COUNT:
            	findUnPlanCount(request, response, beeUnPlanListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeUnPlanListAction.UNPLAN_DELETE:
            	deleteUnPlan(request, response, beeUnPlanListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeUnPlanListAction.UNPLAN_INSERT:
            	insertUnPlan(request, response, beeUnPlanListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeUnPlanListAction.UNPLAN_UPDATE:
            	updateUnPlan(request, response, beeUnPlanListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            	
            case BeeUnPlanListAction.WO_FAIL_LIST:
            	findWoFailList(request, response, beeUnPlanListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeUnPlanListAction.WO_FAIL_DELETE:
            	deleteWoFail(request, response, beeUnPlanListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeUnPlanListAction.WO_FAIL_INSERT:
            	insertWoFailList(request, response, beeUnPlanListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeUnPlanListAction.WO_FAIL_UPDATE:
            	updateWoFailList(request, response, beeUnPlanListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            	
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }

    private void findUnPlanList(HttpServletRequest request, HttpServletResponse response, BeeUnPlanListForm beeUnPlanListForm) throws Exception
    {
    	BeeUnPlanListService beeUnPlanListService = (BeeUnPlanListService) getBean("beeUnPlanListService");
    	BeeUnPlanCommonDTO beeUnPlanCommonDTO = beeUnPlanListForm.getBeeUnPlanCommonDTO();
    	Map map = getMapData(request);
    	
    	beeUnPlanCommonDTO.setIsLoadMaxCount(true);
    	if("".equals(CommonUtil.convertString(String.valueOf(map.get("nextStartRow"))))){
    		beeUnPlanCommonDTO.setIsLoadMaxCount(false);
    	}
    	beeUnPlanCommonDTO.setFirstRow(CommonUtil.convertString(String.valueOf(map.get("nextStartRow"))));

        //리스트를 조회한다.
        List resultList = beeUnPlanListService.findUnPlanList(beeUnPlanCommonDTO, map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }

    private void findUnPlanCount(HttpServletRequest request, HttpServletResponse response, BeeUnPlanListForm beeUnPlanListForm) throws Exception
    {
    	BeeUnPlanListService beeUnPlanListService = (BeeUnPlanListService) getBean("beeUnPlanListService");
    	BeeUnPlanCommonDTO beeUnPlanCommonDTO = beeUnPlanListForm.getBeeUnPlanCommonDTO();
    	Map map = getMapData(request);
        //리스트를 조회한다.
        List resultList = beeUnPlanListService.findUnPlanCount(beeUnPlanCommonDTO, map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
    private void deleteUnPlan(HttpServletRequest request, HttpServletResponse response, BeeUnPlanListForm beeUnPlanListForm) throws Exception
    {
    	BeeUnPlanListService beeUnPlanListService = (BeeUnPlanListService) getBean("beeUnPlanListService");
    	List list = getListData(request);
    	int qty = beeUnPlanListService.deleteUnPlan(list);
    	setMessage(response, "","OK");
    }
    private void insertUnPlan(HttpServletRequest request, HttpServletResponse response, BeeUnPlanListForm beeUnPlanListForm) throws Exception
    {
    	BeeUnPlanListService beeUnPlanListService = (BeeUnPlanListService) getBean("beeUnPlanListService");
    	List list = getListData(request);
    	int qty = beeUnPlanListService.insertUnPlan(list);
    	setMessage(response, "","OK");
    }
    private void updateUnPlan(HttpServletRequest request, HttpServletResponse response, BeeUnPlanListForm beeUnPlanListForm) throws Exception
    {
    	BeeUnPlanListService beeUnPlanListService = (BeeUnPlanListService) getBean("beeUnPlanListService");
    	List list = getListData(request);
    	int qty = beeUnPlanListService.updateUnPlan(list);
    	setMessage(response, "","OK");
    }
    

    private void findWoFailList(HttpServletRequest request, HttpServletResponse response, BeeUnPlanListForm beeUnPlanListForm) throws Exception
    {
    	BeeUnPlanListService beeUnPlanListService = (BeeUnPlanListService) getBean("beeUnPlanListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beeUnPlanListService.findWoFailList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void deleteWoFail(HttpServletRequest request, HttpServletResponse response, BeeUnPlanListForm beeUnPlanListForm) throws Exception
    {
    	BeeUnPlanListService beeUnPlanListService = (BeeUnPlanListService) getBean("beeUnPlanListService");
    	List list = getListData(request);
    	int qty = beeUnPlanListService.deleteWoFail(list);
    	setMessage(response, "","OK");
    }
    private void insertWoFailList(HttpServletRequest request, HttpServletResponse response, BeeUnPlanListForm beeUnPlanListForm) throws Exception
    {
    	BeeUnPlanListService beeUnPlanListService = (BeeUnPlanListService) getBean("beeUnPlanListService");
    	List list = getListData(request);
    	int qty = beeUnPlanListService.insertWoFailList(list);
    	setMessage(response, "","OK");
    }
    private void updateWoFailList(HttpServletRequest request, HttpServletResponse response, BeeUnPlanListForm beeUnPlanListForm) throws Exception
    {
    	BeeUnPlanListService beeUnPlanListService = (BeeUnPlanListService) getBean("beeUnPlanListService");
    	List list = getListData(request);
    	int qty = beeUnPlanListService.updateWoFailList(list);
    	setMessage(response, "","OK");
    }
}
