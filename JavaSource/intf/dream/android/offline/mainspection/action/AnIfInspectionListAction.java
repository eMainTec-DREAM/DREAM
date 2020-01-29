package intf.dream.android.offline.mainspection.action;


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
import intf.dream.android.offline.mainspection.form.AnIfInspectionListForm;
import intf.dream.android.offline.mainspection.service.AnIfInspectionListService;

/**
 * inspection 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/anIfInspectionList" name="anIfInspectionListForm"
 *                input="/android/offline/mainspection/anIfInspectionList.jsp" scope="request"
 *                validate="false"
 */
public class AnIfInspectionListAction extends IfAuthAction
{
    //SCHED_FIND
    public static final String SCHED_FIND 					= "INSPECTION_SCHED";
    //PMLST_FIND
    public static final String PMLST_FIND	 				= "INSPECTION_PMLST";
    //PMEQUIP_FIND
    public static final String PMEQUIP_FIND 				= "INSPECTION_PMEQUIP";
    //PMPOINT_FIND
    public static final String PMPOINT_FIND 				= "INSPECTION_PMPOINT";
    //INSPECTION_UPLOAD
    public static final String WORKORDER_SAVE				= "WORKORDER_SAVE";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AnIfInspectionListForm anIfInspectionListForm = (AnIfInspectionListForm) form;
        
        switch (anIfInspectionListForm.getServiceName())
        {
            case AnIfInspectionListAction.SCHED_FIND:
            	findSchedList(request, response, anIfInspectionListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 

            case AnIfInspectionListAction.PMLST_FIND:
            	findPmlstList(request, response, anIfInspectionListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 

            case AnIfInspectionListAction.PMEQUIP_FIND:
            	findPmequipList(request, response, anIfInspectionListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 

            case AnIfInspectionListAction.PMPOINT_FIND:
            	findPmpointList(request, response, anIfInspectionListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 

            case AnIfInspectionListAction.WORKORDER_SAVE:
            	saveWorkorder(request, response, anIfInspectionListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 

            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findSchedList(HttpServletRequest request, HttpServletResponse response, AnIfInspectionListForm anIfInspectionListForm)  throws Exception
    {
    	AnIfInspectionListService anIfInspectionListService = (AnIfInspectionListService) getBean("anIfInspectionListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anIfInspectionListService.findSchedList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void findPmlstList(HttpServletRequest request, HttpServletResponse response, AnIfInspectionListForm anIfInspectionListForm)  throws Exception
    {
    	AnIfInspectionListService anIfInspectionListService = (AnIfInspectionListService) getBean("anIfInspectionListService");

    	Map map = getMapData(request);
    	//리스트를 조회한다.
        List list = anIfInspectionListService.findPmlstList(map);
        List resultList = new ArrayList();
        
        if (list.size() > 0) {
        	List equipList = anIfInspectionListService.findPmequipList(map);
        	List pointList = anIfInspectionListService.findPmpointList(map);
        	
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
    private void findPmequipList(HttpServletRequest request, HttpServletResponse response, AnIfInspectionListForm anIfInspectionListForm)  throws Exception
    {
    	AnIfInspectionListService anIfInspectionListService = (AnIfInspectionListService) getBean("anIfInspectionListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anIfInspectionListService.findPmequipList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void findPmpointList(HttpServletRequest request, HttpServletResponse response, AnIfInspectionListForm anIfInspectionListForm)  throws Exception
    {
    	AnIfInspectionListService anIfInspectionListService = (AnIfInspectionListService) getBean("anIfInspectionListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anIfInspectionListService.findPmpointList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void saveWorkorder(HttpServletRequest request, HttpServletResponse response, AnIfInspectionListForm anIfInspectionListForm)  throws Exception
    {
    	AnIfInspectionListService anIfInspectionListService = (AnIfInspectionListService) getBean("anIfInspectionListService");

    	List list = getListData(request);
    	int qty = anIfInspectionListService.saveWorkorder(list);
    	setMessage(response, "","OK");
    }
}
