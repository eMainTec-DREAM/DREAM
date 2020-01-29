package dream.doc.manual.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.doc.manual.dto.DocManualCommonDTO;

/**
 * 사용자매뉴얼 - 목록 dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface DocManualListDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param docManualCommonDTO
     * @return List
     */
    public List findHelpList(DocManualCommonDTO docManualCommonDTO, User user);
    
    /**
     * delete
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param docManualDTOList
     * @return
     */
    public int deleteHelp(String id, User user);

    public String findTotalCount(DocManualCommonDTO docManualCommonDTO, User user) throws Exception;
}