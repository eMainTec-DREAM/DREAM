package dream.work.let.categ.dao;

import common.bean.User;
import dream.work.let.categ.dto.WorkLetCategCommonDTO;
import dream.work.let.categ.dto.WorkLetCategPointDetailDTO;
import dream.work.let.categ.dto.WorkLetCategPointListDTO;

/**
 * 안전작업유형 점검항목 Detail Page - Detail DAO
 * @author euna0207
 * @version $Id: WorkLetCategPointDetailDAO.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since 1.0
 *
 */
public interface WorkLetCategPointDetailDAO
{
    /**
     * FIND DETAIL
     * @param workLetCategPointListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public WorkLetCategPointDetailDTO findDetail(WorkLetCategCommonDTO workLetCategCommonDTO, WorkLetCategPointListDTO workLetCategPointListDTO, User user) throws Exception;
    
    /**
     * INSERT DETAIL
     * @param workLetCategPointDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(WorkLetCategCommonDTO workLetCategCommonDTO, WorkLetCategPointDetailDTO workLetCategPointDetailDTO, User user) throws Exception;
    
    /**
     * UPDATE DETAIL
     * @param workLetCategPointDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(WorkLetCategCommonDTO workLetCategCommonDTO, WorkLetCategPointDetailDTO workLetCategPointDetailDTO, User user) throws Exception;
    
}