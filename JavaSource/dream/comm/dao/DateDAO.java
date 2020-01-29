package dream.comm.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.comm.form.DateForm;


/**
 * Date DAO
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface DateDAO extends BaseJdbcDaoSupportIntf
{

    String getTimeStamp(int timezone, String format, String intervalType, int interval);

}