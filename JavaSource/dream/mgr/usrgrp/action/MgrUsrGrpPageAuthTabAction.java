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
import dream.mgr.usrgrp.dto.MgrUsrGrpPageAuthTabDTO;
import dream.mgr.usrgrp.form.MgrUsrGrpPageAuthTabForm;
import dream.mgr.usrgrp.service.MgrUsrGrpPageAuthTabService;

/**
 * 화면권한설정상세탭탭권한
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/mgrUsrGrpPageAuthTabList" name="mgrUsrGrpPageAuthTabForm"
 *                input="/dream/mgr/usrgrp/mgrUsrGrpPageAuthTabList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/mgrUsrGrpPageAuthTabDetail" name="mgrUsrGrpPageAuthTabForm"
 *                input="/dream/mgr/usrgrp/mgrUsrGrpPageAuthTabDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrUsrGrpPageAuthTabDetail" path="/dream/mgr/usrgrp/mgrUsrGrpPageAuthTabDetail.jsp"
 *                        redirect="false"
 */

public class MgrUsrGrpPageAuthTabAction extends AuthAction
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
        MgrUsrGrpPageAuthTabForm mgrUsrGrpPageAuthTabForm = (MgrUsrGrpPageAuthTabForm) form;
        
        switch (mgrUsrGrpPageAuthTabForm.getStrutsAction())
        {
            case MgrUsrGrpPageAuthTabAction.BASE_SET_HEADER:
                setHeader(request, response, mgrUsrGrpPageAuthTabForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrUsrGrpPageAuthTabAction.LIST_FIND:
                findList(request, response, mgrUsrGrpPageAuthTabForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case MgrUsrGrpPageAuthTabAction.LIST_INPUT_AUTH:
            	inputAuth(request, mgrUsrGrpPageAuthTabForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case MgrUsrGrpPageAuthTabAction.LIST_DELETE_AUTH:
                deleteAuth(request, mgrUsrGrpPageAuthTabForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case MgrUsrGrpPageAuthTabAction.BASE_GRID_EXPORT:
            	findList(request, response, mgrUsrGrpPageAuthTabForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            case MgrUsrGrpPageAuthTabAction.DETAIL_INIT:
                findDetail(request, response, mgrUsrGrpPageAuthTabForm);
                returnActionForward = mapping.findForward("mgrUsrGrpPageAuthTabDetail");
                break;
            case MgrUsrGrpPageAuthTabAction.DETAIL_UPDATE:
                updateDetail(request, response, mgrUsrGrpPageAuthTabForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
            	returnActionForward = mapping.getInputForward();
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MgrUsrGrpPageAuthTabForm mgrUsrGrpPageAuthTabForm) throws IOException
    {
        super.setHeader(request, response, mgrUsrGrpPageAuthTabForm.getListId(), mgrUsrGrpPageAuthTabForm.getCurrentPageId()); 
    }
    
    private void findList(HttpServletRequest request, HttpServletResponse response, MgrUsrGrpPageAuthTabForm mgrUsrGrpPageAuthTabForm, boolean excelExport) throws Exception
    {
    	MgrUsrGrpPageAuthTabService mgrUsrGrpPageAuthTabService = (MgrUsrGrpPageAuthTabService) getBean("mgrUsrGrpPageAuthTabService");
    	MgrUsrGrpPageAuthTabDTO mgrUsrGrpPageAuthTabDTO = mgrUsrGrpPageAuthTabForm.getMgrUsrGrpPageAuthTabDTO();

    	//Paging
    	mgrUsrGrpPageAuthTabDTO.setIsLoadMaxCount("Y".equals(mgrUsrGrpPageAuthTabForm.getIsLoadMaxCount())?true:false);
    	mgrUsrGrpPageAuthTabDTO.setFirstRow(mgrUsrGrpPageAuthTabForm.getFirstRow());
    	mgrUsrGrpPageAuthTabDTO.setOrderBy(mgrUsrGrpPageAuthTabForm.getOrderBy());
    	mgrUsrGrpPageAuthTabDTO.setDirection(mgrUsrGrpPageAuthTabForm.getDirection());
    	
    	User user = getUser(request);
    	
        List resultList = mgrUsrGrpPageAuthTabService.findList(mgrUsrGrpPageAuthTabDTO, user);
        //Paging
        String totalCount = "";
        if(Integer.parseInt(mgrUsrGrpPageAuthTabForm.getIsTotalCount()) == 0 && !excelExport) totalCount = mgrUsrGrpPageAuthTabService.findTotalCount(mgrUsrGrpPageAuthTabDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,mgrUsrGrpPageAuthTabForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    private void inputAuth(HttpServletRequest request, MgrUsrGrpPageAuthTabForm mgrUsrGrpPageAuthTabForm) throws Exception
    {
    	MgrUsrGrpPageAuthTabService mgrUsrGrpPageAuthTabService = (MgrUsrGrpPageAuthTabService) getBean("mgrUsrGrpPageAuthTabService");
    	String[] pgpageIds = mgrUsrGrpPageAuthTabForm.getDeleteRows();
        String[] usrgrpIds = mgrUsrGrpPageAuthTabForm.getDeleteRowsExt();
        
    	User user = getUser(request);
        
        mgrUsrGrpPageAuthTabService.inputAuth(pgpageIds, usrgrpIds, user);
        
        loadSec();
        
        setAjaxStatus(request);
    }
    
    private void deleteAuth(HttpServletRequest request, MgrUsrGrpPageAuthTabForm mgrUsrGrpPageAuthTabForm) throws Exception
    {
        MgrUsrGrpPageAuthTabService mgrUsrGrpPageAuthTabService = (MgrUsrGrpPageAuthTabService) getBean("mgrUsrGrpPageAuthTabService");
        String[] pgpageIds = mgrUsrGrpPageAuthTabForm.getDeleteRows();
        String[] usrgrpIds = mgrUsrGrpPageAuthTabForm.getDeleteRowsExt();
        
        User user = getUser(request);
        
        mgrUsrGrpPageAuthTabService.deleteAuth(pgpageIds, usrgrpIds, user);
        
        loadSec();
        
        setAjaxStatus(request);
    }
    
    private void findDetail(HttpServletRequest request, HttpServletResponse response, MgrUsrGrpPageAuthTabForm mgrUsrGrpPageAuthTabForm) throws Exception 
    {   
        MgrUsrGrpPageAuthTabService mgrUsrGrpPageAuthTabService = (MgrUsrGrpPageAuthTabService)getBean("mgrUsrGrpPageAuthTabService");
        
        MgrUsrGrpPageAuthTabDTO mgrUsrGrpPageAuthTabDTO = mgrUsrGrpPageAuthTabForm.getMgrUsrGrpPageAuthTabDTO();

        User user = getUser(request);
        
        mgrUsrGrpPageAuthTabDTO = mgrUsrGrpPageAuthTabService.findDetail(mgrUsrGrpPageAuthTabDTO, user);
        mgrUsrGrpPageAuthTabForm.setMgrUsrGrpPageAuthTabDTO(mgrUsrGrpPageAuthTabDTO);
    }

    private void updateDetail(HttpServletRequest request, HttpServletResponse response, MgrUsrGrpPageAuthTabForm mgrUsrGrpPageAuthTabForm) throws Exception
    {
        MgrUsrGrpPageAuthTabService mgrUsrGrpPageAuthTabService = (MgrUsrGrpPageAuthTabService)getBean("mgrUsrGrpPageAuthTabService");
        MgrUsrGrpPageAuthTabDTO  mgrUsrGrpPageAuthTabDTO = mgrUsrGrpPageAuthTabForm.getMgrUsrGrpPageAuthTabDTO();
        
        User user = getUser(request);
        
        mgrUsrGrpPageAuthTabService.updateDetail(mgrUsrGrpPageAuthTabDTO, user);
        
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
