package dream.work.rpt.mapmtrend.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.rpt.mapmtrend.dto.MaPmTrendCommonDTO;
import dream.work.rpt.mapmtrend.form.MaPmTrendListForm;
import dream.work.rpt.mapmtrend.service.MaPmTrendListService;

/**
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/maPmTrendList" name="maPmTrendListForm"
 *                input="/dream/work/rpt/pmtrend/maPmTrendList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPmTrendList" path="/dream/work/rpt/pmtrend/maPmTrendList.jsp"
 *                        redirect="false"
 */
public class MaPmTrendListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int EMP_MTTR_LIST_FIND 			= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPmTrendListForm maPmTrendListForm = (MaPmTrendListForm) form;
        
        switch (maPmTrendListForm.getStrutsAction())
        {
        
            case MaPmTrendListAction.EMP_MTTR_LIST_FIND:
                findList(request,response, maPmTrendListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPmTrendListAction.BASE_SET_HEADER:
            	setHeader(request, response, maPmTrendListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPmTrendListAction.BASE_GRID_EXPORT:
            	findList(request,response, maPmTrendListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaPmTrendListForm maPmTrendListForm) throws IOException
    {
        super.setHeader(request, response, maPmTrendListForm.getListId(), maPmTrendListForm.getCurrentPageId()); 
    }
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param maPmTrendListForm
     * @param excelExport
     * @throws Exception
     */
    private void findList(HttpServletRequest request,HttpServletResponse response, MaPmTrendListForm maPmTrendListForm, boolean excelExport) throws Exception
    {
        MaPmTrendListService maPmTrendListService = (MaPmTrendListService) getBean("maPmTrendListService");
        
        MaPmTrendCommonDTO maPmTrendCommonDTO = maPmTrendListForm.getMaPmTrendCommonDTO();
        maPmTrendCommonDTO.setCompNo(getUser(request).getCompNo());
        maPmTrendCommonDTO.setUserLang(getUser(request).getLangId());
        
        //Paging
        maPmTrendCommonDTO.setIsLoadMaxCount("Y".equals(maPmTrendListForm.getIsLoadMaxCount())?true:false);
        maPmTrendCommonDTO.setFirstRow(maPmTrendListForm.getFirstRow());
        maPmTrendCommonDTO.setOrderBy(maPmTrendListForm.getOrderBy());
        maPmTrendCommonDTO.setDirection(maPmTrendListForm.getDirection());
        
        List resultList = maPmTrendListService.findList(maPmTrendCommonDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(maPmTrendListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maPmTrendListService.findTotalCount(maPmTrendCommonDTO,getUser(request));
        
        if(excelExport) super.makeGridExport(resultList, request, response,maPmTrendListForm.getListId(),maPmTrendListForm.getCurrentPageId(), maPmTrendListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
}