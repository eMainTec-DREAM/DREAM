package dream.mgr.contract.service.spring;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import common.bean.User;
import common.util.CommonUtil;
import common.util.DateUtil;
import dream.mgr.contract.dao.MgrContractDAO;
import dream.mgr.contract.dto.MgrContractDTO;
import dream.mgr.contract.service.MgrContractService;

/**
 * 단가계약설정 - 목록 serviceimpl
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="mgrContractServiceTarget"
 * @spring.txbn id="mgrContractService"
 * @spring.property name="mgrContractDAO" ref="mgrContractDAO"
 */
public class MgrContractServiceImpl implements MgrContractService
{
    private MgrContractDAO mgrContractDAO = null;

    public MgrContractDAO getMgrContractDAO() {
		return mgrContractDAO;
	}

	public void setMgrContractDAO(MgrContractDAO mgrContractDAO) {
		this.mgrContractDAO = mgrContractDAO;
	}

	public List findList(MgrContractDTO mgrContractDTO, User user)
    {      
        return mgrContractDAO.findList(mgrContractDTO, user);
    }
	
    public String findTotalCount(MgrContractDTO mgrContractDTO, User user)
    {
        return mgrContractDAO.findTotalCount(mgrContractDTO, user);
    }
    
    public int[] deleteList(String[] deleteRows, User user) throws Exception{
    	int[] result = null;
        MgrContractDTO mgrContractDTO = new MgrContractDTO();
        List<MgrContractDTO> list = new ArrayList<MgrContractDTO>();
        
        if(!deleteRows.equals(null)) {
        	for(int i=0; i<deleteRows.length; i++) {
        		mgrContractDTO.setContractId(deleteRows[i]);
        		
        		list.add((MgrContractDTO) BeanUtils.cloneBean(mgrContractDTO));
        	}
        	result = mgrContractDAO.deleteList(list, user);
        }
        
        return result;
    }
    
	public MgrContractDTO findDetail(MgrContractDTO mgrContractDTO,User user)throws Exception
    {
        return (MgrContractDTO)CommonUtil.makeDetailFromList(mgrContractDAO.findList(mgrContractDTO,user), mgrContractDTO);
    }
	
	public int[] insertDetail(List<MgrContractDTO> list, User user) throws Exception
	{
	    return mgrContractDAO.insertDetail(list,user);
	}
	
	public int insertDetail(MgrContractDTO mgrContractDTO, User user) throws Exception
	{
		if(CommonUtil.isNullCheck(mgrContractDTO.getContractId()))
			mgrContractDTO.setContractId(mgrContractDAO.getNextSequence("SQACONTRACT_ID"));
		
		List<MgrContractDTO> list = new ArrayList<MgrContractDTO>();
		list.add((MgrContractDTO) BeanUtils.cloneBean(mgrContractDTO));
		
		mgrContractDTO.setCreTime(DateUtil.getDateTime());
		mgrContractDTO.setUpdTime(DateUtil.getDateTime());
		
		int[] rtn =  this.insertDetail(list, user);
		
		return rtn[0];
	}
	
	public int updateDetail(MgrContractDTO mgrContractDTO, User user) throws Exception
    {        
		int[] result = null;
        
        List<MgrContractDTO> list = new ArrayList<MgrContractDTO>();
        list.add(mgrContractDTO);
        
        mgrContractDTO.setUpdTime(DateUtil.getDateTime());
        
        result = mgrContractDAO.updateDetail(list, user);
        
        return result[0];
    }
}

