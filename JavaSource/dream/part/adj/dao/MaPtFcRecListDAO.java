package dream.part.adj.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.part.adj.dto.MaPtFcRecCommonDTO;

/**
 * 무상입고 - 목록 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaPtFcRecListDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtFcRecCommonDTO
     * @return List
     */
    public List findList(MaPtFcRecCommonDTO maPtFcRecCommonDTO, User user);
    
    /**
     * delete
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteList(String compNo, String fcRecListId);

	public String findTotalCount(MaPtFcRecCommonDTO maPtFcRecCommonDTO, User user);
}