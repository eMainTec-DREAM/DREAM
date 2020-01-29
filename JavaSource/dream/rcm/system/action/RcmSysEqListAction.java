package dream.rcm.system.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.rcm.system.dto.RcmSysCommonDTO;
import dream.rcm.system.dto.RcmSysEqListDTO;
import dream.rcm.system.form.RcmSysEqListForm;
import dream.rcm.system.service.RcmSysEqListService;

/**
 * 설비설정 목록
 * @author  kim21017
 * @version $Id: RcmSysEqListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/rcmSysEqList" name="rcmSysEqListForm"
 *                input="/dream/rcm/system/rcmSysEqList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="rcmSysEqList" path="/dream/rcm/system/rcmSysEqList.jsp"
 *                        redirect="false"
 */
public class RcmSysEqListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int RCM_SYS_EQ_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int RCM_SYS_EQ_LIST_DELETE 		= 7002;
    /** 입력 */
    public static final int RCM_SYS_EQ_LIST_INPUT 		= 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        RcmSysEqListForm rcmSysEqListForm = (RcmSysEqListForm) form;
        
        switch (rcmSysEqListForm.getStrutsAction())
        {
        
            case RcmSysEqListAction.RCM_SYS_EQ_LIST_FIND:
                findList(request,response, rcmSysEqListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case RcmSysEqListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, rcmSysEqListForm.getListId(), rcmSysEqListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case RcmSysEqListAction.RCM_SYS_EQ_LIST_DELETE:
            	deleteList(request,rcmSysEqListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case RcmSysEqListAction.RCM_SYS_EQ_LIST_INPUT:
            	inputList(request,rcmSysEqListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case RcmSysEqListAction.BASE_GRID_EXPORT:
            	findList(request,response, rcmSysEqListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: RcmSysEqListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param rcmSysEqListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request,HttpServletResponse response, RcmSysEqListForm rcmSysEqListForm, boolean excelExport) throws Exception
    {
        RcmSysEqListService rcmSysEqListService = (RcmSysEqListService) getBean("rcmSysEqListService");
        RcmSysCommonDTO rcmSysCommonDTO = rcmSysEqListForm.getRcmSysCommonDTO();
        RcmSysEqListDTO rcmSysEqListDTO = rcmSysEqListForm.getRcmSysEqListDTO();
      //Paging
    	rcmSysCommonDTO.setIsLoadMaxCount("Y".equals(rcmSysEqListForm.getIsLoadMaxCount())?true:false);
    	rcmSysCommonDTO.setFirstRow(rcmSysEqListForm.getFirstRow());
    	rcmSysCommonDTO.setOrderBy(rcmSysEqListForm.getOrderBy());
    	rcmSysCommonDTO.setDirection(rcmSysEqListForm.getDirection());
        
    	List resultList = rcmSysEqListService.findList(rcmSysCommonDTO, rcmSysEqListDTO, getUser(request));
        
    	String totalCount = "";
        if(Integer.parseInt(rcmSysEqListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = rcmSysEqListService.findTotalCount(rcmSysCommonDTO,rcmSysEqListDTO, getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,rcmSysEqListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: RcmSysEqListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param rcmSysEqListForm
     * @throws Exception
     */
    private void deleteList(HttpServletRequest request, RcmSysEqListForm rcmSysEqListForm) throws Exception
    {
    	RcmSysEqListService rcmSysEqListService = (RcmSysEqListService) getBean("rcmSysEqListService");
        
    	String[] deleteRows = rcmSysEqListForm.getDeleteRows();
    
    	rcmSysEqListService.deleteList(deleteRows, getUser(request).getCompNo());
    	
    	setAjaxStatus(request);
    }
    
    /**
     * input
     * @author  kim2107
     * @version $Id: RcmSysEqListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param rcmSysEqListForm
     * @throws Exception
     */
    private void inputList(HttpServletRequest request, RcmSysEqListForm rcmSysEqListForm) throws Exception
    {
    	RcmSysEqListService rcmSysEqListService = (RcmSysEqListService) getBean("rcmSysEqListService");
    	
    	RcmSysCommonDTO rcmSysCommonDTO = rcmSysEqListForm.getRcmSysCommonDTO();
        RcmSysEqListDTO rcmSysEqListDTO = rcmSysEqListForm.getRcmSysEqListDTO();

        rcmSysCommonDTO.setCompNo(getUser(request).getCompNo());
        
        rcmSysEqListService.inputList(rcmSysCommonDTO, rcmSysEqListDTO);
        
    	setAjaxStatus(request);
    }
}