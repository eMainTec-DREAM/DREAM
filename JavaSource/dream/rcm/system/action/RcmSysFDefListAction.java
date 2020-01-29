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
import dream.rcm.system.dto.RcmSysFDefListDTO;
import dream.rcm.system.form.RcmSysFDefListForm;
import dream.rcm.system.service.RcmSysFDefListService;

/**
 * 기능정의 목록
 * @author  kim21017
 * @version $Id: RcmSysFDefListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/rcmSysFDefList" name="rcmSysFDefListForm"
 *                input="/dream/rcm/system/rcmSysFDefList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="rcmSysFDefList" path="/dream/rcm/system/rcmSysFDefList.jsp"
 *                        redirect="false"
 */
public class RcmSysFDefListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int RCM_SYS_FDEF_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int RCM_SYS_FDEF_LIST_DELETE 	= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        RcmSysFDefListForm rcmSysFDefListForm = (RcmSysFDefListForm) form;
        
        switch (rcmSysFDefListForm.getStrutsAction())
        {
        
            case RcmSysFDefListAction.RCM_SYS_FDEF_LIST_FIND:
                findList(request,response, rcmSysFDefListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case RcmSysFDefListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, rcmSysFDefListForm.getListId(), rcmSysFDefListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case RcmSysFDefListAction.RCM_SYS_FDEF_LIST_DELETE:
            	deleteList(request,rcmSysFDefListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case RcmSysFDefListAction.BASE_GRID_EXPORT:
            	findList(request,response, rcmSysFDefListForm, true);
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
     * @version $Id: RcmSysFDefListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param rcmSysFDefListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request,HttpServletResponse response, RcmSysFDefListForm rcmSysFDefListForm, boolean excelExport) throws Exception
    {
        RcmSysFDefListService rcmSysFDefListService = (RcmSysFDefListService) getBean("rcmSysFDefListService");
        RcmSysCommonDTO rcmSysCommonDTO = rcmSysFDefListForm.getRcmSysCommonDTO();
        RcmSysFDefListDTO rcmSysFDefListDTO = rcmSysFDefListForm.getRcmSysFDefListDTO();
    	//Paging
    	rcmSysCommonDTO.setIsLoadMaxCount("Y".equals(rcmSysFDefListForm.getIsLoadMaxCount())?true:false);
    	rcmSysCommonDTO.setFirstRow(rcmSysFDefListForm.getFirstRow());
    	rcmSysCommonDTO.setOrderBy(rcmSysFDefListForm.getOrderBy());
    	rcmSysCommonDTO.setDirection(rcmSysFDefListForm.getDirection());
    	
        List resultList = rcmSysFDefListService.findList(rcmSysCommonDTO, rcmSysFDefListDTO, getUser(request));
        String totalCount = "";
        if(Integer.parseInt(rcmSysFDefListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = rcmSysFDefListService.findTotalCount(rcmSysCommonDTO,rcmSysFDefListDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,rcmSysFDefListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: RcmSysFDefListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param rcmSysFDefListForm
     * @throws Exception
     */
    private void deleteList(HttpServletRequest request, RcmSysFDefListForm rcmSysFDefListForm) throws Exception
    {
    	RcmSysFDefListService rcmSysFDefListService = (RcmSysFDefListService) getBean("rcmSysFDefListService");
        
    	String[] deleteRows = rcmSysFDefListForm.getDeleteRows();
    
    	rcmSysFDefListService.deleteList(deleteRows, getUser(request).getCompNo());
    	
    	setAjaxStatus(request);
    }
}