package dream.part.list.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.part.list.dto.PartListBinListDTO;

/**
 * 부품창고 보관위치 - List DAO
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 *
 */
public interface PartListBinListDAO extends BaseJdbcDaoSupportIntf
{
	/**
	 * FIND LIST
	 * @param partListBinListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public List findPtWhBinList(PartListBinListDTO partListBinListDTO, User user) throws Exception;
    
    /**
     * DELETE LIST
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public int deletePtWhBinList(String id, User user) throws Exception;
    /**
	 * FIND LIST COUNT
	 * @param partListBinListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(PartListBinListDTO partListBinListDTO, User user) throws Exception;
    
}