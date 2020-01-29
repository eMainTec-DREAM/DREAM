package dream.tool.adj.dao;

import dream.tool.adj.dto.MaPttDisCommonDTO;
import dream.tool.adj.dto.MaPttDisPartsDetailDTO;
import dream.tool.adj.dto.MaPttDisPartsListDTO;

/**
 *  »ó¼¼ dao
 * @author  kim21017
 * @version $Id: MaPttDisPartsDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface MaPttDisPartsDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaPttDisPartsDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPttDisPartsListDTO
     * @param maPttDisCommonDTO
     * @return
     */
    public MaPttDisPartsDetailDTO findDetail(MaPttDisPartsListDTO maPttDisPartsListDTO, MaPttDisCommonDTO maPttDisCommonDTO);

    /**
     * detail update
     * @author kim21017
     * @version $Id: MaPttDisPartsDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPttDisPartsDetailDTO
     * @param maPttDisCommonDTO
     * @return
     */
    public int updateDetail(MaPttDisPartsDetailDTO maPttDisPartsDetailDTO, MaPttDisCommonDTO maPttDisCommonDTO);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaPttDisPartsDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPttDisPartsDetailDTO
     * @param maPttDisCommonDTO
     * @return
     */
    public int insertDetail(MaPttDisPartsDetailDTO maPttDisPartsDetailDTO, MaPttDisCommonDTO maPttDisCommonDTO);
    
}