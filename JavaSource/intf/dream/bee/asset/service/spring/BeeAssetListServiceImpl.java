package intf.dream.bee.asset.service.spring;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import common.bean.MwareConfig;
import common.file.FileUploadUtil;
import intf.dream.bee.asset.dao.BeeAssetListDAO;
import intf.dream.bee.asset.dto.BeeAssetCommonDTO;
import intf.dream.bee.asset.service.BeeAssetListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="beeAssetListServiceTarget"
 * @spring.txbn id="beeAssetListService"
 * @spring.property name="beeAssetListDAO" ref="beeAssetListDAO"
 */
public class BeeAssetListServiceImpl implements BeeAssetListService
{
    private BeeAssetListDAO beeAssetListDAO = null;

	public BeeAssetListDAO getBeeAssetListDAO() {
		return beeAssetListDAO;
	}
	public void setBeeAssetListDAO(BeeAssetListDAO beeAssetListDAO) {
		this.beeAssetListDAO = beeAssetListDAO;
	}
	
	public List findAssetList(BeeAssetCommonDTO beeAssetCommonDTO, Map map) throws Exception
	{      
		return beeAssetListDAO.findAssetList(beeAssetCommonDTO, map);
	}
	public List findAssetCount(BeeAssetCommonDTO beeAssetCommonDTO, Map map) throws Exception
	{      
		return beeAssetListDAO.findAssetCount(beeAssetCommonDTO, map);
	}
	public List findAssetPartList(Map map) throws Exception
	{      
		return beeAssetListDAO.findAssetPartList(map);
	}
	public List findAssetSpecList(Map map) throws Exception
	{      
		return beeAssetListDAO.findAssetSpecList(map);
	}
	public List findAssetToolList(Map map) throws Exception
	{
		return beeAssetListDAO.findAssetToolList(map);
	}
	public List findDocumentList(Map map) throws Exception
	{      
		List list = beeAssetListDAO.findDocumentList(map);
		
		return list;
	}
	public List findDocumentCtgList(Map map) throws Exception
	{      
		List list = beeAssetListDAO.findDocumentCtgList(map);
		
		return list;
	}
	public List fileCopyList(Map map) throws Exception
	{      
		List list = beeAssetListDAO.findDocumentList(map);
		
		for(int i=0; i<list.size(); i++){
			Map listMap = (Map)list.get(i);
			
			String fileNo = String.valueOf(listMap.get("ID"));	//docdata_id
			String fileName = String.valueOf(listMap.get("FILE_NAME"));	//file_name
			String filePath = String.valueOf(listMap.get("FILE_PATH"));	//file_path
			
			String originFile = MwareConfig.getFileDir() + filePath + File.separator + fileNo;
			String targetFile = MwareConfig.getWebAppPath() + "dream"+File.separator+"android"+File.separator+"" + fileNo;
			
			try
	           {
				File of = new File(originFile);
				File f = new File(targetFile);
				
				if (of.length() != f.length() || !f.isFile()) {
					FileUploadUtil.fileCopy(originFile, targetFile, MwareConfig.getWebAppPath()+"dream"+File.separator+"android"+File.separator);
				}
	           }catch (IOException e){}
			
		}
		
		return list;
	}
	public List findWoHistList(Map map) throws Exception
	{      
		return beeAssetListDAO.findWoHistList(map);
	}
	public String findTotalCount(BeeAssetCommonDTO beeAssetCommonDTO, Map map) throws Exception
	{      
		return beeAssetListDAO.findTotalCount(beeAssetCommonDTO,map);
	}
	@Override
	public int insertAsset(List list) throws Exception {
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			beeAssetListDAO.insertAsset(map);
		}
		return resultQty;
	}
	@Override
	public int updateAsset(List list) throws Exception {
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			beeAssetListDAO.updateAsset(map);
		}
		return resultQty;
	}
	
	public List eqAsmbList(Map map) throws Exception
	{      
		return beeAssetListDAO.eqAsmbList(map);
	}
	@Override
	public int insertEqAsmb(List list) throws Exception {
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			beeAssetListDAO.insertEqAsmb(map);
			beeAssetListDAO.makeEqAsmbFullDesc(map);
		}
		return resultQty;
	}
	@Override
	public int updateEqAsmb(List list) throws Exception {
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			beeAssetListDAO.updateEqAsmb(map);
			beeAssetListDAO.makeEqAsmbFullDesc(map);
		}
		return resultQty;
	}

	public List eqSpecList(Map map) throws Exception
	{      
		return beeAssetListDAO.eqSpecList(map);
	}
	@Override
	public int insertEqSpec(List list) throws Exception {
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			beeAssetListDAO.insertEqSpec(map);
		}
		return resultQty;
	}
	@Override
	public int updateEqSpec(List list) throws Exception {
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			beeAssetListDAO.updateEqSpec(map);
		}
		return resultQty;
	}

	public List eqPartList(Map map) throws Exception
	{      
		return beeAssetListDAO.eqPartList(map);
	}
	@Override
	public int insertEqPart(List list) throws Exception {
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			beeAssetListDAO.insertEqPart(map);
		}
		return resultQty;
	}
	@Override
	public int updateEqPart(List list) throws Exception {
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			beeAssetListDAO.updateEqPart(map);
		}
		return resultQty;
	}
	
}

