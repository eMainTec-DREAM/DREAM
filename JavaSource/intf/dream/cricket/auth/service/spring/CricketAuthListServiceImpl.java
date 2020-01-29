package intf.dream.cricket.auth.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.cricket.auth.dao.CricketAuthListDAO;
import intf.dream.cricket.auth.service.CricketAuthListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="cricketAuthListServiceTarget"
 * @spring.txbn id="cricketAuthListService"
 * @spring.property name="cricketAuthListDAO" ref="cricketAuthListDAO"
 */
public class CricketAuthListServiceImpl implements CricketAuthListService
{
    private CricketAuthListDAO cricketAuthListDAO = null;
    
	public CricketAuthListDAO getCricketAuthListDAO() {
		return cricketAuthListDAO;
	}
	public void setCricketAuthListDAO(CricketAuthListDAO cricketAuthListDAO) {
		this.cricketAuthListDAO = cricketAuthListDAO;
	}
	@Override
	public List findAuthPageList(Map map) throws Exception {
		return cricketAuthListDAO.findAuthPageList(map);
		}
	@Override
	public List findAuthPgBtnList(Map map) throws Exception {
		return cricketAuthListDAO.findAuthPgBtnList(map);
		}
	@Override
	public List findAuthPgFieldList(Map map) throws Exception {
		return cricketAuthListDAO.findAuthPgFieldList(map);
		}
	@Override
	public List findAuthPgGridColList(Map map) throws Exception {
		return cricketAuthListDAO.findAuthPgGridColList(map);
		}
	@Override
	public List findAuthPgPageList(Map map) throws Exception {
		return cricketAuthListDAO.findAuthPgPageList(map);
		}
	@Override
	public List findAuthPgLinkedList(Map map) throws Exception {
		return cricketAuthListDAO.findAuthPgLinkedList(map);
		}
	
}

