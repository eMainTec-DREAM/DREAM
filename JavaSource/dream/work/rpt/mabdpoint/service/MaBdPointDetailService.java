package dream.work.rpt.mabdpoint.service;

import common.bean.User;
import dream.work.rpt.mabdpoint.dto.MaBdPointCommonDTO;
import dream.work.rpt.mabdpoint.dto.MaBdPointDetailDTO;

/**
 * 이상점검조치 - 상세 service
 * 
 * @author kim21017
 * @version $Id:$
 * @since 1.0
 */
public interface MaBdPointDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param ptRecListId
     * @return
     * @throws Exception
     */
    public MaBdPointDetailDTO findDetail(MaBdPointCommonDTO maBdPointCommonDTO, User loginUser)throws Exception;
   
    
    /**
     * detail update
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maBdPointDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaBdPointDetailDTO maBdPointDetailDTO) throws Exception;
    
    public String findId(MaBdPointDetailDTO maBdPointDetailDTO, User loginUser) throws Exception;
    
    public String findStatus(MaBdPointCommonDTO maBdPointCommonDTO, User user) throws Exception;
    
    public int checkNgPoint(String pmiType, String keyId, String status, String actualDate, User user) throws Exception;
}
