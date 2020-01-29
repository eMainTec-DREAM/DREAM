package intf.dream.android.offline.mainitcode.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.simple.parser.ParseException;

import common.struts.IfAuthAction;
import common.util.CommonUtil;
import intf.dream.android.offline.mainitcode.form.AnIfInitcodeListForm;
import intf.dream.android.offline.mainitcode.service.AnIfInitcodeListService;

/**
 * initdownload
 * 
 * @author
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/anIfInitcodeList" name="anIfInitcodeListForm"
 *                input="/android/offline/mainitcode/anIfInitcodeList.jsp"
 *                scope="request" validate="false"
 */
public class AnIfInitcodeListAction extends IfAuthAction {
	// LANG
	public static final String LANG_FIND 				= "LANG";
	// TACDSYSD
	public static final String CDSYSD_FIND 				= "SYSCODE";
	// TACDUSRD
	public static final String CDUSRD_FIND 				= "CDUSRD";
	// FAILURE
	public static final String FAILURE_FIND 			= "FAILURE";
	// TAUSER
	public static final String USER_FIND 				= "USER";
	// TAEMP
	public static final String EMP_FIND 				= "EMP";
	// TADEPT
	public static final String DEPT_FIND 				= "DEPT";
	// TAEQUIPMENT
	public static final String EQUIPMENT_FIND 			= "EQUIPMENT";
	// TAPMEQUIP
	public static final String PMEQUIP_FIND 			= "PMEQUIP";
	// TAEQLOC
	public static final String EQLOC_FIND 				= "EQLOC";
	// TAEQCTG
	public static final String EQCTG_FIND 				= "EQCTG";
	// TAPARTS
	public static final String PARTS_FIND 				= "PARTS";
	// TAWAREHOUSE
	public static final String WAREHOUSE_FIND 			= "WAREHOUSE";
	// TAWKCTR
	public static final String WKCTR_FIND 				= "WKCTR";
	// TAPLANT
	public static final String PLANT_FIND 				= "PLANT";
	// TAPTSTOCK
	public static final String STOCK_FIND 				= "STOCK";
	// TAMENU
	public static final String MENU_FIND 				= "MENU";
	// TAPRODUCT
	public static final String PRODUCT_FIND 			= "PRODUCT";
	// TAUSRGRPMENU
	public static final String USRGRPMENU_FIND 			= "USRGRPMENU";
	// TACONFIG
	public static final String CONFIG_FIND 				= "CONFIG";
	// DOWNLOAD USER CHECK
	public static final String DOWNLOAD_USER_CHECK 		= "CHECKUSER";
	// TAPAGE
	public static final String PAGE_FIND			 	= "PAGE";
	// TAPGBTN
	public static final String PGBTN_FIND			 	= "PGBTN";
	// TAPGFIELD
	public static final String PGFIELD_FIND			 	= "PGFIELD";
	// TAPGGRID
	public static final String PGGRID_FIND				= "PGGRID";
	// TAPGGRIDCOL
	public static final String PGGRIDCOL_FIND			= "PGGRIDCOL";
	// TAPGPAGE
	public static final String PGPAGE_FIND				= "PGPAGE";
	// TAPGLINKEDFUNC
	public static final String PGLINKEDFUNC_FIND		= "PGLINKEDFUNC";
	// TAUGPGAU
	public static final String UGPGAU_FIND				= "UGPGAU";
	// TAUGPGBTNAU
	public static final String UGPGBTNAU_FIND			= "UGPGBTNAU";
	// TAUGPGPGAU
	public static final String UGPGPGAU_FIND			= "UGPGPGAU";

	protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward returnActionForward = null;
		AnIfInitcodeListForm anIfInitcodeListForm = (AnIfInitcodeListForm) form;

		switch (anIfInitcodeListForm.getServiceName()) {
		case AnIfInitcodeListAction.LANG_FIND:
			findLangList(request, response, anIfInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AnIfInitcodeListAction.USER_FIND:
			findUserList(request, response, anIfInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AnIfInitcodeListAction.EMP_FIND:
			findEmpList(request, response, anIfInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AnIfInitcodeListAction.DEPT_FIND:
			findDeptList(request, response, anIfInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AnIfInitcodeListAction.WKCTR_FIND:
			findWkctrList(request, response, anIfInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AnIfInitcodeListAction.PLANT_FIND:
			findPlantList(request, response, anIfInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AnIfInitcodeListAction.EQUIPMENT_FIND:
			findEquipmentList(request, response, anIfInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;
			
		case AnIfInitcodeListAction.PMEQUIP_FIND:
			findPmequipList(request, response, anIfInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AnIfInitcodeListAction.EQLOC_FIND:
			findEqlocList(request, response, anIfInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;
		case AnIfInitcodeListAction.EQCTG_FIND:
			findEqctgList(request, response, anIfInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AnIfInitcodeListAction.PARTS_FIND:
			findPartsList(request, response, anIfInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AnIfInitcodeListAction.CDSYSD_FIND:
			findSyscodeList(request, response, anIfInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AnIfInitcodeListAction.CDUSRD_FIND:
			findUsrcodeList(request, response, anIfInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AnIfInitcodeListAction.FAILURE_FIND:
			findFailureList(request, response, anIfInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AnIfInitcodeListAction.WAREHOUSE_FIND:
			findWarehouseList(request, response, anIfInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AnIfInitcodeListAction.MENU_FIND:
			findMenuList(request, response, anIfInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;
		case AnIfInitcodeListAction.PRODUCT_FIND:
			findProductList(request, response, anIfInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AnIfInitcodeListAction.USRGRPMENU_FIND:
			findUsrGrpMenuList(request, response, anIfInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;
		case AnIfInitcodeListAction.CONFIG_FIND:
			findConfigList(request, response, anIfInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AnIfInitcodeListAction.STOCK_FIND:
			findStockList(request, response, anIfInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AnIfInitcodeListAction.DOWNLOAD_USER_CHECK:
			findDownUserCheck(request, response, anIfInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AnIfInitcodeListAction.PAGE_FIND:
			findPageList(request, response, anIfInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AnIfInitcodeListAction.PGBTN_FIND:
			findPgBtnList(request, response, anIfInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AnIfInitcodeListAction.PGFIELD_FIND:
			findPgFieldList(request, response, anIfInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AnIfInitcodeListAction.PGGRID_FIND:
			findPgGridList(request, response, anIfInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AnIfInitcodeListAction.PGGRIDCOL_FIND:
			findPgGridColList(request, response, anIfInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AnIfInitcodeListAction.PGPAGE_FIND:
			findPgPageList(request, response, anIfInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AnIfInitcodeListAction.PGLINKEDFUNC_FIND:
			findPgLinkedFuncList(request, response, anIfInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AnIfInitcodeListAction.UGPGAU_FIND:
			findUgPgAuList(request, response, anIfInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AnIfInitcodeListAction.UGPGBTNAU_FIND:
			findUgPgBtnAuList(request, response, anIfInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AnIfInitcodeListAction.UGPGPGAU_FIND:
			findUgPgPgAuList(request, response, anIfInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		default:
			returnActionForward = mapping.findForward("jsonPage");
			break;
		}

		return returnActionForward;
	}

	private void findLangList(HttpServletRequest request, HttpServletResponse response,
			AnIfInitcodeListForm anIfInitcodeListForm) throws IOException, ParseException {
		AnIfInitcodeListService anIfInitcodeListService = (AnIfInitcodeListService) getBean("anIfInitcodeListService");

		Map map = getMapData(request);

		// 리스트를 조회한다.
		List resultList = anIfInitcodeListService.findLangList(map);

		// 조회한 List 를 form에 셋팅한다.
		super.makeJsonResult(resultList, request, response);
	}

	private void findSyscodeList(HttpServletRequest request, HttpServletResponse response,
			AnIfInitcodeListForm anIfInitcodeListForm) throws IOException, ParseException {
		AnIfInitcodeListService anIfInitcodeListService = (AnIfInitcodeListService) getBean("anIfInitcodeListService");

		Map map = getMapData(request);

		// 리스트를 조회한다.
		List resultList = anIfInitcodeListService.findSyscodeList(map);

		// 조회한 List 를 form에 셋팅한다.
		super.makeJsonResult(resultList, request, response);
	}

	private void findUsrcodeList(HttpServletRequest request, HttpServletResponse response,
			AnIfInitcodeListForm anIfInitcodeListForm) throws IOException, ParseException {
		AnIfInitcodeListService anIfInitcodeListService = (AnIfInitcodeListService) getBean("anIfInitcodeListService");

		Map map = getMapData(request);

		// 리스트를 조회한다.
		List resultList = anIfInitcodeListService.findUsrcodeList(map);

		// 조회한 List 를 form에 셋팅한다.
		super.makeJsonResult(resultList, request, response);
	}

	private void findFailureList(HttpServletRequest request, HttpServletResponse response,
			AnIfInitcodeListForm anIfInitcodeListForm) throws IOException, ParseException {
		AnIfInitcodeListService anIfInitcodeListService = (AnIfInitcodeListService) getBean("anIfInitcodeListService");

		Map map = getMapData(request);

		// 리스트를 조회한다.
		List resultList = anIfInitcodeListService.findFailureList(map);

		// 조회한 List 를 form에 셋팅한다.
		super.makeJsonResult(resultList, request, response);
	}

	private void findWarehouseList(HttpServletRequest request, HttpServletResponse response,
			AnIfInitcodeListForm anIfInitcodeListForm) throws IOException, ParseException {
		AnIfInitcodeListService anIfInitcodeListService = (AnIfInitcodeListService) getBean("anIfInitcodeListService");

		Map map = getMapData(request);

		// 리스트를 조회한다.
		List resultList = anIfInitcodeListService.findWarehouseList(map);

		// 조회한 List 를 form에 셋팅한다.
		super.makeJsonResult(resultList, request, response);
	}
	private void findMenuList(HttpServletRequest request, HttpServletResponse response,
			AnIfInitcodeListForm anIfInitcodeListForm) throws IOException, ParseException {
		AnIfInitcodeListService anIfInitcodeListService = (AnIfInitcodeListService) getBean("anIfInitcodeListService");

		Map map = getMapData(request);

		// 리스트를 조회한다.
		List resultList = anIfInitcodeListService.findMenuList(map);

		// 조회한 List 를 form에 셋팅한다.
		super.makeJsonResult(resultList, request, response);
	}
	private void findProductList(HttpServletRequest request, HttpServletResponse response,
			AnIfInitcodeListForm anIfInitcodeListForm) throws IOException, ParseException {
		AnIfInitcodeListService anIfInitcodeListService = (AnIfInitcodeListService) getBean("anIfInitcodeListService");

		Map map = getMapData(request);

		// 리스트를 조회한다.
		List resultList = anIfInitcodeListService.findProductList(map);

		// 조회한 List 를 form에 셋팅한다.
		super.makeJsonResult(resultList, request, response);
	}
	private void findUsrGrpMenuList(HttpServletRequest request, HttpServletResponse response,
			AnIfInitcodeListForm anIfInitcodeListForm) throws IOException, ParseException {
		AnIfInitcodeListService anIfInitcodeListService = (AnIfInitcodeListService) getBean("anIfInitcodeListService");

		Map map = getMapData(request);

		// 리스트를 조회한다.
		List resultList = anIfInitcodeListService.findUsrGrpMenuList(map);

		// 조회한 List 를 form에 셋팅한다.
		super.makeJsonResult(resultList, request, response);
	}
	private void findConfigList(HttpServletRequest request, HttpServletResponse response,
			AnIfInitcodeListForm anIfInitcodeListForm) throws IOException, ParseException {
		AnIfInitcodeListService anIfInitcodeListService = (AnIfInitcodeListService) getBean("anIfInitcodeListService");
		
		Map map = getMapData(request);
		
		// 리스트를 조회한다.
		List resultList = anIfInitcodeListService.findConfigList(map);
		
		// 조회한 List 를 form에 셋팅한다.
		super.makeJsonResult(resultList, request, response);
	}

	private void findWkctrList(HttpServletRequest request, HttpServletResponse response,
			AnIfInitcodeListForm anIfInitcodeListForm) throws IOException, ParseException {
		AnIfInitcodeListService anIfInitcodeListService = (AnIfInitcodeListService) getBean("anIfInitcodeListService");

		Map map = getMapData(request);

		// 리스트를 조회한다.
		List resultList = anIfInitcodeListService.findWkctrList(map);

		// 조회한 List 를 form에 셋팅한다.
		super.makeJsonResult(resultList, request, response);
	}

	private void findPlantList(HttpServletRequest request, HttpServletResponse response,
			AnIfInitcodeListForm anIfInitcodeListForm) throws IOException, ParseException {
		AnIfInitcodeListService anIfInitcodeListService = (AnIfInitcodeListService) getBean("anIfInitcodeListService");

		Map map = getMapData(request);

		// 리스트를 조회한다.
		List resultList = anIfInitcodeListService.findPlantList(map);

		// 조회한 List 를 form에 셋팅한다.
		super.makeJsonResult(resultList, request, response);
	}

	private void findStockList(HttpServletRequest request, HttpServletResponse response,
			AnIfInitcodeListForm anIfInitcodeListForm) throws IOException, ParseException {
		AnIfInitcodeListService anIfInitcodeListService = (AnIfInitcodeListService) getBean("anIfInitcodeListService");

		Map map = getMapData(request);

		String wcodeId		= anIfInitcodeListService.findUserWcode(map);
		// 리스트를 조회한다.
		List resultList = anIfInitcodeListService.findStockList(map,wcodeId);

		// 조회한 List 를 form에 셋팅한다.
		super.makeJsonResult(resultList, request, response);
	}

	private void findUserList(HttpServletRequest request, HttpServletResponse response,
			AnIfInitcodeListForm anIfInitcodeListForm) throws IOException, ParseException {
		AnIfInitcodeListService anIfInitcodeListService = (AnIfInitcodeListService) getBean("anIfInitcodeListService");

		Map map = getMapData(request);

		// 리스트를 조회한다.
		List resultList = anIfInitcodeListService.findUserList(map);

		// 조회한 List 를 form에 셋팅한다.
		super.makeJsonResult(resultList, request, response);
	}

	private void findDownUserCheck(HttpServletRequest request, HttpServletResponse response,
			AnIfInitcodeListForm anIfInitcodeListForm) throws IOException, ParseException {
		AnIfInitcodeListService anIfInitcodeListService = (AnIfInitcodeListService) getBean("anIfInitcodeListService");

		Map map = getMapData(request);

		// 리스트를 조회한다.
		List resultList = anIfInitcodeListService.findDownUserCheck(map);

		// 조회한 List 를 form에 셋팅한다.
		super.makeJsonResult(resultList, request, response);
	}

	private void findEmpList(HttpServletRequest request, HttpServletResponse response,
			AnIfInitcodeListForm anIfInitcodeListForm) throws IOException, ParseException {
		AnIfInitcodeListService anIfInitcodeListService = (AnIfInitcodeListService) getBean("anIfInitcodeListService");

		Map map = getMapData(request);

		// 리스트를 조회한다.
		List resultList = anIfInitcodeListService.findEmpList(map);

		// 조회한 List 를 form에 셋팅한다.
		super.makeJsonResult(resultList, request, response);
	}

	private void findDeptList(HttpServletRequest request, HttpServletResponse response,
			AnIfInitcodeListForm anIfInitcodeListForm) throws IOException, ParseException {
		AnIfInitcodeListService anIfInitcodeListService = (AnIfInitcodeListService) getBean("anIfInitcodeListService");

		Map map = getMapData(request);

		// 리스트를 조회한다.
		List resultList = anIfInitcodeListService.findDeptList(map);

		// 조회한 List 를 form에 셋팅한다.
		super.makeJsonResult(resultList, request, response);
	}

	private void findEquipmentList(HttpServletRequest request, HttpServletResponse response,
			AnIfInitcodeListForm anIfInitcodeListForm) throws IOException, ParseException {
		AnIfInitcodeListService anIfInitcodeListService = (AnIfInitcodeListService) getBean("anIfInitcodeListService");

		Map map = getMapData(request);
		
		String isPlanDown = String.valueOf(map.get("isPlanDown"));
		String eqCtgType 	= anIfInitcodeListService.findUserEqCtgType(map);
		String eqLocId		= anIfInitcodeListService.findUserEqLoc(map);
		String deptId		= anIfInitcodeListService.findUserDept(map);
		String usageDeptId	= anIfInitcodeListService.findUserUsageDept(map);
		String isMaintDept	= anIfInitcodeListService.findIsMaintDept(map);
        
		if("Y".equals(isPlanDown)){
			eqCtgType = String.valueOf(map.get("eqctgType"));
			eqLocId   = String.valueOf(map.get("eqlocId"));
			deptId    = String.valueOf(map.get("deptId"));
			usageDeptId    = String.valueOf(map.get("usageDeptId"));
		}
		if("Y".equals(isMaintDept)) deptId = "";
		
		// 리스트를 조회한다.
		List list = anIfInitcodeListService.findEquipmentList(map,eqCtgType, eqLocId, deptId,usageDeptId);

		List resultList = new ArrayList();
		if (list.size() > 0) {

			List toolList = anIfInitcodeListService.findEqtoolList(map,eqCtgType, eqLocId, deptId,usageDeptId);
			List specList = anIfInitcodeListService.findEqspecList(map,eqCtgType, eqLocId, deptId,usageDeptId);
			List partList = anIfInitcodeListService.findEqpartList(map,eqCtgType, eqLocId, deptId,usageDeptId);
			List asmbList = anIfInitcodeListService.findEqasmbList(map,eqCtgType, eqLocId, deptId,usageDeptId);
			
			for (Object obj : list) {
				Map objMap = (Map) obj;

				String hdrId = CommonUtil.convertString(objMap.get("equip_id"));
				
				List sendToolList = new ArrayList();
				List sendSpecList = new ArrayList();
				List sendPartList = new ArrayList();
				List sendAsmbList = new ArrayList();
				
				for(Object toolObj : toolList)
		    	  {
					Map toolObjMap = (Map)toolObj;
		    		String equip = CommonUtil.convertString(toolObjMap.get("equip_id"));
		    		if(hdrId.equals(equip)) sendToolList.add(toolObjMap);
		    	  }
				for(Object specObj : specList)
		    	  {
					Map specObjMap = (Map)specObj;
		    		String equip = CommonUtil.convertString(specObjMap.get("equip_id"));
		    		if(hdrId.equals(equip)) sendSpecList.add(specObjMap);
		    	  }
				for(Object partObj :partList)
		    	  {
					Map partObjMap = (Map)partObj;
		    		String equip = CommonUtil.convertString(partObjMap.get("equip_id"));
		    		if(hdrId.equals(equip)) sendPartList.add(partObjMap);
		    	  }
				for(Object asmbObj :asmbList)
		    	  {
					Map asmbObjMap = (Map)asmbObj;
		    		String equip = CommonUtil.convertString(asmbObjMap.get("equip_id"));
		    		if(hdrId.equals(equip)) sendAsmbList.add(asmbObjMap);
		    	  }

				objMap.put("TOOL", CommonUtil.makeNullToBlank(sendToolList));
				objMap.put("SPEC", CommonUtil.makeNullToBlank(sendSpecList));
				objMap.put("PART", CommonUtil.makeNullToBlank(sendPartList));
				objMap.put("ASMB", CommonUtil.makeNullToBlank(sendAsmbList));
				
				resultList.add(objMap);
			}

		}

		// 조회한 List 를 form에 셋팅한다.
		 super.makeJsonResult(resultList, request, response);
	}

	private void findPmequipList(HttpServletRequest request, HttpServletResponse response,
			AnIfInitcodeListForm anIfInitcodeListForm) throws IOException, ParseException {
		AnIfInitcodeListService anIfInitcodeListService = (AnIfInitcodeListService) getBean("anIfInitcodeListService");

		Map map = getMapData(request);

		// 리스트를 조회한다.
		List resultList = anIfInitcodeListService.findPmequipList(map);

		// 조회한 List 를 form에 셋팅한다.
		super.makeJsonResult(resultList, request, response);
	}
	private void findEqlocList(HttpServletRequest request, HttpServletResponse response,
			AnIfInitcodeListForm anIfInitcodeListForm) throws IOException, ParseException {
		AnIfInitcodeListService anIfInitcodeListService = (AnIfInitcodeListService) getBean("anIfInitcodeListService");

		Map map = getMapData(request);

		// 리스트를 조회한다.
		List resultList = anIfInitcodeListService.findEqlocList(map);

		// 조회한 List 를 form에 셋팅한다.
		super.makeJsonResult(resultList, request, response);
	}

	private void findEqctgList(HttpServletRequest request, HttpServletResponse response,
			AnIfInitcodeListForm anIfInitcodeListForm) throws IOException, ParseException {
		AnIfInitcodeListService anIfInitcodeListService = (AnIfInitcodeListService) getBean("anIfInitcodeListService");

		Map map = getMapData(request);

		// 리스트를 조회한다.
		List resultList = anIfInitcodeListService.findEqctgList(map);

		// 조회한 List 를 form에 셋팅한다.
		super.makeJsonResult(resultList, request, response);
	}

	private void findPartsList(HttpServletRequest request, HttpServletResponse response,
			AnIfInitcodeListForm anIfInitcodeListForm) throws IOException, ParseException {
		AnIfInitcodeListService anIfInitcodeListService = (AnIfInitcodeListService) getBean("anIfInitcodeListService");

		Map map = getMapData(request);

		String wcodeId		= anIfInitcodeListService.findUserWcode(map);
		// 리스트를 조회한다.
		List resultList = anIfInitcodeListService.findPartsList(map,wcodeId);

		// 조회한 List 를 form에 셋팅한다.
		super.makeJsonResult(resultList, request, response);
	}

	private void findPageList(HttpServletRequest request, HttpServletResponse response, AnIfInitcodeListForm anIfInitcodeListForm) throws IOException, ParseException {
		AnIfInitcodeListService anIfInitcodeListService = (AnIfInitcodeListService) getBean("anIfInitcodeListService");
		Map map = getMapData(request);
		// 리스트를 조회한다.
		List resultList = anIfInitcodeListService.findPageList(map);
		// 조회한 List 를 form에 셋팅한다.
		super.makeJsonResult(resultList, request, response);
	}

	private void findPgBtnList(HttpServletRequest request, HttpServletResponse response, AnIfInitcodeListForm anIfInitcodeListForm) throws IOException, ParseException {
		AnIfInitcodeListService anIfInitcodeListService = (AnIfInitcodeListService) getBean("anIfInitcodeListService");
		Map map = getMapData(request);
		// 리스트를 조회한다.
		List resultList = anIfInitcodeListService.findPgBtnList(map);
		// 조회한 List 를 form에 셋팅한다.
		super.makeJsonResult(resultList, request, response);
	}

	private void findPgFieldList(HttpServletRequest request, HttpServletResponse response, AnIfInitcodeListForm anIfInitcodeListForm) throws IOException, ParseException {
		AnIfInitcodeListService anIfInitcodeListService = (AnIfInitcodeListService) getBean("anIfInitcodeListService");
		Map map = getMapData(request);
		// 리스트를 조회한다.
		List resultList = anIfInitcodeListService.findPgFieldList(map);
		// 조회한 List 를 form에 셋팅한다.
		super.makeJsonResult(resultList, request, response);
	}

	private void findPgGridList(HttpServletRequest request, HttpServletResponse response, AnIfInitcodeListForm anIfInitcodeListForm) throws IOException, ParseException {
		AnIfInitcodeListService anIfInitcodeListService = (AnIfInitcodeListService) getBean("anIfInitcodeListService");
		Map map = getMapData(request);
		// 리스트를 조회한다.
		List resultList = anIfInitcodeListService.findPgGridList(map);
		// 조회한 List 를 form에 셋팅한다.
		super.makeJsonResult(resultList, request, response);
	}
	
	private void findPgGridColList(HttpServletRequest request, HttpServletResponse response, AnIfInitcodeListForm anIfInitcodeListForm) throws IOException, ParseException {
		AnIfInitcodeListService anIfInitcodeListService = (AnIfInitcodeListService) getBean("anIfInitcodeListService");
		Map map = getMapData(request);
		// 리스트를 조회한다.
		List resultList = anIfInitcodeListService.findPgGridColList(map);
		// 조회한 List 를 form에 셋팅한다.
		super.makeJsonResult(resultList, request, response);
	}

	private void findPgPageList(HttpServletRequest request, HttpServletResponse response, AnIfInitcodeListForm anIfInitcodeListForm) throws IOException, ParseException {
		AnIfInitcodeListService anIfInitcodeListService = (AnIfInitcodeListService) getBean("anIfInitcodeListService");
		Map map = getMapData(request);
		// 리스트를 조회한다.
		List resultList = anIfInitcodeListService.findPgPageList(map);
		// 조회한 List 를 form에 셋팅한다.
		super.makeJsonResult(resultList, request, response);
	}

	private void findPgLinkedFuncList(HttpServletRequest request, HttpServletResponse response, AnIfInitcodeListForm anIfInitcodeListForm) throws IOException, ParseException {
		AnIfInitcodeListService anIfInitcodeListService = (AnIfInitcodeListService) getBean("anIfInitcodeListService");
		Map map = getMapData(request);
		// 리스트를 조회한다.
		List resultList = anIfInitcodeListService.findPgLinkedFuncList(map);
		// 조회한 List 를 form에 셋팅한다.
		super.makeJsonResult(resultList, request, response);
	}

	private void findUgPgAuList(HttpServletRequest request, HttpServletResponse response, AnIfInitcodeListForm anIfInitcodeListForm) throws IOException, ParseException {
		AnIfInitcodeListService anIfInitcodeListService = (AnIfInitcodeListService) getBean("anIfInitcodeListService");
		Map map = getMapData(request);
		// 리스트를 조회한다.
		List resultList = anIfInitcodeListService.findUgPgAuList(map);
		// 조회한 List 를 form에 셋팅한다.
		super.makeJsonResult(resultList, request, response);
	}

	private void findUgPgBtnAuList(HttpServletRequest request, HttpServletResponse response, AnIfInitcodeListForm anIfInitcodeListForm) throws IOException, ParseException {
		AnIfInitcodeListService anIfInitcodeListService = (AnIfInitcodeListService) getBean("anIfInitcodeListService");
		Map map = getMapData(request);
		// 리스트를 조회한다.
		List resultList = anIfInitcodeListService.findUgPgBtnAuList(map);
		// 조회한 List 를 form에 셋팅한다.
		super.makeJsonResult(resultList, request, response);
	}

	private void findUgPgPgAuList(HttpServletRequest request, HttpServletResponse response, AnIfInitcodeListForm anIfInitcodeListForm) throws IOException, ParseException {
		AnIfInitcodeListService anIfInitcodeListService = (AnIfInitcodeListService) getBean("anIfInitcodeListService");
		Map map = getMapData(request);
		// 리스트를 조회한다.
		List resultList = anIfInitcodeListService.findUgPgPgAuList(map);
		// 조회한 List 를 form에 셋팅한다.
		super.makeJsonResult(resultList, request, response);
	}
	
}
