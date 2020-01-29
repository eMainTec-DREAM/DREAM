package dream.mgr.ptwh.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.mgr.ptwh.dto.MgrPtWhBinDetailDTO;
import dream.mgr.ptwh.dto.MgrPtWhBinListDTO;
import dream.mgr.ptwh.form.MgrPtWhBinDetailForm;
import dream.mgr.ptwh.service.MgrPtWhBinDetailService;

/**
 * ��ǰâ�� ������ġ - Detail Action
 * 
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/mgrPtWhBinDetail" name="mgrPtWhBinDetailForm"
 *                input="/dream/mgr/ptwh/mgrPtWhBinDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrPtWhBinDetail" path="/dream/mgr/ptwh/mgrPtWhBinDetail.jsp"
 *                        redirect="false"
 */
public class MgrPtWhBinDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int DETAIL_INIT 		= 8001;
    /** ���� */
    public static final int DETAIL_INPUT 		= 5002;
    /** ���� */
    public static final int DETAIL_UPDATE 		= 6003;
    /** �ߺ� üũ */
    public static final int DETAIL_CHECK		= 1004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MgrPtWhBinDetailForm mgrPtWhBinDetailForm = (MgrPtWhBinDetailForm) form;
        
        super.updateAudit(mgrPtWhBinDetailForm.getMgrPtWhBinDetailDTO().getAuditKey()==""?mgrPtWhBinDetailForm.getMgrPtWhBinListDTO().getAuditKey():mgrPtWhBinDetailForm.getMgrPtWhBinDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (mgrPtWhBinDetailForm.getStrutsAction())
        {
            case MgrPtWhBinDetailAction.DETAIL_INIT:
                this.findDetail(request, response, mgrPtWhBinDetailForm);
                returnActionForward = mapping.findForward("mgrPtWhBinDetail");
                break;
            case MgrPtWhBinDetailAction.DETAIL_INPUT:
            	insertDetail(request, response, mgrPtWhBinDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MgrPtWhBinDetailAction.DETAIL_UPDATE:
            	updateDetail(request, response, mgrPtWhBinDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MgrPtWhBinDetailAction.DETAIL_CHECK:
            	validEmpNo(mgrPtWhBinDetailForm, request);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("mgrPtWhBinDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * ��ǰâ�� ������ġ FIND DETAIL
     * @author cjscjs9
     * @param request
     * @param response
     * @param mgrPtWhBinDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, MgrPtWhBinDetailForm mgrPtWhBinDetailForm) throws Exception 
    {   
    	MgrPtWhBinDetailService mgrPtWhBinDetailService = (MgrPtWhBinDetailService)getBean("mgrPtWhBinDetailService");
    	
    	MgrPtWhBinListDTO mgrPtWhBinListDTO = mgrPtWhBinDetailForm.getMgrPtWhBinListDTO(); 

    	User user = getUser(request);
    	
    	MgrPtWhBinDetailDTO mgrPtWhBinDetailDTO = mgrPtWhBinDetailService.findPtWhEmpDetail(mgrPtWhBinListDTO, user);
    	mgrPtWhBinDetailForm.setMgrPtWhBinDetailDTO(mgrPtWhBinDetailDTO);
    }

    /**
     * ��ǰâ�� ������ġ INSERT DETAIL
     * @author cjscjs9
     * @param request
     * @param response
     * @param mgrPtWhBinDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, MgrPtWhBinDetailForm mgrPtWhBinDetailForm) throws Exception
    {
    	MgrPtWhBinDetailService mgrPtWhBinDetailService = (MgrPtWhBinDetailService)getBean("mgrPtWhBinDetailService");
    	MgrPtWhBinDetailDTO  mgrPtWhBinDetailDTO = mgrPtWhBinDetailForm.getMgrPtWhBinDetailDTO(); 
    	
    	User user = getUser(request);
    	
    	mgrPtWhBinDetailService.insertPtWhEmpDetail(mgrPtWhBinDetailDTO, user);
        setAjaxStatus(request);
    }

    /**
     * ��ǰâ�� ������ġ UPDATE DETAIL
     * @author cjscjs9
     * @param request
     * @param response
     * @param mgrPtWhBinDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, MgrPtWhBinDetailForm mgrPtWhBinDetailForm) throws Exception
    {
    	MgrPtWhBinDetailService mgrPtWhBinDetailService = (MgrPtWhBinDetailService)getBean("mgrPtWhBinDetailService");
    	MgrPtWhBinDetailDTO  mgrPtWhBinDetailDTO = mgrPtWhBinDetailForm.getMgrPtWhBinDetailDTO();
    	
    	User user = getUser(request);
    	
    	mgrPtWhBinDetailService.updatePtWhEmpDetail(mgrPtWhBinDetailDTO, user);
        
        setAjaxStatus(request);
    }
    
    /**
     * ��ǰâ�� ������ġ �ߺ�üũ (valid emp id)  
     * @author cjscjs9
     * @version $Id: $
     * @since   1.0
     * 
     * @param maEmpDetailForm
     * @param request
     */
    private void validEmpNo(MgrPtWhBinDetailForm mgrPtWhBinDetailForm, HttpServletRequest request) throws Exception
    {
    	MgrPtWhBinDetailService mgrPtWhBinDetailService = (MgrPtWhBinDetailService)getBean("mgrPtWhBinDetailService");
    	MgrPtWhBinListDTO mgrPtWhBinListDTO = mgrPtWhBinDetailForm.getMgrPtWhBinListDTO();
    	MgrPtWhBinDetailDTO mgrPtWhBinDetailDTO = mgrPtWhBinDetailForm.getMgrPtWhBinDetailDTO();
    	mgrPtWhBinDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        String isValid = mgrPtWhBinDetailService.validEmpNo(mgrPtWhBinListDTO, mgrPtWhBinDetailDTO, getUser(request));
        
        setAjaxDesc(request, isValid);
    }

}