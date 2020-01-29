package dream.mgr.contract.service;

import java.util.List;

import common.bean.User;
import dream.mgr.contract.dto.MgrContractDTO;

/**
 * 사용달력설정 - 목록 service
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface MgrContractService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id$
     * @param mgrContractDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List<MgrContractDTO> findList(MgrContractDTO mgrContractDTO, User user);

    public String findTotalCount(MgrContractDTO mgrContractDTO, User user);

    public int[] deleteList(String[] deleteRows, User user) throws Exception;
    
    public MgrContractDTO findDetail(MgrContractDTO mgrContractDTO, User user)throws Exception;

    public int updateDetail(MgrContractDTO mgrContractDTO, User user) throws Exception;
    
    public int insertDetail(MgrContractDTO mgrContractDTO, User user) throws Exception;
    
    public int[] insertDetail(List<MgrContractDTO> list, User user) throws Exception;
}
