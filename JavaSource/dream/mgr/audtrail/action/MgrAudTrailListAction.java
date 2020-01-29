package dream.mgr.audtrail.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.SuperAuthAction;
import dream.mgr.audtrail.dto.MgrAudTrailCommonDTO;
import dream.mgr.audtrail.form.MgrAudTrailListForm;
import dream.mgr.audtrail.service.MgrAudTrailListService;

/**
 * Audit Trail  Action
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @struts:action path="/mgrAudTrailList" name="mgrAudTrailListForm"
 *                input="/dream/mgr/audtrail/mgrAudTrailList.jsp" scope="request"
 *                validate="false"
 */
public class MgrAudTrailListAction extends SuperAuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MgrAudTrailListForm mgrAudTrailListForm = (MgrAudTrailListForm) form;
        
        switch (mgrAudTrailListForm.getStrutsAction())
        {
            case MgrAudTrailListAction.LIST_FIND:
            	findList(request, mgrAudTrailListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrAudTrailListAction.BASE_SET_HEADER:
                setHeader(request, response, mgrAudTrailListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrAudTrailListAction.BASE_GRID_EXPORT:
            	findList(request, mgrAudTrailListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MgrAudTrailListForm mgrAudTrailListForm) throws IOException
    {
        super.setHeader(request, response, mgrAudTrailListForm.getListId(),mgrAudTrailListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param mgrAudTrailListForm
     * @param response
     * @throws Exception
     */
    private void findList(HttpServletRequest request, MgrAudTrailListForm mgrAudTrailListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	MgrAudTrailListService mgrAudTrailListService = (MgrAudTrailListService) getBean("mgrAudTrailListService");        

    	MgrAudTrailCommonDTO mgrAudTrailCommonDTO = mgrAudTrailListForm.getMgrAudTrailCommonDTO();
    	
    	//Paging
        mgrAudTrailCommonDTO.setIsLoadMaxCount("Y".equals(mgrAudTrailListForm.getIsLoadMaxCount())?true:false);
        mgrAudTrailCommonDTO.setFirstRow(mgrAudTrailListForm.getFirstRow());
        mgrAudTrailCommonDTO.setOrderBy(mgrAudTrailListForm.getOrderBy());
        mgrAudTrailCommonDTO.setDirection(mgrAudTrailListForm.getDirection());
        
        User user = getUser(request);
        
        //리스트를 조회한다.
        List resultList = mgrAudTrailListService.findList(mgrAudTrailCommonDTO, user);

        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(mgrAudTrailListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = mgrAudTrailListService.findTotalCount(mgrAudTrailCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,mgrAudTrailListForm.getListId(),mgrAudTrailListForm.getCurrentPageId(), mgrAudTrailListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
	}
}
