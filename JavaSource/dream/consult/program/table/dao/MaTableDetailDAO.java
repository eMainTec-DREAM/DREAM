package dream.consult.program.table.dao;

import dream.consult.program.table.dto.MaTableDetailDTO;

/**
 * 데이터 테이블 - 상세 dao
 * @author kim21017
 * @version $Id: MaTableDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 */
public interface MaTableDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaTableDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param tableMId
     * @return
     */
    public MaTableDetailDTO findDetail(String tableMId, String lang);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaTableDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maTableDetailDTO
     * @return
     */
    public int insertDetail(MaTableDetailDTO maTableDetailDTO);

    /**
     * detail update
     * @author kim21017
     * @version $Id: MaTableDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maTableDetailDTO
     * @return
     */
    public int updateDetail(MaTableDetailDTO maTableDetailDTO);
}