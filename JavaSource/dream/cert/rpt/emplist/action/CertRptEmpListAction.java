package dream.cert.rpt.emplist.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;

import dream.cert.rpt.emplist.dto.CertRptEmpCommonDTO;
import dream.cert.rpt.emplist.form.CertRptEmpListForm;
import dream.cert.rpt.emplist.service.CertRptEmpListService;

/**
 * 자격증분류 - 목록 action
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/certRptEmpList" name="certRptEmpListForm"
 *                input="/dream/cert/rpt/certRptEmpList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="certRptEmpList" path="/dream/cert/rpt/certRptEmpList.jsp"
 *                        redirect="false"
 */
public class CertRptEmpListAction extends AuthAction
{
    /** 조회 */
    public static final int CERT_EMP_LIST_FIND     = 1001;

    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        CertRptEmpListForm certRptEmpListForm = (CertRptEmpListForm) form;

        switch (certRptEmpListForm.getStrutsAction())
        {
            case CertRptEmpListAction.CERT_EMP_LIST_FIND:
            	findPtRecList(request, certRptEmpListForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case CertRptEmpListAction.BASE_SET_HEADER:
                setHeader(request, response, certRptEmpListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case CertRptEmpListAction.BASE_GRID_EXPORT:
            	findPtRecList(request, certRptEmpListForm,response);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("certRptEmpList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, CertRptEmpListForm certRptEmpListForm) throws IOException
    {
        super.setHeader(request, response, certRptEmpListForm.getListId(), certRptEmpListForm.getCurrentPageId());
    }

    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     *
     * @param request
     * @param certRptEmpListForm
     * @throws Exception
     */
    private void findPtRecList(HttpServletRequest request, CertRptEmpListForm certRptEmpListForm, HttpServletResponse response) throws IOException
    {
    	CertRptEmpListService certRptEmpListService = (CertRptEmpListService) getBean("certRptEmpListService");

    	CertRptEmpCommonDTO certRptEmpCommonDTO = certRptEmpListForm.getCertRptEmpCommonDTO();

        //리스트를 조회한다.
        List resultList = certRptEmpListService.findList(certRptEmpCommonDTO   ,getUser(request));
        super.makeJsonResult(resultList, request, response, certRptEmpListForm.getListId());
    }

    
}
