package dream.part.list.service.spring;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.List;

import com.microsoft.azure.storage.StorageException;

import common.bean.User;
import common.util.FileUtil;
import dream.asset.list.service.MaEqMstrDetailService;
import dream.part.list.dao.MaPtMstrDetailDAO;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.dto.MaPtMstrDetailDTO;
import dream.part.list.service.MaPtMstrDetailService;

/**
 * 보전자재분류(마스터) - 상세 serviceimpl 
 * @author  
 * @version $Id:  $
 * @since   1.0
 * @spring.bean id="maPtMstrDetailServiceTarget"
 * @spring.txbn id="maPtMstrDetailService"
 * @spring.property name="maPtMstrDetailDAO" ref="maPtMstrDetailDAO"
 * @spring.property name="maEqMstrDetailService" ref="maEqMstrDetailService"
 */
public class MaPtMstrDetailServiceImpl implements MaPtMstrDetailService
{
    private MaPtMstrDetailDAO maPtMstrDetailDAO = null;
    
    private MaEqMstrDetailService maEqMstrDetailService = null;
    
    public MaEqMstrDetailService getMaEqMstrDetailService()
    {
        return maEqMstrDetailService;
    }

    public void setMaEqMstrDetailService(MaEqMstrDetailService maEqMstrDetailService)
    {
        this.maEqMstrDetailService = maEqMstrDetailService;
    }

    public MaPtMstrDetailDAO getMaPtMstrDetailDAO() 
    {
		return maPtMstrDetailDAO;
	}

	public void setMaPtMstrDetailDAO(MaPtMstrDetailDAO maPtMstrDetailDAO) 
	{
		this.maPtMstrDetailDAO = maPtMstrDetailDAO;
	}

	public MaPtMstrDetailDTO findDetail(MaPtMstrCommonDTO maPtMstrCommonDTO, User loginUser)
    {	    
        return maPtMstrDetailDAO.findDetail(maPtMstrCommonDTO, loginUser);
    }

    public List getPhotoList(MaPtMstrCommonDTO maPtMstrCommonDTO, User loginUser) throws InvalidKeyException, URISyntaxException, StorageException
    {
        return FileUtil.makeSlideImg(maPtMstrCommonDTO.getPartId(), "PTMSTR");
    }
    
	public int insertDetail(MaPtMstrDetailDTO maPtMstrDetailDTO, User loginUser) throws Exception
    {   
		maPtMstrDetailDTO.setFullDesc(makeFullDesc(maPtMstrDetailDTO));
		
		int result = maPtMstrDetailDAO.insertDetail(maPtMstrDetailDTO, loginUser);
		
		//maPtMstrDetailDAO.createStock(maPtMstrDetailDTO, loginUser);
		
		maPtMstrDetailDAO.SP_IF_UPD_TXPARTS(loginUser);
        return result;
    }
	
	public int updateDetail(MaPtMstrDetailDTO maPtMstrDetailDTO, User loginUser) throws Exception
    {        
		maPtMstrDetailDTO.setFullDesc(makeFullDesc(maPtMstrDetailDTO));
		
		int result = maPtMstrDetailDAO.updateDetail(maPtMstrDetailDTO, loginUser);
		//cycle, period_type은 설비구성자재에 해당 값이 없을 경우 일괄 업데이트 해 줌.
		//result = maPtMstrDetailDAO.updateEqPartCycle(maPtMstrDetailDTO, loginUser); 
		//cycle, period type column은 Drop되어 존재하지 않음. 주석처리 (2017-09-07 노정현)
		
		maPtMstrDetailDAO.SP_IF_UPD_TXPARTS(loginUser);
        return result;
    }
	
	public String validPartNo(MaPtMstrDetailDTO maPtMstrDetailDTO, User loginUser) throws Exception
    {
        return maPtMstrDetailDAO.validPartNo(maPtMstrDetailDTO, loginUser);
    }

    @Override
    public List findSlideImage(MaPtMstrCommonDTO maPtMstrCommonDTO, User loginUser) throws InvalidKeyException, URISyntaxException, StorageException
    {
        return FileUtil.makeSlideImg(maPtMstrCommonDTO.getPartId(), "PTMSTR");
    }
    
    
    private String makeFullDesc(MaPtMstrDetailDTO maPtMstrDetailDTO){
    	
    	String fullDesc = "";
    	
		if(!"".equals(maPtMstrDetailDTO.getPartNo())){
			fullDesc = maPtMstrDetailDTO.getPartNo();
		}
		if(!"".equals(maPtMstrDetailDTO.getDescription())){
			fullDesc = fullDesc + ","+ maPtMstrDetailDTO.getDescription();
		}
		if(!"".equals(maPtMstrDetailDTO.getPtSize())){
			fullDesc = fullDesc + ","+ maPtMstrDetailDTO.getPtSize();
		}
		if(!"".equals(maPtMstrDetailDTO.getMaker())){
			fullDesc = fullDesc + ","+ maPtMstrDetailDTO.getMaker();
		}
		if(!"".equals(maPtMstrDetailDTO.getModel())){
		    fullDesc = fullDesc + ","+ maPtMstrDetailDTO.getModel();
		}
		if(!"".equals(maPtMstrDetailDTO.getVendorPtCode())){
			fullDesc = fullDesc + ","+ maPtMstrDetailDTO.getVendorPtCode();
		}
		
		return fullDesc;
		
    }

}
