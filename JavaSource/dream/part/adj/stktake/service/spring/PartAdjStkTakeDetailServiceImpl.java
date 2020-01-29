package dream.part.adj.stktake.service.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.part.adj.stktake.dao.PartAdjStkTakeDetailDAO;
import dream.part.adj.stktake.dao.PartAdjStkTakeItemDetailDAO;
import dream.part.adj.stktake.dto.PartAdjStkTakeCommonDTO;
import dream.part.adj.stktake.dto.PartAdjStkTakeDetailDTO;
import dream.part.adj.stktake.service.PartAdjStkTakeDetailService;
import dream.pers.appreq.dto.AppReqDetailDTO;

/**
 * 부품실사 - 상세 serviceimpl 
 * @author  kim21017
 * @version $Id: PartAdjStkTakeDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="partAdjStkTakeDetailServiceTarget"
 * @spring.txbn id="partAdjStkTakeDetailService"
 * @spring.property name="partAdjStkTakeDetailDAO" ref="partAdjStkTakeDetailDAO"
 * @spring.property name="partAdjStkTakeItemDetailDAO" ref="partAdjStkTakeItemDetailDAO"
 */
public class PartAdjStkTakeDetailServiceImpl implements PartAdjStkTakeDetailService
{
    private PartAdjStkTakeDetailDAO partAdjStkTakeDetailDAO = null;
    // 구매신청 item
    private PartAdjStkTakeItemDetailDAO partAdjStkTakeItemDetailDAO = null;
    
    
    public PartAdjStkTakeDetailDAO getPartAdjStkTakeDetailDAO() {
		return partAdjStkTakeDetailDAO;
	}
	public void setPartAdjStkTakeDetailDAO(PartAdjStkTakeDetailDAO partAdjStkTakeDetailDAO) {
		this.partAdjStkTakeDetailDAO = partAdjStkTakeDetailDAO;
	}
	public PartAdjStkTakeItemDetailDAO getPartAdjStkTakeItemDetailDAO() {
		return partAdjStkTakeItemDetailDAO;
	}
	public void setPartAdjStkTakeItemDetailDAO(PartAdjStkTakeItemDetailDAO partAdjStkTakeItemDetailDAO) {
		this.partAdjStkTakeItemDetailDAO = partAdjStkTakeItemDetailDAO;
	}
	
	public PartAdjStkTakeDetailDTO findDetail(PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO, User user)throws Exception
    {
        return partAdjStkTakeDetailDAO.findDetail(partAdjStkTakeCommonDTO,user);
    }
	
	public int updateDetail(PartAdjStkTakeDetailDTO partAdjStkTakeDetailDTO, User user) throws Exception
    {        
        return partAdjStkTakeDetailDAO.updateDetail(partAdjStkTakeDetailDTO);
    }
	
	public int confirmDetail(PartAdjStkTakeDetailDTO partAdjStkTakeDetailDTO, User user) throws Exception
    {        
		 List resultList = null;
	     List returnList = null;
		 resultList = partAdjStkTakeDetailDAO.findItemList(partAdjStkTakeDetailDTO, user);
		 returnList = new ArrayList();
		
		 for(Object resultMap : resultList)
         {
             Map result = (Map)resultMap;
             String partId = String.valueOf(result.get("PARTID"));
             float gapQty = Float.parseFloat(String.valueOf(result.get("GAPQTY")));
             float oldStockQty = Float.parseFloat(String.valueOf(result.get("STOCKQTY")));
             float newStockQty = Float.parseFloat(String.valueOf(result.get("REALQTY")));
             String ptGrade = (String.valueOf(result.get("PTGRADE")));
             String ptStkTakeItemId= (String.valueOf(result.get("PTSTKTAKEITEMID")));
             
             //갭이 0 이상인경우 입고
             if(gapQty>0)
             {	
            	// TAPTRECHIST 생성 후, SP_PT_INSTOCK 호출 
 	            String ptRecHistId = partAdjStkTakeDetailDAO.getNextSequence("SQAPTRECHIST_ID");
 	            partAdjStkTakeDetailDAO.insertPtRecHistory(ptRecHistId, (newStockQty-oldStockQty)+"",ptGrade, partAdjStkTakeDetailDTO, user, ptStkTakeItemId);
 	            partAdjStkTakeDetailDAO.updateItemRecHistory(ptRecHistId, (newStockQty-oldStockQty)+"",ptGrade, partAdjStkTakeDetailDTO, user, ptStkTakeItemId);
 	            partAdjStkTakeDetailDAO.executeSpPtInStock(user.getCompNo(), ptRecHistId);
             }
             //0 보다 작은경우 출고
             else if(gapQty<0)
             {
            	 // TAPTISSHIST 생성 후, SP_PT_OUTSTOCK 호출 
 	            String ptIssHistId = partAdjStkTakeDetailDAO.getNextSequence("SQAPTISSHIST_ID");
 	            partAdjStkTakeDetailDAO.insertPtIssHistory(ptIssHistId, (oldStockQty-newStockQty)+"",ptGrade, partAdjStkTakeDetailDTO, user, ptStkTakeItemId);
 	           partAdjStkTakeDetailDAO.updateItemIssHistory(ptIssHistId, (oldStockQty-newStockQty)+"",ptGrade, partAdjStkTakeDetailDTO, user, ptStkTakeItemId);
 	            partAdjStkTakeDetailDAO.executeSpPtOutStock(user.getCompNo(), ptIssHistId);
             }

             returnList.add(result);
             //System.out.println("docGrade:"+docGrade+"     userGrade:"+userGrade);
         }
	    return  partAdjStkTakeDetailDAO.confirmDetail(partAdjStkTakeDetailDTO, user);
    }

	public int insertDetail(PartAdjStkTakeDetailDTO partAdjStkTakeDetailDTO, User user) throws Exception
    {
		PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO = new PartAdjStkTakeCommonDTO();
		partAdjStkTakeCommonDTO.setPtStkTakeListId(partAdjStkTakeDetailDTO.getPtStkTakeListId());
		
		int result = partAdjStkTakeDetailDAO.insertDetail(partAdjStkTakeDetailDTO);

		partAdjStkTakeItemDetailDAO.insertDefaultItem(partAdjStkTakeCommonDTO, user);
		
        return result;
    }

    @Override
    public void appProcess(AppReqDetailDTO appReqDetailDTO, User user) throws Exception
    {
        if("C".equals(appReqDetailDTO.getParentStatus())) {
            PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO = new PartAdjStkTakeCommonDTO();
            partAdjStkTakeCommonDTO.setPtStkTakeListId(appReqDetailDTO.getObjectId());
            PartAdjStkTakeDetailDTO partAdjStkTakeDetailDTO = partAdjStkTakeDetailDAO.findDetail(partAdjStkTakeCommonDTO, user);
            
            confirmDetail(partAdjStkTakeDetailDTO, user);
        }
        else {
            partAdjStkTakeDetailDAO.setStatus(appReqDetailDTO, user);
        }
    }

}
