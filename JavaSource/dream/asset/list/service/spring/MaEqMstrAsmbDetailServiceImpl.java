package dream.asset.list.service.spring;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.List;

import com.microsoft.azure.storage.StorageException;

import common.bean.User;
import common.util.CommonUtil;
import common.util.FileUtil;
import dream.asset.list.dao.MaEqMstrAsmbDetailDAO;
import dream.asset.list.dto.MaEqMstrAsmbDetailDTO;
import dream.asset.list.dto.MaEqMstrAsmbListDTO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrDetailDTO;
import dream.asset.list.service.MaEqMstrAsmbDetailService;
import dream.asset.list.service.MaEqMstrAsmbListService;
import dream.asset.list.service.MaEqMstrDetailService;

/**
 * 설비부위
 * @author kim2107
 * @version $Id: MaEqMstrAsmbDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maEqMstrAsmbDetailServiceTarget"
 * @spring.txbn id="maEqMstrAsmbDetailService"
 * @spring.property name="maEqMstrAsmbDetailDAO" ref="maEqMstrAsmbDetailDAO"
 * @spring.property name="maEqMstrDetailService" ref="maEqMstrDetailService"
 */
public class MaEqMstrAsmbDetailServiceImpl implements MaEqMstrAsmbDetailService
{
    private MaEqMstrAsmbDetailDAO maEqMstrAsmbDetailDAO = null;
    
    private MaEqMstrDetailService maEqMstrDetailService = null;
    
	public MaEqMstrAsmbDetailDAO getMaEqMstrAsmbDetailDAO() {
		return maEqMstrAsmbDetailDAO;
	}

	public MaEqMstrDetailService getMaEqMstrDetailService() {
		return maEqMstrDetailService;
	}

	public void setMaEqMstrDetailService(MaEqMstrDetailService maEqMstrDetailService) {
		this.maEqMstrDetailService = maEqMstrDetailService;
	}

	public void setMaEqMstrAsmbDetailDAO(MaEqMstrAsmbDetailDAO maEqMstrAsmbDetailDAO) {
		this.maEqMstrAsmbDetailDAO = maEqMstrAsmbDetailDAO;
	}

	public MaEqMstrAsmbDetailDTO findDetail(MaEqMstrAsmbListDTO maEqMstrAsmbListDTO, MaEqMstrCommonDTO maEqMstrCommonDTO, User user)throws Exception
    {
		MaEqMstrAsmbListService maEqMstrAsmbListService = (MaEqMstrAsmbListService) CommonUtil.getBean("maEqMstrAsmbListService", user);
		return (MaEqMstrAsmbDetailDTO) CommonUtil.makeDetailFromList(maEqMstrAsmbListService.findAsmbList(maEqMstrCommonDTO, maEqMstrAsmbListDTO, user), new MaEqMstrAsmbDetailDTO());
    }
    
	public int updateDetail(MaEqMstrAsmbDetailDTO maEqMstrAsmbDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO, User user) throws Exception
    {
	    int rtnValue = 0;
	    
	    //update 전 기존 데이터 find(description 또는 부모가 변경되었는지 확인용)
	    MaEqMstrAsmbListDTO maEqMstrAsmbListDTO = new MaEqMstrAsmbListDTO();
	    maEqMstrAsmbListDTO.setEqAsmbId(maEqMstrAsmbDetailDTO.getEqAsmbId());
	    MaEqMstrAsmbDetailDTO originDTO = this.findDetail(maEqMstrAsmbListDTO, maEqMstrCommonDTO, user);
	    
	    List list = new ArrayList();
	    list.add(maEqMstrAsmbDetailDTO);
	    rtnValue = maEqMstrAsmbDetailDAO.updateDetail(list, user)[0];
	    
	    // description이 변경되었거나 부모가 변경되었을 경우 full_desc를 update
        if(!maEqMstrAsmbDetailDTO.getEqAsmbDesc().equals(originDTO.getEqAsmbDesc())
                || !maEqMstrAsmbDetailDTO.getPeqAsmbId().equals(originDTO.getPeqAsmbId())){
        	this.updateFullDesc(maEqMstrAsmbDetailDTO, maEqMstrCommonDTO, user);
        }
	    
        return rtnValue;
    }
	
	public int insertDetail(MaEqMstrAsmbDetailDTO maEqMstrAsmbDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO, User user) throws Exception
    {        
	    int rtnValue = 0;
	    
	    rtnValue = maEqMstrAsmbDetailDAO.insertDetail( maEqMstrAsmbDetailDTO, maEqMstrCommonDTO, user);
	    this.updateFullDesc(maEqMstrAsmbDetailDTO, maEqMstrCommonDTO, user);
	    
        return rtnValue;
    }

    public List findSlideImage(MaEqMstrAsmbDetailDTO maEqMstrAsmbDetailDTO, String compNo) throws InvalidKeyException, URISyntaxException, StorageException
    {
    	return FileUtil.makeSlideImg(maEqMstrAsmbDetailDTO.getEqAsmbId(), "EQASMB");
    }
	
    public List getPhotoList(MaEqMstrAsmbDetailDTO maEqMstrAsmbDetailDTO, User loginUser) throws InvalidKeyException, URISyntaxException, StorageException
    {
    	return FileUtil.makeSlideImg(maEqMstrAsmbDetailDTO.getEqAsmbId(), "EQASMB");
    }

	public String copyDetail(String oldEquipId, String newEquipId, String oldKeyId, String newKeyId, User user) throws Exception {
		
		//이미지 파일 복사
		MaEqMstrDetailDTO maEqMstrDetailDTO = new MaEqMstrDetailDTO();
		
		maEqMstrDetailDTO.setOldEqAsmbId(oldKeyId);
		maEqMstrDetailDTO.setEqAsmbId(newKeyId);
		List resultList = maEqMstrDetailService.fileCopyUpload(maEqMstrDetailDTO, "EQASMB", user);
		maEqMstrDetailDTO.setSlideFileList(resultList);
		
		maEqMstrAsmbDetailDAO.copyDetail(oldEquipId, newEquipId, oldKeyId, newKeyId, user);
		
		//full_desc를 update
		MaEqMstrAsmbDetailDTO maEqMstrAsmbDetailDTO = new MaEqMstrAsmbDetailDTO();
		MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
		maEqMstrCommonDTO.setEquipId(newEquipId);
		maEqMstrAsmbDetailDTO.setEqAsmbId(newKeyId);
        this.updateFullDesc(maEqMstrAsmbDetailDTO, maEqMstrCommonDTO, user);
        
		return "0";
	}
	
	private int updateFullDesc(MaEqMstrAsmbDetailDTO maEqMstrAsmbDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO, User user) throws Exception
    {
        int rtnValue = 0;
        
        MaEqMstrAsmbListService maEqMstrAsmbListService = (MaEqMstrAsmbListService) CommonUtil.getBean("maEqMstrAsmbListService", user);
        List list = CommonUtil.makeFullDesc(maEqMstrAsmbListService.findAsmbList(maEqMstrCommonDTO, new MaEqMstrAsmbListDTO(), user), maEqMstrAsmbDetailDTO.getEqAsmbId(), "EQASMBID", "PEQASMBID", "EQASMBDESC", "FULLDESC", MaEqMstrAsmbDetailDTO.class);

        maEqMstrAsmbDetailDAO.updateDetail(list, user);
        
        return rtnValue;
    }
}
