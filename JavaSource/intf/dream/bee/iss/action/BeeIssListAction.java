package intf.dream.bee.iss.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import common.util.CommonUtil;
import intf.dream.bee.iss.dto.BeeIssCommonDTO;
import intf.dream.bee.iss.form.BeeIssListForm;
import intf.dream.bee.iss.service.BeeIssListService;

/**
 * 온라인버전 출고 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/beeIssList" name="beeIssListForm"
 *                input="/bee/iss/beeIssList.jsp" scope="request"
 *                validate="false"
 */
public class BeeIssListAction extends IfOnlineAuthAction
{
    //ISS LIST
    public static final String ISS_LIST 					= "ISS_LIST";
    //ISS LIST COUNT
    public static final String ISS_COUNT					= "ISS_COUNT";
    //ISS DELETE
    public static final String ISS_DELETE 					= "ISS_DELETE";
    //ISS INSERT
    public static final String ISS_INSERT 					= "ISS_INSERT";
    //ISS UPDATE
    public static final String ISS_UPDATE 					= "ISS_UPDATE";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        BeeIssListForm beeIssListForm = (BeeIssListForm) form;
        
        switch (beeIssListForm.getServiceName())
        {
            case BeeIssListAction.ISS_LIST:
            	findIssList(request, response, beeIssListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeIssListAction.ISS_COUNT:
            	findIssCount(request, response, beeIssListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeIssListAction.ISS_DELETE:
            	deleteIss(request, response, beeIssListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeIssListAction.ISS_INSERT:
            	insertIss(request, response, beeIssListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeIssListAction.ISS_UPDATE:
            	updateIss(request, response, beeIssListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            	
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }

    private void findIssList(HttpServletRequest request, HttpServletResponse response, BeeIssListForm beeIssListForm) throws Exception
    {
    	BeeIssListService beeIssListService = (BeeIssListService) getBean("beeIssListService");
    	BeeIssCommonDTO beeIssCommonDTO = beeIssListForm.getBeeIssCommonDTO();
    	Map map = getMapData(request);

    	beeIssCommonDTO.setIsLoadMaxCount(true);
    	if("".equals(CommonUtil.convertString(String.valueOf(map.get("nextStartRow"))))){
    		beeIssCommonDTO.setIsLoadMaxCount(false);
    	}
    	beeIssCommonDTO.setFirstRow(CommonUtil.convertString(String.valueOf(map.get("nextStartRow"))));
    	
        //리스트를 조회한다.
        List resultList = beeIssListService.findIssList(beeIssCommonDTO, map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }

    private void findIssCount(HttpServletRequest request, HttpServletResponse response, BeeIssListForm beeIssListForm) throws Exception
    {
    	BeeIssListService beeIssListService = (BeeIssListService) getBean("beeIssListService");
    	BeeIssCommonDTO beeIssCommonDTO = beeIssListForm.getBeeIssCommonDTO();
    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beeIssListService.findIssCount(beeIssCommonDTO, map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
    private void deleteIss(HttpServletRequest request, HttpServletResponse response, BeeIssListForm beeIssListForm) throws Exception
    {
    	BeeIssListService beeIssListService = (BeeIssListService) getBean("beeIssListService");
    	List list = getListData(request);
    	int qty = beeIssListService.deleteIss(list);
    	setMessage(response, "","OK");
    }
    private void insertIss(HttpServletRequest request, HttpServletResponse response, BeeIssListForm beeIssListForm) throws Exception
    {
    	BeeIssListService beeIssListService = (BeeIssListService) getBean("beeIssListService");
    	List list = getListData(request);
    	int qty = beeIssListService.insertIss(list);
    	setMessage(response, "","OK");
    }
    private void updateIss(HttpServletRequest request, HttpServletResponse response, BeeIssListForm beeIssListForm) throws Exception
    {
    	BeeIssListService beeIssListService = (BeeIssListService) getBean("beeIssListService");
    	List list = getListData(request);
    	int qty = beeIssListService.updateIss(list);
    	setMessage(response, "","OK");
    }
}
