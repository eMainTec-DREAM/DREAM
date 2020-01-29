package dream.tool.stk.service.spring;

import common.bean.User;
import common.util.CommonUtil;
import dream.tool.stk.dao.MaPttStckDetailDAO;
import dream.tool.stk.dto.MaPttStckCommonDTO;
import dream.tool.stk.dto.MaPttStckDetailDTO;
import dream.tool.stk.service.MaPttStckDetailService;

/**
 * ������� - �� serviceimpl 
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
	   
	    // ������� ������ �����ϴ��� üũ�Ѵ�. �����  update 
	    String cntSaftyQty = maPttStckDetailDAO.validPtSaftyStck(maPttStckDetailDTO);
	    if(CommonUtil.parseInt(cntSaftyQty) == 0)
	    {
	        result =+ maPttStckDetailDAO.insertPtSaftyStock(maPttStckDetailDTO);
	    }
	    else
	    {
	        result =+ maPttStckDetailDAO.updatePtSaftyStock(maPttStckDetailDTO);
	    }
	    // TAPTRECHIST ������ ���� �� SP_PT_INSTOCK ���ν��� ȣ�� 
	    String compNo = maPttStckDetailDTO.getCompNo();

	  //B��� ��� insert 

	    String nextPtRecHistId = maPttStckDetailDAO.getNextSequence("SQAPTRECHIST_ID");
		// �����Է½ÿ��� �Է��� �������� �԰�������� �Ѵ�. 
		maPttStckDetailDAO.insertPtRecHistory(nextPtRecHistId, maPttStckDetailDTO.getBstockQty(),"B", maPttStckDetailDTO, loginUser);
		maPttStckDetailDAO.executeSpPtInStock(compNo, nextPtRecHistId);
	  
	    
        return result;
    }
	
	public int updateDetail(MaPttStckDetailDTO maPttStckDetailDTO, User loginUser) throws Exception
    {
	    int result = 0;

		//B��� ��� update

			/** ������ ó���� ���� ���� ����� ��� ������ ��ȸ�Ѵ�. 
		     *  �԰�/��� �̷� �� ������ ó���� �����.        */
	    	double oldStockQty = CommonUtil.parseDouble(maPttStckDetailDAO.getStockQty(maPttStckDetailDTO,"B"));
	    	double newStockQty = CommonUtil.parseDouble(maPttStckDetailDTO.getBstockQty());
		    
		    // TAPTSTOCK ������ ���� 
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
		    
	        // �̷� ���̺� insert ���� ������ Set
	        
	        // ������ �������� ������������ ���� ���, �������̸�ŭ ���ó�� 
	        if(oldStockQty > newStockQty) 
	        {
	            // TAPTISSHIST ���� ��, SP_PT_OUTSTOCK ȣ�� 
	            String ptIssHistId = maPttStckDetailDAO.getNextSequence("SQAPTISSHIST_ID");
	            maPttStckDetailDAO.insertPtIssHistory(ptIssHistId, (oldStockQty-newStockQty)+"","B", maPttStckDetailDTO, loginUser);
	            maPttStckDetailDAO.executeSpPtOutStock(maPttStckDetailDTO.getCompNo(), ptIssHistId);
	        }
	        // ������ �������� ������������ Ŭ ���, �������̸�ŭ �԰�ó�� 
	        else if(oldStockQty < newStockQty) 
	        {
	            // TAPTRECHIST ���� ��, SP_PT_INSTOCK ȣ�� 
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
