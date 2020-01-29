package intf.dream.android.online.finder.calibration.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.android.online.finder.calibration.form.AnOnFinderCalibrationListForm;
import intf.dream.android.online.finder.calibration.service.AnOnFinderCalibrationListService;

/**
 * finder - Calibration 
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/anOnFinderCalibrationList" name="anOnFinderCalibrationListForm"
 *                input="/android/online/finder/calibration/anOnFinderCalibrationList.jsp" scope="request"
 *                validate="false"
 */
public class AnOnFinderCalibrationListAction extends IfOnlineAuthAction
{
    //CALIBRATION_FIND
    public static final String CALIBRATION_FIND 			= "CALIBRATION";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AnOnFinderCalibrationListForm anOnFinderCalibrationListForm = (AnOnFinderCalibrationListForm) form;
        
        switch (anOnFinderCalibrationListForm.getServiceName())
        {
            case AnOnFinderCalibrationListAction.CALIBRATION_FIND:
            	findCalibrationList(request, response, anOnFinderCalibrationListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findCalibrationList(HttpServletRequest request, HttpServletResponse response, AnOnFinderCalibrationListForm anOnFinderCalibrationListForm) throws Exception
    {
    	AnOnFinderCalibrationListService anOnFinderCalibrationListService = (AnOnFinderCalibrationListService) getBean("anOnFinderCalibrationListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnFinderCalibrationListService.findCalibrationList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
}
