package dream.tool.list.action;

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

import common.struts.AuthAction;
import dream.tool.list.dto.MaPttMstrDetailDTO;
import dream.tool.list.form.MaPttMstrDetailForm;
import dream.tool.list.service.MaPttMstrDetailService;

/**
 * ��������з�(������) - �� action
 * 
 * @author 
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/maPttMstrDetail" name="maPttMstrDetailForm"
 *                input="/dream/tool/list/maPttMstrDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPttMstrDetail" path="/dream/tool/list/maPttMstrDetail.jsp"
 *                        redirect="false"
 */
public class MaPttMstrDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int PTMSTR_DETAIL_INIT 		    = 8001;
    /** ���� */
    public static final int PTMSTR_DETAIL_INPUT 		= 5002;
    /** ���� */
    public static final int PTMSTR_DETAIL_UPDATE 		= 6003;
    /** �ߺ� üũ */
    public static final int PTMSTR_DETAIL_CHECK 		= 1004;
    
    public static final int PTMSTR_DETAIL_PHOTO         = 8005;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPttMstrDetailForm maPttMstrDetailForm = (MaPttMstrDetailForm) form;
        
        super.updateAudit(maPttMstrDetailForm.getMaPttMstrDetailDTO().getAuditKey()==""?maPttMstrDetailForm.getMaPttMstrCommonDTO().getAuditKey():maPttMstrDetailForm.getMaPttMstrDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maPttMstrDetailForm.getStrutsAction())
        {
            case MaPttMstrDetailAction.PTMSTR_DETAIL_INIT:
                this.findDetail(request, maPttMstrDetailForm);
                returnActionForward = mapping.findForward("maPttMstrDetail");
                break;
            case MaPttMstrDetailAction.PTMSTR_DETAIL_INPUT:
            	insertDetail(maPttMstrDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPttMstrDetailAction.PTMSTR_DETAIL_UPDATE:
            	updateDetail(maPttMstrDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPttMstrDetailAction.PTMSTR_DETAIL_CHECK:
            	validDeptNo(maPttMstrDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPttMstrDetailAction.PTMSTR_DETAIL_PHOTO:
                findSlideImage(maPttMstrDetailForm, request, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.findForward("maPttMstrDetail");
                break;
        }

        return returnActionForward;
    }
    
    private void findSlideImage(MaPttMstrDetailForm maPttMstrDetailForm, HttpServletRequest request, HttpServletResponse response) throws IOException, InvalidKeyException, URISyntaxException, StorageException
    {
        MaPttMstrDetailService maPttMstrDetailService = (MaPttMstrDetailService)getBean("maPttMstrDetailService");

        // ��ȸ�� �� �κ�
        List  list = maPttMstrDetailService.findSlideImage(maPttMstrDetailForm.getMaPttMstrCommonDTO(), getUser(request));
 
        Gson gson = new Gson();
        
        String jsonString = gson.toJson(list);

        response.getWriter().print(jsonString);
        
    }

    /**
     * ��������з�(������) �� ��ȸ
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maPttMstrDetailForm
     */
    private void findDetail(HttpServletRequest request, MaPttMstrDetailForm maPttMstrDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaPttMstrDetailService maPttMstrDetailService = (MaPttMstrDetailService)getBean("maPttMstrDetailService");

        // �Ѱ��� �μ��ڵ� ����
//        String compNo = maPttMstrDetailForm.getMaPttMstrCommonDTO().getCompNo();
//        String partId = maPttMstrDetailForm.getMaPttMstrCommonDTO().getPartId();
        
        // ��ȸ�� �� �κ�
        MaPttMstrDetailDTO maPttMstrDetailDTO = maPttMstrDetailService.findDetail(maPttMstrDetailForm.getMaPttMstrCommonDTO(), getUser(request));
        
        List photoList = maPttMstrDetailService.getPhotoList(maPttMstrDetailForm.getMaPttMstrCommonDTO(), getUser(request));
        
        request.setAttribute("slideFileList",photoList);
        // ��ȸ�� ��  �����Ѵ�.
        maPttMstrDetailForm.setMaPttMstrDetailDTO(maPttMstrDetailDTO);
    }
    
    
    
    /**
     * detail insert
     * @author  
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPttMstrDetailForm
     * @param request
     */
    private void insertDetail(MaPttMstrDetailForm maPttMstrDetailForm, HttpServletRequest request) throws Exception
    {
        MaPttMstrDetailService maPttMstrDetailService = (MaPttMstrDetailService) getBean("maPttMstrDetailService");
        
        MaPttMstrDetailDTO maPttMstrDetailDTO = maPttMstrDetailForm.getMaPttMstrDetailDTO();
        
        maPttMstrDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        maPttMstrDetailService.insertDetail(maPttMstrDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPttMstrDetailForm
     * @param request
     */
    private void updateDetail(MaPttMstrDetailForm maPttMstrDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPttMstrDetailService maPttMstrDetailService = (MaPttMstrDetailService) getBean("maPttMstrDetailService");
        
        MaPttMstrDetailDTO maPttMstrDetailDTO = maPttMstrDetailForm.getMaPttMstrDetailDTO();
        
        maPttMstrDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        maPttMstrDetailService.updateDetail(maPttMstrDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * valid dept no
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPttMstrDetailForm
     * @param request
     */
    private void validDeptNo(MaPttMstrDetailForm maPttMstrDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPttMstrDetailService maPttMstrDetailService = (MaPttMstrDetailService) getBean("maPttMstrDetailService");
        
        MaPttMstrDetailDTO maPttMstrDetailDTO = maPttMstrDetailForm.getMaPttMstrDetailDTO();
        
        maPttMstrDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        String isValid = maPttMstrDetailService.validDeptNo(maPttMstrDetailDTO, getUser(request));
        
        setAjaxDesc(request, isValid);
    }
    
}