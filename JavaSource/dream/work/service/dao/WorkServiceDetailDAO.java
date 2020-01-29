package dream.work.service.dao;

import common.bean.User;
import dream.work.service.dto.WorkServiceCommonDTO;
import dream.work.service.dto.WorkServiceDetailDTO;

/**
 * 서비스 마스터 - Detail DAO
 * @author cjscjs9
 * @version $Id: WorkServiceDetailDAO.java,v 1.0 2018/08/02 09:12:40 cjscjs9 Exp $
 * @since 1.0
 *
 */
public interface WorkServiceDetailDAO
{
    /**
     * FIND DETAIL
     * @param workServiceCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public WorkServiceDetailDTO findWorkServiceDetail(WorkServiceCommonDTO workServiceCommonDTO, User user) throws Exception;
    
    /**
     * INSERT DETAIL
     * @param workServiceDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertWorkServiceDetail(WorkServiceDetailDTO workServiceDetailDTO, User user) throws Exception;
    
    /**
     * UPDATE DETAIL
     * @param workServiceDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateWorkServiceDetail(WorkServiceDetailDTO workServiceDetailDTO, User user) throws Exception;
    
}