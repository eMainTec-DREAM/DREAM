package dream.work.plan.service.service.spring;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import common.bean.User;
import common.util.CommonUtil;
import dream.work.plan.service.dao.WoPlanServiceDAO;
import dream.work.plan.service.dto.WoPlanServiceDTO;
import dream.work.plan.service.service.WoPlanServiceService;

/**
 * 서비스작업 ServiceImpl 
 * @author nhkim8548
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="woPlanServiceServiceTarget"
 * @spring.txbn id="woPlanServiceService"
 * @spring.property name="woPlanServiceDAO" ref="woPlanServiceDAO"
 */
public class WoPlanServiceServiceImpl implements WoPlanServiceService
{
    private WoPlanServiceDAO woPlanServiceDAO = null;

    public WoPlanServiceDAO getWoPlanServiceDAO() 
    {
        return woPlanServiceDAO;
    }

    public void setWoPlanServiceDAO(WoPlanServiceDAO woPlanServiceDAO) 
    {
        this.woPlanServiceDAO = woPlanServiceDAO;
    }

    public List findList(WoPlanServiceDTO woPlanServiceDTO, User user) throws Exception
    {      
        return woPlanServiceDAO.findList(woPlanServiceDTO, user);
    }
    
    public String findTotalCount(WoPlanServiceDTO woPlanServiceDTO, User user)
    {      
    	return woPlanServiceDAO.findTotalCount(woPlanServiceDTO, user);
    }

	public int[] deleteList(String[] deleteRows, User user) throws Exception
    {
        int[] result = null;
        WoPlanServiceDTO woPlanServiceDTO = new WoPlanServiceDTO();
        List<WoPlanServiceDTO> list = new ArrayList<WoPlanServiceDTO>();
        
        if(!deleteRows.equals(null)) {
        	for(int i=0; i<deleteRows.length; i++) {
        		woPlanServiceDTO.setWoPlanServiceId(deleteRows[i]);
        		
        		list.add((WoPlanServiceDTO) BeanUtils.cloneBean(woPlanServiceDTO));
        	}
        	result = woPlanServiceDAO.deleteList(list, user);
        }
        
        return result;
    }

	@Override
	public WoPlanServiceDTO findDetail(WoPlanServiceDTO woPlanServiceDTO, User user) throws Exception 
	{
		return (WoPlanServiceDTO)CommonUtil.makeDetailFromList(woPlanServiceDAO.findList(woPlanServiceDTO, user), woPlanServiceDTO);
	}
	
	@Override
    public int updateDetail(WoPlanServiceDTO woPlanServiceDTO, User user) throws Exception
    {
        int[] result = null;
        List<WoPlanServiceDTO> list = new ArrayList<WoPlanServiceDTO>();
        list.add(woPlanServiceDTO);
        
        result = woPlanServiceDAO.updateDetail(list, user);
        
        return result[0];
    }

	public int[] insertDetail(List<WoPlanServiceDTO> list, User user) throws Exception
    {
        return woPlanServiceDAO.insertDetail(list, user);
    }
	
	public int insertDetail(WoPlanServiceDTO woPlanServiceDTO, User user) throws Exception
    {
       	if(CommonUtil.isNullCheck(woPlanServiceDTO.getWoPlanServiceId()))
       		woPlanServiceDTO.setWoPlanServiceId(woPlanServiceDAO.getNextSequence("SQAWOPLANSERVICE_ID"));
        
        List<WoPlanServiceDTO> list = new ArrayList<WoPlanServiceDTO>();
        list.add((WoPlanServiceDTO) BeanUtils.cloneBean(woPlanServiceDTO));
        
        int[] rtn =  this.insertDetail(list, user);
        
        return rtn[0];
    }
}