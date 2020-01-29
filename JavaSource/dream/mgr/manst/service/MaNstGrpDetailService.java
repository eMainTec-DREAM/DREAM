package dream.mgr.manst.service;

import common.bean.User;
import dream.mgr.manst.dto.MaNstGrpCommonDTO;
import dream.mgr.manst.dto.MaNstGrpDetailDTO;

/**
 * 무정지대표라인 - 상세 service
 * 
 * @author kim21017
 * @version $Id: MaNstGrpDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface MaNstGrpDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaNstGrpDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param eqLocId
     * @param compNo
     * @return
     * @throws Exception
     */
    public MaNstGrpDetailDTO findDetail(MaNstGrpCommonDTO maNstGrpCommonDTO, User loginUser)throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaNstGrpDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maNstGrpDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaNstGrpDetailDTO maNstGrpDetailDTO) throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaNstGrpDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maNstGrpDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaNstGrpDetailDTO maNstGrpDetailDTO) throws Exception;
}
