package dream.consult.program.table.dao;

import dream.consult.program.table.dto.MaTableColDetailDTO;
import dream.consult.program.table.dto.MaTableCommonDTO;

/**
 * 데이터 테이블-분류 dao
 * @author  kim21017
 * @version $Id: MaTableColDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface MaTableColDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaTableColDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param tableMId
     * @param tabColId
     * @return
     */
    public MaTableColDetailDTO findDetail(String tableMId, String tabColId, String lang);

    /**
     * detail update
     * @author kim21017
     * @version $Id: MaTableColDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maTableColDetailDTO
     * @param maTableCommonDTO
     * @return
     */
    public int updateDetail(MaTableColDetailDTO maTableColDetailDTO, MaTableCommonDTO maTableCommonDTO);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaTableColDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maTableColDetailDTO
     * @param maTableCommonDTO
     * @return
     */
    public int insertDetail(MaTableColDetailDTO maTableColDetailDTO, MaTableCommonDTO maTableCommonDTO);
}