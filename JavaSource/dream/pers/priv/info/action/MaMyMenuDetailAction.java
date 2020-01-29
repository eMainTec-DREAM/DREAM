package dream.pers.priv.info.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.pers.priv.info.dto.MaMyInfoCommonDTO;
import dream.pers.priv.info.dto.MaMyMenuDetailDTO;
import dream.pers.priv.info.form.MaMyMenuDetailForm;
import dream.pers.priv.info.service.MaMyMenuDetailService;


/**
 * 상세
 * @author  jung7126
 * @version $Id: MaMyMenuDetailAction.java,v 1.0 2015/12/04 09:09:30 jung7126 Exp $
 * @since   1.0
 * @struts:action path="/maMyMenuDetail" name="maMyMenuDetailForm"
 *                input="/dream/pers/priv/info/maMyMenuDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maMyMenuDetail" path="/dream/pers/priv/info/maMyMenuDetail.jsp"
 *                        redirect="false"
 */
public class MaMyMenuDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int MY_MENU_DETAIL_INIT 		= 1001;
    /** 수정 */
    public static final int MY_MENU_DETAIL_UPDATE 	= 1002;
    /** 입력 */
    public static final int MY_MENU_DETAIL_INPUT 	= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaMyMenuDetailForm maMyMenuDetailForm = (MaMyMenuDetailForm) form;
        switch (maMyMenuDetailForm.getStrutsAction())
        {
            case MaMyMenuDetailAction.MY_MENU_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, maMyMenuDetailForm);
                returnActionForward = mapping.findForward("maMyMenuDetail");
                break;
            case MaMyMenuDetailAction.MY_MENU_DETAIL_UPDATE:
            	updateDetail(maMyMenuDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaMyMenuDetailAction.MY_MENU_DETAIL_INPUT:
            	insertDetail(maMyMenuDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maMyMenuDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 화면별 버튼 상세 조회
     * @author kim2107
     * @version $Id: MaMyMenuDetailAction.java,v 1.2 2015/12/02 01:21:30 jung7126 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maMyMenuDetailForm
     */
    private void findDetail(HttpServletRequest request, MaMyMenuDetailForm maMyMenuDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaMyMenuDetailService maMyMenuDetailService = (MaMyMenuDetailService)getBean("maMyMenuDetailService");

    	// 넘겨진 pageId 구함
    	MaMyInfoCommonDTO maMyInfoCommonDTO = maMyMenuDetailForm.getMaMyInfoCommonDTO();
        
        // 조회된 상세 부분
        MaMyMenuDetailDTO maMyMenuDetailDTO = maMyMenuDetailService.findDetail(maMyInfoCommonDTO, getUser(request));
        
        // 조회된 상세  셋팅한다.
        maMyMenuDetailForm.setMaMyMenuDetailDTO(maMyMenuDetailDTO);
    }
    /**
     * detail update
     * @author  jung7126
     * @version $Id: MaMyMenuDetailAction.java,v 1.2 2015/12/02 01:21:30 jung7126 Exp $
     * @since   1.0
     * 
     * @param maMyMenuDetailForm
     * @param request
     */
    private void updateDetail(MaMyMenuDetailForm maMyMenuDetailForm, HttpServletRequest request) throws Exception
    {
    	MaMyMenuDetailService maMyMenuDetailService = (MaMyMenuDetailService) getBean("maMyMenuDetailService");
        
        MaMyMenuDetailDTO maMyMenuDetailDTO = maMyMenuDetailForm.getMaMyMenuDetailDTO();
        MaMyInfoCommonDTO maMyInfoCommonDTO = maMyMenuDetailForm.getMaMyInfoCommonDTO();
        
        maMyMenuDetailDTO.setEnterBy(getUser(request).getUserId());
        
        maMyMenuDetailService.updateDetail(maMyMenuDetailDTO,maMyInfoCommonDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  jung7126
     * @version $Id: MaMyMenuDetailAction.java,v 1.2 2015/12/02 01:21:30 jung7126 Exp $
     * @since   1.0
     * 
     * @param maMyMenuDetailForm
     * @param request
     */
    private void insertDetail(MaMyMenuDetailForm maMyMenuDetailForm, HttpServletRequest request) throws Exception
    {
    	MaMyMenuDetailService maMyMenuDetailService = (MaMyMenuDetailService) getBean("maMyMenuDetailService");
        
        MaMyMenuDetailDTO maMyMenuDetailDTO = maMyMenuDetailForm.getMaMyMenuDetailDTO();
        
        MaMyInfoCommonDTO maMyInfoCommonDTO = maMyMenuDetailForm.getMaMyInfoCommonDTO();
        maMyMenuDetailDTO.setEnterBy(getUser(request).getUserId());
        maMyMenuDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maMyMenuDetailService.insertDetail(maMyMenuDetailDTO, maMyInfoCommonDTO);
        
        setAjaxStatus(request);
    }
}