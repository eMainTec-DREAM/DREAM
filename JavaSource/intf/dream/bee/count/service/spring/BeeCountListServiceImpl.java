package intf.dream.bee.count.service.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.util.QueryBuffer;
import intf.dream.bee.asset.dao.BeeAssetListDAO;
import intf.dream.bee.cal.dao.BeeCalListDAO;
import intf.dream.bee.count.dao.BeeCountListDAO;
import intf.dream.bee.count.service.BeeCountListService;
import intf.dream.bee.doc.dao.BeeDocListDAO;
import intf.dream.bee.inspection.dao.BeeInspectionListDAO;
import intf.dream.bee.iss.dao.BeeIssListDAO;
import intf.dream.bee.maptpurreq.dao.BeeMaPtPurReqListDAO;
import intf.dream.bee.pmi.day.dao.BeePmiDayListDAO;
import intf.dream.bee.pmi.part.dao.BeePmiPartListDAO;
import intf.dream.bee.pmi.patrol.dao.BeePmiPatrolListDAO;
import intf.dream.bee.pmi.routine.dao.BeePmiRoutineListDAO;
import intf.dream.bee.pmwork.dao.BeePmworkListDAO;
import intf.dream.bee.stktake.dao.BeeStkTakeListDAO;
import intf.dream.bee.stock.dao.BeeStockListDAO;
import intf.dream.bee.unplan.dao.BeeUnPlanListDAO;
import intf.dream.bee.wodaily.dao.BeeWoDailyListDAO;
import intf.dream.bee.woreq.dao.BeeWoReqListDAO;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="beeCountListServiceTarget"
 * @spring.txbn id="beeCountListService"
 * @spring.property name="beeCountListDAO" ref="beeCountListDAO"
 * @spring.property name="beeWoReqListDAO" ref="beeWoReqListDAO"
 * @spring.property name="beeInspectionListDAO" ref="beeInspectionListDAO"
 * @spring.property name="beePmiDayListDAO" ref="beePmiDayListDAO"
 * @spring.property name="beePmiRoutineListDAO" ref="beePmiRoutineListDAO"
 * @spring.property name="beePmiPartListDAO" ref="beePmiPartListDAO"
 * @spring.property name="beePmiPatrolListDAO" ref="beePmiPatrolListDAO"
 * @spring.property name="beePmworkListDAO" ref="beePmworkListDAO"
 * @spring.property name="beeUnPlanListDAO" ref="beeUnPlanListDAO"
 * @spring.property name="beeIssListDAO" ref="beeIssListDAO"
 * @spring.property name="beeStockListDAO" ref="beeStockListDAO"
 * @spring.property name="beeStkTakeListDAO" ref="beeStkTakeListDAO"
 * @spring.property name="beeMaPtPurReqListDAO" ref="beeMaPtPurReqListDAO"
 * @spring.property name="beeAssetListDAO" ref="beeAssetListDAO"
 * @spring.property name="beeCalListDAO" ref="beeCalListDAO"
 * @spring.property name="beeDocListDAO" ref="beeDocListDAO"
 * @spring.property name="beeWoDailyListDAO" ref="beeWoDailyListDAO"
 */
public class BeeCountListServiceImpl implements BeeCountListService
{
    private BeeCountListDAO beeCountListDAO = null;
    private BeeWoReqListDAO beeWoReqListDAO = null;
    private BeeInspectionListDAO beeInspectionListDAO = null;
    private BeePmiDayListDAO beePmiDayListDAO = null;
    private BeePmiRoutineListDAO beePmiRoutineListDAO = null;
    private BeePmiPartListDAO beePmiPartListDAO = null;
    private BeePmiPatrolListDAO beePmiPatrolListDAO = null;
    private BeePmworkListDAO beePmworkListDAO = null;
    private BeeUnPlanListDAO beeUnPlanListDAO = null;
    private BeeIssListDAO beeIssListDAO = null;
    private BeeStockListDAO beeStockListDAO = null;
    private BeeStkTakeListDAO beeStkTakeListDAO = null;
    private BeeMaPtPurReqListDAO beeMaPtPurReqListDAO = null;
    private BeeAssetListDAO beeAssetListDAO = null;
    private BeeCalListDAO beeCalListDAO = null;
    private BeeDocListDAO beeDocListDAO = null;
    private BeeWoDailyListDAO beeWoDailyListDAO = null;

	public BeeWoDailyListDAO getBeeWoDailyListDAO() {
		return beeWoDailyListDAO;
	}
	public void setBeeWoDailyListDAO(BeeWoDailyListDAO beeWoDailyListDAO) {
		this.beeWoDailyListDAO = beeWoDailyListDAO;
	}
	public BeeDocListDAO getBeeDocListDAO() {
		return beeDocListDAO;
	}
	public void setBeeDocListDAO(BeeDocListDAO beeDocListDAO) {
		this.beeDocListDAO = beeDocListDAO;
	}
	public BeeCalListDAO getBeeCalListDAO() {
		return beeCalListDAO;
	}
	public void setBeeCalListDAO(BeeCalListDAO beeCalListDAO) {
		this.beeCalListDAO = beeCalListDAO;
	}
	public BeeAssetListDAO getBeeAssetListDAO() {
		return beeAssetListDAO;
	}
	public void setBeeAssetListDAO(BeeAssetListDAO beeAssetListDAO) {
		this.beeAssetListDAO = beeAssetListDAO;
	}
	public BeeMaPtPurReqListDAO getBeeMaPtPurReqListDAO() {
		return beeMaPtPurReqListDAO;
	}
	public void setBeeMaPtPurReqListDAO(BeeMaPtPurReqListDAO beeMaPtPurReqListDAO) {
		this.beeMaPtPurReqListDAO = beeMaPtPurReqListDAO;
	}
	public BeeStkTakeListDAO getBeeStkTakeListDAO() {
		return beeStkTakeListDAO;
	}
	public void setBeeStkTakeListDAO(BeeStkTakeListDAO beeStkTakeListDAO) {
		this.beeStkTakeListDAO = beeStkTakeListDAO;
	}
	public BeeStockListDAO getBeeStockListDAO() {
		return beeStockListDAO;
	}
	public void setBeeStockListDAO(BeeStockListDAO beeStockListDAO) {
		this.beeStockListDAO = beeStockListDAO;
	}
	public BeeIssListDAO getBeeIssListDAO() {
		return beeIssListDAO;
	}
	public void setBeeIssListDAO(BeeIssListDAO beeIssListDAO) {
		this.beeIssListDAO = beeIssListDAO;
	}
	public BeeUnPlanListDAO getBeeUnPlanListDAO() {
		return beeUnPlanListDAO;
	}
	public void setBeeUnPlanListDAO(BeeUnPlanListDAO beeUnPlanListDAO) {
		this.beeUnPlanListDAO = beeUnPlanListDAO;
	}
	public BeePmworkListDAO getBeePmworkListDAO() {
		return beePmworkListDAO;
	}
	public void setBeePmworkListDAO(BeePmworkListDAO beePmworkListDAO) {
		this.beePmworkListDAO = beePmworkListDAO;
	}
	public BeePmiPatrolListDAO getBeePmiPatrolListDAO() {
		return beePmiPatrolListDAO;
	}
	public void setBeePmiPatrolListDAO(BeePmiPatrolListDAO beePmiPatrolListDAO) {
		this.beePmiPatrolListDAO = beePmiPatrolListDAO;
	}
	public BeePmiPartListDAO getBeePmiPartListDAO() {
		return beePmiPartListDAO;
	}
	public void setBeePmiPartListDAO(BeePmiPartListDAO beePmiPartListDAO) {
		this.beePmiPartListDAO = beePmiPartListDAO;
	}
	public BeePmiRoutineListDAO getBeePmiRoutineListDAO() {
		return beePmiRoutineListDAO;
	}
	public void setBeePmiRoutineListDAO(BeePmiRoutineListDAO beePmiRoutineListDAO) {
		this.beePmiRoutineListDAO = beePmiRoutineListDAO;
	}
	public BeePmiDayListDAO getBeePmiDayListDAO() {
		return beePmiDayListDAO;
	}
	public void setBeePmiDayListDAO(BeePmiDayListDAO beePmiDayListDAO) {
		this.beePmiDayListDAO = beePmiDayListDAO;
	}
	public BeeInspectionListDAO getBeeInspectionListDAO() {
		return beeInspectionListDAO;
	}
	public void setBeeInspectionListDAO(BeeInspectionListDAO beeInspectionListDAO) {
		this.beeInspectionListDAO = beeInspectionListDAO;
	}
	public BeeWoReqListDAO getBeeWoReqListDAO() {
		return beeWoReqListDAO;
	}
	public void setBeeWoReqListDAO(BeeWoReqListDAO beeWoReqListDAO) {
		this.beeWoReqListDAO = beeWoReqListDAO;
	}
	public BeeCountListDAO getBeeCountListDAO() {
		return beeCountListDAO;
	}
	public void setBeeCountListDAO(BeeCountListDAO beeCountListDAO) {
		this.beeCountListDAO = beeCountListDAO;
	}
	
	public List findMainList(Map map) throws Exception
	{
		List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
		
		//작업요청
		Map<Object, Object> woReqMap = new HashMap<>();
		woReqMap.put("TITLE", "WOREQ");
		woReqMap.put("COUNT", QueryBuffer.listToString(beeWoReqListDAO.findWoReqCount(null, map)));
		list.add(woReqMap);
		//작업접수
		Map<Object, Object> woResMap = new HashMap<>();
		woResMap.put("TITLE", "WORES");
		woResMap.put("COUNT", QueryBuffer.listToString(beeWoReqListDAO.findWoResCount(null, map)));
		list.add(woResMap);
		//분해점검
		Map<Object, Object> inspectionMap = new HashMap<>();
		inspectionMap.put("TITLE", "INSPECTION");
		inspectionMap.put("COUNT", QueryBuffer.listToString(beeInspectionListDAO.findInspectionCount(null, map)));
		list.add(inspectionMap);
		//일상점검
		Map<Object, Object> pmiDayMap = new HashMap<>();
		pmiDayMap.put("TITLE", "PMIDAY");
		pmiDayMap.put("COUNT", QueryBuffer.listToString(beePmiDayListDAO.findPmiDayCount(null, map)));
		list.add(pmiDayMap);
		//부품교체점검
		Map<Object, Object> pmiPartMap = new HashMap<>();
		pmiPartMap.put("TITLE", "PMIPART");
		pmiPartMap.put("COUNT", QueryBuffer.listToString(beePmiPartListDAO.findPmiPartCount(null, map)));
		list.add(pmiPartMap);
		//정기점검
		Map<Object, Object> pmiRoutineMap = new HashMap<>();
		pmiRoutineMap.put("TITLE", "PMIROUTINE");
		pmiRoutineMap.put("COUNT", QueryBuffer.listToString(beePmiRoutineListDAO.findPmiRoutineCount(null, map)));
		list.add(pmiRoutineMap);
		//순회점검
		Map<Object, Object> pmiPatrolMap = new HashMap<>();
		pmiPatrolMap.put("TITLE", "PMIPATROL");
		pmiPatrolMap.put("COUNT", QueryBuffer.listToString(beePmiPatrolListDAO.findPmiPatrolCount(null, map)));
		list.add(pmiPatrolMap);
		//계획작업
		Map<Object, Object> pmWorkMap = new HashMap<>();
		pmWorkMap.put("TITLE", "PMWORK");
		pmWorkMap.put("COUNT", QueryBuffer.listToString(beePmworkListDAO.findPmworkCount(null, map)));
		list.add(pmWorkMap);
		//교정작업
		Map<Object, Object> calMap = new HashMap<>();
		calMap.put("TITLE", "CALIBRATION");
		calMap.put("COUNT", QueryBuffer.listToString(beeCalListDAO.findCalCount(null, map)));
		list.add(calMap);
		//돌발작업
		Map<Object, Object> unPlanMap = new HashMap<>();
		unPlanMap.put("TITLE", "UNPLAN");
		unPlanMap.put("COUNT", QueryBuffer.listToString(beeUnPlanListDAO.findUnPlanCount(null, map)));
		list.add(unPlanMap);
		//출고
		Map<Object, Object> issMap = new HashMap<>();
		issMap.put("TITLE", "ISS");
		issMap.put("COUNT", QueryBuffer.listToString(beeIssListDAO.findIssCount(null, map)));
		list.add(issMap);
		//현재고
		Map<Object, Object> stockMap = new HashMap<>();
		stockMap.put("TITLE", "STOCK");
		stockMap.put("COUNT", QueryBuffer.listToString(beeStockListDAO.findStockCount(null, map)));
		list.add(stockMap);
		//부품실사
		Map<Object, Object> stkTakeMap = new HashMap<>();
		stkTakeMap.put("TITLE", "STKTAKE");
		stkTakeMap.put("COUNT", QueryBuffer.listToString(beeStkTakeListDAO.findStkTakeCount(null, map)));
		list.add(stkTakeMap);
		//설비
		Map<Object, Object> equipmentMap = new HashMap<>();
		equipmentMap.put("TITLE", "EQUIPMENT");
		equipmentMap.put("COUNT", QueryBuffer.listToString(beeAssetListDAO.findAssetCount(null, map)));
		list.add(equipmentMap);
		//문서
		Map<Object, Object> documentMap = new HashMap<>();
		documentMap.put("TITLE", "DOCUMENT");
		documentMap.put("COUNT", String.valueOf(beeDocListDAO.findDocList(null, map).size()));
		list.add(documentMap);
		//현장구매신청
		Map<Object, Object> purReqMap = new HashMap<>();
		purReqMap.put("TITLE", "PTPURREQ");
		purReqMap.put("COUNT", QueryBuffer.listToString(beeMaPtPurReqListDAO.findMaPtPurReqCount(null, map)));
		list.add(purReqMap);
		//일일작업
		Map<Object, Object> woDailyMap = new HashMap<>();
		woDailyMap.put("TITLE", "WODAILY");
		woDailyMap.put("COUNT", QueryBuffer.listToString(beeWoDailyListDAO.findWoDailyCount(null, map)));
		list.add(woDailyMap);

		
		return list;
	}
}

