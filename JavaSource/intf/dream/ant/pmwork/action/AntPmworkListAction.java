package intf.dream.ant.pmwork.action;


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
import intf.dream.ant.pmwork.form.AntPmworkListForm;
import intf.dream.ant.pmwork.service.AntPmworkListService;

/**
 * inspection 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/antPmworkList" name="antPmworkListForm"
 *                input="/ant/pmwork/antPmworkList.jsp" scope="request"
 *                validate="false"
 */
public class AntPmworkListAction extends IfAuthAction
{
    //WORKORDER
    public static final String WORKORDER_FIND 			= "PMWORK_WORKORDER";
    //PMWORK_UPLOAD
    public static final String WORKORDER_SAVE			= "WORKORDER_SAVE";
    
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AntPmworkListForm antPmworkListForm = (AntPmworkListForm) form;
        
        switch (antPmworkListForm.getServiceName())
        {
            case AntPmworkListAction.WORKORDER_FIND:
            	findHdrList(request, response, antPmworkListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AntPmworkListAction.WORKORDER_SAVE:
            	saveWorkorder(request, response, antPmworkListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findHdrList(HttpServletRequest request, HttpServletResponse response, AntPmworkListForm antPmworkListForm) throws Exception
    {
    	AntPmworkListService antPmworkListService = (AntPmworkListService) getBean("antPmworkListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List list = antPmworkListService.findHdrList(map);
        List resultList = new ArrayList();
        
        if (list.size() > 0) {
        	List craftList = antPmworkListService.findWocraftList(map);
			List equipList = antPmworkListService.findWoequipList(map);
			List partsList = antPmworkListService.findWopartsList(map);
			
			for (Object obj : list) {
				Map objMap = (Map) obj;
				String hdrId = CommonUtil.convertString(objMap.get("WKOR_ID"));
				
				List sendCraftList = new ArrayList();
				List sendEquipList = new ArrayList();
				List sendPartsList = new ArrayList();
				
				for(Object itemObj : craftList){
					Map itemObjMap = (Map)itemObj;
		    		String item = CommonUtil.convertString(itemObjMap.get("WKOR_ID"));
		    		if(hdrId.equals(item)) sendCraftList.add(itemObjMap);
		    	}
				for(Object itemObj : equipList){
					Map itemObjMap = (Map)itemObj;
		    		String item = CommonUtil.convertString(itemObjMap.get("WKOR_ID"));
		    		if(hdrId.equals(item)) sendEquipList.add(itemObjMap);
		    	}
				for(Object itemObj : partsList){
					Map itemObjMap = (Map)itemObj;
		    		String item = CommonUtil.convertString(itemObjMap.get("WKOR_ID"));
		    		if(hdrId.equals(item)) sendPartsList.add(itemObjMap);
		    	}
				objMap.put("CRAFT", CommonUtil.makeNullToBlank(sendCraftList));
				objMap.put("EQUIP", CommonUtil.makeNullToBlank(sendEquipList));
				objMap.put("PARTS", CommonUtil.makeNullToBlank(sendPartsList));

				resultList.add(objMap);
			}
        }

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void saveWorkorder(HttpServletRequest request, HttpServletResponse response, AntPmworkListForm antPmworkListForm)  throws Exception
    {
    	AntPmworkListService antPmworkListService = (AntPmworkListService) getBean("antPmworkListService");

    	List list = getListData(request);
    	int qty = antPmworkListService.saveWorkorder(list);
    	setMessage(response, "","OK");
    }
}
