package dream.asset.list.dao;

import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrMoldModHistDetailDTO;

/**
 * 구성자재 상세 dao
 * @author  kim21017
 * @version $Id: MaEqMstrMoldModHistDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface MaEqMstrMoldModHistDetailDAO 
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaEqMstrMoldModHistDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param equipId
     * @param eqMoldModHistId
     * @param compNo
     * @return
     */
    public MaEqMstrMoldModHistDetailDTO findDetail(String equipId, String eqMoldModHistId, String compNo);
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaEqMstrMoldModHistDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrMoldModHistDetailDTO
     * @param maEqMstrCommonDTO
     * @return
     */
    public int updateDetail(MaEqMstrMoldModHistDetailDTO maEqMstrMoldModHistDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaEqMstrMoldModHistDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrMoldModHistDetailDTO
     * @param maEqMstrCommonDTO
     * @return
     */
    public int insertDetail(MaEqMstrMoldModHistDetailDTO maEqMstrMoldModHistDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO);
}