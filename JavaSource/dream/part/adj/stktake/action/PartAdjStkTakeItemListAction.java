package dream.part.adj.stktake.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.part.adj.stktake.dto.PartAdjStkTakeCommonDTO;
import dream.part.adj.stktake.dto.PartAdjStkTakeItemDetailDTO;
import dream.part.adj.stktake.dto.PartAdjStkTakeListDTO;
import dream.part.adj.stktake.form.PartAdjStkTakeItemListForm;
import dream.part.adj.stktake.service.PartAdjStkTakeItemListService;

/**
 * 부품실사 item  목록
 * @author  kim21017
 * @version $Id: PartAdjStkTakeItemListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/partAdjStkTakeItemList" name="partAdjStkTakeItemListForm"
 *                input="/dream/part/adj/stktake/partAdjStkTakeItemList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="partAdjStkTakeItemList" path="/dream/part/adj/stktake/partAdjStkTakeItemList.jsp"
 *                        redirect="false"
 */
public class PartAdjStkTakeItemListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int BUY_ITEM_LIST_FIND 			= 8001;
    /** 삭제 */
    public static final int BUY_ITEM_LIST_DELETE 		= 7002;
    /** 입력 */
    public static final int BUY_ITEM_LIST_INPUT         = 5003;
    /** TAEXCELTAB 데이터 가져오기 */
    public static final int GET_DATA		 			= 1004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        PartAdjStkTakeItemListForm partAdjStkTakeItemListForm = (PartAdjStkTakeItemListForm) form;
        
        super.updateAudit(partAdjStkTakeItemListForm.getPartAdjStkTakeListDTO().getAuditKey()==""?partAdjStkTakeItemListForm.getPartAdjStkTakeListDTO().getAuditKey():partAdjStkTakeItemListForm.getPartAdjStkTakeListDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (partAdjStkTakeItemListForm.getStrutsAction())
        {
            case PartAdjStkTakeItemListAction.BASE_SET_HEADER:
                super.setHeader(request, response, partAdjStkTakeItemListForm.getListId(), partAdjStkTakeItemListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case PartAdjStkTakeItemListAction.BUY_ITEM_LIST_FIND:
                findItemList(request, response, partAdjStkTakeItemListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case PartAdjStkTakeItemListAction.BUY_ITEM_LIST_DELETE:
            	deleteItemList(request,partAdjStkTakeItemListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case PartAdjStkTakeItemListAction.BUY_ITEM_LIST_INPUT:
                insertItemList(request,partAdjStkTakeItemListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case PartAdjStkTakeItemListAction.GET_DATA:
            	getData(request,response, partAdjStkTakeItemListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case PartAdjStkTakeItemListAction.BASE_GRID_EXPORT:
            	findItemList(request,response, partAdjStkTakeItemListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("partAdjStkTakeItemList");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: PartAdjStkTakeItemListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param partAdjStkTakeItemListForm
     * @throws Exception
     */
    private void findItemList(HttpServletRequest request, HttpServletResponse response, PartAdjStkTakeItemListForm partAdjStkTakeItemListForm, boolean excelExport) throws Exception
    {
        PartAdjStkTakeItemListService partAdjStkTakeItemListService = (PartAdjStkTakeItemListService) getBean("partAdjStkTakeItemListService");
        PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO = partAdjStkTakeItemListForm.getPartAdjStkTakeCommonDTO();
        partAdjStkTakeCommonDTO.setCompNo(getUser(request).getCompNo());
        PartAdjStkTakeListDTO partAdjStkTakeListDTO = partAdjStkTakeItemListForm.getPartAdjStkTakeListDTO();
        
        //Paging
        partAdjStkTakeListDTO.setIsLoadMaxCount("Y".equals(partAdjStkTakeItemListForm.getIsLoadMaxCount())?true:false);
        partAdjStkTakeListDTO.setFirstRow(partAdjStkTakeItemListForm.getFirstRow());
        partAdjStkTakeListDTO.setOrderBy(partAdjStkTakeItemListForm.getOrderBy());
        partAdjStkTakeListDTO.setDirection(partAdjStkTakeItemListForm.getDirection());
        
        User user = getUser(request);
        List resultList = partAdjStkTakeItemListService.findItemList(partAdjStkTakeCommonDTO, partAdjStkTakeListDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(partAdjStkTakeItemListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = partAdjStkTakeItemListService.findTotalCount(partAdjStkTakeCommonDTO, partAdjStkTakeListDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,partAdjStkTakeItemListForm.getListId(),partAdjStkTakeItemListForm.getCurrentPageId(), partAdjStkTakeItemListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: PartAdjStkTakeItemListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param partAdjStkTakeItemListForm
     * @throws Exception
     */
    private void deleteItemList(HttpServletRequest request, PartAdjStkTakeItemListForm partAdjStkTakeItemListForm) throws Exception
    {
    	PartAdjStkTakeItemListService partAdjStkTakeItemListService = (PartAdjStkTakeItemListService) getBean("partAdjStkTakeItemListService");
        
    	String[] deleteRows = partAdjStkTakeItemListForm.getDeleteRows();
        
        User user = getUser(request);
    	
    	partAdjStkTakeItemListService.deleteItemList(deleteRows, user);
    	
    	setAjaxStatus(request);
    }
    
    /**
     * insert
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param partAdjStkTakeItemListForm
     */
    private void insertItemList(HttpServletRequest request, PartAdjStkTakeItemListForm partAdjStkTakeItemListForm) throws Exception
    {
        PartAdjStkTakeItemListService partAdjStkTakeItemListService = (PartAdjStkTakeItemListService) getBean("partAdjStkTakeItemListService");
        
        PartAdjStkTakeItemDetailDTO partAdjStkTakeItemDetailDTO = partAdjStkTakeItemListForm.getPartAdjStkTakeItemDetailDTO();
        
        PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO = partAdjStkTakeItemListForm.getPartAdjStkTakeCommonDTO();
        partAdjStkTakeCommonDTO.setCompNo(getUser(request).getCompNo());
        
        partAdjStkTakeItemListService.insertItemList(partAdjStkTakeItemDetailDTO, partAdjStkTakeCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    private void getData(HttpServletRequest request, HttpServletResponse response, PartAdjStkTakeItemListForm partAdjStkTakeItemListForm) throws Exception
    {
        PartAdjStkTakeItemListService partAdjStkTakeItemListService = (PartAdjStkTakeItemListService) getBean("partAdjStkTakeItemListService");
        	
    	User user = getUser(request);
    	
    	String result = partAdjStkTakeItemListService.getData(user);
    	
    	setAjaxDesc(request, result);
    }
    
}