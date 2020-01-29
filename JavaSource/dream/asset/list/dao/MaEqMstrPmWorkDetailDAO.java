package dream.asset.list.dao;


import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrPmWorkDetailDTO;
import dream.asset.list.dto.MaEqMstrPmWorkListDTO;

/**
 * 설비 정기작업 상세 DAO
 * @author  kim21017
 * @version $Id: MaEqMstrPmWorkDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface MaEqMstrPmWorkDetailDAO extends BaseJdbcDaoSupportIntf
{    
	/**
	 * 설비 예방작업 상세 조회
	 * @param maEqMstrCommonDTO
	 * @param maEqMstrPmWorkListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public MaEqMstrPmWorkDetailDTO findDetail(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmWorkListDTO maEqMstrPmWorkListDTO, User user)throws Exception;
}