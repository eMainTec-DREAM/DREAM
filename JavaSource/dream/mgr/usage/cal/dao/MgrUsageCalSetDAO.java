package dream.mgr.usage.cal.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.mgr.usage.cal.dto.MgrUsageCalSetDTO;

/**
 * 가동시간설정 - 목록 
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface MgrUsageCalSetDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrUsageCalSetDTO
     * @return List
     */
    public List findList(MgrUsageCalSetDTO mgrUsageCalSetDTO,User user);

    public String findTotalCount(MgrUsageCalSetDTO mgrUsageCalSetDTO, User user);

    public int[] insertDetail(final List<MgrUsageCalSetDTO> list, final User user) throws Exception;
    
    public int[] deleteList(final List<MgrUsageCalSetDTO> list, final User user) throws Exception;

    public int[] updateDetail(final List<MgrUsageCalSetDTO> list, final User user) throws Exception;

    public String getColums(MgrUsageCalSetDTO mgrUsageCalSetDTO, User user);
    
    public String getTables(MgrUsageCalSetDTO mgrUsageCalSetDTO, User user);
    
    public String getOrderBy(MgrUsageCalSetDTO mgrUsageCalSetDTO, User user);
    
    public String getWhere(MgrUsageCalSetDTO mgrUsageCalSetDTO, User user);
}