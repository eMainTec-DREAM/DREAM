package dream.org.vendor.action;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.org.vendor.dto.MaVendorCommonDTO;
import dream.org.vendor.form.MaVendorListForm;
import dream.org.vendor.service.MaVendorListService;

/**
 * ���þ�ü - ��� action
 * @author  ssong
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/maVendorList" name="maVendorListForm"
 *                input="/dream/org/vendor/maVendorList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maVendorList" path="/dream/org/vendor/maVendorList.jsp"
 *                        redirect="false"
 */
public class MaVendorListAction extends AuthAction
{
    /** ��ȸ */
    public static final int VENDOR_LIST_FIND    = 8001;
    /** ���� */
    public static final int VENDOR_LIST_DELETE  = 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaVendorListForm maVendorListForm = (MaVendorListForm) form;
        
        super.updateAudit(maVendorListForm.getMaVendorCommonDTO().getAuditKey()==""?maVendorListForm.getMaVendorCommonDTO().getAuditKey():maVendorListForm.getMaVendorCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maVendorListForm.getStrutsAction())
        {
            case MaVendorListAction.BASE_SET_HEADER:
                setHeader(request, response, maVendorListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaVendorListAction.VENDOR_LIST_FIND:
                findList(request, response, maVendorListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case MaVendorListAction.VENDOR_LIST_DELETE:
            	deleteList(request, maVendorListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case MaVendorListAction.BASE_GRID_EXPORT:
            	findList(request, response, maVendorListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maVendorList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaVendorListForm maVendorListForm) throws IOException
    {
        super.setHeader(request, response, maVendorListForm.getListId(), maVendorListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maVendorListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, MaVendorListForm maVendorListForm, boolean excelExport) throws IOException
    {
    	MaVendorListService maVendorListService = (MaVendorListService) getBean("maVendorListService");        

    	MaVendorCommonDTO maVendorCommonDTO = maVendorListForm.getMaVendorCommonDTO();

    	//Paging
    	maVendorCommonDTO.setIsLoadMaxCount("Y".equals(maVendorListForm.getIsLoadMaxCount())?true:false);
    	maVendorCommonDTO.setFirstRow(maVendorListForm.getFirstRow());
    	maVendorCommonDTO.setOrderBy(maVendorListForm.getOrderBy());
    	maVendorCommonDTO.setDirection(maVendorListForm.getDirection());
    	// �α� comp_no �� �����Ѵ�.
    	maVendorCommonDTO.setFilterCompNo((getUser(request).getCompNo()));
        
        //����Ʈ�� ��ȸ�Ѵ�.
        List resultList = maVendorListService.findList(maVendorCommonDTO, getUser(request));
      //Paging
        String totalCount = "";
        if(Integer.parseInt(maVendorListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maVendorListService.findTotalCount(maVendorCommonDTO,getUser(request));

        // ��ȸ�� List �� form�� �����Ѵ�.
        if(excelExport) super.makeGridExport(resultList, request, response,maVendorListForm.getListId(),maVendorListForm.getCurrentPageId(), maVendorListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * delete
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param maVendorListForm
     * @param request
     */
    private void deleteList(HttpServletRequest request, MaVendorListForm maVendorListForm) throws Exception
    {
    	MaVendorListService maVendorListService = (MaVendorListService) getBean("maVendorListService");        

        String[] deleteRows = maVendorListForm.getDeleteRows();    // sheet ����
        
        maVendorListService.deleteList((getUser(request).getCompNo()), deleteRows);
        
        setAjaxStatus(request);
    }
}
