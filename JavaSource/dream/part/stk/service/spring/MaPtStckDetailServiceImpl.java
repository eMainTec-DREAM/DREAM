package dream.part.stk.service.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.bean.User;
import common.util.CommonUtil;
import dream.part.stk.dao.MaPtStckDetailDAO;
import dream.part.stk.dto.MaPtStckCommonDTO;
import dream.part.stk.dto.MaPtStckDetailDTO;
import dream.part.stk.service.MaPtStckDetailService;

/**
 * 자재재고 - 상세 serviceimpl 
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maPtStckDetailServiceTarget"
 * @spring.txbn id="maPtStckDetailService"
 * @spring.property name="maPtStckDetailDAO" ref="maPtStckDetailDAO"
 */
public class MaPtStckDetailServiceImpl implements MaPtStckDetailService
{
    private MaPtStckDetailDAO maPtStckDetailDAO = null;
    
    public MaPtStckDetailDAO getMaPtStckDetailDAO() 
    {
		return maPtStckDetailDAO;
	}

	public void setMaPtStckDetailDAO(MaPtStckDetailDAO maPtStckDetailDAO) 
	{
		this.maPtStckDetailDAO = maPtStckDetailDAO;
	}

	public MaPtStckDetailDTO findDetail(MaPtStckCommonDTO maPtStckCommonDTO)throws Exception
    {
        MaPtStckDetailDTO maPtStckDetailDTO = maPtStckDetailDAO.findDetail(maPtStckCommonDTO);
        
        return maPtStckDetailDTO;
    }
	
	public int insertDetail(MaPtStckDetailDTO maPtStckDetailDTO, User loginUser) throws Exception
    {   
	    int result = 0;
//	    if(CommonUtil.parseInt(maPtStckDetailDTO.getAstockQty())>=0){
//	    	result = maPtStckDetailDAO.insertPtStock(maPtStckDetailDTO,maPtStckDetailDTO.getAstockQty(),"A");
//	    }
//	    if(CommonUtil.parseInt(maPtStckDetailDTO.getBstockQty())>=0){
//	    	result = maPtStckDetailDAO.insertPtStock(maPtStckDetailDTO,maPtStckDetailDTO.getBstockQty(),"B");
//	    }
	    // 안전재고 수량이 존재하는지 체크한다. 존재시  update 
	    String cntSaftyQty = maPtStckDetailDAO.validPtSaftyStck(maPtStckDetailDTO);
	    if(CommonUtil.parseInt(cntSaftyQty) == 0)
	    {
	        result =+ maPtStckDetailDAO.insertPtSaftyStock(maPtStckDetailDTO);
	    }
	    else
	    {
	        result =+ maPtStckDetailDAO.updatePtSaftyStock(maPtStckDetailDTO);
	    }
	    // TAPTRECHIST 데이터 생성 및 SP_PT_INSTOCK 프로시져 호출 
	    String compNo = maPtStckDetailDTO.getCompNo();
	    //A등급 재고 insert 
	    if(CommonUtil.parseInt(maPtStckDetailDTO.getAstockQty())>=0 || !"".equals(maPtStckDetailDTO.getAbinNo())){
	    	String nextPtRecHistId = maPtStckDetailDAO.getNextSequence("SQAPTRECHIST_ID");
		    // 최초입력시에는 입력한 재고수량을 입고수량으로 한다. 
		    maPtStckDetailDAO.insertPtRecHistory(nextPtRecHistId, maPtStckDetailDTO.getAstockQty(),"A", maPtStckDetailDTO, loginUser);
		    maPtStckDetailDAO.executeSpPtInStock(compNo, nextPtRecHistId);
	    }
	  //B등급 재고 insert 
	    if(CommonUtil.parseInt(maPtStckDetailDTO.getBstockQty())>=0 || !"".equals(maPtStckDetailDTO.getBbinNo())){
	    	String nextPtRecHistId = maPtStckDetailDAO.getNextSequence("SQAPTRECHIST_ID");
		    // 최초입력시에는 입력한 재고수량을 입고수량으로 한다. 
		    maPtStckDetailDAO.insertPtRecHistory(nextPtRecHistId, maPtStckDetailDTO.getBstockQty(),"B", maPtStckDetailDTO, loginUser);
		    maPtStckDetailDAO.executeSpPtInStock(compNo, nextPtRecHistId);
	    }
	    
	    updateDetail(maPtStckDetailDTO, loginUser);
	    
        return result;
    }
	
	public int updateDetail(MaPtStckDetailDTO maPtStckDetailDTO, User loginUser) throws Exception
    {
	    int result = 0;
	    //A등급 재고 update
		if(CommonUtil.parseDouble(maPtStckDetailDTO.getAstockQty())>=0 || !"".equals(maPtStckDetailDTO.getAbinNo())){
			/** 데이터 처리전 현재 재고와 변경된 재고 수량을 조회한다. 
		     *  입고/출고 이력 및 재고수량 처리에 사용함.        */
		    double oldStockQty = CommonUtil.parseDouble(maPtStckDetailDAO.getStockQty(maPtStckDetailDTO,"A"));
		    double newStockQty = CommonUtil.parseDouble(maPtStckDetailDTO.getAstockQty());
		    
		    // TAPTSTOCK 데이터 변경 
	        result = maPtStckDetailDAO.updatePtStock(maPtStckDetailDTO, "A", loginUser);
	        //result =+ maPtStckDetailDAO.updatePtSaftyStock(maPtStckDetailDTO);
	        
	        String cntSaftyQty = maPtStckDetailDAO.validPtSaftyStck(maPtStckDetailDTO);
		    if(CommonUtil.parseInt(cntSaftyQty) == 0)
		    {
		        result =+ maPtStckDetailDAO.insertPtSaftyStock(maPtStckDetailDTO);
		    }
		    else
		    {
		        result =+ maPtStckDetailDAO.updatePtSaftyStock(maPtStckDetailDTO);
		    }
		    
	        // 이력 테이블 insert 위한 데이터 Set
	        
	        // 수정된 재고수량이 기존수량보다 작을 경우, 수량차이만큼 출고처리 
	        if(oldStockQty > newStockQty) 
	        {
	            // TAPTISSHIST 생성 후, SP_PT_OUTSTOCK 호출 
	            String ptIssHistId = maPtStckDetailDAO.getNextSequence("SQAPTISSHIST_ID");
	            maPtStckDetailDAO.insertPtIssHistory(ptIssHistId, (oldStockQty-newStockQty)+"","A", maPtStckDetailDTO, loginUser);
	            maPtStckDetailDAO.executeSpPtOutStock(maPtStckDetailDTO.getCompNo(), ptIssHistId);
	        }
	        // 수정된 재고수량이 기존수량보다 클 경우, 수량차이만큼 입고처리 
	        else if(oldStockQty < newStockQty) 
	        {
	            // TAPTRECHIST 생성 후, SP_PT_INSTOCK 호출 
	            String ptRecHistId = maPtStckDetailDAO.getNextSequence("SQAPTRECHIST_ID");
	            maPtStckDetailDAO.insertPtRecHistory(ptRecHistId, (newStockQty-oldStockQty)+"","A", maPtStckDetailDTO, loginUser);
	            maPtStckDetailDAO.executeSpPtInStock(maPtStckDetailDTO.getCompNo(), ptRecHistId);
	        }
		}
		//B등급 재고 update
		if(CommonUtil.parseDouble(maPtStckDetailDTO.getBstockQty())>=0 || !"".equals(maPtStckDetailDTO.getBbinNo())){
			
			/** 데이터 처리전 현재 재고와 변경된 재고 수량을 조회한다. 
		     *  입고/출고 이력 및 재고수량 처리에 사용함.        */
			double oldStockQty = CommonUtil.parseDouble(maPtStckDetailDAO.getStockQty(maPtStckDetailDTO,"B"));
			double newStockQty = CommonUtil.parseDouble(maPtStckDetailDTO.getBstockQty());
		    
		    // TAPTSTOCK 데이터 변경 
	        result = maPtStckDetailDAO.updatePtStock(maPtStckDetailDTO, "B", loginUser);
	        //result =+ maPtStckDetailDAO.updatePtSaftyStock(maPtStckDetailDTO);
	        
	        String cntSaftyQty = maPtStckDetailDAO.validPtSaftyStck(maPtStckDetailDTO);
		    if(CommonUtil.parseInt(cntSaftyQty) == 0)
		    {
		        result =+ maPtStckDetailDAO.insertPtSaftyStock(maPtStckDetailDTO);
		    }
		    else
		    {
		        result =+ maPtStckDetailDAO.updatePtSaftyStock(maPtStckDetailDTO);
		    }
		    
	        // 이력 테이블 insert 위한 데이터 Set
	        
	        // 수정된 재고수량이 기존수량보다 작을 경우, 수량차이만큼 출고처리 
	        if(oldStockQty > newStockQty) 
	        {
	            // TAPTISSHIST 생성 후, SP_PT_OUTSTOCK 호출 
	            String ptIssHistId = maPtStckDetailDAO.getNextSequence("SQAPTISSHIST_ID");
	            maPtStckDetailDAO.insertPtIssHistory(ptIssHistId, (oldStockQty-newStockQty)+"","B", maPtStckDetailDTO, loginUser);
	            maPtStckDetailDAO.executeSpPtOutStock(maPtStckDetailDTO.getCompNo(), ptIssHistId);
	        }
	        // 수정된 재고수량이 기존수량보다 클 경우, 수량차이만큼 입고처리 
	        else if(oldStockQty < newStockQty) 
	        {
	            // TAPTRECHIST 생성 후, SP_PT_INSTOCK 호출 
	            String ptRecHistId = maPtStckDetailDAO.getNextSequence("SQAPTRECHIST_ID");
	            maPtStckDetailDAO.insertPtRecHistory(ptRecHistId, (newStockQty-oldStockQty)+"","B", maPtStckDetailDTO, loginUser);
	            maPtStckDetailDAO.executeSpPtInStock(maPtStckDetailDTO.getCompNo(), ptRecHistId);
	        }
		}
        return result;
    }
    
    public String validPtStck(MaPtStckDetailDTO maPtStckDetailDTO) throws Exception
    {
        return maPtStckDetailDAO.validPtStck(maPtStckDetailDTO);
    }
    public List getReportView(MaPtStckDetailDTO maPtStckDetailDTO,User user) {
		Map<String, Object> reportMap = null;
		List<Map<String, Object>> reportList = new ArrayList<Map<String, Object>>();
		
		List detailList = maPtStckDetailDAO.findReportPtDetail(maPtStckDetailDTO,user);
     	reportMap = (Map)detailList.get(0);
     	reportMap.put("PT_LIST", maPtStckDetailDAO.findReportPartList(maPtStckDetailDTO,user));
        
     	reportList.add((Map)reportMap);

	     return reportList;
	}
    
}
