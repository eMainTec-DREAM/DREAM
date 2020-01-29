package dream.tool.stk.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.tool.stk.dto.MaPttStckCommonDTO;
import dream.tool.stk.form.MaPttStckListForm;
import dream.tool.stk.service.MaPttStckListService;

/**
 * 자재재고 - 목록 action
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/maPttStckList" name="maPttStckListForm"
 *                input="/dream/tool/stk/maPttStckList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPttStckList" path="/dream/tool/stk/maPttStckList.jsp"
 *                        redirect="false"
 */
public class MaPttStckListAction extends AuthAction
{
    /** 조회 */
    public static final int PTSTCK_LIST_FIND 	= 1001;
    /** 삭제 */
    public static final int PTSTCK_LIST_DELETE  = 7002;
    /** 폐기신청 */
    public static final int PTSTCK_LIST_DISREQ  = 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaPttStckListForm maPttStckListForm = (MaPttStckListForm) form;
        
        switch (maPttStckListForm.getStrutsAction())
        {
            case MaPttStckListAction.PTSTCK_LIST_FIND:
            	findPtStckList(request, maPttStckListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPttStckListAction.BASE_SET_HEADER:
                setHeader(request, response, maPttStckListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPttStckListAction.PTSTCK_LIST_DELETE:
            	deletePtStck(request, maPttStckListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaPttStckListAction.PTSTCK_LIST_DISREQ:
            	reqPtStck(request, maPttStckListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaPttStckListAction.BASE_GRID_EXPORT:
            	findPtStckList(request, maPttStckListForm,response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maPttStckList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaPttStckListForm maPttStckListForm) throws IOException
    {
        super.setHeader(request, response, maPttStckListForm.getListId(), maPttStckListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPttStckListForm
     * @throws Exception
     */
    private void findPtStckList(HttpServletRequest request, MaPttStckListForm maPttStckListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	MaPttStckListService maPttStckListService = (MaPttStckListService) getBean("maPttStckListService");        

    	MaPttStckCommonDTO maPttStckCommonDTO = maPttStckListForm.getMaPttStckCommonDTO();
    	maPttStckCommonDTO.setCompNo(getUser(request).getCompNo());
    	//Paging
    	maPttStckCommonDTO.setIsLoadMaxCount("Y".equals(maPttStckListForm.getIsLoadMaxCount())?true:false);
    	maPttStckCommonDTO.setFirstRow(maPttStckListForm.getFirstRow());
    	maPttStckCommonDTO.setOrderBy(maPttStckListForm.getOrderBy());
    	maPttStckCommonDTO.setDirection(maPttStckListForm.getDirection());
        
        //리스트를 조회한다.
        List resultList = maPttStckListService.findPtStckList(maPttStckCommonDTO);
        String totalCount = "";
        
        if(Integer.parseInt(maPttStckListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maPttStckListService.findTotalCount(maPttStckCommonDTO, getUser(request));
        
        if(excelExport) super.makeGridExport(resultList, request, response,maPttStckListForm.getListId(),maPttStckListForm.getCurrentPageId(), maPttStckListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * req
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtStckListForm
     * @param request
     */
    private void reqPtStck(HttpServletRequest request, MaPttStckListForm maPttStckListForm) throws Exception
    {
    	MaPttStckListService maPttStckListService = (MaPttStckListService) getBean("maPttStckListService");
        maPttStckListService.reqPtStck(maPttStckListForm, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * delete
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttStckListForm
     * @param request
     */
    private void deletePtStck(HttpServletRequest request, MaPttStckListForm maPttStckListForm) throws Exception
    {
    	MaPttStckListService maPttStckListService = (MaPttStckListService) getBean("maPttStckListService");
    	String[] deleteRows = maPttStckListForm.getDeleteRows();    // wcode_id list
    	String[] deleteRowsExt = maPttStckListForm.getDeleteRowsExt();    // part_id list
        
        maPttStckListService.deletePtStck(getUser(request).getCompNo(), deleteRows, deleteRowsExt, getUser(request));
        
        setAjaxStatus(request);
    }
}
