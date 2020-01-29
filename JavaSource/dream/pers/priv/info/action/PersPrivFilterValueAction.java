package dream.pers.priv.info.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.pers.priv.info.dto.PersPrivFilterValueDTO;
import dream.pers.priv.info.form.PersPrivFilterValueForm;
import dream.pers.priv.info.service.PersPrivFilterValueService;

/**
 * 목록
 * @author  euna0207
 * @version $Id$
 * @since   1.0
 * @struts:action path="/persPrivFilterValueList" name="persPrivFilterValueForm"
 *                input="/dream/pers/priv/info/persPrivFilterValueList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/persPrivFilterValueDetail" name="persPrivFilterValueForm"
 *                input="/dream/pers/priv/info/persPrivFilterValueDetail.jsp" scope="request"
 *                validate="false"                
 * @struts.action-forward name="persPrivFilterValueList" path="/dream/pers/priv/info/persPrivFilterValueList.jsp"
 *                        redirect="false" 
 * @struts.action-forward name="persPrivFilterValueDetail" path="/dream/pers/priv/info/persPrivFilterValueDetail.jsp"
 *                        redirect="false"
 *                        
 */
public class PersPrivFilterValueAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int LIST_DELETE 	= 7002;
    /** 저장 */
    public static final int DETAIL_INPUT 	= 8002;
   
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        PersPrivFilterValueForm persPrivFilterValueForm = (PersPrivFilterValueForm) form;
        System.out.println(persPrivFilterValueForm.getStrutsAction());
        
        switch (persPrivFilterValueForm.getStrutsAction())
        {
        
            case PersPrivFilterValueAction.LIST_FIND:
                findList(request,response, persPrivFilterValueForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case PersPrivFilterValueAction.BASE_GRID_EXPORT:
            	findList(request,response, persPrivFilterValueForm, true);
            	returnActionForward = new ActionForward("/gridExport");
            	break;
            case PersPrivFilterValueAction.BASE_SET_HEADER:
            	super.setHeader(request, response, persPrivFilterValueForm.getListId(), persPrivFilterValueForm.getCurrentPageId());
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case PersPrivFilterValueAction.LIST_DELETE:
            	deleteList(request,persPrivFilterValueForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case PersPrivFilterValueAction.DETAIL_INPUT:
            	insertDetail(request,persPrivFilterValueForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;            	
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    

	/**
     * grid find
     * @author  euna0207
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param persPrivFilterValueForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request,HttpServletResponse response, PersPrivFilterValueForm persPrivFilterValueForm, boolean excelExport) throws Exception
    {
        PersPrivFilterValueService persPrivFilterValueService = (PersPrivFilterValueService) getBean("persPrivFilterValueService");
        PersPrivFilterValueDTO persPrivFilterValueDTO = persPrivFilterValueForm.getPersPrivFilterValueDTO();
     
        //Paging
        persPrivFilterValueDTO.setIsLoadMaxCount("Y".equals(persPrivFilterValueForm.getIsLoadMaxCount())?true:false);
        persPrivFilterValueDTO.setFirstRow(persPrivFilterValueForm.getFirstRow());
        persPrivFilterValueDTO.setOrderBy(persPrivFilterValueForm.getOrderBy());
        persPrivFilterValueDTO.setDirection(persPrivFilterValueForm.getDirection());
        
        User user = getUser(request);
        List resultList = persPrivFilterValueService.findList(persPrivFilterValueDTO, getUser(request));
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(persPrivFilterValueForm.getIsTotalCount()) == 0 && !excelExport) totalCount = persPrivFilterValueService.findTotalCount(persPrivFilterValueDTO, user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, persPrivFilterValueForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  euna0207
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param persPrivFilterValueForm
     * @throws Exception
     */
    private void deleteList(HttpServletRequest request, PersPrivFilterValueForm persPrivFilterValueForm) throws Exception
    {
    	PersPrivFilterValueService persPrivFilterValueService = (PersPrivFilterValueService) getBean("persPrivFilterValueService");
    	String[] deleteRows = persPrivFilterValueForm.getDeleteRows();
    
    	User user = getUser(request);
    	persPrivFilterValueService.deleteList(deleteRows, user);
    	
    	setAjaxStatus(request);
    }

    private void insertDetail(HttpServletRequest request, PersPrivFilterValueForm persPrivFilterValueForm) throws Exception {
    	PersPrivFilterValueService persPrivFilterValueService = (PersPrivFilterValueService) getBean("persPrivFilterValueService");
    	PersPrivFilterValueDTO  persPrivFilterValueDTO = persPrivFilterValueForm.getPersPrivFilterValueDTO();
    	
    	User user = getUser(request);
    	
    	persPrivFilterValueService.insertDetail(persPrivFilterValueDTO, user);
        setAjaxStatus(request);	
    }
}