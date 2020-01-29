package dream.work.pm.list.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.WorkPmMsTimeUInsListDTO;

/**
 * �۾��ð�  dao
 * @author  js.lee
 * @version $Id: Exp $
 * @since   1.0
 */
public interface WorkPmMsTimeUInsListDAO extends BaseJdbcDaoSupportIntf
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
    public List findMsTimeList(MaPmMstrCommonDTO maPmMstrCommonDTO, WorkPmMsTimeUInsListDTO workPmMsTimeUInsListDTO, User loginUser);
    
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
    
    public String findTotalCount(MaPmMstrCommonDTO maPmMstrCommonDTO, WorkPmMsTimeUInsListDTO workPmMsTimeUInsListDTO,User user) throws Exception;

}