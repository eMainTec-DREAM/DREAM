package intf.dream.android.online.asset.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.android.online.asset.dto.AnOnAssetCommonDTO;
import intf.dream.android.online.asset.form.AnOnAssetListForm;
import intf.dream.android.online.asset.service.AnOnAssetListService;

/**
 * count 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/anOnAssetList" name="anOnAssetListForm"
 *                input="/android/online/asset/anOnAssetList.jsp" scope="request"
 *                validate="false"
 */
public class AnOnAssetListAction extends IfOnlineAuthAction
{
    //Asset List
    public static final String ASSET_FIND 					= "ASSET";
    //Asset PART List
    public static final String ASSET_PART_FIND 				= "ASSET_PART";
    //Asset SPEC List
    public static final String ASSET_SPEC_FIND 				= "ASSET_SPEC";
    //Asset Tool List
    public static final String ASSET_TOOL_FIND 				= "ASSET_TOOL";
    //DOCUMENT LIST
    public static final String DOCUMENT_LIST 				= "DOCUMENT_LIST";
    //DOCUMENT CTG LIST
    public static final String DOCUMENT_CTG_LIST 			= "DOCUMENT_CTG_LIST";
    //FILE_COPY
    public static final String FILE_COPY 					= "FILE_COPY";
    //WO_HIST
    public static final String WO_HIST_LIST 				= "WO_HIST_LIST";
    //Asset Insert
    public static final String ASSET_INSERT					= "ASSET_INSERT";
    //Asset Update
    public static final String ASSET_UPDATE					= "ASSET_UPDATE";

    //EQASMB LIST
    public static final String EQASMB_LIST					="EQASMB_LIST";
    //EQASMB INSERT
    public static final String EQASMB_INSERT				="EQASMB_INSERT";
    //EQASMB UPDATE
    public static final String EQASMB_UPDATE				="EQASMB_UPDATE";

    //EQSPEC LIST
    public static final String EQSPEC_LIST					="EQSPEC_LIST";
    //EQSPEC INSERT
    public static final String EQSPEC_INSERT				="EQSPEC_INSERT";
    //EQSPEC UPDATE
    public static final String EQSPEC_UPDATE				="EQSPEC_UPDATE";

    //EQPART LIST
    public static final String EQPART_LIST					="EQPART_LIST";
    //EQPART INSERT
    public static final String EQPART_INSERT				="EQPART_INSERT";
    //EQPART UPDATE
    public static final String EQPART_UPDATE				="EQPART_UPDATE";
    
    
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AnOnAssetListForm anOnAssetListForm = (AnOnAssetListForm) form;
        
        switch (anOnAssetListForm.getServiceName())
        {
            case AnOnAssetListAction.ASSET_FIND:
            	findAssetList(request, response, anOnAssetListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnAssetListAction.ASSET_PART_FIND:
            	findAssetPartList(request, response, anOnAssetListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnAssetListAction.ASSET_SPEC_FIND:
            	findAssetSpecList(request, response, anOnAssetListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnAssetListAction.ASSET_TOOL_FIND:
            	findAssetToolList(request, response, anOnAssetListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnAssetListAction.DOCUMENT_LIST:
            	findDocumentList(request, response, anOnAssetListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnAssetListAction.DOCUMENT_CTG_LIST:
            	findDocumentCtgList(request, response, anOnAssetListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnAssetListAction.FILE_COPY:
            	fileCopyList(request, response, anOnAssetListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnAssetListAction.WO_HIST_LIST:
            	findWoHistList(request, response, anOnAssetListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnAssetListAction.ASSET_INSERT:
            	insertAsset(request, response, anOnAssetListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnAssetListAction.ASSET_UPDATE:
            	updateAsset(request, response, anOnAssetListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;

            case AnOnAssetListAction.EQASMB_LIST:
            	eqAsmbList(request, response, anOnAssetListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnAssetListAction.EQASMB_INSERT:
            	insertEqAsmb(request, response, anOnAssetListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnAssetListAction.EQASMB_UPDATE:
            	updateEqAsmb(request, response, anOnAssetListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;

            case AnOnAssetListAction.EQSPEC_LIST:
            	eqSpecList(request, response, anOnAssetListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnAssetListAction.EQSPEC_INSERT:
            	insertEqSpec(request, response, anOnAssetListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnAssetListAction.EQSPEC_UPDATE:
            	updateEqSpec(request, response, anOnAssetListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;

            case AnOnAssetListAction.EQPART_LIST:
            	eqPartList(request, response, anOnAssetListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnAssetListAction.EQPART_INSERT:
            	insertEqPart(request, response, anOnAssetListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnAssetListAction.EQPART_UPDATE:
            	updateEqPart(request, response, anOnAssetListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findAssetList(HttpServletRequest request, HttpServletResponse response, AnOnAssetListForm anOnAssetListForm) throws Exception
    {
    	AnOnAssetListService anOnAssetListService = (AnOnAssetListService) getBean("anOnAssetListService");
    	AnOnAssetCommonDTO anOnAssetCommonDTO = anOnAssetListForm.getAnOnAssetCommonDTO();
    	Map map = getMapData(request);
    	
        //리스트를 조회한다.
        List resultList = anOnAssetListService.findAssetList(anOnAssetCommonDTO,map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
    private void findWoHistList(HttpServletRequest request, HttpServletResponse response, AnOnAssetListForm anOnAssetListForm) throws Exception
    {
    	AnOnAssetListService anOnAssetListService = (AnOnAssetListService) getBean("anOnAssetListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnAssetListService.findWoHistList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
    private void findAssetPartList(HttpServletRequest request, HttpServletResponse response, AnOnAssetListForm anOnAssetListForm) throws Exception
    {
    	AnOnAssetListService anOnAssetListService = (AnOnAssetListService) getBean("anOnAssetListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnAssetListService.findAssetPartList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
    private void findAssetSpecList(HttpServletRequest request, HttpServletResponse response, AnOnAssetListForm anOnAssetListForm) throws Exception
    {
    	AnOnAssetListService anOnAssetListService = (AnOnAssetListService) getBean("anOnAssetListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnAssetListService.findAssetSpecList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void findAssetToolList(HttpServletRequest request, HttpServletResponse response, AnOnAssetListForm anOnAssetListForm) throws Exception
    {
    	AnOnAssetListService anOnAssetListService = (AnOnAssetListService) getBean("anOnAssetListService");
    	
    	Map map = getMapData(request);
    	
    	//리스트를 조회한다.
    	List resultList = anOnAssetListService.findAssetToolList(map);
    	
    	// 조회한 List 를 form에 셋팅한다.
    	super.makeJsonResult(resultList, request, response);
    }
    private void findDocumentList(HttpServletRequest request, HttpServletResponse response, AnOnAssetListForm anOnAssetListForm) throws Exception
    {
    	AnOnAssetListService anOnAssetListService = (AnOnAssetListService) getBean("anOnAssetListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnAssetListService.findDocumentList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void findDocumentCtgList(HttpServletRequest request, HttpServletResponse response, AnOnAssetListForm anOnAssetListForm) throws Exception
    {
    	AnOnAssetListService anOnAssetListService = (AnOnAssetListService) getBean("anOnAssetListService");
    	
    	Map map = getMapData(request);
    	
    	//리스트를 조회한다.
    	List resultList = anOnAssetListService.findDocumentCtgList(map);
    	
    	// 조회한 List 를 form에 셋팅한다.
    	super.makeJsonResult(resultList, request, response);
    }
    private void fileCopyList(HttpServletRequest request, HttpServletResponse response, AnOnAssetListForm anOnAssetListForm) throws Exception
    {
    	AnOnAssetListService anOnAssetListService = (AnOnAssetListService) getBean("anOnAssetListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnAssetListService.fileCopyList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void insertAsset(HttpServletRequest request, HttpServletResponse response, AnOnAssetListForm anOnAssetListForm) throws Exception
    {
    	AnOnAssetListService anOnAssetListService = (AnOnAssetListService) getBean("anOnAssetListService");
    	List list = getListData(request);
    	int qty = anOnAssetListService.insertAsset(list);
    	setMessage(response, "","OK"); 
    }
    private void updateAsset(HttpServletRequest request, HttpServletResponse response, AnOnAssetListForm anOnAssetListForm) throws Exception
    {
    	AnOnAssetListService anOnAssetListService = (AnOnAssetListService) getBean("anOnAssetListService");
    	List list = getListData(request);
    	int qty = anOnAssetListService.updateAsset(list);
    	setMessage(response, "","OK"); 
    }
    
    private void eqAsmbList(HttpServletRequest request, HttpServletResponse response, AnOnAssetListForm anOnAssetListForm) throws Exception
    {
    	AnOnAssetListService anOnAssetListService = (AnOnAssetListService) getBean("anOnAssetListService");
    	Map map = getMapData(request);
        List resultList = anOnAssetListService.eqAsmbList(map);
        super.makeJsonResult(resultList, request, response);
    }
    private void insertEqAsmb(HttpServletRequest request, HttpServletResponse response, AnOnAssetListForm anOnAssetListForm) throws Exception
    {
    	AnOnAssetListService anOnAssetListService = (AnOnAssetListService) getBean("anOnAssetListService");
    	List list = getListData(request);
    	int qty = anOnAssetListService.insertEqAsmb(list);
    	setMessage(response, "","OK"); 
    }
    private void updateEqAsmb(HttpServletRequest request, HttpServletResponse response, AnOnAssetListForm anOnAssetListForm) throws Exception
    {
    	AnOnAssetListService anOnAssetListService = (AnOnAssetListService) getBean("anOnAssetListService");
    	List list = getListData(request);
    	int qty = anOnAssetListService.updateEqAsmb(list);
    	setMessage(response, "","OK"); 
    }
    
    private void eqSpecList(HttpServletRequest request, HttpServletResponse response, AnOnAssetListForm anOnAssetListForm) throws Exception
    {
    	AnOnAssetListService anOnAssetListService = (AnOnAssetListService) getBean("anOnAssetListService");
    	Map map = getMapData(request);
        List resultList = anOnAssetListService.eqSpecList(map);
        super.makeJsonResult(resultList, request, response);
    }
    private void insertEqSpec(HttpServletRequest request, HttpServletResponse response, AnOnAssetListForm anOnAssetListForm) throws Exception
    {
    	AnOnAssetListService anOnAssetListService = (AnOnAssetListService) getBean("anOnAssetListService");
    	List list = getListData(request);
    	int qty = anOnAssetListService.insertEqSpec(list);
    	setMessage(response, "","OK"); 
    }
    private void updateEqSpec(HttpServletRequest request, HttpServletResponse response, AnOnAssetListForm anOnAssetListForm) throws Exception
    {
    	AnOnAssetListService anOnAssetListService = (AnOnAssetListService) getBean("anOnAssetListService");
    	List list = getListData(request);
    	int qty = anOnAssetListService.updateEqSpec(list);
    	setMessage(response, "","OK"); 
    }
    
    private void eqPartList(HttpServletRequest request, HttpServletResponse response, AnOnAssetListForm anOnAssetListForm) throws Exception
    {
    	AnOnAssetListService anOnAssetListService = (AnOnAssetListService) getBean("anOnAssetListService",request);
    	Map map = getMapData(request);
        List resultList = anOnAssetListService.eqPartList(map);
        super.makeJsonResult(resultList, request, response);
    }
    private void insertEqPart(HttpServletRequest request, HttpServletResponse response, AnOnAssetListForm anOnAssetListForm) throws Exception
    {
    	AnOnAssetListService anOnAssetListService = (AnOnAssetListService) getBean("anOnAssetListService");
    	List list = getListData(request);
    	int qty = anOnAssetListService.insertEqPart(list);
    	setMessage(response, "","OK"); 
    }
    private void updateEqPart(HttpServletRequest request, HttpServletResponse response, AnOnAssetListForm anOnAssetListForm) throws Exception
    {
    	AnOnAssetListService anOnAssetListService = (AnOnAssetListService) getBean("anOnAssetListService");
    	List list = getListData(request);
    	int qty = anOnAssetListService.updateEqPart(list);
    	setMessage(response, "","OK"); 
    }
    
}
