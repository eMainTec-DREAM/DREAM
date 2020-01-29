package dream.mapt.mastat.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.mapt.mastat.dto.MaPtExpStatCommonDTO;
import dream.mapt.mastat.form.MaPtExpStatListForm;
import dream.mapt.mastat.service.MaPtExpStatListService;

/**
 * 자재비용분석 - 목록 action
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/maPtExpStatList" name="maPtExpStatListForm"
 *                input="/dream/mapt/mastat/maPtExpStatList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPtExpStatList" path="/dream/mapt/mastat/maPtExpStatList.jsp"
 *                        redirect="false"
 */
public class MaPtExpStatListAction extends AuthAction
{
    /** 조회 */
    public static final int PTEXP_STAT_LIST_FIND     = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaPtExpStatListForm maPtExpStatListForm = (MaPtExpStatListForm) form;
        
        switch (maPtExpStatListForm.getStrutsAction())
        {
            case MaPtExpStatListAction.PTEXP_STAT_LIST_FIND:
            	findList(request, maPtExpStatListForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtExpStatListAction.BASE_SET_HEADER:
                setHeader(request, response, maPtExpStatListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtExpStatListAction.BASE_GRID_EXPORT:
            	findList(request, maPtExpStatListForm,response);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maPtExpStatList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaPtExpStatListForm maPtExpStatListForm) throws IOException
    {
        super.setHeader(request, response, maPtExpStatListForm.getListId(), maPtExpStatListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPtExpStatListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, MaPtExpStatListForm maPtExpStatListForm, HttpServletResponse response) throws IOException
    {
    	MaPtExpStatListService maPtExpStatListService = (MaPtExpStatListService) getBean("maPtExpStatListService");        

    	MaPtExpStatCommonDTO maPtExpStatCommonDTO = maPtExpStatListForm.getMaPtExpStatCommonDTO();
    	maPtExpStatCommonDTO.setCompNo(getUser(request).getCompNo());
    	
        //리스트를 조회한다.
        List resultList = maPtExpStatListService.findList(maPtExpStatCommonDTO);
        super.makeJsonResult(resultList, request, response, maPtExpStatListForm.getListId());
    }
    
}
