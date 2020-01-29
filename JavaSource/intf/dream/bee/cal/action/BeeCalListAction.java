package intf.dream.bee.cal.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import common.util.CommonUtil;
import intf.dream.bee.cal.dto.BeeCalCommonDTO;
import intf.dream.bee.cal.form.BeeCalListForm;
import intf.dream.bee.cal.service.BeeCalListService;

/**
 * 온라인버전 교정 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/beeCalList" name="beeCalListForm"
 *                input="/bee/cal/beeCalList.jsp" scope="request"
 *                validate="false"
 */
public class BeeCalListAction extends IfOnlineAuthAction
{
    //CAL LIST
    public static final String CAL_LIST 					= "CAL_LIST";
    //CAL COUNT
    public static final String CAL_COUNT 					= "CAL_COUNT";
    //CAL DELETE
    public static final String CAL_DELETE 					= "CAL_DELETE";
    //CAL INSERT
    public static final String CAL_INSERT 					= "CAL_INSERT";
    //CAL UPDATE
    public static final String CAL_UPDATE 					= "CAL_UPDATE";

    //CAL ETC VALUE LIST
    public static final String CAL_ETC_VALUE_LIST 			= "CAL_ETC_VALUE_LIST";
    //CAL ETC VALUE DELETE
    public static final String CAL_ETC_VALUE_DELETE 		= "CAL_ETC_VALUE_DELETE";
    //CAL ETC VALUE INSERT
    public static final String CAL_ETC_VALUE_INSERT 		= "CAL_ETC_VALUE_INSERT";
    //CAL ETC VALUE UPDATE
    public static final String CAL_ETC_VALUE_UPDATE 		= "CAL_ETC_VALUE_UPDATE";

    //CAL STD EQ LIST
    public static final String CAL_STD_EQ_LIST 				= "CAL_STD_EQ_LIST";
    //CAL STD EQ DELETE
    public static final String CAL_STD_EQ_DELETE 			= "CAL_STD_EQ_DELETE";
    //CAL STD EQ INSERT
    public static final String CAL_STD_EQ_INSERT 			= "CAL_STD_EQ_INSERT";
    //CAL STD EQ UPDATE
    public static final String CAL_STD_EQ_UPDATE 			= "CAL_STD_EQ_UPDATE";

    //CAL GNL VALUE LIST
    public static final String CAL_GNL_VALUE_LIST 			= "CAL_GNL_VALUE_LIST";
    //CAL GNL VALUE DELETE
    public static final String CAL_GNL_VALUE_DELETE 		= "CAL_GNL_VALUE_DELETE";
    //CAL GNL VALUE INSERT
    public static final String CAL_GNL_VALUE_INSERT 		= "CAL_GNL_VALUE_INSERT";
    //CAL GNL VALUE UPDATE
    public static final String CAL_GNL_VALUE_UPDATE 		= "CAL_GNL_VALUE_UPDATE";

    //CAL SCL VALUE LIST
    public static final String CAL_SCL_VALUE_LIST 			= "CAL_SCL_VALUE_LIST";
    //CAL SCL VALUE DELETE
    public static final String CAL_SCL_VALUE_DELETE 		= "CAL_SCL_VALUE_DELETE";
    //CAL SCL VALUE INSERT
    public static final String CAL_SCL_VALUE_INSERT 		= "CAL_SCL_VALUE_INSERT";
    //CAL SCL VALUE UPDATE
    public static final String CAL_SCL_VALUE_UPDATE 		= "CAL_SCL_VALUE_UPDATE";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        BeeCalListForm beeCalListForm = (BeeCalListForm) form;
        
        switch (beeCalListForm.getServiceName())
        {
            case BeeCalListAction.CAL_LIST:
            	findCalList(request, response, beeCalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeCalListAction.CAL_COUNT:
            	findCalCount(request, response, beeCalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeCalListAction.CAL_DELETE:
            	deleteCal(request, response, beeCalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeCalListAction.CAL_INSERT:
            	insertCal(request, response, beeCalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeCalListAction.CAL_UPDATE:
            	updateCal(request, response, beeCalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            	
            case BeeCalListAction.CAL_ETC_VALUE_LIST:
            	findCalEtcValueList(request, response, beeCalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeCalListAction.CAL_ETC_VALUE_DELETE:
            	deleteCalEtcValueList(request, response, beeCalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeCalListAction.CAL_ETC_VALUE_INSERT:
            	insertCalEtcValueList(request, response, beeCalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeCalListAction.CAL_ETC_VALUE_UPDATE:
            	updateCalEtcValueList(request, response, beeCalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;

            case BeeCalListAction.CAL_STD_EQ_LIST:
            	findCalStdEqList(request, response, beeCalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeCalListAction.CAL_STD_EQ_DELETE:
            	deleteCalStdEqList(request, response, beeCalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeCalListAction.CAL_STD_EQ_INSERT:
            	insertCalStdEqList(request, response, beeCalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeCalListAction.CAL_STD_EQ_UPDATE:
            	updateCalStdEqList(request, response, beeCalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            	
            case BeeCalListAction.CAL_GNL_VALUE_LIST:
            	findCalGnlValueList(request, response, beeCalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeCalListAction.CAL_GNL_VALUE_DELETE:
            	deleteCalGnlValueList(request, response, beeCalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeCalListAction.CAL_GNL_VALUE_INSERT:
            	insertCalGnlValueList(request, response, beeCalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeCalListAction.CAL_GNL_VALUE_UPDATE:
            	updateCalGnlValueList(request, response, beeCalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            	
            	
            case BeeCalListAction.CAL_SCL_VALUE_LIST:
            	findCalSclValueList(request, response, beeCalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeCalListAction.CAL_SCL_VALUE_DELETE:
            	deleteCalSclValueList(request, response, beeCalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeCalListAction.CAL_SCL_VALUE_INSERT:
            	insertCalSclValueList(request, response, beeCalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeCalListAction.CAL_SCL_VALUE_UPDATE:
            	updateCalSclValueList(request, response, beeCalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            	
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }

    private void findCalList(HttpServletRequest request, HttpServletResponse response, BeeCalListForm beeCalListForm) throws Exception
    {
    	BeeCalListService beeCalListService = (BeeCalListService) getBean("beeCalListService");
    	BeeCalCommonDTO beeCalCommonDTO = beeCalListForm.getBeeCalCommonDTO();
    	Map map = getMapData(request);
    	
    	beeCalCommonDTO.setIsLoadMaxCount(true);
    	if("".equals(CommonUtil.convertString(String.valueOf(map.get("nextStartRow"))))){
    		beeCalCommonDTO.setIsLoadMaxCount(false);
    	}
    	beeCalCommonDTO.setFirstRow(CommonUtil.convertString(String.valueOf(map.get("nextStartRow"))));

        //리스트를 조회한다.
        List resultList = beeCalListService.findCalList(beeCalCommonDTO, map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }

    private void findCalCount(HttpServletRequest request, HttpServletResponse response, BeeCalListForm beeCalListForm) throws Exception
    {
    	BeeCalListService beeCalListService = (BeeCalListService) getBean("beeCalListService");
    	BeeCalCommonDTO beeCalCommonDTO = beeCalListForm.getBeeCalCommonDTO();
    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beeCalListService.findCalCount(beeCalCommonDTO, map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
    private void deleteCal(HttpServletRequest request, HttpServletResponse response, BeeCalListForm beeCalListForm) throws Exception
    {
    	BeeCalListService beeCalListService = (BeeCalListService) getBean("beeCalListService");
    	List list = getListData(request);
    	int qty = beeCalListService.deleteCal(list);
    	setMessage(response, "","OK");
    }
    private void insertCal(HttpServletRequest request, HttpServletResponse response, BeeCalListForm beeCalListForm) throws Exception
    {
    	BeeCalListService beeCalListService = (BeeCalListService) getBean("beeCalListService");
    	List list = getListData(request);
    	int qty = beeCalListService.insertCal(list);
    	setMessage(response, "","OK");
    }
    private void updateCal(HttpServletRequest request, HttpServletResponse response, BeeCalListForm beeCalListForm) throws Exception
    {
    	BeeCalListService beeCalListService = (BeeCalListService) getBean("beeCalListService");
    	List list = getListData(request);
    	int qty = beeCalListService.updateCal(list);
    	setMessage(response, "","OK");
    }
    

    private void findCalEtcValueList(HttpServletRequest request, HttpServletResponse response, BeeCalListForm beeCalListForm) throws Exception
    {
    	BeeCalListService beeCalListService = (BeeCalListService) getBean("beeCalListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beeCalListService.findCalEtcValueList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void deleteCalEtcValueList(HttpServletRequest request, HttpServletResponse response, BeeCalListForm beeCalListForm) throws Exception
    {
    	BeeCalListService beeCalListService = (BeeCalListService) getBean("beeCalListService");
    	List list = getListData(request);
    	int qty = beeCalListService.deleteCalEtcValueList(list);
    	setMessage(response, "","OK");
    }
    private void insertCalEtcValueList(HttpServletRequest request, HttpServletResponse response, BeeCalListForm beeCalListForm) throws Exception
    {
    	BeeCalListService beeCalListService = (BeeCalListService) getBean("beeCalListService");
    	List list = getListData(request);
    	int qty = beeCalListService.insertCalEtcValueList(list);
    	setMessage(response, "","OK");
    }
    private void updateCalEtcValueList(HttpServletRequest request, HttpServletResponse response, BeeCalListForm beeCalListForm) throws Exception
    {
    	BeeCalListService beeCalListService = (BeeCalListService) getBean("beeCalListService");
    	List list = getListData(request);
    	int qty = beeCalListService.updateCalEtcValueList(list);
    	setMessage(response, "","OK");
    }
    
    private void findCalStdEqList(HttpServletRequest request, HttpServletResponse response, BeeCalListForm beeCalListForm) throws Exception
    {
    	BeeCalListService beeCalListService = (BeeCalListService) getBean("beeCalListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beeCalListService.findCalStdEqList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void deleteCalStdEqList(HttpServletRequest request, HttpServletResponse response, BeeCalListForm beeCalListForm) throws Exception
    {
    	BeeCalListService beeCalListService = (BeeCalListService) getBean("beeCalListService");
    	List list = getListData(request);
    	int qty = beeCalListService.deleteCalStdEqList(list);
    	setMessage(response, "","OK");
    }
    private void insertCalStdEqList(HttpServletRequest request, HttpServletResponse response, BeeCalListForm beeCalListForm) throws Exception
    {
    	BeeCalListService beeCalListService = (BeeCalListService) getBean("beeCalListService");
    	List list = getListData(request);
    	int qty = beeCalListService.insertCalStdEqList(list);
    	setMessage(response, "","OK");
    }
    private void updateCalStdEqList(HttpServletRequest request, HttpServletResponse response, BeeCalListForm beeCalListForm) throws Exception
    {
    	BeeCalListService beeCalListService = (BeeCalListService) getBean("beeCalListService");
    	List list = getListData(request);
    	int qty = beeCalListService.updateCalStdEqList(list);
    	setMessage(response, "","OK");
    }
    
    private void findCalGnlValueList(HttpServletRequest request, HttpServletResponse response, BeeCalListForm beeCalListForm) throws Exception
    {
    	BeeCalListService beeCalListService = (BeeCalListService) getBean("beeCalListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beeCalListService.findCalGnlValueList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void deleteCalGnlValueList(HttpServletRequest request, HttpServletResponse response, BeeCalListForm beeCalListForm) throws Exception
    {
    	BeeCalListService beeCalListService = (BeeCalListService) getBean("beeCalListService");
    	List list = getListData(request);
    	int qty = beeCalListService.deleteCalGnlValueList(list);
    	setMessage(response, "","OK");
    }
    private void insertCalGnlValueList(HttpServletRequest request, HttpServletResponse response, BeeCalListForm beeCalListForm) throws Exception
    {
    	BeeCalListService beeCalListService = (BeeCalListService) getBean("beeCalListService");
    	List list = getListData(request);
    	int qty = beeCalListService.insertCalGnlValueList(list);
    	setMessage(response, "","OK");
    }
    private void updateCalGnlValueList(HttpServletRequest request, HttpServletResponse response, BeeCalListForm beeCalListForm) throws Exception
    {
    	BeeCalListService beeCalListService = (BeeCalListService) getBean("beeCalListService");
    	List list = getListData(request);
    	int qty = beeCalListService.updateCalGnlValueList(list);
    	setMessage(response, "","OK");
    }

    private void findCalSclValueList(HttpServletRequest request, HttpServletResponse response, BeeCalListForm beeCalListForm) throws Exception
    {
    	BeeCalListService beeCalListService = (BeeCalListService) getBean("beeCalListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beeCalListService.findCalSclValueList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void deleteCalSclValueList(HttpServletRequest request, HttpServletResponse response, BeeCalListForm beeCalListForm) throws Exception
    {
    	BeeCalListService beeCalListService = (BeeCalListService) getBean("beeCalListService");
    	List list = getListData(request);
    	int qty = beeCalListService.deleteCalSclValueList(list);
    	setMessage(response, "","OK");
    }
    private void insertCalSclValueList(HttpServletRequest request, HttpServletResponse response, BeeCalListForm beeCalListForm) throws Exception
    {
    	BeeCalListService beeCalListService = (BeeCalListService) getBean("beeCalListService");
    	List list = getListData(request);
    	int qty = beeCalListService.insertCalSclValueList(list);
    	setMessage(response, "","OK");
    }
    private void updateCalSclValueList(HttpServletRequest request, HttpServletResponse response, BeeCalListForm beeCalListForm) throws Exception
    {
    	BeeCalListService beeCalListService = (BeeCalListService) getBean("beeCalListService");
    	List list = getListData(request);
    	int qty = beeCalListService.updateCalSclValueList(list);
    	setMessage(response, "","OK");
    }
}
