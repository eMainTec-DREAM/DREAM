package dream.tool.iss.rent.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.tool.iss.rent.dto.MaPttIssCommonDTO;
import dream.tool.iss.rent.form.MaPttIssListForm;
import dream.tool.iss.rent.service.MaPttIssListService;

/**
 * 구매입고 - 목록 action
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/maPttIssList" name="maPttIssListForm"
 *                input="/dream/tool/iss/rent/maPttIssList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPttIssList" path="/dream/tool/iss/rent/maPttIssList.jsp"
 *                        redirect="false"
 */
public class MaPttIssListAction extends AuthAction
{
    /** 조회 */
    public static final int PTISS_LIST_FIND     = 1001;
    /** 삭제 */
    public static final int PTISS_LIST_DELETE   = 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaPttIssListForm maPttIssListForm = (MaPttIssListForm) form;
        
        switch (maPttIssListForm.getStrutsAction())
        {
            case MaPttIssListAction.PTISS_LIST_FIND:
            	findPtIssList(request, maPttIssListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPttIssListAction.BASE_SET_HEADER:
                setHeader(request, response, maPttIssListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPttIssListAction.PTISS_LIST_DELETE:
            	deletePtIss(request, maPttIssListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaPttIssListAction.BASE_GRID_EXPORT:
            	findPtIssList(request, maPttIssListForm,response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maPttIssList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaPttIssListForm maPttIssListForm) throws IOException
    {
        super.setHeader(request, response, maPttIssListForm.getListId(), maPttIssListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPttIssListForm
     * @throws Exception
     */
    private void findPtIssList(HttpServletRequest request, MaPttIssListForm maPttIssListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	MaPttIssListService maPttIssListService = (MaPttIssListService) getBean("maPttIssListService");        

    	MaPttIssCommonDTO maPttIssCommonDTO = maPttIssListForm.getMaPttIssCommonDTO();
    	maPttIssCommonDTO.setCompNo(getUser(request).getCompNo());
    	
    	//Paging
    	maPttIssCommonDTO.setIsLoadMaxCount("Y".equals(maPttIssListForm.getIsLoadMaxCount())?true:false);
    	maPttIssCommonDTO.setFirstRow(maPttIssListForm.getFirstRow());
    	maPttIssCommonDTO.setOrderBy(maPttIssListForm.getOrderBy());
    	maPttIssCommonDTO.setDirection(maPttIssListForm.getDirection());

        //리스트를 조회한다.
        List resultList = maPttIssListService.findList(maPttIssCommonDTO,getUser(request));
        String totalCount = "";
        
        if(Integer.parseInt(maPttIssListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maPttIssListService.findTotalCount(maPttIssCommonDTO, getUser(request));
        
        if(excelExport) super.makeGridExport(resultList, request, response,maPttIssListForm.getListId(),maPttIssListForm.getCurrentPageId(), maPttIssListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    
    }
    
    /**
     * delete
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttIssListForm
     * @param request
     */
    private void deletePtIss(HttpServletRequest request, MaPttIssListForm maPttIssListForm) throws Exception
    {
    	MaPttIssListService maPttIssListService = (MaPttIssListService) getBean("maPttIssListService");
    	String[] deleteRows = maPttIssListForm.getDeleteRows();    // sheet 내역
        
        maPttIssListService.deleteList(getUser(request).getCompNo(), deleteRows);
        
        setAjaxStatus(request);
    }
}
