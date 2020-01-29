package dream.mgr.usage.cal.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.mgr.usage.cal.dto.MgrUsageCalSetDayDTO;

/**
 * 사용달력일별횟수설정 - 목록 dao
 * @author  youngjoo38
 * @version $Id: $
 * @since   1.0
 */
public interface MgrUsageCalSetDayDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param mgrUsageCalSetDayDTO
     * @return List
     */
    public List findList(MgrUsageCalSetDayDTO mgrUsageCalSetDayDTO, User user);

    public String findTotalCount(MgrUsageCalSetDayDTO mgrUsageCalSetDayDTO, User user);
    
    /**
     * 삭제
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param id
     * @return
     */
    public int[] deleteList(final List<MgrUsageCalSetDayDTO> list, final User user) throws Exception;
    
    public int[] insertDetail(final List<MgrUsageCalSetDayDTO> list, final User user) throws Exception;
    
    public int[] updateDetail(final List<MgrUsageCalSetDayDTO> list, final User user) throws Exception;
    
    public String validLineRunPlan(MgrUsageCalSetDayDTO mgrUsageCalSetDayDTO, User user);

    public String getColums(MgrUsageCalSetDayDTO mgrUsageCalSetDayDTO, User user);
    
    public String getTables(MgrUsageCalSetDayDTO mgrUsageCalSetDayDTO, User user);
    
    public String getOrderBy(MgrUsageCalSetDayDTO mgrUsageCalSetDayDTO, User user);
    
    public String getWhere(MgrUsageCalSetDayDTO mgrUsageCalSetDayDTO, User user);

}