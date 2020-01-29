package dream.part.pur.buy.service.spring;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import common.bean.User;
import common.util.CommonUtil;
import dream.part.pur.buy.dao.MaPtBuyReqListDAO;
import dream.part.pur.buy.dto.MaPtBuyReqDetailDTO;
import dream.part.pur.buy.dto.MaPtBuyReqHdrCommonDTO;
import dream.part.pur.buy.dto.MaPtBuyReqListDTO;
import dream.part.pur.buy.service.MaPtBuyReqDetailService;
import dream.part.pur.buy.service.MaPtBuyReqListService;
import dream.part.pur.req.dto.MaPtPurReqDetailDTO;
import dream.part.pur.req.dto.MaPtReqCommonDTO;
import dream.part.pur.req.service.MaPtPurReqDetailService;

/**
 * 구매신청item 목록 serviceimpl
 * @author kim21017
 * @version $Id: MaPtBuyReqListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maPtBuyReqListServiceTarget"
 * @spring.txbn id="maPtBuyReqListService"
 * @spring.property name="maPtBuyReqListDAO" ref="maPtBuyReqListDAO"
 * @spring.property name="maPtBuyReqDetailService" ref="maPtBuyReqDetailService"
 */
public class MaPtBuyReqListServiceImpl implements MaPtBuyReqListService
{
    private MaPtBuyReqListDAO maPtBuyReqListDAO = null;
    private MaPtBuyReqDetailService maPtBuyReqDetailService = null;

    public MaPtBuyReqDetailService getMaPtBuyReqDetailService()
    {
        return maPtBuyReqDetailService; 
    }
    public void setMaPtBuyReqDetailService(
            MaPtBuyReqDetailService maPtBuyReqDetailService)
    {
        this.maPtBuyReqDetailService = maPtBuyReqDetailService;
    }
    public MaPtBuyReqListDAO getMaPtBuyReqListDAO() {
		return maPtBuyReqListDAO;
	}
	public void setMaPtBuyReqListDAO(MaPtBuyReqListDAO maPtBuyReqListDAO) {
		this.maPtBuyReqListDAO = maPtBuyReqListDAO;
	}
	
	public List findItemList(MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO, MaPtBuyReqListDTO maPtBuyReqListDTO, User user)
    {      
        return maPtBuyReqListDAO.findItemList(maPtBuyReqHdrCommonDTO, maPtBuyReqListDTO, user);
    }
	
	public int deleteItemList(String[] deleteRows , String[] deleteRowsExt, String[] deleteRowsExt1, User user) throws Exception{
		int result = 0;
        for(int i = 0; deleteRows.length > i ; i++)
        {
            // ptprlist_id : deleteRows
    		// ptpritem_id : deleteRowsExt
    		// ptpnlist_id : deleteRowsExt1
        	
            result = result + maPtBuyReqListDAO.deleteItemList(deleteRows[i], deleteRowsExt[i], user);
            
            // 부품삭제시 현장구매신청건이고 해당 현장구매신청의 상태가 구매신청 이전이면(C) 접수일자, 구매청구일자 초기화
            if (!"".equals(deleteRowsExt1[i]) && deleteRowsExt1[i] != null) {
                MaPtPurReqDetailService maPtPurReqDetailService = (MaPtPurReqDetailService) CommonUtil.getBean("maPtPurReqDetailService", user);
                MaPtReqCommonDTO maPtReqCommonDTO = new MaPtReqCommonDTO();
                maPtReqCommonDTO.setReqId(deleteRowsExt1[i]);
                MaPtPurReqDetailDTO maPtPurReqDetailDTO = maPtPurReqDetailService.findDetail(maPtReqCommonDTO, user);
                maPtPurReqDetailDTO = maPtPurReqDetailService.getQty(maPtPurReqDetailDTO, user);
                maPtPurReqDetailDTO = maPtPurReqDetailService.getStatus(maPtPurReqDetailDTO, user);
                if("C".equals(maPtPurReqDetailDTO.getInputStatus())){
                    maPtPurReqDetailDTO.setRecDate("");
                    maPtPurReqDetailDTO.setPrDate("");
                }
                maPtPurReqDetailService.updateDetail(maPtPurReqDetailDTO, user);
            }
        }
        
        return result;
    }
    @Override
    public int insertItemList(MaPtBuyReqDetailDTO maPtBuyReqDetailDTO, MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO, User user) throws Exception
    {
        int result = 0;
        
        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(maPtBuyReqDetailDTO.getMultiDesc());
        
        for(Object obj:array){
            JSONObject object = (JSONObject) obj;
            String seq = maPtBuyReqListDAO.getNextSequence("SQAPTPRITEM_ID");
            maPtBuyReqDetailDTO.setPtPrItemId(seq);
            maPtBuyReqDetailDTO.setPartId((String) object.get("PART_ID"));
            maPtBuyReqDetailDTO.setPartDesc((String) object.get("DESCRIPTION"));
            maPtBuyReqDetailDTO.setPtSize((String) object.get("PT_SIZE"));
            maPtBuyReqDetailDTO.setUnitPrice((String) object.get("LAST_PRICE"));
            
            result = result + maPtBuyReqDetailService.insertDetail(maPtBuyReqDetailDTO, maPtBuyReqHdrCommonDTO, user);
        }
        
        return result;
    }
    
	@Override
	public int insertItemListByPtPn(MaPtBuyReqDetailDTO maPtBuyReqDetailDTO, MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO, User user) throws Exception {
		
        int result = 0;
        MaPtPurReqDetailService maPtPurReqDetailService = (MaPtPurReqDetailService) CommonUtil.getBean("maPtPurReqDetailService", user);
        MaPtReqCommonDTO maPtReqCommonDTO = new MaPtReqCommonDTO();
        MaPtPurReqDetailDTO maPtPurReqDetailDTO;
        
        String[] ptpnlistIds = maPtBuyReqDetailDTO.getPtPnListIds().split("\\+");
        String curr = maPtBuyReqDetailDTO.getCurr();
        String accntType = maPtBuyReqDetailDTO.getAccntType();
        double recQty;
        
        if (ptpnlistIds != null && !("".equals(ptpnlistIds))) {
	        for (int i = 0; i < ptpnlistIds.length; i++)
	        {
	            maPtReqCommonDTO.setReqId(ptpnlistIds[i]);
	            maPtPurReqDetailDTO = maPtPurReqDetailService.findDetail(maPtReqCommonDTO, user);
	            
	            recQty = toDouble(CommonUtil.getRowMoneyToNum(maPtPurReqDetailDTO.getQty())) - toDouble(CommonUtil.getRowMoneyToNum(maPtPurReqDetailDTO.getPrOnQty()));
	        	
	            maPtBuyReqDetailDTO.setPtPrItemId(maPtBuyReqListDAO.getNextSequence("SQAPTPRITEM_ID"));
	            maPtBuyReqDetailDTO.setPtPnListId(maPtPurReqDetailDTO.getReqId());
	            maPtBuyReqDetailDTO.setPartId(maPtPurReqDetailDTO.getPartId());
	            maPtBuyReqDetailDTO.setPartDesc(maPtPurReqDetailDTO.getPartDesc());
	            maPtBuyReqDetailDTO.setPtSize(maPtPurReqDetailDTO.getPtSize());
	            maPtBuyReqDetailDTO.setAppReqById(maPtPurReqDetailDTO.getEnterById());
	            maPtBuyReqDetailDTO.setRemark(maPtPurReqDetailDTO.getRemark());
	            maPtBuyReqDetailDTO.setUnitPrice(maPtPurReqDetailDTO.getLastPrice());
	            maPtBuyReqDetailDTO.setRecQty(String.valueOf(recQty));
	        	maPtBuyReqDetailDTO.setCurr(curr);
	        	maPtBuyReqDetailDTO.setAccntType(accntType);
	        	
	        	result = result + maPtBuyReqDetailService.insertDetail(maPtBuyReqDetailDTO, maPtBuyReqHdrCommonDTO, user);
			}
        }
        
        return result;
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
	
	@Override
	public String findTotalCount(MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO, MaPtBuyReqListDTO maPtBuyReqListDTO, User user) throws Exception {
        return maPtBuyReqListDAO.findTotalCount(maPtBuyReqHdrCommonDTO, maPtBuyReqListDTO, user);
	}
	
	@Override
    public void saveList(List<Map> gridList, User user) throws Exception
    {
	    MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO = null;
	    MaPtBuyReqDetailDTO maPtBuyReqDetailDTO = null;
        
        for(Map map : gridList)
        {
            maPtBuyReqHdrCommonDTO = (MaPtBuyReqHdrCommonDTO)CommonUtil.makeDTO(map, MaPtBuyReqHdrCommonDTO.class);
            maPtBuyReqDetailDTO = (MaPtBuyReqDetailDTO)CommonUtil.makeDTO(map, MaPtBuyReqDetailDTO.class);
            maPtBuyReqHdrCommonDTO.setCompNo(user.getCompNo());
            
            switch(maPtBuyReqDetailDTO.getNativeeditor())
            {
                case "inserted":
                    break;
                case "updated" : maPtBuyReqDetailService.updateDetail(maPtBuyReqDetailDTO, maPtBuyReqHdrCommonDTO, user);
                    break;
                case "deleted": 
                    break;
            }
            
        }
    }
}

