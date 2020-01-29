package intf.dream.ant.initcode.action;

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
import intf.dream.ant.initcode.form.AntInitcodeListForm;
import intf.dream.ant.initcode.service.AntInitcodeListService;

/**
 * initdownload
 * 
 * @author
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/antInitcodeList" name="antInitcodeListForm"
 *                input="/ant/initcode/antInitcodeList.jsp"
 *                scope="request" validate="false"
 */
public class AntInitcodeListAction extends IfAuthAction {
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
		AntInitcodeListForm antInitcodeListForm = (AntInitcodeListForm) form;

		switch (antInitcodeListForm.getServiceName()) {
		case AntInitcodeListAction.LANG_FIND:
			findLangList(request, response, antInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AntInitcodeListAction.USER_FIND:
			findUserList(request, response, antInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AntInitcodeListAction.EMP_FIND:
			findEmpList(request, response, antInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AntInitcodeListAction.DEPT_FIND:
			findDeptList(request, response, antInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AntInitcodeListAction.WKCTR_FIND:
			findWkctrList(request, response, antInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AntInitcodeListAction.PLANT_FIND:
			findPlantList(request, response, antInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AntInitcodeListAction.EQUIPMENT_FIND:
			findEquipmentList(request, response, antInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;
			
		case AntInitcodeListAction.PMEQUIP_FIND:
			findPmequipList(request, response, antInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AntInitcodeListAction.EQLOC_FIND:
			findEqlocList(request, response, antInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;
		case AntInitcodeListAction.EQCTG_FIND:
			findEqctgList(request, response, antInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AntInitcodeListAction.PARTS_FIND:
			findPartsList(request, response, antInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AntInitcodeListAction.CDSYSD_FIND:
			findSyscodeList(request, response, antInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AntInitcodeListAction.CDUSRD_FIND:
			findUsrcodeList(request, response, antInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AntInitcodeListAction.FAILURE_FIND:
			findFailureList(request, response, antInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AntInitcodeListAction.WAREHOUSE_FIND:
			findWarehouseList(request, response, antInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AntInitcodeListAction.MENU_FIND:
			findMenuList(request, response, antInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;
		case AntInitcodeListAction.PRODUCT_FIND:
			findProductList(request, response, antInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AntInitcodeListAction.USRGRPMENU_FIND:
			findUsrGrpMenuList(request, response, antInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;
		case AntInitcodeListAction.CONFIG_FIND:
			findConfigList(request, response, antInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AntInitcodeListAction.STOCK_FIND:
			findStockList(request, response, antInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AntInitcodeListAction.DOWNLOAD_USER_CHECK:
			findDownUserCheck(request, response, antInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AntInitcodeListAction.PAGE_FIND:
			findPageList(request, response, antInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AntInitcodeListAction.PGBTN_FIND:
			findPgBtnList(request, response, antInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AntInitcodeListAction.PGFIELD_FIND:
			findPgFieldList(request, response, antInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AntInitcodeListAction.PGGRID_FIND:
			findPgGridList(request, response, antInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AntInitcodeListAction.PGGRIDCOL_FIND:
			findPgGridColList(request, response, antInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AntInitcodeListAction.PGPAGE_FIND:
			findPgPageList(request, response, antInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AntInitcodeListAction.PGLINKEDFUNC_FIND:
			findPgLinkedFuncList(request, response, antInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AntInitcodeListAction.UGPGAU_FIND:
			findUgPgAuList(request, response, antInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AntInitcodeListAction.UGPGBTNAU_FIND:
			findUgPgBtnAuList(request, response, antInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AntInitcodeListAction.UGPGPGAU_FIND:
			findUgPgPgAuList(request, response, antInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		default:
			returnActionForward = mapping.findForward("jsonPage");
			break;
		}

		return returnActionForward;
	}

	private void findLangList(HttpServletRequest request, HttpServletResponse response,
			AntInitcodeListForm antInitcodeListForm) throws IOException, ParseException {
		AntInitcodeListService antInitcodeListService = (AntInitcodeListService) getBean("antInitcodeListService");

		Map map = getMapData(request);

		// ����Ʈ�� ��ȸ�Ѵ�.
		List resultList = antInitcodeListService.findLangList(map);

		// ��ȸ�� List �� form�� �����Ѵ�.
		super.makeJsonResult(resultList, request, response);
	}

	private void findSyscodeList(HttpServletRequest request, HttpServletResponse response,
			AntInitcodeListForm antInitcodeListForm) throws IOException, ParseException {
		AntInitcodeListService antInitcodeListService = (AntInitcodeListService) getBean("antInitcodeListService");

		Map map = getMapData(request);

		// ����Ʈ�� ��ȸ�Ѵ�.
		List resultList = antInitcodeListService.findSyscodeList(map);

		// ��ȸ�� List �� form�� �����Ѵ�.
		super.makeJsonResult(resultList, request, response);
	}

	private void findUsrcodeList(HttpServletRequest request, HttpServletResponse response,
			AntInitcodeListForm antInitcodeListForm) throws IOException, ParseException {
		AntInitcodeListService antInitcodeListService = (AntInitcodeListService) getBean("antInitcodeListService");

		Map map = getMapData(request);

		// ����Ʈ�� ��ȸ�Ѵ�.
		List resultList = antInitcodeListService.findUsrcodeList(map);

		// ��ȸ�� List �� form�� �����Ѵ�.
		super.makeJsonResult(resultList, request, response);
	}

	private void findFailureList(HttpServletRequest request, HttpServletResponse response,
			AntInitcodeListForm antInitcodeListForm) throws IOException, ParseException {
		AntInitcodeListService antInitcodeListService = (AntInitcodeListService) getBean("antInitcodeListService");

		Map map = getMapData(request);

		// ����Ʈ�� ��ȸ�Ѵ�.
		List resultList = antInitcodeListService.findFailureList(map);

		// ��ȸ�� List �� form�� �����Ѵ�.
		super.makeJsonResult(resultList, request, response);
	}

	private void findWarehouseList(HttpServletRequest request, HttpServletResponse response,
			AntInitcodeListForm antInitcodeListForm) throws IOException, ParseException {
		AntInitcodeListService antInitcodeListService = (AntInitcodeListService) getBean("antInitcodeListService");

		Map map = getMapData(request);

		// ����Ʈ�� ��ȸ�Ѵ�.
		List resultList = antInitcodeListService.findWarehouseList(map);

		// ��ȸ�� List �� form�� �����Ѵ�.
		super.makeJsonResult(resultList, request, response);
	}
	private void findMenuList(HttpServletRequest request, HttpServletResponse response,
			AntInitcodeListForm antInitcodeListForm) throws IOException, ParseException {
		AntInitcodeListService antInitcodeListService = (AntInitcodeListService) getBean("antInitcodeListService");

		Map map = getMapData(request);

		// ����Ʈ�� ��ȸ�Ѵ�.
		List resultList = antInitcodeListService.findMenuList(map);

		// ��ȸ�� List �� form�� �����Ѵ�.
		super.makeJsonResult(resultList, request, response);
	}
	private void findProductList(HttpServletRequest request, HttpServletResponse response,
			AntInitcodeListForm antInitcodeListForm) throws IOException, ParseException {
		AntInitcodeListService antInitcodeListService = (AntInitcodeListService) getBean("antInitcodeListService");

		Map map = getMapData(request);

		// ����Ʈ�� ��ȸ�Ѵ�.
		List resultList = antInitcodeListService.findProductList(map);

		// ��ȸ�� List �� form�� �����Ѵ�.
		super.makeJsonResult(resultList, request, response);
	}
	private void findUsrGrpMenuList(HttpServletRequest request, HttpServletResponse response,
			AntInitcodeListForm antInitcodeListForm) throws IOException, ParseException {
		AntInitcodeListService antInitcodeListService = (AntInitcodeListService) getBean("antInitcodeListService");

		Map map = getMapData(request);

		// ����Ʈ�� ��ȸ�Ѵ�.
		List resultList = antInitcodeListService.findUsrGrpMenuList(map);

		// ��ȸ�� List �� form�� �����Ѵ�.
		super.makeJsonResult(resultList, request, response);
	}
	private void findConfigList(HttpServletRequest request, HttpServletResponse response,
			AntInitcodeListForm antInitcodeListForm) throws IOException, ParseException {
		AntInitcodeListService antInitcodeListService = (AntInitcodeListService) getBean("antInitcodeListService");
		
		Map map = getMapData(request);
		
		// ����Ʈ�� ��ȸ�Ѵ�.
		List resultList = antInitcodeListService.findConfigList(map);
		
		// ��ȸ�� List �� form�� �����Ѵ�.
		super.makeJsonResult(resultList, request, response);
	}

	private void findWkctrList(HttpServletRequest request, HttpServletResponse response,
			AntInitcodeListForm antInitcodeListForm) throws IOException, ParseException {
		AntInitcodeListService antInitcodeListService = (AntInitcodeListService) getBean("antInitcodeListService");

		Map map = getMapData(request);

		// ����Ʈ�� ��ȸ�Ѵ�.
		List resultList = antInitcodeListService.findWkctrList(map);

		// ��ȸ�� List �� form�� �����Ѵ�.
		super.makeJsonResult(resultList, request, response);
	}

	private void findPlantList(HttpServletRequest request, HttpServletResponse response,
			AntInitcodeListForm antInitcodeListForm) throws IOException, ParseException {
		AntInitcodeListService antInitcodeListService = (AntInitcodeListService) getBean("antInitcodeListService");

		Map map = getMapData(request);

		// ����Ʈ�� ��ȸ�Ѵ�.
		List resultList = antInitcodeListService.findPlantList(map);

		// ��ȸ�� List �� form�� �����Ѵ�.
		super.makeJsonResult(resultList, request, response);
	}

	private void findStockList(HttpServletRequest request, HttpServletResponse response,
			AntInitcodeListForm antInitcodeListForm) throws IOException, ParseException {
		AntInitcodeListService antInitcodeListService = (AntInitcodeListService) getBean("antInitcodeListService");

		Map map = getMapData(request);

		String wcodeId		= antInitcodeListService.findUserWcode(map);
		// ����Ʈ�� ��ȸ�Ѵ�.
		List resultList = antInitcodeListService.findStockList(map,wcodeId);

		// ��ȸ�� List �� form�� �����Ѵ�.
		super.makeJsonResult(resultList, request, response);
	}

	private void findUserList(HttpServletRequest request, HttpServletResponse response,
			AntInitcodeListForm antInitcodeListForm) throws IOException, ParseException {
		AntInitcodeListService antInitcodeListService = (AntInitcodeListService) getBean("antInitcodeListService");

		Map map = getMapData(request);

		// ����Ʈ�� ��ȸ�Ѵ�.
		List resultList = antInitcodeListService.findUserList(map);

		// ��ȸ�� List �� form�� �����Ѵ�.
		super.makeJsonResult(resultList, request, response);
	}

	private void findDownUserCheck(HttpServletRequest request, HttpServletResponse response,
			AntInitcodeListForm antInitcodeListForm) throws IOException, ParseException {
		AntInitcodeListService antInitcodeListService = (AntInitcodeListService) getBean("antInitcodeListService");

		Map map = getMapData(request);

		// ����Ʈ�� ��ȸ�Ѵ�.
		List resultList = antInitcodeListService.findDownUserCheck(map);

		// ��ȸ�� List �� form�� �����Ѵ�.
		super.makeJsonResult(resultList, request, response);
	}

	private void findEmpList(HttpServletRequest request, HttpServletResponse response,
			AntInitcodeListForm antInitcodeListForm) throws IOException, ParseException {
		AntInitcodeListService antInitcodeListService = (AntInitcodeListService) getBean("antInitcodeListService");

		Map map = getMapData(request);

		// ����Ʈ�� ��ȸ�Ѵ�.
		List resultList = antInitcodeListService.findEmpList(map);

		// ��ȸ�� List �� form�� �����Ѵ�.
		super.makeJsonResult(resultList, request, response);
	}

	private void findDeptList(HttpServletRequest request, HttpServletResponse response,
			AntInitcodeListForm antInitcodeListForm) throws IOException, ParseException {
		AntInitcodeListService antInitcodeListService = (AntInitcodeListService) getBean("antInitcodeListService");

		Map map = getMapData(request);

		// ����Ʈ�� ��ȸ�Ѵ�.
		List resultList = antInitcodeListService.findDeptList(map);

		// ��ȸ�� List �� form�� �����Ѵ�.
		super.makeJsonResult(resultList, request, response);
	}

	private void findEquipmentList(HttpServletRequest request, HttpServletResponse response,
			AntInitcodeListForm antInitcodeListForm) throws IOException, ParseException {
		AntInitcodeListService antInitcodeListService = (AntInitcodeListService) getBean("antInitcodeListService");

		Map map = getMapData(request);
		
		String isPlanDown = String.valueOf(map.get("isPlanDown"));
		String eqCtgType 	= antInitcodeListService.findUserEqCtgType(map);
		String eqLocId		= antInitcodeListService.findUserEqLoc(map);
		String deptId		= antInitcodeListService.findUserDept(map);
		String usageDeptId	= antInitcodeListService.findUserUsageDept(map);
		String isMaintDept	= antInitcodeListService.findIsMaintDept(map);
        
		if("Y".equals(isPlanDown)){
			eqCtgType = String.valueOf(map.get("eqctgType"));
			eqLocId   = String.valueOf(map.get("eqlocId"));
			deptId    = String.valueOf(map.get("deptId"));
			usageDeptId    = String.valueOf(map.get("usageDeptId"));
		}
		if("Y".equals(isMaintDept)) deptId = "";
		
		// ����Ʈ�� ��ȸ�Ѵ�.
		List list = antInitcodeListService.findEquipmentList(map,eqCtgType, eqLocId, deptId,usageDeptId);

		List resultList = new ArrayList();
		if (list.size() > 0) {

			List toolList = antInitcodeListService.findEqtoolList(map,eqCtgType, eqLocId, deptId,usageDeptId);
			List specList = antInitcodeListService.findEqspecList(map,eqCtgType, eqLocId, deptId,usageDeptId);
			List partList = antInitcodeListService.findEqpartList(map,eqCtgType, eqLocId, deptId,usageDeptId);
			List asmbList = antInitcodeListService.findEqasmbList(map,eqCtgType, eqLocId, deptId,usageDeptId);
			
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

		// ��ȸ�� List �� form�� �����Ѵ�.
		 super.makeJsonResult(resultList, request, response);
	}

	private void findPmequipList(HttpServletRequest request, HttpServletResponse response,
			AntInitcodeListForm antInitcodeListForm) throws IOException, ParseException {
		AntInitcodeListService antInitcodeListService = (AntInitcodeListService) getBean("antInitcodeListService");

		Map map = getMapData(request);

		// ����Ʈ�� ��ȸ�Ѵ�.
		List resultList = antInitcodeListService.findPmequipList(map);

		// ��ȸ�� List �� form�� �����Ѵ�.
		super.makeJsonResult(resultList, request, response);
	}
	private void findEqlocList(HttpServletRequest request, HttpServletResponse response,
			AntInitcodeListForm antInitcodeListForm) throws IOException, ParseException {
		AntInitcodeListService antInitcodeListService = (AntInitcodeListService) getBean("antInitcodeListService");

		Map map = getMapData(request);

		// ����Ʈ�� ��ȸ�Ѵ�.
		List resultList = antInitcodeListService.findEqlocList(map);

		// ��ȸ�� List �� form�� �����Ѵ�.
		super.makeJsonResult(resultList, request, response);
	}

	private void findEqctgList(HttpServletRequest request, HttpServletResponse response,
			AntInitcodeListForm antInitcodeListForm) throws IOException, ParseException {
		AntInitcodeListService antInitcodeListService = (AntInitcodeListService) getBean("antInitcodeListService");

		Map map = getMapData(request);

		// ����Ʈ�� ��ȸ�Ѵ�.
		List resultList = antInitcodeListService.findEqctgList(map);

		// ��ȸ�� List �� form�� �����Ѵ�.
		super.makeJsonResult(resultList, request, response);
	}

	private void findPartsList(HttpServletRequest request, HttpServletResponse response,
			AntInitcodeListForm antInitcodeListForm) throws IOException, ParseException {
		AntInitcodeListService antInitcodeListService = (AntInitcodeListService) getBean("antInitcodeListService");

		Map map = getMapData(request);

		String wcodeId		= antInitcodeListService.findUserWcode(map);
		// ����Ʈ�� ��ȸ�Ѵ�.
		List resultList = antInitcodeListService.findPartsList(map,wcodeId);

		// ��ȸ�� List �� form�� �����Ѵ�.
		super.makeJsonResult(resultList, request, response);
	}

	private void findPageList(HttpServletRequest request, HttpServletResponse response, AntInitcodeListForm antInitcodeListForm) throws IOException, ParseException {
		AntInitcodeListService antInitcodeListService = (AntInitcodeListService) getBean("antInitcodeListService");
		Map map = getMapData(request);
		// ����Ʈ�� ��ȸ�Ѵ�.
		List resultList = antInitcodeListService.findPageList(map);
		// ��ȸ�� List �� form�� �����Ѵ�.
		super.makeJsonResult(resultList, request, response);
	}

	private void findPgBtnList(HttpServletRequest request, HttpServletResponse response, AntInitcodeListForm antInitcodeListForm) throws IOException, ParseException {
		AntInitcodeListService antInitcodeListService = (AntInitcodeListService) getBean("antInitcodeListService");
		Map map = getMapData(request);
		// ����Ʈ�� ��ȸ�Ѵ�.
		List resultList = antInitcodeListService.findPgBtnList(map);
		// ��ȸ�� List �� form�� �����Ѵ�.
		super.makeJsonResult(resultList, request, response);
	}

	private void findPgFieldList(HttpServletRequest request, HttpServletResponse response, AntInitcodeListForm antInitcodeListForm) throws IOException, ParseException {
		AntInitcodeListService antInitcodeListService = (AntInitcodeListService) getBean("antInitcodeListService");
		Map map = getMapData(request);
		// ����Ʈ�� ��ȸ�Ѵ�.
		List resultList = antInitcodeListService.findPgFieldList(map);
		// ��ȸ�� List �� form�� �����Ѵ�.
		super.makeJsonResult(resultList, request, response);
	}

	private void findPgGridList(HttpServletRequest request, HttpServletResponse response, AntInitcodeListForm antInitcodeListForm) throws IOException, ParseException {
		AntInitcodeListService antInitcodeListService = (AntInitcodeListService) getBean("antInitcodeListService");
		Map map = getMapData(request);
		// ����Ʈ�� ��ȸ�Ѵ�.
		List resultList = antInitcodeListService.findPgGridList(map);
		// ��ȸ�� List �� form�� �����Ѵ�.
		super.makeJsonResult(resultList, request, response);
	}
	
	private void findPgGridColList(HttpServletRequest request, HttpServletResponse response, AntInitcodeListForm antInitcodeListForm) throws IOException, ParseException {
		AntInitcodeListService antInitcodeListService = (AntInitcodeListService) getBean("antInitcodeListService");
		Map map = getMapData(request);
		// ����Ʈ�� ��ȸ�Ѵ�.
		List resultList = antInitcodeListService.findPgGridColList(map);
		// ��ȸ�� List �� form�� �����Ѵ�.
		super.makeJsonResult(resultList, request, response);
	}

	private void findPgPageList(HttpServletRequest request, HttpServletResponse response, AntInitcodeListForm antInitcodeListForm) throws IOException, ParseException {
		AntInitcodeListService antInitcodeListService = (AntInitcodeListService) getBean("antInitcodeListService");
		Map map = getMapData(request);
		// ����Ʈ�� ��ȸ�Ѵ�.
		List resultList = antInitcodeListService.findPgPageList(map);
		// ��ȸ�� List �� form�� �����Ѵ�.
		super.makeJsonResult(resultList, request, response);
	}

	private void findPgLinkedFuncList(HttpServletRequest request, HttpServletResponse response, AntInitcodeListForm antInitcodeListForm) throws IOException, ParseException {
		AntInitcodeListService antInitcodeListService = (AntInitcodeListService) getBean("antInitcodeListService");
		Map map = getMapData(request);
		// ����Ʈ�� ��ȸ�Ѵ�.
		List resultList = antInitcodeListService.findPgLinkedFuncList(map);
		// ��ȸ�� List �� form�� �����Ѵ�.
		super.makeJsonResult(resultList, request, response);
	}

	private void findUgPgAuList(HttpServletRequest request, HttpServletResponse response, AntInitcodeListForm antInitcodeListForm) throws IOException, ParseException {
		AntInitcodeListService antInitcodeListService = (AntInitcodeListService) getBean("antInitcodeListService");
		Map map = getMapData(request);
		// ����Ʈ�� ��ȸ�Ѵ�.
		List resultList = antInitcodeListService.findUgPgAuList(map);
		// ��ȸ�� List �� form�� �����Ѵ�.
		super.makeJsonResult(resultList, request, response);
	}

	private void findUgPgBtnAuList(HttpServletRequest request, HttpServletResponse response, AntInitcodeListForm antInitcodeListForm) throws IOException, ParseException {
		AntInitcodeListService antInitcodeListService = (AntInitcodeListService) getBean("antInitcodeListService");
		Map map = getMapData(request);
		// ����Ʈ�� ��ȸ�Ѵ�.
		List resultList = antInitcodeListService.findUgPgBtnAuList(map);
		// ��ȸ�� List �� form�� �����Ѵ�.
		super.makeJsonResult(resultList, request, response);
	}

	private void findUgPgPgAuList(HttpServletRequest request, HttpServletResponse response, AntInitcodeListForm antInitcodeListForm) throws IOException, ParseException {
		AntInitcodeListService antInitcodeListService = (AntInitcodeListService) getBean("antInitcodeListService");
		Map map = getMapData(request);
		// ����Ʈ�� ��ȸ�Ѵ�.
		List resultList = antInitcodeListService.findUgPgPgAuList(map);
		// ��ȸ�� List �� form�� �����Ѵ�.
		super.makeJsonResult(resultList, request, response);
	}
	
}
