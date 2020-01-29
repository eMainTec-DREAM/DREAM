package dream.rcm.fmea.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.rcm.fmea.dto.RcmFmeaCommonDTO;
import dream.rcm.fmea.dto.RcmFmeaCrityListDTO;
import dream.rcm.fmea.form.RcmFmeaCrityListForm;
import dream.rcm.fmea.service.RcmFmeaCrityListService;

/**
 * 목록
 * @author  kim21017
 * @version $Id: RcmFmeaCrityListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/rcmFmeaCrityList" name="rcmFmeaCrityListForm"
 *                input="/dream/rcm/fmea/rcmFmeaCrityList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="rcmFmeaCrityList" path="/dream/rcm/fmea/rcmFmeaCrityList.jsp"
 *                        redirect="false"
 * @struts:action path="/rcmPmtaskCrityList" name="rcmFmeaCrityListForm"
 *                input="/dream/rcm/pmtask/rcmPmtaskCrityList.jsp" scope="request"
 *                validate="false"
 */
public class RcmFmeaCrityListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int FMEA_CRITY_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int FMEA_CRITY_LIST_DELETE 		= 7002;
    
    public static final int FMEA_CRITY_SAVE 		= 5003;
    public static final int FMEA_CRITY_DELETE 		= 7004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        RcmFmeaCrityListForm rcmFmeaCrityListForm = (RcmFmeaCrityListForm) form;
        
        switch (rcmFmeaCrityListForm.getStrutsAction())
        {
            case RcmFmeaListAction.BASE_SET_HEADER:
                super.setHeader(request, response, rcmFmeaCrityListForm.getListId(), rcmFmeaCrityListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case RcmFmeaCrityListAction.FMEA_CRITY_LIST_FIND:
                findList(request, response, rcmFmeaCrityListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case RcmFmeaCrityListAction.FMEA_CRITY_LIST_DELETE:
            	deleteList(request,rcmFmeaCrityListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case RcmFmeaCrityListAction.FMEA_CRITY_SAVE:
            	saveList(request,rcmFmeaCrityListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case RcmFmeaCrityListAction.FMEA_CRITY_DELETE:
            	deleteCrity(request,rcmFmeaCrityListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case RcmFmeaCrityListAction.BASE_GRID_EXPORT:
            	findList(request,response, rcmFmeaCrityListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    private void deleteCrity(HttpServletRequest request, RcmFmeaCrityListForm rcmFmeaCrityListForm) {
    	RcmFmeaCrityListService rcmFmeaCrityListService = (RcmFmeaCrityListService) getBean("rcmFmeaCrityListService");
    	RcmFmeaCommonDTO rcmFmeaCommonDTO = rcmFmeaCrityListForm.getRcmFmeaCommonDTO();
        rcmFmeaCommonDTO.setCompNo(getUser(request).getCompNo());
        
    	rcmFmeaCrityListService.deleteCrity(rcmFmeaCrityListForm.getRcmFmeaCrityListDTO(),rcmFmeaCommonDTO);
    	
    	setAjaxStatus(request);
	}

	private void saveList(HttpServletRequest request, RcmFmeaCrityListForm rcmFmeaCrityListForm) {
    	RcmFmeaCrityListService rcmFmeaCrityListService = (RcmFmeaCrityListService) getBean("rcmFmeaCrityListService");
    	RcmFmeaCommonDTO rcmFmeaCommonDTO = rcmFmeaCrityListForm.getRcmFmeaCommonDTO();
        rcmFmeaCommonDTO.setCompNo(getUser(request).getCompNo());
        
    	rcmFmeaCrityListService.insertCrity(rcmFmeaCrityListForm.getRcmFmeaCrityListDTO(),rcmFmeaCommonDTO);
    	
    	setAjaxStatus(request);
	}

	/**
     * grid find
     * @author  kim2107
     * @version $Id: RcmFmeaCrityListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param rcmFmeaCrityListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, RcmFmeaCrityListForm rcmFmeaCrityListForm, boolean excelExport) throws Exception
    {
        RcmFmeaCrityListService rcmFmeaCrityListService = (RcmFmeaCrityListService) getBean("rcmFmeaCrityListService");
        RcmFmeaCommonDTO rcmFmeaCommonDTO = rcmFmeaCrityListForm.getRcmFmeaCommonDTO();
        rcmFmeaCommonDTO.setCompNo(getUser(request).getCompNo());
        RcmFmeaCrityListDTO rcmFmeaCrityListDTO = rcmFmeaCrityListForm.getRcmFmeaCrityListDTO();
        
    	//Paging
        rcmFmeaCommonDTO.setIsLoadMaxCount("Y".equals(rcmFmeaCrityListForm.getIsLoadMaxCount())?true:false);
        rcmFmeaCommonDTO.setFirstRow(rcmFmeaCrityListForm.getFirstRow());
        rcmFmeaCommonDTO.setOrderBy(rcmFmeaCrityListForm.getOrderBy());
        rcmFmeaCommonDTO.setDirection(rcmFmeaCrityListForm.getDirection());
        
        List resultList = rcmFmeaCrityListService.findList(rcmFmeaCommonDTO, rcmFmeaCrityListDTO);
    	String totalCount = "";
        if(Integer.parseInt(rcmFmeaCrityListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = rcmFmeaCrityListService.findTotalCount(rcmFmeaCommonDTO,rcmFmeaCrityListDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,rcmFmeaCrityListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
  
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: RcmFmeaCrityListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param rcmFmeaCrityListForm
     * @throws Exception
     */
    private void deleteList(HttpServletRequest request, RcmFmeaCrityListForm rcmFmeaCrityListForm) throws Exception
    {
    	RcmFmeaCrityListService rcmFmeaCrityListService = (RcmFmeaCrityListService) getBean("rcmFmeaCrityListService");
        
    	rcmFmeaCrityListService.deleteList(rcmFmeaCrityListForm.getDeleteRows());
    	
    	setAjaxStatus(request);
    }
}