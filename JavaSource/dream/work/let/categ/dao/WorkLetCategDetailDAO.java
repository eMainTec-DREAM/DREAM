package dream.work.let.categ.dao;

import common.bean.User;
import dream.work.let.categ.dto.WorkLetCategCommonDTO;
import dream.work.let.categ.dto.WorkLetCategDetailDTO;

/**
 * 안전작업유형 Detail Page - Detail DAO
 * @author euna0207
 * @version $Id: WorkLetCategDetailDAO.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since 1.0
 *
 */
public interface WorkLetCategDetailDAO
{
    /**
     * FIND DETAIL
     * @param workLetCategCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public WorkLetCategDetailDTO findDetail(WorkLetCategCommonDTO workLetCategCommonDTO, User user) throws Exception;
    
    /**
     * INSERT DETAIL
     * @param workLetCategDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(WorkLetCategDetailDTO workLetCategDetailDTO, User user) throws Exception;
    
    /**
     * UPDATE DETAIL
     * @param workLetCategDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(WorkLetCategDetailDTO workLetCategDetailDTO, User user) throws Exception;
    
}