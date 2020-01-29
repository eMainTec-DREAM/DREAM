package dream.mgr.usrgrp.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.config.service.ConfigService;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.mgr.usrgrp.dto.MgrUsrGrpPageAuthDTO;
import dream.mgr.usrgrp.form.MgrUsrGrpPageAuthForm;
import dream.mgr.usrgrp.service.MgrUsrGrpPageAuthService;

/**
 * 화면권한설정
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/mgrUsrGrpPageAuthList" name="mgrUsrGrpPageAuthForm"
 *                input="/dream/mgr/usrgrp/mgrUsrGrpPageAuthList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/mgrUsrGrpPageAuthDetail" name="mgrUsrGrpPageAuthForm"
 *                input="/dream/mgr/usrgrp/mgrUsrGrpPageAuthDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrUsrGrpPageAuthDetail" path="/dream/mgr/usrgrp/mgrUsrGrpPageAuthDetail.jsp"
 *                        redirect="false"
 */

public class MgrUsrGrpPageAuthAction extends AuthAction
{
    /** 목록 조회 */
    public static final int LIST_FIND           = 1001;
    /** 목록 권한부여 */
    public static final int LIST_INPUT_AUTH     = 1002;
    /** 목록 권한제거 */
    public static final int LIST_DELETE_AUTH    = 1003;
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DETAIL_INIT         = 1004;
    /** 상세 수정 */
    public static final int DETAIL_UPDATE       = 1005;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MgrUsrGrpPageAuthForm mgrUsrGrpPageAuthForm = (MgrUsrGrpPageAuthForm) form;
        
        switch (mgrUsrGrpPageAuthForm.getStrutsAction())
        {
            case MgrUsrGrpPageAuthAction.BASE_SET_HEADER:
                setHeader(request, response, mgrUsrGrpPageAuthForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrUsrGrpPageAuthAction.LIST_FIND:
                findList(request, response, mgrUsrGrpPageAuthForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case MgrUsrGrpPageAuthAction.LIST_INPUT_AUTH:
            	inputAuth(request, mgrUsrGrpPageAuthForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case MgrUsrGrpPageAuthAction.LIST_DELETE_AUTH:
                deleteAuth(request, mgrUsrGrpPageAuthForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case MgrUsrGrpPageAuthAction.BASE_GRID_EXPORT:
            	findList(request, response, mgrUsrGrpPageAuthForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            case MgrUsrGrpPageAuthAction.DETAIL_INIT:
                findDetail(request, response, mgrUsrGrpPageAuthForm);
                returnActionForward = mapping.findForward("mgrUsrGrpPageAuthDetail");
                break;
            case MgrUsrGrpPageAuthAction.DETAIL_UPDATE:
                updateDetail(request, response, mgrUsrGrpPageAuthForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
            	returnActionForward = mapping.getInputForward();
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MgrUsrGrpPageAuthForm mgrUsrGrpPageAuthForm) throws IOException
    {
        super.setHeader(request, response, mgrUsrGrpPageAuthForm.getListId(), mgrUsrGrpPageAuthForm.getCurrentPageId()); 
    }
    
    private void findList(HttpServletRequest request, HttpServletResponse response, MgrUsrGrpPageAuthForm mgrUsrGrpPageAuthForm, boolean excelExport) throws Exception
    {
    	MgrUsrGrpPageAuthService mgrUsrGrpPageAuthService = (MgrUsrGrpPageAuthService) getBean("mgrUsrGrpPageAuthService");
    	MgrUsrGrpPageAuthDTO mgrUsrGrpPageAuthDTO = mgrUsrGrpPageAuthForm.getMgrUsrGrpPageAuthDTO();

    	//Paging
    	mgrUsrGrpPageAuthDTO.setIsLoadMaxCount("Y".equals(mgrUsrGrpPageAuthForm.getIsLoadMaxCount())?true:false);
    	mgrUsrGrpPageAuthDTO.setFirstRow(mgrUsrGrpPageAuthForm.getFirstRow());
    	mgrUsrGrpPageAuthDTO.setOrderBy(mgrUsrGrpPageAuthForm.getOrderBy());
    	mgrUsrGrpPageAuthDTO.setDirection(mgrUsrGrpPageAuthForm.getDirection());
    	
    	User user = getUser(request);
    	
        List resultList = mgrUsrGrpPageAuthService.findList(mgrUsrGrpPageAuthDTO, user);
        //Paging
        String totalCount = "";
        if(Integer.parseInt(mgrUsrGrpPageAuthForm.getIsTotalCount()) == 0 && !excelExport) totalCount = mgrUsrGrpPageAuthService.findTotalCount(mgrUsrGrpPageAuthDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,mgrUsrGrpPageAuthForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    private void inputAuth(HttpServletRequest request, MgrUsrGrpPageAuthForm mgrUsrGrpPageAuthForm) throws Exception
    {
    	MgrUsrGrpPageAuthService mgrUsrGrpPageAuthService = (MgrUsrGrpPageAuthService) getBean("mgrUsrGrpPageAuthService");
    	String[] pageIds = mgrUsrGrpPageAuthForm.getDeleteRows();
        String[] usrgrpIds = mgrUsrGrpPageAuthForm.getDeleteRowsExt();
        
    	User user = getUser(request);
    	
        mgrUsrGrpPageAuthService.inputAuth(pageIds, usrgrpIds, user);
        
        loadSec();
        
        setAjaxStatus(request);
    }
    
    private void deleteAuth(HttpServletRequest request, MgrUsrGrpPageAuthForm mgrUsrGrpPageAuthForm) throws Exception
    {
        MgrUsrGrpPageAuthService mgrUsrGrpPageAuthService = (MgrUsrGrpPageAuthService) getBean("mgrUsrGrpPageAuthService");
        String[] pageIds = mgrUsrGrpPageAuthForm.getDeleteRows();
        String[] usrgrpIds = mgrUsrGrpPageAuthForm.getDeleteRowsExt();
        
        User user = getUser(request);
        
        mgrUsrGrpPageAuthService.deleteAuth(pageIds, usrgrpIds, user);
        
        loadSec();
        
        setAjaxStatus(request);
    }
    
    private void findDetail(HttpServletRequest request, HttpServletResponse response, MgrUsrGrpPageAuthForm mgrUsrGrpPageAuthForm) throws Exception 
    {   
        MgrUsrGrpPageAuthService mgrUsrGrpPageAuthService = (MgrUsrGrpPageAuthService)getBean("mgrUsrGrpPageAuthService");
        
        MgrUsrGrpPageAuthDTO mgrUsrGrpPageAuthDTO = mgrUsrGrpPageAuthForm.getMgrUsrGrpPageAuthDTO(); 

        User user = getUser(request);
        
        mgrUsrGrpPageAuthDTO = mgrUsrGrpPageAuthService.findDetail(mgrUsrGrpPageAuthDTO, user);
        mgrUsrGrpPageAuthForm.setMgrUsrGrpPageAuthDTO(mgrUsrGrpPageAuthDTO);
    }

    private void updateDetail(HttpServletRequest request, HttpServletResponse response, MgrUsrGrpPageAuthForm mgrUsrGrpPageAuthForm) throws Exception
    {
        MgrUsrGrpPageAuthService mgrUsrGrpPageAuthService = (MgrUsrGrpPageAuthService)getBean("mgrUsrGrpPageAuthService");
        MgrUsrGrpPageAuthDTO  mgrUsrGrpPageAuthDTO = mgrUsrGrpPageAuthForm.getMgrUsrGrpPageAuthDTO();
        
        User user = getUser(request);
        
        mgrUsrGrpPageAuthService.updateDetail(mgrUsrGrpPageAuthDTO, user);
        
        loadSec();
        
        setAjaxStatus(request);
    }
    
    private void loadSec()
    {
        Runnable myThreadTask = new Runnable(){ //Runnable 객체
            public void run(){
                ConfigService configService = (ConfigService) CommonUtil.getBean("configService");
                
                configService.loadSecurityTable();
            }
        };
        
        Thread thread = new Thread(myThreadTask);
        thread.start();
    }
}
