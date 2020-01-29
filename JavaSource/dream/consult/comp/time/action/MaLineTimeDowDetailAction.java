package dream.consult.comp.time.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;
import dream.consult.comp.time.dto.MaLineTimeCommonDTO;
import dream.consult.comp.time.dto.MaLineTimeDowDetailDTO;
import dream.consult.comp.time.dto.MaLineTimeDowListDTO;
import dream.consult.comp.time.form.MaLineTimeDowDetailForm;
import dream.consult.comp.time.service.MaLineTimeDowDetailService;

/**
 * ���Ϻ� ����
 * @author  kim21017
 * @version $Id: MaLineTimeDowDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maLineTimeDowDetail" name="maLineTimeDowDetailForm"
 *                input="/dream/consult/comp/time/maLineTimeDowDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maLineTimeDowDetail" path="/dream/consult/comp/time/maLineTimeDowDetail.jsp"
 *                        redirect="false"
 */
public class MaLineTimeDowDetailAction extends ConsultAuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int LINE_DOW_DETAIL_INIT 	= 1001;
    /** ���� */
    public static final int LINE_DOW_DETAIL_UPDATE 	= 1002;
    /** �Է� */
    public static final int LINE_DOW_DETAIL_INPUT 	= 1003;
    /** �Է� */
    public static final int LINE_DOW_DAY_CHECK	 	= 1004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaLineTimeDowDetailForm maLineTimeDowDetailForm = (MaLineTimeDowDetailForm) form;
        switch (maLineTimeDowDetailForm.getStrutsAction())
        {
            case MaLineTimeDowDetailAction.LINE_DOW_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maLineTimeDowDetailForm);
                returnActionForward = mapping.findForward("maLineTimeDowDetail");
                break;
            case MaLineTimeDowDetailAction.LINE_DOW_DETAIL_UPDATE:
            	updateDetail(maLineTimeDowDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaLineTimeDowDetailAction.LINE_DOW_DETAIL_INPUT:
            	insertDetail(maLineTimeDowDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaLineTimeDowDetailAction.LINE_DOW_DAY_CHECK:
            	validDay(maLineTimeDowDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maLineTimeDowDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * ���Ϻ� ���� �� ��ȸ
     * @author kim2107
     * @version $Id: MaLineTimeDowDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maLineTimeDowDetailForm
     */
    private void findDetail(HttpServletRequest request, MaLineTimeDowDetailForm maLineTimeDowDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaLineTimeDowDetailService maLineTimeDowDetailService = (MaLineTimeDowDetailService)getBean("maLineTimeDowDetailService");
    	
    	MaLineTimeDowListDTO maLineTimeDowListDTO = maLineTimeDowDetailForm.getMaLineTimeDowListDTO();
        
        // ��ȸ�� �� �κ�
        MaLineTimeDowDetailDTO maLineTimeDowDetailDTO = maLineTimeDowDetailService.findDetail(maLineTimeDowListDTO, getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        maLineTimeDowDetailForm.setMaLineTimeDowDetailDTO(maLineTimeDowDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaLineTimeDowDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maLineTimeDowDetailForm
     * @param request
     */
    private void updateDetail(MaLineTimeDowDetailForm maLineTimeDowDetailForm, HttpServletRequest request) throws Exception
    {
    	MaLineTimeDowDetailService maLineTimeDowDetailService = (MaLineTimeDowDetailService) getBean("maLineTimeDowDetailService");
        
        MaLineTimeDowDetailDTO maLineTimeDowDetailDTO = maLineTimeDowDetailForm.getMaLineTimeDowDetailDTO();
        
        maLineTimeDowDetailService.updateDetail(maLineTimeDowDetailDTO,getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaLineTimeDowDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maLineTimeDowDetailForm
     * @param request
     */
    private void insertDetail(MaLineTimeDowDetailForm maLineTimeDowDetailForm, HttpServletRequest request) throws Exception
    {
    	MaLineTimeDowDetailService maLineTimeDowDetailService = (MaLineTimeDowDetailService) getBean("maLineTimeDowDetailService");
        
        MaLineTimeDowDetailDTO maLineTimeDowDetailDTO = maLineTimeDowDetailForm.getMaLineTimeDowDetailDTO();
        
        maLineTimeDowDetailService.insertDetail(maLineTimeDowDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * ���� �ߺ� ������ �˻�
     */
    private void validDay(MaLineTimeDowDetailForm maLineTimeDowDetailForm, HttpServletRequest request) throws Exception
    {
    	MaLineTimeDowDetailService maLineTimeDowDetailService = (MaLineTimeDowDetailService) getBean("maLineTimeDowDetailService");
        
    	MaLineTimeCommonDTO maLineTimeCommonDTO = maLineTimeDowDetailForm.getMaLineTimeCommonDTO();
    	MaLineTimeDowDetailDTO maLineTimeDowDetailDTO = maLineTimeDowDetailForm.getMaLineTimeDowDetailDTO();
        
        String isValid = maLineTimeDowDetailService.validDay(maLineTimeCommonDTO, maLineTimeDowDetailDTO, getUser(request));
        setAjaxDesc(request,isValid);
    }
    
}