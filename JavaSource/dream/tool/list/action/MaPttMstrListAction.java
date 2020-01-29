package dream.tool.list.action;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.tool.list.dto.MaPttMstrCommonDTO;
import dream.tool.list.form.MaPttMstrListForm;
import dream.tool.list.service.MaPttMstrListService;

/**
 * 보전자재분류(마스터) - 목록 action
 * @author  ssong
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/maPttMstrList" name="maPttMstrListForm"
 *                input="/dream/tool/list/maPttMstrList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPttMstrList" path="/dream/tool/list/maPttMstrList.jsp"
 *                        redirect="false"
 */
public class MaPttMstrListAction extends AuthAction
{
    /** 조회 */
    public static final int PTMSTR_LIST_FIND 	= 1001;
    /** 삭제 */
    public static final int PTMSTR_LIST_DELETE  = 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaPttMstrListForm maPttMstrListForm = (MaPttMstrListForm) form;
        
        //super.updateAudit(maPttMstrListForm.getMaPttMstrCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maPttMstrListForm.getStrutsAction())
        {
            case MaPttMstrListAction.BASE_SET_HEADER:
                setHeader(request, response, maPttMstrListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPttMstrListAction.PTMSTR_LIST_FIND:
                findList(request, response, maPttMstrListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case MaPttMstrListAction.PTMSTR_LIST_DELETE:
            	deleteList(request, maPttMstrListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case MaPttMstrListAction.BASE_GRID_EXPORT:
            	findList(request, response, maPttMstrListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maPttMstrList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaPttMstrListForm maPttMstrListForm) throws IOException
    {
        super.setHeader(request, response, maPttMstrListForm.getListId(), maPttMstrListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maPttMstrListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, MaPttMstrListForm maPttMstrListForm, boolean excelExport) throws IOException
    {
    	MaPttMstrListService maPttMstrListService = (MaPttMstrListService) getBean("maPttMstrListService");        
    	MaPttMstrCommonDTO maPttMstrCommonDTO = maPttMstrListForm.getMaPttMstrCommonDTO();

        //Paging
    	maPttMstrCommonDTO.setIsLoadMaxCount("Y".equals(maPttMstrListForm.getIsLoadMaxCount())?true:false);
    	maPttMstrCommonDTO.setFirstRow(maPttMstrListForm.getFirstRow());
    	maPttMstrCommonDTO.setOrderBy(maPttMstrListForm.getOrderBy());
    	maPttMstrCommonDTO.setDirection(maPttMstrListForm.getDirection());
        
    	// 로긴 comp_no 를 셋팅한다.
    	maPttMstrCommonDTO.setFilterCompNo((getUser(request).getCompNo()));
        
        //리스트를 조회한다.
        List resultList = maPttMstrListService.findList(maPttMstrCommonDTO, getUser(request));
        
        String totalCount = "";
        if(Integer.parseInt(maPttMstrListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maPttMstrListService.findTotalCount(maPttMstrCommonDTO, getUser(request));
        
        if(excelExport) super.makeGridExport(resultList, request, response,maPttMstrListForm.getListId(),maPttMstrListForm.getCurrentPageId(), maPttMstrListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * delete
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPttMstrListForm
     * @param request
     */
    private void deleteList(HttpServletRequest request, MaPttMstrListForm maPttMstrListForm) throws Exception
    {
    	MaPttMstrListService maPttMstrListService = (MaPttMstrListService) getBean("maPttMstrListService");        

        String[] deleteRows = maPttMstrListForm.getDeleteRows();    // sheet 내역
        
        maPttMstrListService.deleteList(deleteRows, getUser(request));
        
        setAjaxStatus(request);
    }
}
