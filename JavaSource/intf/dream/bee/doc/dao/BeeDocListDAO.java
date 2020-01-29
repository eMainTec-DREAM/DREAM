package intf.dream.bee.doc.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
import intf.dream.bee.doc.dto.BeeDocCommonDTO;

/**
 * dao
 * 
 * @author
 * @version $Id: $
 * @since 1.0
 */
public interface BeeDocListDAO extends BaseJdbcDaoSupportIntf {
	public List findDocList(BeeDocCommonDTO beeDocCommonDTO, Map map) throws Exception;
	public List findDocCount(BeeDocCommonDTO beeDocCommonDTO, Map map) throws Exception;
}