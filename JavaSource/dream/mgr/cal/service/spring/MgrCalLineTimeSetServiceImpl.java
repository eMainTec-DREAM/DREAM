package dream.mgr.cal.service.spring;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import common.bean.User;
import common.util.CommonUtil;
import dream.mgr.cal.dao.MgrCalLineTimeSetDAO;
import dream.mgr.cal.dto.MgrCalLineTimeSetDTO;
import dream.mgr.cal.service.MgrCalLineTimeSetService;

/**
 * 가동달력설정 - 목록 serviceimpl
 * @author euna0207
 * @version $Id: MgrCalLineTimeSetServiceImpl.java,v 1.0 2015/12/02 09:12:51 euna0207 Exp $
 * @since 1.0
 * 
 * @spring.bean id="mgrCalLineTimeSetServiceTarget"
 * @spring.txbn id="mgrCalLineTimeSetService"
 * @spring.property name="mgrCalLineTimeSetDAO" ref="mgrCalLineTimeSetDAO"
 */
public class MgrCalLineTimeSetServiceImpl implements MgrCalLineTimeSetService
{
    private MgrCalLineTimeSetDAO mgrCalLineTimeSetDAO = null;

    public MgrCalLineTimeSetDAO getMgrCalLineTimeSetDAO() {
		return mgrCalLineTimeSetDAO;
	}

	public void setMgrCalLineTimeSetDAO(MgrCalLineTimeSetDAO mgrCalLineTimeSetDAO) {
		this.mgrCalLineTimeSetDAO = mgrCalLineTimeSetDAO;
	}

	public List findList(MgrCalLineTimeSetDTO mgrCalLineTimeSetDTO, User user)
    {      
        return mgrCalLineTimeSetDAO.findList(mgrCalLineTimeSetDTO, user);
    }
	
    public String findTotalCount(MgrCalLineTimeSetDTO mgrCalLineTimeSetDTO, User user)
    {
        return mgrCalLineTimeSetDAO.findTotalCount(mgrCalLineTimeSetDTO, user);
    }
    
    public int[] deleteList(String[] deleteRows, User user) throws Exception{
    	int[] result = null;
        MgrCalLineTimeSetDTO mgrCalLineTimeSetDTO = new MgrCalLineTimeSetDTO();
        List<MgrCalLineTimeSetDTO> list = new ArrayList<MgrCalLineTimeSetDTO>();
        
        if(!deleteRows.equals(null)) {
        	for(int i=0; i<deleteRows.length; i++) {
        		mgrCalLineTimeSetDTO.setLnWrkListId(deleteRows[i]);
        		
        		list.add((MgrCalLineTimeSetDTO) BeanUtils.cloneBean(mgrCalLineTimeSetDTO));
        	}
        	result = mgrCalLineTimeSetDAO.deleteList(list, user);
        }
        
        return result;
    }
    
	public MgrCalLineTimeSetDTO findDetail(MgrCalLineTimeSetDTO mgrCalLineTimeSetDTO,User user)throws Exception
    {
        return (MgrCalLineTimeSetDTO)CommonUtil.makeDetailFromList(mgrCalLineTimeSetDAO.findList(mgrCalLineTimeSetDTO,user), mgrCalLineTimeSetDTO);
    }
	
	public int[] insertDetail(List<MgrCalLineTimeSetDTO> list, User user) throws Exception
	{
	    return mgrCalLineTimeSetDAO.insertDetail(list,user);
	}
	
	public int insertDetail(MgrCalLineTimeSetDTO mgrCalLineTimeSetDTO, User user) throws Exception
	{
		if(CommonUtil.isNullCheck(mgrCalLineTimeSetDTO.getLnWrkListId()))
			mgrCalLineTimeSetDTO.setLnWrkListId(mgrCalLineTimeSetDAO.getNextSequence("SQALNWRKLIST_ID"));
		
		List<MgrCalLineTimeSetDTO> list = new ArrayList<MgrCalLineTimeSetDTO>();
		list.add((MgrCalLineTimeSetDTO) BeanUtils.cloneBean(mgrCalLineTimeSetDTO));
		
		int[] rtn =  this.insertDetail(list, user);
		
		return rtn[0];
	}
	
	public int updateDetail(MgrCalLineTimeSetDTO mgrCalLineTimeSetDTO, User user) throws Exception
    {        
		int[] result = null;
        
        List<MgrCalLineTimeSetDTO> list = new ArrayList<MgrCalLineTimeSetDTO>();
        list.add(mgrCalLineTimeSetDTO);
        
        result = mgrCalLineTimeSetDAO.updateDetail(list, user);
        
        return result[0];
    }
}

