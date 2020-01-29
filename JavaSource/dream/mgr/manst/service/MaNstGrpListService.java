package dream.mgr.manst.service;

import java.util.List;

import dream.mgr.manst.dto.MaNstGrpCommonDTO;


/**
 * 무정지대표라인 - 목록 service
 * @author  kim21017
 * @version $Id: MaNstGrpListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaNstGrpListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaNstGrpListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maNstGrpCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findNstGrpList(MaNstGrpCommonDTO maNstGrpCommonDTO);    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaNstGrpListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @param maNstGrpCommonDTO
     * @return
     * @throws Exception
     */
    public int deleteNstGrp(String[] deleteRows, MaNstGrpCommonDTO maNstGrpCommonDTO) throws Exception;
}
