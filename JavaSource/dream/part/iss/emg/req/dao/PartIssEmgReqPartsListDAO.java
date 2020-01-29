package dream.part.iss.emg.req.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.part.iss.emg.list.dto.MaPtIssEmgCommonDTO;
import dream.part.iss.emg.req.dto.PartIssEmgReqCommonDTO;

/**
 * 자재출고확정 - 목록 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface PartIssEmgReqPartsListDAO extends BaseJdbcDaoSupportIntf
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
    public List findPtIssEmgList(PartIssEmgReqCommonDTO partIssEmgReqCommonDTO,User user);

    public int deletePtIssEmg(String ptemgisslist_id,User user);

    public String findTotalCount(PartIssEmgReqCommonDTO partIssEmgReqCommonDTO,User user);
}