package intf.dream.android.online.doc.service;

import java.util.List;
import java.util.Map;

/**
 * service
 * 
 * @author
 * @version $Id: $
 * @since 1.0
 */
public interface AnOnDocListService {
	public List findDocList(Map map) throws Exception;
	public List findFileUrl(Map map) throws Exception;
}
