package dream.rcm.funceq.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.rcm.funceq.dto.RcmFuncEqCommonDTO;
import dream.rcm.funceq.dto.RcmFuncEqItemListDTO;
import dream.rcm.funceq.form.RcmFuncEqItemListForm;
import dream.rcm.funceq.service.RcmFuncEqItemListService;

/**
 * 응답  목록
 * @author  kim21017
 * @version $Id: RcmFuncEqItemListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/rcmFuncEqItemList" name="rcmFuncEqItemListForm"
 *                input="/dream/rcm/funceq/rcmFuncEqItemList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="rcmFuncEqItemList" path="/dream/rcm/funceq/rcmFuncEqItemList.jsp"
 *                        redirect="false"
 */
public class RcmFuncEqItemListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int QNA_ANS_LIST_FIND 			= 1001;
    /** 삭제 */
    public static final int QNA_ANS_LIST_DELETE 		= 7002;
    /** 입력 */
    public static final int RCM_FUNC_LIST_INPUT 		= 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        RcmFuncEqItemListForm rcmFuncEqItemListForm = (RcmFuncEqItemListForm) form;
        
        switch (rcmFuncEqItemListForm.getStrutsAction())
        {
            case RcmFuncEqListAction.BASE_SET_HEADER:
                super.setHeader(request, response, rcmFuncEqItemListForm.getListId(), rcmFuncEqItemListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case RcmFuncEqItemListAction.QNA_ANS_LIST_FIND:
                findItemList(request, response, rcmFuncEqItemListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case RcmFuncEqItemListAction.QNA_ANS_LIST_DELETE:
            	deleteItemList(request,rcmFuncEqItemListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case RcmFuncEqItemListAction.BASE_GRID_EXPORT:
            	findItemList(request,response, rcmFuncEqItemListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            case RcmFuncEqItemListAction.RCM_FUNC_LIST_INPUT:
            	inputList(request,rcmFuncEqItemListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("rcmFuncEqItemList");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: RcmFuncEqItemListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param rcmFuncEqItemListForm
     * @throws Exception
     */
    private void findItemList(HttpServletRequest request, HttpServletResponse response, RcmFuncEqItemListForm rcmFuncEqItemListForm, boolean excelExport) throws Exception
    {
        RcmFuncEqItemListService rcmFuncEqItemListService = (RcmFuncEqItemListService) getBean("rcmFuncEqItemListService");
        RcmFuncEqCommonDTO rcmFuncEqCommonDTO = rcmFuncEqItemListForm.getRcmFuncEqCommonDTO();
        RcmFuncEqItemListDTO rcmFuncEqItemListDTO = rcmFuncEqItemListForm.getRcmFuncEqItemListDTO();

        rcmFuncEqCommonDTO.setCompNo(getUser(request).getCompNo());
        
        rcmFuncEqCommonDTO.setIsLoadMaxCount("Y".equals(rcmFuncEqItemListForm.getIsLoadMaxCount())?true:false);
        rcmFuncEqCommonDTO.setFirstRow(rcmFuncEqItemListForm.getFirstRow());
        rcmFuncEqCommonDTO.setOrderBy(rcmFuncEqItemListForm.getOrderBy());
        rcmFuncEqCommonDTO.setDirection(rcmFuncEqItemListForm.getDirection());
    	
        List resultList = rcmFuncEqItemListService.findItemList(rcmFuncEqCommonDTO, rcmFuncEqItemListDTO);
        
        String totalCount = "";
        if(Integer.parseInt(rcmFuncEqItemListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = rcmFuncEqItemListService.findTotalCount(rcmFuncEqCommonDTO,rcmFuncEqItemListDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,rcmFuncEqItemListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: RcmFuncEqItemListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param rcmFuncEqItemListForm
     * @throws Exception
     */
    private void deleteItemList(HttpServletRequest request, RcmFuncEqItemListForm rcmFuncEqItemListForm) throws Exception
    {
    	RcmFuncEqItemListService rcmFuncEqItemListService = (RcmFuncEqItemListService) getBean("rcmFuncEqItemListService");
        
    	rcmFuncEqItemListService.deleteItemList(rcmFuncEqItemListForm.getDeleteRows(), rcmFuncEqItemListForm.getDeleteRowsExt());
    	
    	setAjaxStatus(request);
    }
    /**
     * input
     * @author  kim2107
     * @version $Id: RcmSysFEnvListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param rcmSysFEnvListForm
     * @throws Exception
     */
    private void inputList(HttpServletRequest request, RcmFuncEqItemListForm rcmFuncEqItemListForm) throws Exception
    {
    	RcmFuncEqItemListService rcmFuncEqItemListService = (RcmFuncEqItemListService) getBean("rcmFuncEqItemListService");
    	
    	RcmFuncEqCommonDTO rcmFuncEqCommonDTO = rcmFuncEqItemListForm.getRcmFuncEqCommonDTO();
        
    	RcmFuncEqItemListDTO rcmFuncEqItemListDTO  = rcmFuncEqItemListForm.getRcmFuncEqItemListDTO();

    	rcmFuncEqCommonDTO.setCompNo(getUser(request).getCompNo());
        
    	rcmFuncEqItemListService.inputList(rcmFuncEqCommonDTO, rcmFuncEqItemListDTO);
        
    	setAjaxStatus(request);
    }
}