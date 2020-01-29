package dream.comm.revision.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.SuperAuthAction;
import dream.comm.revision.dto.CommRevCommonDTO;
import dream.comm.revision.form.CommRevForm;
import dream.comm.revision.service.CommRevService;

/**
 * 설비종류 - 상세 action
 * 
 * @author jung7126
 * @version $Id: CommRevAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/workPmListRevCreate" name="commRevForm"
 *                input="/dream/work/pm/list/workPmListRevCreate.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workPmListRevUpdate" name="commRevForm"
 *                input="/dream/work/pm/list/workPmListRevUpdate.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/assetListRegislate" name="commRevForm"
 *                input="/dream/asset/list/assetListRegislate.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/assetListRevision" name="commRevForm"
 *                input="/dream/asset/list/assetListRevision.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/commRevRegislate" name="commRevForm"
 *                input="/dream/comm/revision/commRevRegislate.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/commEqToolRevRegislate" name="commRevForm"
 *                input="/dream/comm/revision/commEqToolRevRegislate.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/commRevRevision" name="commRevForm"
 *                input="/dream/comm/revision/commRevRevision.jsp" scope="request"
 *                validate="false"
 */
public class CommRevAction extends SuperAuthAction
{
	/** 제정 페이지 */
    public static final int COMM_REV_REGISLATE_INIT 		= 1001;
    /** 제정 저장 */
    public static final int COMM_REV_REGISLATE_INPUT 		= 1002;
    /** 제정 완료 */
    public static final int COMM_REV_REGISLATE_COMPLETE 	= 1003;
    /** 현재 최대 Revision 번호 가져오기 */
    public static final int MAX_REV_NO 						= 1004;
    /** 개정 페이지 */
    public static final int COMM_REV_REVISION_INIT 			= 2001;
    /** 개정 저장 */
    public static final int COMM_REV_REVISION_INPUT 		= 2002;
    /** 개정 완료 */
    public static final int COMM_REV_REVISION_COMPLETE 		= 2003;
    /** REV Valid Check */
    public static final int CHECK_REV_NO 					= 3001;
    /** EQ Valid Check */
    public static final int CHECK_OBJECT_NO 				= 3002;
    /** GET CUSTOM ITEM NO */
    public static final int COMM_REV_CUSTOM_OBJECT_NO		= 4002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        CommRevForm commRevForm = (CommRevForm) form;
        
        switch (commRevForm.getStrutsAction())
        {
	        case CommRevAction.COMM_REV_REGISLATE_INIT:
	            returnActionForward = mapping.getInputForward();
	            break;
	        case CommRevAction.COMM_REV_REGISLATE_INPUT:
	        	insertRegislate(commRevForm, request);
	            returnActionForward = mapping.findForward("ajaxXmlVal");
	        	break;
	        case CommRevAction.COMM_REV_REGISLATE_COMPLETE:
            	completeRegislate(commRevForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case CommRevAction.MAX_REV_NO :
                maxRevNo(commRevForm, request);  
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case CommRevAction.COMM_REV_REVISION_INIT:
            	findDetail(commRevForm, request);
                returnActionForward = mapping.getInputForward();
                break;
            case CommRevAction.COMM_REV_REVISION_INPUT:
            	insertRevision(commRevForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case CommRevAction.COMM_REV_REVISION_COMPLETE:
            	completeRevision(commRevForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case CommRevAction.CHECK_REV_NO:
            	validRevNo(commRevForm, request);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case CommRevAction.CHECK_OBJECT_NO:
            	validObjectNo(commRevForm, request);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case CommRevAction.COMM_REV_CUSTOM_OBJECT_NO:
            	selectCustomObjectNo(commRevForm, request);
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
     * @version $Id: CommRevAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param commRevForm
     */
    private void findDetail(CommRevForm commRevForm, HttpServletRequest request)throws Exception 
    {   
    	CommRevService commRevService = (CommRevService)getBean("commRevService");

        CommRevCommonDTO commRevCommonDTO = commRevForm.getCommRevCommonDTO();
        
        commRevCommonDTO = commRevService.findDetail(commRevCommonDTO, getUser(request));
        
        commRevForm.setCommRevCommonDTO(commRevCommonDTO);
    }
    /**
     * regislate insert
     * @author  kim21017
     * @version $Id: WorkPmListRevCreateAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param commRevForm
     * @param request
     */
    private void insertRegislate(CommRevForm commRevForm, HttpServletRequest request) throws Exception
    {
    	CommRevService commRevService = (CommRevService) getBean("commRevService",request);
        
    	CommRevCommonDTO commRevCommonDTO = commRevForm.getCommRevCommonDTO();
        
    	commRevCommonDTO.setEnterBy(getUser(request).getUserId());
    	commRevCommonDTO.setCompNo(getUser(request).getCompNo());
    	
        commRevService.insertRegislate(commRevCommonDTO,getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * revision insert
     * @author  kim21017
     * @version $Id: CommRevAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param commRevForm
     * @param request
     */
    private void insertRevision(CommRevForm commRevForm, HttpServletRequest request) throws Exception
    {
        CommRevService commRevService = (CommRevService) getBean("commRevService");
        
        CommRevCommonDTO commRevCommonDTO = commRevForm.getCommRevCommonDTO();
        
        commRevCommonDTO.setEnterBy(getUser(request).getUserId());
        commRevCommonDTO.setCompNo(getUser(request).getCompNo());
        
    	commRevService.insertRevision(commRevCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * Regislate Complete
     * @author  kim21017
     * @version $Id: CommRevAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPmMstrDetailForm
     * @param request
     */
    private void completeRegislate(CommRevForm commRevForm, HttpServletRequest request) throws Exception
    {
    	CommRevService commRevService = (CommRevService) getBean("commRevService");
        
    	CommRevCommonDTO commRevCommonDTO = commRevForm.getCommRevCommonDTO();
        
    	commRevCommonDTO.setEnterBy(getUser(request).getUserId());
    	commRevCommonDTO.setCompNo(getUser(request).getCompNo());
        
    	commRevService.completeRegislate(commRevCommonDTO ,getUser(request));
        
        setAjaxStatus(request);
    }
    
    private void maxRevNo(CommRevForm commRevForm, HttpServletRequest request)
    {
    	CommRevService commRevService = (CommRevService) getBean("commRevService");

    	CommRevCommonDTO commRevCommonDTO = commRevForm.getCommRevCommonDTO();
    	
    	commRevCommonDTO.setCompNo((getUser(request).getCompNo()));
        
        String resultStr = commRevService.maxRevNo(commRevCommonDTO, getUser(request));

        setAjaxDesc(request, resultStr);
	}
    
    /**
     * Revision Complete
     * @author  kim21017
     * @version $Id: CommRevAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPmMstrDetailForm
     * @param request
     */
    private void completeRevision(CommRevForm commRevForm, HttpServletRequest request) throws Exception
    {
    	CommRevService commRevService = (CommRevService) getBean("commRevService");
        
    	CommRevCommonDTO commRevCommonDTO = commRevForm.getCommRevCommonDTO();
    	
    	String isValid = commRevService.completeRevision(commRevCommonDTO, getUser(request));
        
    	setAjaxDesc(request, isValid);
    }
    
    private void validRevNo(CommRevForm commRevForm, HttpServletRequest request) throws Exception
    {
    	CommRevService commRevService = (CommRevService) getBean("commRevService");
        
    	CommRevCommonDTO commRevCommonDTO = commRevForm.getCommRevCommonDTO();
    	
        String isValid = commRevService.validRevNo(commRevCommonDTO, getUser(request));
        setAjaxDesc(request, isValid);
    }
    private void validObjectNo(CommRevForm commRevForm, HttpServletRequest request) throws Exception
    {
    	CommRevService commRevService = (CommRevService) getBean("commRevService");
        
    	CommRevCommonDTO commRevCommonDTO = commRevForm.getCommRevCommonDTO();
    	
        String isValid = commRevService.validObjectNo(commRevCommonDTO, getUser(request));
        setAjaxDesc(request, isValid);
    }
    private void selectCustomObjectNo(CommRevForm commRevForm, HttpServletRequest request) throws Exception
    {
    	CommRevService commRevService = (CommRevService) getBean("commRevService",request);
        
    	CommRevCommonDTO commRevCommonDTO = commRevForm.getCommRevCommonDTO();
    	
        String customItemNo = commRevService.selectCustomObjectNo(commRevCommonDTO, getUser(request));
        setAjaxDesc(request, customItemNo);
    }
}