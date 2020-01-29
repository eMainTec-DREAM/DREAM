package dream.cert.list.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.cert.list.dto.CertCommonDTO;
import dream.cert.list.form.CertListForm;
import dream.cert.list.service.CertListService;

/**
 * 자격증분류 - 목록 action
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/certList" name="certListForm"
 *                input="/dream/cert/list/certList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="certList" path="/dream/cert/list/certList.jsp"
 *                        redirect="false"
 */
public class CertListAction extends AuthAction
{
    /** 조회 */
    public static final int CERT_LIST_FIND     = 1001;
    /** 삭제 */
    public static final int CERT_LIST_DELETE   = 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        CertListForm certListForm = (CertListForm) form;
        
        switch (certListForm.getStrutsAction())
        {
            case CertListAction.CERT_LIST_FIND:
            	findPtRecList(request, certListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case CertListAction.BASE_SET_HEADER:
                setHeader(request, response, certListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case CertListAction.CERT_LIST_DELETE:
            	deletePtRec(request, certListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case CertListAction.BASE_GRID_EXPORT:
            	findPtRecList(request, certListForm,response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("certList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, CertListForm certListForm) throws IOException
    {
        super.setHeader(request, response, certListForm.getListId(), certListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param certListForm
     * @throws Exception
     */
    private void findPtRecList(HttpServletRequest request, CertListForm certListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	CertListService certListService = (CertListService) getBean("certListService");        

    	CertCommonDTO certCommonDTO = certListForm.getCertCommonDTO();
    	
    	//Paging
    	certCommonDTO.setIsLoadMaxCount("Y".equals(certListForm.getIsLoadMaxCount())?true:false);
    	certCommonDTO.setFirstRow(certListForm.getFirstRow());
    	certCommonDTO.setOrderBy(certListForm.getOrderBy());
        certCommonDTO.setDirection(certListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = certListService.findList(certCommonDTO,getUser(request));
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(certListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = certListService.findTotalCount(certCommonDTO,getUser(request));
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, certListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * delete
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param certListForm
     * @param request
     */
    private void deletePtRec(HttpServletRequest request, CertListForm certListForm) throws Exception
    {
    	CertListService certListService = (CertListService) getBean("certListService");
    	String[] deleteRows = certListForm.getDeleteRows();    // sheet 내역
        
        certListService.deleteList(getUser(request).getCompNo(), deleteRows);
        
        setAjaxStatus(request);
    }
}
