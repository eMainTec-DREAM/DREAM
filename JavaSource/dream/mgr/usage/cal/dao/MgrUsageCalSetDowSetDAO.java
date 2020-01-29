package dream.mgr.usage.cal.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.mgr.usage.cal.dto.MgrUsageCalSetDowSetDTO;

/**
 * 요일별 설정 목록 dao
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface MgrUsageCalSetDowSetDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrUsageCalSetDTO
     * @param mgrUsageCalSetDowSetDTO
     * @param loginUser
     * @return List
     */
    public List findDowList(MgrUsageCalSetDowSetDTO mgrUsageCalSetDowSetDTO, User loginUser);

    public String findTotalCount(MgrUsageCalSetDowSetDTO mgrUsageCalSetDowSetDTO, User user);

    public int[] updateDetail(final List<MgrUsageCalSetDowSetDTO> list, User user) throws Exception;
    
    public int[] insertDetail(final List<MgrUsageCalSetDowSetDTO> list, User user) throws Exception;
    
    public int[] deleteDowList(final List<MgrUsageCalSetDowSetDTO> list, final User user) throws Exception;

    public String getColums(MgrUsageCalSetDowSetDTO mgrUsageCalSetDowSetDTO, User user);
    
    public String getTables(MgrUsageCalSetDowSetDTO mgrUsageCalSetDowSetDTO, User user);
    
    public String getOrderBy(MgrUsageCalSetDowSetDTO mgrUsageCalSetDowSetDTO, User user);
    
    public String getWhere(MgrUsageCalSetDowSetDTO mgrUsageCalSetDowSetDTO, User user);
    /**
     * 요일 중복 검사
     */
    public String validDay(MgrUsageCalSetDowSetDTO mgrUsageCalSetDowSetDTO, User user);
    public int deleteWrkTime(MgrUsageCalSetDowSetDTO mgrUsageCalSetDowSetDTO, User user);
    public int execRunTime(MgrUsageCalSetDowSetDTO mgrUsageCalSetDowSetDTO, User user);
}