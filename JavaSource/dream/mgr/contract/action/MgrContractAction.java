package dream.mgr.contract.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.mgr.contract.dto.MgrContractDTO;
import dream.mgr.contract.form.MgrContractForm;
import dream.mgr.contract.service.MgrContractService;

/**
 * 단가계약설정 - 목록 
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @struts:action path="/mgrContractList" name="mgrContractForm"
 *                input="/dream/mgr/contract/mgrContractList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/mgrContractDetail" name="mgrContractForm"
 *                input="/dream/mgr/contract/mgrContractDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrContractList" path="/dream/mgr/contract/mgrContractList.jsp"
 *                        redirect="false"
 * @struts.action-forward name="mgrContractDetail" path="/dream/mgr/contract/mgrContractDetail.jsp"
 *                        redirect="false"
 */
public class MgrContractAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 			= 8001;
    /** 삭제 */
    public static final int LIST_DELETE      	= 7002;
    /** 상세 조회 */
    public static final int DETAIL_INIT 		= 8002;
    /** 상세 저장 */
    public static final int DETAIL_INPUT 		= 5001;
    /** 상세 수정 */
    public static final int DETAIL_UPDATE      = 6001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MgrContractForm mgrContractForm = (MgrContractForm) form;
        
        switch (mgrContractForm.getStrutsAction())
        {
            case MgrContractAction.LIST_FIND:
                findList(request, mgrContractForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrContractAction.LIST_DELETE:
                deleteList(request, mgrContractForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MgrContractAction.DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, mgrContractForm);
                returnActionForward = mapping.getInputForward();
                break;
            case MgrContractAction.DETAIL_INPUT:
                insertDetail(mgrContractForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MgrContractAction.DETAIL_UPDATE:
            	updateDetail(mgrContractForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MgrContractAction.BASE_SET_HEADER:
                setHeader(request, response, mgrContractForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrContractAction.BASE_GRID_EXPORT:
            	findList(request, mgrContractForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MgrContractForm mgrContractForm) throws IOException
    {
        super.setHeader(request, response, mgrContractForm.getListId(),mgrContractForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param mgrContractForm
     * @param response
     * @throws Exception
     */
    private void findList(HttpServletRequest request, MgrContractForm mgrContractForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	MgrContractService mgrContractService = (MgrContractService) getBean("mgrContractService");        

    	MgrContractDTO mgrContractDTO = mgrContractForm.getMgrContractDTO();
        
        mgrContractDTO.setIsLoadMaxCount("Y".equals(mgrContractForm.getIsLoadMaxCount())?true:false);
        mgrContractDTO.setFirstRow(mgrContractForm.getFirstRow());
        mgrContractDTO.setOrderBy(mgrContractForm.getOrderBy());
        mgrContractDTO.setDirection(mgrContractForm.getDirection());
        //리스트를 조회한다.
        List resultList = mgrContractService.findList(mgrContractDTO,getUser(request));

        String totalCount = "";
        if(Integer.parseInt(mgrContractForm.getIsTotalCount()) == 0 && !excelExport) totalCount = mgrContractService.findTotalCount(mgrContractDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,mgrContractForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
	}
    
    private void findDetail(HttpServletRequest request, MgrContractForm mgrContractForm) throws Exception
    {
    	MgrContractService mgrContractService = (MgrContractService) getBean("mgrContractService");        
    	
    	MgrContractDTO mgrContractDTO = mgrContractForm.getMgrContractDTO();
    	
    	// 유저
    	User user = getUser(request);

        // 조회된 상세 부분
    	mgrContractDTO = mgrContractService.findDetail(mgrContractDTO,user);
    	
        // 조회된 상세  셋팅한다.
    	mgrContractForm.setMgrContractDTO(mgrContractDTO);

    }
    
    /**
     * delete
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param mgrContractForm
     * @throws Exception
     */
    private void deleteList(HttpServletRequest request, MgrContractForm mgrContractForm) throws Exception
    {
        MgrContractService mgrContractService = (MgrContractService) getBean("mgrContractService");
        
        String[] deleteRows = mgrContractForm.getDeleteRows();
    
        mgrContractService.deleteList(deleteRows, getUser(request));
        
        setAjaxStatus(request);
    }

    private void insertDetail(MgrContractForm mgrContractForm, HttpServletRequest request) throws Exception
    {
    	// Service 객체 생성
    	MgrContractService mgrContractService = (MgrContractService) getBean("mgrContractService");
    	
    	MgrContractDTO mgrContractDTO = mgrContractForm.getMgrContractDTO();
        
        User user = getUser(request);
        
        mgrContractService.insertDetail(mgrContractDTO, user);
        
        setAjaxStatus(request);
    }
    
    private void updateDetail(MgrContractForm mgrContractForm, HttpServletRequest request) throws Exception
    {
    	// Service 객체 생성
    	MgrContractService mgrContractService = (MgrContractService) getBean("mgrContractService");
    	
    	MgrContractDTO mgrContractDTO = mgrContractForm.getMgrContractDTO();
    	
    	User user = getUser(request);
    	
    	mgrContractService.updateDetail(mgrContractDTO, user);
    	
    	setAjaxStatus(request);
    }
}
