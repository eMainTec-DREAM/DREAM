package intf.dream.android.offline.pmi.patrol.action;


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
import intf.dream.android.offline.pmi.patrol.form.AnIfPmiPatrolListForm;
import intf.dream.android.offline.pmi.patrol.service.AnIfPmiPatrolListService;

/**
 * 순회점검 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/anIfPmiPatrolList" name="anIfPmiPatrolListForm"
 *                input="/android/offline/pmi/patrol/anIfPmiPatrolList.jsp" scope="request"
 *                validate="false"
 */
public class AnIfPmiPatrolListAction extends IfAuthAction
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
        AnIfPmiPatrolListForm anIfPmiPatrolListForm = (AnIfPmiPatrolListForm) form;
        
        switch (anIfPmiPatrolListForm.getServiceName())
        {
            case AnIfPmiPatrolListAction.INSP_SCHED:
            	findSchedList(request, response, anIfPmiPatrolListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 

            case AnIfPmiPatrolListAction.INSP_PMLST:
            	findPmlstList(request, response, anIfPmiPatrolListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 

            case AnIfPmiPatrolListAction.INSPLIST_SAVE:
            	saveInspList(request, response, anIfPmiPatrolListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 

            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findSchedList(HttpServletRequest request, HttpServletResponse response, AnIfPmiPatrolListForm anIfPmiPatrolListForm)  throws Exception
    {
    	AnIfPmiPatrolListService anIfPmiPatrolListService = (AnIfPmiPatrolListService) getBean("anIfPmiPatrolListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anIfPmiPatrolListService.findSchedList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void findPmlstList(HttpServletRequest request, HttpServletResponse response, AnIfPmiPatrolListForm anIfPmiPatrolListForm)  throws Exception
    {
    	AnIfPmiPatrolListService anIfPmiPatrolListService = (AnIfPmiPatrolListService) getBean("anIfPmiPatrolListService");

    	Map map = getMapData(request);
    	//리스트를 조회한다.
        List list = anIfPmiPatrolListService.findPmlstList(map);
        List resultList = new ArrayList();
        
        if (list.size() > 0) {
        	List equipList = anIfPmiPatrolListService.findPmequipList(map);
        	List pointList = anIfPmiPatrolListService.findPmpointList(map);
        	
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
    private void saveInspList(HttpServletRequest request, HttpServletResponse response, AnIfPmiPatrolListForm anIfPmiPatrolListForm)  throws Exception
    {
    	AnIfPmiPatrolListService anIfPmiPatrolListService = (AnIfPmiPatrolListService) getBean("anIfPmiPatrolListService");

    	List list = getListData(request);
    	int qty = anIfPmiPatrolListService.saveInspList(list);
    	setMessage(response, "","OK");
    }
}
