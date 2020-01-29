package dream.mgr.contract.service.spring;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import common.bean.User;
import common.util.CommonUtil;
import dream.mgr.contract.dao.MgrContractItemDAO;
import dream.mgr.contract.dto.MgrContractItemDTO;
import dream.mgr.contract.service.MgrContractItemService;

/**
 * 단가계약 설정  목록
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="mgrContractItemServiceTarget"
 * @spring.txbn id="mgrContractItemService"
 * @spring.property name="mgrContractItemDAO" ref="mgrContractItemDAO"
 */
public class MgrContractItemServiceImpl implements MgrContractItemService
{
    private MgrContractItemDAO mgrContractItemDAO = null;


	public List findList(MgrContractItemDTO mgrContractItemDTO, User loginUser)
    {      
        return mgrContractItemDAO.findList(mgrContractItemDTO, loginUser);
    }

    public String findTotalCount(MgrContractItemDTO mgrContractItemDTO, User user)
    {      
    	return mgrContractItemDAO.findTotalCount(mgrContractItemDTO, user);
    }
    
	
	public MgrContractItemDAO getMgrContractItemDAO() {
		return mgrContractItemDAO;
	}

	public void setMgrContractItemDAO(MgrContractItemDAO mgrContractItemDAO) {
		this.mgrContractItemDAO = mgrContractItemDAO;
	}
	
	public int[] deleteList(String[] deleteRows, User user) throws Exception{
		int[] result = null;
        MgrContractItemDTO mgrContractItemDTO = new MgrContractItemDTO();
        List<MgrContractItemDTO> list = new ArrayList<MgrContractItemDTO>();
        
        if(!deleteRows.equals(null)) {
        	for(int i=0; i<deleteRows.length; i++) {
        		mgrContractItemDTO.setContractItemId(deleteRows[i]);
        		
        		list.add((MgrContractItemDTO)BeanUtils.cloneBean(mgrContractItemDTO));
        	}
        	result = mgrContractItemDAO.deleteList(list, user);
        }
        
        return result;
    }
	
	@Override
	public MgrContractItemDTO findDetail(MgrContractItemDTO mgrContractItemDTO, User user) throws Exception 
	{
		return (MgrContractItemDTO)CommonUtil.makeDetailFromList(mgrContractItemDAO.findList(mgrContractItemDTO, user), mgrContractItemDTO);
	}
	
	public int updateDetail(MgrContractItemDTO mgrContractItemDTO, User user) throws Exception
    {
		int[] result = null;
        
        List<MgrContractItemDTO> list = new ArrayList<MgrContractItemDTO>();
        list.add(mgrContractItemDTO);
        
        result = mgrContractItemDAO.updateDetail(list, user);
        
        return result[0];
    }
	
	public int insertDetail(MgrContractItemDTO mgrContractItemDTO, User user) throws Exception
	{
		if(CommonUtil.isNullCheck(mgrContractItemDTO.getContractItemId()))
			mgrContractItemDTO.setContractItemId(mgrContractItemDAO.getNextSequence("SQACONTRACTITEM_ID"));
		
		List<MgrContractItemDTO> list = new ArrayList<MgrContractItemDTO>();
		list.add(mgrContractItemDTO);
		
		int[] rtn =  this.insertDetail(list, user);
		
		return rtn[0];
	}

	public int[] insertDetail(List<MgrContractItemDTO> list, User user) throws Exception
    {
        return mgrContractItemDAO.insertDetail(list, user);
    }
	
}

