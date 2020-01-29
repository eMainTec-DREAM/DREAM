package dream.comm.revision.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.SuperAuthAction;
import dream.comm.revision.dto.CommRevHistCommonDTO;
import dream.comm.revision.dto.CommRevHistDetailDTO;
import dream.comm.revision.form.CommRevHistDetailForm;
import dream.comm.revision.service.CommRevHistDetailService;

/**
 * 설비종류 - 상세 action
 * 
 * @author jung7126
 * @version $Id: CommRevHistDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/commRevHistDetail" name="commRevHistDetailForm"
 *                input="/dream/comm/revision/hist/commRevHistDetail.jsp" scope="request"
 *                validate="false"
 */
public class CommRevHistDetailAction extends SuperAuthAction
{
	/** 제/개정 상세내용 조회 */
    public static final int COMM_REV_HIST_FIND 		= 1001;
    /** 제/개정 상세내용 저장 */
    public static final int COMM_REV_HIST_UPDATE	= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        CommRevHistDetailForm commRevHistDetailForm = (CommRevHistDetailForm) form;
        
        switch (commRevHistDetailForm.getStrutsAction())
        {
            case CommRevHistDetailAction.COMM_REV_HIST_FIND:
            	findDetail(commRevHistDetailForm, request);
                returnActionForward = mapping.getInputForward();
                break;
            case CommRevHistDetailAction.COMM_REV_HIST_UPDATE:
            	updateRevHist(commRevHistDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * Param 조회
     * @author jung7126
     * @version $Id: CommRevHistDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param commRevHistDetailForm
     */
    private void findDetail(CommRevHistDetailForm commRevHistDetailForm, HttpServletRequest request)throws Exception 
    {   
    	CommRevHistDetailService commRevHistDetailService = (CommRevHistDetailService)getBean("commRevHistDetailService");
    	
    	CommRevHistCommonDTO commRevHistCommonDTO = commRevHistDetailForm.getCommRevHistCommonDTO();
    	
    	CommRevHistDetailDTO commRevHistDetailDTO = commRevHistDetailService.findDetail(commRevHistCommonDTO, getUser(request));
    	
        commRevHistDetailForm.setCommRevHistDetailDTO(commRevHistDetailDTO);
    }
    /**
     * regislate insert
     * @author  kim21017
     * @version $Id: WorkPmListRevCreateAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param commRevHistDetailForm
     * @param request
     */
    private void updateRevHist(CommRevHistDetailForm commRevHistDetailForm, HttpServletRequest request) throws Exception
    {
    	CommRevHistDetailService commRevHistDetailService = (CommRevHistDetailService) getBean("commRevHistDetailService");
        
    	CommRevHistDetailDTO commRevHistDetailDTO = commRevHistDetailForm.getCommRevHistDetailDTO();
        
    	commRevHistDetailDTO.setEnterBy(getUser(request).getUserId());
    	commRevHistDetailDTO.setCompNo(getUser(request).getCompNo());

        commRevHistDetailService.updateRevHist(commRevHistDetailDTO);
        
        setAjaxStatus(request);
    }
}