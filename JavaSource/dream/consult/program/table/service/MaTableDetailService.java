package dream.consult.program.table.service;

import dream.consult.program.table.dto.MaTableDetailDTO;

/**
 * 데이터 테이블 - 상세 service
 * 
 * @author kim21017
 * @version $Id: MaTableDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface MaTableDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaTableDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param tableMId
     * @return
     * @throws Exception
     */
    public MaTableDetailDTO findDetail(String tableMId, String lang)throws Exception;

    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaTableDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maTableDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaTableDetailDTO maTableDetailDTO) throws Exception;
    
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaTableDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maTableDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaTableDetailDTO maTableDetailDTO) throws Exception;
}
