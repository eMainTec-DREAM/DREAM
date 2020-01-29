package dream.work.pm.list.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.WorkPmListMsTimeListDTO;

/**
 * 작업시간  dao
 * @author  js.lee
 * @version $Id: Exp $
 * @since   1.0
 */
public interface WorkPmListMsTimeListDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: Exp $
     * @since   1.0
     * 
     * @param maPmMstrCommonDTO
     * @param loginUser
     * @return List
     */
    public List findMsTimeList(MaPmMstrCommonDTO maPmMstrCommonDTO, WorkPmListMsTimeListDTO workPmListMsTimeListDTO, User loginUser);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteMsTimeList(String id, User loginUser);
    
    public String findTotalCount(MaPmMstrCommonDTO maPmMstrCommonDTO, WorkPmListMsTimeListDTO workPmListMsTimeListDTO,User user) throws Exception;

}