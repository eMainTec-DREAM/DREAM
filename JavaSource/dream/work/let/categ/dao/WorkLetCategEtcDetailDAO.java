package dream.work.let.categ.dao;

import common.bean.User;
import dream.work.let.categ.dto.WorkLetCategCommonDTO;
import dream.work.let.categ.dto.WorkLetCategEtcDetailDTO;
import dream.work.let.categ.dto.WorkLetCategEtcListDTO;

/**
 * 안전작업유형 - 보완사항 Detail Page - Detail DAO
 * @author euna0207
 * @version $Id: WorkLetCategEtcDetailDAO.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since 1.0
 *
 */
public interface WorkLetCategEtcDetailDAO
{
    /**
     * FIND DETAIL
     * @param workLetCategEtcListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public WorkLetCategEtcDetailDTO findDetail(WorkLetCategCommonDTO workLetCategCommonDTO, WorkLetCategEtcListDTO workLetCategEtcListDTO, User user) throws Exception;
    
    /**
     * INSERT DETAIL
     * @param workLetCategEtcDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(WorkLetCategCommonDTO workLetCategCommonDTO, WorkLetCategEtcDetailDTO workLetCategEtcDetailDTO, User user) throws Exception;
    
    /**
     * UPDATE DETAIL
     * @param workLetCategEtcDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(WorkLetCategCommonDTO workLetCategCommonDTO, WorkLetCategEtcDetailDTO workLetCategEtcDetailDTO, User user) throws Exception;
    
}