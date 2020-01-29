package dream.part.pur.buy.service.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.bean.MwareConfig;
import common.bean.User;
import common.util.CommonUtil;
import common.util.DateUtil;
import common.util.MailUtil;
import common.util.StringUtil;
import dream.mgr.message.service.MgrMessageTransDetailService;
import dream.part.pur.buy.dao.MaPtBuyReqHdrDetailDAO;
import dream.part.pur.buy.dto.MaPtBuyReqDetailDTO;
import dream.part.pur.buy.dto.MaPtBuyReqHdrCommonDTO;
import dream.part.pur.buy.dto.MaPtBuyReqHdrDetailDTO;
import dream.part.pur.buy.dto.MaPtBuyReqListDTO;
import dream.part.pur.buy.service.MaPtBuyReqHdrDetailService;
import dream.part.pur.buy.service.MaPtBuyReqListService;
import dream.part.pur.po.dto.MaPtPoDetailDTO;
import dream.part.pur.po.dto.PartPurPoItemDTO;
import dream.part.pur.po.service.MaPtPoDetailService;
import dream.part.pur.po.service.PartPurPoItemService;
import dream.part.pur.req.dto.MaPtPurReqDetailDTO;
import dream.part.pur.req.dto.MaPtReqCommonDTO;
import dream.part.pur.req.service.MaPtPurReqDetailService;
import dream.part.rec.dto.MaPtRecDetailDTO;
import dream.part.rec.service.MaPtRecDetailService;
import dream.pers.appreq.dto.AppReqDetailDTO;

/**
 * 구매신청 - 상세 serviceimpl 
 * @author  kim21017
 * @version $Id: MaPtBuyReqHdrDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maPtBuyReqHdrDetailServiceTarget"
 * @spring.txbn id="maPtBuyReqHdrDetailService"
 * @spring.property name="maPtBuyReqHdrDetailDAO" ref="maPtBuyReqHdrDetailDAO"
 * @spring.property name="mgrMessageTransDetailService" ref="mgrMessageTransDetailService"
 */
public class MaPtBuyReqHdrDetailServiceImpl implements MaPtBuyReqHdrDetailService
{
    private MaPtBuyReqHdrDetailDAO maPtBuyReqHdrDetailDAO = null;
    private MgrMessageTransDetailService mgrMessageTransDetailService = null;
    
    public MgrMessageTransDetailService getMgrMessageTransDetailService() {
		return mgrMessageTransDetailService;
	}

	public void setMgrMessageTransDetailService(MgrMessageTransDetailService mgrMessageTransDetailService) {
		this.mgrMessageTransDetailService = mgrMessageTransDetailService;
	}

	public MaPtBuyReqHdrDetailDAO getMaPtBuyReqHdrDetailDAO() {
		return maPtBuyReqHdrDetailDAO;
	}

	public void setMaPtBuyReqHdrDetailDAO(MaPtBuyReqHdrDetailDAO maPtBuyReqHdrDetailDAO) {
		this.maPtBuyReqHdrDetailDAO = maPtBuyReqHdrDetailDAO;
	}

	public MaPtBuyReqHdrDetailDTO findDetail(MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO, User user)throws Exception
    {
        return maPtBuyReqHdrDetailDAO.findDetail(maPtBuyReqHdrCommonDTO,user);
    }
	
	public int updateDetail(MaPtBuyReqHdrDetailDTO maPtBuyReqHdrDetailDTO, User user) throws Exception
    {        
        return maPtBuyReqHdrDetailDAO.updateDetail(maPtBuyReqHdrDetailDTO, user);
    }
	
	public String[] confirmDetail(MaPtBuyReqHdrDetailDTO maPtBuyReqHdrDetailDTO, User user) throws Exception
    {        
	    String[] result = new String[2];
	    result[0] = "S";
		
		if("Y".equals(MwareConfig.getIsPrConfirmToReceipt()))//IS_PR_CONFIRM_TO_RECEIPT  Y:PO,GR 데이터 생성
		{
		    //발주서 작성
            MaPtPoDetailService maPtPoDetailService = (MaPtPoDetailService) CommonUtil.getBean("maPtPoDetailService", user);
            MaPtPoDetailDTO maPtPoDetailDTO = new MaPtPoDetailDTO();
            String poListId = maPtBuyReqHdrDetailDAO.getNextSequence("SQAPOLIST_ID");
            maPtPoDetailDTO.setPoListId(poListId);
            maPtPoDetailDTO.setPoListNo(poListId);
            maPtPoDetailDTO.setPoStatusId("GRW");
            maPtPoDetailDTO.setPlant(maPtBuyReqHdrDetailDTO.getPlantId());
            maPtPoDetailDTO.setDeptId(maPtBuyReqHdrDetailDTO.getDeptId());
            maPtPoDetailDTO.setPoDate(maPtBuyReqHdrDetailDTO.getReqDate());
            maPtPoDetailDTO.setCurrency("WON");
            maPtPoDetailDTO.setTotPrice(maPtBuyReqHdrDetailDTO.getTotAmt());
            maPtPoDetailDTO.setVendorId(maPtBuyReqHdrDetailDTO.getVendorId());
            maPtPoDetailDTO.setWcodeId(maPtBuyReqHdrDetailDTO.getWcodeId());
            maPtPoDetailService.insertDetail(maPtPoDetailDTO, user);
            
            //발주 품목 작성
            PartPurPoItemService partPurPoItemService = (PartPurPoItemService) CommonUtil.getBean("partPurPoItemService", user);
            PartPurPoItemDTO partPurPoItemDTO;
            MaPtBuyReqListService maPtBuyReqListService = (MaPtBuyReqListService) CommonUtil.getBean("maPtBuyReqListService", user);
            MaPtBuyReqDetailDTO maPtBuyReqDetailDTO;
            MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO = new MaPtBuyReqHdrCommonDTO();
            maPtBuyReqHdrCommonDTO.setPtPrListId(maPtBuyReqHdrDetailDTO.getPtPrListId());
            List<Map> prItemList = maPtBuyReqListService.findItemList(maPtBuyReqHdrCommonDTO, new MaPtBuyReqListDTO(), user);
            for(Map map:prItemList){
                maPtBuyReqDetailDTO = (MaPtBuyReqDetailDTO) CommonUtil.makeDTO(map, MaPtBuyReqDetailDTO.class);
                partPurPoItemDTO = new PartPurPoItemDTO();
                partPurPoItemDTO.setPtPoItemId(maPtBuyReqHdrDetailDAO.getNextSequence("SQAPTPOITEM_ID"));
                partPurPoItemDTO.setPoListId(poListId);
                partPurPoItemDTO.setPartId(maPtBuyReqDetailDTO.getPartId());
                partPurPoItemDTO.setPartGrade(maPtBuyReqDetailDTO.getPartGrade());
                partPurPoItemDTO.setPoQty(maPtBuyReqDetailDTO.getRecQty());
                partPurPoItemDTO.setUnitPrice(maPtBuyReqDetailDTO.getUnitPrice());
                partPurPoItemDTO.setTotPrice(maPtBuyReqDetailDTO.getTotalPrice());
                partPurPoItemDTO.setPtPrItemId(maPtBuyReqDetailDTO.getPtPrItemId());
                partPurPoItemService.insertDetail(partPurPoItemDTO, user);
            }
		    
            //구매입고서 작성
            MaPtRecDetailService maPtRecDetailService = (MaPtRecDetailService) CommonUtil.getBean("maPtRecDetailService", user);
            MaPtRecDetailDTO maPtRecDetailDTO;
            partPurPoItemDTO = new PartPurPoItemDTO();
            String prRecListId;
            partPurPoItemDTO.setPoListId(poListId);
            List<Map> poItemList = partPurPoItemService.findList(partPurPoItemDTO, user);
            for(Map map:poItemList){
                partPurPoItemDTO = (PartPurPoItemDTO) CommonUtil.makeDTO(map, PartPurPoItemDTO.class);
                maPtRecDetailDTO = new MaPtRecDetailDTO();
                prRecListId = maPtBuyReqHdrDetailDAO.getNextSequence("SQAPRRECLIST_ID");
                maPtRecDetailDTO.setPrRecListId(prRecListId);
                maPtRecDetailDTO.setPrRecListNo(prRecListId);
                maPtRecDetailDTO.setPrRecListStatus("W");
                maPtRecDetailDTO.setPlantId(partPurPoItemDTO.getPlant());
                maPtRecDetailDTO.setDeptId(partPurPoItemDTO.getDeptId());
                maPtRecDetailDTO.setWcodeId(partPurPoItemDTO.getWcodeId());
                maPtRecDetailDTO.setVendorId(partPurPoItemDTO.getVendorId());
                maPtRecDetailDTO.setRecDate(partPurPoItemDTO.getPoDate());
                maPtRecDetailDTO.setPartId(partPurPoItemDTO.getPartId());
                maPtRecDetailDTO.setPartDesc(partPurPoItemDTO.getPartDesc());
                maPtRecDetailDTO.setPartSize(partPurPoItemDTO.getPtSize());
                maPtRecDetailDTO.setUom(partPurPoItemDTO.getUom());
                maPtRecDetailDTO.setPtpritemId(partPurPoItemDTO.getPtPrItemId());
                maPtRecDetailDTO.setPoitemId(partPurPoItemDTO.getPtPoItemId());
                maPtRecDetailDTO.setRecQty(partPurPoItemDTO.getPoQty());
                maPtRecDetailDTO.setUnitPrice(partPurPoItemDTO.getUnitPrice());
                maPtRecDetailDTO.setTotPrice(partPurPoItemDTO.getTotPrice());
                maPtRecDetailService.insertDetail(maPtRecDetailDTO, user);
            }
		    
			maPtBuyReqHdrDetailDTO.setPtPrListStatusId("GRW");
		}
		else if("Y".equals(MwareConfig.getPocreateAfterPrcomp()))// POCREATE_AFTER_PRCOMP  Y: PO 데이터 생성
		{
		    //발주서 작성
		    MaPtPoDetailService maPtPoDetailService = (MaPtPoDetailService) CommonUtil.getBean("maPtPoDetailService", user);
		    MaPtPoDetailDTO maPtPoDetailDTO = new MaPtPoDetailDTO();
		    String poListId = maPtBuyReqHdrDetailDAO.getNextSequence("SQAPOLIST_ID");
		    maPtPoDetailDTO.setPoListId(poListId);
		    maPtPoDetailDTO.setPoListNo(poListId);
		    maPtPoDetailDTO.setPoStatusId("C");
		    maPtPoDetailDTO.setPlant(maPtBuyReqHdrDetailDTO.getPlantId());
		    maPtPoDetailDTO.setDeptId(maPtBuyReqHdrDetailDTO.getDeptId());
		    maPtPoDetailDTO.setPoDate(maPtBuyReqHdrDetailDTO.getReqDate());
		    maPtPoDetailDTO.setCurrency("WON");
		    maPtPoDetailDTO.setTotPrice(maPtBuyReqHdrDetailDTO.getTotAmt());
		    maPtPoDetailDTO.setVendorId(maPtBuyReqHdrDetailDTO.getVendorId());
		    maPtPoDetailDTO.setWcodeId(maPtBuyReqHdrDetailDTO.getWcodeId());
		    maPtPoDetailService.insertDetail(maPtPoDetailDTO, user);
		    
		    //발주 품목 작성
		    PartPurPoItemService partPurPoItemService = (PartPurPoItemService) CommonUtil.getBean("partPurPoItemService", user);
		    PartPurPoItemDTO partPurPoItemDTO;
		    MaPtBuyReqListService maPtBuyReqListService = (MaPtBuyReqListService) CommonUtil.getBean("maPtBuyReqListService", user);
		    MaPtBuyReqDetailDTO maPtBuyReqDetailDTO;
		    MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO = new MaPtBuyReqHdrCommonDTO();
		    maPtBuyReqHdrCommonDTO.setPtPrListId(maPtBuyReqHdrDetailDTO.getPtPrListId());
		    List<Map> prItemList = maPtBuyReqListService.findItemList(maPtBuyReqHdrCommonDTO, new MaPtBuyReqListDTO(), user);
		    for(Map map:prItemList){
		        maPtBuyReqDetailDTO = (MaPtBuyReqDetailDTO) CommonUtil.makeDTO(map, MaPtBuyReqDetailDTO.class);
		        partPurPoItemDTO = new PartPurPoItemDTO();
		        partPurPoItemDTO.setPtPoItemId(maPtBuyReqHdrDetailDAO.getNextSequence("SQAPTPOITEM_ID"));
		        partPurPoItemDTO.setPoListId(poListId);
		        partPurPoItemDTO.setPartId(maPtBuyReqDetailDTO.getPartId());
		        partPurPoItemDTO.setPartGrade(maPtBuyReqDetailDTO.getPartGrade());
		        partPurPoItemDTO.setPoQty(maPtBuyReqDetailDTO.getRecQty());
		        partPurPoItemDTO.setUnitPrice(maPtBuyReqDetailDTO.getUnitPrice());
		        partPurPoItemDTO.setTotPrice(maPtBuyReqDetailDTO.getTotalPrice());
		        partPurPoItemDTO.setPtPrItemId(maPtBuyReqDetailDTO.getPtPrItemId());
		        partPurPoItemService.insertDetail(partPurPoItemDTO, user);
		    }
		    
		    maPtBuyReqHdrDetailDTO.setPtPrListStatusId("POC");
		}
		else
		{
		    maPtBuyReqHdrDetailDTO.setPtPrListStatusId("C");
		}
		
		// 구매신청 상태 업데이트
		this.updateDetail(maPtBuyReqHdrDetailDTO, user);
		
		// 현장구매청구 상태, 수량 업데이트
        MaPtPurReqDetailService maPtPurReqDetailService = (MaPtPurReqDetailService) CommonUtil.getBean("maPtPurReqDetailService", user);
        MaPtReqCommonDTO maPtReqCommonDTO = new MaPtReqCommonDTO();
        MaPtPurReqDetailDTO maPtPurReqDetailDTO;
        MaPtBuyReqListService maPtBuyReqListService = (MaPtBuyReqListService) CommonUtil.getBean("maPtBuyReqListService", user);
        MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO = new MaPtBuyReqHdrCommonDTO();
        maPtBuyReqHdrCommonDTO.setPtPrListId(maPtBuyReqHdrDetailDTO.getPtPrListId());
        String[] ptPnListIds = StringUtil.toSingleArray(maPtBuyReqListService.findItemList(maPtBuyReqHdrCommonDTO, new MaPtBuyReqListDTO(), user), "PTPNLISTID");
        for(String ptPnListId:ptPnListIds){
            if(!CommonUtil.isNullCheck(ptPnListId)){
                maPtReqCommonDTO.setReqId(ptPnListId);
                maPtPurReqDetailDTO = maPtPurReqDetailService.findDetail(maPtReqCommonDTO, user);
                maPtPurReqDetailDTO.setPrDate(DateUtil.getTimeStamp(user.getOffset()));
                maPtPurReqDetailDTO = maPtPurReqDetailService.getQty(maPtPurReqDetailDTO, user);
                maPtPurReqDetailDTO = maPtPurReqDetailService.getStatus(maPtPurReqDetailDTO, user);
                maPtPurReqDetailService.updateDetail(maPtPurReqDetailDTO, user);
            }
        }
		
		// 접수자가 있으면 확정시에 메일링
		MailUtil.sendMail("PRI10", maPtBuyReqHdrDetailDTO.getPtPrListId(), user);
		
        return result;
    }
	
	public String checkDetail(MaPtBuyReqHdrDetailDTO maPtBuyReqHdrDetailDTO) throws Exception
    {        
        return maPtBuyReqHdrDetailDAO.checkDetail(maPtBuyReqHdrDetailDTO);
    }
	public int insertDetail(MaPtBuyReqHdrDetailDTO maPtBuyReqHdrDetailDTO, User loginUser) throws Exception
    {        
        return maPtBuyReqHdrDetailDAO.insertDetail(maPtBuyReqHdrDetailDTO,loginUser);
    }
    public List getReportView(MaPtBuyReqHdrDetailDTO maPtBuyReqHdrDetailDTO) {
		Map<String, Object> reportMap = null;
		List<Map<String, Object>> reportList = new ArrayList<Map<String, Object>>();
		
		List detailList = maPtBuyReqHdrDetailDAO.findReportPtDetail(maPtBuyReqHdrDetailDTO);
     	reportMap = (Map)detailList.get(0);
     	reportMap.put("PT_LIST", maPtBuyReqHdrDetailDAO.findReportPartList(maPtBuyReqHdrDetailDTO));
        
     	reportList.add((Map)reportMap);

	     return reportList;
	}
    public void appProcess(AppReqDetailDTO appReqDetailDTO, User user) throws Exception
    {
        if("C".equals(appReqDetailDTO.getParentStatus())) {
            MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO = new MaPtBuyReqHdrCommonDTO();
            maPtBuyReqHdrCommonDTO.setPtPrListId(appReqDetailDTO.getObjectId());
            MaPtBuyReqHdrDetailDTO maPtBuyReqHdrDetailDTO = maPtBuyReqHdrDetailDAO.findDetail(maPtBuyReqHdrCommonDTO, user);
            maPtBuyReqHdrDetailDTO.setReqDate(appReqDetailDTO.getReqDate());
            confirmDetail(maPtBuyReqHdrDetailDTO, user);
        }
        else {
            maPtBuyReqHdrDetailDAO.setStatus(appReqDetailDTO, user);
        }
    }
    
    @Override
    public MaPtBuyReqHdrDetailDTO getStatus(MaPtBuyReqHdrDetailDTO maPtBuyReqHdrDetailDTO, User user) throws Exception
    {
        double prQty = 0;
        double poOnQty = 0;
        double poQty = 0;
        double grOnQty = 0;
        double grQty = 0;
        MaPtBuyReqListService maPtBuyReqListService = (MaPtBuyReqListService) CommonUtil.getBean("maPtBuyReqListService", user);
        MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO = new MaPtBuyReqHdrCommonDTO();
        maPtBuyReqHdrCommonDTO.setPtPrListId(maPtBuyReqHdrDetailDTO.getPtPrListId());
        List<Map> list = maPtBuyReqListService.findItemList(maPtBuyReqHdrCommonDTO, new MaPtBuyReqListDTO(), user);
        for(Map map:list){
            prQty += toDouble(StringUtil.valueOf(map.get("RECQTY")));
            poOnQty += toDouble(StringUtil.valueOf(map.get("POONQTY")));
            poQty += toDouble(StringUtil.valueOf(map.get("POQTY")));
            grOnQty += toDouble(StringUtil.valueOf(map.get("GRONQTY")));
            grQty += toDouble(StringUtil.valueOf(map.get("GRQTY")));
        }
        
        //C > POW > POC > GRW > GRC
        if(poOnQty==0) maPtBuyReqHdrDetailDTO.setPtPrListStatusId("C");
        if(poOnQty>0) maPtBuyReqHdrDetailDTO.setPtPrListStatusId("POW");
        if(prQty<=poQty) maPtBuyReqHdrDetailDTO.setPtPrListStatusId("POC");
        if(grOnQty>0) maPtBuyReqHdrDetailDTO.setPtPrListStatusId("GRW");
        if(prQty<=grQty) maPtBuyReqHdrDetailDTO.setPtPrListStatusId("GRC");
        
        return maPtBuyReqHdrDetailDTO;
    }
    private double toDouble(String str)
    {
        try{
            return "".equals(str)?0:Double.parseDouble(str);
        }catch(NumberFormatException nfe){
            nfe.printStackTrace();
            return 0;
        }
    }
}
