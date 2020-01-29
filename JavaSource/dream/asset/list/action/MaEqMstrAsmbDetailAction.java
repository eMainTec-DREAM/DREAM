package dream.asset.list.action;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;
import com.microsoft.azure.storage.StorageException;

import common.bean.User;
import common.struts.AuthAction;
import dream.asset.list.dto.MaEqMstrAsmbDetailDTO;
import dream.asset.list.dto.MaEqMstrAsmbListDTO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.form.MaEqMstrAsmbDetailForm;
import dream.asset.list.service.MaEqMstrAsmbDetailService;

/**
 * ��������(����)
 * @author  kim21017
 * @version $Id: MaEqMstrAsmbDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maEqMstrAsmbDetail" name="maEqMstrAsmbDetailForm"
 *                input="/dream/asset/list/maEqMstrAsmbDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maEqMstrAsmbDetail" path="/dream/asset/list/maEqMstrAsmbDetail.jsp"
 *                        redirect="false"
 */
public class MaEqMstrAsmbDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int EQ_MSTR_ASMB_DETAIL_INIT 	= 8001;
    /** ���� */
    public static final int EQ_MSTR_ASMB_DETAIL_UPDATE 	= 6002;
    /** �Է� */
    public static final int EQ_MSTR_ASMB_DETAIL_INPUT 	= 5003;
    /** Slide Image ��ȸ */
    public static final int EQ_MSTR_ASMB_DETAIL_PHOTO 	= 8004;
    /** ���� */
    public static final int DETAIL_COPY 				= 5005;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaEqMstrAsmbDetailForm maEqMstrAsmbDetailForm = (MaEqMstrAsmbDetailForm) form;
        
        super.updateAudit(maEqMstrAsmbDetailForm.getMaEqMstrAsmbDetailDTO().getAuditKey()==""?maEqMstrAsmbDetailForm.getMaEqMstrAsmbListDTO().getAuditKey():maEqMstrAsmbDetailForm.getMaEqMstrAsmbDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maEqMstrAsmbDetailForm.getStrutsAction())
        {
            case MaEqMstrAsmbDetailAction.EQ_MSTR_ASMB_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maEqMstrAsmbDetailForm);
                returnActionForward = mapping.findForward("maEqMstrAsmbDetail");
                break;
            case MaEqMstrAsmbDetailAction.EQ_MSTR_ASMB_DETAIL_UPDATE:
            	updateDetail(maEqMstrAsmbDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaEqMstrAsmbDetailAction.EQ_MSTR_ASMB_DETAIL_INPUT:
            	insertDetail(maEqMstrAsmbDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaEqMstrAsmbDetailAction.EQ_MSTR_ASMB_DETAIL_PHOTO:
            	findSlideImage(maEqMstrAsmbDetailForm, request, response);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case MaEqMstrAsmbDetailAction.DETAIL_COPY:
            	copyDetail(maEqMstrAsmbDetailForm, request, response);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maEqMstrAsmbDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * �������� ��ȸ 
     * @author kim2107
     * @version $Id: MaEqMstrAsmbDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqMstrAsmbDetailForm
     */
    private void findDetail(HttpServletRequest request, MaEqMstrAsmbDetailForm maEqMstrAsmbDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaEqMstrAsmbDetailService maEqMstrAsmbDetailService = (MaEqMstrAsmbDetailService)getBean("maEqMstrAsmbDetailService");

    	MaEqMstrAsmbListDTO maEqMstrAsmbListDTO = maEqMstrAsmbDetailForm.getMaEqMstrAsmbListDTO();
    	MaEqMstrCommonDTO maEqMstrCommonDTO = maEqMstrAsmbDetailForm.getMaEqMstrCommonDTO();
    	User user = getUser(request);
    	
        // ��ȸ�� �� �κ�
        MaEqMstrAsmbDetailDTO maEqMstrAsmbDetailDTO = maEqMstrAsmbDetailService.findDetail(maEqMstrAsmbListDTO, maEqMstrCommonDTO, user);
        
        List photoList = maEqMstrAsmbDetailService.getPhotoList(maEqMstrAsmbDetailDTO, getUser(request));
        		
        request.setAttribute("slideFileList", photoList);
        
        // ��ȸ�� ��  �����Ѵ�.
        maEqMstrAsmbDetailForm.setMaEqMstrAsmbDetailDTO(maEqMstrAsmbDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaEqMstrAsmbDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrAsmbDetailForm
     * @param request
     */
    private void updateDetail(MaEqMstrAsmbDetailForm maEqMstrAsmbDetailForm, HttpServletRequest request) throws Exception
    {
    	MaEqMstrAsmbDetailService maEqMstrAsmbDetailService = (MaEqMstrAsmbDetailService) getBean("maEqMstrAsmbDetailService");
        
        MaEqMstrAsmbDetailDTO maEqMstrAsmbDetailDTO = maEqMstrAsmbDetailForm.getMaEqMstrAsmbDetailDTO();
        MaEqMstrCommonDTO maEqMstrCommonDTO = maEqMstrAsmbDetailForm.getMaEqMstrCommonDTO();

        User user = getUser(request);
        
        maEqMstrAsmbDetailService.updateDetail(maEqMstrAsmbDetailDTO,maEqMstrCommonDTO, user);
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaEqMstrAsmbDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrAsmbDetailForm
     * @param request
     */
    private void insertDetail(MaEqMstrAsmbDetailForm maEqMstrAsmbDetailForm, HttpServletRequest request) throws Exception
    {
    	MaEqMstrAsmbDetailService maEqMstrAsmbDetailService = (MaEqMstrAsmbDetailService) getBean("maEqMstrAsmbDetailService");
        
        MaEqMstrAsmbDetailDTO maEqMstrAsmbDetailDTO = maEqMstrAsmbDetailForm.getMaEqMstrAsmbDetailDTO();
        MaEqMstrCommonDTO maEqMstrCommonDTO = maEqMstrAsmbDetailForm.getMaEqMstrCommonDTO();
        User user = getUser(request);
        
        maEqMstrAsmbDetailService.insertDetail(maEqMstrAsmbDetailDTO, maEqMstrCommonDTO, user);
        
        setAjaxStatus(request);
    }

    private void findSlideImage(MaEqMstrAsmbDetailForm maEqMstrAsmbDetailForm, HttpServletRequest request, HttpServletResponse response) throws IOException, InvalidKeyException, URISyntaxException, StorageException
    {
        // Service ��ü ����
    	MaEqMstrAsmbDetailService maEqMstrAsmbDetailService = (MaEqMstrAsmbDetailService)getBean("maEqMstrAsmbDetailService");

        // ��ȸ�� �� �κ�
        List list = maEqMstrAsmbDetailService.findSlideImage(maEqMstrAsmbDetailForm.getMaEqMstrAsmbDetailDTO(), getUser(request).getCompNo());

        Gson gson = new Gson();
        
        String jsonString = gson.toJson(list);

        response.getWriter().print(jsonString);
    }
    
    private void copyDetail(MaEqMstrAsmbDetailForm maEqMstrAsmbDetailForm, HttpServletRequest request, HttpServletResponse response ) throws Exception
    {
    	MaEqMstrAsmbDetailService maEqMstrAsmbDetailService = (MaEqMstrAsmbDetailService)getBean("maEqMstrAsmbDetailService");	
    	MaEqMstrAsmbDetailDTO maEqMstrAsmbDetailDTO = maEqMstrAsmbDetailForm.getMaEqMstrAsmbDetailDTO();
    	
    	MaEqMstrCommonDTO maEqMstrCommonDTO = maEqMstrAsmbDetailForm.getMaEqMstrCommonDTO();

    	String oldEquipId = maEqMstrCommonDTO.getEquipId();
    	String newEquipId = maEqMstrCommonDTO.getEquipId();
    	String oldKeyId   = maEqMstrAsmbDetailDTO.getOldEqAsmbId();
    	String newKeyId   = maEqMstrAsmbDetailDTO.getEqAsmbId();
    	
    	User user = getUser(request);
    	
    	String result = maEqMstrAsmbDetailService.copyDetail(oldEquipId, newEquipId, oldKeyId, newKeyId, user);
    	setAjaxStatus(request, result);
    }
}
