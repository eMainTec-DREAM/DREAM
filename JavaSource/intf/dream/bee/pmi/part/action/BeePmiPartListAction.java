package intf.dream.bee.pmi.part.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import common.util.CommonUtil;
import intf.dream.bee.pmi.part.dto.BeePmiPartCommonDTO;
import intf.dream.bee.pmi.part.form.BeePmiPartListForm;
import intf.dream.bee.pmi.part.service.BeePmiPartListService;

/**
 * 온라인버전 Part Change 점검
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/beePmiPartList" name="beePmiPartListForm"
 *                input="/bee/pmi/day/beePmiPartList.jsp" scope="request"
 *                validate="false"
 */
public class BeePmiPartListAction extends IfOnlineAuthAction
{
    //PmiPart List
    public static final String PMI_LIST 					= "PMI_LIST";
    //PmiPart List Count
    public static final String PMI_COUNT 					= "PMI_COUNT";
    //Point List
    public static final String POINT_LIST					= "POINT_LIST";
    //Point Hist List
    public static final String POINT_HIST_LIST				= "POINT_HIST_LIST";
    //Wopoint count
    public static final String POINT_COUNT					= "POINT_COUNT";
    //INSERT POINT
    public static final String POINT_INSERT					= "POINT_INSERT";
    //UPDATE POINT
    public static final String POINT_UPDATE					= "POINT_UPDATE";
    //CHECK STATUS
    public static final String CHECK_STATUS					= "CHECK_STATUS";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        BeePmiPartListForm beePmiPartListForm = (BeePmiPartListForm) form;
        
        switch (beePmiPartListForm.getServiceName())
        {
            case BeePmiPartListAction.PMI_LIST:
            	findPmiPartList(request, response, beePmiPartListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeePmiPartListAction.PMI_COUNT:
            	findPmiPartCount(request, response, beePmiPartListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeePmiPartListAction.POINT_LIST:
            	findPointList(request, response, beePmiPartListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeePmiPartListAction.POINT_HIST_LIST:
            	findPointHistList(request, response, beePmiPartListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeePmiPartListAction.POINT_COUNT:
            	findWoPointCount(request, response, beePmiPartListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeePmiPartListAction.POINT_INSERT:
            	insertPoint(request, response, beePmiPartListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeePmiPartListAction.POINT_UPDATE:
            	updatePoint(request, response, beePmiPartListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeePmiPartListAction.CHECK_STATUS:
            	findStatus(request, response, beePmiPartListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findPmiPartList(HttpServletRequest request, HttpServletResponse response, BeePmiPartListForm beePmiPartListForm) throws Exception
    {
    	BeePmiPartListService beePmiPartListService = (BeePmiPartListService) getBean("beePmiPartListService");
    	BeePmiPartCommonDTO beePmiPartCommonDTO = beePmiPartListForm.getBeePmiPartCommonDTO();
    	Map map = getMapData(request);

    	beePmiPartCommonDTO.setIsLoadMaxCount(true);
    	if("".equals(CommonUtil.convertString(String.valueOf(map.get("nextStartRow"))))){
    		beePmiPartCommonDTO.setIsLoadMaxCount(false);
    	}
    	beePmiPartCommonDTO.setFirstRow(CommonUtil.convertString(String.valueOf(map.get("nextStartRow"))));
    	
        //리스트를 조회한다.
        List resultList = beePmiPartListService.findPmiPartList(beePmiPartCommonDTO, map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }

    private void findPmiPartCount(HttpServletRequest request, HttpServletResponse response, BeePmiPartListForm beePmiPartListForm) throws Exception
    {
    	BeePmiPartListService beePmiPartListService = (BeePmiPartListService) getBean("beePmiPartListService");
    	BeePmiPartCommonDTO beePmiPartCommonDTO = beePmiPartListForm.getBeePmiPartCommonDTO();
    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beePmiPartListService.findPmiPartCount(beePmiPartCommonDTO, map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void findPointList(HttpServletRequest request, HttpServletResponse response, BeePmiPartListForm beePmiPartListForm) throws Exception
    {
    	BeePmiPartListService beePmiPartListService = (BeePmiPartListService) getBean("beePmiPartListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beePmiPartListService.findPointList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
    private void findPointHistList(HttpServletRequest request, HttpServletResponse response, BeePmiPartListForm beePmiPartListForm) throws Exception
    {
    	BeePmiPartListService beePmiPartListService = (BeePmiPartListService) getBean("beePmiPartListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beePmiPartListService.findPointHistList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void findWoPointCount(HttpServletRequest request, HttpServletResponse response, BeePmiPartListForm beePmiPartListForm) throws Exception
    {
    	BeePmiPartListService beePmiPartListService = (BeePmiPartListService) getBean("beePmiPartListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beePmiPartListService.findWoPointCount(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void findStatus(HttpServletRequest request, HttpServletResponse response, BeePmiPartListForm beePmiPartListForm) throws Exception
    {
    	BeePmiPartListService beePmiPartListService = (BeePmiPartListService) getBean("beePmiPartListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beePmiPartListService.findStatus(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void insertPoint(HttpServletRequest request, HttpServletResponse response, BeePmiPartListForm beePmiPartListForm) throws Exception
    {
    	BeePmiPartListService beePmiPartListService = (BeePmiPartListService) getBean("beePmiPartListService");
    	List list = getListData(request);
    	int qty = beePmiPartListService.insertPoint(list);
    	setMessage(response, "","OK");
    }
    private void updatePoint(HttpServletRequest request, HttpServletResponse response, BeePmiPartListForm beePmiPartListForm) throws Exception
    {
    	BeePmiPartListService beePmiPartListService = (BeePmiPartListService) getBean("beePmiPartListService");
    	List list = getListData(request);
    	int qty = beePmiPartListService.updatePoint(list);
    	setMessage(response, "","OK");
    }
}
