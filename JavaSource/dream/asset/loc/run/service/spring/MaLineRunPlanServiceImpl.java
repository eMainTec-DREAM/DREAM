package dream.asset.loc.run.service.spring;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import common.bean.User;
import common.util.CommonUtil;
import dream.asset.loc.run.dao.MaLineRunPlanDAO;
import dream.asset.loc.run.dto.MaLineRunPlanDTO;
import dream.asset.loc.run.service.MaLineRunPlanService;

/**
 * 라인가동계획 - 목록 serviceimpl
 * @author kim21017
 * @version
 * @since 1.0
 * 
 * @spring.bean id="maLineRunPlanServiceTarget"
 * @spring.txbn id="maLineRunPlanService"
 * @spring.property name="maLineRunPlanDAO" ref="maLineRunPlanDAO"
 */
public class MaLineRunPlanServiceImpl implements MaLineRunPlanService
{
    private MaLineRunPlanDAO maLineRunPlanDAO = null;

    public MaLineRunPlanDAO getMaLineRunPlanDAO() 
    {
		return maLineRunPlanDAO;
	}

	public void setMaLineRunPlanDAO(MaLineRunPlanDAO maLineRunPlanDAO) 
	{
		this.maLineRunPlanDAO = maLineRunPlanDAO;
	}

	public List findList(MaLineRunPlanDTO maLineRunPlanDTO, User user)
    {      
        return maLineRunPlanDAO.findList(maLineRunPlanDTO, user);
    }

	@Override
	public String findTotalCount(MaLineRunPlanDTO maLineRunPlanDTO, User user)
	{
		return maLineRunPlanDAO.findTotalCount(maLineRunPlanDTO, user);
	}
	
	public int[] deleteList(String[] deleteRows, User user) throws Exception
	{
		int[] result = null;
        MaLineRunPlanDTO maLineRunPlanDTO = new MaLineRunPlanDTO();
        List<MaLineRunPlanDTO> list = new ArrayList<MaLineRunPlanDTO>();
        
        if(!deleteRows.equals(null)) {
        	for(int i=0; i<deleteRows.length; i++) {
        		maLineRunPlanDTO.setLnWrkTimeId(deleteRows[i]);
        		
        		list.add((MaLineRunPlanDTO) BeanUtils.cloneBean(maLineRunPlanDTO));
        	}
        	result = maLineRunPlanDAO.deleteList(list, user);
        }
        
        return result;
    }

	public MaLineRunPlanDTO findDetail(MaLineRunPlanDTO maLineRunPlanDTO, User user)throws Exception
    {
        return (MaLineRunPlanDTO)CommonUtil.makeDetailFromList(maLineRunPlanDAO.findList(maLineRunPlanDTO, user),maLineRunPlanDTO);
    }
    
	public int insertDetail(MaLineRunPlanDTO maLineRunPlanDTO, User user) throws Exception
    {        
		if(CommonUtil.isNullCheck(maLineRunPlanDTO.getLnWrkTimeId()))
			maLineRunPlanDTO.setLnWrkTimeId(maLineRunPlanDAO.getNextSequence("SQALNWRKTIME_ID"));
		
		List<MaLineRunPlanDTO> list = new ArrayList<MaLineRunPlanDTO>();
		list.add((MaLineRunPlanDTO) BeanUtils.cloneBean(maLineRunPlanDTO));
		
		int[] rtn =  this.insertDetail(list, user);
		
		return rtn[0];
    }
	
	public int[] insertDetail(List<MaLineRunPlanDTO> list, User user) throws Exception
	{        
        return maLineRunPlanDAO.insertDetail(list, user);
	}
	
	public int updateDetail(MaLineRunPlanDTO maLineRunPlanDTO, User user) throws Exception
    {        
		int[] result = null;
        
        List<MaLineRunPlanDTO> list = new ArrayList<MaLineRunPlanDTO>();
        list.add(maLineRunPlanDTO);
        
        result = maLineRunPlanDAO.updateDetail(list, user);
        
        return result[0];
    }
	public String validLineRunPlan(MaLineRunPlanDTO maLineRunPlanDTO, User user) throws Exception
    {
        return maLineRunPlanDAO.validLineRunPlan(maLineRunPlanDTO, user);
    }
}

