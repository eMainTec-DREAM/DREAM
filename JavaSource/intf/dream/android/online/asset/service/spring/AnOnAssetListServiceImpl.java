package intf.dream.android.online.asset.service.spring;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import common.bean.MwareConfig;
import common.file.FileUploadUtil;
import intf.dream.android.online.asset.dao.AnOnAssetListDAO;
import intf.dream.android.online.asset.dto.AnOnAssetCommonDTO;
import intf.dream.android.online.asset.service.AnOnAssetListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="anOnAssetListServiceTarget"
 * @spring.txbn id="anOnAssetListService"
 * @spring.property name="anOnAssetListDAO" ref="anOnAssetListDAO"
 */
public class AnOnAssetListServiceImpl implements AnOnAssetListService
{
    private AnOnAssetListDAO anOnAssetListDAO = null;

	public AnOnAssetListDAO getAnOnAssetListDAO() {
		return anOnAssetListDAO;
	}
	public void setAnOnAssetListDAO(AnOnAssetListDAO anOnAssetListDAO) {
		this.anOnAssetListDAO = anOnAssetListDAO;
	}
	
	public List findAssetList(AnOnAssetCommonDTO anOnAssetCommonDTO, Map map) throws Exception
	{      
		return anOnAssetListDAO.findAssetList(anOnAssetCommonDTO, map);
	}
	public List findAssetPartList(Map map) throws Exception
	{      
		return anOnAssetListDAO.findAssetPartList(map);
	}
	public List findAssetSpecList(Map map) throws Exception
	{      
		return anOnAssetListDAO.findAssetSpecList(map);
	}
	public List findAssetToolList(Map map) throws Exception
	{
		return anOnAssetListDAO.findAssetToolList(map);
	}
	public List findDocumentList(Map map) throws Exception
	{      
		List list = anOnAssetListDAO.findDocumentList(map);
		
		return list;
	}
	public List findDocumentCtgList(Map map) throws Exception
	{      
		List list = anOnAssetListDAO.findDocumentCtgList(map);
		
		return list;
	}
	public List fileCopyList(Map map) throws Exception
	{      
		List list = anOnAssetListDAO.findDocumentList(map);
		
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
		return anOnAssetListDAO.findWoHistList(map);
	}
	public String findTotalCount(AnOnAssetCommonDTO anOnAssetCommonDTO, Map map) throws Exception
	{      
		return anOnAssetListDAO.findTotalCount(anOnAssetCommonDTO,map);
	}
	@Override
	public int insertAsset(List list) throws Exception {
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			anOnAssetListDAO.insertAsset(map);
		}
		return resultQty;
	}
	@Override
	public int updateAsset(List list) throws Exception {
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			anOnAssetListDAO.updateAsset(map);
		}
		return resultQty;
	}
	
	public List eqAsmbList(Map map) throws Exception
	{      
		return anOnAssetListDAO.eqAsmbList(map);
	}
	@Override
	public int insertEqAsmb(List list) throws Exception {
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			anOnAssetListDAO.insertEqAsmb(map);
			anOnAssetListDAO.makeEqAsmbFullDesc(map);
		}
		return resultQty;
	}
	@Override
	public int updateEqAsmb(List list) throws Exception {
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			anOnAssetListDAO.updateEqAsmb(map);
			anOnAssetListDAO.makeEqAsmbFullDesc(map);
		}
		return resultQty;
	}

	public List eqSpecList(Map map) throws Exception
	{      
		return anOnAssetListDAO.eqSpecList(map);
	}
	@Override
	public int insertEqSpec(List list) throws Exception {
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			anOnAssetListDAO.insertEqSpec(map);
		}
		return resultQty;
	}
	@Override
	public int updateEqSpec(List list) throws Exception {
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			anOnAssetListDAO.updateEqSpec(map);
		}
		return resultQty;
	}

	public List eqPartList(Map map) throws Exception
	{      
		return anOnAssetListDAO.eqPartList(map);
	}
	@Override
	public int insertEqPart(List list) throws Exception {
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			anOnAssetListDAO.insertEqPart(map);
		}
		return resultQty;
	}
	@Override
	public int updateEqPart(List list) throws Exception {
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			anOnAssetListDAO.updateEqPart(map);
		}
		return resultQty;
	}
	
}

