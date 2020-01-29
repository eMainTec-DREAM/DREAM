package dream.mgr.contract.service;

import java.util.List;

import common.bean.User;
import dream.mgr.contract.dto.MgrContractDTO;
import dream.mgr.contract.dto.MgrContractItemDTO;

/**
 * 단가계약 설정  목록
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface MgrContractItemService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrContractDTO
     * @param mgrContractItemDTO
     * @param loginUser
     * @throws Exception
     */
    public List<MgrContractDTO> findList(MgrContractItemDTO mgrContractItemDTO, User loginUser);
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
    
    public String findTotalCount(MgrContractItemDTO mgrContractItemDTO, User user);

    public MgrContractItemDTO findDetail(MgrContractItemDTO mgrContractItemDTO, User user)throws Exception;

    public int updateDetail(MgrContractItemDTO mgrContractItemDTO, User user) throws Exception;

    public int insertDetail(MgrContractItemDTO mgrContractItemDTO, User user) throws Exception;

    public int[] insertDetail(List<MgrContractItemDTO> list, User user) throws Exception;

}
