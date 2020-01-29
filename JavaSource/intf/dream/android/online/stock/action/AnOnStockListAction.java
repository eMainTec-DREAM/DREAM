package intf.dream.android.online.stock.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.android.online.stock.form.AnOnStockListForm;
import intf.dream.android.online.stock.service.AnOnStockListService;

/**
 * �¶��ι��� ����� 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/anOnStockList" name="anOnStockListForm"
 *                input="/android/online/stock/anOnStockList.jsp" scope="request"
 *                validate="false"
 */
public class AnOnStockListAction extends IfOnlineAuthAction
{
    //STOCK LIST
    public static final String STOCK_LIST 						= "STOCK_LIST";
    //STOCK UPDATE
    public static final String STOCK_UPDATE 					= "STOCK_UPDATE";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AnOnStockListForm anOnStockListForm = (AnOnStockListForm) form;
        
        switch (anOnStockListForm.getServiceName())
        {
            case AnOnStockListAction.STOCK_LIST:
            	findStockList(request, response, anOnStockListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnStockListAction.STOCK_UPDATE:
            	updateStock(request, response, anOnStockListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            	
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }

    private void findStockList(HttpServletRequest request, HttpServletResponse response, AnOnStockListForm anOnStockListForm) throws Exception
    {
    	AnOnStockListService anOnStockListService = (AnOnStockListService) getBean("anOnStockListService");

    	Map map = getMapData(request);

        //����Ʈ�� ��ȸ�Ѵ�.
        List resultList = anOnStockListService.findStockList(map);

        // ��ȸ�� List �� form�� �����Ѵ�.
        super.makeJsonResult(resultList, request, response);
    }
    
    private void updateStock(HttpServletRequest request, HttpServletResponse response, AnOnStockListForm anOnStockListForm) throws Exception
    {
    	AnOnStockListService anOnStockListService = (AnOnStockListService) getBean("anOnStockListService");
    	List list = getListData(request);
    	int qty = anOnStockListService.updateStock(list);
    	setMessage(response, "","OK");
    }
}
