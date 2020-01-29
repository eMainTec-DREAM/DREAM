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
import dream.mgr.usrgrp.dto.MgrUsrGrpPageAuthBtnDTO;
import dream.mgr.usrgrp.form.MgrUsrGrpPageAuthBtnForm;
import dream.mgr.usrgrp.service.MgrUsrGrpPageAuthBtnService;

/**
 * 화면권한설정상세탭버튼권한
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/mgrUsrGrpPageAuthBtnList" name="mgrUsrGrpPageAuthBtnForm"
 *                input="/dream/mgr/usrgrp/mgrUsrGrpPageAuthBtnList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/mgrUsrGrpPageAuthBtnDetail" name="mgrUsrGrpPageAuthBtnForm"
 *                input="/dream/mgr/usrgrp/mgrUsrGrpPageAuthBtnDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrUsrGrpPageAuthBtnDetail" path="/dream/mgr/usrgrp/mgrUsrGrpPageAuthBtnDetail.jsp"
 *                        redirect="false"
 */

public class MgrUsrGrpPageAuthBtnAction extends AuthAction
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
        MgrUsrGrpPageAuthBtnForm mgrUsrGrpPageAuthBtnForm = (MgrUsrGrpPageAuthBtnForm) form;
        
        switch (mgrUsrGrpPageAuthBtnForm.getStrutsAction())
        {
            case MgrUsrGrpPageAuthBtnAction.BASE_SET_HEADER:
                setHeader(request, response, mgrUsrGrpPageAuthBtnForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrUsrGrpPageAuthBtnAction.LIST_FIND:
                findList(request, response, mgrUsrGrpPageAuthBtnForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case MgrUsrGrpPageAuthBtnAction.LIST_INPUT_AUTH:
            	inputAuth(request, mgrUsrGrpPageAuthBtnForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case MgrUsrGrpPageAuthBtnAction.LIST_DELETE_AUTH:
                deleteAuth(request, mgrUsrGrpPageAuthBtnForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case MgrUsrGrpPageAuthBtnAction.BASE_GRID_EXPORT:
            	findList(request, response, mgrUsrGrpPageAuthBtnForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            case MgrUsrGrpPageAuthBtnAction.DETAIL_INIT:
                findDetail(request, response, mgrUsrGrpPageAuthBtnForm);
                returnActionForward = mapping.findForward("mgrUsrGrpPageAuthBtnDetail");
                break;
            case MgrUsrGrpPageAuthBtnAction.DETAIL_UPDATE:
                updateDetail(request, response, mgrUsrGrpPageAuthBtnForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
            	returnActionForward = mapping.getInputForward();
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MgrUsrGrpPageAuthBtnForm mgrUsrGrpPageAuthBtnForm) throws IOException
    {
        super.setHeader(request, response, mgrUsrGrpPageAuthBtnForm.getListId(), mgrUsrGrpPageAuthBtnForm.getCurrentPageId()); 
    }
    
    private void findList(HttpServletRequest request, HttpServletResponse response, MgrUsrGrpPageAuthBtnForm mgrUsrGrpPageAuthBtnForm, boolean excelExport) throws Exception
    {
    	MgrUsrGrpPageAuthBtnService mgrUsrGrpPageAuthBtnService = (MgrUsrGrpPageAuthBtnService) getBean("mgrUsrGrpPageAuthBtnService");
    	MgrUsrGrpPageAuthBtnDTO mgrUsrGrpPageAuthBtnDTO = mgrUsrGrpPageAuthBtnForm.getMgrUsrGrpPageAuthBtnDTO();

    	//Paging
    	mgrUsrGrpPageAuthBtnDTO.setIsLoadMaxCount("Y".equals(mgrUsrGrpPageAuthBtnForm.getIsLoadMaxCount())?true:false);
    	mgrUsrGrpPageAuthBtnDTO.setFirstRow(mgrUsrGrpPageAuthBtnForm.getFirstRow());
    	mgrUsrGrpPageAuthBtnDTO.setOrderBy(mgrUsrGrpPageAuthBtnForm.getOrderBy());
    	mgrUsrGrpPageAuthBtnDTO.setDirection(mgrUsrGrpPageAuthBtnForm.getDirection());
    	
    	User user = getUser(request);
    	
        List resultList = mgrUsrGrpPageAuthBtnService.findList(mgrUsrGrpPageAuthBtnDTO, user);
        //Paging
        String totalCount = "";
        if(Integer.parseInt(mgrUsrGrpPageAuthBtnForm.getIsTotalCount()) == 0 && !excelExport) totalCount = mgrUsrGrpPageAuthBtnService.findTotalCount(mgrUsrGrpPageAuthBtnDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,mgrUsrGrpPageAuthBtnForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    private void inputAuth(HttpServletRequest request, MgrUsrGrpPageAuthBtnForm mgrUsrGrpPageAuthBtnForm) throws Exception
    {
    	MgrUsrGrpPageAuthBtnService mgrUsrGrpPageAuthBtnService = (MgrUsrGrpPageAuthBtnService) getBean("mgrUsrGrpPageAuthBtnService");
    	String[] pgbtnIds = mgrUsrGrpPageAuthBtnForm.getDeleteRows();
        String[] usrgrpIds = mgrUsrGrpPageAuthBtnForm.getDeleteRowsExt();
        
    	User user = getUser(request);
        
        mgrUsrGrpPageAuthBtnService.inputAuth(pgbtnIds, usrgrpIds, user);
        
        loadSec();
        
        setAjaxStatus(request);
    }
    
    private void deleteAuth(HttpServletRequest request, MgrUsrGrpPageAuthBtnForm mgrUsrGrpPageAuthBtnForm) throws Exception
    {
        MgrUsrGrpPageAuthBtnService mgrUsrGrpPageAuthBtnService = (MgrUsrGrpPageAuthBtnService) getBean("mgrUsrGrpPageAuthBtnService");
        String[] pgbtnIds = mgrUsrGrpPageAuthBtnForm.getDeleteRows();
        String[] usrgrpIds = mgrUsrGrpPageAuthBtnForm.getDeleteRowsExt();
        
        User user = getUser(request);
        
        mgrUsrGrpPageAuthBtnService.deleteAuth(pgbtnIds, usrgrpIds, user);
        
        loadSec();
        
        setAjaxStatus(request);
    }
    
    private void findDetail(HttpServletRequest request, HttpServletResponse response, MgrUsrGrpPageAuthBtnForm mgrUsrGrpPageAuthBtnForm) throws Exception 
    {   
        MgrUsrGrpPageAuthBtnService mgrUsrGrpPageAuthBtnService = (MgrUsrGrpPageAuthBtnService)getBean("mgrUsrGrpPageAuthBtnService");
        
        MgrUsrGrpPageAuthBtnDTO mgrUsrGrpPageAuthBtnDTO = mgrUsrGrpPageAuthBtnForm.getMgrUsrGrpPageAuthBtnDTO();

        User user = getUser(request);
        
        mgrUsrGrpPageAuthBtnDTO = mgrUsrGrpPageAuthBtnService.findDetail(mgrUsrGrpPageAuthBtnDTO, user);
        mgrUsrGrpPageAuthBtnForm.setMgrUsrGrpPageAuthBtnDTO(mgrUsrGrpPageAuthBtnDTO);
    }

    private void updateDetail(HttpServletRequest request, HttpServletResponse response, MgrUsrGrpPageAuthBtnForm mgrUsrGrpPageAuthBtnForm) throws Exception
    {
        MgrUsrGrpPageAuthBtnService mgrUsrGrpPageAuthBtnService = (MgrUsrGrpPageAuthBtnService)getBean("mgrUsrGrpPageAuthBtnService");
        MgrUsrGrpPageAuthBtnDTO  mgrUsrGrpPageAuthBtnDTO = mgrUsrGrpPageAuthBtnForm.getMgrUsrGrpPageAuthBtnDTO();
        
        User user = getUser(request);
        
        mgrUsrGrpPageAuthBtnService.updateDetail(mgrUsrGrpPageAuthBtnDTO, user);
        
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
