package dream.mgr.contract.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.mgr.contract.dto.MgrContractDTO;

/**
 * 단가계약설정 - 목록 
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface MgrContractDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrContractDTO
     * @return List
     */
    public List findList(MgrContractDTO mgrContractDTO,User user);

    public String findTotalCount(MgrContractDTO mgrContractDTO, User user);

    public int[] insertDetail(final List<MgrContractDTO> list, final User user) throws Exception;
    
    public int[] deleteList(final List<MgrContractDTO> list, final User user) throws Exception;

    public int[] updateDetail(final List<MgrContractDTO> list, final User user) throws Exception;

    public String getColums(MgrContractDTO mgrContractDTO, User user);
    
    public String getTables(MgrContractDTO mgrContractDTO, User user);
    
    public String getOrderBy(MgrContractDTO mgrContractDTO, User user);
    
    public String getWhere(MgrContractDTO mgrContractDTO, User user);
}