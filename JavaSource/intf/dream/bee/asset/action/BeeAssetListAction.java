package intf.dream.bee.asset.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import common.util.CommonUtil;
import intf.dream.bee.asset.dto.BeeAssetCommonDTO;
import intf.dream.bee.asset.form.BeeAssetListForm;
import intf.dream.bee.asset.service.BeeAssetListService;

/**
 * count 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/beeAssetList" name="beeAssetListForm"
 *                input="/bee/asset/beeAssetList.jsp" scope="request"
 *                validate="false"
 */
public class BeeAssetListAction extends IfOnlineAuthAction
{
    //Asset List
    public static final String ASSET_FIND 					= "ASSET";
    //Asset List Count
    public static final String ASSET_COUNT_FIND 			= "ASSET_COUNT";
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
        BeeAssetListForm beeAssetListForm = (BeeAssetListForm) form;
        
        super.updateAudit(request);
        
        switch (beeAssetListForm.getServiceName())
        {
            case BeeAssetListAction.ASSET_FIND:
            	findAssetList(request, response, beeAssetListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeeAssetListAction.ASSET_COUNT_FIND:
            	findAssetCount(request, response, beeAssetListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeeAssetListAction.ASSET_PART_FIND:
            	findAssetPartList(request, response, beeAssetListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeeAssetListAction.ASSET_SPEC_FIND:
            	findAssetSpecList(request, response, beeAssetListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeeAssetListAction.ASSET_TOOL_FIND:
            	findAssetToolList(request, response, beeAssetListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeeAssetListAction.DOCUMENT_LIST:
            	findDocumentList(request, response, beeAssetListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeAssetListAction.DOCUMENT_CTG_LIST:
            	findDocumentCtgList(request, response, beeAssetListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeAssetListAction.FILE_COPY:
            	fileCopyList(request, response, beeAssetListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeAssetListAction.WO_HIST_LIST:
            	findWoHistList(request, response, beeAssetListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeAssetListAction.ASSET_INSERT:
            	insertAsset(request, response, beeAssetListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeAssetListAction.ASSET_UPDATE:
            	updateAsset(request, response, beeAssetListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;

            case BeeAssetListAction.EQASMB_LIST:
            	eqAsmbList(request, response, beeAssetListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeAssetListAction.EQASMB_INSERT:
            	insertEqAsmb(request, response, beeAssetListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeAssetListAction.EQASMB_UPDATE:
            	updateEqAsmb(request, response, beeAssetListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;

            case BeeAssetListAction.EQSPEC_LIST:
            	eqSpecList(request, response, beeAssetListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeAssetListAction.EQSPEC_INSERT:
            	insertEqSpec(request, response, beeAssetListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeAssetListAction.EQSPEC_UPDATE:
            	updateEqSpec(request, response, beeAssetListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;

            case BeeAssetListAction.EQPART_LIST:
            	eqPartList(request, response, beeAssetListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeAssetListAction.EQPART_INSERT:
            	insertEqPart(request, response, beeAssetListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeAssetListAction.EQPART_UPDATE:
            	updateEqPart(request, response, beeAssetListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findAssetList(HttpServletRequest request, HttpServletResponse response, BeeAssetListForm beeAssetListForm) throws Exception
    {
    	BeeAssetListService beeAssetListService = (BeeAssetListService) getBean("beeAssetListService");
    	BeeAssetCommonDTO beeAssetCommonDTO = beeAssetListForm.getBeeAssetCommonDTO();
    	Map map = getMapData(request);
    	
    	beeAssetCommonDTO.setIsLoadMaxCount(true);
    	if("".equals(CommonUtil.convertString(String.valueOf(map.get("nextStartRow"))))){
    		beeAssetCommonDTO.setIsLoadMaxCount(false);
    	}
    	beeAssetCommonDTO.setFirstRow(CommonUtil.convertString(String.valueOf(map.get("nextStartRow"))));
    	
        //리스트를 조회한다.
        List resultList = beeAssetListService.findAssetList(beeAssetCommonDTO,map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void findAssetCount(HttpServletRequest request, HttpServletResponse response, BeeAssetListForm beeAssetListForm) throws Exception
    {
    	BeeAssetListService beeAssetListService = (BeeAssetListService) getBean("beeAssetListService");
    	BeeAssetCommonDTO beeAssetCommonDTO = beeAssetListForm.getBeeAssetCommonDTO();
    	
    	Map map = getMapData(request);
    	
    	//리스트를 조회한다.
    	List resultList = beeAssetListService.findAssetCount(beeAssetCommonDTO,map);
    	
    	// 조회한 List 를 form에 셋팅한다.
    	super.makeJsonResult(resultList, request, response);
    }
    private void findWoHistList(HttpServletRequest request, HttpServletResponse response, BeeAssetListForm beeAssetListForm) throws Exception
    {
    	BeeAssetListService beeAssetListService = (BeeAssetListService) getBean("beeAssetListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beeAssetListService.findWoHistList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
    private void findAssetPartList(HttpServletRequest request, HttpServletResponse response, BeeAssetListForm beeAssetListForm) throws Exception
    {
    	BeeAssetListService beeAssetListService = (BeeAssetListService) getBean("beeAssetListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beeAssetListService.findAssetPartList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
    private void findAssetSpecList(HttpServletRequest request, HttpServletResponse response, BeeAssetListForm beeAssetListForm) throws Exception
    {
    	BeeAssetListService beeAssetListService = (BeeAssetListService) getBean("beeAssetListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beeAssetListService.findAssetSpecList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void findAssetToolList(HttpServletRequest request, HttpServletResponse response, BeeAssetListForm beeAssetListForm) throws Exception
    {
    	BeeAssetListService beeAssetListService = (BeeAssetListService) getBean("beeAssetListService");
    	
    	Map map = getMapData(request);
    	
    	//리스트를 조회한다.
    	List resultList = beeAssetListService.findAssetToolList(map);
    	
    	// 조회한 List 를 form에 셋팅한다.
    	super.makeJsonResult(resultList, request, response);
    }
    private void findDocumentList(HttpServletRequest request, HttpServletResponse response, BeeAssetListForm beeAssetListForm) throws Exception
    {
    	BeeAssetListService beeAssetListService = (BeeAssetListService) getBean("beeAssetListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beeAssetListService.findDocumentList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void findDocumentCtgList(HttpServletRequest request, HttpServletResponse response, BeeAssetListForm beeAssetListForm) throws Exception
    {
    	BeeAssetListService beeAssetListService = (BeeAssetListService) getBean("beeAssetListService");
    	
    	Map map = getMapData(request);
    	
    	//리스트를 조회한다.
    	List resultList = beeAssetListService.findDocumentCtgList(map);
    	
    	// 조회한 List 를 form에 셋팅한다.
    	super.makeJsonResult(resultList, request, response);
    }
    private void fileCopyList(HttpServletRequest request, HttpServletResponse response, BeeAssetListForm beeAssetListForm) throws Exception
    {
    	BeeAssetListService beeAssetListService = (BeeAssetListService) getBean("beeAssetListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beeAssetListService.fileCopyList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void insertAsset(HttpServletRequest request, HttpServletResponse response, BeeAssetListForm beeAssetListForm) throws Exception
    {
    	BeeAssetListService beeAssetListService = (BeeAssetListService) getBean("beeAssetListService");
    	List list = getListData(request);
    	int qty = beeAssetListService.insertAsset(list);
    	setMessage(response, "","OK"); 
    }
    private void updateAsset(HttpServletRequest request, HttpServletResponse response, BeeAssetListForm beeAssetListForm) throws Exception
    {
    	BeeAssetListService beeAssetListService = (BeeAssetListService) getBean("beeAssetListService");
    	List list = getListData(request);
    	int qty = beeAssetListService.updateAsset(list);
    	setMessage(response, "","OK"); 
    }
    
    private void eqAsmbList(HttpServletRequest request, HttpServletResponse response, BeeAssetListForm beeAssetListForm) throws Exception
    {
    	BeeAssetListService beeAssetListService = (BeeAssetListService) getBean("beeAssetListService");
    	Map map = getMapData(request);
        List resultList = beeAssetListService.eqAsmbList(map);
        super.makeJsonResult(resultList, request, response);
    }
    private void insertEqAsmb(HttpServletRequest request, HttpServletResponse response, BeeAssetListForm beeAssetListForm) throws Exception
    {
    	BeeAssetListService beeAssetListService = (BeeAssetListService) getBean("beeAssetListService");
    	List list = getListData(request);
    	int qty = beeAssetListService.insertEqAsmb(list);
    	setMessage(response, "","OK"); 
    }
    private void updateEqAsmb(HttpServletRequest request, HttpServletResponse response, BeeAssetListForm beeAssetListForm) throws Exception
    {
    	BeeAssetListService beeAssetListService = (BeeAssetListService) getBean("beeAssetListService");
    	List list = getListData(request);
    	int qty = beeAssetListService.updateEqAsmb(list);
    	setMessage(response, "","OK"); 
    }
    
    private void eqSpecList(HttpServletRequest request, HttpServletResponse response, BeeAssetListForm beeAssetListForm) throws Exception
    {
    	BeeAssetListService beeAssetListService = (BeeAssetListService) getBean("beeAssetListService");
    	Map map = getMapData(request);
        List resultList = beeAssetListService.eqSpecList(map);
        super.makeJsonResult(resultList, request, response);
    }
    private void insertEqSpec(HttpServletRequest request, HttpServletResponse response, BeeAssetListForm beeAssetListForm) throws Exception
    {
    	BeeAssetListService beeAssetListService = (BeeAssetListService) getBean("beeAssetListService");
    	List list = getListData(request);
    	int qty = beeAssetListService.insertEqSpec(list);
    	setMessage(response, "","OK"); 
    }
    private void updateEqSpec(HttpServletRequest request, HttpServletResponse response, BeeAssetListForm beeAssetListForm) throws Exception
    {
    	BeeAssetListService beeAssetListService = (BeeAssetListService) getBean("beeAssetListService");
    	List list = getListData(request);
    	int qty = beeAssetListService.updateEqSpec(list);
    	setMessage(response, "","OK"); 
    }
    
    private void eqPartList(HttpServletRequest request, HttpServletResponse response, BeeAssetListForm beeAssetListForm) throws Exception
    {
    	BeeAssetListService beeAssetListService = (BeeAssetListService) getBean("beeAssetListService",request);
    	Map map = getMapData(request);
        List resultList = beeAssetListService.eqPartList(map);
        super.makeJsonResult(resultList, request, response);
    }
    private void insertEqPart(HttpServletRequest request, HttpServletResponse response, BeeAssetListForm beeAssetListForm) throws Exception
    {
    	BeeAssetListService beeAssetListService = (BeeAssetListService) getBean("beeAssetListService");
    	List list = getListData(request);
    	int qty = beeAssetListService.insertEqPart(list);
    	setMessage(response, "","OK"); 
    }
    private void updateEqPart(HttpServletRequest request, HttpServletResponse response, BeeAssetListForm beeAssetListForm) throws Exception
    {
    	BeeAssetListService beeAssetListService = (BeeAssetListService) getBean("beeAssetListService");
    	List list = getListData(request);
    	int qty = beeAssetListService.updateEqPart(list);
    	setMessage(response, "","OK"); 
    }
    
}
