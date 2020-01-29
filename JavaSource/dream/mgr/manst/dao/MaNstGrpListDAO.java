package dream.mgr.manst.dao;

import java.util.List;

import dream.mgr.manst.dto.MaNstGrpCommonDTO;

/**
 * 무정지대표라인 - 목록 
 * @author  kim21017
 * @version $Id: MaNstGrpListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaNstGrpListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaNstGrpListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maNstGrpCommonDTO
     * @return List
     */
    public List findNstGrpList(MaNstGrpCommonDTO maNstGrpCommonDTO);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaNstGrpListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param maNstGrpCommonDTO
     * @return
     */
    public int deleteNstGrp(String id, MaNstGrpCommonDTO maNstGrpCommonDTO);
}