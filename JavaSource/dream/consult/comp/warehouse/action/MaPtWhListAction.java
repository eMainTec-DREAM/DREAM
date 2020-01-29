package dream.consult.comp.warehouse.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.ConsultAuthAction;
import common.util.CommonUtil;
import dream.consult.comp.list.action.MaCompMngListAction;
import dream.consult.comp.warehouse.dto.MaPtWhCommonDTO;
import dream.consult.comp.warehouse.form.MaPtWhListForm;
import dream.consult.comp.warehouse.service.MaPtWhListService;

/**
 * 부품창고 - 목록 action
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/maPtWhList" name="maPtWhListForm"
 *                input="/dream/consult/comp/warehouse/maPtWhList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPtWhList" path="/dream/consult/comp/warehouse/maPtWhList.jsp"
 *                        redirect="false"
 */
public class MaPtWhListAction extends ConsultAuthAction
{
    /** 조회 */
    public static final int PTWH_LIST_FIND 	= 1001;
    /** 삭제 */
    public static final int PTWH_LIST_DELETE = 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaPtWhListForm maPtWhListForm = (MaPtWhListForm) form;
        
        switch (maPtWhListForm.getStrutsAction())
        {
            case MaPtWhListAction.PTWH_LIST_FIND:
            	findPtWhList(request, maPtWhListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtWhListAction.BASE_SET_HEADER:
                setHeader(request, response, maPtWhListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtWhListAction.PTWH_LIST_DELETE:
            	deletePtWh(request, maPtWhListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaCompMngListAction.BASE_GRID_EXPORT:
            	findPtWhList(request, maPtWhListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maPtWhList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaPtWhListForm maPtWhListForm) throws IOException
    {
        super.setHeader(request, response, maPtWhListForm.getListId(), maPtWhListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPtWhListForm
     * @throws Exception
     */
    private void findPtWhList(HttpServletRequest request, MaPtWhListForm maPtWhListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	MaPtWhListService maPtWhListService = (MaPtWhListService) getBean("maPtWhListService");        

    	MaPtWhCommonDTO maPtWhCommonDTO = maPtWhListForm.getMaPtWhCommonDTO();
    	
    	//Paging
        maPtWhCommonDTO.setIsLoadMaxCount("Y".equals(maPtWhListForm.getIsLoadMaxCount())?true:false);
        maPtWhCommonDTO.setFirstRow(maPtWhListForm.getFirstRow());
        maPtWhCommonDTO.setOrderBy(maPtWhListForm.getOrderBy());
        maPtWhCommonDTO.setDirection(maPtWhListForm.getDirection());
        
        User user = getUser(request);
        
        //리스트를 조회한다.
        List resultList = maPtWhListService.findPtWhList(maPtWhCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(maPtWhListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maPtWhListService.findTotalCount(maPtWhCommonDTO,user);

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, maPtWhListForm); 
        else CommonUtil.makeJsonResult(resultList, request, response, maPtWhListForm.getListId());
    }
    
    /**
     * delete
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtWhListForm
     * @param request
     */
    private void deletePtWh(HttpServletRequest request, MaPtWhListForm maPtWhListForm) throws Exception
    {
    	MaPtWhListService maPtWhListService = (MaPtWhListService) getBean("maPtWhListService");
    	String[] deleteRows = maPtWhListForm.getDeleteRows();    // sheet 내역
    	String[] deleteRowsExt = maPtWhListForm.getDeleteRowsExt();
        
        maPtWhListService.deletePtWh(deleteRows, deleteRowsExt, getUser(request));
        
        setAjaxStatus(request);
    }
}
