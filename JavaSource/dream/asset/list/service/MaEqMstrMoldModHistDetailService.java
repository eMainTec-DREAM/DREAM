package dream.asset.list.service;

import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrMoldModHistDetailDTO;

/**
 * 구성자재 상세
 * @author  kim210117
 * @version $Id: MaEqMstrMoldModHistDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface MaEqMstrMoldModHistDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaEqMstrMoldModHistDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param equipId
     * @param eqPartId
     * @param compNo
     * @return
     * @throws Exception
     */
    public MaEqMstrMoldModHistDetailDTO findDetail(String equipId, String eqPartId,String compNo)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaEqMstrMoldModHistDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrMoldModHistDetailDTO
     * @param maEqMstrCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaEqMstrMoldModHistDetailDTO maEqMstrMoldModHistDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaEqMstrMoldModHistDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrMoldModHistDetailDTO
     * @param maEqMstrCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaEqMstrMoldModHistDetailDTO maEqMstrMoldModHistDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO) throws Exception;
}
