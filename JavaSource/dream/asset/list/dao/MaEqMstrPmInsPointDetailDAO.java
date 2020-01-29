package dream.asset.list.dao;


import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.asset.list.dto.MaEqMstrPmInsDetailDTO;
import dream.asset.list.dto.MaEqMstrPmInsPointDetailDTO;
import dream.asset.list.dto.MaEqMstrPmInsPointListDTO;

/**
 * ���� �������� �׸� �� DAO
 * @author  kim21017
 * @version $Id: MaEqMstrPmInsPointDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface MaEqMstrPmInsPointDetailDAO extends BaseJdbcDaoSupportIntf
{    
	/**
	 * ���� �������� �׸� �� ��ȸ
	 * @param MaEqMstrPmInsDetailDTO
	 * @param maEqMstrPmInsPointListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public MaEqMstrPmInsPointDetailDTO findDetail(MaEqMstrPmInsDetailDTO maEqMstrPmInsDetailDTO, MaEqMstrPmInsPointListDTO maEqMstrPmInsPointListDTO, User user)throws Exception;
}