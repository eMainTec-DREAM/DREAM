package dream.mgr.usage.cal.service.spring;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import common.bean.User;
import common.util.CommonUtil;
import dream.mgr.usage.cal.dao.MgrUsageCalSetDayDAO;
import dream.mgr.usage.cal.dto.MgrUsageCalSetDayDTO;
import dream.mgr.usage.cal.service.MgrUsageCalSetDayService;

/**
 * 사용달력일별횟수설정 - 목록 serviceimpl
 * @author youngjoo38
 * @version
 * @since 1.0
 * 
 * @spring.bean id="mgrUsageCalSetDayServiceTarget"
 * @spring.txbn id="mgrUsageCalSetDayService"
 * @spring.property name="mgrUsageCalSetDayDAO" ref="mgrUsageCalSetDayDAO"
 */
public class MgrUsageCalSetDayServiceImpl implements MgrUsageCalSetDayService
{
    private MgrUsageCalSetDayDAO mgrUsageCalSetDayDAO = null;

    public MgrUsageCalSetDayDAO getMgrUsageCalSetDayDAO() 
    {
		return mgrUsageCalSetDayDAO;
	}

	public void setMgrUsageCalSetDayDAO(MgrUsageCalSetDayDAO mgrUsageCalSetDayDAO) 
	{
		this.mgrUsageCalSetDayDAO = mgrUsageCalSetDayDAO;
	}

	public List findList(MgrUsageCalSetDayDTO mgrUsageCalSetDayDTO, User user)
    {      
        return mgrUsageCalSetDayDAO.findList(mgrUsageCalSetDayDTO, user);
    }

	@Override
	public String findTotalCount(MgrUsageCalSetDayDTO mgrUsageCalSetDayDTO, User user)
	{
		return mgrUsageCalSetDayDAO.findTotalCount(mgrUsageCalSetDayDTO, user);
	}
	
	public int[] deleteList(String[] deleteRows, User user) throws Exception
	{
		int[] result = null;
        MgrUsageCalSetDayDTO mgrUsageCalSetDayDTO = new MgrUsageCalSetDayDTO();
        List<MgrUsageCalSetDayDTO> list = new ArrayList<MgrUsageCalSetDayDTO>();
        
        if(!deleteRows.equals(null)) {
        	for(int i=0; i<deleteRows.length; i++) {
        		mgrUsageCalSetDayDTO.setLnWrkTimeId(deleteRows[i]);
        		
        		list.add((MgrUsageCalSetDayDTO) BeanUtils.cloneBean(mgrUsageCalSetDayDTO));
        	}
        	result = mgrUsageCalSetDayDAO.deleteList(list, user);
        }
        
        return result;
    }

	public MgrUsageCalSetDayDTO findDetail(MgrUsageCalSetDayDTO mgrUsageCalSetDayDTO, User user)throws Exception
    {
        return (MgrUsageCalSetDayDTO)CommonUtil.makeDetailFromList(mgrUsageCalSetDayDAO.findList(mgrUsageCalSetDayDTO, user),mgrUsageCalSetDayDTO);
    }
    
	public int insertDetail(MgrUsageCalSetDayDTO mgrUsageCalSetDayDTO, User user) throws Exception
    {        
		if(CommonUtil.isNullCheck(mgrUsageCalSetDayDTO.getLnWrkTimeId()))
			mgrUsageCalSetDayDTO.setLnWrkTimeId(mgrUsageCalSetDayDAO.getNextSequence("SQALNWRKTIME_ID"));
		
		List<MgrUsageCalSetDayDTO> list = new ArrayList<MgrUsageCalSetDayDTO>();
		list.add((MgrUsageCalSetDayDTO) BeanUtils.cloneBean(mgrUsageCalSetDayDTO));
		
		int[] rtn =  this.insertDetail(list, user);
		
		return rtn[0];
    }
	
	public int[] insertDetail(List<MgrUsageCalSetDayDTO> list, User user) throws Exception
	{        
        return mgrUsageCalSetDayDAO.insertDetail(list, user);
	}
	
	public int updateDetail(MgrUsageCalSetDayDTO mgrUsageCalSetDayDTO, User user) throws Exception
    {        
		int[] result = null;
        
        List<MgrUsageCalSetDayDTO> list = new ArrayList<MgrUsageCalSetDayDTO>();
        list.add(mgrUsageCalSetDayDTO);
        
        result = mgrUsageCalSetDayDAO.updateDetail(list, user);
        
        return result[0];
    }
	public String validLineRunPlan(MgrUsageCalSetDayDTO mgrUsageCalSetDayDTO, User user) throws Exception
    {
        return mgrUsageCalSetDayDAO.validLineRunPlan(mgrUsageCalSetDayDTO, user);
    }
}

