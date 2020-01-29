package intf.dream.ant.inspection.action;


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
import intf.dream.ant.inspection.form.AntInspectionListForm;
import intf.dream.ant.inspection.service.AntInspectionListService;

/**
 * inspection 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/antInspectionList" name="antInspectionListForm"
 *                input="/ant/inspection/antInspectionList.jsp" scope="request"
 *                validate="false"
 */
public class AntInspectionListAction extends IfAuthAction
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
        AntInspectionListForm antInspectionListForm = (AntInspectionListForm) form;
        
        switch (antInspectionListForm.getServiceName())
        {
            case AntInspectionListAction.SCHED_FIND:
            	findSchedList(request, response, antInspectionListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 

            case AntInspectionListAction.PMLST_FIND:
            	findPmlstList(request, response, antInspectionListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 

            case AntInspectionListAction.PMEQUIP_FIND:
            	findPmequipList(request, response, antInspectionListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 

            case AntInspectionListAction.PMPOINT_FIND:
            	findPmpointList(request, response, antInspectionListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 

            case AntInspectionListAction.WORKORDER_SAVE:
            	saveWorkorder(request, response, antInspectionListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 

            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findSchedList(HttpServletRequest request, HttpServletResponse response, AntInspectionListForm antInspectionListForm)  throws Exception
    {
    	AntInspectionListService antInspectionListService = (AntInspectionListService) getBean("antInspectionListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = antInspectionListService.findSchedList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void findPmlstList(HttpServletRequest request, HttpServletResponse response, AntInspectionListForm antInspectionListForm)  throws Exception
    {
    	AntInspectionListService antInspectionListService = (AntInspectionListService) getBean("antInspectionListService");

    	Map map = getMapData(request);
    	//리스트를 조회한다.
        List list = antInspectionListService.findPmlstList(map);
        List resultList = new ArrayList();
        
        if (list.size() > 0) {
        	List equipList = antInspectionListService.findPmequipList(map);
        	List pointList = antInspectionListService.findPmpointList(map);
        	
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
    private void findPmequipList(HttpServletRequest request, HttpServletResponse response, AntInspectionListForm antInspectionListForm)  throws Exception
    {
    	AntInspectionListService antInspectionListService = (AntInspectionListService) getBean("antInspectionListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = antInspectionListService.findPmequipList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void findPmpointList(HttpServletRequest request, HttpServletResponse response, AntInspectionListForm antInspectionListForm)  throws Exception
    {
    	AntInspectionListService antInspectionListService = (AntInspectionListService) getBean("antInspectionListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = antInspectionListService.findPmpointList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void saveWorkorder(HttpServletRequest request, HttpServletResponse response, AntInspectionListForm antInspectionListForm)  throws Exception
    {
    	AntInspectionListService antInspectionListService = (AntInspectionListService) getBean("antInspectionListService");

    	List list = getListData(request);
    	int qty = antInspectionListService.saveWorkorder(list);
    	setMessage(response, "","OK");
    }
}
