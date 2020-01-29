package dream.tool.stk.service.spring;

import common.bean.User;
import common.util.CommonUtil;
import dream.tool.stk.dao.MaPttStckDetailDAO;
import dream.tool.stk.dto.MaPttStckCommonDTO;
import dream.tool.stk.dto.MaPttStckDetailDTO;
import dream.tool.stk.service.MaPttStckDetailService;

/**
 * 자재재고 - 상세 serviceimpl 
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maPttStckDetailServiceTarget"
 * @spring.txbn id="maPttStckDetailService"
 * @spring.property name="maPttStckDetailDAO" ref="maPttStckDetailDAO"
 */
public class MaPttStckDetailServiceImpl implements MaPttStckDetailService
{
    private MaPttStckDetailDAO maPttStckDetailDAO = null;
    
    public MaPttStckDetailDAO getMaPttStckDetailDAO() 
    {
		return maPttStckDetailDAO;
	}

	public void setMaPttStckDetailDAO(MaPttStckDetailDAO maPttStckDetailDAO) 
	{
		this.maPttStckDetailDAO = maPttStckDetailDAO;
	}

	public MaPttStckDetailDTO findDetail(MaPttStckCommonDTO maPttStckCommonDTO)throws Exception
    {
        MaPttStckDetailDTO maPttStckDetailDTO = maPttStckDetailDAO.findDetail(maPttStckCommonDTO);
        
        return maPttStckDetailDTO;
    }
	
	public int insertDetail(MaPttStckDetailDTO maPttStckDetailDTO, User loginUser) throws Exception
    {   
	    int result = 0;

//	    result = maPttStckDetailDAO.insertPtStock(maPttStckDetailDTO,maPttStckDetailDTO.getBstockQty(),"B");
	   
	    // 안전재고 수량이 존재하는지 체크한다. 존재시  update 
	    String cntSaftyQty = maPttStckDetailDAO.validPtSaftyStck(maPttStckDetailDTO);
	    if(CommonUtil.parseInt(cntSaftyQty) == 0)
	    {
	        result =+ maPttStckDetailDAO.insertPtSaftyStock(maPttStckDetailDTO);
	    }
	    else
	    {
	        result =+ maPttStckDetailDAO.updatePtSaftyStock(maPttStckDetailDTO);
	    }
	    // TAPTRECHIST 데이터 생성 및 SP_PT_INSTOCK 프로시져 호출 
	    String compNo = maPttStckDetailDTO.getCompNo();

	  //B등급 재고 insert 

	    String nextPtRecHistId = maPttStckDetailDAO.getNextSequence("SQAPTRECHIST_ID");
		// 최초입력시에는 입력한 재고수량을 입고수량으로 한다. 
		maPttStckDetailDAO.insertPtRecHistory(nextPtRecHistId, maPttStckDetailDTO.getBstockQty(),"B", maPttStckDetailDTO, loginUser);
		maPttStckDetailDAO.executeSpPtInStock(compNo, nextPtRecHistId);
	  
	    
        return result;
    }
	
	public int updateDetail(MaPttStckDetailDTO maPttStckDetailDTO, User loginUser) throws Exception
    {
	    int result = 0;

		//B등급 재고 update

			/** 데이터 처리전 현재 재고와 변경된 재고 수량을 조회한다. 
		     *  입고/출고 이력 및 재고수량 처리에 사용함.        */
	    	double oldStockQty = CommonUtil.parseDouble(maPttStckDetailDAO.getStockQty(maPttStckDetailDTO,"B"));
	    	double newStockQty = CommonUtil.parseDouble(maPttStckDetailDTO.getBstockQty());
		    
		    // TAPTSTOCK 데이터 변경 
	        result = maPttStckDetailDAO.updatePtStock(maPttStckDetailDTO, "B");
	        //result =+ maPttStckDetailDAO.updatePtSaftyStock(maPttStckDetailDTO);
	        
	        String cntSaftyQty = maPttStckDetailDAO.validPtSaftyStck(maPttStckDetailDTO);
		    if(CommonUtil.parseInt(cntSaftyQty) == 0)
		    {
		        result =+ maPttStckDetailDAO.insertPtSaftyStock(maPttStckDetailDTO);
		    }
		    else
		    {
		        result =+ maPttStckDetailDAO.updatePtSaftyStock(maPttStckDetailDTO);
		    }
		    
	        // 이력 테이블 insert 위한 데이터 Set
	        
	        // 수정된 재고수량이 기존수량보다 작을 경우, 수량차이만큼 출고처리 
	        if(oldStockQty > newStockQty) 
	        {
	            // TAPTISSHIST 생성 후, SP_PT_OUTSTOCK 호출 
	            String ptIssHistId = maPttStckDetailDAO.getNextSequence("SQAPTISSHIST_ID");
	            maPttStckDetailDAO.insertPtIssHistory(ptIssHistId, (oldStockQty-newStockQty)+"","B", maPttStckDetailDTO, loginUser);
	            maPttStckDetailDAO.executeSpPtOutStock(maPttStckDetailDTO.getCompNo(), ptIssHistId);
	        }
	        // 수정된 재고수량이 기존수량보다 클 경우, 수량차이만큼 입고처리 
	        else if(oldStockQty < newStockQty) 
	        {
	            // TAPTRECHIST 생성 후, SP_PT_INSTOCK 호출 
	            String ptRecHistId = maPttStckDetailDAO.getNextSequence("SQAPTRECHIST_ID");
	            maPttStckDetailDAO.insertPtRecHistory(ptRecHistId, (newStockQty-oldStockQty)+"","B", maPttStckDetailDTO, loginUser);
	            maPttStckDetailDAO.executeSpPtInStock(maPttStckDetailDTO.getCompNo(), ptRecHistId);
	        }
	
        return result;
    }
    
    public String validPtStck(MaPttStckDetailDTO maPttStckDetailDTO) throws Exception
    {
        return maPttStckDetailDAO.validPtStck(maPttStckDetailDTO);
    }
    
}
