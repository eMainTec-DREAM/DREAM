package dream.pers.priv.pgm.action;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.main.service.MainService;
import dream.pers.priv.pgm.dto.PersPrivUsrFieldCommonDTO;
import dream.pers.priv.pgm.form.PersPrivUsrFieldListForm;
import dream.pers.priv.pgm.service.PersPrivUsrFieldListService;

/**
 * 화면별 필드 목록
 * @author  kim21017
 * @version $Id: MaPgUsrFieldListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/persPrivUsrFieldList" name="persPrivUsrFieldListForm"
 *                input="/dream/pers/priv/pgm/persPrivUsrFieldList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="persPrivUsrFieldList" path="/dream/pers/priv/pgm/persPrivUsrFieldList.jsp"
 *                        redirect="false"
 */
public class PersPrivUsrFieldListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int PG_FIELD_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int PG_FIELD_LIST_DELETE 	= 1002;
    /** 초기 필드 생성 */
    public static final int PG_FIELD_CREATE         = 1003;
    /** 필드 숨기기 */
    public static final int PG_FIELD_HIDE           = 1004;
    /** 필드 보이기 */
    public static final int PG_FIELD_SHOW           = 1005;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        PersPrivUsrFieldListForm maPgUsrFieldListForm = (PersPrivUsrFieldListForm) form;
        
        switch (maPgUsrFieldListForm.getStrutsAction())
        {
        
            case PersPrivUsrFieldListAction.PG_FIELD_LIST_FIND:
                findFieldList(request,response, maPgUsrFieldListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case PersPrivUsrFieldListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, maPgUsrFieldListForm.getListId(), maPgUsrFieldListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case PersPrivUsrFieldListAction.PG_FIELD_LIST_DELETE:
            	deleteFieldList(request,maPgUsrFieldListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case PersPrivUsrFieldListAction.PG_FIELD_HIDE:
                hideFieldList(request,maPgUsrFieldListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case PersPrivUsrFieldListAction.PG_FIELD_SHOW:
                showFieldList(request,maPgUsrFieldListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case PersPrivUsrFieldListAction.PG_FIELD_CREATE:
                createFieldList(request,maPgUsrFieldListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case PersPrivUsrFieldListAction.BASE_GRID_EXPORT:
            	findFieldList(request,response, maPgUsrFieldListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("persPrivUsrFieldList");
                break;
        }

        return returnActionForward;
    }
    
    private void showFieldList(HttpServletRequest request,  PersPrivUsrFieldListForm maPgUsrFieldListForm) throws Exception
    {
        PersPrivUsrFieldListService maPgUsrFieldListService = (PersPrivUsrFieldListService) getBean("persPrivUsrFieldListService");
        
        String[] deleteRows = maPgUsrFieldListForm.getDeleteRows();
    
        maPgUsrFieldListService.hideFieldList(deleteRows, getUser(request), false);
        
        HttpSession session = request.getSession();
        MainService mainService = (MainService) getBean("mainService");
        Hashtable pageFields = mainService.findPageFields(getUser(request));
        session.setAttribute("PAGEFIELD", pageFields);

        setAjaxStatus(request);
    }

    private void hideFieldList(HttpServletRequest request,  PersPrivUsrFieldListForm maPgUsrFieldListForm) throws Exception
    {
        PersPrivUsrFieldListService maPgUsrFieldListService = (PersPrivUsrFieldListService) getBean("persPrivUsrFieldListService");
        
        String[] deleteRows = maPgUsrFieldListForm.getDeleteRows();
    
        maPgUsrFieldListService.hideFieldList(deleteRows, getUser(request), true);
        
        HttpSession session = request.getSession();
        MainService mainService = (MainService) getBean("mainService");
        Hashtable pageFields = mainService.findPageFields(getUser(request));
        session.setAttribute("PAGEFIELD", pageFields);

        setAjaxStatus(request);
    }

    private void createFieldList(HttpServletRequest request, PersPrivUsrFieldListForm maPgUsrFieldListForm)
    {
        PersPrivUsrFieldListService maPgUsrFieldListService = (PersPrivUsrFieldListService) getBean("persPrivUsrFieldListService");

        PersPrivUsrFieldCommonDTO persPrivUsrFieldCommonDTO = maPgUsrFieldListForm.getPersPrivUsrFieldCommonDTO();
        persPrivUsrFieldCommonDTO.setUserLang(getUser(request).getLangId());
        
        maPgUsrFieldListService.createFieldList(persPrivUsrFieldCommonDTO, getUser(request));
        
        //다시 로드!
        HttpSession session = request.getSession();
        MainService mainService = (MainService) getBean("mainService");
        Hashtable pageFields = mainService.findPageFields(getUser(request));
        session.setAttribute("PAGEFIELD", pageFields);
    }

    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaPgUsrFieldListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maPgUsrFieldListForm
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     * @throws ClassNotFoundException 
     * @throws IOException 
     * @throws Exception
     */
    private void findFieldList(HttpServletRequest request,HttpServletResponse response, PersPrivUsrFieldListForm maPgUsrFieldListForm, boolean excelExport) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException 
    {
        PersPrivUsrFieldListService maPgUsrFieldListService = (PersPrivUsrFieldListService) getBean("persPrivUsrFieldListService");
 
        PersPrivUsrFieldCommonDTO persPrivUsrFieldCommonDTO = maPgUsrFieldListForm.getPersPrivUsrFieldCommonDTO();
        persPrivUsrFieldCommonDTO.setUserLang(getUser(request).getLangId());
        
        //Paging
        persPrivUsrFieldCommonDTO.setIsLoadMaxCount("Y".equals(maPgUsrFieldListForm.getIsLoadMaxCount())?true:false);
        persPrivUsrFieldCommonDTO.setFirstRow(maPgUsrFieldListForm.getFirstRow());
        persPrivUsrFieldCommonDTO.setOrderBy(maPgUsrFieldListForm.getOrderBy());
        persPrivUsrFieldCommonDTO.setDirection(maPgUsrFieldListForm.getDirection());
        
        List resultList = maPgUsrFieldListService.findFieldList(persPrivUsrFieldCommonDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(maPgUsrFieldListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maPgUsrFieldListService.findTotalCount(persPrivUsrFieldCommonDTO,getUser(request));
        
        if(excelExport) super.makeGridExport(resultList, request, response,maPgUsrFieldListForm.getListId(),maPgUsrFieldListForm.getCurrentPageId(), maPgUsrFieldListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: MaPgUsrFieldListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maPgUsrFieldListForm
     * @throws Exception
     */
    private void deleteFieldList(HttpServletRequest request, PersPrivUsrFieldListForm maPgUsrFieldListForm) throws Exception
    {
    	PersPrivUsrFieldListService maPgUsrFieldListService = (PersPrivUsrFieldListService) getBean("persPrivUsrFieldListService");
        
    	String[] deleteRows = maPgUsrFieldListForm.getDeleteRows();
    
    	maPgUsrFieldListService.deleteFieldList(deleteRows);
    	
    	MainService mainService = (MainService) getBean("mainService"); 
        HttpSession session = request.getSession();
        Hashtable pageFields = mainService.findPageFields(getUser(request));
        session.setAttribute("PAGEFIELD", pageFields);
        
    	setAjaxStatus(request);
    }
}