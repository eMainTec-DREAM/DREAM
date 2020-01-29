package dream.scheduler.list.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.scheduler.list.dto.MaBatchMngCommonDTO;
import dream.scheduler.list.form.MaBatchMngListForm;
import dream.scheduler.list.service.MaBatchMngListService;

/**
 * 버튼 - 목록 action
 * @author  kim21017
 * @version $Id: MaBatchMngListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maBatchMngList" name="maBatchMngListForm"
 *                input="/dream/scheduler/list/maBatchMngList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maBatchMngList" path="/dream/scheduler/list/maBatchMngList.jsp"
 *                        redirect="false"
 */
public class MaBatchMngListAction extends AuthAction
{
    /** 조회 */
    public static final int BATCH_LIST_FIND 	= 1001;
    /** 삭제 */
    public static final int BATCH_LIST_DELETE 	= 7002;
    /** 프로시져실행 */
    public static final int BATCH_LIST_EXEC 	= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaBatchMngListForm maBatchMngListForm = (MaBatchMngListForm) form;
        
        switch (maBatchMngListForm.getStrutsAction())
        {
            case MaBatchMngListAction.BATCH_LIST_FIND:
                findBatchList(request, maBatchMngListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaBatchMngListAction.BASE_SET_HEADER:
                setHeader(request, response, maBatchMngListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaBatchMngListAction.BATCH_LIST_DELETE:
            	deleteBatch(request, maBatchMngListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaBatchMngListAction.BATCH_LIST_EXEC:
            	execBatch(request, maBatchMngListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaBatchMngListAction.BASE_GRID_EXPORT:
            	findBatchList(request, maBatchMngListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maBatchMngList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaBatchMngListForm maBatchMngListForm) throws IOException
    {
        super.setHeader(request, response, maBatchMngListForm.getListId(),maBatchMngListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaBatchMngListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maBatchMngListForm
     * @param response
     * @throws Exception
     */
    private void findBatchList(HttpServletRequest request, MaBatchMngListForm maBatchMngListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	MaBatchMngListService maBatchMngListService = (MaBatchMngListService) getBean("maBatchMngListService");        

    	MaBatchMngCommonDTO maBatchMngCommonDTO = maBatchMngListForm.getMaBatchMngCommonDTO();
    	maBatchMngCommonDTO.setCompNo(getUser(request).getCompNo());
    	
    	//Paging
    	maBatchMngCommonDTO.setIsLoadMaxCount("Y".equals(maBatchMngListForm.getIsLoadMaxCount())?true:false);
    	maBatchMngCommonDTO.setFirstRow(maBatchMngListForm.getFirstRow());
    	maBatchMngCommonDTO.setOrderBy(maBatchMngListForm.getOrderBy());
    	maBatchMngCommonDTO.setDirection(maBatchMngListForm.getDirection());

        //리스트를 조회한다.
        List resultList = maBatchMngListService.findBatchList(maBatchMngCommonDTO);

        //Paging
        String totalCount = "";
                
        if(Integer.parseInt(maBatchMngListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maBatchMngListService.findTotalCount(maBatchMngCommonDTO,getUser(request));
                
        if(excelExport) super.makeGridExport(resultList, request, response, maBatchMngListForm.getListId(),maBatchMngListForm.getCurrentPageId(), maBatchMngListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
	}
    /**
     * delete
     * @author  kim21017
     * @version $Id: MaBatchMngListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maBatchMngListForm
     */
    private void deleteBatch(HttpServletRequest request, MaBatchMngListForm maBatchMngListForm) throws Exception
    {
    	MaBatchMngListService maBatchMngListService = (MaBatchMngListService) getBean("maBatchMngListService");        

    	String[] deleteRows = maBatchMngListForm.getDeleteRows();
    	
    	maBatchMngListService.deleteBatch(deleteRows, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * exec
     * @author  kim21017
     * @version $Id: MaBatchMngListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maBatchMngListForm
     */
    private void execBatch(HttpServletRequest request, MaBatchMngListForm maBatchMngListForm) throws Exception
    {
    	MaBatchMngListService maBatchMngListService = (MaBatchMngListService) getBean("maBatchMngListService");        

    	String[] selectRows = maBatchMngListForm.getSelectRows();
    	
    	maBatchMngListService.execBatch(selectRows, getUser(request));
        
        setAjaxStatus(request);
    }
}
