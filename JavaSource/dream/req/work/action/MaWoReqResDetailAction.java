package dream.req.work.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.req.work.dto.MaWoReqCommonDTO;
import dream.req.work.dto.MaWoReqResDetailDTO;
import dream.req.work.form.MaWoReqResDetailForm;
import dream.req.work.service.MaWoReqResDetailService;

/**
 * �۾���û�� - ó���۾�
 * @author  kim21017
 * @version $Id: MaWoReqResDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maWoReqResDetail" name="maWoReqResDetailForm"
 *                input="/dream/req/work/maWoReqResDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maWoReqResDetail" path="/dream/req/work/maWoReqResDetail.jsp"
 *                        redirect="false"
 */
public class MaWoReqResDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int DETAIL_INIT 	= 1001;
    /** ���� */
    public static final int DETAIL_UPDATE 	= 1002;
    /** �Է� */
    public static final int DETAIL_INPUT 	= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaWoReqResDetailForm maWoReqResDetailForm = (MaWoReqResDetailForm) form;
        String strutsAction = request.getParameter("strutsAction");
        if(strutsAction!="") maWoReqResDetailForm.setStrutsAction(Integer.parseInt(strutsAction));
        switch (maWoReqResDetailForm.getStrutsAction())
        {
            case MaWoReqResDetailAction.DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maWoReqResDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case MaWoReqResDetailAction.DETAIL_UPDATE:
            	updateDetail(maWoReqResDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaWoReqResDetailAction.DETAIL_INPUT:
            	insertDetail(maWoReqResDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * �� ��ȸ
     * @author kim2107
     * @version $Id: MaWoReqResDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maWoReqResDetailForm
     */
    private void findDetail(HttpServletRequest request, MaWoReqResDetailForm maWoReqResDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaWoReqResDetailService maWoReqResDetailService = (MaWoReqResDetailService)getBean("maWoReqResDetailService");

    	// ��û�� id
        String woReqId = maWoReqResDetailForm.getMaWoReqCommonDTO().getWoReqId();
        // ó������ id
        String woReqResId = maWoReqResDetailForm.getMaWoReqResListDTO().getWoReqResId();
        
        // ��ȸ�� �� �κ�
        MaWoReqResDetailDTO maWoReqResDetailDTO = maWoReqResDetailService.findDetail(woReqId, woReqResId, getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        maWoReqResDetailForm.setMaWoReqResDetailDTO(maWoReqResDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaWoReqResDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoReqResDetailForm
     * @param request
     */
    private void updateDetail(MaWoReqResDetailForm maWoReqResDetailForm, HttpServletRequest request) throws Exception
    {
    	MaWoReqResDetailService maWoReqResDetailService = (MaWoReqResDetailService) getBean("maWoReqResDetailService");
        
        MaWoReqResDetailDTO maWoReqResDetailDTO = maWoReqResDetailForm.getMaWoReqResDetailDTO();
        MaWoReqCommonDTO maWoReqCommonDTO = maWoReqResDetailForm.getMaWoReqCommonDTO();
        maWoReqResDetailDTO.setEnterBy(getUser(request).getUserId());
        maWoReqCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maWoReqResDetailService.updateDetail(maWoReqResDetailDTO,maWoReqCommonDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaWoReqResDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoReqResDetailForm
     * @param request
     */
    private void insertDetail(MaWoReqResDetailForm maWoReqResDetailForm, HttpServletRequest request) throws Exception
    {
    	MaWoReqResDetailService maWoReqResDetailService = (MaWoReqResDetailService) getBean("maWoReqResDetailService");
        
        MaWoReqResDetailDTO maWoReqResDetailDTO = maWoReqResDetailForm.getMaWoReqResDetailDTO();
        
        MaWoReqCommonDTO maWoReqCommonDTO = maWoReqResDetailForm.getMaWoReqCommonDTO();
        maWoReqResDetailDTO.setEnterBy(getUser(request).getUserId());
        maWoReqCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maWoReqResDetailService.insertDetail(maWoReqResDetailDTO, maWoReqCommonDTO);
        
        setAjaxStatus(request);
    }
}