package dream.tool.rec.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.tool.rec.dto.MaPttRecCommonDTO;
import dream.tool.rec.form.MaPttRecListForm;
import dream.tool.rec.service.MaPttRecListService;

/**
 * 구매입고 - 목록 action
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/maPttRecList" name="maPttRecListForm"
 *                input="/dream/tool/rec/maPttRecList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPttRecList" path="/dream/tool/rec/maPttRecList.jsp"
 *                        redirect="false"
 */
public class MaPttRecListAction extends AuthAction
{
    /** 조회 */
    public static final int PTREC_LIST_FIND     = 1001;
    /** 삭제 */
    public static final int PTREC_LIST_DELETE   = 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaPttRecListForm maPttRecListForm = (MaPttRecListForm) form;
        
        switch (maPttRecListForm.getStrutsAction())
        {
            case MaPttRecListAction.PTREC_LIST_FIND:
            	findPtRecList(request, maPttRecListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPttRecListAction.BASE_SET_HEADER:
                setHeader(request, response, maPttRecListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPttRecListAction.PTREC_LIST_DELETE:
            	deletePtRec(request, maPttRecListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaPttRecListAction.BASE_GRID_EXPORT:
            	findPtRecList(request, maPttRecListForm,response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maPttRecList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaPttRecListForm maPttRecListForm) throws IOException
    {
        super.setHeader(request, response, maPttRecListForm.getListId(), maPttRecListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPttRecListForm
     * @throws Exception
     */
    private void findPtRecList(HttpServletRequest request, MaPttRecListForm maPttRecListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	MaPttRecListService maPttRecListService = (MaPttRecListService) getBean("maPttRecListService");        

    	MaPttRecCommonDTO maPttRecCommonDTO = maPttRecListForm.getMaPttRecCommonDTO();
    	maPttRecCommonDTO.setCompNo(getUser(request).getCompNo());
    	
    	//Paging
    	maPttRecCommonDTO.setIsLoadMaxCount("Y".equals(maPttRecListForm.getIsLoadMaxCount())?true:false);
    	maPttRecCommonDTO.setFirstRow(maPttRecListForm.getFirstRow());
    	maPttRecCommonDTO.setOrderBy(maPttRecListForm.getOrderBy());
    	maPttRecCommonDTO.setDirection(maPttRecListForm.getDirection());

    	//리스트를 조회한다.
        List resultList = maPttRecListService.findList(maPttRecCommonDTO,getUser(request));
        String totalCount = "";
        
        if(Integer.parseInt(maPttRecListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maPttRecListService.findTotalCount(maPttRecCommonDTO, getUser(request));
        
        if(excelExport) super.makeGridExport(resultList, request, response,maPttRecListForm.getListId(),maPttRecListForm.getCurrentPageId(), maPttRecListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * delete
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttRecListForm
     * @param request
     */
    private void deletePtRec(HttpServletRequest request, MaPttRecListForm maPttRecListForm) throws Exception
    {
    	MaPttRecListService maPttRecListService = (MaPttRecListService) getBean("maPttRecListService");
    	String[] deleteRows = maPttRecListForm.getDeleteRows();    // sheet 내역
        
        maPttRecListService.deleteList(getUser(request).getCompNo(), deleteRows);
        
        setAjaxStatus(request);
    }
}
