package dream.work.plan.service.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.work.plan.service.dto.WoPlanServiceDTO;

/**
 * 서비스작업 DAO 
 * @author  nhkim8548
 * @version $Id:$
 * @since   1.0
 */
public interface WoPlanServiceDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  nhkim8548
     * @version $Id:$
     * @since   1.0
     * 
     * @param woPlanServiceDTO
     * @return List
     */
    public List findList(WoPlanServiceDTO woPlanServiceDTO, User user) throws Exception;
    
    public String findTotalCount(WoPlanServiceDTO woPlanServiceDTO, User user);
    
    public int[] insertDetail(final List<WoPlanServiceDTO> list, final User user) throws Exception;
    
    public int[] deleteList(final List<WoPlanServiceDTO> list, final User user) throws Exception;

    public int[] updateDetail(final List<WoPlanServiceDTO> list, final User user) throws Exception;

    public String getColums(WoPlanServiceDTO woPlanServiceDTO, User user);
    
    public String getTables(WoPlanServiceDTO woPlanServiceDTO, User user);
    
    public String getOrderBy(WoPlanServiceDTO woPlanServiceDTO, User user);
    
    public String getWhere(WoPlanServiceDTO woPlanServiceDTO, User user);
}