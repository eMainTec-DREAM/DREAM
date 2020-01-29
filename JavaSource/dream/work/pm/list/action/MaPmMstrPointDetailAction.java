package dream.work.pm.list.action;

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
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.MaPmMstrPointDetailDTO;
import dream.work.pm.list.form.MaPmMstrPointDetailForm;
import dream.work.pm.list.service.MaPmMstrPointDetailService;

/**
 * �۾���� - �˻��׸�
 * @author  jung7126
 * @version $Id: MaPmMstrPointDetailAction.java,v 1.0 2015/12/04 09:09:30 jung7126 Exp $
 * @since   1.0
 * @struts:action path="/maPmMstrPointDetail" name="maPmMstrPointDetailForm"
 *                input="/dream/work/pm/list/maPmMstrPointDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maPmMstrPointValueDetail" name="maPmMstrPointDetailForm"
 *                input="/dream/work/pm/list/maPmMstrPointValueDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workPmListPtrlRtPointDetail" name="maPmMstrPointDetailForm"
 *                input="/dream/work/pm/list/workPmListPtrlRtPointDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maEqMstrPmInsPointDetail" name="maPmMstrPointDetailForm"
 *                input="/dream/asset/list/maEqMstrPmInsPointDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maEqMstrPmInsPointValueDetail" name="maPmMstrPointDetailForm"
 *                input="/dream/asset/list/maEqMstrPmInsPointValueDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPmMstrPointDetail" path="/dream/work/pm/list/maPmMstrPointDetail.jsp"
 *                        redirect="false"
 */
public class MaPmMstrPointDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int PM_MSTR_POINT_DETAIL_INIT 	= 1001;
    /** ���� */
    public static final int PM_MSTR_POINT_DETAIL_UPDATE = 1002;
    /** �Է� */
    public static final int PM_MSTR_POINT_DETAIL_INPUT 	= 1003;
    /** Slide Image ��ȸ */
    public static final int DETAIL_PHOTO        		= 8001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPmMstrPointDetailForm maPmMstrPointDetailForm = (MaPmMstrPointDetailForm) form;
        
        super.updateAudit(maPmMstrPointDetailForm.getMaPmMstrPointDetailDTO().getAuditKey()==""?maPmMstrPointDetailForm.getMaPmMstrCommonDTO().getAuditKey():maPmMstrPointDetailForm.getMaPmMstrPointDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maPmMstrPointDetailForm.getStrutsAction())
        {
            case MaPmMstrPointDetailAction.PM_MSTR_POINT_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maPmMstrPointDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case MaPmMstrPointDetailAction.PM_MSTR_POINT_DETAIL_UPDATE:
            	updateDetail(maPmMstrPointDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPmMstrPointDetailAction.PM_MSTR_POINT_DETAIL_INPUT:
            	insertDetail(maPmMstrPointDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPmMstrPointDetailAction.DETAIL_PHOTO:
                findSlideImage(request, response, maPmMstrPointDetailForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * �۾����-�˻��׸� �� ��ȸ
     * @author kim2107
     * @version $Id: MaPmMstrPointDetailAction.java,v 1.2 2015/12/02 01:21:30 jung7126 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maPmMstrPointDetailForm
     */
    private void findDetail(HttpServletRequest request, MaPmMstrPointDetailForm maPmMstrPointDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaPmMstrPointDetailService maPmMstrPointDetailService = (MaPmMstrPointDetailService)getBean("maPmMstrPointDetailService");
    	MaPmMstrPointDetailDTO maPmMstrPointDetailDTO = maPmMstrPointDetailForm.getMaPmMstrPointDetailDTO();
        MaPmMstrCommonDTO maPmMstrMstrCommonDTO = maPmMstrPointDetailForm.getMaPmMstrCommonDTO();
    	// �۾����Id ����
        String wkOrId = maPmMstrPointDetailForm.getMaPmMstrCommonDTO().getPmId();
        // �Ѱ��� �����׸�id ����
        String pmPointId = maPmMstrPointDetailForm.getMaPmMstrCommonDTO().getPmPointId();

        // ��ȸ�� �� �κ�
        maPmMstrPointDetailDTO = maPmMstrPointDetailService.findDetail(wkOrId, pmPointId, getUser(request));
        // ��ȸ�� ��  �����Ѵ�.
        maPmMstrPointDetailForm.setMaPmMstrPointDetailDTO(maPmMstrPointDetailDTO);
        
        request.setAttribute("slideFileList", maPmMstrPointDetailDTO.getSlideFileList());
    }
    /**
     * detail update
     * @author  jung7126
     * @version $Id: MaPmMstrPointDetailAction.java,v 1.2 2015/12/02 01:21:30 jung7126 Exp $
     * @since   1.0
     * 
     * @param maPmMstrPointDetailForm
     * @param request
     */
    private void updateDetail(MaPmMstrPointDetailForm maPmMstrPointDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPmMstrPointDetailService maPmMstrPointDetailService = (MaPmMstrPointDetailService) getBean("maPmMstrPointDetailService");
        
        MaPmMstrPointDetailDTO maPmMstrPointDetailDTO = maPmMstrPointDetailForm.getMaPmMstrPointDetailDTO();
        MaPmMstrCommonDTO maPmMstrMstrCommonDTO = maPmMstrPointDetailForm.getMaPmMstrCommonDTO();
        maPmMstrPointDetailDTO.setEnterBy(getUser(request).getUserId());
        maPmMstrMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maPmMstrPointDetailService.updateDetail(maPmMstrPointDetailDTO,maPmMstrMstrCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  jung7126
     * @version $Id: MaPmMstrPointDetailAction.java,v 1.2 2015/12/02 01:21:30 jung7126 Exp $
     * @since   1.0
     * 
     * @param maPmMstrPointDetailForm
     * @param request
     */
    private void insertDetail(MaPmMstrPointDetailForm maPmMstrPointDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPmMstrPointDetailService maPmMstrPointDetailService = (MaPmMstrPointDetailService) getBean("maPmMstrPointDetailService");
        
        MaPmMstrPointDetailDTO maPmMstrPointDetailDTO = maPmMstrPointDetailForm.getMaPmMstrPointDetailDTO();
        
        MaPmMstrCommonDTO maPmMstrMstrCommonDTO = maPmMstrPointDetailForm.getMaPmMstrCommonDTO();
        maPmMstrPointDetailDTO.setEnterBy(getUser(request).getUserId());
        maPmMstrMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maPmMstrPointDetailService.insertDetail(maPmMstrPointDetailDTO, maPmMstrMstrCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * ���� �����̵� �̹���
     * @author  js.lee
     * @version $Id: Exp $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param maPmMstrPointDetailForm
     * @throws IOException
     * @throws StorageException 
     * @throws URISyntaxException 
     * @throws InvalidKeyException 
     */
    private void findSlideImage(HttpServletRequest request, HttpServletResponse response, MaPmMstrPointDetailForm maPmMstrPointDetailForm) throws IOException, InvalidKeyException, URISyntaxException, StorageException
    {
        // Service ��ü ����
    	MaPmMstrPointDetailService maPmMstrPointDetailService = (MaPmMstrPointDetailService) getBean("maPmMstrPointDetailService");
        
        // ��ȸ�� �� �κ�
        List list = maPmMstrPointDetailService.findSlideImage(maPmMstrPointDetailForm.getMaPmMstrPointDetailDTO(), getUser(request).getCompNo());

        Gson gson = new Gson();
        
        String jsonString = gson.toJson(list);

        response.getWriter().print(jsonString);
    }
}