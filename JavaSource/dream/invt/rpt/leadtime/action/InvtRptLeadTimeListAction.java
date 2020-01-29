package dream.invt.rpt.leadtime.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.invt.rpt.leadtime.dto.InvtRptLeadTimeCommonDTO;
import dream.invt.rpt.leadtime.form.InvtRptLeadTimeListForm;
import dream.invt.rpt.leadtime.service.InvtRptLeadTimeListService;

/**
 * InvtRptLeadTime Page - List Action
 * 
 * @author cjscjs9
 * @version $Id: InvtRptLeadTimeListAction.java,v 1.0 2017/08/22 15:19:40 cjscjs9 Exp $
 * @since 1.0
 * @struts:action path="/invtRptLeadTimeList" name="invtRptLeadTimeListForm"
 *                input="/dream/invt/rpt/leadtime/invtRptLeadTimeList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="invtRptLeadTimeList" path="/dream/invt/rpt/leadtime/invtRptLeadTimeList.jsp"
 *                        redirect="false"
 */
public class InvtRptLeadTimeListAction extends AuthAction
{
    
  //일반 페이지 적용시 AuthAction 으로 변경해야합니다.
    /** 조회 */
    public static final int LIST_FIND       = 1001;
	public static final int CHART_FIND 		= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        InvtRptLeadTimeListForm invtRptLeadTimeListForm = (InvtRptLeadTimeListForm) form;
        
        switch (invtRptLeadTimeListForm.getStrutsAction())
        {
            case InvtRptLeadTimeListAction.BASE_SET_HEADER:
                setHeader(request, response, invtRptLeadTimeListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case InvtRptLeadTimeListAction.LIST_FIND:
                findList(request, response, invtRptLeadTimeListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case InvtRptLeadTimeListAction.CHART_FIND:
            	findChart(request,response, invtRptLeadTimeListForm, false);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case InvtRptLeadTimeListAction.BASE_GRID_EXPORT:
                findList(request, response, invtRptLeadTimeListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("invtRptLeadTimeList");
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, InvtRptLeadTimeListForm invtRptLeadTimeListForm) throws IOException
    {
        super.setHeader(request, response, invtRptLeadTimeListForm.getListId(), invtRptLeadTimeListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  cjscjs9
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param invtRptLeadTimeListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, InvtRptLeadTimeListForm invtRptLeadTimeListForm, boolean excelExport) throws Exception
    {
        InvtRptLeadTimeListService invtRptLeadTimeListService = (InvtRptLeadTimeListService) getBean("invtRptLeadTimeListService");
        InvtRptLeadTimeCommonDTO invtRptLeadTimeCommonDTO = invtRptLeadTimeListForm.getInvtRptLeadTimeCommonDTO();
      
        //Paging
        invtRptLeadTimeCommonDTO.setIsLoadMaxCount("Y".equals(invtRptLeadTimeListForm.getIsLoadMaxCount())?true:false);
        invtRptLeadTimeCommonDTO.setFirstRow(invtRptLeadTimeListForm.getFirstRow());
        invtRptLeadTimeCommonDTO.setOrderBy(invtRptLeadTimeListForm.getOrderBy());
        invtRptLeadTimeCommonDTO.setDirection(invtRptLeadTimeListForm.getDirection());
        
        User loginUser = getUser(request);
        List resultList = invtRptLeadTimeListService.findList(invtRptLeadTimeCommonDTO, loginUser);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(invtRptLeadTimeListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = invtRptLeadTimeListService.findTotalCount(invtRptLeadTimeCommonDTO,loginUser);
        
        if(excelExport) super.makeGridExport(resultList, request, response,invtRptLeadTimeListForm.getListId(),invtRptLeadTimeListForm.getCurrentPageId(), invtRptLeadTimeListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    private void findChart(HttpServletRequest request,HttpServletResponse response, InvtRptLeadTimeListForm invtRptLeadTimeListForm, boolean excelExport) throws Exception
    {
    	InvtRptLeadTimeListService invtRptLeadTimeListService = (InvtRptLeadTimeListService) getBean("invtRptLeadTimeListService");
    	InvtRptLeadTimeCommonDTO invtRptLeadTimeCommonDTO = invtRptLeadTimeListForm.getInvtRptLeadTimeCommonDTO();
    	
    	List resultList = invtRptLeadTimeListService.findChart(invtRptLeadTimeCommonDTO, getUser(request));
    	
    	if(excelExport) super.makeGridExport(resultList, request, response,invtRptLeadTimeListForm.getListId(),invtRptLeadTimeListForm.getCurrentPageId(), invtRptLeadTimeListForm.getFileName());
    	else super.makeJsonResult(resultList, request, response, invtRptLeadTimeListForm.getListId());
    }
    
}