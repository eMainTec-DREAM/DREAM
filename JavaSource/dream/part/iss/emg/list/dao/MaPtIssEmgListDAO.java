package dream.part.iss.emg.list.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.part.iss.emg.list.dto.MaPtIssEmgCommonDTO;

/**
 * 자재출고확정 - 목록 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaPtIssEmgListDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtIssEmgCommonDTO
     * @return List
     */
    public List findPtIssEmgList(MaPtIssEmgCommonDTO maPtIssEmgCommonDTO, User user);

    public int deletePtIssEmg(String ptemgisslist_id, User user);

    public String findTotalCount(MaPtIssEmgCommonDTO maPtIssEmgCommonDTO, User user);
}