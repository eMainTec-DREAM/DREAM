package intf.dream.bee.auth.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.bee.auth.dao.BeeAuthListDAO;
import intf.dream.bee.auth.service.BeeAuthListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="beeAuthListServiceTarget"
 * @spring.txbn id="beeAuthListService"
 * @spring.property name="beeAuthListDAO" ref="beeAuthListDAO"
 */
public class BeeAuthListServiceImpl implements BeeAuthListService
{
    private BeeAuthListDAO beeAuthListDAO = null;
    
	public BeeAuthListDAO getBeeAuthListDAO() {
		return beeAuthListDAO;
	}
	public void setBeeAuthListDAO(BeeAuthListDAO beeAuthListDAO) {
		this.beeAuthListDAO = beeAuthListDAO;
	}
	@Override
	public List findAuthPageList(Map map) throws Exception {
		return beeAuthListDAO.findAuthPageList(map);
		}
	@Override
	public List findAuthPgBtnList(Map map) throws Exception {
		return beeAuthListDAO.findAuthPgBtnList(map);
		}
	@Override
	public List findAuthPgFieldList(Map map) throws Exception {
		return beeAuthListDAO.findAuthPgFieldList(map);
		}
	@Override
	public List findAuthPgGridColList(Map map) throws Exception {
		return beeAuthListDAO.findAuthPgGridColList(map);
		}
	@Override
	public List findAuthPgPageList(Map map) throws Exception {
		return beeAuthListDAO.findAuthPgPageList(map);
		}
	@Override
	public List findAuthPgLinkedList(Map map) throws Exception {
		return beeAuthListDAO.findAuthPgLinkedList(map);
		}
	
}

