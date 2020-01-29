package dream.consult.program.lang.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.message.DataBaseMessageResources;
import common.struts.AuthAction;
import common.struts.ConsultAuthAction;
import dream.consult.program.lang.dto.MaLangMngDetailDTO;
import dream.consult.program.lang.form.MaLangMngDetailForm;
import dream.consult.program.lang.service.MaLangMngDetailService;

/**
 * �ٱ��� - �� action
 * 
 * @author kim2107
 * @version $Id: MaLangMngDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/maLangMngDetail" name="maLangMngDetailForm"
 *                input="/dream/consult/program/lang/maLangMngDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maLangMngDetail" path="/dream/consult/program/lang/maLangMngDetail.jsp"
 *                        redirect="false"
 */
public class MaLangMngDetailAction extends ConsultAuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int LANG_DETAIL_INIT 		= 1001;
    /** ���� */
    public static final int LANG_DETAIL_UPDATE 		= 1002;
    /** �Է� */
    public static final int LANG_DETAIL_INPUT 		= 1003;
    /** �ߺ�üũ */
    public static final int LANG_DETAIL_CHECK		= 1004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaLangMngDetailForm maLangMngDetailForm = (MaLangMngDetailForm) form;
        
        switch (maLangMngDetailForm.getStrutsAction())
        {
            case MaLangMngDetailAction.LANG_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maLangMngDetailForm);
                returnActionForward = mapping.findForward("maLangMngDetail");
                break;
            case MaLangMngDetailAction.LANG_DETAIL_UPDATE:
            	updateDetail(maLangMngDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaLangMngDetailAction.LANG_DETAIL_INPUT:
            	insertDetail(maLangMngDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaLangMngDetailAction.LANG_DETAIL_CHECK:
            	validKeyId(maLangMngDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maLangMngDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * �ٱ��� �� ��ȸ
     * @author kim2107
     * @version $Id: MaLangMngDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maLangMngDetailForm
     */
    private void findDetail(HttpServletRequest request, MaLangMngDetailForm maLangMngDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaLangMngDetailService maLangMngDetailService = (MaLangMngDetailService)getBean("maLangMngDetailService");

    	// �Ѱ��� langIdAll ����
        String langId = maLangMngDetailForm.getMaLangMngCommonDTO().getLangId();
        
        // ��ȸ�� �� �κ�
        MaLangMngDetailDTO maLangMngDetailDTO = maLangMngDetailService.findDetail(langId, getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        maLangMngDetailForm.setMaLangMngDetailDTO(maLangMngDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaLangMngDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maLangMngDetailForm
     * @param request
     */
    private void updateDetail(MaLangMngDetailForm maLangMngDetailForm, HttpServletRequest request) throws Exception
    {
    	MaLangMngDetailService maLangMngDetailService = (MaLangMngDetailService) getBean("maLangMngDetailService");
        
        MaLangMngDetailDTO maLangMngDetailDTO = maLangMngDetailForm.getMaLangMngDetailDTO();
        
        maLangMngDetailService.updateDetail(maLangMngDetailDTO, getUser(request));
        
        DataBaseMessageResources dataBaseMessageResources = (DataBaseMessageResources)getResources(request);
//        String localeKey = getLocale(request).getLanguage();
        dataBaseMessageResources.loadLocale(getUser(request).getLangId());

        setAjaxStatus(request);
    }
    
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaLangMngDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maLangMngDetailForm
     * @param request
     */
    private void insertDetail(MaLangMngDetailForm maLangMngDetailForm, HttpServletRequest request) throws Exception
    {
        MaLangMngDetailService maLangMngDetailService = (MaLangMngDetailService) getBean("maLangMngDetailService");
        
        MaLangMngDetailDTO maLangMngDetailDTO = maLangMngDetailForm.getMaLangMngDetailDTO();
        
        maLangMngDetailService.insertDetail(maLangMngDetailDTO, getUser(request));
        
        DataBaseMessageResources dataBaseMessageResources = (DataBaseMessageResources)getResources(request);
//        String localeKey = getLocale(request).getLanguage();
        dataBaseMessageResources.loadLocale(getUser(request).getLangId());
        
        setAjaxStatus(request);
    }
    /**
     * valid keyId
     * @author  kim21017
     * @version $Id: MaLangMngDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maLangMngDetailForm
     * @param request
     */
    private void validKeyId(MaLangMngDetailForm maLangMngDetailForm, HttpServletRequest request) throws Exception
    {
    	MaLangMngDetailService maLangMngDetailService = (MaLangMngDetailService) getBean("maLangMngDetailService");
        
        MaLangMngDetailDTO maLangMngDetailDTO = maLangMngDetailForm.getMaLangMngDetailDTO();
        
        String isValid = maLangMngDetailService.validKeyId(maLangMngDetailDTO, getUser(request));
        
        setAjaxDesc(request, isValid);
    }
}