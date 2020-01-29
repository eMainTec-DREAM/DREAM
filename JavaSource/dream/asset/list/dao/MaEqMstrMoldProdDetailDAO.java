package dream.asset.list.dao;

import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrMoldProdDetailDTO;

/**
 * 구성자재 상세 dao
 * @author  kim21017
 * @version $Id: MaEqMstrMoldProdDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface MaEqMstrMoldProdDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaEqMstrMoldProdDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param equipId
     * @param eqPartId
     * @param compNo
     * @return
     */
    public MaEqMstrMoldProdDetailDTO findDetail(String equipId, String eqMoldProductId, String compNo);
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaEqMstrMoldProdDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrMoldProdDetailDTO
     * @param maEqMstrCommonDTO
     * @return
     */
    public int updateDetail(MaEqMstrMoldProdDetailDTO maEqMstrMoldProdDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaEqMstrMoldProdDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrMoldProdDetailDTO
     * @param maEqMstrCommonDTO
     * @return
     */
    public int insertDetail(MaEqMstrMoldProdDetailDTO maEqMstrMoldProdDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO);
}