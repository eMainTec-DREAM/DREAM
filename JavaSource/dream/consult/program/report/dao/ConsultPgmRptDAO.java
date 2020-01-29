package dream.consult.program.report.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.consult.program.report.dto.ConsultPgmRptDTO;

/**
 * Report List - ¸ñ·Ï dao 
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface ConsultPgmRptDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param consultPgmRptDTO
     * @return List
     */
    public List findList(ConsultPgmRptDTO consultPgmRptDTO, User user) throws Exception;
    
    public String findTotalCount(ConsultPgmRptDTO consultPgmRptDTO, User user);
    
    public int[] insertDetail(final List<ConsultPgmRptDTO> list, final User user) throws Exception;
    
    public int[] deleteList(final List<ConsultPgmRptDTO> list, final User user) throws Exception;

    public int[] updateDetail(final List<ConsultPgmRptDTO> list, final User user) throws Exception;

    public String getColums(ConsultPgmRptDTO consultPgmRptDTO, User user);
    
    public String getTables(ConsultPgmRptDTO consultPgmRptDTO, User user);
    
    public String getOrderBy(ConsultPgmRptDTO consultPgmRptDTO, User user);
    
    public String getWhere(ConsultPgmRptDTO consultPgmRptDTO, User user);
    
    public String checkDetail(ConsultPgmRptDTO consultPgmRptDTO, User user);
}