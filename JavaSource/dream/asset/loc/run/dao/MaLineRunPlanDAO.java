package dream.asset.loc.run.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.asset.loc.run.dto.MaLineRunPlanDTO;

/**
 * 라인가동계획 - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface MaLineRunPlanDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param maLineRunPlanDTO
     * @return List
     */
    public List findList(MaLineRunPlanDTO maLineRunPlanDTO, User user);

    public String findTotalCount(MaLineRunPlanDTO maLineRunPlanDTO, User user);
    
    /**
     * 삭제
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param id
     * @return
     */
    public int[] deleteList(final List<MaLineRunPlanDTO> list, final User user) throws Exception;
    
    public int[] insertDetail(final List<MaLineRunPlanDTO> list, final User user) throws Exception;
    
    public int[] updateDetail(final List<MaLineRunPlanDTO> list, final User user) throws Exception;
    
    public String validLineRunPlan(MaLineRunPlanDTO maLineRunPlanDTO, User user);

    public String getColums(MaLineRunPlanDTO maLineRunPlanDTO, User user);
    
    public String getTables(MaLineRunPlanDTO maLineRunPlanDTO, User user);
    
    public String getOrderBy(MaLineRunPlanDTO maLineRunPlanDTO, User user);
    
    public String getWhere(MaLineRunPlanDTO maLineRunPlanDTO, User user);

}