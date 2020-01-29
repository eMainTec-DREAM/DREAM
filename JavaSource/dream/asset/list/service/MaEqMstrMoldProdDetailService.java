package dream.asset.list.service;

import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrMoldProdDetailDTO;

/**
 * 구성자재 상세
 * @author  kim210117
 * @version $Id: MaEqMstrMoldProdDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface MaEqMstrMoldProdDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaEqMstrMoldProdDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param equipId
     * @param eqPartId
     * @param compNo
     * @return
     * @throws Exception
     */
    public MaEqMstrMoldProdDetailDTO findDetail(String equipId, String eqPartId,String compNo)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaEqMstrMoldProdDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrMoldProdDetailDTO
     * @param maEqMstrCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaEqMstrMoldProdDetailDTO maEqMstrMoldProdDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaEqMstrMoldProdDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrMoldProdDetailDTO
     * @param maEqMstrCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaEqMstrMoldProdDetailDTO maEqMstrMoldProdDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO) throws Exception;
}
