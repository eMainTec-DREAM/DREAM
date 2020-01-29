package intf.dream.ant.pmi.patrol.action;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfAuthAction;
import common.util.CommonUtil;
import intf.dream.ant.pmi.patrol.form.AntPmiPatrolListForm;
import intf.dream.ant.pmi.patrol.service.AntPmiPatrolListService;

/**
 * 순회점검 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/antPmiPatrolList" name="antPmiPatrolListForm"
 *                input="/ant/pmi/patrol/antPmiPatrolList.jsp" scope="request"
 *                validate="false"
 */
public class AntPmiPatrolListAction extends IfAuthAction
{
    //SCHED_FIND
    public static final String INSP_SCHED 				= "INSP_SCHED";
    //PMLST_FIND
    public static final String INSP_PMLST				= "INSP_PMLST";
    //PMI Patrol
    public static final String INSPLIST_SAVE			= "INSPLIST_SAVE";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AntPmiPatrolListForm antPmiPatrolListForm = (AntPmiPatrolListForm) form;
        
        switch (antPmiPatrolListForm.getServiceName())
        {
            case AntPmiPatrolListAction.INSP_SCHED:
            	findSchedList(request, response, antPmiPatrolListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 

            case AntPmiPatrolListAction.INSP_PMLST:
            	findPmlstList(request, response, antPmiPatrolListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 

            case AntPmiPatrolListAction.INSPLIST_SAVE:
            	saveInspList(request, response, antPmiPatrolListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 

            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findSchedList(HttpServletRequest request, HttpServletResponse response, AntPmiPatrolListForm antPmiPatrolListForm)  throws Exception
    {
    	AntPmiPatrolListService antPmiPatrolListService = (AntPmiPatrolListService) getBean("antPmiPatrolListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = antPmiPatrolListService.findSchedList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void findPmlstList(HttpServletRequest request, HttpServletResponse response, AntPmiPatrolListForm antPmiPatrolListForm)  throws Exception
    {
    	AntPmiPatrolListService antPmiPatrolListService = (AntPmiPatrolListService) getBean("antPmiPatrolListService");

    	Map map = getMapData(request);
    	//리스트를 조회한다.
        List list = antPmiPatrolListService.findPmlstList(map);
        List resultList = new ArrayList();
        
        if (list.size() > 0) {
        	List equipList = antPmiPatrolListService.findPmequipList(map);
        	List pointList = antPmiPatrolListService.findPmpointList(map);
        	
        	for (Object obj : list) {
				Map objMap = (Map) obj;
				String hdrId = CommonUtil.convertString(objMap.get("PM_ID"));

				List sendEquipList = new ArrayList();
				List sendPointList = new ArrayList();

				for(Object itemObj : equipList){
					Map itemObjMap = (Map)itemObj;
		    		String item = CommonUtil.convertString(itemObjMap.get("PM_ID"));
		    		if(hdrId.equals(item)) sendEquipList.add(itemObjMap);
		    	}
				for(Object itemObj : pointList){
					Map itemObjMap = (Map)itemObj;
		    		String item = CommonUtil.convertString(itemObjMap.get("PM_ID"));
		    		if(hdrId.equals(item)) sendPointList.add(itemObjMap);
		    	}
				objMap.put("EQUIP", CommonUtil.makeNullToBlank(sendEquipList));
				objMap.put("POINT", CommonUtil.makeNullToBlank(sendPointList));

				resultList.add(objMap);
        	}
        }

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void saveInspList(HttpServletRequest request, HttpServletResponse response, AntPmiPatrolListForm antPmiPatrolListForm)  throws Exception
    {
    	AntPmiPatrolListService antPmiPatrolListService = (AntPmiPatrolListService) getBean("antPmiPatrolListService");

    	List list = getListData(request);
    	int qty = antPmiPatrolListService.saveInspList(list);
    	setMessage(response, "","OK");
    }
}
