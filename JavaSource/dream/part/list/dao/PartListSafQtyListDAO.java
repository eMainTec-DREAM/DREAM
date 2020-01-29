package dream.part.list.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.part.list.dto.PartListSafQtyListDTO;

/**
 * 부품창고 보관위치 - List DAO
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 *
 */
public interface PartListSafQtyListDAO extends BaseJdbcDaoSupportIntf
{
	/**
	 * FIND LIST
	 * @param partListSafQtyListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public List findPtWhEmpList(PartListSafQtyListDTO partListSafQtyListDTO, User user) throws Exception;
    
    /**
     * DELETE LIST
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public int deletePtWhEmpList(String id, User user) throws Exception;
    /**
	 * FIND LIST COUNT
	 * @param partListSafQtyListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(PartListSafQtyListDTO partListSafQtyListDTO, User user) throws Exception;
    
}