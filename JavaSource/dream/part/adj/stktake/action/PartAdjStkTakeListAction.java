package dream.part.adj.stktake.action;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.part.adj.stktake.dto.PartAdjStkTakeCommonDTO;
import dream.part.adj.stktake.form.PartAdjStkTakeListForm;
import dream.part.adj.stktake.service.PartAdjStkTakeListService;

/**
 * 부품실사 - 목록 action
 * @author  kim21017
 * @version $Id: PartAdjStkTakeListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/partAdjStkTakeList" name="partAdjStkTakeListForm"
 *                input="/dream/part/adj/stktake/partAdjStkTakeList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="partAdjStkTakeList" path="/dream/part/adj/stktake/partAdjStkTakeList.jsp"
 *                        redirect="false"
 */
public class PartAdjStkTakeListAction extends AuthAction
{
    /** 조회 */
    public static final int BUY_LIST_FIND 		= 8001;
    /** 삭제 */
    public static final int BUY_LIST_DELETE 	= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        PartAdjStkTakeListForm partAdjStkTakeListForm = (PartAdjStkTakeListForm) form;
        
        super.updateAudit(partAdjStkTakeListForm.getPartAdjStkTakeCommonDTO().getAuditKey()==""?partAdjStkTakeListForm.getPartAdjStkTakeCommonDTO().getAuditKey():partAdjStkTakeListForm.getPartAdjStkTakeCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (partAdjStkTakeListForm.getStrutsAction())
        {
            case PartAdjStkTakeListAction.BUY_LIST_FIND:
                findBuyList(request, partAdjStkTakeListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case PartAdjStkTakeListAction.BASE_SET_HEADER:
                setHeader(request, response, partAdjStkTakeListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case PartAdjStkTakeListAction.BUY_LIST_DELETE:
                deleteBuy(request, partAdjStkTakeListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case PartAdjStkTakeListAction.BASE_GRID_EXPORT:
            	findBuyList(request, partAdjStkTakeListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("partAdjStkTakeList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, PartAdjStkTakeListForm partAdjStkTakeListForm) throws IOException
    {
        super.setHeader(request, response, partAdjStkTakeListForm.getListId(), partAdjStkTakeListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: PartAdjStkTakeListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param partAdjStkTakeListForm
     * @throws IOException 
     * @throws Exception
     */
    private void findBuyList(HttpServletRequest request, PartAdjStkTakeListForm partAdjStkTakeListForm, HttpServletResponse response, boolean excelExport) throws Exception 
    {
    	PartAdjStkTakeListService partAdjStkTakeListService = (PartAdjStkTakeListService) getBean("partAdjStkTakeListService");        

    	PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO = partAdjStkTakeListForm.getPartAdjStkTakeCommonDTO();
    	partAdjStkTakeCommonDTO.setCompNo(getUser(request).getCompNo());
    	
    	//Paging
        partAdjStkTakeCommonDTO.setIsLoadMaxCount("Y".equals(partAdjStkTakeListForm.getIsLoadMaxCount())?true:false);
        partAdjStkTakeCommonDTO.setFirstRow(partAdjStkTakeListForm.getFirstRow());
        partAdjStkTakeCommonDTO.setOrderBy(partAdjStkTakeListForm.getOrderBy());
        partAdjStkTakeCommonDTO.setDirection(partAdjStkTakeListForm.getDirection());
        
        User user = getUser(request);
        //리스트를 조회한다.
        List resultList = partAdjStkTakeListService.findBuyList(partAdjStkTakeCommonDTO,getUser(request));
 
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(partAdjStkTakeListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = partAdjStkTakeListService.findTotalCount(partAdjStkTakeCommonDTO,user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,partAdjStkTakeListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim21017
     * @version $Id: PartAdjStkTakeListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param partAdjStkTakeListForm
     * @param request
     */
    private void deleteBuy(HttpServletRequest request, PartAdjStkTakeListForm partAdjStkTakeListForm) throws Exception
    {
    	PartAdjStkTakeListService partAdjStkTakeListService = (PartAdjStkTakeListService) getBean("partAdjStkTakeListService");        

    	String[] deleteRows = partAdjStkTakeListForm.getDeleteRows();    // sheet 내역
        
    	partAdjStkTakeListService.deleteBuy(deleteRows,getUser(request));
        
        setAjaxStatus(request);
    }
    
}
