package dream.consult.comp.user.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.codehaus.jettison.json.JSONException;

import common.config.service.ConfigService;
import common.struts.ConsultAuthAction;
import common.util.CommonUtil;
import dream.consult.comp.user.dto.ConsultCompUsrGrpCommonDTO;
import dream.consult.comp.user.form.ConsultCompUsrGrpAuthListForm;
import dream.consult.comp.user.service.ConsultCompUsrGrpAuthListService;


/**
 * 권한그룹 - 목록 action
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/consultCompUsrGrpWebAuthList" name="consultCompUsrGrpAuthListForm"
 *                input="/dream/consult/comp/user/consultCompUsrGrpWebAuthList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/consultCompUsrGrpBeeAuthList" name="consultCompUsrGrpAuthListForm"
 *                input="/dream/consult/comp/user/consultCompUsrGrpBeeAuthList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/consultCompUsrGrpPageAuthList" name="consultCompUsrGrpAuthListForm"
 *                input="/dream/consult/comp/user/consultCompUsrGrpPageAuthList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/consultCompUsrGrpBtnAuthList" name="consultCompUsrGrpAuthListForm"
 *                input="/dream/consult/comp/user/consultCompUsrGrpBtnAuthList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="consultCompUsrGrpAuthList" path="/dream/consult/comp/user/consultCompUsrGrpWebAuthList.jsp"
 *                        redirect="false"
 */
public class ConsultCompUsrGrpAuthListAction extends ConsultAuthAction
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
        ConsultCompUsrGrpAuthListForm consultCompUsrGrpAuthListForm = (ConsultCompUsrGrpAuthListForm) form;
        
        switch (consultCompUsrGrpAuthListForm.getStrutsAction())
        {
            case ConsultCompUsrGrpAuthListAction.BASE_SET_HEADER:
                setHeader(request, response, consultCompUsrGrpAuthListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ConsultCompUsrGrpAuthListAction.USRGRP_AUTH_LIST_FIND:
                findList(request, response, consultCompUsrGrpAuthListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break; 
            case ConsultCompUsrGrpAuthListAction.USRGRP_AUTHPAGE_LIST_FIND:
                findPageList(request, response, consultCompUsrGrpAuthListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ConsultCompUsrGrpAuthListAction.USRGRP_AUTH_LIST_DELETE:
                deleteList(request, consultCompUsrGrpAuthListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;   
            case ConsultCompUsrGrpAuthListAction.USRGRP_AUTHPAGE_LIST_SAVE:
                savePageAuthList(request, consultCompUsrGrpAuthListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;    
            case ConsultCompUsrGrpAuthListAction.USRGRP_AUTHBTN_LIST_FIND:
                findBtnList(request, response, consultCompUsrGrpAuthListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ConsultCompUsrGrpAuthListAction.USRGRP_AUTHPBTN_LIST_SAVE:
                saveBtnAuthList(request, consultCompUsrGrpAuthListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;  
            case ConsultCompUsrGrpAuthListAction.BASE_GRID_EXPORT:
                switch (consultCompUsrGrpAuthListForm.getStAct())
                {
                    case ConsultCompUsrGrpAuthListAction.USRGRP_AUTH_LIST_FIND:
                        findList(request, response, consultCompUsrGrpAuthListForm, true);
                        break;
                    case ConsultCompUsrGrpAuthListAction.USRGRP_AUTHPAGE_LIST_FIND:
                        findPageList(request, response, consultCompUsrGrpAuthListForm, true);
                        break;
                    case ConsultCompUsrGrpAuthListAction.USRGRP_AUTHBTN_LIST_FIND:
                        findBtnList(request, response, consultCompUsrGrpAuthListForm, true);
                        break;
                }
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();// findForward("consultCompUsrGrpAuthList");
                break;
        }

        return returnActionForward;
    }

    private void findBtnList(HttpServletRequest request, HttpServletResponse response,ConsultCompUsrGrpAuthListForm consultCompUsrGrpAuthListForm, boolean excelExport) throws IOException {
    	ConsultCompUsrGrpAuthListService consultCompUsrGrpAuthListService = (ConsultCompUsrGrpAuthListService) getBean("consultCompUsrGrpAuthListService");        

    	ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO = consultCompUsrGrpAuthListForm.getConsultCompUsrGrpCommonDTO();
    	
    	// 로긴 comp_no 를 셋팅한다.
    	consultCompUsrGrpCommonDTO.setCompNo((getUser(request).getCompNo()));
    	consultCompUsrGrpCommonDTO.setUserLang(getUser(request).getLocale().getLanguage());
        
        //리스트를 조회한다.
        List resultList = consultCompUsrGrpAuthListService.findUseGrpBtnList(consultCompUsrGrpCommonDTO);

        // 조회한 List 를 form에 셋팅한다.
        //super.makeJsonResult(resultList, request, response, consultCompUsrGrpAuthListForm.getListId());
        if(excelExport) CommonUtil.makeTreeGridExport(resultList, request, response, consultCompUsrGrpAuthListForm);
        else super.makeTreeJsonResult(resultList, request, response, consultCompUsrGrpAuthListForm.getListId());
	}

	private void saveBtnAuthList(HttpServletRequest request, ConsultCompUsrGrpAuthListForm consultCompUsrGrpAuthListForm) throws JSONException {
    	ConsultCompUsrGrpAuthListService consultCompUsrGrpAuthListService = (ConsultCompUsrGrpAuthListService) getBean("consultCompUsrGrpAuthListService");        

        ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO = consultCompUsrGrpAuthListForm.getConsultCompUsrGrpCommonDTO();
        consultCompUsrGrpCommonDTO.setCompNo(getUser(request).getCompNo());

        consultCompUsrGrpAuthListService.saveBtnAuthList(consultCompUsrGrpCommonDTO, getUser(request));

        loadSec();

        setAjaxStatus(request);
	}

	private void savePageAuthList(HttpServletRequest request, ConsultCompUsrGrpAuthListForm consultCompUsrGrpAuthListForm) throws JSONException {
    	ConsultCompUsrGrpAuthListService consultCompUsrGrpAuthListService = (ConsultCompUsrGrpAuthListService) getBean("consultCompUsrGrpAuthListService");        

        ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO = consultCompUsrGrpAuthListForm.getConsultCompUsrGrpCommonDTO();
//        consultCompUsrGrpCommonDTO.setCompNo(getUser(request).getCompNo());

        consultCompUsrGrpAuthListService.savePageAuthList(consultCompUsrGrpCommonDTO, getUser(request));

        loadSec();

        setAjaxStatus(request);
	}

	/**
     * Find All Page List under selected Menu Id as hierarchy form.
     * @param request
     * @param response
     * @param consultCompUsrGrpAuthListForm
     * @throws IOException 
     */
    private void findPageList(HttpServletRequest request, HttpServletResponse response, ConsultCompUsrGrpAuthListForm consultCompUsrGrpAuthListForm, boolean excelExport) throws IOException {
    	ConsultCompUsrGrpAuthListService consultCompUsrGrpAuthListService = (ConsultCompUsrGrpAuthListService) getBean("consultCompUsrGrpAuthListService");        

    	ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO = consultCompUsrGrpAuthListForm.getConsultCompUsrGrpCommonDTO();
    	
    	// 로긴 comp_no 를 셋팅한다.
    	consultCompUsrGrpCommonDTO.setFilterCompNo((getUser(request).getCompNo()));
    	consultCompUsrGrpCommonDTO.setUserLang(getUser(request).getLocale().getLanguage());

        //리스트를 조회한다.
        List resultList = consultCompUsrGrpAuthListService.findUseGrpPageList(consultCompUsrGrpCommonDTO);

        // 조회한 List 를 form에 셋팅한다.
        //super.makeTreeJsonResult(resultList, request, response, consultCompUsrGrpAuthListForm.getListId());
        if(excelExport) CommonUtil.makeTreeGridExport(resultList, request, response, consultCompUsrGrpAuthListForm);
        else super.makeTreeJsonResult(resultList, request, response, consultCompUsrGrpAuthListForm.getListId());
	}

	private void setHeader(HttpServletRequest request, HttpServletResponse response, ConsultCompUsrGrpAuthListForm consultCompUsrGrpAuthListForm) throws IOException
    {
        super.setHeader(request, response, consultCompUsrGrpAuthListForm.getListId(), consultCompUsrGrpAuthListForm.getCurrentPageId()); 
    }
    
    
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param consultCompUsrGrpAuthListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, ConsultCompUsrGrpAuthListForm consultCompUsrGrpAuthListForm, boolean excelExport) throws Exception
    {
    	ConsultCompUsrGrpAuthListService consultCompUsrGrpAuthListService = (ConsultCompUsrGrpAuthListService) getBean("consultCompUsrGrpAuthListService");        

    	ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO = consultCompUsrGrpAuthListForm.getConsultCompUsrGrpCommonDTO();
    	
    	// 로긴 comp_no 를 셋팅한다.
    	consultCompUsrGrpCommonDTO.setFilterCompNo((getUser(request).getCompNo()));
    	consultCompUsrGrpCommonDTO.setUserLang(getUser(request).getLocale().getLanguage());
        //리스트를 조회한다.
        List resultList = consultCompUsrGrpAuthListService.findUseGrpAuthList(consultCompUsrGrpCommonDTO,getUser(request));

        //super.makeTreeJsonResult(resultList, request, response, consultCompUsrGrpAuthListForm.getListId());
        if(excelExport) CommonUtil.makeTreeGridExport(resultList, request, response, consultCompUsrGrpAuthListForm);
        else super.makeTreeJsonResult(resultList, request, response, consultCompUsrGrpAuthListForm.getListId());
    }
    
    
    /**
     * delete
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param consultCompUsrGrpAuthListForm
     * @param request
     */
    private void deleteList(HttpServletRequest request, ConsultCompUsrGrpAuthListForm consultCompUsrGrpAuthListForm) throws Exception
    {
        ConsultCompUsrGrpAuthListService consultCompUsrGrpAuthListService = (ConsultCompUsrGrpAuthListService) getBean("consultCompUsrGrpAuthListService");        

        ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO = consultCompUsrGrpAuthListForm.getConsultCompUsrGrpCommonDTO();
//        consultCompUsrGrpCommonDTO.setCompNo(getUser(request).getCompNo());

        consultCompUsrGrpAuthListService.authMenu(consultCompUsrGrpCommonDTO, getUser(request));

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
