package intf.dream.android.online.cal.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.android.online.cal.form.AnOnCalListForm;
import intf.dream.android.online.cal.service.AnOnCalListService;

/**
 * 온라인버전 교정 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/anOnCalList" name="anOnCalListForm"
 *                input="/android/online/cal/anOnCalList.jsp" scope="request"
 *                validate="false"
 */
public class AnOnCalListAction extends IfOnlineAuthAction
{
    //CAL LIST
    public static final String CAL_LIST 					= "CAL_LIST";
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
        AnOnCalListForm anOnCalListForm = (AnOnCalListForm) form;
        
        switch (anOnCalListForm.getServiceName())
        {
            case AnOnCalListAction.CAL_LIST:
            	findCalList(request, response, anOnCalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnCalListAction.CAL_DELETE:
            	deleteCal(request, response, anOnCalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnCalListAction.CAL_INSERT:
            	insertCal(request, response, anOnCalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnCalListAction.CAL_UPDATE:
            	updateCal(request, response, anOnCalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            	
            case AnOnCalListAction.CAL_ETC_VALUE_LIST:
            	findCalEtcValueList(request, response, anOnCalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnCalListAction.CAL_ETC_VALUE_DELETE:
            	deleteCalEtcValueList(request, response, anOnCalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnCalListAction.CAL_ETC_VALUE_INSERT:
            	insertCalEtcValueList(request, response, anOnCalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnCalListAction.CAL_ETC_VALUE_UPDATE:
            	updateCalEtcValueList(request, response, anOnCalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;

            case AnOnCalListAction.CAL_STD_EQ_LIST:
            	findCalStdEqList(request, response, anOnCalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnCalListAction.CAL_STD_EQ_DELETE:
            	deleteCalStdEqList(request, response, anOnCalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnCalListAction.CAL_STD_EQ_INSERT:
            	insertCalStdEqList(request, response, anOnCalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnCalListAction.CAL_STD_EQ_UPDATE:
            	updateCalStdEqList(request, response, anOnCalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            	
            case AnOnCalListAction.CAL_GNL_VALUE_LIST:
            	findCalGnlValueList(request, response, anOnCalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnCalListAction.CAL_GNL_VALUE_DELETE:
            	deleteCalGnlValueList(request, response, anOnCalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnCalListAction.CAL_GNL_VALUE_INSERT:
            	insertCalGnlValueList(request, response, anOnCalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnCalListAction.CAL_GNL_VALUE_UPDATE:
            	updateCalGnlValueList(request, response, anOnCalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            	
            	
            case AnOnCalListAction.CAL_SCL_VALUE_LIST:
            	findCalSclValueList(request, response, anOnCalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnCalListAction.CAL_SCL_VALUE_DELETE:
            	deleteCalSclValueList(request, response, anOnCalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnCalListAction.CAL_SCL_VALUE_INSERT:
            	insertCalSclValueList(request, response, anOnCalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnCalListAction.CAL_SCL_VALUE_UPDATE:
            	updateCalSclValueList(request, response, anOnCalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            	
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }

    private void findCalList(HttpServletRequest request, HttpServletResponse response, AnOnCalListForm anOnCalListForm) throws Exception
    {
    	AnOnCalListService anOnCalListService = (AnOnCalListService) getBean("anOnCalListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnCalListService.findCalList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
    private void deleteCal(HttpServletRequest request, HttpServletResponse response, AnOnCalListForm anOnCalListForm) throws Exception
    {
    	AnOnCalListService anOnCalListService = (AnOnCalListService) getBean("anOnCalListService");
    	List list = getListData(request);
    	int qty = anOnCalListService.deleteCal(list);
    	setMessage(response, "","OK");
    }
    private void insertCal(HttpServletRequest request, HttpServletResponse response, AnOnCalListForm anOnCalListForm) throws Exception
    {
    	AnOnCalListService anOnCalListService = (AnOnCalListService) getBean("anOnCalListService");
    	List list = getListData(request);
    	int qty = anOnCalListService.insertCal(list);
    	setMessage(response, "","OK");
    }
    private void updateCal(HttpServletRequest request, HttpServletResponse response, AnOnCalListForm anOnCalListForm) throws Exception
    {
    	AnOnCalListService anOnCalListService = (AnOnCalListService) getBean("anOnCalListService");
    	List list = getListData(request);
    	int qty = anOnCalListService.updateCal(list);
    	setMessage(response, "","OK");
    }
    

    private void findCalEtcValueList(HttpServletRequest request, HttpServletResponse response, AnOnCalListForm anOnCalListForm) throws Exception
    {
    	AnOnCalListService anOnCalListService = (AnOnCalListService) getBean("anOnCalListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnCalListService.findCalEtcValueList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void deleteCalEtcValueList(HttpServletRequest request, HttpServletResponse response, AnOnCalListForm anOnCalListForm) throws Exception
    {
    	AnOnCalListService anOnCalListService = (AnOnCalListService) getBean("anOnCalListService");
    	List list = getListData(request);
    	int qty = anOnCalListService.deleteCalEtcValueList(list);
    	setMessage(response, "","OK");
    }
    private void insertCalEtcValueList(HttpServletRequest request, HttpServletResponse response, AnOnCalListForm anOnCalListForm) throws Exception
    {
    	AnOnCalListService anOnCalListService = (AnOnCalListService) getBean("anOnCalListService");
    	List list = getListData(request);
    	int qty = anOnCalListService.insertCalEtcValueList(list);
    	setMessage(response, "","OK");
    }
    private void updateCalEtcValueList(HttpServletRequest request, HttpServletResponse response, AnOnCalListForm anOnCalListForm) throws Exception
    {
    	AnOnCalListService anOnCalListService = (AnOnCalListService) getBean("anOnCalListService");
    	List list = getListData(request);
    	int qty = anOnCalListService.updateCalEtcValueList(list);
    	setMessage(response, "","OK");
    }
    
    private void findCalStdEqList(HttpServletRequest request, HttpServletResponse response, AnOnCalListForm anOnCalListForm) throws Exception
    {
    	AnOnCalListService anOnCalListService = (AnOnCalListService) getBean("anOnCalListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnCalListService.findCalStdEqList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void deleteCalStdEqList(HttpServletRequest request, HttpServletResponse response, AnOnCalListForm anOnCalListForm) throws Exception
    {
    	AnOnCalListService anOnCalListService = (AnOnCalListService) getBean("anOnCalListService");
    	List list = getListData(request);
    	int qty = anOnCalListService.deleteCalStdEqList(list);
    	setMessage(response, "","OK");
    }
    private void insertCalStdEqList(HttpServletRequest request, HttpServletResponse response, AnOnCalListForm anOnCalListForm) throws Exception
    {
    	AnOnCalListService anOnCalListService = (AnOnCalListService) getBean("anOnCalListService");
    	List list = getListData(request);
    	int qty = anOnCalListService.insertCalStdEqList(list);
    	setMessage(response, "","OK");
    }
    private void updateCalStdEqList(HttpServletRequest request, HttpServletResponse response, AnOnCalListForm anOnCalListForm) throws Exception
    {
    	AnOnCalListService anOnCalListService = (AnOnCalListService) getBean("anOnCalListService");
    	List list = getListData(request);
    	int qty = anOnCalListService.updateCalStdEqList(list);
    	setMessage(response, "","OK");
    }
    
    private void findCalGnlValueList(HttpServletRequest request, HttpServletResponse response, AnOnCalListForm anOnCalListForm) throws Exception
    {
    	AnOnCalListService anOnCalListService = (AnOnCalListService) getBean("anOnCalListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnCalListService.findCalGnlValueList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void deleteCalGnlValueList(HttpServletRequest request, HttpServletResponse response, AnOnCalListForm anOnCalListForm) throws Exception
    {
    	AnOnCalListService anOnCalListService = (AnOnCalListService) getBean("anOnCalListService");
    	List list = getListData(request);
    	int qty = anOnCalListService.deleteCalGnlValueList(list);
    	setMessage(response, "","OK");
    }
    private void insertCalGnlValueList(HttpServletRequest request, HttpServletResponse response, AnOnCalListForm anOnCalListForm) throws Exception
    {
    	AnOnCalListService anOnCalListService = (AnOnCalListService) getBean("anOnCalListService");
    	List list = getListData(request);
    	int qty = anOnCalListService.insertCalGnlValueList(list);
    	setMessage(response, "","OK");
    }
    private void updateCalGnlValueList(HttpServletRequest request, HttpServletResponse response, AnOnCalListForm anOnCalListForm) throws Exception
    {
    	AnOnCalListService anOnCalListService = (AnOnCalListService) getBean("anOnCalListService");
    	List list = getListData(request);
    	int qty = anOnCalListService.updateCalGnlValueList(list);
    	setMessage(response, "","OK");
    }

    private void findCalSclValueList(HttpServletRequest request, HttpServletResponse response, AnOnCalListForm anOnCalListForm) throws Exception
    {
    	AnOnCalListService anOnCalListService = (AnOnCalListService) getBean("anOnCalListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnCalListService.findCalSclValueList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void deleteCalSclValueList(HttpServletRequest request, HttpServletResponse response, AnOnCalListForm anOnCalListForm) throws Exception
    {
    	AnOnCalListService anOnCalListService = (AnOnCalListService) getBean("anOnCalListService");
    	List list = getListData(request);
    	int qty = anOnCalListService.deleteCalSclValueList(list);
    	setMessage(response, "","OK");
    }
    private void insertCalSclValueList(HttpServletRequest request, HttpServletResponse response, AnOnCalListForm anOnCalListForm) throws Exception
    {
    	AnOnCalListService anOnCalListService = (AnOnCalListService) getBean("anOnCalListService");
    	List list = getListData(request);
    	int qty = anOnCalListService.insertCalSclValueList(list);
    	setMessage(response, "","OK");
    }
    private void updateCalSclValueList(HttpServletRequest request, HttpServletResponse response, AnOnCalListForm anOnCalListForm) throws Exception
    {
    	AnOnCalListService anOnCalListService = (AnOnCalListService) getBean("anOnCalListService");
    	List list = getListData(request);
    	int qty = anOnCalListService.updateCalSclValueList(list);
    	setMessage(response, "","OK");
    }
}
