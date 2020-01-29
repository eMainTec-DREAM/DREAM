package intf.dream.android.offline.macal.action;


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
import intf.dream.android.offline.macal.form.AnIfCalListForm;
import intf.dream.android.offline.macal.service.AnIfCalListService;

/**
 * inspection 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/anIfCalList" name="anIfCalListForm"
 *                input="/android/offline/macal/anIfCalList.jsp" scope="request"
 *                validate="false"
 */
public class AnIfCalListAction extends IfAuthAction
{
    //WORKORDER
    public static final String WORKORDER_FIND 			= "CAL_WORKORDER";
    //CAL_UPLOAD
    public static final String WORKORDER_SAVE			= "WORKORDER_SAVE";
    
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AnIfCalListForm anIfCalListForm = (AnIfCalListForm) form;
        
        switch (anIfCalListForm.getServiceName())
        {
            case AnIfCalListAction.WORKORDER_FIND:
            	findHdrList(request, response, anIfCalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            	
            case AnIfCalListAction.WORKORDER_SAVE:
            	saveWorkorder(request, response, anIfCalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findHdrList(HttpServletRequest request, HttpServletResponse response, AnIfCalListForm anIfCalListForm) throws Exception
    {
    	AnIfCalListService anIfCalListService = (AnIfCalListService) getBean("anIfCalListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List list = anIfCalListService.findHdrList(map);
        List resultList = new ArrayList();
        

		if (list.size() > 0) {
			List craftList = anIfCalListService.findWocraftList(map);
			List equipList = anIfCalListService.findWoequipList(map);
			List calibList = anIfCalListService.findWocalibList(map);
			List calibValueList = anIfCalListService.findWocalibValueList(map);
			List calibStdEqList = anIfCalListService.findWocalibStdEq(map);
			List calibGnlValueList = anIfCalListService.findWocalibGnlValue(map);
			List calibSclValueList = anIfCalListService.findWocalibSclValue(map);
			
			for (Object obj : list) {
				Map objMap = (Map) obj;
				String hdrId = CommonUtil.convertString(objMap.get("WKOR_ID"));

				List sendCraftList = new ArrayList();
				List sendEquipList = new ArrayList();
				List sendCalibList = new ArrayList();
				List sendCalibValueList = new ArrayList();
				List sendCalibStdEqList = new ArrayList();
				List sendCalibGnlValueList = new ArrayList();
				List sendCalibSclValueList = new ArrayList();
				
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
				for(Object itemObj : calibList){
					Map itemObjMap = (Map)itemObj;
		    		String item = CommonUtil.convertString(itemObjMap.get("WKOR_ID"));
		    		if(hdrId.equals(item)) sendCalibList.add(itemObjMap);
		    	}
				for(Object itemObj : calibValueList){
					Map itemObjMap = (Map)itemObj;
		    		String item = CommonUtil.convertString(itemObjMap.get("WKOR_ID"));
		    		if(hdrId.equals(item)) sendCalibValueList.add(itemObjMap);
		    	}
				for(Object itemObj : calibStdEqList){
					Map itemObjMap = (Map)itemObj;
		    		String item = CommonUtil.convertString(itemObjMap.get("WKOR_ID"));
		    		if(hdrId.equals(item)) sendCalibStdEqList.add(itemObjMap);
		    	}
				for(Object itemObj : calibGnlValueList){
					Map itemObjMap = (Map)itemObj;
		    		String item = CommonUtil.convertString(itemObjMap.get("WKOR_ID"));
		    		if(hdrId.equals(item)) sendCalibGnlValueList.add(itemObjMap);
		    	}
				for(Object itemObj : calibSclValueList){
					Map itemObjMap = (Map)itemObj;
		    		String item = CommonUtil.convertString(itemObjMap.get("WKOR_ID"));
		    		if(hdrId.equals(item)) sendCalibSclValueList.add(itemObjMap);
		    	}
				
				objMap.put("CRAFT", CommonUtil.makeNullToBlank(sendCraftList));
				objMap.put("EQUIP", CommonUtil.makeNullToBlank(sendEquipList));
				objMap.put("CALIB", CommonUtil.makeNullToBlank(sendCalibList));
				objMap.put("CALIBVALUE", CommonUtil.makeNullToBlank(sendCalibValueList));
				objMap.put("CALIBSTDEQ", CommonUtil.makeNullToBlank(sendCalibStdEqList));
				objMap.put("CALIBGNLVALUE", CommonUtil.makeNullToBlank(sendCalibGnlValueList));
				objMap.put("CALIBSCLVALUE", CommonUtil.makeNullToBlank(sendCalibSclValueList));
				
				resultList.add(objMap);
			}
		}
        
        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
    private void saveWorkorder(HttpServletRequest request, HttpServletResponse response, AnIfCalListForm anIfCalListForm)  throws Exception
    {
    	AnIfCalListService anIfCalListService = (AnIfCalListService) getBean("anIfCalListService");

    	List list = getListData(request);
    	int qty = anIfCalListService.saveWorkorder(list);
    	setMessage(response, "","OK");
    }
}
