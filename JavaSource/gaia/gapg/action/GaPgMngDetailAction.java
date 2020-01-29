package gaia.gapg.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.GaiaAuthAction;
import gaia.gapg.dto.GaPgMngDetailDTO;
import gaia.gapg.form.GaPgMngDetailForm;
import gaia.gapg.service.GaPgMngDetailService;
/**
 * ȭ�� - �� action
 * 
 * @author kim2107
 * @version $Id: GaPgMngDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/gaPgMngDetail" name="gaPgMngDetailForm"
 *                input="/gaia/gapg/gaPgMngDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="gaPgMngDetail" path="/gaia/gapg/gaPgMngDetail.jsp"
 *                        redirect="false"
 */
public class GaPgMngDetailAction extends GaiaAuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int PG_DETAIL_INIT 			= 1001;
    /** ���� */
    public static final int PG_DETAIL_INPUT 		= 1002;
    /** ���� */
    public static final int PG_DETAIL_UPDATE 		= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        GaPgMngDetailForm gaPgMngDetailForm = (GaPgMngDetailForm) form;
        
        switch (gaPgMngDetailForm.getStrutsAction())
        {
            case GaPgMngDetailAction.PG_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, gaPgMngDetailForm);
                returnActionForward = mapping.findForward("gaPgMngDetail");
                break;
            case GaPgMngDetailAction.PG_DETAIL_INPUT:
            	insertDetail(gaPgMngDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case GaPgMngDetailAction.PG_DETAIL_UPDATE:
            	updateDetail(gaPgMngDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("gaPgMngDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * ȭ�� �� ��ȸ
     * @author kim2107
     * @version $Id: GaPgMngDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param gaPgMngDetailForm
     */
    private void findDetail(HttpServletRequest request, GaPgMngDetailForm gaPgMngDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	GaPgMngDetailService gaPgMngDetailService = (GaPgMngDetailService)getBean("gaPgMngDetailService");

        // �Ѱ��� ������Id ����
        String pageId = gaPgMngDetailForm.getGaPgMngCommonDTO().getPageId();
        
        // ��ȸ�� �� �κ�
        GaPgMngDetailDTO gaPgMngDetailDTO = gaPgMngDetailService.findDetail(pageId,getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        gaPgMngDetailForm.setGaPgMngDetailDTO(gaPgMngDetailDTO);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: GaPgMngDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param gaPgMngDetailForm
     * @param request
     */
    private void insertDetail(GaPgMngDetailForm gaPgMngDetailForm, HttpServletRequest request) throws Exception
    {
        GaPgMngDetailService gaPgMngDetailService = (GaPgMngDetailService) getBean("gaPgMngDetailService");
        
        GaPgMngDetailDTO gaPgMngDetailDTO = gaPgMngDetailForm.getGaPgMngDetailDTO();
        
        gaPgMngDetailDTO.setEnterBy(getUser(request).getUserId());
        
        gaPgMngDetailService.insertDetail(gaPgMngDetailDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: GaPgMngDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param gaPgMngDetailForm
     * @param request
     */
    private void updateDetail(GaPgMngDetailForm gaPgMngDetailForm, HttpServletRequest request) throws Exception
    {
    	GaPgMngDetailService gaPgMngDetailService = (GaPgMngDetailService) getBean("gaPgMngDetailService");
        
        GaPgMngDetailDTO gaPgMngDetailDTO = gaPgMngDetailForm.getGaPgMngDetailDTO();
        
        gaPgMngDetailDTO.setEnterBy(getUser(request).getUserId());
        
        gaPgMngDetailService.updateDetail(gaPgMngDetailDTO);
        
        setAjaxStatus(request);
    }
}