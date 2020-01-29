package dream.rcm.ffail.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.rcm.ffail.dto.RcmFfailItemListDTO;
import dream.rcm.ffail.dto.RcmFfailCommonDTO;
import dream.rcm.ffail.form.RcmFfailItemListForm;
import dream.rcm.ffail.service.RcmFfailItemListService;

/**
 * 응답  목록
 * @author  kim21017
 * @version $Id: RcmFfailItemListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/rcmFfailItemList" name="rcmFfailItemListForm"
 *                input="/dream/rcm/ffail/rcmFfailItemList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="rcmFfailItemList" path="/dream/rcm/ffail/rcmFfailItemList.jsp"
 *                        redirect="false"
 */
public class RcmFfailItemListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int QNA_ANS_LIST_FIND 			= 1001;
    /** 삭제 */
    public static final int QNA_ANS_LIST_DELETE 		= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        RcmFfailItemListForm rcmFfailItemListForm = (RcmFfailItemListForm) form;
        
        switch (rcmFfailItemListForm.getStrutsAction())
        {
            case RcmFfailListAction.BASE_SET_HEADER:
                super.setHeader(request, response, rcmFfailItemListForm.getListId(), rcmFfailItemListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case RcmFfailItemListAction.QNA_ANS_LIST_FIND:
                findItemList(request, response, rcmFfailItemListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case RcmFfailItemListAction.QNA_ANS_LIST_DELETE:
            	deleteItemList(request,rcmFfailItemListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case RcmFfailItemListAction.BASE_GRID_EXPORT:
            	findItemList(request,response, rcmFfailItemListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("rcmFfailItemList");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: RcmFfailItemListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param rcmFfailItemListForm
     * @throws Exception
     */
    private void findItemList(HttpServletRequest request, HttpServletResponse response, RcmFfailItemListForm rcmFfailItemListForm, boolean excelExport) throws Exception
    {
        RcmFfailItemListService rcmFfailItemListService = (RcmFfailItemListService) getBean("rcmFfailItemListService");
        RcmFfailCommonDTO rcmFfailCommonDTO = rcmFfailItemListForm.getRcmFfailCommonDTO();
        RcmFfailItemListDTO rcmFfailItemListDTO = rcmFfailItemListForm.getRcmFfailItemListDTO();

        rcmFfailCommonDTO.setCompNo(getUser(request).getCompNo());
       
        rcmFfailCommonDTO.setIsLoadMaxCount("Y".equals(rcmFfailItemListForm.getIsLoadMaxCount())?true:false);
        rcmFfailCommonDTO.setFirstRow(rcmFfailItemListForm.getFirstRow());
        rcmFfailCommonDTO.setOrderBy(rcmFfailItemListForm.getOrderBy());
        rcmFfailCommonDTO.setDirection(rcmFfailItemListForm.getDirection());
    	
        List resultList = rcmFfailItemListService.findItemList(rcmFfailCommonDTO, rcmFfailItemListDTO);
        
        String totalCount = "";
        if(Integer.parseInt(rcmFfailItemListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = rcmFfailItemListService.findTotalCount(rcmFfailCommonDTO,rcmFfailItemListDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,rcmFfailItemListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: RcmFfailItemListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param rcmFfailItemListForm
     * @throws Exception
     */
    private void deleteItemList(HttpServletRequest request, RcmFfailItemListForm rcmFfailItemListForm) throws Exception
    {
    	RcmFfailItemListService rcmFfailItemListService = (RcmFfailItemListService) getBean("rcmFfailItemListService");
        
    	rcmFfailItemListService.deleteItemList(rcmFfailItemListForm.getDeleteRows(), rcmFfailItemListForm.getDeleteRowsExt());
    	
    	setAjaxStatus(request);
    }
}