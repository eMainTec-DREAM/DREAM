package dream.asset.loc.run.action;


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
import dream.asset.loc.run.dto.MaLineRunPlanDTO;
import dream.asset.loc.run.form.MaLineRunPlanForm;
import dream.asset.loc.run.service.MaLineRunPlanService;

/**
 * 라인가동계획 - 목록 action
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/maLineRunPlanList" name="maLineRunPlanForm"
 *                input="/dream/asset/loc/run/maLineRunPlanList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maLineRunPlanDetail" name="maLineRunPlanForm"
 *                input="/dream/asset/loc/run/maLineRunPlanDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maLineRunPlanList" path="/dream/asset/loc/run/maLineRunPlanList.jsp"
 *                        redirect="false"
 * @struts.action-forward name="maLineRunPlanDetail" path="/dream/asset/loc/run/maLineRunPlanDetail.jsp"
 *                        redirect="false"
 */
public class MaLineRunPlanAction extends AuthAction
{
    /** 목록 조회 */
    public static final int LINE_RUN_PLAN_LIST_FIND 		= 1001;
    /** 목록 삭제 */
    public static final int LINE_RUN_PLAN_LIST_DELETE 		= 7002;
    /** 상세 조회 */
    public static final int LINE_RUN_PLAN_DETAIL_INIT 		= 8001;
    /** 상세 저장 */
    public static final int LINE_RUN_PLAN_DETAIL_INPUT 	    = 5002;
    /** 상세 수정 */
    public static final int LINE_RUN_PLAN_DETAIL_UPDATE 	= 6003;
    /** 상세 중복 체크 */
    public static final int LINE_RUN_PLAN_DETAIL_CHECK 	    = 1004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaLineRunPlanForm maLineRunPlanForm = (MaLineRunPlanForm) form;
        
        switch (maLineRunPlanForm.getStrutsAction())
        {
            case MaLineRunPlanAction.BASE_SET_HEADER:
                setHeader(request, response, maLineRunPlanForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaLineRunPlanAction.LINE_RUN_PLAN_LIST_FIND:
                findList(request, response, maLineRunPlanForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case MaLineRunPlanAction.LINE_RUN_PLAN_LIST_DELETE:
            	deleteList(request, maLineRunPlanForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case MaLineRunPlanAction.LINE_RUN_PLAN_DETAIL_INIT:
                findDetail(request, maLineRunPlanForm);
                returnActionForward = mapping.findForward("maLineRunPlanDetail");
                break;
            case MaLineRunPlanAction.LINE_RUN_PLAN_DETAIL_INPUT:
            	insertDetail(maLineRunPlanForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaLineRunPlanAction.LINE_RUN_PLAN_DETAIL_UPDATE:
            	updateDetail(maLineRunPlanForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaLineRunPlanAction.LINE_RUN_PLAN_DETAIL_CHECK:
                validLineRunPlan(maLineRunPlanForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            
            case MaLineRunPlanAction.BASE_GRID_EXPORT:
            	findList(request, response, maLineRunPlanForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaLineRunPlanForm maLineRunPlanForm) throws IOException
    {
        super.setHeader(request, response, maLineRunPlanForm.getListId(), maLineRunPlanForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maLineRunPlanForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, MaLineRunPlanForm maLineRunPlanForm, boolean excelExport) throws IOException
    {
    	MaLineRunPlanService maLineRunPlanService = (MaLineRunPlanService) getBean("maLineRunPlanService");        

    	MaLineRunPlanDTO maLineRunPlanDTO = maLineRunPlanForm.getMaLineRunPlanDTO();

    	//comp_no 를 셋팅한다.
    	maLineRunPlanDTO.setCompNo((getUser(request).getCompNo()));
    	
    	//Paging
    	maLineRunPlanDTO.setIsLoadMaxCount("Y".equals(maLineRunPlanForm.getIsLoadMaxCount())?true:false);
    	maLineRunPlanDTO.setFirstRow(maLineRunPlanForm.getFirstRow());
    	maLineRunPlanDTO.setOrderBy(maLineRunPlanForm.getOrderBy());
    	maLineRunPlanDTO.setDirection(maLineRunPlanForm.getDirection());
        
    	User user = getUser(request);
    	
        //리스트를 조회한다.
        List resultList = maLineRunPlanService.findList(maLineRunPlanDTO, user);
      
        //Paging
        String totalCount = "";
                
        if(Integer.parseInt(maLineRunPlanForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maLineRunPlanService.findTotalCount(maLineRunPlanDTO,getUser(request));
                
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, maLineRunPlanForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * delete
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param maLineRunPlanForm
     * @param request
     */
    private void deleteList(HttpServletRequest request, MaLineRunPlanForm maLineRunPlanForm) throws Exception
    {
    	MaLineRunPlanService maLineRunPlanService = (MaLineRunPlanService) getBean("maLineRunPlanService");        

        String[] deleteRows = maLineRunPlanForm.getDeleteRows();    // sheet 내역
        
        maLineRunPlanService.deleteList(deleteRows, getUser(request));
        
        setAjaxStatus(request);
    }
    

    /**
     * 라인가동계획 상세 조회
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maLineRunPlanForm
     */
    private void findDetail(HttpServletRequest request, MaLineRunPlanForm maLineRunPlanForm)throws Exception 
    {   
        // Service 객체 생성
    	MaLineRunPlanService maLineRunPlanService = (MaLineRunPlanService)getBean("maLineRunPlanService");

    	MaLineRunPlanDTO maLineRunPlanDTO = maLineRunPlanForm.getMaLineRunPlanDTO();

    	// 유저
    	User user = getUser(request);
    	
        // 조회된 상세 부분
        maLineRunPlanDTO = maLineRunPlanService.findDetail(maLineRunPlanDTO, user);
        
        // 조회된 상세  셋팅한다.
        maLineRunPlanForm.setMaLineRunPlanDTO(maLineRunPlanDTO);
    }
    
    /**
     * detail insert
     * @author  
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maLineRunPlanForm
     * @param request
     */
    private void insertDetail(MaLineRunPlanForm maLineRunPlanForm, HttpServletRequest request) throws Exception
    {
        MaLineRunPlanService maLineRunPlanService = (MaLineRunPlanService) getBean("maLineRunPlanService");
        
        MaLineRunPlanDTO maLineRunPlanDTO = maLineRunPlanForm.getMaLineRunPlanDTO();

    	// 유저
    	User user = getUser(request);
    	
        maLineRunPlanDTO.setCompNo((getUser(request).getCompNo()));
        
        maLineRunPlanService.insertDetail(maLineRunPlanDTO, user);
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maLineRunPlanForm
     * @param request
     */
    private void updateDetail(MaLineRunPlanForm maLineRunPlanForm, HttpServletRequest request) throws Exception
    {
    	MaLineRunPlanService maLineRunPlanService = (MaLineRunPlanService) getBean("maLineRunPlanService");
        
        MaLineRunPlanDTO maLineRunPlanDTO = maLineRunPlanForm.getMaLineRunPlanDTO();
        
        maLineRunPlanDTO.setCompNo((getUser(request).getCompNo()));

    	// 유저
    	User user = getUser(request);
    	
        maLineRunPlanService.updateDetail(maLineRunPlanDTO, user);
        
        setAjaxStatus(request);
    }
    
    /**
     * valid no
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maLineRunPlanForm
     * @param request
     */
    private void validLineRunPlan(MaLineRunPlanForm maLineRunPlanForm, HttpServletRequest request) throws Exception
    {
    	MaLineRunPlanService maLineRunPlanService = (MaLineRunPlanService) getBean("maLineRunPlanService");
        
        MaLineRunPlanDTO maLineRunPlanDTO = maLineRunPlanForm.getMaLineRunPlanDTO();
        
        maLineRunPlanDTO.setCompNo((getUser(request).getCompNo()));

    	// 유저
    	User user = getUser(request);
    	
        String isValid = maLineRunPlanService.validLineRunPlan(maLineRunPlanDTO, user);
        
        setAjaxDesc(request, isValid);
    }
}
