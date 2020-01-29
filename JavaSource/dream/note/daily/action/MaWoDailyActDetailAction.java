package dream.note.daily.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.note.daily.dto.MaWoDailyActDetailDTO;
import dream.note.daily.dto.MaWoDailyCommonDTO;
import dream.note.daily.form.MaWoDailyActDetailForm;
import dream.note.daily.service.MaWoDailyActDetailService;

/**
 * �����۾� - Main Activities
 * @author  kim21017
 * @version $Id: MaWoDailyActDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maWoDailyActDetail" name="maWoDailyActDetailForm"
 *                input="/dream/note/daily/maWoDailyActDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maWoDailyActDetail" path="/dream/note/daily/maWoDailyActDetail.jsp"
 *                        redirect="false"
 */
public class MaWoDailyActDetailAction extends AuthAction
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
        MaWoDailyActDetailForm maWoDailyActDetailForm = (MaWoDailyActDetailForm) form;
        String strutsAction = request.getParameter("strutsAction");
        if(strutsAction!="") maWoDailyActDetailForm.setStrutsAction(Integer.parseInt(strutsAction));
        switch (maWoDailyActDetailForm.getStrutsAction())
        {
            case MaWoDailyActDetailAction.DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maWoDailyActDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case MaWoDailyActDetailAction.DETAIL_UPDATE:
            	updateDetail(maWoDailyActDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaWoDailyActDetailAction.DETAIL_INPUT:
            	insertDetail(maWoDailyActDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * �����۾�-Main Act �� ��ȸ
     * @author kim2107
     * @version $Id: MaWoDailyActDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maWoDailyActDetailForm
     */
    private void findDetail(HttpServletRequest request, MaWoDailyActDetailForm maWoDailyActDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaWoDailyActDetailService maWoDailyActDetailService = (MaWoDailyActDetailService)getBean("maWoDailyActDetailService");

    	// �����۾�Id ����
        String woDayListId = maWoDailyActDetailForm.getMaWoDailyCommonDTO().getWoDayListId();
        // �Ѱ��� actid ����
        String woDayActId = maWoDailyActDetailForm.getMaWoDailyActListDTO().getWoDayActId();
        
        // ��ȸ�� �� �κ�
        MaWoDailyActDetailDTO maWoDailyActDetailDTO = maWoDailyActDetailService.findDetail(woDayListId, woDayActId, getUser(request).getCompNo());
        
        // ��ȸ�� ��  �����Ѵ�.
        maWoDailyActDetailForm.setMaWoDailyActDetailDTO(maWoDailyActDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaWoDailyActDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoDailyActDetailForm
     * @param request
     */
    private void updateDetail(MaWoDailyActDetailForm maWoDailyActDetailForm, HttpServletRequest request) throws Exception
    {
    	MaWoDailyActDetailService maWoDailyActDetailService = (MaWoDailyActDetailService) getBean("maWoDailyActDetailService");
        
        MaWoDailyActDetailDTO maWoDailyActDetailDTO = maWoDailyActDetailForm.getMaWoDailyActDetailDTO();
        MaWoDailyCommonDTO maWoDailyCommonDTO = maWoDailyActDetailForm.getMaWoDailyCommonDTO();
        maWoDailyActDetailDTO.setEnterBy(getUser(request).getUserId());
        maWoDailyCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maWoDailyActDetailService.updateDetail(maWoDailyActDetailDTO,maWoDailyCommonDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaWoDailyActDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoDailyActDetailForm
     * @param request
     */
    private void insertDetail(MaWoDailyActDetailForm maWoDailyActDetailForm, HttpServletRequest request) throws Exception
    {
    	MaWoDailyActDetailService maWoDailyActDetailService = (MaWoDailyActDetailService) getBean("maWoDailyActDetailService");
        
        MaWoDailyActDetailDTO maWoDailyActDetailDTO = maWoDailyActDetailForm.getMaWoDailyActDetailDTO();
        
        MaWoDailyCommonDTO maWoDailyCommonDTO = maWoDailyActDetailForm.getMaWoDailyCommonDTO();
        maWoDailyActDetailDTO.setEnterBy(getUser(request).getUserId());
        maWoDailyCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maWoDailyActDetailService.insertDetail(maWoDailyActDetailDTO, maWoDailyCommonDTO);
        
        setAjaxStatus(request);
    }
}