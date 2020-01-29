package dream.tool.adj.service;

import dream.tool.adj.dto.MaPttDisCommonDTO;
import dream.tool.adj.dto.MaPttDisPartsDetailDTO;
import dream.tool.adj.dto.MaPttDisPartsListDTO;

/**
 *  - detail
 * @author  kim210117
 * @version $Id: MaPttDisPartsDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface MaPttDisPartsDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaPttDisPartsDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPttDisPartsListDTO
     * @param maPttDisCommonDTO
     * @return
     * @throws Exception
     */
    public MaPttDisPartsDetailDTO findDetail(MaPttDisPartsListDTO maPttDisPartsListDTO, MaPttDisCommonDTO maPttDisCommonDTO)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaPttDisPartsDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPttDisPartsDetailDTO
     * @param maPttDisCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaPttDisPartsDetailDTO maPttDisPartsDetailDTO, MaPttDisCommonDTO maPttDisCommonDTO) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaPttDisPartsDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPttDisPartsDetailDTO
     * @param maPttDisCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaPttDisPartsDetailDTO maPttDisPartsDetailDTO, MaPttDisCommonDTO maPttDisCommonDTO) throws Exception;
    
}
