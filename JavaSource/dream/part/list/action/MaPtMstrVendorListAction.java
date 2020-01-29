package dream.part.list.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.form.MaPtMstrVendorListForm;
import dream.part.list.service.MaPtMstrVendorListService;

/**
 * 부품거래처 목록
 * @author  ssong
 * @version $Id$
 * @since   1.0
 * @struts:action path="/maPtMstrVendorList" name="maPtMstrVendorListForm"
 *                input="/dream/part/list/maPtMstrVendorList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPtMstrVendorList" path="/dream/part/list/maPtMstrVendorList.jsp"
 *                        redirect="false"
 */
public class MaPtMstrVendorListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int PTMSTR_VENDOR_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int PTMSTR_VENDOR_LIST_DELETE 		= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPtMstrVendorListForm maPtMstrVendorListForm = (MaPtMstrVendorListForm) form;
        
        switch (maPtMstrVendorListForm.getStrutsAction())
        {
        
            case MaPtMstrVendorListAction.PTMSTR_VENDOR_LIST_FIND:
                findList(request,response, maPtMstrVendorListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtMstrVendorListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, maPtMstrVendorListForm.getListId(), maPtMstrVendorListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtMstrVendorListAction.PTMSTR_VENDOR_LIST_DELETE:
            	deleteList(request,maPtMstrVendorListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPtMstrVendorListAction.BASE_GRID_EXPORT:
            	findList(request,response, maPtMstrVendorListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maPtMstrVendorList");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPtMstrVendorListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request,HttpServletResponse response, MaPtMstrVendorListForm maPtMstrVendorListForm, boolean excelExport) throws Exception
    {
        MaPtMstrVendorListService maPtMstrVendorListService = (MaPtMstrVendorListService) getBean("maPtMstrVendorListService");
        MaPtMstrCommonDTO maPtMstrCommonDTO = maPtMstrVendorListForm.getMaPtMstrCommonDTO();
        		
        //Paging
    	maPtMstrCommonDTO.setIsLoadMaxCount("Y".equals(maPtMstrVendorListForm.getIsLoadMaxCount())?true:false);
    	maPtMstrCommonDTO.setFirstRow(maPtMstrVendorListForm.getFirstRow());
    	maPtMstrCommonDTO.setOrderBy(maPtMstrVendorListForm.getOrderBy());
    	maPtMstrCommonDTO.setDirection(maPtMstrVendorListForm.getDirection());
    	
    	User user = getUser(request);
    	maPtMstrCommonDTO.setPartId(maPtMstrCommonDTO.getPartId());
    	
        List resultList = maPtMstrVendorListService.findList(maPtMstrCommonDTO, user);
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(maPtMstrVendorListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maPtMstrVendorListService.findTotalCount(maPtMstrCommonDTO, user);

        if(excelExport) super.makeGridExport(resultList, request, response,maPtMstrVendorListForm.getListId(),maPtMstrVendorListForm.getCurrentPageId(), maPtMstrVendorListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * delete
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPtMstrVendorListForm
     * @throws Exception
     */
    private void deleteList(HttpServletRequest request, MaPtMstrVendorListForm maPtMstrVendorListForm) throws Exception
    {
    	MaPtMstrVendorListService maPtMstrVendorListService = (MaPtMstrVendorListService) getBean("maPtMstrVendorListService");
        
    	String compNo = getUser(request).getCompNo();
    	String[] deleteRows = maPtMstrVendorListForm.getDeleteRows();
    
    	maPtMstrVendorListService.deleteList(deleteRows, getUser(request));
    	
    	setAjaxStatus(request);
    }
}