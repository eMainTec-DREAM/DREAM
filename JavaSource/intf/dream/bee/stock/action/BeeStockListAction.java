package intf.dream.bee.stock.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import common.util.CommonUtil;
import intf.dream.bee.stock.dto.BeeStockCommonDTO;
import intf.dream.bee.stock.form.BeeStockListForm;
import intf.dream.bee.stock.service.BeeStockListService;

/**
 * 온라인버전 현재고 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/beeStockList" name="beeStockListForm"
 *                input="/bee/stock/beeStockList.jsp" scope="request"
 *                validate="false"
 */
public class BeeStockListAction extends IfOnlineAuthAction
{
    //STOCK LIST
    public static final String STOCK_LIST 						= "STOCK_LIST";
    //STOCK LIST COUNT
    public static final String STOCK_COUNT 						= "STOCK_COUNT";
    //STOCK UPDATE
    public static final String STOCK_UPDATE 					= "STOCK_UPDATE";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        BeeStockListForm beeStockListForm = (BeeStockListForm) form;
        
        switch (beeStockListForm.getServiceName())
        {
            case BeeStockListAction.STOCK_LIST:
            	findStockList(request, response, beeStockListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeStockListAction.STOCK_COUNT:
            	findStockCount(request, response, beeStockListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeStockListAction.STOCK_UPDATE:
            	updateStock(request, response, beeStockListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            	
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }

    private void findStockList(HttpServletRequest request, HttpServletResponse response, BeeStockListForm beeStockListForm) throws Exception
    {
    	BeeStockListService beeStockListService = (BeeStockListService) getBean("beeStockListService");
    	BeeStockCommonDTO beeStockCommonDTO = beeStockListForm.getBeeStockCommonDTO();
    	Map map = getMapData(request);

    	beeStockCommonDTO.setIsLoadMaxCount(true);
    	if("".equals(CommonUtil.convertString(String.valueOf(map.get("nextStartRow"))))){
    		beeStockCommonDTO.setIsLoadMaxCount(false);
    	}
    	beeStockCommonDTO.setFirstRow(CommonUtil.convertString(String.valueOf(map.get("nextStartRow"))));
    	
        //리스트를 조회한다.
        List resultList = beeStockListService.findStockList(beeStockCommonDTO, map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }

    private void findStockCount(HttpServletRequest request, HttpServletResponse response, BeeStockListForm beeStockListForm) throws Exception
    {
    	BeeStockListService beeStockListService = (BeeStockListService) getBean("beeStockListService");
    	BeeStockCommonDTO beeStockCommonDTO = beeStockListForm.getBeeStockCommonDTO();
    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beeStockListService.findStockCount(beeStockCommonDTO, map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
    private void updateStock(HttpServletRequest request, HttpServletResponse response, BeeStockListForm beeStockListForm) throws Exception
    {
    	BeeStockListService beeStockListService = (BeeStockListService) getBean("beeStockListService");
    	List list = getListData(request);
    	int qty = beeStockListService.updateStock(list);
    	setMessage(response, "","OK");
    }
}
