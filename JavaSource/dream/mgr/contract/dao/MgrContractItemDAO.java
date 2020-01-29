package dream.mgr.contract.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.mgr.contract.dto.MgrContractItemDTO;

/**
 * 단가계약 설정 목록 dao
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface MgrContractItemDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrContractDTO
     * @param mgrContractItemDTO
     * @param loginUser
     * @return List
     */
    public List findList(MgrContractItemDTO mgrContractItemDTO, User loginUser);
    public String findTotalCount(MgrContractItemDTO mgrContractItemDTO, User user);
    public int[] updateDetail(final List<MgrContractItemDTO> list, User user) throws Exception;
    public int[] insertDetail(final List<MgrContractItemDTO> list, User user) throws Exception;
    public int[] deleteList(final List<MgrContractItemDTO> list, final User user) throws Exception;
    public String getColums(MgrContractItemDTO mgrContractItemDTO, User user);
    public String getTables(MgrContractItemDTO mgrContractItemDTO, User user);
    public String getOrderBy(MgrContractItemDTO mgrContractItemDTO, User user);
    public String getWhere(MgrContractItemDTO mgrContractItemDTO, User user);
}