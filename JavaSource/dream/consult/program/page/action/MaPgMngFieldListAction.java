package dream.consult.program.page.action;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;
import dream.consult.program.page.dto.MaPgMngCommonDTO;
import dream.consult.program.page.dto.MaPgMngFieldListDTO;
import dream.consult.program.page.form.MaPgMngFieldListForm;
import dream.consult.program.page.service.MaPgMngFieldListService;
import dream.main.service.MainService;

/**
 * 화면별 필드 목록
 * @author  kim21017
 * @version $Id: MaPgMngFieldListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maPgMngFieldList" name="maPgMngFieldListForm"
 *                input="/dream/consult/program/page/maPgMngFieldList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maPgFieldMngList" name="maPgMngFieldListForm"
 *                input="/dream/consult/program/page/maPgFieldMngList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPgMngFieldList" path="/dream/consult/program/page/maPgMngFieldList.jsp"
 *                        redirect="false"
 */
public class MaPgMngFieldListAction extends ConsultAuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int PG_FIELD_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int PG_FIELD_LIST_DELETE 		= 1002;
    /** 시스템셋팅Y */
    public static final int PG_FIELD_LIST_SYSY         = 1003;
    /** 시스템셋팅N */
    public static final int PG_FIELD_LIST_SYSN         = 1004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPgMngFieldListForm maPgMngFieldListForm = (MaPgMngFieldListForm) form;
        
        switch (maPgMngFieldListForm.getStrutsAction())
        {
        
            case MaPgMngFieldListAction.PG_FIELD_LIST_FIND:
                findFieldList(request,response, maPgMngFieldListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPgMngFieldListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, maPgMngFieldListForm.getListId(), maPgMngFieldListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPgMngFieldListAction.PG_FIELD_LIST_DELETE:
            	deleteFieldList(request,maPgMngFieldListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPgMngFieldListAction.PG_FIELD_LIST_SYSY:
                sysYColList(request,maPgMngFieldListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaPgMngFieldListAction.PG_FIELD_LIST_SYSN:
                sysNColList(request,maPgMngFieldListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaPgMngFieldListAction.BASE_GRID_EXPORT:
            	findFieldList(request,response, maPgMngFieldListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaPgMngFieldListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maPgMngFieldListForm
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     * @throws ClassNotFoundException 
     * @throws IOException 
     * @throws Exception
     */
    private void findFieldList(HttpServletRequest request,HttpServletResponse response, MaPgMngFieldListForm maPgMngFieldListForm, boolean excelExport) throws Exception 
    {
        MaPgMngFieldListService maPgMngFieldListService = (MaPgMngFieldListService) getBean("maPgMngFieldListService");
        
        MaPgMngCommonDTO maPgMngCommonDTO = maPgMngFieldListForm.getMaPgMngCommonDTO();
        MaPgMngFieldListDTO maPgMngFieldListDTO = maPgMngFieldListForm.getMaPgMngFieldListDTO();
        maPgMngCommonDTO.setUserLang(getUser(request).getLangId());
        
        
        //Paging
        maPgMngCommonDTO.setIsLoadMaxCount("Y".equals(maPgMngFieldListForm.getIsLoadMaxCount())?true:false);
        maPgMngCommonDTO.setFirstRow(maPgMngFieldListForm.getFirstRow());
        maPgMngCommonDTO.setOrderBy(maPgMngFieldListForm.getOrderBy());
        maPgMngCommonDTO.setDirection(maPgMngFieldListForm.getDirection());
        
        List resultList = maPgMngFieldListService.findFieldList(maPgMngCommonDTO, maPgMngFieldListDTO);
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(maPgMngFieldListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maPgMngFieldListService.findTotalCount(maPgMngCommonDTO,maPgMngFieldListDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,maPgMngFieldListForm.getListId(),maPgMngFieldListForm.getCurrentPageId(), maPgMngFieldListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: MaPgMngFieldListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maPgMngFieldListForm
     * @throws Exception
     */
    private void deleteFieldList(HttpServletRequest request, MaPgMngFieldListForm maPgMngFieldListForm) throws Exception
    {
    	MaPgMngFieldListService maPgMngFieldListService = (MaPgMngFieldListService) getBean("maPgMngFieldListService");
        
    	String[] deleteRows = maPgMngFieldListForm.getDeleteRows();
    
    	maPgMngFieldListService.deleteFieldList(deleteRows);
    	
    	MainService mainService = (MainService) getBean("mainService"); 
        HttpSession session = request.getSession();
        Hashtable pageFields = mainService.findPageFields(getUser(request));
        session.setAttribute("PAGEFIELD", pageFields);
        
    	setAjaxStatus(request);
    }
    
    /**
     * setSystemY
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPgMngFieldListForm
     * @throws Exception
     */
    private void sysYColList(HttpServletRequest request, MaPgMngFieldListForm maPgMngFieldListForm) throws Exception
    {
        MaPgMngFieldListService maPgMngFieldListService = (MaPgMngFieldListService) getBean("maPgMngFieldListService");
        
        String[] deleteRows = maPgMngFieldListForm.getDeleteRows();
    
        maPgMngFieldListService.sysYColList(deleteRows);
        
        setAjaxStatus(request);
    }
    /**
     * setSystemN
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPgMngFieldListForm
     * @throws Exception
     */
    private void sysNColList(HttpServletRequest request, MaPgMngFieldListForm maPgMngFieldListForm) throws Exception
    {
        MaPgMngFieldListService maPgMngFieldListService = (MaPgMngFieldListService) getBean("maPgMngFieldListService");
        
        String[] deleteRows = maPgMngFieldListForm.getDeleteRows();
    
        maPgMngFieldListService.sysNColList(deleteRows);
        
        setAjaxStatus(request);
    }
}