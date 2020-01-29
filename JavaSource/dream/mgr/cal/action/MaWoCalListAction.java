package dream.mgr.cal.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.mgr.cal.dto.MaWoCalCommonDTO;
import dream.mgr.cal.form.MaWoCalListForm;
import dream.mgr.cal.service.MaWoCalListService;

/**
 * Working Calendar - 목록 action
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/maWoCalList" name="maWoCalListForm"
 *                input="/dream/mgr/cal/maWoCalList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoCalPopup" name="maWoCalListForm"
 *                input="/dream/mgr/cal/maWoCalPopup.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maWoCalList" path="/dream/mgr/cal/maWoCalList.jsp"
 *                        redirect="false"
 */
public class MaWoCalListAction extends AuthAction
{
    /** 조회 */
    public static final int WO_CAL_LIST_FIND 	= 1001;
    /** 근무지정 */
    public static final int WO_CAL_LIST_DAYON 	= 6002;
    /** 휴무지정 */
    public static final int WO_CAL_LIST_DAYOFF 	= 6003;
    /** 팝업 */
    public static final int WO_CAL_POPUP_DEFAULT= 1004;
    /** 팝업 저장 */
    public static final int WO_CAL_POPUP_SAVE   = 5005;
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaWoCalListForm maWoCalListForm = (MaWoCalListForm) form;
        
        switch (maWoCalListForm.getStrutsAction())
        {
            case MaWoCalListAction.BASE_SET_HEADER:
                setHeader(request, response, maWoCalListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoCalListAction.WO_CAL_LIST_FIND:
                findList(request, response, maWoCalListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case MaWoCalListAction.WO_CAL_LIST_DAYON:
            	dayOnList(request, maWoCalListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaWoCalListAction.WO_CAL_LIST_DAYOFF:
            	dayOffList(request, maWoCalListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaWoCalListAction.BASE_GRID_EXPORT:
            	findList(request, response, maWoCalListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            case MaWoCalListAction.WO_CAL_POPUP_SAVE:
            	popupSave(request, maWoCalListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaWoCalListAction.WO_CAL_POPUP_DEFAULT:
                returnActionForward = mapping.findForward("maWoCalPopup");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaWoCalListForm maWoCalListForm) throws IOException
    {
        super.setHeader(request, response, maWoCalListForm.getListId(), maWoCalListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maWoCalListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, MaWoCalListForm maWoCalListForm, boolean excelExport) throws IOException
    {
    	MaWoCalListService maWoCalListService = (MaWoCalListService) getBean("maWoCalListService");        
    	MaWoCalCommonDTO maWoCalCommonDTO = maWoCalListForm.getMaWoCalCommonDTO();

    	//comp_no 를 셋팅한다.
    	maWoCalCommonDTO.setCompNo(getUser(request).getCompNo());
    	maWoCalCommonDTO.setUserLang(getUser(request).getLangId());
    	
    	//Paging
    	maWoCalCommonDTO.setIsLoadMaxCount("Y".equals(maWoCalListForm.getIsLoadMaxCount())?true:false);
    	maWoCalCommonDTO.setFirstRow(maWoCalListForm.getFirstRow());
    	maWoCalCommonDTO.setOrderBy(maWoCalListForm.getOrderBy());
    	maWoCalCommonDTO.setDirection(maWoCalListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = maWoCalListService.findList(maWoCalCommonDTO,getUser(request));
        
        //Paging
        String totalCount = "";
                
        if(Integer.parseInt(maWoCalListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maWoCalListService.findTotalCount(maWoCalCommonDTO,getUser(request));
                
        if(excelExport) super.makeGridExport(resultList, request, response, maWoCalListForm.getListId(),maWoCalListForm.getCurrentPageId(), maWoCalListForm.getFileName());
        // 조회한 List 를 form에 셋팅한다.
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * 근무지정
     * @author  kim21017
     * @version $Id: MaWoCalListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maBatchMngListForm
     */
    private void dayOnList(HttpServletRequest request, MaWoCalListForm maWoCalListForm) throws Exception
    {
    	MaWoCalListService maWoCalListService = (MaWoCalListService) getBean("maWoCalListService");        
    	
    	String[] selectRows = maWoCalListForm.getDeleteRows();
    	String[] selectRowsExt = maWoCalListForm.getDeleteRowsExt();
    	
    	MaWoCalCommonDTO maWoCalCommonDTO = maWoCalListForm.getMaWoCalCommonDTO();
    	//comp_no 를 셋팅한다.
    	maWoCalCommonDTO.setCompNo(getUser(request).getCompNo());
    	maWoCalCommonDTO.setUserLang(getUser(request).getLangId());
    	maWoCalCommonDTO.setFilterPlantId(getUser(request).getPlant());
    	maWoCalListService.dayOnList(getUser(request).getCompNo(), selectRows, getUser(request), selectRowsExt);
        
        setAjaxStatus(request);
    }
    /**
     * 휴무지정
     * @author  kim21017
     * @version $Id: MaWoCalListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maBatchMngListForm
     */
    private void dayOffList(HttpServletRequest request, MaWoCalListForm maWoCalListForm) throws Exception
    {
    	MaWoCalListService maWoCalListService = (MaWoCalListService) getBean("maWoCalListService");        
    	
    	String[] selectRows = maWoCalListForm.getSelectRows();
    	MaWoCalCommonDTO maWoCalCommonDTO = maWoCalListForm.getMaWoCalCommonDTO();
    	//comp_no 를 셋팅한다.
    	maWoCalCommonDTO.setCompNo(getUser(request).getCompNo());
    	maWoCalCommonDTO.setUserLang(getUser(request).getLangId());
    	maWoCalCommonDTO.setFilterPlantId(getUser(request).getPlant());
    	maWoCalListService.dayOffList(getUser(request).getCompNo(), selectRows,getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * 휴무지정
     * @author  kim21017
     * @version $Id: MaWoCalListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maBatchMngListForm
     */
    private void popupSave(HttpServletRequest request, MaWoCalListForm maWoCalListForm) throws Exception
    {
    	MaWoCalListService maWoCalListService = (MaWoCalListService) getBean("maWoCalListService");        
    	
    	//String[] selectRows = maWoCalListForm.getSelectRows();
    	
    	maWoCalListService.popupSave(getUser(request).getCompNo(), getUser(request).getUserNo(),getUser(request).getPlant(),maWoCalListForm);
        
        setAjaxStatus(request);
    }
    
}
