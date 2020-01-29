/**
 * 
 */
package dream.asset.list.service;

import java.util.List;

import common.bean.User;
import dream.asset.list.dto.AssetListPartOpQtyDTO;
/**
 * ������Ⱓ��뿹�����
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 */
public interface AssetListPartOpQtyService {
	public List findList(AssetListPartOpQtyDTO assetListPartOpQtyDTO, User user) throws Exception;
	
	public AssetListPartOpQtyDTO findDetail(AssetListPartOpQtyDTO assetListPartOpQtyDTO, User user) throws Exception;
    
    public int updateDetail(AssetListPartOpQtyDTO assetListPartOpQtyDTO, User user) throws Exception;
	
	public int inputDetail(AssetListPartOpQtyDTO assetListPartOpQtyDTO, User user) throws Exception;
	
	public int deleteList(String[] deleteRows, User user) throws Exception;
	
    public String findTotalCount(AssetListPartOpQtyDTO assetListPartOpQtyDTO, User user) throws Exception;
}
