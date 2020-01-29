package intf.dream.bee.finder.calibration.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.bee.finder.calibration.form.BeeFinderCalibrationListForm;
import intf.dream.bee.finder.calibration.service.BeeFinderCalibrationListService;

/**
 * finder - Calibration 
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/beeFinderCalibrationList" name="beeFinderCalibrationListForm"
 *                input="/bee/finder/calibration/beeFinderCalibrationList.jsp" scope="request"
 *                validate="false"
 */
public class BeeFinderCalibrationListAction extends IfOnlineAuthAction
{
    //CALIBRATION_FIND
    public static final String CALIBRATION_FIND 			= "CALIBRATION";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        BeeFinderCalibrationListForm beeFinderCalibrationListForm = (BeeFinderCalibrationListForm) form;
        
        switch (beeFinderCalibrationListForm.getServiceName())
        {
            case BeeFinderCalibrationListAction.CALIBRATION_FIND:
            	findCalibrationList(request, response, beeFinderCalibrationListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findCalibrationList(HttpServletRequest request, HttpServletResponse response, BeeFinderCalibrationListForm beeFinderCalibrationListForm) throws Exception
    {
    	BeeFinderCalibrationListService beeFinderCalibrationListService = (BeeFinderCalibrationListService) getBean("beeFinderCalibrationListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beeFinderCalibrationListService.findCalibrationList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
}
