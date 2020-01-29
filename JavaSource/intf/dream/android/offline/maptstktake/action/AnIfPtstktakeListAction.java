package intf.dream.android.offline.maptstktake.action;


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
import intf.dream.android.offline.maptstktake.form.AnIfPtstktakeListForm;
import intf.dream.android.offline.maptstktake.service.AnIfPtstktakeListService;

/**
 * stktake 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/anIfPtstktakeList" name="anIfPtstktakeListForm"
 *                input="/android/offline/maptstktake/anIfPtstktakeList.jsp" scope="request"
 *                validate="false"
 */
public class AnIfPtstktakeListAction extends IfAuthAction
{
    //TAPTSTKTAKELIST
    public static final String STKTAKELIST_FIND 		= "STKTAKELIST";
    //TAPTSTKTAKEITEM SAVE
    public static final String STKTAKEITEM_SAVE 		= "STKTAKEITEM_SAVE";
    
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AnIfPtstktakeListForm anIfPtstktakeListForm = (AnIfPtstktakeListForm) form;
        
        switch (anIfPtstktakeListForm.getServiceName())
        {

            case AnIfPtstktakeListAction.STKTAKELIST_FIND:
            	findStktakeList(request, response, anIfPtstktakeListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 

            case AnIfPtstktakeListAction.STKTAKEITEM_SAVE:
            	savePtstktakeItem(request, response, anIfPtstktakeListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findStktakeList(HttpServletRequest request, HttpServletResponse response, AnIfPtstktakeListForm anIfPtstktakeListForm) throws Exception
    {
    	AnIfPtstktakeListService anIfPtstktakeListService = (AnIfPtstktakeListService) getBean("anIfPtstktakeListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List list = anIfPtstktakeListService.findStktakeList(map);

		List resultList = new ArrayList();
		if (list.size() > 0) {
			List itemList = anIfPtstktakeListService.findStktakeItem(map);

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
    
    private void savePtstktakeItem(HttpServletRequest request, HttpServletResponse response, AnIfPtstktakeListForm anIfPtstktakeListForm)  throws Exception
    {
    	AnIfPtstktakeListService anIfPtstktakeListService = (AnIfPtstktakeListService) getBean("anIfPtstktakeListService");

    	List list = getListData(request);
    	int qty = anIfPtstktakeListService.savePtstktakeItem(list);
    	setMessage(response, "","OK");
    }
}
