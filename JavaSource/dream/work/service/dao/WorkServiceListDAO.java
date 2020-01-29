package dream.work.service.dao;

import java.util.List;

import common.bean.User;
import dream.work.service.dto.WorkServiceCommonDTO;

/**
 * 서비스 마스터 - List DAO
 * @author cjscjs9
 * @version $Id: WorkServiceListDAO.java,v 1.0 2018/08/02 09:12:40 cjscjs9 Exp $
 * @since 1.0
 *
 */
public interface WorkServiceListDAO
{
	/**
	 * FIND LIST
	 * @param workServiceCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public List findWorkServiceList(WorkServiceCommonDTO workServiceCommonDTO, User user) throws Exception;
    
    /**
     * DELETE LIST
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteWorkServiceList(String id, User user) throws Exception;
    /**
	 * FIND LIST COUNT
	 * @param workServiceCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(WorkServiceCommonDTO workServiceCommonDTO, User user) throws Exception;
    
}