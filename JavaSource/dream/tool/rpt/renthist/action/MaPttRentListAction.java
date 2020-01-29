package dream.tool.rpt.renthist.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.tool.rpt.renthist.dto.MaPttRentCommonDTO;
import dream.tool.rpt.renthist.form.MaPttRentListForm;
import dream.tool.rpt.renthist.service.MaPttRentListService;

/**
 * 공기구대여내역 - 목록 action
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/maPttRentList" name="maPttRentListForm"
 *                input="/dream/tool/rpt/renthist/maPttRentList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPttRentList" path="/dream/tool/rpt/renthist/maPttRentList.jsp"
 *                        redirect="false"
 */
public class MaPttRentListAction extends AuthAction
{
    /** 조회 */
    public static final int PTRENT_STAT_LIST_FIND     = 1001;
    /** 반납 */
    public static final int PTRENT_STAT_LIST_RETURN     = 1002;
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaPttRentListForm maPttRentListForm = (MaPttRentListForm) form;
        
        switch (maPttRentListForm.getStrutsAction())
        {
            case MaPttRentListAction.PTRENT_STAT_LIST_FIND:
            	findList(request, maPttRentListForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPttRentListAction.BASE_SET_HEADER:
                setHeader(request, response, maPttRentListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPttRentListAction.PTRENT_STAT_LIST_RETURN:
            	reqPtReturn(request, maPttRentListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaPttRentListAction.BASE_GRID_EXPORT:
            	findList(request, maPttRentListForm,response);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maPttRentList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaPttRentListForm maPttRentListForm) throws IOException
    {
        super.setHeader(request, response, maPttRentListForm.getListId(), maPttRentListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPttRentListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, MaPttRentListForm maPttRentListForm, HttpServletResponse response) throws IOException
    {
    	MaPttRentListService maPttRentListService = (MaPttRentListService) getBean("maPttRentListService");        

    	MaPttRentCommonDTO maPttRentCommonDTO = maPttRentListForm.getMaPttRentCommonDTO();
    	maPttRentCommonDTO.setCompNo(getUser(request).getCompNo());
    	
        //리스트를 조회한다.
        List resultList = maPttRentListService.findList(maPttRentCommonDTO);
        super.makeJsonResult(resultList, request, response, maPttRentListForm.getListId());
    }
    /**
     * req
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtStckListForm
     * @param request
     */
    private void reqPtReturn(HttpServletRequest request, MaPttRentListForm maPttRentListForm) throws Exception
    {
    	MaPttRentListService maPttRentListService = (MaPttRentListService) getBean("maPttRentListService");
        maPttRentListService.reqPtReturn(maPttRentListForm, getUser(request));
        
        setAjaxStatus(request);
    }
}
