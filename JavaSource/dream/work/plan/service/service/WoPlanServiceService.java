package dream.work.plan.service.service;

import java.util.List;

import common.bean.User;
import dream.work.plan.service.dto.WoPlanServiceDTO;

/**
 * 서비스작업 Service 
 * @author  nhkim8548
 * @version $Id:$
 * @since   1.0
 */
public interface WoPlanServiceService
{     
    public List<WoPlanServiceDTO> findList(WoPlanServiceDTO woPlanServiceDTO, User user) throws Exception;
    
    public int[] deleteList(String[] deleteRows, User user) throws Exception;

    public String findTotalCount(WoPlanServiceDTO woPlanServiceDTO, User user);
    
    public WoPlanServiceDTO findDetail(WoPlanServiceDTO woPlanServiceDTO, User user) throws Exception;
    
    public int updateDetail(WoPlanServiceDTO woPlanServiceDTO, User user) throws Exception;
    
    public int insertDetail(WoPlanServiceDTO woPlanServiceDTO, User user) throws Exception;
    
    public int[] insertDetail(List<WoPlanServiceDTO> list, User user) throws Exception;
}
