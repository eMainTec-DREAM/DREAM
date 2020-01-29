package dream.consult.program.table.dao;

import java.util.List;

import common.bean.User;
import dream.consult.program.table.dto.MaTableCommonDTO;

/**
 * 데이터 테이블 - 목록 dao
 * @author  kim21017
 * @version $Id: MaTableListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaTableListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaTableListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maTableCommonDTO
     * @return List
     */
    public List findListTypeList(MaTableCommonDTO maTableCommonDTO);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaTableListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteListType(String id);
    
    public String findTotalCount(MaTableCommonDTO maTableCommonDTO, User user) throws Exception;
}