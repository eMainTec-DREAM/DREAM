package dream.mgr.ptwh.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.mgr.ptwh.dto.MgrPtWhBinListDTO;

/**
 * ��ǰâ�� ������ġ - List DAO
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 *
 */
public interface MgrPtWhBinListDAO extends BaseJdbcDaoSupportIntf
{
	/**
	 * FIND LIST
	 * @param mgrPtWhBinListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public List findPtWhEmpList(MgrPtWhBinListDTO mgrPtWhBinListDTO, User user) throws Exception;
    
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
	 * @param mgrPtWhBinListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(MgrPtWhBinListDTO mgrPtWhBinListDTO, User user) throws Exception;

    public int deleteQrList(User user, String fileName) throws Exception;

    public int insertQrList(String id, String fileName, User user) throws Exception;
    
}