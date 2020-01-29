package dream.work.service.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.work.service.dto.WorkServicePriceDTO;

/**
 * 서비스 설정 목록 dao
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface WorkServicePriceDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrContractDTO
     * @param workServicePriceDTO
     * @param loginUser
     * @return List
     */
    public List findList(WorkServicePriceDTO workServicePriceDTO, User loginUser);
    public String findTotalCount(WorkServicePriceDTO workServicePriceDTO, User user);
    public int[] updateDetail(final List<WorkServicePriceDTO> list, User user) throws Exception;
    public int[] insertDetail(final List<WorkServicePriceDTO> list, User user) throws Exception;
    public int[] deleteList(final List<WorkServicePriceDTO> list, final User user) throws Exception;
    public String getColums(WorkServicePriceDTO workServicePriceDTO, User user);
    public String getTables(WorkServicePriceDTO workServicePriceDTO, User user);
    public String getOrderBy(WorkServicePriceDTO workServicePriceDTO, User user);
    public String getWhere(WorkServicePriceDTO workServicePriceDTO, User user);
}