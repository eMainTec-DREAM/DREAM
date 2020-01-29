package dream.rcm.funceq.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.rcm.funceq.dto.RcmFuncEqCommonDTO;
import dream.rcm.funceq.form.RcmFuncEqListForm;
import dream.rcm.funceq.service.RcmFuncEqListService;

/**
 * 질의 - 목록 action
 * @author  kim21017
 * @version $Id: RcmFuncEqListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/rcmFuncEqList" name="rcmFuncEqListForm"
 *                input="/dream/rcm/funceq/rcmFuncEqList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="rcmFuncEqList" path="/dream/rcm/funceq/rcmFuncEqList.jsp"
 *                        redirect="false"
 */
public class RcmFuncEqListAction extends AuthAction
{
    /** 조회 */
    public static final int QNA_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int QNA_LIST_DELETE 	= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        RcmFuncEqListForm rcmFuncEqListForm = (RcmFuncEqListForm) form;
        
        switch (rcmFuncEqListForm.getStrutsAction())
        {
            case RcmFuncEqListAction.QNA_LIST_FIND:
                findQnaList(request, rcmFuncEqListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case RcmFuncEqListAction.BASE_SET_HEADER:
                setHeader(request, response, rcmFuncEqListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case RcmFuncEqListAction.QNA_LIST_DELETE:
                deleteQna(request, rcmFuncEqListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case RcmFuncEqListAction.BASE_GRID_EXPORT:
            	findQnaList(request, rcmFuncEqListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("rcmFuncEqList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, RcmFuncEqListForm rcmFuncEqListForm) throws IOException
    {
        super.setHeader(request, response, rcmFuncEqListForm.getListId(), rcmFuncEqListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: RcmFuncEqListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param rcmFuncEqListForm
     * @throws IOException 
     * @throws Exception
     */
    private void findQnaList(HttpServletRequest request, RcmFuncEqListForm rcmFuncEqListForm, HttpServletResponse response, boolean excelExport) throws IOException 
    {
    	RcmFuncEqListService rcmFuncEqListService = (RcmFuncEqListService) getBean("rcmFuncEqListService");        

    	RcmFuncEqCommonDTO rcmFuncEqCommonDTO = rcmFuncEqListForm.getRcmFuncEqCommonDTO();
    	rcmFuncEqCommonDTO.setCompNo(getUser(request).getCompNo());
    	
    	rcmFuncEqCommonDTO.setIsLoadMaxCount("Y".equals(rcmFuncEqListForm.getIsLoadMaxCount())?true:false);
    	rcmFuncEqCommonDTO.setFirstRow(rcmFuncEqListForm.getFirstRow());
    	rcmFuncEqCommonDTO.setOrderBy(rcmFuncEqListForm.getOrderBy());
    	rcmFuncEqCommonDTO.setDirection(rcmFuncEqListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = rcmFuncEqListService.findQnaList(rcmFuncEqCommonDTO);
        
        String totalCount = "";
        if(Integer.parseInt(rcmFuncEqListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = rcmFuncEqListService.findTotalCount(rcmFuncEqCommonDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,rcmFuncEqListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
     }
    /**
     * delete
     * @author  kim21017
     * @version $Id: RcmFuncEqListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFuncEqListForm
     * @param request
     */
    private void deleteQna(HttpServletRequest request, RcmFuncEqListForm rcmFuncEqListForm) throws Exception
    {
    	RcmFuncEqListService rcmFuncEqListService = (RcmFuncEqListService) getBean("rcmFuncEqListService");        

    	String[] deleteRows = rcmFuncEqListForm.getDeleteRows();    // sheet 내역
        
    	rcmFuncEqListService.deleteQna(deleteRows,getUser(request));
        
        setAjaxStatus(request);
    }
}
