package dream.asset.list.dao;


import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.asset.list.dto.MaEqMstrPmWorkDetailDTO;
import dream.asset.list.dto.MaEqMstrPmWorkPartDetailDTO;
import dream.asset.list.dto.MaEqMstrPmWorkPartListDTO;

/**
 * ���� �����۾� ��ǰ �� DAO
 * @author  kim21017
 * @version $Id: MaEqMstrPmWorkPartDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface MaEqMstrPmWorkPartDetailDAO extends BaseJdbcDaoSupportIntf
{    
	/**
	 * ���� �������� �׸� �� ��ȸ
	 * @param MaEqMstrPmWorkDetailDTO
	 * @param maEqMstrPmWorkPartListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public MaEqMstrPmWorkPartDetailDTO findDetail(MaEqMstrPmWorkDetailDTO maEqMstrPmWorkDetailDTO, MaEqMstrPmWorkPartListDTO maEqMstrPmWorkPartListDTO, User user)throws Exception;
}