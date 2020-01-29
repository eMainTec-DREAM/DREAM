package dream.part.list.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.part.list.dto.MaPtMstrStockListDTO;
import dream.part.list.form.MaPtMstrStockListForm;
import dream.part.list.service.MaPtMstrStockListService;

/**
 * 자재재고 - 목록 action
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/maPtMstrStockList" name="maPtMstrStockListForm"
 *                input="/dream/part/list/maPtMstrStockList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPtMstrStockList" path="/dream/part/list/maPtMstrStockList.jsp"
 *                        redirect="false"
 */
public class MaPtMstrStockListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaPtMstrStockListForm maPtMstrStockListForm = (MaPtMstrStockListForm) form;
        
        switch (maPtMstrStockListForm.getStrutsAction())
        {
            case MaPtMstrStockListAction.LIST_FIND:
            	findPtMstrStockList(request, maPtMstrStockListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtMstrStockListAction.BASE_SET_HEADER:
                setHeader(request, response, maPtMstrStockListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtMstrStockListAction.BASE_GRID_EXPORT:
            	findPtMstrStockList(request, maPtMstrStockListForm,response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaPtMstrStockListForm maPtMstrStockListForm) throws IOException
    {
        super.setHeader(request, response, maPtMstrStockListForm.getListId(), maPtMstrStockListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPtMstrStockListForm
     * @throws Exception
     */
    private void findPtMstrStockList(HttpServletRequest request, MaPtMstrStockListForm maPtMstrStockListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	MaPtMstrStockListService maPtMstrStockListService = (MaPtMstrStockListService) getBean("maPtMstrStockListService", request);        

    	MaPtMstrStockListDTO maPtMstrStockListDTO = maPtMstrStockListForm.getMaPtMstrStockListDTO();
    	
    	//Paging
    	maPtMstrStockListDTO.setIsLoadMaxCount("Y".equals(maPtMstrStockListForm.getIsLoadMaxCount())?true:false);
    	maPtMstrStockListDTO.setFirstRow(maPtMstrStockListForm.getFirstRow());
    	maPtMstrStockListDTO.setOrderBy(maPtMstrStockListForm.getOrderBy());
    	maPtMstrStockListDTO.setDirection(maPtMstrStockListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = maPtMstrStockListService.findPtMstrStockList(maPtMstrStockListDTO,getUser(request));
        
        String totalCount = "";
        if(Integer.parseInt(maPtMstrStockListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maPtMstrStockListService.findTotalCount(maPtMstrStockListDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,maPtMstrStockListForm.getListId(),maPtMstrStockListForm.getCurrentPageId(), maPtMstrStockListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
}
