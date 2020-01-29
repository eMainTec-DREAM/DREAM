package dream.mgr.usage.cal.service.spring;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import common.bean.User;
import common.util.CommonUtil;
import dream.mgr.usage.cal.dao.MgrUsageCalSetDAO;
import dream.mgr.usage.cal.dto.MgrUsageCalSetDTO;
import dream.mgr.usage.cal.service.MgrUsageCalSetService;

/**
 * 사용달력설정 - 목록 serviceimpl
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="mgrUsageCalSetServiceTarget"
 * @spring.txbn id="mgrUsageCalSetService"
 * @spring.property name="mgrUsageCalSetDAO" ref="mgrUsageCalSetDAO"
 */
public class MgrUsageCalSetServiceImpl implements MgrUsageCalSetService
{
    private MgrUsageCalSetDAO mgrUsageCalSetDAO = null;

    public MgrUsageCalSetDAO getMgrUsageCalSetDAO() {
		return mgrUsageCalSetDAO;
	}

	public void setMgrUsageCalSetDAO(MgrUsageCalSetDAO mgrUsageCalSetDAO) {
		this.mgrUsageCalSetDAO = mgrUsageCalSetDAO;
	}

	public List findList(MgrUsageCalSetDTO mgrUsageCalSetDTO, User user)
    {      
        return mgrUsageCalSetDAO.findList(mgrUsageCalSetDTO, user);
    }
	
    public String findTotalCount(MgrUsageCalSetDTO mgrUsageCalSetDTO, User user)
    {
        return mgrUsageCalSetDAO.findTotalCount(mgrUsageCalSetDTO, user);
    }
    
    public int[] deleteList(String[] deleteRows, User user) throws Exception{
    	int[] result = null;
        MgrUsageCalSetDTO mgrUsageCalSetDTO = new MgrUsageCalSetDTO();
        List<MgrUsageCalSetDTO> list = new ArrayList<MgrUsageCalSetDTO>();
        
        if(!deleteRows.equals(null)) {
        	for(int i=0; i<deleteRows.length; i++) {
        		mgrUsageCalSetDTO.setLnWrkListId(deleteRows[i]);
        		
        		list.add((MgrUsageCalSetDTO) BeanUtils.cloneBean(mgrUsageCalSetDTO));
        	}
        	result = mgrUsageCalSetDAO.deleteList(list, user);
        }
        
        return result;
    }
    
	public MgrUsageCalSetDTO findDetail(MgrUsageCalSetDTO mgrUsageCalSetDTO,User user)throws Exception
    {
        return (MgrUsageCalSetDTO)CommonUtil.makeDetailFromList(mgrUsageCalSetDAO.findList(mgrUsageCalSetDTO,user), mgrUsageCalSetDTO);
    }
	
	public int[] insertDetail(List<MgrUsageCalSetDTO> list, User user) throws Exception
	{
	    return mgrUsageCalSetDAO.insertDetail(list,user);
	}
	
	public int insertDetail(MgrUsageCalSetDTO mgrUsageCalSetDTO, User user) throws Exception
	{
		if(CommonUtil.isNullCheck(mgrUsageCalSetDTO.getLnWrkListId()))
			mgrUsageCalSetDTO.setLnWrkListId(mgrUsageCalSetDAO.getNextSequence("SQALNWRKLIST_ID"));
		
		List<MgrUsageCalSetDTO> list = new ArrayList<MgrUsageCalSetDTO>();
		list.add((MgrUsageCalSetDTO) BeanUtils.cloneBean(mgrUsageCalSetDTO));
		
		int[] rtn =  this.insertDetail(list, user);
		
		return rtn[0];
	}
	
	public int updateDetail(MgrUsageCalSetDTO mgrUsageCalSetDTO, User user) throws Exception
    {        
		int[] result = null;
        
        List<MgrUsageCalSetDTO> list = new ArrayList<MgrUsageCalSetDTO>();
        list.add(mgrUsageCalSetDTO);
        
        result = mgrUsageCalSetDAO.updateDetail(list, user);
        
        return result[0];
    }
}

