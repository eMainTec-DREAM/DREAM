package intf.dream.android.online.auth.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.android.online.auth.dao.AnOnAuthListDAO;
import intf.dream.android.online.auth.service.AnOnAuthListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="anOnAuthListServiceTarget"
 * @spring.txbn id="anOnAuthListService"
 * @spring.property name="anOnAuthListDAO" ref="anOnAuthListDAO"
 */
public class AnOnAuthListServiceImpl implements AnOnAuthListService
{
    private AnOnAuthListDAO anOnAuthListDAO = null;
    
	public AnOnAuthListDAO getAnOnAuthListDAO() {
		return anOnAuthListDAO;
	}
	public void setAnOnAuthListDAO(AnOnAuthListDAO anOnAuthListDAO) {
		this.anOnAuthListDAO = anOnAuthListDAO;
	}
	@Override
	public List findAuthPageList(Map map) throws Exception {
		return anOnAuthListDAO.findAuthPageList(map);
		}
	@Override
	public List findAuthPgBtnList(Map map) throws Exception {
		return anOnAuthListDAO.findAuthPgBtnList(map);
		}
	@Override
	public List findAuthPgFieldList(Map map) throws Exception {
		return anOnAuthListDAO.findAuthPgFieldList(map);
		}
	@Override
	public List findAuthPgGridColList(Map map) throws Exception {
		return anOnAuthListDAO.findAuthPgGridColList(map);
		}
	@Override
	public List findAuthPgPageList(Map map) throws Exception {
		return anOnAuthListDAO.findAuthPgPageList(map);
		}
	@Override
	public List findAuthPgLinkedList(Map map) throws Exception {
		return anOnAuthListDAO.findAuthPgLinkedList(map);
		}
	
}

