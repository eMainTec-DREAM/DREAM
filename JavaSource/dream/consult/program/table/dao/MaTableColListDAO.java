package dream.consult.program.table.dao;

import java.util.List;

import common.bean.User;
import dream.consult.program.table.dto.MaTableColListDTO;
import dream.consult.program.table.dto.MaTableCommonDTO;

/**
 * 데이터 테이블 detail-code 목록 dao
 * @author  kim21017
 * @version $Id: MaTableColListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaTableColListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaTableColListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maTableCommonDTO
     * @param maTableColListDTO
     * @return List
     */
    public List findCodeList(MaTableCommonDTO maTableCommonDTO, MaTableColListDTO maTableColListDTO, String lang);
    public String findTotalCount(MaTableCommonDTO maTableCommonDTO, MaTableColListDTO maTableColListDTO, User user);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaTableColListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRow
     * @param deleteRowsExt 
     * @return
     */
    public int deleteCodeList(String deleteRow, String deleteRowsExt);
}