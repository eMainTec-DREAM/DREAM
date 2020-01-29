package dream.mgr.cal.service.spring;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import common.bean.User;
import common.util.CommonUtil;
import dream.mgr.cal.dao.MgrCalLineTimeDowSetDAO;
import dream.mgr.cal.dto.MgrCalLineTimeDowSetDTO;
import dream.mgr.cal.dto.MgrCalLineTimeSetDTO;
import dream.mgr.cal.service.MgrCalLineTimeDowSetService;

/**
 * 요일별 설정  목록
 * @author euna0207
 * @version $Id: MgrCalLineTimeDowSetServiceImpl.java,v 1.0 2015/12/02 09:12:51 euna0207 Exp $
 * @since 1.0
 * 
 * @spring.bean id="mgrCalLineTimeDowSetServiceTarget"
 * @spring.txbn id="mgrCalLineTimeDowSetService"
 * @spring.property name="mgrCalLineTimeDowSetDAO" ref="mgrCalLineTimeDowSetDAO"
 */
public class MgrCalLineTimeDowSetServiceImpl implements MgrCalLineTimeDowSetService
{
    private MgrCalLineTimeDowSetDAO mgrCalLineTimeDowSetDAO = null;


	public List findDowList(MgrCalLineTimeDowSetDTO mgrCalLineTimeDowSetDTO, User loginUser)
    {      
//        return mgrCalLineTimeDowSetDAO.findDowList(mgrCalLineTimeSetDTO, mgrCalLineTimeDowSetDTO, loginUser);
        return mgrCalLineTimeDowSetDAO.findDowList(mgrCalLineTimeDowSetDTO, loginUser);
    }

    public String findTotalCount(MgrCalLineTimeDowSetDTO mgrCalLineTimeDowSetDTO, User user)
    {      
    	return mgrCalLineTimeDowSetDAO.findTotalCount(mgrCalLineTimeDowSetDTO, user);
    }
    
	
	public MgrCalLineTimeDowSetDAO getMgrCalLineTimeDowSetDAO() {
		return mgrCalLineTimeDowSetDAO;
	}

	public void setMgrCalLineTimeDowSetDAO(MgrCalLineTimeDowSetDAO mgrCalLineTimeDowSetDAO) {
		this.mgrCalLineTimeDowSetDAO = mgrCalLineTimeDowSetDAO;
	}
	
	public int[] deleteDowList(String[] deleteRows, User user) throws Exception{
		int[] result = null;
        MgrCalLineTimeDowSetDTO mgrCalLineTimeDowSetDTO = new MgrCalLineTimeDowSetDTO();
        List<MgrCalLineTimeDowSetDTO> list = new ArrayList<MgrCalLineTimeDowSetDTO>();
        
        if(!deleteRows.equals(null)) {
        	for(int i=0; i<deleteRows.length; i++) {
        		mgrCalLineTimeDowSetDTO.setEqLocDowRunId(deleteRows[i]);
        		
        		list.add((MgrCalLineTimeDowSetDTO)BeanUtils.cloneBean(mgrCalLineTimeDowSetDTO));
        	}
        	result = mgrCalLineTimeDowSetDAO.deleteDowList(list, user);
        }
        
        return result;
    }
	
	public String validDay(MgrCalLineTimeSetDTO mgrCalLineTimeSetDTO, MgrCalLineTimeDowSetDTO mgrCalLineTimeDowSetDTO, User user) throws Exception
    {        
        return mgrCalLineTimeDowSetDAO.validDay(mgrCalLineTimeDowSetDTO, user);
    }

	@Override
	public MgrCalLineTimeDowSetDTO findDetail(MgrCalLineTimeDowSetDTO mgrCalLineTimeDowSetDTO, User user) throws Exception 
	{
		return (MgrCalLineTimeDowSetDTO)CommonUtil.makeDetailFromList(mgrCalLineTimeDowSetDAO.findDowList(mgrCalLineTimeDowSetDTO, user), mgrCalLineTimeDowSetDTO);
	}
	
	public int updateDetail(MgrCalLineTimeDowSetDTO mgrCalLineTimeDowSetDTO, User user) throws Exception
    {
		int[] result = null;
        
        List<MgrCalLineTimeDowSetDTO> list = new ArrayList<MgrCalLineTimeDowSetDTO>();
        list.add(mgrCalLineTimeDowSetDTO);
        
        result = mgrCalLineTimeDowSetDAO.updateDetail(list, user);
        
		mgrCalLineTimeDowSetDAO.deleteWrkTime(mgrCalLineTimeDowSetDTO, user);
		mgrCalLineTimeDowSetDAO.execRunTime(mgrCalLineTimeDowSetDTO, user);
        
        return result[0];
    }
	
	public int insertDetail(MgrCalLineTimeDowSetDTO mgrCalLineTimeDowSetDTO, User user) throws Exception
	{
		if(CommonUtil.isNullCheck(mgrCalLineTimeDowSetDTO.getEqLocDowRunId()))
			mgrCalLineTimeDowSetDTO.setEqLocDowRunId(mgrCalLineTimeDowSetDAO.getNextSequence("SQAEQLOCDOWRUN_ID"));
		
		List<MgrCalLineTimeDowSetDTO> list = new ArrayList<MgrCalLineTimeDowSetDTO>();
		list.add(mgrCalLineTimeDowSetDTO);
		
		int[] rtn =  this.insertDetail(list, user);
		
		mgrCalLineTimeDowSetDAO.deleteWrkTime(mgrCalLineTimeDowSetDTO, user);
		mgrCalLineTimeDowSetDAO.execRunTime(mgrCalLineTimeDowSetDTO, user);
		
		return rtn[0];
	}

	public int[] insertDetail(List<MgrCalLineTimeDowSetDTO> list, User user) throws Exception
    {
        return mgrCalLineTimeDowSetDAO.insertDetail(list, user);
    }
	
}

