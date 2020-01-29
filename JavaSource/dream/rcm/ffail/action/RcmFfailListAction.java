package dream.rcm.ffail.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.rcm.ffail.dto.RcmFfailCommonDTO;
import dream.rcm.ffail.form.RcmFfailListForm;
import dream.rcm.ffail.service.RcmFfailListService;

/**
 * 질의 - 목록 action
 * @author  kim21017
 * @version $Id: RcmFfailListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/rcmFfailList" name="rcmFfailListForm"
 *                input="/dream/rcm/ffail/rcmFfailList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="rcmFfailList" path="/dream/rcm/ffail/rcmFfailList.jsp"
 *                        redirect="false"
 */
public class RcmFfailListAction extends AuthAction
{
    /** 조회 */
    public static final int QNA_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int QNA_LIST_DELETE 	= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        RcmFfailListForm rcmFfailListForm = (RcmFfailListForm) form;
        
        switch (rcmFfailListForm.getStrutsAction())
        {
            case RcmFfailListAction.QNA_LIST_FIND:
                findQnaList(request, rcmFfailListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case RcmFfailListAction.BASE_SET_HEADER:
                setHeader(request, response, rcmFfailListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case RcmFfailListAction.QNA_LIST_DELETE:
                deleteQna(request, rcmFfailListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case RcmFfailListAction.BASE_GRID_EXPORT:
            	findQnaList(request, rcmFfailListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("rcmFfailList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, RcmFfailListForm rcmFfailListForm) throws IOException
    {
        super.setHeader(request, response, rcmFfailListForm.getListId(), rcmFfailListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: RcmFfailListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param rcmFfailListForm
     * @throws IOException 
     * @throws Exception
     */
    private void findQnaList(HttpServletRequest request, RcmFfailListForm rcmFfailListForm, HttpServletResponse response, boolean excelExport) throws IOException 
    {
    	RcmFfailListService rcmFfailListService = (RcmFfailListService) getBean("rcmFfailListService");        

    	RcmFfailCommonDTO rcmFfailCommonDTO = rcmFfailListForm.getRcmFfailCommonDTO();
    	rcmFfailCommonDTO.setCompNo(getUser(request).getCompNo());
    	//Paging
    	rcmFfailCommonDTO.setIsLoadMaxCount("Y".equals(rcmFfailListForm.getIsLoadMaxCount())?true:false);
    	rcmFfailCommonDTO.setFirstRow(rcmFfailListForm.getFirstRow());
    	rcmFfailCommonDTO.setOrderBy(rcmFfailListForm.getOrderBy());
    	rcmFfailCommonDTO.setDirection(rcmFfailListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = rcmFfailListService.findQnaList(rcmFfailCommonDTO);
        
        String totalCount = "";
        if(Integer.parseInt(rcmFfailListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = rcmFfailListService.findTotalCount(rcmFfailCommonDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, rcmFfailListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
     }
    /**
     * delete
     * @author  kim21017
     * @version $Id: RcmFfailListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFfailListForm
     * @param request
     */
    private void deleteQna(HttpServletRequest request, RcmFfailListForm rcmFfailListForm) throws Exception
    {
    	RcmFfailListService rcmFfailListService = (RcmFfailListService) getBean("rcmFfailListService");        

    	String[] deleteRows = rcmFfailListForm.getDeleteRows();    // sheet 내역
        
    	rcmFfailListService.deleteQna(deleteRows,getUser(request));
        
        setAjaxStatus(request);
    }
}
