package dream.work.list.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.list.dto.MaWoResultClnDetailDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.form.MaWoResultClnDetailForm;
import dream.work.list.service.MaWoResultClnDetailService;

/**
 * �۾���� - �۾�����
 * @author  kim21017
 * @version $Id: MaWoResultClnDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maWoResultClnDetail" name="maWoResultClnDetailForm"
 *                input="/dream/work/list/maWoResultClnDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultOilDetail" name="maWoResultClnDetailForm"
 *                input="/dream/work/list/maWoResultOilDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maWoResultClnDetail" path="/dream/work/list/maWoResultClnDetail.jsp"
 *                        redirect="false"
 */
public class MaWoResultClnDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int WO_RESULT_CLN_DETAIL_INIT 	= 8001;
    /** ���� */
    public static final int WO_RESULT_CLN_DETAIL_UPDATE = 6002;
    /** �Է� */
    public static final int WO_RESULT_CLN_DETAIL_INPUT 	= 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaWoResultClnDetailForm maWoResultClnDetailForm = (MaWoResultClnDetailForm) form;

        super.updateAudit(maWoResultClnDetailForm.getMaWoResultClnDetailDTO().getAuditKey()==""?maWoResultClnDetailForm.getMaWoResultMstrCommonDTO().getAuditKey():maWoResultClnDetailForm.getMaWoResultClnDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        String strutsAction = request.getParameter("strutsAction");
        if(strutsAction!="") maWoResultClnDetailForm.setStrutsAction(Integer.parseInt(strutsAction));
        switch (maWoResultClnDetailForm.getStrutsAction())
        {
            case MaWoResultClnDetailAction.WO_RESULT_CLN_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maWoResultClnDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case MaWoResultClnDetailAction.WO_RESULT_CLN_DETAIL_UPDATE:
            	updateDetail(maWoResultClnDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaWoResultClnDetailAction.WO_RESULT_CLN_DETAIL_INPUT:
            	insertDetail(maWoResultClnDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * �۾����-�۾��� �� ��ȸ
     * @author kim2107
     * @version $Id: MaWoResultClnDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maWoResultClnDetailForm
     */
    private void findDetail(HttpServletRequest request, MaWoResultClnDetailForm maWoResultClnDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaWoResultClnDetailService maWoResultClnDetailService = (MaWoResultClnDetailService)getBean("maWoResultClnDetailService");

    	// �۾����Id ����
        String wkOrId = maWoResultClnDetailForm.getMaWoResultMstrCommonDTO().getWkOrId();
        // �Ѱ��� ����id ����
        String woEquipId = maWoResultClnDetailForm.getMaWoResultClnListDTO().getWoEquipId();
        
        // ��ȸ�� �� �κ�
        MaWoResultClnDetailDTO maWoResultClnDetailDTO = maWoResultClnDetailService.findDetail(wkOrId, woEquipId, getUser(request).getCompNo());
        
        // ��ȸ�� ��  �����Ѵ�.
        maWoResultClnDetailForm.setMaWoResultClnDetailDTO(maWoResultClnDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaWoResultClnDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultClnDetailForm
     * @param request
     */
    private void updateDetail(MaWoResultClnDetailForm maWoResultClnDetailForm, HttpServletRequest request) throws Exception
    {
    	MaWoResultClnDetailService maWoResultClnDetailService = (MaWoResultClnDetailService) getBean("maWoResultClnDetailService");
        
        MaWoResultClnDetailDTO maWoResultClnDetailDTO = maWoResultClnDetailForm.getMaWoResultClnDetailDTO();
        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = maWoResultClnDetailForm.getMaWoResultMstrCommonDTO();
        maWoResultClnDetailDTO.setEnterBy(getUser(request).getUserId());
        maWoResultMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maWoResultClnDetailService.updateDetail(maWoResultClnDetailDTO,maWoResultMstrCommonDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaWoResultClnDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultClnDetailForm
     * @param request
     */
    private void insertDetail(MaWoResultClnDetailForm maWoResultClnDetailForm, HttpServletRequest request) throws Exception
    {
    	MaWoResultClnDetailService maWoResultClnDetailService = (MaWoResultClnDetailService) getBean("maWoResultClnDetailService");
        
        MaWoResultClnDetailDTO maWoResultClnDetailDTO = maWoResultClnDetailForm.getMaWoResultClnDetailDTO();
        
        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = maWoResultClnDetailForm.getMaWoResultMstrCommonDTO();
        maWoResultClnDetailDTO.setEnterBy(getUser(request).getUserId());
        maWoResultMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maWoResultClnDetailService.insertDetail(maWoResultClnDetailDTO, maWoResultMstrCommonDTO);
        
        setAjaxStatus(request);
    }
}