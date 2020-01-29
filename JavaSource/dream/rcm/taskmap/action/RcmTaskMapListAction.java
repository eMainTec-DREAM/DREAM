package dream.rcm.taskmap.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.rcm.taskmap.dto.RcmTaskMapCommonDTO;
import dream.rcm.taskmap.form.RcmTaskMapListForm;
import dream.rcm.taskmap.service.RcmTaskMapListService;

/**
 * 질의 - 목록 action
 * @author  kim21017
 * @version $Id: RcmTaskMapListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/rcmTaskMapList" name="rcmTaskMapListForm"
 *                input="/dream/rcm/taskmap/rcmTaskMapList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="rcmTaskMapList" path="/dream/rcm/taskmap/rcmTaskMapList.jsp"
 *                        redirect="false"
 */
public class RcmTaskMapListAction extends AuthAction
{
    /** 조회 */
    public static final int QNA_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int QNA_LIST_DELETE 	= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        RcmTaskMapListForm rcmTaskMapListForm = (RcmTaskMapListForm) form;
        
        switch (rcmTaskMapListForm.getStrutsAction())
        {
            case RcmTaskMapListAction.QNA_LIST_FIND:
                findQnaList(request, rcmTaskMapListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case RcmTaskMapListAction.BASE_SET_HEADER:
                setHeader(request, response, rcmTaskMapListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case RcmTaskMapListAction.QNA_LIST_DELETE:
                deleteQna(request, rcmTaskMapListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case RcmTaskMapListAction.BASE_GRID_EXPORT:
            	findQnaList(request, rcmTaskMapListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("rcmTaskMapList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, RcmTaskMapListForm rcmTaskMapListForm) throws IOException
    {
        super.setHeader(request, response, rcmTaskMapListForm.getListId(), rcmTaskMapListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: RcmTaskMapListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param rcmTaskMapListForm
     * @throws IOException 
     * @throws Exception
     */
    private void findQnaList(HttpServletRequest request, RcmTaskMapListForm rcmTaskMapListForm, HttpServletResponse response, boolean excelExport) throws IOException 
    {
    	RcmTaskMapListService rcmTaskMapListService = (RcmTaskMapListService) getBean("rcmTaskMapListService");        

    	RcmTaskMapCommonDTO rcmTaskMapCommonDTO = rcmTaskMapListForm.getRcmTaskMapCommonDTO();
    	rcmTaskMapCommonDTO.setCompNo(getUser(request).getCompNo());
        
    	//Paging
    	rcmTaskMapCommonDTO.setIsLoadMaxCount("Y".equals(rcmTaskMapListForm.getIsLoadMaxCount())?true:false);
    	rcmTaskMapCommonDTO.setFirstRow(rcmTaskMapListForm.getFirstRow());
    	rcmTaskMapCommonDTO.setOrderBy(rcmTaskMapListForm.getOrderBy());
    	rcmTaskMapCommonDTO.setDirection(rcmTaskMapListForm.getDirection());
    	
    	//리스트를 조회한다.
        List resultList = rcmTaskMapListService.findQnaList(rcmTaskMapCommonDTO);
 
        String totalCount = "";
        if(Integer.parseInt(rcmTaskMapListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = rcmTaskMapListService.findTotalCount(rcmTaskMapCommonDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,rcmTaskMapListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
  
    }
    /**
     * delete
     * @author  kim21017
     * @version $Id: RcmTaskMapListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmTaskMapListForm
     * @param request
     */
    private void deleteQna(HttpServletRequest request, RcmTaskMapListForm rcmTaskMapListForm) throws Exception
    {
    	RcmTaskMapListService rcmTaskMapListService = (RcmTaskMapListService) getBean("rcmTaskMapListService");        

    	String[] deleteRows = rcmTaskMapListForm.getDeleteRows();    // sheet 내역
        
    	rcmTaskMapListService.deleteQna(deleteRows,getUser(request));
        
        setAjaxStatus(request);
    }
}
