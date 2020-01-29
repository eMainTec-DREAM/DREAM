package intf.dream.android.online.finder.wkctr.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.android.online.finder.wkctr.form.AnOnFinderWkctrListForm;
import intf.dream.android.online.finder.wkctr.service.AnOnFinderWkctrListService;

/**
 * finder - Wkctr 
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/anOnFinderWkctrList" name="anOnFinderWkctrListForm"
 *                input="/android/online/finder/wkctr/anOnFinderWkctrList.jsp" scope="request"
 *                validate="false"
 */
public class AnOnFinderWkctrListAction extends IfOnlineAuthAction
{
    //WORKGROUP_FIND
    public static final String WORKGROUP_FIND 			= "WKCTR";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AnOnFinderWkctrListForm anOnFinderWkctrListForm = (AnOnFinderWkctrListForm) form;
        
        switch (anOnFinderWkctrListForm.getServiceName())
        {
            case AnOnFinderWkctrListAction.WORKGROUP_FIND:
            	findWkctrList(request, response, anOnFinderWkctrListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findWkctrList(HttpServletRequest request, HttpServletResponse response, AnOnFinderWkctrListForm anOnFinderWkctrListForm) throws Exception
    {
    	AnOnFinderWkctrListService anOnFinderWkctrListService = (AnOnFinderWkctrListService) getBean("anOnFinderWkctrListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnFinderWkctrListService.findWkctrList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
}
