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
 * ������� - �� serviceimpl 
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
	    // ������� ������ �����ϴ��� üũ�Ѵ�. �����  update 
	    String cntSaftyQty = maPtStckDetailDAO.validPtSaftyStck(maPtStckDetailDTO);
	    if(CommonUtil.parseInt(cntSaftyQty) == 0)
	    {
	        result =+ maPtStckDetailDAO.insertPtSaftyStock(maPtStckDetailDTO);
	    }
	    else
	    {
	        result =+ maPtStckDetailDAO.updatePtSaftyStock(maPtStckDetailDTO);
	    }
	    // TAPTRECHIST ������ ���� �� SP_PT_INSTOCK ���ν��� ȣ�� 
	    String compNo = maPtStckDetailDTO.getCompNo();
	    //A��� ��� insert 
	    if(CommonUtil.parseInt(maPtStckDetailDTO.getAstockQty())>=0 || !"".equals(maPtStckDetailDTO.getAbinNo())){
	    	String nextPtRecHistId = maPtStckDetailDAO.getNextSequence("SQAPTRECHIST_ID");
		    // �����Է½ÿ��� �Է��� �������� �԰�������� �Ѵ�. 
		    maPtStckDetailDAO.insertPtRecHistory(nextPtRecHistId, maPtStckDetailDTO.getAstockQty(),"A", maPtStckDetailDTO, loginUser);
		    maPtStckDetailDAO.executeSpPtInStock(compNo, nextPtRecHistId);
	    }
	  //B��� ��� insert 
	    if(CommonUtil.parseInt(maPtStckDetailDTO.getBstockQty())>=0 || !"".equals(maPtStckDetailDTO.getBbinNo())){
	    	String nextPtRecHistId = maPtStckDetailDAO.getNextSequence("SQAPTRECHIST_ID");
		    // �����Է½ÿ��� �Է��� �������� �԰�������� �Ѵ�. 
		    maPtStckDetailDAO.insertPtRecHistory(nextPtRecHistId, maPtStckDetailDTO.getBstockQty(),"B", maPtStckDetailDTO, loginUser);
		    maPtStckDetailDAO.executeSpPtInStock(compNo, nextPtRecHistId);
	    }
	    
	    updateDetail(maPtStckDetailDTO, loginUser);
	    
        return result;
    }
	
	public int updateDetail(MaPtStckDetailDTO maPtStckDetailDTO, User loginUser) throws Exception
    {
	    int result = 0;
	    //A��� ��� update
		if(CommonUtil.parseDouble(maPtStckDetailDTO.getAstockQty())>=0 || !"".equals(maPtStckDetailDTO.getAbinNo())){
			/** ������ ó���� ���� ���� ����� ��� ������ ��ȸ�Ѵ�. 
		     *  �԰�/��� �̷� �� ������ ó���� �����.        */
		    double oldStockQty = CommonUtil.parseDouble(maPtStckDetailDAO.getStockQty(maPtStckDetailDTO,"A"));
		    double newStockQty = CommonUtil.parseDouble(maPtStckDetailDTO.getAstockQty());
		    
		    // TAPTSTOCK ������ ���� 
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
		    
	        // �̷� ���̺� insert ���� ������ Set
	        
	        // ������ �������� ������������ ���� ���, �������̸�ŭ ���ó�� 
	        if(oldStockQty > newStockQty) 
	        {
	            // TAPTISSHIST ���� ��, SP_PT_OUTSTOCK ȣ�� 
	            String ptIssHistId = maPtStckDetailDAO.getNextSequence("SQAPTISSHIST_ID");
	            maPtStckDetailDAO.insertPtIssHistory(ptIssHistId, (oldStockQty-newStockQty)+"","A", maPtStckDetailDTO, loginUser);
	            maPtStckDetailDAO.executeSpPtOutStock(maPtStckDetailDTO.getCompNo(), ptIssHistId);
	        }
	        // ������ �������� ������������ Ŭ ���, �������̸�ŭ �԰�ó�� 
	        else if(oldStockQty < newStockQty) 
	        {
	            // TAPTRECHIST ���� ��, SP_PT_INSTOCK ȣ�� 
	            String ptRecHistId = maPtStckDetailDAO.getNextSequence("SQAPTRECHIST_ID");
	            maPtStckDetailDAO.insertPtRecHistory(ptRecHistId, (newStockQty-oldStockQty)+"","A", maPtStckDetailDTO, loginUser);
	            maPtStckDetailDAO.executeSpPtInStock(maPtStckDetailDTO.getCompNo(), ptRecHistId);
	        }
		}
		//B��� ��� update
		if(CommonUtil.parseDouble(maPtStckDetailDTO.getBstockQty())>=0 || !"".equals(maPtStckDetailDTO.getBbinNo())){
			
			/** ������ ó���� ���� ���� ����� ��� ������ ��ȸ�Ѵ�. 
		     *  �԰�/��� �̷� �� ������ ó���� �����.        */
			double oldStockQty = CommonUtil.parseDouble(maPtStckDetailDAO.getStockQty(maPtStckDetailDTO,"B"));
			double newStockQty = CommonUtil.parseDouble(maPtStckDetailDTO.getBstockQty());
		    
		    // TAPTSTOCK ������ ���� 
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
		    
	        // �̷� ���̺� insert ���� ������ Set
	        
	        // ������ �������� ������������ ���� ���, �������̸�ŭ ���ó�� 
	        if(oldStockQty > newStockQty) 
	        {
	            // TAPTISSHIST ���� ��, SP_PT_OUTSTOCK ȣ�� 
	            String ptIssHistId = maPtStckDetailDAO.getNextSequence("SQAPTISSHIST_ID");
	            maPtStckDetailDAO.insertPtIssHistory(ptIssHistId, (oldStockQty-newStockQty)+"","B", maPtStckDetailDTO, loginUser);
	            maPtStckDetailDAO.executeSpPtOutStock(maPtStckDetailDTO.getCompNo(), ptIssHistId);
	        }
	        // ������ �������� ������������ Ŭ ���, �������̸�ŭ �԰�ó�� 
	        else if(oldStockQty < newStockQty) 
	        {
	            // TAPTRECHIST ���� ��, SP_PT_INSTOCK ȣ�� 
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
