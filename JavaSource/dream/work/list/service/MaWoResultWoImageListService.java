package dream.work.list.service;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.List;

import com.microsoft.azure.storage.StorageException;

import common.bean.User;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultWoImageListDTO;

/**
 * 작업결과 작업사진  목록
 * @author  kim21017
 * @version $Id: MaWoResultWoImageListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaWoResultWoImageListService
{     
	/**
	 * 
	 * @param maWoResultMstrCommonDTO
	 * @param maWoResultWoImageListDTO
	 * @param user
	 * @return
	 * @throws StorageException 
	 * @throws URISyntaxException 
	 * @throws InvalidKeyException 
	 */
	public List findSlideImage(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultWoImageListDTO maWoResultWoImageListDTO, User user) throws InvalidKeyException, URISyntaxException, StorageException;
	
	/**
	 * 
	 * @param maWoResultMstrCommonDTO
	 * @param user
	 * @return
	 * @throws StorageException 
	 * @throws URISyntaxException 
	 * @throws InvalidKeyException 
	 */
	public MaWoResultWoImageListDTO findImage(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user) throws InvalidKeyException, URISyntaxException, StorageException;
	
	/**
	 * 
	 * @param maWoResultMstrCommonDTO
	 * @param user
	 * @return
	 */
	public String[][] getImageCount(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user);
}
