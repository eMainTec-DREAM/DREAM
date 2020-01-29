package dream.work.service.service.spring;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import common.bean.User;
import common.util.CommonUtil;
import common.util.DateUtil;
import dream.work.service.dao.WorkServicePriceDAO;
import dream.work.service.dto.WorkServicePriceDTO;
import dream.work.service.service.WorkServicePriceService;

/**
 * 서비스 설정  목록
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="workServicePriceServiceTarget"
 * @spring.txbn id="workServicePriceService"
 * @spring.property name="workServicePriceDAO" ref="workServicePriceDAO"
 */
public class WorkServicePriceServiceImpl implements WorkServicePriceService
{
    private WorkServicePriceDAO workServicePriceDAO = null;


	public List findList(WorkServicePriceDTO workServicePriceDTO, User loginUser)
    {      
        return workServicePriceDAO.findList(workServicePriceDTO, loginUser);
    }

    public String findTotalCount(WorkServicePriceDTO workServicePriceDTO, User user)
    {      
    	return workServicePriceDAO.findTotalCount(workServicePriceDTO, user);
    }
    
	
	public WorkServicePriceDAO getWorkServicePriceDAO() {
		return workServicePriceDAO;
	}

	public void setWorkServicePriceDAO(WorkServicePriceDAO workServicePriceDAO) {
		this.workServicePriceDAO = workServicePriceDAO;
	}
	
	public int[] deleteList(String[] deleteRows, User user) throws Exception{
		int[] result = null;
        WorkServicePriceDTO workServicePriceDTO = new WorkServicePriceDTO();
        List<WorkServicePriceDTO> list = new ArrayList<WorkServicePriceDTO>();
        
        if(!deleteRows.equals(null)) {
        	for(int i=0; i<deleteRows.length; i++) {
        		workServicePriceDTO.setServicePriceId(deleteRows[i]);
        		
        		list.add((WorkServicePriceDTO)BeanUtils.cloneBean(workServicePriceDTO));
        	}
        	result = workServicePriceDAO.deleteList(list, user);
        }
        
        return result;
    }
	
	@Override
	public WorkServicePriceDTO findDetail(WorkServicePriceDTO workServicePriceDTO, User user) throws Exception 
	{
		return (WorkServicePriceDTO)CommonUtil.makeDetailFromList(workServicePriceDAO.findList(workServicePriceDTO, user), workServicePriceDTO);
	}
	
	public int updateDetail(WorkServicePriceDTO workServicePriceDTO, User user) throws Exception
    {
		int[] result = null;
        
        List<WorkServicePriceDTO> list = new ArrayList<WorkServicePriceDTO>();
        list.add(workServicePriceDTO);
        
        workServicePriceDTO.setUpdTime(DateUtil.getDateTime());
        
        result = workServicePriceDAO.updateDetail(list, user);
        
        return result[0];
    }
	
	public int insertDetail(WorkServicePriceDTO workServicePriceDTO, User user) throws Exception
	{
		if(CommonUtil.isNullCheck(workServicePriceDTO.getServicePriceId()))
			workServicePriceDTO.setServicePriceId(workServicePriceDAO.getNextSequence("SQASERVICEPRICE_ID"));
		
		List<WorkServicePriceDTO> list = new ArrayList<WorkServicePriceDTO>();
		list.add(workServicePriceDTO);
		
		workServicePriceDTO.setCreTime(DateUtil.getDateTime());
		workServicePriceDTO.setUpdTime(DateUtil.getDateTime());

		int[] rtn =  this.insertDetail(list, user);
		
		return rtn[0];
	}

	public int[] insertDetail(List<WorkServicePriceDTO> list, User user) throws Exception
    {
        return workServicePriceDAO.insertDetail(list, user);
    }
	
}

