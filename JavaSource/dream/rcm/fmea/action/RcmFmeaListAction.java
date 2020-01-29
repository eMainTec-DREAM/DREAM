package dream.rcm.fmea.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.rcm.fmea.dto.RcmFmeaCommonDTO;
import dream.rcm.fmea.form.RcmFmeaListForm;
import dream.rcm.fmea.service.RcmFmeaListService;

/**
 * 목록 action
 * @author  kim21017
 * @version $Id: RcmFmeaListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/rcmFmeaList" name="rcmFmeaListForm"
 *                input="/dream/rcm/fmea/rcmFmeaList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="rcmFmeaList" path="/dream/rcm/fmea/rcmFmeaList.jsp"
 *                        redirect="false"
 */
public class RcmFmeaListAction extends AuthAction
{
    /** 조회 */
    public static final int FMEA_LIST_FIND 		= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        RcmFmeaListForm rcmFmeaListForm = (RcmFmeaListForm) form;
        
        switch (rcmFmeaListForm.getStrutsAction())
        {
            case RcmFmeaListAction.FMEA_LIST_FIND:
                findList(request, rcmFmeaListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case RcmFmeaListAction.BASE_SET_HEADER:
                setHeader(request, response, rcmFmeaListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case RcmFmeaListAction.BASE_GRID_EXPORT:
            	findList(request, rcmFmeaListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("rcmFmeaList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, RcmFmeaListForm rcmFmeaListForm) throws IOException
    {
        super.setHeader(request, response, rcmFmeaListForm.getListId(), rcmFmeaListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: RcmFmeaListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param rcmFmeaListForm
     * @throws IOException 
     * @throws Exception
     */
    private void findList(HttpServletRequest request, RcmFmeaListForm rcmFmeaListForm, HttpServletResponse response, boolean excelExport) throws IOException 
    {
    	RcmFmeaListService rcmFmeaListService = (RcmFmeaListService) getBean("rcmFmeaListService");        

    	RcmFmeaCommonDTO rcmFmeaCommonDTO = rcmFmeaListForm.getRcmFmeaCommonDTO();
    	rcmFmeaCommonDTO.setCompNo(getUser(request).getCompNo());
    	
    	//Paging
    	rcmFmeaCommonDTO.setIsLoadMaxCount("Y".equals(rcmFmeaListForm.getIsLoadMaxCount())?true:false);
    	rcmFmeaCommonDTO.setFirstRow(rcmFmeaListForm.getFirstRow());
    	rcmFmeaCommonDTO.setOrderBy(rcmFmeaListForm.getOrderBy());
    	rcmFmeaCommonDTO.setDirection(rcmFmeaListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = rcmFmeaListService.findList(rcmFmeaCommonDTO, getUser(request));
 
        String totalCount = "";
        if(Integer.parseInt(rcmFmeaListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = rcmFmeaListService.findTotalCount(rcmFmeaCommonDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,rcmFmeaListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
}
