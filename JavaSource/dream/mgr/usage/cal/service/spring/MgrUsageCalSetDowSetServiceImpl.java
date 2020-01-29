package dream.mgr.usage.cal.service.spring;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import common.bean.User;
import common.util.CommonUtil;
import dream.mgr.usage.cal.dao.MgrUsageCalSetDowSetDAO;
import dream.mgr.usage.cal.dto.MgrUsageCalSetDTO;
import dream.mgr.usage.cal.dto.MgrUsageCalSetDowSetDTO;
import dream.mgr.usage.cal.service.MgrUsageCalSetDowSetService;

/**
 * 요일별 설정  목록
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="mgrUsageCalSetDowSetServiceTarget"
 * @spring.txbn id="mgrUsageCalSetDowSetService"
 * @spring.property name="mgrUsageCalSetDowSetDAO" ref="mgrUsageCalSetDowSetDAO"
 */
public class MgrUsageCalSetDowSetServiceImpl implements MgrUsageCalSetDowSetService
{
    private MgrUsageCalSetDowSetDAO mgrUsageCalSetDowSetDAO = null;


	public List findDowList(MgrUsageCalSetDowSetDTO mgrUsageCalSetDowSetDTO, User loginUser)
    {      
//        return mgrUsageCalSetDowSetDAO.findDowList(mgrUsageCalSetDTO, mgrUsageCalSetDowSetDTO, loginUser);
        return mgrUsageCalSetDowSetDAO.findDowList(mgrUsageCalSetDowSetDTO, loginUser);
    }

    public String findTotalCount(MgrUsageCalSetDowSetDTO mgrUsageCalSetDowSetDTO, User user)
    {      
    	return mgrUsageCalSetDowSetDAO.findTotalCount(mgrUsageCalSetDowSetDTO, user);
    }
    
	
	public MgrUsageCalSetDowSetDAO getMgrUsageCalSetDowSetDAO() {
		return mgrUsageCalSetDowSetDAO;
	}

	public void setMgrUsageCalSetDowSetDAO(MgrUsageCalSetDowSetDAO mgrUsageCalSetDowSetDAO) {
		this.mgrUsageCalSetDowSetDAO = mgrUsageCalSetDowSetDAO;
	}
	
	public int[] deleteDowList(String[] deleteRows, User user) throws Exception{
		int[] result = null;
        MgrUsageCalSetDowSetDTO mgrUsageCalSetDowSetDTO = new MgrUsageCalSetDowSetDTO();
        List<MgrUsageCalSetDowSetDTO> list = new ArrayList<MgrUsageCalSetDowSetDTO>();
        
        if(!deleteRows.equals(null)) {
        	for(int i=0; i<deleteRows.length; i++) {
        		mgrUsageCalSetDowSetDTO.setEqLocDowRunId(deleteRows[i]);
        		
        		list.add((MgrUsageCalSetDowSetDTO)BeanUtils.cloneBean(mgrUsageCalSetDowSetDTO));
        	}
        	result = mgrUsageCalSetDowSetDAO.deleteDowList(list, user);
        }
        
        return result;
    }
	
	public String validDay(MgrUsageCalSetDTO mgrUsageCalSetDTO, MgrUsageCalSetDowSetDTO mgrUsageCalSetDowSetDTO, User user) throws Exception
    {        
        return mgrUsageCalSetDowSetDAO.validDay(mgrUsageCalSetDowSetDTO, user);
    }

	@Override
	public MgrUsageCalSetDowSetDTO findDetail(MgrUsageCalSetDowSetDTO mgrUsageCalSetDowSetDTO, User user) throws Exception 
	{
		return (MgrUsageCalSetDowSetDTO)CommonUtil.makeDetailFromList(mgrUsageCalSetDowSetDAO.findDowList(mgrUsageCalSetDowSetDTO, user), mgrUsageCalSetDowSetDTO);
	}
	
	public int updateDetail(MgrUsageCalSetDowSetDTO mgrUsageCalSetDowSetDTO, User user) throws Exception
    {
		int[] result = null;
        
        List<MgrUsageCalSetDowSetDTO> list = new ArrayList<MgrUsageCalSetDowSetDTO>();
        list.add(mgrUsageCalSetDowSetDTO);
        
        result = mgrUsageCalSetDowSetDAO.updateDetail(list, user);
        
		mgrUsageCalSetDowSetDAO.deleteWrkTime(mgrUsageCalSetDowSetDTO, user);
		mgrUsageCalSetDowSetDAO.execRunTime(mgrUsageCalSetDowSetDTO, user);
        
        return result[0];
    }
	
	public int insertDetail(MgrUsageCalSetDowSetDTO mgrUsageCalSetDowSetDTO, User user) throws Exception
	{
		if(CommonUtil.isNullCheck(mgrUsageCalSetDowSetDTO.getEqLocDowRunId()))
			mgrUsageCalSetDowSetDTO.setEqLocDowRunId(mgrUsageCalSetDowSetDAO.getNextSequence("SQAEQLOCDOWRUN_ID"));
		
		List<MgrUsageCalSetDowSetDTO> list = new ArrayList<MgrUsageCalSetDowSetDTO>();
		list.add(mgrUsageCalSetDowSetDTO);
		
		int[] rtn =  this.insertDetail(list, user);
		
		mgrUsageCalSetDowSetDAO.deleteWrkTime(mgrUsageCalSetDowSetDTO, user);
		mgrUsageCalSetDowSetDAO.execRunTime(mgrUsageCalSetDowSetDTO, user);
		
		return rtn[0];
	}

	public int[] insertDetail(List<MgrUsageCalSetDowSetDTO> list, User user) throws Exception
    {
        return mgrUsageCalSetDowSetDAO.insertDetail(list, user);
    }
	
}

