package dream.mgr.manst.dao;

import common.bean.User;
import dream.mgr.manst.dto.MaNstGrpCommonDTO;
import dream.mgr.manst.dto.MaNstGrpDetailDTO;

/**
 * 무정지대표라인 - 상세
 * 
 * @author kim21017
 * @version $Id: MaNstGrpDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 */
public interface MaNstGrpDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaNstGrpDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param eqLocId
     * @param compNo
     * @return
     */
    public MaNstGrpDetailDTO findDetail(MaNstGrpCommonDTO maNstGrpCommonDTO, User loginUser);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaNstGrpDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maNstGrpDetailDTO
     * @return
     */
    public int insertDetail(MaNstGrpDetailDTO maNstGrpDetailDTO);

    /**
     * detail update
     * @author kim21017
     * @version $Id: MaNstGrpDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maNstGrpDetailDTO
     * @return
     */
    public int updateDetail(MaNstGrpDetailDTO maNstGrpDetailDTO);

    public int updateLineRate(MaNstGrpDetailDTO maNstGrpDetailDTO);
}