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
import dream.mgr.ptwh.dto.MgrPtWhBinListDTO;
import dream.mgr.ptwh.dto.MgrPtWhCommonDTO;
import dream.mgr.ptwh.form.MgrPtWhBinListForm;
import dream.mgr.ptwh.service.MgrPtWhBinListService;

/**
 * 부품창고 보관위치 - List Action
 * 
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/mgrPtWhBinList" name="mgrPtWhBinListForm"
 *                input="/dream/mgr/ptwh/mgrPtWhBinList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrPtWhBinList" path="/dream/mgr/ptwh/mgrPtWhBinList.jsp"
 *                        redirect="false"
 */

public class MgrPtWhBinListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int LIST_DELETE 	= 7002;
    /** 바코드 출력 */
    public static final int LIST_QR_INSERT 	= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MgrPtWhBinListForm mgrPtWhBinListForm = (MgrPtWhBinListForm) form;
        
        switch (mgrPtWhBinListForm.getStrutsAction())
        {
            case MgrPtWhBinListAction.BASE_SET_HEADER:
                setHeader(request, response, mgrPtWhBinListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrPtWhBinListAction.LIST_FIND:
                findList(request, response, mgrPtWhBinListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case MgrPtWhBinListAction.LIST_DELETE:
            	deleteList(request, mgrPtWhBinListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case MgrPtWhBinListAction.LIST_QR_INSERT:
                insertQrList(request, mgrPtWhBinListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case MgrPtWhBinListAction.BASE_GRID_EXPORT:
            	findList(request, response, mgrPtWhBinListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("mgrPtWhBinList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MgrPtWhBinListForm mgrPtWhBinListForm) throws IOException
    {
        super.setHeader(request, response, mgrPtWhBinListForm.getListId(), mgrPtWhBinListForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param mgrPtWhBinListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, MgrPtWhBinListForm mgrPtWhBinListForm, boolean excelExport) throws Exception
    {
    	MgrPtWhBinListService mgrPtWhBinListService = (MgrPtWhBinListService) getBean("mgrPtWhBinListService");
    	MgrPtWhBinListDTO mgrPtWhBinListDTO = mgrPtWhBinListForm.getMgrPtWhBinListDTO();
    	MgrPtWhCommonDTO mgrPtWhCommonDTO = mgrPtWhBinListForm.getMgrPtWhCommonDTO();

    	//Paging
    	mgrPtWhBinListDTO.setIsLoadMaxCount("Y".equals(mgrPtWhBinListForm.getIsLoadMaxCount())?true:false);
    	mgrPtWhBinListDTO.setFirstRow(mgrPtWhBinListForm.getFirstRow());
    	mgrPtWhBinListDTO.setOrderBy(mgrPtWhBinListForm.getOrderBy());
    	mgrPtWhBinListDTO.setDirection(mgrPtWhBinListForm.getDirection());
    	
    	User user = getUser(request);
    	mgrPtWhBinListDTO.setWcodeId(mgrPtWhCommonDTO.getWcodeId());
    	
        List resultList = mgrPtWhBinListService.findPtWhEmpList(mgrPtWhBinListDTO, user);
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(mgrPtWhBinListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = mgrPtWhBinListService.findTotalCount(mgrPtWhBinListDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,mgrPtWhBinListForm.getListId(),mgrPtWhBinListForm.getCurrentPageId(), mgrPtWhBinListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param mgrPtWhBinListForm
     */
    private void deleteList(HttpServletRequest request, MgrPtWhBinListForm mgrPtWhBinListForm) throws Exception
    {
    	MgrPtWhBinListService mgrPtWhBinListService = (MgrPtWhBinListService) getBean("mgrPtWhBinListService");
        String[] deleteRows = mgrPtWhBinListForm.getDeleteRows();
        
    	User user = getUser(request);
        
        mgrPtWhBinListService.deletePtWhEmpList(deleteRows, user);
        setAjaxStatus(request);
    }
    
    private void insertQrList(HttpServletRequest request, MgrPtWhBinListForm mgrPtWhBinListForm) throws Exception
    {
        MgrPtWhBinListService mgrPtWhBinListService = (MgrPtWhBinListService) getBean("mgrPtWhBinListService");
        String[] selectRows = mgrPtWhBinListForm.getSelectRows();

        mgrPtWhBinListService.insertQrList(selectRows, mgrPtWhBinListForm.getFileName(), getUser(request));
        setAjaxStatus(request);
    }
    
}
