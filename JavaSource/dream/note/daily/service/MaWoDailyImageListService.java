package dream.note.daily.service;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.List;

import com.microsoft.azure.storage.StorageException;

import common.bean.User;
import dream.note.daily.dto.MaWoDailyCommonDTO;
import dream.note.daily.dto.MaWoDailyImageListDTO;

/**
 * 일일작업승인 사진  목록
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface MaWoDailyImageListService
{     
	/**
	 * 
	 * @param maWoDailyCommonDTO
	 * @param maWoDailyImageListDTO
	 * @param user
	 * @return
	 * @throws StorageException 
	 * @throws URISyntaxException 
	 * @throws InvalidKeyException 
	 */
	public List findSlideImage(MaWoDailyCommonDTO maWoDailyCommonDTO, MaWoDailyImageListDTO maWoDailyImageListDTO, User user) throws InvalidKeyException, URISyntaxException, StorageException;
	
	/**
	 * 
	 * @param maWoDailyCommonDTO
	 * @param user
	 * @return
	 * @throws StorageException 
	 * @throws URISyntaxException 
	 * @throws InvalidKeyException 
	 */
	public MaWoDailyImageListDTO findImage(MaWoDailyCommonDTO maWoDailyCommonDTO, User user) throws InvalidKeyException, URISyntaxException, StorageException;
	
	/**
	 * 
	 * @param maWoDailyCommonDTO
	 * @param user
	 * @return
	 */
	public String[][] getImageCount(MaWoDailyCommonDTO maWoDailyCommonDTO, User user);
}
