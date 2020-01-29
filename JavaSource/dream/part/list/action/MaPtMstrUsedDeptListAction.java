package dream.part.list.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.form.MaPtMstrUsedDeptListForm;
import dream.part.list.service.MaPtMstrUsedDeptListService;

/**
 * 부품사용부서 목록
 * @author  ssong
 * @version $Id$
 * @since   1.0
 * @struts:action path="/maPtMstrUsedDeptList" name="maPtMstrUsedDeptListForm"
 *                input="/dream/part/list/maPtMstrUsedDeptList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPtMstrUsedDeptList" path="/dream/part/list/maPtMstrUsedDeptList.jsp"
 *                        redirect="false"
 */
public class MaPtMstrUsedDeptListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int PTMSTR_USEDDEPT_LIST_FIND   = 1001;
    /** 삭제 */
    public static final int PTMSTR_USEDDEPT_LIST_DELETE = 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPtMstrUsedDeptListForm maPtMstrUsedDeptListForm = (MaPtMstrUsedDeptListForm) form;
        
        switch (maPtMstrUsedDeptListForm.getStrutsAction())
        {
        
            case MaPtMstrUsedDeptListAction.PTMSTR_USEDDEPT_LIST_FIND:
                findList(request,response, maPtMstrUsedDeptListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtMstrUsedDeptListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, maPtMstrUsedDeptListForm.getListId(), maPtMstrUsedDeptListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtMstrUsedDeptListAction.PTMSTR_USEDDEPT_LIST_DELETE:
            	deleteList(request,maPtMstrUsedDeptListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPtMstrUsedDeptListAction.BASE_GRID_EXPORT:
            	findList(request,response, maPtMstrUsedDeptListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maPtMstrUsedDeptList");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPtMstrUsedDeptListForm
     * @param b 
     * @throws Exception
     */
    private void findList(HttpServletRequest request,HttpServletResponse response, MaPtMstrUsedDeptListForm maPtMstrUsedDeptListForm, boolean excelExport) throws Exception
    {
        MaPtMstrUsedDeptListService maPtMstrUsedDeptListService = (MaPtMstrUsedDeptListService) getBean("maPtMstrUsedDeptListService");
    	MaPtMstrCommonDTO maPtMstrCommonDTO = maPtMstrUsedDeptListForm.getMaPtMstrCommonDTO();
    	
        String compNo = getUser(request).getCompNo();
        String partId = maPtMstrUsedDeptListForm.getMaPtMstrCommonDTO().getPartId();
        
    	maPtMstrCommonDTO.setIsLoadMaxCount("Y".equals(maPtMstrUsedDeptListForm.getIsLoadMaxCount())?true:false);
    	maPtMstrCommonDTO.setFirstRow(maPtMstrUsedDeptListForm.getFirstRow());
    	maPtMstrCommonDTO.setOrderBy(maPtMstrUsedDeptListForm.getOrderBy());
    	maPtMstrCommonDTO.setDirection(maPtMstrUsedDeptListForm.getDirection());
    	
        List resultList = maPtMstrUsedDeptListService.findList(maPtMstrUsedDeptListForm.getMaPtMstrCommonDTO(), getUser(request));
        
        String totalCount = "";
        if(Integer.parseInt(maPtMstrUsedDeptListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maPtMstrUsedDeptListService.findTotalCount(maPtMstrCommonDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,maPtMstrUsedDeptListForm.getListId(),maPtMstrUsedDeptListForm.getCurrentPageId(), maPtMstrUsedDeptListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * delete
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPtMstrUsedDeptListForm
     * @throws Exception
     */
    private void deleteList(HttpServletRequest request, MaPtMstrUsedDeptListForm maPtMstrUsedDeptListForm) throws Exception
    {
    	MaPtMstrUsedDeptListService maPtMstrUsedDeptListService = (MaPtMstrUsedDeptListService) getBean("maPtMstrUsedDeptListService");
        
    	String compNo = getUser(request).getCompNo();
    	String[] deleteRows = maPtMstrUsedDeptListForm.getDeleteRows();
    
    	maPtMstrUsedDeptListService.deleteList(deleteRows, getUser(request));
    	
    	setAjaxStatus(request);
    }
}