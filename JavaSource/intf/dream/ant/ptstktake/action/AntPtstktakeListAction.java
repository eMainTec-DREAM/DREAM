package intf.dream.ant.ptstktake.action;


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
import intf.dream.ant.ptstktake.form.AntPtstktakeListForm;
import intf.dream.ant.ptstktake.service.AntPtstktakeListService;

/**
 * stktake 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/antPtstktakeList" name="antPtstktakeListForm"
 *                input="/ant/ptstktake/antPtstktakeList.jsp" scope="request"
 *                validate="false"
 */
public class AntPtstktakeListAction extends IfAuthAction
{
    //TAPTSTKTAKELIST
    public static final String STKTAKELIST_FIND 		= "STKTAKELIST";
    //TAPTSTKTAKEITEM SAVE
    public static final String STKTAKEITEM_SAVE 		= "STKTAKEITEM_SAVE";
    
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AntPtstktakeListForm antPtstktakeListForm = (AntPtstktakeListForm) form;
        
        switch (antPtstktakeListForm.getServiceName())
        {

            case AntPtstktakeListAction.STKTAKELIST_FIND:
            	findStktakeList(request, response, antPtstktakeListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 

            case AntPtstktakeListAction.STKTAKEITEM_SAVE:
            	savePtstktakeItem(request, response, antPtstktakeListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findStktakeList(HttpServletRequest request, HttpServletResponse response, AntPtstktakeListForm antPtstktakeListForm) throws Exception
    {
    	AntPtstktakeListService antPtstktakeListService = (AntPtstktakeListService) getBean("antPtstktakeListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List list = antPtstktakeListService.findStktakeList(map);

		List resultList = new ArrayList();
		if (list.size() > 0) {
			List itemList = antPtstktakeListService.findStktakeItem(map);

			for (Object obj : list) {
				Map objMap = (Map) obj;
				String hdrId = CommonUtil.convertString(objMap.get("PTSTKTAKELIST_ID"));
				
				List sendItemList = new ArrayList();
				for(Object itemObj : itemList){
					Map itemObjMap = (Map)itemObj;
		    		String item = CommonUtil.convertString(itemObjMap.get("PTSTKTAKELIST_ID"));
		    		if(hdrId.equals(item)) sendItemList.add(itemObjMap);
		    	}
				objMap.put("ITEM", CommonUtil.makeNullToBlank(sendItemList));

				resultList.add(objMap);
			}
		}
        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
    private void savePtstktakeItem(HttpServletRequest request, HttpServletResponse response, AntPtstktakeListForm antPtstktakeListForm)  throws Exception
    {
    	AntPtstktakeListService antPtstktakeListService = (AntPtstktakeListService) getBean("antPtstktakeListService");

    	List list = getListData(request);
    	int qty = antPtstktakeListService.savePtstktakeItem(list);
    	setMessage(response, "","OK");
    }
}
