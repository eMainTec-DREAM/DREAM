package dream.consult.program.table.service;

import dream.consult.program.table.dto.MaTableColDetailDTO;
import dream.consult.program.table.dto.MaTableColListDTO;
import dream.consult.program.table.dto.MaTableCommonDTO;

/**
 * 데이터 테이블 - detail-code 
 * @author  kim210117
 * @version $Id: MaTableColDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface MaTableColDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaTableColDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param tableMId
     * @param tabColId
     * @return
     * @throws Exception
     */
    public MaTableColDetailDTO findDetail(String tableMId, String tabColId, String lang)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaTableColDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maTableColDetailDTO
     * @param maTableCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaTableColDetailDTO maTableColDetailDTO, MaTableCommonDTO maTableCommonDTO) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaTableColDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maTableColDetailDTO
     * @param maTableCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaTableColDetailDTO maTableColDetailDTO, MaTableCommonDTO maTableCommonDTO) throws Exception;
}
