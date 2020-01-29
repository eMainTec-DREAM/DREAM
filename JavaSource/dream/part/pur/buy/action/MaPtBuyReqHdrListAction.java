package dream.part.pur.buy.action;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.part.pur.buy.dto.MaPtBuyReqHdrCommonDTO;
import dream.part.pur.buy.form.MaPtBuyReqHdrListForm;
import dream.part.pur.buy.service.MaPtBuyReqHdrListService;

/**
 * 구매신청 - 목록 action
 * @author  kim21017
 * @version $Id: MaPtBuyReqHdrListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maPtBuyReqHdrList" name="maPtBuyReqHdrListForm"
 *                input="/dream/part/pur/buy/maPtBuyReqHdrList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPtBuyReqHdrList" path="/dream/part/pur/buy/maPtBuyReqHdrList.jsp"
 *                        redirect="false"
 */
public class MaPtBuyReqHdrListAction extends AuthAction
{
    /** 조회 */
    public static final int BUY_LIST_FIND 		= 8001;
    /** 삭제 */
    public static final int BUY_LIST_DELETE 	= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaPtBuyReqHdrListForm maPtBuyReqHdrListForm = (MaPtBuyReqHdrListForm) form;
        
        super.updateAudit(maPtBuyReqHdrListForm.getMaPtBuyReqHdrCommonDTO().getAuditKey()==""?maPtBuyReqHdrListForm.getMaPtBuyReqHdrCommonDTO().getAuditKey():maPtBuyReqHdrListForm.getMaPtBuyReqHdrCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maPtBuyReqHdrListForm.getStrutsAction())
        {
            case MaPtBuyReqHdrListAction.BUY_LIST_FIND:
                findBuyList(request, maPtBuyReqHdrListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtBuyReqHdrListAction.BASE_SET_HEADER:
                setHeader(request, response, maPtBuyReqHdrListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtBuyReqHdrListAction.BUY_LIST_DELETE:
                deleteBuy(request, maPtBuyReqHdrListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaPtBuyReqHdrListAction.BASE_GRID_EXPORT:
            	findBuyList(request, maPtBuyReqHdrListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maPtBuyReqHdrList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaPtBuyReqHdrListForm maPtBuyReqHdrListForm) throws IOException
    {
        super.setHeader(request, response, maPtBuyReqHdrListForm.getListId(), maPtBuyReqHdrListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaPtBuyReqHdrListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maPtBuyReqHdrListForm
     * @throws IOException 
     * @throws Exception
     */
    private void findBuyList(HttpServletRequest request, MaPtBuyReqHdrListForm maPtBuyReqHdrListForm, HttpServletResponse response, boolean excelExport) throws IOException 
    {
    	MaPtBuyReqHdrListService maPtBuyReqHdrListService = (MaPtBuyReqHdrListService) getBean("maPtBuyReqHdrListService");        

    	MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO = maPtBuyReqHdrListForm.getMaPtBuyReqHdrCommonDTO();
    	maPtBuyReqHdrCommonDTO.setCompNo(getUser(request).getCompNo());
    	
    	//Paging
    	maPtBuyReqHdrCommonDTO.setIsLoadMaxCount("Y".equals(maPtBuyReqHdrListForm.getIsLoadMaxCount())?true:false);
    	maPtBuyReqHdrCommonDTO.setFirstRow(maPtBuyReqHdrListForm.getFirstRow());
    	maPtBuyReqHdrCommonDTO.setOrderBy(maPtBuyReqHdrListForm.getOrderBy());
    	maPtBuyReqHdrCommonDTO.setDirection(maPtBuyReqHdrListForm.getDirection());

        //리스트를 조회한다.
        List resultList = maPtBuyReqHdrListService.findBuyList(maPtBuyReqHdrCommonDTO,getUser(request));
        
        //Paging
        String totalCount = "";
                
        if(Integer.parseInt(maPtBuyReqHdrListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maPtBuyReqHdrListService.findTotalCount(maPtBuyReqHdrCommonDTO,getUser(request));
                
        if(excelExport) super.makeGridExport(resultList, request, response, maPtBuyReqHdrListForm.getListId(),maPtBuyReqHdrListForm.getCurrentPageId(), maPtBuyReqHdrListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim21017
     * @version $Id: MaPtBuyReqHdrListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPtBuyReqHdrListForm
     * @param request
     */
    private void deleteBuy(HttpServletRequest request, MaPtBuyReqHdrListForm maPtBuyReqHdrListForm) throws Exception
    {
    	
    	System.out.println("action의 deleteBuy :::::::::::::::::::::::::::::::::::::::::::::: ");
    	MaPtBuyReqHdrListService maPtBuyReqHdrListService = (MaPtBuyReqHdrListService) getBean("maPtBuyReqHdrListService");        

    	String[] deleteRows = maPtBuyReqHdrListForm.getDeleteRows();    // sheet 내역
        
    	maPtBuyReqHdrListService.deleteBuy(deleteRows,getUser(request));
        
        setAjaxStatus(request);
    }
}
