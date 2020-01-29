package intf.dream.cricket.finder.calibration.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.cricket.finder.calibration.form.CricketFinderCalibrationListForm;
import intf.dream.cricket.finder.calibration.service.CricketFinderCalibrationListService;

/**
 * finder - Calibration 
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/cricketFinderCalibrationList" name="cricketFinderCalibrationListForm"
 *                input="/cricket/finder/calibration/cricketFinderCalibrationList.jsp" scope="request"
 *                validate="false"
 */
public class CricketFinderCalibrationListAction extends IfOnlineAuthAction
{
    //CALIBRATION_FIND
    public static final String CALIBRATION_FIND 			= "CALIBRATION";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        CricketFinderCalibrationListForm cricketFinderCalibrationListForm = (CricketFinderCalibrationListForm) form;
        
        switch (cricketFinderCalibrationListForm.getServiceName())
        {
            case CricketFinderCalibrationListAction.CALIBRATION_FIND:
            	findCalibrationList(request, response, cricketFinderCalibrationListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findCalibrationList(HttpServletRequest request, HttpServletResponse response, CricketFinderCalibrationListForm cricketFinderCalibrationListForm) throws Exception
    {
    	CricketFinderCalibrationListService cricketFinderCalibrationListService = (CricketFinderCalibrationListService) getBean("cricketFinderCalibrationListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = cricketFinderCalibrationListService.findCalibrationList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
}
