package dream.rcm.taskmap.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.rcm.taskmap.dto.RcmTaskMapItemListDTO;
import dream.rcm.taskmap.dto.RcmTaskMapCommonDTO;
import dream.rcm.taskmap.form.RcmTaskMapItemListForm;
import dream.rcm.taskmap.service.RcmTaskMapItemListService;

/**
 * 응답  목록
 * @author  kim21017
 * @version $Id: RcmTaskMapItemListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/rcmTaskMapItemList" name="rcmTaskMapItemListForm"
 *                input="/dream/rcm/taskmap/rcmTaskMapItemList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="rcmTaskMapItemList" path="/dream/rcm/taskmap/rcmTaskMapItemList.jsp"
 *                        redirect="false"
 */
public class RcmTaskMapItemListAction extends AuthAction
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
        RcmTaskMapItemListForm rcmTaskMapItemListForm = (RcmTaskMapItemListForm) form;
        
        switch (rcmTaskMapItemListForm.getStrutsAction())
        {
            case RcmTaskMapListAction.BASE_SET_HEADER:
                super.setHeader(request, response, rcmTaskMapItemListForm.getListId(), rcmTaskMapItemListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case RcmTaskMapItemListAction.QNA_ANS_LIST_FIND:
                findItemList(request, response, rcmTaskMapItemListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case RcmTaskMapItemListAction.QNA_ANS_LIST_DELETE:
            	deleteItemList(request,rcmTaskMapItemListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case RcmTaskMapItemListAction.BASE_GRID_EXPORT:
            	findItemList(request,response, rcmTaskMapItemListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("rcmTaskMapItemList");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: RcmTaskMapItemListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param rcmTaskMapItemListForm
     * @throws Exception
     */
    private void findItemList(HttpServletRequest request, HttpServletResponse response, RcmTaskMapItemListForm rcmTaskMapItemListForm, boolean excelExport) throws Exception
    {
        RcmTaskMapItemListService rcmTaskMapItemListService = (RcmTaskMapItemListService) getBean("rcmTaskMapItemListService");
        RcmTaskMapCommonDTO rcmTaskMapCommonDTO = rcmTaskMapItemListForm.getRcmTaskMapCommonDTO();
        rcmTaskMapCommonDTO.setCompNo(getUser(request).getCompNo());
        RcmTaskMapItemListDTO rcmTaskMapItemListDTO = rcmTaskMapItemListForm.getRcmTaskMapItemListDTO();

    	//Paging
        rcmTaskMapCommonDTO.setIsLoadMaxCount("Y".equals(rcmTaskMapItemListForm.getIsLoadMaxCount())?true:false);
        rcmTaskMapCommonDTO.setFirstRow(rcmTaskMapItemListForm.getFirstRow());
        rcmTaskMapCommonDTO.setOrderBy(rcmTaskMapItemListForm.getOrderBy());
        rcmTaskMapCommonDTO.setDirection(rcmTaskMapItemListForm.getDirection());
    	
        List resultList = rcmTaskMapItemListService.findItemList(rcmTaskMapCommonDTO, rcmTaskMapItemListDTO,getUser(request));
        
        String totalCount = "";
        if(Integer.parseInt(rcmTaskMapItemListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = rcmTaskMapItemListService.findTotalCount(rcmTaskMapCommonDTO, rcmTaskMapItemListDTO, getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,rcmTaskMapItemListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: RcmTaskMapItemListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param rcmTaskMapItemListForm
     * @throws Exception
     */
    private void deleteItemList(HttpServletRequest request, RcmTaskMapItemListForm rcmTaskMapItemListForm) throws Exception
    {
    	RcmTaskMapItemListService rcmTaskMapItemListService = (RcmTaskMapItemListService) getBean("rcmTaskMapItemListService");
        
    	rcmTaskMapItemListService.deleteItemList(rcmTaskMapItemListForm.getDeleteRows(), rcmTaskMapItemListForm.getDeleteRowsExt());
    	
    	setAjaxStatus(request);
    }
}