package dream.mgr.usrgrp.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.codehaus.jettison.json.JSONException;

import common.bean.MwareConfig;
import common.config.service.ConfigService;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.mgr.usrgrp.dto.MaUsrGrpCommonDTO;
import dream.mgr.usrgrp.form.MaUsrGrpAuthListForm;
import dream.mgr.usrgrp.service.MaUsrGrpAuthListService;

/**
 * 권한그룹 - 목록 action
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/maUsrGrpAuthList" name="maUsrGrpAuthListForm"
 *                input="/dream/mgr/usrgrp/maUsrGrpAuthList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maUsrGrpAndroidAuthList" name="maUsrGrpAuthListForm"
 *                input="/dream/mgr/usrgrp/maUsrGrpAndroidAuthList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/mgrUsrgrpPageList" name="maUsrGrpAuthListForm"
 *                input="/dream/mgr/usrgrp/mgrUsrgrpPageList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/mgrUsrgrpBtnList" name="maUsrGrpAuthListForm"
 *                input="/dream/mgr/usrgrp/mgrUsrgrpBtnList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maUsrGrpAuthList" path="/dream/mgr/usrgrp/maUsrGrpAuthList.jsp"
 *                        redirect="false"
 */
public class MaUsrGrpAuthListAction extends AuthAction
{
    /** 조회 */
    public static final int USRGRP_AUTH_LIST_FIND = 1001;
    /** 삭제 */
    public static final int USRGRP_AUTH_LIST_DELETE = 1002;
    /** 삭제 */
    public static final int USRGRP_AUTHPAGE_LIST_FIND = 1003;
    /** Save Page Authority  */
    public static final int USRGRP_AUTHPAGE_LIST_SAVE = 1004;
    /** Find Button List  */
    public static final int USRGRP_AUTHBTN_LIST_FIND = 1005;
    /** Save Button Authority  */
    public static final int USRGRP_AUTHPBTN_LIST_SAVE = 1006;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaUsrGrpAuthListForm maUsrGrpAuthListForm = (MaUsrGrpAuthListForm) form;
        
        switch (maUsrGrpAuthListForm.getStrutsAction())
        {
            case MaUsrGrpAuthListAction.BASE_SET_HEADER:
                setHeader(request, response, maUsrGrpAuthListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaUsrGrpAuthListAction.USRGRP_AUTH_LIST_FIND:
                findList(request, response, maUsrGrpAuthListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break; 
            case MaUsrGrpAuthListAction.USRGRP_AUTHPAGE_LIST_FIND:
                findPageList(request, response, maUsrGrpAuthListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaUsrGrpAuthListAction.USRGRP_AUTH_LIST_DELETE:
                deleteList(request, maUsrGrpAuthListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;   
            case MaUsrGrpAuthListAction.USRGRP_AUTHPAGE_LIST_SAVE:
                savePageAuthList(request, maUsrGrpAuthListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;    
            case MaUsrGrpAuthListAction.USRGRP_AUTHBTN_LIST_FIND:
                findBtnList(request, response, maUsrGrpAuthListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaUsrGrpAuthListAction.USRGRP_AUTHPBTN_LIST_SAVE:
                saveBtnAuthList(request, maUsrGrpAuthListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;  
            case MaUsrGrpAuthListAction.BASE_GRID_EXPORT:
            	findList(request, response, maUsrGrpAuthListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();// findForward("maUsrGrpAuthList");
                break;
        }

        return returnActionForward;
    }

    private void findBtnList(HttpServletRequest request, HttpServletResponse response,MaUsrGrpAuthListForm maUsrGrpAuthListForm) throws IOException {
    	MaUsrGrpAuthListService maUsrGrpAuthListService = (MaUsrGrpAuthListService) getBean("maUsrGrpAuthListService");        

    	MaUsrGrpCommonDTO maUsrGrpCommonDTO = maUsrGrpAuthListForm.getMaUsrGrpCommonDTO();
    	
    	// 로긴 comp_no 를 셋팅한다.
    	maUsrGrpCommonDTO.setCompNo((getUser(request).getCompNo()));
    	maUsrGrpCommonDTO.setUserLang(getUser(request).getLocale().getLanguage());
        
        //리스트를 조회한다.
        List resultList = maUsrGrpAuthListService.findUseGrpBtnList(maUsrGrpCommonDTO);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response, maUsrGrpAuthListForm.getListId());
	}

	private void saveBtnAuthList(HttpServletRequest request, MaUsrGrpAuthListForm maUsrGrpAuthListForm) throws JSONException {
    	MaUsrGrpAuthListService maUsrGrpAuthListService = (MaUsrGrpAuthListService) getBean("maUsrGrpAuthListService");        

        MaUsrGrpCommonDTO maUsrGrpCommonDTO = maUsrGrpAuthListForm.getMaUsrGrpCommonDTO();
        maUsrGrpCommonDTO.setCompNo(getUser(request).getCompNo());

        maUsrGrpAuthListService.saveBtnAuthList(maUsrGrpCommonDTO, getUser(request));

        loadSec();

        setAjaxStatus(request);
	}

	private void savePageAuthList(HttpServletRequest request, MaUsrGrpAuthListForm maUsrGrpAuthListForm) throws JSONException {
    	MaUsrGrpAuthListService maUsrGrpAuthListService = (MaUsrGrpAuthListService) getBean("maUsrGrpAuthListService");        

        MaUsrGrpCommonDTO maUsrGrpCommonDTO = maUsrGrpAuthListForm.getMaUsrGrpCommonDTO();
        maUsrGrpCommonDTO.setCompNo(getUser(request).getCompNo());

        maUsrGrpAuthListService.savePageAuthList(maUsrGrpCommonDTO, getUser(request));

        loadSec();

        setAjaxStatus(request);
	}

	/**
     * Find All Page List under selected Menu Id as hierarchy form.
     * @param request
     * @param response
     * @param maUsrGrpAuthListForm
     * @throws IOException 
     */
    private void findPageList(HttpServletRequest request, HttpServletResponse response, MaUsrGrpAuthListForm maUsrGrpAuthListForm) throws IOException {
    	MaUsrGrpAuthListService maUsrGrpAuthListService = (MaUsrGrpAuthListService) getBean("maUsrGrpAuthListService");        

    	MaUsrGrpCommonDTO maUsrGrpCommonDTO = maUsrGrpAuthListForm.getMaUsrGrpCommonDTO();
    	
    	// 로긴 comp_no 를 셋팅한다.
    	maUsrGrpCommonDTO.setFilterCompNo((getUser(request).getCompNo()));
    	maUsrGrpCommonDTO.setUserLang(getUser(request).getLocale().getLanguage());
        
        //리스트를 조회한다.
        List resultList = maUsrGrpAuthListService.findUseGrpPageList(maUsrGrpCommonDTO);

        // 조회한 List 를 form에 셋팅한다.
        super.makeTreeJsonResult(resultList, request, response, maUsrGrpAuthListForm.getListId());
	}

	private void setHeader(HttpServletRequest request, HttpServletResponse response, MaUsrGrpAuthListForm maUsrGrpAuthListForm) throws IOException
    {
        super.setHeader(request, response, maUsrGrpAuthListForm.getListId(), maUsrGrpAuthListForm.getCurrentPageId()); 
    }
    
    
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maUsrGrpAuthListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, MaUsrGrpAuthListForm maUsrGrpAuthListForm, boolean excelExport) throws Exception
    {
    	MaUsrGrpAuthListService maUsrGrpAuthListService = (MaUsrGrpAuthListService) getBean("maUsrGrpAuthListService");        

    	MaUsrGrpCommonDTO maUsrGrpCommonDTO = maUsrGrpAuthListForm.getMaUsrGrpCommonDTO();
    	
    	// 로긴 comp_no 를 셋팅한다.
    	maUsrGrpCommonDTO.setFilterCompNo((getUser(request).getCompNo()));
    	maUsrGrpCommonDTO.setUserLang(getUser(request).getLocale().getLanguage());
        //리스트를 조회한다.
        List resultList = maUsrGrpAuthListService.findUseGrpAuthList(maUsrGrpCommonDTO,getUser(request));

        //super.makeTreeJsonResult(resultList, request, response, maUsrGrpAuthListForm.getListId());
        if(excelExport) super.makeTreeGridExport(resultList, request, response, maUsrGrpAuthListForm.getListId(),maUsrGrpAuthListForm.getCurrentPageId(), maUsrGrpAuthListForm.getFileName());
        else super.makeTreeJsonResult(resultList, request, response, maUsrGrpAuthListForm.getListId());
    }
    
    
    /**
     * delete
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maUsrGrpAuthListForm
     * @param request
     */
    private void deleteList(HttpServletRequest request, MaUsrGrpAuthListForm maUsrGrpAuthListForm) throws Exception
    {
        MaUsrGrpAuthListService maUsrGrpAuthListService = (MaUsrGrpAuthListService) getBean("maUsrGrpAuthListService");        

        MaUsrGrpCommonDTO maUsrGrpCommonDTO = maUsrGrpAuthListForm.getMaUsrGrpCommonDTO();
        maUsrGrpCommonDTO.setCompNo(getUser(request).getCompNo());

        maUsrGrpAuthListService.authMenu(maUsrGrpCommonDTO, getUser(request));

        loadSec();

        setAjaxStatus(request);
    }

    private void loadSec()
    {
    	Runnable myThreadTask = new Runnable(){	//Runnable 객체
			public void run(){
				ConfigService configService = (ConfigService) CommonUtil.getBean("configService");
				
		        configService.loadSecurityTable();
			}
		};
		
		Thread thread = new Thread(myThreadTask);
		thread.start();

    
    }
}
