package dream.part.adj.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.part.adj.dto.MaPtFcRecCommonDTO;
import dream.part.adj.dto.MaPtFcRecDetailDTO;
import dream.part.adj.form.MaPtFcRecListForm;
import dream.part.adj.service.MaPtFcRecListService;

/**
 * 무상입고 - 목록 action
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/maPtFcRecList" name="maPtFcRecListForm"
 *                input="/dream/part/adj/maPtFcRecList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPtFcRecList" path="/dream/part/adj/maPtFcRecList.jsp"
 *                        redirect="false"
 */
public class MaPtFcRecListAction extends AuthAction
{
    /** 조회 */
    public static final int PTFCREC_LIST_FIND     = 1001;
    /** 삭제 */
    public static final int PTFCREC_LIST_DELETE   = 7002;
    /** 저장 */
    public static final int PTFCREC_LIST_INPUT    = 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaPtFcRecListForm maPtFcRecListForm = (MaPtFcRecListForm) form;
        
        switch (maPtFcRecListForm.getStrutsAction())
        {
            case MaPtFcRecListAction.PTFCREC_LIST_FIND:
            	findPtFcRecList(request, maPtFcRecListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtFcRecListAction.BASE_SET_HEADER:
                setHeader(request, response, maPtFcRecListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtFcRecListAction.PTFCREC_LIST_DELETE:
            	deletePtFcRec(request, maPtFcRecListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaPtFcRecListAction.PTFCREC_LIST_INPUT:
                insertPtFcRec(request, maPtFcRecListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaPtFcRecListAction.BASE_GRID_EXPORT:
            	findPtFcRecList(request, maPtFcRecListForm,response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maPtFcRecList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaPtFcRecListForm maPtFcRecListForm) throws IOException
    {
        super.setHeader(request, response, maPtFcRecListForm.getListId(), maPtFcRecListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPtFcRecListForm
     * @throws Exception
     */
    private void findPtFcRecList(HttpServletRequest request, MaPtFcRecListForm maPtFcRecListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	MaPtFcRecListService maPtFcRecListService = (MaPtFcRecListService) getBean("maPtFcRecListService");        

    	MaPtFcRecCommonDTO maPtFcRecCommonDTO = maPtFcRecListForm.getMaPtFcRecCommonDTO();
    	maPtFcRecCommonDTO.setCompNo(getUser(request).getCompNo());
    	
    	//Paging
    	maPtFcRecCommonDTO.setIsLoadMaxCount("Y".equals(maPtFcRecListForm.getIsLoadMaxCount())?true:false);
    	maPtFcRecCommonDTO.setFirstRow(maPtFcRecListForm.getFirstRow());
    	maPtFcRecCommonDTO.setOrderBy(maPtFcRecListForm.getOrderBy());
    	maPtFcRecCommonDTO.setDirection(maPtFcRecListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = maPtFcRecListService.findList(maPtFcRecCommonDTO,getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(maPtFcRecListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maPtFcRecListService.findTotalCount(maPtFcRecCommonDTO, getUser(request));
        
        if(excelExport) super.makeGridExport(resultList, request, response,maPtFcRecListForm.getListId(),maPtFcRecListForm.getCurrentPageId(), maPtFcRecListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * delete
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtFcRecListForm
     * @param request
     */
    private void deletePtFcRec(HttpServletRequest request, MaPtFcRecListForm maPtFcRecListForm) throws Exception
    {
    	MaPtFcRecListService maPtFcRecListService = (MaPtFcRecListService) getBean("maPtFcRecListService");
    	String[] deleteRows = maPtFcRecListForm.getDeleteRows();    // sheet 내역
        
        maPtFcRecListService.deleteList(getUser(request).getCompNo(), deleteRows);
        
        setAjaxStatus(request);
    }
    
    /**
     * insert
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPtFcRecListForm
     */
    private void insertPtFcRec(HttpServletRequest request, MaPtFcRecListForm maPtFcRecListForm) throws Exception
    {
        MaPtFcRecListService maPtFcRecListService = (MaPtFcRecListService) getBean("maPtFcRecListService");
        
        MaPtFcRecDetailDTO maPtFcRecDetailDTO = maPtFcRecListForm.getMaPtFcRecDetailDTO();
        
        maPtFcRecDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maPtFcRecListService.insertPtFcRec(maPtFcRecDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}
