package dream.part.pur.po.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.part.pur.po.dto.MaPtPoCommonDTO;
import dream.part.pur.po.form.MaPtPoListForm;
import dream.part.pur.po.service.MaPtPoListService;

/**
 * 발주이력  - 목록 action
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/maPtPoList" name="maPtPoListForm"
 *                input="/dream/part/pur/po/maPtPoList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPtPoList" path="/dream/part/pur/po/maPtPoList.jsp"
 *                        redirect="false"
 */
public class MaPtPoListAction extends AuthAction
{
    /** 조회 */
    public static final int PTPO_LIST_FIND     = 1001;
    /** 삭제 */
    public static final int PTPO_LIST_DELETE   = 7002;
    /** 입고완료 */
    public static final int PTPO_LIST_REC      = 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaPtPoListForm maPtPoListForm = (MaPtPoListForm) form;
        
        switch (maPtPoListForm.getStrutsAction())
        {
            case MaPtPoListAction.PTPO_LIST_FIND:
            	findPtPoList(request, maPtPoListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtPoListAction.BASE_SET_HEADER:
                setHeader(request, response, maPtPoListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtPoListAction.PTPO_LIST_DELETE:
            	deletePtPo(request, maPtPoListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaPtPoListAction.PTPO_LIST_REC:
            	recPtPo(request, maPtPoListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPtPoListAction.BASE_GRID_EXPORT:
            	findPtPoList(request, maPtPoListForm,response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maPtPoList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaPtPoListForm maPtPoListForm) throws IOException
    {
        super.setHeader(request, response, maPtPoListForm.getListId(), maPtPoListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPtPoListForm
     * @throws Exception
     */
    private void findPtPoList(HttpServletRequest request, MaPtPoListForm maPtPoListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	MaPtPoListService maPtPoListService = (MaPtPoListService) getBean("maPtPoListService");        

    	MaPtPoCommonDTO maPtPoCommonDTO = maPtPoListForm.getMaPtPoCommonDTO();
    	maPtPoCommonDTO.setCompNo(getUser(request).getCompNo());
    	
        //Paging
    	maPtPoCommonDTO.setIsLoadMaxCount("Y".equals(maPtPoListForm.getIsLoadMaxCount())?true:false);
        maPtPoCommonDTO.setFirstRow(maPtPoListForm.getFirstRow());
        maPtPoCommonDTO.setOrderBy(maPtPoListForm.getOrderBy());
        maPtPoCommonDTO.setDirection(maPtPoListForm.getDirection());
        maPtPoCommonDTO.setCompNo(getUser(request).getCompNo());
        maPtPoCommonDTO.setUserLang(getUser(request).getLangId());
        
        
        //리스트를 조회한다.
        List resultList = maPtPoListService.findList(maPtPoCommonDTO,getUser(request));
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(maPtPoListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maPtPoListService.findTotalCount(maPtPoCommonDTO, getUser(request));
        
        if(excelExport) super.makeGridExport(resultList, request, response,maPtPoListForm.getListId(),maPtPoListForm.getCurrentPageId(), maPtPoListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * delete
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtPoListForm
     * @param request
     */
    private void deletePtPo(HttpServletRequest request, MaPtPoListForm maPtPoListForm) throws Exception
    {
    	MaPtPoListService maPtPoListService = (MaPtPoListService) getBean("maPtPoListService");
    	String[] deleteRows = maPtPoListForm.getDeleteRows();    // sheet 내역
        
        maPtPoListService.deleteList(getUser(request).getCompNo(), deleteRows);
        
        setAjaxStatus(request);
    }
    private void recPtPo(HttpServletRequest request, MaPtPoListForm maPtPoListForm) throws Exception
    {
    	MaPtPoListService maPtPoListService = (MaPtPoListService) getBean("maPtPoListService");
    	maPtPoListService.recList(maPtPoListForm, getUser(request));
    	
    	setAjaxStatus(request);
    }
}
