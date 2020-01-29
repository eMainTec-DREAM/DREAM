package dream.work.service.service;

import java.util.List;

import common.bean.User;
import dream.mgr.contract.dto.MgrContractDTO;
import dream.work.service.dto.WorkServicePriceDTO;

/**
 * 서비스 설정  목록
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface WorkServicePriceService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrContractDTO
     * @param workServicePriceDTO
     * @param loginUser
     * @throws Exception
     */
    public List<MgrContractDTO> findList(WorkServicePriceDTO workServicePriceDTO, User loginUser);
    /**
     *  delete
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0f
     * 
     * @param deleteRows
     * @param deleteRowsExt
     * @throws Exception
     */
    public int[] deleteList(String[] deleteRows, User user) throws Exception;
    
    public String findTotalCount(WorkServicePriceDTO workServicePriceDTO, User user);

    public WorkServicePriceDTO findDetail(WorkServicePriceDTO workServicePriceDTO, User user)throws Exception;

    public int updateDetail(WorkServicePriceDTO workServicePriceDTO, User user) throws Exception;

    public int insertDetail(WorkServicePriceDTO workServicePriceDTO, User user) throws Exception;

    public int[] insertDetail(List<WorkServicePriceDTO> list, User user) throws Exception;

}
