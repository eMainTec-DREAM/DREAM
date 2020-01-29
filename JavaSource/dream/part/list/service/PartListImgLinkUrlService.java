/**
 * 
 */
package dream.part.list.service;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.part.list.dto.PartListImgLinkUrlDTO;
/**
 * ºÎÇ°Image Link Service
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 */
public interface PartListImgLinkUrlService {
	public List findList(PartListImgLinkUrlDTO partListImgLinkUrlDTO, User user) throws Exception;
	
	public int deleteList(String[] deleteRows, User user) throws Exception;
	
    public String findTotalCount(PartListImgLinkUrlDTO partListImgLinkUrlDTO, User user) throws Exception;
    
    public PartListImgLinkUrlDTO findDetail(PartListImgLinkUrlDTO partListImgLinkUrlDTO, User user) throws Exception;
    
    public int insertDetail(PartListImgLinkUrlDTO partListImgLinkUrlDTO, User user) throws Exception;
    
    public int updateDetail(PartListImgLinkUrlDTO partListImgLinkUrlDTO, User user) throws Exception;
    
    public Map<String, String> getImage(PartListImgLinkUrlDTO partListImgLinkUrlDTO, User user) throws Exception;

    public void getImageAll(User user) throws Exception;
}
