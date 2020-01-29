package dream.cert.list.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.cert.list.dto.CertCommonDTO;
import dream.cert.list.dto.CertEmpListDTO;
import dream.cert.list.form.CertEmpListForm;
import dream.cert.list.service.CertEmpListService;

/**
 * 작업결과-작업자 목록
 * @author  kim21017
 * @version $Id: CertEmpListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/certEmpList" name="certEmpListForm"
 *                input="/dream/cert/list/certEmpList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="certEmpList" path="/dream/cert/list/certEmpList.jsp"
 *                        redirect="false"
 */
public class CertEmpListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int CERT_EMP_LIST_FIND 			= 1001;
    /** 삭제 */
    public static final int CERT_EMP_LIST_DELETE 		= 7002;

    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        CertEmpListForm certEmpListForm = (CertEmpListForm) form;
        String strutsAction = request.getParameter("strutsAction");
        if(strutsAction!="") certEmpListForm.setStrutsAction(Integer.parseInt(strutsAction));

        switch (certEmpListForm.getStrutsAction())
        {

            case CertEmpListAction.CERT_EMP_LIST_FIND:
                findEmpList(request,response, certEmpListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case CertEmpListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, certEmpListForm.getListId(), certEmpListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case CertEmpListAction.CERT_EMP_LIST_DELETE:
            	deleteEmpList(request,certEmpListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case CertEmpListAction.BASE_GRID_EXPORT:
            	findEmpList(request,response, certEmpListForm, true);
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
     * @version $Id: CertEmpListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     *
     * @param request
     * @param certEmpListForm
     * @throws Exception
     */
    private void findEmpList(HttpServletRequest request,HttpServletResponse response, CertEmpListForm certEmpListForm, boolean excelExport) throws Exception
    {
        CertEmpListService certEmpListService = (CertEmpListService) getBean("certEmpListService");
        CertCommonDTO certCommonDTO = certEmpListForm.getCertCommonDTO();
        CertEmpListDTO certEmpListDTO = certEmpListForm.getCertEmpListDTO();
        
        //Paging
        certEmpListDTO.setIsLoadMaxCount("Y".equals(certEmpListForm.getIsLoadMaxCount())?true:false);
        certEmpListDTO.setFirstRow(certEmpListForm.getFirstRow());
        certEmpListDTO.setOrderBy(certEmpListForm.getOrderBy());
        certEmpListDTO.setDirection(certEmpListForm.getDirection());
        
        List resultList = certEmpListService.findEmpList(certCommonDTO, certEmpListDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(certEmpListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = certEmpListService.findTotalCount(certCommonDTO, certEmpListDTO, getUser(request));
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, certEmpListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: CertEmpListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     *
     * @param request
     * @param certEmpListForm
     * @throws Exception
     */
    private void deleteEmpList(HttpServletRequest request, CertEmpListForm certEmpListForm) throws Exception
    {
    	CertEmpListService certEmpListService = (CertEmpListService) getBean("certEmpListService");

    	String[] deleteRows = certEmpListForm.getDeleteRows();

    	certEmpListService.deleteEmpList(deleteRows, getUser(request));

    	setAjaxStatus(request);
    }
}