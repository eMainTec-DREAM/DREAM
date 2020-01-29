package dream.consult.comp.list.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;

import common.config.service.ConfigService;
import common.struts.BaseAction;
import common.struts.ConsultAuthAction;
import dream.consult.comp.list.dto.MaCompMngCommonDTO;
import dream.consult.comp.list.dto.MaCompMngDetailDTO;
import dream.consult.comp.list.form.MaCompMngDetailForm;
import dream.consult.comp.list.service.MaCompMngDetailService;
import dream.mgr.user.dto.MaUserCommonDTO;
import dream.mgr.user.service.MaUserListService;

/**
 * ȸ�缳�� - �� action
 * 
 * @author kim2107
 * @version $Id: MaCompMngDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/maCompMngDetail" name="maCompMngDetailForm"
 *                input="/dream/consult/comp/list/maCompMngDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maCompMngDetail" path="/dream/consult/comp/list/maCompMngDetail.jsp"
 *                        redirect="false"
 */
public class MaCompMngDetailAction extends ConsultAuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int COMP_DETAIL_INIT 		= 1001;
    /** ���� */
    public static final int COMP_DETAIL_INPUT 		= 1002;
    /** ���� */
    public static final int COMP_DETAIL_UPDATE 		= 1003;
    /** ȯ�溯���ε� */
    public static final int COMP_CONFIG_RELOAD      = 1004;
    /** ����� ��ȣ �缳�� (�������) */
    public static final int COMP_PWRESET_RELOAD     = 1005;
    /** Slide Image ��ȸ */
    public static final int COMP_DETAIL_PHOTO       = 8004;
    
    public static final int COMP_IMAGE_COUNT		= 1006;

    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaCompMngDetailForm maCompMngDetailForm = (MaCompMngDetailForm) form;
        
        switch (maCompMngDetailForm.getStrutsAction())
        {
            case MaCompMngDetailAction.COMP_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maCompMngDetailForm);
                returnActionForward = mapping.findForward("maCompMngDetail");
                break;
            case MaCompMngDetailAction.COMP_DETAIL_INPUT:
            	insertDetail(maCompMngDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaCompMngDetailAction.COMP_DETAIL_UPDATE:
            	updateDetail(maCompMngDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaCompMngDetailAction.COMP_CONFIG_RELOAD:
                reloadConfig(maCompMngDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaCompMngDetailAction.COMP_DETAIL_PHOTO:
                findSlideImage(maCompMngDetailForm, request, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;     
            case MaCompMngDetailAction.COMP_PWRESET_RELOAD:
                resetPassword(maCompMngDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaCompMngDetailAction.COMP_IMAGE_COUNT:
            	this.getImageCount(request, maCompMngDetailForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;    
            default:
            	// ������ ��ȸ
                returnActionForward = mapping.findForward("maCompMngDetail");
                break;
        }

        return returnActionForward;
    }

    private void getImageCount(HttpServletRequest request, MaCompMngDetailForm maCompMngDetailForm) {
        // Service ��ü ����
    	MaCompMngDetailService maCompMngDetailService = (MaCompMngDetailService)getBean("maCompMngDetailService");
    	
        // ��ȸ�� �� �κ�
        MaCompMngCommonDTO maCompMngCommonDTO = maCompMngDetailForm.getMaCompMngCommonDTO();
        MaCompMngDetailDTO maCompMngDetailDTO = maCompMngDetailForm.getMaCompMngDetailDTO();
        
        String[][] resultList = maCompMngDetailService.getImageCount(maCompMngCommonDTO, maCompMngDetailDTO, getUser(request));
        
        setAjaxData(request, resultList);
		
	}

	private void findSlideImage(MaCompMngDetailForm maCompMngDetailForm, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        // Service ��ü ����
    	MaCompMngDetailService maCompMngDetailService = (MaCompMngDetailService)getBean("maCompMngDetailService");
        MaCompMngCommonDTO maCompMngCommonDTO = maCompMngDetailForm.getMaCompMngCommonDTO();
        
        // ��ȸ�� �� �κ�
        List list = maCompMngDetailService.findSlideImage(maCompMngDetailForm.getMaCompMngCommonDTO(), maCompMngDetailForm.getMaCompMngDetailDTO(), getUser(request));

        Gson gson = new Gson();
        
        String jsonString = gson.toJson(list);

        response.getWriter().print(jsonString);
    }

	private void resetPassword(MaCompMngDetailForm maCompMngDetailForm, HttpServletRequest request) 
    {
    	MaUserListService maUserListService = (MaUserListService) getBean("maUserListService");        

    	MaUserCommonDTO maUserCommonDTO =  new MaUserCommonDTO();

    	// �α� comp_no �� �����Ѵ�.
    	maUserCommonDTO.setFilterCompNo(maCompMngDetailForm.getMaCompMngCommonDTO().getCompNo());
    	maUserCommonDTO.setCompNo(maCompMngDetailForm.getMaCompMngCommonDTO().getCompNo());
    	
    	maUserListService.resetPassword(maUserCommonDTO, getUser(request));
	}

	private void reloadConfig(MaCompMngDetailForm maCompMngDetailForm, HttpServletRequest request)
    {
        ConfigService configService = (ConfigService) BaseAction.ctx.getBean("configService");
        configService.loadSecurityTable();
        configService.loadPageButtons();
        configService.loadTabPages();
        configService.loadPages();
        configService.loadMenuPathTable();
        configService.loadPageLinkes();
        configService.loadSysCodeJson();
        configService.loadConfig();
        setAjaxStatus(request);
    }

    /**
     * ȸ�缳�� �� ��ȸ
     * @author kim2107
     * @version $Id: MaCompMngDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maCompMngDetailForm
     */
    private void findDetail(HttpServletRequest request, MaCompMngDetailForm maCompMngDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaCompMngDetailService maCompMngDetailService = (MaCompMngDetailService)getBean("maCompMngDetailService");
        
        // ��ȸ�� �� �κ�
        MaCompMngDetailDTO maCompMngDetailDTO = maCompMngDetailService.findDetail(maCompMngDetailForm.getMaCompMngCommonDTO(),getUser(request));
        // ��ȸ�� ��  �����Ѵ�.
        maCompMngDetailForm.setMaCompMngDetailDTO(maCompMngDetailDTO);
        // �̹��� ��������
        maCompMngDetailDTO = maCompMngDetailService.findImage(maCompMngDetailForm.getMaCompMngCommonDTO(), maCompMngDetailForm.getMaCompMngDetailDTO(), getUser(request));
        //�̹����� request��ü�� ���
        request.setAttribute("slideLoginTitleFileList", maCompMngDetailDTO.getLoginTitleLogo());
        request.setAttribute("slideLoginSubTitleFileList", maCompMngDetailDTO.getLoginSubTitleLog());
        request.setAttribute("slideMainTitleFileList", maCompMngDetailDTO.getMainTitleLogo());
        
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaCompMngDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maCompMngDetailForm
     * @param request
     */
    private void insertDetail(MaCompMngDetailForm maCompMngDetailForm, HttpServletRequest request) throws Exception
    {
        MaCompMngDetailService maCompMngDetailService = (MaCompMngDetailService) getBean("maCompMngDetailService");
        
        MaCompMngDetailDTO maCompMngDetailDTO = maCompMngDetailForm.getMaCompMngDetailDTO();
        
        maCompMngDetailDTO.setEnterBy(getUser(request).getUserId());
        
        maCompMngDetailService.insertDetail(maCompMngDetailDTO,getUser(request));

        maCompMngDetailService.findSlideImage(maCompMngDetailForm.getMaCompMngCommonDTO(), maCompMngDetailForm.getMaCompMngDetailDTO(), getUser(request));
        setAjaxStatus(request);
        
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaCompMngDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maCompMngDetailForm
     * @param request
     */
    private void updateDetail(MaCompMngDetailForm maCompMngDetailForm, HttpServletRequest request) throws Exception
    {
    	MaCompMngDetailService maCompMngDetailService = (MaCompMngDetailService) getBean("maCompMngDetailService");
        
        MaCompMngDetailDTO maCompMngDetailDTO = maCompMngDetailForm.getMaCompMngDetailDTO();
        
        maCompMngDetailDTO.setEnterBy(getUser(request).getUserId());
        
        maCompMngDetailService.updateDetail(maCompMngDetailDTO,getUser(request));
        maCompMngDetailService.findSlideImage(maCompMngDetailForm.getMaCompMngCommonDTO(), maCompMngDetailForm.getMaCompMngDetailDTO(), getUser(request));
        setAjaxStatus(request);
    }
}