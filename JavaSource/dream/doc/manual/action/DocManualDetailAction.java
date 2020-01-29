package dream.doc.manual.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.doc.manual.dto.DocManualDetailDTO;
import dream.doc.manual.form.DocManualDetailForm;
import dream.doc.manual.service.DocManualDetailService;

/**
 * ����ڸŴ��� - �� action
 * 
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/docManualDetail" name="docManualDetailForm"
 *                input="/dream/doc/manual/docManualDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="docManualDetail" path="/dream/doc/manual/docManualDetail.jsp"
 *                        redirect="false"
 */
public class DocManualDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int HELP_DETAIL_INIT 		= 8001;
    /** ���� */
    public static final int HELP_DETAIL_INPUT 		= 5002;
    /** ���� */
    public static final int HELP_DETAIL_UPDATE 		= 6003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        DocManualDetailForm docManualDetailForm = (DocManualDetailForm) form;
        
        super.updateAudit(docManualDetailForm.getDocManualDetailDTO().getAuditKey()==""?docManualDetailForm.getDocManualCommonDTO().getAuditKey():docManualDetailForm.getDocManualDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (docManualDetailForm.getStrutsAction())
        {
            case DocManualDetailAction.HELP_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, docManualDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case DocManualDetailAction.HELP_DETAIL_INPUT:
            	insertDetail(docManualDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case DocManualDetailAction.HELP_DETAIL_UPDATE:
            	updateDetail(docManualDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * ���� �� ��ȸ
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param docManualDetailForm
     */
    private void findDetail(HttpServletRequest request, DocManualDetailForm docManualDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	DocManualDetailService docManualDetailService = (DocManualDetailService)getBean("docManualDetailService");

        // �Ѱ��� Id ����
        String id = docManualDetailForm.getDocManualCommonDTO().getOnlineHelpId();
        
        // ��ȸ�� �� �κ�
        DocManualDetailDTO docManualDetailDTO = docManualDetailService.findDetail(id,getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        docManualDetailForm.setDocManualDetailDTO(docManualDetailDTO);
    }
    /**
     * detail insert
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param docManualDetailForm
     * @param request
     */
    private void insertDetail(DocManualDetailForm docManualDetailForm, HttpServletRequest request) throws Exception
    {
        DocManualDetailService docManualDetailService = (DocManualDetailService) getBean("docManualDetailService");
        
        DocManualDetailDTO docManualDetailDTO = docManualDetailForm.getDocManualDetailDTO();
        
        docManualDetailDTO.setEnterBy(getUser(request).getUserId());
        
        docManualDetailService.insertDetail(docManualDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param docManualDetailForm
     * @param request
     */
    private void updateDetail(DocManualDetailForm docManualDetailForm, HttpServletRequest request) throws Exception
    {
    	DocManualDetailService docManualDetailService = (DocManualDetailService) getBean("docManualDetailService");
        
        DocManualDetailDTO docManualDetailDTO = docManualDetailForm.getDocManualDetailDTO();
        
        docManualDetailDTO.setEnterBy(getUser(request).getUserId());
        
        docManualDetailService.updateDetail(docManualDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}