package dream.work.pm.list.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;

/**
 * WorkPmListDInsPoint Page - List DAO
 * @author youngjoo38
 * @version $Id: WorkPmListDInsPointListDAO.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public interface WorkPmListDInsPointListDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * FIND LIST
     * @param workPmListDInsPointListDTO
     * @param user
     * @return
     * @throws Exception
     */

    
    public List findList(MaPmMstrCommonDTO maPmMstrCommonDTO, User user) throws Exception;
    
    /**
     * DELETE LIST
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteList(String id, User user) throws Exception;
    /**
     * FIND TOTAL LIST
     * @param workPmListDInsPointListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(MaPmMstrCommonDTO maPmMstrCommonDTO, User user) throws Exception;
    
}
