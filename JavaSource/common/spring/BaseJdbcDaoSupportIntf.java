package common.spring;

import java.util.List;

import common.bean.User;

/**
 * JDBC DAO 공통 구현 
 * @author  javaworker
 * @version $Id: BaseJdbcDaoSupport.java,v 1.1 2013/08/30 09:13:56 javaworker Exp $
 * @since   1.0
 *
 */
public interface BaseJdbcDaoSupportIntf
{        
    public String getNextSequence(String sequenceName);
    
    public List getHeader(User user, String page_id, String list_id);
    
	public List getUserHeader(User user, String list_id, String currentPageId);
	
	public String convertString(Object obj);
	
    public List getGaiaUserHeader(User user, String listId, String currentPageId);
}
