package dream.rcm.pmtask.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.rcm.pmtask.dto.RcmPmtaskCommonDTO;
import dream.rcm.pmtask.form.RcmPmtaskListForm;
import dream.rcm.pmtask.service.RcmPmtaskListService;

/**
 * 목록 action
 * @author  kim21017
 * @version $Id: RcmPmtaskListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/rcmPmtaskList" name="rcmPmtaskListForm"
 *                input="/dream/rcm/pmtask/rcmPmtaskList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="rcmPmtaskList" path="/dream/rcm/pmtask/rcmPmtaskList.jsp"
 *                        redirect="false"
 */
public class RcmPmtaskListAction extends AuthAction
{
    /** 조회 */
    public static final int PMTASK_LIST_FIND 		= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        RcmPmtaskListForm rcmPmtaskListForm = (RcmPmtaskListForm) form;
        
        switch (rcmPmtaskListForm.getStrutsAction())
        {
            case RcmPmtaskListAction.PMTASK_LIST_FIND:
                findList(request, rcmPmtaskListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case RcmPmtaskListAction.BASE_SET_HEADER:
                setHeader(request, response, rcmPmtaskListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case RcmPmtaskListAction.BASE_GRID_EXPORT:
            	findList(request, rcmPmtaskListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("rcmPmtaskList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, RcmPmtaskListForm rcmPmtaskListForm) throws IOException
    {
        super.setHeader(request, response, rcmPmtaskListForm.getListId(), rcmPmtaskListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: RcmPmtaskListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param rcmPmtaskListForm
     * @throws IOException 
     * @throws Exception
     */
    private void findList(HttpServletRequest request, RcmPmtaskListForm rcmPmtaskListForm, HttpServletResponse response, boolean excelExport) throws IOException 
    {
    	RcmPmtaskListService rcmPmtaskListService = (RcmPmtaskListService) getBean("rcmPmtaskListService");        

    	RcmPmtaskCommonDTO rcmPmtaskCommonDTO = rcmPmtaskListForm.getRcmPmtaskCommonDTO();
    	rcmPmtaskCommonDTO.setCompNo(getUser(request).getCompNo());
     	//Paging
        rcmPmtaskCommonDTO.setIsLoadMaxCount("Y".equals(rcmPmtaskListForm.getIsLoadMaxCount())?true:false);
        rcmPmtaskCommonDTO.setFirstRow(rcmPmtaskListForm.getFirstRow());
        rcmPmtaskCommonDTO.setOrderBy(rcmPmtaskListForm.getOrderBy());
        rcmPmtaskCommonDTO.setDirection(rcmPmtaskListForm.getDirection());
        //리스트를 조회한다.
        List resultList = rcmPmtaskListService.findList(rcmPmtaskCommonDTO, getUser(request));
    	
        String totalCount = "";
        if(Integer.parseInt(rcmPmtaskListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = rcmPmtaskListService.findTotalCount(rcmPmtaskCommonDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,rcmPmtaskListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
   
    }
}
