package dream.part.rep.service.spring;

import common.bean.MwareConfig;
import common.bean.User;
import dream.part.rep.dao.MaPtRepDetailDAO;
import dream.part.rep.dto.MaPtRepCommonDTO;
import dream.part.rep.dto.MaPtRepDetailDTO;
import dream.part.rep.service.MaPtRepDetailService;

/**
 * ��ǰ���� - �� serviceimpl 
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maPtRepDetailServiceTarget"
 * @spring.txbn id="maPtRepDetailService"
 * @spring.property name="maPtRepDetailDAO" ref="maPtRepDetailDAO"
 */
public class MaPtRepDetailServiceImpl implements MaPtRepDetailService
{
    private MaPtRepDetailDAO maPtRepDetailDAO = null;
    
    public MaPtRepDetailDAO getMaPtRepDetailDAO() 
    {
		return maPtRepDetailDAO;
	}

	public void setMaPtRepDetailDAO(MaPtRepDetailDAO maPtRepDetailDAO) 
	{
		this.maPtRepDetailDAO = maPtRepDetailDAO;
	}

	public MaPtRepDetailDTO findDetail(MaPtRepCommonDTO maPtRepCommonDTO, User loginUser)throws Exception
    {
        return maPtRepDetailDAO.findDetail(maPtRepCommonDTO, loginUser);
    }
	
	public int insertDetail(MaPtRepDetailDTO maPtRepDetailDTO, User loginUser) throws Exception
    {   
        
		if("N".equals(MwareConfig.getIsUsePartGrade())){
			maPtRepDetailDTO.setPartGrade(MwareConfig.getPartGrade()); //��ǰ����� ������� �ʴ� ���� ������ �ִ� ��ǰ����� �����.);
		}
		return maPtRepDetailDAO.insertDetail(maPtRepDetailDTO, loginUser);
    }
	
	public int updateDetail(MaPtRepDetailDTO maPtRepDetailDTO, User loginUser) throws Exception
    {        
		if("N".equals(MwareConfig.getIsUsePartGrade())){
			maPtRepDetailDTO.setPartGrade(MwareConfig.getPartGrade()); //��ǰ����� ������� �ʴ� ���� ������ �ִ� ��ǰ����� �����.);
		}
		return maPtRepDetailDAO.updateDetail(maPtRepDetailDTO, loginUser);
    }
	
	public int updatePtRepairListStatus(MaPtRepDetailDTO maPtRepDetailDTO, User loginUser) throws Exception
	{      
	    String ptRepairListStatus = maPtRepDetailDTO.getPtRepairListStatus();
	    
	    // �系�԰�, ���ּ����԰� ó�� 
        if("L".equals(ptRepairListStatus) || "V".equals(ptRepairListStatus))
        {
            String ptRecHistId = maPtRepDetailDAO.getNextSequence("SQAPTRECHIST_ID");
            maPtRepDetailDTO.setPtRecHistId(ptRecHistId);
            maPtRepDetailDAO.insertRecHistory(maPtRepDetailDTO, loginUser);
            maPtRepDetailDAO.executeSpPtInstock(maPtRepDetailDTO, loginUser);
        }
        
        String isSerial = maPtRepDetailDTO.getIsSerial();
        
        if("Y".equals(isSerial))
        {
            //�ø��� ����, ���ּ����Ƿ�
            if("S".equals(ptRepairListStatus))
            {
            	maPtRepDetailDAO.updateEquipment(maPtRepDetailDTO, loginUser,"O");
            	maPtRepDetailDAO.insertEqHistory(maPtRepDetailDTO, loginUser,"O");
            }
            //�系���� �Ϸ�
            else if("L".equals(ptRepairListStatus)||"V".equals(ptRepairListStatus))
            {
            	maPtRepDetailDAO.updateEquipment(maPtRepDetailDTO, loginUser,"S");
            	maPtRepDetailDAO.insertEqHistory(maPtRepDetailDTO, loginUser,"S");
            }
            //���� �Ұ�
            else if("X".equals(ptRepairListStatus))
            {
            	maPtRepDetailDAO.updateEquipment(maPtRepDetailDTO, loginUser,"D");
            	maPtRepDetailDAO.insertEqHistory(maPtRepDetailDTO, loginUser,"D");
            }
            
        }
        
	    return maPtRepDetailDAO.updatePtRepairListStatus(maPtRepDetailDTO, loginUser);
	}
	
	public int updatePtRepairListStatusCancel(MaPtRepDetailDTO maPtRepDetailDTO, User loginUser) throws Exception
	{   
	    // ���� ��� �� ���� ������Ʈ��, ���� ���¸� ��ȸ�Ѵ�.  
	    String ptRepairListStatus = maPtRepDetailDAO.findPtRepairListStatus(maPtRepDetailDTO, loginUser);
	    
        // ������� ���·� �����ϱ���, �系����(L) or ���ּ���(V) �� ��� �԰��̷¿� "���"�� ����� ��� �����ɼ��ְ� �Ѵ�. 
        if("L".equals(ptRepairListStatus) || "V".equals(ptRepairListStatus))
        {
            String ptRecHistId = maPtRepDetailDAO.getNextSequence("SQAPTRECHIST_ID");
            maPtRepDetailDTO.setPtRecHistId(ptRecHistId);
            maPtRepDetailDAO.insertRecHistory(maPtRepDetailDTO, loginUser);
            maPtRepDetailDAO.executeSpPtInstock(maPtRepDetailDTO, loginUser);
        }
	    // �԰��̷� ��� ó�� �� ���¸� �����Ѵ�. 
	    return maPtRepDetailDAO.updatePtRepairListStatus(maPtRepDetailDTO, loginUser);
	}
    
    public String validPtRepairListNo(MaPtRepDetailDTO maPtRepDetailDTO, User loginUser) throws Exception
    {
        return maPtRepDetailDAO.validPtRepairListNo(maPtRepDetailDTO, loginUser);
    }
}
