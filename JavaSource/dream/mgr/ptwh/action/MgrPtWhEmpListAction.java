package dream.mgr.ptwh.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.mgr.ptwh.dto.MgrPtWhCommonDTO;
import dream.mgr.ptwh.dto.MgrPtWhEmpListDTO;
import dream.mgr.ptwh.form.MgrPtWhEmpListForm;
import dream.mgr.ptwh.service.MgrPtWhEmpListService;

/**
 * 부품창고 담당자 - List Action
 * 
 * @author sy.yang
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/mgrPtWhEmpList" name="mgrPtWhEmpListForm"
 *                input="/dream/mgr/ptwh/mgrPtWhEmpList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrPtWhEmpList" path="/dream/mgr/ptwh/mgrPtWhEmpList.jsp"
 *                        redirect="false"
 */

public class MgrPtWhEmpListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int LIST_DELETE 	= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MgrPtWhEmpListForm mgrPtWhEmpListForm = (MgrPtWhEmpListForm) form;
        
        switch (mgrPtWhEmpListForm.getStrutsAction())
        {
            case MgrPtWhEmpListAction.BASE_SET_HEADER:
                setHeader(request, response, mgrPtWhEmpListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrPtWhEmpListAction.LIST_FIND:
                findList(request, response, mgrPtWhEmpListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case MgrPtWhEmpListAction.LIST_DELETE:
            	deleteList(request, mgrPtWhEmpListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case MgrPtWhEmpListAction.BASE_GRID_EXPORT:
            	findList(request, response, mgrPtWhEmpListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("mgrPtWhEmpList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MgrPtWhEmpListForm mgrPtWhEmpListForm) throws IOException
    {
        super.setHeader(request, response, mgrPtWhEmpListForm.getListId(), mgrPtWhEmpListForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param mgrPtWhEmpListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, MgrPtWhEmpListForm mgrPtWhEmpListForm, boolean excelExport) throws Exception
    {
    	MgrPtWhEmpListService mgrPtWhEmpListService = (MgrPtWhEmpListService) getBean("mgrPtWhEmpListService");
    	MgrPtWhEmpListDTO mgrPtWhEmpListDTO = mgrPtWhEmpListForm.getMgrPtWhEmpListDTO();
    	MgrPtWhCommonDTO mgrPtWhCommonDTO = mgrPtWhEmpListForm.getMgrPtWhCommonDTO();

    	//Paging
    	mgrPtWhEmpListDTO.setIsLoadMaxCount("Y".equals(mgrPtWhEmpListForm.getIsLoadMaxCount())?true:false);
    	mgrPtWhEmpListDTO.setFirstRow(mgrPtWhEmpListForm.getFirstRow());
    	mgrPtWhEmpListDTO.setOrderBy(mgrPtWhEmpListForm.getOrderBy());
    	mgrPtWhEmpListDTO.setDirection(mgrPtWhEmpListForm.getDirection());
    	
    	User user = getUser(request);
    	mgrPtWhEmpListDTO.setWcodeId(mgrPtWhCommonDTO.getWcodeId());
    	
        List resultList = mgrPtWhEmpListService.findPtWhEmpList(mgrPtWhEmpListDTO, user);
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(mgrPtWhEmpListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = mgrPtWhEmpListService.findTotalCount(mgrPtWhEmpListDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,mgrPtWhEmpListForm.getListId(),mgrPtWhEmpListForm.getCurrentPageId(), mgrPtWhEmpListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param mgrPtWhEmpListForm
     */
    private void deleteList(HttpServletRequest request, MgrPtWhEmpListForm mgrPtWhEmpListForm) throws Exception
    {
    	MgrPtWhEmpListService mgrPtWhEmpListService = (MgrPtWhEmpListService) getBean("mgrPtWhEmpListService");
        String[] deleteRows = mgrPtWhEmpListForm.getDeleteRows();
        
    	User user = getUser(request);
        
        mgrPtWhEmpListService.deletePtWhEmpList(deleteRows, user);
        setAjaxStatus(request);
    }
    
}
