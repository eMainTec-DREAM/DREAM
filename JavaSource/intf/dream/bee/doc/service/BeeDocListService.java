package intf.dream.bee.doc.service;

import java.util.List;
import java.util.Map;

import intf.dream.bee.doc.dto.BeeDocCommonDTO;

/**
 * service
 * 
 * @author
 * @version $Id: $
 * @since 1.0
 */
public interface BeeDocListService {
	public List findDocList(BeeDocCommonDTO beeDocCommonDTO, Map map) throws Exception;
	public List findDocCount(BeeDocCommonDTO beeDocCommonDTO, Map map) throws Exception;
	public List findFileUrl(BeeDocCommonDTO beeDocCommonDTO, Map map) throws Exception;
}
