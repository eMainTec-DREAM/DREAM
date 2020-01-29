package dream.tool.list.service.spring;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.List;

import com.microsoft.azure.storage.StorageException;

import common.bean.User;
import common.util.FileUtil;
import dream.asset.list.service.MaEqMstrDetailService;
import dream.tool.list.dao.MaPttMstrDetailDAO;
import dream.tool.list.dto.MaPttMstrCommonDTO;
import dream.tool.list.dto.MaPttMstrDetailDTO;
import dream.tool.list.service.MaPttMstrDetailService;

/**
 * 보전자재분류(마스터) - 상세 serviceimpl 
 * @author  
 * @version $Id:  $
 * @since   1.0
 * @spring.bean id="maPttMstrDetailServiceTarget"
 * @spring.txbn id="maPttMstrDetailService"
 * @spring.property name="maPttMstrDetailDAO" ref="maPttMstrDetailDAO"
 * @spring.property name="maEqMstrDetailService" ref="maEqMstrDetailService"
 */
public class MaPttMstrDetailServiceImpl implements MaPttMstrDetailService
{
    private MaPttMstrDetailDAO maPttMstrDetailDAO = null;
    
    private MaEqMstrDetailService maEqMstrDetailService = null;
    
    public MaEqMstrDetailService getMaEqMstrDetailService()
    {
        return maEqMstrDetailService;
    }

    public void setMaEqMstrDetailService(MaEqMstrDetailService maEqMstrDetailService)
    {
        this.maEqMstrDetailService = maEqMstrDetailService;
    }

    public MaPttMstrDetailDAO getMaPttMstrDetailDAO() 
    {
		return maPttMstrDetailDAO;
	}

	public void setMaPttMstrDetailDAO(MaPttMstrDetailDAO maPttMstrDetailDAO) 
	{
		this.maPttMstrDetailDAO = maPttMstrDetailDAO;
	}

	public MaPttMstrDetailDTO findDetail(MaPttMstrCommonDTO maPttMstrCommonDTO, User loginUser)
    {	    
        return maPttMstrDetailDAO.findDetail(maPttMstrCommonDTO, loginUser);
    }

    public List getPhotoList(MaPttMstrCommonDTO maPttMstrCommonDTO, User loginUser) throws InvalidKeyException, URISyntaxException, StorageException
    {
        return FileUtil.makeSlideImg(maPttMstrCommonDTO.getPartId(), "PTTMSTR");
    }
    
	public int insertDetail(MaPttMstrDetailDTO maPttMstrDetailDTO, User loginUser) throws Exception
    {   
		int result = maPttMstrDetailDAO.insertDetail(maPttMstrDetailDTO, loginUser);
		maPttMstrDetailDAO.SP_IF_UPD_TXPARTS(loginUser);
        return result;
    }
	
	public int updateDetail(MaPttMstrDetailDTO maPttMstrDetailDTO, User loginUser) throws Exception
    {        
		int result = maPttMstrDetailDAO.updateDetail(maPttMstrDetailDTO, loginUser);
		maPttMstrDetailDAO.SP_IF_UPD_TXPARTS(loginUser);
        return result;
    }
	
	public String validDeptNo(MaPttMstrDetailDTO maPttMstrDetailDTO, User loginUser) throws Exception
    {
        return maPttMstrDetailDAO.validPartNo(maPttMstrDetailDTO, loginUser);
    }

    @Override
    public List findSlideImage(MaPttMstrCommonDTO maPttMstrCommonDTO, User loginUser) throws InvalidKeyException, URISyntaxException, StorageException
    {
        return FileUtil.makeSlideImg(maPttMstrCommonDTO.getPartId(), "PTTMSTR");
    }

}
