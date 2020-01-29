package dream.cert.list.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.cert.list.dto.CertCommonDTO;
import dream.cert.list.dto.CertEmpDetailDTO;
import dream.cert.list.form.CertEmpDetailForm;
import dream.cert.list.service.CertEmpDetailService;

/**
 * �ڰ��� - ���
 * @author  kim21017
 * @version $Id: CertEmpDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/certEmpDetail" name="certEmpDetailForm"
 *                input="/dream/cert/list/certEmpDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="certEmpDetail" path="/dream/cert/list/certEmpDetail.jsp"
 *                        redirect="false"
 */
public class CertEmpDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int CERT_EMP_DETAIL_INIT 	= 8001;
    /** ���� */
    public static final int CERT_EMP_DETAIL_UPDATE 	= 6002;
    /** �Է� */
    public static final int CERT_EMP_DETAIL_INPUT 	= 5003;
    /** ���� �ڰ��� �ߺ� EMP �˻� */
    public static final int CERT_EMP_DETAIL_CHECK	= 1004;

    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        CertEmpDetailForm certEmpDetailForm = (CertEmpDetailForm) form;
        String strutsAction = request.getParameter("strutsAction");
        if(strutsAction!="") certEmpDetailForm.setStrutsAction(Integer.parseInt(strutsAction));
        
        super.updateAudit(certEmpDetailForm.getCertEmpDetailDTO().getAuditKey()==""?certEmpDetailForm.getCertEmpListDTO().getAuditKey():certEmpDetailForm.getCertEmpDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (certEmpDetailForm.getStrutsAction())
        {
            case CertEmpDetailAction.CERT_EMP_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, certEmpDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case CertEmpDetailAction.CERT_EMP_DETAIL_UPDATE:
            	updateDetail(certEmpDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case CertEmpDetailAction.CERT_EMP_DETAIL_INPUT:
            	insertDetail(certEmpDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case CertEmpDetailAction.CERT_EMP_DETAIL_CHECK:
            	validEmp(certEmpDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    /**
     * �ڰ���-��� �� ��ȸ
     * @author kim2107
     * @version $Id: CertEmpDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *
     * @param request
     * @param certEmpDetailForm
     */
    private void findDetail(HttpServletRequest request, CertEmpDetailForm certEmpDetailForm)throws Exception
    {
        // Service ��ü ����
    	CertEmpDetailService certEmpDetailService = (CertEmpDetailService)getBean("certEmpDetailService");
    	// �ڰ���Id ����
        String empCertListId = certEmpDetailForm.getCertEmpListDTO().getEmpCertListId();
        // ��ȸ�� �� �κ�
        CertEmpDetailDTO certEmpDetailDTO = certEmpDetailService.findDetail(empCertListId, getUser(request));
        // ��ȸ�� ��  �����Ѵ�.
        certEmpDetailForm.setCertEmpDetailDTO(certEmpDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: CertEmpDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *
     * @param certEmpDetailForm
     * @param request
     */
    private void updateDetail(CertEmpDetailForm certEmpDetailForm, HttpServletRequest request) throws Exception
    {
    	CertEmpDetailService certEmpDetailService = (CertEmpDetailService) getBean("certEmpDetailService");

        CertEmpDetailDTO certEmpDetailDTO = certEmpDetailForm.getCertEmpDetailDTO();
        CertCommonDTO certCommonDTO = certEmpDetailForm.getCertCommonDTO();
        
        certEmpDetailService.updateDetail(certEmpDetailDTO,certCommonDTO, getUser(request));

        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: CertEmpDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *
     * @param certEmpDetailForm
     * @param request
     */
    private void insertDetail(CertEmpDetailForm certEmpDetailForm, HttpServletRequest request) throws Exception
    {
    	CertEmpDetailService certEmpDetailService = (CertEmpDetailService) getBean("certEmpDetailService");

        CertEmpDetailDTO certEmpDetailDTO = certEmpDetailForm.getCertEmpDetailDTO();

        CertCommonDTO certCommonDTO = certEmpDetailForm.getCertCommonDTO();
        certEmpDetailService.insertDetail(certEmpDetailDTO, certCommonDTO, getUser(request));

        setAjaxStatus(request);
    }
    /**
     * ��� �ߺ� �˻�
     */
    private void validEmp(CertEmpDetailForm certEmpDetailForm, HttpServletRequest request) throws Exception
    {
    	CertEmpDetailService certEmpDetailService = (CertEmpDetailService) getBean("certEmpDetailService");

        CertEmpDetailDTO certEmpDetailDTO = certEmpDetailForm.getCertEmpDetailDTO();

        CertCommonDTO certCommonDTO = certEmpDetailForm.getCertCommonDTO();
        String isValid = certEmpDetailService.validEmp(certEmpDetailDTO,certCommonDTO, getUser(request));
        setAjaxDesc(request, isValid);
    }
}