package intf.dream.android.offline.maptstock.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfAuthAction;
import intf.dream.android.offline.maptstock.form.AnIfPtstockListForm;
import intf.dream.android.offline.maptstock.service.AnIfPtstockListService;

/**
 * inspection 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/anIfPtstockList" name="anIfPtstockListForm"
 *                input="/android/offline/maptstock/anIfPtstockList.jsp" scope="request"
 *                validate="false"
 */
public class AnIfPtstockListAction extends IfAuthAction
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
        AnIfPtstockListForm anIfPtstockListForm = (AnIfPtstockListForm) form;
        
        switch (anIfPtstockListForm.getServiceName())
        {
            case AnIfPtstockListAction.PARTS_FIND:
            	findPartsList(request, response, anIfPtstockListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 

            case AnIfPtstockListAction.STOCK_FIND:
            	findStockList(request, response, anIfPtstockListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 

            case AnIfPtstockListAction.STOCK_SAVE:
            	savePtstock(request, response, anIfPtstockListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findPartsList(HttpServletRequest request, HttpServletResponse response, AnIfPtstockListForm anIfPtstockListForm) throws Exception
    {
    	AnIfPtstockListService anIfPtstockListService = (AnIfPtstockListService) getBean("anIfPtstockListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anIfPtstockListService.findPartsList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void findStockList(HttpServletRequest request, HttpServletResponse response, AnIfPtstockListForm anIfPtstockListForm) throws Exception
    {
    	AnIfPtstockListService anIfPtstockListService = (AnIfPtstockListService) getBean("anIfPtstockListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anIfPtstockListService.findStockList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }

    private void savePtstock(HttpServletRequest request, HttpServletResponse response, AnIfPtstockListForm anIfPtstockListForm)  throws Exception
    {
    	AnIfPtstockListService anIfPtstockListService = (AnIfPtstockListService) getBean("anIfPtstockListService");

    	List list = getListData(request);
    	int qty = anIfPtstockListService.savePtstock(list);
    	setMessage(response, "","OK");
    }
}
