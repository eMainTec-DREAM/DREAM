package dream.comm.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.comm.dto.CommLogScrnTraceDTO;


/**
 * Validation DAOIml
 * @author  javaworker
 * @version $Id: ValidationDAO.java,v 1.20 2014/09/03 04:19:26 pochul2423 Exp $
 * @since   1.0
 */
public interface CommLogScreenTraceDAO extends BaseJdbcDaoSupportIntf
{
	public int insertLogScreenTrace(CommLogScrnTraceDTO commLogScrnTraceDTO, User user);

}