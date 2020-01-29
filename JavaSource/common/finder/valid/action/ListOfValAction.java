package common.finder.valid.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.codehaus.jettison.json.JSONException;
import org.json.simple.parser.ParseException;

import common.finder.valid.dto.ListOfValDTO;
import common.finder.valid.form.ListOfValForm;
import common.finder.valid.service.ListOfValService;
import common.struts.SuperAuthAction;

/**
 * List Of Value
 * 검색 popup 창에 대한 Action 이다.
 * @author  javaworker
 * @version $Id: ListOfValAction.java,v 1.2 2014/01/28 07:49:29 pochul2423 Exp $
 * @since   1.0
 * 
 * @struts:action path="/listOfVal" name="listOfValForm"
 *                input="/common/finder/listOfValPopup.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/listOfSysVal" name="listOfValForm"
 *                input="/common/finder/listOfSysVal.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovUsrDirPopup" path="/common/finder/lovUsrDirPopup.jsp"
 *                        redirect="false"
 * @struts.action-forward name="lovSysDirPopup" path="/common/finder/lovSysDirPopup.jsp"
 *                        redirect="false"
 * @struts.action-forward name="lovSysDirPopup2" path="/common/finder/lovSysDirPopup2.jsp"
 *                        redirect="false"
 * @struts.action-forward name="lovCompPopup" path="/common/finder/lovCompPopup.jsp"
 *                        redirect="false"
 * @struts.action-forward name="lovTablePopup" path="/common/finder/lovTablePopup.jsp"
 *                        redirect="false"
 * @struts.action-forward name="lovMobileTablePopup" path="/common/finder/lovMobileTablePopup.jsp"
 *                        redirect="false"
 * @struts.action-forward name="lovMobileSysDirPopup" path="/common/finder/lovMobileSysDirPopup.jsp"
 *                        redirect="false"
 */
public class ListOfValAction extends SuperAuthAction
{
    /** 사용자 Dir Page */
    public static final int LOV_USR_DIR_DEFAULT 	= 2001;
    /** 사용자 Dir Lov */
    public static final int LOV_USR_DIR_FIND 		= 2002;
    /** 시스템Dir Page */
    public static final int LOV_SYS_DIR_DEFAULT 	= 2003;
    /** 시스템 Dir Lov */
    public static final int LOV_SYS_DIR_FIND 		= 2004;    
    /** 시스템Dir Page */
    public static final int LOV_SYS_DIR_DEFAULT2    = 2005;
    /** 시스템 AC Dir Lov */
    public static final int LOV_ACSYS_DIR_FIND 		= 2006;
    
    /** 시스템 Comp Dir Page */
    public static final int LOV_COMP_DEFAULT    = 2007;
    /** COMP Lov */
    public static final int LOV_COMP_FIND 		= 2008;
    
    /** Table Lov Page */
    public static final int LOV_TABLE_DEFAULT 		= 3001;
    /** Table Lov List */
    public static final int LOV_TABLE_FIND 			= 3002;
    
    /** Mobile Lov List */
    public static final int LOV_MOBILE_TABLE_DEFAULT 	= 3003;
    /** Mobile Dir Lov List */
    public static final int LOV_MOBILE_DIR_DEFAULT		= 3004;
    

    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ListOfValForm listOfValForm = (ListOfValForm)form;
        ActionForward returnActionForward = null;
      //  returnActionForward = mapping.getInputForward();
        switch (listOfValForm.getStrutsAction())
        {
	        case ListOfValAction.LOV_MOBILE_TABLE_DEFAULT :
	            returnActionForward = mapping.findForward("lovMobileTablePopup");
	            break;
	        case ListOfValAction.LOV_MOBILE_DIR_DEFAULT :
	            returnActionForward = mapping.findForward("lovMobileSysDirPopup");
	            break;
	        case ListOfValAction.LOV_USR_DIR_DEFAULT :
	            returnActionForward = mapping.findForward("lovUsrDirPopup");
	            break;
	        case ListOfValAction.LOV_USR_DIR_FIND :
	            findUsrDirList(request, listOfValForm,response);
	            returnActionForward = mapping.findForward("jsonPage");
	            break;
	        case ListOfValAction.LOV_SYS_DIR_DEFAULT :
	        	setJsonParam(request, listOfValForm,response);
	            returnActionForward = mapping.findForward("lovSysDirPopup");
	            break;
	        case ListOfValAction.LOV_ACSYS_DIR_FIND :
	            findAcSysDirList(request, listOfValForm,response);
	            returnActionForward = mapping.findForward("jsonPage");
	            break;
	        case ListOfValAction.LOV_SYS_DIR_DEFAULT2 :
                returnActionForward = mapping.findForward("lovSysDirPopup2");
                break;
	        case ListOfValAction.LOV_SYS_DIR_FIND :
	            findSysDirList(request, listOfValForm,response);
	            returnActionForward = mapping.findForward("jsonPage");
	            break;
	        case ListOfValAction.LOV_COMP_DEFAULT :
                returnActionForward = mapping.findForward("lovCompPopup");
                break;
	        case ListOfValAction.LOV_COMP_FIND :
	        	findCompList(request, listOfValForm,response);
	        	returnActionForward = mapping.findForward("jsonPage");
	        	break;
            case ListOfValAction.LOV_TABLE_DEFAULT :
                returnActionForward = mapping.findForward("lovTablePopup");
                break;
            case ListOfValAction.LOV_TABLE_FIND :
                findTableList(request, listOfValForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ListOfValAction.BASE_SET_HEADER:
                setHeader(request, response, listOfValForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
            	returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }

    /**
     * System Code With Auto Complete Param
     * @param request
     * @param listOfValForm
     * @param response
     * @throws IOException
     */
    private void findAcSysDirList(HttpServletRequest request, ListOfValForm listOfValForm,HttpServletResponse response) throws IOException 
    {
    	ListOfValService listOfValService = (ListOfValService)getBean("listOfValService");
        
        List resultList = listOfValService.findAcSysDirList(listOfValForm.getListOfValDTO(),getUser(request), listOfValForm);
        
        super.makeJsonResult(resultList, request, response, listOfValForm.getListId());
	}

	private void setJsonParam(HttpServletRequest request, ListOfValForm listOfValForm, HttpServletResponse response) throws ParseException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    	
    	ListOfValDTO listOfValDTO =listOfValForm.getListOfValDTO();
    	ListOfValService listOfValService = (ListOfValService)getBean("listOfValService");
        
    	ListOfValDTO resultDTO = listOfValService.setJsonParm(listOfValDTO);
    	
    	listOfValForm.setListOfValDTO(resultDTO);
	}

	private void setHeader(HttpServletRequest request, HttpServletResponse response, ListOfValForm listOfValForm) throws IOException
    {
        super.setHeader(request, response, listOfValForm.getListId(),listOfValForm.getCurrentPageId()); 
    }
    /**
     * code, description 을 검색 조건으로 
     * 별도의 Table 을 검색한다.
     * @author  javaworker
     * @version $Id: ListOfValAction.java,v 1.2 2014/01/28 07:49:29 pochul2423 Exp $
     * @since   1.0
     * 
     * @param request
     * @param listOfValForm
     */
    private void findTableList(HttpServletRequest request,
            ListOfValForm listOfValForm, HttpServletResponse response) throws IOException
    {
        ListOfValService listOfValService = (ListOfValService)getBean("listOfValService");
        
        ListOfValDTO listOfValDTO = listOfValForm.getListOfValDTO();
        listOfValDTO.setUserLang(getUser(request).getLocale().getLanguage());
        listOfValDTO.setCompNo(getUser(request).getCompNo());
        listOfValDTO.setUsrgrpId(getUser(request).getUsrgrpId());
        
        List resultList = listOfValService.findTableList(listOfValDTO,getUser(request));
        
        super.makeJsonResult(resultList, request, response, listOfValForm.getListId());
    	
    }

    /**
     * code, description 을 검색 조건으로 
     * 사용자코드 테이블 검색
     * @author  javaworker
     * @version $Id: ListOfValAction.java,v 1.2 2014/01/28 07:49:29 pochul2423 Exp $
     * @since   1.0
     * 
     * @param request
     * @param listOfValForm
     */
    private void findUsrDirList(HttpServletRequest request,
            ListOfValForm listOfValForm,HttpServletResponse response) throws IOException
    {
        ListOfValService listOfValService = (ListOfValService)getBean("listOfValService");
        ListOfValDTO listOfValDTO =  listOfValForm.getListOfValDTO();
        listOfValDTO.setCompNo(getUser(request).getCompNo());
        
        List resultList = listOfValService.findUsrDirList(listOfValDTO);
        
        super.makeJsonResult(resultList, request, response, listOfValForm.getListId());
    	
    }
    
    /**
     * code, description 을 검색 조건으로 
     * 시스템코드 테이블 검색
     * @author  javaworker
     * @version $Id: ListOfValAction.java,v 1.2 2014/01/28 07:49:29 pochul2423 Exp $
     * @since   1.0
     * 
     * @param request
     * @param listOfValForm
     * @throws JSONException 
     * @throws ParseException 
     */
    private void findSysDirList(HttpServletRequest request,
            ListOfValForm listOfValForm,HttpServletResponse response) throws IOException, ParseException, JSONException
    {
        ListOfValService listOfValService = (ListOfValService)getBean("listOfValService");
        
        List resultList = listOfValService.findSysDirList(listOfValForm.getListOfValDTO(),getUser(request));
        
        super.makeJsonResult(resultList, request, response, listOfValForm.getListId());
    	
    }
    
    /**
     * code, description 을 검색 조건으로 
     * 회사코드 테이블 검색
     * @author  javaworker
     * @version $Id: ListOfValAction.java,v 1.2 2014/01/28 07:49:29 pochul2423 Exp $
     * @since   1.0
     * 
     * @param request
     * @param listOfValForm
     * @throws JSONException 
     * @throws ParseException 
     */
    private void findCompList(HttpServletRequest request,
    		ListOfValForm listOfValForm,HttpServletResponse response) throws IOException, ParseException, JSONException
    {
    	ListOfValService listOfValService = (ListOfValService)getBean("listOfValService");
    	
    	List resultList = listOfValService.findCompList(listOfValForm.getListOfValDTO(),getUser(request));
    	
    	super.makeJsonResult(resultList, request, response, listOfValForm.getListId());
    	
    }

}