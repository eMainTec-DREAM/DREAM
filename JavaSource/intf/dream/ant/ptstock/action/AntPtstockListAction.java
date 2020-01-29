package intf.dream.ant.ptstock.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfAuthAction;
import intf.dream.ant.ptstock.form.AntPtstockListForm;
import intf.dream.ant.ptstock.service.AntPtstockListService;

/**
 * inspection 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/antPtstockList" name="antPtstockListForm"
 *                input="/ant/ptstock/antPtstockList.jsp" scope="request"
 *                validate="false"
 */
public class AntPtstockListAction extends IfAuthAction
{
    //TAPARTS
    public static final String PARTS_FIND 				= "STOCK_PARTS";
    //TAPTSTOCK
    public static final String STOCK_FIND 				= "STOCK";
    //TAPTSTOCK SAVE
    public static final String STOCK_SAVE 				= "STOCK_SAVE";
    
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AntPtstockListForm antPtstockListForm = (AntPtstockListForm) form;
        
        switch (antPtstockListForm.getServiceName())
        {
            case AntPtstockListAction.PARTS_FIND:
            	findPartsList(request, response, antPtstockListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 

            case AntPtstockListAction.STOCK_FIND:
            	findStockList(request, response, antPtstockListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 

            case AntPtstockListAction.STOCK_SAVE:
            	savePtstock(request, response, antPtstockListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findPartsList(HttpServletRequest request, HttpServletResponse response, AntPtstockListForm antPtstockListForm) throws Exception
    {
    	AntPtstockListService antPtstockListService = (AntPtstockListService) getBean("antPtstockListService");

    	Map map = getMapData(request);

        //����Ʈ�� ��ȸ�Ѵ�.
        List resultList = antPtstockListService.findPartsList(map);

        // ��ȸ�� List �� form�� �����Ѵ�.
        super.makeJsonResult(resultList, request, response);
    }
    private void findStockList(HttpServletRequest request, HttpServletResponse response, AntPtstockListForm antPtstockListForm) throws Exception
    {
    	AntPtstockListService antPtstockListService = (AntPtstockListService) getBean("antPtstockListService");

    	Map map = getMapData(request);

        //����Ʈ�� ��ȸ�Ѵ�.
        List resultList = antPtstockListService.findStockList(map);

        // ��ȸ�� List �� form�� �����Ѵ�.
        super.makeJsonResult(resultList, request, response);
    }

    private void savePtstock(HttpServletRequest request, HttpServletResponse response, AntPtstockListForm antPtstockListForm)  throws Exception
    {
    	AntPtstockListService antPtstockListService = (AntPtstockListService) getBean("antPtstockListService");

    	List list = getListData(request);
    	int qty = antPtstockListService.savePtstock(list);
    	setMessage(response, "","OK");
    }
}
