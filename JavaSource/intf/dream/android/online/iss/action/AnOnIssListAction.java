package intf.dream.android.online.iss.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.android.online.iss.form.AnOnIssListForm;
import intf.dream.android.online.iss.service.AnOnIssListService;

/**
 * 온라인버전 출고 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/anOnIssList" name="anOnIssListForm"
 *                input="/android/online/iss/anOnIssList.jsp" scope="request"
 *                validate="false"
 */
public class AnOnIssListAction extends IfOnlineAuthAction
{
    //ISS LIST
    public static final String ISS_LIST 					= "ISS_LIST";
    //ISS DELETE
    public static final String ISS_DELETE 					= "ISS_DELETE";
    //ISS INSERT
    public static final String ISS_INSERT 					= "ISS_INSERT";
    //ISS UPDATE
    public static final String ISS_UPDATE 					= "ISS_UPDATE";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AnOnIssListForm anOnIssListForm = (AnOnIssListForm) form;
        
        switch (anOnIssListForm.getServiceName())
        {
            case AnOnIssListAction.ISS_LIST:
            	findIssList(request, response, anOnIssListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnIssListAction.ISS_DELETE:
            	deleteIss(request, response, anOnIssListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnIssListAction.ISS_INSERT:
            	insertIss(request, response, anOnIssListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnIssListAction.ISS_UPDATE:
            	updateIss(request, response, anOnIssListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            	
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }

    private void findIssList(HttpServletRequest request, HttpServletResponse response, AnOnIssListForm anOnIssListForm) throws Exception
    {
    	AnOnIssListService anOnIssListService = (AnOnIssListService) getBean("anOnIssListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnIssListService.findIssList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
    private void deleteIss(HttpServletRequest request, HttpServletResponse response, AnOnIssListForm anOnIssListForm) throws Exception
    {
    	AnOnIssListService anOnIssListService = (AnOnIssListService) getBean("anOnIssListService");
    	List list = getListData(request);
    	int qty = anOnIssListService.deleteIss(list);
    	setMessage(response, "","OK");
    }
    private void insertIss(HttpServletRequest request, HttpServletResponse response, AnOnIssListForm anOnIssListForm) throws Exception
    {
    	AnOnIssListService anOnIssListService = (AnOnIssListService) getBean("anOnIssListService");
    	List list = getListData(request);
    	int qty = anOnIssListService.insertIss(list);
    	setMessage(response, "","OK");
    }
    private void updateIss(HttpServletRequest request, HttpServletResponse response, AnOnIssListForm anOnIssListForm) throws Exception
    {
    	AnOnIssListService anOnIssListService = (AnOnIssListService) getBean("anOnIssListService");
    	List list = getListData(request);
    	int qty = anOnIssListService.updateIss(list);
    	setMessage(response, "","OK");
    }
}
