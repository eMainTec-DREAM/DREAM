package dream.part.rec.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.part.rec.dto.MaPtRecSerialDetailDTO;
import dream.part.rec.dto.MaPtRecCommonDTO;
import dream.part.rec.dto.MaPtRecSerialListDTO;
import dream.part.rec.form.MaPtRecSerialDetailForm;
import dream.part.rec.service.MaPtRecSerialDetailService;
import dream.work.list.action.MaWoResultCraftDetailAction;
import dream.work.list.dto.MaWoResultCraftDetailDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.form.MaWoResultCraftDetailForm;
import dream.work.list.service.MaWoResultCraftDetailService;

/**
 * �����԰� item ��
 * @author  kim21017
 * @version $Id: MaPtRecSerialDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maPtRecSerialDetail" name="maPtRecSerialDetailForm"
 *                input="/dream/part/rec/maPtRecSerialDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPtRecSerialDetail" path="/dream/part/rec/maPtRecSerialDetail.jsp"
 *                        redirect="false"
 */
public class MaPtRecSerialDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int BUY_ITEM_DETAIL_INIT 		= 1001;
    /** ���� */
    public static final int BUY_ITEM_DETAIL_UPDATE 		= 1002;
    /** �Է� */
    public static final int BUY_ITEM_DETAIL_INPUT 		= 1003;
    /** �Է� */
    public static final int BUY_ITEM_DETAIL_CHECK 		= 1004;
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
    	
        ActionForward returnActionForward = null;
        MaPtRecSerialDetailForm maPtRecSerialDetailForm = (MaPtRecSerialDetailForm) form;
        switch (maPtRecSerialDetailForm.getStrutsAction())
        {
            case MaPtRecSerialDetailAction.BUY_ITEM_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maPtRecSerialDetailForm);
                returnActionForward = mapping.findForward("maPtRecSerialDetail");
                break;
            case MaPtRecSerialDetailAction.BUY_ITEM_DETAIL_UPDATE:
            	updateDetail(maPtRecSerialDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPtRecSerialDetailAction.BUY_ITEM_DETAIL_INPUT:
            	insertDetail(maPtRecSerialDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPtRecSerialDetailAction.BUY_ITEM_DETAIL_CHECK:
            	validSerial(maPtRecSerialDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maPtRecSerialDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * �����԰� item ��ȸ
     * @author kim2107
     * @version $Id: MaPtRecSerialDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maPtRecSerialDetailForm
     */
    private void findDetail(HttpServletRequest request, MaPtRecSerialDetailForm maPtRecSerialDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaPtRecSerialDetailService maPtRecSerialDetailService = (MaPtRecSerialDetailService)getBean("maPtRecSerialDetailService");
    	MaPtRecCommonDTO maPtRecCommonDTO = maPtRecSerialDetailForm.getMaPtRecCommonDTO();
    	MaPtRecSerialListDTO maPtRecSerialListDTO = maPtRecSerialDetailForm.getMaPtRecSerialListDTO();
    	maPtRecCommonDTO.setCompNo(getUser(request).getCompNo());
    	maPtRecCommonDTO.setUserLang(getUser(request).getLangId());
        // ��ȸ�� �� �κ�
        MaPtRecSerialDetailDTO maPtRecSerialDetailDTO = maPtRecSerialDetailService.findDetail(maPtRecSerialListDTO,maPtRecCommonDTO);
        
        // ��ȸ�� ��  �����Ѵ�.
        maPtRecSerialDetailForm.setMaPtRecSerialDetailDTO(maPtRecSerialDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaPtRecSerialDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPtRecSerialDetailForm
     * @param request
     */
    private void updateDetail(MaPtRecSerialDetailForm maPtRecSerialDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPtRecSerialDetailService maPtRecSerialDetailService = (MaPtRecSerialDetailService) getBean("maPtRecSerialDetailService");
        
        MaPtRecSerialDetailDTO maPtRecSerialDetailDTO = maPtRecSerialDetailForm.getMaPtRecSerialDetailDTO();
        MaPtRecCommonDTO maPtRecCommonDTO = maPtRecSerialDetailForm.getMaPtRecCommonDTO();
    	maPtRecCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maPtRecSerialDetailService.updateDetail(maPtRecSerialDetailDTO,maPtRecCommonDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaPtRecSerialDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPtRecSerialDetailForm
     * @param request
     */
    private void insertDetail(MaPtRecSerialDetailForm maPtRecSerialDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPtRecSerialDetailService maPtRecSerialDetailService = (MaPtRecSerialDetailService) getBean("maPtRecSerialDetailService");
        
        MaPtRecSerialDetailDTO maPtRecSerialDetailDTO = maPtRecSerialDetailForm.getMaPtRecSerialDetailDTO();
        
        MaPtRecCommonDTO maPtRecCommonDTO = maPtRecSerialDetailForm.getMaPtRecCommonDTO();
    	maPtRecCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maPtRecSerialDetailService.insertDetail(maPtRecSerialDetailDTO, maPtRecCommonDTO);
        
        setAjaxStatus(request);
    }
    /**
     * ��� �ߺ� �˻�
     */
    private void validSerial(MaPtRecSerialDetailForm maPtRecSerialDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPtRecSerialDetailService maPtRecSerialDetailService = (MaPtRecSerialDetailService) getBean("maPtRecSerialDetailService");
        
    	MaPtRecSerialDetailDTO maPtRecSerialDetailDTO = maPtRecSerialDetailForm.getMaPtRecSerialDetailDTO();
        
    	MaPtRecCommonDTO maPtRecCommonDTO = maPtRecSerialDetailForm.getMaPtRecCommonDTO();
        String isValid = maPtRecSerialDetailService.validSerial(maPtRecSerialDetailDTO,maPtRecCommonDTO, getUser(request));
        setAjaxDesc(request, isValid);
    }
}