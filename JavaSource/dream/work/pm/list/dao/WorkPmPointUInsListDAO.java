package dream.work.pm.list.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;

/**
 * 사용량 항목 - List DAO
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 *
 */
public interface WorkPmPointUInsListDAO extends BaseJdbcDaoSupportIntf
{
	/**
	 * FIND LIST
	 * @param maPmMstrCommonDTO
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
	 * FIND LIST COUNT
	 * @param maPmMstrCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(MaPmMstrCommonDTO maPmMstrCommonDTO, User user) throws Exception;
    
}