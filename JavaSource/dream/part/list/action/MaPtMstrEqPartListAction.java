package dream.part.list.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.dto.MaPtMstrEqPartDetailDTO;
import dream.part.list.form.MaPtMstrEqPartDetailForm;
import dream.part.list.form.MaPtMstrEqPartListForm;
import dream.part.list.service.MaPtMstrEqPartDetailService;
import dream.part.list.service.MaPtMstrEqPartListService;

/**
 * 사용설비 목록
 * @author  ssong
 * @version $Id$
 * @since   1.0
 * @struts:action path="/maPtMstrEqPartList" name="maPtMstrEqPartListForm"
 *                input="/dream/part/list/maPtMstrEqPartList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPtMstrEqPartList" path="/dream/part/list/maPtMstrEqPartList.jsp"
 *                        redirect="false"
 */
public class MaPtMstrEqPartListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int PTMSTR_EQPART_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int PTMSTR_EQPART_LIST_DELETE 		= 7002;
    /** 입력 */
    public static final int PTMSTR_EQPART_LIST_INPUT        = 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPtMstrEqPartListForm maPtMstrEqPartListForm = (MaPtMstrEqPartListForm) form;
        
        super.updateAudit(maPtMstrEqPartListForm.getMaPtMstrCommonDTO().getAuditKey()==""?maPtMstrEqPartListForm.getMaPtMstrCommonDTO().getAuditKey():maPtMstrEqPartListForm.getMaPtMstrCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maPtMstrEqPartListForm.getStrutsAction())
        {
        
            case MaPtMstrEqPartListAction.PTMSTR_EQPART_LIST_FIND:
                findList(request,response, maPtMstrEqPartListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtMstrEqPartListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, maPtMstrEqPartListForm.getListId(), maPtMstrEqPartListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtMstrEqPartListAction.PTMSTR_EQPART_LIST_DELETE:
            	deleteList(request,maPtMstrEqPartListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPtMstrEqPartListAction.PTMSTR_EQPART_LIST_INPUT:
                insertList(request,maPtMstrEqPartListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaPtMstrEqPartListAction.BASE_GRID_EXPORT:
            	findList(request,response, maPtMstrEqPartListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maPtMstrEqPartList");
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
     * @param maPtMstrEqPartListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request,HttpServletResponse response, MaPtMstrEqPartListForm maPtMstrEqPartListForm, boolean excelExport) throws Exception
    {
        MaPtMstrEqPartListService maPtMstrEqPartListService = (MaPtMstrEqPartListService) getBean("maPtMstrEqPartListService");
        MaPtMstrCommonDTO maPtMstrCommonDTO = maPtMstrEqPartListForm.getMaPtMstrCommonDTO();
        
        
        String compNo = getUser(request).getCompNo();
        String partId = maPtMstrEqPartListForm.getMaPtMstrCommonDTO().getPartId();
        
        //Paging
        maPtMstrCommonDTO.setIsLoadMaxCount("Y".equals(maPtMstrEqPartListForm.getIsLoadMaxCount())?true:false);
        maPtMstrCommonDTO.setFirstRow(maPtMstrEqPartListForm.getFirstRow());
        maPtMstrCommonDTO.setOrderBy(maPtMstrEqPartListForm.getOrderBy());
        maPtMstrCommonDTO.setDirection(maPtMstrEqPartListForm.getDirection());
        
        User user = getUser(request);
        List resultList = maPtMstrEqPartListService.findList(maPtMstrEqPartListForm.getMaPtMstrCommonDTO(), getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(maPtMstrEqPartListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maPtMstrEqPartListService.findTotalCount(maPtMstrCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,maPtMstrEqPartListForm.getListId(),maPtMstrEqPartListForm.getCurrentPageId(), maPtMstrEqPartListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * delete
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPtMstrEqPartListForm
     * @throws Exception
     */
    private void deleteList(HttpServletRequest request, MaPtMstrEqPartListForm maPtMstrEqPartListForm) throws Exception
    {
    	MaPtMstrEqPartListService maPtMstrEqPartListService = (MaPtMstrEqPartListService) getBean("maPtMstrEqPartListService");
        
    	String compNo = getUser(request).getCompNo();
    	String[] deleteRows = maPtMstrEqPartListForm.getDeleteRows();
    
    	maPtMstrEqPartListService.deleteList(deleteRows, getUser(request));
    	
    	setAjaxStatus(request);
    }
    
    /**
     * insert
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtMstrEqPartListForm
     * @param request
     */
    private void insertList(HttpServletRequest request, MaPtMstrEqPartListForm maPtMstrEqPartListForm) throws Exception
    {
        MaPtMstrEqPartListService maPtMstrEqPartListService = (MaPtMstrEqPartListService) getBean("maPtMstrEqPartListService");
        
        MaPtMstrEqPartDetailDTO maPtMstrEqPartDetailDTO = maPtMstrEqPartListForm.getMaPtMstrEqPartDetailDTO();
        
        maPtMstrEqPartDetailDTO.setCompNo((getUser(request).getCompNo()));
        // 자재Id 구함
        String partId = maPtMstrEqPartListForm.getMaPtMstrCommonDTO().getPartId();
        maPtMstrEqPartDetailDTO.setPartId(partId);
        
        maPtMstrEqPartListService.insertList(maPtMstrEqPartDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}